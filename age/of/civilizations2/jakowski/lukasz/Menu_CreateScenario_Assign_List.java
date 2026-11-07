/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button_EditorFlag;
import age.of.civilizations2.jakowski.lukasz.Button_Transparent;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Image;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Flag;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Text;
import age.of.civilizations2.jakowski.lukasz.SliderMenu;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.GdxRuntimeException;
import java.util.ArrayList;
import java.util.List;

class Menu_CreateScenario_Assign_List
extends SliderMenu {
    private List<String> lCivsTags = null;
    private List<Image> lFlags = new ArrayList<Image>();
    private List<Integer> lLoadedFlags_TagsIDs = new ArrayList<Integer>();

    protected Menu_CreateScenario_Assign_List() {
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        int tPosX = CFG.PADDING;
        this.lCivsTags = new ArrayList<String>();
        for (int i = 0; i < CFG.game.getCivsSize(); ++i) {
            menuElements.add(new Button_EditorFlag(i, tPosX, CFG.PADDING, true){

                @Override
                protected void buildElementHover() {
                    ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                    ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                    nData.add(new MenuElement_Hover_v2_Element_Type_Flag(this.getCurrent()));
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.game.getCiv(this.getCurrent()).getCivName(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                    nElements.add(new MenuElement_Hover_v2_Element2(nData));
                    nData.clear();
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Provinces") + ": "));
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + CFG.game.getCiv(this.getCurrent()).getNumOfProvinces(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                    nElements.add(new MenuElement_Hover_v2_Element2(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover_v2(nElements);
                }
            });
            tPosX += ImageManager.getImage(Images.top_flag_frame).getWidth() + CFG.PADDING;
            this.lCivsTags.add(CFG.game.getCiv(i).getCivTag());
        }
        CFG.glyphLayout.setText(CFG.fontMain, CFG.langManager.get("SelectCivilization"));
        tPosX = 0;
        tPosX = CFG.glyphLayout.width + (float)(CFG.PADDING * 4) > (float)CFG.BUTTON_WIDTH ? (int)(CFG.glyphLayout.width + (float)(CFG.PADDING * 4)) : CFG.BUTTON_WIDTH + CFG.PADDING * 4;
        menuElements.add(new Button_Transparent(0, 0, ((MenuElement)menuElements.get(menuElements.size() - 1)).getPosX() + ((MenuElement)menuElements.get(menuElements.size() - 1)).getWidth(), CFG.BUTTON_HEIGHT + CFG.PADDING * 2, true));
        this.initMenu(null, tPosX + CFG.PADDING * 2, CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING * 2, CFG.GAME_WIDTH - CFG.map.getMapBG().getMinimapWidth() - tPosX - CFG.PADDING * 2, CFG.BUTTON_HEIGHT + CFG.PADDING * 2, menuElements, true, false);
        this.updateLanguage();
    }

    @Override
    protected void updateLanguage() {
    }

    @Override
    protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        ImageManager.getImage(Images.editor_line).draw2(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.editor_line).getHeight() + iTranslateY, this.getWidth(), CFG.BUTTON_HEIGHT + CFG.PADDING * 2);
        oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.65f));
        ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth(), 1);
        oSB.setColor(Color.WHITE);
        this.beginClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        try {
            for (int i = 0; i < this.getMenuElementsSize(); ++i) {
                block10: {
                    if (!this.getMenuElement(i).getIsInView()) continue;
                    try {
                        this.lFlags.get(this.getFlagID(i)).draw(oSB, this.getMenuPosX() + this.getMenuElement(i).getPosX() + iTranslateX, this.getPosY() + CFG.BUTTON_HEIGHT / 2 - ImageManager.getImage(Images.top_flag_frame).getHeight() / 2 - this.lFlags.get(this.getFlagID(i)).getHeight() + iTranslateY, ImageManager.getImage(Images.top_flag_frame).getWidth(), ImageManager.getImage(Images.top_flag_frame).getHeight());
                    }
                    catch (NullPointerException ex) {
                        if (CFG.game.getCiv(i).getCivTag().equals("ran")) {
                            oSB.setColor(new Color((float)CFG.game.getCiv(i).getR() / 255.0f, (float)CFG.game.getCiv(i).getG() / 255.0f, (float)CFG.game.getCiv(i).getB() / 255.0f, 1.0f));
                            CFG.game.getCiv(i).getFlag().draw(oSB, this.getMenuPosX() + this.getMenuElement(i).getPosX() + iTranslateX, this.getPosY() + CFG.BUTTON_HEIGHT / 2 - ImageManager.getImage(Images.top_flag_frame).getHeight() / 2 + iTranslateY - CFG.game.getCiv(i).getFlag().getHeight(), ImageManager.getImage(Images.top_flag_frame).getWidth(), ImageManager.getImage(Images.top_flag_frame).getHeight());
                            oSB.setColor(Color.WHITE);
                        } else {
                            CFG.game.getCiv(i).getFlag().draw(oSB, this.getMenuPosX() + this.getMenuElement(i).getPosX() + iTranslateX, this.getPosY() + CFG.BUTTON_HEIGHT / 2 - ImageManager.getImage(Images.top_flag_frame).getHeight() / 2 + iTranslateY - CFG.game.getCiv(i).getFlag().getHeight(), ImageManager.getImage(Images.top_flag_frame).getWidth(), ImageManager.getImage(Images.top_flag_frame).getHeight());
                        }
                        if (!this.getMenuElement(i).getIsHovered()) break block10;
                        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.0375f));
                        ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getMenuPosX() + this.getMenuElement(i).getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + CFG.BUTTON_HEIGHT / 2 - ImageManager.getImage(Images.top_flag_frame).getHeight() / 2 + iTranslateY, ImageManager.getImage(Images.top_flag_frame).getWidth(), ImageManager.getImage(Images.top_flag_frame).getHeight());
                        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.575f));
                        ImageManager.getImage(Images.gradient).draw(oSB, this.getMenuPosX() + this.getMenuElement(i).getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + CFG.BUTTON_HEIGHT / 2 - ImageManager.getImage(Images.top_flag_frame).getHeight() / 2 + iTranslateY, ImageManager.getImage(Images.top_flag_frame).getWidth(), ImageManager.getImage(Images.top_flag_frame).getHeight() / 4);
                        ImageManager.getImage(Images.gradient).draw(oSB, this.getMenuPosX() + this.getMenuElement(i).getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + ImageManager.getImage(Images.top_flag_frame).getHeight() - ImageManager.getImage(Images.top_flag_frame).getHeight() / 4 + CFG.BUTTON_HEIGHT / 2 - ImageManager.getImage(Images.top_flag_frame).getHeight() / 2 + iTranslateY, ImageManager.getImage(Images.top_flag_frame).getWidth(), ImageManager.getImage(Images.top_flag_frame).getHeight() / 4, false, true);
                        oSB.setColor(Color.WHITE);
                    }
                }
                if (this.getMenuElement(i).getIsHovered() || i == CFG.iCreateScenario_AssignProvinces_Civ) {
                    ImageManager.getImage(Images.top_flag_frame_h).draw(oSB, this.getMenuPosX() + this.getMenuElement(i).getPosX() + iTranslateX, this.getPosY() + CFG.BUTTON_HEIGHT / 2 - ImageManager.getImage(Images.top_flag_frame).getHeight() / 2 + iTranslateY);
                } else {
                    ImageManager.getImage(Images.top_flag_frame).draw(oSB, this.getMenuPosX() + this.getMenuElement(i).getPosX() + iTranslateX, this.getPosY() + CFG.BUTTON_HEIGHT / 2 - ImageManager.getImage(Images.top_flag_frame).getHeight() / 2 + iTranslateY);
                }
                oSB.setColor(new Color((float)CFG.game.getCiv(i).getR() / 255.0f, (float)CFG.game.getCiv(i).getG() / 255.0f, (float)CFG.game.getCiv(i).getB() / 255.0f, 1.0f));
                ImageManager.getImage(Images.gradient).draw(oSB, this.getMenuPosX() + this.getMenuElement(i).getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + CFG.BUTTON_HEIGHT / 2 + ImageManager.getImage(Images.top_flag_frame).getHeight() / 2 + iTranslateY, ImageManager.getImage(Images.top_flag_frame).getWidth(), CFG.CIV_COLOR_WIDTH);
                ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getMenuPosX() + this.getMenuElement(i).getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() + CFG.BUTTON_HEIGHT / 2 + ImageManager.getImage(Images.top_flag_frame).getHeight() / 2 + iTranslateY, ImageManager.getImage(Images.top_flag_frame).getWidth(), 1);
                oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.475f));
                ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getMenuPosX() + this.getMenuElement(i).getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + CFG.BUTTON_HEIGHT / 2 + ImageManager.getImage(Images.top_flag_frame).getHeight() / 2 + iTranslateY, ImageManager.getImage(Images.top_flag_frame).getWidth() / 4, CFG.CIV_COLOR_WIDTH);
                ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getMenuPosX() + ImageManager.getImage(Images.top_flag_frame).getWidth() - ImageManager.getImage(Images.top_flag_frame).getWidth() / 4 + this.getMenuElement(i).getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + CFG.BUTTON_HEIGHT / 2 + ImageManager.getImage(Images.top_flag_frame).getHeight() / 2 + iTranslateY, ImageManager.getImage(Images.top_flag_frame).getWidth() / 4, CFG.CIV_COLOR_WIDTH, true, false);
                oSB.setColor(CFG.COLOR_FLAG_FRAME);
                ImageManager.getImage(Images.gradient).draw(oSB, this.getMenuPosX() + this.getMenuElement(i).getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + CFG.BUTTON_HEIGHT / 2 + ImageManager.getImage(Images.top_flag_frame).getHeight() / 2 + iTranslateY, 1, CFG.CIV_COLOR_WIDTH);
                ImageManager.getImage(Images.gradient).draw(oSB, this.getMenuPosX() + ImageManager.getImage(Images.top_flag_frame).getWidth() - 1 + this.getMenuElement(i).getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + CFG.BUTTON_HEIGHT / 2 + ImageManager.getImage(Images.top_flag_frame).getHeight() / 2 + iTranslateY, 1, CFG.CIV_COLOR_WIDTH);
                oSB.setColor(Color.WHITE);
            }
        }
        catch (IndexOutOfBoundsException indexOutOfBoundsException) {
        }
        catch (NullPointerException nullPointerException) {
            // empty catch block
        }
        this.endClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.6f));
        ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + 1 + iTranslateY, CFG.PADDING * 2, this.getHeight() - 1);
        ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + this.getWidth() - CFG.PADDING * 2 + iTranslateX, this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + 1 + iTranslateY, CFG.PADDING * 2, this.getHeight() - 1, true, false);
        oSB.setColor(CFG.COLOR_MINIMAP_BORDER);
        ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, 1, this.getHeight());
        oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.7f));
        ImageManager.getImage(Images.gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY, 1, this.getHeight() / 2, false, true);
        ImageManager.getImage(Images.gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + this.getHeight() - this.getHeight() / 2 + iTranslateY, 1, this.getHeight() / 2, false, false);
        oSB.setColor(Color.WHITE);
    }

    @Override
    protected void updateMenuElements_IsInView() {
        super.updateMenuElements_IsInView();
        for (int i = 0; i < this.getMenuElementsSize() - 1; ++i) {
            int tempTagID = this.getIsLoaded(this.lCivsTags.get(i));
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

    private final int getIsLoaded(String nCivTag) {
        for (int i = 0; i < this.lLoadedFlags_TagsIDs.size(); ++i) {
            if (!this.lCivsTags.get(this.lLoadedFlags_TagsIDs.get(i)).equals(nCivTag)) continue;
            return i;
        }
        return -1;
    }

    private final int getFlagID(int nCivTagID) {
        for (int i = 0; i < this.lLoadedFlags_TagsIDs.size(); ++i) {
            if (this.lLoadedFlags_TagsIDs.get(i) != nCivTagID) continue;
            return i;
        }
        return 0;
    }

    private final void loadFlag(int nCivTagID) {
        block10: {
            try {
                try {
                    this.lFlags.add(new Image(new Texture(Gdx.files.internal("game/flagsH/" + this.lCivsTags.get(nCivTagID) + ".png")), Texture.TextureFilter.Linear));
                }
                catch (GdxRuntimeException e) {
                    try {
                        this.lFlags.add(new Image(new Texture(Gdx.files.internal("game/flagsH/" + CFG.ideologiesManager.getRealTag(this.lCivsTags.get(nCivTagID)) + ".png")), Texture.TextureFilter.Linear));
                    }
                    catch (GdxRuntimeException ex) {
                        if (CFG.isAndroid()) {
                            try {
                                this.lFlags.add(new Image(new Texture(Gdx.files.local("game/civilizations_editor/" + CFG.ideologiesManager.getRealTag(this.lCivsTags.get(nCivTagID)) + "/" + CFG.ideologiesManager.getRealTag(this.lCivsTags.get(nCivTagID)) + "_FLH.png")), Texture.TextureFilter.Linear));
                            }
                            catch (GdxRuntimeException erq) {
                                this.lFlags.add(new Image(new Texture(Gdx.files.internal("game/civilizations_editor/" + CFG.ideologiesManager.getRealTag(this.lCivsTags.get(nCivTagID)) + "/" + CFG.ideologiesManager.getRealTag(this.lCivsTags.get(nCivTagID)) + "_FLH.png")), Texture.TextureFilter.Linear));
                            }
                            break block10;
                        }
                        this.lFlags.add(new Image(new Texture(Gdx.files.internal("game/civilizations_editor/" + CFG.ideologiesManager.getRealTag(this.lCivsTags.get(nCivTagID)) + "/" + CFG.ideologiesManager.getRealTag(this.lCivsTags.get(nCivTagID)) + "_FLH.png")), Texture.TextureFilter.Linear));
                    }
                }
            }
            catch (GdxRuntimeException ex) {
                this.lFlags.add(null);
            }
            catch (OutOfMemoryError e) {
                this.lFlags.add(null);
            }
        }
        this.lLoadedFlags_TagsIDs.add(nCivTagID);
    }

    @Override
    protected final void actionElement(int iID) {
        if (iID == this.getMenuElementsSize() - 1) {
            return;
        }
        if (CFG.iCreateScenario_AssignProvinces_Civ != this.getMenuElement(iID).getCurrent()) {
            CFG.game.disableDrawCivilizationRegions(CFG.iCreateScenario_AssignProvinces_Civ);
            CFG.game.enableDrawCivilizationRegions(this.getMenuElement(iID).getCurrent(), 0);
        } else if (this.getMenuElement(iID).getCurrent() > 0) {
            CFG.map.getMapCoordinates().centerToProvinceID(CFG.game.getCiv(this.getMenuElement(iID).getCurrent()).getCapitalProvinceID());
        }
        CFG.iCreateScenario_AssignProvinces_Civ = this.getMenuElement(iID).getCurrent();
    }

    @Override
    protected void onBackPressed() {
        try {
            for (int i = 0; i < this.lFlags.size(); ++i) {
                this.lFlags.get(i).getTexture().dispose();
            }
            this.lFlags.clear();
            this.lLoadedFlags_TagsIDs.clear();
            this.lCivsTags.clear();
        }
        catch (NullPointerException nullPointerException) {
            // empty catch block
        }
    }
}

