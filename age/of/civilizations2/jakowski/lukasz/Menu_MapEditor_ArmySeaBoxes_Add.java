/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button_Game;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Menu;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.Point_XY;
import age.of.civilizations2.jakowski.lukasz.Province_ArmyBox;
import age.of.civilizations2.jakowski.lukasz.SliderMenu;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

class Menu_MapEditor_ArmySeaBoxes_Add
extends SliderMenu {
    protected static Point_XY oFirstPoint = null;
    protected static Point_XY oSecondPoint = null;

    protected Menu_MapEditor_ArmySeaBoxes_Add() {
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        menuElements.add(new Button_Game(null, -1, CFG.PADDING, CFG.GAME_HEIGHT - CFG.PADDING - CFG.BUTTON_HEIGHT, CFG.BUTTON_WIDTH * 2));
        menuElements.add(new Button_Game(null, -1, CFG.GAME_WIDTH - CFG.BUTTON_WIDTH * 2 - CFG.PADDING, CFG.GAME_HEIGHT - CFG.PADDING - CFG.BUTTON_HEIGHT, CFG.BUTTON_WIDTH * 2){

            @Override
            protected final Color getColor(boolean isActive) {
                return isActive ? new Color(0.75f, 0.8f, 0.03f, 1.0f) : (this.getClickable() ? new Color(0.941f, 1.0f, 0.0f, 1.0f) : new Color(0.674f, 0.09f, 0.066f, 0.5f));
            }
        });
        menuElements.add(new Button_Game(null, -1, CFG.PADDING, CFG.PADDING, CFG.GAME_WIDTH / 2 - CFG.PADDING - CFG.PADDING / 2));
        menuElements.add(new Button_Game(null, -1, CFG.GAME_WIDTH / 2 + CFG.PADDING / 2, CFG.PADDING, CFG.GAME_WIDTH / 2 - CFG.PADDING - CFG.PADDING / 2));
        this.initMenu(null, 0, 0, CFG.GAME_WIDTH, CFG.GAME_HEIGHT, menuElements);
        this.updateLanguage();
    }

    @Override
    protected void updateLanguage() {
        this.getMenuElement(0).setText(CFG.langManager.get("Back"));
        this.getMenuElement(1).setText(CFG.langManager.get("Save"));
        this.getMenuElement(2).setText(CFG.langManager.get("Reset") + " [1]");
        this.getMenuElement(3).setText(CFG.langManager.get("Reset") + " [2]");
    }

    @Override
    protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        super.beginClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        CFG.drawEditorButtons_Bot_Edge_R(oSB, iTranslateX, this.getMenuElement(0).getPosY() - CFG.PADDING + iTranslateY, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT + CFG.PADDING * 2);
        CFG.drawEditorTitle_Edge_LR(oSB, iTranslateX, this.getMenuElement(2).getPosY() - CFG.PADDING + iTranslateY, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT + CFG.PADDING * 2);
        super.drawMenu(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
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
                if (oFirstPoint.getPosY() >= 0 && oSecondPoint.getPosY() >= 0) {
                    int tempPoint;
                    if (oFirstPoint.getPosX() > oSecondPoint.getPosX()) {
                        tempPoint = oFirstPoint.getPosX();
                        oFirstPoint.setPosX(oSecondPoint.getPosX());
                        oSecondPoint.setPosX(tempPoint);
                    }
                    if (oFirstPoint.getPosY() > oSecondPoint.getPosY()) {
                        tempPoint = oFirstPoint.getPosY();
                        oFirstPoint.setPosY(oSecondPoint.getPosY());
                        oSecondPoint.setPosY(tempPoint);
                    }
                    if (CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV2 < 0) {
                        if (CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes() == null) {
                            CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).initProvinceArmyBoxes();
                        }
                        ArrayList<Province_ArmyBox> nSet = new ArrayList<Province_ArmyBox>();
                        nSet.add(new Province_ArmyBox(oFirstPoint.getPosX(), oFirstPoint.getPosY(), oSecondPoint.getPosX(), oSecondPoint.getPosY()));
                        for (int i = 0; i < CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().size(); ++i) {
                            nSet.add(CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().get(i));
                        }
                        CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).setProvinceArmyBoxes(nSet);
                    } else {
                        CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).getProvinceArmyBoxes().set(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV2, new Province_ArmyBox(oFirstPoint.getPosX(), oFirstPoint.getPosY(), oSecondPoint.getPosX(), oSecondPoint.getPosY()));
                    }
                    this.onBackPressed();
                } else {
                    CFG.toast.setInView("UPDATE POINTS!");
                }
                return;
            }
            case 2: {
                oFirstPoint.setPosX(-1);
                oFirstPoint.setPosY(-1);
                return;
            }
            case 3: {
                oSecondPoint.setPosX(-1);
                oSecondPoint.setPosY(-1);
                return;
            }
        }
    }

    @Override
    protected void onBackPressed() {
        CFG.menuManager.setViewID(Menu.eMAP_EDITOR_ARMY_SEA_BOXES_EDIT);
        CFG.menuManager.setBackAnimation(true);
        CFG.game.getProvince(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1).updateDrawArmy();
    }
}

