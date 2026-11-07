package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

class Text_BuildTitle_Terrain extends Text_BuildTitle {
   private int iTerrainID;

   protected Text_BuildTitle_Terrain(String sText, int iTextPositionX, int iPosX, int iPosY, int iWidth, int iHeight, int iTerrainID) {
      super(sText, iTextPositionX, iPosX, iPosY, iWidth, iHeight);
      this.iTerrainID = iTerrainID;
   }

   @Override
   protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
      super.draw(oSB, iTranslateX, iTranslateY, isActive, scrollableY);
      CFG.terrainTypesManager
         .getIcon(this.iTerrainID)
         .draw(
            oSB,
            this.getPosX()
               + this.getWidth()
               - CFG.PADDING * 2
               - (int)((float)CFG.terrainTypesManager.getIcon(this.iTerrainID).getWidth() * this.getImageScale())
               + iTranslateX,
            this.getPosY()
               + this.getHeight() / 2
               - CFG.terrainTypesManager.getIcon(this.iTerrainID).getHeight()
               - (int)((float)CFG.terrainTypesManager.getIcon(this.iTerrainID).getHeight() * this.getImageScale()) / 2
               + iTranslateY,
            (int)((float)CFG.terrainTypesManager.getIcon(this.iTerrainID).getWidth() * this.getImageScale()),
            (int)((float)CFG.terrainTypesManager.getIcon(this.iTerrainID).getHeight() * this.getImageScale())
         );
   }

   protected float getImageScale() {
      return (float)CFG.TEXT_HEIGHT * 1.0F / (float)CFG.terrainTypesManager.getIcon(this.iTerrainID).getHeight();
   }
}
