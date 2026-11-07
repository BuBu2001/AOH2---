/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button_NewGameStyle_Left;
import age.of.civilizations2.jakowski.lukasz.Button_New_Game_Players;
import age.of.civilizations2.jakowski.lukasz.Button_New_Game_Players_Players_RIGHT;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Image;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.Menu;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Text;
import age.of.civilizations2.jakowski.lukasz.SliderMenu;
import age.of.civilizations2.jakowski.lukasz.SliderMenuTitle;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.GdxRuntimeException;
import java.util.ArrayList;
import java.util.List;

class Menu_MapEditor_FormableCivs_Claimants
extends SliderMenu {
    private List<Image> lFlags = new ArrayList<Image>();

    protected Menu_MapEditor_FormableCivs_Claimants() {
        int tempW = CFG.CIV_INFO_MENU_WIDTH;
        int tempElemH = CFG.BUTTON_HEIGHT;
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        int tY = CFG.PADDING;
        menuElements.add(new Button_New_Game_Players(null, -1, CFG.PADDING + 2, tY, tempW - CFG.PADDING * 2 - 2, true){

            @Override
            protected void buildElementHover() {
                try {
                    ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                    ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("FormableHelp0"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                    nElements.add(new MenuElement_Hover_v2_Element2(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover_v2(nElements);
                }
                catch (IndexOutOfBoundsException e) {
                    this.menuElementHover = null;
                }
            }
        });
        tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
        for (int i = 0; i < CFG.formableCivs_GameData.getClaimantsSize(); ++i) {
            menuElements.add(new Button_NewGameStyle_Left(CFG.langManager.getCiv(CFG.formableCivs_GameData.getClaimant(i)), CFG.PADDING * 3 + CFG.CIV_FLAG_WIDTH, CFG.PADDING + 2, tY, tempW - 2 - CFG.PADDING * 2 - (int)((float)CFG.BUTTON_HEIGHT * 0.6f), (int)((float)CFG.BUTTON_HEIGHT * 0.6f), true));
            menuElements.add(new Button_New_Game_Players_Players_RIGHT("", -1, tempW - 2 - CFG.PADDING - (int)((float)CFG.BUTTON_HEIGHT * 0.6f), tY, (int)((float)CFG.BUTTON_HEIGHT * 0.6f), true){

                @Override
                protected void buildElementHover() {
                    ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                    ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Delete"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                    nElements.add(new MenuElement_Hover_v2_Element2(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover_v2(nElements);
                }
            });
            tY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
        }
        this.initMenu(new SliderMenuTitle(null, CFG.BUTTON_HEIGHT * 3 / 4, false, false){

            @Override
            protected void draw(SpriteBatch oSB, int iTranslateX, int nPosX, int nPosY, int nWidth, boolean sliderMenuIsActive) {
                ImageManager.getImage(Images.new_game_top_edge_title).draw2(oSB, Menu_MapEditor_FormableCivs_Claimants.this.getPosX() - 2 + iTranslateX, Menu_MapEditor_FormableCivs_Claimants.this.getPosY() - ImageManager.getImage(Images.new_game_top_edge_title).getHeight() - this.getHeight(), Menu_MapEditor_FormableCivs_Claimants.this.getWidth() + 2, this.getHeight(), false, false);
                oSB.setColor(new Color(CFG.COLOR_GRADIENT_TITLE_BLUE.r, CFG.COLOR_GRADIENT_TITLE_BLUE.g, CFG.COLOR_GRADIENT_TITLE_BLUE.b, 0.4f));
                ImageManager.getImage(Images.gradient).draw(oSB, Menu_MapEditor_FormableCivs_Claimants.this.getPosX() + iTranslateX, Menu_MapEditor_FormableCivs_Claimants.this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() - this.getHeight() * 3 / 4, Menu_MapEditor_FormableCivs_Claimants.this.getWidth(), this.getHeight() * 3 / 4, false, true);
                oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.65f));
                ImageManager.getImage(Images.gradient).draw(oSB, Menu_MapEditor_FormableCivs_Claimants.this.getPosX() + iTranslateX, Menu_MapEditor_FormableCivs_Claimants.this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() - CFG.PADDING, Menu_MapEditor_FormableCivs_Claimants.this.getWidth(), CFG.PADDING, false, true);
                oSB.setColor(new Color(0.451f, 0.329f, 0.11f, 1.0f));
                ImageManager.getImage(Images.pix255_255_255).draw(oSB, Menu_MapEditor_FormableCivs_Claimants.this.getPosX() + iTranslateX, Menu_MapEditor_FormableCivs_Claimants.this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight(), Menu_MapEditor_FormableCivs_Claimants.this.getWidth());
                oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.9f));
                ImageManager.getImage(Images.line_32_off1).draw(oSB, Menu_MapEditor_FormableCivs_Claimants.this.getPosX() + iTranslateX, Menu_MapEditor_FormableCivs_Claimants.this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() - ImageManager.getImage(Images.line_32_off1).getHeight(), Menu_MapEditor_FormableCivs_Claimants.this.getWidth(), 1);
                oSB.setColor(Color.WHITE);
                CFG.fontMain.getData().setScale(0.75f);
                CFG.drawText(oSB, this.getText(), nPosX + nWidth / 2 - (int)((float)this.getTextWidth() * 0.75f / 2.0f) + iTranslateX, nPosY - this.getHeight() + this.getHeight() / 2 + 1 - (int)((float)this.getTextHeight() * 0.75f / 2.0f), CFG.COLOR_TEXT_MODIFIER_NEUTRAL);
                CFG.fontMain.getData().setScale(1.0f);
            }
        }, CFG.GAME_WIDTH - tempW, CFG.BUTTON_HEIGHT * 2 + CFG.PADDING * 4 + CFG.PADDING * 2 + CFG.BUTTON_HEIGHT * 3 / 4, tempW, Math.min(((MenuElement)menuElements.get(menuElements.size() - 1)).getPosY() + ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING, CFG.GAME_HEIGHT - (CFG.BUTTON_HEIGHT * 2 + CFG.PADDING * 4 + CFG.PADDING * 2 + CFG.BUTTON_HEIGHT * 3 / 4) - CFG.BUTTON_HEIGHT - CFG.PADDING * 3), menuElements);
        this.updateLanguage();
        this.loadFlags();
    }

    @Override
    protected void updateLanguage() {
        this.getMenuElement(0).setText(CFG.langManager.get("AddCivilization"));
        this.getTitle().setText(CFG.langManager.get("Claimants"));
    }

    @Override
    protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        ImageManager.getImage(Images.new_game_top_edge_line).draw2(oSB, this.getPosX() - 2 + iTranslateX, this.getPosY() - ImageManager.getImage(Images.new_game_top_edge_line).getHeight() + iTranslateY, this.getWidth() + 2, this.getHeight(), false, true);
        super.beginClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        super.drawMenu(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        for (int i = 0; i < this.lFlags.size(); ++i) {
            this.lFlags.get(i).draw(oSB, this.getPosX() + this.getMenuElement(i * 2 + 1).getPosX() + CFG.PADDING * 2 + iTranslateX, this.getMenuPosY() + this.getMenuElement(i * 2 + 1).getPosY() + this.getMenuElement(i * 2 + 1).getHeight() / 2 - ImageManager.getImage(Images.flag_rect).getHeight() / 2 - this.lFlags.get(i).getHeight() + iTranslateY, ImageManager.getImage(Images.flag_rect).getWidth(), ImageManager.getImage(Images.flag_rect).getHeight());
            ImageManager.getImage(Images.flag_rect).draw(oSB, this.getPosX() + this.getMenuElement(i * 2 + 1).getPosX() + CFG.PADDING * 2 + iTranslateX, this.getMenuPosY() + this.getMenuElement(i * 2 + 1).getPosY() + this.getMenuElement(i * 2 + 1).getHeight() / 2 - ImageManager.getImage(Images.flag_rect).getHeight() / 2 + iTranslateY);
        }
        super.endClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        oSB.setColor(CFG.COLOR_CREATE_NEW_GAME_BOX_PLAYERS);
        ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + this.getHeight(), this.getWidth());
        oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.4f));
        ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() - ImageManager.getImage(Images.line_32_off1).getHeight() + this.getHeight(), this.getWidth(), 1);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.5f));
        ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() - 2 + iTranslateX, this.getPosY() + this.getHeight(), this.getWidth() + 2);
        oSB.setColor(Color.WHITE);
    }

    @Override
    protected void actionElement(int iID) {
        if (iID == 0) {
            CFG.menuManager.setViewID(Menu.eMAP_EDITOR_FORMABLE_CIVS_SELECT_CLAIMANT);
        }
        if ((iID - 1) % 2 == 1) {
            CFG.formableCivs_GameData.removeClaimant((iID - 1) / 2);
            CFG.menuManager.rebuildMapEditor_FormableCivs_Claimants();
        } else {
            CFG.toast.setInView(this.getMenuElement(iID).getText(), CFG.COLOR_TEXT_NUM_OF_PROVINCES);
        }
    }

    @Override
    protected void onBackPressed() {
        this.disposeFlags();
    }

    private final void loadFlags() {
        this.disposeFlags();
        for (int i = 0; i < CFG.formableCivs_GameData.getClaimantsSize(); ++i) {
            try {
                try {
                    this.lFlags.add(new Image(new Texture(Gdx.files.internal("game/flags/" + CFG.formableCivs_GameData.getClaimant(i) + ".png")), Texture.TextureFilter.Nearest));
                }
                catch (GdxRuntimeException e) {
                    try {
                        this.lFlags.add(new Image(new Texture(Gdx.files.internal("game/flags/" + CFG.ideologiesManager.getRealTag(CFG.formableCivs_GameData.getClaimant(i)) + ".png")), Texture.TextureFilter.Nearest));
                    }
                    catch (GdxRuntimeException ex) {
                        if (CFG.isAndroid()) {
                            try {
                                this.lFlags.add(new Image(new Texture(Gdx.files.local("game/civilizations_editor/" + CFG.formableCivs_GameData.getClaimant(i) + "/" + CFG.formableCivs_GameData.getClaimant(i) + "_FL.png")), Texture.TextureFilter.Nearest));
                            }
                            catch (GdxRuntimeException erq) {
                                this.lFlags.add(new Image(new Texture(Gdx.files.internal("game/civilizations_editor/" + CFG.formableCivs_GameData.getClaimant(i) + "/" + CFG.formableCivs_GameData.getClaimant(i) + "_FL.png")), Texture.TextureFilter.Nearest));
                            }
                            continue;
                        }
                        this.lFlags.add(new Image(new Texture(Gdx.files.internal("game/civilizations_editor/" + CFG.formableCivs_GameData.getClaimant(i) + "/" + CFG.formableCivs_GameData.getClaimant(i) + "_FL.png")), Texture.TextureFilter.Nearest));
                    }
                }
                continue;
            }
            catch (GdxRuntimeException e) {
                this.lFlags.add(new Image(new Texture(Gdx.files.internal("game/flags/ran.png")), Texture.TextureFilter.Nearest));
            }
        }
    }

    protected final void disposeFlags() {
        for (int i = 0; i < this.lFlags.size(); ++i) {
            this.lFlags.get(i).getTexture().dispose();
        }
        this.lFlags.clear();
    }
}

