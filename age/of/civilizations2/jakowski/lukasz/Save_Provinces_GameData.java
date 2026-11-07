package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

class Save_Provinces_GameData implements Serializable {
   private static final long serialVersionUID = 0L;
   protected List<Province_Army> lArmies = new ArrayList<>();
   protected int iCivsSize;
   protected int iTrueOwnerOfProvince = 0;
   protected boolean isCapital = false;
   protected boolean isPartOfHolyRomaEmpire = false;
   protected PlagueProvince_GameData provincePlague = null;
   protected int iNumOfPlaguesTotal = 0;
   protected int iPlaguesDeaths = 0;
   protected int iLastPlagueTurnID = 0;
   protected int iNumOfTurnsWithBalanceOnMinus = 0;
   protected int iNewColonyBonus = 0;
   protected List<Province_SupportRebels> lSupportRebels = new ArrayList<>();
   protected int iSupportRebelsSize = 0;
   protected int turnChange_Population = 0;
   protected int turnChange_Economy = 0;
   protected float turnChange_Development = 0.0F;
   protected float turnChange_Happiness = 0.0F;
   protected float turnChange_Stability = 0.0F;
   protected float turnChange_RevRisk = 0.0F;
   protected int iNumOfRevolutions = 0;
   protected Province_Population oPopulation;
   protected int iEconomy;
   protected float fDevelopmentLevel;
   protected float fHappiness = 0.85F;
   protected float fRevolutionaryRisk = 0.0F;
   protected Province_Core oProvinceCore = null;
   protected int isNotSuppliedForXTurns = -1;
   protected int iDefensivePosition = 0;
   protected int iWatchTower;
   protected int iFort;
   protected int iPort;
   protected int iFarm;
   protected int iLibrary;
   protected int iArmoury;
   protected int iWorkshop;
   protected int iSupply;
   protected byte wasConquered = 0;
   protected byte wasAttacked = 0;
   protected byte neighbooringProvinceOfCivWasLost = 0;
   protected int wastelandLevel = -1;

   protected final void resetData() {
      this.iLastPlagueTurnID = -19;
      this.iPlaguesDeaths = 0;
      this.iNumOfPlaguesTotal = 0;
      this.provincePlague = null;
      this.turnChange_Population = 0;
      this.turnChange_Economy = 0;
      this.turnChange_Development = 0.0F;
      this.turnChange_Happiness = 0.0F;
      this.turnChange_Stability = 0.0F;
      this.turnChange_RevRisk = 0.0F;
      this.iNewColonyBonus = 0;
      this.iNumOfTurnsWithBalanceOnMinus = 0;
      this.iNumOfRevolutions = 0;
      this.lSupportRebels = new ArrayList<>();
      this.iSupportRebelsSize = 0;
   }
}
