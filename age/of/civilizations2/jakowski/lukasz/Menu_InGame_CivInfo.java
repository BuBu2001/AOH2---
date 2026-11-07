/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.AoCGame;
import age.of.civilizations2.jakowski.lukasz.Button_FlagFrame;
import age.of.civilizations2.jakowski.lukasz.Button_Rank;
import age.of.civilizations2.jakowski.lukasz.Button_Transparent;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.ColorPicker_AoC;
import age.of.civilizations2.jakowski.lukasz.Dialog;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.Keyboard;
import age.of.civilizations2.jakowski.lukasz.Menu;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Flag;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Ideology;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Ideology_Vassal;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Image;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Text;
import age.of.civilizations2.jakowski.lukasz.Menu_InGame_CivilizationView;
import age.of.civilizations2.jakowski.lukasz.SliderMenu;
import age.of.civilizations2.jakowski.lukasz.SliderMenuTitle;
import age.of.civilizations2.jakowski.lukasz.SoundsManager;
import age.of.civilizations2.jakowski.lukasz.Text;
import age.of.civilizations2.jakowski.lukasz.Text_Scrollable;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.utils.ScissorStack;
import java.util.ArrayList;

class Menu_InGame_CivInfo
extends SliderMenu {
    protected static final int ANIMATION_TIME = 175;
    protected static long lTime = 0L;
    protected static boolean hideAnimation = true;
    protected String sGreatPower;
    protected int iGreatPowerWidth = 0;
    protected static final float FONT_SIZE_GREAT = 0.7f;
    protected static final float FONT_SIZE_INFO = 0.8f;

    protected Menu_InGame_CivInfo() {
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        menuElements.add(new Button_Rank("1", CFG.PADDING * 2 + ImageManager.getImage(Images.top_flag_frame).getWidth() - ImageManager.getImage(Images.top_circle).getWidth() * 4 / 5, CFG.PADDING * 2 + ImageManager.getImage(Images.top_flag_frame).getHeight() - ImageManager.getImage(Images.top_circle).getHeight() * 6 / 7){

            @Override
            protected void buildElementHover() {
                this.menuElementHover = CFG.game.getHover_RankOfCiv(CFG.getActiveCivInfo());
            }
        });
        menuElements.add(new Text_Scrollable(null, ImageManager.getImage(Images.top_flag_frame).getWidth() + CFG.PADDING * 4, CFG.PADDING * 3, CFG.CIV_INFO_MENU_WIDTH - ImageManager.getImage(Images.top_flag_frame).getWidth() - CFG.PADDING * 5, CFG.COLOR_TEXT_CIV_NAME){

            @Override
            protected Color getColor(boolean isActive) {
                return isActive ? CFG.COLOR_TEXT_CIV_NAME_ACTIVE : (this.getClickable() ? (this.getIsHovered() ? CFG.COLOR_TEXT_CIV_NAME_HOVERED : CFG.COLOR_TEXT_CIV_NAME) : new Color(0.78f, 0.78f, 0.78f, 0.7f));
            }

            @Override
            protected void buildElementHover() {
                ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("UpdateCivilizationName") + ".", CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                if (CFG.game.getCiv(CFG.getActiveCivInfo()).getCivID() != CFG.game.getCiv(CFG.getActiveCivInfo()).getPuppetOfCivID()) {
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Lord") + ": "));
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.game.getCiv(CFG.game.getCiv(CFG.getActiveCivInfo()).getPuppetOfCivID()).getCivName(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                    nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getCiv(CFG.getActiveCivInfo()).getPuppetOfCivID(), CFG.PADDING, 0));
                    nData.add(new MenuElement_Hover_v2_Element_Type_Ideology(CFG.game.getCiv(CFG.game.getCiv(CFG.getActiveCivInfo()).getPuppetOfCivID()).getIdeologyID(), CFG.PADDING, 0));
                    nElements.add(new MenuElement_Hover_v2_Element2(nData));
                    nData.clear();
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Vassal") + ": "));
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.game.getCiv(CFG.getActiveCivInfo()).getCivName(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                    nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.getActiveCivInfo(), CFG.PADDING, 0));
                    nData.add(new MenuElement_Hover_v2_Element_Type_Ideology_Vassal(CFG.game.getCiv(CFG.getActiveCivInfo()).getIdeologyID(), CFG.PADDING, 0));
                    nElements.add(new MenuElement_Hover_v2_Element2(nData));
                    nData.clear();
                } else {
                    nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.getActiveCivInfo()));
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.game.getCiv(CFG.getActiveCivInfo()).getCivName()));
                    nElements.add(new MenuElement_Hover_v2_Element2(nData));
                    nData.clear();
                }
                this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }

            @Override
            protected void draw_Element(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
                if (CFG.game.getCiv(CFG.getActiveCivInfo()).getCivID() != CFG.game.getCiv(CFG.getActiveCivInfo()).getPuppetOfCivID()) {
                    super.draw_Element(oSB, iTranslateX + CFG.PADDING + (int)((float)CFG.game.getCiv(CFG.game.getCiv(CFG.getActiveCivInfo()).getPuppetOfCivID()).getFlag().getWidth() * Menu_InGame_CivInfo.this.getImageScale(CFG.game.getCiv(CFG.game.getCiv(CFG.getActiveCivInfo()).getPuppetOfCivID()).getFlag().getHeight())), iTranslateY, isActive, scrollableY);
                    CFG.game.getCiv(CFG.game.getCiv(CFG.getActiveCivInfo()).getPuppetOfCivID()).getFlag().draw(oSB, this.getPosX() + this.getCurrent() + iTranslateX, this.getPosY() - CFG.game.getCiv(CFG.game.getCiv(CFG.getActiveCivInfo()).getPuppetOfCivID()).getFlag().getHeight() + (int)((float)this.getHeight() - (float)CFG.game.getCiv(CFG.game.getCiv(CFG.getActiveCivInfo()).getPuppetOfCivID()).getFlag().getHeight() * Menu_InGame_CivInfo.this.getImageScale(CFG.game.getCiv(CFG.game.getCiv(CFG.getActiveCivInfo()).getPuppetOfCivID()).getFlag().getHeight())) / 2 + iTranslateY, (int)((float)CFG.game.getCiv(CFG.game.getCiv(CFG.getActiveCivInfo()).getPuppetOfCivID()).getFlag().getWidth() * Menu_InGame_CivInfo.this.getImageScale(CFG.game.getCiv(CFG.game.getCiv(CFG.getActiveCivInfo()).getPuppetOfCivID()).getFlag().getHeight())), (int)((float)CFG.game.getCiv(CFG.game.getCiv(CFG.getActiveCivInfo()).getPuppetOfCivID()).getFlag().getHeight() * Menu_InGame_CivInfo.this.getImageScale(CFG.game.getCiv(CFG.game.getCiv(CFG.getActiveCivInfo()).getPuppetOfCivID()).getFlag().getHeight())));
                    ImageManager.getImage(Images.flag_rect).draw(oSB, this.getPosX() + this.getCurrent() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.flag_rect).getHeight() + (int)((float)this.getHeight() - (float)CFG.game.getCiv(CFG.game.getCiv(CFG.getActiveCivInfo()).getPuppetOfCivID()).getFlag().getHeight() * Menu_InGame_CivInfo.this.getImageScale(CFG.game.getCiv(CFG.game.getCiv(CFG.getActiveCivInfo()).getPuppetOfCivID()).getFlag().getHeight())) / 2 + iTranslateY, (int)((float)CFG.game.getCiv(CFG.game.getCiv(CFG.getActiveCivInfo()).getPuppetOfCivID()).getFlag().getWidth() * Menu_InGame_CivInfo.this.getImageScale(CFG.game.getCiv(CFG.game.getCiv(CFG.getActiveCivInfo()).getPuppetOfCivID()).getFlag().getHeight())), (int)((float)CFG.game.getCiv(CFG.game.getCiv(CFG.getActiveCivInfo()).getPuppetOfCivID()).getFlag().getHeight() * Menu_InGame_CivInfo.this.getImageScale(CFG.game.getCiv(CFG.game.getCiv(CFG.getActiveCivInfo()).getPuppetOfCivID()).getFlag().getHeight())));
                } else {
                    super.draw_Element(oSB, iTranslateX, iTranslateY, isActive, scrollableY);
                }
            }

            @Override
            protected int getTextWidth() {
                try {
                    if (CFG.game.getCiv(CFG.getActiveCivInfo()).getCivID() != CFG.game.getCiv(CFG.getActiveCivInfo()).getPuppetOfCivID()) {
                        return super.getTextWidth() + CFG.PADDING + (int)((float)CFG.game.getCiv(CFG.game.getCiv(CFG.getActiveCivInfo()).getPuppetOfCivID()).getFlag().getWidth() * Menu_InGame_CivInfo.this.getImageScale(CFG.game.getCiv(CFG.game.getCiv(CFG.getActiveCivInfo()).getPuppetOfCivID()).getFlag().getHeight()));
                    }
                    return super.getTextWidth();
                }
                catch (IndexOutOfBoundsException ex) {
                    return super.getTextWidth();
                }
            }
        });
        menuElements.add(new Button_FlagFrame(CFG.PADDING * 2, CFG.PADDING * 2, true){

            @Override
            protected void buildElementHover() {
                ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("ShowHideColorPicker"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.pickeIcon, CFG.PADDING, 0));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("CivRank") + ": "));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + CFG.game.getCiv(CFG.getActiveCivInfo()).getRankPosition() + "/" + CFG.game.getCivsSize(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.rank, CFG.PADDING, 0));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }

            @Override
            protected int getSFX() {
                return SoundsManager.SOUND_CLICK2;
            }
        });
        menuElements.add(new Text(null, ImageManager.getImage(Images.top_flag_frame).getWidth() + CFG.PADDING * 4, CFG.PADDING * 4 + CFG.TEXT_HEIGHT){
            int iCurrent;
            {
                this.iCurrent = 0;
            }

            @Override
            protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
                CFG.fontMain.getData().setScale(0.8f);
                CFG.drawTextWithShadow(oSB, this.sText, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getColor(isActive));
                CFG.drawTextWithShadow(oSB, "" + this.getCurrent(), this.getPosX() + (int)((float)this.getTextWidth() * 0.8f) + iTranslateX, this.getPosY() + iTranslateY, CFG.COLOR_TEXT_NUM_OF_PROVINCES);
                CFG.fontMain.getData().setScale(1.0f);
            }

            @Override
            protected Color getColor(boolean isActive) {
                return isActive ? CFG.COLOR_TEXT_RANK_ACTIVE : (this.getClickable() ? (this.getIsHovered() ? CFG.COLOR_TEXT_RANK_HOVER : CFG.COLOR_TEXT_RANK) : new Color(0.78f, 0.78f, 0.78f, 0.7f));
            }

            @Override
            protected void buildElementHover() {
                this.menuElementHover = CFG.game.getHover_ProvincesOfCiv(CFG.getActiveCivInfo());
            }

            @Override
            protected int getCurrent() {
                return this.iCurrent;
            }

            @Override
            protected void setCurrent(int nCurrent) {
                this.iCurrent = nCurrent;
            }

            @Override
            protected int getWidth() {
                return CFG.CIV_INFO_MENU_WIDTH - ImageManager.getImage(Images.top_flag_frame).getWidth() - CFG.PADDING * 5 - 2;
            }
        });
        menuElements.add(new Text("", ImageManager.getImage(Images.top_flag_frame).getWidth() + CFG.PADDING * 4, CFG.PADDING * 4 + CFG.TEXT_HEIGHT){

            @Override
            protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
                Rectangle clipBounds = new Rectangle(this.getPosX() + iTranslateX, CFG.GAME_HEIGHT - this.getPosY() - iTranslateY, this.getWidth(), -this.getHeight());
                oSB.flush();
                ScissorStack.pushScissors(clipBounds);
                CFG.fontMain.getData().setScale(0.8f);
                CFG.drawTextWithShadow(oSB, this.getText(), this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getColor(isActive));
                CFG.fontMain.getData().setScale(1.0f);
                try {
                    oSB.flush();
                    ScissorStack.popScissors();
                }
                catch (IllegalStateException illegalStateException) {
                    // empty catch block
                }
            }

            @Override
            protected Color getColor(boolean isActive) {
                return isActive ? CFG.COLOR_TEXT_RANK_ACTIVE : (this.getClickable() ? (this.getIsHovered() ? CFG.COLOR_TEXT_RANK_HOVER : CFG.COLOR_TEXT_RANK) : new Color(0.78f, 0.78f, 0.78f, 0.7f));
            }

            @Override
            protected void buildElementHover() {
                this.menuElementHover = CFG.game.getHover_LeaderOfCiv(CFG.getActiveCivInfo());
            }

            @Override
            protected int getWidth() {
                return CFG.CIV_INFO_MENU_WIDTH - ImageManager.getImage(Images.top_flag_frame).getWidth() - CFG.PADDING * 5 - 2;
            }
        });
        menuElements.add(new Button_Transparent(0, 0, CFG.CIV_INFO_MENU_WIDTH - 1, ImageManager.getImage(Images.top_flag_frame).getHeight() + CFG.PADDING * 4 - 1, false));
        this.initMenu(new SliderMenuTitle("", 0, false, false), 0 + AoCGame.LEFT, ImageManager.getImage(Images.top_left).getHeight() + CFG.PADDING * 3, CFG.CIV_INFO_MENU_WIDTH, ImageManager.getImage(Images.top_flag_frame).getHeight() + CFG.PADDING * 4, menuElements, false, true);
        this.updateLanguage();
    }

    @Override
    protected void updateLanguage() {
        try {
            this.getMenuElement(1).setText(CFG.getActiveCivInfo() > 0 ? CFG.game.getCiv(CFG.getActiveCivInfo()).getCivName() : "");
        }
        catch (IndexOutOfBoundsException ex) {
            this.getMenuElement(1).setText("");
            CFG.setActiveCivInfo(0);
        }
        this.getMenuElement(3).setText(CFG.langManager.get("Provinces") + ": ");
        this.sGreatPower = CFG.langManager.get("GreatPower");
        CFG.glyphLayout.setText(CFG.fontMain, this.sGreatPower);
        this.iGreatPowerWidth = (int)(CFG.glyphLayout.width * 0.7f);
    }

    private final float getImageScale() {
        return (float)CFG.TEXT_HEIGHT * 0.7f / (float)CFG.CIV_FLAG_HEIGHT;
    }

    @Override
    protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        if (lTime + 175L >= System.currentTimeMillis()) {
            iTranslateX = hideAnimation ? (iTranslateX -= (int)((float)this.getWidth() * ((float)(System.currentTimeMillis() - lTime) / 175.0f))) : (iTranslateX += -this.getWidth() + (int)((float)this.getWidth() * ((float)(System.currentTimeMillis() - lTime) / 175.0f)));
            CFG.setRender_3(true);
        } else if (hideAnimation) {
            super.setVisible(false);
            return;
        }
        if (CFG.game.getCiv((int)CFG.getActiveCivInfo()).iLeague > 7 || CFG.game.getCiv(CFG.getActiveCivInfo()).getRankPosition() < 11) {
            ImageManager.getImage(Images.dialog_title).draw2(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.dialog_title).getHeight() - (int)((float)CFG.TEXT_HEIGHT * 0.7f + (float)(CFG.PADDING * 2)) + iTranslateY, this.iGreatPowerWidth + CFG.PADDING * 5 + (int)((float)ImageManager.getImage(Images.rank).getWidth() * this.getImageScale()), (int)((float)CFG.TEXT_HEIGHT * 0.7f + (float)(CFG.PADDING * 4)), true, false);
            ImageManager.getImage(Images.rank).draw(oSB, 2 + this.getPosX() + CFG.PADDING + iTranslateX, this.getPosY() - CFG.PADDING - (int)((float)CFG.TEXT_HEIGHT * 0.7f) / 2 - (int)((float)ImageManager.getImage(Images.rank).getHeight() * this.getImageScale()) / 2 + 1 + iTranslateY - ImageManager.getImage(Images.rank).getHeight(), (int)((float)ImageManager.getImage(Images.rank).getWidth() * this.getImageScale()), (int)((float)ImageManager.getImage(Images.rank).getHeight() * this.getImageScale()));
            CFG.fontMain.getData().setScale(0.7f);
            CFG.drawTextWithShadow(oSB, this.sGreatPower, this.getPosX() + CFG.PADDING * 2 + (int)((float)ImageManager.getImage(Images.rank).getWidth() * this.getImageScale()) + iTranslateX, this.getPosY() - CFG.PADDING - (int)((float)CFG.TEXT_HEIGHT * 0.7f) + 1 + iTranslateY, CFG.COLOR_TEXT_NUM_OF_PROVINCES);
            CFG.fontMain.getData().setScale(1.0f);
        }
        ImageManager.getImage(Images.new_game_top_edge).draw2(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.new_game_top_edge).getHeight() + iTranslateY, this.getWidth(), this.getHeight(), true, false);
        oSB.setColor(new Color(CFG.COLOR_GRADIENT_TITLE_BLUE.r, CFG.COLOR_GRADIENT_TITLE_BLUE.g, CFG.COLOR_GRADIENT_TITLE_BLUE.b, 0.275f));
        ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + 1 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth(), this.getHeight() - 1);
        oSB.setColor(Color.WHITE);
        oSB.setColor(new Color(CFG.COLOR_GRADIENT_LIGHTER_DARK_BLUE.r, CFG.COLOR_GRADIENT_LIGHTER_DARK_BLUE.g, CFG.COLOR_GRADIENT_LIGHTER_DARK_BLUE.b, 1.0f));
        ImageManager.getImage(Images.gradient).draw(oSB, this.getPosX() + iTranslateX, this.getMenuPosY() - ImageManager.getImage(Images.gradient).getHeight() + this.getHeight() - this.getHeight() / 2 + iTranslateY, this.getWidth() - 2, this.getHeight() / 2, false, true);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.65f));
        ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() + iTranslateX, this.getMenuPosY() + this.getHeight() - ImageManager.getImage(Images.pix255_255_255).getHeight() - 2 + iTranslateY, this.getWidth() - 2, 1);
        oSB.setColor(CFG.COLOR_NEW_GAME_EDGE_LINE);
        ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() + iTranslateX, this.getMenuPosY() + this.getHeight() - ImageManager.getImage(Images.pix255_255_255).getHeight() - 1 + iTranslateY, this.getWidth() - 2, 1);
        oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.65f));
        ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX() + iTranslateX, this.getMenuPosY() + this.getHeight() - ImageManager.getImage(Images.line_32_off1).getHeight() - 1 + iTranslateY, this.getWidth() - 2, 1);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.8f));
        ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + iTranslateX, this.getMenuPosY() + this.getHeight() - ImageManager.getImage(Images.slider_gradient).getHeight() - 1 + iTranslateY, this.getWidth() / 4, 1);
        ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + this.getWidth() - 2 - this.getWidth() / 4 + iTranslateX, this.getMenuPosY() + this.getHeight() - ImageManager.getImage(Images.slider_gradient).getHeight() - 1 + iTranslateY, this.getWidth() / 4, 1, true, false);
        oSB.setColor(Color.WHITE);
        super.draw(oSB, iTranslateX, 1 + iTranslateY, sliderMenuIsActive);
        if (AoCGame.LEFT != 0) {
            oSB.setColor(CFG.COLOR_CREATE_NEW_GAME_BOX_PLAYERS);
            ImageManager.getImage(Images.pix255_255_255).draw2(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY, 1, this.getHeight(), true, false);
            oSB.setColor(Color.WHITE);
        }
    }

    @Override
    protected void drawCloseButton(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        this.getCloseButtonImage(sliderMenuIsActive).draw(oSB, this.getPosX() + this.getWidth() - ImageManager.getImage(Images.btn_close).getWidth() * 3 / 5 + iTranslateX, this.getPosY() - this.getTitle().getHeight() - ImageManager.getImage(Images.btn_close).getHeight() + iTranslateY, ImageManager.getImage(Images.btn_close).getWidth() * 3 / 5, ImageManager.getImage(Images.btn_close).getHeight() * 3 / 5);
    }

    private final float getImageScale(int nImageHeight) {
        return (float)CFG.TEXT_HEIGHT / (float)nImageHeight < 1.0f ? (float)CFG.TEXT_HEIGHT / (float)nImageHeight : 1.0f;
    }

    @Override
    protected void onHovered() {
        CFG.menuManager.setOrderOfMenu_InGame_CivInfo();
    }

    @Override
    protected void actionElement(int iID) {
        CFG.menuManager.setOrderOfMenu_InGame_CivInfo();
        switch (iID) {
            case 0: {
                if (CFG.menuManager.getVisibleInGame_Rank()) {
                    CFG.menuManager.setVisibleInGame_Rank(false);
                    break;
                }
                CFG.menuManager.rebuildInGame_Rank();
                break;
            }
            case 1: {
                Keyboard.changeCivilizationNameMode = CFG.getActiveCivInfo();
                CFG.updateKeyboard_Actions();
                CFG.showKeyboard();
                break;
            }
            case 2: {
                CFG.menuManager.getColorPicker().setPosX(CFG.GAME_WIDTH - CFG.menuManager.getColorPicker().getWidth() - CFG.PADDING * 4);
                CFG.menuManager.getColorPicker().setPosY(this.getPosY() + CFG.PADDING * 2);
                CFG.menuManager.getColorPicker().setVisible(!CFG.menuManager.getColorPicker().getVisible(), ColorPicker_AoC.PickerAction.ACTIVE_CIVILIZATION_COLOR);
                if (!CFG.menuManager.getColorPicker().getVisible()) break;
                CFG.viewsManager.disableAllViews();
                break;
            }
            case 3: {
                try {
                    if (CFG.game.getCiv(CFG.getActiveCivInfo()).getNumOfProvinces() <= 0) break;
                    Menu_InGame_CivilizationView.iCivID = CFG.getActiveCivInfo();
                    CFG.game.getPlayer((int)CFG.PLAYER_TURNID).iBefore_PosX = CFG.map.getMapCoordinates().getPosX();
                    CFG.game.getPlayer((int)CFG.PLAYER_TURNID).iBefore_PosY = CFG.map.getMapCoordinates().getPosY();
                    CFG.game.getPlayer((int)CFG.PLAYER_TURNID).fBefore_Scale = CFG.map.getMapScale().getCurrentScale();
                    CFG.game.getPlayer((int)CFG.PLAYER_TURNID).iBefore_ActiveProvince = CFG.game.getActiveProvinceID();
                    CFG.game.getPlayer((int)CFG.PLAYER_TURNID).iACTIVE_VIEW_MODE = CFG.viewsManager.getActiveViewID();
                    CFG.viewsManager.disableAllViews();
                    CFG.menuManager.setViewID(Menu.eINGAME_CIV_VIEW);
                    if (CFG.FOG_OF_WAR == 2) {
                        CFG.game.enableDrawCivilizationRegions_FogOfWar(Menu_InGame_CivilizationView.iCivID, 0);
                    } else {
                        CFG.game.enableDrawCivilizationRegions(Menu_InGame_CivilizationView.iCivID, 0);
                    }
                    CFG.map.getMapBG().updateWorldMap_Shaders();
                    CFG.toast.setInView(CFG.game.getCiv(Menu_InGame_CivilizationView.iCivID).getCivName(), CFG.COLOR_TEXT_NUM_OF_PROVINCES);
                    CFG.toast.setTimeInView(1500);
                }
                catch (IndexOutOfBoundsException ex) {
                    Menu_InGame_CivilizationView.iCivID = CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID();
                }
                break;
            }
            case 4: {
                if (CFG.game.getCiv((int)CFG.getActiveCivInfo()).civGameData.leaderData.getWiki().length() <= 0) break;
                CFG.EDITOR_ACTIVE_GAMEDATA_TAG = CFG.game.getCiv((int)CFG.getActiveCivInfo()).civGameData.leaderData.getWiki();
                CFG.setDialogType(Dialog.GO_TO_WIKI_SCENARIO);
            }
        }
    }

    @Override
    protected void setVisible(boolean visible) {
        if (visible) {
            super.setVisible(visible);
            this.setHideAnimation(false);
        } else {
            this.setHideAnimation(true);
        }
    }

    protected final void setHideAnimation(boolean hideAnimation) {
        if (hideAnimation != Menu_InGame_CivInfo.hideAnimation) {
            lTime = lTime > System.currentTimeMillis() - 175L ? System.currentTimeMillis() - (175L - (System.currentTimeMillis() - lTime)) : System.currentTimeMillis();
            CFG.setRender_3(true);
        }
        Menu_InGame_CivInfo.hideAnimation = hideAnimation;
    }
}

