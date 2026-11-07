package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

class Button_PrepareForWar extends Button_Build {
   protected static final float FONTSIZE2 = 0.6F;
   protected int warAgainstCivID;
   protected int iFromCivID;
   protected String sFromCivName;
   protected int iFromCivNameWidth;
   protected String sProvinceName;
   protected int iProvinceNameWidth;
   protected String sDate;
   protected int iDateWidth;
   protected String sEconomy;
   protected int iEconomyWidth;
   protected Color oColor = Color.WHITE;

   protected Button_PrepareForWar(String nText, int iFromCivID, int warAgainstCivID, int numOfTurns, int iPosX, int iPosY, int iWidth) {
      super(nText, Images.diplo_war_preparations, 0, 0, iPosX, iPosY, iWidth, true, false, 0, 0.0F);
      this.warAgainstCivID = warAgainstCivID;
      this.iFromCivID = iFromCivID;
      this.sFromCivName = CFG.game.getCiv(iFromCivID).getCivName();
      CFG.glyphLayout.setText(CFG.fontMain, this.sFromCivName);
      this.iFromCivNameWidth = (int)(CFG.glyphLayout.width * 0.8F);
      this.sProvinceName = CFG.game.getCiv(warAgainstCivID).getCivName();
      CFG.glyphLayout.setText(CFG.fontMain, this.sProvinceName);
      this.iProvinceNameWidth = (int)(CFG.glyphLayout.width * 0.7F);
      this.sDate = CFG.langManager.get("PreparationTime") + ": ";
      CFG.glyphLayout.setText(CFG.fontMain, this.sDate);
      this.iDateWidth = (int)(CFG.glyphLayout.width * 0.6F);
      this.sEconomy = CFG.langManager.get("TurnsX", numOfTurns);
      CFG.glyphLayout.setText(CFG.fontMain, this.sEconomy);
      this.iEconomyWidth = (int)(CFG.glyphLayout.width * 0.6F);
   }

   @Override
   protected void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
      ImageManager.getImage(this.iImageID)
         .draw(
            oSB,
            this.getPosX() + Button_Diplomacy.iDiploWidth / 2 - ImageManager.getImage(this.iImageID).getWidth() / 2 + iTranslateX,
            this.getPosY() + this.getHeight() / 2 - ImageManager.getImage(this.iImageID).getHeight() / 2 + iTranslateY
         );
      CFG.game
         .getCiv(this.iFromCivID)
         .getFlag()
         .draw(
            oSB,
            this.getPosX()
               + this.getWidth()
               - CFG.PADDING * 2
               - (int)((float)ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(Images.flag_rect, 1.0F))
               + iTranslateX,
            this.getPosY()
               + this.getHeight() / 2
               - CFG.game.getCiv(this.iFromCivID).getFlag().getHeight()
               - (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect, 1.0F)) / 2
               + iTranslateY,
            (int)((float)ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(Images.flag_rect, 1.0F)),
            (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect, 1.0F))
         );
      ImageManager.getImage(Images.flag_rect)
         .draw(
            oSB,
            this.getPosX()
               + this.getWidth()
               - CFG.PADDING * 2
               - (int)((float)ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(Images.flag_rect, 1.0F))
               + iTranslateX,
            this.getPosY()
               + this.getHeight() / 2
               - ImageManager.getImage(Images.flag_rect).getHeight()
               - (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect, 1.0F)) / 2
               + iTranslateY,
            (int)((float)ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(Images.flag_rect, 1.0F)),
            (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect, 1.0F))
         );
      CFG.fontMain.getData().setScale(0.8F);
      CFG.drawTextWithShadow(
         oSB,
         this.sFromCivName,
         this.getPosX()
            + this.getWidth()
            - CFG.PADDING * 3
            - this.iFromCivNameWidth
            - (int)((float)ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(Images.flag_rect, 1.0F))
            + iTranslateX,
         this.getPosY() + this.getHeight() / 2 - (int)((float)this.getTextHeight() * 0.8F) / 2 + iTranslateY,
         this.getColor(isActive)
      );
      CFG.game
         .getCiv(this.warAgainstCivID)
         .getFlag()
         .draw(
            oSB,
            this.getPosX() + CFG.PADDING * 2 + this.iProvinceNameWidth + Button_Diplomacy.iDiploWidth + (int)((float)this.getTextWidth() * 0.7F) + iTranslateX,
            this.getPosY()
               + this.getHeight() / 2
               - (int)((float)this.getTextHeight() * 0.7F) / 2
               - CFG.PADDING / 2
               - CFG.game.getCiv(this.warAgainstCivID).getFlag().getHeight()
               - (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect, 0.7F)) / 2
               + iTranslateY,
            (int)((float)ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(Images.flag_rect, 0.7F)),
            (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect, 0.7F))
         );
      ImageManager.getImage(Images.flag_rect)
         .draw(
            oSB,
            this.getPosX() + CFG.PADDING * 2 + this.iProvinceNameWidth + Button_Diplomacy.iDiploWidth + (int)((float)this.getTextWidth() * 0.7F) + iTranslateX,
            this.getPosY()
               + this.getHeight() / 2
               - (int)((float)this.getTextHeight() * 0.7F) / 2
               - CFG.PADDING / 2
               - ImageManager.getImage(Images.flag_rect).getHeight()
               - (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect, 0.7F)) / 2
               + iTranslateY,
            (int)((float)ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(Images.flag_rect, 0.7F)),
            (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect, 0.7F))
         );
      ImageManager.getImage(Images.time)
         .draw(
            oSB,
            this.getPosX() + CFG.PADDING * 2 + this.iEconomyWidth + Button_Diplomacy.iDiploWidth + this.iDateWidth + iTranslateX,
            this.getPosY() + this.getHeight() / 2 + CFG.PADDING / 2 - ImageManager.getImage(Images.time).getHeight() + iTranslateY,
            (int)((float)ImageManager.getImage(Images.time).getWidth() * this.getImageScale(Images.time, 0.6F)),
            (int)((float)ImageManager.getImage(Images.time).getHeight() * this.getImageScale(Images.time, 0.6F))
         );
      CFG.fontMain.getData().setScale(0.7F);
      CFG.drawTextWithShadow(
         oSB,
         this.getText(),
         this.getPosX() + CFG.PADDING + Button_Diplomacy.iDiploWidth + iTranslateX,
         this.getPosY() + this.getHeight() / 2 - (int)((float)this.getTextHeight() * 0.7F) - CFG.PADDING / 2 + iTranslateY,
         this.getColor(isActive)
      );
      CFG.drawTextWithShadow(
         oSB,
         this.sProvinceName,
         this.getPosX() + CFG.PADDING + Button_Diplomacy.iDiploWidth + (int)((float)this.getTextWidth() * 0.7F) + iTranslateX,
         this.getPosY() + this.getHeight() / 2 - (int)((float)this.getTextHeight() * 0.7F) - CFG.PADDING / 2 + iTranslateY,
         CFG.COLOR_TEXT_NUM_OF_PROVINCES
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
         this.oColor
      );
      CFG.fontMain.getData().setScale(1.0F);
   }
}
