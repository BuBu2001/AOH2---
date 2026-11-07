/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button_Game_Ideology;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Image;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.SliderMenu;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.utils.ScissorStack;
import com.badlogic.gdx.utils.GdxRuntimeException;
import java.util.ArrayList;
import java.util.List;

class Menu_CreateScenario_Civilizations_Ideologies
extends SliderMenu {
    private String sCivsTag;
    private List<Image> lFlags = new ArrayList<Image>();
    private List<Integer> lLoadedFlags_TagsIDs = new ArrayList<Integer>();

    protected Menu_CreateScenario_Civilizations_Ideologies() {
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        if (CFG.game.getActiveProvinceID() >= 0 && CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID() > 0 && CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).getCapitalProvinceID() == CFG.game.getActiveProvinceID() && !CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).getCivTag().equals("ran")) {
            try {
                this.sCivsTag = CFG.ideologiesManager.getRealTag(CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).getCivTag());
                for (int i = 0; i < CFG.ideologiesManager.getIdeologiesSize(); ++i) {
                    menuElements.add(new Button_Game_Ideology("" + CFG.langManager.getCiv(this.sCivsTag + CFG.ideologiesManager.getIdeology(i).getExtraTag()), i, -1, CFG.GAME_WIDTH + CFG.PADDING, CFG.PADDING, CFG.BUTTON_WIDTH, CFG.game.isCivTagAvailable(this.sCivsTag + CFG.ideologiesManager.getIdeology(i).getExtraTag())));
                }
            }
            catch (GdxRuntimeException gdxRuntimeException) {
                // empty catch block
            }
        }
        this.initMenu(null, 0, CFG.BUTTON_HEIGHT + CFG.PADDING * 2, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT + CFG.PADDING * 2 + 1, menuElements);
        this.updatedButtonsWidth(CFG.PADDING, CFG.BUTTON_WIDTH);
        this.updateMenuElements_IsInView();
        CFG.fMOVE_MENU_PERCENTAGE = 5.0f;
        CFG.lMOVE_MENU_TIME = System.currentTimeMillis();
    }

    @Override
    protected void updateMenuElements_IsInView() {
        super.updateMenuElements_IsInView_X();
        for (int i = 0; i < this.getMenuElementsSize(); ++i) {
            int tempTagID = this.getIsLoaded(i);
            if (this.getMenuElement(i).getIsInView()) {
                if (tempTagID >= 0) continue;
                this.loadFlag(i);
                continue;
            }
            if (tempTagID < 0) continue;
            this.lFlags.get(tempTagID).getTexture().dispose();
            this.lFlags.set(tempTagID, null);
            this.lFlags.remove(tempTagID);
            this.lLoadedFlags_TagsIDs.remove(tempTagID);
        }
    }

    private final int getIsLoaded(int nCivTag) {
        for (int i = 0; i < this.lLoadedFlags_TagsIDs.size(); ++i) {
            if (this.lLoadedFlags_TagsIDs.get(i) != nCivTag) continue;
            return i;
        }
        return -1;
    }

    private final void loadFlag(int nCivTagID) {
        block10: {
            try {
                try {
                    this.lFlags.add(new Image(new Texture(Gdx.files.internal("game/flags/" + this.sCivsTag + CFG.ideologiesManager.getIdeology(nCivTagID).getExtraTag() + ".png")), Texture.TextureFilter.Nearest));
                }
                catch (GdxRuntimeException ex) {
                    boolean isDone = false;
                    if (CFG.ideologiesManager.getIdeology((int)nCivTagID).REVOLUTIONARY) {
                        this.lFlags.add(new Image(new Texture(Gdx.files.internal("game/flags/rb" + this.sCivsTag.charAt(0) % 6 + ".png")), Texture.TextureFilter.Nearest));
                        isDone = true;
                    }
                    if (isDone) break block10;
                    try {
                        this.lFlags.add(new Image(new Texture(Gdx.files.internal("game/flags/" + this.sCivsTag + ".png")), Texture.TextureFilter.Nearest));
                    }
                    catch (GdxRuntimeException exr) {
                        if (CFG.isAndroid()) {
                            try {
                                this.lFlags.add(new Image(new Texture(Gdx.files.local("game/civilizations_editor/" + this.sCivsTag + "/" + this.sCivsTag + "_FL.png")), Texture.TextureFilter.Linear));
                            }
                            catch (GdxRuntimeException erq) {
                                this.lFlags.add(new Image(new Texture(Gdx.files.internal("game/civilizations_editor/" + this.sCivsTag + "/" + this.sCivsTag + "_FL.png")), Texture.TextureFilter.Linear));
                            }
                            break block10;
                        }
                        this.lFlags.add(new Image(new Texture(Gdx.files.internal("game/civilizations_editor/" + this.sCivsTag + "/" + this.sCivsTag + "_FL.png")), Texture.TextureFilter.Linear));
                    }
                }
            }
            catch (GdxRuntimeException ex) {
                this.lFlags.add(new Image(new Texture(Gdx.files.internal("game/flags/ran.png")), Texture.TextureFilter.Nearest));
            }
        }
        this.lLoadedFlags_TagsIDs.add(nCivTagID);
    }

    private final int getFlagID(int nCivTagID) {
        for (int i = 0; i < this.lLoadedFlags_TagsIDs.size(); ++i) {
            if (this.lLoadedFlags_TagsIDs.get(i) != nCivTagID) continue;
            return i;
        }
        return 0;
    }

    @Override
    protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        if ((CFG.fMOVE_MENU_PERCENTAGE += (float)(System.currentTimeMillis() - CFG.lMOVE_MENU_TIME) / 225.0f * 95.0f) > 100.0f) {
            CFG.fMOVE_MENU_PERCENTAGE = 100.0f;
        } else {
            CFG.setRender_3(true);
        }
        CFG.lMOVE_MENU_TIME = System.currentTimeMillis();
        Rectangle clipBounds = new Rectangle(this.getPosX() + iTranslateX, CFG.GAME_HEIGHT - this.getPosY() - iTranslateY, this.getWidth(), -this.getHeight());
        oSB.flush();
        ScissorStack.pushScissors(clipBounds);
        if (this.getMenuElementsSize() > 0) {
            CFG.drawEditorButtons_Top_Edge_R(oSB, iTranslateX, this.getMenuPosY() - (int)((float)this.getHeight() * (100.0f - CFG.fMOVE_MENU_PERCENTAGE) / 100.0f) + iTranslateY, this.getMenuElement(this.getMenuElementsSize() - 1).getPosX() + this.getMenuElement(this.getMenuElementsSize() - 1).getWidth() + CFG.PADDING, CFG.BUTTON_HEIGHT + CFG.PADDING * 2);
        }
        super.drawMenu(oSB, iTranslateX, iTranslateY += -((int)((float)this.getHeight() * (100.0f - CFG.fMOVE_MENU_PERCENTAGE) / 100.0f)), sliderMenuIsActive);
        for (int i = 0; i < this.getMenuElementsSize(); ++i) {
            if (!this.getMenuElement(i).getIsInView()) continue;
            this.lFlags.get(this.getFlagID(i)).draw(oSB, this.getMenuElement(i).getPosX() + (this.getMenuElement(i).getWidth() - (this.getMenuElement(i).getTextPos() + CFG.PADDING + CFG.CIV_FLAG_WIDTH)) / 2 + this.getMenuPosX() + iTranslateX, this.getMenuElement(i).getPosY() - this.lFlags.get(this.getFlagID(i)).getHeight() + this.getMenuPosY() + this.getMenuElement(i).getHeight() / 2 - CFG.PADDING / 2 - this.getMenuElement(i).getTextHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 + iTranslateY, CFG.CIV_FLAG_WIDTH, CFG.CIV_FLAG_HEIGHT);
            ImageManager.getImage(Images.flag_rect).draw(oSB, this.getMenuElement(i).getPosX() + (this.getMenuElement(i).getWidth() - (this.getMenuElement(i).getTextPos() + CFG.PADDING + CFG.CIV_FLAG_WIDTH)) / 2 + this.getMenuPosX() + iTranslateX, this.getMenuElement(i).getPosY() + this.getMenuPosY() + this.getMenuElement(i).getHeight() / 2 - CFG.PADDING / 2 - this.getMenuElement(i).getTextHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 + iTranslateY);
        }
        super.endClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
    }

    @Override
    protected final void actionElement(int iID) {
        if (CFG.game.getActiveProvinceID() >= 0 && CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID() > 0) {
            CFG.game.updateCivilizationIdeology(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID(), this.sCivsTag + CFG.ideologiesManager.getIdeology(iID).getExtraTag());
        }
        this.setVisible(false);
        CFG.updateCreateScenario_Civilizations();
    }

    @Override
    protected void setVisible(boolean visible) {
        if (!visible) {
            try {
                for (int i = 0; i < this.lFlags.size(); ++i) {
                    this.lFlags.get(i).getTexture().dispose();
                }
                this.lFlags.clear();
                this.lLoadedFlags_TagsIDs.clear();
                this.sCivsTag = null;
            }
            catch (NullPointerException nullPointerException) {
                // empty catch block
            }
        }
        super.setVisible(visible);
    }
}

