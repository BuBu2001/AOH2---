package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;
import java.util.List;

class Graph_Vertical_Data {
   private int iCivID;
   private List<Graph_Vertical_Data_Value> lValues = new ArrayList<>();
   private boolean inView = true;
   private long lTime = 0L;
   private static final int ANIMATION_TIME = 725;

   protected Graph_Vertical_Data(int iCivID) {
      this.iCivID = iCivID;
   }

   protected final void drawData(SpriteBatch oSB, int iPosX, int iPosY, int iWidth, int iHeight, List<Color> nColors) {
      if (this.lTime == 0L) {
         this.lTime = System.currentTimeMillis();
      }

      int tempValuesHeight = 0;
      if (this.lTime + 725L > System.currentTimeMillis()) {
         int tempHeight = 0;

         for(int i = 0; i < this.lValues.size(); ++i) {
            tempHeight += this.lValues.get(i).getHeight();
         }

         tempHeight = (int)((float)tempHeight * ((float)(System.currentTimeMillis() - this.lTime) / 725.0F));
         tempValuesHeight = tempHeight;
         int i = 0;

         for(int tempAnimationHeight = 0; i < this.lValues.size(); ++i) {
            try {
               this.lValues
                  .get(i)
                  .draw(
                     oSB,
                     iPosX,
                     iPosY + iHeight,
                     iWidth,
                     tempAnimationHeight,
                     tempHeight >= this.lValues.get(i).getHeight() ? this.lValues.get(i).getHeight() : tempHeight,
                     nColors.get(this.lValues.get(i).getDataTypeID())
                  );
            } catch (IndexOutOfBoundsException var14) {
               this.lValues
                  .get(i)
                  .draw(
                     oSB,
                     iPosX,
                     iPosY + iHeight,
                     iWidth,
                     tempAnimationHeight,
                     tempHeight >= this.lValues.get(i).getHeight() ? this.lValues.get(i).getHeight() : tempHeight,
                     Color.WHITE
                  );
            }

            tempAnimationHeight += this.lValues.get(i).getHeight();
            tempHeight -= this.lValues.get(i).getHeight();
            if (tempHeight <= 0) {
               break;
            }
         }

         CFG.setRender_3(true);
      } else {
         for(int i = 0; i < this.lValues.size(); ++i) {
            try {
               this.lValues.get(i).draw(oSB, iPosX, iPosY + iHeight, iWidth, tempValuesHeight, nColors.get(this.lValues.get(i).getDataTypeID()));
            } catch (IndexOutOfBoundsException var13) {
               this.lValues.get(i).draw(oSB, iPosX, iPosY + iHeight, iWidth, tempValuesHeight, Color.WHITE);
            }

            tempValuesHeight += this.lValues.get(i).getHeight();
         }
      }

      CFG.fontMain.getData().setScale(0.8F);
      CFG.drawTextRotated(
         oSB,
         "" + this.getValue(),
         iPosX + iWidth / 2 - (int)((float)CFG.TEXT_HEIGHT * 0.8F / 2.0F),
         iPosY + iHeight - CFG.PADDING,
         new Color(1.0F, 1.0F, 1.0F, 0.45F),
         90.0F
      );
      CFG.fontMain.getData().setScale(1.0F);
      oSB.setColor(Color.WHITE);

      try {
         CFG.game
            .getCiv(this.iCivID)
            .getFlag()
            .draw(
               oSB,
               iPosX,
               iPosY + iHeight - tempValuesHeight - CFG.PADDING - CFG.CIV_FLAG_HEIGHT - CFG.game.getCiv(this.iCivID).getFlag().getHeight(),
               CFG.CIV_FLAG_WIDTH,
               CFG.CIV_FLAG_HEIGHT
            );
      } catch (IndexOutOfBoundsException var12) {
         ImageManager.getImage(Images.randomCivilizationFlag)
            .draw(
               oSB,
               iPosX,
               iPosY + iHeight - tempValuesHeight - CFG.PADDING - ImageManager.getImage(Images.randomCivilizationFlag).getHeight() - CFG.CIV_FLAG_HEIGHT,
               CFG.CIV_FLAG_WIDTH,
               CFG.CIV_FLAG_HEIGHT
            );
      }

      ImageManager.getImage(Images.flag_rect).draw(oSB, iPosX, iPosY + iHeight - tempValuesHeight - CFG.PADDING - CFG.CIV_FLAG_HEIGHT);
   }

   protected final void drawData_ONLY_SPLTTED(SpriteBatch oSB, int iPosX, int iPosY, int iWidth, int iHeight, List<Color> nColors) {
      if (this.lTime == 0L) {
         this.lTime = System.currentTimeMillis();
      }

      int tempValuesHeight = 0;
      if (this.lTime + 725L > System.currentTimeMillis()) {
         int tempHeight = 0;

         for(int i = 0; i < this.lValues.size(); ++i) {
            tempHeight += this.lValues.get(i).getHeight();
         }

         tempHeight = (int)((float)tempHeight * ((float)(System.currentTimeMillis() - this.lTime) / 725.0F));
         tempValuesHeight = tempHeight;
         int i = 0;

         for(int tempAnimationHeight = 0; i < this.lValues.size(); ++i) {
            try {
               this.lValues
                  .get(i)
                  .draw(
                     oSB,
                     iPosX,
                     iPosY + iHeight,
                     iWidth,
                     tempAnimationHeight,
                     tempHeight >= this.lValues.get(i).getHeight() ? this.lValues.get(i).getHeight() : tempHeight,
                     nColors.get(this.lValues.get(i).getDataTypeID())
                  );
            } catch (IndexOutOfBoundsException var13) {
               this.lValues
                  .get(i)
                  .draw(
                     oSB,
                     iPosX,
                     iPosY + iHeight,
                     iWidth,
                     tempAnimationHeight,
                     tempHeight >= this.lValues.get(i).getHeight() ? this.lValues.get(i).getHeight() : tempHeight,
                     Color.WHITE
                  );
            }

            tempAnimationHeight += this.lValues.get(i).getHeight();
            tempHeight -= this.lValues.get(i).getHeight();
            if (tempHeight <= 0) {
               break;
            }
         }

         CFG.setRender_3(true);
      } else {
         for(int i = 0; i < this.lValues.size(); ++i) {
            try {
               this.lValues.get(i).draw(oSB, iPosX, iPosY + iHeight, iWidth, tempValuesHeight, nColors.get(this.lValues.get(i).getDataTypeID()));
            } catch (IndexOutOfBoundsException var12) {
               this.lValues.get(i).draw(oSB, iPosX, iPosY + iHeight, iWidth, tempValuesHeight, Color.WHITE);
            }

            tempValuesHeight += this.lValues.get(i).getHeight();
         }
      }

      CFG.fontMain.getData().setScale(0.8F);
      CFG.drawTextRotated(
         oSB,
         "" + (float)this.getValue() / 100.0F,
         iPosX + iWidth / 2 - (int)((float)CFG.TEXT_HEIGHT * 0.8F / 2.0F),
         iPosY + iHeight - CFG.PADDING,
         new Color(1.0F, 1.0F, 1.0F, 0.45F),
         90.0F
      );
      CFG.fontMain.getData().setScale(1.0F);
      oSB.setColor(Color.WHITE);
      CFG.game
         .getCiv(this.iCivID)
         .getFlag()
         .draw(
            oSB,
            iPosX,
            iPosY + iHeight - tempValuesHeight - CFG.PADDING - CFG.CIV_FLAG_HEIGHT - CFG.game.getCiv(this.iCivID).getFlag().getHeight(),
            CFG.CIV_FLAG_WIDTH,
            CFG.CIV_FLAG_HEIGHT
         );
      ImageManager.getImage(Images.flag_rect).draw(oSB, iPosX, iPosY + iHeight - tempValuesHeight - CFG.PADDING - CFG.CIV_FLAG_HEIGHT);
   }

   protected final void buildHeights(int nGraphHeight, int nMaxValue) {
      for(int i = 0; i < this.lValues.size(); ++i) {
         this.lValues.get(i).setHeight((int)((float)this.lValues.get(i).getValue() / (float)nMaxValue * (float)nGraphHeight));
      }
   }

   protected final void buildContintentData() {
      this.lValues.clear();
      List<Integer> numOfProvincesByContinents = new ArrayList<>();

      for(int i = 0; i < CFG.map.getMapContinents().getContinentsSize(); ++i) {
         numOfProvincesByContinents.add(0);
      }

      for(int i = 0; i < CFG.game.getCiv(this.iCivID).getNumOfProvinces(); ++i) {
         numOfProvincesByContinents.set(
            CFG.game.getProvince(CFG.game.getCiv(this.iCivID).getProvinceID(i)).getContinent(),
            numOfProvincesByContinents.get(CFG.game.getProvince(CFG.game.getCiv(this.iCivID).getProvinceID(i)).getContinent()) + 1
         );
      }

      List<Graph_Vertical_Data_Value> tempValues = new ArrayList<>();

      for(int i = 0; i < CFG.map.getMapContinents().getContinentsSize(); ++i) {
         if (numOfProvincesByContinents.get(i) > 0) {
            tempValues.add(new Graph_Vertical_Data_Value_Continent(numOfProvincesByContinents.get(i), i));
         }
      }

      while(tempValues.size() > 0) {
         int tempMaxID = 0;

         for(int i = 1; i < tempValues.size(); ++i) {
            if (tempValues.get(tempMaxID).getValue() < tempValues.get(i).getValue()) {
               tempMaxID = i;
            }
         }

         this.lValues.add(tempValues.get(tempMaxID));
         tempValues.remove(tempMaxID);
      }
   }

   protected final void buildPopulationData() {
      this.lValues.clear();
      List<Integer> numOfPopulation = new ArrayList<>();

      for(int i = 0; i < CFG.game.getCivsSize(); ++i) {
         numOfPopulation.add(0);
      }

      for(int i = 0; i < CFG.game.getCiv(this.iCivID).getNumOfProvinces(); ++i) {
         for(int j = 0; j < CFG.game.getProvince(CFG.game.getCiv(this.iCivID).getProvinceID(i)).getPopulationData().getNationalitiesSize(); ++j) {
            numOfPopulation.set(
               CFG.game.getProvince(CFG.game.getCiv(this.iCivID).getProvinceID(i)).getPopulationData().getCivID(j),
               numOfPopulation.get(CFG.game.getProvince(CFG.game.getCiv(this.iCivID).getProvinceID(i)).getPopulationData().getCivID(j))
                  + CFG.game.getProvince(CFG.game.getCiv(this.iCivID).getProvinceID(i)).getPopulationData().getPopulationID(j)
            );
         }
      }

      int nSecondBiggestPopulationID = 0;
      int nRestOfPopulation = 0;

      for(int i = nSecondBiggestPopulationID + 1; i < CFG.game.getCivsSize(); ++i) {
         if (numOfPopulation.get(nSecondBiggestPopulationID) < numOfPopulation.get(i) && i != this.iCivID) {
            nSecondBiggestPopulationID = i;
         }
      }

      for(int i = 0; i < CFG.game.getCivsSize(); ++i) {
         if (i != nSecondBiggestPopulationID && i != this.iCivID) {
            nRestOfPopulation += numOfPopulation.get(i);
         }
      }

      this.lValues.add(new Graph_Vertical_Data_Value_Population(numOfPopulation.get(this.iCivID), this.iCivID));
      this.lValues.add(new Graph_Vertical_Data_Value_Population(numOfPopulation.get(nSecondBiggestPopulationID), nSecondBiggestPopulationID));
      this.lValues.add(new Graph_Vertical_Data_Value_Population(nRestOfPopulation, 0));
   }

   protected final void buildPopulationOfCivilizationAllAroundTheWorldData(int nOfCivID) {
      this.lValues.clear();
      int nPopulation = 0;

      for(int i = 0; i < CFG.game.getCiv(this.iCivID).getNumOfProvinces(); ++i) {
         nPopulation += CFG.game.getProvince(CFG.game.getCiv(this.iCivID).getProvinceID(i)).getPopulationData().getPopulationOfCivID(nOfCivID);
      }

      this.lValues.add(new Graph_Vertical_Data_Value_PopulationAllAroundTheWorld(nPopulation, nOfCivID));
   }

   protected final void buildArmiesData() {
      this.lValues.clear();
      int nNumOfUnits = CFG.game.getCiv(this.iCivID).getNumOfUnits();

      for(int i = 0; i < CFG.game.getCiv(this.iCivID).getArmyInAnotherProvinceSize(); ++i) {
         nNumOfUnits += CFG.game.getProvince(CFG.game.getCiv(this.iCivID).getArmyInAnotherProvince(i)).getArmyCivID(this.iCivID);
      }

      this.lValues.add(new Graph_Vertical_Data_Value_PopulationAllAroundTheWorld(nNumOfUnits, this.iCivID));
   }

   protected final void buildArmyPerCapitaData() {
      this.lValues.clear();
      int nPopulation = 0;
      int nNumOfUnits = CFG.game.getCiv(this.iCivID).getNumOfUnits();

      for(int i = 0; i < CFG.game.getCiv(this.iCivID).getNumOfProvinces(); ++i) {
         nPopulation += CFG.game.getProvince(CFG.game.getCiv(this.iCivID).getProvinceID(i)).getPopulationData().getPopulationOfCivID(this.iCivID);
      }

      for(int i = 0; i < CFG.game.getCiv(this.iCivID).getArmyInAnotherProvinceSize(); ++i) {
         nNumOfUnits += CFG.game.getProvince(CFG.game.getCiv(this.iCivID).getArmyInAnotherProvince(i)).getArmyCivID(this.iCivID);
      }

      nPopulation += nNumOfUnits;
      this.lValues.add(new Graph_Vertical_Data_Value_ArmyPerCapita((int)((float)nNumOfUnits * 100.0F / (float)nPopulation * 100.0F), this.iCivID));
   }

   protected final void buildTechnologyLevelsData() {
      this.lValues.clear();
      this.lValues.add(new Graph_Vertical_Data_Value_TechnologyLevels((int)(CFG.game.getCiv(this.iCivID).getTechnologyLevel() * 100.0F), this.iCivID));
   }

   protected final void buildPopulationByProvincesData() {
      this.lValues.clear();
      this.lValues
         .add(new Graph_Vertical_Data_Value_PopulationByProvinces(CFG.game.getProvince(this.iCivID).getPopulationData().getPopulation(), this.iCivID));
      this.iCivID = CFG.game.getProvince(this.iCivID).getCivID();
   }

   protected final void buildEconomyByProvincesData() {
      this.lValues.clear();
      this.lValues.add(new Graph_Vertical_Data_Value_PopulationByProvinces(CFG.game.getProvince(this.iCivID).getEconomy(), this.iCivID));
      this.iCivID = CFG.game.getProvince(this.iCivID).getCivID();
   }

   protected final void buildConqueredProvincesData() {
      this.lValues.clear();
      this.lValues.add(new Graph_Vertical_Data_Value_PopulationByProvinces(CFG.game.getCiv(this.iCivID).civGameData.iNumOfConqueredProvinces, this.iCivID));
   }

   protected final void buildConstructedBuildingsData() {
      this.lValues.clear();
      this.lValues.add(new Graph_Vertical_Data_Value_PopulationByProvinces(CFG.game.getCiv(this.iCivID).civGameData.iNumOfBuildingsConstructed, this.iCivID));
   }

   protected final void buildArmyByProvincesData() {
      this.lValues.clear();
      int nArmySize = 0;

      for(int i = 0; i < CFG.game.getProvince(this.iCivID).getCivsSize(); ++i) {
         nArmySize += CFG.game.getProvince(this.iCivID).getArmy(i);
      }

      this.lValues.add(new Graph_Vertical_Data_Value_PopulationByProvinces(nArmySize, this.iCivID));
      this.iCivID = CFG.game.getProvince(this.iCivID).getCivID();
   }

   protected final void buildTechnologyLevelsByProvincesData() {
      this.lValues.clear();
      this.lValues
         .add(new Graph_Vertical_Data_Value_PopulationByProvinces((int)(CFG.game.getProvince(this.iCivID).getDevelopmentLevel() * 100.0F), this.iCivID));
      this.iCivID = CFG.game.getProvince(this.iCivID).getCivID();
   }

   protected final void buildEconomyData() {
      this.lValues.clear();
      int nEconomy = 0;

      for(int i = 0; i < CFG.game.getCiv(this.iCivID).getNumOfProvinces(); ++i) {
         nEconomy += CFG.game.getProvince(CFG.game.getCiv(this.iCivID).getProvinceID(i)).getEconomy();
      }

      this.lValues.add(new Graph_Vertical_Data_Value_Population(nEconomy, this.iCivID));
   }

   protected final void buildPopulationOfCivByNationalitiesData(int nCivID) {
      this.lValues.clear();
      int nPopulation = 0;

      for(int i = 0; i < CFG.game.getCiv(nCivID).getNumOfProvinces(); ++i) {
         nPopulation += CFG.game.getProvince(CFG.game.getCiv(nCivID).getProvinceID(i)).getPopulationData().getPopulationOfCivID(this.iCivID);
      }

      this.lValues.add(new Graph_Vertical_Data_Value_Population(nPopulation, this.iCivID));
   }

   protected final int getCivID() {
      return this.iCivID;
   }

   protected final int getValue() {
      int tOut = 0;

      for(int i = 0; i < this.lValues.size(); ++i) {
         tOut += this.lValues.get(i).getValue();
      }

      return tOut;
   }

   protected final boolean getInView() {
      return this.inView;
   }

   protected final void setInView(boolean inView) {
      this.inView = inView;
   }

   protected final void resetAnimation() {
      this.lTime = 0L;
   }

   protected final int getValuesSize() {
      return this.lValues.size();
   }

   protected final int getValue(int i) {
      return this.lValues.get(i).getValue();
   }

   protected final int getValueDataTypeID(int i) {
      return this.lValues.get(i).getDataTypeID();
   }
}
