package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.utils.ScissorStack;
import com.badlogic.gdx.utils.GdxRuntimeException;
import java.util.ArrayList;
import java.util.List;

class Button_Statistics_WarDetails extends Button_Statistics {
   protected static final float FONT_SCALE = 0.75F;
   protected static final float FONT_SCALE2 = 0.65F;
   protected static final float FONT_SCALE3 = 0.6F;
   protected int iCivID;
   protected String iCivilianDeaths;
   protected int iCivilianDeathsWidth;
   protected Color oColorCivilianDeaths;
   protected String iEconomicLosses;
   protected int iEconomicLossesWidth;
   protected Color oColorEconomicLosses;
   protected String sProvinces;
   protected int iParticipation;
   protected Color oColorParticipation;
   protected Image civFlag = null;
   protected boolean canPeaceOut = false;
   protected long ANIMATION_TIME = 0L;
   protected int ANIMATION_POSX = 0;
   protected static final int ANIMATION_TIMER = 175;
   protected long lTime = 0L;
   protected float fAlphaMod = 0.0F;
   protected boolean backAnimation = false;

   protected Button_Statistics_WarDetails(
      int nCivID,
      int iCivilianDeaths,
      int iEconomicLosses,
      int iParticipation,
      int iProvinces,
      int iProvincesTotal,
      int iPosX,
      int iPosY,
      int iWidth,
      boolean canPeaceOut
   ) {
      super(
         CFG.FOG_OF_WAR == 2
            ? (CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetCivilization(nCivID) ? CFG.game.getCiv(nCivID).getCivName() : CFG.langManager.get("Undiscovered"))
            : CFG.game.getCiv(nCivID).getCivName(),
         0,
         iPosX,
         iPosY,
         iWidth,
         CFG.TEXT_HEIGHT * 2 + CFG.PADDING * 4
      );
      this.oColorCivilianDeaths = iCivilianDeaths == 0 ? CFG.COLOR_TEXT_MODIFIER_NEUTRAL2 : CFG.COLOR_TEXT_MODIFIER_NEGATIVE2;
      this.oColorEconomicLosses = iEconomicLosses == 0 ? CFG.COLOR_TEXT_MODIFIER_NEUTRAL2 : CFG.COLOR_TEXT_MODIFIER_NEGATIVE2;
      this.canPeaceOut = canPeaceOut;
      if (CFG.FOG_OF_WAR == 2) {
         if (CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetCivilization(nCivID)) {
            this.iCivID = nCivID;
         } else {
            this.iCivID = -1;
         }
      } else {
         this.iCivID = nCivID;
      }

      this.iCivilianDeaths = CFG.getNumberWithSpaces("" + iCivilianDeaths);
      this.iEconomicLosses = CFG.getNumberWithSpaces("" + iEconomicLosses);
      this.iParticipation = iParticipation;
      CFG.glyphLayout.setText(CFG.fontMain, "" + iCivilianDeaths);
      this.iCivilianDeathsWidth = (int)(CFG.glyphLayout.width * 0.65F);
      CFG.glyphLayout.setText(CFG.fontMain, "" + iEconomicLosses);
      this.iEconomicLossesWidth = (int)(CFG.glyphLayout.width * 0.65F);
      this.sProvinces = CFG.langManager.get("Provinces")
         + ": "
         + iProvinces
         + "/"
         + iProvincesTotal
         + (
            iProvinces != iProvincesTotal
               ? (
                  iProvinces > iProvincesTotal
                     ? " [" + (iProvinces - iProvincesTotal) + " " + CFG.langManager.get("Occupied") + "]"
                     : " [" + (iProvincesTotal - iProvinces) + " " + CFG.langManager.get("Lost") + "]"
               )
               : ""
         );
      this.oColorParticipation = iParticipation == 0 ? CFG.COLOR_TEXT_OPTIONS_NS : CFG.COLOR_TEXT_MODIFIER_NEUTRAL;
      if (this.iCivID >= 0) {
         try {
            try {
               this.civFlag = new Image(
                  new Texture(Gdx.files.internal("game/flagsH/" + CFG.game.getCiv(this.iCivID).getCivTag() + ".png")), Texture.TextureFilter.Linear
               );
            } catch (GdxRuntimeException var26) {
               try {
                  try {
                     this.civFlag = new Image(
                        new Texture(Gdx.files.internal("game/flagsH/" + CFG.ideologiesManager.getRealTag(CFG.game.getCiv(this.iCivID).getCivTag()) + ".png")),
                        Texture.TextureFilter.Linear
                     );
                  } catch (GdxRuntimeException var21) {
                     try {
                        this.civFlag = new Image(
                           new Texture(Gdx.files.internal("game/flags/" + CFG.game.getCiv(this.iCivID).getCivTag() + ".png")), Texture.TextureFilter.Linear
                        );
                     } catch (GdxRuntimeException var20) {
                        this.civFlag = new Image(
                           new Texture(Gdx.files.internal("game/flags/" + CFG.ideologiesManager.getRealTag(CFG.game.getCiv(this.iCivID).getCivTag()) + ".png")),
                           Texture.TextureFilter.Linear
                        );
                     }
                  }
               } catch (GdxRuntimeException var25) {
                  if (CFG.isAndroid()) {
                     try {
                        this.civFlag = new Image(
                           new Texture(
                              Gdx.files
                                 .local(
                                    "game/civilizations_editor/"
                                       + CFG.ideologiesManager.getRealTag(CFG.game.getCiv(this.iCivID).getCivTag())
                                       + "/"
                                       + CFG.ideologiesManager.getRealTag(CFG.game.getCiv(this.iCivID).getCivTag())
                                       + "_FLH.png"
                                 )
                           ),
                           Texture.TextureFilter.Linear
                        );
                     } catch (GdxRuntimeException var19) {
                        this.civFlag = new Image(
                           new Texture(
                              Gdx.files
                                 .internal(
                                    "game/civilizations_editor/"
                                       + CFG.ideologiesManager.getRealTag(CFG.game.getCiv(this.iCivID).getCivTag())
                                       + "/"
                                       + CFG.ideologiesManager.getRealTag(CFG.game.getCiv(this.iCivID).getCivTag())
                                       + "_FLH.png"
                                 )
                           ),
                           Texture.TextureFilter.Linear
                        );
                     }
                  } else {
                     this.civFlag = new Image(
                        new Texture(
                           Gdx.files
                              .internal(
                                 "game/civilizations_editor/"
                                    + CFG.ideologiesManager.getRealTag(CFG.game.getCiv(this.iCivID).getCivTag())
                                    + "/"
                                    + CFG.ideologiesManager.getRealTag(CFG.game.getCiv(this.iCivID).getCivTag())
                                    + "_FLH.png"
                              )
                        ),
                        Texture.TextureFilter.Linear
                     );
                  }
               }
            }
         } catch (GdxRuntimeException var27) {
            if (CFG.game.getCiv(this.iCivID).getCivTag().indexOf(";") > 0) {
               String nTag = CFG.game.getCiv(this.iCivID).getCivTag().substring(0, CFG.game.getCiv(this.iCivID).getCivTag().indexOf(";"));

               try {
                  try {
                     this.civFlag = new Image(new Texture(Gdx.files.internal("game/flagsH/" + nTag + ".png")), Texture.TextureFilter.Linear);
                  } catch (GdxRuntimeException var23) {
                     try {
                        try {
                           this.civFlag = new Image(
                              new Texture(Gdx.files.internal("game/flagsH/" + CFG.ideologiesManager.getRealTag(nTag) + ".png")), Texture.TextureFilter.Linear
                           );
                        } catch (GdxRuntimeException var18) {
                           try {
                              this.civFlag = new Image(new Texture(Gdx.files.internal("game/flags/" + nTag + ".png")), Texture.TextureFilter.Linear);
                           } catch (GdxRuntimeException var17) {
                              this.civFlag = new Image(
                                 new Texture(Gdx.files.internal("game/flags/" + CFG.ideologiesManager.getRealTag(nTag) + ".png")),
                                 Texture.TextureFilter.Linear
                              );
                           }
                        }
                     } catch (GdxRuntimeException var22) {
                        if (CFG.isAndroid()) {
                           try {
                              this.civFlag = new Image(
                                 new Texture(
                                    Gdx.files
                                       .local(
                                          "game/civilizations_editor/"
                                             + CFG.ideologiesManager.getRealTag(nTag)
                                             + "/"
                                             + CFG.ideologiesManager.getRealTag(nTag)
                                             + "_FLH.png"
                                       )
                                 ),
                                 Texture.TextureFilter.Linear
                              );
                           } catch (GdxRuntimeException var16) {
                              this.civFlag = new Image(
                                 new Texture(
                                    Gdx.files
                                       .internal(
                                          "game/civilizations_editor/"
                                             + CFG.ideologiesManager.getRealTag(nTag)
                                             + "/"
                                             + CFG.ideologiesManager.getRealTag(nTag)
                                             + "_FLH.png"
                                       )
                                 ),
                                 Texture.TextureFilter.Linear
                              );
                           }
                        } else {
                           this.civFlag = new Image(
                              new Texture(
                                 Gdx.files
                                    .internal(
                                       "game/civilizations_editor/"
                                          + CFG.ideologiesManager.getRealTag(nTag)
                                          + "/"
                                          + CFG.ideologiesManager.getRealTag(nTag)
                                          + "_FLH.png"
                                    )
                              ),
                              Texture.TextureFilter.Linear
                           );
                        }
                     }
                  }
               } catch (GdxRuntimeException var24) {
                  this.civFlag = null;
               }
            }
         } catch (OutOfMemoryError var28) {
            this.civFlag = null;
         }
      } else {
         this.civFlag = null;
      }
   }

   @Override
   protected void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
      if (this.row) {
         oSB.setColor(new Color(CFG.COLOR_GRADIENT_TITLE_BLUE.r, CFG.COLOR_GRADIENT_TITLE_BLUE.g, CFG.COLOR_GRADIENT_TITLE_BLUE.b, 0.135F));
         ImageManager.getImage(Images.pix255_255_255)
            .draw(
               oSB,
               this.getPosX() + iTranslateX,
               this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY,
               this.getWidth(),
               this.getHeight()
            );
         oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.275F));
         ImageManager.getImage(Images.gradient)
            .draw(
               oSB,
               this.getPosX() + iTranslateX,
               this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY,
               this.getWidth(),
               this.getHeight()
            );
      } else {
         oSB.setColor(new Color(CFG.COLOR_GRADIENT_TITLE_BLUE.r, CFG.COLOR_GRADIENT_TITLE_BLUE.g, CFG.COLOR_GRADIENT_TITLE_BLUE.b, 0.3F));
         ImageManager.getImage(Images.pix255_255_255)
            .draw(
               oSB,
               this.getPosX() + iTranslateX,
               this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY,
               this.getWidth(),
               this.getHeight()
            );
         oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.35F));
         ImageManager.getImage(Images.gradient)
            .draw(
               oSB,
               this.getPosX() + iTranslateX,
               this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY,
               this.getWidth(),
               this.getHeight()
            );
      }

      if (isActive || this.getIsHovered()) {
         oSB.setColor(new Color(CFG.COLOR_GRADIENT_DIPLOMACY.r, CFG.COLOR_GRADIENT_DIPLOMACY.g, CFG.COLOR_GRADIENT_DIPLOMACY.b, isActive ? 0.345F : 0.265F));
         ImageManager.getImage(Images.line_32_off1)
            .draw(
               oSB,
               this.getPosX() + iTranslateX,
               this.getPosY() + 1 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
               this.getWidth(),
               this.getHeight() - 2
            );
      }

      oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.525F));
      ImageManager.getImage(Images.slider_gradient)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY,
            this.getWidth(),
            this.getHeight()
         );
      this.drawFlag(oSB, iTranslateX, iTranslateY, isActive);
      oSB.setColor(new Color(0.06F, 0.06F, 0.1F, 0.45F));
      ImageManager.getImage(Images.gradient)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY,
            this.getWidth(),
            this.getHeight() / 4
         );
      ImageManager.getImage(Images.gradient)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() + this.getHeight() - this.getHeight() / 4 - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY,
            this.getWidth(),
            this.getHeight() / 4,
            false,
            true
         );
      oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.65F));
      ImageManager.getImage(Images.gradient)
         .draw(
            oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY, this.getWidth(), CFG.PADDING
         );
      ImageManager.getImage(Images.gradient)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() + this.getHeight() - CFG.PADDING - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY,
            this.getWidth(),
            CFG.PADDING,
            false,
            true
         );
      oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.6F));
      ImageManager.getImage(Images.pix255_255_255)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() + this.getHeight() - 2 - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY,
            this.getWidth(),
            1,
            true,
            false
         );
      oSB.setColor(new Color(CFG.COLOR_CREATE_NEW_GAME_BOX_PLAYERS.r, CFG.COLOR_CREATE_NEW_GAME_BOX_PLAYERS.g, CFG.COLOR_CREATE_NEW_GAME_BOX_PLAYERS.b, 0.85F));
      ImageManager.getImage(Images.slider_gradient)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() + this.getHeight() - 1 - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY,
            this.getWidth(),
            1,
            true,
            false
         );
      oSB.setColor(Color.WHITE);
   }

   private final void drawFlag(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
      oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.5F));
      ImageManager.getImage(Images.slider_gradient)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY,
            (int)((float)this.getHeight() / 44.0F * 68.0F),
            this.getHeight(),
            false,
            false
         );
      if (this.civFlag != null) {
         try {
            oSB.setColor(new Color(1.0F, 1.0F, 1.0F, 0.5F));
            oSB.setShader(AoCGame.shaderAlpha);
            ImageManager.getImage(Images.slider_gradient).getTexture().bind(2);
            this.civFlag.getTexture().bind(1);
            Gdx.gl.glActiveTexture(33984);
            ImageManager.getImage(Images.slider_gradient)
               .draw(
                  oSB,
                  this.getPosX() + this.ANIMATION_POSX + iTranslateX,
                  this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY,
                  (int)((float)this.getHeight() / 44.0F * 68.0F),
                  this.getHeight(),
                  false,
                  false
               );
            oSB.setShader(AoCGame.defaultShader);
            oSB.setColor(new Color(1.0F, 1.0F, 1.0F, 0.1F));
            oSB.setShader(AoCGame.shaderAlpha);
            ImageManager.getImage(Images.gradient).getTexture().bind(2);
            this.civFlag.getTexture().bind(1);
            Gdx.gl.glActiveTexture(33984);
            ImageManager.getImage(Images.gradient)
               .draw(
                  oSB,
                  this.getPosX() + this.ANIMATION_POSX + iTranslateX,
                  this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY,
                  (int)((float)this.getHeight() / 44.0F * 68.0F),
                  this.getHeight(),
                  false,
                  false
               );
            oSB.setShader(AoCGame.defaultShader);
            oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.35F));
            ImageManager.getImage(Images.slider_gradient)
               .draw(
                  oSB,
                  this.getPosX() + this.ANIMATION_POSX + iTranslateX,
                  this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY,
                  (int)((float)this.getHeight() / 44.0F * 68.0F),
                  this.getHeight(),
                  false,
                  false
               );
         } catch (NullPointerException var6) {
            oSB.setShader(AoCGame.defaultShader);
         }
      } else {
         oSB.setShader(AoCGame.defaultShader);
      }

      oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.825F));
      ImageManager.getImage(Images.slider_gradient)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY,
            (int)((float)this.getHeight() / 44.0F * (float)CFG.PADDING),
            this.getHeight(),
            false,
            false
         );
   }

   protected final int getMaxNameWidth() {
      return this.getWidth() / 2;
   }

   @Override
   protected void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
      try {
         oSB.setColor(
            new Color(
               (float)CFG.game.getCiv(this.iCivID).getR() / 255.0F,
               (float)CFG.game.getCiv(this.iCivID).getG() / 255.0F,
               (float)CFG.game.getCiv(this.iCivID).getB() / 255.0F,
               1.0F
            )
         );
      } catch (IndexOutOfBoundsException var11) {
         oSB.setColor(
            new Color(
               CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_AT_WAR.getR(),
               CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_AT_WAR.getG(),
               CFG.diplomacyColors_GameData.COLOR_DIPLOMACY_AT_WAR.getB(),
               1.0F
            )
         );
      }

      ImageManager.getImage(Images.pix255_255_255)
         .draw(
            oSB,
            this.getPosX() + this.ANIMATION_POSX + CFG.PADDING + iTranslateX,
            this.getPosY() + CFG.PADDING + CFG.PADDING / 2 - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY,
            2,
            (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect))
         );
      oSB.setColor(Color.WHITE);
      oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.2F));
      ImageManager.getImage(Images.line_32_off1)
         .draw(
            oSB,
            this.getPosX()
               + this.getWidth()
               - CFG.PADDING * 2
               - (int)((float)ImageManager.getImage(Images.economy).getWidth() * this.getImageScale(Images.economy))
               + iTranslateX,
            this.getPosY()
               + CFG.PADDING
               + (CFG.TEXT_HEIGHT - (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect))) / 2
               - ImageManager.getImage(Images.line_32_off1).getHeight()
               + iTranslateY,
            CFG.PADDING * 2 + (int)((float)ImageManager.getImage(Images.economy).getWidth() * this.getImageScale(Images.economy)),
            (int)((float)ImageManager.getImage(Images.population).getHeight() * this.getImageScale(Images.population))
         );
      ImageManager.getImage(Images.line_32_off1)
         .draw(
            oSB,
            this.getPosX()
               + this.getWidth()
               - CFG.PADDING * 2
               - (int)((float)ImageManager.getImage(Images.economy).getWidth() * this.getImageScale(Images.economy))
               + iTranslateX,
            this.getPosY()
               + this.getHeight()
               - CFG.PADDING
               - CFG.TEXT_HEIGHT
               + (CFG.TEXT_HEIGHT - (int)((float)ImageManager.getImage(Images.economy).getHeight() * this.getImageScale(Images.economy))) / 2
               - ImageManager.getImage(Images.line_32_off1).getHeight()
               + iTranslateY,
            CFG.PADDING * 2 + (int)((float)ImageManager.getImage(Images.economy).getWidth() * this.getImageScale(Images.economy)),
            (int)((float)ImageManager.getImage(Images.economy).getHeight() * this.getImageScale(Images.economy))
         );
      oSB.setColor(new Color(CFG.COLOR_GRADIENT_TITLE_BLUE.r, CFG.COLOR_GRADIENT_TITLE_BLUE.g, CFG.COLOR_GRADIENT_TITLE_BLUE.b, 0.325F));
      ImageManager.getImage(Images.line_32_vertical)
         .draw(
            oSB,
            this.getPosX()
               + this.getWidth()
               - CFG.PADDING * 2
               - (int)((float)ImageManager.getImage(Images.economy).getWidth() * this.getImageScale(Images.economy))
               + iTranslateX,
            this.getPosY()
               + CFG.PADDING
               + (CFG.TEXT_HEIGHT - (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect))) / 2
               - ImageManager.getImage(Images.line_32_vertical).getHeight()
               + iTranslateY,
            1,
            (int)((float)ImageManager.getImage(Images.population).getHeight() * this.getImageScale(Images.population))
         );
      ImageManager.getImage(Images.line_32_vertical)
         .draw(
            oSB,
            this.getPosX()
               + this.getWidth()
               - CFG.PADDING * 2
               - (int)((float)ImageManager.getImage(Images.economy).getWidth() * this.getImageScale(Images.economy))
               + iTranslateX,
            this.getPosY()
               + this.getHeight()
               - CFG.PADDING
               - CFG.TEXT_HEIGHT
               + (CFG.TEXT_HEIGHT - (int)((float)ImageManager.getImage(Images.economy).getHeight() * this.getImageScale(Images.economy))) / 2
               - ImageManager.getImage(Images.line_32_vertical).getHeight()
               + iTranslateY,
            1,
            (int)((float)ImageManager.getImage(Images.economy).getHeight() * this.getImageScale(Images.economy))
         );
      oSB.setColor(Color.WHITE);

      try {
         if (this.iCivID >= 0) {
            CFG.game
               .getCiv(this.iCivID)
               .getFlag()
               .draw(
                  oSB,
                  this.getPosX() + this.ANIMATION_POSX + CFG.PADDING + 2 + iTranslateX,
                  this.getPosY()
                     + CFG.PADDING
                     + CFG.PADDING / 2
                     + (CFG.TEXT_HEIGHT - (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect))) / 2
                     - CFG.game.getCiv(this.iCivID).getFlag().getHeight()
                     + iTranslateY,
                  (int)((float)ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(Images.flag_rect)),
                  (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect))
               );
         } else {
            ImageManager.getImage(Images.randomCivilizationFlag)
               .draw(
                  oSB,
                  this.getPosX() + this.ANIMATION_POSX + CFG.PADDING + 2 + iTranslateX,
                  this.getPosY()
                     + CFG.PADDING
                     + CFG.PADDING / 2
                     + (CFG.TEXT_HEIGHT - (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect))) / 2
                     - ImageManager.getImage(Images.randomCivilizationFlag).getHeight()
                     + iTranslateY,
                  (int)((float)ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(Images.flag_rect)),
                  (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect))
               );
         }
      } catch (IndexOutOfBoundsException var10) {
         ImageManager.getImage(Images.randomCivilizationFlag)
            .draw(
               oSB,
               this.getPosX() + this.ANIMATION_POSX + CFG.PADDING + 2 + iTranslateX,
               this.getPosY()
                  + CFG.PADDING
                  + CFG.PADDING / 2
                  + (CFG.TEXT_HEIGHT - (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect))) / 2
                  - ImageManager.getImage(Images.randomCivilizationFlag).getHeight()
                  + iTranslateY,
               (int)((float)ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(Images.flag_rect)),
               (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect))
            );
      }

      ImageManager.getImage(Images.flag_rect)
         .draw(
            oSB,
            this.getPosX() + this.ANIMATION_POSX + CFG.PADDING + 2 + iTranslateX,
            this.getPosY()
               + CFG.PADDING
               + CFG.PADDING / 2
               + (CFG.TEXT_HEIGHT - (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect))) / 2
               - ImageManager.getImage(Images.flag_rect).getHeight()
               + iTranslateY,
            (int)((float)ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(Images.flag_rect)),
            (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect))
         );
      ImageManager.getImage(Images.population)
         .draw(
            oSB,
            this.getPosX()
               + this.getWidth()
               - CFG.PADDING
               - (int)((float)ImageManager.getImage(Images.economy).getWidth() * this.getImageScale(Images.economy)) / 2
               - (int)((float)ImageManager.getImage(Images.population).getWidth() * this.getImageScale(Images.population)) / 2
               + iTranslateX,
            this.getPosY()
               + CFG.PADDING
               + (CFG.TEXT_HEIGHT - (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect))) / 2
               - ImageManager.getImage(Images.population).getHeight()
               + iTranslateY,
            (int)((float)ImageManager.getImage(Images.population).getWidth() * this.getImageScale(Images.population)),
            (int)((float)ImageManager.getImage(Images.population).getHeight() * this.getImageScale(Images.population))
         );
      ImageManager.getImage(Images.economy)
         .draw(
            oSB,
            this.getPosX()
               + this.getWidth()
               - CFG.PADDING
               - (int)((float)ImageManager.getImage(Images.economy).getWidth() * this.getImageScale(Images.economy))
               + iTranslateX,
            this.getPosY()
               + this.getHeight()
               - CFG.PADDING
               - CFG.TEXT_HEIGHT
               + (CFG.TEXT_HEIGHT - (int)((float)ImageManager.getImage(Images.economy).getHeight() * this.getImageScale(Images.economy))) / 2
               - ImageManager.getImage(Images.economy).getHeight()
               + iTranslateY,
            (int)((float)ImageManager.getImage(Images.economy).getWidth() * this.getImageScale(Images.economy)),
            (int)((float)ImageManager.getImage(Images.economy).getHeight() * this.getImageScale(Images.economy))
         );
      Rectangle clipBounds = new Rectangle(
         (float)(
            this.getPosX()
               + this.ANIMATION_POSX
               + CFG.PADDING
               + (int)((float)ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(Images.flag_rect))
               + iTranslateX
         ),
         (float)(
            CFG.GAME_HEIGHT
               - (this.getPosY() + CFG.PADDING + CFG.PADDING / 2 + (int)(((float)CFG.TEXT_HEIGHT - (float)CFG.TEXT_HEIGHT * 0.75F) / 2.0F) + iTranslateY)
         ),
         (float)this.getMaxNameWidth(),
         (float)(-CFG.TEXT_HEIGHT)
      );
      oSB.flush();
      ScissorStack.pushScissors(clipBounds);
      CFG.fontMain.getData().setScale(0.75F);
      CFG.drawTextWithShadow(
         oSB,
         this.getText(),
         this.getPosX()
            + this.ANIMATION_POSX
            + CFG.PADDING * 2
            + (int)((float)ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(Images.flag_rect))
            + iTranslateX,
         this.getPosY() + CFG.PADDING + CFG.PADDING / 2 + (int)(((float)CFG.TEXT_HEIGHT - (float)CFG.TEXT_HEIGHT * 0.75F) / 2.0F) + iTranslateY,
         this.getColor(isActive)
      );

      try {
         oSB.flush();
         ScissorStack.popScissors();
      } catch (IllegalStateException var9) {
      }

      CFG.fontMain.getData().setScale(0.65F);
      CFG.drawTextWithShadow(
         oSB,
         "" + this.iCivilianDeaths,
         this.getPosX()
            + this.getWidth()
            - this.iCivilianDeathsWidth
            - CFG.PADDING * 3
            - (int)((float)ImageManager.getImage(Images.economy).getWidth() * this.getImageScale(Images.economy))
            + iTranslateX,
         this.getPosY() + CFG.PADDING + (int)(((float)CFG.TEXT_HEIGHT - (float)CFG.TEXT_HEIGHT * 0.65F) / 2.0F) + iTranslateY,
         this.oColorCivilianDeaths
      );
      CFG.drawTextWithShadow(
         oSB,
         "" + this.iEconomicLosses,
         this.getPosX()
            + this.getWidth()
            - this.iEconomicLossesWidth
            - CFG.PADDING * 3
            - (int)((float)ImageManager.getImage(Images.economy).getWidth() * this.getImageScale(Images.economy))
            + iTranslateX,
         this.getPosY()
            + this.getHeight()
            - CFG.PADDING
            - CFG.TEXT_HEIGHT
            + (int)(((float)CFG.TEXT_HEIGHT - (float)CFG.TEXT_HEIGHT * 0.65F) / 2.0F)
            + iTranslateY,
         this.oColorEconomicLosses
      );
      CFG.fontMain.getData().setScale(0.6F);
      CFG.drawTextWithShadow(
         oSB,
         "" + this.iParticipation + "%",
         this.getPosX()
            + this.ANIMATION_POSX
            + CFG.PADDING * 3
            + (int)Math.min((float)(this.getMaxNameWidth() - CFG.PADDING), (float)this.getTextWidth() * 0.75F)
            + (int)((float)ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(Images.flag_rect))
            + iTranslateX,
         this.getPosY()
            + CFG.PADDING
            + CFG.PADDING / 2
            + (int)(((float)CFG.TEXT_HEIGHT - (float)CFG.TEXT_HEIGHT * 0.75F) / 2.0F + (float)CFG.TEXT_HEIGHT * 0.75F - (float)CFG.TEXT_HEIGHT * 0.6F)
            + iTranslateY,
         this.oColorParticipation
      );
      CFG.drawTextWithShadow(
         oSB,
         "" + this.sProvinces,
         this.getPosX() + this.ANIMATION_POSX + CFG.PADDING + iTranslateX,
         this.getPosY()
            + this.getHeight()
            - CFG.PADDING
            - CFG.TEXT_HEIGHT
            + (int)(((float)CFG.TEXT_HEIGHT - (float)CFG.TEXT_HEIGHT * 0.6F) / 2.0F)
            + iTranslateY,
         new Color(CFG.COLOR_TEXT_MODIFIER_NEUTRAL.r, CFG.COLOR_TEXT_MODIFIER_NEUTRAL.g, CFG.COLOR_TEXT_MODIFIER_NEUTRAL.b, 0.85F)
      );
      CFG.fontMain.getData().setScale(1.0F);
      if (this.canPeaceOut && this.getIsHovered()) {
         if (this.ANIMATION_POSX < this.getTruceIconWidth()) {
            this.ANIMATION_POSX = (int)(Math.min((float)(System.currentTimeMillis() - this.ANIMATION_TIME) / 175.0F, 1.0F) * (float)this.getTruceIconWidth());
            CFG.setRender_3(true);
         }

         Rectangle clipBounds2 = new Rectangle(
            (float)(this.getPosX() + iTranslateX),
            (float)(CFG.GAME_HEIGHT - this.getPosY() - iTranslateY),
            (float)this.ANIMATION_POSX,
            (float)(-this.getHeight())
         );
         oSB.flush();
         ScissorStack.pushScissors(clipBounds2);
         oSB.setColor(new Color(CFG.COLOR_GRADIENT_TITLE_BLUE.r, CFG.COLOR_GRADIENT_TITLE_BLUE.g, CFG.COLOR_GRADIENT_TITLE_BLUE.b, 0.325F));
         ImageManager.getImage(Images.gradient)
            .draw(
               oSB,
               this.getPosX() + iTranslateX,
               this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY,
               this.getTruceIconWidth(),
               this.getHeight()
            );
         ImageManager.getImage(Images.line_32_off1)
            .draw(
               oSB,
               this.getPosX() + iTranslateX,
               this.getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
               this.getTruceIconWidth(),
               this.getHeight()
            );
         oSB.setColor(new Color(CFG.COLOR_GRADIENT_TITLE_BLUE.r, CFG.COLOR_GRADIENT_TITLE_BLUE.g, CFG.COLOR_GRADIENT_TITLE_BLUE.b, 0.675F));
         ImageManager.getImage(Images.line_32_vertical)
            .draw(
               oSB,
               this.getPosX() + this.getTruceIconWidth() - 1 + iTranslateX,
               this.getPosY() + CFG.PADDING - ImageManager.getImage(Images.line_32_vertical).getHeight() + iTranslateY,
               1,
               this.getHeight() - CFG.PADDING * 2
            );
         if (this.lTime < System.currentTimeMillis() - 30L) {
            if (this.backAnimation) {
               this.fAlphaMod -= 0.02F;
               if (this.fAlphaMod < 0.0F) {
                  this.backAnimation = false;
               }
            } else {
               this.fAlphaMod += 0.02F;
               if (this.fAlphaMod > 0.4F) {
                  this.backAnimation = true;
               }
            }

            this.lTime = System.currentTimeMillis();
         }

         oSB.setColor(new Color(1.0F, 1.0F, 1.0F, 1.0F - this.fAlphaMod));
         CFG.setRender_3(true);
         ImageManager.getImage(Images.diplo_truce)
            .draw(
               oSB,
               this.getPosX() + this.getTruceIconWidth() / 2 - ImageManager.getImage(Images.diplo_truce).getWidth() / 2 + iTranslateX,
               this.getPosY() + this.getHeight() / 2 - ImageManager.getImage(Images.diplo_truce).getHeight() / 2 + iTranslateY
            );

         try {
            oSB.flush();
            ScissorStack.popScissors();
         } catch (IllegalStateException var8) {
         }
      } else {
         this.backAnimation = false;
         this.fAlphaMod = 0.0F;
         this.lTime = System.currentTimeMillis();
      }
   }

   @Override
   protected void buildElementHover() {
      List<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
      List<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
      if (this.canPeaceOut) {
         nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("PeaceNegotiations"), CFG.COLOR_TEXT_NUM_OF_PROVINCES));
         nData.add(new MenuElement_Hover_v2_Element_Type_Flag(this.getCurrent(), CFG.PADDING, 0));
         nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.diplo_truce, CFG.PADDING, 0));
         nData.add(new MenuElement_Hover_v2_Element_Type_Flag(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), CFG.PADDING, 0));
         nElements.add(new MenuElement_Hover_v2_Element2(nData));
         nData.clear();
         nData.add(new MenuElement_Hover_v2_Element_Type_Space());
         nElements.add(new MenuElement_Hover_v2_Element2(nData));
         nData.clear();
      }

      nData.add(new MenuElement_Hover_v2_Element_Type_Flag(this.iCivID));
      nData.add(new MenuElement_Hover_v2_Element_Type_Text(this.getText(), CFG.COLOR_TEXT_NUM_OF_PROVINCES));
      nElements.add(new MenuElement_Hover_v2_Element2(nData));
      nData.clear();
      nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + this.sProvinces, CFG.COLOR_TEXT_MODIFIER_NEUTRAL));
      nElements.add(new MenuElement_Hover_v2_Element2(nData));
      nData.clear();
      nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Participation") + ": "));
      nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + this.iParticipation + "%", CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
      nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.diplo_rivals, CFG.PADDING, 0));
      nElements.add(new MenuElement_Hover_v2_Element2(nData));
      nData.clear();
      nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("CivilianDeaths") + ": "));
      nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + this.iCivilianDeaths, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2));
      nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.population, CFG.PADDING, 0));
      nElements.add(new MenuElement_Hover_v2_Element2(nData));
      nData.clear();
      nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("EconomicLosses") + ": "));
      nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + this.iEconomicLosses, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2));
      nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.economy, CFG.PADDING, 0));
      nElements.add(new MenuElement_Hover_v2_Element2(nData));
      nData.clear();
      nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("WarWeariness") + ": "));
      nData.add(
         new MenuElement_Hover_v2_Element_Type_Text(
            "" + (float)((int)(CFG.game.getCiv(this.iCivID).getWarWeariness() * 10000.0F)) / 100.0F + "%", CFG.COLOR_TEXT_MODIFIER_NEUTRAL2
         )
      );
      nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.diplo_weariness, CFG.PADDING, 0));
      nElements.add(new MenuElement_Hover_v2_Element2(nData));
      nData.clear();
      this.menuElementHover = new MenuElement_Hover_v2(nElements);
   }

   @Override
   protected Color getColor(boolean isActive) {
      return isActive ? CFG.COLOR_TEXT_OPTIONS_NS_HOVER : (this.getIsHovered() ? CFG.COLOR_TEXT_OPTIONS_NS : Color.WHITE);
   }

   protected final float getImageScale(int nImageID) {
      return (float)CFG.TEXT_HEIGHT / (float)ImageManager.getImage(nImageID).getHeight() < 1.0F
         ? (float)CFG.TEXT_HEIGHT / (float)ImageManager.getImage(nImageID).getHeight()
         : 1.0F;
   }

   @Override
   protected void setVisible(boolean isVisible) {
      super.setVisible(isVisible);
      if (!isVisible) {
      }
   }

   @Override
   protected int getCurrent() {
      return this.iCivID;
   }

   @Override
   protected void setIsHovered(boolean isHovered) {
      super.setIsHovered(isHovered);
      if (this.canPeaceOut) {
         if (this.getIsHovered()) {
            this.ANIMATION_TIME = System.currentTimeMillis();
            this.ANIMATION_POSX = 0;
         } else {
            this.ANIMATION_TIME = 0L;
            this.ANIMATION_POSX = 0;
         }
      }
   }

   protected int getTruceIconWidth() {
      return ImageManager.getImage(Images.diplo_truce).getWidth() + CFG.PADDING * 4;
   }

   @Override
   protected void actionElement(int iID) {
      try {
         if (this.canPeaceOut) {
            int nWarID = CFG.game.getWarID(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID(), this.getCurrent());
            if (nWarID >= 0) {
               CFG.game.getPlayer(CFG.PLAYER_TURNID).iBefore_ActiveProvince = CFG.game.getActiveProvinceID();
               CFG.game.getPlayer(CFG.PLAYER_TURNID).iACTIVE_VIEW_MODE = CFG.viewsManager.getActiveViewID();
               CFG.viewsManager.disableAllViews();
               List<Boolean> lDefenders = new ArrayList<>();
               List<Boolean> lAggressors = new ArrayList<>();
               if (CFG.game.getWar(nWarID).getIsAggressor(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())) {
                  for(int i = 0; i < CFG.game.getWar(nWarID).getAggressorsSize(); ++i) {
                     lAggressors.add(true);
                  }

                  for(int i = 0; i < CFG.game.getWar(nWarID).getDefendersSize(); ++i) {
                     lDefenders.add(CFG.game.getWar(nWarID).getDefenderID(i).getCivID() == this.getCurrent());
                  }
               } else {
                  for(int i = 0; i < CFG.game.getWar(nWarID).getAggressorsSize(); ++i) {
                     lAggressors.add(CFG.game.getWar(nWarID).getAggressorID(i).getCivID() == this.getCurrent());
                  }

                  for(int i = 0; i < CFG.game.getWar(nWarID).getDefendersSize(); ++i) {
                     lDefenders.add(true);
                  }
               }

               Menu_PeaceTreaty.WAR_ID = nWarID;
               CFG.peaceTreatyData = new PeaceTreaty_Data(
                  Menu_PeaceTreaty.WAR_ID, lDefenders, lAggressors, CFG.game.getWar(nWarID).getIsAggressor(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID())
               );
               CFG.game.resetChooseProvinceData_Immediately();
               CFG.game.resetRegroupArmyData();
               CFG.menuManager.setViewID(Menu.eINGAME_PEACE_TREATY);
            }
         } else {
            CFG.toast.setInView(CFG.game.getCiv(this.getCurrent()).getCivName(), CFG.COLOR_TEXT_NUM_OF_PROVINCES);
            if (CFG.FOG_OF_WAR == 2) {
               if (CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetCivilization(this.getCurrent())
                  && CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetProvince(CFG.game.getCiv(this.getCurrent()).getCapitalProvinceID())) {
                  CFG.game.setActiveProvinceID(CFG.game.getCiv(this.getCurrent()).getCapitalProvinceID());
                  CFG.map.getMapCoordinates().centerToProvinceID(CFG.game.getActiveProvinceID());
               }
            } else {
               CFG.game.setActiveProvinceID(CFG.game.getCiv(this.getCurrent()).getCapitalProvinceID());
               CFG.map.getMapCoordinates().centerToProvinceID(CFG.game.getActiveProvinceID());
            }

            if (CFG.viewsManager.getActiveViewID() == ViewsManager.VIEW_DIPLOMACY_MODE) {
               CFG.game.disableDrawCivilizationRegions_Active();
               CFG.game.enableDrawCivilizationRegions_ActiveProvince();
            }
         }
      } catch (IndexOutOfBoundsException var6) {
      }
   }

   @Override
   protected int getSFX() {
      return this.canPeaceOut ? SoundsManager.SOUND_CLICK2 : super.getSFX();
   }
}
