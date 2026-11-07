package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.io.Serializable;

class HistoryLog implements Serializable {
   private static final long serialVersionUID = 0L;
   protected static final float FONT_SCALE = 0.7F;
   protected static int ICON_WIDTH = 0;
   protected HistoryLog_Types historyLog_Type = HistoryLog_Types.WAR_DECLARAION;
   protected int iCivA;
   protected int iCivB;

   protected void updateLanguage() {
   }

   protected void draw(SpriteBatch oSB, int nTurnID, int iPosX, int iPosY, int iWidth, int iHeight, boolean isActive) {
      CFG.fontMain.getData().setScale(0.7F);
      CFG.drawTextWithShadow(
         oSB,
         (String)HistoryManager.lHistoryDates.get(nTurnID) + ": ",
         iPosX + ICON_WIDTH + CFG.PADDING,
         iPosY + (int)(((float)iHeight - (float)CFG.TEXT_HEIGHT * 0.7F) / 2.0F),
         CFG.COLOR_TEXT_RANK
      );
      CFG.fontMain.getData().setScale(1.0F);
   }

   protected final void drawLeftIconBG(SpriteBatch oSB, int iPosX, int iPosY, int iWidth, int iHeight, boolean isActive) {
      oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.375F));
      ImageManager.getImage(Images.slider_gradient)
         .draw(
            oSB, iPosX + ICON_WIDTH - ICON_WIDTH / 2, iPosY - ImageManager.getImage(Images.slider_gradient).getHeight(), ICON_WIDTH / 2, iHeight, true, false
         );
      oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.225F));
      ImageManager.getImage(Images.slider_gradient)
         .draw(oSB, iPosX + ICON_WIDTH, iPosY - ImageManager.getImage(Images.slider_gradient).getHeight(), CFG.PADDING, iHeight);
      oSB.setColor(new Color(CFG.COLOR_GRADIENT_TITLE_BLUE.r, CFG.COLOR_GRADIENT_TITLE_BLUE.g, CFG.COLOR_GRADIENT_TITLE_BLUE.b, 0.7F));
      ImageManager.getImage(Images.line_32_vertical)
         .draw(oSB, iPosX + ICON_WIDTH - 1, iPosY - ImageManager.getImage(Images.line_32_vertical).getHeight(), 1, iHeight, true, false);
      oSB.setColor(Color.WHITE);
   }

   protected final void drawLeftIcon(SpriteBatch oSB, int nImageID, int iPosX, int iPosY, int iWidth, int iHeight, boolean isActive) {
      this.drawLeftIconBG(oSB, iPosX, iPosY, iWidth, iHeight, isActive);
      ImageManager.getImage(nImageID)
         .draw(
            oSB,
            iPosX + (int)(((float)ICON_WIDTH - (float)ImageManager.getImage(nImageID).getWidth() * getImageScale(nImageID)) / 2.0F),
            iPosY
               + (int)(((float)iHeight - (float)ImageManager.getImage(nImageID).getHeight() * getImageScale(nImageID)) / 2.0F)
               - ImageManager.getImage(nImageID).getHeight(),
            (int)((float)ImageManager.getImage(nImageID).getWidth() * getImageScale(nImageID)),
            (int)((float)ImageManager.getImage(nImageID).getHeight() * getImageScale(nImageID))
         );
   }

   protected static final float getImageScale(int nImageID) {
      return (float)CFG.TEXT_HEIGHT * 0.7F / (float)ImageManager.getImage(nImageID).getHeight() < 1.0F
         ? (float)CFG.TEXT_HEIGHT * 0.7F / (float)ImageManager.getImage(nImageID).getHeight()
         : 1.0F;
   }

   protected static final float getImageScale_CrownVassal(int nIdelogyID) {
      return (float)CFG.TEXT_HEIGHT * 0.7F / (float)CFG.ideologiesManager.getIdeology(nIdelogyID).getiCrownVassalImage().getHeight() < 1.0F
         ? (float)CFG.TEXT_HEIGHT * 0.7F / (float)CFG.ideologiesManager.getIdeology(nIdelogyID).getiCrownVassalImage().getHeight()
         : 1.0F;
   }

   protected String getName() {
      return "";
   }
}
