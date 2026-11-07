package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.GdxRuntimeException;
import java.util.ArrayList;
import java.util.List;

public class Button_Diplomacy_Flag extends Button_Statistics {
   protected static final float FONT_SCALE = 0.75F;
   protected int iCivID;
   protected Image civFlag = null;

   protected Button_Diplomacy_Flag(int nCivID, int iPosX, int iPosY, int iWidth) {
      super(CFG.game.getCiv(nCivID).getCivName(), 0, iPosX, iPosY, iWidth, CFG.TEXT_HEIGHT * 2 + CFG.PADDING * 4);
      this.iCivID = nCivID;
      if (this.iCivID >= 0) {
         try {
            try {
               this.civFlag = new Image(
                  new Texture(Gdx.files.internal("game/flagsH/" + CFG.game.getCiv(this.iCivID).getCivTag() + ".png")), Texture.TextureFilter.Linear
               );
            } catch (GdxRuntimeException var12) {
               try {
                  try {
                     this.civFlag = new Image(
                        new Texture(Gdx.files.internal("game/flagsH/" + CFG.ideologiesManager.getRealTag(CFG.game.getCiv(this.iCivID).getCivTag()) + ".png")),
                        Texture.TextureFilter.Linear
                     );
                  } catch (GdxRuntimeException var10) {
                     try {
                        this.civFlag = new Image(
                           new Texture(Gdx.files.internal("game/flags/" + CFG.game.getCiv(this.iCivID).getCivTag() + ".png")), Texture.TextureFilter.Linear
                        );
                     } catch (GdxRuntimeException var9) {
                        this.civFlag = new Image(
                           new Texture(Gdx.files.internal("game/flags/" + CFG.ideologiesManager.getRealTag(CFG.game.getCiv(this.iCivID).getCivTag()) + ".png")),
                           Texture.TextureFilter.Linear
                        );
                     }
                  }
               } catch (GdxRuntimeException var11) {
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
                     } catch (GdxRuntimeException var8) {
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
         } catch (GdxRuntimeException var13) {
            this.civFlag = null;
         } catch (OutOfMemoryError var14) {
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
                  this.getPosX() + iTranslateX,
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
                  this.getPosX() + iTranslateX,
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
                  this.getPosX() + iTranslateX,
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
      } catch (IndexOutOfBoundsException var7) {
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
            this.getPosX() + this.getWidth() - CFG.PADDING - 2 - ImageManager.getImage(Images.diplo_rivals).getWidth() / 2 + iTranslateX,
            this.getPosY() + CFG.PADDING + CFG.PADDING / 2 - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY,
            2,
            (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect))
         );
      oSB.setColor(Color.WHITE);

      try {
         CFG.game
            .getCiv(this.iCivID)
            .getFlag()
            .draw(
               oSB,
               this.getPosX()
                  + this.getWidth()
                  - CFG.PADDING
                  - 2
                  - (int)((float)ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(Images.flag_rect))
                  - ImageManager.getImage(Images.diplo_rivals).getWidth() / 2
                  + iTranslateX,
               this.getPosY()
                  + CFG.PADDING
                  + CFG.PADDING / 2
                  + (CFG.TEXT_HEIGHT - (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect))) / 2
                  - CFG.game.getCiv(this.iCivID).getFlag().getHeight()
                  + iTranslateY,
               (int)((float)ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(Images.flag_rect)),
               (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect))
            );
      } catch (IndexOutOfBoundsException var6) {
         ImageManager.getImage(Images.randomCivilizationFlag)
            .draw(
               oSB,
               this.getPosX()
                  + this.getWidth()
                  - CFG.PADDING
                  - 2
                  - (int)((float)ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(Images.flag_rect))
                  - ImageManager.getImage(Images.diplo_rivals).getWidth() / 2
                  + iTranslateX,
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
            this.getPosX()
               + this.getWidth()
               - CFG.PADDING
               - 2
               - (int)((float)ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(Images.flag_rect))
               - ImageManager.getImage(Images.diplo_rivals).getWidth() / 2
               + iTranslateX,
            this.getPosY()
               + CFG.PADDING
               + CFG.PADDING / 2
               + (CFG.TEXT_HEIGHT - (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect))) / 2
               - ImageManager.getImage(Images.flag_rect).getHeight()
               + iTranslateY,
            (int)((float)ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(Images.flag_rect)),
            (int)((float)ImageManager.getImage(Images.flag_rect).getHeight() * this.getImageScale(Images.flag_rect))
         );
      CFG.fontMain.getData().setScale(0.75F);
      CFG.drawTextWithShadow(
         oSB,
         this.getText(),
         this.getPosX()
            + this.getWidth()
            - CFG.PADDING
            - 2
            - (int)((float)ImageManager.getImage(Images.flag_rect).getWidth() * this.getImageScale(Images.flag_rect))
            - ImageManager.getImage(Images.diplo_rivals).getWidth() / 2
            - (int)((float)this.getTextWidth() * 0.75F)
            - CFG.PADDING
            + iTranslateX,
         this.getPosY() + CFG.PADDING + CFG.PADDING / 2 + (int)(((float)CFG.TEXT_HEIGHT - (float)CFG.TEXT_HEIGHT * 0.75F) / 2.0F) + iTranslateY,
         this.getColor(isActive)
      );
      CFG.fontMain.getData().setScale(1.0F);
   }

   @Override
   protected void buildElementHover() {
      List<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
      List<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
      nData.add(new MenuElement_Hover_v2_Element_Type_Flag(this.iCivID));
      nData.add(new MenuElement_Hover_v2_Element_Type_Text(this.getText(), CFG.COLOR_TEXT_NUM_OF_PROVINCES));
      nElements.add(new MenuElement_Hover_v2_Element2(nData));
      nData.clear();
      this.menuElementHover = new MenuElement_Hover_v2(nElements);
   }

   @Override
   protected Color getColor(boolean isActive) {
      return isActive ? CFG.COLOR_TEXT_OPTIONS_NS_ACTIVE : (this.getIsHovered() ? CFG.COLOR_TEXT_OPTIONS_NS_HOVER : CFG.COLOR_TEXT_OPTIONS_NS);
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
}
