package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;
import java.util.List;

class Button_Stats_Image_Population extends Button_Stats {
   private static final int NUM_OF_FLAGS = 2;
   private int iProvinceID = 0;
   private List<Integer> lSorted = new ArrayList<>();

   protected Button_Stats_Image_Population(
      String sText, float FONT_SCALE, int iPosX, int iPosY, int iMinWidth, int iHeight, boolean isClickable, boolean isVisible
   ) {
      super(sText, FONT_SCALE, iPosX, iPosY, iMinWidth, iHeight, isClickable, isVisible);
   }

   @Override
   protected void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
      ImageManager.getImage(Images.population)
         .draw(
            oSB,
            this.getPosX()
               + this.getTextPos()
               + (ImageManager.getImage(Images.economy).getWidth() - ImageManager.getImage(Images.population).getWidth()) / 2
               + iTranslateX,
            this.getPosY()
               + this.getHeight() / 2
               - ImageManager.getImage(Images.population).getHeight()
               - ImageManager.getImage(Images.population).getHeight() / 2
               + iTranslateY,
            ImageManager.getImage(Images.population).getWidth(),
            ImageManager.getImage(Images.population).getHeight()
         );
      CFG.fontMain.getData().setScale(this.FONT_SCALE);
      CFG.drawTextWithShadow(
         oSB,
         this.getTextToDraw(),
         this.getPosX() + this.getTextPos() + CFG.PADDING + ImageManager.getImage(Images.economy).getWidth() + iTranslateX,
         this.getPosY() + this.getHeight() / 2 - this.iTextHeight / 2 + iTranslateY,
         this.getColor(isActive)
      );
      CFG.fontMain.getData().setScale(1.0F);

      try {
         if (CFG.FOG_OF_WAR == 2) {
            for(int i = 0; i < Math.min(this.lSorted.size(), 2); ++i) {
               if (CFG.game
                  .getPlayer(CFG.PLAYER_TURNID)
                  .getMetCivilization(CFG.game.getProvince(this.iProvinceID).getPopulationData().getCivID(this.lSorted.get(i)))) {
                  CFG.game
                     .getCiv(CFG.game.getProvince(this.iProvinceID).getPopulationData().getCivID(this.lSorted.get(i)))
                     .getFlag()
                     .draw(
                        oSB,
                        this.getPosX()
                           + this.getTextPos()
                           + CFG.PADDING
                           + ImageManager.getImage(Images.economy).getWidth()
                           + iTranslateX
                           + this.getTextWidth()
                           + CFG.PADDING * (i + 1)
                           + (int)((float)CFG.CIV_FLAG_WIDTH * this.getImageScale()) * i,
                        this.getPosY()
                           - CFG.game.getCiv(CFG.game.getProvince(this.iProvinceID).getPopulationData().getCivID(this.lSorted.get(i))).getFlag().getHeight()
                           + this.getHeight() / 2
                           - (int)((float)CFG.CIV_FLAG_HEIGHT * this.getImageScale() / 2.0F)
                           + iTranslateY,
                        (int)((float)CFG.CIV_FLAG_WIDTH * this.getImageScale()),
                        (int)((float)CFG.CIV_FLAG_HEIGHT * this.getImageScale())
                     );
               } else {
                  ImageManager.getImage(Images.randomCivilizationFlag)
                     .draw(
                        oSB,
                        this.getPosX()
                           + this.getTextPos()
                           + CFG.PADDING
                           + ImageManager.getImage(Images.economy).getWidth()
                           + iTranslateX
                           + this.getTextWidth()
                           + CFG.PADDING * (i + 1)
                           + (int)((float)CFG.CIV_FLAG_WIDTH * this.getImageScale()) * i,
                        this.getPosY()
                           - ImageManager.getImage(Images.randomCivilizationFlag).getHeight()
                           + this.getHeight() / 2
                           - (int)((float)CFG.CIV_FLAG_HEIGHT * this.getImageScale() / 2.0F)
                           + iTranslateY,
                        (int)((float)CFG.CIV_FLAG_WIDTH * this.getImageScale()),
                        (int)((float)CFG.CIV_FLAG_HEIGHT * this.getImageScale())
                     );
               }

               ImageManager.getImage(Images.flag_rect)
                  .draw(
                     oSB,
                     this.getPosX()
                        + this.getTextPos()
                        + CFG.PADDING
                        + ImageManager.getImage(Images.economy).getWidth()
                        + iTranslateX
                        + this.getTextWidth()
                        + CFG.PADDING * (i + 1)
                        + (int)((float)CFG.CIV_FLAG_WIDTH * this.getImageScale()) * i,
                     this.getPosY()
                        - CFG.CIV_FLAG_HEIGHT
                        + this.getHeight() / 2
                        - (int)((float)CFG.CIV_FLAG_HEIGHT * this.getImageScale() / 2.0F)
                        + iTranslateY,
                     (int)((float)CFG.CIV_FLAG_WIDTH * this.getImageScale()),
                     (int)((float)CFG.CIV_FLAG_HEIGHT * this.getImageScale())
                  );
            }
         } else {
            for(int i = 0; i < Math.min(this.lSorted.size(), 2); ++i) {
               CFG.game
                  .getCiv(CFG.game.getProvince(this.iProvinceID).getPopulationData().getCivID(this.lSorted.get(i)))
                  .getFlag()
                  .draw(
                     oSB,
                     this.getPosX()
                        + this.getTextPos()
                        + CFG.PADDING
                        + ImageManager.getImage(Images.economy).getWidth()
                        + iTranslateX
                        + this.getTextWidth()
                        + CFG.PADDING * (i + 1)
                        + (int)((float)CFG.CIV_FLAG_WIDTH * this.getImageScale()) * i,
                     this.getPosY()
                        - CFG.game.getCiv(CFG.game.getProvince(this.iProvinceID).getPopulationData().getCivID(this.lSorted.get(i))).getFlag().getHeight()
                        + this.getHeight() / 2
                        - (int)((float)CFG.CIV_FLAG_HEIGHT * this.getImageScale() / 2.0F)
                        + iTranslateY,
                     (int)((float)CFG.CIV_FLAG_WIDTH * this.getImageScale()),
                     (int)((float)CFG.CIV_FLAG_HEIGHT * this.getImageScale())
                  );
               ImageManager.getImage(Images.flag_rect)
                  .draw(
                     oSB,
                     this.getPosX()
                        + this.getTextPos()
                        + CFG.PADDING
                        + ImageManager.getImage(Images.economy).getWidth()
                        + iTranslateX
                        + this.getTextWidth()
                        + CFG.PADDING * (i + 1)
                        + (int)((float)CFG.CIV_FLAG_WIDTH * this.getImageScale()) * i,
                     this.getPosY()
                        - CFG.CIV_FLAG_HEIGHT
                        + this.getHeight() / 2
                        - (int)((float)CFG.CIV_FLAG_HEIGHT * this.getImageScale() / 2.0F)
                        + iTranslateY,
                     (int)((float)CFG.CIV_FLAG_WIDTH * this.getImageScale()),
                     (int)((float)CFG.CIV_FLAG_HEIGHT * this.getImageScale())
                  );
            }
         }
      } catch (IndexOutOfBoundsException var6) {
         this.setCurrent(this.getCurrent());
      }
   }

   @Override
   public void setText(String sText) {
      this.sText = sText;
      this.setWidth(this.iMinWidth);

      try {
         CFG.glyphLayout.setText(CFG.fontMain, sText);
         this.iTextWidth = (int)(CFG.glyphLayout.width * this.FONT_SCALE);
         this.iTextHeight = (int)(CFG.glyphLayout.height * this.FONT_SCALE);
         if (super.getWidth()
            < this.iTextWidth
               + CFG.PADDING * 2
               + ImageManager.getImage(Images.economy).getWidth()
               + (int)((float)CFG.CIV_FLAG_WIDTH * this.getImageScale() + (float)CFG.PADDING) * Math.min(this.lSorted.size(), 2)) {
            this.setWidth(
               this.iTextWidth
                  + CFG.PADDING * 2
                  + ImageManager.getImage(Images.economy).getWidth()
                  + (int)((float)CFG.CIV_FLAG_WIDTH * this.getImageScale() + (float)CFG.PADDING) * Math.min(this.lSorted.size(), 2)
            );
         }
      } catch (NullPointerException var3) {
      } catch (IllegalArgumentException var4) {
      }
   }

   private final float getImageScale() {
      return (float)CFG.TEXT_HEIGHT / (float)CFG.CIV_FLAG_HEIGHT;
   }

   @Override
   protected int getCurrent() {
      return this.iProvinceID;
   }

   @Override
   protected void setCurrent(int nCurrent) {
      this.iProvinceID = nCurrent;
      this.lSorted.clear();
      List<Integer> tempIDs = new ArrayList<>();

      for(int i = 0; i < CFG.game.getProvince(this.iProvinceID).getPopulationData().getNationalitiesSize(); ++i) {
         tempIDs.add(i);
      }

      while(tempIDs.size() > 0) {
         int tIDAdd = 0;

         for(int i = 1; i < tempIDs.size(); ++i) {
            if (CFG.game.getProvince(this.iProvinceID).getPopulationData().getPopulationID(tempIDs.get(tIDAdd))
               < CFG.game.getProvince(this.iProvinceID).getPopulationData().getPopulationID(tempIDs.get(i))) {
               tIDAdd = i;
            }
         }

         this.lSorted.add(tempIDs.get(tIDAdd));
         tempIDs.remove(tIDAdd);
      }
   }
}
