/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.Menu_InGame_RTO2;
import age.of.civilizations2.jakowski.lukasz.SliderMenu;
import age.of.civilizations2.jakowski.lukasz.Text_Scrollable;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

class Menu_InGame_RTO_Bot2
extends SliderMenu {
    protected Menu_InGame_RTO_Bot2() {
        int i;
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        int tempW = CFG.CIV_INFO_MENU_WIDTH;
        String tempText = "";
        ArrayList<Integer> lSortedPos = new ArrayList<Integer>();
        ArrayList<Integer> lPos = new ArrayList<Integer>();
        for (i = 0; i < CFG.game.getPlayersSize(); ++i) {
            if (CFG.game.getCiv(CFG.game.getPlayer(i).getCivID()).getNumOfProvinces() > 0) {
                lSortedPos.add(CFG.game.getRTO().getPositionInRTOOfCiv(CFG.game.getPlayer(i).getCivID()));
                lPos.add(i);
                continue;
            }
            lSortedPos.add(-1);
            lPos.add(i);
        }
        for (i = 0; i < CFG.game.getPlayersSize(); ++i) {
            for (int j = i; j < CFG.game.getPlayersSize(); ++j) {
                if ((Integer)lSortedPos.get((Integer)lPos.get(i)) <= (Integer)lSortedPos.get((Integer)lPos.get(j))) continue;
                int tempA = (Integer)lPos.get(i);
                lPos.set(i, (Integer)lPos.get(j));
                lPos.set(j, tempA);
            }
        }
        for (i = 0; i < lPos.size(); ++i) {
            if (CFG.game.getCiv(CFG.game.getPlayer((Integer)lPos.get(i)).getCivID()).getNumOfProvinces() <= 0) continue;
            tempText = tempText + CFG.game.getCiv(CFG.game.getPlayer((Integer)lPos.get(i)).getCivID()).getCivName() + ": " + CFG.game.getRTO().getPositionInRTOOfCiv(CFG.game.getPlayer((Integer)lPos.get(i)).getCivID()) + " - ";
        }
        menuElements.add(new Text_Scrollable(CFG.langManager.get("Position") + ": [" + tempText.substring(0, tempText.length() - 3 > 0 ? tempText.length() - 3 : tempText.length()) + "]", CFG.PADDING * 2, CFG.PADDING * 2, tempW - CFG.PADDING * 2, CFG.COLOR_TEXT_CIV_INFO_TITLE, 0.8f));
        this.initMenu(null, CFG.GAME_WIDTH - tempW, 0, tempW, CFG.TEXT_HEIGHT + CFG.PADDING * 4, menuElements, false, false);
        this.updateLanguage();
    }

    @Override
    protected void updateLanguage() {
    }

    @Override
    protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        if (Menu_InGame_RTO2.lTime + 250L >= System.currentTimeMillis()) {
            iTranslateX += this.getWidth() - (int)((float)this.getWidth() * ((float)(System.currentTimeMillis() - Menu_InGame_RTO2.lTime) / 250.0f));
        }
        ImageManager.getImage(Images.new_game_box).draw2(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.new_game_box).getHeight() + iTranslateY, this.getWidth(), this.getHeight(), false, true);
        oSB.setColor(new Color(0.451f, 0.329f, 0.11f, 1.0f));
        ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() + 2 + iTranslateX, this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight(), this.getWidth() - 4);
        oSB.setColor(Color.WHITE);
        super.draw(oSB, iTranslateX, 1 + iTranslateY, sliderMenuIsActive);
    }

    @Override
    protected void actionElement(int iID) {
        switch (iID) {
            default: 
        }
    }
}

