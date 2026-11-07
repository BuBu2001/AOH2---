package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

class Button_Flag extends MenuElement {
   private int iCivID;
   private Button_Flag.DrawButton drawButton;

   protected Button_Flag(int nCivID, int nPosX, int nPosY, int nWidth, int nHeight, Button_Flag.ButtonFlagType buttonFlagType) {
      this.typeOfElement = MenuElement.TypeOfElement.BUTTON_FLAG;
      this.iCivID = nCivID;
      this.setPosX(nPosX);
      this.setPosY(nPosY);
      this.setWidth(nWidth);
      this.setHeight(nHeight);
      switch(buttonFlagType) {
         case FLAG_COLOR:
            if (this.iCivID >= 0) {
               this.drawButton = new Button_Flag.DrawButton() {
                  @Override
                  public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
                     oSB.setColor(
                        new Color(
                           (float)CFG.game.getCiv(Button_Flag.this.iCivID).getR() / 255.0F,
                           (float)CFG.game.getCiv(Button_Flag.this.iCivID).getG() / 255.0F,
                           (float)CFG.game.getCiv(Button_Flag.this.iCivID).getB() / 255.0F,
                           1.0F
                        )
                     );
                     ImageManager.getImage(Images.pix255_255_255)
                        .draw(
                           oSB,
                           Button_Flag.this.getPosX() + iTranslateX,
                           Button_Flag.this.getPosY() + iTranslateY - 1,
                           (int)((float)CFG.CIV_COLOR_WIDTH * CFG.GUI_SCALE),
                           Button_Flag.this.getHeight()
                        );
                     oSB.setColor(Color.WHITE);
                     CFG.game
                        .getCiv(Button_Flag.this.iCivID)
                        .getFlag()
                        .draw(
                           oSB,
                           Button_Flag.this.getPosX() + (int)((float)CFG.CIV_COLOR_WIDTH * CFG.GUI_SCALE) + CFG.PADDING * 2 + iTranslateX,
                           Button_Flag.this.getPosY()
                              - CFG.game.getCiv(Button_Flag.this.iCivID).getFlag().getHeight()
                              + iTranslateY
                              + Button_Flag.this.getHeight() / 2
                              - CFG.CIV_FLAG_HEIGHT / 2,
                           CFG.CIV_FLAG_WIDTH,
                           CFG.CIV_FLAG_HEIGHT
                        );
                     ImageManager.getImage(Images.flag_rect)
                        .draw(
                           oSB,
                           Button_Flag.this.getPosX() + (int)((float)CFG.CIV_COLOR_WIDTH * CFG.GUI_SCALE) + CFG.PADDING * 2 + iTranslateX,
                           Button_Flag.this.getPosY() + iTranslateY + Button_Flag.this.getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2
                        );
                  }
               };
            } else {
               this.drawButton = new Button_Flag.DrawButton() {
                  @Override
                  public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
                     oSB.setColor(CFG.RANDOM_CIVILIZATION_COLOR);
                     ImageManager.getImage(Images.pix255_255_255)
                        .draw(
                           oSB,
                           Button_Flag.this.getPosX() + iTranslateX,
                           Button_Flag.this.getPosY() + iTranslateY - 1,
                           (int)((float)CFG.CIV_COLOR_WIDTH * CFG.GUI_SCALE),
                           Button_Flag.this.getHeight()
                        );
                     oSB.setColor(Color.WHITE);
                     ImageManager.getImage(Images.randomCivilizationFlag)
                        .draw(
                           oSB,
                           Button_Flag.this.getPosX() + (int)((float)CFG.CIV_COLOR_WIDTH * CFG.GUI_SCALE) + CFG.PADDING * 2 + iTranslateX,
                           Button_Flag.this.getPosY()
                              - ImageManager.getImage(Images.randomCivilizationFlag).getHeight()
                              + iTranslateY
                              + Button_Flag.this.getHeight() / 2
                              - CFG.CIV_FLAG_HEIGHT / 2,
                           CFG.CIV_FLAG_WIDTH,
                           CFG.CIV_FLAG_HEIGHT
                        );
                     ImageManager.getImage(Images.flag_rect)
                        .draw(
                           oSB,
                           Button_Flag.this.getPosX() + (int)((float)CFG.CIV_COLOR_WIDTH * CFG.GUI_SCALE) + CFG.PADDING * 2 + iTranslateX,
                           Button_Flag.this.getPosY() + iTranslateY + Button_Flag.this.getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2
                        );
                  }
               };
            }
            break;
         case FLAG:
            if (this.iCivID > 0) {
               this.drawButton = new Button_Flag.DrawButton() {
                  @Override
                  public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
                     CFG.game
                        .getCiv(Button_Flag.this.iCivID)
                        .getFlag()
                        .draw(
                           oSB,
                           Button_Flag.this.getPosX() + Button_Flag.this.getWidth() / 2 - CFG.CIV_FLAG_WIDTH / 2 + iTranslateX,
                           Button_Flag.this.getPosY()
                              - CFG.game.getCiv(Button_Flag.this.iCivID).getFlag().getHeight()
                              + iTranslateY
                              + Button_Flag.this.getHeight() / 2
                              - CFG.CIV_FLAG_HEIGHT / 2,
                           CFG.CIV_FLAG_WIDTH,
                           CFG.CIV_FLAG_HEIGHT
                        );
                     ImageManager.getImage(Images.flag_rect)
                        .draw(
                           oSB,
                           Button_Flag.this.getPosX() + Button_Flag.this.getWidth() / 2 - CFG.CIV_FLAG_WIDTH / 2 + iTranslateX,
                           Button_Flag.this.getPosY() + iTranslateY + Button_Flag.this.getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2
                        );
                  }
               };
            } else {
               this.drawButton = new Button_Flag.DrawButton() {
                  @Override
                  public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
                     ImageManager.getImage(Images.randomCivilizationFlag)
                        .draw(
                           oSB,
                           Button_Flag.this.getPosX() + Button_Flag.this.getWidth() / 2 - CFG.CIV_FLAG_WIDTH / 2 + iTranslateX,
                           Button_Flag.this.getPosY()
                              - ImageManager.getImage(Images.randomCivilizationFlag).getHeight()
                              + iTranslateY
                              + Button_Flag.this.getHeight() / 2
                              - CFG.CIV_FLAG_HEIGHT / 2,
                           CFG.CIV_FLAG_WIDTH,
                           CFG.CIV_FLAG_HEIGHT
                        );
                     ImageManager.getImage(Images.flag_rect)
                        .draw(
                           oSB,
                           Button_Flag.this.getPosX() + Button_Flag.this.getWidth() / 2 - CFG.CIV_FLAG_WIDTH / 2 + iTranslateX,
                           Button_Flag.this.getPosY() + iTranslateY + Button_Flag.this.getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2
                        );
                  }
               };
            }
      }
   }

   @Override
   protected final void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
      if (isActive) {
         ImageManager.getImage(Images.btnh_menu_1_h).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth());
      } else {
         ImageManager.getImage(Images.btn_menu_1_h).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth());
      }

      this.drawButton.draw(oSB, iTranslateX, iTranslateY, isActive, scrollableY);
   }

   public static enum ButtonFlagType {
      FLAG_COLOR,
      FLAG;
   }

   interface DrawButton {
      void draw(SpriteBatch var1, int var2, int var3, boolean var4, boolean var5);
   }
}
