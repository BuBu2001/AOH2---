/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button_Menu;
import age.of.civilizations2.jakowski.lukasz.Button_Menu_Classic_Wiki;
import age.of.civilizations2.jakowski.lukasz.Button_Menu_LR_Line;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Dialog;
import age.of.civilizations2.jakowski.lukasz.Event_SelectCivAction;
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
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.GdxRuntimeException;
import java.util.ArrayList;
import java.util.List;

class Menu_CreateScenario_Events_SelectCiv_List
extends SliderMenu {
    private List<String> lCivsTags = null;
    private List<Image> lFlags = new ArrayList<Image>();
    private List<Integer> lLoadedFlags_TagsIDs = new ArrayList<Integer>();

    protected Menu_CreateScenario_Events_SelectCiv_List() {
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        this.lCivsTags = new ArrayList<String>();
        ArrayList<String> lTempNames = new ArrayList<String>();
        ArrayList<String> lTempTags = new ArrayList<String>();
        menuElements.add(new Button_Menu_LR_Line(CFG.langManager.get("AddNewCivilization"), -1, 0, CFG.PADDING, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT, true));
        if (CFG.sSearch != null && CFG.sSearch.length() > 0) {
            for (int i = 0; i < CFG.game.getCivsSize(); ++i) {
                if (CFG.langManager.getCiv(CFG.game.getCiv(i).getCivName()).toLowerCase().indexOf(CFG.sSearch.toLowerCase()) < 0) continue;
                lTempNames.add(CFG.langManager.getCiv(CFG.game.getCiv(i).getCivName()));
                lTempTags.add(CFG.game.getCiv(i).getCivTag());
            }
            int nPosY = 0;
            int tID = 0;
            while (lTempNames.size() > 0) {
                int toAddID = 0;
                for (int i = 1; i < lTempNames.size(); ++i) {
                    if (!CFG.compareAlphabetic_TwoString((String)lTempNames.get(toAddID), (String)lTempNames.get(i))) continue;
                    toAddID = i;
                }
                menuElements.add(new Button_Menu(CFG.langManager.getCiv((String)lTempTags.get(toAddID)), (int)(50.0f * CFG.GUI_SCALE), 0, CFG.BUTTON_HEIGHT * (nPosY + 1) + CFG.PADDING * (nPosY + 2), CFG.GAME_WIDTH - (CFG.BUTTON_WIDTH + CFG.BUTTON_WIDTH / 2), CFG.BUTTON_HEIGHT, true));
                menuElements.add(new Button_Menu_Classic_Wiki(tID++, CFG.GAME_WIDTH - (CFG.BUTTON_WIDTH + CFG.BUTTON_WIDTH / 2), CFG.BUTTON_HEIGHT * (nPosY + 1) + CFG.PADDING * (nPosY + 2), CFG.BUTTON_WIDTH + CFG.BUTTON_WIDTH / 2, CFG.BUTTON_HEIGHT, true){

                    @Override
                    protected void buildElementHover() {
                        try {
                            ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                            ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Wiki") + ": "));
                            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.getWikiInormationsLink_Clear((String)Menu_CreateScenario_Events_SelectCiv_List.this.lCivsTags.get(this.getTextPos())), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                            nElements.add(new MenuElement_Hover_v2_Element2(nData));
                            nData.clear();
                            this.menuElementHover = new MenuElement_Hover_v2(nElements);
                        }
                        catch (IndexOutOfBoundsException ex) {
                            super.buildElementHover();
                        }
                    }
                });
                this.lCivsTags.add((String)lTempTags.get(toAddID));
                lTempNames.remove(toAddID);
                lTempTags.remove(toAddID);
                ++nPosY;
            }
        } else if (CFG.chosen_AlphabetCharachter == null) {
            for (int i = 0; i < CFG.game.getCivsSize(); ++i) {
                lTempNames.add(CFG.langManager.getCiv(CFG.game.getCiv(i).getCivName()));
                lTempTags.add(CFG.game.getCiv(i).getCivTag());
            }
            int nPosY = 0;
            int tID = 0;
            while (lTempNames.size() > 0) {
                int toAddID = 0;
                for (int i = 1; i < lTempNames.size(); ++i) {
                    if (!CFG.compareAlphabetic_TwoString((String)lTempNames.get(toAddID), (String)lTempNames.get(i))) continue;
                    toAddID = i;
                }
                menuElements.add(new Button_Menu(CFG.langManager.getCiv((String)lTempTags.get(toAddID)), (int)(50.0f * CFG.GUI_SCALE), 0, CFG.BUTTON_HEIGHT * (nPosY + 1) + CFG.PADDING * (nPosY + 2), CFG.GAME_WIDTH - (CFG.BUTTON_WIDTH + CFG.BUTTON_WIDTH / 2), CFG.BUTTON_HEIGHT, true));
                menuElements.add(new Button_Menu_Classic_Wiki(tID++, CFG.GAME_WIDTH - (CFG.BUTTON_WIDTH + CFG.BUTTON_WIDTH / 2), CFG.BUTTON_HEIGHT * (nPosY + 1) + CFG.PADDING * (nPosY + 2), CFG.BUTTON_WIDTH + CFG.BUTTON_WIDTH / 2, CFG.BUTTON_HEIGHT, true){

                    @Override
                    protected void buildElementHover() {
                        try {
                            ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                            ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Wiki") + ": "));
                            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.getWikiInormationsLink_Clear((String)Menu_CreateScenario_Events_SelectCiv_List.this.lCivsTags.get(this.getTextPos())), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                            nElements.add(new MenuElement_Hover_v2_Element2(nData));
                            nData.clear();
                            this.menuElementHover = new MenuElement_Hover_v2(nElements);
                        }
                        catch (IndexOutOfBoundsException ex) {
                            super.buildElementHover();
                        }
                    }
                });
                this.lCivsTags.add((String)lTempTags.get(toAddID));
                lTempNames.remove(toAddID);
                lTempTags.remove(toAddID);
                ++nPosY;
            }
        } else {
            for (int i = 0; i < CFG.game.getCivsSize(); ++i) {
                if (CFG.langManager.getCiv(CFG.game.getCiv(i).getCivName()).charAt(0) != CFG.chosen_AlphabetCharachter.charAt(0)) continue;
                lTempNames.add(CFG.langManager.getCiv(CFG.game.getCiv(i).getCivName()));
                lTempTags.add(CFG.game.getCiv(i).getCivTag());
            }
            int nPosY = 0;
            int tID = 0;
            while (lTempNames.size() > 0) {
                int toAddID = 0;
                for (int i = 1; i < lTempNames.size(); ++i) {
                    if (!CFG.compareAlphabetic_TwoString((String)lTempNames.get(toAddID), (String)lTempNames.get(i))) continue;
                    toAddID = i;
                }
                menuElements.add(new Button_Menu(CFG.langManager.getCiv((String)lTempTags.get(toAddID)), (int)(50.0f * CFG.GUI_SCALE), 0, CFG.BUTTON_HEIGHT * (nPosY + 1) + CFG.PADDING * (nPosY + 2), CFG.GAME_WIDTH - (CFG.BUTTON_WIDTH + CFG.BUTTON_WIDTH / 2), CFG.BUTTON_HEIGHT, true));
                menuElements.add(new Button_Menu_Classic_Wiki(tID++, CFG.GAME_WIDTH - (CFG.BUTTON_WIDTH + CFG.BUTTON_WIDTH / 2), CFG.BUTTON_HEIGHT * (nPosY + 1) + CFG.PADDING * (nPosY + 2), CFG.BUTTON_WIDTH + CFG.BUTTON_WIDTH / 2, CFG.BUTTON_HEIGHT, true){

                    @Override
                    protected void buildElementHover() {
                        try {
                            ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                            ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Wiki") + ": "));
                            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.getWikiInormationsLink_Clear((String)Menu_CreateScenario_Events_SelectCiv_List.this.lCivsTags.get(this.getTextPos())), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                            nElements.add(new MenuElement_Hover_v2_Element2(nData));
                            nData.clear();
                            this.menuElementHover = new MenuElement_Hover_v2(nElements);
                        }
                        catch (IndexOutOfBoundsException ex) {
                            super.buildElementHover();
                        }
                    }
                });
                this.lCivsTags.add((String)lTempTags.get(toAddID));
                lTempNames.remove(toAddID);
                lTempTags.remove(toAddID);
                ++nPosY;
            }
        }
        this.initMenu(null, 0, CFG.BUTTON_HEIGHT * 3 / 4 + CFG.BUTTON_HEIGHT + CFG.PADDING, CFG.GAME_WIDTH, CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT * 3 / 4 - CFG.BUTTON_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING * 2, menuElements, true, false);
        this.updateLanguage();
    }

    @Override
    protected void updateLanguage() {
    }

    @Override
    protected void updateMenuElements_IsInView() {
        int tempRandomButton;
        super.updateMenuElements_IsInView();
        for (int i = tempRandomButton = 1; i < this.getMenuElementsSize(); i += 2) {
            int tempTagID = this.getIsLoaded(this.lCivsTags.get((i - tempRandomButton) / 2));
            if (this.getMenuElement(i).getIsInView()) {
                if (tempTagID >= 0) continue;
                this.loadFlag((i - tempRandomButton) / 2);
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
        block9: {
            try {
                try {
                    this.lFlags.add(new Image(new Texture(Gdx.files.internal("game/flags/" + this.lCivsTags.get(nCivTagID) + ".png")), Texture.TextureFilter.Nearest));
                }
                catch (GdxRuntimeException e) {
                    try {
                        this.lFlags.add(new Image(new Texture(Gdx.files.internal("game/flags/" + CFG.ideologiesManager.getRealTag(this.lCivsTags.get(nCivTagID)) + ".png")), Texture.TextureFilter.Nearest));
                    }
                    catch (GdxRuntimeException ex) {
                        if (CFG.isAndroid()) {
                            try {
                                this.lFlags.add(new Image(new Texture(Gdx.files.local("game/civilizations_editor/" + this.lCivsTags.get(nCivTagID) + "/" + this.lCivsTags.get(nCivTagID) + "_FL.png")), Texture.TextureFilter.Nearest));
                            }
                            catch (GdxRuntimeException erq) {
                                this.lFlags.add(new Image(new Texture(Gdx.files.internal("game/civilizations_editor/" + this.lCivsTags.get(nCivTagID) + "/" + this.lCivsTags.get(nCivTagID) + "_FL.png")), Texture.TextureFilter.Nearest));
                            }
                            break block9;
                        }
                        this.lFlags.add(new Image(new Texture(Gdx.files.internal("game/civilizations_editor/" + this.lCivsTags.get(nCivTagID) + "/" + this.lCivsTags.get(nCivTagID) + "_FL.png")), Texture.TextureFilter.Nearest));
                    }
                }
            }
            catch (GdxRuntimeException e) {
                this.lFlags.add(new Image(new Texture(Gdx.files.internal("game/flags/ran.png")), Texture.TextureFilter.Nearest));
            }
        }
        this.lLoadedFlags_TagsIDs.add(nCivTagID);
    }

    @Override
    protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        super.beginClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        super.drawMenu(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        int tempRandomButton = 1;
        try {
            for (int i = tempRandomButton; i < this.getMenuElementsSize(); i += 2) {
                if (!this.getMenuElement(i).getIsInView()) continue;
                this.lFlags.get(this.getFlagID((i - tempRandomButton) / 2)).draw(oSB, this.getMenuElement(i).getPosX() + this.getMenuElement(i).getTextPos() / 2 - CFG.CIV_FLAG_WIDTH / 2 + iTranslateX, this.getMenuElement(i).getPosY() + this.getMenuPosY() + this.getMenuElement(i).getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 - this.lFlags.get(this.getFlagID((i - tempRandomButton) / 2)).getHeight() + iTranslateY, CFG.CIV_FLAG_WIDTH, CFG.CIV_FLAG_HEIGHT);
                ImageManager.getImage(Images.flag_rect).draw(oSB, this.getMenuElement(i).getPosX() + this.getMenuElement(i).getTextPos() / 2 - CFG.CIV_FLAG_WIDTH / 2 + iTranslateX, this.getMenuElement(i).getPosY() + this.getMenuPosY() + this.getMenuElement(i).getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 + iTranslateY);
            }
        }
        catch (IndexOutOfBoundsException indexOutOfBoundsException) {
        }
        catch (NullPointerException nullPointerException) {
            // empty catch block
        }
        super.endClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @Override
    protected final void actionElement(int iID) {
        switch (iID) {
            case 0: {
                CFG.menuManager.setViewID(Menu.eCREATE_SCENARIO_EVENTS_ADD_CIV);
                return;
            }
        }
        if (--iID % 2 == 0) {
            CFG.chosen_AlphabetCharachter = null;
            CFG.sSearch = null;
            if (CFG.eventsManager.eSelectCivAction == Event_SelectCivAction.OUT_SELECTCIV_FORMCIV2) {
                if (this.lCivsTags.get(iID / 2).equals(CFG.game.getCiv(0).getCivTag())) return;
                CFG.eventsManager.lCreateScenario_Event.lDecisions.get((int)CFG.eventsManager.iCreateEvent_EditTriggerID).lOutcomes.get(CFG.eventsManager.iCreateEvent_EditConditionID).setText(this.lCivsTags.get(iID / 2));
            } else {
                int tID = this.getCivID(this.lCivsTags.get(iID / 2));
                CFG.eventsManager.selectCivAction(tID);
            }
            this.onBack();
            this.onBackPressed();
            return;
        } else {
            CFG.EDITOR_ACTIVE_GAMEDATA_TAG = this.lCivsTags.get(iID / 2);
            CFG.setDialogType(Dialog.GO_TO_WIKI);
        }
    }

    private final int getCivID(String nTag) {
        for (int i = 1; i < CFG.game.getCivsSize(); ++i) {
            if (!CFG.game.getCiv(i).getCivTag().equals(nTag)) continue;
            return i;
        }
        return 0;
    }

    private final void onBack() {
        CFG.eventsManager.selectCivBack();
    }

    @Override
    protected void onBackPressed() {
        for (int i = 0; i < this.lFlags.size(); ++i) {
            this.lFlags.get(i).getTexture().dispose();
        }
        this.lFlags.clear();
        this.lLoadedFlags_TagsIDs.clear();
        this.lCivsTags.clear();
    }
}

