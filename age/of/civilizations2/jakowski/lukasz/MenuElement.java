/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.GraphData;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover;
import age.of.civilizations2.jakowski.lukasz.SoundsManager;
import age.of.civilizations2.jakowski.lukasz.Touch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.List;

class MenuElement {
    protected TypeOfElement typeOfElement;
    private int iPosX;
    private int iPosY;
    private int iWidth;
    private int iHeight;
    private boolean isClickable = true;
    private boolean isVisible = true;
    private boolean isInView = false;
    private boolean isHovered = false;
    protected MenuElement_Hover menuElementHover;

    protected void buildElementHover() {
    }

    protected void resetElementHover() {
        this.menuElementHover = null;
    }

    protected void drawMenuElementHover2(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
        if (this.menuElementHover != null) {
            if (CFG.isAndroid()) {
                this.menuElementHover.drawAlwaysOver_Mobile(oSB, Touch.getMousePosX(), Touch.getMousePosY() - CFG.menuManager.getHover_ExtraPosY());
            } else {
                this.menuElementHover.draw(oSB, Touch.getMousePosX() + CFG.menuManager.getHover_ExtraPosX(), Touch.getMousePosY() + CFG.menuManager.getHover_ExtraPosY());
            }
        }
    }

    protected boolean getMenuElement_Hover_IsNull() {
        return this.menuElementHover == null;
    }

    protected void updateHover(int nPosX, int nPosY, int menuPosX, int menuPosY) {
    }

    protected MenuElement() {
    }

    protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY) {
    }

    protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
    }

    protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, int flagPixelID) {
    }

    protected void setText(String sText) {
    }

    protected boolean getCheckboxState() {
        return false;
    }

    protected void setCheckboxState(boolean checkboxState) {
    }

    protected void updateSlider(int nPosX) {
    }

    protected void setCurrent(int nCurrent) {
    }

    protected int getCurrent() {
        return 0;
    }

    protected void setMin(int iMin) {
    }

    protected void setMax(int iMax) {
    }

    protected boolean getMoveable() {
        return false;
    }

    protected boolean getAnotherView() {
        return false;
    }

    protected void setAnotherView(boolean inAnotherView) {
    }

    protected void setScrollPosY(int iScrollPosY) {
    }

    protected void scrollTheMenu() {
    }

    protected void srollByWheel(int nScoll) {
    }

    protected boolean getIsScrollable() {
        return false;
    }

    protected void addText(String sText, int extraHeight) {
    }

    protected void setData(List<GraphData> nData) {
    }

    protected void addData(GraphData nData) {
    }

    protected void removeData(int iCivID) {
    }

    protected void actionElement(int iID) {
    }

    protected final TypeOfElement getTypeOfElement() {
        return this.typeOfElement;
    }

    protected void setTypeOfButton(Button.TypeOfButton typeOfButton) {
    }

    protected boolean getClickable() {
        return this.isClickable;
    }

    protected final void setClickable(boolean isClickable) {
        this.isClickable = isClickable;
    }

    protected boolean getVisible() {
        return this.isVisible;
    }

    protected void setVisible(boolean isVisible) {
        this.isVisible = isVisible;
    }

    protected int getPosX() {
        return this.iPosX;
    }

    protected final void setPosX(int iPosX) {
        this.iPosX = iPosX;
    }

    protected int getPosY() {
        return this.iPosY;
    }

    protected final void setPosY(int iPosY) {
        this.iPosY = iPosY;
    }

    protected int getWidth() {
        return this.iWidth;
    }

    protected void setWidth(int iWidth) {
        this.iWidth = iWidth;
    }

    protected int getHeight() {
        return this.iHeight;
    }

    protected final void setHeight(int iHeight) {
        this.iHeight = iHeight;
    }

    protected String getTextToDraw() {
        return "";
    }

    protected String getText() {
        return "";
    }

    protected int getTextWidth() {
        return 0;
    }

    protected int getTextHeight() {
        return 0;
    }

    protected int getTextPos() {
        return 0;
    }

    protected final boolean getIsInView() {
        return this.isInView;
    }

    protected final void setIsInView(boolean isInView) {
        this.isInView = isInView;
    }

    protected final boolean getIsHovered() {
        return this.isHovered;
    }

    protected void setIsHovered(boolean isHovered) {
        this.isHovered = isHovered;
    }

    protected int getSFX() {
        return SoundsManager.SOUND_CLICK;
    }

    protected static enum TypeOfElement {
        BUTTON,
        BUTTON_FLAG,
        BUTTON_TRANSPARENT,
        SLIDER,
        SLIDE,
        TEXT,
        TEXT_SLIDER,
        MINIMAP,
        MINIMAPINFO,
        FLAG_PIXEL,
        SPACE,
        DIPLOMACY_INFO,
        GRAPH,
        GRAPH_VERTICAL,
        GRAPH_CIRCLE;

    }
}

