package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.GdxRuntimeException;

class Wonder {
   protected String sName = null;
   protected int iPosX;
   protected int iPosY;
   protected int iSinceYear;
   protected int iUntilYear;
   protected Image nImage;
   protected String sWiki;
   protected boolean isAvailable = true;

   protected Wonder(String sName, String sImage, int nPosX, int nPosY, int iSinceYear, int iUntilYear, String sWiki) {
      this.sName = sName;
      this.iSinceYear = iSinceYear;
      this.iUntilYear = iUntilYear;
      this.iPosX = nPosX;
      this.iPosY = nPosY;
      this.sWiki = sWiki;

      try {
         this.nImage = new Image(
            new Texture(Gdx.files.internal("map/" + CFG.map.getFile_ActiveMap_Path() + "wonders/" + "images/" + sImage)), Texture.TextureFilter.Linear
         );
      } catch (IndexOutOfBoundsException var9) {
         this.nImage = new Image(new Texture(Gdx.files.internal("UI/pix")), Texture.TextureFilter.Linear);
      } catch (NullPointerException var10) {
         this.nImage = new Image(new Texture(Gdx.files.internal("UI/pix")), Texture.TextureFilter.Linear);
      } catch (GdxRuntimeException var11) {
         this.nImage = new Image(new Texture(Gdx.files.internal("UI/pix")), Texture.TextureFilter.Linear);
      }
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
      this.nImage
         .draw(
            oSB,
            (int)(
               (float)(this.iPosX * CFG.map.getMapBG().getMapScale() + CFG.game.getProvince(nProvinceID).getTranslateProvincePosX()) * nScale
                  - (float)(this.nImage.getWidth() / 2)
            ),
            (int)((float)(this.iPosY * CFG.map.getMapBG().getMapScale() + CFG.map.getMapCoordinates().getPosY()) * nScale) - this.nImage.getHeight() / 2
         );
   }

   protected final void dispose() {
      try {
         this.nImage.dispose();
         this.nImage = null;
      } catch (NullPointerException var2) {
      } catch (GdxRuntimeException var3) {
      }
   }
}
