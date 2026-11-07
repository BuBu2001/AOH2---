/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button_Menu_Active;
import age.of.civilizations2.jakowski.lukasz.Button_Menu_Classic;
import age.of.civilizations2.jakowski.lukasz.Button_Menu_Classic_Search;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Menu;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Text;
import age.of.civilizations2.jakowski.lukasz.SliderMenu;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.GdxRuntimeException;
import java.util.ArrayList;
import java.util.List;

class Menu_MapEditor_FormableCivs_SelectFormable_Alphabet
extends SliderMenu {
    private List<Character> lCharacters;
    private String nSearch = null;

    protected Menu_MapEditor_FormableCivs_SelectFormable_Alphabet() {
        int a;
        boolean addChar;
        int j;
        boolean add;
        int i;
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        this.nSearch = CFG.langManager.get("Search");
        menuElements.add(new Button_Menu_Classic_Search("", CFG.PADDING * 2, 0, CFG.PADDING, CFG.BUTTON_WIDTH * 2, CFG.BUTTON_HEIGHT, true){

            @Override
            protected void buildElementHover() {
                ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Search"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }

            @Override
            protected String getTextToDraw() {
                return Menu_MapEditor_FormableCivs_SelectFormable_Alphabet.this.nSearch + ": " + super.getTextToDraw();
            }
        });
        if (CFG.chosen_AlphabetCharachter == null) {
            menuElements.add(new Button_Menu_Active(null, -1, CFG.BUTTON_WIDTH * 2, CFG.PADDING, CFG.BUTTON_HEIGHT, CFG.BUTTON_HEIGHT, true){

                @Override
                protected void buildElementHover() {
                    ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                    ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("AllCivilizations"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                    nElements.add(new MenuElement_Hover_v2_Element2(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover_v2(nElements);
                }
            });
        } else {
            menuElements.add(new Button_Menu_Classic(null, -1, CFG.BUTTON_WIDTH * 2, CFG.PADDING, CFG.BUTTON_HEIGHT, CFG.BUTTON_HEIGHT, true){

                @Override
                protected void buildElementHover() {
                    ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                    ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                    nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("AllCivilizations"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                    nElements.add(new MenuElement_Hover_v2_Element2(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover_v2(nElements);
                }
            });
        }
        String tempTADDED = null;
        String[] tagsSPLITED_ADDED = new String[]{};
        int tagsSPLITED_ADDEDLength = 0;
        try {
            FileHandle tempFileTADDED = CFG.readLocalFiles() ? Gdx.files.local("map/" + CFG.map.getFile_ActiveMap_Path() + "formable_civs/" + "Age_of_Civilizations") : Gdx.files.internal("map/" + CFG.map.getFile_ActiveMap_Path() + "formable_civs/" + "Age_of_Civilizations");
            tempTADDED = tempFileTADDED.readString();
            tagsSPLITED_ADDED = tempTADDED.split(";");
            tagsSPLITED_ADDEDLength = tagsSPLITED_ADDED.length;
        }
        catch (GdxRuntimeException tempFileTADDED) {
            // empty catch block
        }
        FileHandle tempFileT = Gdx.files.internal("game/civilizations/Age_of_Civilizations");
        String tempT = tempFileT.readString();
        String[] tagsSPLITED = tempT.split(";");
        String[] tagsSPLITED_ED = new String[]{};
        try {
            FileHandle tempFileT_ED = null;
            tempFileT_ED = CFG.isAndroid() ? Gdx.files.local("game/civilizations_editor/Age_of_Civilizations") : Gdx.files.internal("game/civilizations_editor/Age_of_Civilizations");
            String tempT_ED = tempFileT_ED.readString();
            tagsSPLITED_ED = tempT_ED.split(";");
        }
        catch (GdxRuntimeException tempFileT_ED) {
            // empty catch block
        }
        this.lCharacters = new ArrayList<Character>();
        int iSize = tagsSPLITED.length;
        for (i = 0; i < iSize; ++i) {
            if (CFG.isInFormableCivs(tagsSPLITED[i])) continue;
            add = true;
            for (j = 0; j < tagsSPLITED_ADDEDLength; ++j) {
                if (!tagsSPLITED_ADDED[j].equals(tagsSPLITED[i])) continue;
                add = false;
                break;
            }
            if (!add) continue;
            addChar = true;
            for (a = 0; a < this.lCharacters.size(); ++a) {
                if (this.lCharacters.get(a).charValue() != CFG.langManager.getCiv(tagsSPLITED[i]).charAt(0)) continue;
                addChar = false;
                break;
            }
            if (!addChar) continue;
            this.lCharacters.add(Character.valueOf(CFG.langManager.getCiv(tagsSPLITED[i]).charAt(0)));
        }
        iSize = tagsSPLITED_ED.length;
        for (i = 0; i < iSize; ++i) {
            if (CFG.isInFormableCivs(tagsSPLITED_ED[i])) continue;
            add = true;
            for (j = 0; j < tagsSPLITED_ADDEDLength; ++j) {
                if (!tagsSPLITED_ADDED[j].equals(tagsSPLITED_ED[i])) continue;
                add = false;
                break;
            }
            if (!add) continue;
            addChar = true;
            for (a = 0; a < this.lCharacters.size(); ++a) {
                if (this.lCharacters.get(a).charValue() != CFG.langManager.getCiv(tagsSPLITED_ED[i]).charAt(0)) continue;
                addChar = false;
                break;
            }
            if (!addChar) continue;
            this.lCharacters.add(Character.valueOf(CFG.langManager.getCiv(tagsSPLITED_ED[i]).charAt(0)));
        }
        for (i = 0; i < this.lCharacters.size() - 1; ++i) {
            for (int j2 = i + 1; j2 < this.lCharacters.size(); ++j2) {
                if (this.lCharacters.get(i).charValue() <= this.lCharacters.get(j2).charValue()) continue;
                char temp = this.lCharacters.get(i).charValue();
                this.lCharacters.set(i, this.lCharacters.get(j2));
                this.lCharacters.set(j2, Character.valueOf(temp));
            }
        }
        for (i = 0; i < this.lCharacters.size(); ++i) {
            if (CFG.chosen_AlphabetCharachter != null && this.lCharacters.get(i).charValue() == CFG.chosen_AlphabetCharachter.charAt(0)) {
                menuElements.add(new Button_Menu_Active("[" + this.lCharacters.get(i) + "]", -1, CFG.BUTTON_HEIGHT * (i + 1) + CFG.BUTTON_WIDTH * 2, CFG.PADDING, CFG.BUTTON_HEIGHT, CFG.BUTTON_HEIGHT, true));
                continue;
            }
            menuElements.add(new Button_Menu_Classic("[" + this.lCharacters.get(i) + "]", -1, CFG.BUTTON_HEIGHT * (i + 1) + CFG.BUTTON_WIDTH * 2, CFG.PADDING, CFG.BUTTON_HEIGHT, CFG.BUTTON_HEIGHT, true));
        }
        if (((MenuElement)menuElements.get(menuElements.size() - 1)).getPosX() + ((MenuElement)menuElements.get(menuElements.size() - 1)).getWidth() < CFG.GAME_WIDTH) {
            int tempElementWidth = (CFG.GAME_WIDTH - CFG.BUTTON_WIDTH * 2) / (menuElements.size() - 1);
            int tempPosX = 0;
            for (int i2 = 0; i2 < menuElements.size() - 1; ++i2) {
                if (i2 == 0) {
                    ((MenuElement)menuElements.get(i2)).setPosX(tempPosX);
                    ((MenuElement)menuElements.get(i2)).setWidth(CFG.BUTTON_WIDTH * 2);
                    tempPosX += ((MenuElement)menuElements.get(i2)).getWidth();
                    continue;
                }
                ((MenuElement)menuElements.get(i2)).setPosX(tempPosX);
                ((MenuElement)menuElements.get(i2)).setWidth(tempElementWidth);
                tempPosX += ((MenuElement)menuElements.get(i2)).getWidth();
            }
            ((MenuElement)menuElements.get(menuElements.size() - 1)).setPosX(tempPosX);
            ((MenuElement)menuElements.get(menuElements.size() - 1)).setWidth(CFG.GAME_WIDTH - tempPosX);
        }
        this.initMenu(null, 0, CFG.BUTTON_HEIGHT * 3 / 4, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT + CFG.PADDING * 2, menuElements, true, false);
        this.updateLanguage();
    }

    @Override
    protected void updateLanguage() {
        if (CFG.sSearch != null) {
            this.getMenuElement(0).setText(CFG.sSearch);
        }
        this.getMenuElement(1).setText("[" + CFG.langManager.get("ALL") + "]");
    }

    @Override
    protected final void actionElement(int iID) {
        switch (iID) {
            case 0: {
                CFG.showKeyboard();
                return;
            }
            case 1: {
                if (CFG.chosen_AlphabetCharachter != null || CFG.sSearch != null) {
                    CFG.chosen_AlphabetCharachter = null;
                    CFG.sSearch = null;
                    CFG.menuManager.setViewID(Menu.eMAP_EDITOR_FORMABLE_CIVS_SELECT_FORMABLE);
                }
                return;
            }
        }
        if (CFG.chosen_AlphabetCharachter == null || CFG.sSearch != null || CFG.chosen_AlphabetCharachter.charAt(0) != this.lCharacters.get(iID - 2).charValue()) {
            CFG.chosen_AlphabetCharachter = "" + this.lCharacters.get(iID - 2);
            CFG.sSearch = null;
            CFG.menuManager.setViewID(Menu.eMAP_EDITOR_FORMABLE_CIVS_SELECT_FORMABLE);
        }
    }
}

