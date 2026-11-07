package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.GdxRuntimeException;

class RandomGame_Player {
   private String sTag = null;
   private int iCapitalProvinceID;
   private Image flagOfCivilization = null;

   protected RandomGame_Player(String sTag, int iCapitalProvinceID) {
      this.sTag = sTag;
      this.iCapitalProvinceID = iCapitalProvinceID;
   }

   protected final String getTag() {
      return this.sTag;
   }

   protected final void setTag(String sTag) {
      this.sTag = sTag;
      if (sTag == null) {
         this.disposePlayersFlag();
      } else {
         this.loadPlayersFlag();
      }
   }

   protected final int getCapitalProvinceID() {
      return this.iCapitalProvinceID;
   }

   protected final void setCapitalProvinceID(int iCapitalProvinceID) {
      this.iCapitalProvinceID = iCapitalProvinceID;
   }

   protected final void loadPlayersFlag() {
      this.disposePlayersFlag();

      try {
         try {
            this.flagOfCivilization = new Image(new Texture(Gdx.files.internal("game/flags/" + this.sTag + ".png")), Texture.TextureFilter.Nearest);
         } catch (GdxRuntimeException var6) {
            try {
               this.flagOfCivilization = new Image(
                  new Texture(Gdx.files.internal("game/flags/" + CFG.ideologiesManager.getRealTag(this.sTag) + ".png")), Texture.TextureFilter.Nearest
               );
            } catch (GdxRuntimeException var5) {
               if (CFG.isAndroid()) {
                  try {
                     this.flagOfCivilization = new Image(
                        new Texture(
                           Gdx.files
                              .local(
                                 "game/civilizations_editor/"
                                    + CFG.ideologiesManager.getRealTag(this.sTag)
                                    + "/"
                                    + CFG.ideologiesManager.getRealTag(this.sTag)
                                    + "_FL.png"
                              )
                        ),
                        Texture.TextureFilter.Nearest
                     );
                  } catch (GdxRuntimeException var4) {
                     this.flagOfCivilization = new Image(
                        new Texture(
                           Gdx.files
                              .internal(
                                 "game/civilizations_editor/"
                                    + CFG.ideologiesManager.getRealTag(this.sTag)
                                    + "/"
                                    + CFG.ideologiesManager.getRealTag(this.sTag)
                                    + "_FL.png"
                              )
                        ),
                        Texture.TextureFilter.Nearest
                     );
                  }
               } else {
                  this.flagOfCivilization = new Image(
                     new Texture(
                        Gdx.files
                           .internal(
                              "game/civilizations_editor/"
                                 + CFG.ideologiesManager.getRealTag(this.sTag)
                                 + "/"
                                 + CFG.ideologiesManager.getRealTag(this.sTag)
                                 + "_FL.png"
                           )
                     ),
                     Texture.TextureFilter.Nearest
                  );
               }
            }
         }
      } catch (GdxRuntimeException var7) {
         this.disposePlayersFlag();
      }
   }

   protected final void disposePlayersFlag() {
      if (this.flagOfCivilization != null) {
         this.flagOfCivilization.getTexture().dispose();
         this.flagOfCivilization = null;
      }
   }

   protected final Image getFlag() {
      return this.flagOfCivilization == null ? ImageManager.getImage(Images.randomCivilizationFlag) : this.flagOfCivilization;
   }
}
