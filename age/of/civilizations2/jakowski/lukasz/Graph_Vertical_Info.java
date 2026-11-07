package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.utils.ScissorStack;
import java.util.ArrayList;
import java.util.List;

class Graph_Vertical_Info {
   private List<String> lTexts = null;
   private int iTextsSize = 0;
   private List<Integer> lTextWidths = null;
   private List<Color> lColors = null;
   private boolean moveable = false;
   private boolean moveRight = false;
   private int iTextWidth = 0;
   private int iTextPosX = 0;
   private long lTime = 0L;
   private List<Integer> lSortedIDs = null;

   protected Graph_Vertical_Info(List<String> nTexts, List<Color> nColors, int iWidth, boolean nSortText) {
      this.iTextsSize = nTexts.size();
      this.lTexts = new ArrayList<>();
      this.lColors = new ArrayList<>();
      this.lSortedIDs = new ArrayList<>();
      List<Boolean> tempAdded = new ArrayList<>();

      for(int i = 0; i < this.iTextsSize; ++i) {
         this.lSortedIDs.add(i);
         tempAdded.add(false);
      }

      if (!nSortText) {
         this.lTexts = nTexts;
         this.lColors = nColors;
      } else {
         while(nTexts.size() != this.lTexts.size()) {
            int nMinID = 0;

            for(int i = 0; i < this.iTextsSize; ++i) {
               if (!tempAdded.get(i)) {
                  nMinID = i;
                  break;
               }
            }

            for(int i = nMinID + 1; i < this.iTextsSize; ++i) {
               if (!tempAdded.get(i) && CFG.compareAlphabetic_TwoString(nTexts.get(nMinID), nTexts.get(i))) {
                  nMinID = i;
               }
            }

            this.lTexts.add(nTexts.get(nMinID));
            this.lColors.add(nColors.get(nMinID));
            tempAdded.set(nMinID, true);
            this.lSortedIDs.set(nMinID, this.lTexts.size() - 1);
         }
      }

      this.lTextWidths = new ArrayList<>();
      CFG.fontMain.getData().setScale(0.7F);

      for(int i = 0; i < this.iTextsSize; ++i) {
         CFG.glyphLayout.setText(CFG.fontMain, this.lTexts.get(i));
         this.iTextWidth += (int)CFG.glyphLayout.width;
         this.lTextWidths.add((int)CFG.glyphLayout.width);
      }

      CFG.fontMain.getData().setScale(1.0F);
      this.iTextWidth += CFG.PADDING * this.iTextsSize + CFG.PADDING * (this.iTextsSize - 1) + (int)((float)CFG.TEXT_HEIGHT * 0.7F * (float)this.iTextsSize);
      this.updateMoveable(iWidth);
   }

   protected final void updateMoveable(int iWidth) {
      if (this.iTextWidth > iWidth) {
         this.moveable = true;
         this.resetMoveable();
      } else {
         this.resetMoveable();
         this.moveable = false;
         this.iTextPosX = iWidth / 2 - this.iTextWidth / 2;
      }
   }

   protected final void draw(SpriteBatch oSB, int nPosX, int nPosY, int nWidth) {
      if (this.moveable) {
         Rectangle clipBounds = new Rectangle(
            (float)nPosX, (float)(CFG.GAME_HEIGHT - nPosY), (float)nWidth, (float)(-((int)((float)CFG.TEXT_HEIGHT * 0.7F)) - CFG.PADDING)
         );
         oSB.flush();
         ScissorStack.pushScissors(clipBounds);
         if (this.lTime < System.currentTimeMillis() - 45L) {
            this.lTime = System.currentTimeMillis();
            if (this.moveRight) {
               --this.iTextPosX;
               if (-this.iTextPosX + nWidth >= this.iTextWidth + CFG.PADDING) {
                  this.moveRight = !this.moveRight;
               }

               CFG.setRender_3(true);
            } else {
               ++this.iTextPosX;
               if (this.iTextPosX >= 0) {
                  this.moveRight = !this.moveRight;
               }

               CFG.setRender_3(true);
            }
         } else {
            CFG.setRender_3(true);
         }
      }

      int i = 0;

      for(int tempOffsetX = 0; i < this.iTextsSize; ++i) {
         oSB.setColor(this.lColors.get(i));
         ImageManager.getImage(Images.pix255_255_255)
            .draw(
               oSB,
               nPosX + tempOffsetX + this.iTextPosX,
               nPosY - ImageManager.getImage(Images.pix255_255_255).getHeight(),
               (int)((float)CFG.TEXT_HEIGHT * 0.7F),
               (int)((float)CFG.TEXT_HEIGHT * 0.7F)
            );
         oSB.setColor(new Color(this.lColors.get(i).r, this.lColors.get(i).g, this.lColors.get(i).b, 0.7F));
         ImageManager.getImage(Images.gradient)
            .draw(
               oSB,
               nPosX + tempOffsetX + this.iTextPosX,
               nPosY - ImageManager.getImage(Images.gradient).getHeight(),
               (int)((float)CFG.TEXT_HEIGHT * 0.7F),
               (int)((float)CFG.TEXT_HEIGHT * 0.7F)
            );
         tempOffsetX += (int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING;
         CFG.drawTextWithShadow(
            oSB,
            this.lTexts.get(i),
            nPosX + tempOffsetX + this.iTextPosX,
            nPosY,
            new Color(this.lColors.get(i).r, this.lColors.get(i).g, this.lColors.get(i).b, 0.7F)
         );
         tempOffsetX += this.lTextWidths.get(i) + CFG.PADDING;
      }

      if (this.moveable) {
         try {
            oSB.flush();
            ScissorStack.popScissors();
         } catch (IllegalStateException var7) {
         }
      }
   }

   protected final void resetMoveable() {
      this.iTextPosX = 0;
      this.moveRight = true;
   }

   protected final int getTextSize() {
      return this.iTextsSize;
   }

   protected final String getText(int i) {
      return this.lTexts.get(i);
   }

   protected final int getSortedID(int i) {
      return this.lSortedIDs.get(i);
   }

   protected final List<Color> getColors() {
      return this.lColors;
   }
}
