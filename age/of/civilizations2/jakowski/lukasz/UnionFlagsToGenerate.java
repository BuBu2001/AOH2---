package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.utils.ScissorStack;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.ScreenUtils;
import java.util.ArrayList;
import java.util.List;

class UnionFlagsToGenerate {
   protected int iID = -1;
   protected List<String> lTags = new ArrayList<>();
   protected UnionFlagsToGenerate_TypesOfAction typeOfAction = UnionFlagsToGenerate_TypesOfAction.ACTIVE_CIV_INFO;

   protected final boolean generateFlag(SpriteBatch oSB) {
      try {
         List<Image> tempFlags = new ArrayList<>();

         for(int i = 0; i < this.lTags.size(); ++i) {
            try {
               try {
                  tempFlags.add(new Image(new Texture(Gdx.files.internal("game/flagsH/" + (String)this.lTags.get(i) + ".png")), Texture.TextureFilter.Linear));
               } catch (GdxRuntimeException var17) {
                  try {
                     tempFlags.add(
                        new Image(
                           new Texture(Gdx.files.internal("game/flagsH/" + CFG.ideologiesManager.getRealTag(this.lTags.get(i)) + ".png")),
                           Texture.TextureFilter.Linear
                        )
                     );
                  } catch (GdxRuntimeException var16) {
                     if (CFG.isAndroid()) {
                        try {
                           tempFlags.add(
                              new Image(
                                 new Texture(
                                    Gdx.files
                                       .local(
                                          "game/civilizations_editor/"
                                             + CFG.ideologiesManager.getRealTag(this.lTags.get(i))
                                             + "/"
                                             + CFG.ideologiesManager.getRealTag(this.lTags.get(i))
                                             + "_FLH.png"
                                       )
                                 ),
                                 Texture.TextureFilter.Linear
                              )
                           );
                        } catch (GdxRuntimeException var12) {
                           tempFlags.add(
                              new Image(
                                 new Texture(
                                    Gdx.files
                                       .internal(
                                          "game/civilizations_editor/"
                                             + CFG.ideologiesManager.getRealTag(this.lTags.get(i))
                                             + "/"
                                             + CFG.ideologiesManager.getRealTag(this.lTags.get(i))
                                             + "_FLH.png"
                                       )
                                 ),
                                 Texture.TextureFilter.Linear
                              )
                           );
                        }
                     } else {
                        tempFlags.add(
                           new Image(
                              new Texture(
                                 Gdx.files
                                    .internal(
                                       "game/civilizations_editor/"
                                          + CFG.ideologiesManager.getRealTag(this.lTags.get(i))
                                          + "/"
                                          + CFG.ideologiesManager.getRealTag(this.lTags.get(i))
                                          + "_FLH.png"
                                    )
                              ),
                              Texture.TextureFilter.Linear
                           )
                        );
                     }
                  }
               }
            } catch (GdxRuntimeException var18) {
               try {
                  try {
                     tempFlags.add(
                        new Image(new Texture(Gdx.files.internal("game/flags/" + (String)this.lTags.get(i) + ".png")), Texture.TextureFilter.Nearest)
                     );
                  } catch (GdxRuntimeException var14) {
                     try {
                        tempFlags.add(
                           new Image(
                              new Texture(Gdx.files.internal("game/flags/" + CFG.ideologiesManager.getRealTag(this.lTags.get(i)) + ".png")),
                              Texture.TextureFilter.Nearest
                           )
                        );
                     } catch (GdxRuntimeException var13) {
                        if (CFG.isAndroid()) {
                           try {
                              tempFlags.add(
                                 new Image(
                                    new Texture(
                                       Gdx.files
                                          .local(
                                             "game/civilizations_editor/"
                                                + CFG.ideologiesManager.getRealTag(this.lTags.get(i))
                                                + "/"
                                                + CFG.ideologiesManager.getRealTag(this.lTags.get(i))
                                                + "_FL.png"
                                          )
                                    ),
                                    Texture.TextureFilter.Nearest
                                 )
                              );
                           } catch (GdxRuntimeException var11) {
                              tempFlags.add(
                                 new Image(
                                    new Texture(
                                       Gdx.files
                                          .internal(
                                             "game/civilizations_editor/"
                                                + CFG.ideologiesManager.getRealTag(this.lTags.get(i))
                                                + "/"
                                                + CFG.ideologiesManager.getRealTag(this.lTags.get(i))
                                                + "_FL.png"
                                          )
                                    ),
                                    Texture.TextureFilter.Nearest
                                 )
                              );
                           }
                        } else {
                           tempFlags.add(
                              new Image(
                                 new Texture(
                                    Gdx.files
                                       .internal(
                                          "game/civilizations_editor/"
                                             + CFG.ideologiesManager.getRealTag(this.lTags.get(i))
                                             + "/"
                                             + CFG.ideologiesManager.getRealTag(this.lTags.get(i))
                                             + "_FL.png"
                                       )
                                 ),
                                 Texture.TextureFilter.Nearest
                              )
                           );
                        }
                     }
                  }
               } catch (GdxRuntimeException var15) {
                  tempFlags.add(new Image(new Texture(Gdx.files.internal("game/flags/ran.png")), Texture.TextureFilter.Nearest));
               }
            } catch (OutOfMemoryError var19) {
            }
         }

         if (this.typeOfAction == UnionFlagsToGenerate_TypesOfAction.CIV_ID_SMALL) {
            oSB.setColor(Color.BLACK);
            ImageManager.getImage(Images.pix255_255_255).draw(oSB, 0, ImageManager.getImage(Images.pix255_255_255).getHeight(), 27, 18);
            oSB.setColor(Color.WHITE);

            for(int i = 0; i < tempFlags.size() && i < 4; ++i) {
               oSB.setShader(AoCGame.shaderAlpha);
               CFG.unionFlagsToGenerate_Manager.lFlags_Small.get(i).getTexture().bind(2);
               tempFlags.get(i).getTexture().bind(1);
               Gdx.gl.glActiveTexture(33984);
               CFG.unionFlagsToGenerate_Manager.lFlags_Small.get(i).draw(oSB, 0, 0, false, true);
               oSB.setShader(AoCGame.defaultShader);
            }

            Image tGenerated = new Image(new Texture(ScreenUtils.getFrameBufferPixmap(0, CFG.GAME_HEIGHT - 18, 27, 18)));
            tGenerated.draw(oSB, 0, 0, false, true);

            try {
               oSB.flush();
               ScissorStack.popScissors();
            } catch (IllegalStateException var10) {
            }

            oSB.end();
            oSB.begin();
            oSB.setColor(Color.WHITE);
            tGenerated.getTexture().dispose();
            Image var24 = null;
            Image var25 = new Image(new Texture(ScreenUtils.getFrameBufferPixmap(0, CFG.GAME_HEIGHT - 18, 27, 18)));
            CFG.game.getCiv(this.iID).setFlag(var25);
         } else if (this.typeOfAction == UnionFlagsToGenerate_TypesOfAction.ACTIVE_CIV_INFO) {
            oSB.setColor(Color.BLACK);
            ImageManager.getImage(Images.pix255_255_255).draw(oSB, 0, ImageManager.getImage(Images.pix255_255_255).getHeight(), 27, 18);
            oSB.setColor(Color.WHITE);

            for(int i = 0; i < tempFlags.size() && i < 4; ++i) {
               oSB.setShader(AoCGame.shaderAlpha);
               CFG.unionFlagsToGenerate_Manager.lFlags_H.get(i).getTexture().bind(2);
               tempFlags.get(i).getTexture().bind(1);
               Gdx.gl.glActiveTexture(33984);
               CFG.unionFlagsToGenerate_Manager.lFlags_H.get(i).draw(oSB, 0, 0, false, true);
               oSB.setShader(AoCGame.defaultShader);
            }

            Image tGenerated = new Image(new Texture(ScreenUtils.getFrameBufferPixmap(0, CFG.GAME_HEIGHT - 44, 68, 44)));
            tGenerated.draw(oSB, 0, 0, false, true);

            try {
               oSB.flush();
               ScissorStack.popScissors();
            } catch (IllegalStateException var9) {
            }

            oSB.end();
            oSB.begin();
            oSB.setColor(Color.WHITE);
            tGenerated.getTexture().dispose();
            Image var28 = null;
            Image var29 = new Image(new Texture(ScreenUtils.getFrameBufferPixmap(0, CFG.GAME_HEIGHT - 44, 68, 44)));
            CFG.setActiveCivInfoFlag(var29);
         } else if (this.typeOfAction == UnionFlagsToGenerate_TypesOfAction.PLAYER_ID) {
            oSB.setColor(Color.BLACK);
            ImageManager.getImage(Images.pix255_255_255).draw(oSB, 0, ImageManager.getImage(Images.pix255_255_255).getHeight(), 27, 18);
            oSB.setColor(Color.WHITE);

            for(int i = 0; i < tempFlags.size() && i < 4; ++i) {
               oSB.setShader(AoCGame.shaderAlpha);
               CFG.unionFlagsToGenerate_Manager.lFlags_H.get(i).getTexture().bind(2);
               tempFlags.get(i).getTexture().bind(1);
               Gdx.gl.glActiveTexture(33984);
               CFG.unionFlagsToGenerate_Manager.lFlags_H.get(i).draw(oSB, 0, 0, false, true);
               oSB.setShader(AoCGame.defaultShader);
            }

            Image tGenerated = new Image(new Texture(ScreenUtils.getFrameBufferPixmap(0, CFG.GAME_HEIGHT - 44, 68, 44)));
            tGenerated.draw(oSB, 0, 0, false, true);

            try {
               oSB.flush();
               ScissorStack.popScissors();
            } catch (IllegalStateException var8) {
            }

            oSB.end();
            oSB.begin();
            oSB.setColor(Color.WHITE);
            tGenerated.getTexture().dispose();
            Image var32 = null;
            Image var33 = new Image(new Texture(ScreenUtils.getFrameBufferPixmap(0, CFG.GAME_HEIGHT - 44, 68, 44)));

            for(int i = 0; i < CFG.game.getPlayersSize(); ++i) {
               if (CFG.game.getPlayer(i).getCivID() == this.iID) {
                  CFG.game.getPlayer(i).loadPlayersFlag(var33);
                  break;
               }
            }
         }

         for(int i = 0; i < tempFlags.size(); ++i) {
            tempFlags.get(i).getTexture().dispose();
         }

         tempFlags.clear();
         tempFlags = null;
         return true;
      } catch (RuntimeException var20) {
         return false;
      }
   }
}
