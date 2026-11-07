/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button_New_Game_Players;
import age.of.civilizations2.jakowski.lukasz.Button_New_Game_Players_CivID;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Civilization_GameData3;
import age.of.civilizations2.jakowski.lukasz.Game_Render_Province;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.Menu;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.SliderMenu;
import age.of.civilizations2.jakowski.lukasz.SliderMenuTitle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;
import java.util.List;

class Menu_InGame_CreateAVassal_Civ
extends SliderMenu {
    private List<Integer> lCivs = new ArrayList<Integer>();

    protected Menu_InGame_CreateAVassal_Civ() {
        int i;
        int tempW = CFG.CIV_INFO_MENU_WIDTH;
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        menuElements.add(new Button_New_Game_Players(CFG.langManager.get("CreateaCivilization"), -1, CFG.PADDING, CFG.PADDING, tempW - CFG.PADDING * 2, true));
        int tY = ((MenuElement)menuElements.get(menuElements.size() - 1)).getPosY() + ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
        menuElements.add(new Button_New_Game_Players(CFG.langManager.get("SelectCivilization"), -1, CFG.PADDING, tY, tempW - CFG.PADDING * 2, true){

            @Override
            protected boolean getClickable() {
                return true;
            }
        });
        tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
        ArrayList<Integer> lForeignCores = new ArrayList<Integer>();
        for (i = 0; i < CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getNumOfProvinces(); ++i) {
            if (CFG.game.getProvince(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getProvinceID(i)).isOccupied()) continue;
            for (int j = 0; j < CFG.game.getProvince(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getProvinceID(i)).getCore().getCivsSize(); ++j) {
                if (CFG.game.getProvince(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getProvinceID(i)).getCore().getCivID(j) == CFG.game.getProvince(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getProvinceID(i)).getCivID() || CFG.game.getCiv(CFG.game.getProvince(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getProvinceID(i)).getCore().getCivID(j)).getNumOfProvinces() != 0) continue;
                boolean tAdd = true;
                for (int k = 0; k < lForeignCores.size(); ++k) {
                    if (((Integer)lForeignCores.get(k)).intValue() != CFG.game.getProvince(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getProvinceID(i)).getCore().getCivID(j)) continue;
                    tAdd = false;
                    break;
                }
                if (!tAdd) continue;
                lForeignCores.add(CFG.game.getProvince(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getProvinceID(i)).getCore().getCivID(j));
            }
        }
        for (i = 0; i < lForeignCores.size(); ++i) {
            menuElements.add(new Button_New_Game_Players_CivID((Integer)lForeignCores.get(i), CFG.game.getCiv((Integer)lForeignCores.get(i)).getCivName(), -1, CFG.PADDING, tY, tempW - CFG.PADDING * 2, !CFG.game.getCivsAtWar(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), (Integer)lForeignCores.get(i))));
            tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
        }
        this.initMenu(new SliderMenuTitle(null, CFG.BUTTON_HEIGHT * 3 / 4, false, false){

            @Override
            protected void draw(SpriteBatch oSB, int iTranslateX, int nPosX, int nPosY, int nWidth, boolean sliderMenuIsActive) {
                ImageManager.getImage(Images.dialog_title).draw2(oSB, nPosX - 2 + iTranslateX, nPosY - this.getHeight() - ImageManager.getImage(Images.dialog_title).getHeight(), nWidth + 2, this.getHeight());
                oSB.setColor(new Color(0.23529412f, 0.3137255f, 0.4117647f, 0.165f));
                ImageManager.getImage(Images.line_32_off1).draw(oSB, nPosX + iTranslateX, nPosY - this.getHeight() + 2 - ImageManager.getImage(Images.line_32_off1).getHeight(), nWidth, this.getHeight() - 2, false, true);
                oSB.setColor(new Color(0.23529412f, 0.3137255f, 0.4117647f, 0.375f));
                ImageManager.getImage(Images.gradient).draw(oSB, nPosX + iTranslateX, nPosY - this.getHeight() * 2 / 3 - ImageManager.getImage(Images.gradient).getHeight(), nWidth, this.getHeight() * 2 / 3, false, true);
                oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.65f));
                ImageManager.getImage(Images.gradient).draw(oSB, nPosX + iTranslateX, nPosY - CFG.PADDING - ImageManager.getImage(Images.gradient).getHeight(), nWidth, CFG.PADDING, false, true);
                oSB.setColor(CFG.COLOR_NEW_GAME_EDGE_LINE);
                ImageManager.getImage(Images.pix255_255_255).draw(oSB, nPosX + iTranslateX, nPosY - 1 - ImageManager.getImage(Images.pix255_255_255).getHeight(), nWidth, 1);
                oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.55f));
                ImageManager.getImage(Images.line_32_off1).draw(oSB, nPosX + iTranslateX, nPosY - 2 - ImageManager.getImage(Images.line_32_off1).getHeight(), nWidth, 1);
                oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.8f));
                ImageManager.getImage(Images.line_32_off1).draw(oSB, nPosX + iTranslateX, nPosY - 1 - ImageManager.getImage(Images.line_32_off1).getHeight(), nWidth, 1);
                oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.45f));
                ImageManager.getImage(Images.slider_gradient).draw(oSB, nPosX + iTranslateX, nPosY - 1 - ImageManager.getImage(Images.slider_gradient).getHeight(), nWidth / 2, 1);
                ImageManager.getImage(Images.slider_gradient).draw(oSB, nPosX + nWidth - nWidth / 2 + iTranslateX, nPosY - 1 - ImageManager.getImage(Images.slider_gradient).getHeight(), nWidth / 2, 1, true, false);
                oSB.setColor(Color.WHITE);
                CFG.fontMain.getData().setScale(0.8f);
                CFG.drawText(oSB, this.getText(), nPosX + (int)((float)nWidth - (float)this.getTextWidth() * 0.8f) / 2 + iTranslateX, 2 + nPosY - this.getHeight() + (int)((float)this.getHeight() - (float)this.getTextHeight() * 0.8f) / 2, Color.WHITE);
                CFG.fontMain.getData().setScale(1.0f);
            }
        }, CFG.GAME_WIDTH - tempW, CFG.BUTTON_HEIGHT + CFG.PADDING * 3 + CFG.BUTTON_HEIGHT * 3 / 4, tempW, Math.min(menuElements.size() > 0 ? ((MenuElement)menuElements.get(menuElements.size() - 1)).getPosY() + ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING : CFG.PADDING, CFG.GAME_HEIGHT - (CFG.BUTTON_HEIGHT * 2 + CFG.PADDING * 4 + CFG.PADDING * 2 + CFG.BUTTON_HEIGHT / 2) - CFG.BUTTON_HEIGHT - CFG.PADDING * 2), menuElements, true, true);
        this.updateLanguage();
    }

    @Override
    protected void updateLanguage() {
        this.getTitle().setText(CFG.langManager.get("SelectVassal"));
    }

    @Override
    protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        ImageManager.getImage(Images.new_game_top_edge_line).draw2(oSB, this.getPosX() - 2 + iTranslateX, this.getPosY() - ImageManager.getImage(Images.new_game_top_edge_line).getHeight() + iTranslateY, this.getWidth() + 4, this.getHeight(), false, true);
        super.draw(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        oSB.setColor(CFG.COLOR_CREATE_NEW_GAME_BOX_PLAYERS);
        ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() - 2 + iTranslateX, this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + this.getHeight(), this.getWidth() + 2);
        oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.4f));
        ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX() - 2 + iTranslateX, this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() - ImageManager.getImage(Images.line_32_off1).getHeight() + this.getHeight(), this.getWidth() + 2, 1);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.5f));
        ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() - 2 + iTranslateX, this.getPosY() + this.getHeight(), this.getWidth() + 2);
        oSB.setColor(Color.WHITE);
    }

    @Override
    protected void actionElement(int iID) {
        if (iID == 0) {
            CFG.brushTool = false;
            CFG.menuManager.getColorPicker().setPosX(CFG.CIV_INFO_MENU_WIDTH + CFG.CIV_INFO_MENU_WIDTH * 3 / 4 + CFG.PADDING * 4);
            CFG.flagManager.loadData();
            CFG.flagManager.initFlagEdit();
            CFG.EDITOR_ACTIVE_GAMEDATA_TAG = "" + System.currentTimeMillis() + CFG.extraRandomTag();
            CFG.editorCivilization_GameData = new Civilization_GameData3();
            CFG.backToMenu = Menu.eINGAME_CREATE_VASSAL;
            CFG.menuManager.setViewID(Menu.eCREATE_CIVILIZATION);
            Game_Render_Province.updateDrawProvinces();
            CFG.map.getMapBG().updateWorldMap_Shaders();
        } else if (iID == 1) {
            CFG.menuManager.setViewID(Menu.eINGAME_CREATE_VASSAL_SELECT_CIV);
            CFG.map.getMapBG().updateWorldMap_Shaders();
        } else {
            int i;
            CFG.game.getSelectedProvinces().clearSelectedProvinces();
            CFG.createVassal_Data.setCivTag(CFG.game.getCiv(this.getMenuElement(iID).getCurrent()).getCivTag());
            CFG.createVassal_Data.iCapitalProvinceID = -1;
            block0: for (i = 0; i < CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getNumOfProvinces(); ++i) {
                for (int j = 0; j < CFG.game.getProvince(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getProvinceID(i)).getCore().getCivsSize(); ++j) {
                    if (CFG.game.getProvince(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getProvinceID(i)).getCore().getCivID(j) != this.getMenuElement(iID).getCurrent() || CFG.game.getProvince(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getProvinceID(i)).getTrueOwnerOfProvince() != CFG.game.getProvince(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getProvinceID(i)).getCivID()) continue;
                    if (CFG.game.getProvince(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getProvinceID(i)).getIsCapital()) continue block0;
                    CFG.game.getSelectedProvinces().addProvince(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getProvinceID(i));
                    continue block0;
                }
            }
            if (!CFG.createVassal_Data.sCivTag.equals(CFG.ideologiesManager.getRealTag(CFG.createVassal_Data.sCivTag) + CFG.ideologiesManager.getIdeology(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getIdeologyID()).getExtraTag()) && CFG.game.isCivTagAvailable(CFG.ideologiesManager.getRealTag(CFG.createVassal_Data.sCivTag) + CFG.ideologiesManager.getIdeology(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getIdeologyID()).getExtraTag())) {
                CFG.createVassal_Data.setCivTag(CFG.ideologiesManager.getRealTag(CFG.createVassal_Data.sCivTag) + CFG.ideologiesManager.getIdeology(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getIdeologyID()).getExtraTag());
            }
            if (CFG.game.getSelectedProvinces().getProvincesSize() > 0) {
                CFG.createVassal_Data.iCapitalProvinceID = CFG.game.getSelectedProvinces().getProvince(0);
                for (i = 1; i < CFG.game.getSelectedProvinces().getProvincesSize(); ++i) {
                    if (CFG.game.getProvince(CFG.createVassal_Data.iCapitalProvinceID).getPopulationData().getPopulation() >= CFG.game.getProvince(CFG.game.getSelectedProvinces().getProvince(i)).getPopulationData().getPopulation()) continue;
                    CFG.createVassal_Data.iCapitalProvinceID = CFG.game.getSelectedProvinces().getProvince(i);
                }
            }
            CFG.menuManager.setViewID(Menu.eINGAME_CREATE_VASSAL);
        }
    }
}

