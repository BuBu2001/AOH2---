package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;
import java.util.List;

class Selected_Provinces {
   protected long lTime = 0L;
   protected int iAlpha = 50;
   protected int iStepID = 0;
   protected boolean backAnimation = false;
   protected long lTimeBorder = 0L;
   protected int iStepIDBorder = 0;
   protected int iBorderAlpha = 255;
   protected boolean backAnimationBorder = false;
   private List<Integer> lProvincesID = new ArrayList<>();
   private int iProvincesSize;

   protected Selected_Provinces() {
   }

   protected final void update() {
      this.updateProvinceAlpha();
      this.updateBorderAlpha();
   }

   protected void updateProvinceAlpha() {
      if (this.lTime < System.currentTimeMillis() - 25L) {
         ++this.iStepID;
         if (this.backAnimation) {
            ++this.iAlpha;
         } else {
            --this.iAlpha;
         }

         this.lTime = System.currentTimeMillis();
         if (this.iStepID == 30) {
            this.iStepID = 0;
            this.backAnimation = !this.backAnimation;
            this.lTime += this.backAnimation ? 450L : 600L;
         }
      }
   }

   protected void updateBorderAlpha() {
      if (this.lTimeBorder < System.currentTimeMillis() - 30L) {
         ++this.iStepIDBorder;
         if (this.backAnimationBorder) {
            this.iBorderAlpha += 3;
         } else {
            this.iBorderAlpha -= 3;
         }

         this.lTimeBorder = System.currentTimeMillis();
         if (this.iStepIDBorder == 45) {
            this.iStepIDBorder = 0;
            this.backAnimationBorder = !this.backAnimationBorder;
            this.lTimeBorder += this.backAnimationBorder ? 225L : 300L;
         }
      }
   }

   protected void updateColor(SpriteBatch oSB) {
      oSB.setColor(new Color(1.0F, 1.0F, 1.0F, (float)this.iAlpha * 1.6F / 255.0F));
   }

   protected final void draw(SpriteBatch oSB) {
      this.update();
      this.updateColor(oSB);

      for(int i = 0; i < this.iProvincesSize; ++i) {
         if (CFG.game.getProvince(this.lProvincesID.get(i)).getDrawProvince()) {
            CFG.game.getProvince(this.lProvincesID.get(i)).drawProvince_ActiveProvince(oSB);
         }
      }
   }

   protected final void draw_CreateAVassal(SpriteBatch oSB) {
      this.update();
      oSB.setColor(
         new Color(CFG.createVassal_Data.oColor.r, CFG.createVassal_Data.oColor.g, CFG.createVassal_Data.oColor.b, (float)this.iAlpha * 1.6F / 255.0F)
      );

      for(int i = 0; i < this.iProvincesSize; ++i) {
         if (CFG.game.getProvince(this.lProvincesID.get(i)).getDrawProvince()) {
            CFG.game.getProvince(this.lProvincesID.get(i)).drawProvince_ActiveProvince(oSB);
         }
      }
   }

   protected final void draw_HolyRomanEmpire(SpriteBatch oSB) {
      this.update();
      oSB.setColor(
         new Color(
            HolyRomanEmpire_Manager.oColorHRE.r,
            HolyRomanEmpire_Manager.oColorHRE.g,
            HolyRomanEmpire_Manager.oColorHRE.b,
            (float)this.iAlpha * (CFG.VIEW_SHOW_VALUES ? 3.0F : 2.4F) / 255.0F
         )
      );

      for(int i = 0; i < this.iProvincesSize; ++i) {
         if (CFG.game.getProvince(this.lProvincesID.get(i)).getDrawProvince()) {
            CFG.game.getProvince(this.lProvincesID.get(i)).drawProvince_ActiveProvince(oSB);
         }
      }
   }

   protected final boolean addProvince(int nProvinceID) {
      for(int i = 0; i < this.iProvincesSize; ++i) {
         if (this.lProvincesID.get(i) == nProvinceID) {
            return false;
         }
      }

      this.lProvincesID.add(nProvinceID);
      this.iProvincesSize = this.lProvincesID.size();
      return true;
   }

   protected final void popProvince() {
      if (this.lProvincesID.size() > 0) {
         this.removeProvince(this.lProvincesID.get(this.getProvincesSize() - 1));
      }
   }

   protected final boolean removeProvince(int nProvinceID) {
      for(int i = 0; i < this.iProvincesSize; ++i) {
         if (this.lProvincesID.get(i) == nProvinceID) {
            this.lProvincesID.remove(i);
            this.iProvincesSize = this.lProvincesID.size();
            return true;
         }
      }

      return false;
   }

   protected final void clearSelectedProvinces() {
      this.lProvincesID.clear();
      this.iProvincesSize = 0;
   }

   protected final boolean checkIfExists(int nProvinceID) {
      for(int i = 0; i < this.iProvincesSize; ++i) {
         if (this.lProvincesID.get(i) == nProvinceID) {
            return true;
         }
      }

      return false;
   }

   protected final void updateArmies_CivID(int nCivID, int nArmy) {
      for(int i = 0; i < this.getProvincesSize(); ++i) {
         if ((CFG.gameAction.hasArmyInProvince(this.getProvince(i), nCivID) || this.canAddArmy(nCivID, this.getProvince(i)))
            && nArmy != CFG.game.getProvince(this.getProvince(i)).getArmyCivID(nCivID)) {
            CFG.game.getProvince(this.getProvince(i)).updateArmy(nCivID, nArmy);
         }
      }
   }

   protected final boolean canAddArmy(int nCivID, int nProvinceID) {
      if (CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getAllianceID() > 0
         && CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getAllianceID() == CFG.game.getCiv(nCivID).getAllianceID()) {
         return true;
      } else {
         return CFG.game.getCiv(CFG.game.getProvince(nProvinceID).getCivID()).getPuppetOfCivID() == nCivID;
      }
   }

   protected final boolean canBeReleasedAsVassal(int nCivID, int nProvinceID) {
      if (CFG.game.getCiv(nCivID).getCapitalProvinceID() == nProvinceID) {
         return false;
      } else {
         return CFG.game.getProvince(nProvinceID).getCivID() == nCivID && CFG.game.getProvince(nProvinceID).getTrueOwnerOfProvince() == nCivID;
      }
   }

   protected final List<Integer> getProvinces() {
      return this.lProvincesID;
   }

   protected final int getProvince(int i) {
      return this.lProvincesID.get(i);
   }

   protected final int getProvincesSize() {
      return this.iProvincesSize;
   }
}
