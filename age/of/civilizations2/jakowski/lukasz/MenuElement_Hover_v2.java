/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element2;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.List;

class MenuElement_Hover_v2
implements MenuElement_Hover {
    protected static final float TEXT_SCALE = 0.75f;
    private List<MenuElement_Hover_v2_Element2> lElements;
    private int iElementsSize = 0;
    protected static long ANIMATION_TIME;
    protected static float ANIMATION_ALPHA;
    protected static float ANIMATION_PADDING;
    protected static int ANIMATION_INTERVAL;
    private int iHeight = 0;
    private int iWidth = 0;
    private int iWidthOver = 0;
    private int iScrollPosX = 0;
    private boolean backAnimation = true;
    private long lTime = 0L;

    protected static final void resetAnimation() {
        ANIMATION_TIME = System.currentTimeMillis();
        ANIMATION_ALPHA = 0.01f;
        ANIMATION_PADDING = CFG.PADDING;
    }

    protected static final void resetAnimation_2() {
        ANIMATION_TIME = System.currentTimeMillis();
        ANIMATION_ALPHA = 0.3f;
        ANIMATION_PADDING = CFG.PADDING;
    }

    protected MenuElement_Hover_v2(List<MenuElement_Hover_v2_Element2> nElements) {
        int i;
        this.lElements = nElements;
        this.iElementsSize = this.lElements.size();
        this.iWidth = 0;
        for (i = 0; i < this.iElementsSize; ++i) {
            if (this.lElements.get(i).getWidth() <= CFG.GAME_WIDTH - CFG.PADDING * 2 || this.lElements.get(i).getWidth() - CFG.GAME_WIDTH - CFG.PADDING * 2 <= this.iWidthOver) continue;
            this.iWidthOver = this.lElements.get(i).getWidth() - CFG.GAME_WIDTH - CFG.PADDING * 2;
        }
        if (this.iWidthOver > 0) {
            this.iScrollPosX = this.iWidthOver + CFG.PADDING * 10;
            this.lTime = System.currentTimeMillis();
        }
        for (i = 0; i < this.iElementsSize; ++i) {
            if (this.lElements.get(i).getWidth() <= this.iWidth) continue;
            this.iWidth = this.lElements.get(i).getWidth();
        }
        this.iWidth += CFG.PADDING * 6;
        this.iHeight = CFG.TEXT_HEIGHT * this.iElementsSize + CFG.PADDING * (this.iElementsSize - 1) + CFG.PADDING * 4;
    }

    @Override
    public final void draw(SpriteBatch oSB, int nPosX, int nPosY) {
        if ((nPosX = (int)((float)nPosX + ANIMATION_PADDING)) + this.iWidth > CFG.GAME_WIDTH - CFG.PADDING) {
            nPosX = CFG.GAME_WIDTH - this.iWidth - CFG.PADDING;
        }
        if (nPosY < 0) {
            nPosY = CFG.PADDING;
        } else if (nPosY + this.iHeight > CFG.GAME_HEIGHT) {
            nPosY = CFG.GAME_HEIGHT - this.iHeight - CFG.PADDING;
        }
        this.draw_Hover(oSB, nPosX, nPosY);
    }

    @Override
    public final void drawAlwaysOver(SpriteBatch oSB, int nPosX, int nPosY) {
        nPosX = (int)((float)nPosX + ANIMATION_PADDING);
        nPosY = nPosY - this.iHeight - CFG.PADDING;
        if ((nPosX += CFG.PADDING) + this.iWidth > CFG.GAME_WIDTH - CFG.PADDING) {
            nPosX = CFG.GAME_WIDTH - this.iWidth - CFG.PADDING;
        }
        if (nPosY < 0) {
            nPosY = CFG.PADDING;
        } else if (nPosY + this.iHeight > CFG.GAME_HEIGHT) {
            nPosY = CFG.GAME_HEIGHT - this.iHeight - CFG.PADDING;
        }
        this.draw_Hover(oSB, nPosX, nPosY);
    }

    @Override
    public final void drawAlwaysOver_Mobile(SpriteBatch oSB, int nPosX, int nPosY) {
        nPosX = (int)((float)nPosX + ANIMATION_PADDING);
        nPosY = nPosY - this.iHeight - CFG.PADDING * 4;
        if ((nPosX -= this.iWidth / 4) < CFG.PADDING) {
            nPosX = CFG.PADDING;
        }
        if (nPosX + this.iWidth > CFG.GAME_WIDTH - CFG.PADDING) {
            nPosX = CFG.GAME_WIDTH - this.iWidth - CFG.PADDING;
        }
        if (nPosY < 0) {
            nPosY = CFG.PADDING;
        } else if (nPosY + this.iHeight > CFG.GAME_HEIGHT) {
            nPosY = CFG.GAME_HEIGHT - this.iHeight - CFG.PADDING;
        }
        this.draw_Hover(oSB, nPosX, nPosY);
    }

    @Override
    public final void drawAlwaysBelow(SpriteBatch oSB, int nPosX, int nPosY) {
        nPosX = (int)((float)nPosX + ANIMATION_PADDING);
        nPosY += CFG.PADDING;
        if ((nPosX += CFG.PADDING) + this.iWidth > CFG.GAME_WIDTH - CFG.PADDING) {
            nPosX = CFG.GAME_WIDTH - this.iWidth - CFG.PADDING;
        }
        if (nPosY < 0) {
            nPosY = CFG.PADDING;
        } else if (nPosY + this.iHeight > CFG.GAME_HEIGHT) {
            nPosY = CFG.GAME_HEIGHT - this.iHeight - CFG.PADDING;
        }
        this.draw_Hover(oSB, nPosX, nPosY);
    }

    @Override
    public final void drawProvinceInfo(SpriteBatch oSB, int nPosX, int nPosY) {
        nPosX = (int)((float)nPosX + ANIMATION_PADDING);
        this.draw_Hover(oSB, nPosX, nPosY);
    }

    protected final int getScrollPosX() {
        if (this.iWidthOver > 0) {
            if (this.backAnimation) {
                if (this.lTime + 1500L < System.currentTimeMillis() && this.iScrollPosX-- < -CFG.PADDING) {
                    this.backAnimation = !this.backAnimation;
                    this.lTime = System.currentTimeMillis();
                }
            } else if (this.lTime + 1000L < System.currentTimeMillis() && this.iScrollPosX++ > this.iWidthOver + CFG.PADDING * 10) {
                this.backAnimation = !this.backAnimation;
                this.lTime = System.currentTimeMillis();
            }
            CFG.setRender_3(true);
            return this.iScrollPosX;
        }
        return 0;
    }

    @Override
    public final void draw_Hover(SpriteBatch oSB, int nPosX, int nPosY) {
        int tempScrollX = this.getScrollPosX();
        oSB.setColor(new Color(1.0f, 1.0f, 1.0f, ANIMATION_ALPHA));
        CFG.drawRect_NewGameBox(oSB, nPosX + tempScrollX, nPosY, this.iWidth, this.iHeight);
        oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.15f * ANIMATION_ALPHA));
        ImageManager.getImage(Images.line_32_off1).draw(oSB, nPosX + tempScrollX, nPosY + 1 - ImageManager.getImage(Images.line_32_off1).getHeight(), this.iWidth, 1);
        ImageManager.getImage(Images.line_32_off1).draw(oSB, nPosX + tempScrollX, nPosY + this.iHeight - 2 - ImageManager.getImage(Images.line_32_off1).getHeight(), this.iWidth, 1);
        oSB.setColor(new Color(1.0f, 1.0f, 1.0f, ANIMATION_ALPHA));
        CFG.fontMain.getData().setScale(0.75f);
        for (int i = 0; i < this.iElementsSize; ++i) {
            this.lElements.get(i).draw(oSB, nPosX + tempScrollX + CFG.PADDING * 3, nPosY + CFG.PADDING + CFG.TEXT_HEIGHT * i + CFG.PADDING * i, ANIMATION_ALPHA);
        }
        CFG.fontMain.getData().setScale(1.0f);
        oSB.setColor(Color.WHITE);
    }

    @Override
    public final void draw_HoverWithoutAnimation(SpriteBatch oSB, int nPosX, int nPosY) {
        int tempScrollX = this.getScrollPosX();
        if (nPosY + this.iHeight > CFG.GAME_HEIGHT - CFG.PADDING * 2) {
            nPosY = CFG.GAME_HEIGHT - CFG.PADDING * 2 - this.iHeight;
        }
        oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 1.0f));
        CFG.drawRect_NewGameBox_EDGE(oSB, nPosX + tempScrollX, nPosY, this.iWidth, this.iHeight);
        oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.15f));
        ImageManager.getImage(Images.line_32_off1).draw(oSB, nPosX + tempScrollX, nPosY + 1 - ImageManager.getImage(Images.line_32_off1).getHeight(), this.iWidth, 1);
        ImageManager.getImage(Images.line_32_off1).draw(oSB, nPosX + tempScrollX, nPosY + this.iHeight - 2 - ImageManager.getImage(Images.line_32_off1).getHeight(), this.iWidth, 1);
        oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 1.0f));
        CFG.fontMain.getData().setScale(0.75f);
        for (int i = 0; i < this.iElementsSize; ++i) {
            this.lElements.get(i).draw(oSB, nPosX + tempScrollX + CFG.PADDING * 3, nPosY + CFG.PADDING + CFG.TEXT_HEIGHT * i + CFG.PADDING * i, 1.0f);
        }
        CFG.fontMain.getData().setScale(1.0f);
        oSB.setColor(Color.WHITE);
    }

    static {
        ANIMATION_INTERVAL = 2450;
    }
}

