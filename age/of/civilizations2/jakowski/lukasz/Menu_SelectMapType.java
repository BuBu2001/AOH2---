/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button_Menu_Classic_Wiki;
import age.of.civilizations2.jakowski.lukasz.Button_Menu_Descripted;
import age.of.civilizations2.jakowski.lukasz.Button_Menu_LR_Line;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Dialog;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.Menu;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Image;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Text;
import age.of.civilizations2.jakowski.lukasz.Menu_Games_Title;
import age.of.civilizations2.jakowski.lukasz.Menu_SelectMapType_Scale;
import age.of.civilizations2.jakowski.lukasz.SliderMenu;
import age.of.civilizations2.jakowski.lukasz.Text;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

class Menu_SelectMapType
extends SliderMenu {
    protected Menu_SelectMapType() {
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
        for (int i = 0; i < CFG.map.getNumOfMaps(); ++i) {
            menuElements.add(new Button_Menu_Descripted(CFG.map.getMapAuthor(i), CFG.map.getMapName(i), (int)(50.0f * CFG.GUI_SCALE), 0, tY, tempMenuWidth - CFG.BUTTON_WIDTH, CFG.BUTTON_HEIGHT, true, CFG.map.getActiveMapID() == i));
            ((MenuElement)menuElements.get(menuElements.size() - 1)).setCurrent(i);
            menuElements.add(new Button_Menu_Classic_Wiki(i, tempMenuWidth - CFG.BUTTON_WIDTH, tY, CFG.BUTTON_WIDTH, CFG.BUTTON_HEIGHT, true){

                @Override
                protected void buildElementHover() {
                    if (this.getClickable()) {
                        ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                        ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Wiki") + ": ", CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.map.getMapWiki(this.getCurrent())));
                        nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.wikipedia, CFG.PADDING, 0));
                        nElements.add(new MenuElement_Hover_v2_Element2(nData));
                        nData.clear();
                        this.menuElementHover = new MenuElement_Hover_v2(nElements);
                    } else {
                        this.menuElementHover = null;
                    }
                }
            });
            tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
        }
        this.initMenuWithBackButton(null, CFG.GAME_WIDTH - tempMenuWidth, 0, tempMenuWidth, CFG.GAME_HEIGHT, menuElements);
        this.updateLanguage();
    }

    @Override
    protected void updateLanguage() {
        this.getMenuElement(0).setText(CFG.langManager.get("Back"));
        this.getMenuElement(1).setText(CFG.langManager.get("SelectMapType"));
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
        for (int i = 0; i < CFG.map.getNumOfMaps(); ++i) {
            if (!this.getMenuElement(i * 2 + 2).getIsInView()) continue;
            CFG.map.getIcon(i).draw(oSB, this.getPosX() + this.getMenuElement(i * 2 + 2).getTextPos() / 2 - CFG.map.getIcon(i).getWidth() / 2 + iTranslateX, this.getMenuElement(i * 2 + 2).getPosY() + this.getMenuElement(i * 2 + 2).getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 + this.getMenuPosY() - CFG.map.getIcon(i).getHeight() + iTranslateY, CFG.CIV_FLAG_WIDTH, CFG.CIV_FLAG_HEIGHT);
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
        if (iID % 2 == 0) {
            Menu_SelectMapType_Scale.MAP_ID_TO_LOAD = (iID - 2) / 2;
            CFG.menuManager.setViewID(Menu.eSELECT_MAP_TYPE_SCALE);
        } else if (CFG.map.getMapWiki((iID - 2) / 2).length() > 0) {
            CFG.EDITOR_ACTIVE_GAMEDATA_TAG = CFG.map.getMapWiki((iID - 2) / 2);
            CFG.setDialogType(Dialog.GO_TO_WIKI_SCENARIO);
        } else {
            CFG.toast.setInView(CFG.langManager.get("NoData"), CFG.COLOR_TEXT_MODIFIER_NEGATIVE2);
        }
    }

    @Override
    protected final void onBackPressed() {
        CFG.menuManager.setViewID(CFG.backToMenu);
        CFG.menuManager.setBackAnimation(true);
    }
}

