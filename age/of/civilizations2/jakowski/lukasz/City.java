package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.io.Serializable;

class City implements Serializable {
   private static final long serialVersionUID = 0L;
   private String sCityName = null;
   protected int iWidth = 0;
   private int iPosX;
   private int iPosY;
   private int iCityLevel = 0;

   protected City(String sName, int nPosX, int nPosY, int iCityLevel) {
      this.sCityName = sName;
      this.updateCityNameWidth();
      this.iPosX = nPosX;
      this.iPosY = nPosY;
      this.iCityLevel = iCityLevel;
   }

   protected final void draw(SpriteBatch oSB, int nProvinceID, float nScale) {
      this.draw(oSB, nProvinceID, nScale, CFG.COLOR_CITY_NAME, this.getCityLevel());
   }

   protected final void drawInLine(SpriteBatch oSB, int nProvinceID, float nScale) {
      this.drawInLine(oSB, nProvinceID, nScale, CFG.COLOR_CITY_NAME, this.getCityLevel());
   }

   protected final void draw(SpriteBatch oSB, int nProvinceID, float nScale, int nImageID) {
      this.draw(oSB, nProvinceID, nScale, CFG.COLOR_CITY_NAME, nImageID);
   }

   protected final void draw(SpriteBatch oSB, int nProvinceID, float nScale, Color nColor) {
      this.draw(oSB, nProvinceID, nScale, nColor, this.getCityLevel());
   }

   protected final void draw(SpriteBatch oSB, int nProvinceID, float nScale, Color nColor, int nImageID) {
      CFG.drawText(
         oSB,
         this.getCityName(),
         (int)(
            (float)(this.getPosX() * CFG.map.getMapBG().getMapScale() + CFG.game.getProvince(nProvinceID).getTranslateProvincePosX()) * nScale
               - (float)this.iWidth / 2.0F
         ),
         (int)((float)(this.getPosY() * CFG.map.getMapBG().getMapScale() + CFG.map.getMapCoordinates().getPosY()) * nScale)
            - ImageManager.getImage(nImageID).getHeight() / 2
            + ImageManager.getImage(nImageID).getHeight()
            + 2,
         nColor
      );
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
   }

   protected final void drawInLine(SpriteBatch oSB, int nProvinceID, float nScale, Color nColor, int nImageID) {
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
         this.getCityName(),
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
   }

   protected final void drawCityImage_Level(SpriteBatch oSB, int nProvinceID, float nScale) {
      ImageManager.getImage(this.getCityLevel())
         .draw(
            oSB,
            (int)(
               (float)(this.getPosX() * CFG.map.getMapBG().getMapScale() + CFG.game.getProvince(nProvinceID).getTranslateProvincePosX()) * nScale
                  - (float)(ImageManager.getImage(this.getCityLevel()).getWidth() / 2)
            ),
            (int)((float)(this.getPosY() * CFG.map.getMapBG().getMapScale() + CFG.map.getMapCoordinates().getPosY()) * nScale)
               - ImageManager.getImage(this.getCityLevel()).getHeight() / 2
         );
   }

   protected final void updateCityNameWidth() {
      CFG.glyphLayout.setText(CFG.fontMain, this.sCityName);
      this.iWidth = (int)(CFG.glyphLayout.width * CFG.settingsManager.CITIES_FONT_SCALE);
   }

   protected final String getCityName() {
      return this.sCityName;
   }

   protected final void setCityName(String sCityName) {
      this.sCityName = sCityName;
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

   protected final int getCityLevel() {
      return this.iCityLevel;
   }

   protected final void setCityLevel(int iCityLevel) {
      this.iCityLevel = iCityLevel;
   }
}
