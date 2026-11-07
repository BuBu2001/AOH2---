#!/usr/bin/env python3
"""Аудит ассетов: какие файлы ресурсов требует код и какие из них есть на диске.

Использование:
    python tools/check_assets.py "C:\\path\\to\\game_folder"

Скрипт вытаскивает из исходников пути, которые передаются в
ImageManager.addImage(...) / Gdx.files.internal(...), разворачивает
CFG.getRescouresPath() во все варианты (H/XH/XXH/XXXH/XXXXH) и проверяет
наличие файла в папке игры (без учёта регистра; для AoH3 дополнительно
пробует подменить UI/ -> ui/interface/).
"""

import os
import re
import sys
from collections import Counter

SRC = os.path.join("age", "of", "civilizations2", "jakowski", "lukasz")
EXT = (".png", ".jpg", ".jpeg", ".json", ".txt", ".fnt", ".glsl", ".ogg", ".mp3",
       ".ttf", ".ser", ".csv", ".xml", ".properties", ".pack", ".atlas")
BUCKETS = ["H", "XH", "XXH", "XXXH", "XXXXH"]
CALL_RE = re.compile(r"(?:addImage|Gdx\.files\.(?:internal|local))\s*\(([^;]{0,600}?)\)\s*;", re.S)
LIT_RE = re.compile(r'"([^"\\]*)"')


def collect():
    paths = set()
    for name in os.listdir(SRC):
        if not name.endswith(".java"):
            continue
        with open(os.path.join(SRC, name), encoding="utf-8", errors="replace") as fh:
            text = fh.read()
        for m in CALL_RE.finditer(text):
            args = m.group(1)
            parts = LIT_RE.findall(args)
            if not parts:
                continue
            joined = "".join(parts)
            if not joined.lower().endswith(EXT):
                continue
            if "getRescouresPath" in args:
                for b in BUCKETS:
                    paths.add(joined.replace("getRescouresPath", b))
            else:
                paths.add(joined)
    return paths


def index(root):
    """путь в нижнем регистре -> реальный путь"""
    idx = {}
    for dirpath, _dirs, files in os.walk(root):
        for f in files:
            full = os.path.join(dirpath, f)
            rel = os.path.relpath(full, root).replace(os.sep, "/").lower()
            idx[rel] = full
    return idx


def variants(p):
    yield p
    if p.startswith("UI/"):
        yield "ui/interface/" + p[3:]
        yield "ui/" + p[3:]


def main():
    if len(sys.argv) < 2:
        sys.exit(__doc__)
    root = sys.argv[1]
    if not os.path.isdir(root):
        sys.exit(f"нет такой папки: {root}")

    wanted = sorted(collect())
    idx = index(root)

    found, missing = [], []
    for p in wanted:
        hit = None
        for v in variants(p):
            key = v.replace("\\", "/").lower()
            if key in idx:
                hit = idx[key]
                break
        (found if hit else missing).append((p, hit))

    print(f"папка игры : {root}")
    print(f"файлов в ней: {len(idx):,}")
    print(f"требуется кодом: {len(wanted)}")
    print(f"найдено: {len(found)}  ({100*len(found)//max(1,len(wanted))}%)")
    print(f"не найдено: {len(missing)}")

    by_dir = Counter(p.split("/")[0] + "/" + (p.split("/")[1] if "/" in p else "") for p, _ in missing)
    print("\n-- чего не хватает (по папкам) --")
    for k, v in by_dir.most_common(25):
        print(f"{v:5d}  {k}")

    print("\n-- примеры найденного --")
    for p, h in found[:15]:
        print(f"  OK   {p}  ->  {os.path.relpath(h, root)}")
    print("\n-- примеры отсутствующего --")
    for p, _ in missing[:25]:
        print(f"  MISS {p}")


if __name__ == "__main__":
    main()
