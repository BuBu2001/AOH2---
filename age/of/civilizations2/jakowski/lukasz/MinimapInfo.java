package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

class MinimapInfo extends MenuElement {
   private float scale;

   protected MinimapInfo(int nPosX, int nPosY, int nWidth) {
      this.typeOfElement = MenuElement.TypeOfElement.MINIMAPINFO;
      this.setPosX(nPosX);
      this.setPosY(nPosY);
      this.scale = (float)nWidth / (float)CFG.map.getMapBG().getWidth();
      this.setWidth(nWidth);
      this.setHeight((int)((float)CFG.map.getMapBG().getHeight() * this.scale));
   }

   @Override
   protected final void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
      oSB.end();
      AoCGame.viewport
         .setWorldSize(
            (float)CFG.GAME_WIDTH * ((float)CFG.map.getMapBG().getWidth() / (float)this.getWidth()),
            (float)CFG.GAME_HEIGHT * ((float)CFG.map.getMapBG().getHeight() / (float)this.getHeight())
         );
      AoCGame.viewport.apply();
      AoCGame.camera
         .setToOrtho(
            true,
            (float)CFG.GAME_WIDTH * ((float)CFG.map.getMapBG().getWidth() / (float)this.getWidth()),
            -((float)CFG.GAME_HEIGHT * ((float)CFG.map.getMapBG().getHeight() / (float)this.getHeight()))
         );
      oSB.setProjectionMatrix(AoCGame.camera.combined);
      oSB.begin();
      CFG.map
         .getMapBG()
         .drawMap(
            oSB,
            (int)((float)(this.getPosX() + iTranslateX) * ((float)CFG.map.getMapBG().getWidth() / (float)this.getWidth())),
            (int)((float)(this.getPosY() + iTranslateY) * ((float)CFG.map.getMapBG().getHeight() / (float)this.getHeight()))
         );
      CFG.game
         .drawProvinces(
            oSB,
            (int)((float)(this.getPosX() + iTranslateX) * ((float)CFG.map.getMapBG().getWidth() / (float)this.getWidth())),
            (int)((float)(this.getPosY() + iTranslateY) * ((float)CFG.map.getMapBG().getHeight() / (float)this.getHeight())),
            1.0F,
            255
         );
      oSB.end();
      AoCGame.camera.setToOrtho(false, (float)CFG.GAME_WIDTH, (float)(-CFG.GAME_HEIGHT));
      AoCGame.viewport.setWorldSize((float)CFG.GAME_WIDTH, (float)CFG.GAME_HEIGHT);
      AoCGame.viewport.apply();
      oSB.setProjectionMatrix(AoCGame.camera.combined);
      oSB.begin();
   }
}
