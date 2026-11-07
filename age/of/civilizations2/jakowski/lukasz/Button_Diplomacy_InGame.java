package age.of.civilizations2.jakowski.lukasz;

import java.util.List;

class Button_Diplomacy_InGame extends Button_Diplomacy {
   protected Button_Diplomacy_InGame(int iDiploImageID, List<Integer> nCivs, int iPosX, int iPosY, int iWidth) {
      super(iDiploImageID, nCivs, iPosX, iPosY, iWidth);
   }

   @Override
   protected void setAnotherView(boolean inAnotherView) {
      if (this.iHoveredID >= 0 && this.lCivs.get(this.iHoveredID) >= 0) {
         CFG.game.disableDrawCivilizationRegions(CFG.getActiveCivInfo());
         CFG.setActiveCivInfo(this.lCivs.get(this.iHoveredID));

         try {
            if (CFG.FOG_OF_WAR == 2) {
               if (CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetProvince(CFG.game.getCiv(CFG.getActiveCivInfo()).getCapitalProvinceID())) {
                  CFG.game.setActiveProvinceID(CFG.game.getCiv(CFG.getActiveCivInfo()).getCapitalProvinceID());
               } else {
                  for(int i = 0; i < CFG.game.getCiv(CFG.getActiveCivInfo()).getNumOfProvinces(); ++i) {
                     if (CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetProvince(CFG.game.getCiv(CFG.getActiveCivInfo()).getProvinceID(i))) {
                        CFG.game.setActiveProvinceID(CFG.game.getCiv(CFG.getActiveCivInfo()).getProvinceID(i));
                        break;
                     }
                  }
               }
            } else {
               CFG.game.setActiveProvinceID(CFG.game.getCiv(CFG.getActiveCivInfo()).getCapitalProvinceID());
            }
         } catch (IndexOutOfBoundsException var3) {
         }

         CFG.updateActiveCivInfo_InGame();
         if (CFG.viewsManager.getActiveViewID() == ViewsManager.VIEW_DIPLOMACY_MODE) {
            if (CFG.FOG_OF_WAR == 2) {
               CFG.game.enableDrawCivilizationRegions_FogOfWar(CFG.getActiveCivInfo(), 1);
            } else {
               CFG.game.enableDrawCivilizationRegions(CFG.getActiveCivInfo(), 1);
            }
         }
      }
   }
}
