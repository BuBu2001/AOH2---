/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button_Game;
import age.of.civilizations2.jakowski.lukasz.Button_Menu;
import age.of.civilizations2.jakowski.lukasz.Button_Menu_Classic;
import age.of.civilizations2.jakowski.lukasz.Button_Menu_ReflectedBG;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.ColorPicker_AoC;
import age.of.civilizations2.jakowski.lukasz.Game_Render_Province;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.Menu;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Text;
import age.of.civilizations2.jakowski.lukasz.SliderMenu;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

class Menu_TerrainType_Add
extends SliderMenu {
    private String sName;
    private int iNameWidth;
    private String sIconFileName;
    private final String sIconFileNameType = ".png";

    protected Menu_TerrainType_Add() {
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        menuElements.add(new Button_Game(null, -1, CFG.PADDING, CFG.PADDING, true));
        menuElements.add(new Button_Menu("", -1, CFG.BUTTON_WIDTH + CFG.PADDING * 2, 0, CFG.GAME_WIDTH - (CFG.BUTTON_WIDTH + CFG.PADDING * 2) * 2, CFG.BUTTON_HEIGHT + CFG.PADDING * 2, true){

            @Override
            protected Color getColor(boolean isActive) {
                return isActive ? new Color(0.82f, 0.82f, 0.82f, 1.0f) : (this.getClickable() ? new Color(1.0f, 1.0f, 1.0f, 1.0f) : new Color(0.84f, 0.84f, 0.84f, 0.7f));
            }

            @Override
            protected String getTextToDraw() {
                return Menu_TerrainType_Add.this.sName + ": " + super.getText();
            }

            @Override
            protected int getTextWidth() {
                return super.getTextWidth() + Menu_TerrainType_Add.this.iNameWidth;
            }

            @Override
            protected void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
            }

            @Override
            protected void buildElementHover() {
                ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("TerrainTypeName") + ".", CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
        });
        menuElements.add(new Button_Game(null, -1, CFG.GAME_WIDTH - CFG.BUTTON_WIDTH - CFG.PADDING, CFG.PADDING, true){

            @Override
            protected void buildElementHover() {
                ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Save") + ".", CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
        });
        menuElements.add(new Button_Menu("", (int)(50.0f * CFG.GUI_SCALE), 0, CFG.BUTTON_HEIGHT + CFG.PADDING * 3, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT, true){

            @Override
            protected String getTextToDraw() {
                return Menu_TerrainType_Add.this.sIconFileName + ": \"" + super.getText() + ".png" + "\"";
            }

            @Override
            protected void buildElementHover() {
                ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Path") + ": "));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text("UI/" + CFG.getRescouresPath() + "terrain/", CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
        });
        menuElements.add(new Button_Menu(null, -1, 0, CFG.BUTTON_HEIGHT * 2 + CFG.PADDING * 4, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT, true){

            @Override
            protected void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                super.drawText(oSB, iTranslateX, iTranslateY, isActive);
                oSB.setColor(new Color(CFG.editorTerrain_Data2.getColor().getR(), CFG.editorTerrain_Data2.getColor().getG(), CFG.editorTerrain_Data2.getColor().getB(), 1.0f));
                ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() + this.getWidth() / 2 - this.getTextWidth() / 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 + this.getTextHeight() / 2 + CFG.PADDING - 1 + iTranslateY, this.getTextWidth(), CFG.CIV_COLOR_WIDTH);
                oSB.setColor(Color.WHITE);
            }
        });
        menuElements.add(new Button_Menu("-", -1, 0, CFG.BUTTON_HEIGHT * 3 + CFG.PADDING * 5, CFG.BUTTON_WIDTH * 2, CFG.BUTTON_HEIGHT, true){

            @Override
            protected void buildElementHover() {
                ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text("-1%", CFG.COLOR_TEXT_MODIFIER_NEGATIVE));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
        });
        menuElements.add(new Button_Menu_Classic(null, -1, CFG.BUTTON_WIDTH * 2, CFG.BUTTON_HEIGHT * 3 + CFG.PADDING * 5, CFG.GAME_WIDTH - CFG.BUTTON_WIDTH * 4, CFG.BUTTON_HEIGHT, true){
            private int iCurrent;

            @Override
            protected void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                super.drawText(oSB, iTranslateX, iTranslateY, isActive);
                CFG.drawTextWithShadow(oSB, (this.getCurrent() > 0 ? "+" : "") + this.getCurrent() + "%", this.getPosX() + this.getWidth() / 2 + this.getTextWidth() / 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - this.getTextHeight() / 2 + iTranslateY, this.getCurrent() == 0 ? CFG.COLOR_TEXT_MODIFIER_NEUTRAL : (this.getCurrent() > 0 ? CFG.COLOR_TEXT_MODIFIER_POSITIVE : CFG.COLOR_TEXT_MODIFIER_NEGATIVE));
            }

            @Override
            protected int getCurrent() {
                return this.iCurrent;
            }

            @Override
            protected void setCurrent(int nCurrent) {
                if (nCurrent > 95) {
                    nCurrent = 95;
                } else if (nCurrent < -95) {
                    nCurrent = -95;
                }
                this.iCurrent = nCurrent;
            }
        });
        menuElements.add(new Button_Menu_ReflectedBG("+", -1, CFG.GAME_WIDTH - CFG.BUTTON_WIDTH * 2, CFG.BUTTON_HEIGHT * 3 + CFG.PADDING * 5, CFG.BUTTON_WIDTH * 2, CFG.BUTTON_HEIGHT, true){

            @Override
            protected void buildElementHover() {
                ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text("+1%", CFG.COLOR_TEXT_MODIFIER_NEGATIVE));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
        });
        menuElements.add(new Button_Menu("-", -1, 0, CFG.BUTTON_HEIGHT * 4 + CFG.PADDING * 6, CFG.BUTTON_WIDTH * 2, CFG.BUTTON_HEIGHT, true){

            @Override
            protected void buildElementHover() {
                ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text("-1%", CFG.COLOR_TEXT_MODIFIER_NEGATIVE));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
        });
        menuElements.add(new Button_Menu_Classic(null, -1, CFG.BUTTON_WIDTH * 2, CFG.BUTTON_HEIGHT * 4 + CFG.PADDING * 6, CFG.GAME_WIDTH - CFG.BUTTON_WIDTH * 4, CFG.BUTTON_HEIGHT, true){
            private int iCurrent;

            @Override
            protected void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                super.drawText(oSB, iTranslateX, iTranslateY, isActive);
                CFG.drawTextWithShadow(oSB, (this.getCurrent() > 0 ? "+" : "") + this.getCurrent() + "%", this.getPosX() + this.getWidth() / 2 + this.getTextWidth() / 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - this.getTextHeight() / 2 + iTranslateY, this.getCurrent() == 0 ? CFG.COLOR_TEXT_MODIFIER_NEUTRAL : (this.getCurrent() < 0 ? CFG.COLOR_TEXT_MODIFIER_POSITIVE : CFG.COLOR_TEXT_MODIFIER_NEGATIVE));
            }

            @Override
            protected int getCurrent() {
                return this.iCurrent;
            }

            @Override
            protected void setCurrent(int nCurrent) {
                if (nCurrent > 95) {
                    nCurrent = 95;
                } else if (nCurrent < -95) {
                    nCurrent = -95;
                }
                this.iCurrent = nCurrent;
            }
        });
        menuElements.add(new Button_Menu_ReflectedBG("+", -1, CFG.GAME_WIDTH - CFG.BUTTON_WIDTH * 2, CFG.BUTTON_HEIGHT * 4 + CFG.PADDING * 6, CFG.BUTTON_WIDTH * 2, CFG.BUTTON_HEIGHT, true){

            @Override
            protected void buildElementHover() {
                ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text("+1%", CFG.COLOR_TEXT_MODIFIER_POSITIVE));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
        });
        menuElements.add(new Button_Menu("-", -1, 0, CFG.BUTTON_HEIGHT * 6 + CFG.PADDING * 8, CFG.BUTTON_WIDTH * 2, CFG.BUTTON_HEIGHT, true){

            @Override
            protected void buildElementHover() {
                ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text("-1%", CFG.COLOR_TEXT_MODIFIER_NEGATIVE));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
        });
        menuElements.add(new Button_Menu_Classic(null, -1, CFG.BUTTON_WIDTH * 2, CFG.BUTTON_HEIGHT * 6 + CFG.PADDING * 8, CFG.GAME_WIDTH - CFG.BUTTON_WIDTH * 4, CFG.BUTTON_HEIGHT, true){
            private int iCurrent;

            @Override
            protected void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                super.drawText(oSB, iTranslateX, iTranslateY, isActive);
                CFG.drawTextWithShadow(oSB, (this.getCurrent() > 0 ? "+" : "") + this.getCurrent() + "%", this.getPosX() + this.getWidth() / 2 + this.getTextWidth() / 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - this.getTextHeight() / 2 + iTranslateY, this.getCurrent() == 0 ? CFG.COLOR_TEXT_MODIFIER_NEUTRAL : (this.getCurrent() > 0 ? CFG.COLOR_TEXT_MODIFIER_POSITIVE : CFG.COLOR_TEXT_MODIFIER_NEGATIVE));
            }

            @Override
            protected int getCurrent() {
                return this.iCurrent;
            }

            @Override
            protected void setCurrent(int nCurrent) {
                if (nCurrent > 95) {
                    nCurrent = 95;
                } else if (nCurrent < -95) {
                    nCurrent = -95;
                }
                this.iCurrent = nCurrent;
            }
        });
        menuElements.add(new Button_Menu_ReflectedBG("+", -1, CFG.GAME_WIDTH - CFG.BUTTON_WIDTH * 2, CFG.BUTTON_HEIGHT * 6 + CFG.PADDING * 8, CFG.BUTTON_WIDTH * 2, CFG.BUTTON_HEIGHT, true){

            @Override
            protected void buildElementHover() {
                ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text("+1%", CFG.COLOR_TEXT_MODIFIER_POSITIVE));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
        });
        menuElements.add(new Button_Menu("-", -1, 0, CFG.BUTTON_HEIGHT * 7 + CFG.PADDING * 9, CFG.BUTTON_WIDTH * 2, CFG.BUTTON_HEIGHT, true){

            @Override
            protected void buildElementHover() {
                ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text("-1%", CFG.COLOR_TEXT_MODIFIER_NEGATIVE));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
        });
        menuElements.add(new Button_Menu_Classic(null, -1, CFG.BUTTON_WIDTH * 2, CFG.BUTTON_HEIGHT * 7 + CFG.PADDING * 9, CFG.GAME_WIDTH - CFG.BUTTON_WIDTH * 4, CFG.BUTTON_HEIGHT, true){
            private int iCurrent;

            @Override
            protected void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                super.drawText(oSB, iTranslateX, iTranslateY, isActive);
                CFG.drawTextWithShadow(oSB, (this.getCurrent() > 0 ? "+" : "") + this.getCurrent() + "%", this.getPosX() + this.getWidth() / 2 + this.getTextWidth() / 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - this.getTextHeight() / 2 + iTranslateY, this.getCurrent() == 0 ? CFG.COLOR_TEXT_MODIFIER_NEUTRAL : (this.getCurrent() > 0 ? CFG.COLOR_TEXT_MODIFIER_POSITIVE : CFG.COLOR_TEXT_MODIFIER_NEGATIVE));
            }

            @Override
            protected int getCurrent() {
                return this.iCurrent;
            }

            @Override
            protected void setCurrent(int nCurrent) {
                if (nCurrent > 95) {
                    nCurrent = 95;
                } else if (nCurrent < -95) {
                    nCurrent = -95;
                }
                this.iCurrent = nCurrent;
            }
        });
        menuElements.add(new Button_Menu_ReflectedBG("+", -1, CFG.GAME_WIDTH - CFG.BUTTON_WIDTH * 2, CFG.BUTTON_HEIGHT * 7 + CFG.PADDING * 9, CFG.BUTTON_WIDTH * 2, CFG.BUTTON_HEIGHT, true){

            @Override
            protected void buildElementHover() {
                ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text("+1%", CFG.COLOR_TEXT_MODIFIER_POSITIVE));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
        });
        menuElements.add(new Button_Menu("-", -1, 0, CFG.BUTTON_HEIGHT * 8 + CFG.PADDING * 10, CFG.BUTTON_WIDTH * 2, CFG.BUTTON_HEIGHT, true){

            @Override
            protected void buildElementHover() {
                ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text("-1%", CFG.COLOR_TEXT_MODIFIER_POSITIVE));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
        });
        menuElements.add(new Button_Menu_Classic(null, -1, CFG.BUTTON_WIDTH * 2, CFG.BUTTON_HEIGHT * 8 + CFG.PADDING * 10, CFG.GAME_WIDTH - CFG.BUTTON_WIDTH * 4, CFG.BUTTON_HEIGHT, true){
            private int iCurrent;

            @Override
            protected void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                super.drawText(oSB, iTranslateX, iTranslateY, isActive);
                CFG.drawTextWithShadow(oSB, (this.getCurrent() > 0 ? "+" : "") + this.getCurrent() + "%", this.getPosX() + this.getWidth() / 2 + this.getTextWidth() / 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - this.getTextHeight() / 2 + iTranslateY, this.getCurrent() == 0 ? CFG.COLOR_TEXT_MODIFIER_NEUTRAL : (this.getCurrent() < 0 ? CFG.COLOR_TEXT_MODIFIER_POSITIVE : CFG.COLOR_TEXT_MODIFIER_NEGATIVE));
            }

            @Override
            protected int getCurrent() {
                return this.iCurrent;
            }

            @Override
            protected void setCurrent(int nCurrent) {
                if (nCurrent > 95) {
                    nCurrent = 95;
                } else if (nCurrent < -95) {
                    nCurrent = -95;
                }
                this.iCurrent = nCurrent;
            }
        });
        menuElements.add(new Button_Menu_ReflectedBG("+", -1, CFG.GAME_WIDTH - CFG.BUTTON_WIDTH * 2, CFG.BUTTON_HEIGHT * 8 + CFG.PADDING * 10, CFG.BUTTON_WIDTH * 2, CFG.BUTTON_HEIGHT, true){

            @Override
            protected void buildElementHover() {
                ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text("+1%", CFG.COLOR_TEXT_MODIFIER_NEGATIVE));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
        });
        menuElements.add(new Button_Menu("-", -1, 0, CFG.BUTTON_HEIGHT * 5 + CFG.PADDING * 7, CFG.BUTTON_WIDTH * 2, CFG.BUTTON_HEIGHT, true){

            @Override
            protected void buildElementHover() {
                ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text("-1%", CFG.COLOR_TEXT_MODIFIER_POSITIVE));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
        });
        menuElements.add(new Button_Menu_Classic(null, -1, CFG.BUTTON_WIDTH * 2, CFG.BUTTON_HEIGHT * 5 + CFG.PADDING * 7, CFG.GAME_WIDTH - CFG.BUTTON_WIDTH * 4, CFG.BUTTON_HEIGHT, true){
            private int iCurrent;

            @Override
            protected void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                super.drawText(oSB, iTranslateX, iTranslateY, isActive);
                CFG.drawTextWithShadow(oSB, (this.getCurrent() > 0 ? "+" : "") + this.getCurrent() + "%", this.getPosX() + this.getWidth() / 2 + this.getTextWidth() / 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - this.getTextHeight() / 2 + iTranslateY, this.getCurrent() == 0 ? CFG.COLOR_TEXT_MODIFIER_NEUTRAL : (this.getCurrent() < 0 ? CFG.COLOR_TEXT_MODIFIER_POSITIVE : CFG.COLOR_TEXT_MODIFIER_NEGATIVE));
            }

            @Override
            protected int getCurrent() {
                return this.iCurrent;
            }

            @Override
            protected void setCurrent(int nCurrent) {
                if (nCurrent > 95) {
                    nCurrent = 95;
                } else if (nCurrent < -95) {
                    nCurrent = -95;
                }
                this.iCurrent = nCurrent;
            }
        });
        menuElements.add(new Button_Menu_ReflectedBG("+", -1, CFG.GAME_WIDTH - CFG.BUTTON_WIDTH * 2, CFG.BUTTON_HEIGHT * 5 + CFG.PADDING * 7, CFG.BUTTON_WIDTH * 2, CFG.BUTTON_HEIGHT, true){

            @Override
            protected void buildElementHover() {
                ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text("+1%", CFG.COLOR_TEXT_MODIFIER_NEGATIVE));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
        });
        menuElements.add(new Button_Menu("-", -1, 0, CFG.BUTTON_HEIGHT * 9 + CFG.PADDING * 11, CFG.BUTTON_WIDTH * 2, CFG.BUTTON_HEIGHT, true){

            @Override
            protected void buildElementHover() {
                ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text("-1%", CFG.COLOR_TEXT_MODIFIER_POSITIVE));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
        });
        menuElements.add(new Button_Menu_Classic(null, -1, CFG.BUTTON_WIDTH * 2, CFG.BUTTON_HEIGHT * 9 + CFG.PADDING * 11, CFG.GAME_WIDTH - CFG.BUTTON_WIDTH * 4, CFG.BUTTON_HEIGHT, true){
            private int iCurrent;

            @Override
            protected void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                super.drawText(oSB, iTranslateX, iTranslateY, isActive);
                CFG.drawTextWithShadow(oSB, (this.getCurrent() > 0 ? "+" : "") + this.getCurrent(), this.getPosX() + this.getWidth() / 2 + this.getTextWidth() / 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - this.getTextHeight() / 2 + iTranslateY, this.getCurrent() == 0 ? CFG.COLOR_TEXT_MODIFIER_NEUTRAL : (this.getCurrent() > 0 ? CFG.COLOR_TEXT_MODIFIER_POSITIVE : CFG.COLOR_TEXT_MODIFIER_NEGATIVE));
            }

            @Override
            protected int getCurrent() {
                return this.iCurrent;
            }

            @Override
            protected void setCurrent(int nCurrent) {
                if (nCurrent > 2) {
                    nCurrent = 2;
                } else if (nCurrent < 0) {
                    nCurrent = 0;
                }
                this.iCurrent = nCurrent;
            }
        });
        menuElements.add(new Button_Menu_ReflectedBG("+", -1, CFG.GAME_WIDTH - CFG.BUTTON_WIDTH * 2, CFG.BUTTON_HEIGHT * 9 + CFG.PADDING * 11, CFG.BUTTON_WIDTH * 2, CFG.BUTTON_HEIGHT, true){

            @Override
            protected void buildElementHover() {
                ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text("+1%", CFG.COLOR_TEXT_MODIFIER_NEGATIVE));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
        });
        menuElements.add(new Button_Menu("-", -1, 0, CFG.BUTTON_HEIGHT * 10 + CFG.PADDING * 12, CFG.BUTTON_WIDTH * 2, CFG.BUTTON_HEIGHT, true){

            @Override
            protected void buildElementHover() {
                ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text("-1%", CFG.COLOR_TEXT_MODIFIER_POSITIVE));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
        });
        menuElements.add(new Button_Menu_Classic(null, -1, CFG.BUTTON_WIDTH * 2, CFG.BUTTON_HEIGHT * 10 + CFG.PADDING * 12, CFG.GAME_WIDTH - CFG.BUTTON_WIDTH * 4, CFG.BUTTON_HEIGHT, true){
            private int iCurrent;

            @Override
            protected void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                super.drawText(oSB, iTranslateX, iTranslateY, isActive);
                CFG.drawTextWithShadow(oSB, (this.getCurrent() > 0 ? "+" : "") + this.getCurrent() + "%", this.getPosX() + this.getWidth() / 2 + this.getTextWidth() / 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - this.getTextHeight() / 2 + iTranslateY, this.getCurrent() == 0 ? CFG.COLOR_TEXT_MODIFIER_NEUTRAL : (this.getCurrent() > 0 ? CFG.COLOR_TEXT_MODIFIER_POSITIVE : CFG.COLOR_TEXT_MODIFIER_NEGATIVE));
            }

            @Override
            protected int getCurrent() {
                return this.iCurrent;
            }

            @Override
            protected void setCurrent(int nCurrent) {
                if (nCurrent > 0) {
                    nCurrent = 0;
                } else if (nCurrent < -40) {
                    nCurrent = -40;
                }
                this.iCurrent = nCurrent;
            }
        });
        menuElements.add(new Button_Menu_ReflectedBG("+", -1, CFG.GAME_WIDTH - CFG.BUTTON_WIDTH * 2, CFG.BUTTON_HEIGHT * 10 + CFG.PADDING * 12, CFG.BUTTON_WIDTH * 2, CFG.BUTTON_HEIGHT, true){

            @Override
            protected void buildElementHover() {
                ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text("+1%", CFG.COLOR_TEXT_MODIFIER_NEGATIVE));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
        });
        this.initMenu(null, 0, 0, CFG.GAME_WIDTH, CFG.GAME_HEIGHT, menuElements);
        this.updateLanguage();
    }

    @Override
    protected void updateLanguage() {
        this.sName = CFG.langManager.get("TerrainTypeName");
        CFG.glyphLayout.setText(CFG.fontMain, this.sName + ": ");
        this.iNameWidth = (int)CFG.glyphLayout.width;
        this.sIconFileName = CFG.langManager.get("IconName");
        this.getMenuElement(0).setText(CFG.langManager.get("Back"));
        this.getMenuElement(1).setText(CFG.editorTerrain_Data2.getName());
        this.getMenuElement(2).setText(CFG.langManager.get("Save"));
        this.getMenuElement(3).setText(CFG.editorTerrain_Data2.getIconName());
        this.getMenuElement(4).setText(CFG.langManager.get("Color"));
        this.getMenuElement(6).setText(CFG.langManager.get("DefenseModifier") + ": ");
        this.getMenuElement(9).setText(CFG.langManager.get("MilitaryUpkeepModifier") + ": ");
        this.getMenuElement(12).setText(CFG.langManager.get("PopulationGrowthModifier") + ": ");
        this.getMenuElement(15).setText(CFG.langManager.get("EconomyGrowthModifier") + ": ");
        this.getMenuElement(18).setText(CFG.langManager.get("BuildCostModifier") + ": ");
        this.getMenuElement(21).setText(CFG.langManager.get("MovementCostModifier") + ": ");
        this.getMenuElement(24).setText(CFG.langManager.get("BaseProvinceValue") + ": ");
        this.getMenuElement(27).setText(CFG.langManager.get("BaseDevelopmentLevel") + ": ");
        this.getMenuElement(6).setCurrent((int)(CFG.editorTerrain_Data2.getDefensiveModifier() * 100.0f));
        this.getMenuElement(9).setCurrent((int)(CFG.editorTerrain_Data2.getBuildCostModifier() * 100.0f));
        this.getMenuElement(12).setCurrent((int)(CFG.editorTerrain_Data2.getPopulationGrowthModifier() * 100.0f));
        this.getMenuElement(15).setCurrent((int)(CFG.editorTerrain_Data2.getEconomyGrowthModifier() * 100.0f));
        this.getMenuElement(18).setCurrent((int)(CFG.editorTerrain_Data2.getBuildCostModifier() * 100.0f));
        this.getMenuElement(21).setCurrent((int)(CFG.editorTerrain_Data2.getMovementCost() * 100.0f));
        this.getMenuElement(24).setCurrent(CFG.editorTerrain_Data2.getBaseProvinceValue());
        this.getMenuElement(27).setCurrent((int)(CFG.editorTerrain_Data2.getBaseDevelopmentLevel() * 100.0f));
    }

    @Override
    protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        CFG.drawEditorTitle_Edge_R(oSB, iTranslateX, iTranslateY, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT + CFG.PADDING * 2);
        super.draw(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
    }

    @Override
    protected final void actionElement(int iID) {
        switch (iID) {
            case 0: {
                this.onBackPressed();
                return;
            }
            case 1: {
                CFG.showKeyboard();
                return;
            }
            case 2: {
                if (this.getMenuElement(1).getText().equals("")) {
                    CFG.showKeyboard(1);
                    CFG.toast.setInView(this.sName);
                } else if (this.getMenuElement(3).getText().equals("")) {
                    CFG.showKeyboard(3);
                    CFG.toast.setInView("UI/" + CFG.getRescouresPath() + "terrain/");
                    CFG.toast.setTimeInView(4500);
                } else {
                    CFG.toast.setInView(CFG.langManager.get("Saved"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE);
                    CFG.editorTerrain_Data2.setName(this.getMenuElement(1).getText());
                    CFG.editorTerrain_Data2.setIconName(this.getMenuElement(3).getText());
                    CFG.editorTerrain_Data2.setDefensiveModifier((float)this.getMenuElement(6).getCurrent() / 100.0f);
                    CFG.editorTerrain_Data2.setMilitaryUpkeepModifier((float)this.getMenuElement(9).getCurrent() / 100.0f);
                    CFG.editorTerrain_Data2.setPopulationGrowthModifier((float)this.getMenuElement(12).getCurrent() / 100.0f);
                    CFG.editorTerrain_Data2.setEconomyGrowthModifier((float)this.getMenuElement(15).getCurrent() / 100.0f);
                    CFG.editorTerrain_Data2.setBuildCostModifier((float)this.getMenuElement(18).getCurrent() / 100.0f);
                    CFG.editorTerrain_Data2.setMovementCost((float)this.getMenuElement(21).getCurrent() / 100.0f);
                    CFG.editorTerrain_Data2.setBaseProvinceValue(this.getMenuElement(24).getCurrent());
                    CFG.editorTerrain_Data2.setBaseDevelopmentLevel((float)this.getMenuElement(27).getCurrent() / 100.0f);
                    CFG.terrainTypesManager.saveTerrainData();
                    CFG.terrainTypesManager.loadTerrainTypes();
                    this.onBackPressed();
                }
                return;
            }
            case 3: {
                CFG.showKeyboard();
                CFG.toast.setInView("UI/" + CFG.getRescouresPath() + "terrain/");
                CFG.toast.setTimeInView(4500);
                return;
            }
            case 4: {
                if (CFG.menuManager.getColorPicker().getVisible()) {
                    CFG.menuManager.getColorPicker().setVisible(false, null);
                } else {
                    CFG.menuManager.getColorPicker().setActiveRGBColor(CFG.editorTerrain_Data2.getColor().getR(), CFG.editorTerrain_Data2.getColor().getG(), CFG.editorTerrain_Data2.getColor().getB());
                    CFG.menuManager.getColorPicker().setPosX(CFG.PADDING * 3);
                    CFG.menuManager.getColorPicker().setPosY(CFG.BUTTON_HEIGHT * 3 + CFG.PADDING * 7);
                    CFG.menuManager.getColorPicker().setVisible(true, ColorPicker_AoC.PickerAction.EDITOR_TERRAIN_COLOR);
                }
                return;
            }
            case 5: {
                this.getMenuElement(iID + 1).setCurrent(this.getMenuElement(iID + 1).getCurrent() - 1);
                return;
            }
            case 6: {
                CFG.toast.setInView(this.getMenuElement(iID).getText() + (this.getMenuElement(iID).getCurrent() > 0 ? "+" : "") + this.getMenuElement(iID).getCurrent() + "%");
                return;
            }
            case 7: {
                this.getMenuElement(iID - 1).setCurrent(this.getMenuElement(iID - 1).getCurrent() + 1);
                return;
            }
            case 8: {
                this.getMenuElement(iID + 1).setCurrent(this.getMenuElement(iID + 1).getCurrent() - 1);
                return;
            }
            case 9: {
                CFG.toast.setInView(this.getMenuElement(iID).getText() + (this.getMenuElement(iID).getCurrent() > 0 ? "+" : "") + this.getMenuElement(iID).getCurrent() + "%");
                return;
            }
            case 10: {
                this.getMenuElement(iID - 1).setCurrent(this.getMenuElement(iID - 1).getCurrent() + 1);
                return;
            }
            case 11: {
                this.getMenuElement(iID + 1).setCurrent(this.getMenuElement(iID + 1).getCurrent() - 1);
                return;
            }
            case 12: {
                CFG.toast.setInView(this.getMenuElement(iID).getText() + (this.getMenuElement(iID).getCurrent() > 0 ? "+" : "") + this.getMenuElement(iID).getCurrent() + "%");
                return;
            }
            case 13: {
                this.getMenuElement(iID - 1).setCurrent(this.getMenuElement(iID - 1).getCurrent() + 1);
                return;
            }
            case 14: {
                this.getMenuElement(iID + 1).setCurrent(this.getMenuElement(iID + 1).getCurrent() - 1);
                return;
            }
            case 15: {
                CFG.toast.setInView(this.getMenuElement(iID).getText() + (this.getMenuElement(iID).getCurrent() > 0 ? "+" : "") + this.getMenuElement(iID).getCurrent() + "%");
                return;
            }
            case 16: {
                this.getMenuElement(iID - 1).setCurrent(this.getMenuElement(iID - 1).getCurrent() + 1);
                return;
            }
            case 17: {
                this.getMenuElement(iID + 1).setCurrent(this.getMenuElement(iID + 1).getCurrent() - 1);
                return;
            }
            case 18: {
                CFG.toast.setInView(this.getMenuElement(iID).getText() + (this.getMenuElement(iID).getCurrent() > 0 ? "+" : "") + this.getMenuElement(iID).getCurrent() + "%");
                return;
            }
            case 19: {
                this.getMenuElement(iID - 1).setCurrent(this.getMenuElement(iID - 1).getCurrent() + 1);
                return;
            }
            case 20: {
                this.getMenuElement(iID + 1).setCurrent(this.getMenuElement(iID + 1).getCurrent() - 1);
                return;
            }
            case 21: {
                CFG.toast.setInView(this.getMenuElement(iID).getText() + (this.getMenuElement(iID).getCurrent() > 0 ? "+" : "") + this.getMenuElement(iID).getCurrent() + "%");
                return;
            }
            case 22: {
                this.getMenuElement(iID - 1).setCurrent(this.getMenuElement(iID - 1).getCurrent() + 1);
                return;
            }
            case 23: {
                this.getMenuElement(iID + 1).setCurrent(this.getMenuElement(iID + 1).getCurrent() - 1);
                return;
            }
            case 24: {
                CFG.toast.setInView(this.getMenuElement(iID).getText() + (this.getMenuElement(iID).getCurrent() > 0 ? "+" : "") + this.getMenuElement(iID).getCurrent());
                return;
            }
            case 25: {
                this.getMenuElement(iID - 1).setCurrent(this.getMenuElement(iID - 1).getCurrent() + 1);
                return;
            }
            case 26: {
                this.getMenuElement(iID + 1).setCurrent(this.getMenuElement(iID + 1).getCurrent() - 1);
                return;
            }
            case 27: {
                CFG.toast.setInView(this.getMenuElement(iID).getText() + (this.getMenuElement(iID).getCurrent() > 0 ? "+" : "") + this.getMenuElement(iID).getCurrent() + "%");
                return;
            }
            case 28: {
                this.getMenuElement(iID - 1).setCurrent(this.getMenuElement(iID - 1).getCurrent() + 1);
                return;
            }
        }
    }

    @Override
    protected void onBackPressed() {
        CFG.menuManager.getColorPicker().setVisible(false, null);
        CFG.menuManager.setViewID(Menu.eTERRAIN_TYPES_EDITOR);
        CFG.menuManager.setBackAnimation(true);
        Game_Render_Province.updateDrawProvinces();
    }
}

