# Документация по исходникам Age of Civilizations II 1.4

Три документа, от общего к частному:

| Файл | Что внутри | Когда открывать |
|---|---|---|
| [`ARCHITECTURE.md`](ARCHITECTURE.md) | карта подсистем, поток запуска, UI-архитектура, модель игры, ход, ИИ, сейвы, таблица «куда идти, если…» | первым делом, чтобы понять устройство |
| [`FILES.md`](FILES.md) | каталог **всех 1552** `.java`: семейство, строки, базовый класс, назначение, ключевые методы | когда нужно найти конкретный файл |
| [`files.json`](files.json) | те же данные машинночитаемо (для скриптов/поиска) | для автоматизации |
| [`ASSETS.md`](ASSETS.md) | какие ресурсы нужны игре, где они лежат, чем проверить полноту | перед первой сборкой/запуском |

## Как быстро найти нужное

```powershell
# по имени файла
Get-ChildItem -Recurse -Filter "*Province*.java"

# по содержимому
Select-String -Path age\of\civilizations2\jakowski\lukasz\*.java -Pattern "langManager.get"

# по каталогу (jq — опционально)
jq -r '.[] | select(.description|test("дипломат";"i")) | .file' docs/files.json
```

## Обновление каталога

```powershell
python tools/gen_file_index.py
```

Скрипт перечитывает `age/of/civilizations2/jakowski/lukasz/*.java` (+ `desktop/*.java`) и
перегенерирует `FILES.md` и `files.json`. Описания ключевых классов задаются в `DESCRIPTIONS`,
остальные строятся по префиксу имени и словарю `WORDS`/`PHRASES` — их можно дополнять.
