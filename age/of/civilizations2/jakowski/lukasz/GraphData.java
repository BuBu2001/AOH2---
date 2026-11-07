package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.utils.ScissorStack;
import java.util.ArrayList;
import java.util.List;

class GraphData {
   private static final float ALPHA_CIV_LINE = 0.8F;
   private int iCivID;
   private List<Integer> lPointsY;
   private int iPointsSize = 0;
   private List<GraphLine> lGraphLines;
   private int iBeginTurnID;
   private boolean drawData = true;
   private boolean visible = true;
   private boolean backAnimation = false;
   protected static final int ANIMATION_TIME = 750;
   private long lTime = 0L;

   protected GraphData(int iCivID, List<Integer> nPointsY, int iBeginTurnID) {
      this.iCivID = iCivID;
      this.iPointsSize = nPointsY.size();
      this.lPointsY = new ArrayList<>();
      this.lGraphLines = new ArrayList<>();

      for(int i = 0; i < this.iPointsSize; ++i) {
         this.lPointsY.add(nPointsY.get(i));
      }

      this.iBeginTurnID = iBeginTurnID;
      this.drawData = false;
   }

   protected final void draw(SpriteBatch oSB, int iPosX, int iPosY, int iWidth, int iHeight, List<Integer> nPointsPosX, int id, boolean active, int iFixPosY) {
      if (this.lTime + 750L >= System.currentTimeMillis()) {
         this.drawAnimation(oSB, iPosX, iPosY, iWidth, iHeight, nPointsPosX, id, active, iFixPosY);
      } else {
         this.drawGraphData(oSB, iPosX, iPosY - iFixPosY, nPointsPosX, id, active);
      }
   }

   protected final void drawAnimation(
      SpriteBatch oSB, int iPosX, int iPosY, int iWidth, int iHeight, List<Integer> nPointsPosX, int id, boolean active, int iFixPosY
   ) {
      Rectangle clipBounds;
      if (this.backAnimation) {
         clipBounds = new Rectangle(
            (float)iPosX,
            (float)(CFG.GAME_HEIGHT - iPosY),
            (float)(iWidth - (int)((float)iWidth * ((float)(System.currentTimeMillis() - this.lTime) / 750.0F))),
            (float)(-iHeight)
         );
      } else {
         clipBounds = new Rectangle(
            (float)iPosX,
            (float)(CFG.GAME_HEIGHT - iPosY),
            (float)((int)((float)iWidth * ((float)(System.currentTimeMillis() - this.lTime) / 750.0F))),
            (float)(-iHeight)
         );
      }

      oSB.flush();
      ScissorStack.pushScissors(clipBounds);
      this.drawGraphData(oSB, iPosX, iPosY - iFixPosY, nPointsPosX, id, true);
      CFG.setRender_3(true);

      try {
         oSB.flush();
         ScissorStack.popScissors();
      } catch (IllegalStateException var12) {
      }
   }

   private final void drawGraphData(SpriteBatch oSB, int iPosX, int iPosY, List<Integer> nPointsPosX, int id, boolean active) {
      try {
         oSB.setColor(
            new Color(
               (float)CFG.game.getCiv(this.iCivID).getR() / 255.0F,
               (float)CFG.game.getCiv(this.iCivID).getG() / 255.0F,
               (float)CFG.game.getCiv(this.iCivID).getB() / 255.0F,
               active ? 1.0F : 0.8F
            )
         );
      } catch (IndexOutOfBoundsException var8) {
         oSB.setColor(new Color(0.05882353F, 0.05882353F, 0.05882353F, active ? 1.0F : 0.8F));
      }

      for(int i = 0; i < this.iPointsSize - 1; ++i) {
         this.lGraphLines.get(i).draw(oSB, iPosX + nPointsPosX.get(this.iBeginTurnID + i), iPosY, id);
      }
   }

   protected final void drawCivButton(SpriteBatch oSB, int iPosX, int iPosY, boolean active) {
      oSB.setColor(
         new Color(
            Graph.GRAPH_BG_COLOR.r,
            Graph.GRAPH_BG_COLOR.g,
            Graph.GRAPH_BG_COLOR.b,
            active ? Graph.GRAPH_BG_COLOR.a * 2.0F : (this.drawData ? Graph.GRAPH_BG_COLOR.a : Graph.GRAPH_BG_COLOR.a / 4.0F)
         )
      );
      ImageManager.getImage(Images.pix255_255_255).draw(oSB, iPosX, iPosY, Graph.getGraphButtonWidth(), Graph.getGraphButtonHeight());
      oSB.setColor(
         new Color(Graph.GRAPH_BORDERS_COLOR.r, Graph.GRAPH_BORDERS_COLOR.g, Graph.GRAPH_BORDERS_COLOR.b, this.drawData ? Graph.GRAPH_BORDERS_COLOR.a : 0.25F)
      );

      try {
         oSB.setColor(
            new Color(
               (float)CFG.game.getCiv(this.iCivID).getR() / 255.0F,
               (float)CFG.game.getCiv(this.iCivID).getG() / 255.0F,
               (float)CFG.game.getCiv(this.iCivID).getB() / 255.0F,
               this.drawData ? 0.8F : 0.4F
            )
         );
      } catch (IndexOutOfBoundsException var7) {
         oSB.setColor(new Color(0.05882353F, 0.05882353F, 0.05882353F, this.drawData ? 0.8F : 0.4F));
      }

      ImageManager.getImage(Images.pix255_255_255).draw(oSB, iPosX, iPosY, CFG.CIV_COLOR_WIDTH, Graph.getGraphButtonHeight());
      oSB.setColor(this.drawData ? Color.WHITE : new Color(1.0F, 1.0F, 1.0F, 0.25F));

      try {
         CFG.game
            .getCiv(this.iCivID)
            .getFlag()
            .draw(
               oSB,
               iPosX + Graph.getGraphButtonWidth() / 2 - CFG.CIV_FLAG_WIDTH / 2,
               iPosY + Graph.getGraphButtonHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 - CFG.game.getCiv(this.iCivID).getFlag().getHeight(),
               CFG.CIV_FLAG_WIDTH,
               CFG.CIV_FLAG_HEIGHT
            );
      } catch (IndexOutOfBoundsException var6) {
         ImageManager.getImage(Images.randomCivilizationFlag)
            .draw(
               oSB,
               iPosX + Graph.getGraphButtonWidth() / 2 - CFG.CIV_FLAG_WIDTH / 2,
               iPosY + Graph.getGraphButtonHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 - ImageManager.getImage(Images.randomCivilizationFlag).getHeight(),
               CFG.CIV_FLAG_WIDTH,
               CFG.CIV_FLAG_HEIGHT
            );
      }

      ImageManager.getImage(Images.flag_rect)
         .draw(oSB, iPosX + Graph.getGraphButtonWidth() / 2 - CFG.CIV_FLAG_WIDTH / 2, iPosY + Graph.getGraphButtonHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2);
      oSB.setColor(Color.WHITE);
   }

   protected final void buildGraph(int iHeight, int nMinPoint, int nMaxPoint, List<Integer> nPointsPosX) {
      this.lGraphLines.clear();

      for(int i = 0; i < this.iPointsSize - 1; ++i) {
         this.lGraphLines
            .add(
               new GraphLine(
                  nPointsPosX.get(this.iBeginTurnID + i),
                  (int)((float)iHeight - (float)iHeight * 100.0F * (float)this.lPointsY.get(i).intValue() / (float)(nMaxPoint - nMinPoint) / 100.0F),
                  nPointsPosX.get(this.iBeginTurnID + i + 1),
                  (int)((float)iHeight - (float)iHeight * 100.0F * (float)this.lPointsY.get(i + 1).intValue() / (float)(nMaxPoint - nMinPoint) / 100.0F)
               )
            );
      }
   }

   protected final int getPointY(int i) {
      try {
         return this.lPointsY.get(i);
      } catch (IndexOutOfBoundsException var3) {
         return 0;
      }
   }

   protected final int getPointsSize() {
      return this.iPointsSize;
   }

   protected final int getCivID() {
      return this.iCivID;
   }

   protected final int getBeginTurnID() {
      return this.iBeginTurnID;
   }

   protected final boolean getDrawData() {
      return this.drawData;
   }

   protected final void setDrawData(boolean drawData) {
      if (drawData != this.drawData) {
         if (this.lTime <= System.currentTimeMillis() - 750L || !this.drawData && !this.backAnimation) {
            this.lTime = System.currentTimeMillis();
         } else {
            this.lTime = System.currentTimeMillis() - (750L - (System.currentTimeMillis() - this.lTime));
         }

         CFG.setRender_3(true);
         this.backAnimation = !drawData;
      }

      this.drawData = drawData;
   }

   protected final boolean getVisible() {
      return this.visible;
   }

   protected final void setVisible(boolean visible) {
      this.visible = visible;
   }

   protected final boolean getBackAnimation() {
      return this.backAnimation;
   }

   protected final void setBackAnimation(boolean backAnimation) {
      this.backAnimation = backAnimation;
   }

   protected final long getTime() {
      return this.lTime;
   }
}
