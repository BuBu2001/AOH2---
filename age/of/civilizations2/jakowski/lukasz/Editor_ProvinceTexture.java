package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.PixmapIO;

class Editor_ProvinceTexture extends Editor {
   private int button;
   private int iBrushScale = 1;
   private boolean theDoubleMode = false;

   @Override
   protected void keyDown(int keycode) {
      if (Gdx.input.isKeyPressed(19)) {
         this.theDoubleMode = !this.theDoubleMode;
         if (CFG.game.getActiveProvinceID() != CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1) {
            CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV2 = CFG.game.getActiveProvinceID();
         }
      }

      if (Gdx.input.isKeyPressed(67) || Gdx.input.isKeyPressed(66)) {
         CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1 = -1;
         CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV2 = -1;
      }

      if (Gdx.input.isKeyPressed(44)) {
         if (CFG.MANAGE_DIPLOMACY_CUSTOMIZE_ALLIANCE_ID == 0) {
            CFG.MANAGE_DIPLOMACY_CUSTOMIZE_ALLIANCE_ID = -1;
         } else {
            CFG.MANAGE_DIPLOMACY_CUSTOMIZE_ALLIANCE_ID = 0;
         }
      }

      if (Gdx.input.isKeyPressed(21)) {
         --this.iBrushScale;
         if (this.iBrushScale < 1) {
            this.iBrushScale = 1;
         }
      } else if (Gdx.input.isKeyPressed(22)) {
         ++this.iBrushScale;
         if (this.iBrushScale > 3) {
            this.iBrushScale = 3;
         }
      }

      if (Gdx.input.isKeyPressed(62)) {
         CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1 = CFG.game.getActiveProvinceID();
      }

      if (Gdx.input.isKeyPressed(41)) {
         int tempID = CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1;
         CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1 = CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV2;
         CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV2 = tempID;
      }

      if (Gdx.input.isKeyPressed(20) && CFG.game.getActiveProvinceID() != CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1) {
         CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV2 = CFG.game.getActiveProvinceID();
      }

      if (Gdx.input.isKeyPressed(46) && CFG.game.getActiveProvinceID() >= 0) {
         CFG.game.getProvince(CFG.game.getActiveProvinceID()).buildProvinceBG(true);
         CFG.game.getProvince(CFG.game.getActiveProvinceID()).loadProvinceBG();
      }

      if (Gdx.input.isKeyPressed(49) && CFG.game.getActiveProvinceID() >= 0) {
         int provID = CFG.game.getActiveProvinceID();
         if (provID >= 0 && !CFG.game.getProvince(provID).getSeaProvince()) {
            Pixmap wtf = PixmapIO.readCIM(
               Gdx.files
                  .local(
                     "map/"
                        + CFG.map.getFile_ActiveMap_Path()
                        + "data/"
                        + "scales/"
                        + "provinces/"
                        + CFG.map.getMapDefaultScale(CFG.map.getActiveMapID())
                        + "/"
                        + provID
                  )
            );
            Pixmap wtf2 = PixmapIO.readCIM(
               Gdx.files
                  .local("map/" + CFG.map.getFile_ActiveMap_Path() + "data/" + "scales/" + "provinces/" + CFG.map.getMapBG().getMapScale() + "/" + provID)
            );
            Pixmap pixmap = null;
            int screenX = 0;
            int screenY = 0;
            Pixmap omg = new Pixmap(1, 1, Pixmap.Format.LuminanceAlpha);
            omg.setColor(new Color(1.0F, 1.0F, 1.0F, 1.0F));
            omg.drawPixel(0, 0);
            pixmap = new Pixmap(wtf2.getWidth(), wtf2.getHeight(), Pixmap.Format.LuminanceAlpha);
            pixmap.setColor(new Color(1.0F, 1.0F, 1.0F, 1.0F));

            for(int yi = 0; yi < pixmap.getHeight(); ++yi) {
               for(int xi = 0; xi < pixmap.getWidth(); ++xi) {
                  if (omg.getPixel(0, 0)
                     == wtf.getPixel(
                        (int)((float)xi * ((float)CFG.map.getMapDefaultScale(CFG.map.getActiveMapID()) / (float)CFG.map.getMapBG().getMapScale())),
                        (int)((float)yi * ((float)CFG.map.getMapDefaultScale(CFG.map.getActiveMapID()) / (float)CFG.map.getMapBG().getMapScale()))
                     )) {
                     pixmap.drawPixel(xi, yi);
                  }
               }
            }

            if (pixmap == null) {
               return;
            }

            CFG.game.getProvince(provID).setBG(pixmap);
            PixmapIO.writeCIM(
               Gdx.files
                  .local("map/" + CFG.map.getFile_ActiveMap_Path() + "data/" + "scales/" + "provinces/" + CFG.map.getMapBG().getMapScale() + "/" + provID),
               pixmap
            );
         }
      }
   }

   @Override
   protected void touchDown(int screenX, int screenY, int pointer, int button) {
      if ((screenX > CFG.BUTTON_WIDTH * 2 + CFG.PADDING * 2 || screenY < CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING * 2)
         && CFG.MANAGE_DIPLOMACY_CUSTOMIZE_ALLIANCE_ID >= 0) {
         if (CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1 >= 0 && CFG.MANAGE_DIPLOMACY_CUSTOMIZE_ALLIANCE_ID == 0) {
            CFG.map.getMapCoordinates().setDisableMovingMap(true);
         }

         this.button = button;
         if (this.theDoubleMode) {
            this.dragged(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1, screenX, screenY, button == 1);
            this.dragged(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV2, screenX, screenY, button != 1);
         } else {
            this.dragged(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1, screenX, screenY, button == 1);
         }
      } else {
         CFG.MANAGE_DIPLOMACY_CUSTOMIZE_ALLIANCE_ID = -1;
         CFG.map.getMapCoordinates().setDisableMovingMap(false);
      }
   }

   @Override
   protected void touchDragged(int screenX, int screenY, int pointer) {
      if ((screenX > CFG.BUTTON_WIDTH * 2 + CFG.PADDING * 2 || screenY < CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING * 2)
         && CFG.MANAGE_DIPLOMACY_CUSTOMIZE_ALLIANCE_ID >= 0) {
         if (this.theDoubleMode) {
            this.dragged(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1, screenX, screenY, this.button == 1);
            this.dragged(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV2, screenX, screenY, this.button != 1);
            if (this.iBrushScale == 2) {
               this.dragged(
                  CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1,
                  screenX - (int)(1.0F * CFG.map.getMapScale().getCurrentScale()),
                  screenY - (int)(1.0F * CFG.map.getMapScale().getCurrentScale()),
                  this.button == 1
               );
               this.dragged(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1, screenX, screenY - (int)(1.0F * CFG.map.getMapScale().getCurrentScale()), this.button == 1);
               this.dragged(
                  CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1,
                  screenX + (int)(1.0F * CFG.map.getMapScale().getCurrentScale()),
                  screenY - (int)(1.0F * CFG.map.getMapScale().getCurrentScale()),
                  this.button == 1
               );
               this.dragged(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1, screenX - (int)(1.0F * CFG.map.getMapScale().getCurrentScale()), screenY, this.button == 1);
               this.dragged(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1, screenX + (int)(1.0F * CFG.map.getMapScale().getCurrentScale()), screenY, this.button == 1);
               this.dragged(
                  CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1,
                  screenX - (int)(1.0F * CFG.map.getMapScale().getCurrentScale()),
                  screenY + (int)(1.0F * CFG.map.getMapScale().getCurrentScale()),
                  this.button == 1
               );
               this.dragged(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1, screenX, screenY + (int)(1.0F * CFG.map.getMapScale().getCurrentScale()), this.button == 1);
               this.dragged(
                  CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1,
                  screenX + (int)(1.0F * CFG.map.getMapScale().getCurrentScale()),
                  screenY + (int)(1.0F * CFG.map.getMapScale().getCurrentScale()),
                  this.button == 1
               );
               this.dragged(
                  CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV2,
                  screenX - (int)(1.0F * CFG.map.getMapScale().getCurrentScale()),
                  screenY - (int)(1.0F * CFG.map.getMapScale().getCurrentScale()),
                  this.button != 1
               );
               this.dragged(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV2, screenX, screenY - (int)(1.0F * CFG.map.getMapScale().getCurrentScale()), this.button != 1);
               this.dragged(
                  CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV2,
                  screenX + (int)(1.0F * CFG.map.getMapScale().getCurrentScale()),
                  screenY - (int)(1.0F * CFG.map.getMapScale().getCurrentScale()),
                  this.button != 1
               );
               this.dragged(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV2, screenX - (int)(1.0F * CFG.map.getMapScale().getCurrentScale()), screenY, this.button != 1);
               this.dragged(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV2, screenX + (int)(1.0F * CFG.map.getMapScale().getCurrentScale()), screenY, this.button != 1);
               this.dragged(
                  CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV2,
                  screenX - (int)(1.0F * CFG.map.getMapScale().getCurrentScale()),
                  screenY + (int)(1.0F * CFG.map.getMapScale().getCurrentScale()),
                  this.button != 1
               );
               this.dragged(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV2, screenX, screenY + (int)(1.0F * CFG.map.getMapScale().getCurrentScale()), this.button != 1);
               this.dragged(
                  CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV2,
                  screenX + (int)(1.0F * CFG.map.getMapScale().getCurrentScale()),
                  screenY + (int)(1.0F * CFG.map.getMapScale().getCurrentScale()),
                  this.button != 1
               );
            } else if (this.iBrushScale == 3) {
               this.dragged(
                  CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1,
                  screenX - (int)(2.0F * CFG.map.getMapScale().getCurrentScale()),
                  screenY - (int)(2.0F * CFG.map.getMapScale().getCurrentScale()),
                  this.button == 1
               );
               this.dragged(
                  CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1,
                  screenX - (int)(1.0F * CFG.map.getMapScale().getCurrentScale()),
                  screenY - (int)(2.0F * CFG.map.getMapScale().getCurrentScale()),
                  this.button == 1
               );
               this.dragged(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1, screenX, screenY - (int)(2.0F * CFG.map.getMapScale().getCurrentScale()), this.button == 1);
               this.dragged(
                  CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1,
                  screenX + (int)(1.0F * CFG.map.getMapScale().getCurrentScale()),
                  screenY - (int)(2.0F * CFG.map.getMapScale().getCurrentScale()),
                  this.button == 1
               );
               this.dragged(
                  CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1,
                  screenX + (int)(2.0F * CFG.map.getMapScale().getCurrentScale()),
                  screenY - (int)(2.0F * CFG.map.getMapScale().getCurrentScale()),
                  this.button == 1
               );
               this.dragged(
                  CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1,
                  screenX - (int)(2.0F * CFG.map.getMapScale().getCurrentScale()),
                  screenY - (int)(1.0F * CFG.map.getMapScale().getCurrentScale()),
                  this.button == 1
               );
               this.dragged(
                  CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1,
                  screenX - (int)(1.0F * CFG.map.getMapScale().getCurrentScale()),
                  screenY - (int)(1.0F * CFG.map.getMapScale().getCurrentScale()),
                  this.button == 1
               );
               this.dragged(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1, screenX, screenY - (int)(1.0F * CFG.map.getMapScale().getCurrentScale()), this.button == 1);
               this.dragged(
                  CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1,
                  screenX + (int)(1.0F * CFG.map.getMapScale().getCurrentScale()),
                  screenY - (int)(1.0F * CFG.map.getMapScale().getCurrentScale()),
                  this.button == 1
               );
               this.dragged(
                  CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1,
                  screenX + (int)(2.0F * CFG.map.getMapScale().getCurrentScale()),
                  screenY - (int)(1.0F * CFG.map.getMapScale().getCurrentScale()),
                  this.button == 1
               );
               this.dragged(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1, screenX - (int)(2.0F * CFG.map.getMapScale().getCurrentScale()), screenY, this.button == 1);
               this.dragged(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1, screenX - (int)(1.0F * CFG.map.getMapScale().getCurrentScale()), screenY, this.button == 1);
               this.dragged(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1, screenX + (int)(1.0F * CFG.map.getMapScale().getCurrentScale()), screenY, this.button == 1);
               this.dragged(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1, screenX + (int)(2.0F * CFG.map.getMapScale().getCurrentScale()), screenY, this.button == 1);
               this.dragged(
                  CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1,
                  screenX - (int)(2.0F * CFG.map.getMapScale().getCurrentScale()),
                  screenY + (int)(1.0F * CFG.map.getMapScale().getCurrentScale()),
                  this.button == 1
               );
               this.dragged(
                  CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1,
                  screenX - (int)(1.0F * CFG.map.getMapScale().getCurrentScale()),
                  screenY + (int)(1.0F * CFG.map.getMapScale().getCurrentScale()),
                  this.button == 1
               );
               this.dragged(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1, screenX, screenY + (int)(1.0F * CFG.map.getMapScale().getCurrentScale()), this.button == 1);
               this.dragged(
                  CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1,
                  screenX + (int)(1.0F * CFG.map.getMapScale().getCurrentScale()),
                  screenY + (int)(1.0F * CFG.map.getMapScale().getCurrentScale()),
                  this.button == 1
               );
               this.dragged(
                  CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1,
                  screenX + (int)(2.0F * CFG.map.getMapScale().getCurrentScale()),
                  screenY + (int)(1.0F * CFG.map.getMapScale().getCurrentScale()),
                  this.button == 1
               );
               this.dragged(
                  CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1,
                  screenX - (int)(2.0F * CFG.map.getMapScale().getCurrentScale()),
                  screenY + (int)(2.0F * CFG.map.getMapScale().getCurrentScale()),
                  this.button == 1
               );
               this.dragged(
                  CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1,
                  screenX - (int)(1.0F * CFG.map.getMapScale().getCurrentScale()),
                  screenY + (int)(2.0F * CFG.map.getMapScale().getCurrentScale()),
                  this.button == 1
               );
               this.dragged(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1, screenX, screenY + (int)(2.0F * CFG.map.getMapScale().getCurrentScale()), this.button == 1);
               this.dragged(
                  CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1,
                  screenX + (int)(1.0F * CFG.map.getMapScale().getCurrentScale()),
                  screenY + (int)(2.0F * CFG.map.getMapScale().getCurrentScale()),
                  this.button == 1
               );
               this.dragged(
                  CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1,
                  screenX + (int)(2.0F * CFG.map.getMapScale().getCurrentScale()),
                  screenY + (int)(2.0F * CFG.map.getMapScale().getCurrentScale()),
                  this.button == 1
               );
               this.dragged(
                  CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV2,
                  screenX - (int)(2.0F * CFG.map.getMapScale().getCurrentScale()),
                  screenY - (int)(2.0F * CFG.map.getMapScale().getCurrentScale()),
                  this.button != 1
               );
               this.dragged(
                  CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV2,
                  screenX - (int)(1.0F * CFG.map.getMapScale().getCurrentScale()),
                  screenY - (int)(2.0F * CFG.map.getMapScale().getCurrentScale()),
                  this.button != 1
               );
               this.dragged(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV2, screenX, screenY - (int)(2.0F * CFG.map.getMapScale().getCurrentScale()), this.button != 1);
               this.dragged(
                  CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV2,
                  screenX + (int)(1.0F * CFG.map.getMapScale().getCurrentScale()),
                  screenY - (int)(2.0F * CFG.map.getMapScale().getCurrentScale()),
                  this.button != 1
               );
               this.dragged(
                  CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV2,
                  screenX + (int)(2.0F * CFG.map.getMapScale().getCurrentScale()),
                  screenY - (int)(2.0F * CFG.map.getMapScale().getCurrentScale()),
                  this.button != 1
               );
               this.dragged(
                  CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV2,
                  screenX - (int)(2.0F * CFG.map.getMapScale().getCurrentScale()),
                  screenY - (int)(1.0F * CFG.map.getMapScale().getCurrentScale()),
                  this.button != 1
               );
               this.dragged(
                  CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV2,
                  screenX - (int)(1.0F * CFG.map.getMapScale().getCurrentScale()),
                  screenY - (int)(1.0F * CFG.map.getMapScale().getCurrentScale()),
                  this.button != 1
               );
               this.dragged(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV2, screenX, screenY - (int)(1.0F * CFG.map.getMapScale().getCurrentScale()), this.button != 1);
               this.dragged(
                  CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV2,
                  screenX + (int)(1.0F * CFG.map.getMapScale().getCurrentScale()),
                  screenY - (int)(1.0F * CFG.map.getMapScale().getCurrentScale()),
                  this.button != 1
               );
               this.dragged(
                  CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV2,
                  screenX + (int)(2.0F * CFG.map.getMapScale().getCurrentScale()),
                  screenY - (int)(1.0F * CFG.map.getMapScale().getCurrentScale()),
                  this.button != 1
               );
               this.dragged(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV2, screenX - (int)(2.0F * CFG.map.getMapScale().getCurrentScale()), screenY, this.button != 1);
               this.dragged(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV2, screenX - (int)(1.0F * CFG.map.getMapScale().getCurrentScale()), screenY, this.button != 1);
               this.dragged(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV2, screenX + (int)(1.0F * CFG.map.getMapScale().getCurrentScale()), screenY, this.button != 1);
               this.dragged(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV2, screenX + (int)(2.0F * CFG.map.getMapScale().getCurrentScale()), screenY, this.button != 1);
               this.dragged(
                  CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV2,
                  screenX - (int)(2.0F * CFG.map.getMapScale().getCurrentScale()),
                  screenY + (int)(1.0F * CFG.map.getMapScale().getCurrentScale()),
                  this.button != 1
               );
               this.dragged(
                  CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV2,
                  screenX - (int)(1.0F * CFG.map.getMapScale().getCurrentScale()),
                  screenY + (int)(1.0F * CFG.map.getMapScale().getCurrentScale()),
                  this.button != 1
               );
               this.dragged(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV2, screenX, screenY + (int)(1.0F * CFG.map.getMapScale().getCurrentScale()), this.button != 1);
               this.dragged(
                  CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV2,
                  screenX + (int)(1.0F * CFG.map.getMapScale().getCurrentScale()),
                  screenY + (int)(1.0F * CFG.map.getMapScale().getCurrentScale()),
                  this.button != 1
               );
               this.dragged(
                  CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV2,
                  screenX + (int)(2.0F * CFG.map.getMapScale().getCurrentScale()),
                  screenY + (int)(1.0F * CFG.map.getMapScale().getCurrentScale()),
                  this.button != 1
               );
               this.dragged(
                  CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV2,
                  screenX - (int)(2.0F * CFG.map.getMapScale().getCurrentScale()),
                  screenY + (int)(2.0F * CFG.map.getMapScale().getCurrentScale()),
                  this.button != 1
               );
               this.dragged(
                  CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV2,
                  screenX - (int)(1.0F * CFG.map.getMapScale().getCurrentScale()),
                  screenY + (int)(2.0F * CFG.map.getMapScale().getCurrentScale()),
                  this.button != 1
               );
               this.dragged(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV2, screenX, screenY + (int)(2.0F * CFG.map.getMapScale().getCurrentScale()), this.button != 1);
               this.dragged(
                  CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV2,
                  screenX + (int)(1.0F * CFG.map.getMapScale().getCurrentScale()),
                  screenY + (int)(2.0F * CFG.map.getMapScale().getCurrentScale()),
                  this.button != 1
               );
               this.dragged(
                  CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV2,
                  screenX + (int)(2.0F * CFG.map.getMapScale().getCurrentScale()),
                  screenY + (int)(2.0F * CFG.map.getMapScale().getCurrentScale()),
                  this.button != 1
               );
            }
         } else {
            this.dragged(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1, screenX, screenY, this.button == 1);
            if (this.iBrushScale == 2) {
               this.dragged(
                  CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1,
                  screenX - (int)(1.0F * CFG.map.getMapScale().getCurrentScale()),
                  screenY - (int)(1.0F * CFG.map.getMapScale().getCurrentScale()),
                  this.button == 1
               );
               this.dragged(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1, screenX, screenY - (int)(1.0F * CFG.map.getMapScale().getCurrentScale()), this.button == 1);
               this.dragged(
                  CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1,
                  screenX + (int)(1.0F * CFG.map.getMapScale().getCurrentScale()),
                  screenY - (int)(1.0F * CFG.map.getMapScale().getCurrentScale()),
                  this.button == 1
               );
               this.dragged(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1, screenX - (int)(1.0F * CFG.map.getMapScale().getCurrentScale()), screenY, this.button == 1);
               this.dragged(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1, screenX + (int)(1.0F * CFG.map.getMapScale().getCurrentScale()), screenY, this.button == 1);
               this.dragged(
                  CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1,
                  screenX - (int)(1.0F * CFG.map.getMapScale().getCurrentScale()),
                  screenY + (int)(1.0F * CFG.map.getMapScale().getCurrentScale()),
                  this.button == 1
               );
               this.dragged(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1, screenX, screenY + (int)(1.0F * CFG.map.getMapScale().getCurrentScale()), this.button == 1);
               this.dragged(
                  CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1,
                  screenX + (int)(1.0F * CFG.map.getMapScale().getCurrentScale()),
                  screenY + (int)(1.0F * CFG.map.getMapScale().getCurrentScale()),
                  this.button == 1
               );
            } else if (this.iBrushScale == 3) {
               this.dragged(
                  CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1,
                  screenX - (int)(2.0F * CFG.map.getMapScale().getCurrentScale()),
                  screenY - (int)(2.0F * CFG.map.getMapScale().getCurrentScale()),
                  this.button == 1
               );
               this.dragged(
                  CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1,
                  screenX - (int)(1.0F * CFG.map.getMapScale().getCurrentScale()),
                  screenY - (int)(2.0F * CFG.map.getMapScale().getCurrentScale()),
                  this.button == 1
               );
               this.dragged(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1, screenX, screenY - (int)(2.0F * CFG.map.getMapScale().getCurrentScale()), this.button == 1);
               this.dragged(
                  CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1,
                  screenX + (int)(1.0F * CFG.map.getMapScale().getCurrentScale()),
                  screenY - (int)(2.0F * CFG.map.getMapScale().getCurrentScale()),
                  this.button == 1
               );
               this.dragged(
                  CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1,
                  screenX + (int)(2.0F * CFG.map.getMapScale().getCurrentScale()),
                  screenY - (int)(2.0F * CFG.map.getMapScale().getCurrentScale()),
                  this.button == 1
               );
               this.dragged(
                  CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1,
                  screenX - (int)(2.0F * CFG.map.getMapScale().getCurrentScale()),
                  screenY - (int)(1.0F * CFG.map.getMapScale().getCurrentScale()),
                  this.button == 1
               );
               this.dragged(
                  CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1,
                  screenX - (int)(1.0F * CFG.map.getMapScale().getCurrentScale()),
                  screenY - (int)(1.0F * CFG.map.getMapScale().getCurrentScale()),
                  this.button == 1
               );
               this.dragged(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1, screenX, screenY - (int)(1.0F * CFG.map.getMapScale().getCurrentScale()), this.button == 1);
               this.dragged(
                  CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1,
                  screenX + (int)(1.0F * CFG.map.getMapScale().getCurrentScale()),
                  screenY - (int)(1.0F * CFG.map.getMapScale().getCurrentScale()),
                  this.button == 1
               );
               this.dragged(
                  CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1,
                  screenX + (int)(2.0F * CFG.map.getMapScale().getCurrentScale()),
                  screenY - (int)(1.0F * CFG.map.getMapScale().getCurrentScale()),
                  this.button == 1
               );
               this.dragged(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1, screenX - (int)(2.0F * CFG.map.getMapScale().getCurrentScale()), screenY, this.button == 1);
               this.dragged(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1, screenX - (int)(1.0F * CFG.map.getMapScale().getCurrentScale()), screenY, this.button == 1);
               this.dragged(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1, screenX + (int)(1.0F * CFG.map.getMapScale().getCurrentScale()), screenY, this.button == 1);
               this.dragged(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1, screenX + (int)(2.0F * CFG.map.getMapScale().getCurrentScale()), screenY, this.button == 1);
               this.dragged(
                  CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1,
                  screenX - (int)(2.0F * CFG.map.getMapScale().getCurrentScale()),
                  screenY + (int)(1.0F * CFG.map.getMapScale().getCurrentScale()),
                  this.button == 1
               );
               this.dragged(
                  CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1,
                  screenX - (int)(1.0F * CFG.map.getMapScale().getCurrentScale()),
                  screenY + (int)(1.0F * CFG.map.getMapScale().getCurrentScale()),
                  this.button == 1
               );
               this.dragged(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1, screenX, screenY + (int)(1.0F * CFG.map.getMapScale().getCurrentScale()), this.button == 1);
               this.dragged(
                  CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1,
                  screenX + (int)(1.0F * CFG.map.getMapScale().getCurrentScale()),
                  screenY + (int)(1.0F * CFG.map.getMapScale().getCurrentScale()),
                  this.button == 1
               );
               this.dragged(
                  CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1,
                  screenX + (int)(2.0F * CFG.map.getMapScale().getCurrentScale()),
                  screenY + (int)(1.0F * CFG.map.getMapScale().getCurrentScale()),
                  this.button == 1
               );
               this.dragged(
                  CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1,
                  screenX - (int)(2.0F * CFG.map.getMapScale().getCurrentScale()),
                  screenY + (int)(2.0F * CFG.map.getMapScale().getCurrentScale()),
                  this.button == 1
               );
               this.dragged(
                  CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1,
                  screenX - (int)(1.0F * CFG.map.getMapScale().getCurrentScale()),
                  screenY + (int)(2.0F * CFG.map.getMapScale().getCurrentScale()),
                  this.button == 1
               );
               this.dragged(CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1, screenX, screenY + (int)(2.0F * CFG.map.getMapScale().getCurrentScale()), this.button == 1);
               this.dragged(
                  CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1,
                  screenX + (int)(1.0F * CFG.map.getMapScale().getCurrentScale()),
                  screenY + (int)(2.0F * CFG.map.getMapScale().getCurrentScale()),
                  this.button == 1
               );
               this.dragged(
                  CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1,
                  screenX + (int)(2.0F * CFG.map.getMapScale().getCurrentScale()),
                  screenY + (int)(2.0F * CFG.map.getMapScale().getCurrentScale()),
                  this.button == 1
               );
            }
         }
      } else {
         CFG.MANAGE_DIPLOMACY_CUSTOMIZE_ALLIANCE_ID = -1;
         CFG.map.getMapCoordinates().setDisableMovingMap(false);
      }
   }

   private final void dragged(int provID, int screenX, int screenY, boolean type) {
      if (provID >= 0) {
         Pixmap wtf = PixmapIO.readCIM(
            Gdx.files.local("map/" + CFG.map.getFile_ActiveMap_Path() + "data/" + "scales/" + "provinces/" + CFG.map.getMapBG().getMapScale() + "/" + provID)
         );
         Pixmap pixmap = null;
         screenX = (int)((float)screenX / CFG.map.getMapScale().getCurrentScale());
         screenY = (int)((float)screenY / CFG.map.getMapScale().getCurrentScale());

         for(int y = 0; y < wtf.getHeight(); ++y) {
            for(int x = 0; x < wtf.getWidth(); ++x) {
               if (x
                     == screenX
                        - CFG.map.getMapCoordinates().getPosX()
                        - (CFG.map.getMapCoordinates().getSecondSideOfMap() ? CFG.map.getMapBG().getWidth() : 0)
                        - CFG.game.getProvince(provID).getMinX()
                  && y == screenY - CFG.map.getMapCoordinates().getPosY() - CFG.game.getProvince(provID).getMinY()) {
                  if (type) {
                     pixmap = PixmapIO.readCIM(
                        Gdx.files
                           .local(
                              "map/" + CFG.map.getFile_ActiveMap_Path() + "data/" + "scales/" + "provinces/" + CFG.map.getMapBG().getMapScale() + "/" + provID
                           )
                     );
                     pixmap.setColor(new Color(1.0F, 1.0F, 1.0F, 1.0F));
                     pixmap.drawPixel(x, y);
                  } else {
                     Pixmap omg = new Pixmap(1, 1, Pixmap.Format.LuminanceAlpha);
                     omg.setColor(new Color(1.0F, 1.0F, 1.0F, 1.0F));
                     omg.drawPixel(0, 0);
                     pixmap = new Pixmap(wtf.getWidth(), wtf.getHeight(), Pixmap.Format.LuminanceAlpha);
                     pixmap.setColor(new Color(1.0F, 1.0F, 1.0F, 1.0F));

                     for(int yi = 0; yi < pixmap.getHeight(); ++yi) {
                        for(int xi = 0; xi < pixmap.getWidth(); ++xi) {
                           if (omg.getPixel(0, 0) == wtf.getPixel(xi, yi) && (xi != x || yi != y)) {
                              pixmap.drawPixel(xi, yi);
                           }
                        }
                     }
                  }
               }
            }
         }

         if (pixmap == null) {
            return;
         }

         CFG.game.getProvince(provID).setBG(pixmap);
         PixmapIO.writeCIM(
            Gdx.files.local("map/" + CFG.map.getFile_ActiveMap_Path() + "data/" + "scales/" + "provinces/" + CFG.map.getMapBG().getMapScale() + "/" + provID),
            pixmap
         );
         CFG.game.setActiveProvinceID(provID);
      }
   }

   @Override
   protected void touchUp(int screenX, int screenY, int pointer, int button) {
      CFG.map.getMapCoordinates().setDisableMovingMap(false);
   }

   @Override
   public String toString() {
      return "ACTIVE PROVINCE ID 1: "
         + CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1
         + "\n"
         + (this.theDoubleMode ? "ID 2: " + CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV2 + "\n" : "")
         + "\nBRUSH SCALE: "
         + this.iBrushScale
         + "\nSPACE -> SET ACTIVE PROVINCE 1\nDOWN -> SET ACTIVE PROVINCE 2\nBACKSPACE -> RESET ACTIVE PROVINCES\nP -> PAUSE: "
         + (CFG.MANAGE_DIPLOMACY_CUSTOMIZE_ALLIANCE_ID < 0)
         + "\nUP -> DOUBLE MODE\nLEFT, RIGHT -> BRUSH SCALE\n\nR -> REBUILD BACKGROUND\nU -> REBUILD BG BASED ON DEFAULT SCALE";
   }

   @Override
   protected void setInUse(boolean inUse) {
      CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV1 = -1;
      CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV2 = -1;
      CFG.MANAGE_DIPLOMACY_CUSTOMIZE_ALLIANCE_ID = 0;
      this.theDoubleMode = false;
      this.iBrushScale = 1;
      super.setInUse(inUse);
   }
}
