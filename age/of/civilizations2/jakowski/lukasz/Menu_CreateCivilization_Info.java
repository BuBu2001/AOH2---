/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button_Menu;
import age.of.civilizations2.jakowski.lukasz.Button_Menu_Classic;
import age.of.civilizations2.jakowski.lukasz.Button_Menu_ReflectedBG;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Color_GameData;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.SliderMenu;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

class Menu_CreateCivilization_Info
extends SliderMenu {
    private String sName;
    private int iSRID = 0;

    protected Menu_CreateCivilization_Info() {
        int i;
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        menuElements.add(new Button_Menu("", (int)(50.0f * CFG.GUI_SCALE), 0, CFG.PADDING, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT, true){

            @Override
            protected String getTextToDraw() {
                return Menu_CreateCivilization_Info.this.sName + ": " + super.getTextToDraw();
            }
        });
        menuElements.add(new Button_Menu(null, (int)(50.0f * CFG.GUI_SCALE), 0, CFG.BUTTON_HEIGHT + CFG.PADDING * 2, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT, true));
        menuElements.add(new Button_Menu(null, (int)(50.0f * CFG.GUI_SCALE), 0, CFG.BUTTON_HEIGHT * 2 + CFG.PADDING * 3, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT, true));
        menuElements.add(new Button_Menu("<<", -1, 0, CFG.BUTTON_HEIGHT * 3 + CFG.PADDING * 4, CFG.BUTTON_WIDTH * 2, CFG.BUTTON_HEIGHT, true));
        menuElements.add(new Button_Menu_Classic("", -1, CFG.BUTTON_WIDTH * 2, CFG.BUTTON_HEIGHT * 3 + CFG.PADDING * 4, CFG.GAME_WIDTH - CFG.BUTTON_WIDTH * 4, CFG.BUTTON_HEIGHT, true));
        menuElements.add(new Button_Menu_ReflectedBG(">>", -1, CFG.GAME_WIDTH - CFG.BUTTON_WIDTH * 2, CFG.BUTTON_HEIGHT * 3 + CFG.PADDING * 4, CFG.BUTTON_WIDTH * 2, CFG.BUTTON_HEIGHT, true));
        this.iSRID = CFG.serviceRibbon_Manager.getSRID(CFG.editorCivilization_GameData.sr_GameData.getSRTAG());
        int tempSRColorsSize = CFG.serviceRibbon_Manager.getSR(CFG.editorCivilization_GameData.sr_GameData.getSRTAG()).getSize();
        for (i = 0; i < tempSRColorsSize; ++i) {
            menuElements.add(new Button_Menu(CFG.langManager.get("ServiceRibbon") + " - " + CFG.langManager.get("Color") + ": " + (i + 1), -1, 0, CFG.BUTTON_HEIGHT * (4 + i) + CFG.PADDING * (5 + i), CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT, true){
                int iCurrent;

                @Override
                protected void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                    super.drawText(oSB, iTranslateX, iTranslateY, isActive);
                    oSB.setColor(CFG.editorCivilization_GameData.sr_GameData.getColor(this.iCurrent).getR(), CFG.editorCivilization_GameData.sr_GameData.getColor(this.iCurrent).getG(), CFG.editorCivilization_GameData.sr_GameData.getColor(this.iCurrent).getB(), 1.0f);
                    ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getWidth() / 2 - this.getTextWidth() / 2 + iTranslateX, this.getPosY() + Menu_CreateCivilization_Info.this.getMenuPosY() + this.getHeight() / 2 + this.getTextHeight() / 2 + CFG.CIV_COLOR_WIDTH, this.getTextWidth(), CFG.CIV_COLOR_WIDTH);
                    oSB.setColor(Color.WHITE);
                }

                @Override
                protected void setCurrent(int nCurrent) {
                    this.iCurrent = nCurrent;
                }
            });
            ((MenuElement)menuElements.get(menuElements.size() - 1)).setCurrent(i);
        }
        for (i = CFG.editorCivilization_GameData.sr_GameData.getColors().size(); i < tempSRColorsSize; ++i) {
            if (i == 0) {
                CFG.editorCivilization_GameData.sr_GameData.getColors().add(new Color_GameData(0.9843137f, 0.015686275f, 0.0f));
                continue;
            }
            if (i == 1) {
                CFG.editorCivilization_GameData.sr_GameData.getColors().add(new Color_GameData(1.0f, 1.0f, 1.0f));
                continue;
            }
            if (i == 2) {
                CFG.editorCivilization_GameData.sr_GameData.getColors().add(new Color_GameData(0.15294118f, 0.3019608f, 0.60784316f));
                continue;
            }
            if (i == 3) {
                CFG.editorCivilization_GameData.sr_GameData.getColors().add(new Color_GameData(0.08627451f, 0.14901961f, 0.4509804f));
                continue;
            }
            Color tempColor = CFG.getRandomColor();
            CFG.editorCivilization_GameData.sr_GameData.getColors().add(new Color_GameData(tempColor.r, tempColor.g, tempColor.b));
        }
        this.initMenu(null, CFG.PADDING, CFG.BUTTON_HEIGHT + CFG.PADDING * 2, CFG.GAME_WIDTH, CFG.GAME_HEIGHT - (CFG.BUTTON_HEIGHT + CFG.PADDING * 2), menuElements);
        this.updateLanguage();
    }

    @Override
    protected void updateLanguage() {
        this.sName = CFG.langManager.get("CivilizationName");
        this.getMenuElement(1).setText(CFG.langManager.get("Flag"));
        this.getMenuElement(2).setText(CFG.langManager.get("CivilizationColor"));
    }

    @Override
    protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        super.beginClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        super.drawMenu(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        ArrayList<Color> tempColors = new ArrayList<Color>();
        for (int i = 0; i < CFG.editorCivilization_GameData.sr_GameData.getColors().size(); ++i) {
            tempColors.add(new Color(CFG.editorCivilization_GameData.sr_GameData.getColors().get(i).getR(), CFG.editorCivilization_GameData.sr_GameData.getColors().get(i).getG(), CFG.editorCivilization_GameData.sr_GameData.getColors().get(i).getB(), 1.0f));
        }
        int tempWidth = CFG.SERVICE_RIBBON_WIDTH * 6 + CFG.PADDING * 5;
        for (int j = 0; j < 6; ++j) {
            CFG.serviceRibbon_Manager.drawSRLevel(oSB, CFG.GAME_WIDTH / 2 - tempWidth / 2 + (CFG.SERVICE_RIBBON_WIDTH + CFG.PADDING) * j + iTranslateX, this.getMenuElement(4).getPosY() + this.getMenuElement(4).getHeight() / 2 - CFG.SERVICE_RIBBON_HEIGHT / 2 + this.getMenuPosY(), j, 0, 0, this.iSRID, tempColors);
        }
        super.endClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
    }

    @Override
    protected final void actionElement(int iID) {
        switch (iID) {
            case 0: {
                CFG.showKeyboard();
                return;
            }
        }
    }
}

