/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button_Game;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Menu;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.Minimap;
import age.of.civilizations2.jakowski.lukasz.SliderMenu;
import age.of.civilizations2.jakowski.lukasz.Text;
import age.of.civilizations2.jakowski.lukasz.Touch;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.GdxRuntimeException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

class Menu_Leader_Edit
extends SliderMenu {
    protected Menu_Leader_Edit() {
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        menuElements.add(new Button_Game(null, -1, CFG.PADDING, CFG.PADDING, true));
        menuElements.add(new Button_Game(null, -1, CFG.GAME_WIDTH - CFG.BUTTON_WIDTH - CFG.PADDING, CFG.PADDING, true));
        menuElements.add(new Minimap(CFG.GAME_WIDTH - CFG.map.getMapBG().getMinimapWidth(), CFG.GAME_HEIGHT - CFG.map.getMapBG().getMinimapHeight()));
        menuElements.add(new Text(null, -1, CFG.BUTTON_WIDTH + CFG.PADDING * 2, 0, CFG.GAME_WIDTH - (CFG.BUTTON_WIDTH + CFG.PADDING * 2) * 2, CFG.BUTTON_HEIGHT + CFG.PADDING * 2){

            @Override
            protected Color getColor(boolean isActive) {
                return isActive ? new Color(0.56f, 0.56f, 0.56f, 1.0f) : (this.getClickable() ? (this.getIsHovered() ? new Color(0.68f, 0.68f, 0.68f, 1.0f) : new Color(CFG.COLOR_TEXT_CNG_TOP_SCENARIO_NAME.r, CFG.COLOR_TEXT_CNG_TOP_SCENARIO_NAME.g, CFG.COLOR_TEXT_CNG_TOP_SCENARIO_NAME.b, 0.95f)) : new Color(0.78f, 0.78f, 0.78f, 0.7f));
            }
        });
        this.initMenu(null, 0, 0, CFG.GAME_WIDTH, CFG.GAME_HEIGHT, menuElements);
        this.updateLanguage();
    }

    @Override
    protected void updateLanguage() {
        this.getMenuElement(0).setText(CFG.langManager.get("Back"));
        this.getMenuElement(1).setText(CFG.langManager.get("Save"));
        this.getMenuElement(3).setText(CFG.langManager.get("AddNewLeader"));
    }

    @Override
    protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        CFG.drawEditorTitle_Edge_R_Reflected(oSB, iTranslateX, iTranslateY, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT + CFG.PADDING * 2);
        super.draw(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
    }

    @Override
    protected final void actionElement(int iID) {
        switch (iID) {
            case 0: {
                this.onBackPressed();
                CFG.brushTool = false;
                CFG.menuManager.setViewID(Menu.eGAME_LEADERS);
                CFG.menuManager.setBackAnimation(true);
                return;
            }
            case 1: {
                CFG.menuManager.saveLeader_Edit_Data();
                if (CFG.leader_GameData.getLeaderOfCiv().getName().length() < 1) {
                    CFG.toast.setInView("-- " + CFG.langManager.get("Name") + " --", CFG.COLOR_TEXT_MODIFIER_NEGATIVE2);
                    CFG.toast.setTimeInView(6000);
                    break;
                }
                if (CFG.leader_GameData.getCivsSize() == 0) {
                    CFG.toast.setInView("-- " + CFG.langManager.get("Civilizations") + " --", CFG.COLOR_TEXT_MODIFIER_NEGATIVE2);
                    CFG.toast.setTimeInView(6000);
                    break;
                }
                this.saveLeader();
                this.onBackPressed();
                CFG.brushTool = false;
                CFG.menuManager.setViewID(Menu.eGAME_LEADERS);
                CFG.menuManager.setBackAnimation(true);
                break;
            }
            case 2: {
                CFG.map.getMapCoordinates().centerToMinimapClick(Touch.getMousePosX() - this.getMenuElement(iID).getPosX() - this.getPosX(), Touch.getMousePosY() - this.getMenuElement(iID).getPosY() - this.getMenuPosY());
                break;
            }
        }
    }

    private final void saveLeader() {
        OutputStream os = null;
        try {
            FileHandle fileData = Gdx.files.local("game/leaders/" + CFG.leader_GameData.getLeaderOfCiv().getTag());
            fileData.writeBytes(CFG.serialize(CFG.leader_GameData), false);
        }
        catch (IOException fileData) {
        }
        finally {
            if (os != null) {
                try {
                    os.close();
                }
                catch (Exception fileData) {}
            }
        }
        try {
            FileHandle file = CFG.readLocalFiles() ? Gdx.files.local("game/leaders/Age_of_Civilizations") : Gdx.files.internal("game/leaders/Age_of_Civilizations");
            String tempTags = file.readString();
            if (tempTags.indexOf(CFG.leader_GameData.getLeaderOfCiv().getTag()) < 0) {
                FileHandle fileSave = Gdx.files.local("game/leaders/Age_of_Civilizations");
                fileSave.writeString(tempTags + CFG.leader_GameData.getLeaderOfCiv().getTag() + ";", false);
            } else {
                String[] tempTagsSplited = tempTags.split(";");
                boolean tAdd = true;
                int iSize = tempTagsSplited.length;
                for (int i = 0; i < iSize; ++i) {
                    if (!tempTagsSplited[i].equals(CFG.leader_GameData.getLeaderOfCiv().getTag())) continue;
                    tAdd = false;
                    break;
                }
                if (tAdd) {
                    FileHandle fileSave = Gdx.files.local("game/leaders/Age_of_Civilizations");
                    fileSave.writeString(tempTags + CFG.leader_GameData.getLeaderOfCiv().getTag() + ";", false);
                }
            }
        }
        catch (GdxRuntimeException ex) {
            FileHandle fileSave = Gdx.files.local("game/leaders/Age_of_Civilizations");
            fileSave.writeString(CFG.leader_GameData.getLeaderOfCiv().getTag() + ";", false);
        }
    }
}

