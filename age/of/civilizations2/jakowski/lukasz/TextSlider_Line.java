package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;
import java.util.List;

class TextSlider_Line {
   private List<String> lText = new ArrayList<>();
   private int iHeight;
   private TextSlider_Line.Align align;

   protected TextSlider_Line(String sText, int nWidth, int extraHeight, TextSlider_Line.Align nAlign, float nFONT_SCALE) {
      this.align = nAlign;
      String[] tempLine = sText.split(" ");
      int i = 0;
      int currentW = 0;
      int iSize = tempLine.length;

      for(int last = 0; i < iSize; ++i) {
         CFG.glyphLayout.setText(CFG.fontMain, tempLine[i] + " ");
         currentW += (int)(CFG.glyphLayout.width * nFONT_SCALE);
         if (currentW >= nWidth || i == iSize - 1 && currentW < nWidth) {
            String addLine = "";

            for(int j = last; j < (i == iSize - 1 && currentW < nWidth ? iSize : i); ++j) {
               addLine = addLine + tempLine[j] + " ";
            }

            this.lText.add(addLine);
            last = i;
            if (currentW >= nWidth && i == iSize - 1) {
               this.lText.add(tempLine[i]);
            }

            currentW = (int)(CFG.glyphLayout.width * nFONT_SCALE);
         }
      }

      this.iHeight = (int)((float)this.lText.size() * ((float)CFG.TEXT_HEIGHT * nFONT_SCALE + (float)CFG.PADDING) + (float)extraHeight);
   }

   protected void draw(SpriteBatch oSB, int nPosX, int nPosY, int nWidth, Color oColor) {
      int i = 0;

      for(int iSize = this.lText.size(); i < iSize; ++i) {
         CFG.drawText(oSB, this.lText.get(i), nPosX, nPosY + (CFG.TEXT_HEIGHT + CFG.PADDING) * i, oColor);
      }
   }

   protected final void setHeight(int iHeight) {
      this.iHeight = iHeight;
   }

   protected final int getHeight() {
      return this.iHeight;
   }

   protected static enum Align {
      LEFT,
      CENTER,
      RIGHT;
   }
}
