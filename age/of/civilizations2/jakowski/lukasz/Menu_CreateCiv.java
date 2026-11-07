/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.AoCGame;
import age.of.civilizations2.jakowski.lukasz.Button_Flag_JustFrame;
import age.of.civilizations2.jakowski.lukasz.Button_Menu;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.ColorPicker_AoC;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Text;
import age.of.civilizations2.jakowski.lukasz.SliderMenu;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.GdxRuntimeException;
import java.util.ArrayList;

public class Menu_CreateCiv
extends SliderMenu {
    private String sName;

    protected Menu_CreateCiv() {
        int tempW = CFG.CIV_INFO_MENU_WIDTH + CFG.CIV_INFO_MENU_WIDTH * 3 / 4;
        int tempH = ImageManager.getImage(Images.top_flag_frame).getHeight() + CFG.PADDING * 4;
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        menuElements.add(new Button_Menu("", 0, ImageManager.getImage(Images.top_flag_frame).getWidth() + CFG.PADDING * 4, CFG.PADDING * 2, tempW - (ImageManager.getImage(Images.top_flag_frame).getWidth() + CFG.PADDING * 4) - CFG.PADDING, tempH - CFG.PADDING * 4, true){

            @Override
            protected String getTextToDraw() {
                return Menu_CreateCiv.this.sName + ": " + super.getTextToDraw();
            }

            @Override
            protected void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
            }

            @Override
            protected void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                CFG.fontMain.getData().setScale(0.95f);
                if (isActive) {
                    CFG.drawText(oSB, this.getTextToDraw(), this.getPosX() + this.textPosition.getTextPosition() + iTranslateX, this.getPosY() + (int)(((float)this.getHeight() - (float)this.iTextHeight * 0.95f) / 2.0f) + iTranslateY, this.getColor(isActive));
                } else {
                    CFG.drawTextWithShadow(oSB, this.getTextToDraw(), this.getPosX() + this.textPosition.getTextPosition() + iTranslateX, this.getPosY() + (int)(((float)this.getHeight() - (float)this.iTextHeight * 0.95f) / 2.0f) + iTranslateY, this.getColor(isActive));
                }
                CFG.fontMain.getData().setScale(1.0f);
            }

            @Override
            protected void buildElementHover() {
                ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("CivilizationName") + (this.getText().length() > 0 ? ": " : "")));
                nData.add(new MenuElement_Hover_v2_Element_Type_Text(this.getText(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
        });
        menuElements.add(new Button_Flag_JustFrame(CFG.PADDING * 2, CFG.PADDING * 2, true){

            @Override
            protected void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
            }
        });
        this.initMenu(null, 0 + AoCGame.LEFT, CFG.BUTTON_HEIGHT / 2, tempW, tempH, menuElements);
        this.updateLanguage();
    }

    @Override
    protected void updateLanguage() {
        block4: {
            this.sName = CFG.langManager.get("Name");
            try {
                try {
                    FileHandle file = CFG.readLocalFiles() ? Gdx.files.local("game/civilizations_editor/" + CFG.EDITOR_ACTIVE_GAMEDATA_TAG + "/" + CFG.EDITOR_ACTIVE_GAMEDATA_TAG + "_NM") : Gdx.files.internal("game/civilizations_editor/" + CFG.EDITOR_ACTIVE_GAMEDATA_TAG + "/" + CFG.EDITOR_ACTIVE_GAMEDATA_TAG + "_NM");
                    this.getMenuElement(0).setText(file.readString());
                }
                catch (GdxRuntimeException ex) {
                    FileHandle file = CFG.readLocalFiles() ? Gdx.files.internal("game/civilizations_editor/" + CFG.EDITOR_ACTIVE_GAMEDATA_TAG + "/" + CFG.EDITOR_ACTIVE_GAMEDATA_TAG + "_NM") : Gdx.files.local("game/civilizations_editor/" + CFG.EDITOR_ACTIVE_GAMEDATA_TAG + "/" + CFG.EDITOR_ACTIVE_GAMEDATA_TAG + "_NM");
                    this.getMenuElement(0).setText(file.readString());
                }
            }
            catch (GdxRuntimeException e) {
                this.getMenuElement(0).setText("");
                if (!CFG.LOGS) break block4;
                CFG.exceptionStack(e);
            }
        }
    }

    @Override
    protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        ImageManager.getImage(Images.dialog_title).draw2(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.dialog_title).getHeight(), this.getWidth() + 2, this.getHeight(), true, false);
        oSB.setColor(new Color((float)CFG.editorCivilization_GameData.getR() / 255.0f, (float)CFG.editorCivilization_GameData.getG() / 255.0f, (float)CFG.editorCivilization_GameData.getB() / 255.0f, 0.165f));
        ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + 2 - ImageManager.getImage(Images.line_32_off1).getHeight(), this.getWidth(), this.getHeight() - 2, false, true);
        oSB.setColor(new Color((float)CFG.editorCivilization_GameData.getR() / 255.0f, (float)CFG.editorCivilization_GameData.getG() / 255.0f, (float)CFG.editorCivilization_GameData.getB() / 255.0f, 0.375f));
        ImageManager.getImage(Images.gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - this.getHeight() * 2 / 3 - ImageManager.getImage(Images.gradient).getHeight(), this.getWidth(), this.getHeight() * 2 / 3, false, true);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.65f));
        ImageManager.getImage(Images.gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - CFG.PADDING - ImageManager.getImage(Images.gradient).getHeight(), this.getWidth(), CFG.PADDING, false, true);
        oSB.setColor(CFG.COLOR_NEW_GAME_EDGE_LINE);
        ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - 1 - ImageManager.getImage(Images.pix255_255_255).getHeight(), this.getWidth(), 1);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.55f));
        ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - 2 - ImageManager.getImage(Images.line_32_off1).getHeight(), this.getWidth(), 1);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.8f));
        ImageManager.getImage(Images.line_32_off1).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - 1 - ImageManager.getImage(Images.line_32_off1).getHeight(), this.getWidth(), 1);
        oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.45f));
        ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - 1 - ImageManager.getImage(Images.slider_gradient).getHeight(), this.getWidth() / 2, 1);
        ImageManager.getImage(Images.slider_gradient).draw(oSB, this.getPosX() + this.getWidth() - this.getWidth() / 2 + iTranslateX, this.getPosY() + this.getHeight() - 1 - ImageManager.getImage(Images.slider_gradient).getHeight(), this.getWidth() / 2, 1, true, false);
        oSB.setColor(Color.WHITE);
        oSB.setColor(Color.WHITE);
        CFG.flagManager.drawFlag_FlagFrameSize(oSB, this.getPosX() + CFG.PADDING * 2 + (ImageManager.getImage(Images.top_flag_frame).getWidth() - ImageManager.getImage(Images.top_flag_frame).getWidth()) / 2 + iTranslateX, this.getPosY() + CFG.PADDING * 2 + (ImageManager.getImage(Images.top_flag_frame).getHeight() - ImageManager.getImage(Images.top_flag_frame).getHeight()) / 2);
        if (CFG.menuManager.getVisibleCreateCiv_Data()) {
            ImageManager.getImage(Images.top_flag_frame).draw(oSB, this.getPosX() + CFG.PADDING * 2 + (ImageManager.getImage(Images.top_flag_frame).getWidth() - ImageManager.getImage(Images.top_flag_frame).getWidth()) / 2 + iTranslateX, this.getPosY() + CFG.PADDING * 2 + (ImageManager.getImage(Images.top_flag_frame).getHeight() - ImageManager.getImage(Images.top_flag_frame).getHeight()) / 2);
        }
        super.draw(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
    }

    @Override
    protected final void actionElement(int iID) {
        switch (iID) {
            case 0: {
                CFG.showKeyboard();
                return;
            }
            case 1: {
                if (CFG.menuManager.getColorPicker().getVisible() && CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID == -1) {
                    CFG.menuManager.getColorPicker().setVisible(false, null);
                } else {
                    CFG.MANAGE_DIPLOMACY_CUSTOMIZE_RELATIONS_CIV_ID = -1;
                    CFG.menuManager.getColorPicker().setActiveRGBColor((float)CFG.editorCivilization_GameData.getR() / 255.0f, (float)CFG.editorCivilization_GameData.getG() / 255.0f, (float)CFG.editorCivilization_GameData.getB() / 255.0f);
                    CFG.menuManager.getColorPicker().setVisible(true, ColorPicker_AoC.PickerAction.EDITOR_CIV_GAME_COLOR);
                }
                return;
            }
        }
    }
}

