/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button_TodayPartOf;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.SliderMenu;
import age.of.civilizations2.jakowski.lukasz.SliderMenuTitle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

class Menu_InGame_FormableCiv_Provinces_TodayPartOf
extends SliderMenu {
    protected Menu_InGame_FormableCiv_Provinces_TodayPartOf() {
        boolean tAdd;
        int i;
        int iPartOfSize;
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        int tMenuWidth = CFG.CIV_INFO_MENU_WIDTH * 3 / 4;
        int tElementH = Math.max(CFG.TEXT_HEIGHT + CFG.PADDING * 4, CFG.CIV_FLAG_HEIGHT + CFG.PADDING * 2);
        int tPosY = 0;
        ArrayList<Integer> tempPartOf = new ArrayList<Integer>();
        ArrayList<Integer> tempProvinces = new ArrayList<Integer>();
        int nFormableCivMapProvinces = 0;
        if (CFG.FOG_OF_WAR == 2) {
            iPartOfSize = 0;
            for (i = 0; i < CFG.formableCivs_GameData.getProvincesSize(); ++i) {
                if (CFG.game.getProvince(CFG.formableCivs_GameData.getProvinceID(i)).getSeaProvince() || CFG.game.getProvince(CFG.formableCivs_GameData.getProvinceID(i)).getWasteland() >= 0 || CFG.game.getProvince(CFG.formableCivs_GameData.getProvinceID(i)).getCivID() <= 0) continue;
                tAdd = true;
                int tempCivID = CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetProvince(CFG.formableCivs_GameData.getProvinceID(i)) ? CFG.game.getProvince(CFG.formableCivs_GameData.getProvinceID(i)).getCivID() : -1;
                for (int j = 0; j < iPartOfSize; ++j) {
                    if ((Integer)tempPartOf.get(j) != tempCivID) continue;
                    tAdd = false;
                    tempProvinces.set(j, (Integer)tempProvinces.get(j) + 1);
                    break;
                }
                if (tAdd) {
                    tempPartOf.add(tempCivID);
                    tempProvinces.add(1);
                    ++iPartOfSize;
                }
                ++nFormableCivMapProvinces;
            }
        } else {
            iPartOfSize = 0;
            for (i = 0; i < CFG.formableCivs_GameData.getProvincesSize(); ++i) {
                if (CFG.game.getProvince(CFG.formableCivs_GameData.getProvinceID(i)).getSeaProvince() || CFG.game.getProvince(CFG.formableCivs_GameData.getProvinceID(i)).getWasteland() >= 0 || CFG.game.getProvince(CFG.formableCivs_GameData.getProvinceID(i)).getCivID() <= 0) continue;
                tAdd = true;
                for (int j = 0; j < iPartOfSize; ++j) {
                    if (((Integer)tempPartOf.get(j)).intValue() != CFG.game.getProvince(CFG.formableCivs_GameData.getProvinceID(i)).getCivID()) continue;
                    tAdd = false;
                    tempProvinces.set(j, (Integer)tempProvinces.get(j) + 1);
                    break;
                }
                if (tAdd) {
                    tempPartOf.add(CFG.game.getProvince(CFG.formableCivs_GameData.getProvinceID(i)).getCivID());
                    tempProvinces.add(1);
                    ++iPartOfSize;
                }
                ++nFormableCivMapProvinces;
            }
        }
        int iSize = tempPartOf.size();
        for (i = 0; i < iSize; ++i) {
            for (int j = i + 1; j < iSize; ++j) {
                if ((Integer)tempProvinces.get(i) >= (Integer)tempProvinces.get(j)) continue;
                int tRev = (Integer)tempPartOf.get(i);
                tempPartOf.set(i, (Integer)tempPartOf.get(j));
                tempPartOf.set(j, tRev);
                tRev = (Integer)tempProvinces.get(i);
                tempProvinces.set(i, (Integer)tempProvinces.get(j));
                tempProvinces.set(j, tRev);
            }
        }
        for (i = 0; i < tempPartOf.size(); ++i) {
            menuElements.add(new Button_TodayPartOf((Integer)tempPartOf.get(i), CFG.getPercentage((Integer)tempProvinces.get(i), nFormableCivMapProvinces, 4), 0, tPosY, tMenuWidth, tElementH, true));
            tPosY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight();
        }
        this.initMenu(new SliderMenuTitle(null, CFG.BUTTON_HEIGHT / 2, false, false){

            @Override
            protected void draw(SpriteBatch oSB, int iTranslateX, int nPosX, int nPosY, int nWidth, boolean sliderMenuIsActive) {
                oSB.setColor(CFG.COLOR_GRADIENT_DARK_BLUE);
                ImageManager.getImage(Images.slider_gradient).draw(oSB, nPosX + iTranslateX, nPosY - this.getHeight() - ImageManager.getImage(Images.slider_gradient).getHeight(), nWidth, this.getHeight(), true, false);
                oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.35f));
                ImageManager.getImage(Images.slider_gradient).draw(oSB, nPosX + nWidth - nWidth / 2 + iTranslateX, nPosY - this.getHeight() - ImageManager.getImage(Images.slider_gradient).getHeight(), nWidth / 2, this.getHeight(), true, false);
                oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.65f));
                ImageManager.getImage(Images.slider_gradient).draw(oSB, nPosX + iTranslateX, nPosY + 2 - this.getHeight() - ImageManager.getImage(Images.slider_gradient).getHeight(), nWidth, 1, true, false);
                ImageManager.getImage(Images.slider_gradient).draw(oSB, nPosX + iTranslateX, nPosY - 1 - ImageManager.getImage(Images.slider_gradient).getHeight(), nWidth, 1, true, false);
                oSB.setColor(Color.WHITE);
                CFG.fontMain.getData().setScale(0.8f);
                CFG.drawText(oSB, this.getText(), nPosX + nWidth / 2 - (int)((float)this.getTextWidth() * 0.8f) / 2 + iTranslateX, 2 + nPosY - this.getHeight() + (int)((float)this.getHeight() - (float)this.getTextHeight() * 0.8f) / 2, Color.WHITE);
                CFG.fontMain.getData().setScale(1.0f);
            }
        }, CFG.GAME_WIDTH - tMenuWidth, Math.max(CFG.BUTTON_HEIGHT * 4 / 5, Math.max(CFG.CIV_FLAG_HEIGHT + CFG.PADDING * 4, (CFG.TEXT_HEIGHT + CFG.PADDING) * 2 + CFG.PADDING)) + CFG.PADDING * 2 + CFG.BUTTON_HEIGHT / 2, tMenuWidth, Math.min(CFG.GAME_HEIGHT - (Math.max(CFG.BUTTON_HEIGHT * 4 / 5, Math.max(CFG.CIV_FLAG_HEIGHT + CFG.PADDING * 4, (CFG.TEXT_HEIGHT + CFG.PADDING) * 2 + CFG.PADDING)) + CFG.PADDING * 2 + CFG.BUTTON_HEIGHT / 2), ((MenuElement)menuElements.get(menuElements.size() - 1)).getPosY() + ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING), menuElements, true, false);
        for (i = 0; i < this.getMenuElementsSize(); ++i) {
            this.getMenuElement(i).setCurrent(i % 2);
        }
        this.updateLanguage();
    }

    @Override
    protected void updateLanguage() {
        this.getTitle().setText(CFG.langManager.get("TodayPartOf"));
    }

    @Override
    protected void drawScrollPos(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        if (sliderMenuIsActive) {
            super.drawScrollPos(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        }
    }

    @Override
    protected void actionElement(int iID) {
        try {
            CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID = CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID == this.getMenuElement(iID).getCurrent() ? CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID() : this.getMenuElement(iID).getCurrent();
            for (int i = 0; i < CFG.game.getCiv(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID).getNumOfProvinces(); ++i) {
                CFG.game.getProvince(CFG.game.getCiv(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID).getProvinceID(i)).setFromCivID(0);
            }
            CFG.toast.setInView(CFG.game.getCiv(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID).getCivName());
        }
        catch (IndexOutOfBoundsException ex) {
            CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID = CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID();
            CFG.toast.setInView(CFG.langManager.get("Undiscovered"), CFG.COLOR_TEXT_NUM_OF_PROVINCES);
        }
    }
}

