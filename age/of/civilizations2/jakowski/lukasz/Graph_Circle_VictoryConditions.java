package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.List;

class Graph_Circle_VictoryConditions extends Graph_Circle {
   protected static final float FONT_SIZE = 0.7F;
   protected static final float FONT_SIZE2 = 0.6F;
   private String sTitle;
   private int iTitleWidth;
   protected int iPaddingGraph = 0;
   protected boolean row;
   protected Color colorTitle;
   protected int iCivID;
   protected String sTopLeft;
   protected int iTopLeftWidth;
   protected String sTopRight;
   protected int iTopRightWidth;
   protected String sBot;
   protected int iBotWidth;
   protected String sDisabled;
   protected int iDisabledWidth;
   protected boolean disabled;
   protected int iImageID;

   protected Graph_Circle_VictoryConditions(
      boolean disabled,
      int iImageID,
      boolean row,
      String nTitle,
      Color colorTitle,
      int iPosX,
      int iPosY,
      List<Integer> nValues,
      List<Integer> nCivIDs,
      int nCivID,
      String nTopLeft,
      String nTopRight,
      String nBot
   ) {
      super(iPosX, iPosY, nValues, nCivIDs, null);
      this.row = row;
      this.disabled = disabled;
      this.iPaddingGraph = CFG.PADDING + CFG.PADDING / 2;
      this.iImageID = iImageID;
      this.iCivID = nCivID;
      this.sTitle = nTitle;
      CFG.glyphLayout.setText(CFG.fontMain, this.sTitle);
      this.iTitleWidth = (int)(CFG.glyphLayout.width * 0.7F);
      this.colorTitle = colorTitle;
      this.sTopLeft = nTopLeft;
      this.sTopRight = nTopRight;
      this.sBot = nBot;
      CFG.glyphLayout.setText(CFG.fontMain, this.sTopLeft);
      this.iTopLeftWidth = (int)(CFG.glyphLayout.width * 0.7F);
      CFG.glyphLayout.setText(CFG.fontMain, this.sTopRight);
      this.iTopRightWidth = (int)(CFG.glyphLayout.width * 0.7F);
      CFG.glyphLayout.setText(CFG.fontMain, this.sBot);
      this.iBotWidth = (int)(CFG.glyphLayout.width * 0.6F);
      this.sDisabled = CFG.langManager.get("Disabled");
      CFG.glyphLayout.setText(CFG.fontMain, this.sDisabled);
      this.iDisabledWidth = (int)CFG.glyphLayout.width;
   }

   @Override
   protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
      if (this.disabled) {
         oSB.setColor(new Color(CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.r, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.g, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.b, 0.075F));
      } else {
         oSB.setColor(new Color(CFG.COLOR_GRADIENT_DIPLOMACY.r, CFG.COLOR_GRADIENT_DIPLOMACY.g, CFG.COLOR_GRADIENT_DIPLOMACY.b, 0.125F));
      }

      ImageManager.getImage(Images.pix255_255_255)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY,
            this.getWidth(),
            this.getHeight()
         );
      if (!this.disabled) {
         oSB.setColor(new Color(CFG.COLOR_GRADIENT_DIPLOMACY.r, CFG.COLOR_GRADIENT_DIPLOMACY.g, CFG.COLOR_GRADIENT_DIPLOMACY.b, 0.25F));
         ImageManager.getImage(Images.pix255_255_255)
            .draw(
               oSB,
               this.getPosX() + this.getWidth() - (CFG.PADDING * 4 + CFG.CIV_FLAG_WIDTH) + iTranslateX,
               this.getPosY() + this.getHeight_Title() - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY,
               CFG.PADDING * 4 + CFG.CIV_FLAG_WIDTH,
               this.getHeight() - this.getHeight_Title()
            );
         oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.45F));
         ImageManager.getImage(Images.slider_gradient)
            .draw(
               oSB,
               this.getPosX() + this.getWidth() - (CFG.PADDING * 4 + CFG.CIV_FLAG_WIDTH) + iTranslateX,
               this.getPosY() + this.getHeight_Title() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY,
               CFG.PADDING * 2,
               this.getHeight() - this.getHeight_Title(),
               false,
               false
            );
         ImageManager.getImage(Images.slider_gradient)
            .draw(
               oSB,
               this.getPosX() + this.getWidth() - CFG.PADDING * 2 + iTranslateX,
               this.getPosY() + this.getHeight_Title() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY,
               CFG.PADDING * 2,
               this.getHeight() - this.getHeight_Title(),
               true,
               false
            );
         oSB.setColor(new Color(CFG.COLOR_GRADIENT_TITLE_BLUE.r, CFG.COLOR_GRADIENT_TITLE_BLUE.g, CFG.COLOR_GRADIENT_TITLE_BLUE.b, 0.35F));
         ImageManager.getImage(Images.line_32_vertical)
            .draw(
               oSB,
               this.getPosX() + this.getWidth() - (CFG.PADDING * 4 + CFG.CIV_FLAG_WIDTH) + iTranslateX,
               this.getPosY() + this.getHeight_Title() - ImageManager.getImage(Images.line_32_vertical).getHeight() + iTranslateY,
               1,
               this.getHeight() - this.getHeight_Title()
            );
      }

      if (this.row) {
         oSB.setColor(new Color(CFG.COLOR_GRADIENT_DIPLOMACY.r, CFG.COLOR_GRADIENT_DIPLOMACY.g, CFG.COLOR_GRADIENT_DIPLOMACY.b, 0.4F));
         ImageManager.getImage(Images.slider_gradient)
            .draw(
               oSB,
               this.getPosX() + iTranslateX,
               this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY,
               this.getWidth() / 2,
               this.getHeight()
            );
         ImageManager.getImage(Images.slider_gradient)
            .draw(
               oSB,
               this.getPosX() + this.getWidth() - this.getWidth() / 2 + iTranslateX,
               this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY,
               this.getWidth() / 2,
               this.getHeight(),
               true,
               false
            );
         oSB.setColor(new Color(CFG.COLOR_INFO_BOX_GRADIENT.r, CFG.COLOR_INFO_BOX_GRADIENT.g, CFG.COLOR_INFO_BOX_GRADIENT.b, 0.35F));
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
         oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.6F));
         ImageManager.getImage(Images.line_32_off1)
            .draw(
               oSB,
               this.getPosX() + iTranslateX,
               this.getPosY() + this.getHeight() - 1 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
               this.getWidth(),
               1
            );
         ImageManager.getImage(Images.line_32_off1)
            .draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth(), 1);
         if (isActive || this.getIsHovered()) {
            oSB.setColor(new Color(CFG.COLOR_GRADIENT_DIPLOMACY.r, CFG.COLOR_GRADIENT_DIPLOMACY.g, CFG.COLOR_GRADIENT_DIPLOMACY.b, 0.35F));
            ImageManager.getImage(Images.slider_gradient)
               .draw(
                  oSB,
                  this.getPosX() + iTranslateX,
                  this.getPosY() + 1 - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY,
                  this.getWidth(),
                  this.getHeight() - 2,
                  true,
                  false
               );
         }
      } else {
         oSB.setColor(new Color(CFG.COLOR_GRADIENT_DIPLOMACY.r, CFG.COLOR_GRADIENT_DIPLOMACY.g, CFG.COLOR_GRADIENT_DIPLOMACY.b, 0.6F));
         ImageManager.getImage(Images.slider_gradient)
            .draw(
               oSB,
               this.getPosX() + iTranslateX,
               this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY,
               this.getWidth() / 2,
               this.getHeight()
            );
         ImageManager.getImage(Images.slider_gradient)
            .draw(
               oSB,
               this.getPosX() + this.getWidth() - this.getWidth() / 2 + iTranslateX,
               this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY,
               this.getWidth() / 2,
               this.getHeight(),
               true,
               false
            );
         oSB.setColor(new Color(CFG.COLOR_GRADIENT_DARK_BLUE.r, CFG.COLOR_GRADIENT_DARK_BLUE.g, CFG.COLOR_GRADIENT_DARK_BLUE.b, 0.45F));
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
         oSB.setColor(new Color(CFG.COLOR_GRADIENT_DIPLOMACY.r, CFG.COLOR_GRADIENT_DIPLOMACY.g, CFG.COLOR_GRADIENT_DIPLOMACY.b, 0.85F));
         ImageManager.getImage(Images.line_32_off1)
            .draw(
               oSB,
               this.getPosX() + iTranslateX,
               this.getPosY() + this.getHeight() - 1 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
               this.getWidth(),
               1
            );
         ImageManager.getImage(Images.line_32_off1)
            .draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY, this.getWidth(), 1);
         if (isActive || this.getIsHovered()) {
            oSB.setColor(new Color(CFG.COLOR_GRADIENT_DIPLOMACY.r, CFG.COLOR_GRADIENT_DIPLOMACY.g, CFG.COLOR_GRADIENT_DIPLOMACY.b, 0.45F));
            ImageManager.getImage(Images.slider_gradient)
               .draw(
                  oSB,
                  this.getPosX() + iTranslateX,
                  this.getPosY() + 1 - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY,
                  this.getWidth(),
                  this.getHeight() - 2,
                  true,
                  false
               );
         }
      }

      oSB.setColor(new Color(this.colorTitle.r, this.colorTitle.g, this.colorTitle.b, 0.225F));
      ImageManager.getImage(Images.pix255_255_255)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY,
            this.getWidth(),
            this.getHeight_Title()
         );
      oSB.setColor(new Color(this.colorTitle.r, this.colorTitle.g, this.colorTitle.b, !this.getIsHovered() && !isActive ? 0.125F : 0.155F));
      ImageManager.getImage(Images.gradient)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY,
            this.getWidth(),
            this.getHeight_Title(),
            false,
            true
         );
      oSB.setColor(new Color(this.colorTitle.r, this.colorTitle.g, this.colorTitle.b, 0.125F));
      ImageManager.getImage(Images.pix255_255_255)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY,
            Button_Diplomacy.iDiploWidth + CFG.PADDING * 2,
            this.getHeight_Title()
         );
      oSB.setColor(new Color(this.colorTitle.r, this.colorTitle.g, this.colorTitle.b, 0.275F));
      ImageManager.getImage(Images.slider_gradient)
         .draw(
            oSB,
            this.getPosX() + Button_Diplomacy.iDiploWidth + CFG.PADDING * 2 + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY,
            (int)((float)(this.getWidth() - Button_Diplomacy.iDiploWidth + CFG.PADDING * 2) * 0.25F),
            this.getHeight_Title()
         );
      oSB.setColor(new Color(this.getColorLeft().r, this.getColorLeft().g, this.getColorLeft().b, 0.1425F));
      ImageManager.getImage(Images.line_32_off1)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
            this.getWidth(),
            this.getHeight_Title(),
            false,
            true
         );
      oSB.setColor(new Color(this.colorTitle.r, this.colorTitle.g, this.colorTitle.b, 0.045F));
      ImageManager.getImage(Images.pix255_255_255)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY,
            Button_Diplomacy.iDiploWidth + CFG.PADDING * 2,
            this.getHeight_Title()
         );
      oSB.setColor(new Color(this.colorTitle.r, this.colorTitle.g, this.colorTitle.b, 0.105F));
      ImageManager.getImage(Images.line_32_off1)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
            Button_Diplomacy.iDiploWidth + CFG.PADDING * 2,
            this.getHeight_Title()
         );
      oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.425F));
      ImageManager.getImage(Images.slider_gradient)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY,
            CFG.PADDING * 2,
            this.getHeight_Title(),
            false,
            false
         );
      ImageManager.getImage(Images.slider_gradient)
         .draw(
            oSB,
            this.getPosX() + Button_Diplomacy.iDiploWidth + CFG.PADDING * 2 - CFG.PADDING * 2 + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY,
            CFG.PADDING * 2,
            this.getHeight_Title(),
            true,
            false
         );
      oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.65F));
      ImageManager.getImage(Images.gradient)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY,
            this.getWidth(),
            CFG.PADDING,
            false,
            false
         );
      ImageManager.getImage(Images.gradient)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() + this.getHeight_Title() - CFG.PADDING - ImageManager.getImage(Images.gradient).getHeight() + iTranslateY,
            this.getWidth(),
            CFG.PADDING,
            false,
            true
         );
      oSB.setColor(CFG.COLOR_NEW_GAME_EDGE_LINE);
      ImageManager.getImage(Images.pix255_255_255)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() + this.getHeight_Title() - 1 - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY,
            this.getWidth(),
            1
         );
      oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.55F));
      ImageManager.getImage(Images.line_32_off1)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() + this.getHeight_Title() - 2 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
            this.getWidth(),
            1
         );
      oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.8F));
      ImageManager.getImage(Images.line_32_off1)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() + this.getHeight_Title() - 1 - ImageManager.getImage(Images.line_32_off1).getHeight() + iTranslateY,
            this.getWidth(),
            1
         );
      oSB.setColor(new Color(CFG.COLOR_FLAG_FRAME.r, CFG.COLOR_FLAG_FRAME.g, CFG.COLOR_FLAG_FRAME.b, 0.45F));
      ImageManager.getImage(Images.slider_gradient)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() + this.getHeight_Title() - 1 - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY,
            this.getWidth() / 2,
            1
         );
      ImageManager.getImage(Images.slider_gradient)
         .draw(
            oSB,
            this.getPosX() + this.getWidth() - this.getWidth() / 2 + iTranslateX,
            this.getPosY() + this.getHeight_Title() - 1 - ImageManager.getImage(Images.slider_gradient).getHeight() + iTranslateY,
            this.getWidth() / 2,
            1,
            true,
            false
         );
      oSB.setColor(CFG.COLOR_NEW_GAME_EDGE_LINE);
      ImageManager.getImage(Images.pix255_255_255)
         .draw(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() + this.getHeight() - 1 - ImageManager.getImage(Images.pix255_255_255).getHeight() + iTranslateY,
            this.getWidth(),
            1
         );
      oSB.setColor(Color.WHITE);
      if (!this.disabled) {
         CFG.game
            .getCiv(this.iCivID)
            .getFlag()
            .draw(
               oSB,
               this.getPosX() + this.getWidth() - CFG.PADDING * 2 - CFG.CIV_FLAG_WIDTH + iTranslateX,
               this.getPosY()
                  + this.getHeight_Title()
                  + (this.getHeight() - this.getHeight_Title()) / 2
                  - CFG.CIV_FLAG_HEIGHT / 2
                  - CFG.game.getCiv(this.iCivID).getFlag().getHeight()
                  + iTranslateY,
               CFG.CIV_FLAG_WIDTH,
               CFG.CIV_FLAG_HEIGHT
            );
         ImageManager.getImage(Images.flag_rect)
            .draw(
               oSB,
               this.getPosX() + this.getWidth() - CFG.PADDING * 2 - CFG.CIV_FLAG_WIDTH + iTranslateX,
               this.getPosY()
                  + this.getHeight_Title()
                  + (this.getHeight() - this.getHeight_Title()) / 2
                  - CFG.CIV_FLAG_HEIGHT / 2
                  - ImageManager.getImage(Images.flag_rect).getHeight()
                  + iTranslateY,
               CFG.CIV_FLAG_WIDTH,
               CFG.CIV_FLAG_HEIGHT
            );
      }

      ImageManager.getImage(this.iImageID)
         .draw(
            oSB,
            this.getPosX() + CFG.PADDING + Button_Diplomacy.iDiploWidth / 2 - ImageManager.getImage(this.iImageID).getWidth() / 2 + iTranslateX,
            this.getPosY() + this.getHeight_Title() / 2 - ImageManager.getImage(this.iImageID).getHeight() / 2 + iTranslateY
         );
      CFG.fontMain.getData().setScale(0.7F);
      CFG.drawTextWithShadow(
         oSB,
         this.sTitle,
         this.getPosX() + CFG.PADDING * 3 + Button_Diplomacy.iDiploWidth + iTranslateX,
         this.getPosY() + this.getHeight_Title() / 2 - (int)((float)CFG.TEXT_HEIGHT * 0.7F) / 2 + iTranslateY,
         this.getColor(isActive)
      );
      CFG.fontMain.getData().setScale(1.0F);
      if (this.disabled) {
         CFG.drawTextWithShadow(
            oSB,
            this.sDisabled,
            this.getPosX() + this.getWidth() / 2 - this.iDisabledWidth / 2 + iTranslateX,
            this.getPosY() + this.getHeight_Title() + (this.getHeight() - this.getHeight_Title()) / 2 - CFG.TEXT_HEIGHT / 2 + iTranslateY,
            new Color(CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.r, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.g, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.b, 0.65F)
         );
      } else {
         CFG.fontMain.getData().setScale(0.7F);
         CFG.drawTextWithShadow(
            oSB,
            this.sTopRight,
            this.getPosX() + this.getWidth() - CFG.CIV_FLAG_WIDTH - CFG.PADDING * 2 - this.iTopRightWidth - CFG.PADDING * 3 + iTranslateX,
            this.getPosY()
               + this.getHeight_Title()
               + (this.getHeight() - this.getHeight_Title()) / 2
               - CFG.PADDING / 2
               - (int)((float)CFG.TEXT_HEIGHT * 0.7F)
               + iTranslateY,
            CFG.COLOR_TEXT_NUM_OF_PROVINCES
         );
         CFG.drawTextWithShadow(
            oSB,
            this.sTopLeft,
            this.getPosX() + this.getWidth() - CFG.CIV_FLAG_WIDTH - CFG.PADDING * 2 - this.iTopRightWidth - CFG.PADDING * 3 - this.iTopLeftWidth + iTranslateX,
            this.getPosY()
               + this.getHeight_Title()
               + (this.getHeight() - this.getHeight_Title()) / 2
               - CFG.PADDING / 2
               - (int)((float)CFG.TEXT_HEIGHT * 0.7F)
               + iTranslateY,
            Color.WHITE
         );
         CFG.fontMain.getData().setScale(0.6F);
         CFG.drawTextWithShadow(
            oSB,
            this.sBot,
            this.getPosX() + this.getWidth() - CFG.CIV_FLAG_WIDTH - CFG.PADDING * 2 - this.iBotWidth - CFG.PADDING * 3 + iTranslateX,
            this.getPosY() + this.getHeight_Title() + (this.getHeight() - this.getHeight_Title()) / 2 + CFG.PADDING / 2 + iTranslateY,
            CFG.COLOR_TEXT_MODIFIER_NEUTRAL
         );
         CFG.fontMain.getData().setScale(1.0F);
         this.drawGraph(
            oSB,
            iTranslateX,
            iTranslateY,
            isActive,
            scrollableY,
            this.getPosX() + CFG.PADDING * 2,
            this.getPosY() + this.getHeight_Title() + this.iPaddingGraph,
            this.getWidth_PercStrings(CFG.graphCircleDraw.getWidth()),
            CFG.graphCircleDraw.getWidth(),
            CFG.graphCircleDraw.getWidth()
         );
      }
   }

   @Override
   protected int getHeight() {
      return CFG.graphCircleDraw.getWidth() + this.iPaddingGraph * 2 + this.getHeight_Title();
   }

   protected int getHeight_Title() {
      return CFG.TEXT_HEIGHT + CFG.PADDING * 4;
   }

   protected Color getColor(boolean isActive) {
      return isActive
         ? CFG.COLOR_TEXT_OPTIONS_NS_ACTIVE
         : (
            this.getClickable()
               ? (this.getIsHovered() ? CFG.COLOR_TEXT_OPTIONS_NS_HOVER : Color.WHITE)
               : new Color(CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.r, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.g, CFG.COLOR_TEXT_MODIFIER_NEGATIVE2.b, 0.6F)
         );
   }

   protected Color getColorLeft() {
      return new Color(0.19607843F, 0.39215687F, 0.7647059F, 1.0F);
   }

   @Override
   protected void setAnotherView(boolean inAnotherView) {
      this.isDescriptionActive = true;
   }
}
