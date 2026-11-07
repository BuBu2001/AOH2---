/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.Menu_Civilization_Info;
import age.of.civilizations2.jakowski.lukasz.SliderMenu;
import age.of.civilizations2.jakowski.lukasz.SliderMenuTitle;
import age.of.civilizations2.jakowski.lukasz.Text;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

class Menu_Civilizations_Info_Diplomacy
extends SliderMenu {
    protected Menu_Civilizations_Info_Diplomacy() {
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        menuElements.add(new Text(null, CFG.PADDING * 2, CFG.PADDING){

            @Override
            protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
                CFG.fontMain.getData().setScale(0.8f);
                CFG.drawTextWithShadow(oSB, this.sText, this.getPosX() + iTranslateX, this.getPosY() + (int)((float)this.getHeight() - (float)this.getTextHeight() * 0.8f) / 2 + iTranslateY, this.getColor(isActive));
                CFG.fontMain.getData().setScale(1.0f);
            }

            @Override
            protected Color getColor(boolean isActive) {
                return CFG.getColor_CivInfo_Text(isActive, this.getIsHovered());
            }
        });
        menuElements.add(new Text(null, CFG.PADDING * 2, CFG.PADDING + CFG.TEXT_HEIGHT + CFG.PADDING){

            @Override
            protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
                CFG.fontMain.getData().setScale(0.8f);
                CFG.drawTextWithShadow(oSB, this.sText, this.getPosX() + iTranslateX, this.getPosY() + (int)((float)this.getHeight() - (float)this.getTextHeight() * 0.8f) / 2 + iTranslateY, this.getColor(isActive));
                CFG.fontMain.getData().setScale(1.0f);
            }

            @Override
            protected Color getColor(boolean isActive) {
                return CFG.getColor_CivInfo_Text(isActive, this.getIsHovered());
            }
        });
        menuElements.add(new Text(null, CFG.PADDING * 2, CFG.PADDING + (CFG.TEXT_HEIGHT + CFG.PADDING) * 2){

            @Override
            protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
                CFG.fontMain.getData().setScale(0.8f);
                CFG.drawTextWithShadow(oSB, this.sText, this.getPosX() + iTranslateX, this.getPosY() + (int)((float)this.getHeight() - (float)this.getTextHeight() * 0.8f) / 2 + iTranslateY, this.getColor(isActive));
                CFG.fontMain.getData().setScale(1.0f);
            }

            @Override
            protected Color getColor(boolean isActive) {
                return CFG.getColor_CivInfo_Text(isActive, this.getIsHovered());
            }
        });
        menuElements.add(new Text(null, CFG.PADDING * 2, CFG.PADDING + (CFG.TEXT_HEIGHT + CFG.PADDING) * 3){

            @Override
            protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
                CFG.fontMain.getData().setScale(0.8f);
                CFG.drawTextWithShadow(oSB, this.sText, this.getPosX() + iTranslateX, this.getPosY() + (int)((float)this.getHeight() - (float)this.getTextHeight() * 0.8f) / 2 + iTranslateY, this.getColor(isActive));
                CFG.fontMain.getData().setScale(1.0f);
            }

            @Override
            protected Color getColor(boolean isActive) {
                return CFG.getColor_CivInfo_Text(isActive, this.getIsHovered());
            }
        });
        this.initMenu(new SliderMenuTitle(null, CFG.TEXT_HEIGHT + CFG.PADDING * 2, false, false){

            @Override
            protected void draw(SpriteBatch oSB, int iTranslateX, int nPosX, int nPosY, int nWidth, boolean sliderMenuIsActive) {
                oSB.setColor(new Color(0.011f, 0.014f, 0.019f, 0.2f));
                ImageManager.getImage(Images.gradient).draw(oSB, Menu_Civilizations_Info_Diplomacy.this.getPosX() - CFG.PADDING + 2 + iTranslateX, Menu_Civilizations_Info_Diplomacy.this.getPosY() + 2 - ImageManager.getImage(Images.gradient).getHeight() - (this.getHeight() + CFG.PADDING * 2), Menu_Civilizations_Info_Diplomacy.this.getWidth() + CFG.PADDING * 2 - 2, (this.getHeight() + CFG.PADDING * 2 - 4) * 3 / 4, false, false);
                oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.25f));
                ImageManager.getImage(Images.line_32_off1).draw(oSB, Menu_Civilizations_Info_Diplomacy.this.getPosX() - CFG.PADDING + 2 + iTranslateX, Menu_Civilizations_Info_Diplomacy.this.getPosY() - this.getHeight() - ImageManager.getImage(Images.line_32_off1).getHeight() + 1 - CFG.PADDING * 2, Menu_Civilizations_Info_Diplomacy.this.getWidth() + CFG.PADDING * 2 - 2, ImageManager.getImage(Images.line_32_off1).getHeight(), false, true);
                oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.4f));
                ImageManager.getImage(Images.line_32_off1).draw(oSB, Menu_Civilizations_Info_Diplomacy.this.getPosX() - CFG.PADDING + 2 + iTranslateX, Menu_Civilizations_Info_Diplomacy.this.getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() - 2 - CFG.PADDING * 2, Menu_Civilizations_Info_Diplomacy.this.getWidth() + CFG.PADDING * 2 - 2, ImageManager.getImage(Images.line_32_off1).getHeight(), false, true);
                oSB.setColor(Color.WHITE);
                CFG.fontMain.getData().setScale(0.85f);
                CFG.drawTextWithShadow(oSB, this.getText(), nPosX + nWidth / 2 - (int)((float)this.getTextWidth() * 0.85f) / 2 + iTranslateX, nPosY - this.getHeight() + this.getHeight() / 2 - (int)((float)this.getTextHeight() * 0.85f) / 2 - CFG.PADDING * 2, CFG.COLOR_TEXT_CIV_INFO_TITLE);
                CFG.fontMain.getData().setScale(1.0f);
            }
        }, CFG.GAME_WIDTH - CFG.CIV_INFO_MENU_WIDTH + CFG.PADDING, ImageManager.getImage(Images.new_game_top).getHeight() + CFG.PADDING * 4 + (int)((float)CFG.TEXT_HEIGHT * 0.6f) + ImageManager.getImage(Images.top_flag_frame).getHeight() + CFG.PADDING * 4 + CFG.TEXT_HEIGHT + CFG.PADDING * 2 + CFG.PADDING * 2, CFG.CIV_INFO_MENU_WIDTH - CFG.PADDING * 2, CFG.TEXT_HEIGHT * 5 + CFG.PADDING * 6 - CFG.PADDING * 4, menuElements, false, false);
        this.updateLanguage();
    }

    @Override
    protected void updateLanguage() {
        this.getTitle().setText(CFG.langManager.get("Diplomacy"));
        this.getMenuElement(0).setText(CFG.langManager.get("CivWars"));
        this.getMenuElement(1).setText(CFG.langManager.get("CivAllies"));
        this.getMenuElement(2).setText(CFG.langManager.get("CivPacts"));
        this.getMenuElement(3).setText(CFG.langManager.get("CivVassals"));
    }

    @Override
    protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        if (Menu_Civilization_Info.lTime + 250L >= System.currentTimeMillis()) {
            iTranslateX += this.getWidth() - (int)((float)this.getWidth() * ((float)(System.currentTimeMillis() - Menu_Civilization_Info.lTime) / 250.0f));
        }
        ImageManager.getImage(Images.new_game_top_edge_line).draw2(oSB, this.getPosX() - CFG.PADDING + iTranslateX, this.getPosY() - ImageManager.getImage(Images.new_game_top_edge_line).getHeight() - this.getTitle().getHeight() - CFG.PADDING * 2, this.getWidth() + CFG.PADDING * 2, this.getHeight() + this.getTitle().getHeight() + CFG.PADDING * 4);
        CFG.drawRect_NewGameBox(oSB, this.getPosX() + iTranslateX, this.getPosY() - CFG.PADDING, this.getWidth(), this.getHeight() + CFG.PADDING * 2);
        super.draw(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
    }

    @Override
    protected void actionElement(int iID) {
        switch (iID) {
            default: 
        }
    }

    @Override
    protected void drawScrollPos(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        super.drawScrollPos(oSB, iTranslateX - 2, iTranslateY, sliderMenuIsActive);
    }
}

