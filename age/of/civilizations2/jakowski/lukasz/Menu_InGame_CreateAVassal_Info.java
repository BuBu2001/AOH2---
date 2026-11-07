/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.AoCGame;
import age.of.civilizations2.jakowski.lukasz.Button_FlagFrame;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.ColorPicker_AoC;
import age.of.civilizations2.jakowski.lukasz.Image;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Image;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Text;
import age.of.civilizations2.jakowski.lukasz.SliderMenu;
import age.of.civilizations2.jakowski.lukasz.SliderMenuTitle;
import age.of.civilizations2.jakowski.lukasz.Text;
import age.of.civilizations2.jakowski.lukasz.Text_Scrollable;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

class Menu_InGame_CreateAVassal_Info
extends SliderMenu {
    protected static boolean hideAnimation = true;

    protected Menu_InGame_CreateAVassal_Info() {
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        menuElements.add(new Text_Scrollable(null, ImageManager.getImage(Images.top_flag_frame).getWidth() + CFG.PADDING * 4, CFG.PADDING * 3, CFG.CIV_INFO_MENU_WIDTH - ImageManager.getImage(Images.top_flag_frame).getWidth() - CFG.PADDING * 5, CFG.COLOR_TEXT_CIV_NAME){

            @Override
            protected Color getColor(boolean isActive) {
                return isActive ? CFG.COLOR_TEXT_CIV_NAME_ACTIVE : (this.getClickable() ? (this.getIsHovered() ? CFG.COLOR_TEXT_CIV_NAME_HOVERED : CFG.COLOR_TEXT_CIV_NAME) : new Color(0.78f, 0.78f, 0.78f, 0.7f));
            }

            @Override
            protected void buildElementHover() {
                ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("SelectVassal"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }

            @Override
            protected void draw_Element(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
                super.draw_Element(oSB, iTranslateX + CFG.PADDING + (int)((float)ImageManager.getImage(Images.flag_rect).getWidth() * Menu_InGame_CreateAVassal_Info.this.getImageScale(ImageManager.getImage(Images.flag_rect).getHeight())), iTranslateY, isActive, scrollableY);
                try {
                    CFG.createVassal_Data.getFlagOfCiv().draw(oSB, this.getPosX() + this.getCurrent() + iTranslateX, this.getPosY() - CFG.createVassal_Data.getFlagOfCiv().getHeight() + (this.getHeight() - (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * Menu_InGame_CreateAVassal_Info.this.getImageScale(ImageManager.getImage(Images.flag_rect).getHeight()))) / 2 + iTranslateY, (int)((float)ImageManager.getImage(Images.flag_rect).getWidth() * Menu_InGame_CreateAVassal_Info.this.getImageScale(ImageManager.getImage(Images.flag_rect).getHeight())), (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * Menu_InGame_CreateAVassal_Info.this.getImageScale(ImageManager.getImage(Images.flag_rect).getHeight())));
                }
                catch (NullPointerException ex) {
                    ImageManager.getImage(Images.randomCivilizationFlag).draw(oSB, this.getPosX() + this.getCurrent() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.randomCivilizationFlag).getHeight() + (this.getHeight() - (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * Menu_InGame_CreateAVassal_Info.this.getImageScale(ImageManager.getImage(Images.flag_rect).getHeight()))) / 2 + iTranslateY, (int)((float)ImageManager.getImage(Images.flag_rect).getWidth() * Menu_InGame_CreateAVassal_Info.this.getImageScale(ImageManager.getImage(Images.flag_rect).getHeight())), (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * Menu_InGame_CreateAVassal_Info.this.getImageScale(ImageManager.getImage(Images.flag_rect).getHeight())));
                }
                ImageManager.getImage(Images.flag_rect).draw(oSB, this.getPosX() + this.getCurrent() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.flag_rect).getHeight() + (int)((float)this.getHeight() - (float)ImageManager.getImage(Images.flag_rect).getHeight() * Menu_InGame_CreateAVassal_Info.this.getImageScale(ImageManager.getImage(Images.flag_rect).getHeight())) / 2 + iTranslateY, (int)((float)ImageManager.getImage(Images.flag_rect).getWidth() * Menu_InGame_CreateAVassal_Info.this.getImageScale(ImageManager.getImage(Images.flag_rect).getHeight())), (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * Menu_InGame_CreateAVassal_Info.this.getImageScale(ImageManager.getImage(Images.flag_rect).getHeight())));
            }

            @Override
            protected int getTextWidth() {
                try {
                    return super.getTextWidth() + CFG.PADDING + (int)((float)ImageManager.getImage(Images.flag_rect).getWidth() * Menu_InGame_CreateAVassal_Info.this.getImageScale(ImageManager.getImage(Images.flag_rect).getHeight()));
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
                this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }

            @Override
            protected Image getFlag() {
                try {
                    if (CFG.createVassal_Data.getFlagOfCivH() != null) {
                        return CFG.createVassal_Data.getFlagOfCivH();
                    }
                    return ImageManager.getImage(Images.randomCivilizationFlag);
                }
                catch (NullPointerException ex) {
                    return ImageManager.getImage(Images.randomCivilizationFlag);
                }
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
                CFG.drawTextWithShadow(oSB, this.sText, this.getPosX() + iTranslateX, this.getPosY() + (int)((float)this.getHeight() - (float)this.getTextHeight() * 0.8f) / 2 + iTranslateY, this.getColor(isActive));
                CFG.drawTextWithShadow(oSB, "" + this.getCurrent(), this.getPosX() + (int)((float)this.getTextWidth() * 0.8f) + iTranslateX, this.getPosY() + (int)((float)this.getHeight() - (float)this.getTextHeight() * 0.8f) / 2 + iTranslateY, CFG.COLOR_TEXT_NUM_OF_PROVINCES);
                CFG.fontMain.getData().setScale(1.0f);
            }

            @Override
            protected Color getColor(boolean isActive) {
                return isActive ? CFG.COLOR_TEXT_RANK_ACTIVE : (this.getClickable() ? (this.getIsHovered() ? CFG.COLOR_TEXT_RANK_HOVER : CFG.COLOR_TEXT_RANK) : new Color(0.78f, 0.78f, 0.78f, 0.7f));
            }

            @Override
            protected void buildElementHover() {
                ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Provinces") + ": "));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + CFG.game.getSelectedProvinces().getProvincesSize(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }

            @Override
            protected int getCurrent() {
                return this.iCurrent;
            }

            @Override
            protected void setCurrent(int nCurrent) {
                this.iCurrent = nCurrent;
            }
        });
        this.initMenu(new SliderMenuTitle("", 0, false, false), 0 + AoCGame.LEFT, CFG.BUTTON_HEIGHT + CFG.PADDING * 3, CFG.CIV_INFO_MENU_WIDTH, ImageManager.getImage(Images.top_flag_frame).getHeight() + CFG.PADDING * 4, menuElements, true, true);
        this.updateLanguage();
    }

    @Override
    protected void updateLanguage() {
        try {
            this.getMenuElement(0).setText(CFG.createVassal_Data.sCivTag == null ? CFG.langManager.get("SelectCivilization") : CFG.langManager.getCiv(CFG.createVassal_Data.sCivTag));
        }
        catch (IndexOutOfBoundsException ex) {
            this.getMenuElement(0).setText(CFG.langManager.get("SelectCivilization"));
        }
        catch (NullPointerException ex) {
            this.getMenuElement(0).setText(CFG.langManager.get("SelectCivilization"));
        }
        this.getMenuElement(2).setText(CFG.langManager.get("Provinces") + ": ");
        this.getMenuElement(2).setCurrent(CFG.game.getSelectedProvinces().getProvincesSize());
        this.getMenuElement(0).setPosY(this.getMenuElement(1).getPosY() + this.getMenuElement(1).getHeight() / 2 - (int)(((float)CFG.TEXT_HEIGHT + (float)CFG.TEXT_HEIGHT * 0.8f + (float)(CFG.PADDING * 2)) / 2.0f));
        this.getMenuElement(2).setPosY(this.getMenuElement(0).getPosY() + CFG.TEXT_HEIGHT + CFG.PADDING);
    }

    @Override
    protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        ImageManager.getImage(Images.new_game_top_edge).draw2(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.new_game_top_edge).getHeight() + iTranslateY, this.getWidth(), this.getHeight(), true, false);
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
    protected void actionElement(int iID) {
        CFG.menuManager.setOrderOfMenu_InGame_CreateAVassal_Info();
        switch (iID) {
            case 0: 
            case 2: {
                CFG.menuManager.setVisible_InGame_CreateVassal_Civs(!CFG.menuManager.getVisible_InGame_CreateVassal_Civs());
                break;
            }
            case 1: {
                CFG.menuManager.getColorPicker().setPosX(CFG.GAME_WIDTH - CFG.menuManager.getColorPicker().getWidth() - CFG.PADDING * 4);
                CFG.menuManager.getColorPicker().setPosY(this.getPosY() + CFG.PADDING * 2);
                CFG.menuManager.getColorPicker().setVisible(!CFG.menuManager.getColorPicker().getVisible(), ColorPicker_AoC.PickerAction.CREATE_VASSAL_COLOR);
                if (!CFG.menuManager.getColorPicker().getVisible()) break;
                CFG.viewsManager.disableAllViews();
            }
        }
    }
}

