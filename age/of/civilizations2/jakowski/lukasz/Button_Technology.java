package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;
import java.util.List;

class Button_Technology extends Button_Build {
   protected static final float FONTSIZE2 = 0.6F;
   protected String sProvinceName;
   protected String sDate;
   protected int iDateWidth;
   protected String sEconomy;
   protected int iEconomyWidth;
   protected String sPoints;
   protected int iPointsWidth;
   protected Color oColor = Color.WHITE;
   protected int iCivID;

   protected Button_Technology(int nCivID, int iPosX, int iPosY, int iWidth) {
      super(CFG.game.getCiv(nCivID).getCivName(), Images.technology, 0, 0, iPosX, iPosY, iWidth, true, false, 0, 0.0F);
      this.iCivID = nCivID;
      this.sDate = CFG.langManager.get("TechnologyLevel") + ": ";
      CFG.glyphLayout.setText(CFG.fontMain, this.sDate);
      this.iDateWidth = (int)(CFG.glyphLayout.width * 0.6F);
      this.sEconomy = "" + (float)((int)(CFG.game.getCiv(nCivID).getTechnologyLevel() * 100.0F)) / 100.0F;
      CFG.glyphLayout.setText(CFG.fontMain, this.sEconomy);
      this.iEconomyWidth = (int)(CFG.glyphLayout.width * 0.6F);
      this.setMin(CFG.game.getCiv(nCivID).civGameData.skills.getPointsLeft(nCivID));
   }

   @Override
   protected void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
      CFG.game
         .getCiv(this.iCivID)
         .getFlag()
         .draw(
            oSB,
            this.getPosX() + Button_Diplomacy.iDiploWidth / 2 - CFG.CIV_FLAG_WIDTH / 2 + iTranslateX,
            this.getPosY() + this.getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 - CFG.game.getCiv(this.iCivID).getFlag().getHeight() + iTranslateY,
            CFG.CIV_FLAG_WIDTH,
            CFG.CIV_FLAG_HEIGHT
         );
      ImageManager.getImage(Images.flag_rect)
         .draw(
            oSB,
            this.getPosX() + Button_Diplomacy.iDiploWidth / 2 - CFG.CIV_FLAG_WIDTH / 2 + iTranslateX,
            this.getPosY() + this.getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 - ImageManager.getImage(Images.flag_rect).getHeight() + iTranslateY,
            CFG.CIV_FLAG_WIDTH,
            CFG.CIV_FLAG_HEIGHT
         );
      CFG.fontMain.getData().setScale(0.7F);
      CFG.drawTextWithShadow(
         oSB,
         this.getText(),
         this.getPosX() + CFG.PADDING + Button_Diplomacy.iDiploWidth + iTranslateX,
         this.getPosY() + this.getHeight() / 2 - (int)((float)this.getTextHeight() * 0.7F) - CFG.PADDING / 2 + iTranslateY,
         this.getColor(isActive)
      );
      CFG.fontMain.getData().setScale(0.6F);
      CFG.drawTextWithShadow(
         oSB,
         this.sDate,
         this.getPosX() + CFG.PADDING + Button_Diplomacy.iDiploWidth + iTranslateX,
         this.getPosY() + this.getHeight() / 2 + CFG.PADDING / 2 + iTranslateY,
         CFG.COLOR_TEXT_OPTIONS_NS_HOVER
      );
      CFG.drawTextWithShadow(
         oSB,
         this.sEconomy,
         this.getPosX() + CFG.PADDING + Button_Diplomacy.iDiploWidth + this.iDateWidth + iTranslateX,
         this.getPosY() + this.getHeight() / 2 + CFG.PADDING / 2 + iTranslateY,
         CFG.COLOR_TEXT_TECHNOLOGY
      );
      ImageManager.getImage(Images.technology)
         .draw(
            oSB,
            this.getPosX() + CFG.PADDING * 2 + this.iEconomyWidth + Button_Diplomacy.iDiploWidth + this.iDateWidth + iTranslateX,
            this.getPosY() + this.getHeight() / 2 + CFG.PADDING / 2 - ImageManager.getImage(Images.technology).getHeight() + iTranslateY,
            (int)((float)ImageManager.getImage(Images.technology).getWidth() * this.getImageScale(Images.technology, 0.6F)),
            (int)((float)ImageManager.getImage(Images.technology).getHeight() * this.getImageScale(Images.technology, 0.6F))
         );
      CFG.fontMain.getData().setScale(1.0F);
      ImageManager.getImage(Images.technology)
         .draw(
            oSB,
            this.getPosX() + this.getWidth() - CFG.PADDING * 2 - ImageManager.getImage(Images.technology).getWidth() + iTranslateX,
            this.getPosY() + this.getHeight() / 2 - ImageManager.getImage(Images.technology).getHeight() / 2 + iTranslateY
         );
      CFG.drawTextWithShadow(
         oSB,
         this.sPoints,
         this.getPosX() + this.getWidth() - CFG.PADDING * 3 - ImageManager.getImage(Images.technology).getWidth() - this.iPointsWidth + iTranslateX,
         this.getPosY() + this.getHeight() / 2 - CFG.TEXT_HEIGHT / 2 + iTranslateY,
         this.oColor
      );
   }

   @Override
   protected void buildElementHover() {
      List<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
      List<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
      nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("PointsLeft") + ": "));
      nData.add(new MenuElement_Hover_v2_Element_Type_Text(this.sPoints, this.oColor));
      nData.add(
         new MenuElement_Hover_v2_Element_Type_Text("/" + (int)(CFG.game.getCiv(this.iCivID).getTechnologyLevel() * 100.0F), CFG.COLOR_TEXT_NUM_OF_PROVINCES)
      );
      nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.technology, CFG.PADDING, 0));
      nElements.add(new MenuElement_Hover_v2_Element2(nData));
      nData.clear();
      this.menuElementHover = new MenuElement_Hover_v2(nElements);
   }

   @Override
   protected void setMin(int iMin) {
      this.sPoints = "" + iMin;
      CFG.glyphLayout.setText(CFG.fontMain, this.sPoints);
      this.iPointsWidth = (int)CFG.glyphLayout.width;
      this.oColor = iMin > 0 ? CFG.COLOR_TEXT_NUM_OF_PROVINCES : CFG.COLOR_TEXT_MODIFIER_NEUTRAL;
   }
}
