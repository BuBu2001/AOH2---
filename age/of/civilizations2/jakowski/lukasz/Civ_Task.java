package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;

class Civ_Task implements Serializable {
   private static final long serialVersionUID = 0L;
   protected Civ_Task_Type taskType = Civ_Task_Type.ASSIMILATE_PROVINCE;
   protected int iProvinceID;

   protected boolean runTask() {
      return true;
   }
}
