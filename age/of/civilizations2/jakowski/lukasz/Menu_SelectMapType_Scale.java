/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button_Menu;
import age.of.civilizations2.jakowski.lukasz.Button_Menu_LR_Line;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.Menu;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.Menu_Games_Title;
import age.of.civilizations2.jakowski.lukasz.SliderMenu;
import age.of.civilizations2.jakowski.lukasz.Text;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

class Menu_SelectMapType_Scale
extends SliderMenu {
    protected static int MAP_ID_TO_LOAD = 0;

    protected Menu_SelectMapType_Scale() {
        int i;
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        int tempMenuWidth = Menu_Games_Title.getMenuWidth();
        int tY = 0;
        menuElements.add(new Button_Menu_LR_Line(null, -1, 0, 0, tempMenuWidth, CFG.BUTTON_HEIGHT, true));
        menuElements.add(new Text(null, -1, 0, tY, tempMenuWidth, CFG.BUTTON_HEIGHT * 3 / 4){

            @Override
            protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
                CFG.drawRect_InfoBox_Right_Title(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight());
                CFG.fontMain.getData().setScale(0.8f);
                CFG.drawTextWithShadow(oSB, this.getText(), this.getPosX() + this.getWidth() / 2 - (int)((float)this.getTextWidth() * 0.8f) / 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - (int)((float)this.getTextHeight() * 0.8f) / 2 + iTranslateY, CFG.COLOR_TEXT_CIV_INFO_TITLE);
                CFG.fontMain.getData().setScale(1.0f);
            }
        });
        tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
        FileHandle tempFileT = Gdx.files.internal("map/" + CFG.map.getFile_Map_Path(MAP_ID_TO_LOAD) + "data/" + "scales/" + "provinces/" + "Age_of_Civilizations");
        String tempT = tempFileT.readString();
        String[] tagsSPLITED = tempT.split(";");
        ArrayList<Integer> tempScales = new ArrayList<Integer>();
        for (i = 0; i < tagsSPLITED.length; ++i) {
            tempScales.add(Integer.parseInt(tagsSPLITED[i]));
        }
        for (i = 0; i < tempScales.size(); ++i) {
            if (CFG.map.getActiveMapID() == MAP_ID_TO_LOAD) {
                if (CFG.map.getMapScale(CFG.map.getActiveMapID()) == ((Integer)tempScales.get(i)).intValue()) {
                    menuElements.add(new Button_Menu(CFG.langManager.get("Scale") + " x" + tempScales.get(i) + " - [" + CFG.map.getMapBG().getWidth() / CFG.map.getMapScale(CFG.map.getActiveMapID()) * (Integer)tempScales.get(i) + "x" + CFG.map.getMapBG().getHeight() / CFG.map.getMapScale(CFG.map.getActiveMapID()) * (Integer)tempScales.get(i) + "]", (int)(50.0f * CFG.GUI_SCALE), 0, tY, tempMenuWidth, CFG.BUTTON_HEIGHT, true, true));
                } else {
                    menuElements.add(new Button_Menu(CFG.langManager.get("Scale") + " x" + tempScales.get(i) + " - [" + CFG.map.getMapBG().getWidth() / CFG.map.getMapScale(CFG.map.getActiveMapID()) * (Integer)tempScales.get(i) + "x" + CFG.map.getMapBG().getHeight() / CFG.map.getMapScale(CFG.map.getActiveMapID()) * (Integer)tempScales.get(i) + "]", (int)(50.0f * CFG.GUI_SCALE), 0, tY, tempMenuWidth, CFG.BUTTON_HEIGHT, true));
                }
            } else {
                menuElements.add(new Button_Menu(CFG.langManager.get("Scale") + " x" + tempScales.get(i) + " - [" + CFG.langManager.get("NoData").toUpperCase() + "]", (int)(50.0f * CFG.GUI_SCALE), 0, tY, tempMenuWidth, CFG.BUTTON_HEIGHT, true));
            }
            tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
        }
        this.initMenuWithBackButton(null, CFG.GAME_WIDTH - tempMenuWidth, 0, tempMenuWidth, CFG.GAME_HEIGHT, menuElements);
        this.updateLanguage();
    }

    @Override
    protected void updateLanguage() {
        this.getMenuElement(0).setText(CFG.langManager.get("Back"));
        this.getMenuElement(1).setText(CFG.langManager.get("SelectMapScale"));
    }

    @Override
    protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.6f));
        ImageManager.getImage(Images.gradient).draw(oSB, iTranslateX, -ImageManager.getImage(Images.gradient).getHeight() + iTranslateY, CFG.GAME_WIDTH, CFG.PADDING * 3);
        ImageManager.getImage(Images.gradient).draw(oSB, iTranslateX, CFG.GAME_HEIGHT - ImageManager.getImage(Images.gradient).getHeight() - CFG.PADDING * 3 + iTranslateY, CFG.GAME_WIDTH, CFG.PADDING * 3, false, true);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.1f));
        ImageManager.getImage(Images.patt2).draw(oSB, iTranslateX, -ImageManager.getImage(Images.patt2).getHeight(), CFG.GAME_WIDTH, CFG.GAME_HEIGHT, 0.0f, 0);
        oSB.setColor(1.0f, 1.0f, 1.0f, 1.0f);
        ImageManager.getImage(Images.gameLogo).draw(oSB, CFG.PADDING * 2 + iTranslateX, CFG.GAME_HEIGHT - CFG.PADDING * 2 - ImageManager.getImage(Images.gameLogo).getHeight() + iTranslateY);
        oSB.setColor(1.0f, 1.0f, 1.0f, 0.85f);
        ImageManager.getImage(Images.new_game_top_edge_line).draw2(oSB, this.getPosX() - 2 + iTranslateX, -ImageManager.getImage(Images.new_game_top_edge_line).getHeight() + iTranslateY, this.getWidth() + 2, CFG.GAME_HEIGHT);
        oSB.setColor(CFG.COLOR_GRADIENT_TITLE_BLUE.r, CFG.COLOR_GRADIENT_TITLE_BLUE.g, CFG.COLOR_GRADIENT_TITLE_BLUE.b, 0.275f);
        ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX() + iTranslateX, -ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth(), CFG.GAME_HEIGHT);
        oSB.setColor(Color.WHITE);
        super.beginClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        super.drawMenu(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        for (int i = 0; i < this.getMenuElementsSize() - 2; ++i) {
            if (!this.getMenuElement(i + 2).getIsInView()) continue;
            CFG.map.getIcon(CFG.map.getActiveMapID()).draw(oSB, this.getPosX() + this.getMenuElement(i + 2).getTextPos() / 2 - CFG.map.getIcon(CFG.map.getActiveMapID()).getWidth() / 2 + iTranslateX, this.getMenuElement(i + 2).getPosY() + this.getMenuElement(i + 2).getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 + this.getMenuPosY() - CFG.map.getIcon(CFG.map.getActiveMapID()).getHeight() + iTranslateY, CFG.CIV_FLAG_WIDTH, CFG.CIV_FLAG_HEIGHT);
        }
        super.endClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
    }

    @Override
    protected final void actionElement(int iID) {
        switch (iID) {
            case 0: {
                this.onBackPressed();
                return;
            }
            case 1: {
                return;
            }
        }
        FileHandle tempFileT = Gdx.files.internal("map/" + CFG.map.getFile_Map_Path(MAP_ID_TO_LOAD) + "data/" + "scales/" + "provinces/" + "Age_of_Civilizations");
        String tempT = tempFileT.readString();
        String[] tagsSPLITED = tempT.split(";");
        CFG.map.setMapScale(MAP_ID_TO_LOAD, Integer.parseInt(tagsSPLITED[iID - 2]));
        CFG.map.setActiveMapID(MAP_ID_TO_LOAD);
        CFG.goToMenu = Menu.eSELECT_MAP_TYPE;
        CFG.menuManager.setViewIDWithoutAnimation(Menu.eLOAD_MAP);
    }

    @Override
    protected final void onBackPressed() {
        CFG.menuManager.setViewID(Menu.eSELECT_MAP_TYPE);
        CFG.menuManager.setBackAnimation(true);
    }
}

