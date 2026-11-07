/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button_New_Game_Players_Report;
import age.of.civilizations2.jakowski.lukasz.Button_Report_Armies;
import age.of.civilizations2.jakowski.lukasz.Button_Report_Armies_Right;
import age.of.civilizations2.jakowski.lukasz.Button_Report_ProvinceLosses;
import age.of.civilizations2.jakowski.lukasz.Button_Report_Units;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Text;
import age.of.civilizations2.jakowski.lukasz.SliderMenu;
import age.of.civilizations2.jakowski.lukasz.SliderMenuTitle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.utils.ScissorStack;
import java.util.ArrayList;

class Menu_InGame_Report
extends SliderMenu {
    private final float TITILE_GRADIENT_ALPHA = 0.2f;
    protected static final int ANIMATION_TIME = 225;
    private long lTime = 0L;

    protected Menu_InGame_Report() {
        int i;
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        int tempWidth = (int)((float)CFG.BUTTON_WIDTH * 6.5f);
        int tempHeight = CFG.BUTTON_HEIGHT * 4 + CFG.BUTTON_HEIGHT * 3 / 4;
        if (tempWidth > CFG.GAME_WIDTH) {
            tempWidth = CFG.GAME_WIDTH - CFG.PADDING * 4;
        }
        CFG.reportData.checkReport();
        menuElements.add(new Button_Report_Units(2, 0, tempWidth - 4, CFG.BUTTON_HEIGHT / 3){

            @Override
            protected int getWidth() {
                return Menu_InGame_Report.this.getW() - 4;
            }
        });
        menuElements.add(new Button_Report_ProvinceLosses(CFG.PADDING * 2, ((MenuElement)menuElements.get(0)).getHeight() + CFG.PADDING * 2, tempWidth - CFG.PADDING * 4, CFG.reportData.iPopulationLosses, CFG.reportData.iEconomyLosses){

            @Override
            protected int getWidth() {
                return Menu_InGame_Report.this.getW() - CFG.PADDING * 4;
            }
        });
        int tH = 0;
        for (i = 0; i < CFG.reportData.lAttackers_IDs.size(); ++i) {
            menuElements.add(new Button_Report_Armies(CFG.PADDING * 2, ((MenuElement)menuElements.get(1)).getPosY() + ((MenuElement)menuElements.get(1)).getHeight() + CFG.PADDING + tH, (tempWidth - CFG.PADDING * 6) / 2, CFG.reportData.lAttackers_IDs.get(i), CFG.reportData.lAttackers_Armies.get(i), CFG.reportData.lAttackers_Armies_Lost.get(i)){

                @Override
                protected int getWidth() {
                    return (Menu_InGame_Report.this.getW() - CFG.PADDING * 6) / 2;
                }
            });
            tH += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
        }
        tH = 0;
        for (i = 0; i < CFG.reportData.lDefenders_IDs.size(); ++i) {
            menuElements.add(new Button_Report_Armies_Right(tempWidth - CFG.PADDING * 2 - (tempWidth - CFG.PADDING * 6) / 2, ((MenuElement)menuElements.get(1)).getPosY() + ((MenuElement)menuElements.get(1)).getHeight() + CFG.PADDING + tH, (tempWidth - CFG.PADDING * 6) / 2, CFG.reportData.lDefenders_IDs.get(i), CFG.reportData.lDefenders_Armies.get(i), CFG.reportData.lDefenders_ArmiesLost.get(i)){

                @Override
                protected int getPosX() {
                    return Menu_InGame_Report.this.getW() - CFG.PADDING * 2 - (Menu_InGame_Report.this.getW() - CFG.PADDING * 6) / 2;
                }

                @Override
                protected int getWidth() {
                    return (Menu_InGame_Report.this.getW() - CFG.PADDING * 6) / 2;
                }
            });
            tH += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
        }
        int tempMaxH = 0;
        for (int i2 = 0; i2 < menuElements.size(); ++i2) {
            if (((MenuElement)menuElements.get(i2)).getPosY() + ((MenuElement)menuElements.get(i2)).getHeight() <= tempMaxH) continue;
            tempMaxH = ((MenuElement)menuElements.get(i2)).getPosY() + ((MenuElement)menuElements.get(i2)).getHeight();
        }
        menuElements.add(new Button_New_Game_Players_Report(CFG.reportData.attackersWon ? CFG.reportData.lAttackers_IDs.get(0) : CFG.reportData.lDefenders_IDs.get(0), CFG.langManager.get("IsVictorious", CFG.game.getCiv(CFG.reportData.attackersWon ? CFG.reportData.lAttackers_IDs.get(0) : CFG.reportData.lDefenders_IDs.get(0)).getCivName()) + "!", -1, CFG.PADDING, tempMaxH + CFG.PADDING * 2, tempWidth - CFG.PADDING * 2, CFG.BUTTON_HEIGHT / 2, true){

            @Override
            protected Color getColor(boolean isActive) {
                return isActive ? CFG.COLOR_TEXT_CIV_INFO_ACTIVE : (this.getIsHovered() ? CFG.COLOR_TEXT_CIV_INFO_HOVER : new Color(0.4509804f, 0.45882353f, 0.4745098f, 1.0f));
            }

            @Override
            protected void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                oSB.setColor(new Color(1.0f, 1.0f, 1.0f, this.getIsHovered() ? 0.75f : 0.5f));
                super.drawButtonBG(oSB, iTranslateX, iTranslateY, isActive);
                oSB.setColor(Color.WHITE);
            }

            @Override
            protected void buildElementHover() {
                ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Ok"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }

            @Override
            protected int getWidth() {
                return Menu_InGame_Report.this.getW() - CFG.PADDING * 2;
            }
        });
        if (tempHeight > ((MenuElement)menuElements.get(menuElements.size() - 1)).getPosY() + ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING) {
            tempHeight = ((MenuElement)menuElements.get(menuElements.size() - 1)).getPosY() + ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
        }
        this.initMenu(new SliderMenuTitle(CFG.game.getProvince(CFG.reportData.iBattleOfProvinceID).getName().length() > 0 ? this.getRandomBattleName(CFG.game.getProvince(CFG.reportData.iBattleOfProvinceID).getName()) : CFG.langManager.get("Battle"), CFG.BUTTON_HEIGHT * 3 / 5, true, true){

            @Override
            protected void draw(SpriteBatch oSB, int iTranslateX, int nPosX, int nPosY, int nWidth, boolean sliderMenuIsActive) {
                ImageManager.getImage(Images.new_game_top_edge_title).draw2(oSB, nPosX + iTranslateX, nPosY - ImageManager.getImage(Images.new_game_top_edge_title).getHeight() - this.getHeight(), nWidth - ImageManager.getImage(Images.new_game_top_edge_title).getWidth(), this.getHeight());
                ImageManager.getImage(Images.new_game_top_edge_title).draw2(oSB, nPosX + nWidth - ImageManager.getImage(Images.new_game_top_edge_title).getWidth() + iTranslateX, nPosY - ImageManager.getImage(Images.new_game_top_edge_title).getHeight() - this.getHeight(), ImageManager.getImage(Images.new_game_top_edge_title).getWidth(), this.getHeight(), true);
                oSB.setColor(new Color(CFG.COLOR_GRADIENT_TITLE_BLUE));
                ImageManager.getImage(Images.gradient).draw(oSB, nPosX + 2 + iTranslateX, nPosY - (this.getHeight() - 2) * 2 / 3 - ImageManager.getImage(Images.gradient).getHeight(), nWidth - 4, (this.getHeight() - 2) * 2 / 3, false, true);
                oSB.setColor(new Color(CFG.COLOR_GRADIENT_TITLE_BLUE.r, CFG.COLOR_GRADIENT_TITLE_BLUE.g, CFG.COLOR_GRADIENT_TITLE_BLUE.b, 0.5f));
                ImageManager.getImage(Images.gradient).draw(oSB, nPosX + 2 + iTranslateX, nPosY - (int)((float)this.getHeight() / 2.5f) - ImageManager.getImage(Images.gradient).getHeight() + 2, nWidth - 4, (int)((float)this.getHeight() / 2.5f) - 2, false, true);
                oSB.setColor(CFG.COLOR_NEW_GAME_EDGE_LINE);
                ImageManager.getImage(Images.pix255_255_255).draw2(oSB, nPosX + 2 + iTranslateX, nPosY - ImageManager.getImage(Images.pix255_255_255).getHeight() * 2, nWidth - 4, ImageManager.getImage(Images.pix255_255_255).getHeight());
                oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.65f));
                ImageManager.getImage(Images.line_32_off1).draw(oSB, nPosX + 2 + iTranslateX, nPosY - ImageManager.getImage(Images.pix255_255_255).getHeight() * 2, nWidth - 4, 1);
                oSB.setColor(Color.WHITE);
                try {
                    if (CFG.game.getProvince(CFG.reportData.iBattleOfProvinceID).getSeaProvince()) {
                        ImageManager.getImage(Images.icon_move_sea).draw(oSB, nPosX + nWidth / 2 - (int)((float)this.getTextWidth() * 0.7f) / 2 - CFG.PADDING - ImageManager.getImage(Images.icon_move_sea).getWidth() + iTranslateX, nPosY - this.getHeight() + this.getHeight() / 2 - ImageManager.getImage(Images.icon_move_sea).getHeight() / 2);
                    } else {
                        ImageManager.getImage(Images.diplo_rivals).draw(oSB, nPosX + nWidth / 2 - (int)((float)this.getTextWidth() * 0.7f) / 2 - CFG.PADDING - ImageManager.getImage(Images.diplo_rivals).getWidth() + iTranslateX, nPosY - this.getHeight() + this.getHeight() / 2 - ImageManager.getImage(Images.diplo_rivals).getHeight() / 2);
                    }
                }
                catch (IndexOutOfBoundsException indexOutOfBoundsException) {
                }
                catch (NullPointerException nullPointerException) {
                    // empty catch block
                }
                CFG.fontMain.getData().setScale(0.7f);
                CFG.drawTextWithShadow(oSB, this.getText(), nPosX + nWidth / 2 - (int)((float)this.getTextWidth() * 0.7f) / 2 + iTranslateX, nPosY - this.getHeight() + this.getHeight() / 2 - (int)((float)this.getTextHeight() * 0.7f / 2.0f), Color.WHITE);
                CFG.fontMain.getData().setScale(1.0f);
            }
        }, (CFG.GAME_WIDTH - tempWidth) / 2, ImageManager.getImage(Images.top_left).getHeight() + CFG.PADDING * 2 + CFG.BUTTON_HEIGHT * 3 / 5, tempWidth, tempHeight, menuElements, false, true);
    }

    protected final String getRandomBattleName(String sBattleOf) {
        int nR = CFG.oR.nextInt(1000);
        switch (nR % 4) {
            case 1: {
                return CFG.langManager.get("ScrambleFor", sBattleOf);
            }
            case 2: {
                return CFG.langManager.get("InvasionOf", sBattleOf);
            }
            case 3: {
                return CFG.langManager.get("AttackOn", sBattleOf);
            }
        }
        return CFG.langManager.get("BattleOf", sBattleOf);
    }

    @Override
    protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        if (this.lTime + 225L >= System.currentTimeMillis()) {
            Rectangle clipBounds = new Rectangle(this.getPosX(), CFG.GAME_HEIGHT - this.getPosY(), this.getWidth(), -((int)((float)this.getHeight() * ((float)(System.currentTimeMillis() - this.lTime) / 225.0f))));
            oSB.flush();
            ScissorStack.pushScissors(clipBounds);
            CFG.setRender_3(true);
        }
        ImageManager.getImage(Images.new_game_top_edge).draw2(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.new_game_top_edge).getHeight() + iTranslateY, this.getWidth() - ImageManager.getImage(Images.new_game_top_edge).getWidth(), this.getHeight() + CFG.PADDING, false, true);
        ImageManager.getImage(Images.new_game_top_edge).draw2(oSB, this.getPosX() + this.getWidth() - ImageManager.getImage(Images.new_game_top_edge).getWidth() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.new_game_top_edge).getHeight() + iTranslateY, ImageManager.getImage(Images.new_game_top_edge).getWidth(), this.getHeight() + CFG.PADDING, true, true);
        this.beginClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        if (CFG.reportData.attackersWon) {
            oSB.setColor(new Color(CFG.COLOR_TEXT_CHECKBOX_TRUE.r, CFG.COLOR_TEXT_CHECKBOX_TRUE.g, CFG.COLOR_TEXT_CHECKBOX_TRUE.b, 0.325f));
        } else {
            oSB.setColor(new Color(CFG.COLOR_TEXT_CHECKBOX_FALSE.r, CFG.COLOR_TEXT_CHECKBOX_FALSE.g, CFG.COLOR_TEXT_CHECKBOX_FALSE.b, 0.325f));
        }
        ImageManager.getImage(Images.gradient).draw(oSB, this.getPosX() + 2 + iTranslateX, this.getMenuPosY() + this.getMenuElement(0).getHeight() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY, (this.getWidth() - 4) / 2, (int)((float)this.getMenuElement(0).getHeight() * 1.45f));
        if (CFG.reportData.attackersWon) {
            oSB.setColor(new Color(CFG.COLOR_TEXT_CHECKBOX_FALSE.r, CFG.COLOR_TEXT_CHECKBOX_FALSE.g, CFG.COLOR_TEXT_CHECKBOX_FALSE.b, 0.325f));
        } else {
            oSB.setColor(new Color(CFG.COLOR_TEXT_CHECKBOX_TRUE.r, CFG.COLOR_TEXT_CHECKBOX_TRUE.g, CFG.COLOR_TEXT_CHECKBOX_TRUE.b, 0.325f));
        }
        ImageManager.getImage(Images.gradient).draw(oSB, this.getPosX() + 2 + (this.getWidth() - 4) / 2 + iTranslateX, this.getMenuPosY() + this.getMenuElement(0).getHeight() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY, (this.getWidth() - 4) / 2, (int)((float)this.getMenuElement(0).getHeight() * 1.45f));
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.475f));
        ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + 2 + (this.getWidth() - 4) / 2 - (this.getWidth() - 4) / 8 + iTranslateX, this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY, (this.getWidth() - 4) / 8, this.getHeight(), true, false);
        ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + 2 + (this.getWidth() - 4) / 2 + iTranslateX, this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY, (this.getWidth() - 4) / 8, this.getHeight());
        oSB.setColor(Color.WHITE);
        this.drawMenu(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        if (this.lTime + 225L >= System.currentTimeMillis()) {
            try {
                oSB.flush();
                ScissorStack.popScissors();
            }
            catch (IllegalStateException illegalStateException) {
                // empty catch block
            }
        }
        this.endClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
    }

    @Override
    protected void drawScrollPos(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        if (sliderMenuIsActive) {
            super.drawScrollPos(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        }
    }

    @Override
    protected final void actionElement(int iID) {
        if (iID == this.getMenuElementsSize() - 1) {
            this.setVisible(false);
        }
    }

    @Override
    protected void setVisible(boolean visible) {
        super.setVisible(visible);
        this.lTime = System.currentTimeMillis();
    }

    protected final int getW() {
        return this.getWidth();
    }
}

