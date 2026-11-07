/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button_Menu;
import age.of.civilizations2.jakowski.lukasz.Button_Menu_LR_MainMenu;
import age.of.civilizations2.jakowski.lukasz.Button_Menu_LR_MainMenu_Games;
import age.of.civilizations2.jakowski.lukasz.Button_Menu_LR_MainMenu_Rate;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Dialog;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import age.of.civilizations2.jakowski.lukasz.Map;
import age.of.civilizations2.jakowski.lukasz.Menu;
import age.of.civilizations2.jakowski.lukasz.MenuElement;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element2;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type;
import age.of.civilizations2.jakowski.lukasz.MenuElement_Hover_v2_Element_Type_Text;
import age.of.civilizations2.jakowski.lukasz.SaveManager;
import age.of.civilizations2.jakowski.lukasz.SliderMenu;
import age.of.civilizations2.jakowski.lukasz.Statistics_Civ_GameData;
import age.of.civilizations2.jakowski.lukasz.Text;
import age.of.civilizations2.jakowski.lukasz.Touch;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.utils.ScissorStack;
import com.badlogic.gdx.utils.GdxRuntimeException;
import java.io.IOException;
import java.util.ArrayList;

class Menu_Main
extends SliderMenu {
    private int iTitleOffset = 0;
    private long lTime = 0L;
    private int ANIMATION_TIME = 425;
    protected static float ICONS_ALPHA_PC = 0.15f;
    protected static float ICONS_ALPHA = 0.125f;
    protected static final float LOGO_APLHA_DEFAULT = 0.95f;
    protected static boolean RATE_THE_GAME = false;

    protected Menu_Main() {
        int i;
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        RATE_THE_GAME = false;
        int numOfProvinces = 0;
        if (!CFG.settingsManager.gameRated && CFG.isAndroid()) {
            try {
                FileHandle file = Gdx.files.local("saves/stats/civ/Age_of_Civilizations");
                String tempTags = file.readString();
                String[] tData = tempTags.split(";");
                for (int i2 = 0; i2 < tData.length; ++i2) {
                    try {
                        Statistics_Civ_GameData tempData = (Statistics_Civ_GameData)CFG.deserialize(Gdx.files.local("saves/stats/civ/" + tData[i2]).readBytes());
                        if ((numOfProvinces += tempData.getConqueredProvinces()) < 50) continue;
                        break;
                    }
                    catch (GdxRuntimeException gdxRuntimeException) {
                        continue;
                    }
                    catch (ClassNotFoundException classNotFoundException) {
                        continue;
                    }
                    catch (IOException iOException) {
                        // empty catch block
                    }
                }
            }
            catch (GdxRuntimeException ex) {
                RATE_THE_GAME = false;
            }
            RATE_THE_GAME = numOfProvinces >= 50;
        } else {
            RATE_THE_GAME = false;
        }
        int tempH = CFG.GAME_HEIGHT / 2 - (CFG.BUTTON_HEIGHT * 6 + CFG.PADDING * 7) / 2 + (CFG.BUTTON_HEIGHT + CFG.PADDING * 2) / 2;
        menuElements.add(new Button_Menu_LR_MainMenu_Games(null, -1, CFG.GAME_WIDTH / 10, tempH, CFG.GAME_WIDTH - CFG.GAME_WIDTH / 5, CFG.BUTTON_HEIGHT, true));
        menuElements.add(new Button_Menu_LR_MainMenu(null, -1, CFG.GAME_WIDTH / 10, tempH + CFG.BUTTON_HEIGHT + CFG.PADDING, CFG.GAME_WIDTH - CFG.GAME_WIDTH / 5, CFG.BUTTON_HEIGHT, true));
        menuElements.add(new Button_Menu_LR_MainMenu(null, -1, CFG.GAME_WIDTH / 10, tempH + CFG.BUTTON_HEIGHT * 2 + CFG.PADDING * 2, CFG.GAME_WIDTH - CFG.GAME_WIDTH / 5, CFG.BUTTON_HEIGHT, true));
        if (RATE_THE_GAME) {
            menuElements.add(new Button_Menu_LR_MainMenu_Rate(null, -1, CFG.GAME_WIDTH / 10, tempH + CFG.BUTTON_HEIGHT * 3 + CFG.PADDING * 3, CFG.GAME_WIDTH - CFG.GAME_WIDTH / 5, CFG.BUTTON_HEIGHT, true){

                @Override
                protected void actionElement(int iID) {
                    try {
                        CFG.settingsManager.gameRated = true;
                        CFG.saveSettings();
                        Gdx.net.openURI("https://play.google.com/store/apps/details?id=age.of.civilizations2.jakowski.lukasz");
                    }
                    catch (GdxRuntimeException ex) {
                        CFG.toast.setInView(CFG.langManager.get("NoData"));
                    }
                }
            });
        } else {
            menuElements.add(new Button_Menu_LR_MainMenu(null, -1, CFG.GAME_WIDTH / 10, tempH + CFG.BUTTON_HEIGHT * 3 + CFG.PADDING * 3, CFG.GAME_WIDTH - CFG.GAME_WIDTH / 5, CFG.BUTTON_HEIGHT, true){

                @Override
                protected void actionElement(int iID) {
                    CFG.setDialogType(Dialog.START_TUTORIAL);
                }
            });
        }
        menuElements.add(new Button_Menu_LR_MainMenu(null, -1, CFG.GAME_WIDTH / 10, tempH + CFG.BUTTON_HEIGHT * 4 + CFG.PADDING * 4, CFG.GAME_WIDTH - CFG.GAME_WIDTH / 5, CFG.BUTTON_HEIGHT, true));
        menuElements.add(new Button_Menu_LR_MainMenu(null, -1, CFG.GAME_WIDTH / 10, tempH + CFG.BUTTON_HEIGHT * 5 + CFG.PADDING * 5, CFG.GAME_WIDTH - CFG.GAME_WIDTH / 5, CFG.BUTTON_HEIGHT, true));
        menuElements.add(new Text("", -1, CFG.GAME_WIDTH / 2 - ImageManager.getImage(Images.gameLogo).getWidth() / 2 - CFG.PADDING * 2, ((MenuElement)menuElements.get(0)).getPosY() - CFG.PADDING - ImageManager.getImage(Images.main_menu_edge).getHeight() - CFG.BUTTON_HEIGHT, ImageManager.getImage(Images.gameLogo).getWidth() + CFG.PADDING * 4, CFG.BUTTON_HEIGHT){

            @Override
            protected int getPosX() {
                return CFG.GAME_WIDTH / 2 - ImageManager.getImage(Images.gameLogo).getWidth() / 2 - CFG.PADDING * 3;
            }

            @Override
            protected int getWidth() {
                return ImageManager.getImage(Images.gameLogo).getWidth() + CFG.PADDING * 6;
            }

            @Override
            protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
                CFG.setRender_3(true);
                if (isActive) {
                    oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.75f));
                } else if (this.getIsHovered()) {
                    oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 1.0f));
                } else {
                    oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.95f));
                }
                ImageManager.getImage(Images.gameLogo).draw(oSB, this.getPosX() + this.getWidth() / 2 - ImageManager.getImage(Images.gameLogo).getWidth() / 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - ImageManager.getImage(Images.gameLogo).getHeight() / 2 + iTranslateY);
                oSB.setColor(Color.WHITE);
            }

            @Override
            protected void buildElementHover() {
                ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text("Age of Civilizations II", CFG.COLOR_TEXT_NUM_OF_PROVINCES));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
        });
        int tRebuildPosY = 0;
        for (i = 0; i < menuElements.size(); ++i) {
            if (tRebuildPosY <= ((MenuElement)menuElements.get(i)).getPosY()) continue;
            tRebuildPosY = ((MenuElement)menuElements.get(i)).getPosY();
        }
        if (tRebuildPosY < 0) {
            for (i = 0; i < menuElements.size(); ++i) {
                ((MenuElement)menuElements.get(i)).setPosY(((MenuElement)menuElements.get(i)).getPosY() - tRebuildPosY + CFG.PADDING * 4);
            }
        }
        menuElements.add(new Button_Menu(null, -1, CFG.GAME_WIDTH - CFG.BUTTON_HEIGHT, CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT, CFG.BUTTON_HEIGHT, CFG.BUTTON_HEIGHT, true){

            @Override
            protected void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                if (isActive) {
                    oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 1.0f));
                } else if (this.getIsHovered()) {
                    oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.65f));
                } else {
                    oSB.setColor(new Color(1.0f, 1.0f, 1.0f, ICONS_ALPHA_PC));
                }
                ImageManager.getImage(Images.line_32_vertical).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() / 4 - ImageManager.getImage(Images.line_32_vertical).getHeight() + iTranslateY, 1, this.getHeight() / 2);
                ImageManager.getImage(Images.logo_steam).draw(oSB, this.getPosX() + this.getWidth() / 2 - ImageManager.getImage(Images.logo_steam).getWidth() / 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - ImageManager.getImage(Images.logo_steam).getHeight() / 2 + iTranslateY);
                oSB.setColor(Color.WHITE);
            }

            @Override
            protected void buildElementHover() {
                ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text("https://store.steampowered.com/app/603850/Age_of_Civilizations_II/", CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }

            @Override
            protected void drawMenuElementHover2(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                if (this.menuElementHover != null) {
                    this.menuElementHover.drawAlwaysOver(oSB, Touch.getMousePosX(), this.getPosY());
                }
            }
        });
        menuElements.add(new Button_Menu(null, -1, CFG.GAME_WIDTH - CFG.BUTTON_HEIGHT, CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT * 2, CFG.BUTTON_HEIGHT, CFG.BUTTON_HEIGHT, true){

            @Override
            protected void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                if (isActive) {
                    oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 1.0f));
                } else if (this.getIsHovered()) {
                    oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.65f));
                } else {
                    oSB.setColor(new Color(1.0f, 1.0f, 1.0f, ICONS_ALPHA));
                }
                ImageManager.getImage(Images.line_32_vertical).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() / 4 - ImageManager.getImage(Images.line_32_vertical).getHeight() + iTranslateY, 1, this.getHeight() / 2);
                ImageManager.getImage(Images.logo_android).draw(oSB, this.getPosX() + this.getWidth() / 2 - ImageManager.getImage(Images.logo_android).getWidth() / 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - ImageManager.getImage(Images.logo_android).getHeight() / 2 + iTranslateY);
                oSB.setColor(Color.WHITE);
            }

            @Override
            protected void buildElementHover() {
                ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text("https://play.google.com/store/apps/details?id=age.of.civilizations2.jakowski.lukasz", CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }

            @Override
            protected void drawMenuElementHover2(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                if (this.menuElementHover != null) {
                    this.menuElementHover.drawAlwaysOver(oSB, Touch.getMousePosX(), this.getPosY());
                }
            }
        });
        menuElements.add(new Button_Menu(null, -1, CFG.GAME_WIDTH - CFG.BUTTON_HEIGHT, CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT * 3, CFG.BUTTON_HEIGHT, CFG.BUTTON_HEIGHT, true){

            @Override
            protected void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                if (isActive) {
                    oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 1.0f));
                } else if (this.getIsHovered()) {
                    oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.65f));
                } else {
                    oSB.setColor(new Color(1.0f, 1.0f, 1.0f, ICONS_ALPHA));
                }
                ImageManager.getImage(Images.line_32_vertical).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() / 4 - ImageManager.getImage(Images.line_32_vertical).getHeight() + iTranslateY, 1, this.getHeight() / 2);
                ImageManager.getImage(Images.logo_app).draw(oSB, this.getPosX() + this.getWidth() / 2 - ImageManager.getImage(Images.logo_app).getWidth() / 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - ImageManager.getImage(Images.logo_app).getHeight() / 2 + iTranslateY);
                oSB.setColor(Color.WHITE);
            }

            @Override
            protected void drawMenuElementHover2(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                if (this.menuElementHover != null) {
                    this.menuElementHover.drawAlwaysOver(oSB, Touch.getMousePosX(), this.getPosY());
                }
            }

            @Override
            protected void buildElementHover() {
                ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text("www.AgeOfCivilizationsGame.com", CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
        });
        menuElements.add(new Button_Menu(null, -1, CFG.GAME_WIDTH - CFG.BUTTON_HEIGHT, CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT * 4, CFG.BUTTON_HEIGHT, CFG.BUTTON_HEIGHT, true){

            @Override
            protected void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                if (isActive) {
                    oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 1.0f));
                } else if (this.getIsHovered()) {
                    oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.65f));
                } else {
                    oSB.setColor(new Color(1.0f, 1.0f, 1.0f, ICONS_ALPHA));
                }
                ImageManager.getImage(Images.line_32_vertical).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() / 4 - ImageManager.getImage(Images.line_32_vertical).getHeight() + iTranslateY, 1, this.getHeight() / 2);
                ImageManager.getImage(Images.logo_fb).draw(oSB, this.getPosX() + this.getWidth() / 2 - ImageManager.getImage(Images.logo_fb).getWidth() / 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - ImageManager.getImage(Images.logo_fb).getHeight() / 2 + iTranslateY);
                oSB.setColor(Color.WHITE);
            }

            @Override
            protected void buildElementHover() {
                ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text("https://facebook.com/AgeofCivilizationsJakowski/", CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }

            @Override
            protected void drawMenuElementHover2(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                if (this.menuElementHover != null) {
                    this.menuElementHover.drawAlwaysOver(oSB, Touch.getMousePosX(), this.getPosY());
                }
            }
        });
        menuElements.add(new Button_Menu(null, -1, CFG.GAME_WIDTH - CFG.BUTTON_HEIGHT, CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT * 5, CFG.BUTTON_HEIGHT, CFG.BUTTON_HEIGHT, true){

            @Override
            protected void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                if (isActive) {
                    oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 1.0f));
                } else if (this.getIsHovered()) {
                    oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.65f));
                } else {
                    oSB.setColor(new Color(1.0f, 1.0f, 1.0f, ICONS_ALPHA));
                }
                ImageManager.getImage(Images.line_32_vertical).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() / 4 - ImageManager.getImage(Images.line_32_vertical).getHeight() + iTranslateY, 1, this.getHeight() / 2);
                ImageManager.getImage(Images.logo_twit).draw(oSB, this.getPosX() + this.getWidth() / 2 - ImageManager.getImage(Images.logo_twit).getWidth() / 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - ImageManager.getImage(Images.logo_twit).getHeight() / 2 + iTranslateY);
                oSB.setColor(Color.WHITE);
            }

            @Override
            protected void buildElementHover() {
                ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text("https://twitter.com/jakowskidev", CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }

            @Override
            protected void drawMenuElementHover2(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                if (this.menuElementHover != null) {
                    this.menuElementHover.drawAlwaysOver(oSB, Touch.getMousePosX(), this.getPosY());
                }
            }
        });
        menuElements.add(new Button_Menu(null, -1, CFG.GAME_WIDTH - CFG.BUTTON_HEIGHT, CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT * 6, CFG.BUTTON_HEIGHT, CFG.BUTTON_HEIGHT, true){

            @Override
            protected void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                if (isActive) {
                    oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 1.0f));
                } else if (this.getIsHovered()) {
                    oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.65f));
                } else {
                    oSB.setColor(new Color(1.0f, 1.0f, 1.0f, ICONS_ALPHA));
                }
                ImageManager.getImage(Images.line_32_vertical).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() / 4 - ImageManager.getImage(Images.line_32_vertical).getHeight() + iTranslateY, 1, this.getHeight() / 2);
                ImageManager.getImage(Images.logo_yt).draw(oSB, this.getPosX() + this.getWidth() / 2 - ImageManager.getImage(Images.logo_yt).getWidth() / 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - ImageManager.getImage(Images.logo_yt).getHeight() / 2 + iTranslateY);
                oSB.setColor(Color.WHITE);
            }

            @Override
            protected void buildElementHover() {
                ArrayList<MenuElement_Hover_v2_Element2> nElements = new ArrayList<MenuElement_Hover_v2_Element2>();
                ArrayList<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<MenuElement_Hover_v2_Element_Type>();
                nData.add(new MenuElement_Hover_v2_Element_Type_Text("https://www.YouTube.com/user/jakowskiuki", CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
                nElements.add(new MenuElement_Hover_v2_Element2(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }

            @Override
            protected void drawMenuElementHover2(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                if (this.menuElementHover != null) {
                    this.menuElementHover.drawAlwaysOver(oSB, Touch.getMousePosX(), this.getPosY());
                }
            }
        });
        this.initMenu(null, 0, 0, CFG.GAME_WIDTH, CFG.GAME_HEIGHT, menuElements);
        this.updateLanguage();
        this.iTitleOffset = CFG.XXXXHDPI ? 7 : (CFG.XXXHDPI ? 7 : (CFG.XXHDPI ? 7 : (CFG.XHDPI ? 7 : 7)));
    }

    @Override
    protected final void updateLanguage() {
        this.getMenuElement(0).setText(CFG.langManager.get("Games"));
        this.getMenuElement(1).setText(CFG.langManager.get("Editor"));
        this.getMenuElement(2).setText(CFG.langManager.get("Settings"));
        this.getMenuElement(3).setText(RATE_THE_GAME ? CFG.langManager.get("Rate") + " " + "Age of Civilizations II" : CFG.langManager.get("Tutorial"));
        this.getMenuElement(4).setText(CFG.langManager.get("About"));
        this.getMenuElement(5).setText(CFG.langManager.get("ExitGame"));
        CFG.sTOTAL = CFG.langManager.get("Total");
        CFG.sTOTAL_WORLDS_POPULATION = CFG.langManager.get("WorldsPopulation");
        CFG.glyphLayout.setText(CFG.fontMain, CFG.sLoading);
        CFG.iLoadingWidth = (int)CFG.glyphLayout.width;
        CFG.glyphLayout.setText(CFG.fontMain, CFG.getLukaszJakowskiGames());
        CFG.iJakowskiGamesWidth = (int)CFG.glyphLayout.width;
        CFG.glyphLayout.setText(CFG.fontMain, "presents");
        CFG.iJakowskiGames_PresentsWidth = (int)CFG.glyphLayout.width;
        CFG.glyphLayout.setText(CFG.fontMain, "Age of Civilizations II");
        CFG.iAgeOfCivilizationsWidth = (int)CFG.glyphLayout.width;
        this.getMenuElement(6).setText("Age of Civilizations II");
    }

    @Override
    protected void drawScrollPos(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        if (sliderMenuIsActive) {
            super.drawScrollPos(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        }
    }

    @Override
    protected void beginClip(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        if (this.lTime + (long)this.ANIMATION_TIME > System.currentTimeMillis()) {
            Rectangle clipBounds = new Rectangle(this.getPosX() + iTranslateX, CFG.GAME_HEIGHT - this.getPosY() - iTranslateY, this.getWidth(), -((int)((float)this.getHeight() * ((float)(System.currentTimeMillis() - this.lTime) / (float)this.ANIMATION_TIME))));
            oSB.flush();
            ScissorStack.pushScissors(clipBounds);
            CFG.setRender_3(true);
        } else {
            super.beginClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        }
    }

    @Override
    protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean sliderMenuIsActive) {
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.6f));
        ImageManager.getImage(Images.gradient).draw(oSB, iTranslateX, -ImageManager.getImage(Images.gradient).getHeight() + iTranslateY, CFG.GAME_WIDTH, CFG.PADDING * 3);
        ImageManager.getImage(Images.gradient).draw(oSB, iTranslateX, CFG.GAME_HEIGHT - ImageManager.getImage(Images.gradient).getHeight() - CFG.PADDING * 3 + iTranslateY, CFG.GAME_WIDTH, CFG.PADDING * 3, false, true);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.1f));
        ImageManager.getImage(Images.patt2).draw(oSB, iTranslateX, -ImageManager.getImage(Images.patt2).getHeight(), CFG.GAME_WIDTH, CFG.GAME_HEIGHT, 0.0f, 0);
        this.beginClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        oSB.setColor(Color.WHITE);
        ImageManager.getImage(Images.main_menu_edge).draw2(oSB, this.getMenuElement(0).getPosX() - 2 + iTranslateX, this.getMenuPosY() + this.getMenuElement(0).getPosY() - ImageManager.getImage(Images.main_menu_edge).getHeight() * 2 - CFG.PADDING + iTranslateY, CFG.GAME_WIDTH / 2 - ImageManager.getImage(Images.gameLogo).getWidth() / 2 - CFG.PADDING * 3 - ImageManager.getImage(Images.main_menu_edge).getWidth() - this.iTitleOffset - (this.getMenuElement(0).getPosX() - 2), this.getMenuElement(5).getPosY() - this.getMenuElement(1).getPosY() + this.getMenuElement(5).getHeight() * 2 + ImageManager.getImage(Images.main_menu_edge).getHeight() + CFG.PADDING + CFG.PADDING * 2);
        ImageManager.getImage(Images.main_menu_edge).draw2(oSB, CFG.GAME_WIDTH / 2 - ImageManager.getImage(Images.gameLogo).getWidth() / 2 - CFG.PADDING * 3 - ImageManager.getImage(Images.main_menu_edge).getWidth() + iTranslateX, this.getMenuPosY() + this.getMenuElement(0).getPosY() - ImageManager.getImage(Images.main_menu_edge).getHeight() * 2 - CFG.PADDING - CFG.BUTTON_HEIGHT + iTranslateY, ImageManager.getImage(Images.main_menu_edge).getWidth() + ImageManager.getImage(Images.gameLogo).getWidth() + CFG.PADDING * 6, CFG.BUTTON_HEIGHT - this.iTitleOffset);
        ImageManager.getImage(Images.main_menu_edge).draw2(oSB, CFG.GAME_WIDTH / 2 - ImageManager.getImage(Images.gameLogo).getWidth() / 2 - CFG.PADDING * 3 - ImageManager.getImage(Images.main_menu_edge).getWidth() + ImageManager.getImage(Images.main_menu_edge).getWidth() + ImageManager.getImage(Images.gameLogo).getWidth() + CFG.PADDING * 6 + iTranslateX, this.getMenuPosY() + this.getMenuElement(0).getPosY() - ImageManager.getImage(Images.main_menu_edge).getHeight() * 2 - CFG.PADDING - CFG.BUTTON_HEIGHT + iTranslateY, ImageManager.getImage(Images.main_menu_edge).getWidth(), CFG.BUTTON_HEIGHT - this.iTitleOffset, true);
        ImageManager.getImage(Images.main_menu_edge2).draw2(oSB, CFG.GAME_WIDTH / 2 - ImageManager.getImage(Images.gameLogo).getWidth() / 2 - CFG.PADDING * 3 - ImageManager.getImage(Images.main_menu_edge).getWidth() - this.iTitleOffset + iTranslateX, this.getMenuPosY() + this.getMenuElement(0).getPosY() - ImageManager.getImage(Images.main_menu_edge).getHeight() - ImageManager.getImage(Images.main_menu_edge2).getHeight() - CFG.PADDING - this.iTitleOffset + iTranslateY, CFG.GAME_WIDTH / 2 - ImageManager.getImage(Images.gameLogo).getWidth() / 2 - CFG.PADDING * 3 - ImageManager.getImage(Images.main_menu_edge).getWidth() + ImageManager.getImage(Images.main_menu_edge).getWidth() + this.iTitleOffset - (ImageManager.getImage(Images.main_menu_edge2).getWidth() - ImageManager.getImage(Images.main_menu_edge).getWidth()) + ImageManager.getImage(Images.gameLogo).getWidth() + CFG.PADDING * 6 - (CFG.GAME_WIDTH / 2 - ImageManager.getImage(Images.gameLogo).getWidth() / 2 - CFG.PADDING * 3 - ImageManager.getImage(Images.main_menu_edge).getWidth() - this.iTitleOffset), this.getMenuElement(5).getPosY() - this.getMenuElement(1).getPosY() + this.getMenuElement(5).getHeight() * 2 + ImageManager.getImage(Images.main_menu_edge2).getHeight() + CFG.PADDING + CFG.PADDING * 2 + this.iTitleOffset - (ImageManager.getImage(Images.main_menu_edge2).getWidth() - ImageManager.getImage(Images.main_menu_edge).getWidth()));
        ImageManager.getImage(Images.main_menu_edge2).draw2(oSB, CFG.GAME_WIDTH / 2 - ImageManager.getImage(Images.gameLogo).getWidth() / 2 - CFG.PADDING * 3 - ImageManager.getImage(Images.main_menu_edge).getWidth() + ImageManager.getImage(Images.main_menu_edge).getWidth() + this.iTitleOffset - (ImageManager.getImage(Images.main_menu_edge2).getWidth() - ImageManager.getImage(Images.main_menu_edge).getWidth()) + ImageManager.getImage(Images.gameLogo).getWidth() + CFG.PADDING * 6 + iTranslateX, this.getMenuPosY() + this.getMenuElement(0).getPosY() - ImageManager.getImage(Images.main_menu_edge).getHeight() - ImageManager.getImage(Images.main_menu_edge2).getHeight() - CFG.PADDING - this.iTitleOffset + iTranslateY, ImageManager.getImage(Images.main_menu_edge2).getWidth(), this.getMenuElement(5).getPosY() - this.getMenuElement(1).getPosY() + this.getMenuElement(5).getHeight() * 2 + ImageManager.getImage(Images.main_menu_edge2).getHeight() + CFG.PADDING + CFG.PADDING * 2 + this.iTitleOffset - (ImageManager.getImage(Images.main_menu_edge2).getWidth() - ImageManager.getImage(Images.main_menu_edge).getWidth()), true);
        ImageManager.getImage(Images.main_menu_edge).draw2(oSB, CFG.GAME_WIDTH / 2 - ImageManager.getImage(Images.gameLogo).getWidth() / 2 - CFG.PADDING * 3 - ImageManager.getImage(Images.main_menu_edge).getWidth() + ImageManager.getImage(Images.main_menu_edge).getWidth() + this.iTitleOffset - (ImageManager.getImage(Images.main_menu_edge2).getWidth() - ImageManager.getImage(Images.main_menu_edge).getWidth()) + ImageManager.getImage(Images.gameLogo).getWidth() + CFG.PADDING * 6 + ImageManager.getImage(Images.main_menu_edge2).getWidth() + iTranslateX, this.getMenuPosY() + this.getMenuElement(0).getPosY() - ImageManager.getImage(Images.main_menu_edge).getHeight() * 2 - CFG.PADDING + iTranslateY, this.getMenuElement(0).getPosX() + this.getMenuElement(0).getWidth() + 2 - (CFG.GAME_WIDTH / 2 - ImageManager.getImage(Images.gameLogo).getWidth() / 2 - CFG.PADDING * 3 - ImageManager.getImage(Images.main_menu_edge).getWidth() + ImageManager.getImage(Images.main_menu_edge).getWidth() + this.iTitleOffset - (ImageManager.getImage(Images.main_menu_edge2).getWidth() - ImageManager.getImage(Images.main_menu_edge).getWidth()) + ImageManager.getImage(Images.gameLogo).getWidth() + CFG.PADDING * 6 + ImageManager.getImage(Images.main_menu_edge2).getWidth()), this.getMenuElement(5).getPosY() - this.getMenuElement(1).getPosY() + this.getMenuElement(5).getHeight() * 2 + ImageManager.getImage(Images.main_menu_edge).getHeight() + CFG.PADDING + CFG.PADDING * 2, true);
        ImageManager.getImage(Images.main_menu_edge).draw2(oSB, this.getMenuElement(0).getPosX() - 2 + iTranslateX, this.getMenuPosY() + this.getMenuElement(0).getPosY() - ImageManager.getImage(Images.main_menu_edge).getHeight() * 2 - CFG.PADDING + this.getMenuElement(5).getPosY() - this.getMenuElement(1).getPosY() + this.getMenuElement(5).getHeight() * 2 + ImageManager.getImage(Images.main_menu_edge).getHeight() + CFG.PADDING + CFG.PADDING * 2 + iTranslateY, this.getMenuElement(0).getWidth() + 4 - ImageManager.getImage(Images.main_menu_edge).getWidth(), ImageManager.getImage(Images.main_menu_edge).getHeight(), false, true);
        ImageManager.getImage(Images.main_menu_edge).draw(oSB, this.getMenuElement(0).getPosX() - 2 + this.getMenuElement(0).getWidth() + 4 - ImageManager.getImage(Images.main_menu_edge).getWidth() + iTranslateX, this.getMenuPosY() + this.getMenuElement(0).getPosY() - ImageManager.getImage(Images.main_menu_edge).getHeight() * 2 - CFG.PADDING + this.getMenuElement(5).getPosY() - this.getMenuElement(1).getPosY() + this.getMenuElement(5).getHeight() * 2 + ImageManager.getImage(Images.main_menu_edge).getHeight() + CFG.PADDING + CFG.PADDING * 2 + ImageManager.getImage(Images.main_menu_edge).getHeight() + iTranslateY, true, true);
        this.drawMenu(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        this.endClip(oSB, iTranslateX, iTranslateY, sliderMenuIsActive);
        CFG.drawVersion_LEFT_BOT(oSB, iTranslateX);
        oSB.setColor(Color.WHITE);
        CFG.setRender_3(true);
        if ((Map.GAME_CRASHED_LOADED_MIN_SCALE || CFG.map.getMapBG().getMapScale() <= 1) && CFG.map.getMapBG().getMapScale() == 1 && !CFG.toast.getInView()) {
            ArrayList<String> nMess = new ArrayList<String>();
            ArrayList<Color> nCol = new ArrayList<Color>();
            nMess.add("Game crashed while loading");
            nCol.add(CFG.COLOR_TEXT_MODIFIER_NEGATIVE2);
            nMess.add("-- Loaded minimum scale of map --");
            nCol.add(CFG.COLOR_TEXT_MODIFIER_NEGATIVE2);
            nMess.add("Go to: Games -> Map: XX -> Earth: -> Scale X5");
            nCol.add(Color.WHITE);
            CFG.toast.setInView(nMess, nCol);
            CFG.toast.setTimeInView(6000);
        }
    }

    @Override
    protected final void actionElement(int iID) {
        switch (iID) {
            case 0: {
                CFG.menuManager.setViewID(Menu.eGAMES);
                CFG.menuManager.setOrderOfMenu_Games();
                break;
            }
            case 1: {
                SaveManager.gameCanBeContinued = false;
                CFG.menuManager.setViewID(Menu.eEDITOR);
                break;
            }
            case 2: {
                CFG.goToMenu2 = Menu.eMAINMENU;
                CFG.menuManager.setViewID(Menu.eSETTINGS);
                break;
            }
            case 3: {
                this.getMenuElement(iID).actionElement(iID);
                return;
            }
            case 4: 
            case 6: {
                CFG.menuManager.setViewID(Menu.eABOUT);
                CFG.map.getMapScale().setNewCurrentScaleByButton2(0.175f);
                return;
            }
            case 5: {
                CFG.setDialogType(Dialog.EXIT_GAME);
                return;
            }
            case 7: {
                CFG.GO_TO_LINK = "https://store.steampowered.com/app/603850/Age_of_Civilizations_II/";
                CFG.setDialogType(Dialog.GO_TO_LINK);
                return;
            }
            case 8: {
                CFG.GO_TO_LINK = "https://play.google.com/store/apps/details?id=age.of.civilizations2.jakowski.lukasz";
                CFG.setDialogType(Dialog.GO_TO_LINK);
                return;
            }
            case 9: {
                CFG.GO_TO_LINK = "http://ageofcivilizationsGame.com";
                CFG.setDialogType(Dialog.GO_TO_LINK);
                return;
            }
            case 10: {
                CFG.GO_TO_LINK = "https://www.facebook.com/AgeofCivilizationsJakowski/";
                CFG.setDialogType(Dialog.GO_TO_LINK);
                return;
            }
            case 11: {
                CFG.GO_TO_LINK = "https://twitter.com/jakowskidev";
                CFG.setDialogType(Dialog.GO_TO_LINK);
                return;
            }
            case 12: {
                CFG.GO_TO_LINK = "https://www.youtube.com/user/jakowskiuki";
                CFG.setDialogType(Dialog.GO_TO_LINK);
                return;
            }
        }
        for (int i = 0; i < CFG.game.getProvincesSize(); ++i) {
            if (CFG.game.getProvince(i).getSeaProvince()) continue;
            CFG.game.getProvince(i).setFromCivID(0);
        }
        CFG.map.getMapCoordinates().centerToRandomMapPosition();
    }

    @Override
    protected final void onBackPressed() {
    }

    @Override
    protected void setVisible(boolean visible) {
        super.setVisible(visible);
        this.lTime = System.currentTimeMillis();
    }
}

