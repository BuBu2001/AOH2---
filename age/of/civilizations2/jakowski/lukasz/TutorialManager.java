package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;
import java.util.List;

class TutorialManager {
   protected boolean IN_TUTORIAL = false;
   protected int STEP_ID = 0;
   protected int INNER_STEP = 0;
   protected TutorialManager.TutStep tutStep;
   private List<MenuElement_Hover> textBox = new ArrayList<>();
   private int iDrawPosX = 0;
   private int iDrawPosY = 0;
   private List<List<TutorialBox>> tutBoxes = new ArrayList<>();
   protected static final Color COLOR_TITLE = new Color(0.9137255F, 0.9137255F, 0.9137255F, 1.0F);
   protected static final Color COLOR_TEXT = new Color(0.78431374F, 0.8235294F, 0.78431374F, 1.0F);

   protected TutorialManager() {
      this.updateDrawTutorial(false);
   }

   protected final void startTutorial() {
      this.STEP_ID = 0;
      this.updateDrawTutorial(true);
   }

   protected final void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY) {
      this.tutStep.draw(oSB, iTranslateX, iTranslateY);
   }

   protected final void updateDrawTutorial(boolean enable) {
      this.IN_TUTORIAL = enable;
      this.INNER_STEP = 0;
      if (enable) {
         if (this.STEP_ID == 0) {
            this.iDrawPosX = CFG.PADDING * 2;
            this.iDrawPosY = ImageManager.getImage(Images.top_left).getHeight() + CFG.PADDING * 2;
            this.textBox.clear();
            this.tutBoxes.clear();
            List<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
            List<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("WelcomeToTheTutorial"), CFG.COLOR_TEXT_NUM_OF_PROVINCES));
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
            List<String> tempText = this.getTextSplited(CFG.langManager.get("t0"));

            for(int i = 0; i < tempText.size(); ++i) {
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(tempText.get(i), COLOR_TITLE));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
            }

            tempText.clear();
            tempText = this.getTextSplited(CFG.langManager.get("t1"));

            for(int i = 0; i < tempText.size(); ++i) {
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(tempText.get(i), COLOR_TEXT));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
            }

            tempText.clear();
            tempText = this.getTextSplited(CFG.langManager.get("t2"));

            for(int i = 0; i < tempText.size(); ++i) {
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(tempText.get(i), COLOR_TEXT));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
            }

            this.textBox.add(new MenuElement_Hover_v2(nElements));
            List<TutorialBox> tempBoxes = new ArrayList<>();
            tempBoxes.add(
               new TutorialBox(CFG.langManager.get("Next")) {
                  @Override
                  protected int getPosX() {
                     return CFG.menuManager.getInGame_ProvinceInfo().getPosX() + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(0).getPosX();
                  }
   
                  @Override
                  protected int getPosY() {
                     return CFG.menuManager.getInGame_ProvinceInfo().getPosY()
                        + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(0).getPosY()
                        - CFG.PADDING / 2
                        - CFG.PADDING
                        - this.getHeight();
                  }
               }
            );
            this.tutBoxes.add(tempBoxes);
            this.tutStep = new TutorialManager.TutStep() {
               @Override
               public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY) {
                  if (CFG.menuManager.getInGameView()) {
                     oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.15F));
                     ImageManager.getImage(Images.patt2).draw2(oSB, 0, 0 - ImageManager.getImage(Images.patt2).getHeight(), CFG.GAME_WIDTH, CFG.GAME_HEIGHT);
                     oSB.setColor(new Color(CFG.COLOR_GRADIENT_DARK_BLUE.r, CFG.COLOR_GRADIENT_DARK_BLUE.g, CFG.COLOR_GRADIENT_DARK_BLUE.b, 0.45F));
                     ImageManager.getImage(Images.slider_gradient)
                        .draw(oSB, 0, 0 - ImageManager.getImage(Images.slider_gradient).getHeight(), CFG.CIV_INFO_MENU_WIDTH / 4, CFG.GAME_HEIGHT);
                     ImageManager.getImage(Images.slider_gradient)
                        .draw(
                           oSB,
                           0 + CFG.GAME_WIDTH - CFG.CIV_INFO_MENU_WIDTH / 4,
                           0 - ImageManager.getImage(Images.slider_gradient).getHeight(),
                           CFG.CIV_INFO_MENU_WIDTH / 4,
                           CFG.GAME_HEIGHT,
                           true,
                           false
                        );
                     ImageManager.getImage(Images.gradient)
                        .draw(oSB, 0, 0 - ImageManager.getImage(Images.gradient).getHeight(), CFG.GAME_WIDTH, CFG.CIV_INFO_MENU_WIDTH / 4);
                     ImageManager.getImage(Images.gradient)
                        .draw(
                           oSB,
                           0,
                           0 + CFG.GAME_HEIGHT - CFG.CIV_INFO_MENU_WIDTH / 4 - ImageManager.getImage(Images.gradient).getHeight(),
                           CFG.GAME_WIDTH,
                           CFG.CIV_INFO_MENU_WIDTH / 4,
                           false,
                           true
                        );

                     try {
                        TutorialManager.this.textBox
                           .get(0)
                           .draw_HoverWithoutAnimation(
                              oSB,
                              (
                                    !CFG.menuManager.getInGame_ProvinceBuild_Visible() && !CFG.menuManager.getInGame_CivInfo().getVisible()
                                       ? TutorialManager.this.iDrawPosX
                                       : CFG.CIV_INFO_MENU_WIDTH + CFG.PADDING * 2
                                 )
                                 + AoCGame.LEFT,
                              TutorialManager.this.iDrawPosY
                           );
                        int tElemID = 0;
                        TutorialManager.this.drawLine(
                           oSB,
                           CFG.menuManager.getInGame_ProvinceInfo().getPosX() + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(tElemID).getPosX(),
                           -CFG.PADDING / 2
                              + CFG.menuManager.getInGame_ProvinceInfo().getPosY()
                              + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(tElemID).getPosY(),
                           CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(tElemID).getWidth()
                        );

                        for(int i = 0; i < TutorialManager.this.tutBoxes.get(TutorialManager.this.INNER_STEP).size(); ++i) {
                           TutorialManager.this.tutBoxes.get(TutorialManager.this.INNER_STEP).get(i).draw(oSB);
                        }
                     } catch (IndexOutOfBoundsException var6) {
                     } catch (NullPointerException var7) {
                     }
                  }
               }

               @Override
               public boolean action(Tutorial_ActionType actionType) {
                  if (actionType == Tutorial_ActionType.NEXT_TURN && CFG.gameAction.getActiveTurnState() == Game_Action.TurnStates.INPUT_ORDERS) {
                     ++TutorialManager.this.STEP_ID;
                     TutorialManager.this.updateDrawTutorial(TutorialManager.this.IN_TUTORIAL);
                     return true;
                  } else {
                     return false;
                  }
               }
            };
         } else if (this.STEP_ID == 1) {
            this.iDrawPosX = CFG.PADDING * 2;
            this.iDrawPosY = CFG.menuManager.getInGame().getPosY()
               + CFG.menuManager.getInGame().getMenuElement(7).getPosY()
               + CFG.menuManager.getInGame().getMenuElement(7).getHeight()
               + CFG.PADDING / 2
               + CFG.PADDING
               + CFG.TEXT_HEIGHT
               + CFG.PADDING * 2
               + CFG.PADDING;
            this.textBox.clear();
            this.tutBoxes.clear();
            List<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
            List<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
            nData.add(new MenuElement_Hover_v2_Element_Type_Text("1. ", CFG.COLOR_TEXT_NUM_OF_PROVINCES));
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("OpenCivilizationView"), COLOR_TITLE));
            nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), CFG.PADDING, 0));
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
            nData.add(new MenuElement_Hover_v2_Element_Type_Text("2. ", CFG.COLOR_TEXT_NUM_OF_PROVINCES));
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("CivilizationTreasury"), COLOR_TITLE));
            nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_gold, CFG.PADDING, 0));
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
            nData.add(new MenuElement_Hover_v2_Element_Type_Text("3. ", CFG.COLOR_TEXT_NUM_OF_PROVINCES));
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("NumberOfMovementPointsInThisTurn"), COLOR_TITLE));
            nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_movement_points, CFG.PADDING, 0));
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
            nData.add(new MenuElement_Hover_v2_Element_Type_Text("4. ", CFG.COLOR_TEXT_NUM_OF_PROVINCES));
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("cw1"), COLOR_TITLE));
            nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_diplomacy_points, CFG.PADDING, 0));
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
            nData.add(new MenuElement_Hover_v2_Element_Type_Text("5. ", CFG.COLOR_TEXT_NUM_OF_PROVINCES));
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("OpenTheDiplomacyView"), COLOR_TITLE));
            nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_diplomacy_points, CFG.PADDING, 0));
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
            nData.add(new MenuElement_Hover_v2_Element_Type_Text("6. ", CFG.COLOR_TEXT_NUM_OF_PROVINCES));
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("MoreMapViews"), COLOR_TITLE));
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
            nData.add(new MenuElement_Hover_v2_Element_Type_Text("7. ", CFG.COLOR_TEXT_NUM_OF_PROVINCES));
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("CurrentDateAndTurnOfTheGame"), COLOR_TITLE));
            nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.time, CFG.PADDING, 0));
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
            nData.add(new MenuElement_Hover_v2_Element_Type_Text("8. ", CFG.COLOR_TEXT_NUM_OF_PROVINCES));
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("cw0"), COLOR_TITLE));
            nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.diplo_war, CFG.PADDING, 0));
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
            nData.add(new MenuElement_Hover_v2_Element_Type_Space());
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("HoverAnElementToGetMoreInformations"), CFG.COLOR_TEXT_NUM_OF_PROVINCES));
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
            this.textBox.add(new MenuElement_Hover_v2(nElements));
            List<TutorialBox> tempBoxes = new ArrayList<>();
            tempBoxes.add(
               new TutorialBox(CFG.langManager.get("Next")) {
                  @Override
                  protected int getPosX() {
                     return CFG.menuManager.getInGame_ProvinceInfo().getPosX() + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(0).getPosX();
                  }
   
                  @Override
                  protected int getPosY() {
                     return CFG.menuManager.getInGame_ProvinceInfo().getPosY()
                        + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(0).getPosY()
                        - CFG.PADDING / 2
                        - CFG.PADDING
                        - this.getHeight();
                  }
               }
            );
            tempBoxes.add(
               new TutorialBox(CFG.langManager.get("1")) {
                  @Override
                  protected int getPosX() {
                     return CFG.menuManager.getInGame().getPosX() + CFG.menuManager.getInGame().getMenuElement(7).getPosX();
                  }
   
                  @Override
                  protected int getPosY() {
                     return CFG.menuManager.getInGame().getPosY()
                        + CFG.menuManager.getInGame().getMenuElement(7).getPosY()
                        + CFG.menuManager.getInGame().getMenuElement(7).getHeight()
                        + CFG.PADDING / 2
                        + CFG.PADDING;
                  }
               }
            );
            tempBoxes.add(
               new TutorialBox(CFG.langManager.get("2")) {
                  @Override
                  protected int getPosX() {
                     return CFG.menuManager.getInGame().getPosX() + CFG.menuManager.getInGame().getMenuElement(1).getPosX();
                  }
   
                  @Override
                  protected int getPosY() {
                     return CFG.menuManager.getInGame().getPosY()
                        + CFG.menuManager.getInGame().getMenuElement(1).getPosY()
                        + CFG.menuManager.getInGame().getMenuElement(1).getHeight()
                        + CFG.PADDING / 2
                        + CFG.PADDING;
                  }
               }
            );
            tempBoxes.add(
               new TutorialBox(CFG.langManager.get("3")) {
                  @Override
                  protected int getPosX() {
                     return CFG.menuManager.getInGame().getPosX() + CFG.menuManager.getInGame().getMenuElement(2).getPosX();
                  }
   
                  @Override
                  protected int getPosY() {
                     return CFG.menuManager.getInGame().getPosY()
                        + CFG.menuManager.getInGame().getMenuElement(2).getPosY()
                        + CFG.menuManager.getInGame().getMenuElement(2).getHeight()
                        + CFG.PADDING / 2
                        + CFG.PADDING;
                  }
               }
            );
            tempBoxes.add(
               new TutorialBox(CFG.langManager.get("4")) {
                  @Override
                  protected int getPosX() {
                     return CFG.menuManager.getInGame().getPosX() + CFG.menuManager.getInGame().getMenuElement(3).getPosX();
                  }
   
                  @Override
                  protected int getPosY() {
                     return CFG.menuManager.getInGame().getPosY()
                        + CFG.menuManager.getInGame().getMenuElement(3).getPosY()
                        + CFG.menuManager.getInGame().getMenuElement(3).getHeight()
                        + CFG.PADDING / 2
                        + CFG.PADDING;
                  }
               }
            );
            tempBoxes.add(
               new TutorialBox(CFG.langManager.get("5")) {
                  @Override
                  protected int getPosX() {
                     return CFG.menuManager.getInGame().getPosX() + CFG.menuManager.getInGame().getMenuElement(8).getPosX();
                  }
   
                  @Override
                  protected int getPosY() {
                     return CFG.menuManager.getInGame().getPosY()
                        + CFG.menuManager.getInGame().getMenuElement(8).getPosY()
                        + CFG.menuManager.getInGame().getMenuElement(8).getHeight()
                        + CFG.PADDING / 2
                        + CFG.PADDING;
                  }
               }
            );
            tempBoxes.add(
               new TutorialBox(CFG.langManager.get("6")) {
                  @Override
                  protected int getPosX() {
                     return CFG.menuManager.getInGame().getPosX() + CFG.menuManager.getInGame().getMenuElement(9).getPosX();
                  }
   
                  @Override
                  protected int getPosY() {
                     return CFG.menuManager.getInGame().getPosY()
                        + CFG.menuManager.getInGame().getMenuElement(9).getPosY()
                        + CFG.menuManager.getInGame().getMenuElement(9).getHeight()
                        + CFG.PADDING / 2
                        + CFG.PADDING;
                  }
               }
            );
            tempBoxes.add(
               new TutorialBox(CFG.langManager.get("7")) {
                  @Override
                  protected int getPosX() {
                     return CFG.menuManager.getInGame().getPosX() + CFG.menuManager.getInGame().getMenuElement(5).getPosX();
                  }
   
                  @Override
                  protected int getPosY() {
                     return CFG.menuManager.getInGame().getPosY()
                        + CFG.menuManager.getInGame().getMenuElement(5).getPosY()
                        + CFG.menuManager.getInGame().getMenuElement(5).getHeight()
                        + CFG.PADDING / 2
                        + CFG.PADDING;
                  }
               }
            );
            tempBoxes.add(
               new TutorialBox(CFG.langManager.get("8")) {
                  @Override
                  protected void draw(SpriteBatch oSB) {
                     if (CFG.menuManager.getMenu_InGame_CurrentWars().getVisible()) {
                        super.draw(oSB);
                     }
                  }
   
                  @Override
                  protected int getPosX() {
                     return CFG.menuManager.getMenu_InGame_CurrentWars().getPosX();
                  }
   
                  @Override
                  protected int getPosY() {
                     return CFG.menuManager.getMenu_InGame_CurrentWars().getPosY()
                        + CFG.menuManager.getMenu_InGame_CurrentWars().getHeight()
                        + CFG.PADDING / 2
                        + CFG.PADDING;
                  }
               }
            );
            this.tutBoxes.add(tempBoxes);
            this.tutStep = new TutorialManager.TutStep() {
               @Override
               public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY) {
                  if (CFG.menuManager.getInGameView()) {
                     try {
                        TutorialManager.this.textBox
                           .get(0)
                           .draw_HoverWithoutAnimation(
                              oSB,
                              (
                                    !CFG.menuManager.getInGame_ProvinceBuild_Visible() && !CFG.menuManager.getInGame_CivInfo().getVisible()
                                       ? TutorialManager.this.iDrawPosX
                                       : CFG.CIV_INFO_MENU_WIDTH + CFG.PADDING * 2
                                 )
                                 + AoCGame.LEFT,
                              TutorialManager.this.iDrawPosY
                           );
                        int tElemID = 0;
                        TutorialManager.this.drawLine(
                           oSB,
                           CFG.menuManager.getInGame_ProvinceInfo().getPosX() + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(tElemID).getPosX(),
                           -CFG.PADDING / 2
                              + CFG.menuManager.getInGame_ProvinceInfo().getPosY()
                              + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(tElemID).getPosY(),
                           CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(tElemID).getWidth()
                        );
                        TutorialManager.this.drawLine(
                           oSB,
                           CFG.menuManager.getInGame().getPosX() + CFG.menuManager.getInGame().getMenuElement(1).getPosX(),
                           CFG.PADDING / 2
                              + CFG.menuManager.getInGame().getPosY()
                              + CFG.menuManager.getInGame().getMenuElement(1).getPosY()
                              + CFG.menuManager.getInGame().getMenuElement(1).getHeight(),
                           CFG.menuManager.getInGame().getMenuElement(1).getWidth()
                        );
                        TutorialManager.this.drawLine(
                           oSB,
                           CFG.menuManager.getInGame().getPosX() + CFG.menuManager.getInGame().getMenuElement(2).getPosX(),
                           CFG.PADDING / 2
                              + CFG.menuManager.getInGame().getPosY()
                              + CFG.menuManager.getInGame().getMenuElement(2).getPosY()
                              + CFG.menuManager.getInGame().getMenuElement(2).getHeight(),
                           CFG.menuManager.getInGame().getMenuElement(2).getWidth()
                        );
                        TutorialManager.this.drawLine(
                           oSB,
                           CFG.menuManager.getInGame().getPosX() + CFG.menuManager.getInGame().getMenuElement(3).getPosX(),
                           CFG.PADDING / 2
                              + CFG.menuManager.getInGame().getPosY()
                              + CFG.menuManager.getInGame().getMenuElement(3).getPosY()
                              + CFG.menuManager.getInGame().getMenuElement(3).getHeight(),
                           CFG.menuManager.getInGame().getMenuElement(3).getWidth()
                        );
                        TutorialManager.this.drawLine(
                           oSB,
                           CFG.menuManager.getInGame().getPosX() + CFG.menuManager.getInGame().getMenuElement(5).getPosX(),
                           CFG.PADDING / 2
                              + CFG.menuManager.getInGame().getPosY()
                              + CFG.menuManager.getInGame().getMenuElement(5).getPosY()
                              + CFG.menuManager.getInGame().getMenuElement(5).getHeight(),
                           CFG.menuManager.getInGame().getMenuElement(5).getWidth()
                        );
                        TutorialManager.this.drawLine(
                           oSB,
                           CFG.menuManager.getInGame().getPosX() + CFG.menuManager.getInGame().getMenuElement(7).getPosX(),
                           CFG.PADDING / 2
                              + CFG.menuManager.getInGame().getPosY()
                              + CFG.menuManager.getInGame().getMenuElement(7).getPosY()
                              + CFG.menuManager.getInGame().getMenuElement(7).getHeight(),
                           CFG.menuManager.getInGame().getMenuElement(7).getWidth()
                        );
                        TutorialManager.this.drawLine(
                           oSB,
                           CFG.menuManager.getInGame().getPosX() + CFG.menuManager.getInGame().getMenuElement(8).getPosX(),
                           CFG.PADDING / 2
                              + CFG.menuManager.getInGame().getPosY()
                              + CFG.menuManager.getInGame().getMenuElement(8).getPosY()
                              + CFG.menuManager.getInGame().getMenuElement(8).getHeight(),
                           CFG.menuManager.getInGame().getMenuElement(8).getWidth()
                        );
                        TutorialManager.this.drawLine(
                           oSB,
                           CFG.menuManager.getInGame().getPosX() + CFG.menuManager.getInGame().getMenuElement(9).getPosX(),
                           CFG.PADDING / 2
                              + CFG.menuManager.getInGame().getPosY()
                              + CFG.menuManager.getInGame().getMenuElement(9).getPosY()
                              + CFG.menuManager.getInGame().getMenuElement(9).getHeight(),
                           CFG.menuManager.getInGame().getMenuElement(9).getWidth()
                        );
                        if (CFG.menuManager.getMenu_InGame_CurrentWars().getVisible()) {
                           TutorialManager.this.drawLine(
                              oSB,
                              CFG.menuManager.getMenu_InGame_CurrentWars().getPosX(),
                              CFG.PADDING / 2
                                 + CFG.menuManager.getMenu_InGame_CurrentWars().getPosY()
                                 + CFG.menuManager.getMenu_InGame_CurrentWars().getHeight(),
                              CFG.menuManager.getMenu_InGame_CurrentWars().getWidth()
                           );
                        }

                        for(int i = 0; i < TutorialManager.this.tutBoxes.get(TutorialManager.this.INNER_STEP).size(); ++i) {
                           TutorialManager.this.tutBoxes.get(TutorialManager.this.INNER_STEP).get(i).draw(oSB);
                        }
                     } catch (IndexOutOfBoundsException var6) {
                     } catch (NullPointerException var7) {
                     }
                  }
               }

               @Override
               public boolean action(Tutorial_ActionType actionType) {
                  if (actionType == Tutorial_ActionType.NEXT_TURN && CFG.gameAction.getActiveTurnState() == Game_Action.TurnStates.INPUT_ORDERS) {
                     ++TutorialManager.this.STEP_ID;
                     TutorialManager.this.updateDrawTutorial(TutorialManager.this.IN_TUTORIAL);
                     CFG.game.setActiveProvinceID(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCapitalProvinceID());
                     CFG.map.getMapCoordinates().centerToProvinceID(CFG.game.getActiveProvinceID());
                     int fromCiv = 0;

                     for(int i = 1; i < CFG.game.getCivsSize(); ++i) {
                        if (i != CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()
                           && !CFG.game.getCivsAtWar(i, CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())) {
                           fromCiv = i;
                           break;
                        }
                     }

                     CFG.game
                        .getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
                        .civGameData
                        .civilization_Diplomacy_GameData
                        .messageBox
                        .addMessage(new Message_Gift(fromCiv, 1500));
                     CFG.menuManager.rebuildInGame_Messages();
                     return true;
                  } else {
                     return false;
                  }
               }
            };
         } else if (this.STEP_ID == 2) {
            this.iDrawPosX = CFG.PADDING * 2;
            this.iDrawPosY = CFG.menuManager.getInGame().getPosY()
               + CFG.menuManager.getInGame().getMenuElement(7).getPosY()
               + CFG.menuManager.getInGame().getMenuElement(7).getHeight()
               + CFG.PADDING / 2
               + CFG.PADDING
               + CFG.TEXT_HEIGHT
               + CFG.PADDING * 2
               + CFG.PADDING;
            this.textBox.clear();
            this.tutBoxes.clear();
            List<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
            List<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
            nData.add(new MenuElement_Hover_v2_Element_Type_Text("1. ", CFG.COLOR_TEXT_NUM_OF_PROVINCES));
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("m0"), COLOR_TITLE));
            nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.diplo_message, CFG.PADDING, 0));
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
            nData.add(new MenuElement_Hover_v2_Element_Type_Text("2. ", CFG.COLOR_TEXT_NUM_OF_PROVINCES));
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("CapitalOfYourCivilization"), COLOR_TITLE));
            nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), CFG.PADDING, 0));
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
            nData.add(new MenuElement_Hover_v2_Element_Type_Space());
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
            List<String> tempText = this.getTextSplited(CFG.langManager.get("t7"));

            for(int i = 0; i < tempText.size(); ++i) {
               if (i == 0) {
                  nData.add(new MenuElement_Hover_v2_Element_Type_Ideology(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getIdeologyID()));
               }

               nData.add(new MenuElement_Hover_v2_Element_Type_Text(tempText.get(i), COLOR_TEXT));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
            }

            tempText.clear();
            tempText = this.getTextSplited(CFG.langManager.get("t8", "15", "10"));

            for(int i = 0; i < tempText.size(); ++i) {
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(tempText.get(i), COLOR_TEXT));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
            }

            nData.add(new MenuElement_Hover_v2_Element_Type_Space());
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
            tempText.clear();
            tempText = this.getTextSplited(CFG.langManager.get("s1"));

            for(int i = 0; i < tempText.size(); ++i) {
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(tempText.get(i), CFG.COLOR_TEXT_NUM_OF_PROVINCES));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
            }

            this.textBox.add(new MenuElement_Hover_v2(nElements));
            List<TutorialBox> tempBoxes = new ArrayList<>();
            tempBoxes.add(
               new TutorialBox(CFG.langManager.get("Next")) {
                  @Override
                  protected int getPosX() {
                     return CFG.menuManager.getInGame_ProvinceInfo().getPosX() + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(0).getPosX();
                  }
   
                  @Override
                  protected int getPosY() {
                     return CFG.menuManager.getInGame_ProvinceInfo().getPosY()
                        + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(0).getPosY()
                        - CFG.PADDING / 2
                        - CFG.PADDING
                        - this.getHeight();
                  }
               }
            );
            tempBoxes.add(
               new TutorialBox(CFG.langManager.get("2")) {
                  @Override
                  protected int getPosX() {
                     return (int)(
                           (float)(
                                 CFG.game.getProvince(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCapitalProvinceID()).getCenterX()
                                    + CFG.game
                                       .getProvince(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCapitalProvinceID())
                                       .getShiftX()
                                    + CFG.game
                                       .getProvince(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCapitalProvinceID())
                                       .getTranslateProvincePosX()
                              )
                              * CFG.map.getMapScale().getCurrentScale()
                        )
                        - this.getWidth() / 2;
                  }
   
                  @Override
                  protected int getPosY() {
                     return CFG.game
                           .getDrawProvinceArmy_EndPosY(
                              CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCapitalProvinceID(),
                              CFG.map.getMapScale().getCurrentScale()
                           )
                        + (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) / 2
                        + CFG.ARMY_BG_EXTRA_HEIGHT;
                  }
               }
            );
            tempBoxes.add(new TutorialBox(CFG.langManager.get("1")) {
               @Override
               protected void draw(SpriteBatch oSB) {
                  if (CFG.menuManager.getInGame_Messages().getVisible()) {
                     super.draw(oSB);
                  }
               }

               @Override
               protected int getPosX() {
                  return CFG.menuManager.getInGame_Messages().getPosX();
               }

               @Override
               protected int getPosY() {
                  return CFG.menuManager.getInGame_Messages().getPosY() - CFG.PADDING / 2 - CFG.PADDING - this.getHeight();
               }
            });
            this.tutBoxes.add(tempBoxes);
            this.tutStep = new TutorialManager.TutStep() {
               @Override
               public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY) {
                  if (CFG.menuManager.getInGameView()) {
                     try {
                        TutorialManager.this.textBox
                           .get(0)
                           .draw_HoverWithoutAnimation(
                              oSB,
                              (
                                    !CFG.menuManager.getInGame_ProvinceBuild_Visible() && !CFG.menuManager.getInGame_CivInfo().getVisible()
                                       ? TutorialManager.this.iDrawPosX
                                       : CFG.CIV_INFO_MENU_WIDTH + CFG.PADDING * 2
                                 )
                                 + AoCGame.LEFT,
                              TutorialManager.this.iDrawPosY
                           );
                        int tElemID = 0;
                        TutorialManager.this.drawLine(
                           oSB,
                           CFG.menuManager.getInGame_ProvinceInfo().getPosX() + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(tElemID).getPosX(),
                           -CFG.PADDING / 2
                              + CFG.menuManager.getInGame_ProvinceInfo().getPosY()
                              + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(tElemID).getPosY(),
                           CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(tElemID).getWidth()
                        );
                        if (CFG.menuManager.getInGame_Messages().getVisible()) {
                           TutorialManager.this.drawLine(
                              oSB,
                              CFG.menuManager.getInGame_Messages().getPosX(),
                              -CFG.PADDING / 2 + CFG.menuManager.getInGame_Messages().getPosY(),
                              CFG.menuManager.getInGame_Messages().getWidth()
                           );
                        }

                        for(int i = 0; i < TutorialManager.this.tutBoxes.get(TutorialManager.this.INNER_STEP).size(); ++i) {
                           TutorialManager.this.tutBoxes.get(TutorialManager.this.INNER_STEP).get(i).draw(oSB);
                        }
                     } catch (IndexOutOfBoundsException var6) {
                     } catch (NullPointerException var7) {
                     }
                  }
               }

               @Override
               public boolean action(Tutorial_ActionType actionType) {
                  if (actionType == Tutorial_ActionType.NEXT_TURN && CFG.gameAction.getActiveTurnState() == Game_Action.TurnStates.INPUT_ORDERS) {
                     ++TutorialManager.this.STEP_ID;
                     TutorialManager.this.updateDrawTutorial(TutorialManager.this.IN_TUTORIAL);
                     CFG.game.setActiveProvinceID(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCapitalProvinceID());
                     return true;
                  } else {
                     return false;
                  }
               }
            };
         } else if (this.STEP_ID == 3) {
            this.iDrawPosX = CFG.PADDING * 2 + AoCGame.LEFT;
            this.iDrawPosY = ImageManager.getImage(Images.top_left).getHeight() + CFG.PADDING * 2;
            this.textBox.clear();
            this.tutBoxes.clear();
            List<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
            List<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
            List<String> tempText = this.getTextSplited(CFG.langManager.get("t6"));

            for(int i = 0; i < tempText.size(); ++i) {
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(tempText.get(i), CFG.COLOR_TEXT_NUM_OF_PROVINCES));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
            }

            tempText.clear();
            tempText = this.getTextSplited(CFG.langManager.get("t6a"));

            for(int i = 0; i < tempText.size(); ++i) {
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(tempText.get(i), COLOR_TEXT));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
            }

            nData.add(new MenuElement_Hover_v2_Element_Type_Space());
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
            tempText.clear();
            tempText = this.getTextSplited(CFG.langManager.get("t3"));

            for(int i = 0; i < tempText.size(); ++i) {
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(tempText.get(i), CFG.COLOR_TEXT_NUM_OF_PROVINCES));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
            }

            tempText.clear();
            tempText = this.getTextSplited(CFG.langManager.get("t4"));

            for(int i = 0; i < tempText.size(); ++i) {
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(tempText.get(i), COLOR_TEXT));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
            }

            tempText.clear();
            tempText = this.getTextSplited(CFG.langManager.get("t5"));

            for(int i = 0; i < tempText.size(); ++i) {
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(tempText.get(i), COLOR_TEXT));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
            }

            this.textBox.add(new MenuElement_Hover_v2(nElements));
            List<TutorialBox> tempBoxes = new ArrayList<>();
            tempBoxes.add(
               new TutorialBox(CFG.langManager.get("Next")) {
                  @Override
                  protected int getPosX() {
                     return CFG.menuManager.getInGame_ProvinceInfo().getPosX() + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(0).getPosX();
                  }
   
                  @Override
                  protected int getPosY() {
                     return CFG.menuManager.getInGame_ProvinceInfo().getPosY()
                        + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(0).getPosY()
                        - CFG.PADDING / 2
                        - CFG.PADDING
                        - this.getHeight();
                  }
               }
            );
            this.tutBoxes.add(tempBoxes);
            this.tutStep = new TutorialManager.TutStep() {
               @Override
               public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY) {
                  if (CFG.menuManager.getInGameView()) {
                     try {
                        TutorialManager.this.textBox
                           .get(0)
                           .draw_HoverWithoutAnimation(
                              oSB,
                              !CFG.menuManager.getInGame_ProvinceBuild_Visible() && !CFG.menuManager.getInGame_CivInfo().getVisible()
                                 ? TutorialManager.this.iDrawPosX
                                 : CFG.CIV_INFO_MENU_WIDTH + CFG.PADDING * 2,
                              TutorialManager.this.iDrawPosY
                           );
                        int tElemID = 0;
                        TutorialManager.this.drawLine(
                           oSB,
                           CFG.menuManager.getInGame_ProvinceInfo().getPosX() + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(tElemID).getPosX(),
                           -CFG.PADDING / 2
                              + CFG.menuManager.getInGame_ProvinceInfo().getPosY()
                              + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(tElemID).getPosY(),
                           CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(tElemID).getWidth()
                        );

                        for(int i = 0; i < TutorialManager.this.tutBoxes.get(TutorialManager.this.INNER_STEP).size(); ++i) {
                           TutorialManager.this.tutBoxes.get(TutorialManager.this.INNER_STEP).get(i).draw(oSB);
                        }
                     } catch (IndexOutOfBoundsException var6) {
                     } catch (NullPointerException var7) {
                     }
                  }
               }

               @Override
               public boolean action(Tutorial_ActionType actionType) {
                  if (actionType == Tutorial_ActionType.NEXT_TURN && CFG.gameAction.getActiveTurnState() == Game_Action.TurnStates.INPUT_ORDERS) {
                     ++TutorialManager.this.STEP_ID;
                     TutorialManager.this.updateDrawTutorial(TutorialManager.this.IN_TUTORIAL);
                     CFG.game.setActiveProvinceID(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCapitalProvinceID());
                     return true;
                  } else {
                     return false;
                  }
               }
            };
         } else if (this.STEP_ID == 4) {
            this.iDrawPosX = CFG.PADDING * 2 + AoCGame.LEFT;
            this.iDrawPosY = ImageManager.getImage(Images.top_left).getHeight() + CFG.PADDING * 2;
            this.textBox.clear();
            this.tutBoxes.clear();
            List<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
            List<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("b0"), CFG.COLOR_TEXT_NUM_OF_PROVINCES));
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
            nData.add(new MenuElement_Hover_v2_Element_Type_Text("1. ", CFG.COLOR_TEXT_NUM_OF_PROVINCES));
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Minimap"), COLOR_TITLE));
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
            nData.add(new MenuElement_Hover_v2_Element_Type_Text("2. ", CFG.COLOR_TEXT_NUM_OF_PROVINCES));
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("InformationAboutSelectedProvince"), COLOR_TITLE));
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
            nData.add(new MenuElement_Hover_v2_Element_Type_Text("3. ", CFG.COLOR_TEXT_NUM_OF_PROVINCES));
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("EndTurnOrContinue"), COLOR_TITLE));
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
            this.textBox.add(new MenuElement_Hover_v2(nElements));
            List<TutorialBox> tempBoxes = new ArrayList<>();
            tempBoxes.add(
               new TutorialBox("3 - " + CFG.langManager.get("Next")) {
                  @Override
                  protected int getPosX() {
                     return CFG.menuManager.getInGame_ProvinceInfo().getPosX() + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(0).getPosX();
                  }
   
                  @Override
                  protected int getPosY() {
                     return CFG.menuManager.getInGame_ProvinceInfo().getPosY()
                        + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(0).getPosY()
                        - CFG.PADDING / 2
                        - CFG.PADDING
                        - this.getHeight();
                  }
               }
            );
            tempBoxes.add(new TutorialBox(CFG.langManager.get("1")) {
               @Override
               protected int getPosX() {
                  return CFG.menuManager.getInGame().getPosX() + CFG.menuManager.getInGame().getMenuElement(0).getPosX() + CFG.PADDING;
               }

               @Override
               protected int getPosY() {
                  return CFG.menuManager.getInGame_ProvinceInfo().getPosY() - CFG.PADDING / 2 - CFG.PADDING - this.getHeight();
               }
            });
            tempBoxes.add(new TutorialBox(CFG.langManager.get("2")) {
               @Override
               protected int getPosX() {
                  return CFG.menuManager.getInGame_ProvinceInfo().getPosX();
               }

               @Override
               protected int getPosY() {
                  return CFG.menuManager.getInGame_ProvinceInfo().getPosY() - CFG.PADDING / 2 - CFG.PADDING - this.getHeight();
               }
            });
            this.tutBoxes.add(tempBoxes);
            this.tutStep = new TutorialManager.TutStep() {
               @Override
               public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY) {
                  if (CFG.menuManager.getInGameView()) {
                     try {
                        TutorialManager.this.textBox
                           .get(0)
                           .draw_HoverWithoutAnimation(
                              oSB,
                              !CFG.menuManager.getInGame_ProvinceBuild_Visible() && !CFG.menuManager.getInGame_CivInfo().getVisible()
                                 ? TutorialManager.this.iDrawPosX
                                 : CFG.CIV_INFO_MENU_WIDTH + CFG.PADDING * 2,
                              TutorialManager.this.iDrawPosY
                           );
                        int tElemID = 0;
                        TutorialManager.this.drawLine(
                           oSB,
                           CFG.menuManager.getInGame_ProvinceInfo().getPosX() + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(tElemID).getPosX(),
                           -CFG.PADDING / 2
                              + CFG.menuManager.getInGame_ProvinceInfo().getPosY()
                              + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(tElemID).getPosY(),
                           CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(tElemID).getWidth()
                        );
                        TutorialManager.this.drawLine(
                           oSB,
                           CFG.menuManager.getInGame_ProvinceInfo().getPosX(),
                           -CFG.PADDING / 2 + CFG.menuManager.getInGame_ProvinceInfo().getPosY(),
                           CFG.menuManager.getInGame_ProvinceInfo().getWidth()
                              - CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(0).getWidth()
                              - CFG.PADDING * 2
                        );
                        TutorialManager.this.drawLine(
                           oSB,
                           CFG.menuManager.getInGame().getPosX() + CFG.menuManager.getInGame().getMenuElement(0).getPosX(),
                           -CFG.PADDING / 2 + CFG.menuManager.getInGame_ProvinceInfo().getPosY(),
                           CFG.menuManager.getInGame().getMenuElement(0).getWidth()
                        );

                        for(int i = 0; i < TutorialManager.this.tutBoxes.get(TutorialManager.this.INNER_STEP).size(); ++i) {
                           TutorialManager.this.tutBoxes.get(TutorialManager.this.INNER_STEP).get(i).draw(oSB);
                        }
                     } catch (IndexOutOfBoundsException var6) {
                     } catch (NullPointerException var7) {
                     }
                  }
               }

               @Override
               public boolean action(Tutorial_ActionType actionType) {
                  if (actionType == Tutorial_ActionType.NEXT_TURN && CFG.gameAction.getActiveTurnState() == Game_Action.TurnStates.INPUT_ORDERS) {
                     ++TutorialManager.this.STEP_ID;
                     TutorialManager.this.updateDrawTutorial(TutorialManager.this.IN_TUTORIAL);
                     CFG.game.setActiveProvinceID(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCapitalProvinceID());
                     return true;
                  } else {
                     return false;
                  }
               }
            };
         } else if (this.STEP_ID == 5) {
            this.iDrawPosX = CFG.PADDING * 2 + AoCGame.LEFT;
            this.iDrawPosY = ImageManager.getImage(Images.top_left).getHeight() + CFG.PADDING * 2;
            this.textBox.clear();
            this.tutBoxes.clear();
            List<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
            List<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
            nData.add(new MenuElement_Hover_v2_Element_Type_Text("1. ", CFG.COLOR_TEXT_NUM_OF_PROVINCES));
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("b1"), COLOR_TITLE));
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(" - ", COLOR_TITLE));
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("OpenCloseCivilizationInformationsView"), COLOR_TEXT));
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
            nData.add(new MenuElement_Hover_v2_Element_Type_Text("2. ", CFG.COLOR_TEXT_NUM_OF_PROVINCES));
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("CoreIsALegitimatePartOfCivilization"), COLOR_TITLE));
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(" - ", COLOR_TITLE));
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("b2"), COLOR_TEXT));
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
            nData.add(new MenuElement_Hover_v2_Element_Type_Text("3. ", CFG.COLOR_TEXT_NUM_OF_PROVINCES));
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("ProvinceValue"), COLOR_TITLE));
            nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.victoryPoints, CFG.PADDING, 0));
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
            nData.add(new MenuElement_Hover_v2_Element_Type_Text("4. ", CFG.COLOR_TEXT_NUM_OF_PROVINCES));
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("TerrainType"), COLOR_TITLE));
            nData.add(new MenuElement_Hover_v2_Element_Type_Terrain(1, CFG.PADDING, 0));
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(" - ", COLOR_TITLE));
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("b3"), COLOR_TEXT));
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
            this.textBox.add(new MenuElement_Hover_v2(nElements));
            List<TutorialBox> tempBoxes = new ArrayList<>();
            tempBoxes.add(
               new TutorialBox(CFG.langManager.get("Next")) {
                  @Override
                  protected int getPosX() {
                     return CFG.menuManager.getInGame_ProvinceInfo().getPosX() + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(0).getPosX();
                  }
   
                  @Override
                  protected int getPosY() {
                     return CFG.menuManager.getInGame_ProvinceInfo().getPosY()
                        + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(0).getPosY()
                        - CFG.PADDING / 2
                        - CFG.PADDING
                        - this.getHeight();
                  }
               }
            );
            tempBoxes.add(
               new TutorialBox(CFG.langManager.get("1")) {
                  @Override
                  protected int getPosX() {
                     return CFG.menuManager.getInGame_ProvinceInfo().getPosX() + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(2).getPosX();
                  }
   
                  @Override
                  protected int getPosY() {
                     return CFG.menuManager.getInGame_ProvinceInfo().getPosY()
                        + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(2).getPosY()
                        - CFG.PADDING / 2
                        - CFG.PADDING
                        - this.getHeight();
                  }
               }
            );
            tempBoxes.add(
               new TutorialBox(CFG.langManager.get("2")) {
                  @Override
                  protected int getPosX() {
                     return CFG.menuManager.getInGame_ProvinceInfo().getPosX() + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(3).getPosX();
                  }
   
                  @Override
                  protected int getPosY() {
                     return CFG.menuManager.getInGame_ProvinceInfo().getPosY()
                        + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(3).getPosY()
                        - CFG.PADDING / 2
                        - CFG.PADDING
                        - this.getHeight();
                  }
               }
            );
            tempBoxes.add(
               new TutorialBox(CFG.langManager.get("3")) {
                  @Override
                  protected int getPosX() {
                     return CFG.menuManager.getInGame_ProvinceInfo().getPosX() + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(8).getPosX();
                  }
   
                  @Override
                  protected int getPosY() {
                     return CFG.menuManager.getInGame_ProvinceInfo().getPosY()
                        + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(8).getPosY()
                        - CFG.PADDING / 2
                        - CFG.PADDING
                        - this.getHeight();
                  }
               }
            );
            tempBoxes.add(
               new TutorialBox(CFG.langManager.get("4")) {
                  @Override
                  protected int getPosX() {
                     return CFG.menuManager.getInGame_ProvinceInfo().getPosX() + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(6).getPosX();
                  }
   
                  @Override
                  protected int getPosY() {
                     return CFG.menuManager.getInGame_ProvinceInfo().getPosY()
                        + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(6).getPosY()
                        - CFG.PADDING / 2
                        - CFG.PADDING
                        - this.getHeight();
                  }
               }
            );
            this.tutBoxes.add(tempBoxes);
            this.tutStep = new TutorialManager.TutStep() {
               @Override
               public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY) {
                  if (CFG.menuManager.getInGameView()) {
                     try {
                        TutorialManager.this.textBox
                           .get(0)
                           .draw_HoverWithoutAnimation(
                              oSB,
                              !CFG.menuManager.getInGame_ProvinceBuild_Visible() && !CFG.menuManager.getInGame_CivInfo().getVisible()
                                 ? TutorialManager.this.iDrawPosX
                                 : CFG.CIV_INFO_MENU_WIDTH + CFG.PADDING * 2,
                              TutorialManager.this.iDrawPosY
                           );
                        if (CFG.game.getActiveProvinceID() < 0
                           || CFG.game.getProvince(CFG.game.getActiveProvinceID()).getSeaProvince()
                           || CFG.game.getProvince(CFG.game.getActiveProvinceID()).getWasteland() >= 0) {
                           CFG.game.setActiveProvinceID(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCapitalProvinceID());
                        }

                        int tElemID = 0;
                        TutorialManager.this.drawLine(
                           oSB,
                           CFG.menuManager.getInGame_ProvinceInfo().getPosX() + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(tElemID).getPosX(),
                           -CFG.PADDING / 2
                              + CFG.menuManager.getInGame_ProvinceInfo().getPosY()
                              + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(tElemID).getPosY(),
                           CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(tElemID).getWidth()
                        );
                        int var8 = 2;
                        TutorialManager.this.drawLine(
                           oSB,
                           CFG.menuManager.getInGame_ProvinceInfo().getPosX() + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(var8).getPosX(),
                           -CFG.PADDING / 2
                              + CFG.menuManager.getInGame_ProvinceInfo().getPosY()
                              + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(var8).getPosY(),
                           CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(var8).getWidth()
                        );
                        var8 = 3;
                        TutorialManager.this.drawLine(
                           oSB,
                           CFG.menuManager.getInGame_ProvinceInfo().getPosX() + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(var8).getPosX(),
                           -CFG.PADDING / 2
                              + CFG.menuManager.getInGame_ProvinceInfo().getPosY()
                              + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(var8).getPosY(),
                           CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(var8).getWidth()
                        );
                        var8 = 8;
                        TutorialManager.this.drawLine(
                           oSB,
                           CFG.menuManager.getInGame_ProvinceInfo().getPosX() + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(var8).getPosX(),
                           -CFG.PADDING / 2
                              + CFG.menuManager.getInGame_ProvinceInfo().getPosY()
                              + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(var8).getPosY(),
                           CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(var8).getWidth()
                        );
                        var8 = 6;
                        TutorialManager.this.drawLine(
                           oSB,
                           CFG.menuManager.getInGame_ProvinceInfo().getPosX() + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(var8).getPosX(),
                           -CFG.PADDING / 2
                              + CFG.menuManager.getInGame_ProvinceInfo().getPosY()
                              + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(var8).getPosY(),
                           CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(var8).getWidth()
                        );

                        for(int i = 0; i < TutorialManager.this.tutBoxes.get(TutorialManager.this.INNER_STEP).size(); ++i) {
                           TutorialManager.this.tutBoxes.get(TutorialManager.this.INNER_STEP).get(i).draw(oSB);
                        }
                     } catch (IndexOutOfBoundsException var6) {
                     } catch (NullPointerException var7) {
                     }
                  }
               }

               @Override
               public boolean action(Tutorial_ActionType actionType) {
                  if (actionType == Tutorial_ActionType.NEXT_TURN && CFG.gameAction.getActiveTurnState() == Game_Action.TurnStates.INPUT_ORDERS) {
                     ++TutorialManager.this.STEP_ID;
                     TutorialManager.this.updateDrawTutorial(TutorialManager.this.IN_TUTORIAL);
                     CFG.game.setActiveProvinceID(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCapitalProvinceID());
                     return true;
                  } else {
                     return false;
                  }
               }
            };
         } else if (this.STEP_ID == 6) {
            this.iDrawPosX = CFG.PADDING * 2 + AoCGame.LEFT;
            this.iDrawPosY = ImageManager.getImage(Images.top_left).getHeight() + CFG.PADDING * 2;
            this.textBox.clear();
            this.tutBoxes.clear();
            List<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
            List<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
            nData.add(new MenuElement_Hover_v2_Element_Type_Text("1. ", CFG.COLOR_TEXT_NUM_OF_PROVINCES));
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("u0"), COLOR_TITLE));
            nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.population, CFG.PADDING, 0));
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(" - ", COLOR_TITLE));
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("u0a"), COLOR_TEXT));
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(" - ", COLOR_TITLE));
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("u0b"), COLOR_TEXT));
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
            nData.add(new MenuElement_Hover_v2_Element_Type_Text("2. ", CFG.COLOR_TEXT_NUM_OF_PROVINCES));
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("g0"), COLOR_TITLE));
            nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.population_growth, CFG.PADDING, 0));
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(" - ", COLOR_TITLE));
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("g1"), COLOR_TEXT));
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
            nData.add(new MenuElement_Hover_v2_Element_Type_Text("3. ", CFG.COLOR_TEXT_NUM_OF_PROVINCES));
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Happiness"), COLOR_TITLE));
            nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.happiness, CFG.PADDING, 0));
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(" - ", COLOR_TITLE));
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("ha0"), COLOR_TEXT));
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(" - ", COLOR_TITLE));
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("ha1"), COLOR_TEXT));
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
            this.textBox.add(new MenuElement_Hover_v2(nElements));
            List<TutorialBox> tempBoxes = new ArrayList<>();
            tempBoxes.add(
               new TutorialBox(CFG.langManager.get("Next")) {
                  @Override
                  protected int getPosX() {
                     return CFG.menuManager.getInGame_ProvinceInfo().getPosX() + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(0).getPosX();
                  }
   
                  @Override
                  protected int getPosY() {
                     return CFG.menuManager.getInGame_ProvinceInfo().getPosY()
                        + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(0).getPosY()
                        - CFG.PADDING / 2
                        - CFG.PADDING
                        - this.getHeight();
                  }
               }
            );
            tempBoxes.add(
               new TutorialBox(CFG.langManager.get("1")) {
                  @Override
                  protected int getPosX() {
                     return CFG.menuManager.getInGame_ProvinceInfo().getPosX() + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(4).getPosX();
                  }
   
                  @Override
                  protected int getPosY() {
                     return CFG.menuManager.getInGame_ProvinceInfo().getPosY()
                        + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(4).getPosY()
                        - CFG.PADDING / 2
                        - CFG.PADDING
                        - this.getHeight();
                  }
               }
            );
            tempBoxes.add(
               new TutorialBox(CFG.langManager.get("2")) {
                  @Override
                  protected int getPosX() {
                     return CFG.menuManager.getInGame_ProvinceInfo().getPosX() + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(9).getPosX();
                  }
   
                  @Override
                  protected int getPosY() {
                     return CFG.menuManager.getInGame_ProvinceInfo().getPosY()
                        + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(9).getPosY()
                        - CFG.PADDING / 2
                        - CFG.PADDING
                        - this.getHeight();
                  }
               }
            );
            tempBoxes.add(
               new TutorialBox(CFG.langManager.get("3")) {
                  @Override
                  protected int getPosX() {
                     return CFG.menuManager.getInGame_ProvinceInfo().getPosX() + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(11).getPosX();
                  }
   
                  @Override
                  protected int getPosY() {
                     return CFG.menuManager.getInGame_ProvinceInfo().getPosY()
                        + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(11).getPosY()
                        - CFG.PADDING / 2
                        - CFG.PADDING
                        - this.getHeight();
                  }
               }
            );
            tempBoxes.add(
               new TutorialBox(CFG.langManager.get("1")) {
                  @Override
                  protected int getPosX() {
                     return CFG.menuManager.getInGame_ProvinceInfo().getPosX() + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(5).getPosX();
                  }
   
                  @Override
                  protected int getPosY() {
                     return CFG.menuManager.getInGame_ProvinceInfo().getPosY()
                        + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(5).getPosY()
                        - CFG.PADDING / 2
                        - CFG.PADDING
                        - this.getHeight();
                  }
               }
            );
            this.tutBoxes.add(tempBoxes);
            this.tutStep = new TutorialManager.TutStep() {
               @Override
               public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY) {
                  if (CFG.menuManager.getInGameView()) {
                     try {
                        TutorialManager.this.textBox
                           .get(0)
                           .draw_HoverWithoutAnimation(
                              oSB,
                              !CFG.menuManager.getInGame_ProvinceBuild_Visible() && !CFG.menuManager.getInGame_CivInfo().getVisible()
                                 ? TutorialManager.this.iDrawPosX
                                 : CFG.CIV_INFO_MENU_WIDTH + CFG.PADDING * 2,
                              TutorialManager.this.iDrawPosY
                           );
                        if (CFG.game.getActiveProvinceID() < 0
                           || CFG.game.getProvince(CFG.game.getActiveProvinceID()).getSeaProvince()
                           || CFG.game.getProvince(CFG.game.getActiveProvinceID()).getWasteland() >= 0) {
                           CFG.game.setActiveProvinceID(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCapitalProvinceID());
                        }

                        int tElemID = 0;
                        TutorialManager.this.drawLine(
                           oSB,
                           CFG.menuManager.getInGame_ProvinceInfo().getPosX() + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(tElemID).getPosX(),
                           -CFG.PADDING / 2
                              + CFG.menuManager.getInGame_ProvinceInfo().getPosY()
                              + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(tElemID).getPosY(),
                           CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(tElemID).getWidth()
                        );
                        int var8 = 4;
                        TutorialManager.this.drawLine(
                           oSB,
                           CFG.menuManager.getInGame_ProvinceInfo().getPosX() + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(var8).getPosX(),
                           -CFG.PADDING / 2
                              + CFG.menuManager.getInGame_ProvinceInfo().getPosY()
                              + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(var8).getPosY(),
                           CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(var8).getWidth()
                        );
                        var8 = 9;
                        TutorialManager.this.drawLine(
                           oSB,
                           CFG.menuManager.getInGame_ProvinceInfo().getPosX() + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(var8).getPosX(),
                           -CFG.PADDING / 2
                              + CFG.menuManager.getInGame_ProvinceInfo().getPosY()
                              + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(var8).getPosY(),
                           CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(var8).getWidth()
                        );
                        var8 = 5;
                        TutorialManager.this.drawLine(
                           oSB,
                           CFG.menuManager.getInGame_ProvinceInfo().getPosX() + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(var8).getPosX(),
                           -CFG.PADDING / 2
                              + CFG.menuManager.getInGame_ProvinceInfo().getPosY()
                              + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(var8).getPosY(),
                           CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(var8).getWidth()
                        );
                        var8 = 11;
                        TutorialManager.this.drawLine(
                           oSB,
                           CFG.menuManager.getInGame_ProvinceInfo().getPosX() + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(var8).getPosX(),
                           -CFG.PADDING / 2
                              + CFG.menuManager.getInGame_ProvinceInfo().getPosY()
                              + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(var8).getPosY(),
                           CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(var8).getWidth()
                        );

                        for(int i = 0; i < TutorialManager.this.tutBoxes.get(TutorialManager.this.INNER_STEP).size(); ++i) {
                           TutorialManager.this.tutBoxes.get(TutorialManager.this.INNER_STEP).get(i).draw(oSB);
                        }
                     } catch (IndexOutOfBoundsException var6) {
                     } catch (NullPointerException var7) {
                     }
                  }
               }

               @Override
               public boolean action(Tutorial_ActionType actionType) {
                  if (actionType == Tutorial_ActionType.NEXT_TURN && CFG.gameAction.getActiveTurnState() == Game_Action.TurnStates.INPUT_ORDERS) {
                     ++TutorialManager.this.STEP_ID;
                     TutorialManager.this.updateDrawTutorial(TutorialManager.this.IN_TUTORIAL);
                     CFG.game.setActiveProvinceID(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCapitalProvinceID());
                     return true;
                  } else {
                     return false;
                  }
               }
            };
         } else if (this.STEP_ID == 7) {
            this.iDrawPosX = CFG.PADDING * 2 + AoCGame.LEFT;
            this.iDrawPosY = ImageManager.getImage(Images.top_left).getHeight() + CFG.PADDING * 2;
            this.textBox.clear();
            this.tutBoxes.clear();
            List<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
            List<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
            nData.add(new MenuElement_Hover_v2_Element_Type_Text("1. ", CFG.COLOR_TEXT_NUM_OF_PROVINCES));
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("EconomyOfProvince"), COLOR_TITLE));
            nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.economy, CFG.PADDING, 0));
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
            nData.add(new MenuElement_Hover_v2_Element_Type_Text("2. ", CFG.COLOR_TEXT_NUM_OF_PROVINCES));
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("DevelopmentLevel"), COLOR_TITLE));
            nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.development, CFG.PADDING, 0));
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
            List<String> tempText = this.getTextSplited(CFG.langManager.get("Tech4"));
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(" - ", COLOR_TITLE));

            for(int i = 0; i < tempText.size(); ++i) {
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(tempText.get(i), COLOR_TEXT));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
            }

            tempText.clear();
            tempText = this.getTextSplited(CFG.langManager.get("Tech5"));
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(" - ", COLOR_TITLE));

            for(int i = 0; i < tempText.size(); ++i) {
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(tempText.get(i), COLOR_TEXT));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
            }

            nData.add(new MenuElement_Hover_v2_Element_Type_Text("3. ", CFG.COLOR_TEXT_NUM_OF_PROVINCES));
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("ProvinceStability"), COLOR_TITLE));
            nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.diplo_popstability, CFG.PADDING, 0));
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
            nData.add(new MenuElement_Hover_v2_Element_Type_Text("4. ", CFG.COLOR_TEXT_NUM_OF_PROVINCES));
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("RevolutionaryRisk"), COLOR_TITLE));
            nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.diplo_revolution, CFG.PADDING, 0));
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
            nData.add(new MenuElement_Hover_v2_Element_Type_Text("5. ", CFG.COLOR_TEXT_NUM_OF_PROVINCES));
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Buildings"), COLOR_TITLE));
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
            nData.add(new MenuElement_Hover_v2_Element_Type_Text("6. ", CFG.COLOR_TEXT_NUM_OF_PROVINCES));
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("ProvinceName"), COLOR_TITLE));
            nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.provinces, CFG.PADDING, 0));
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
            this.textBox.add(new MenuElement_Hover_v2(nElements));
            List<TutorialBox> tempBoxes = new ArrayList<>();
            tempBoxes.add(
               new TutorialBox(CFG.langManager.get("Next")) {
                  @Override
                  protected int getPosX() {
                     return CFG.menuManager.getInGame_ProvinceInfo().getPosX() + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(0).getPosX();
                  }
   
                  @Override
                  protected int getPosY() {
                     return CFG.menuManager.getInGame_ProvinceInfo().getPosY()
                        + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(0).getPosY()
                        - CFG.PADDING / 2
                        - CFG.PADDING
                        - this.getHeight();
                  }
               }
            );
            tempBoxes.add(
               new TutorialBox(CFG.langManager.get("1")) {
                  @Override
                  protected int getPosX() {
                     return CFG.menuManager.getInGame_ProvinceInfo().getPosX() + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(7).getPosX();
                  }
   
                  @Override
                  protected int getPosY() {
                     return CFG.menuManager.getInGame_ProvinceInfo().getPosY()
                        + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(7).getPosY()
                        - CFG.PADDING / 2
                        - CFG.PADDING
                        - this.getHeight();
                  }
               }
            );
            tempBoxes.add(
               new TutorialBox(CFG.langManager.get("2")) {
                  @Override
                  protected int getPosX() {
                     return CFG.menuManager.getInGame_ProvinceInfo().getPosX() + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(10).getPosX();
                  }
   
                  @Override
                  protected int getPosY() {
                     return CFG.menuManager.getInGame_ProvinceInfo().getPosY()
                        + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(10).getPosY()
                        - CFG.PADDING / 2
                        - CFG.PADDING
                        - this.getHeight();
                  }
               }
            );
            tempBoxes.add(
               new TutorialBox(CFG.langManager.get("3")) {
                  @Override
                  protected int getPosX() {
                     return CFG.menuManager.getInGame_ProvinceInfo().getPosX() + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(13).getPosX();
                  }
   
                  @Override
                  protected int getPosY() {
                     return CFG.menuManager.getInGame_ProvinceInfo().getPosY()
                        + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(13).getPosY()
                        - CFG.PADDING / 2
                        - CFG.PADDING
                        - this.getHeight();
                  }
               }
            );
            tempBoxes.add(
               new TutorialBox(CFG.langManager.get("4")) {
                  @Override
                  protected int getPosX() {
                     return CFG.menuManager.getInGame_ProvinceInfo().getPosX() + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(15).getPosX();
                  }
   
                  @Override
                  protected int getPosY() {
                     return CFG.menuManager.getInGame_ProvinceInfo().getPosY()
                        + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(15).getPosY()
                        - CFG.PADDING / 2
                        - CFG.PADDING
                        - this.getHeight();
                  }
               }
            );
            tempBoxes.add(
               new TutorialBox(CFG.langManager.get("5")) {
                  @Override
                  protected int getPosX() {
                     return CFG.menuManager.getInGame_ProvinceInfo().getPosX() + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(14).getPosX();
                  }
   
                  @Override
                  protected int getPosY() {
                     return CFG.menuManager.getInGame_ProvinceInfo().getPosY()
                        + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(14).getPosY()
                        - CFG.PADDING / 2
                        - CFG.PADDING
                        - this.getHeight();
                  }
               }
            );
            tempBoxes.add(
               new TutorialBox(CFG.langManager.get("6")) {
                  @Override
                  protected int getPosX() {
                     return CFG.menuManager.getInGame_ProvinceInfo().getPosX() + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(1).getPosX();
                  }
   
                  @Override
                  protected int getPosY() {
                     return CFG.menuManager.getInGame_ProvinceInfo().getPosY()
                        + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(1).getPosY()
                        - CFG.PADDING / 2
                        - CFG.PADDING
                        - this.getHeight();
                  }
               }
            );
            this.tutBoxes.add(tempBoxes);
            this.tutStep = new TutorialManager.TutStep() {
               @Override
               public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY) {
                  if (CFG.menuManager.getInGameView()) {
                     try {
                        TutorialManager.this.textBox
                           .get(0)
                           .draw_HoverWithoutAnimation(
                              oSB,
                              !CFG.menuManager.getInGame_ProvinceBuild_Visible() && !CFG.menuManager.getInGame_CivInfo().getVisible()
                                 ? TutorialManager.this.iDrawPosX
                                 : CFG.CIV_INFO_MENU_WIDTH + CFG.PADDING * 2,
                              TutorialManager.this.iDrawPosY
                           );
                        if (CFG.game.getActiveProvinceID() < 0
                           || CFG.game.getActiveProvinceID() != CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCapitalProvinceID()
                           || CFG.game.getProvince(CFG.game.getActiveProvinceID()).getSeaProvince()
                           || CFG.game.getProvince(CFG.game.getActiveProvinceID()).getWasteland() >= 0) {
                           CFG.game.setActiveProvinceID(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCapitalProvinceID());
                        }

                        int tElemID = 7;
                        TutorialManager.this.drawLine(
                           oSB,
                           CFG.menuManager.getInGame_ProvinceInfo().getPosX() + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(tElemID).getPosX(),
                           -CFG.PADDING / 2
                              + CFG.menuManager.getInGame_ProvinceInfo().getPosY()
                              + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(tElemID).getPosY(),
                           CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(tElemID).getWidth()
                        );
                        int var8 = 10;
                        TutorialManager.this.drawLine(
                           oSB,
                           CFG.menuManager.getInGame_ProvinceInfo().getPosX() + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(var8).getPosX(),
                           -CFG.PADDING / 2
                              + CFG.menuManager.getInGame_ProvinceInfo().getPosY()
                              + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(var8).getPosY(),
                           CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(var8).getWidth()
                        );
                        var8 = 15;
                        TutorialManager.this.drawLine(
                           oSB,
                           CFG.menuManager.getInGame_ProvinceInfo().getPosX() + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(var8).getPosX(),
                           -CFG.PADDING / 2
                              + CFG.menuManager.getInGame_ProvinceInfo().getPosY()
                              + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(var8).getPosY(),
                           CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(var8).getWidth()
                        );
                        var8 = 13;
                        TutorialManager.this.drawLine(
                           oSB,
                           CFG.menuManager.getInGame_ProvinceInfo().getPosX() + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(var8).getPosX(),
                           -CFG.PADDING / 2
                              + CFG.menuManager.getInGame_ProvinceInfo().getPosY()
                              + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(var8).getPosY(),
                           CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(var8).getWidth()
                        );
                        var8 = 14;
                        TutorialManager.this.drawLine(
                           oSB,
                           CFG.menuManager.getInGame_ProvinceInfo().getPosX() + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(var8).getPosX(),
                           -CFG.PADDING / 2
                              + CFG.menuManager.getInGame_ProvinceInfo().getPosY()
                              + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(var8).getPosY(),
                           CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(var8).getWidth()
                        );
                        var8 = 1;
                        TutorialManager.this.drawLine(
                           oSB,
                           CFG.menuManager.getInGame_ProvinceInfo().getPosX() + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(var8).getPosX(),
                           -CFG.PADDING / 2
                              + CFG.menuManager.getInGame_ProvinceInfo().getPosY()
                              + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(var8).getPosY(),
                           CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(var8).getWidth()
                        );

                        for(int i = 0; i < TutorialManager.this.tutBoxes.get(TutorialManager.this.INNER_STEP).size(); ++i) {
                           TutorialManager.this.tutBoxes.get(TutorialManager.this.INNER_STEP).get(i).draw(oSB);
                        }
                     } catch (IndexOutOfBoundsException var6) {
                     } catch (NullPointerException var7) {
                     }
                  }
               }

               @Override
               public boolean action(Tutorial_ActionType actionType) {
                  if (actionType == Tutorial_ActionType.NEXT_TURN && CFG.gameAction.getActiveTurnState() == Game_Action.TurnStates.INPUT_ORDERS) {
                     ++TutorialManager.this.STEP_ID;
                     TutorialManager.this.updateDrawTutorial(TutorialManager.this.IN_TUTORIAL);
                     CFG.game.setActiveProvinceID(-1);
                     return true;
                  } else {
                     return false;
                  }
               }
            };
         } else if (this.STEP_ID == 8) {
            this.iDrawPosX = CFG.PADDING * 2 + AoCGame.LEFT;
            this.iDrawPosY = ImageManager.getImage(Images.top_left).getHeight() + CFG.PADDING * 2;
            this.textBox.clear();
            this.tutBoxes.clear();
            List<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
            List<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
            List<String> tempText = this.getTextSplited(CFG.langManager.get("a0"));

            for(int i = 0; i < tempText.size(); ++i) {
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(tempText.get(i), CFG.COLOR_TEXT_NUM_OF_PROVINCES));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
            }

            tempText.clear();
            nData.add(new MenuElement_Hover_v2_Element_Type_Text("1. ", CFG.COLOR_TEXT_NUM_OF_PROVINCES));
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("a1"), COLOR_TITLE));
            nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_movement_points, CFG.PADDING, 0));
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
            nData.add(new MenuElement_Hover_v2_Element_Type_Text("2. ", CFG.COLOR_TEXT_NUM_OF_PROVINCES));
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("a2"), COLOR_TITLE));
            nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.diplo_army, CFG.PADDING, 0));
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
            tempText.clear();
            tempText = this.getTextSplited(CFG.langManager.get("a2a"));
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(" - ", COLOR_TITLE));

            for(int i = 0; i < tempText.size(); ++i) {
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(tempText.get(i), COLOR_TEXT));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
            }

            tempText.clear();
            tempText = this.getTextSplited(CFG.langManager.get("a2b"));
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(" - ", COLOR_TITLE));

            for(int i = 0; i < tempText.size(); ++i) {
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(tempText.get(i), COLOR_TEXT));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
            }

            tempText.clear();
            tempText = this.getTextSplited(CFG.langManager.get("ArmyRecruitmentWillTakeOneTurn"));
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(" - ", COLOR_TITLE));

            for(int i = 0; i < tempText.size(); ++i) {
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(tempText.get(i), COLOR_TEXT));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
            }

            nData.add(new MenuElement_Hover_v2_Element_Type_Text("3. ", CFG.COLOR_TEXT_NUM_OF_PROVINCES));
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("a3"), COLOR_TITLE));
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
            tempText.clear();
            tempText = this.getTextSplited(CFG.langManager.get("a3a"));
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(" - ", COLOR_TITLE));

            for(int i = 0; i < tempText.size(); ++i) {
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(tempText.get(i), COLOR_TEXT));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
            }

            tempText.clear();
            tempText = this.getTextSplited(CFG.langManager.get("PlunderOccupiedProvince"));
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(" - ", COLOR_TITLE));

            for(int i = 0; i < tempText.size(); ++i) {
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(tempText.get(i), COLOR_TEXT));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
            }

            tempText.clear();
            tempText = this.getTextSplited(CFG.langManager.get("a3b"));
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(" - ", COLOR_TITLE));
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Conscript") + ": ", COLOR_TITLE));

            for(int i = 0; i < tempText.size(); ++i) {
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(tempText.get(i), COLOR_TEXT));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
            }

            tempText.clear();
            tempText = this.getTextSplited(CFG.langManager.get("a3c"));
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(" - ", COLOR_TITLE));

            for(int i = 0; i < tempText.size(); ++i) {
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(tempText.get(i), COLOR_TEXT));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
            }

            tempText.clear();
            tempText = this.getTextSplited(CFG.langManager.get("a4"));
            nData.add(new MenuElement_Hover_v2_Element_Type_Text("4. ", CFG.COLOR_TEXT_NUM_OF_PROVINCES));

            for(int i = 0; i < tempText.size(); ++i) {
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(tempText.get(i), COLOR_TITLE));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
            }

            this.textBox.add(new MenuElement_Hover_v2(nElements));
            List<TutorialBox> tempBoxes = new ArrayList<>();
            tempBoxes.add(
               new TutorialBox(CFG.langManager.get("Next")) {
                  @Override
                  protected int getPosX() {
                     return CFG.menuManager.getInGame_ProvinceInfo().getPosX() + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(0).getPosX();
                  }
   
                  @Override
                  protected int getPosY() {
                     return CFG.menuManager.getInGame_ProvinceInfo().getPosY()
                        + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(0).getPosY()
                        - CFG.PADDING / 2
                        - CFG.PADDING
                        - this.getHeight();
                  }
               }
            );
            tempBoxes.add(
               new TutorialBox(CFG.langManager.get("1")) {
                  @Override
                  protected int getPosX() {
                     return CFG.menuManager.getInGame_ProvinceAction().getPosX() + CFG.menuManager.getInGame_ProvinceAction().getMenuElement(0).getPosX();
                  }
   
                  @Override
                  protected int getPosY() {
                     return CFG.menuManager.getInGame_ProvinceAction().getPosY()
                        + CFG.menuManager.getInGame_ProvinceAction().getMenuElement(0).getPosY()
                        - CFG.PADDING / 2
                        - CFG.PADDING
                        - this.getHeight();
                  }
               }
            );
            tempBoxes.add(
               new TutorialBox(CFG.langManager.get("2")) {
                  @Override
                  protected int getPosX() {
                     return CFG.menuManager.getInGame_ProvinceAction().getPosX() + CFG.menuManager.getInGame_ProvinceAction().getMenuElement(1).getPosX();
                  }
   
                  @Override
                  protected int getPosY() {
                     return CFG.menuManager.getInGame_ProvinceAction().getPosY()
                        + CFG.menuManager.getInGame_ProvinceAction().getMenuElement(1).getPosY()
                        - CFG.PADDING / 2
                        - CFG.PADDING
                        - this.getHeight();
                  }
               }
            );
            tempBoxes.add(
               new TutorialBox(CFG.langManager.get("3")) {
                  @Override
                  protected int getPosX() {
                     return CFG.menuManager.getInGame_ProvinceAction().getPosX() + CFG.menuManager.getInGame_ProvinceAction().getMenuElement(2).getPosX();
                  }
   
                  @Override
                  protected int getPosY() {
                     return CFG.menuManager.getInGame_ProvinceAction().getPosY()
                        + CFG.menuManager.getInGame_ProvinceAction().getMenuElement(2).getPosY()
                        - CFG.PADDING / 2
                        - CFG.PADDING
                        - this.getHeight();
                  }
               }
            );
            tempBoxes.add(
               new TutorialBox(CFG.langManager.get("4")) {
                  @Override
                  protected int getPosX() {
                     return CFG.menuManager.getInGame_ProvinceAction().getPosX() + CFG.menuManager.getInGame_ProvinceAction().getMenuElement(4).getPosX();
                  }
   
                  @Override
                  protected int getPosY() {
                     return CFG.menuManager.getInGame_ProvinceAction().getPosY()
                        + CFG.menuManager.getInGame_ProvinceAction().getMenuElement(4).getPosY()
                        - CFG.PADDING / 2
                        - CFG.PADDING
                        - this.getHeight();
                  }
               }
            );
            this.tutBoxes.add(tempBoxes);
            List<TutorialBox> tempBoxes2 = new ArrayList<>();
            tempBoxes2.add(new TutorialBox("4. " + CFG.langManager.get("chPr")) {
               @Override
               protected int getPosX() {
                  return CFG.PADDING;
               }

               @Override
               protected int getPosY() {
                  return CFG.GAME_HEIGHT - CFG.map.getMapBG().getMinimapHeight() - CFG.PADDING - this.getHeight();
               }
            });
            tempBoxes2.add(
               new TutorialBox(CFG.langManager.get("4")) {
                  @Override
                  protected int getPosX() {
                     return (int)(
                           (float)(
                                 CFG.game.getProvince(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCapitalProvinceID()).getCenterX()
                                    + CFG.game
                                       .getProvince(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCapitalProvinceID())
                                       .getShiftX()
                                    + CFG.game
                                       .getProvince(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCapitalProvinceID())
                                       .getTranslateProvincePosX()
                              )
                              * CFG.map.getMapScale().getCurrentScale()
                        )
                        - this.getWidth() / 2;
                  }
   
                  @Override
                  protected int getPosY() {
                     return CFG.game
                           .getDrawProvinceArmy_EndPosY(
                              CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCapitalProvinceID(),
                              CFG.map.getMapScale().getCurrentScale()
                           )
                        + (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) / 2
                        + CFG.ARMY_BG_EXTRA_HEIGHT;
                  }
               }
            );
            this.tutBoxes.add(tempBoxes2);
            this.tutStep = new TutorialManager.TutStep() {
               @Override
               public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY) {
                  if (CFG.menuManager.getInGameView()) {
                     try {
                        if (CFG.game.getActiveProvinceID() >= 0 && CFG.menuManager.getVisible_InGame_ProvinceAction()) {
                           TutorialManager.this.INNER_STEP = 0;
                           TutorialManager.this.textBox
                              .get(0)
                              .draw_HoverWithoutAnimation(
                                 oSB,
                                 !CFG.menuManager.getInGame_ProvinceBuild_Visible() && !CFG.menuManager.getInGame_CivInfo().getVisible()
                                    ? TutorialManager.this.iDrawPosX
                                    : CFG.CIV_INFO_MENU_WIDTH + CFG.PADDING * 2,
                                 TutorialManager.this.iDrawPosY
                              );
                           int tElemID = 0;
                           TutorialManager.this.drawLine(
                              oSB,
                              CFG.menuManager.getInGame_ProvinceAction().getPosX()
                                 + CFG.menuManager.getInGame_ProvinceAction().getMenuElement(tElemID).getPosX(),
                              -CFG.PADDING / 2
                                 + CFG.menuManager.getInGame_ProvinceAction().getPosY()
                                 + CFG.menuManager.getInGame_ProvinceAction().getMenuElement(tElemID).getPosY(),
                              CFG.menuManager.getInGame_ProvinceAction().getMenuElement(tElemID).getWidth()
                           );
                           int var7 = 1;
                           TutorialManager.this.drawLine(
                              oSB,
                              CFG.menuManager.getInGame_ProvinceAction().getPosX() + CFG.menuManager.getInGame_ProvinceAction().getMenuElement(var7).getPosX(),
                              -CFG.PADDING / 2
                                 + CFG.menuManager.getInGame_ProvinceAction().getPosY()
                                 + CFG.menuManager.getInGame_ProvinceAction().getMenuElement(var7).getPosY(),
                              CFG.menuManager.getInGame_ProvinceAction().getMenuElement(var7).getWidth()
                           );
                           var7 = 2;
                           TutorialManager.this.drawLine(
                              oSB,
                              CFG.menuManager.getInGame_ProvinceAction().getPosX() + CFG.menuManager.getInGame_ProvinceAction().getMenuElement(var7).getPosX(),
                              -CFG.PADDING / 2
                                 + CFG.menuManager.getInGame_ProvinceAction().getPosY()
                                 + CFG.menuManager.getInGame_ProvinceAction().getMenuElement(var7).getPosY(),
                              CFG.menuManager.getInGame_ProvinceAction().getMenuElement(var7).getWidth()
                           );
                           var7 = 4;
                           TutorialManager.this.drawLine(
                              oSB,
                              CFG.menuManager.getInGame_ProvinceAction().getPosX() + CFG.menuManager.getInGame_ProvinceAction().getMenuElement(var7).getPosX(),
                              -CFG.PADDING / 2
                                 + CFG.menuManager.getInGame_ProvinceAction().getPosY()
                                 + CFG.menuManager.getInGame_ProvinceAction().getMenuElement(var7).getPosY(),
                              CFG.menuManager.getInGame_ProvinceAction().getMenuElement(var7).getWidth()
                           );
                        } else {
                           TutorialManager.this.INNER_STEP = 1;
                        }

                        for(int i = 0; i < TutorialManager.this.tutBoxes.get(TutorialManager.this.INNER_STEP).size(); ++i) {
                           TutorialManager.this.tutBoxes.get(TutorialManager.this.INNER_STEP).get(i).draw(oSB);
                        }
                     } catch (IndexOutOfBoundsException var5) {
                     } catch (NullPointerException var6) {
                     }
                  }
               }

               @Override
               public boolean action(Tutorial_ActionType actionType) {
                  if (actionType == Tutorial_ActionType.NEXT_TURN && CFG.gameAction.getActiveTurnState() == Game_Action.TurnStates.INPUT_ORDERS) {
                     if (CFG.game.getActiveProvinceID() >= 0 && CFG.menuManager.getVisible_InGame_ProvinceAction()) {
                        ++TutorialManager.this.STEP_ID;
                        TutorialManager.this.updateDrawTutorial(TutorialManager.this.IN_TUTORIAL);
                     } else {
                        CFG.toast.setInView(CFG.langManager.get("chPr"), CFG.COLOR_TEXT_NUM_OF_PROVINCES);
                        CFG.map
                           .getMapCoordinates()
                           .centerToProvinceID(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCapitalProvinceID());
                     }

                     return true;
                  } else {
                     return false;
                  }
               }
            };
         } else if (this.STEP_ID == 9) {
            this.iDrawPosX = CFG.PADDING * 2 + AoCGame.LEFT;
            this.iDrawPosY = ImageManager.getImage(Images.top_left).getHeight() + CFG.PADDING * 2;
            this.textBox.clear();
            this.tutBoxes.clear();
            List<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
            List<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("q0"), CFG.COLOR_TEXT_NUM_OF_PROVINCES));
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
            List<String> tempText = this.getTextSplited(CFG.langManager.get("r0"));

            for(int i = 0; i < tempText.size(); ++i) {
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(tempText.get(i), COLOR_TITLE));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
            }

            tempText.clear();
            tempText = this.getTextSplited(CFG.langManager.get("YouCantRecruitArmyInOccupiedProvince"));

            for(int i = 0; i < tempText.size(); ++i) {
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(tempText.get(i), COLOR_TEXT));
               if (i == tempText.size() - 1) {
                  nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.patt2, CFG.PADDING, 0));
                  nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.patt2, 0, 0));
                  nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.patt2, 0, 0));
               }

               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
            }

            tempText.clear();
            nData.add(new MenuElement_Hover_v2_Element_Type_Space());
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
            nData.add(new MenuElement_Hover_v2_Element_Type_Text("1. ", CFG.COLOR_TEXT_NUM_OF_PROVINCES));
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("r1"), COLOR_TITLE));
            nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.diplo_army, CFG.PADDING, 0));
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
            this.textBox.add(new MenuElement_Hover_v2(nElements));
            List<MenuElement_Hover_v2_Element2> nElements2 = new ArrayList<>();
            List<MenuElement_Hover_v2_Element_Type> nData2 = new ArrayList<>();
            nData2.add(new MenuElement_Hover_v2_Element_Type_Text("1. ", CFG.COLOR_TEXT_NUM_OF_PROVINCES));
            nData2.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("r2"), COLOR_TITLE));
            nData2.add(new MenuElement_Hover_v2_Element_Type_Image(Images.diplo_army, CFG.PADDING, 0));
            nElements2.add(new MenuElement_Hover_v2_Element2(nData2));
            nData2.clear();
            nData2.add(new MenuElement_Hover_v2_Element_Type_Text("2. ", CFG.COLOR_TEXT_NUM_OF_PROVINCES));
            nData2.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Cancel"), COLOR_TITLE));
            nData2.add(new MenuElement_Hover_v2_Element_Type_Image(Images.icon_check_false, CFG.PADDING, 0));
            nElements2.add(new MenuElement_Hover_v2_Element2(nData2));
            nData2.clear();
            nData2.add(new MenuElement_Hover_v2_Element_Type_Text("3. ", CFG.COLOR_TEXT_NUM_OF_PROVINCES));
            nData2.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Accept"), COLOR_TITLE));
            nData2.add(new MenuElement_Hover_v2_Element_Type_Image(Images.icon_check_true, CFG.PADDING, 0));
            nElements2.add(new MenuElement_Hover_v2_Element2(nData2));
            nData2.clear();
            tempText.clear();
            tempText = this.getTextSplited(
               CFG.langManager.get("RequiredMovementPoints")
                  + ": "
                  + (float)CFG.ideologiesManager.getIdeology(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getIdeologyID()).COST_OF_RECRUIT
                     / 10.0F
            );
            nData2.add(new MenuElement_Hover_v2_Element_Type_Text("4. ", CFG.COLOR_TEXT_NUM_OF_PROVINCES));

            for(int i = 0; i < tempText.size(); ++i) {
               nData2.add(new MenuElement_Hover_v2_Element_Type_Text(tempText.get(i), COLOR_TITLE));
               if (i == tempText.size() - 1) {
                  nData2.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_movement_points, CFG.PADDING, 0));
               }

               nElements2.add(new MenuElement_Hover_v2_Element2(nData2));
               nData2.clear();
            }

            nData2.add(new MenuElement_Hover_v2_Element_Type_Space());
            nElements2.add(new MenuElement_Hover_v2_Element2(nData2));
            nData2.clear();
            tempText.clear();
            tempText = this.getTextSplited(CFG.langManager.get("ArmyRecruitmentWillTakeOneTurn"));
            nData2.add(new MenuElement_Hover_v2_Element_Type_Text(" - ", COLOR_TITLE));

            for(int i = 0; i < tempText.size(); ++i) {
               nData2.add(new MenuElement_Hover_v2_Element_Type_Text(tempText.get(i), COLOR_TEXT));
               if (i == tempText.size() - 1) {
                  nData2.add(new MenuElement_Hover_v2_Element_Type_Image(Images.time, CFG.PADDING, 0));
               }

               nElements2.add(new MenuElement_Hover_v2_Element2(nData2));
               nData2.clear();
            }

            tempText.clear();
            tempText = this.getTextSplited(CFG.langManager.get("OneUnitCostsXGold", 5));
            nData2.add(new MenuElement_Hover_v2_Element_Type_Text(" - ", COLOR_TITLE));

            for(int i = 0; i < tempText.size(); ++i) {
               nData2.add(new MenuElement_Hover_v2_Element_Type_Text(tempText.get(i), COLOR_TEXT));
               if (i == tempText.size() - 1) {
                  nData2.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_gold, CFG.PADDING, 0));
               }

               nElements2.add(new MenuElement_Hover_v2_Element2(nData2));
               nData2.clear();
            }

            this.textBox.add(new MenuElement_Hover_v2(nElements2));
            List<TutorialBox> tempBoxes = new ArrayList<>();
            tempBoxes.add(
               new TutorialBox(CFG.langManager.get("1")) {
                  @Override
                  protected int getPosX() {
                     return CFG.menuManager.getInGame_ProvinceAction().getPosX() + CFG.menuManager.getInGame_ProvinceAction().getMenuElement(1).getPosX();
                  }
   
                  @Override
                  protected int getPosY() {
                     return CFG.menuManager.getInGame_ProvinceAction().getPosY()
                        + CFG.menuManager.getInGame_ProvinceAction().getMenuElement(1).getPosY()
                        - CFG.PADDING / 2
                        - CFG.PADDING
                        - this.getHeight();
                  }
               }
            );
            this.tutBoxes.add(tempBoxes);
            List<TutorialBox> tempBoxes2 = new ArrayList<>();
            tempBoxes2.add(new TutorialBox("2. " + CFG.langManager.get("chPr")) {
               @Override
               protected int getPosX() {
                  return CFG.PADDING;
               }

               @Override
               protected int getPosY() {
                  return CFG.GAME_HEIGHT - CFG.map.getMapBG().getMinimapHeight() - CFG.PADDING - this.getHeight();
               }
            });
            tempBoxes2.add(
               new TutorialBox(CFG.langManager.get("2")) {
                  @Override
                  protected int getPosX() {
                     return (int)(
                           (float)(
                                 CFG.game.getProvince(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCapitalProvinceID()).getCenterX()
                                    + CFG.game
                                       .getProvince(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCapitalProvinceID())
                                       .getShiftX()
                                    + CFG.game
                                       .getProvince(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCapitalProvinceID())
                                       .getTranslateProvincePosX()
                              )
                              * CFG.map.getMapScale().getCurrentScale()
                        )
                        - this.getWidth() / 2;
                  }
   
                  @Override
                  protected int getPosY() {
                     return CFG.game
                           .getDrawProvinceArmy_EndPosY(
                              CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCapitalProvinceID(),
                              CFG.map.getMapScale().getCurrentScale()
                           )
                        + (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) / 2
                        + CFG.ARMY_BG_EXTRA_HEIGHT;
                  }
               }
            );
            this.tutBoxes.add(tempBoxes2);
            List<TutorialBox> tempBoxes3 = new ArrayList<>();
            tempBoxes3.add(
               new TutorialBox("2") {
                  @Override
                  protected int getPosX() {
                     return CFG.menuManager.getInGame_ProvinceRecruit().getPosX() + CFG.menuManager.getInGame_ProvinceRecruit().getMenuElement(0).getPosX();
                  }
   
                  @Override
                  protected int getPosY() {
                     return CFG.menuManager.getInGame_ProvinceRecruit().getPosY()
                        + CFG.menuManager.getInGame_ProvinceRecruit().getMenuElement(0).getPosY()
                        + CFG.menuManager.getInGame_ProvinceRecruit().getMenuElement(0).getHeight()
                        + CFG.PADDING / 2
                        + CFG.PADDING;
                  }
               }
            );
            tempBoxes3.add(
               new TutorialBox("3") {
                  @Override
                  protected int getPosX() {
                     return CFG.menuManager.getInGame_ProvinceRecruit().getPosX() + CFG.menuManager.getInGame_ProvinceRecruit().getMenuElement(1).getPosX();
                  }
   
                  @Override
                  protected int getPosY() {
                     return CFG.menuManager.getInGame_ProvinceRecruit().getPosY()
                        + CFG.menuManager.getInGame_ProvinceRecruit().getMenuElement(1).getPosY()
                        + CFG.menuManager.getInGame_ProvinceRecruit().getMenuElement(1).getHeight()
                        + CFG.PADDING / 2
                        + CFG.PADDING;
                  }
               }
            );
            tempBoxes3.add(
               new TutorialBox("1") {
                  @Override
                  protected int getPosX() {
                     return CFG.menuManager.getInGame_ProvinceRecruit().getPosX() + CFG.menuManager.getInGame_ProvinceRecruit().getMenuElement(2).getPosX();
                  }
   
                  @Override
                  protected int getPosY() {
                     return CFG.menuManager.getInGame_ProvinceRecruit().getPosY()
                        + CFG.menuManager.getInGame_ProvinceRecruit().getMenuElement(2).getPosY()
                        + CFG.menuManager.getInGame_ProvinceRecruit().getMenuElement(2).getHeight()
                        + CFG.PADDING / 2
                        + CFG.PADDING;
                  }
               }
            );
            tempBoxes3.add(
               new TutorialBox("4") {
                  @Override
                  protected int getPosX() {
                     return CFG.menuManager.getInGame_ActionInfo_Province().getPosX()
                        + CFG.menuManager.getInGame_ActionInfo_Province().getMenuElement(1).getPosX();
                  }
   
                  @Override
                  protected int getPosY() {
                     return CFG.menuManager.getInGame_ActionInfo_Province().getPosY()
                        + CFG.menuManager.getInGame_ActionInfo_Province().getMenuElement(1).getPosY()
                        - CFG.PADDING / 2
                        - CFG.PADDING
                        - this.getHeight();
                  }
               }
            );
            this.tutBoxes.add(tempBoxes3);
            this.tutStep = new TutorialManager.TutStep() {
               @Override
               public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY) {
                  if (CFG.menuManager.getInGameView()) {
                     try {
                        if (CFG.game.getActiveProvinceID() >= 0
                           && CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID() == CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()
                           && CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).isRecruitingArmyInProvinceID(CFG.game.getActiveProvinceID())
                              >= 0) {
                           ++TutorialManager.this.STEP_ID;
                           TutorialManager.this.updateDrawTutorial(TutorialManager.this.IN_TUTORIAL);
                           return;
                        }

                        if (CFG.menuManager.getInGame_ProvinceRecruit().getVisible()) {
                           TutorialManager.this.INNER_STEP = 2;
                           TutorialManager.this.textBox
                              .get(1)
                              .draw_HoverWithoutAnimation(
                                 oSB,
                                 !CFG.menuManager.getInGame_ProvinceBuild_Visible() && !CFG.menuManager.getInGame_CivInfo().getVisible()
                                    ? TutorialManager.this.iDrawPosX
                                    : CFG.CIV_INFO_MENU_WIDTH + CFG.PADDING * 2,
                                 TutorialManager.this.iDrawPosY
                              );
                           int tElemID = 0;
                           TutorialManager.this.drawLine(
                              oSB,
                              CFG.menuManager.getInGame_ProvinceRecruit().getPosX()
                                 + CFG.menuManager.getInGame_ProvinceRecruit().getMenuElement(tElemID).getPosX(),
                              CFG.PADDING / 2
                                 + CFG.menuManager.getInGame_ProvinceRecruit().getPosY()
                                 + CFG.menuManager.getInGame_ProvinceRecruit().getMenuElement(tElemID).getPosY()
                                 + CFG.menuManager.getInGame_ProvinceRecruit().getMenuElement(tElemID).getHeight(),
                              CFG.menuManager.getInGame_ProvinceRecruit().getMenuElement(tElemID).getWidth()
                           );
                           int var8 = 1;
                           TutorialManager.this.drawLine(
                              oSB,
                              CFG.menuManager.getInGame_ProvinceRecruit().getPosX()
                                 + CFG.menuManager.getInGame_ProvinceRecruit().getMenuElement(var8).getPosX(),
                              CFG.PADDING / 2
                                 + CFG.menuManager.getInGame_ProvinceRecruit().getPosY()
                                 + CFG.menuManager.getInGame_ProvinceRecruit().getMenuElement(var8).getPosY()
                                 + CFG.menuManager.getInGame_ProvinceRecruit().getMenuElement(var8).getHeight(),
                              CFG.menuManager.getInGame_ProvinceRecruit().getMenuElement(var8).getWidth()
                           );
                           var8 = 2;
                           TutorialManager.this.drawLine(
                              oSB,
                              CFG.menuManager.getInGame_ProvinceRecruit().getPosX()
                                 + CFG.menuManager.getInGame_ProvinceRecruit().getMenuElement(var8).getPosX(),
                              CFG.PADDING / 2
                                 + CFG.menuManager.getInGame_ProvinceRecruit().getPosY()
                                 + CFG.menuManager.getInGame_ProvinceRecruit().getMenuElement(var8).getPosY()
                                 + CFG.menuManager.getInGame_ProvinceRecruit().getMenuElement(var8).getHeight(),
                              CFG.menuManager.getInGame_ProvinceRecruit().getMenuElement(var8).getWidth()
                           );
                           var8 = 1;
                           TutorialManager.this.drawLine(
                              oSB,
                              CFG.menuManager.getInGame_ActionInfo_Province().getPosX()
                                 + CFG.menuManager.getInGame_ActionInfo_Province().getMenuElement(var8).getPosX(),
                              -CFG.PADDING / 2
                                 + CFG.menuManager.getInGame_ActionInfo_Province().getPosY()
                                 + CFG.menuManager.getInGame_ActionInfo_Province().getMenuElement(var8).getPosY(),
                              CFG.menuManager.getInGame_ActionInfo_Province().getMenuElement(var8).getWidth()
                           );
                        } else if (CFG.game.getActiveProvinceID() >= 0
                           && CFG.menuManager.getVisible_InGame_ProvinceAction()
                           && CFG.game.getProvince(CFG.game.getActiveProvinceID()).getTrueOwnerOfProvince()
                              == CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()) {
                           TutorialManager.this.INNER_STEP = 0;
                           TutorialManager.this.textBox
                              .get(0)
                              .draw_HoverWithoutAnimation(
                                 oSB,
                                 !CFG.menuManager.getInGame_ProvinceBuild_Visible() && !CFG.menuManager.getInGame_CivInfo().getVisible()
                                    ? TutorialManager.this.iDrawPosX
                                    : CFG.CIV_INFO_MENU_WIDTH + CFG.PADDING * 2,
                                 TutorialManager.this.iDrawPosY
                              );
                           int tElemID = 1;
                           TutorialManager.this.drawLine(
                              oSB,
                              CFG.menuManager.getInGame_ProvinceAction().getPosX()
                                 + CFG.menuManager.getInGame_ProvinceAction().getMenuElement(tElemID).getPosX(),
                              -CFG.PADDING / 2
                                 + CFG.menuManager.getInGame_ProvinceAction().getPosY()
                                 + CFG.menuManager.getInGame_ProvinceAction().getMenuElement(tElemID).getPosY(),
                              CFG.menuManager.getInGame_ProvinceAction().getMenuElement(tElemID).getWidth()
                           );
                        } else {
                           TutorialManager.this.INNER_STEP = 1;
                           TutorialManager.this.textBox
                              .get(0)
                              .draw_HoverWithoutAnimation(
                                 oSB,
                                 !CFG.menuManager.getInGame_ProvinceBuild_Visible() && !CFG.menuManager.getInGame_CivInfo().getVisible()
                                    ? TutorialManager.this.iDrawPosX
                                    : CFG.CIV_INFO_MENU_WIDTH + CFG.PADDING * 2,
                                 TutorialManager.this.iDrawPosY
                              );
                        }

                        for(int i = 0; i < TutorialManager.this.tutBoxes.get(TutorialManager.this.INNER_STEP).size(); ++i) {
                           TutorialManager.this.tutBoxes.get(TutorialManager.this.INNER_STEP).get(i).draw(oSB);
                        }
                     } catch (IndexOutOfBoundsException var5) {
                     } catch (NullPointerException var6) {
                     }
                  }
               }

               @Override
               public boolean action(Tutorial_ActionType actionType) {
                  if (actionType != Tutorial_ActionType.NEXT_TURN || CFG.gameAction.getActiveTurnState() != Game_Action.TurnStates.INPUT_ORDERS) {
                     return false;
                  } else if (CFG.game.getActiveProvinceID() >= 0 && CFG.menuManager.getVisible_InGame_ProvinceAction()) {
                     return false;
                  } else {
                     CFG.toast.setInView(CFG.langManager.get("chPr"), CFG.COLOR_TEXT_NUM_OF_PROVINCES);
                     CFG.map.getMapCoordinates().centerToProvinceID(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCapitalProvinceID());
                     return true;
                  }
               }
            };
         } else if (this.STEP_ID == 10) {
            this.iDrawPosX = CFG.PADDING * 2 + AoCGame.LEFT;
            this.iDrawPosY = ImageManager.getImage(Images.top_left).getHeight() + CFG.PADDING * 2;
            this.textBox.clear();
            this.tutBoxes.clear();
            List<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
            List<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("q0"), CFG.COLOR_TEXT_NUM_OF_PROVINCES));
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
            List<String> tempText = this.getTextSplited(CFG.langManager.get("n0"));

            for(int i = 0; i < tempText.size(); ++i) {
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(tempText.get(i), COLOR_TITLE));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
            }

            tempText.clear();
            tempText = this.getTextSplited(CFG.langManager.get("n0a"));

            for(int i = 0; i < tempText.size(); ++i) {
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(tempText.get(i), COLOR_TEXT));
               if (i == tempText.size() - 1) {
                  nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.b_port, CFG.PADDING, 0));
               }

               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
            }

            tempText.clear();
            nData.add(new MenuElement_Hover_v2_Element_Type_Space());
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
            nData.add(new MenuElement_Hover_v2_Element_Type_Text("1. ", CFG.COLOR_TEXT_NUM_OF_PROVINCES));
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("n1"), COLOR_TITLE));
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
            this.textBox.add(new MenuElement_Hover_v2(nElements));
            List<MenuElement_Hover_v2_Element2> nElements2 = new ArrayList<>();
            List<MenuElement_Hover_v2_Element_Type> nData2 = new ArrayList<>();
            nData2.add(new MenuElement_Hover_v2_Element_Type_Text("1. ", CFG.COLOR_TEXT_NUM_OF_PROVINCES));
            nData2.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("n2"), COLOR_TITLE));
            nData2.add(new MenuElement_Hover_v2_Element_Type_Image(Images.diplo_army, CFG.PADDING, 0));
            nElements2.add(new MenuElement_Hover_v2_Element2(nData2));
            nData2.clear();
            nData2.add(new MenuElement_Hover_v2_Element_Type_Text("2. ", CFG.COLOR_TEXT_NUM_OF_PROVINCES));
            nData2.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Cancel"), COLOR_TITLE));
            nData2.add(new MenuElement_Hover_v2_Element_Type_Image(Images.icon_check_false, CFG.PADDING, 0));
            nElements2.add(new MenuElement_Hover_v2_Element2(nData2));
            nData2.clear();
            nData2.add(new MenuElement_Hover_v2_Element_Type_Text("3. ", CFG.COLOR_TEXT_NUM_OF_PROVINCES));
            nData2.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Accept"), COLOR_TITLE));
            nData2.add(new MenuElement_Hover_v2_Element_Type_Image(Images.icon_check_true, CFG.PADDING, 0));
            nElements2.add(new MenuElement_Hover_v2_Element2(nData2));
            nData2.clear();
            tempText.clear();
            tempText = this.getTextSplited(CFG.langManager.get("n3a"));
            nData2.add(new MenuElement_Hover_v2_Element_Type_Text(" - ", COLOR_TITLE));

            for(int i = 0; i < tempText.size(); ++i) {
               nData2.add(new MenuElement_Hover_v2_Element_Type_Text(tempText.get(i), COLOR_TEXT));
               nElements2.add(new MenuElement_Hover_v2_Element2(nData2));
               nData2.clear();
            }

            tempText.clear();
            tempText = this.getTextSplited(CFG.langManager.get("RequiredMovementPoints"));
            nData2.add(new MenuElement_Hover_v2_Element_Type_Text("4. ", CFG.COLOR_TEXT_NUM_OF_PROVINCES));

            for(int i = 0; i < tempText.size(); ++i) {
               nData2.add(new MenuElement_Hover_v2_Element_Type_Text(tempText.get(i), COLOR_TITLE));
               if (i == tempText.size() - 1) {
                  nData2.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_movement_points, CFG.PADDING, 0));
               }

               nElements2.add(new MenuElement_Hover_v2_Element2(nData2));
               nData2.clear();
            }

            this.textBox.add(new MenuElement_Hover_v2(nElements2));
            List<MenuElement_Hover_v2_Element2> nElements3 = new ArrayList<>();
            List<MenuElement_Hover_v2_Element_Type> nData3 = new ArrayList<>();
            nData3.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("n3"), COLOR_TITLE));
            nData3.add(new MenuElement_Hover_v2_Element_Type_Image(Images.diplo_army, CFG.PADDING, 0));
            nElements3.add(new MenuElement_Hover_v2_Element2(nData3));
            nData3.clear();
            this.textBox.add(new MenuElement_Hover_v2(nElements3));
            List<TutorialBox> tempBoxes = new ArrayList<>();
            tempBoxes.add(
               new TutorialBox(CFG.langManager.get("1")) {
                  @Override
                  protected int getPosX() {
                     return CFG.menuManager.getInGame_ProvinceAction().getPosX() + CFG.menuManager.getInGame_ProvinceAction().getMenuElement(0).getPosX();
                  }
   
                  @Override
                  protected int getPosY() {
                     return CFG.menuManager.getInGame_ProvinceAction().getPosY()
                        + CFG.menuManager.getInGame_ProvinceAction().getMenuElement(0).getPosY()
                        - CFG.PADDING / 2
                        - CFG.PADDING
                        - this.getHeight();
                  }
               }
            );
            tempBoxes.add(
               new TutorialBox(CFG.langManager.get("1")) {
                  @Override
                  protected int getPosX() {
                     return (int)(
                           (float)(
                                 CFG.game.getProvince(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCapitalProvinceID()).getCenterX()
                                    + CFG.game
                                       .getProvince(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCapitalProvinceID())
                                       .getShiftX()
                                    + CFG.game
                                       .getProvince(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCapitalProvinceID())
                                       .getTranslateProvincePosX()
                              )
                              * CFG.map.getMapScale().getCurrentScale()
                        )
                        - this.getWidth() / 2;
                  }
   
                  @Override
                  protected int getPosY() {
                     return CFG.game
                           .getDrawProvinceArmy_EndPosY(
                              CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCapitalProvinceID(),
                              CFG.map.getMapScale().getCurrentScale()
                           )
                        + (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) / 2
                        + CFG.ARMY_BG_EXTRA_HEIGHT;
                  }
               }
            );
            this.tutBoxes.add(tempBoxes);
            List<TutorialBox> tempBoxes2 = new ArrayList<>();
            tempBoxes2.add(new TutorialBox("2. " + CFG.langManager.get("chPr")) {
               @Override
               protected int getPosX() {
                  return CFG.PADDING;
               }

               @Override
               protected int getPosY() {
                  return CFG.GAME_HEIGHT - CFG.map.getMapBG().getMinimapHeight() - CFG.PADDING - this.getHeight();
               }
            });
            tempBoxes2.add(
               new TutorialBox(CFG.langManager.get("2")) {
                  @Override
                  protected int getPosX() {
                     return (int)(
                           (float)(
                                 CFG.game.getProvince(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCapitalProvinceID()).getCenterX()
                                    + CFG.game
                                       .getProvince(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCapitalProvinceID())
                                       .getShiftX()
                                    + CFG.game
                                       .getProvince(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCapitalProvinceID())
                                       .getTranslateProvincePosX()
                              )
                              * CFG.map.getMapScale().getCurrentScale()
                        )
                        - this.getWidth() / 2;
                  }
   
                  @Override
                  protected int getPosY() {
                     return CFG.game
                           .getDrawProvinceArmy_EndPosY(
                              CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCapitalProvinceID(),
                              CFG.map.getMapScale().getCurrentScale()
                           )
                        + (CFG.ARMY_HEIGHT + CFG.ARMY_BG_EXTRA_HEIGHT * 2) / 2
                        + CFG.ARMY_BG_EXTRA_HEIGHT;
                  }
               }
            );
            this.tutBoxes.add(tempBoxes2);
            List<TutorialBox> tempBoxes3 = new ArrayList<>();
            tempBoxes3.add(
               new TutorialBox("2") {
                  @Override
                  protected int getPosX() {
                     return CFG.menuManager.getInGame_ProvinceMoveUnits().getPosX()
                        + CFG.menuManager.getInGame_ProvinceMoveUnits().getMenuElement(0).getPosX();
                  }
   
                  @Override
                  protected int getPosY() {
                     return CFG.menuManager.getInGame_ProvinceMoveUnits().getPosY()
                        + CFG.menuManager.getInGame_ProvinceMoveUnits().getMenuElement(0).getPosY()
                        + CFG.menuManager.getInGame_ProvinceMoveUnits().getMenuElement(0).getHeight()
                        + CFG.PADDING / 2
                        + CFG.PADDING;
                  }
               }
            );
            tempBoxes3.add(
               new TutorialBox("3") {
                  @Override
                  protected int getPosX() {
                     return CFG.menuManager.getInGame_ProvinceMoveUnits().getPosX()
                        + CFG.menuManager.getInGame_ProvinceMoveUnits().getMenuElement(1).getPosX();
                  }
   
                  @Override
                  protected int getPosY() {
                     return CFG.menuManager.getInGame_ProvinceMoveUnits().getPosY()
                        + CFG.menuManager.getInGame_ProvinceMoveUnits().getMenuElement(1).getPosY()
                        + CFG.menuManager.getInGame_ProvinceMoveUnits().getMenuElement(1).getHeight()
                        + CFG.PADDING / 2
                        + CFG.PADDING;
                  }
               }
            );
            tempBoxes3.add(
               new TutorialBox("1") {
                  @Override
                  protected int getPosX() {
                     return CFG.menuManager.getInGame_ProvinceMoveUnits().getPosX()
                        + CFG.menuManager.getInGame_ProvinceMoveUnits().getMenuElement(2).getPosX();
                  }
   
                  @Override
                  protected int getPosY() {
                     return CFG.menuManager.getInGame_ProvinceMoveUnits().getPosY()
                        + CFG.menuManager.getInGame_ProvinceMoveUnits().getMenuElement(2).getPosY()
                        + CFG.menuManager.getInGame_ProvinceMoveUnits().getMenuElement(2).getHeight()
                        + CFG.PADDING / 2
                        + CFG.PADDING;
                  }
               }
            );
            tempBoxes3.add(
               new TutorialBox("4") {
                  @Override
                  protected int getPosX() {
                     return CFG.menuManager.getInGame_ActionInfo_Province().getPosX()
                        + CFG.menuManager.getInGame_ActionInfo_Province().getMenuElement(1).getPosX();
                  }
   
                  @Override
                  protected int getPosY() {
                     return CFG.menuManager.getInGame_ActionInfo_Province().getPosY()
                        + CFG.menuManager.getInGame_ActionInfo_Province().getMenuElement(1).getPosY()
                        - CFG.PADDING / 2
                        - CFG.PADDING
                        - this.getHeight();
                  }
               }
            );
            this.tutBoxes.add(tempBoxes3);
            this.tutStep = new TutorialManager.TutStep() {
               @Override
               public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY) {
                  if (CFG.menuManager.getInGameView()) {
                     try {
                        if (CFG.game.getActiveProvinceID() >= 0
                           && CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).isMovingUnitsFromProvinceID(CFG.game.getActiveProvinceID())) {
                           ++TutorialManager.this.STEP_ID;
                           TutorialManager.this.updateDrawTutorial(TutorialManager.this.IN_TUTORIAL);
                           return;
                        }

                        if (CFG.menuManager.getInGame_ProvinceMoveUnits().getVisible()) {
                           TutorialManager.this.INNER_STEP = 2;
                           TutorialManager.this.textBox
                              .get(1)
                              .draw_HoverWithoutAnimation(
                                 oSB,
                                 !CFG.menuManager.getInGame_ProvinceBuild_Visible() && !CFG.menuManager.getInGame_CivInfo().getVisible()
                                    ? TutorialManager.this.iDrawPosX
                                    : CFG.CIV_INFO_MENU_WIDTH + CFG.PADDING * 2,
                                 TutorialManager.this.iDrawPosY
                              );
                           int tElemID = 0;
                           TutorialManager.this.drawLine(
                              oSB,
                              CFG.menuManager.getInGame_ProvinceMoveUnits().getPosX()
                                 + CFG.menuManager.getInGame_ProvinceMoveUnits().getMenuElement(tElemID).getPosX(),
                              CFG.PADDING / 2
                                 + CFG.menuManager.getInGame_ProvinceMoveUnits().getPosY()
                                 + CFG.menuManager.getInGame_ProvinceMoveUnits().getMenuElement(tElemID).getPosY()
                                 + CFG.menuManager.getInGame_ProvinceMoveUnits().getMenuElement(tElemID).getHeight(),
                              CFG.menuManager.getInGame_ProvinceMoveUnits().getMenuElement(tElemID).getWidth()
                           );
                           int var8 = 1;
                           TutorialManager.this.drawLine(
                              oSB,
                              CFG.menuManager.getInGame_ProvinceMoveUnits().getPosX()
                                 + CFG.menuManager.getInGame_ProvinceMoveUnits().getMenuElement(var8).getPosX(),
                              CFG.PADDING / 2
                                 + CFG.menuManager.getInGame_ProvinceMoveUnits().getPosY()
                                 + CFG.menuManager.getInGame_ProvinceMoveUnits().getMenuElement(var8).getPosY()
                                 + CFG.menuManager.getInGame_ProvinceMoveUnits().getMenuElement(var8).getHeight(),
                              CFG.menuManager.getInGame_ProvinceMoveUnits().getMenuElement(var8).getWidth()
                           );
                           var8 = 2;
                           TutorialManager.this.drawLine(
                              oSB,
                              CFG.menuManager.getInGame_ProvinceMoveUnits().getPosX()
                                 + CFG.menuManager.getInGame_ProvinceMoveUnits().getMenuElement(var8).getPosX(),
                              CFG.PADDING / 2
                                 + CFG.menuManager.getInGame_ProvinceMoveUnits().getPosY()
                                 + CFG.menuManager.getInGame_ProvinceMoveUnits().getMenuElement(var8).getPosY()
                                 + CFG.menuManager.getInGame_ProvinceMoveUnits().getMenuElement(var8).getHeight(),
                              CFG.menuManager.getInGame_ProvinceMoveUnits().getMenuElement(var8).getWidth()
                           );
                           var8 = 1;
                           TutorialManager.this.drawLine(
                              oSB,
                              CFG.menuManager.getInGame_ActionInfo_Province().getPosX()
                                 + CFG.menuManager.getInGame_ActionInfo_Province().getMenuElement(var8).getPosX(),
                              -CFG.PADDING / 2
                                 + CFG.menuManager.getInGame_ActionInfo_Province().getPosY()
                                 + CFG.menuManager.getInGame_ActionInfo_Province().getMenuElement(var8).getPosY(),
                              CFG.menuManager.getInGame_ActionInfo_Province().getMenuElement(var8).getWidth()
                           );
                        } else if ((
                              CFG.game.getActiveProvinceID() < 0
                                 || CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID() != CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()
                           )
                           && !CFG.chooseProvinceMode) {
                           TutorialManager.this.INNER_STEP = 1;
                           TutorialManager.this.textBox
                              .get(0)
                              .draw_HoverWithoutAnimation(
                                 oSB,
                                 !CFG.menuManager.getInGame_ProvinceBuild_Visible() && !CFG.menuManager.getInGame_CivInfo().getVisible()
                                    ? TutorialManager.this.iDrawPosX
                                    : CFG.CIV_INFO_MENU_WIDTH + CFG.PADDING * 2,
                                 TutorialManager.this.iDrawPosY
                              );
                        } else {
                           if (CFG.chooseProvinceMode && CFG.chosenProvinceID < 0) {
                              TutorialManager.this.INNER_STEP = 3;
                              TutorialManager.this.textBox
                                 .get(2)
                                 .draw_HoverWithoutAnimation(
                                    oSB,
                                    !CFG.menuManager.getInGame_ProvinceBuild_Visible() && !CFG.menuManager.getInGame_CivInfo().getVisible()
                                       ? TutorialManager.this.iDrawPosX
                                       : CFG.CIV_INFO_MENU_WIDTH + CFG.PADDING * 2,
                                    TutorialManager.this.iDrawPosY
                                 );
                              return;
                           }

                           TutorialManager.this.INNER_STEP = 0;
                           TutorialManager.this.textBox
                              .get(0)
                              .draw_HoverWithoutAnimation(
                                 oSB,
                                 !CFG.menuManager.getInGame_ProvinceBuild_Visible() && !CFG.menuManager.getInGame_CivInfo().getVisible()
                                    ? TutorialManager.this.iDrawPosX
                                    : CFG.CIV_INFO_MENU_WIDTH + CFG.PADDING * 2,
                                 TutorialManager.this.iDrawPosY
                              );
                           int tElemID = 0;
                           TutorialManager.this.drawLine(
                              oSB,
                              CFG.menuManager.getInGame_ProvinceAction().getPosX()
                                 + CFG.menuManager.getInGame_ProvinceAction().getMenuElement(tElemID).getPosX(),
                              -CFG.PADDING / 2
                                 + CFG.menuManager.getInGame_ProvinceAction().getPosY()
                                 + CFG.menuManager.getInGame_ProvinceAction().getMenuElement(tElemID).getPosY(),
                              CFG.menuManager.getInGame_ProvinceAction().getMenuElement(tElemID).getWidth()
                           );
                        }

                        for(int i = 0; i < TutorialManager.this.tutBoxes.get(TutorialManager.this.INNER_STEP).size(); ++i) {
                           TutorialManager.this.tutBoxes.get(TutorialManager.this.INNER_STEP).get(i).draw(oSB);
                        }
                     } catch (IndexOutOfBoundsException var5) {
                     } catch (NullPointerException var6) {
                     }
                  }
               }

               @Override
               public boolean action(Tutorial_ActionType actionType) {
                  if (actionType != Tutorial_ActionType.NEXT_TURN || CFG.gameAction.getActiveTurnState() != Game_Action.TurnStates.INPUT_ORDERS) {
                     return false;
                  } else if (CFG.game.getActiveProvinceID() >= 0 && CFG.menuManager.getVisible_InGame_ProvinceAction()) {
                     return false;
                  } else {
                     CFG.toast.setInView(CFG.langManager.get("chPr"), CFG.COLOR_TEXT_NUM_OF_PROVINCES);
                     CFG.map.getMapCoordinates().centerToProvinceID(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCapitalProvinceID());
                     return true;
                  }
               }
            };
         } else if (this.STEP_ID == 11) {
            this.iDrawPosX = CFG.PADDING * 2 + AoCGame.LEFT;
            this.iDrawPosY = ImageManager.getImage(Images.top_left).getHeight() + CFG.PADDING * 2;
            this.textBox.clear();
            this.tutBoxes.clear();
            List<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
            List<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
            nData.add(new MenuElement_Hover_v2_Element_Type_Text("1. ", CFG.COLOR_TEXT_NUM_OF_PROVINCES));
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("a3a2"), COLOR_TITLE));
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
            nData.add(new MenuElement_Hover_v2_Element_Type_Space());
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
            nData.add(new MenuElement_Hover_v2_Element_Type_Text("1. ", CFG.COLOR_TEXT_NUM_OF_PROVINCES));
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get(BuildingsManager.getFort_Name(1)), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
            nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.b_fort, CFG.PADDING, 0));
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(" - ", COLOR_TITLE));
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("HidesTheArmyFromTheSightOfViewOfWatchTower"), COLOR_TEXT));
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(" - ", COLOR_TITLE));
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("DefenseBonus") + ": ", COLOR_TEXT));
            nData.add(new MenuElement_Hover_v2_Element_Type_Text("+" + BuildingsManager.getFort_DefenseBonus(1) + "%", CFG.COLOR_TEXT_MODIFIER_POSITIVE));
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
            nData.add(new MenuElement_Hover_v2_Element_Type_Text("2. ", CFG.COLOR_TEXT_NUM_OF_PROVINCES));
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get(BuildingsManager.getTower_Name(1)), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
            nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.b_tower, CFG.PADDING, 0));
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(" - ", COLOR_TITLE));
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("AllowsToSeeTheArmyInNeighboringProvinces"), COLOR_TEXT));
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(" - ", COLOR_TITLE));
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("DefenseBonus") + ": ", COLOR_TEXT));
            nData.add(new MenuElement_Hover_v2_Element_Type_Text("+" + BuildingsManager.getTower_DefenseBonus(1) + "%", CFG.COLOR_TEXT_MODIFIER_POSITIVE));
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
            nData.add(new MenuElement_Hover_v2_Element_Type_Text("3. ", CFG.COLOR_TEXT_NUM_OF_PROVINCES));
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get(BuildingsManager.getPort_Name(1)), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
            nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.b_port, CFG.PADDING, 0));
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(" - ", COLOR_TITLE));
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("AllowsYourArmyGoToTheSea"), COLOR_TEXT));
            nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.icon_move_sea, CFG.PADDING, 0));
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(" - ", COLOR_TITLE));
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("IncomeProduction") + ": ", COLOR_TEXT));
            nData.add(
               new MenuElement_Hover_v2_Element_Type_Text(
                  "+" + (int)(BuildingsManager.getPort_IncomeProduction(1) * 100.0F) + "%", CFG.COLOR_TEXT_MODIFIER_POSITIVE
               )
            );
            nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.economy, CFG.PADDING, 0));
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
            nData.add(new MenuElement_Hover_v2_Element_Type_Text("4. ", CFG.COLOR_TEXT_NUM_OF_PROVINCES));
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get(BuildingsManager.getLibrary_Name(1)), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
            nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.b_library, CFG.PADDING, 0));
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(" - ", COLOR_TITLE));
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("+1"), CFG.COLOR_TEXT_RESEARCH));
            nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.research, CFG.PADDING, CFG.PADDING));
            nData.add(
               new MenuElement_Hover_v2_Element_Type_Text(
                  CFG.langManager.get("ResearchPerTurnForEveryXPeopleInProvince", BuildingsManager.getLibrary_ResearchPerPopulation(1)), COLOR_TEXT
               )
            );
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
            nData.add(new MenuElement_Hover_v2_Element_Type_Text("5. ", CFG.COLOR_TEXT_NUM_OF_PROVINCES));
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get(BuildingsManager.getFarm_Name(1)), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
            nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.b_farm, CFG.PADDING, 0));
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(" - ", COLOR_TITLE));
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("GrowthRate") + ": ", COLOR_TEXT));
            nData.add(
               new MenuElement_Hover_v2_Element_Type_Text(
                  "+" + (int)(BuildingsManager.getFarm_GrowthRateBonus(1) * 100.0F) + "%", CFG.COLOR_TEXT_MODIFIER_POSITIVE
               )
            );
            nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.population_growth, CFG.PADDING, 0));
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
            nData.add(new MenuElement_Hover_v2_Element_Type_Text("6. ", CFG.COLOR_TEXT_NUM_OF_PROVINCES));
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get(BuildingsManager.getWorkshop_Name(1)), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
            nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.b_workshop, CFG.PADDING, 0));
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(" - ", COLOR_TITLE));
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("IncomeProduction") + ": ", COLOR_TEXT));
            nData.add(
               new MenuElement_Hover_v2_Element_Type_Text(
                  "+" + (int)(BuildingsManager.getWorkshop_IncomeProduction(1) * 100.0F) + "%", CFG.COLOR_TEXT_MODIFIER_POSITIVE
               )
            );
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
            nData.add(new MenuElement_Hover_v2_Element_Type_Text("7. ", CFG.COLOR_TEXT_NUM_OF_PROVINCES));
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get(BuildingsManager.getArmoury_Name(1)), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
            nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.b_armoury, CFG.PADDING, 0));
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(" - ", COLOR_TITLE));
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("ReducesTheCostOfRecruitmentPerUnitByOneGold"), COLOR_TEXT));
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
            nData.add(new MenuElement_Hover_v2_Element_Type_Text("8. ", CFG.COLOR_TEXT_NUM_OF_PROVINCES));
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get(BuildingsManager.getSupply_Name(1)), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
            nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.b_supply, CFG.PADDING, 0));
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(" - ", COLOR_TITLE));
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("MilitaryUpkeep") + ": ", COLOR_TEXT));
            nData.add(
               new MenuElement_Hover_v2_Element_Type_Text("-" + (int)(BuildingsManager.getSupply_Bonus(1) * 100.0F) + "%", CFG.COLOR_TEXT_MODIFIER_POSITIVE)
            );
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
            this.textBox.add(new MenuElement_Hover_v2(nElements));
            List<TutorialBox> tempBoxes = new ArrayList<>();
            tempBoxes.add(
               new TutorialBox(CFG.langManager.get("Next")) {
                  @Override
                  protected int getPosX() {
                     return CFG.menuManager.getInGame_ProvinceInfo().getPosX() + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(0).getPosX();
                  }
   
                  @Override
                  protected int getPosY() {
                     return CFG.menuManager.getInGame_ProvinceInfo().getPosY()
                        + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(0).getPosY()
                        - CFG.PADDING / 2
                        - CFG.PADDING
                        - this.getHeight();
                  }
               }
            );
            tempBoxes.add(
               new TutorialBox(CFG.langManager.get("1")) {
                  @Override
                  protected int getPosX() {
                     return CFG.menuManager.getInGame_ProvinceAction().getPosX() + CFG.menuManager.getInGame_ProvinceAction().getMenuElement(2).getPosX();
                  }
   
                  @Override
                  protected int getPosY() {
                     return CFG.menuManager.getInGame_ProvinceAction().getPosY()
                        + CFG.menuManager.getInGame_ProvinceAction().getMenuElement(2).getPosY()
                        - CFG.PADDING / 2
                        - CFG.PADDING
                        - this.getHeight();
                  }
               }
            );
            this.tutBoxes.add(tempBoxes);
            this.tutStep = new TutorialManager.TutStep() {
               @Override
               public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY) {
                  if (CFG.menuManager.getInGameView()) {
                     try {
                        TutorialManager.this.textBox
                           .get(0)
                           .draw_HoverWithoutAnimation(
                              oSB,
                              !CFG.menuManager.getInGame_ProvinceBuild_Visible() && !CFG.menuManager.getInGame_CivInfo().getVisible()
                                 ? TutorialManager.this.iDrawPosX
                                 : CFG.CIV_INFO_MENU_WIDTH + CFG.PADDING * 2,
                              TutorialManager.this.iDrawPosY
                           );
                        if (CFG.game.getActiveProvinceID() < 0
                           || CFG.game.getActiveProvinceID() != CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCapitalProvinceID()
                           || CFG.game.getProvince(CFG.game.getActiveProvinceID()).getSeaProvince()
                           || CFG.game.getProvince(CFG.game.getActiveProvinceID()).getWasteland() >= 0) {
                           CFG.game.setActiveProvinceID(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCapitalProvinceID());
                        }

                        int tElemID = 2;
                        TutorialManager.this.drawLine(
                           oSB,
                           CFG.menuManager.getInGame_ProvinceAction().getPosX() + CFG.menuManager.getInGame_ProvinceAction().getMenuElement(tElemID).getPosX(),
                           -CFG.PADDING / 2
                              + CFG.menuManager.getInGame_ProvinceAction().getPosY()
                              + CFG.menuManager.getInGame_ProvinceAction().getMenuElement(tElemID).getPosY(),
                           CFG.menuManager.getInGame_ProvinceAction().getMenuElement(tElemID).getWidth()
                        );

                        for(int i = 0; i < TutorialManager.this.tutBoxes.get(TutorialManager.this.INNER_STEP).size(); ++i) {
                           TutorialManager.this.tutBoxes.get(TutorialManager.this.INNER_STEP).get(i).draw(oSB);
                        }
                     } catch (IndexOutOfBoundsException var6) {
                     } catch (NullPointerException var7) {
                     }
                  }
               }

               @Override
               public boolean action(Tutorial_ActionType actionType) {
                  if (actionType == Tutorial_ActionType.NEXT_TURN && CFG.gameAction.getActiveTurnState() == Game_Action.TurnStates.INPUT_ORDERS) {
                     ++TutorialManager.this.STEP_ID;
                     TutorialManager.this.updateDrawTutorial(TutorialManager.this.IN_TUTORIAL);
                     CFG.game.setActiveProvinceID(-1);
                     CFG.menuManager.getInGame_Budget().setVisible(false);
                     return true;
                  } else {
                     return false;
                  }
               }
            };
         } else if (this.STEP_ID == 12) {
            this.iDrawPosX = CFG.PADDING * 2 + AoCGame.LEFT;
            this.iDrawPosY = CFG.menuManager.getInGame().getPosY()
               + CFG.menuManager.getInGame().getMenuElement(7).getPosY()
               + CFG.menuManager.getInGame().getMenuElement(7).getHeight()
               + CFG.PADDING / 2
               + CFG.PADDING
               + CFG.TEXT_HEIGHT
               + CFG.PADDING * 2
               + CFG.PADDING;
            this.textBox.clear();
            this.tutBoxes.clear();
            List<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
            List<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("q0"), CFG.COLOR_TEXT_NUM_OF_PROVINCES));
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
            nData.add(new MenuElement_Hover_v2_Element_Type_Text("1. ", CFG.COLOR_TEXT_NUM_OF_PROVINCES));
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("OpenTheViewOfYourCivilizationsBudget"), COLOR_TITLE));
            nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_gold, CFG.PADDING, 0));
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
            List<String> tempText = this.getTextSplited(CFG.langManager.get("tx0"));
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(" - ", COLOR_TITLE));

            for(int i = 0; i < tempText.size(); ++i) {
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(tempText.get(i), COLOR_TEXT));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
            }

            nData.add(new MenuElement_Hover_v2_Element_Type_Space());
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Inflation"), CFG.COLOR_TEXT_NUM_OF_PROVINCES));
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
            tempText.clear();
            tempText = this.getTextSplited(CFG.langManager.get("InflationH1"));
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(" - ", COLOR_TITLE));

            for(int i = 0; i < tempText.size(); ++i) {
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(tempText.get(i), COLOR_TEXT));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
            }

            tempText.clear();
            tempText = this.getTextSplited(CFG.langManager.get("InflationH2"));
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(" - ", COLOR_TITLE));

            for(int i = 0; i < tempText.size(); ++i) {
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(tempText.get(i), COLOR_TEXT));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
            }

            this.textBox.add(new MenuElement_Hover_v2(nElements));
            List<TutorialBox> tempBoxes = new ArrayList<>();
            tempBoxes.add(
               new TutorialBox(CFG.langManager.get("1")) {
                  @Override
                  protected int getPosX() {
                     return CFG.menuManager.getInGame().getPosX() + CFG.menuManager.getInGame().getMenuElement(1).getPosX();
                  }
   
                  @Override
                  protected int getPosY() {
                     return CFG.menuManager.getInGame().getPosY()
                        + CFG.menuManager.getInGame().getMenuElement(1).getPosY()
                        + CFG.menuManager.getInGame().getMenuElement(1).getHeight()
                        + CFG.PADDING / 2
                        + CFG.PADDING;
                  }
               }
            );
            this.tutBoxes.add(tempBoxes);
            this.tutStep = new TutorialManager.TutStep() {
               @Override
               public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY) {
                  if (CFG.menuManager.getInGameView()) {
                     try {
                        TutorialManager.this.textBox
                           .get(0)
                           .draw_HoverWithoutAnimation(
                              oSB,
                              !CFG.menuManager.getInGame_ProvinceBuild_Visible() && !CFG.menuManager.getInGame_CivInfo().getVisible()
                                 ? TutorialManager.this.iDrawPosX
                                 : CFG.CIV_INFO_MENU_WIDTH + CFG.PADDING * 2,
                              TutorialManager.this.iDrawPosY
                           );
                        TutorialManager.this.drawLine(
                           oSB,
                           CFG.menuManager.getInGame().getPosX() + CFG.menuManager.getInGame().getMenuElement(1).getPosX(),
                           CFG.PADDING / 2
                              + CFG.menuManager.getInGame().getPosY()
                              + CFG.menuManager.getInGame().getMenuElement(1).getPosY()
                              + CFG.menuManager.getInGame().getMenuElement(1).getHeight(),
                           CFG.menuManager.getInGame().getMenuElement(1).getWidth()
                        );
                        int tElemID = 0;

                        for(int i = 0; i < TutorialManager.this.tutBoxes.get(TutorialManager.this.INNER_STEP).size(); ++i) {
                           TutorialManager.this.tutBoxes.get(TutorialManager.this.INNER_STEP).get(i).draw(oSB);
                        }

                        if (CFG.menuManager.getInGame_Budget().getVisible()) {
                           ++TutorialManager.this.STEP_ID;
                           TutorialManager.this.updateDrawTutorial(TutorialManager.this.IN_TUTORIAL);
                        }
                     } catch (IndexOutOfBoundsException var6) {
                     } catch (NullPointerException var7) {
                     }
                  }
               }

               @Override
               public boolean action(Tutorial_ActionType actionType) {
                  if (actionType == Tutorial_ActionType.NEXT_TURN && CFG.gameAction.getActiveTurnState() == Game_Action.TurnStates.INPUT_ORDERS) {
                     CFG.toast.setInView(CFG.langManager.get("OpenTheViewOfYourCivilizationsBudget"), CFG.COLOR_TEXT_NUM_OF_PROVINCES);
                     return true;
                  } else {
                     return false;
                  }
               }
            };
         } else if (this.STEP_ID == 13) {
            this.iDrawPosX = CFG.PADDING * 2 + AoCGame.LEFT;
            this.iDrawPosY = CFG.GAME_HEIGHT;
            this.textBox.clear();
            this.tutBoxes.clear();
            List<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
            List<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("q0"), CFG.COLOR_TEXT_NUM_OF_PROVINCES));
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
            nData.add(new MenuElement_Hover_v2_Element_Type_Text("1. ", CFG.COLOR_TEXT_NUM_OF_PROVINCES));
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("OpenTheViewOfYourCivilizationsBudget"), COLOR_TITLE));
            nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_gold, CFG.PADDING, 0));
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
            this.textBox.add(new MenuElement_Hover_v2(nElements));
            List<MenuElement_Hover_v2_Element2> nElements2 = new ArrayList<>();
            List<MenuElement_Hover_v2_Element_Type> nData2 = new ArrayList<>();
            List<String> tempText = this.getTextSplited(CFG.langManager.get("in1"));
            nData2.add(new MenuElement_Hover_v2_Element_Type_Text("1. ", CFG.COLOR_TEXT_NUM_OF_PROVINCES));

            for(int i = 0; i < tempText.size(); ++i) {
               nData2.add(new MenuElement_Hover_v2_Element_Type_Text(tempText.get(i), COLOR_TITLE));
               nElements2.add(new MenuElement_Hover_v2_Element2(nData2));
               nData2.clear();
            }

            tempText.clear();
            tempText = this.getTextSplited(CFG.langManager.get("in2"));
            nData2.add(new MenuElement_Hover_v2_Element_Type_Text("2. ", CFG.COLOR_TEXT_NUM_OF_PROVINCES));

            for(int i = 0; i < tempText.size(); ++i) {
               nData2.add(new MenuElement_Hover_v2_Element_Type_Text(tempText.get(i), COLOR_TITLE));
               nElements2.add(new MenuElement_Hover_v2_Element2(nData2));
               nData2.clear();
            }

            tempText.clear();
            tempText = this.getTextSplited(CFG.langManager.get("hAdm0"));
            nData2.add(new MenuElement_Hover_v2_Element_Type_Text("3. ", CFG.COLOR_TEXT_NUM_OF_PROVINCES));

            for(int i = 0; i < tempText.size(); ++i) {
               nData2.add(new MenuElement_Hover_v2_Element_Type_Text(tempText.get(i), COLOR_TITLE));
               nElements2.add(new MenuElement_Hover_v2_Element2(nData2));
               nData2.clear();
            }

            tempText.clear();
            tempText = this.getTextSplited(CFG.langManager.get("hAdministrationCost"));
            nData2.add(new MenuElement_Hover_v2_Element_Type_Text(" - ", COLOR_TITLE));

            for(int i = 0; i < tempText.size(); ++i) {
               nData2.add(new MenuElement_Hover_v2_Element_Type_Text(tempText.get(i), COLOR_TEXT));
               nElements2.add(new MenuElement_Hover_v2_Element2(nData2));
               nData2.clear();
            }

            tempText.clear();
            tempText = this.getTextSplited(CFG.langManager.get("tx"));
            nData2.add(new MenuElement_Hover_v2_Element_Type_Text("4. ", CFG.COLOR_TEXT_NUM_OF_PROVINCES));

            for(int i = 0; i < tempText.size(); ++i) {
               nData2.add(new MenuElement_Hover_v2_Element_Type_Text(tempText.get(i), COLOR_TITLE));
               nElements2.add(new MenuElement_Hover_v2_Element2(nData2));
               nData2.clear();
            }

            tempText.clear();
            tempText = this.getTextSplited(CFG.langManager.get("txa"));
            nData2.add(new MenuElement_Hover_v2_Element_Type_Text(" - ", COLOR_TITLE));

            for(int i = 0; i < tempText.size(); ++i) {
               nData2.add(new MenuElement_Hover_v2_Element_Type_Text(tempText.get(i), COLOR_TEXT));
               nElements2.add(new MenuElement_Hover_v2_Element2(nData2));
               nData2.clear();
            }

            this.textBox.add(new MenuElement_Hover_v2(nElements2));
            List<TutorialBox> tempBoxes = new ArrayList<>();
            tempBoxes.add(
               new TutorialBox("1. " + CFG.langManager.get("OpenTheViewOfYourCivilizationsBudget")) {
                  @Override
                  protected int getPosX() {
                     return CFG.menuManager.getInGame().getPosX() + CFG.menuManager.getInGame().getMenuElement(1).getPosX();
                  }
   
                  @Override
                  protected int getPosY() {
                     return CFG.menuManager.getInGame().getPosY()
                        + CFG.menuManager.getInGame().getMenuElement(1).getPosY()
                        + CFG.menuManager.getInGame().getMenuElement(1).getHeight()
                        + CFG.PADDING / 2
                        + CFG.PADDING;
                  }
               }
            );
            this.tutBoxes.add(tempBoxes);
            List<TutorialBox> tempBoxes2 = new ArrayList<>();
            tempBoxes2.add(
               new TutorialBox(CFG.langManager.get("Next")) {
                  @Override
                  protected int getPosX() {
                     return CFG.menuManager.getInGame_ProvinceInfo().getPosX() + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(0).getPosX();
                  }
   
                  @Override
                  protected int getPosY() {
                     return CFG.menuManager.getInGame_ProvinceInfo().getPosY()
                        + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(0).getPosY()
                        - CFG.PADDING / 2
                        - CFG.PADDING
                        - this.getHeight();
                  }
               }
            );
            tempBoxes2.add(
               new TutorialBox(CFG.langManager.get("1")) {
                  @Override
                  protected int getPosX() {
                     return CFG.menuManager.getInGame_Budget().getPosX() + CFG.menuManager.getInGame_Budget().getMenuElement(1).getPosX();
                  }
   
                  @Override
                  protected int getPosY() {
                     return CFG.menuManager.getInGame_Budget().getMenuPosY()
                        + CFG.menuManager.getInGame_Budget().getMenuElement(1).getPosY()
                        - CFG.PADDING / 2
                        - CFG.PADDING
                        - this.getHeight();
                  }
               }
            );
            tempBoxes2.add(
               new TutorialBox(CFG.langManager.get("2")) {
                  @Override
                  protected int getPosX() {
                     return CFG.menuManager.getInGame_Budget().getPosX() + CFG.menuManager.getInGame_Budget().getMenuElement(2).getPosX();
                  }
   
                  @Override
                  protected int getPosY() {
                     return CFG.menuManager.getInGame_Budget().getMenuPosY()
                        + CFG.menuManager.getInGame_Budget().getMenuElement(2).getPosY()
                        + CFG.menuManager.getInGame_Budget().getMenuElement(2).getHeight()
                        + CFG.PADDING / 2
                        + CFG.PADDING;
                  }
               }
            );
            tempBoxes2.add(
               new TutorialBox(CFG.langManager.get("3")) {
                  @Override
                  protected int getPosX() {
                     return CFG.menuManager.getInGame_Budget().getPosX() + CFG.menuManager.getInGame_Budget().getMenuElement(6).getPosX();
                  }
   
                  @Override
                  protected int getPosY() {
                     return CFG.menuManager.getInGame_Budget().getMenuPosY()
                        + CFG.menuManager.getInGame_Budget().getMenuElement(6).getPosY()
                        - CFG.PADDING / 2
                        - CFG.PADDING
                        - this.getHeight();
                  }
               }
            );
            tempBoxes2.add(
               new TutorialBox(CFG.langManager.get("4")) {
                  @Override
                  protected int getPosX() {
                     return CFG.menuManager.getInGame_Budget().getPosX() + CFG.menuManager.getInGame_Budget().getMenuElement(11).getPosX();
                  }
   
                  @Override
                  protected int getPosY() {
                     return CFG.menuManager.getInGame_Budget().getMenuPosY()
                        + CFG.menuManager.getInGame_Budget().getMenuElement(11).getPosY()
                        + CFG.menuManager.getInGame_Budget().getMenuElement(11).getHeight()
                        + CFG.PADDING / 2
                        + CFG.PADDING;
                  }
               }
            );
            this.tutBoxes.add(tempBoxes2);
            this.tutStep = new TutorialManager.TutStep() {
               @Override
               public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY) {
                  if (CFG.menuManager.getInGameView()) {
                     try {
                        if (!CFG.menuManager.getInGame_Budget().getVisible()) {
                           TutorialManager.this.INNER_STEP = 0;
                           TutorialManager.this.textBox
                              .get(0)
                              .draw_HoverWithoutAnimation(
                                 oSB,
                                 !CFG.menuManager.getInGame_ProvinceBuild_Visible() && !CFG.menuManager.getInGame_CivInfo().getVisible()
                                    ? TutorialManager.this.iDrawPosX
                                    : CFG.CIV_INFO_MENU_WIDTH + CFG.PADDING * 2,
                                 TutorialManager.this.iDrawPosY
                              );
                           TutorialManager.this.drawLine(
                              oSB,
                              CFG.menuManager.getInGame().getPosX() + CFG.menuManager.getInGame().getMenuElement(1).getPosX(),
                              CFG.PADDING / 2
                                 + CFG.menuManager.getInGame().getPosY()
                                 + CFG.menuManager.getInGame().getMenuElement(1).getPosY()
                                 + CFG.menuManager.getInGame().getMenuElement(1).getHeight(),
                              CFG.menuManager.getInGame().getMenuElement(1).getWidth()
                           );

                           for(int i = 0; i < TutorialManager.this.tutBoxes.get(TutorialManager.this.INNER_STEP).size(); ++i) {
                              TutorialManager.this.tutBoxes.get(TutorialManager.this.INNER_STEP).get(i).draw(oSB);
                           }
                        } else {
                           TutorialManager.this.INNER_STEP = 1;
                           TutorialManager.this.textBox.get(1).draw_HoverWithoutAnimation(oSB, TutorialManager.this.iDrawPosX, TutorialManager.this.iDrawPosY);
                           int tElemID = 0;
                           TutorialManager.this.drawLine(
                              oSB,
                              CFG.menuManager.getInGame_ProvinceInfo().getPosX() + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(tElemID).getPosX(),
                              -CFG.PADDING / 2
                                 + CFG.menuManager.getInGame_ProvinceInfo().getPosY()
                                 + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(tElemID).getPosY(),
                              CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(tElemID).getWidth()
                           );
                           int var9 = 1;
                           TutorialManager.this.drawLine(
                              oSB,
                              CFG.menuManager.getInGame_Budget().getPosX() + CFG.menuManager.getInGame_Budget().getMenuElement(var9).getPosX(),
                              -CFG.PADDING / 2
                                 + CFG.menuManager.getInGame_Budget().getMenuPosY()
                                 + CFG.menuManager.getInGame_Budget().getMenuElement(var9).getPosY(),
                              CFG.menuManager.getInGame_Budget().getMenuElement(var9).getWidth()
                           );
                           var9 = 2;
                           TutorialManager.this.drawLine(
                              oSB,
                              CFG.menuManager.getInGame_Budget().getPosX() + CFG.menuManager.getInGame_Budget().getMenuElement(var9).getPosX(),
                              CFG.PADDING / 2
                                 + CFG.menuManager.getInGame_Budget().getMenuPosY()
                                 + CFG.menuManager.getInGame_Budget().getMenuElement(var9).getPosY()
                                 + CFG.menuManager.getInGame_Budget().getMenuElement(var9).getHeight(),
                              CFG.menuManager.getInGame_Budget().getMenuElement(var9).getWidth()
                           );
                           var9 = 6;
                           TutorialManager.this.drawLine(
                              oSB,
                              CFG.menuManager.getInGame_Budget().getPosX() + CFG.menuManager.getInGame_Budget().getMenuElement(var9).getPosX(),
                              -CFG.PADDING / 2
                                 + CFG.menuManager.getInGame_Budget().getMenuPosY()
                                 + CFG.menuManager.getInGame_Budget().getMenuElement(var9).getPosY(),
                              CFG.menuManager.getInGame_Budget().getMenuElement(var9).getWidth()
                           );
                           var9 = 11;
                           TutorialManager.this.drawLine(
                              oSB,
                              CFG.menuManager.getInGame_Budget().getPosX() + CFG.menuManager.getInGame_Budget().getMenuElement(var9).getPosX(),
                              CFG.PADDING / 2
                                 + CFG.menuManager.getInGame_Budget().getMenuPosY()
                                 + CFG.menuManager.getInGame_Budget().getMenuElement(var9).getPosY()
                                 + CFG.menuManager.getInGame_Budget().getMenuElement(var9).getHeight(),
                              CFG.menuManager.getInGame_Budget().getMenuElement(var9).getWidth()
                           );
                           TutorialManager.this.drawLine(
                              oSB,
                              CFG.menuManager.getInGame_Budget().getPosX() + CFG.menuManager.getInGame_Budget().getMenuElement(var9).getPosX(),
                              -CFG.PADDING / 2
                                 + CFG.menuManager.getInGame_Budget().getMenuPosY()
                                 + CFG.menuManager.getInGame_Budget().getMenuElement(var9).getPosY(),
                              CFG.menuManager.getInGame_Budget().getMenuElement(var9).getWidth()
                           );

                           for(int i = 0; i < TutorialManager.this.tutBoxes.get(TutorialManager.this.INNER_STEP).size(); ++i) {
                              TutorialManager.this.tutBoxes.get(TutorialManager.this.INNER_STEP).get(i).draw(oSB);
                           }
                        }
                     } catch (IndexOutOfBoundsException var6) {
                     } catch (NullPointerException var7) {
                     }
                  }
               }

               @Override
               public boolean action(Tutorial_ActionType actionType) {
                  if (actionType == Tutorial_ActionType.NEXT_TURN && CFG.gameAction.getActiveTurnState() == Game_Action.TurnStates.INPUT_ORDERS) {
                     if (CFG.menuManager.getInGame_Budget().getVisible()) {
                        ++TutorialManager.this.STEP_ID;
                        TutorialManager.this.updateDrawTutorial(TutorialManager.this.IN_TUTORIAL);
                     } else {
                        CFG.toast.setInView(CFG.langManager.get("OpenTheViewOfYourCivilizationsBudget"), CFG.COLOR_TEXT_NUM_OF_PROVINCES);
                     }

                     return true;
                  } else {
                     return false;
                  }
               }
            };
         } else if (this.STEP_ID == 14) {
            this.iDrawPosX = CFG.PADDING * 2 + AoCGame.LEFT;
            this.iDrawPosY = CFG.GAME_HEIGHT;
            this.textBox.clear();
            this.tutBoxes.clear();
            List<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
            List<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("q0"), CFG.COLOR_TEXT_NUM_OF_PROVINCES));
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
            nData.add(new MenuElement_Hover_v2_Element_Type_Text("1. ", CFG.COLOR_TEXT_NUM_OF_PROVINCES));
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("OpenTheViewOfYourCivilizationsBudget"), COLOR_TITLE));
            nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_gold, CFG.PADDING, 0));
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
            this.textBox.add(new MenuElement_Hover_v2(nElements));
            List<MenuElement_Hover_v2_Element2> nElements2 = new ArrayList<>();
            List<MenuElement_Hover_v2_Element_Type> nData2 = new ArrayList<>();
            List<String> tempText = this.getTextSplited(CFG.langManager.get("hGoods"));
            nData2.add(new MenuElement_Hover_v2_Element_Type_Text("1. ", CFG.COLOR_TEXT_NUM_OF_PROVINCES));

            for(int i = 0; i < tempText.size(); ++i) {
               nData2.add(new MenuElement_Hover_v2_Element_Type_Text(tempText.get(i), COLOR_TITLE));
               nElements2.add(new MenuElement_Hover_v2_Element2(nData2));
               nData2.clear();
            }

            tempText.clear();
            tempText = this.getTextSplited(CFG.langManager.get("hGoods2"));

            for(int i = 0; i < tempText.size(); ++i) {
               nData2.add(new MenuElement_Hover_v2_Element_Type_Text(tempText.get(i), COLOR_TEXT));
               nElements2.add(new MenuElement_Hover_v2_Element2(nData2));
               nData2.clear();
            }

            tempText.clear();
            tempText = this.getTextSplited(
               CFG.langManager
                  .get(
                     "hGoods3",
                     (int)(
                        CFG.ideologiesManager
                              .getIdeology(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getIdeologyID())
                              .getMin_Goods(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
                           * 100.0F
                     )
                  )
            );

            for(int i = 0; i < tempText.size(); ++i) {
               nData2.add(new MenuElement_Hover_v2_Element_Type_Text(tempText.get(i), COLOR_TEXT));
               nElements2.add(new MenuElement_Hover_v2_Element2(nData2));
               nData2.clear();
            }

            tempText.clear();
            tempText = this.getTextSplited(CFG.langManager.get("hGoods4"));

            for(int i = 0; i < tempText.size(); ++i) {
               nData2.add(new MenuElement_Hover_v2_Element_Type_Text(tempText.get(i), COLOR_TEXT));
               nElements2.add(new MenuElement_Hover_v2_Element2(nData2));
               nData2.clear();
            }

            this.textBox.add(new MenuElement_Hover_v2(nElements2));
            List<TutorialBox> tempBoxes = new ArrayList<>();
            tempBoxes.add(
               new TutorialBox("1. " + CFG.langManager.get("OpenTheViewOfYourCivilizationsBudget")) {
                  @Override
                  protected int getPosX() {
                     return CFG.menuManager.getInGame().getPosX() + CFG.menuManager.getInGame().getMenuElement(1).getPosX();
                  }
   
                  @Override
                  protected int getPosY() {
                     return CFG.menuManager.getInGame().getPosY()
                        + CFG.menuManager.getInGame().getMenuElement(1).getPosY()
                        + CFG.menuManager.getInGame().getMenuElement(1).getHeight()
                        + CFG.PADDING / 2
                        + CFG.PADDING;
                  }
               }
            );
            this.tutBoxes.add(tempBoxes);
            List<TutorialBox> tempBoxes2 = new ArrayList<>();
            tempBoxes2.add(
               new TutorialBox(CFG.langManager.get("Next")) {
                  @Override
                  protected int getPosX() {
                     return CFG.menuManager.getInGame_ProvinceInfo().getPosX() + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(0).getPosX();
                  }
   
                  @Override
                  protected int getPosY() {
                     return CFG.menuManager.getInGame_ProvinceInfo().getPosY()
                        + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(0).getPosY()
                        - CFG.PADDING / 2
                        - CFG.PADDING
                        - this.getHeight();
                  }
               }
            );
            tempBoxes2.add(
               new TutorialBox(CFG.langManager.get("1")) {
                  @Override
                  protected int getPosX() {
                     return CFG.menuManager.getInGame_Budget().getPosX() + CFG.menuManager.getInGame_Budget().getMenuElement(13).getPosX();
                  }
   
                  @Override
                  protected int getPosY() {
                     return CFG.menuManager.getInGame_Budget().getMenuPosY()
                        + CFG.menuManager.getInGame_Budget().getMenuElement(13).getPosY()
                        - CFG.PADDING / 2
                        - CFG.PADDING
                        - this.getHeight();
                  }
               }
            );
            this.tutBoxes.add(tempBoxes2);
            this.tutStep = new TutorialManager.TutStep() {
               @Override
               public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY) {
                  if (CFG.menuManager.getInGameView()) {
                     try {
                        if (!CFG.menuManager.getInGame_Budget().getVisible()) {
                           TutorialManager.this.INNER_STEP = 0;
                           TutorialManager.this.textBox
                              .get(0)
                              .draw_HoverWithoutAnimation(
                                 oSB,
                                 !CFG.menuManager.getInGame_ProvinceBuild_Visible() && !CFG.menuManager.getInGame_CivInfo().getVisible()
                                    ? TutorialManager.this.iDrawPosX
                                    : CFG.CIV_INFO_MENU_WIDTH + CFG.PADDING * 2,
                                 TutorialManager.this.iDrawPosY
                              );
                           TutorialManager.this.drawLine(
                              oSB,
                              CFG.menuManager.getInGame().getPosX() + CFG.menuManager.getInGame().getMenuElement(1).getPosX(),
                              CFG.PADDING / 2
                                 + CFG.menuManager.getInGame().getPosY()
                                 + CFG.menuManager.getInGame().getMenuElement(1).getPosY()
                                 + CFG.menuManager.getInGame().getMenuElement(1).getHeight(),
                              CFG.menuManager.getInGame().getMenuElement(1).getWidth()
                           );

                           for(int i = 0; i < TutorialManager.this.tutBoxes.get(TutorialManager.this.INNER_STEP).size(); ++i) {
                              TutorialManager.this.tutBoxes.get(TutorialManager.this.INNER_STEP).get(i).draw(oSB);
                           }
                        } else {
                           TutorialManager.this.INNER_STEP = 1;
                           TutorialManager.this.textBox.get(1).draw_HoverWithoutAnimation(oSB, TutorialManager.this.iDrawPosX, TutorialManager.this.iDrawPosY);
                           int tElemID = 0;
                           TutorialManager.this.drawLine(
                              oSB,
                              CFG.menuManager.getInGame_ProvinceInfo().getPosX() + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(tElemID).getPosX(),
                              -CFG.PADDING / 2
                                 + CFG.menuManager.getInGame_ProvinceInfo().getPosY()
                                 + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(tElemID).getPosY(),
                              CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(tElemID).getWidth()
                           );
                           int var9 = 13;
                           TutorialManager.this.drawLine(
                              oSB,
                              CFG.menuManager.getInGame_Budget().getPosX() + CFG.menuManager.getInGame_Budget().getMenuElement(var9).getPosX(),
                              -CFG.PADDING / 2
                                 + CFG.menuManager.getInGame_Budget().getMenuPosY()
                                 + CFG.menuManager.getInGame_Budget().getMenuElement(var9).getPosY(),
                              CFG.menuManager.getInGame_Budget().getMenuElement(var9).getWidth()
                           );
                           var9 = 14;
                           TutorialManager.this.drawLine(
                              oSB,
                              CFG.menuManager.getInGame_Budget().getPosX() + CFG.menuManager.getInGame_Budget().getMenuElement(var9).getPosX(),
                              CFG.PADDING / 2
                                 + CFG.menuManager.getInGame_Budget().getMenuPosY()
                                 + CFG.menuManager.getInGame_Budget().getMenuElement(var9).getPosY()
                                 + CFG.menuManager.getInGame_Budget().getMenuElement(var9).getHeight(),
                              CFG.menuManager.getInGame_Budget().getMenuElement(var9).getWidth()
                           );

                           for(int i = 0; i < TutorialManager.this.tutBoxes.get(TutorialManager.this.INNER_STEP).size(); ++i) {
                              TutorialManager.this.tutBoxes.get(TutorialManager.this.INNER_STEP).get(i).draw(oSB);
                           }
                        }
                     } catch (IndexOutOfBoundsException var6) {
                     } catch (NullPointerException var7) {
                     }
                  }
               }

               @Override
               public boolean action(Tutorial_ActionType actionType) {
                  if (actionType == Tutorial_ActionType.NEXT_TURN && CFG.gameAction.getActiveTurnState() == Game_Action.TurnStates.INPUT_ORDERS) {
                     if (CFG.menuManager.getInGame_Budget().getVisible()) {
                        ++TutorialManager.this.STEP_ID;
                        TutorialManager.this.updateDrawTutorial(TutorialManager.this.IN_TUTORIAL);
                     } else {
                        CFG.toast.setInView(CFG.langManager.get("OpenTheViewOfYourCivilizationsBudget"), CFG.COLOR_TEXT_NUM_OF_PROVINCES);
                     }

                     return true;
                  } else {
                     return false;
                  }
               }
            };
         } else if (this.STEP_ID == 15) {
            this.iDrawPosX = CFG.PADDING * 2 + AoCGame.LEFT;
            this.iDrawPosY = CFG.GAME_HEIGHT;
            this.textBox.clear();
            this.tutBoxes.clear();
            List<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
            List<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("q0"), CFG.COLOR_TEXT_NUM_OF_PROVINCES));
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
            nData.add(new MenuElement_Hover_v2_Element_Type_Text("1. ", CFG.COLOR_TEXT_NUM_OF_PROVINCES));
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("OpenTheViewOfYourCivilizationsBudget"), COLOR_TITLE));
            nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_gold, CFG.PADDING, 0));
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
            this.textBox.add(new MenuElement_Hover_v2(nElements));
            List<MenuElement_Hover_v2_Element2> nElements2 = new ArrayList<>();
            List<MenuElement_Hover_v2_Element_Type> nData2 = new ArrayList<>();
            List<String> tempText = this.getTextSplited(CFG.langManager.get("Tech1"));
            nData2.add(new MenuElement_Hover_v2_Element_Type_Text("1. ", CFG.COLOR_TEXT_NUM_OF_PROVINCES));

            for(int i = 0; i < tempText.size(); ++i) {
               nData2.add(new MenuElement_Hover_v2_Element_Type_Text(tempText.get(i), COLOR_TITLE));
               nElements2.add(new MenuElement_Hover_v2_Element2(nData2));
               nData2.clear();
            }

            tempText.clear();
            tempText = this.getTextSplited(CFG.langManager.get("Tech2"));

            for(int i = 0; i < tempText.size(); ++i) {
               nData2.add(new MenuElement_Hover_v2_Element_Type_Text(tempText.get(i), COLOR_TEXT));
               nElements2.add(new MenuElement_Hover_v2_Element2(nData2));
               nData2.clear();
            }

            tempText.clear();
            tempText = this.getTextSplited(CFG.langManager.get("Tech3"));

            for(int i = 0; i < tempText.size(); ++i) {
               nData2.add(new MenuElement_Hover_v2_Element_Type_Text(tempText.get(i), COLOR_TEXT));
               nElements2.add(new MenuElement_Hover_v2_Element2(nData2));
               nData2.clear();
            }

            tempText.clear();
            tempText = this.getTextSplited(CFG.langManager.get("Tech4"));

            for(int i = 0; i < tempText.size(); ++i) {
               nData2.add(new MenuElement_Hover_v2_Element_Type_Text(tempText.get(i), COLOR_TEXT));
               nElements2.add(new MenuElement_Hover_v2_Element2(nData2));
               nData2.clear();
            }

            this.textBox.add(new MenuElement_Hover_v2(nElements2));
            List<TutorialBox> tempBoxes = new ArrayList<>();
            tempBoxes.add(
               new TutorialBox("1. " + CFG.langManager.get("OpenTheViewOfYourCivilizationsBudget")) {
                  @Override
                  protected int getPosX() {
                     return CFG.menuManager.getInGame().getPosX() + CFG.menuManager.getInGame().getMenuElement(1).getPosX();
                  }
   
                  @Override
                  protected int getPosY() {
                     return CFG.menuManager.getInGame().getPosY()
                        + CFG.menuManager.getInGame().getMenuElement(1).getPosY()
                        + CFG.menuManager.getInGame().getMenuElement(1).getHeight()
                        + CFG.PADDING / 2
                        + CFG.PADDING;
                  }
               }
            );
            this.tutBoxes.add(tempBoxes);
            List<TutorialBox> tempBoxes2 = new ArrayList<>();
            tempBoxes2.add(
               new TutorialBox(CFG.langManager.get("Next")) {
                  @Override
                  protected int getPosX() {
                     return CFG.menuManager.getInGame_ProvinceInfo().getPosX() + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(0).getPosX();
                  }
   
                  @Override
                  protected int getPosY() {
                     return CFG.menuManager.getInGame_ProvinceInfo().getPosY()
                        + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(0).getPosY()
                        - CFG.PADDING / 2
                        - CFG.PADDING
                        - this.getHeight();
                  }
               }
            );
            tempBoxes2.add(
               new TutorialBox(CFG.langManager.get("1")) {
                  @Override
                  protected int getPosX() {
                     return CFG.menuManager.getInGame_Budget().getPosX() + CFG.menuManager.getInGame_Budget().getMenuElement(15).getPosX();
                  }
   
                  @Override
                  protected int getPosY() {
                     return CFG.menuManager.getInGame_Budget().getMenuPosY()
                        + CFG.menuManager.getInGame_Budget().getMenuElement(15).getPosY()
                        - CFG.PADDING / 2
                        - CFG.PADDING
                        - this.getHeight();
                  }
               }
            );
            this.tutBoxes.add(tempBoxes2);
            this.tutStep = new TutorialManager.TutStep() {
               @Override
               public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY) {
                  if (CFG.menuManager.getInGameView()) {
                     try {
                        if (!CFG.menuManager.getInGame_Budget().getVisible()) {
                           TutorialManager.this.INNER_STEP = 0;
                           TutorialManager.this.textBox
                              .get(0)
                              .draw_HoverWithoutAnimation(
                                 oSB,
                                 !CFG.menuManager.getInGame_ProvinceBuild_Visible() && !CFG.menuManager.getInGame_CivInfo().getVisible()
                                    ? TutorialManager.this.iDrawPosX
                                    : CFG.CIV_INFO_MENU_WIDTH + CFG.PADDING * 2,
                                 TutorialManager.this.iDrawPosY
                              );
                           TutorialManager.this.drawLine(
                              oSB,
                              CFG.menuManager.getInGame().getPosX() + CFG.menuManager.getInGame().getMenuElement(1).getPosX(),
                              CFG.PADDING / 2
                                 + CFG.menuManager.getInGame().getPosY()
                                 + CFG.menuManager.getInGame().getMenuElement(1).getPosY()
                                 + CFG.menuManager.getInGame().getMenuElement(1).getHeight(),
                              CFG.menuManager.getInGame().getMenuElement(1).getWidth()
                           );

                           for(int i = 0; i < TutorialManager.this.tutBoxes.get(TutorialManager.this.INNER_STEP).size(); ++i) {
                              TutorialManager.this.tutBoxes.get(TutorialManager.this.INNER_STEP).get(i).draw(oSB);
                           }
                        } else {
                           TutorialManager.this.INNER_STEP = 1;
                           TutorialManager.this.textBox.get(1).draw_HoverWithoutAnimation(oSB, TutorialManager.this.iDrawPosX, TutorialManager.this.iDrawPosY);
                           int tElemID = 0;
                           TutorialManager.this.drawLine(
                              oSB,
                              CFG.menuManager.getInGame_ProvinceInfo().getPosX() + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(tElemID).getPosX(),
                              -CFG.PADDING / 2
                                 + CFG.menuManager.getInGame_ProvinceInfo().getPosY()
                                 + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(tElemID).getPosY(),
                              CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(tElemID).getWidth()
                           );
                           int var9 = 15;
                           TutorialManager.this.drawLine(
                              oSB,
                              CFG.menuManager.getInGame_Budget().getPosX() + CFG.menuManager.getInGame_Budget().getMenuElement(var9).getPosX(),
                              -CFG.PADDING / 2
                                 + CFG.menuManager.getInGame_Budget().getMenuPosY()
                                 + CFG.menuManager.getInGame_Budget().getMenuElement(var9).getPosY(),
                              CFG.menuManager.getInGame_Budget().getMenuElement(var9).getWidth()
                           );
                           var9 = 16;
                           TutorialManager.this.drawLine(
                              oSB,
                              CFG.menuManager.getInGame_Budget().getPosX() + CFG.menuManager.getInGame_Budget().getMenuElement(var9).getPosX(),
                              CFG.PADDING / 2
                                 + CFG.menuManager.getInGame_Budget().getMenuPosY()
                                 + CFG.menuManager.getInGame_Budget().getMenuElement(var9).getPosY()
                                 + CFG.menuManager.getInGame_Budget().getMenuElement(var9).getHeight(),
                              CFG.menuManager.getInGame_Budget().getMenuElement(var9).getWidth()
                           );

                           for(int i = 0; i < TutorialManager.this.tutBoxes.get(TutorialManager.this.INNER_STEP).size(); ++i) {
                              TutorialManager.this.tutBoxes.get(TutorialManager.this.INNER_STEP).get(i).draw(oSB);
                           }
                        }
                     } catch (IndexOutOfBoundsException var6) {
                     } catch (NullPointerException var7) {
                     }
                  }
               }

               @Override
               public boolean action(Tutorial_ActionType actionType) {
                  if (actionType == Tutorial_ActionType.NEXT_TURN && CFG.gameAction.getActiveTurnState() == Game_Action.TurnStates.INPUT_ORDERS) {
                     if (CFG.menuManager.getInGame_Budget().getVisible()) {
                        ++TutorialManager.this.STEP_ID;
                        TutorialManager.this.updateDrawTutorial(TutorialManager.this.IN_TUTORIAL);
                     } else {
                        CFG.toast.setInView(CFG.langManager.get("OpenTheViewOfYourCivilizationsBudget"), CFG.COLOR_TEXT_NUM_OF_PROVINCES);
                     }

                     return true;
                  } else {
                     return false;
                  }
               }
            };
         } else if (this.STEP_ID == 16) {
            this.iDrawPosX = CFG.PADDING * 2 + AoCGame.LEFT;
            this.iDrawPosY = CFG.GAME_HEIGHT;
            this.textBox.clear();
            this.tutBoxes.clear();
            List<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
            List<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("q0"), CFG.COLOR_TEXT_NUM_OF_PROVINCES));
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
            nData.add(new MenuElement_Hover_v2_Element_Type_Text("1. ", CFG.COLOR_TEXT_NUM_OF_PROVINCES));
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("OpenTheViewOfYourCivilizationsBudget"), COLOR_TITLE));
            nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_gold, CFG.PADDING, 0));
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
            this.textBox.add(new MenuElement_Hover_v2(nElements));
            List<MenuElement_Hover_v2_Element2> nElements2 = new ArrayList<>();
            List<MenuElement_Hover_v2_Element_Type> nData2 = new ArrayList<>();
            List<String> tempText = this.getTextSplited(CFG.langManager.get("BuildYourEconomicPowerBySpendingGoldOnInvestments"));
            nData2.add(new MenuElement_Hover_v2_Element_Type_Text("1. ", CFG.COLOR_TEXT_NUM_OF_PROVINCES));

            for(int i = 0; i < tempText.size(); ++i) {
               nData2.add(new MenuElement_Hover_v2_Element_Type_Text(tempText.get(i), COLOR_TITLE));
               nElements2.add(new MenuElement_Hover_v2_Element2(nData2));
               nData2.clear();
            }

            tempText.clear();
            tempText = this.getTextSplited(CFG.langManager.get("DevelopmentLevelAndEconomyWillBeIncreased"));

            for(int i = 0; i < tempText.size(); ++i) {
               nData2.add(new MenuElement_Hover_v2_Element_Type_Text(tempText.get(i), COLOR_TEXT));
               nElements2.add(new MenuElement_Hover_v2_Element2(nData2));
               nData2.clear();
            }

            tempText.clear();
            tempText = this.getTextSplited(CFG.langManager.get("Tech5"));

            for(int i = 0; i < tempText.size(); ++i) {
               nData2.add(new MenuElement_Hover_v2_Element_Type_Text(tempText.get(i), COLOR_TEXT));
               nElements2.add(new MenuElement_Hover_v2_Element2(nData2));
               nData2.clear();
            }

            this.textBox.add(new MenuElement_Hover_v2(nElements2));
            List<TutorialBox> tempBoxes = new ArrayList<>();
            tempBoxes.add(
               new TutorialBox("1. " + CFG.langManager.get("OpenTheViewOfYourCivilizationsBudget")) {
                  @Override
                  protected int getPosX() {
                     return CFG.menuManager.getInGame().getPosX() + CFG.menuManager.getInGame().getMenuElement(1).getPosX();
                  }
   
                  @Override
                  protected int getPosY() {
                     return CFG.menuManager.getInGame().getPosY()
                        + CFG.menuManager.getInGame().getMenuElement(1).getPosY()
                        + CFG.menuManager.getInGame().getMenuElement(1).getHeight()
                        + CFG.PADDING / 2
                        + CFG.PADDING;
                  }
               }
            );
            this.tutBoxes.add(tempBoxes);
            List<TutorialBox> tempBoxes2 = new ArrayList<>();
            tempBoxes2.add(
               new TutorialBox(CFG.langManager.get("Next")) {
                  @Override
                  protected int getPosX() {
                     return CFG.menuManager.getInGame_ProvinceInfo().getPosX() + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(0).getPosX();
                  }
   
                  @Override
                  protected int getPosY() {
                     return CFG.menuManager.getInGame_ProvinceInfo().getPosY()
                        + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(0).getPosY()
                        - CFG.PADDING / 2
                        - CFG.PADDING
                        - this.getHeight();
                  }
               }
            );
            tempBoxes2.add(
               new TutorialBox(CFG.langManager.get("1")) {
                  @Override
                  protected int getPosX() {
                     return CFG.menuManager.getInGame_Budget().getPosX() + CFG.menuManager.getInGame_Budget().getMenuElement(17).getPosX();
                  }
   
                  @Override
                  protected int getPosY() {
                     return CFG.menuManager.getInGame_Budget().getMenuPosY()
                        + CFG.menuManager.getInGame_Budget().getMenuElement(17).getPosY()
                        - CFG.PADDING / 2
                        - CFG.PADDING
                        - this.getHeight();
                  }
               }
            );
            this.tutBoxes.add(tempBoxes2);
            this.tutStep = new TutorialManager.TutStep() {
               @Override
               public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY) {
                  if (CFG.menuManager.getInGameView()) {
                     try {
                        if (!CFG.menuManager.getInGame_Budget().getVisible()) {
                           TutorialManager.this.INNER_STEP = 0;
                           TutorialManager.this.textBox
                              .get(0)
                              .draw_HoverWithoutAnimation(
                                 oSB,
                                 !CFG.menuManager.getInGame_ProvinceBuild_Visible() && !CFG.menuManager.getInGame_CivInfo().getVisible()
                                    ? TutorialManager.this.iDrawPosX
                                    : CFG.CIV_INFO_MENU_WIDTH + CFG.PADDING * 2,
                                 TutorialManager.this.iDrawPosY
                              );
                           TutorialManager.this.drawLine(
                              oSB,
                              CFG.menuManager.getInGame().getPosX() + CFG.menuManager.getInGame().getMenuElement(1).getPosX(),
                              CFG.PADDING / 2
                                 + CFG.menuManager.getInGame().getPosY()
                                 + CFG.menuManager.getInGame().getMenuElement(1).getPosY()
                                 + CFG.menuManager.getInGame().getMenuElement(1).getHeight(),
                              CFG.menuManager.getInGame().getMenuElement(1).getWidth()
                           );

                           for(int i = 0; i < TutorialManager.this.tutBoxes.get(TutorialManager.this.INNER_STEP).size(); ++i) {
                              TutorialManager.this.tutBoxes.get(TutorialManager.this.INNER_STEP).get(i).draw(oSB);
                           }
                        } else {
                           TutorialManager.this.INNER_STEP = 1;
                           TutorialManager.this.textBox.get(1).draw_HoverWithoutAnimation(oSB, TutorialManager.this.iDrawPosX, TutorialManager.this.iDrawPosY);
                           int tElemID = 0;
                           TutorialManager.this.drawLine(
                              oSB,
                              CFG.menuManager.getInGame_ProvinceInfo().getPosX() + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(tElemID).getPosX(),
                              -CFG.PADDING / 2
                                 + CFG.menuManager.getInGame_ProvinceInfo().getPosY()
                                 + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(tElemID).getPosY(),
                              CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(tElemID).getWidth()
                           );
                           int var9 = 17;
                           TutorialManager.this.drawLine(
                              oSB,
                              CFG.menuManager.getInGame_Budget().getPosX() + CFG.menuManager.getInGame_Budget().getMenuElement(var9).getPosX(),
                              -CFG.PADDING / 2
                                 + CFG.menuManager.getInGame_Budget().getMenuPosY()
                                 + CFG.menuManager.getInGame_Budget().getMenuElement(var9).getPosY(),
                              CFG.menuManager.getInGame_Budget().getMenuElement(var9).getWidth()
                           );
                           var9 = 18;
                           TutorialManager.this.drawLine(
                              oSB,
                              CFG.menuManager.getInGame_Budget().getPosX() + CFG.menuManager.getInGame_Budget().getMenuElement(var9).getPosX(),
                              CFG.PADDING / 2
                                 + CFG.menuManager.getInGame_Budget().getMenuPosY()
                                 + CFG.menuManager.getInGame_Budget().getMenuElement(var9).getPosY()
                                 + CFG.menuManager.getInGame_Budget().getMenuElement(var9).getHeight(),
                              CFG.menuManager.getInGame_Budget().getMenuElement(var9).getWidth()
                           );

                           for(int i = 0; i < TutorialManager.this.tutBoxes.get(TutorialManager.this.INNER_STEP).size(); ++i) {
                              TutorialManager.this.tutBoxes.get(TutorialManager.this.INNER_STEP).get(i).draw(oSB);
                           }
                        }
                     } catch (IndexOutOfBoundsException var6) {
                     } catch (NullPointerException var7) {
                     }
                  }
               }

               @Override
               public boolean action(Tutorial_ActionType actionType) {
                  if (actionType == Tutorial_ActionType.NEXT_TURN && CFG.gameAction.getActiveTurnState() == Game_Action.TurnStates.INPUT_ORDERS) {
                     if (CFG.menuManager.getInGame_Budget().getVisible()) {
                        CFG.menuManager.setVisible_InGame_Budget(false);
                        CFG.menuManager.setVisible_InGame_CivInfo(false);
                        ++TutorialManager.this.STEP_ID;
                        TutorialManager.this.updateDrawTutorial(TutorialManager.this.IN_TUTORIAL);
                     } else {
                        CFG.toast.setInView(CFG.langManager.get("OpenTheViewOfYourCivilizationsBudget"), CFG.COLOR_TEXT_NUM_OF_PROVINCES);
                     }

                     return true;
                  } else {
                     return false;
                  }
               }
            };
         } else if (this.STEP_ID == 17) {
            this.iDrawPosX = CFG.PADDING * 2 + AoCGame.LEFT;
            this.iDrawPosY = CFG.menuManager.getInGame().getPosY()
               + CFG.menuManager.getInGame().getMenuElement(7).getPosY()
               + CFG.menuManager.getInGame().getMenuElement(7).getHeight()
               + CFG.PADDING / 2
               + CFG.PADDING
               + CFG.TEXT_HEIGHT
               + CFG.PADDING * 2
               + CFG.PADDING;
            this.textBox.clear();
            this.tutBoxes.clear();
            List<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
            List<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("q0"), CFG.COLOR_TEXT_NUM_OF_PROVINCES));
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
            nData.add(new MenuElement_Hover_v2_Element_Type_Text("1. ", CFG.COLOR_TEXT_NUM_OF_PROVINCES));
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("d0"), COLOR_TITLE));
            nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_diplomacy_points, CFG.PADDING, 0));
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
            this.textBox.add(new MenuElement_Hover_v2(nElements));
            List<TutorialBox> tempBoxes = new ArrayList<>();
            tempBoxes.add(
               new TutorialBox(CFG.langManager.get("1")) {
                  @Override
                  protected int getPosX() {
                     return CFG.menuManager.getInGame().getPosX() + CFG.menuManager.getInGame().getMenuElement(8).getPosX();
                  }
   
                  @Override
                  protected int getPosY() {
                     return CFG.menuManager.getInGame().getPosY()
                        + CFG.menuManager.getInGame().getMenuElement(8).getPosY()
                        + CFG.menuManager.getInGame().getMenuElement(8).getHeight()
                        + CFG.PADDING / 2
                        + CFG.PADDING;
                  }
               }
            );
            this.tutBoxes.add(tempBoxes);
            this.tutStep = new TutorialManager.TutStep() {
               @Override
               public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY) {
                  if (CFG.menuManager.getInGameView()) {
                     try {
                        TutorialManager.this.textBox
                           .get(0)
                           .draw_HoverWithoutAnimation(
                              oSB,
                              !CFG.menuManager.getInGame_ProvinceBuild_Visible() && !CFG.menuManager.getInGame_CivInfo().getVisible()
                                 ? TutorialManager.this.iDrawPosX
                                 : CFG.CIV_INFO_MENU_WIDTH + CFG.PADDING * 2,
                              TutorialManager.this.iDrawPosY
                           );
                        TutorialManager.this.drawLine(
                           oSB,
                           CFG.menuManager.getInGame().getPosX() + CFG.menuManager.getInGame().getMenuElement(8).getPosX(),
                           CFG.PADDING / 2
                              + CFG.menuManager.getInGame().getPosY()
                              + CFG.menuManager.getInGame().getMenuElement(8).getPosY()
                              + CFG.menuManager.getInGame().getMenuElement(8).getHeight(),
                           CFG.menuManager.getInGame().getMenuElement(8).getWidth()
                        );

                        for(int i = 0; i < TutorialManager.this.tutBoxes.get(TutorialManager.this.INNER_STEP).size(); ++i) {
                           TutorialManager.this.tutBoxes.get(TutorialManager.this.INNER_STEP).get(i).draw(oSB);
                        }

                        if (CFG.menuManager.getVisible_InGame_CivInfo()) {
                           ++TutorialManager.this.STEP_ID;
                           TutorialManager.this.updateDrawTutorial(TutorialManager.this.IN_TUTORIAL);
                        }
                     } catch (IndexOutOfBoundsException var5) {
                     } catch (NullPointerException var6) {
                     }
                  }
               }

               @Override
               public boolean action(Tutorial_ActionType actionType) {
                  if (actionType == Tutorial_ActionType.NEXT_TURN && CFG.gameAction.getActiveTurnState() == Game_Action.TurnStates.INPUT_ORDERS) {
                     CFG.toast.setInView(CFG.langManager.get("d0"), CFG.COLOR_TEXT_NUM_OF_PROVINCES);
                     return true;
                  } else {
                     return false;
                  }
               }
            };
         } else if (this.STEP_ID == 18) {
            this.iDrawPosX = CFG.CIV_INFO_MENU_WIDTH + CFG.PADDING * 2 + AoCGame.LEFT;
            this.iDrawPosY = CFG.menuManager.getInGame().getPosY()
               + CFG.menuManager.getInGame().getMenuElement(7).getPosY()
               + CFG.menuManager.getInGame().getMenuElement(7).getHeight()
               + CFG.PADDING / 2
               + CFG.PADDING
               + CFG.TEXT_HEIGHT
               + CFG.PADDING * 2
               + CFG.PADDING;
            this.textBox.clear();
            this.tutBoxes.clear();
            List<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
            List<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("q0"), CFG.COLOR_TEXT_NUM_OF_PROVINCES));
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
            nData.add(new MenuElement_Hover_v2_Element_Type_Text("1. ", CFG.COLOR_TEXT_NUM_OF_PROVINCES));
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("d0a"), COLOR_TITLE));
            nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.top_diplomacy_points, CFG.PADDING, 0));
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
            nData.add(new MenuElement_Hover_v2_Element_Type_Space());
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
            List<String> tempText = this.getTextSplited(CFG.langManager.get("d1"));

            for(int i = 0; i < tempText.size(); ++i) {
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(tempText.get(i), COLOR_TITLE));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
            }

            tempText.clear();
            tempText = this.getTextSplited(CFG.langManager.get("d2"));

            for(int i = 0; i < tempText.size(); ++i) {
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(tempText.get(i), COLOR_TEXT));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
            }

            nData.add(new MenuElement_Hover_v2_Element_Type_Space());
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
            tempText.clear();
            tempText = this.getTextSplited(CFG.langManager.get("d3"));
            nData.add(new MenuElement_Hover_v2_Element_Type_Text("2. ", CFG.COLOR_TEXT_NUM_OF_PROVINCES));

            for(int i = 0; i < tempText.size(); ++i) {
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(tempText.get(i), COLOR_TEXT));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
            }

            tempText.clear();
            tempText = this.getTextSplited(CFG.langManager.get("d4"));
            nData.add(new MenuElement_Hover_v2_Element_Type_Text("3. ", CFG.COLOR_TEXT_NUM_OF_PROVINCES));

            for(int i = 0; i < tempText.size(); ++i) {
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(tempText.get(i), COLOR_TEXT));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
            }

            tempText.clear();
            tempText = this.getTextSplited(CFG.langManager.get("d5"));
            nData.add(new MenuElement_Hover_v2_Element_Type_Text("4. ", CFG.COLOR_TEXT_NUM_OF_PROVINCES));

            for(int i = 0; i < tempText.size(); ++i) {
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(tempText.get(i), COLOR_TEXT));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
            }

            this.textBox.add(new MenuElement_Hover_v2(nElements));
            List<TutorialBox> tempBoxes = new ArrayList<>();
            tempBoxes.add(
               new TutorialBox(CFG.langManager.get("1")) {
                  @Override
                  protected int getPosX() {
                     return CFG.menuManager.getInGame().getPosX() + CFG.menuManager.getInGame().getMenuElement(8).getPosX();
                  }
   
                  @Override
                  protected int getPosY() {
                     return CFG.menuManager.getInGame().getPosY()
                        + CFG.menuManager.getInGame().getMenuElement(8).getPosY()
                        + CFG.menuManager.getInGame().getMenuElement(8).getHeight()
                        + CFG.PADDING / 2
                        + CFG.PADDING;
                  }
               }
            );
            tempBoxes.add(new TutorialBox(CFG.langManager.get("2")) {
               @Override
               protected int getPosX() {
                  return CFG.menuManager.getInGame_Civ_Info().getPosX() + CFG.menuManager.getInGame_Civ_Info().getWidth() - this.getWidth();
               }

               @Override
               protected int getPosY() {
                  return CFG.menuManager.getInGame_Civ_Info().getPosY();
               }
            });
            tempBoxes.add(new TutorialBox(CFG.langManager.get("3")) {
               @Override
               protected int getPosX() {
                  return CFG.menuManager.getInGame_Civ_Info_Diplomacy().getPosX()
                     + CFG.menuManager.getInGame_Civ_Info_Diplomacy().getWidth()
                     - this.getWidth();
               }

               @Override
               protected int getPosY() {
                  return CFG.menuManager.getInGame_Civ_Info_Diplomacy().getPosY();
               }
            });
            tempBoxes.add(new TutorialBox(CFG.langManager.get("4")) {
               @Override
               protected int getPosX() {
                  return CFG.menuManager.getInGame_Civ_Info_Actions().getPosX() + CFG.menuManager.getInGame_Civ_Info_Actions().getWidth() - this.getWidth();
               }

               @Override
               protected int getPosY() {
                  return CFG.menuManager.getInGame_Civ_Info_Actions().getPosY();
               }
            });
            this.tutBoxes.add(tempBoxes);
            this.tutStep = new TutorialManager.TutStep() {
               @Override
               public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY) {
                  if (CFG.menuManager.getInGameView()) {
                     try {
                        TutorialManager.this.textBox
                           .get(0)
                           .draw_HoverWithoutAnimation(
                              oSB,
                              !CFG.menuManager.getInGame_ProvinceBuild_Visible() && !CFG.menuManager.getInGame_CivInfo().getVisible()
                                 ? TutorialManager.this.iDrawPosX
                                 : CFG.CIV_INFO_MENU_WIDTH + CFG.PADDING * 2,
                              TutorialManager.this.iDrawPosY
                           );
                        TutorialManager.this.drawLine(
                           oSB,
                           CFG.menuManager.getInGame().getPosX() + CFG.menuManager.getInGame().getMenuElement(8).getPosX(),
                           CFG.PADDING / 2
                              + CFG.menuManager.getInGame().getPosY()
                              + CFG.menuManager.getInGame().getMenuElement(8).getPosY()
                              + CFG.menuManager.getInGame().getMenuElement(8).getHeight(),
                           CFG.menuManager.getInGame().getMenuElement(8).getWidth()
                        );
                        TutorialManager.this.drawLineVertical(
                           oSB,
                           CFG.menuManager.getInGame_Civ_Info_Diplomacy().getPosX() + CFG.menuManager.getInGame_Civ_Info_Diplomacy().getWidth() + CFG.PADDING,
                           CFG.menuManager.getInGame_Civ_Info_Diplomacy().getPosY(),
                           CFG.menuManager.getInGame_Civ_Info_Diplomacy().getHeight()
                        );
                        TutorialManager.this.drawLineVertical(
                           oSB,
                           CFG.menuManager.getInGame_Civ_Info().getPosX() + CFG.menuManager.getInGame_Civ_Info().getWidth() + CFG.PADDING,
                           CFG.menuManager.getInGame_Civ_Info().getPosY(),
                           CFG.menuManager.getInGame_Civ_Info().getHeight()
                        );
                        TutorialManager.this.drawLineVertical(
                           oSB,
                           CFG.menuManager.getInGame_Civ_Info_Actions().getPosX() + CFG.menuManager.getInGame_Civ_Info_Actions().getWidth() + CFG.PADDING,
                           CFG.menuManager.getInGame_Civ_Info_Actions().getPosY(),
                           CFG.menuManager.getInGame_Civ_Info_Actions().getHeight()
                        );

                        for(int i = 0; i < TutorialManager.this.tutBoxes.get(TutorialManager.this.INNER_STEP).size(); ++i) {
                           TutorialManager.this.tutBoxes.get(TutorialManager.this.INNER_STEP).get(i).draw(oSB);
                        }

                        if (!CFG.menuManager.getVisible_InGame_CivInfo()) {
                           ++TutorialManager.this.STEP_ID;
                           TutorialManager.this.updateDrawTutorial(TutorialManager.this.IN_TUTORIAL);
                        }
                     } catch (IndexOutOfBoundsException var5) {
                     } catch (NullPointerException var6) {
                     }
                  }
               }

               @Override
               public boolean action(Tutorial_ActionType actionType) {
                  if (actionType == Tutorial_ActionType.NEXT_TURN && CFG.gameAction.getActiveTurnState() == Game_Action.TurnStates.INPUT_ORDERS) {
                     CFG.toast.setInView(CFG.langManager.get("d0a"), CFG.COLOR_TEXT_NUM_OF_PROVINCES);
                     return true;
                  } else {
                     return false;
                  }
               }
            };
         } else if (this.STEP_ID == 19) {
            this.iDrawPosX = CFG.PADDING * 2 + AoCGame.LEFT;
            this.iDrawPosY = CFG.menuManager.getInGame().getPosY()
               + CFG.menuManager.getInGame().getMenuElement(7).getPosY()
               + CFG.menuManager.getInGame().getMenuElement(7).getHeight()
               + CFG.PADDING / 2
               + CFG.PADDING
               + CFG.TEXT_HEIGHT
               + CFG.PADDING * 2
               + CFG.PADDING;
            this.textBox.clear();
            this.tutBoxes.clear();
            List<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
            List<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("q0"), CFG.COLOR_TEXT_NUM_OF_PROVINCES));
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
            nData.add(new MenuElement_Hover_v2_Element_Type_Text("1. ", CFG.COLOR_TEXT_NUM_OF_PROVINCES));
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("EndTurn"), COLOR_TITLE));
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
            this.textBox.add(new MenuElement_Hover_v2(nElements));
            List<TutorialBox> tempBoxes = new ArrayList<>();
            tempBoxes.add(
               new TutorialBox(CFG.langManager.get("Next")) {
                  @Override
                  protected int getPosX() {
                     return CFG.menuManager.getInGame_ProvinceInfo().getPosX() + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(0).getPosX();
                  }
   
                  @Override
                  protected int getPosY() {
                     return CFG.menuManager.getInGame_ProvinceInfo().getPosY()
                        + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(0).getPosY()
                        - CFG.PADDING / 2
                        - CFG.PADDING
                        - this.getHeight();
                  }
               }
            );
            this.tutBoxes.add(tempBoxes);
            this.tutStep = new TutorialManager.TutStep() {
               @Override
               public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY) {
                  if (CFG.menuManager.getInGameView()) {
                     try {
                        TutorialManager.this.textBox
                           .get(0)
                           .draw_HoverWithoutAnimation(
                              oSB,
                              !CFG.menuManager.getInGame_ProvinceBuild_Visible() && !CFG.menuManager.getInGame_CivInfo().getVisible()
                                 ? TutorialManager.this.iDrawPosX
                                 : CFG.CIV_INFO_MENU_WIDTH + CFG.PADDING * 2,
                              TutorialManager.this.iDrawPosY
                           );
                        int tElemID = 0;
                        TutorialManager.this.drawLine(
                           oSB,
                           CFG.menuManager.getInGame_ProvinceInfo().getPosX() + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(tElemID).getPosX(),
                           -CFG.PADDING / 2
                              + CFG.menuManager.getInGame_ProvinceInfo().getPosY()
                              + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(tElemID).getPosY(),
                           CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(tElemID).getWidth()
                        );

                        for(int i = 0; i < TutorialManager.this.tutBoxes.get(TutorialManager.this.INNER_STEP).size(); ++i) {
                           TutorialManager.this.tutBoxes.get(TutorialManager.this.INNER_STEP).get(i).draw(oSB);
                        }
                     } catch (IndexOutOfBoundsException var6) {
                     } catch (NullPointerException var7) {
                     }
                  }
               }

               @Override
               public boolean action(Tutorial_ActionType actionType) {
                  if (actionType == Tutorial_ActionType.NEXT_TURN && CFG.gameAction.getActiveTurnState() == Game_Action.TurnStates.INPUT_ORDERS) {
                     ++TutorialManager.this.STEP_ID;
                     TutorialManager.this.updateDrawTutorial(TutorialManager.this.IN_TUTORIAL);
                     return false;
                  } else {
                     return false;
                  }
               }
            };
         } else if (this.STEP_ID == 20) {
            this.iDrawPosX = CFG.PADDING * 2 + AoCGame.LEFT;
            this.iDrawPosY = CFG.menuManager.getInGame().getPosY()
               + CFG.menuManager.getInGame().getMenuElement(7).getPosY()
               + CFG.menuManager.getInGame().getMenuElement(7).getHeight()
               + CFG.PADDING / 2
               + CFG.PADDING
               + CFG.TEXT_HEIGHT
               + CFG.PADDING * 2
               + CFG.PADDING;
            this.textBox.clear();
            this.tutBoxes.clear();
            List<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
            List<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("EndOfTutorial"), CFG.COLOR_TEXT_NUM_OF_PROVINCES));
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
            nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("GoodLuck"), COLOR_TITLE));
            nElements.add(new MenuElement_Hover_v2_Element2(nData));
            nData.clear();
            this.textBox.add(new MenuElement_Hover_v2(nElements));
            List<TutorialBox> tempBoxes = new ArrayList<>();
            tempBoxes.add(
               new TutorialBox(CFG.langManager.get("Finish")) {
                  @Override
                  protected int getPosX() {
                     return CFG.menuManager.getInGame_ProvinceInfo().getPosX() + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(0).getPosX();
                  }
   
                  @Override
                  protected int getPosY() {
                     return CFG.menuManager.getInGame_ProvinceInfo().getPosY()
                        + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(0).getPosY()
                        - CFG.PADDING / 2
                        - CFG.PADDING
                        - this.getHeight();
                  }
               }
            );
            this.tutBoxes.add(tempBoxes);
            this.tutStep = new TutorialManager.TutStep() {
               @Override
               public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY) {
                  if (CFG.menuManager.getInGameView() && CFG.gameAction.getActiveTurnState() == Game_Action.TurnStates.INPUT_ORDERS) {
                     try {
                        TutorialManager.this.textBox
                           .get(0)
                           .draw_HoverWithoutAnimation(
                              oSB,
                              !CFG.menuManager.getInGame_ProvinceBuild_Visible() && !CFG.menuManager.getInGame_CivInfo().getVisible()
                                 ? TutorialManager.this.iDrawPosX
                                 : CFG.CIV_INFO_MENU_WIDTH + CFG.PADDING * 2,
                              TutorialManager.this.iDrawPosY
                           );
                        int tElemID = 0;
                        TutorialManager.this.drawLine(
                           oSB,
                           CFG.menuManager.getInGame_ProvinceInfo().getPosX() + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(tElemID).getPosX(),
                           -CFG.PADDING / 2
                              + CFG.menuManager.getInGame_ProvinceInfo().getPosY()
                              + CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(tElemID).getPosY(),
                           CFG.menuManager.getInGame_ProvinceInfo().getMenuElement(tElemID).getWidth()
                        );

                        for(int i = 0; i < TutorialManager.this.tutBoxes.get(TutorialManager.this.INNER_STEP).size(); ++i) {
                           TutorialManager.this.tutBoxes.get(TutorialManager.this.INNER_STEP).get(i).draw(oSB);
                        }
                     } catch (IndexOutOfBoundsException var6) {
                     } catch (NullPointerException var7) {
                     }
                  }
               }

               @Override
               public boolean action(Tutorial_ActionType actionType) {
                  if (actionType == Tutorial_ActionType.NEXT_TURN && CFG.gameAction.getActiveTurnState() == Game_Action.TurnStates.INPUT_ORDERS) {
                     ++TutorialManager.this.STEP_ID;
                     TutorialManager.this.updateDrawTutorial(TutorialManager.this.IN_TUTORIAL);
                     CFG.menuManager.setViewID(Menu.eGAMES);
                     TutorialManager.this.IN_TUTORIAL = false;
                     SaveManager.gameCanBeContinued = false;
                     return true;
                  } else {
                     return false;
                  }
               }
            };
         } else {
            this.tutStep = new TutorialManager.TutStep() {
               @Override
               public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY) {
               }

               @Override
               public boolean action(Tutorial_ActionType actionType) {
                  return false;
               }
            };
         }
      } else {
         this.tutStep = new TutorialManager.TutStep() {
            @Override
            public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY) {
            }

            @Override
            public boolean action(Tutorial_ActionType actionType) {
               return false;
            }
         };
      }
   }

   protected final void drawLine(SpriteBatch oSB, int nPosX, int nPosY, int nWidth) {
      oSB.setColor(new Color(1.0F, 1.0F, 1.0F, 0.325F));
      ImageManager.getImage(Images.line_33).draw2(oSB, nPosX, nPosY - ImageManager.getImage(Images.line_33).getHeight(), nWidth, 1);
      oSB.setColor(new Color(1.0F, 1.0F, 1.0F, 0.95F));
      ImageManager.getImage(Images.line_32_off1).draw(oSB, nPosX, nPosY - ImageManager.getImage(Images.line_32_off1).getHeight(), nWidth, 1);
      oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.685F));
      ImageManager.getImage(Images.line_32_off1)
         .draw(oSB, nPosX + nWidth / 4, nPosY - 1 - ImageManager.getImage(Images.line_32_off1).getHeight(), nWidth / 2, 1);
      ImageManager.getImage(Images.line_32_off1)
         .draw(oSB, nPosX + nWidth / 4, nPosY + 1 - ImageManager.getImage(Images.line_32_off1).getHeight(), nWidth / 2, 1);
      oSB.setColor(Color.WHITE);
   }

   protected final void drawLineVertical(SpriteBatch oSB, int nPosX, int nPosY, int nHeight) {
      oSB.setColor(new Color(1.0F, 1.0F, 1.0F, 0.325F));
      ImageManager.getImage(Images.line_32_vertical).draw2(oSB, nPosX, nPosY - ImageManager.getImage(Images.line_32_vertical).getHeight(), 1, nHeight);
      oSB.setColor(new Color(1.0F, 1.0F, 1.0F, 0.95F));
      ImageManager.getImage(Images.line_32_vertical).draw(oSB, nPosX, nPosY - ImageManager.getImage(Images.line_32_vertical).getHeight(), 1, nHeight);
      oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.685F));
      ImageManager.getImage(Images.line_32_vertical)
         .draw(oSB, nPosX - 1, nPosY + nHeight / 4 - ImageManager.getImage(Images.line_32_vertical).getHeight(), 1, nHeight / 2);
      ImageManager.getImage(Images.line_32_vertical)
         .draw(oSB, nPosX + 1, nPosY + nHeight / 4 - ImageManager.getImage(Images.line_32_vertical).getHeight(), 1, nHeight / 2);
      oSB.setColor(Color.WHITE);
   }

   protected List<String> getTextSplited(String nText) {
      List<String> out = new ArrayList<>();
      String[] tempLine = nText.split(" ");
      int i = 0;
      int currentW = 0;
      int iSize = tempLine.length;

      for(int last = 0; i < iSize; ++i) {
         CFG.glyphLayout.setText(CFG.fontMain, tempLine[i] + " ");
         currentW += (int)(CFG.glyphLayout.width * 0.75F);
         if (currentW >= CFG.GAME_WIDTH - this.iDrawPosX - CFG.PADDING * 8 || i == iSize - 1 && currentW < CFG.GAME_WIDTH - this.iDrawPosX - CFG.PADDING * 8) {
            String addLine = "";

            for(int j = last; j < (i == iSize - 1 && currentW < CFG.GAME_WIDTH - this.iDrawPosX - CFG.PADDING * 8 ? iSize : i); ++j) {
               addLine = addLine + tempLine[j] + " ";
            }

            out.add(addLine);
            last = i;
            if (currentW >= CFG.GAME_WIDTH - this.iDrawPosX - CFG.PADDING * 8 && i == iSize - 1) {
               out.add(tempLine[i]);
            }

            currentW = (int)(CFG.glyphLayout.width * 0.75F);
         }
      }

      return out;
   }

   interface TutStep {
      void draw(SpriteBatch var1, int var2, int var3);

      boolean action(Tutorial_ActionType var1);
   }
}
