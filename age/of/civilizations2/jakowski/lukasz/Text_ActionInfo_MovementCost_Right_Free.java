package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;

class Text_ActionInfo_MovementCost_Right_Free extends Text_ActionInfo_MovementCost_Right {
   protected Text_ActionInfo_MovementCost_Right_Free(String sText, int iPosX, int iPosY) {
      super(sText, iPosX, iPosY);
   }

   @Override
   protected Color getColor(boolean isActive) {
      return isActive ? CFG.COLOR_TEXT_FREE_MOVE_ACTIVE : (this.getIsHovered() ? CFG.COLOR_TEXT_FREE_MOVE_HOVER : CFG.COLOR_TEXT_FREE_MOVE);
   }
}
