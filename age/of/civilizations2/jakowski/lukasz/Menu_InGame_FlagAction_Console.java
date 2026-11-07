// 
// Decompiled by Procyon v0.6.0
// 

package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.scenes.scene2d.utils.ScissorStack;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.List;
import java.util.ArrayList;

class Menu_InGame_FlagAction_Console extends SliderMenu
{
    protected Menu_InGame_FlagAction_Console() {
        int tempHeight = CFG.GAME_HEIGHT - (ImageManager.getImage(Images.top_left).getHeight() + CFG.PADDING * 2 + ImageManager.getImage(Images.top_flag_frame).getHeight() + CFG.PADDING * 4 + CFG.TEXT_HEIGHT + CFG.PADDING * 4) - CFG.map.getMapBG().getMinimapHeight() - CFG.PADDING * 2 - CFG.BUTTON_HEIGHT * 3 / 5;
        final int tempWidth = Menu_InGame_FlagAction.getWindowWidth() - CFG.PADDING * 4;
        tempHeight *= (int)0.375f;
        final List<MenuElement> menuElements = new ArrayList<MenuElement>();
        menuElements.add(new Button_Transparent(CFG.PADDING * 2, CFG.PADDING * 2, tempWidth - CFG.PADDING * 4, CFG.TEXT_HEIGHT * 650, true));
        menuElements.add(new Button_Transparent(0, 0, tempWidth, tempHeight, true));
        this.initMenu(null, CFG.PADDING * 2 + AoCGame.LEFT, ImageManager.getImage(Images.top_left).getHeight() + CFG.PADDING * 2 + ImageManager.getImage(Images.top_flag_frame).getHeight() + CFG.PADDING * 4, tempWidth, tempHeight, menuElements, false, false);
    }
    
    @Override
    protected void draw(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean sliderMenuIsActive) {
        oSB.setColor(Color.WHITE);
        ImageManager.getImage(Images.new_game_top_edge).draw2(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.new_game_top_edge).getHeight() + iTranslateY, this.getWidth() - ImageManager.getImage(Images.new_game_top_edge).getWidth(), this.getHeight(), false, true);
        ImageManager.getImage(Images.new_game_top_edge).draw2(oSB, this.getPosX() + this.getWidth() - ImageManager.getImage(Images.new_game_top_edge).getWidth() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.new_game_top_edge).getHeight() + iTranslateY, ImageManager.getImage(Images.new_game_top_edge).getWidth(), this.getHeight(), true, true);
        oSB.setColor(new Color(0.025f, 0.025f, 0.025f, 0.25f));
        ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth(), this.getHeight());
        oSB.setColor(new Color(0.025f, 0.025f, 0.025f, 0.75f));
        ImageManager.getImage(Images.gradient).draw(oSB, this.getPosX() + 2 + iTranslateX, this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY, this.getWidth() - 4, CFG.BUTTON_HEIGHT / 4);
        ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX() + 2 + iTranslateX, this.getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, CFG.BUTTON_HEIGHT / 4, this.getHeight() - 4);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.4f));
        ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - 1 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth() - 2, 1);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.1f));
        ImageManager.getImage(Images.pix255_255_255).draw2(oSB, this.getPosX() + CFG.PADDING + iTranslateX, this.getPosY() + CFG.PADDING - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY, this.getWidth() - CFG.PADDING * 2, this.getHeight() - CFG.PADDING * 2);
        oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.15f));
        ImageManager.getImage(Images.gameLogo).draw(oSB, this.getPosX() + this.getWidth() - CFG.PADDING * 2 - ImageManager.getImage(Images.gameLogo).getWidth() + iTranslateX, this.getPosY() + this.getHeight() - CFG.PADDING * 2 - ImageManager.getImage(Images.gameLogo).getHeight() + iTranslateY);
        oSB.setColor(new Color(CFG.COLOR_GRADIENT_TITLE_BLUE.r, CFG.COLOR_GRADIENT_TITLE_BLUE.g, CFG.COLOR_GRADIENT_TITLE_BLUE.b, 0.45f));
        ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() + CFG.PADDING + iTranslateX, this.getPosY() + CFG.PADDING - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY, this.getWidth() - CFG.PADDING * 2, 1);
        ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() + CFG.PADDING + iTranslateX, this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + this.getHeight() - CFG.PADDING - 1 + iTranslateY, this.getWidth() - CFG.PADDING * 2, 1);
        oSB.setColor(new Color(CFG.COLOR_GRADIENT_TITLE_BLUE.r, CFG.COLOR_GRADIENT_TITLE_BLUE.g, CFG.COLOR_GRADIENT_TITLE_BLUE.b, 0.675f));
        ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX() + CFG.PADDING + iTranslateX, this.getPosY() + CFG.PADDING - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth() - CFG.PADDING * 2, 1);
        ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX() + CFG.PADDING + iTranslateX, this.getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() + this.getHeight() - CFG.PADDING - 1 + iTranslateY, this.getWidth() - CFG.PADDING * 2, 1);
        oSB.setColor(Color.WHITE);
        super.draw(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        CFG.fontMain.getData().setScale(0.8f);
        final Rectangle clipBounds = new Rectangle((float)(this.getPosX() + CFG.PADDING + iTranslateX), (float)(CFG.GAME_HEIGHT - (this.getPosY() + CFG.PADDING) - iTranslateY), (float)(this.getWidth() - CFG.PADDING * 2), (float)(-(this.getHeight() - CFG.PADDING * 2)));
        oSB.flush();
        ScissorStack.pushScissors(clipBounds);
        for (int i = Commands.sConsole.size() - 1, j = 0; i >= 0; --i, ++j) {
            CFG.drawText(oSB, Commands.sConsole.get(i), this.getPosX() + this.getMenuElement(0).getPosX() + CFG.PADDING + iTranslateX, this.getMenuElement(0).getPosY() + this.getMenuPosY() + CFG.TEXT_HEIGHT * j + (int)((CFG.TEXT_HEIGHT - CFG.TEXT_HEIGHT * 0.8f) / 2.0f) + iTranslateY, CFG.COLOR_TEXT_OPTIONS_NS_HOVER);
        }
        try {
            oSB.flush();
            ScissorStack.popScissors();
        }
        catch (final IllegalStateException ex) {}
        CFG.fontMain.getData().setScale(1.0f);
        if (Commands.lShit.size() > 0) {
            for (int i = 0; i < Commands.lShit.size(); ++i) {
                CFG.game.getCiv(i % CFG.game.getCivsSize()).getFlag().draw(oSB, (Math.abs(CFG.map.getMapCoordinates().getPosX()) + Commands.lShit.get(i).getPosX()) % CFG.GAME_WIDTH - CFG.game.getCiv(0).getFlag().getWidth() / 2, (Math.abs(CFG.map.getMapCoordinates().getPosY()) + Commands.lShit.get(i).getPosY()) % CFG.GAME_HEIGHT - CFG.game.getCiv(0).getFlag().getHeight() / 2);
            }
            if (Commands.lShitTime + 12500L < System.currentTimeMillis()) {
                Commands.lShit.clear();
            }
            CFG.setRender_3(true);
        }
    }
    
    @Override
    protected void drawScrollPos(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean sliderMenuIsActive) {
        if (sliderMenuIsActive) {
            super.drawScrollPos(oSB, iTranslateX - CFG.PADDING, iTranslateY, sliderMenuIsActive);
        }
    }
    
    @Override
    protected void onHovered() {
        CFG.menuManager.setOrderOfMenu_InGame_FlagAction();
    }
    
    @Override
    protected void actionElement(final int iID) {
        switch (iID) {
            default: {}
        }
    }
    
    @Override
    protected void setVisible(final boolean visible) {
        super.setVisible(visible);
        Commands.lShit.clear();
    }
}
