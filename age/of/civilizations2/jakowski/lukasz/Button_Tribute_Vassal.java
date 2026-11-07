package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

class Button_Tribute_Vassal extends Button_Build {
   private int iIdelogyID;
   private int iCivID;

   protected Button_Tribute_Vassal(String sText, int nIdelogyID, int iPosX, int iPosY, int nCivID) {
      super(sText, Images.diplo_vassal, 0, 0, iPosX, iPosY, Button_Diplomacy.iDiploWidth, true, false, 0, 0.0F);
      this.setHeight(CFG.TEXT_HEIGHT + CFG.PADDING * 2 + CFG.PADDING * 4);
      this.iIdelogyID = nIdelogyID;
      this.iCivID = nCivID;
   }

   @Override
   protected void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
      try {
         CFG.ideologiesManager
            .getIdeology(this.iIdelogyID)
            .getCrownImageScaled()
            .draw(
               oSB,
               this.getPosX()
                  + Button_Diplomacy.iDiploWidth / 2
                  - CFG.ideologiesManager.getIdeology(this.iIdelogyID).getCrownImageScaled().getWidth() / 2
                  + iTranslateX,
               this.getPosY() + this.getHeight() / 2 - CFG.ideologiesManager.getIdeology(this.iIdelogyID).getCrownImageScaled().getHeight() / 2 + iTranslateY
            );
      } catch (IndexOutOfBoundsException var6) {
         ImageManager.getImage(this.iImageID)
            .draw(
               oSB,
               this.getPosX() + Button_Diplomacy.iDiploWidth / 2 - ImageManager.getImage(this.iImageID).getWidth() / 2 + iTranslateX,
               this.getPosY() + this.getHeight() / 2 - ImageManager.getImage(this.iImageID).getHeight() / 2 + iTranslateY
            );
      }
   }

   @Override
   protected void buildElementHover() {
      this.menuElementHover = CFG.ideologiesManager.getIdeologyHover_Just(this.iIdelogyID);
   }

   @Override
   protected void actionElement(int iID) {
      if (CFG.game.getCiv(this.iCivID).getCapitalProvinceID() >= 0) {
         CFG.map.getMapCoordinates().centerToProvinceID(CFG.game.getCiv(this.iCivID).getCapitalProvinceID());
         CFG.toast.setInView(CFG.game.getProvince(CFG.game.getCiv(this.iCivID).getCapitalProvinceID()).getName(), CFG.COLOR_TEXT_NUM_OF_PROVINCES);
      }
   }
}
