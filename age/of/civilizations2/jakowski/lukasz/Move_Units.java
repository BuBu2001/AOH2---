package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Move_Units {
   private int iFromProvinceID;
   private int iToProvinceID;
   private int iNumOfUnits;
   private int iNumOfUnitsWidth;
   private MoveUnits_Line moveUnitsLine = null;

   protected Move_Units(int iFromProvinceID, int iToProvinceID, int iNumOfUnits, boolean buildLane) {
      this.iFromProvinceID = iFromProvinceID;
      this.iToProvinceID = iToProvinceID;
      this.iNumOfUnits = iNumOfUnits;
      this.buildMoveUnitsLine();
   }

   protected Move_Units(int iFromProvinceID, int iToProvinceID, int iNumOfUnits, boolean buildLane, boolean migrateLine) {
      this.iFromProvinceID = iFromProvinceID;
      this.iToProvinceID = iToProvinceID;
      this.iNumOfUnits = iNumOfUnits;
      if (buildLane) {
         this.buildMoveUnitsLine_Migrate();
      }
   }

   protected final void draw(SpriteBatch oSB, float nScale) {
      this.moveUnitsLine.drawLine(oSB, nScale);
   }

   protected final void draw2(SpriteBatch oSB, float nScale) {
      this.moveUnitsLine.drawLine2(oSB, nScale);
   }

   protected final int getNumOfUnits() {
      return this.iNumOfUnits;
   }

   protected final void setNumOfUnits(int iNumOfUnits) {
      try {
         this.iNumOfUnits = iNumOfUnits;
         if (this.moveUnitsLine != null) {
            this.moveUnitsLine.lMovingTime = System.currentTimeMillis();
            this.moveUnitsLine.fMovingPercentage = 0.1F;
         }

         CFG.glyphLayout.setText(CFG.fontArmy, "" + iNumOfUnits);
         this.iNumOfUnitsWidth = (int)CFG.glyphLayout.width;
      } catch (NullPointerException var3) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var3);
         }
      }
   }

   protected final int getFromProvinceID() {
      return this.iFromProvinceID;
   }

   protected final int getToProvinceID() {
      return this.iToProvinceID;
   }

   protected final MoveUnits_Line getMoveUnitsLine() {
      return this.moveUnitsLine;
   }

   protected final int getUnitsWidth() {
      return this.iNumOfUnitsWidth;
   }

   protected final void buildMoveUnitsLine() {
      try {
         this.moveUnitsLine = new MoveUnits_Line(this.iFromProvinceID, this.iToProvinceID);
         CFG.glyphLayout.setText(CFG.fontArmy, "" + this.iNumOfUnits);
         this.iNumOfUnitsWidth = (int)CFG.glyphLayout.width;
      } catch (IndexOutOfBoundsException var2) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var2);
         }
      } catch (NullPointerException var3) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var3);
         }
      }
   }

   protected final void buildMoveUnitsLine_Migrate() {
      try {
         this.moveUnitsLine = new MoveUnits_Line_Migrate(this.iFromProvinceID, this.iToProvinceID);
         CFG.glyphLayout.setText(CFG.fontArmy, "" + this.iNumOfUnits);
         this.iNumOfUnitsWidth = (int)CFG.glyphLayout.width;
      } catch (IndexOutOfBoundsException var2) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var2);
         }
      }
   }
}
