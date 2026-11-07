/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button_Game_Checkbox;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Game_Render_Province;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Text;
import age.of.civilizations2.jakowski.lukasz.SliderMenu;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;
import java.util.Random;

class Menu_ManageDiplomacy_Options
extends SliderMenu {
    protected Menu_ManageDiplomacy_Options() {
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        menuElements.add(new Button_Game_Checkbox(null, -1, CFG.PADDING, CFG.PADDING, CFG.BUTTON_WIDTH, true, true));
        menuElements.add(new Button_Game_Checkbox(null, -1, CFG.BUTTON_WIDTH + CFG.PADDING * 2, CFG.PADDING, CFG.BUTTON_WIDTH, true, false){

            @Override
            protected void buildElementHover() {
                ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("CustomizeRelations"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
        });
        menuElements.add(new Button_Game_Checkbox(null, -1, CFG.BUTTON_WIDTH * 3 + CFG.PADDING * 4, CFG.PADDING, CFG.BUTTON_WIDTH, true, false));
        menuElements.add(new Button_Game_Checkbox(null, -1, CFG.BUTTON_WIDTH * 2 + CFG.PADDING * 3, CFG.PADDING, CFG.BUTTON_WIDTH, true, false));
        menuElements.add(new Button_Game_Checkbox(null, -1, CFG.BUTTON_WIDTH * 4 + CFG.PADDING * 5, CFG.PADDING, CFG.BUTTON_WIDTH, true, false));
        menuElements.add(new Button_Game_Checkbox(null, -1, CFG.BUTTON_WIDTH * 5 + CFG.PADDING * 6, CFG.PADDING, CFG.BUTTON_WIDTH, true, false));
        menuElements.add(new Button_Game_Checkbox(null, -1, CFG.BUTTON_WIDTH * 6 + CFG.PADDING * 7, CFG.PADDING, CFG.BUTTON_WIDTH, true, false));
        menuElements.add(new Button_Game_Checkbox(null, -1, CFG.BUTTON_WIDTH * 7 + CFG.PADDING * 8, CFG.PADDING, CFG.BUTTON_WIDTH, true, false));
        this.initMenu(null, 0, CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING * 2, CFG.GAME_WIDTH - CFG.BUTTON_WIDTH * 2 - CFG.PADDING * 2, CFG.BUTTON_HEIGHT + CFG.PADDING * 3, menuElements);
        this.updateLanguage();
    }

    @Override
    protected void updateLanguage() {
        this.getMenuElement(0).setText(CFG.langManager.get("Alliances"));
        this.getMenuElement(1).setText(CFG.langManager.get("Relations"));
        this.getMenuElement(2).setText(CFG.langManager.get("NonAggressionPacts"));
        this.getMenuElement(3).setText(CFG.langManager.get("Vassals"));
        this.getMenuElement(4).setText(CFG.langManager.get("GuaranteedIndependence"));
        this.getMenuElement(5).setText(CFG.langManager.get("DefensivePacts"));
        this.getMenuElement(6).setText(CFG.langManager.get("MilitaryAccess"));
        this.getMenuElement(7).setText(CFG.langManager.get("Truces"));
        this.updatedButtonsWidth(CFG.PADDING, CFG.BUTTON_WIDTH);
    }

    @Override
    protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        ImageManager.getImage(Images.editor_line).draw2(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.editor_line).getHeight() + iTranslateY, this.getWidth(), this.getHeight());
        oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.4f));
        ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth(), 1);
        oSB.setColor(Color.WHITE);
        super.draw(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
    }

    @Override
    protected final void actionElement(int iID) {
        CFG.menuManager.getManageDiplomacy_Alliances().setVisible(false);
        CFG.menuManager.setVisible_ManageDiplomacy_Relations(false);
        CFG.menuManager.setVisible_ManageDiplomacy_Pacts3(false);
        CFG.menuManager.setVisible_ManageDiplomacy_Truces(false);
        CFG.menuManager.setVisible_ManageDiplomacy_Pacts_List(false);
        CFG.menuManager.setVisible_ManageDiplomacy_Vassals(false);
        CFG.menuManager.setVisible_ManageDiplomacy_Vassals_List(false);
        CFG.menuManager.setVisible_ManageDiplomacy_Guarantee(false);
        CFG.menuManager.setVisible_ManageDiplomacy_Guarantee_List(false);
        CFG.menuManager.setVisible_ManageDiplomacy_DefensivePact(false);
        CFG.menuManager.setVisible_ManageDiplomacy_DefensivePacts_List(false);
        CFG.menuManager.setVisible_ManageDiplomacy_MilitaryAccess(false);
        CFG.menuManager.setVisible_ManageDiplomacy_MilitaryAccess_List(false);
        CFG.menuManager.getColorPicker().setVisible(false, null);
        for (int i = 0; i < this.getMenuElementsSize(); ++i) {
            this.getMenuElement(i).setCheckboxState(false);
        }
        this.getMenuElement(iID).setCheckboxState(true);
        switch (iID) {
            case 0: {
                CFG.menuManager.getManageDiplomacy_Alliances().setVisible(true);
                break;
            }
            case 1: {
                CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID2 = 0;
                if (CFG.game.getActiveProvinceID() >= 0 && CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID() > 0) {
                    CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID = CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID();
                    if (!CFG.game.getProvince(CFG.game.getCiv(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID).getCapitalProvinceID()).getDrawProvince()) {
                        CFG.map.getMapCoordinates().centerToProvinceID(CFG.game.getCiv(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID).getCapitalProvinceID());
                        CFG.game.setActiveProvinceID(CFG.game.getCiv(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID).getCapitalProvinceID());
                    }
                } else if (CFG.game.getPlayer(0).getCivID() > 0 && CFG.game.getPlayer(0).getCivID() < CFG.game.getCivsSize()) {
                    CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID = CFG.game.getPlayer(0).getCivID();
                    CFG.map.getMapCoordinates().centerToProvinceID(CFG.game.getCiv(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID).getCapitalProvinceID());
                    CFG.game.setActiveProvinceID(CFG.game.getCiv(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID).getCapitalProvinceID());
                } else {
                    Random oR = new Random();
                    CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID = oR.nextInt(CFG.game.getCivsSize() - 1) + 1;
                    CFG.map.getMapCoordinates().centerToProvinceID(CFG.game.getCiv(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID).getCapitalProvinceID());
                    CFG.game.setActiveProvinceID(CFG.game.getCiv(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID).getCapitalProvinceID());
                }
                CFG.menuManager.setVisible_ManageDiplomacy_Relations(true);
                CFG.menuManager.getManageDiplomacy_Relations_Interactive().getMenuElement(0).setText(CFG.langManager.get("CustomizeRelations") + " [" + CFG.game.getCiv(CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID).getCivName() + "]");
                CFG.menuManager.getManageDiplomacy_Relations_Interactive().getMenuElement(1).setClickable(false);
                CFG.menuManager.getManageDiplomacy_Relations_Interactive().getMenuElement(2).setClickable(false);
                CFG.menuManager.getManageDiplomacy_Relations_Interactive().getMenuElement(3).setClickable(false);
                break;
            }
            case 2: {
                CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1 = -1;
                CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV2 = -1;
                if (CFG.game.getActiveProvinceID() >= 0 && CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID() > 0) {
                    CFG.MANAGE_DIPLOMACY_CUSTOMIZE_ALLIANCE_ID = CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID();
                }
                CFG.menuManager.rebuildManageDiplomacy_Pacts3();
                break;
            }
            case 3: {
                CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1 = -1;
                CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV2 = -1;
                if (CFG.game.getActiveProvinceID() >= 0 && CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID() > 0) {
                    CFG.MANAGE_DIPLOMACY_CUSTOMIZE_ALLIANCE_ID = CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID();
                }
                CFG.menuManager.rebuildManageDiplomacy_Vassals();
                break;
            }
            case 4: {
                CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1 = -1;
                CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV2 = -1;
                if (CFG.game.getActiveProvinceID() >= 0 && CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID() > 0) {
                    CFG.MANAGE_DIPLOMACY_CUSTOMIZE_ALLIANCE_ID = CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID();
                }
                CFG.menuManager.rebuildManageDiplomacy_Guarantee();
                break;
            }
            case 5: {
                CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1 = -1;
                CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV2 = -1;
                if (CFG.game.getActiveProvinceID() >= 0 && CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID() > 0) {
                    CFG.MANAGE_DIPLOMACY_CUSTOMIZE_ALLIANCE_ID = CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID();
                }
                CFG.menuManager.rebuildManageDiplomacy_Defensive();
                break;
            }
            case 6: {
                CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1 = -1;
                CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV2 = -1;
                if (CFG.game.getActiveProvinceID() >= 0 && CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID() > 0) {
                    CFG.MANAGE_DIPLOMACY_CUSTOMIZE_ALLIANCE_ID = CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID();
                }
                CFG.menuManager.rebuildManageDiplomacy_MilitaryAccess();
                break;
            }
            case 7: {
                CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1 = -1;
                CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV2 = -1;
                if (CFG.game.getActiveProvinceID() >= 0 && CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID() > 0) {
                    CFG.MANAGE_DIPLOMACY_CUSTOMIZE_ALLIANCE_ID = CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID();
                }
                CFG.menuManager.rebuildManageDiplomacy_Truces();
            }
        }
        Game_Render_Province.updateDrawProvinces();
        CFG.map.getMapTouchManager().update_ExtraAction();
    }
}

