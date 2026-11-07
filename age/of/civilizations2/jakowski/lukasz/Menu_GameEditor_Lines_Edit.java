/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button_Menu;
import age.of.civilizations2.jakowski.lukasz.Button_Menu_ReflectedBG;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Menu;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.SliderMenu;
import age.of.civilizations2.jakowski.lukasz.SliderMenuTitle;
import java.util.ArrayList;

class Menu_GameEditor_Lines_Edit
extends SliderMenu {
    private String sName = null;

    protected Menu_GameEditor_Lines_Edit() {
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        menuElements.add(new Button_Menu(null, -1, 0, 0, CFG.GAME_WIDTH / 2, CFG.BUTTON_HEIGHT, true));
        menuElements.add(new Button_Menu("", (int)(50.0f * CFG.GUI_SCALE), 0, CFG.PADDING, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT, true){

            @Override
            protected String getTextToDraw() {
                return Menu_GameEditor_Lines_Edit.this.sName + ": \"" + super.getTextToDraw() + ".png\"";
            }
        });
        menuElements.add(new Button_Menu("", (int)(50.0f * CFG.GUI_SCALE), 0, CFG.PADDING * 2 + CFG.BUTTON_HEIGHT, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT, true, true){

            @Override
            protected boolean getCheckboxState() {
                return CFG.editorLine_GameData.getRapeatImage();
            }

            @Override
            protected String getTextToDraw() {
                return super.getTextToDraw() + ": " + this.getCheckboxState();
            }
        });
        menuElements.add(new Button_Menu("", (int)(50.0f * CFG.GUI_SCALE), 0, CFG.PADDING * 3 + CFG.BUTTON_HEIGHT * 2, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT, true, true){

            @Override
            protected boolean getCheckboxState() {
                return CFG.editorLine_GameData.getFlipX();
            }

            @Override
            protected String getTextToDraw() {
                return super.getTextToDraw() + ": " + this.getCheckboxState();
            }
        });
        menuElements.add(new Button_Menu("", (int)(50.0f * CFG.GUI_SCALE), 0, CFG.PADDING * 4 + CFG.BUTTON_HEIGHT * 3, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT, true, true){

            @Override
            protected boolean getCheckboxState() {
                return CFG.editorLine_GameData.getMovable();
            }

            @Override
            protected String getTextToDraw() {
                return super.getTextToDraw() + ": " + this.getCheckboxState();
            }
        });
        menuElements.add(new Button_Menu_ReflectedBG(null, -1, CFG.GAME_WIDTH / 2, CFG.PADDING, CFG.GAME_WIDTH / 2, CFG.BUTTON_HEIGHT, true));
        this.initMenuWithBackButton(new SliderMenuTitle(null, CFG.BUTTON_HEIGHT * 3 / 4, false, false), 0, CFG.BUTTON_HEIGHT * 3 / 4, CFG.GAME_WIDTH, CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT * 3 / 4, menuElements);
        this.getMenuElement(this.getMenuElementsSize() - 1).setPosY(this.getMenuElement(0).getPosY());
        this.updateLanguage();
    }

    @Override
    protected void updateLanguage() {
        this.sName = CFG.langManager.get("ImageName");
        this.getMenuElement(0).setText(CFG.langManager.get("Back"));
        this.getMenuElement(1).setText(CFG.editorLine_GameData.getImageName());
        this.getMenuElement(2).setText(CFG.langManager.get("Repeat"));
        this.getMenuElement(3).setText(CFG.langManager.get("Flip") + "X");
        this.getMenuElement(4).setText(CFG.langManager.get("Moveable"));
        this.getMenuElement(this.getMenuElementsSize() - 1).setText(CFG.langManager.get("Save"));
        this.getTitle().setText(CFG.langManager.get("AddNewStyle"));
    }

    @Override
    protected final void actionElement(int iID) {
        switch (iID) {
            case 0: {
                this.onBackPressed();
                return;
            }
            case 1: {
                CFG.showKeyboard();
                return;
            }
            case 2: {
                CFG.editorLine_GameData.setReapeatImage(!CFG.editorLine_GameData.getRapeatImage());
                return;
            }
            case 3: {
                CFG.editorLine_GameData.setFlipX(!CFG.editorLine_GameData.getFlipX());
                return;
            }
            case 4: {
                CFG.editorLine_GameData.setMovable(!CFG.editorLine_GameData.getMovable());
                return;
            }
            case 5: {
                if (this.getMenuElement(1).getText().length() > 0) {
                    CFG.editorLine_GameData.setImageName(this.getMenuElement(1).getText());
                    CFG.game.saveLinesData();
                    this.onBackPressed();
                } else {
                    CFG.showKeyboard(1);
                }
                return;
            }
        }
    }

    @Override
    protected void onBackPressed() {
        CFG.menuManager.setViewID(Menu.eGAME_EDITOR_LINES);
        CFG.menuManager.setBackAnimation(true);
    }
}

