package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;

public class Save_GameData_10 implements Serializable {
   private static final long serialVersionUID = 0L;
   protected HolyRomanEmpire_GameData holyRomanEmpire_GameData;

   protected final void buildData() {
      this.holyRomanEmpire_GameData = CFG.holyRomanEmpire_Manager.getHRE();
   }
}
