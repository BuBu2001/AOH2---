/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button_Menu_LR;
import age.of.civilizations2.jakowski.lukasz.Button_Menu_LR_Line;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Menu;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.ServiceRibbon_GameData;
import age.of.civilizations2.jakowski.lukasz.ServiceRibbon_Overlay_GameData;
import age.of.civilizations2.jakowski.lukasz.SliderMenu;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.io.IOException;
import java.util.ArrayList;

class Menu_ServiceRibbon_Editor
extends SliderMenu {
    protected Menu_ServiceRibbon_Editor() {
        int i;
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        menuElements.add(new Button_Menu_LR_Line(null, -1, 0, CFG.PADDING, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT, true));
        for (int i2 = 0; i2 < CFG.serviceRibbon_Manager.getSRSize(); ++i2) {
            menuElements.add(new Button_Menu_LR("ID: " + CFG.serviceRibbon_Manager.getTag(i2), (int)(50.0f * CFG.GUI_SCALE), 0, CFG.BUTTON_HEIGHT * (i2 + 1) + CFG.PADDING * (i2 + 2), CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT, true){

                @Override
                protected void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                    CFG.fontMain.getData().setScale(0.8f);
                    if (isActive) {
                        CFG.drawText(oSB, this.getTextToDraw(), this.getPosX() + this.textPosition.getTextPosition() + iTranslateX, this.getPosY() + (int)(((float)this.getHeight() - (float)this.iTextHeight * 0.8f) / 2.0f) + iTranslateY, this.getColor(isActive));
                    } else {
                        CFG.drawTextWithShadow(oSB, this.getTextToDraw(), this.getPosX() + this.textPosition.getTextPosition() + iTranslateX, this.getPosY() + (int)(((float)this.getHeight() - (float)this.iTextHeight * 0.8f) / 2.0f) + iTranslateY, this.getColor(isActive));
                    }
                    CFG.fontMain.getData().setScale(1.0f);
                }
            });
        }
        this.initMenu(null, 0, CFG.BUTTON_HEIGHT * 3 / 4, CFG.GAME_WIDTH, CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT * 3 / 4 - CFG.BUTTON_HEIGHT - CFG.PADDING, menuElements);
        this.updateLanguage();
        CFG.editorServiceRibbon_Colors = new ArrayList<Color>();
        int tempMax = 1;
        for (i = 0; i < CFG.serviceRibbon_Manager.getSRSize(); ++i) {
            if (tempMax >= CFG.serviceRibbon_Manager.getSR(i).getSize()) continue;
            tempMax = CFG.serviceRibbon_Manager.getSR(i).getSize();
        }
        for (i = 0; i < tempMax; ++i) {
            CFG.editorServiceRibbon_Colors_Add();
        }
    }

    @Override
    protected void updateLanguage() {
        this.getMenuElement(0).setText(CFG.langManager.get("AddNewServiceRibbon"));
    }

    @Override
    protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        super.beginClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        super.drawMenu(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        int tempWidth = CFG.SERVICE_RIBBON_WIDTH * 6 + CFG.PADDING * 5;
        for (int i = 1; i < CFG.serviceRibbon_Manager.getSRSize() + 1; ++i) {
            for (int j = 0; j < 6; ++j) {
                CFG.serviceRibbon_Manager.drawSRLevel(oSB, CFG.GAME_WIDTH / 2 - tempWidth / 2 + (CFG.SERVICE_RIBBON_WIDTH + CFG.PADDING) * j + iTranslateX, this.getMenuElement(i).getPosY() + this.getMenuElement(i).getHeight() / 2 - CFG.SERVICE_RIBBON_HEIGHT / 2 + this.getMenuPosY(), j, 0, 0, i - 1, CFG.editorServiceRibbon_Colors);
            }
        }
        super.endClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
    }

    @Override
    protected final void actionElement(int iID) {
        switch (iID) {
            case 0: {
                CFG.EDITOR_ACTIVE_GAMEDATA_TAG = System.currentTimeMillis() + CFG.extraRandomTag();
                CFG.editorServiceRibbon_GameData = new ServiceRibbon_GameData();
                CFG.editorServiceRibbon_GameData.addServiceRibbonOverlay(new ServiceRibbon_Overlay_GameData(0, CFG.SERVICE_RIBBON_WIDTH, false));
                CFG.menuManager.setViewID(Menu.eGAME_EDITOR_SERVICE_RIBBON_EDIT);
                break;
            }
            default: {
                CFG.EDITOR_ACTIVE_GAMEDATA_TAG = CFG.serviceRibbon_Manager.getTag(iID - 1);
                FileHandle fileData = Gdx.files.internal("game/service_ribbons/" + CFG.EDITOR_ACTIVE_GAMEDATA_TAG);
                try {
                    CFG.editorServiceRibbon_GameData = (ServiceRibbon_GameData)CFG.deserialize(fileData.readBytes());
                }
                catch (ClassNotFoundException classNotFoundException) {
                }
                catch (IOException iOException) {
                    // empty catch block
                }
                CFG.toast.setInView(CFG.EDITOR_ACTIVE_GAMEDATA_TAG);
                CFG.menuManager.setViewID(Menu.eGAME_EDITOR_SERVICE_RIBBON_EDIT);
            }
        }
    }
}

