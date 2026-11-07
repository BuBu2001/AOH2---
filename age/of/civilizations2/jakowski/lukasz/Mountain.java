package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.io.Serializable;

class Mountain implements Serializable {
   private static final long serialVersionUID = 0L;
   private String sName = null;
   private int iPosX;
   private int iPosY;
   private int iElevation;

   protected Mountain(String sName, int iElevation, int nPosX, int nPosY) {
      this.sName = sName;
      this.iElevation = iElevation;
      this.iPosX = nPosX;
      this.iPosY = nPosY;
   }

   protected final void draw(SpriteBatch oSB, int nProvinceID, float nScale) {
      this.draw(oSB, nProvinceID, nScale, new Color(1.0F, 1.0F, 1.0F, 0.85F), Images.mount);
   }

   protected final void draw(SpriteBatch oSB, int nProvinceID, float nScale, int nImageID) {
      this.draw(oSB, nProvinceID, nScale, new Color(1.0F, 1.0F, 1.0F, 0.85F), nImageID);
   }

   protected final void draw(SpriteBatch oSB, int nProvinceID, float nScale, Color nColor) {
      this.draw(oSB, nProvinceID, nScale, nColor, Images.mount);
   }

   protected final void draw(SpriteBatch oSB, int nProvinceID, float nScale, Color nColor, int nImageID) {
      ImageManager.getImage(nImageID)
         .draw(
            oSB,
            (int)(
               (float)(this.getPosX() * CFG.map.getMapBG().getMapScale() + CFG.game.getProvince(nProvinceID).getTranslateProvincePosX()) * nScale
                  - (float)(ImageManager.getImage(nImageID).getWidth() / 2)
            ),
            (int)((float)(this.getPosY() * CFG.map.getMapBG().getMapScale() + CFG.map.getMapCoordinates().getPosY()) * nScale)
               - ImageManager.getImage(nImageID).getHeight() / 2
         );
      CFG.drawText(
         oSB,
         this.getName(),
         (int)(
            (float)(this.getPosX() * CFG.map.getMapBG().getMapScale() + CFG.game.getProvince(nProvinceID).getTranslateProvincePosX()) * nScale
               + (float)(ImageManager.getImage(nImageID).getWidth() / 2)
               + 1.0F
         ),
         (int)(
            (float)(this.getPosY() * CFG.map.getMapBG().getMapScale() + CFG.map.getMapCoordinates().getPosY()) * nScale
               - (float)(ImageManager.getImage(nImageID).getHeight() / 2)
               + (float)(ImageManager.getImage(nImageID).getHeight() / 2)
               - (float)(CFG.ARMY_HEIGHT / 4)
               + 1.0F
         ),
         nColor
      );
      CFG.drawText(
         oSB,
         "" + this.iElevation + "m",
         (int)(
            (float)(this.getPosX() * CFG.map.getMapBG().getMapScale() + CFG.game.getProvince(nProvinceID).getTranslateProvincePosX()) * nScale
               + (float)(ImageManager.getImage(nImageID).getWidth() / 2)
               + 1.0F
         ),
         (int)(
            (float)(this.getPosY() * CFG.map.getMapBG().getMapScale() + CFG.map.getMapCoordinates().getPosY()) * nScale
               - (float)(ImageManager.getImage(nImageID).getHeight() / 2)
               + (float)(ImageManager.getImage(nImageID).getHeight() / 2)
               + (float)(CFG.ARMY_HEIGHT / 4)
               + 1.0F
               + (float)CFG.PADDING
         ),
         CFG.COLOR_TEXT_MODIFIER_NEUTRAL
      );
   }

   protected final void drawImage(SpriteBatch oSB, int nProvinceID, float nScale) {
      ImageManager.getImage(Images.mount)
         .draw(
            oSB,
            (int)(
               (float)(this.getPosX() * CFG.map.getMapBG().getMapScale() + CFG.game.getProvince(nProvinceID).getTranslateProvincePosX()) * nScale
                  - (float)(ImageManager.getImage(Images.mount).getWidth() / 2)
            ),
            (int)((float)(this.getPosY() * CFG.map.getMapBG().getMapScale() + CFG.map.getMapCoordinates().getPosY()) * nScale)
               - ImageManager.getImage(Images.mount).getHeight() / 2
         );
   }

   protected final String getName() {
      return this.sName;
   }

   protected final void setName(String sName) {
      this.sName = sName;
   }

   protected final int getPosX() {
      return this.iPosX;
   }

   protected final void setPosX(int iPosX) {
      this.iPosX = iPosX;
   }

   protected final int getPosY() {
      return this.iPosY;
   }

   protected final void setPosY(int iPosY) {
      this.iPosY = iPosY;
   }

   protected final int getElevation() {
      return this.iElevation;
   }

   protected final void setElevation(int iElevation) {
      this.iElevation = iElevation;
   }
}
