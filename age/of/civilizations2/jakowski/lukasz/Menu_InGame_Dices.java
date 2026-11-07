/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button_Transparent;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Flag;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Image;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Text;
import age.of.civilizations2.jakowski.lukasz.SliderMenu;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

class Menu_InGame_Dices
extends SliderMenu {
    protected static final float FONT_SCALE = 1.0f;
    protected static final float FONT_SCALE2 = 0.8f;
    private String sLeft;
    private int iLeftWidth;
    private String sRight;
    private int iRightWidth;
    private String sLeftBonus;
    private int iLeftBonusWidth;
    private String sRightBonus;
    private int iRightBonusWidth;

    protected Menu_InGame_Dices() {
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        menuElements.add(new Button_Transparent(0, this.getPosY2(), 1, this.getHeight2(), true){

            @Override
            protected int getPosX() {
                return Menu_InGame_Dices.this.getPosX2();
            }

            @Override
            protected int getWidth() {
                return Menu_InGame_Dices.this.getWidth2();
            }

            @Override
            protected void buildElementHover() {
                ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("XBonusForEachPointOfTheDifferenceBetweenBalueOfDices", "2.5"), CFG.COLOR_TEXT_NUM_OF_PROVINCES));
                nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.dice, CFG.PADDING, 0));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.gameAction.diceAggressorsCivID));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("DiceRoll") + ": ", CFG.COLOR_TEXT_NUM_OF_PROVINCES));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + CFG.gameAction.diceAggressors));
                nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.dice, CFG.PADDING, Menu_InGame_Dices.this.iLeftBonusWidth > 0 ? CFG.PADDING : 0));
                if (Menu_InGame_Dices.this.iLeftBonusWidth > 0) {
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text(Menu_InGame_Dices.this.sLeftBonus, CFG.COLOR_TEXT_MODIFIER_POSITIVE));
                }
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.gameAction.diceDefendersCivID));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("DiceRoll") + ": ", CFG.COLOR_TEXT_NUM_OF_PROVINCES));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + CFG.gameAction.diceDefenders));
                nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.dice, CFG.PADDING, Menu_InGame_Dices.this.iRightBonusWidth > 0 ? CFG.PADDING : 0));
                if (Menu_InGame_Dices.this.iRightBonusWidth > 0) {
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text(Menu_InGame_Dices.this.sRightBonus, CFG.COLOR_TEXT_MODIFIER_POSITIVE));
                }
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
        });
        this.initMenu(null, 0, 0, CFG.map.getMapBG().getMinimapWidth(), CFG.GAME_HEIGHT, menuElements, false, false);
        this.updateLanguage();
    }

    @Override
    protected void updateLanguage() {
        this.sLeft = "" + CFG.gameAction.diceAggressors;
        CFG.glyphLayout.setText(CFG.fontMain, this.sLeft);
        this.iLeftWidth = (int)(CFG.glyphLayout.width * 1.0f);
        this.sRight = "" + CFG.gameAction.diceDefenders;
        CFG.glyphLayout.setText(CFG.fontMain, this.sRight);
        this.iRightWidth = (int)(CFG.glyphLayout.width * 1.0f);
        float tBonus = CFG.gameAction.diceRollBonus(false);
        if (tBonus > 0.0f) {
            this.sLeftBonus = "+" + (float)((int)(tBonus * 100.0f)) / 100.0f + "% " + CFG.langManager.get("Bonus");
            CFG.glyphLayout.setText(CFG.fontMain, this.sLeftBonus);
            this.iLeftBonusWidth = (int)(CFG.glyphLayout.width * 0.8f);
        } else {
            this.sLeftBonus = "";
            this.iLeftBonusWidth = 0;
        }
        tBonus = CFG.gameAction.diceRollBonus(true);
        if (tBonus > 0.0f) {
            this.sRightBonus = "+" + (float)((int)(tBonus * 100.0f)) / 100.0f + "% " + CFG.langManager.get("Bonus");
            CFG.glyphLayout.setText(CFG.fontMain, this.sRightBonus);
            this.iRightBonusWidth = (int)(CFG.glyphLayout.width * 0.8f);
        } else {
            this.sRightBonus = "";
            this.iRightBonusWidth = 0;
        }
    }

    private final int getPosX2() {
        return this.getWidth() / 2 - this.getWidth2() / 2;
    }

    private final int getPosY2() {
        return CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING * 2 - CFG.PADDING - this.getHeight2();
    }

    private final int getWidth2() {
        return this.iLeftWidth + this.iRightWidth + (int)((float)ImageManager.getImage(Images.dice).getWidth() * this.getImageScale(Images.dice)) + CFG.PADDING * 2 + (int)((float)ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(Images.flag_rect)) * 2 + CFG.PADDING * 4;
    }

    private final int getHeight2() {
        return CFG.TEXT_HEIGHT + CFG.PADDING * 2;
    }

    @Override
    protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.725f));
        ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX2() + iTranslateX, this.getPosY2() - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth2(), this.getHeight2());
        oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 1.0f));
        ImageManager.getImage(Images.dice).draw(oSB, this.getPosX2() + this.getWidth2() / 2 - (int)((float)ImageManager.getImage(Images.dice).getWidth() * this.getImageScale(Images.dice)) / 2 + iTranslateX, this.getPosY2() + this.getHeight2() / 2 - (int)((float)ImageManager.getImage(Images.dice).getHeight() * this.getImageScale(Images.dice)) / 2 - ImageManager.getImage(Images.dice).getHeight() + iTranslateY, (int)((float)ImageManager.getImage(Images.dice).getWidth() * this.getImageScale(Images.dice)), (int)((float)ImageManager.getImage(Images.dice).getHeight() * this.getImageScale(Images.dice)));
        CFG.game.getCiv(CFG.gameAction.diceAggressorsCivID).getFlag().draw(oSB, this.getPosX2() + this.getWidth2() / 2 - (int)((float)ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(Images.flag_rect)) / 2 - (int)((float)ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(Images.flag_rect)) - this.iLeftWidth - CFG.PADDING * 2 + iTranslateX, this.getPosY2() + this.getHeight2() / 2 - (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect)) / 2 - CFG.game.getCiv(CFG.gameAction.diceAggressorsCivID).getFlag().getHeight() + iTranslateY, (int)((float)ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(Images.flag_rect)), (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect)));
        ImageManager.getImage(Images.flag_rect).draw(oSB, this.getPosX2() + this.getWidth2() / 2 - (int)((float)ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(Images.flag_rect)) / 2 - (int)((float)ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(Images.flag_rect)) - this.iLeftWidth - CFG.PADDING * 2 + iTranslateX, this.getPosY2() + this.getHeight2() / 2 - (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect)) / 2 - ImageManager.getImage(Images.flag_rect).getHeight() + iTranslateY, (int)((float)ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(Images.flag_rect)), (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect)));
        CFG.game.getCiv(CFG.gameAction.diceDefendersCivID).getFlag().draw(oSB, this.getPosX2() + this.getWidth2() / 2 + (int)((float)ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(Images.flag_rect)) / 2 + this.iRightWidth + CFG.PADDING * 2 + iTranslateX, this.getPosY2() + this.getHeight2() / 2 - (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect)) / 2 - CFG.game.getCiv(CFG.gameAction.diceDefendersCivID).getFlag().getHeight() + iTranslateY, (int)((float)ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(Images.flag_rect)), (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect)));
        ImageManager.getImage(Images.flag_rect).draw(oSB, this.getPosX2() + this.getWidth2() / 2 + (int)((float)ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(Images.flag_rect)) / 2 + this.iRightWidth + CFG.PADDING * 2 + iTranslateX, this.getPosY2() + this.getHeight2() / 2 - (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect)) / 2 - ImageManager.getImage(Images.flag_rect).getHeight() + iTranslateY, (int)((float)ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(Images.flag_rect)), (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect)));
        oSB.setColor(Color.WHITE);
        CFG.fontMain.getData().setScale(1.0f);
        CFG.drawText(oSB, this.sLeft, this.getPosX2() + this.getWidth2() / 2 - this.iLeftWidth - CFG.PADDING - (int)((float)ImageManager.getImage(Images.dice).getWidth() * this.getImageScale(Images.dice)) / 2 + iTranslateX, this.getPosY2() + this.getHeight2() / 2 - (int)((float)CFG.TEXT_HEIGHT * 1.0f / 2.0f) + iTranslateY, this.getMenuElement(0).getIsHovered() ? CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE : (CFG.gameAction.diceAggressors > CFG.gameAction.diceDefenders ? Color.WHITE : (CFG.gameAction.diceAggressors == CFG.gameAction.diceDefenders ? CFG.COLOR_TEXT_MODIFIER_NEUTRAL : CFG.COLOR_TEXT_MODIFIER_NEUTRAL)));
        CFG.drawText(oSB, this.sRight, this.getPosX2() + this.getWidth2() / 2 + (int)((float)ImageManager.getImage(Images.dice).getWidth() * this.getImageScale(Images.dice)) / 2 + CFG.PADDING + iTranslateX, this.getPosY2() + this.getHeight2() / 2 - (int)((float)CFG.TEXT_HEIGHT * 1.0f / 2.0f) + iTranslateY, this.getMenuElement(0).getIsHovered() ? CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE : (CFG.gameAction.diceDefenders > CFG.gameAction.diceAggressors ? Color.WHITE : (CFG.gameAction.diceAggressors == CFG.gameAction.diceDefenders ? CFG.COLOR_TEXT_MODIFIER_NEUTRAL : CFG.COLOR_TEXT_MODIFIER_NEUTRAL)));
        CFG.fontMain.getData().setScale(1.0f);
    }

    private final float getImageScale(int nImageID) {
        return (float)CFG.TEXT_HEIGHT / (float)ImageManager.getImage(nImageID).getHeight() < 1.0f ? (float)CFG.TEXT_HEIGHT / (float)ImageManager.getImage(nImageID).getHeight() : 1.0f;
    }

    @Override
    protected void actionElement(int nMenuElementID) {
        CFG.toast.setInView("" + CFG.gameAction.diceAggressors + " " + CFG.langManager.get("vs") + " " + CFG.gameAction.diceDefenders, CFG.COLOR_TEXT_NUM_OF_PROVINCES);
    }
}

