/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.AoCGame;
import age.of.civilizations2.jakowski.lukasz.Button_NewGameStyle;
import age.of.civilizations2.jakowski.lukasz.Button_NewGameStyle_Left;
import age.of.civilizations2.jakowski.lukasz.Button_NewGameStyle_Middle;
import age.of.civilizations2.jakowski.lukasz.Button_NewGameStyle_Right;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.ColorPicker_AoC;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.Slider;
import age.of.civilizations2.jakowski.lukasz.SliderMenu;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

class Menu_CreateCiv_Overlay
extends SliderMenu {
    protected Menu_CreateCiv_Overlay() {
        int tempW = CFG.CIV_INFO_MENU_WIDTH + CFG.CIV_INFO_MENU_WIDTH * 3 / 4;
        int tempH = ImageManager.getImage(Images.top_flag_frame).getHeight() + CFG.PADDING * 4;
        int tPosY = CFG.PADDING;
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        menuElements.add(new Button_NewGameStyle(null, -1, CFG.PADDING, tPosY, tempW - CFG.PADDING * 2, (int)((float)CFG.BUTTON_HEIGHT * 0.6f), true));
        menuElements.add(new Button_NewGameStyle_Middle("", 0, CFG.PADDING + CFG.BUTTON_HEIGHT, tPosY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING, tempW - CFG.PADDING * 2 - CFG.BUTTON_HEIGHT * 2, Math.max(ImageManager.getImage(Images.top_flag_frame).getHeight() + CFG.PADDING * 2, (int)((float)CFG.BUTTON_HEIGHT * 0.6f)), true){

            @Override
            protected void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                CFG.flagManager.drawFlag_FlagFrameSize(oSB, this.getPosX() + this.getWidth() / 2 - ImageManager.getImage(Images.top_flag_frame).getWidth() / 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - ImageManager.getImage(Images.top_flag_frame).getHeight() / 2 + iTranslateY);
            }
        });
        menuElements.add(new Button_NewGameStyle_Left("<<", -1, CFG.PADDING, tPosY, CFG.BUTTON_HEIGHT, Math.max(ImageManager.getImage(Images.top_flag_frame).getHeight() + CFG.PADDING * 2, (int)((float)CFG.BUTTON_HEIGHT * 0.6f)), true));
        menuElements.add(new Button_NewGameStyle_Right(">>", -1, tempW - CFG.PADDING - CFG.BUTTON_HEIGHT, tPosY, CFG.BUTTON_HEIGHT, Math.max(ImageManager.getImage(Images.top_flag_frame).getHeight() + CFG.PADDING * 2, (int)((float)CFG.BUTTON_HEIGHT * 0.6f)), true));
        menuElements.add(new Slider("X: ", CFG.PADDING + CFG.BUTTON_HEIGHT + CFG.BUTTON_HEIGHT / 2, tPosY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING, tempW - CFG.PADDING * 2 - CFG.BUTTON_HEIGHT * 3, (int)((float)CFG.BUTTON_HEIGHT * 0.7f), -ImageManager.getImage(Images.top_flag_frame).getWidth(), ImageManager.getImage(Images.top_flag_frame).getWidth(), 0));
        menuElements.add(new Button_NewGameStyle_Left("-", -1, CFG.PADDING, tPosY, CFG.BUTTON_HEIGHT + CFG.BUTTON_HEIGHT / 2, (int)((float)CFG.BUTTON_HEIGHT * 0.7f), true));
        menuElements.add(new Button_NewGameStyle_Right("+", -1, tempW - CFG.PADDING - CFG.BUTTON_HEIGHT - CFG.BUTTON_HEIGHT / 2, tPosY, CFG.BUTTON_HEIGHT + CFG.BUTTON_HEIGHT / 2, (int)((float)CFG.BUTTON_HEIGHT * 0.7f), true));
        menuElements.add(new Slider("Y: ", CFG.PADDING + CFG.BUTTON_HEIGHT + CFG.BUTTON_HEIGHT / 2, tPosY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING, tempW - CFG.PADDING * 2 - CFG.BUTTON_HEIGHT * 3, (int)((float)CFG.BUTTON_HEIGHT * 0.7f), -ImageManager.getImage(Images.top_flag_frame).getHeight(), ImageManager.getImage(Images.top_flag_frame).getHeight(), 0));
        menuElements.add(new Button_NewGameStyle_Left("-", -1, CFG.PADDING, tPosY, CFG.BUTTON_HEIGHT + CFG.BUTTON_HEIGHT / 2, (int)((float)CFG.BUTTON_HEIGHT * 0.7f), true));
        menuElements.add(new Button_NewGameStyle_Right("+", -1, tempW - CFG.PADDING - CFG.BUTTON_HEIGHT - CFG.BUTTON_HEIGHT / 2, tPosY, CFG.BUTTON_HEIGHT + CFG.BUTTON_HEIGHT / 2, (int)((float)CFG.BUTTON_HEIGHT * 0.7f), true));
        menuElements.add(new Slider("", CFG.PADDING + CFG.BUTTON_HEIGHT + CFG.BUTTON_HEIGHT / 2, tPosY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING, tempW - CFG.PADDING * 2 - CFG.BUTTON_HEIGHT * 3, (int)((float)CFG.BUTTON_HEIGHT * 0.7f), 1, ImageManager.getImage(Images.top_flag_frame).getWidth(), 1));
        menuElements.add(new Button_NewGameStyle_Left("-", -1, CFG.PADDING, tPosY, CFG.BUTTON_HEIGHT + CFG.BUTTON_HEIGHT / 2, (int)((float)CFG.BUTTON_HEIGHT * 0.7f), true));
        menuElements.add(new Button_NewGameStyle_Right("+", -1, tempW - CFG.PADDING - CFG.BUTTON_HEIGHT - CFG.BUTTON_HEIGHT / 2, tPosY, CFG.BUTTON_HEIGHT + CFG.BUTTON_HEIGHT / 2, (int)((float)CFG.BUTTON_HEIGHT * 0.7f), true));
        menuElements.add(new Slider("", CFG.PADDING + CFG.BUTTON_HEIGHT + CFG.BUTTON_HEIGHT / 2, tPosY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING, tempW - CFG.PADDING * 2 - CFG.BUTTON_HEIGHT * 3, (int)((float)CFG.BUTTON_HEIGHT * 0.7f), 1, ImageManager.getImage(Images.top_flag_frame).getHeight(), 1));
        menuElements.add(new Button_NewGameStyle_Left("-", -1, CFG.PADDING, tPosY, CFG.BUTTON_HEIGHT + CFG.BUTTON_HEIGHT / 2, (int)((float)CFG.BUTTON_HEIGHT * 0.7f), true));
        menuElements.add(new Button_NewGameStyle_Right("+", -1, tempW - CFG.PADDING - CFG.BUTTON_HEIGHT - CFG.BUTTON_HEIGHT / 2, tPosY, CFG.BUTTON_HEIGHT + CFG.BUTTON_HEIGHT / 2, (int)((float)CFG.BUTTON_HEIGHT * 0.7f), true));
        menuElements.add(new Slider("", CFG.PADDING, tPosY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING, tempW - CFG.PADDING * 2, (int)((float)CFG.BUTTON_HEIGHT * 0.7f), 1, 100, 50){

            @Override
            protected String getDrawText() {
                return super.getDrawText() + "%";
            }
        });
        menuElements.add(new Button_NewGameStyle(CFG.langManager.get("Color"), -1, CFG.PADDING, tPosY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING, tempW - CFG.PADDING * 2, (int)((float)CFG.BUTTON_HEIGHT * 0.6f), true){

            @Override
            protected void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                super.drawText(oSB, iTranslateX, iTranslateY, isActive);
                oSB.setColor(CFG.flagManager.flagEdit.lOverlays.get((int)CFG.EDIT_ALLIANCE_NAMES_BUNDLE_ID).oColor.getR(), CFG.flagManager.flagEdit.lOverlays.get((int)CFG.EDIT_ALLIANCE_NAMES_BUNDLE_ID).oColor.getG(), CFG.flagManager.flagEdit.lOverlays.get((int)CFG.EDIT_ALLIANCE_NAMES_BUNDLE_ID).oColor.getB(), 1.0f);
                ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + this.getWidth() / 2 - (int)((float)this.getTextWidth() * 0.8f / 2.0f) + iTranslateX, this.getPosY() + Menu_CreateCiv_Overlay.this.getMenuPosY() + this.getHeight() / 2 + this.getTextHeight() / 2 + CFG.CIV_COLOR_WIDTH, CFG.PADDING, CFG.CIV_COLOR_WIDTH, true, false);
                ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() + this.getWidth() / 2 - (int)((float)this.getTextWidth() * 0.8f / 2.0f) + CFG.PADDING + iTranslateX, this.getPosY() + Menu_CreateCiv_Overlay.this.getMenuPosY() + this.getHeight() / 2 + this.getTextHeight() / 2 + CFG.CIV_COLOR_WIDTH, (int)((float)this.getTextWidth() * 0.8f) - CFG.PADDING * 2, CFG.CIV_COLOR_WIDTH);
                ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + this.getWidth() / 2 - (int)((float)this.getTextWidth() * 0.8f / 2.0f) + (int)((float)this.getTextWidth() * 0.8f) - CFG.PADDING + iTranslateX, this.getPosY() + Menu_CreateCiv_Overlay.this.getMenuPosY() + this.getHeight() / 2 + this.getTextHeight() / 2 + CFG.CIV_COLOR_WIDTH, CFG.PADDING, CFG.CIV_COLOR_WIDTH);
                oSB.setColor(Color.WHITE);
            }
        });
        menuElements.add(new Button_NewGameStyle_Left(CFG.langManager.get("CenterX", "X"), -1, CFG.PADDING, tPosY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING, (tempW - CFG.PADDING * 2) / 2, (int)((float)CFG.BUTTON_HEIGHT * 0.6f), true));
        menuElements.add(new Button_NewGameStyle_Right(CFG.langManager.get("CenterX", "Y"), -1, tempW - CFG.PADDING * 2 - (tempW - CFG.PADDING * 2) / 2, tPosY, (tempW - CFG.PADDING * 2) / 2, (int)((float)CFG.BUTTON_HEIGHT * 0.6f), true));
        this.initMenu(null, 0 + AoCGame.LEFT, CFG.BUTTON_HEIGHT / 2 + (ImageManager.getImage(Images.top_flag_frame).getHeight() + CFG.PADDING * 4), tempW, Math.min((tPosY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING) + CFG.PADDING, CFG.GAME_HEIGHT - (CFG.BUTTON_HEIGHT / 2 + (ImageManager.getImage(Images.top_flag_frame).getHeight() + CFG.PADDING * 4) + CFG.PADDING)), menuElements);
        this.setVisible(false);
        this.updateLanguage();
    }

    @Override
    protected void updateLanguage() {
        this.getMenuElement(0).setText(CFG.langManager.get("Back"));
        try {
            this.getMenuElement(4).setText("X: ");
            this.getMenuElement(4).setCurrent(CFG.flagManager.flagEdit.lOverlays.get((int)CFG.EDIT_ALLIANCE_NAMES_BUNDLE_ID).iPosX);
            this.getMenuElement(7).setText("Y: ");
            this.getMenuElement(7).setCurrent(CFG.flagManager.flagEdit.lOverlays.get((int)CFG.EDIT_ALLIANCE_NAMES_BUNDLE_ID).iPosY);
            this.getMenuElement(10).setText(CFG.langManager.get("Width") + ": ");
            this.getMenuElement(10).setCurrent(CFG.flagManager.flagEdit.lOverlays.get((int)CFG.EDIT_ALLIANCE_NAMES_BUNDLE_ID).iWidth);
            this.getMenuElement(13).setText(CFG.langManager.get("Height") + ": ");
            this.getMenuElement(13).setCurrent(CFG.flagManager.flagEdit.lOverlays.get((int)CFG.EDIT_ALLIANCE_NAMES_BUNDLE_ID).iHeight);
            this.getMenuElement(16).setText(CFG.langManager.get("Scale") + ": ");
            this.updateScale();
        }
        catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            // empty catch block
        }
    }

    private final void updateScale() {
        this.getMenuElement(16).setCurrent((int)((float)CFG.flagManager.flagEdit.lOverlays.get((int)CFG.EDIT_ALLIANCE_NAMES_BUNDLE_ID).iHeight / (float)CFG.flagManager.getOverlay(CFG.flagManager.flagEdit.lOverlays.get((int)CFG.EDIT_ALLIANCE_NAMES_BUNDLE_ID).iOverlayID).getHeight() * 100.0f));
    }

    @Override
    protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        ImageManager.getImage(Images.new_game_top_edge_line).draw2(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.new_game_top_edge_line).getHeight(), this.getWidth() + 2, this.getHeight(), true, false);
        oSB.setColor(new Color(0.011f, 0.014f, 0.019f, 0.25f));
        ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight(), this.getWidth() * 3 / 4, this.getHeight(), false, true);
        oSB.setColor(Color.WHITE);
        super.beginClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        super.drawMenu(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        super.endClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        oSB.setColor(new Color(0.451f, 0.329f, 0.11f, 1.0f));
        ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - ImageManager.getImage(Images.pix255_255_255).getHeight(), this.getWidth());
        oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.4f));
        ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - ImageManager.getImage(Images.pix255_255_255).getHeight() - ImageManager.getImage(Images.slider_gradient).getHeight(), this.getWidth(), 1);
        oSB.setColor(Color.WHITE);
    }

    @Override
    protected final void actionElement(int iID) {
        switch (iID) {
            case 0: {
                CFG.menuManager.setVisibleCreateCiv_Overlay(false);
                CFG.menuManager.rebuildCreateCiv_Flag();
                CFG.menuManager.getColorPicker().setVisible(false, null);
                return;
            }
            case 2: {
                CFG.flagManager.updateOverlay(CFG.EDIT_ALLIANCE_NAMES_BUNDLE_ID, false);
                this.updateLanguage();
                return;
            }
            case 3: {
                CFG.flagManager.updateOverlay(CFG.EDIT_ALLIANCE_NAMES_BUNDLE_ID, true);
                this.updateLanguage();
                return;
            }
            case 4: {
                CFG.flagManager.flagEdit.lOverlays.get((int)CFG.EDIT_ALLIANCE_NAMES_BUNDLE_ID).iPosX = this.getMenuElement(iID).getCurrent();
                return;
            }
            case 5: {
                --CFG.flagManager.flagEdit.lOverlays.get((int)CFG.EDIT_ALLIANCE_NAMES_BUNDLE_ID).iPosX;
                this.getMenuElement(4).setCurrent(CFG.flagManager.flagEdit.lOverlays.get((int)CFG.EDIT_ALLIANCE_NAMES_BUNDLE_ID).iPosX);
                return;
            }
            case 6: {
                ++CFG.flagManager.flagEdit.lOverlays.get((int)CFG.EDIT_ALLIANCE_NAMES_BUNDLE_ID).iPosX;
                this.getMenuElement(4).setCurrent(CFG.flagManager.flagEdit.lOverlays.get((int)CFG.EDIT_ALLIANCE_NAMES_BUNDLE_ID).iPosX);
                return;
            }
            case 7: {
                CFG.flagManager.flagEdit.lOverlays.get((int)CFG.EDIT_ALLIANCE_NAMES_BUNDLE_ID).iPosY = this.getMenuElement(iID).getCurrent();
                return;
            }
            case 8: {
                --CFG.flagManager.flagEdit.lOverlays.get((int)CFG.EDIT_ALLIANCE_NAMES_BUNDLE_ID).iPosY;
                this.getMenuElement(7).setCurrent(CFG.flagManager.flagEdit.lOverlays.get((int)CFG.EDIT_ALLIANCE_NAMES_BUNDLE_ID).iPosY);
                return;
            }
            case 9: {
                ++CFG.flagManager.flagEdit.lOverlays.get((int)CFG.EDIT_ALLIANCE_NAMES_BUNDLE_ID).iPosY;
                this.getMenuElement(7).setCurrent(CFG.flagManager.flagEdit.lOverlays.get((int)CFG.EDIT_ALLIANCE_NAMES_BUNDLE_ID).iPosY);
                return;
            }
            case 10: {
                CFG.flagManager.flagEdit.lOverlays.get((int)CFG.EDIT_ALLIANCE_NAMES_BUNDLE_ID).iWidth = this.getMenuElement(iID).getCurrent();
                this.updateScale();
                return;
            }
            case 11: {
                --CFG.flagManager.flagEdit.lOverlays.get((int)CFG.EDIT_ALLIANCE_NAMES_BUNDLE_ID).iWidth;
                this.getMenuElement(10).setCurrent(CFG.flagManager.flagEdit.lOverlays.get((int)CFG.EDIT_ALLIANCE_NAMES_BUNDLE_ID).iWidth);
                this.updateScale();
                return;
            }
            case 12: {
                ++CFG.flagManager.flagEdit.lOverlays.get((int)CFG.EDIT_ALLIANCE_NAMES_BUNDLE_ID).iWidth;
                this.getMenuElement(10).setCurrent(CFG.flagManager.flagEdit.lOverlays.get((int)CFG.EDIT_ALLIANCE_NAMES_BUNDLE_ID).iWidth);
                this.updateScale();
                return;
            }
            case 13: {
                CFG.flagManager.flagEdit.lOverlays.get((int)CFG.EDIT_ALLIANCE_NAMES_BUNDLE_ID).iHeight = this.getMenuElement(iID).getCurrent();
                return;
            }
            case 14: {
                --CFG.flagManager.flagEdit.lOverlays.get((int)CFG.EDIT_ALLIANCE_NAMES_BUNDLE_ID).iHeight;
                this.getMenuElement(13).setCurrent(CFG.flagManager.flagEdit.lOverlays.get((int)CFG.EDIT_ALLIANCE_NAMES_BUNDLE_ID).iHeight);
                return;
            }
            case 15: {
                ++CFG.flagManager.flagEdit.lOverlays.get((int)CFG.EDIT_ALLIANCE_NAMES_BUNDLE_ID).iHeight;
                this.getMenuElement(13).setCurrent(CFG.flagManager.flagEdit.lOverlays.get((int)CFG.EDIT_ALLIANCE_NAMES_BUNDLE_ID).iHeight);
                return;
            }
            case 16: {
                CFG.flagManager.flagEdit.lOverlays.get((int)CFG.EDIT_ALLIANCE_NAMES_BUNDLE_ID).iWidth = (int)Math.ceil((float)(CFG.flagManager.getOverlay(CFG.flagManager.flagEdit.lOverlays.get((int)CFG.EDIT_ALLIANCE_NAMES_BUNDLE_ID).iOverlayID).getWidth() * this.getMenuElement(iID).getCurrent()) / 100.0f);
                CFG.flagManager.flagEdit.lOverlays.get((int)CFG.EDIT_ALLIANCE_NAMES_BUNDLE_ID).iHeight = (int)Math.ceil((float)(CFG.flagManager.getOverlay(CFG.flagManager.flagEdit.lOverlays.get((int)CFG.EDIT_ALLIANCE_NAMES_BUNDLE_ID).iOverlayID).getHeight() * this.getMenuElement(iID).getCurrent()) / 100.0f);
                this.getMenuElement(10).setCurrent(CFG.flagManager.flagEdit.lOverlays.get((int)CFG.EDIT_ALLIANCE_NAMES_BUNDLE_ID).iWidth);
                this.getMenuElement(13).setCurrent(CFG.flagManager.flagEdit.lOverlays.get((int)CFG.EDIT_ALLIANCE_NAMES_BUNDLE_ID).iHeight);
                return;
            }
            case 17: {
                if (CFG.menuManager.getColorPicker().getVisible()) {
                    CFG.menuManager.getColorPicker().setVisible(false, null);
                } else {
                    CFG.menuManager.getColorPicker().setActiveRGBColor(CFG.flagManager.flagEdit.lOverlays.get((int)CFG.EDIT_ALLIANCE_NAMES_BUNDLE_ID).oColor.getR(), CFG.flagManager.flagEdit.lOverlays.get((int)CFG.EDIT_ALLIANCE_NAMES_BUNDLE_ID).oColor.getG(), CFG.flagManager.flagEdit.lOverlays.get((int)CFG.EDIT_ALLIANCE_NAMES_BUNDLE_ID).oColor.getB());
                    CFG.menuManager.getColorPicker().setVisible(true, ColorPicker_AoC.PickerAction.EDITOR_CIV_FLAG_OVERLAY_COLOR);
                }
                return;
            }
            case 18: {
                CFG.flagManager.flagEdit.lOverlays.get((int)CFG.EDIT_ALLIANCE_NAMES_BUNDLE_ID).iPosX = (68 - CFG.flagManager.flagEdit.lOverlays.get((int)CFG.EDIT_ALLIANCE_NAMES_BUNDLE_ID).iWidth) / 2;
                return;
            }
            case 19: {
                CFG.flagManager.flagEdit.lOverlays.get((int)CFG.EDIT_ALLIANCE_NAMES_BUNDLE_ID).iPosY = (44 - CFG.flagManager.flagEdit.lOverlays.get((int)CFG.EDIT_ALLIANCE_NAMES_BUNDLE_ID).iHeight) / 2;
                return;
            }
        }
    }
}

