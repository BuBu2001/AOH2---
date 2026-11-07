package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.GdxRuntimeException;

class CreateVassal_Data {
   protected int iCapitalProvinceID = -1;
   protected String sCivTag = null;
   protected Color oColor = new Color(1.0F, 1.0F, 1.0F, 1.0F);
   protected boolean playAsVassal = false;
   private Image flagOfCivilization = null;
   private Image flagOfCivilizationH = null;

   protected final void setCivTag(String nTag) {
      this.sCivTag = nTag;
      this.loadFlag();
   }

   protected final void loadFlag() {
      this.dispose();
      if (this.sCivTag != null) {
         try {
            try {
               this.flagOfCivilizationH = new Image(new Texture(Gdx.files.internal("game/flagsH/" + this.sCivTag + ".png")), Texture.TextureFilter.Linear);
            } catch (GdxRuntimeException var11) {
               try {
                  this.flagOfCivilizationH = new Image(
                     new Texture(Gdx.files.internal("game/flagsH/" + CFG.ideologiesManager.getRealTag(this.sCivTag) + ".png")), Texture.TextureFilter.Linear
                  );
               } catch (GdxRuntimeException var10) {
                  if (CFG.isAndroid()) {
                     try {
                        this.flagOfCivilizationH = new Image(
                           new Texture(
                              Gdx.files
                                 .local(
                                    "game/civilizations_editor/"
                                       + CFG.ideologiesManager.getRealTag(this.sCivTag)
                                       + "/"
                                       + CFG.ideologiesManager.getRealTag(this.sCivTag)
                                       + "_FLH.png"
                                 )
                           ),
                           Texture.TextureFilter.Linear
                        );
                     } catch (GdxRuntimeException var5) {
                        this.flagOfCivilizationH = new Image(
                           new Texture(
                              Gdx.files
                                 .internal(
                                    "game/civilizations_editor/"
                                       + CFG.ideologiesManager.getRealTag(this.sCivTag)
                                       + "/"
                                       + CFG.ideologiesManager.getRealTag(this.sCivTag)
                                       + "_FLH.png"
                                 )
                           ),
                           Texture.TextureFilter.Linear
                        );
                     }
                  } else {
                     this.flagOfCivilizationH = new Image(
                        new Texture(
                           Gdx.files
                              .internal(
                                 "game/civilizations_editor/"
                                    + CFG.ideologiesManager.getRealTag(this.sCivTag)
                                    + "/"
                                    + CFG.ideologiesManager.getRealTag(this.sCivTag)
                                    + "_FLH.png"
                              )
                        ),
                        Texture.TextureFilter.Linear
                     );
                  }
               }
            }
         } catch (GdxRuntimeException var12) {
            this.dispose();
         } catch (OutOfMemoryError var13) {
            this.dispose();
         }

         try {
            try {
               this.flagOfCivilization = new Image(new Texture(Gdx.files.internal("game/flags/" + this.sCivTag + ".png")), Texture.TextureFilter.Linear);
            } catch (GdxRuntimeException var7) {
               try {
                  this.flagOfCivilization = new Image(
                     new Texture(Gdx.files.internal("game/flags/" + CFG.ideologiesManager.getRealTag(this.sCivTag) + ".png")), Texture.TextureFilter.Linear
                  );
               } catch (GdxRuntimeException var6) {
                  if (CFG.isAndroid()) {
                     try {
                        this.flagOfCivilization = new Image(
                           new Texture(
                              Gdx.files
                                 .local(
                                    "game/civilizations_editor/"
                                       + CFG.ideologiesManager.getRealTag(this.sCivTag)
                                       + "/"
                                       + CFG.ideologiesManager.getRealTag(this.sCivTag)
                                       + "_FL.png"
                                 )
                           ),
                           Texture.TextureFilter.Linear
                        );
                     } catch (GdxRuntimeException var4) {
                        this.flagOfCivilization = new Image(
                           new Texture(
                              Gdx.files
                                 .internal(
                                    "game/civilizations_editor/"
                                       + CFG.ideologiesManager.getRealTag(this.sCivTag)
                                       + "/"
                                       + CFG.ideologiesManager.getRealTag(this.sCivTag)
                                       + "_FL.png"
                                 )
                           ),
                           Texture.TextureFilter.Linear
                        );
                     }
                  } else {
                     this.flagOfCivilization = new Image(
                        new Texture(
                           Gdx.files
                              .internal(
                                 "game/civilizations_editor/"
                                    + CFG.ideologiesManager.getRealTag(this.sCivTag)
                                    + "/"
                                    + CFG.ideologiesManager.getRealTag(this.sCivTag)
                                    + "_FL.png"
                              )
                        ),
                        Texture.TextureFilter.Linear
                     );
                  }
               }
            }
         } catch (GdxRuntimeException var8) {
            this.dispose();
         } catch (OutOfMemoryError var9) {
            this.dispose();
         }
      }
   }

   protected final Image getFlagOfCiv() {
      return this.flagOfCivilization;
   }

   protected final Image getFlagOfCivH() {
      return this.flagOfCivilizationH == null ? this.flagOfCivilization : this.flagOfCivilizationH;
   }

   protected final void dispose() {
      if (this.flagOfCivilizationH != null) {
         this.flagOfCivilizationH.getTexture().dispose();
         this.flagOfCivilizationH = null;
      }

      if (this.flagOfCivilization != null) {
         this.flagOfCivilization.getTexture().dispose();
         this.flagOfCivilization = null;
      }
   }
}
