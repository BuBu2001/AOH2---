/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Buildings;
import age.of.civilizations2.jakowski.lukasz.BuildingsManager;
import age.of.civilizations2.jakowski.lukasz.Button_NewGameStyle_Left;
import age.of.civilizations2.jakowski.lukasz.Button_NewGameStyle_Right;
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
import age.of.civilizations2.jakowski.lukasz.Text_Build;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.utils.ScissorStack;
import java.util.ArrayList;

class Menu_InGame_BuildConfirm
extends SliderMenu {
    protected static final int ANIMATION_TIME = 225;
    private long lTime = 0L;

    protected Menu_InGame_BuildConfirm() {
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        int tempWidth = (int)((float)CFG.BUTTON_WIDTH * 4.75f);
        int tPosY = CFG.PADDING;
        if (tempWidth > CFG.GAME_WIDTH) {
            tempWidth = CFG.GAME_WIDTH - CFG.PADDING * 4;
        }
        if (BuildingsManager.ACTIVE_BUILDING == Buildings.FORT) {
            menuElements.add(new Text_Build("+20% Defense bonus", CFG.PADDING * 2, tPosY){

                @Override
                protected Color getColor(boolean isActive) {
                    return CFG.COLOR_TEXT_MODIFIER_POSITIVE;
                }
            });
            menuElements.add(new Text_Build("+20% Defense bonus", CFG.PADDING * 2, tPosY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING){

                @Override
                protected Color getColor(boolean isActive) {
                    return CFG.COLOR_TEXT_MODIFIER_NEGATIVE;
                }
            });
            tPosY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
        } else if (BuildingsManager.ACTIVE_BUILDING == Buildings.WATCH_TOWER) {
            // empty if block
        }
        menuElements.add(new Button_NewGameStyle_Left(CFG.langManager.get("Cancel"), -1, CFG.PADDING, tPosY, tempWidth - CFG.PADDING * 2, CFG.BUTTON_HEIGHT / 2, true){

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
                return Menu_InGame_BuildConfirm.this.getW() / 2 - CFG.PADDING - CFG.PADDING / 2;
            }
        });
        menuElements.add(new Button_NewGameStyle_Right(CFG.langManager.get("Build"), -1, CFG.PADDING, tPosY, tempWidth - CFG.PADDING * 2, CFG.BUTTON_HEIGHT / 2, true){

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
            protected int getPosX() {
                return Menu_InGame_BuildConfirm.this.getW() / 2 + CFG.PADDING / 2;
            }

            @Override
            protected int getWidth() {
                return Menu_InGame_BuildConfirm.this.getW() / 2 - CFG.PADDING;
            }
        });
        this.initMenu(new SliderMenuTitle(CFG.langManager.get("Build"), CFG.BUTTON_HEIGHT * 3 / 5, true, true){

            @Override
            protected void draw(SpriteBatch oSB, int iTranslateX, int nPosX, int nPosY, int nWidth, boolean sliderMenuIsActive) {
                ImageManager.getImage(Images.new_game_top_edge_title).draw2(oSB, nPosX + iTranslateX, nPosY - ImageManager.getImage(Images.new_game_top_edge_title).getHeight() - this.getHeight(), nWidth - ImageManager.getImage(Images.new_game_top_edge_title).getWidth(), this.getHeight());
                ImageManager.getImage(Images.new_game_top_edge_title).draw2(oSB, nPosX + nWidth - ImageManager.getImage(Images.new_game_top_edge_title).getWidth() + iTranslateX, nPosY - ImageManager.getImage(Images.new_game_top_edge_title).getHeight() - this.getHeight(), ImageManager.getImage(Images.new_game_top_edge_title).getWidth(), this.getHeight(), true);
                oSB.setColor(new Color(0.09803922f, 0.05882353f, 0.37254903f, 0.55f));
                ImageManager.getImage(Images.gradient).draw(oSB, Menu_InGame_BuildConfirm.this.getPosX() + 2 + iTranslateX, Menu_InGame_BuildConfirm.this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() - this.getHeight() * 3 / 5, Menu_InGame_BuildConfirm.this.getWidth() - 4, this.getHeight() * 3 / 5, false, true);
                oSB.setColor(new Color(CFG.COLOR_GRADIENT_TITLE_BLUE));
                ImageManager.getImage(Images.gradient).draw(oSB, Menu_InGame_BuildConfirm.this.getPosX() + 2 + iTranslateX, Menu_InGame_BuildConfirm.this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() - this.getHeight() / 4, Menu_InGame_BuildConfirm.this.getWidth() - 4, this.getHeight() / 4, false, true);
                oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.4f));
                ImageManager.getImage(Images.gradient).draw(oSB, Menu_InGame_BuildConfirm.this.getPosX() + 2 + iTranslateX, Menu_InGame_BuildConfirm.this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() - CFG.PADDING * 2, Menu_InGame_BuildConfirm.this.getWidth() - 4, CFG.PADDING * 2, false, true);
                oSB.setColor(new Color(0.451f, 0.329f, 0.11f, 1.0f));
                ImageManager.getImage(Images.pix255_255_255).draw(oSB, Menu_InGame_BuildConfirm.this.getPosX() + 2 + iTranslateX, Menu_InGame_BuildConfirm.this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight(), Menu_InGame_BuildConfirm.this.getWidth() - 4);
                oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.4f));
                ImageManager.getImage(Images.line_32_off1).draw(oSB, Menu_InGame_BuildConfirm.this.getPosX() + 2 + iTranslateX, Menu_InGame_BuildConfirm.this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() - ImageManager.getImage(Images.line_32_off1).getHeight(), Menu_InGame_BuildConfirm.this.getWidth() - 4, 1);
                oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.8f));
                ImageManager.getImage(Images.slider_gradient).draw(oSB, Menu_InGame_BuildConfirm.this.getPosX() + 2 + iTranslateX, Menu_InGame_BuildConfirm.this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() - ImageManager.getImage(Images.slider_gradient).getHeight(), Menu_InGame_BuildConfirm.this.getWidth() / 4 - 4, 1);
                ImageManager.getImage(Images.slider_gradient).draw(oSB, Menu_InGame_BuildConfirm.this.getPosX() + 2 + Menu_InGame_BuildConfirm.this.getWidth() - Menu_InGame_BuildConfirm.this.getWidth() / 4 + iTranslateX, Menu_InGame_BuildConfirm.this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() - ImageManager.getImage(Images.slider_gradient).getHeight(), Menu_InGame_BuildConfirm.this.getWidth() / 4 - 4, 1, true, false);
                oSB.setColor(Color.WHITE);
                CFG.fontMain.getData().setScale(0.8f);
                CFG.drawText(oSB, this.getText(), nPosX + nWidth / 2 - (int)((float)this.getTextWidth() * 0.8f / 2.0f) + iTranslateX, nPosY - this.getHeight() + this.getHeight() / 2 + 1 - (int)((float)this.getTextHeight() * 0.8f / 2.0f), Color.WHITE);
                CFG.fontMain.getData().setScale(1.0f);
            }
        }, CFG.GAME_WIDTH / 2 - tempWidth / 2, ImageManager.getImage(Images.top_left).getHeight() + CFG.PADDING * 2 + CFG.BUTTON_HEIGHT * 3 / 5, tempWidth, tPosY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING, menuElements, false, true);
    }

    @Override
    protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        if (this.lTime + 225L >= System.currentTimeMillis()) {
            Rectangle clipBounds = new Rectangle(this.getPosX(), CFG.GAME_HEIGHT - this.getPosY(), this.getWidth(), -((int)((float)this.getHeight() * ((float)(System.currentTimeMillis() - this.lTime) / 225.0f))));
            oSB.flush();
            ScissorStack.pushScissors(clipBounds);
            CFG.setRender_3(true);
        }
        ImageManager.getImage(Images.new_game_top_edge).draw2(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.new_game_top_edge).getHeight() + iTranslateY, this.getWidth() - ImageManager.getImage(Images.new_game_top_edge).getWidth(), this.getHeight(), false, true);
        ImageManager.getImage(Images.new_game_top_edge).draw2(oSB, this.getPosX() + this.getWidth() - ImageManager.getImage(Images.new_game_top_edge).getWidth() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.new_game_top_edge).getHeight() + iTranslateY, ImageManager.getImage(Images.new_game_top_edge).getWidth(), this.getHeight(), true, true);
        this.beginClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
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

