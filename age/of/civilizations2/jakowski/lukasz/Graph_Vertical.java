package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.utils.ScissorStack;
import java.util.ArrayList;
import java.util.List;

class Graph_Vertical extends MenuElement {
   private List<Graph_Vertical_Data> lValues;
   private int iValuesSize = 0;
   private int iValuesTotal = 0;
   private int iDataWidth = 0;
   private Graph_Vertical_Info verticalInfo;
   private Graph_Vertical_Data_Type GRAPH_DATA_TYPE;
   private boolean splitBy100 = false;
   private boolean statisticsMode = false;
   private String sTextX;
   private String sTextY;
   private int iWidthTextY;
   private int iMinPoint;
   private int iMaxPoint;
   private float fAvaragePoint;
   private int iAvaragePosY;
   private byte bDecimal = 0;
   private boolean lessThanTen = false;
   private boolean moveable = false;
   private int iButtonsPosX;
   private int iButtonsPosY;
   private int iHoveredID = -1;
   private boolean scrollModeY = false;
   private int iScrollPosX = -1;
   private int iScrollPosX2 = -1;
   private float fScrollNewMenuPosY = 0.0F;
   private Graph_Vertical.DrawStatisticsData drawStatisticsData;

   protected Graph_Vertical(
      Graph_Vertical_Data_Type nType,
      String sTextX,
      String sTextY,
      int iPosX,
      int iPosY,
      int iWidth,
      int iHeight,
      boolean visible,
      List<Graph_Vertical_Data> nValues
   ) {
      this.GRAPH_DATA_TYPE = nType;
      if (this.GRAPH_DATA_TYPE == Graph_Vertical_Data_Type.NUM_OF_PROVINCES_BY_CONTINENT) {
         this.drawStatisticsData = new Graph_Vertical.DrawStatisticsData() {
            @Override
            public void draw(SpriteBatch oSB, int i, int tempOffsetX, int iTranslateX, int iTranslateY) {
               for(int j = 0; j < Graph_Vertical.this.lValues.get(i).getValuesSize(); ++j) {
                  Graph_Vertical.this.drawStatisticsValue(
                     oSB,
                     "" + Graph_Vertical.this.lValues.get(i).getValue(j),
                     Graph_Vertical.this.getPosX()
                        + (int)((float)CFG.TEXT_HEIGHT * 0.7F)
                        + CFG.PADDING * 2
                        + Graph_Vertical.this.getStatisticsWidth() * 2
                        + Graph_Vertical.this.getStatisticsWidth()
                           * Graph_Vertical.this.verticalInfo.getSortedID(Graph_Vertical.this.lValues.get(i).getValueDataTypeID(j) - 1)
                        + iTranslateX,
                     Graph_Vertical.this.getPosY() + ((int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2) * (i + 1) + iTranslateY
                  );
               }

               Graph_Vertical.this.drawStatisticsValue(
                  oSB,
                  ""
                     + Graph_Vertical.this.lValues.get(i).getValue()
                     + " ["
                     + CFG.getPercentage(Graph_Vertical.this.lValues.get(i).getValue(), Graph_Vertical.this.iValuesTotal, 4)
                     + "%]",
                  Graph_Vertical.this.getPosX() + (int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2 + tempOffsetX + iTranslateX,
                  Graph_Vertical.this.getPosY() + ((int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2) * (i + 1) + iTranslateY
               );
            }

            @Override
            public String getTotal() {
               return CFG.sTOTAL + " [" + Graph_Vertical.this.iValuesTotal + "]";
            }

            @Override
            public String getStatsLP(int i) {
               return CFG.game.getCiv(Graph_Vertical.this.lValues.get(i).getCivID()).getCivName();
            }

            @Override
            public int getStatsLPCivFlagID(int i) {
               return Graph_Vertical.this.lValues.get(i).getCivID();
            }
         };
      } else if (this.GRAPH_DATA_TYPE == Graph_Vertical_Data_Type.POPULATION_OF_CIVILIZATIONS) {
         this.drawStatisticsData = new Graph_Vertical.DrawStatisticsData() {
            @Override
            public void draw(SpriteBatch oSB, int i, int tempOffsetX, int iTranslateX, int iTranslateY) {
               try {
                  Graph_Vertical.this.drawStatisticsValue(
                     oSB,
                     ""
                        + Graph_Vertical.this.lValues.get(i).getValue(0)
                        + " ["
                        + CFG.getPercentage(Graph_Vertical.this.lValues.get(i).getValue(0), Graph_Vertical.this.lValues.get(i).getValue(), 4)
                        + "%]",
                     Graph_Vertical.this.getPosX()
                        + (int)((float)CFG.TEXT_HEIGHT * 0.7F)
                        + CFG.PADDING * 2
                        + Graph_Vertical.this.getStatisticsWidth() * 2
                        + Graph_Vertical.this.getStatisticsWidth() * 0
                        + iTranslateX,
                     Graph_Vertical.this.getPosY() + ((int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2) * (i + 1) + iTranslateY
                  );
                  if (Graph_Vertical.this.lValues.get(i).getValue(1) > 0) {
                     Graph_Vertical.this.drawStatisticsValueWithFlag(
                        oSB,
                        ""
                           + Graph_Vertical.this.lValues.get(i).getValue(1)
                           + " ["
                           + CFG.getPercentage(Graph_Vertical.this.lValues.get(i).getValue(1), Graph_Vertical.this.lValues.get(i).getValue(), 4)
                           + "%]",
                        Graph_Vertical.this.lValues.get(i).getValueDataTypeID(1),
                        Graph_Vertical.this.getPosX()
                           + (int)((float)CFG.TEXT_HEIGHT * 0.7F)
                           + CFG.PADDING * 2
                           + Graph_Vertical.this.getStatisticsWidth() * 2
                           + Graph_Vertical.this.getStatisticsWidth() * 1
                           + iTranslateX,
                        Graph_Vertical.this.getPosY() + ((int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2) * (i + 1) + iTranslateY
                     );
                  } else {
                     Graph_Vertical.this.drawStatisticsValue(
                        oSB,
                        "" + Graph_Vertical.this.lValues.get(i).getValue(1),
                        Graph_Vertical.this.getPosX()
                           + (int)((float)CFG.TEXT_HEIGHT * 0.7F)
                           + CFG.PADDING * 2
                           + Graph_Vertical.this.getStatisticsWidth() * 2
                           + Graph_Vertical.this.getStatisticsWidth() * 1
                           + iTranslateX,
                        Graph_Vertical.this.getPosY() + ((int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2) * (i + 1) + iTranslateY
                     );
                  }

                  if (Graph_Vertical.this.lValues.get(i).getValue(2) > 0) {
                     Graph_Vertical.this.drawStatisticsValue(
                        oSB,
                        ""
                           + Graph_Vertical.this.lValues.get(i).getValue(2)
                           + " ["
                           + CFG.getPercentage(Graph_Vertical.this.lValues.get(i).getValue(2), Graph_Vertical.this.lValues.get(i).getValue(), 4)
                           + "%]",
                        Graph_Vertical.this.getPosX()
                           + (int)((float)CFG.TEXT_HEIGHT * 0.7F)
                           + CFG.PADDING * 2
                           + Graph_Vertical.this.getStatisticsWidth() * 2
                           + Graph_Vertical.this.getStatisticsWidth() * 2
                           + iTranslateX,
                        Graph_Vertical.this.getPosY() + ((int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2) * (i + 1) + iTranslateY
                     );
                  } else {
                     Graph_Vertical.this.drawStatisticsValue(
                        oSB,
                        "" + Graph_Vertical.this.lValues.get(i).getValue(2),
                        Graph_Vertical.this.getPosX()
                           + (int)((float)CFG.TEXT_HEIGHT * 0.7F)
                           + CFG.PADDING * 2
                           + Graph_Vertical.this.getStatisticsWidth() * 2
                           + Graph_Vertical.this.getStatisticsWidth() * 2
                           + iTranslateX,
                        Graph_Vertical.this.getPosY() + ((int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2) * (i + 1) + iTranslateY
                     );
                  }

                  Graph_Vertical.this.drawStatisticsValue(
                     oSB,
                     "" + CFG.getPercentage(Graph_Vertical.this.lValues.get(i).getValue(), Graph_Vertical.this.iValuesTotal, 5) + "%",
                     Graph_Vertical.this.getPosX() + (int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2 + tempOffsetX + iTranslateX,
                     Graph_Vertical.this.getPosY() + ((int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2) * (i + 1) + iTranslateY
                  );
               } catch (IndexOutOfBoundsException var7) {
                  CFG.setRender_3(true);
               }
            }

            @Override
            public String getTotal() {
               return CFG.sTOTAL_WORLDS_POPULATION;
            }

            @Override
            public String getStatsLP(int i) {
               return CFG.game.getCiv(Graph_Vertical.this.lValues.get(i).getCivID()).getCivName();
            }

            @Override
            public int getStatsLPCivFlagID(int i) {
               return Graph_Vertical.this.lValues.get(i).getCivID();
            }
         };
      } else if (this.GRAPH_DATA_TYPE == Graph_Vertical_Data_Type.POPULATION_OF_CIVILIZATION_ALL_AROUND_WORLD) {
         this.drawStatisticsData = new Graph_Vertical.DrawStatisticsData() {
            @Override
            public void draw(SpriteBatch oSB, int i, int tempOffsetX, int iTranslateX, int iTranslateY) {
               for(int j = 0; j < Graph_Vertical.this.lValues.get(i).getValuesSize(); ++j) {
                  Graph_Vertical.this.drawStatisticsValueWithFlag(
                     oSB,
                     "" + Graph_Vertical.this.lValues.get(i).getValue(j),
                     Graph_Vertical.this.lValues.get(i).getValueDataTypeID(j),
                     Graph_Vertical.this.getPosX()
                        + (int)((float)CFG.TEXT_HEIGHT * 0.7F)
                        + CFG.PADDING * 2
                        + Graph_Vertical.this.getStatisticsWidth() * 2
                        + Graph_Vertical.this.getStatisticsWidth() * j
                        + iTranslateX,
                     Graph_Vertical.this.getPosY() + ((int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2) * (i + 1) + iTranslateY
                  );
               }

               Graph_Vertical.this.drawStatisticsValue(
                  oSB,
                  "" + CFG.getPercentage(Graph_Vertical.this.lValues.get(i).getValue(), Graph_Vertical.this.iValuesTotal, 5) + "%",
                  Graph_Vertical.this.getPosX() + (int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2 + tempOffsetX + iTranslateX,
                  Graph_Vertical.this.getPosY() + ((int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2) * (i + 1) + iTranslateY
               );
            }

            @Override
            public String getTotal() {
               return CFG.sTOTAL;
            }

            @Override
            public String getStatsLP(int i) {
               return CFG.game.getCiv(Graph_Vertical.this.lValues.get(i).getCivID()).getCivName();
            }

            @Override
            public int getStatsLPCivFlagID(int i) {
               return Graph_Vertical.this.lValues.get(i).getCivID();
            }
         };
      } else if (this.GRAPH_DATA_TYPE == Graph_Vertical_Data_Type.ARMIES) {
         this.drawStatisticsData = new Graph_Vertical.DrawStatisticsData() {
            @Override
            public void draw(SpriteBatch oSB, int i, int tempOffsetX, int iTranslateX, int iTranslateY) {
               Graph_Vertical.this.drawStatisticsValue(
                  oSB,
                  "" + Graph_Vertical.this.lValues.get(i).getValue(0),
                  Graph_Vertical.this.getPosX()
                     + (int)((float)CFG.TEXT_HEIGHT * 0.7F)
                     + CFG.PADDING * 2
                     + Graph_Vertical.this.getStatisticsWidth() * 2
                     + iTranslateX,
                  Graph_Vertical.this.getPosY() + ((int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2) * (i + 1) + iTranslateY
               );
               Graph_Vertical.this.drawStatisticsValue(
                  oSB,
                  "" + CFG.getPercentage(Graph_Vertical.this.lValues.get(i).getValue(), Graph_Vertical.this.iValuesTotal, 5) + "%",
                  Graph_Vertical.this.getPosX() + (int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2 + tempOffsetX + iTranslateX,
                  Graph_Vertical.this.getPosY() + ((int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2) * (i + 1) + iTranslateY
               );
            }

            @Override
            public String getTotal() {
               return CFG.sTOTAL + " [" + Graph_Vertical.this.iValuesTotal + "]";
            }

            @Override
            public String getStatsLP(int i) {
               return CFG.game.getCiv(Graph_Vertical.this.lValues.get(i).getCivID()).getCivName();
            }

            @Override
            public int getStatsLPCivFlagID(int i) {
               return Graph_Vertical.this.lValues.get(i).getCivID();
            }
         };
      } else if (this.GRAPH_DATA_TYPE == Graph_Vertical_Data_Type.ARMY_PER_CAPITA) {
         this.drawStatisticsData = new Graph_Vertical.DrawStatisticsData() {
            @Override
            public void draw(SpriteBatch oSB, int i, int tempOffsetX, int iTranslateX, int iTranslateY) {
               Graph_Vertical.this.drawStatisticsValue(
                  oSB,
                  "" + (float)Graph_Vertical.this.lValues.get(i).getValue(0) / 100.0F,
                  Graph_Vertical.this.getPosX()
                     + (int)((float)CFG.TEXT_HEIGHT * 0.7F)
                     + CFG.PADDING * 2
                     + Graph_Vertical.this.getStatisticsWidth() * 2
                     + iTranslateX,
                  Graph_Vertical.this.getPosY() + ((int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2) * (i + 1) + iTranslateY
               );
               Graph_Vertical.this.drawStatisticsValue(
                  oSB,
                  "" + CFG.getPercentage(Graph_Vertical.this.lValues.get(i).getValue(), Graph_Vertical.this.iValuesTotal, 5) + "%",
                  Graph_Vertical.this.getPosX() + (int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2 + tempOffsetX + iTranslateX,
                  Graph_Vertical.this.getPosY() + ((int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2) * (i + 1) + iTranslateY
               );
            }

            @Override
            public String getTotal() {
               return CFG.sTOTAL;
            }

            @Override
            public String getStatsLP(int i) {
               return CFG.game.getCiv(Graph_Vertical.this.lValues.get(i).getCivID()).getCivName();
            }

            @Override
            public int getStatsLPCivFlagID(int i) {
               return Graph_Vertical.this.lValues.get(i).getCivID();
            }
         };
         this.splitBy100 = true;
      } else if (this.GRAPH_DATA_TYPE == Graph_Vertical_Data_Type.TECHNOLOGY_LEVELS) {
         this.drawStatisticsData = new Graph_Vertical.DrawStatisticsData() {
            @Override
            public void draw(SpriteBatch oSB, int i, int tempOffsetX, int iTranslateX, int iTranslateY) {
               Graph_Vertical.this.drawStatisticsValue(
                  oSB,
                  "" + (float)Graph_Vertical.this.lValues.get(i).getValue(0) / 100.0F,
                  Graph_Vertical.this.getPosX()
                     + (int)((float)CFG.TEXT_HEIGHT * 0.7F)
                     + CFG.PADDING * 2
                     + Graph_Vertical.this.getStatisticsWidth() * 2
                     + iTranslateX,
                  Graph_Vertical.this.getPosY() + ((int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2) * (i + 1) + iTranslateY
               );
               Graph_Vertical.this.drawStatisticsValue(
                  oSB,
                  "" + (float)Graph_Vertical.this.lValues.get(i).getValue(0) / 100.0F + "%",
                  Graph_Vertical.this.getPosX() + (int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2 + tempOffsetX + iTranslateX,
                  Graph_Vertical.this.getPosY() + ((int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2) * (i + 1) + iTranslateY
               );
            }

            @Override
            public String getTotal() {
               return CFG.sTOTAL;
            }

            @Override
            public String getStatsLP(int i) {
               return CFG.game.getCiv(Graph_Vertical.this.lValues.get(i).getCivID()).getCivName();
            }

            @Override
            public int getStatsLPCivFlagID(int i) {
               return Graph_Vertical.this.lValues.get(i).getCivID();
            }
         };
         this.splitBy100 = true;
      } else if (this.GRAPH_DATA_TYPE == Graph_Vertical_Data_Type.POPULATION_BY_PROVINCES) {
         this.drawStatisticsData = new Graph_Vertical.DrawStatisticsData() {
            @Override
            public void draw(SpriteBatch oSB, int i, int tempOffsetX, int iTranslateX, int iTranslateY) {
               for(int j = 0; j < Graph_Vertical.this.lValues.get(i).getValuesSize(); ++j) {
                  Graph_Vertical.this.drawStatisticsValue(
                     oSB,
                     "" + Graph_Vertical.this.lValues.get(i).getValue(j),
                     Graph_Vertical.this.getPosX()
                        + (int)((float)CFG.TEXT_HEIGHT * 0.7F)
                        + CFG.PADDING * 2
                        + Graph_Vertical.this.getStatisticsWidth() * 2
                        + Graph_Vertical.this.getStatisticsWidth() * j
                        + iTranslateX,
                     Graph_Vertical.this.getPosY() + ((int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2) * (i + 1) + iTranslateY
                  );
               }

               Graph_Vertical.this.drawStatisticsValue(
                  oSB,
                  "" + CFG.getPercentage(Graph_Vertical.this.lValues.get(i).getValue(), Graph_Vertical.this.iValuesTotal, 5) + "%",
                  Graph_Vertical.this.getPosX() + (int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2 + tempOffsetX + iTranslateX,
                  Graph_Vertical.this.getPosY() + ((int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2) * (i + 1) + iTranslateY
               );
            }

            @Override
            public String getTotal() {
               return CFG.sTOTAL_WORLDS_POPULATION + " [" + Graph_Vertical.this.iValuesTotal + "]";
            }

            @Override
            public String getStatsLP(int i) {
               return CFG.game.getProvince(Graph_Vertical.this.lValues.get(i).getValueDataTypeID(0)).getName();
            }

            @Override
            public int getStatsLPCivFlagID(int i) {
               return CFG.game.getProvince(Graph_Vertical.this.lValues.get(i).getValueDataTypeID(0)).getCivID();
            }
         };
      } else if (this.GRAPH_DATA_TYPE == Graph_Vertical_Data_Type.CONQUERED_PROVINCES) {
         this.drawStatisticsData = new Graph_Vertical.DrawStatisticsData() {
            @Override
            public void draw(SpriteBatch oSB, int i, int tempOffsetX, int iTranslateX, int iTranslateY) {
               for(int j = 0; j < Graph_Vertical.this.lValues.get(i).getValuesSize(); ++j) {
                  Graph_Vertical.this.drawStatisticsValue(
                     oSB,
                     "" + Graph_Vertical.this.lValues.get(i).getValue(j),
                     Graph_Vertical.this.getPosX()
                        + (int)((float)CFG.TEXT_HEIGHT * 0.7F)
                        + CFG.PADDING * 2
                        + Graph_Vertical.this.getStatisticsWidth() * 2
                        + Graph_Vertical.this.getStatisticsWidth() * j
                        + iTranslateX,
                     Graph_Vertical.this.getPosY() + ((int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2) * (i + 1) + iTranslateY
                  );
               }

               Graph_Vertical.this.drawStatisticsValue(
                  oSB,
                  "" + CFG.getPercentage(Graph_Vertical.this.lValues.get(i).getValue(), Graph_Vertical.this.iValuesTotal, 5) + "%",
                  Graph_Vertical.this.getPosX() + (int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2 + tempOffsetX + iTranslateX,
                  Graph_Vertical.this.getPosY() + ((int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2) * (i + 1) + iTranslateY
               );
            }

            @Override
            public String getTotal() {
               return CFG.sTOTAL + " [" + Graph_Vertical.this.iValuesTotal + "]";
            }

            @Override
            public String getStatsLP(int i) {
               return CFG.game.getCiv(Graph_Vertical.this.lValues.get(i).getValueDataTypeID(0)).getCivName();
            }

            @Override
            public int getStatsLPCivFlagID(int i) {
               return Graph_Vertical.this.lValues.get(i).getValueDataTypeID(0);
            }
         };
      } else if (this.GRAPH_DATA_TYPE == Graph_Vertical_Data_Type.CONSTRUCTED_BUILDINGS) {
         this.drawStatisticsData = new Graph_Vertical.DrawStatisticsData() {
            @Override
            public void draw(SpriteBatch oSB, int i, int tempOffsetX, int iTranslateX, int iTranslateY) {
               for(int j = 0; j < Graph_Vertical.this.lValues.get(i).getValuesSize(); ++j) {
                  Graph_Vertical.this.drawStatisticsValue(
                     oSB,
                     "" + Graph_Vertical.this.lValues.get(i).getValue(j),
                     Graph_Vertical.this.getPosX()
                        + (int)((float)CFG.TEXT_HEIGHT * 0.7F)
                        + CFG.PADDING * 2
                        + Graph_Vertical.this.getStatisticsWidth() * 2
                        + Graph_Vertical.this.getStatisticsWidth() * j
                        + iTranslateX,
                     Graph_Vertical.this.getPosY() + ((int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2) * (i + 1) + iTranslateY
                  );
               }

               Graph_Vertical.this.drawStatisticsValue(
                  oSB,
                  "" + CFG.getPercentage(Graph_Vertical.this.lValues.get(i).getValue(), Graph_Vertical.this.iValuesTotal, 5) + "%",
                  Graph_Vertical.this.getPosX() + (int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2 + tempOffsetX + iTranslateX,
                  Graph_Vertical.this.getPosY() + ((int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2) * (i + 1) + iTranslateY
               );
            }

            @Override
            public String getTotal() {
               return CFG.sTOTAL + " [" + Graph_Vertical.this.iValuesTotal + "]";
            }

            @Override
            public String getStatsLP(int i) {
               return CFG.game.getCiv(Graph_Vertical.this.lValues.get(i).getValueDataTypeID(0)).getCivName();
            }

            @Override
            public int getStatsLPCivFlagID(int i) {
               return Graph_Vertical.this.lValues.get(i).getValueDataTypeID(0);
            }
         };
      } else if (this.GRAPH_DATA_TYPE == Graph_Vertical_Data_Type.ECONOMY_BY_PROVINCES) {
         this.drawStatisticsData = new Graph_Vertical.DrawStatisticsData() {
            @Override
            public void draw(SpriteBatch oSB, int i, int tempOffsetX, int iTranslateX, int iTranslateY) {
               for(int j = 0; j < Graph_Vertical.this.lValues.get(i).getValuesSize(); ++j) {
                  Graph_Vertical.this.drawStatisticsValue(
                     oSB,
                     "" + Graph_Vertical.this.lValues.get(i).getValue(j),
                     Graph_Vertical.this.getPosX()
                        + (int)((float)CFG.TEXT_HEIGHT * 0.7F)
                        + CFG.PADDING * 2
                        + Graph_Vertical.this.getStatisticsWidth() * 2
                        + Graph_Vertical.this.getStatisticsWidth() * j
                        + iTranslateX,
                     Graph_Vertical.this.getPosY() + ((int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2) * (i + 1) + iTranslateY
                  );
               }

               Graph_Vertical.this.drawStatisticsValue(
                  oSB,
                  "" + CFG.getPercentage(Graph_Vertical.this.lValues.get(i).getValue(), Graph_Vertical.this.iValuesTotal, 5) + "%",
                  Graph_Vertical.this.getPosX() + (int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2 + tempOffsetX + iTranslateX,
                  Graph_Vertical.this.getPosY() + ((int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2) * (i + 1) + iTranslateY
               );
            }

            @Override
            public String getTotal() {
               return CFG.sTOTAL + " [" + Graph_Vertical.this.iValuesTotal + "]";
            }

            @Override
            public String getStatsLP(int i) {
               return CFG.game.getProvince(Graph_Vertical.this.lValues.get(i).getValueDataTypeID(0)).getName();
            }

            @Override
            public int getStatsLPCivFlagID(int i) {
               return CFG.game.getProvince(Graph_Vertical.this.lValues.get(i).getValueDataTypeID(0)).getCivID();
            }
         };
      } else if (this.GRAPH_DATA_TYPE == Graph_Vertical_Data_Type.ARMY_BY_PROVINCES) {
         this.drawStatisticsData = new Graph_Vertical.DrawStatisticsData() {
            @Override
            public void draw(SpriteBatch oSB, int i, int tempOffsetX, int iTranslateX, int iTranslateY) {
               for(int j = 0; j < Graph_Vertical.this.lValues.get(i).getValuesSize(); ++j) {
                  Graph_Vertical.this.drawStatisticsValue(
                     oSB,
                     "" + Graph_Vertical.this.lValues.get(i).getValue(j),
                     Graph_Vertical.this.getPosX()
                        + (int)((float)CFG.TEXT_HEIGHT * 0.7F)
                        + CFG.PADDING * 2
                        + Graph_Vertical.this.getStatisticsWidth() * 2
                        + Graph_Vertical.this.getStatisticsWidth() * j
                        + iTranslateX,
                     Graph_Vertical.this.getPosY() + ((int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2) * (i + 1) + iTranslateY
                  );
               }

               Graph_Vertical.this.drawStatisticsValue(
                  oSB,
                  "" + CFG.getPercentage(Graph_Vertical.this.lValues.get(i).getValue(), Graph_Vertical.this.iValuesTotal, 5) + "%",
                  Graph_Vertical.this.getPosX() + (int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2 + tempOffsetX + iTranslateX,
                  Graph_Vertical.this.getPosY() + ((int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2) * (i + 1) + iTranslateY
               );
            }

            @Override
            public String getTotal() {
               return CFG.sTOTAL + " [" + Graph_Vertical.this.iValuesTotal + "]";
            }

            @Override
            public String getStatsLP(int i) {
               return CFG.game.getProvince(Graph_Vertical.this.lValues.get(i).getValueDataTypeID(0)).getName();
            }

            @Override
            public int getStatsLPCivFlagID(int i) {
               return CFG.game.getProvince(Graph_Vertical.this.lValues.get(i).getValueDataTypeID(0)).getCivID();
            }
         };
      } else if (this.GRAPH_DATA_TYPE == Graph_Vertical_Data_Type.TECHNOLOGY_LEVELS_BY_PROVINCES) {
         this.drawStatisticsData = new Graph_Vertical.DrawStatisticsData() {
            @Override
            public void draw(SpriteBatch oSB, int i, int tempOffsetX, int iTranslateX, int iTranslateY) {
               for(int j = 0; j < Graph_Vertical.this.lValues.get(i).getValuesSize(); ++j) {
                  Graph_Vertical.this.drawStatisticsValue(
                     oSB,
                     "" + (float)Graph_Vertical.this.lValues.get(i).getValue(j) / 100.0F,
                     Graph_Vertical.this.getPosX()
                        + (int)((float)CFG.TEXT_HEIGHT * 0.7F)
                        + CFG.PADDING * 2
                        + Graph_Vertical.this.getStatisticsWidth() * 2
                        + Graph_Vertical.this.getStatisticsWidth() * j
                        + iTranslateX,
                     Graph_Vertical.this.getPosY() + ((int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2) * (i + 1) + iTranslateY
                  );
               }

               Graph_Vertical.this.drawStatisticsValue(
                  oSB,
                  "" + CFG.getPercentage(Graph_Vertical.this.lValues.get(i).getValue(), Graph_Vertical.this.iValuesTotal, 5) + "%",
                  Graph_Vertical.this.getPosX() + (int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2 + tempOffsetX + iTranslateX,
                  Graph_Vertical.this.getPosY() + ((int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2) * (i + 1) + iTranslateY
               );
            }

            @Override
            public String getTotal() {
               return CFG.sTOTAL + " [" + Graph_Vertical.this.iValuesTotal + "]";
            }

            @Override
            public String getStatsLP(int i) {
               return CFG.game.getProvince(Graph_Vertical.this.lValues.get(i).getValueDataTypeID(0)).getName();
            }

            @Override
            public int getStatsLPCivFlagID(int i) {
               return CFG.game.getProvince(Graph_Vertical.this.lValues.get(i).getValueDataTypeID(0)).getCivID();
            }
         };
         this.splitBy100 = true;
      } else if (this.GRAPH_DATA_TYPE == Graph_Vertical_Data_Type.ECONOMY_OF_CIVILIZATIONS) {
         this.drawStatisticsData = new Graph_Vertical.DrawStatisticsData() {
            @Override
            public void draw(SpriteBatch oSB, int i, int tempOffsetX, int iTranslateX, int iTranslateY) {
               for(int j = 0; j < Graph_Vertical.this.lValues.get(i).getValuesSize(); ++j) {
                  Graph_Vertical.this.drawStatisticsValue(
                     oSB,
                     "" + Graph_Vertical.this.lValues.get(i).getValue(j),
                     Graph_Vertical.this.getPosX()
                        + (int)((float)CFG.TEXT_HEIGHT * 0.7F)
                        + CFG.PADDING * 2
                        + Graph_Vertical.this.getStatisticsWidth() * 2
                        + Graph_Vertical.this.getStatisticsWidth() * j
                        + iTranslateX,
                     Graph_Vertical.this.getPosY() + ((int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2) * (i + 1) + iTranslateY
                  );
               }

               Graph_Vertical.this.drawStatisticsValue(
                  oSB,
                  "" + CFG.getPercentage(Graph_Vertical.this.lValues.get(i).getValue(), Graph_Vertical.this.iValuesTotal, 5) + "%",
                  Graph_Vertical.this.getPosX() + (int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2 + tempOffsetX + iTranslateX,
                  Graph_Vertical.this.getPosY() + ((int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2) * (i + 1) + iTranslateY
               );
            }

            @Override
            public String getTotal() {
               return CFG.sTOTAL + " [" + Graph_Vertical.this.iValuesTotal + "]";
            }

            @Override
            public String getStatsLP(int i) {
               return CFG.game.getCiv(Graph_Vertical.this.lValues.get(i).getCivID()).getCivName();
            }

            @Override
            public int getStatsLPCivFlagID(int i) {
               return Graph_Vertical.this.lValues.get(i).getCivID();
            }
         };
      } else if (this.GRAPH_DATA_TYPE == Graph_Vertical_Data_Type.POPULATION_OF_CIVILIZATION_BY_NATIONALITIES) {
         this.drawStatisticsData = new Graph_Vertical.DrawStatisticsData() {
            @Override
            public void draw(SpriteBatch oSB, int i, int tempOffsetX, int iTranslateX, int iTranslateY) {
               for(int j = 0; j < Graph_Vertical.this.lValues.get(i).getValuesSize(); ++j) {
                  Graph_Vertical.this.drawStatisticsValue(
                     oSB,
                     "" + Graph_Vertical.this.lValues.get(i).getValue(j),
                     Graph_Vertical.this.getPosX()
                        + (int)((float)CFG.TEXT_HEIGHT * 0.7F)
                        + CFG.PADDING * 2
                        + Graph_Vertical.this.getStatisticsWidth() * 2
                        + Graph_Vertical.this.getStatisticsWidth() * j
                        + iTranslateX,
                     Graph_Vertical.this.getPosY() + ((int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2) * (i + 1) + iTranslateY
                  );
               }

               Graph_Vertical.this.drawStatisticsValue(
                  oSB,
                  "" + CFG.getPercentage(Graph_Vertical.this.lValues.get(i).getValue(), Graph_Vertical.this.iValuesTotal, 5) + "%",
                  Graph_Vertical.this.getPosX() + (int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2 + tempOffsetX + iTranslateX,
                  Graph_Vertical.this.getPosY() + ((int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2) * (i + 1) + iTranslateY
               );
            }

            @Override
            public String getTotal() {
               return CFG.sTOTAL + " [" + Graph_Vertical.this.iValuesTotal + "]";
            }

            @Override
            public String getStatsLP(int i) {
               return CFG.game.getCiv(Graph_Vertical.this.lValues.get(i).getCivID()).getCivName();
            }

            @Override
            public int getStatsLPCivFlagID(int i) {
               return Graph_Vertical.this.lValues.get(i).getCivID();
            }
         };
      }

      this.iDataWidth = CFG.CIV_FLAG_WIDTH;
      this.setPosX(iPosX);
      this.setPosY(iPosY);
      this.setWidth(iWidth);
      this.setHeight(iHeight);
      this.setVisible(visible);
      this.sTextX = sTextX;
      this.sTextY = sTextY;
      CFG.fontMain.getData().setScale(0.7F);
      CFG.glyphLayout.setText(CFG.fontMain, sTextY);
      this.iWidthTextY = (int)CFG.glyphLayout.width;
      CFG.fontMain.getData().setScale(1.0F);
      this.lValues = nValues;
      this.iValuesSize = this.lValues.size();
      this.buildData();
      this.buildValuesHeights();
      this.typeOfElement = MenuElement.TypeOfElement.GRAPH_VERTICAL;
   }

   @Override
   protected void updateHover(int nPosX, int nPosY, int menuPosX, int menuPosY) {
      if (!this.statisticsMode) {
         if (nPosX >= menuPosX + this.getPosX()
            && nPosX <= menuPosX + this.getPosX() + this.getWidth()
            && nPosY >= menuPosY + this.getPosY()
            && nPosY <= menuPosY + this.getPosY() + this.getHeight()) {
            for(int i = 0; i < this.iValuesSize; ++i) {
               if (nPosX
                     >= menuPosX + this.getPosX() + this.getWidth() - CFG.PADDING * (i + 1) - CFG.PADDING * i - this.iDataWidth * (i + 1) + this.iButtonsPosX
                  && nPosX
                     <= menuPosX
                        + this.getPosX()
                        + this.getWidth()
                        - CFG.PADDING * (i + 1)
                        - CFG.PADDING * i
                        - this.iDataWidth * (i + 1)
                        + this.iButtonsPosX
                        + this.iDataWidth) {
                  this.setHoveredID(i);
                  return;
               }
            }
         }

         this.setHoveredID(-1);
      } else {
         if (nPosX >= menuPosX + this.getPosX()
            && nPosX <= menuPosX + this.getPosX() + this.getWidth()
            && nPosY >= menuPosY + this.getPosY()
            && nPosY <= menuPosY + this.getPosY() + this.getHeight()) {
            for(int i = 0; i < this.iValuesSize; ++i) {
               if (nPosY >= menuPosY + this.getPosY() + this.iButtonsPosY + (int)((float)CFG.TEXT_HEIGHT * 0.7F + (float)(CFG.PADDING * 2)) * i
                  && nPosY
                     <= menuPosY
                        + this.getPosY()
                        + this.iButtonsPosY
                        + (int)((float)CFG.TEXT_HEIGHT * 0.7F + (float)(CFG.PADDING * 2)) * i
                        + (int)((float)CFG.TEXT_HEIGHT * 0.7F + (float)(CFG.PADDING * 2))) {
                  this.setHoveredID(i);
                  return;
               }
            }
         }

         this.setHoveredID(-1);
      }
   }

   private final void setHoveredID(int nHoveredID) {
      if (this.iHoveredID != nHoveredID) {
         this.iHoveredID = nHoveredID;
         this.buildElementHover();
      }
   }

   @Override
   protected void buildElementHover() {
      if (this.iHoveredID >= 0) {
         if (this.GRAPH_DATA_TYPE == Graph_Vertical_Data_Type.NUM_OF_PROVINCES_BY_CONTINENT) {
            if (!this.statisticsMode) {
               List<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               List<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();

               try {
                  nData.add(new MenuElement_Hover_v2_Element_Type_Flag(this.lValues.get(this.iHoveredID).getCivID()));
                  nData.add(
                     new MenuElement_Hover_v2_Element_Type_Text(
                        CFG.game.getCiv(this.lValues.get(this.iHoveredID).getCivID()).getCivName(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                     )
                  );
                  nData.add(
                     new MenuElement_Hover_v2_Element_Type_Text(" [" + (this.iHoveredID + 1) + "/" + this.iValuesSize + "]", CFG.COLOR_TEXT_MODIFIER_NEUTRAL)
                  );
                  nElements.add(new MenuElement_Hover_v2_Element2(nData));
                  nData.clear();
               } catch (IndexOutOfBoundsException var4) {
                  nData.add(new MenuElement_Hover_v2_Element_Type_Image(Images.randomCivilizationFlag));
                  nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Undiscovered"), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                  nData.add(
                     new MenuElement_Hover_v2_Element_Type_Text(" [" + (this.iHoveredID + 1) + "/" + this.iValuesSize + "]", CFG.COLOR_TEXT_MODIFIER_NEUTRAL)
                  );
                  nElements.add(new MenuElement_Hover_v2_Element2(nData));
                  nData.clear();
               }

               nData.add(new MenuElement_Hover_v2_Element_Type_Space());
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Provinces") + ": "));
               nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + this.lValues.get(this.iHoveredID).getValue(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();

               for(int i = 0; i < this.lValues.get(this.iHoveredID).getValuesSize(); ++i) {
                  nData.add(
                     new MenuElement_Hover_v2_Element_Type_Text(
                        "" + CFG.map.getMapContinents().getName(this.lValues.get(this.iHoveredID).getValueDataTypeID(i)) + ": "
                     )
                  );
                  nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + this.lValues.get(this.iHoveredID).getValue(i), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
                  nElements.add(new MenuElement_Hover_v2_Element2(nData));
                  nData.clear();
               }

               this.menuElementHover = new MenuElement_Hover_v2(nElements);
            } else if (this.iHoveredID - 1 >= 0) {
               List<MenuElement_Hover_v2_Element2> nElements = new ArrayList<>();
               List<MenuElement_Hover_v2_Element_Type> nData = new ArrayList<>();
               nData.add(new MenuElement_Hover_v2_Element_Type_Flag(this.lValues.get(this.iHoveredID - 1).getCivID()));
               nData.add(
                  new MenuElement_Hover_v2_Element_Type_Text(
                     CFG.game.getCiv(this.lValues.get(this.iHoveredID - 1).getCivID()).getCivName(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE
                  )
               );
               nData.add(
                  new MenuElement_Hover_v2_Element_Type_Text(" [" + (this.iHoveredID - 1 + 1) + "/" + this.iValuesSize + "]", CFG.COLOR_TEXT_MODIFIER_NEUTRAL)
               );
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               nData.add(new MenuElement_Hover_v2_Element_Type_Space());
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();
               nData.add(new MenuElement_Hover_v2_Element_Type_Text(CFG.langManager.get("Provinces") + ": "));
               nData.add(new MenuElement_Hover_v2_Element_Type_Text("" + this.lValues.get(this.iHoveredID - 1).getValue(), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE));
               nElements.add(new MenuElement_Hover_v2_Element2(nData));
               nData.clear();

               for(int i = 0; i < this.lValues.get(this.iHoveredID - 1).getValuesSize(); ++i) {
                  nData.add(
                     new MenuElement_Hover_v2_Element_Type_Text(
                        "" + CFG.map.getMapContinents().getName(this.lValues.get(this.iHoveredID - 1).getValueDataTypeID(i)) + ": "
                     )
                  );
                  nData.add(
                     new MenuElement_Hover_v2_Element_Type_Text("" + this.lValues.get(this.iHoveredID - 1).getValue(i), CFG.COLOR_BUTTON_GAME_TEXT_ACTIVE)
                  );
                  nElements.add(new MenuElement_Hover_v2_Element2(nData));
                  nData.clear();
               }

               this.menuElementHover = new MenuElement_Hover_v2(nElements);
            }
         }
      } else {
         MenuElement_Hover_v2.resetAnimation_2();
         this.menuElementHover = null;
      }
   }

   @Override
   protected void setCheckboxState(boolean checkboxState) {
      this.buildValuesHeights();
      this.updateInView();
      this.verticalInfo.updateMoveable(this.getWidth() - ((int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2));
      this.updateMoveable();
      CFG.setRender_3(true);
   }

   @Override
   protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
      if (this.scrollModeY) {
         if (this.statisticsMode) {
            if (Math.abs(this.fScrollNewMenuPosY) > 1.0F) {
               this.setCurrent(this.iButtonsPosY + (int)this.fScrollNewMenuPosY);
               this.fScrollNewMenuPosY *= 0.97F;
            } else {
               this.scrollModeY = false;
            }
         } else if (Math.abs(this.fScrollNewMenuPosY) > 1.0F) {
            this.setCurrent(this.iButtonsPosX + (int)this.fScrollNewMenuPosY);
            this.fScrollNewMenuPosY *= 0.97F;
         } else {
            this.scrollModeY = false;
         }

         CFG.setRender_3(true);
      }

      oSB.setColor(Graph.GRAPH_BG_COLOR);
      ImageManager.getImage(Images.pix255_255_255)
         .draw(
            oSB,
            this.getPosX() + (int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2 + iTranslateX,
            this.getPosY() + iTranslateY,
            this.getWidth() - (int)((float)CFG.TEXT_HEIGHT * 0.7F) - CFG.PADDING * 2,
            this.getHeight() - (int)((float)CFG.TEXT_HEIGHT * 0.7F) - CFG.PADDING * 2
         );
      oSB.setColor(new Color(1.0F, 1.0F, 1.0F, 0.075F));
      ImageManager.getImage(Images.patt2)
         .draw2(
            oSB,
            this.getPosX() + (int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2 + iTranslateX,
            this.getPosY() + 1 - ImageManager.getImage(Images.patt2).getHeight() + iTranslateY,
            this.getWidth() - (int)((float)CFG.TEXT_HEIGHT * 0.7F) - CFG.PADDING * 2,
            this.getHeight() - (int)((float)CFG.TEXT_HEIGHT * 0.7F) - CFG.PADDING * 2
         );
      CFG.fontMain.getData().setScale(0.7F);
      CFG.drawTextRotated(
         oSB,
         this.sTextY,
         this.getPosX() + CFG.PADDING + iTranslateX,
         this.getPosY() + this.getHeight() / 2 + this.iWidthTextY / 2 + iTranslateY,
         Graph.TEXT_COLOR,
         90.0F
      );
      this.verticalInfo
         .draw(
            oSB,
            this.getPosX() + (int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2 + iTranslateX,
            this.getPosY() + this.getHeight() - CFG.PADDING - (int)((float)CFG.TEXT_HEIGHT * 0.7F) + iTranslateY,
            this.getWidth() - ((int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2)
         );
      CFG.fontMain.getData().setScale(1.0F);
      if (this.statisticsMode) {
         this.drawStatisticsBegan(oSB, 1 + iTranslateX, iTranslateY, isActive, scrollableY);
      } else {
         ImageManager.getImage(Images.pix255_255_255)
            .draw(
               oSB,
               this.getPosX() + (int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2 + 1 + iTranslateX,
               this.getPosY()
                  + (this.getHeight() - ((int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2) - CFG.CIV_FLAG_HEIGHT - CFG.PADDING * 2) / 2
                  + CFG.CIV_FLAG_HEIGHT
                  + CFG.PADDING * 2
                  + 1
                  + iTranslateY,
               CFG.PADDING - 1
            );
         oSB.setColor(Graph.GRAPH_LINES_DESC);
         ImageManager.getImage(Images.line_33)
            .draw(
               oSB,
               this.getPosX() + (int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2 + iTranslateX,
               this.getPosY() + CFG.CIV_FLAG_HEIGHT + CFG.PADDING * 2 + 1 + iTranslateY,
               this.getWidth() - (int)((float)CFG.TEXT_HEIGHT * 0.7F) - CFG.PADDING * 2
            );
         ImageManager.getImage(Images.line_33)
            .draw(
               oSB,
               this.getPosX() + (int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2 + iTranslateX,
               this.getPosY() + this.iAvaragePosY + iTranslateY,
               this.getWidth() - (int)((float)CFG.TEXT_HEIGHT * 0.7F) - CFG.PADDING * 2,
               1,
               0.0F,
               -this.iButtonsPosX
            );
         Rectangle clipBounds = new Rectangle(
            (float)(this.getPosX() + (int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2 + 1 + iTranslateX),
            (float)(CFG.GAME_HEIGHT - this.getPosY() - iTranslateY),
            (float)(this.getWidth() - ((int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2) - 1),
            (float)(-this.getHeight())
         );
         oSB.flush();
         ScissorStack.pushScissors(clipBounds);
         if (this.getIsHovered() && this.iHoveredID >= 0) {
            oSB.setColor(Graph.GRAPH_LINES_DESC);
            ImageManager.getImage(Images.gradient)
               .draw(
                  oSB,
                  this.getPosX()
                     + this.getWidth()
                     - 1
                     - CFG.PADDING * (this.iHoveredID + 1)
                     - CFG.PADDING * this.iHoveredID
                     - this.iDataWidth * (this.iHoveredID + 1)
                     + this.iButtonsPosX
                     + iTranslateX,
                  -ImageManager.getImage(Images.gradient).getHeight() + this.getPosY() + iTranslateY,
                  this.iDataWidth + 2,
                  this.getHeight() - ((int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2),
                  false,
                  true
               );
            oSB.setColor(new Color(Graph.GRAPH_LINES_DESC.r, Graph.GRAPH_LINES_DESC.g, Graph.GRAPH_LINES_DESC.b, 0.025F));
            ImageManager.getImage(Images.slider_gradient)
               .draw(
                  oSB,
                  this.getPosX()
                     + this.getWidth()
                     - 1
                     - CFG.PADDING * (this.iHoveredID + 1)
                     - CFG.PADDING * this.iHoveredID
                     - this.iDataWidth * (this.iHoveredID + 1)
                     + this.iButtonsPosX
                     + iTranslateX,
                  -ImageManager.getImage(Images.slider_gradient).getHeight() + this.getPosY() + iTranslateY,
                  (this.iDataWidth + 2) / 4,
                  this.getHeight() - ((int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2),
                  false,
                  false
               );
            ImageManager.getImage(Images.slider_gradient)
               .draw(
                  oSB,
                  this.getPosX()
                     + this.getWidth()
                     + this.iDataWidth
                     + 2
                     - (this.iDataWidth + 2) / 4
                     - 1
                     - CFG.PADDING * (this.iHoveredID + 1)
                     - CFG.PADDING * this.iHoveredID
                     - this.iDataWidth * (this.iHoveredID + 1)
                     + this.iButtonsPosX
                     + iTranslateX,
                  -ImageManager.getImage(Images.slider_gradient).getHeight() + this.getPosY() + iTranslateY,
                  (this.iDataWidth + 2) / 4,
                  this.getHeight() - ((int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2),
                  true,
                  false
               );
            oSB.setColor(Color.WHITE);
         }

         if (this.splitBy100) {
            for(int i = 0; i < this.iValuesSize; ++i) {
               if (this.lValues.get(i).getInView()) {
                  this.lValues
                     .get(i)
                     .drawData_ONLY_SPLTTED(
                        oSB,
                        this.getPosX()
                           + this.getWidth()
                           - CFG.PADDING * (i + 1)
                           - CFG.PADDING * i
                           - this.iDataWidth * (i + 1)
                           + this.iButtonsPosX
                           + iTranslateX,
                        this.getPosY() + iTranslateY,
                        this.iDataWidth,
                        this.getHeight() - ((int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2),
                        this.verticalInfo.getColors()
                     );
               }
            }
         } else {
            for(int i = 0; i < this.iValuesSize; ++i) {
               if (this.lValues.get(i).getInView()) {
                  this.lValues
                     .get(i)
                     .drawData(
                        oSB,
                        this.getPosX()
                           + this.getWidth()
                           - CFG.PADDING * (i + 1)
                           - CFG.PADDING * i
                           - this.iDataWidth * (i + 1)
                           + this.iButtonsPosX
                           + iTranslateX,
                        this.getPosY() + iTranslateY,
                        this.iDataWidth,
                        this.getHeight() - ((int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2),
                        this.verticalInfo.getColors()
                     );
               }
            }
         }

         try {
            oSB.flush();
            ScissorStack.popScissors();
         } catch (IllegalStateException var8) {
         }

         CFG.fontMain.getData().setScale(Graph.POINTS_TEXT_SCALE);
         CFG.drawText(
            oSB,
            "" + this.iMaxPoint,
            this.getPosX() + (int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2 + 1 + (int)(2.0F * CFG.GUI_SCALE) + iTranslateX,
            this.getPosY()
               + CFG.CIV_FLAG_HEIGHT
               + CFG.PADDING * 2
               + 1
               + iTranslateY
               - (int)(2.0F * CFG.GUI_SCALE + Graph.POINTS_TEXT_SCALE * (float)CFG.TEXT_HEIGHT),
            Graph.DATA_COLOR
         );
         CFG.drawText(
            oSB,
            this.bDecimal == 0 ? "" + (int)this.fAvaragePoint : "" + (int)this.fAvaragePoint + "." + (this.lessThanTen ? "0" + this.bDecimal : this.bDecimal),
            this.getPosX() + (int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2 + 1 + (int)(2.0F * CFG.GUI_SCALE) + iTranslateX,
            this.getPosY() - (int)(2.0F * CFG.GUI_SCALE) - (int)((float)CFG.TEXT_HEIGHT * Graph.POINTS_TEXT_SCALE) + this.iAvaragePosY - 1 + iTranslateY,
            Graph.DATA_COLOR
         );
         CFG.fontMain.getData().setScale(1.0F);
      }

      oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.6F));
      ImageManager.getImage(Images.pix255_255_255)
         .draw(
            oSB,
            this.getPosX() - 1 + (int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2 + iTranslateX,
            this.getPosY() + iTranslateY,
            1,
            this.getHeight() - (int)((float)CFG.TEXT_HEIGHT * 0.7F) - CFG.PADDING * 2 + CFG.PADDING
         );
      ImageManager.getImage(Images.pix255_255_255)
         .draw(
            oSB,
            this.getPosX() + (int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2 - CFG.PADDING + iTranslateX,
            this.getPosY() + 1 + this.getHeight() - (int)((float)CFG.TEXT_HEIGHT * 0.7F) - CFG.PADDING * 2 + iTranslateY,
            this.getWidth() - (int)((float)CFG.TEXT_HEIGHT * 0.7F) - CFG.PADDING * 2 + CFG.PADDING,
            1
         );
      oSB.setColor(Graph.GRAPH_BORDERS_COLOR);
      ImageManager.getImage(Images.pix255_255_255)
         .draw(
            oSB,
            this.getPosX() + (int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2 + iTranslateX,
            this.getPosY() + iTranslateY,
            1,
            this.getHeight() - (int)((float)CFG.TEXT_HEIGHT * 0.7F) - CFG.PADDING * 2 + CFG.PADDING
         );
      ImageManager.getImage(Images.pix255_255_255)
         .draw(
            oSB,
            this.getPosX() + (int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2 - CFG.PADDING + iTranslateX,
            this.getPosY() + this.getHeight() - (int)((float)CFG.TEXT_HEIGHT * 0.7F) - CFG.PADDING * 2 + iTranslateY,
            this.getWidth() - (int)((float)CFG.TEXT_HEIGHT * 0.7F) - CFG.PADDING * 2 + CFG.PADDING,
            1
         );
      ImageManager.getImage(Images.pix255_255_255)
         .draw(oSB, this.getPosX() + this.getWidth() - CFG.PADDING + iTranslateX, this.getPosY() + 1 + iTranslateY, CFG.PADDING);
      ImageManager.getImage(Images.pix255_255_255)
         .draw(oSB, this.getPosX() - 1 + this.getWidth() + iTranslateX, this.getPosY() + 1 + iTranslateY, 1, CFG.PADDING - 1);
      ImageManager.getImage(Images.pix255_255_255)
         .draw(
            oSB, this.getPosX() + (int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2 + 1 + iTranslateX, this.getPosY() + 1 + iTranslateY, CFG.PADDING - 1
         );
      ImageManager.getImage(Images.pix255_255_255)
         .draw(
            oSB,
            this.getPosX() + this.getWidth() - 1 + iTranslateX,
            this.getPosY() + this.getHeight() - ((int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2) - CFG.PADDING + 1 + iTranslateY,
            1,
            CFG.PADDING - 1
         );
      oSB.setColor(Color.WHITE);
   }

   private final void drawStatisticsBegan(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
      CFG.fontMain.getData().setScale(0.7F);
      int tempOffsetX = 0;
      this.drawStatisticsBoxTitle(
         oSB,
         this.sTextX,
         this.getPosX() + (int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2 + iTranslateX,
         this.getPosY() + iTranslateY,
         this.getStatisticsWidth() * 2
      );
      tempOffsetX += this.getStatisticsWidth() * 2;

      for(int i = 0; i < this.verticalInfo.getTextSize(); ++i) {
         this.drawStatisticsBoxTitle(
            oSB,
            this.verticalInfo.getText(i),
            this.getPosX() + (int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2 + tempOffsetX + iTranslateX,
            this.getPosY() + iTranslateY,
            this.getStatisticsWidth()
         );
         tempOffsetX += this.getStatisticsWidth();
      }

      this.drawStatisticsBoxTitle(
         oSB,
         this.drawStatisticsData.getTotal(),
         this.getPosX() + (int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2 + tempOffsetX + iTranslateX,
         this.getPosY() + iTranslateY,
         this.getWidth() - tempOffsetX - ((int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2)
      );
      Rectangle clipBounds = new Rectangle(
         (float)(this.getPosX() + (int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2 + 1 + iTranslateX),
         (float)(CFG.GAME_HEIGHT - this.getPosY() - ((int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2) - 2 - iTranslateY),
         (float)(this.getWidth() - ((int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2) - 1),
         (float)(-this.getHeight() + ((int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2) * 2 + 1)
      );
      oSB.flush();
      ScissorStack.pushScissors(clipBounds);
      this.drawStatisticsEnd(oSB, iTranslateX, this.iButtonsPosY + iTranslateY, isActive, scrollableY, tempOffsetX);

      try {
         oSB.flush();
         ScissorStack.popScissors();
      } catch (IllegalStateException var9) {
      }

      oSB.setColor(Graph.GRAPH_LINES_COLOR);

      for(int i = -1; i < this.verticalInfo.getTextSize() - 1; ++i) {
         ImageManager.getImage(Images.line_32_vertical)
            .draw2(
               oSB,
               this.getPosX()
                  + (int)((float)CFG.TEXT_HEIGHT * 0.7F)
                  + CFG.PADDING * 2
                  + this.getStatisticsWidth() * 2
                  + this.getStatisticsWidth() * (i + 1)
                  - 1
                  + iTranslateX,
               this.getPosY()
                  + (int)((float)CFG.TEXT_HEIGHT * 0.7F)
                  + CFG.PADDING * 2
                  - ImageManager.getImage(Images.line_32_vertical).getHeight()
                  + iTranslateY,
               1,
               this.getHeight() - ((int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2) * 2,
               false,
               true
            );
      }

      ImageManager.getImage(Images.pix255_255_255)
         .draw(
            oSB,
            this.getPosX()
               + (int)((float)CFG.TEXT_HEIGHT * 0.7F)
               + CFG.PADDING * 2
               + this.getStatisticsWidth() * 2
               + this.getStatisticsWidth() * (this.verticalInfo.getTextSize() - 1 + 1)
               - 1
               + iTranslateX,
            this.getPosY() + (int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2 + iTranslateY,
            1,
            this.getHeight() - ((int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2) * 2,
            false,
            true
         );
      oSB.setColor(Color.WHITE);
   }

   private final void drawStatisticsEnd(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY, int tempOffsetX) {
      float tempFlagScale = (float)CFG.TEXT_HEIGHT * 0.7F / (float)CFG.CIV_FLAG_HEIGHT;

      for(int i = 0; i < this.iValuesSize; ++i) {
         if (this.lValues.get(i).getInView()) {
            if (i % 2 == 0) {
               this.drawStatisticsRowBG(
                  oSB,
                  this.getPosX() + (int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2 + iTranslateX,
                  this.getPosY() + ((int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2) * (i + 1) + iTranslateY,
                  this.getWidth() - (int)((float)CFG.TEXT_HEIGHT * 0.7F) - CFG.PADDING * 2
               );
            }

            if (this.getIsHovered() && i == this.iHoveredID - 1) {
               this.drawStatisticsRowHoverBG(
                  oSB,
                  this.getPosX() + (int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2 + iTranslateX,
                  this.getPosY() + ((int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2) * (i + 1) + iTranslateY,
                  this.getWidth() - (int)((float)CFG.TEXT_HEIGHT * 0.7F) - CFG.PADDING * 2
               );
            }

            this.drawStatisticsRowLine(
               oSB,
               this.getPosX() + (int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2 + iTranslateX,
               this.getPosY() + ((int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2) * (i + 1) + iTranslateY,
               this.getWidth() - (int)((float)CFG.TEXT_HEIGHT * 0.7F) - CFG.PADDING * 2
            );
            oSB.setColor(Color.WHITE);
            CFG.game
               .getCiv(this.drawStatisticsData.getStatsLPCivFlagID(i))
               .getFlag()
               .draw(
                  oSB,
                  this.getPosX() + (int)((float)CFG.TEXT_HEIGHT * 0.7F + (float)CFG.CIV_COLOR_WIDTH) + CFG.PADDING * 2 + iTranslateX,
                  this.getPosY()
                     + ((int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2) * (i + 1)
                     + CFG.PADDING
                     - CFG.game.getCiv(this.drawStatisticsData.getStatsLPCivFlagID(i)).getFlag().getHeight()
                     + iTranslateY,
                  (int)Math.ceil((double)((float)CFG.CIV_FLAG_WIDTH * tempFlagScale)),
                  (int)Math.ceil((double)((float)CFG.CIV_FLAG_HEIGHT * tempFlagScale))
               );
            ImageManager.getImage(Images.flag_rect)
               .draw(
                  oSB,
                  this.getPosX() + (int)((float)CFG.TEXT_HEIGHT * 0.7F + (float)CFG.CIV_COLOR_WIDTH) + CFG.PADDING * 2 + iTranslateX,
                  this.getPosY() + ((int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2) * (i + 1) + CFG.PADDING - CFG.CIV_FLAG_HEIGHT + iTranslateY,
                  (int)Math.ceil((double)((float)CFG.CIV_FLAG_WIDTH * tempFlagScale)),
                  (int)Math.ceil((double)((float)CFG.CIV_FLAG_HEIGHT * tempFlagScale))
               );
            this.drawStatisticsValue2(
               oSB,
               this.drawStatisticsData.getStatsLP(i),
               this.getPosX()
                  + (int)((float)CFG.CIV_FLAG_WIDTH * tempFlagScale + (float)CFG.CIV_COLOR_WIDTH)
                  + (int)((float)CFG.TEXT_HEIGHT * 0.7F)
                  + CFG.PADDING * 2
                  + iTranslateX,
               this.getPosY() + ((int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2) * (i + 1) + iTranslateY,
               this.getStatisticsWidth() * 2 - (int)((float)CFG.CIV_FLAG_WIDTH * tempFlagScale + (float)CFG.CIV_COLOR_WIDTH)
            );
            this.drawStatisticsData.draw(oSB, i, tempOffsetX, iTranslateX, iTranslateY);
         }
      }

      CFG.fontMain.getData().setScale(1.0F);
   }

   private final void drawStatisticsRowLine(SpriteBatch oSB, int nPosX, int nPosY, int nWidth) {
      oSB.setColor(Graph.GRAPH_LINES_COLOR);
      ImageManager.getImage(Images.line_32_off1).draw(oSB, nPosX, nPosY + (int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2, nWidth, 1);
   }

   private final void drawStatisticsRowBG(SpriteBatch oSB, int nPosX, int nPosY, int nWidth) {
      oSB.setColor(new Color(0.05F, 0.05F, 0.05F, 0.7F));
      ImageManager.getImage(Images.pix255_255_255).draw(oSB, nPosX, nPosY, nWidth - 1, (int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2);
   }

   private final void drawStatisticsRowHoverBG(SpriteBatch oSB, int nPosX, int nPosY, int nWidth) {
      oSB.setColor(new Color(Graph.GRAPH_LINES_DESC.r, Graph.GRAPH_LINES_DESC.g, Graph.GRAPH_LINES_DESC.b, 0.1F));
      ImageManager.getImage(Images.slider_gradient).draw(oSB, nPosX, nPosY, nWidth - 1, (int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2);
   }

   private final void drawStatisticsBoxTitle(SpriteBatch oSB, String nText, int nPosX, int nPosY, int nWidth) {
      oSB.setColor(new Color(0.05F, 0.05F, 0.05F, 0.7F));
      ImageManager.getImage(Images.pix255_255_255).draw(oSB, nPosX, nPosY, nWidth, (int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2);
      oSB.setColor(new Color(0.094F, 0.094F, 0.4F, 0.6F));
      ImageManager.getImage(Images.gradient)
         .draw(
            oSB,
            nPosX,
            nPosY
               - ImageManager.getImage(Images.gradient).getHeight()
               + (int)((float)CFG.TEXT_HEIGHT * 0.7F)
               + CFG.PADDING * 2
               - ((int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2) / 2,
            nWidth,
            ((int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2) / 2,
            false,
            true
         );
      oSB.setColor(Graph.GRAPH_BORDERS_COLOR);
      ImageManager.getImage(Images.pix255_255_255).draw(oSB, nPosX, nPosY + (int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2, nWidth, 1);
      oSB.setColor(Graph.GRAPH_LINES_COLOR);
      ImageManager.getImage(Images.line_32_vertical)
         .draw2(
            oSB,
            nPosX - 1 + nWidth,
            nPosY - ImageManager.getImage(Images.line_32_vertical).getHeight(),
            1,
            (int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2
         );
      Rectangle clipBounds = new Rectangle(
         (float)nPosX, (float)(CFG.GAME_HEIGHT - nPosY), (float)(nWidth - CFG.PADDING), (float)(-((int)((float)CFG.TEXT_HEIGHT * 0.7F)) - CFG.PADDING * 2)
      );
      oSB.flush();
      ScissorStack.pushScissors(clipBounds);
      CFG.drawText(oSB, nText, nPosX + CFG.PADDING, nPosY + CFG.PADDING, Graph.TEXT_COLOR);

      try {
         oSB.flush();
         ScissorStack.popScissors();
      } catch (IllegalStateException var8) {
      }
   }

   private final void drawStatisticsValue(SpriteBatch oSB, String nText, int nPosX, int nPosY) {
      CFG.drawText(oSB, nText, nPosX + CFG.PADDING, nPosY + CFG.PADDING, new Color(1.0F, 1.0F, 1.0F, 0.45F));
   }

   private final void drawStatisticsValueWithFlag(SpriteBatch oSB, String nText, int nCivID, int nPosX, int nPosY) {
      float tempFlagScale = (float)CFG.TEXT_HEIGHT * 0.7F / (float)CFG.CIV_FLAG_HEIGHT;
      oSB.setColor(Color.WHITE);
      CFG.game
         .getCiv(nCivID)
         .getFlag()
         .draw(
            oSB,
            nPosX + CFG.PADDING,
            nPosY + CFG.PADDING - CFG.game.getCiv(nCivID).getFlag().getHeight(),
            (int)Math.ceil((double)((float)CFG.CIV_FLAG_WIDTH * tempFlagScale)),
            (int)Math.ceil((double)((float)CFG.CIV_FLAG_HEIGHT * tempFlagScale))
         );
      ImageManager.getImage(Images.flag_rect)
         .draw(
            oSB,
            nPosX + CFG.PADDING,
            nPosY + CFG.PADDING - CFG.CIV_FLAG_HEIGHT,
            (int)Math.ceil((double)((float)CFG.CIV_FLAG_WIDTH * tempFlagScale)),
            (int)Math.ceil((double)((float)CFG.CIV_FLAG_HEIGHT * tempFlagScale))
         );
      CFG.drawText(
         oSB, nText, nPosX + CFG.PADDING * 2 + (int)((float)CFG.CIV_FLAG_WIDTH * tempFlagScale), nPosY + CFG.PADDING, new Color(1.0F, 1.0F, 1.0F, 0.45F)
      );
   }

   private final void drawStatisticsValue2(SpriteBatch oSB, String nText, int nPosX, int nPosY, int nWidth) {
      Rectangle clipBounds = new Rectangle((float)nPosX, (float)(CFG.GAME_HEIGHT - nPosY), (float)(nWidth - CFG.PADDING), (float)(-this.getHeight()));
      oSB.flush();
      ScissorStack.pushScissors(clipBounds);
      CFG.drawText(oSB, nText, nPosX + CFG.CIV_COLOR_WIDTH, nPosY + CFG.PADDING, Graph.DATA_COLOR);

      try {
         oSB.flush();
         ScissorStack.popScissors();
      } catch (IllegalStateException var8) {
      }
   }

   private final int getStatisticsWidth() {
      return (this.getWidth() - ((int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2)) / (this.verticalInfo.getTextSize() + 3);
   }

   protected final void updateInView() {
      if (this.statisticsMode) {
         for(int i = 0; i < this.iValuesSize; ++i) {
            if (this.getButtonsPosY(i) + this.iButtonsPosY >= (int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2
               && this.getButtonsPosY(i) + this.iButtonsPosY <= this.getHeight() - ((int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2) * 2) {
               this.lValues.get(i).setInView(true);
            } else if (this.getButtonsPosY(i) + this.iButtonsPosY + (int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2 >= 0
               && this.getButtonsPosY(i) + (int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2 + this.iButtonsPosY
                  <= this.getHeight() - ((int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2) * 2) {
               this.lValues.get(i).setInView(true);
            } else {
               this.lValues.get(i).setInView(false);
            }
         }
      } else {
         for(int i = 0; i < this.iValuesSize; ++i) {
            if (this.getButtonsPosX(i) + this.iButtonsPosX >= (int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2
               && this.getButtonsPosX(i) + this.iButtonsPosX <= this.getWidth()) {
               this.lValues.get(i).setInView(true);
            } else if (this.getButtonsPosX(i) - this.iDataWidth + this.iButtonsPosX >= (int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2
               && this.getButtonsPosX(i) - this.iDataWidth + this.iButtonsPosX <= this.getWidth()) {
               this.lValues.get(i).setInView(true);
            } else {
               this.lValues.get(i).setInView(false);
            }
         }
      }
   }

   private final int getButtonsPosX(int i) {
      return this.getWidth() - this.iDataWidth * i - CFG.PADDING - CFG.PADDING * 2 * i;
   }

   private final int getButtonsPosY(int i) {
      return (int)((float)CFG.TEXT_HEIGHT * 0.7F + (float)(CFG.PADDING * 2)) * i;
   }

   private final void updateMoveable() {
      if (this.statisticsMode) {
         if (this.getButtonsPosY(this.iValuesSize) > this.getHeight() - (int)((float)CFG.TEXT_HEIGHT * 0.7F + (float)(CFG.PADDING * 2)) * 2) {
            this.moveable = true;
         } else {
            this.moveable = false;
            this.iButtonsPosY = 0;
         }
      } else if (this.getButtonsWidth() > this.getWidth() - ((int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2)) {
         this.moveable = true;
      } else {
         this.moveable = false;
         this.iButtonsPosX = 0;
      }
   }

   @Override
   protected boolean getIsScrollable() {
      return this.moveable;
   }

   @Override
   protected void srollByWheel(int nScoll) {
      this.scrollModeY = false;
      this.setCurrent(this.getCurrent() + nScoll);
   }

   private final int getButtonsWidth() {
      return this.iDataWidth * this.iValuesSize + CFG.PADDING * 2 * (this.iValuesSize - 1);
   }

   protected final void buildData() {
      if (this.lValues.size() != 0) {
         List<String> nTexts = new ArrayList<>();
         List<Color> nColors = new ArrayList<>();
         if (this.GRAPH_DATA_TYPE == Graph_Vertical_Data_Type.NUM_OF_PROVINCES_BY_CONTINENT) {
            for(int i = 0; i < this.iValuesSize; ++i) {
               this.lValues.get(i).buildContintentData();
            }

            for(int i = 0; i < CFG.map.getMapContinents().getContinentsSize(); ++i) {
               if (i != CFG.map.getMapContinents().getOceanContinentID()) {
                  nTexts.add(CFG.map.getMapContinents().getName(i));
                  nColors.add(CFG.map.getMapContinents().getColor(i));
               }
            }

            this.verticalInfo = new Graph_Vertical_Info(nTexts, nColors, this.getWidth() - ((int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2), true);
         } else if (this.GRAPH_DATA_TYPE == Graph_Vertical_Data_Type.POPULATION_OF_CIVILIZATIONS) {
            for(int i = 0; i < this.iValuesSize; ++i) {
               this.lValues.get(i).buildPopulationData();
            }

            nTexts.add(CFG.langManager.get("Native"));
            nColors.add(CFG.COLOR_POPULATION[(CFG.COLOR_POPULATION.length - 1) * 3 / 4]);
            nTexts.add(CFG.langManager.get("Second"));
            nColors.add(CFG.COLOR_POPULATION[(CFG.COLOR_POPULATION.length - 1) / 4]);
            nTexts.add(CFG.langManager.get("Rest"));
            nColors.add(CFG.COLOR_POPULATION[0]);
            this.verticalInfo = new Graph_Vertical_Info(nTexts, nColors, this.getWidth() - ((int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2), false);
         } else if (this.GRAPH_DATA_TYPE == Graph_Vertical_Data_Type.POPULATION_OF_CIVILIZATION_ALL_AROUND_WORLD) {
            int nOfCivID = 0;
            if (CFG.game.getActiveProvinceID() >= 0 && CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID() > 0) {
               nOfCivID = CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID();
            } else {
               nOfCivID = CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID();
            }

            for(int i = 0; i < this.iValuesSize; ++i) {
               this.lValues.get(i).buildPopulationOfCivilizationAllAroundTheWorldData(nOfCivID);
            }

            for(int i = this.lValues.size() - 1; i >= 0; --i) {
               if (this.lValues.get(i).getValue() == 0) {
                  this.lValues.remove(i);
               }
            }

            this.iValuesSize = this.lValues.size();
            nTexts.add("[" + CFG.game.getCiv(nOfCivID).getCivName() + "]");
            nColors.add(
               new Color(
                  (float)CFG.game.getCiv(nOfCivID).getR() / 255.0F,
                  (float)CFG.game.getCiv(nOfCivID).getG() / 255.0F,
                  (float)CFG.game.getCiv(nOfCivID).getB() / 255.0F,
                  1.0F
               )
            );
            this.verticalInfo = new Graph_Vertical_Info(nTexts, nColors, this.getWidth() - ((int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2), false);
         } else if (this.GRAPH_DATA_TYPE == Graph_Vertical_Data_Type.ARMIES) {
            for(int i = 0; i < this.iValuesSize; ++i) {
               this.lValues.get(i).buildArmiesData();
            }

            nTexts.add(CFG.langManager.get("ArmySize"));
            nColors.add(new Color(0.75F, 0.11F, 0.08F, 1.0F));
            this.verticalInfo = new Graph_Vertical_Info(nTexts, nColors, this.getWidth() - ((int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2), false);
         } else if (this.GRAPH_DATA_TYPE == Graph_Vertical_Data_Type.ARMY_PER_CAPITA) {
            for(int i = 0; i < this.iValuesSize; ++i) {
               this.lValues.get(i).buildArmyPerCapitaData();
            }

            nTexts.add(CFG.langManager.get("ArmyPerCapita"));
            nColors.add(new Color(0.7F, 0.18F, 0.14F, 1.0F));
            this.verticalInfo = new Graph_Vertical_Info(nTexts, nColors, this.getWidth() - ((int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2), false);
         } else if (this.GRAPH_DATA_TYPE == Graph_Vertical_Data_Type.TECHNOLOGY_LEVELS) {
            for(int i = 0; i < this.iValuesSize; ++i) {
               this.lValues.get(i).buildTechnologyLevelsData();
            }

            nTexts.add(CFG.langManager.get("TechnologyLevels"));
            nColors.add(CFG.COLOR_TECHNOLOGY_LEVEL[(CFG.COLOR_TECHNOLOGY_LEVEL.length - 1) * 3 / 4]);
            this.verticalInfo = new Graph_Vertical_Info(nTexts, nColors, this.getWidth() - ((int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2), false);
         } else if (this.GRAPH_DATA_TYPE == Graph_Vertical_Data_Type.POPULATION_BY_PROVINCES) {
            for(int i = 0; i < this.iValuesSize; ++i) {
               this.lValues.get(i).buildPopulationByProvincesData();
            }

            nTexts.add(CFG.langManager.get("Population"));
            nColors.add(CFG.COLOR_POPULATION[(CFG.COLOR_POPULATION.length - 1) * 3 / 4]);
            this.verticalInfo = new Graph_Vertical_Info(nTexts, nColors, this.getWidth() - ((int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2), false);
         } else if (this.GRAPH_DATA_TYPE == Graph_Vertical_Data_Type.ECONOMY_BY_PROVINCES) {
            for(int i = 0; i < this.iValuesSize; ++i) {
               this.lValues.get(i).buildEconomyByProvincesData();
            }

            nTexts.add(CFG.langManager.get("Economy"));
            nColors.add(CFG.COLOR_ECONOMY[(CFG.COLOR_ECONOMY.length - 1) * 3 / 4]);
            this.verticalInfo = new Graph_Vertical_Info(nTexts, nColors, this.getWidth() - ((int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2), false);
         } else if (this.GRAPH_DATA_TYPE == Graph_Vertical_Data_Type.CONQUERED_PROVINCES) {
            for(int i = 0; i < this.iValuesSize; ++i) {
               this.lValues.get(i).buildConqueredProvincesData();
            }

            nTexts.add(CFG.langManager.get("ConqueredProvinces"));
            nColors.add(CFG.COLOR_ECONOMY[(CFG.COLOR_ECONOMY.length - 1) * 3 / 4]);
            this.verticalInfo = new Graph_Vertical_Info(nTexts, nColors, this.getWidth() - ((int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2), false);
         } else if (this.GRAPH_DATA_TYPE == Graph_Vertical_Data_Type.CONSTRUCTED_BUILDINGS) {
            for(int i = 0; i < this.iValuesSize; ++i) {
               this.lValues.get(i).buildConstructedBuildingsData();
            }

            nTexts.add(CFG.langManager.get("ConstructedBuildings"));
            nColors.add(CFG.COLOR_ECONOMY[(CFG.COLOR_ECONOMY.length - 1) * 3 / 4]);
            this.verticalInfo = new Graph_Vertical_Info(nTexts, nColors, this.getWidth() - ((int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2), false);
         } else if (this.GRAPH_DATA_TYPE == Graph_Vertical_Data_Type.ARMY_BY_PROVINCES) {
            for(int i = 0; i < this.iValuesSize; ++i) {
               this.lValues.get(i).buildArmyByProvincesData();
            }

            nTexts.add(CFG.langManager.get("Army"));
            nColors.add(new Color(0.7F, 0.18F, 0.14F, 1.0F));
            this.verticalInfo = new Graph_Vertical_Info(nTexts, nColors, this.getWidth() - ((int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2), false);
         } else if (this.GRAPH_DATA_TYPE == Graph_Vertical_Data_Type.TECHNOLOGY_LEVELS_BY_PROVINCES) {
            for(int i = 0; i < this.iValuesSize; ++i) {
               this.lValues.get(i).buildTechnologyLevelsByProvincesData();
            }

            nTexts.add(CFG.langManager.get("TechnologyLevels"));
            nColors.add(CFG.COLOR_TECHNOLOGY_LEVEL[(CFG.COLOR_TECHNOLOGY_LEVEL.length - 1) * 3 / 4]);
            this.verticalInfo = new Graph_Vertical_Info(nTexts, nColors, this.getWidth() - ((int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2), false);
         } else if (this.GRAPH_DATA_TYPE == Graph_Vertical_Data_Type.ECONOMY_OF_CIVILIZATIONS) {
            for(int i = 0; i < this.iValuesSize; ++i) {
               this.lValues.get(i).buildEconomyData();
            }

            nTexts.add(CFG.langManager.get("Economy"));
            nColors.add(CFG.COLOR_ECONOMY[(CFG.COLOR_ECONOMY.length - 1) * 3 / 4]);
            this.verticalInfo = new Graph_Vertical_Info(nTexts, nColors, this.getWidth() - ((int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2), false);
         } else if (this.GRAPH_DATA_TYPE == Graph_Vertical_Data_Type.POPULATION_OF_CIVILIZATION_BY_NATIONALITIES) {
            int nOfCivID = 0;
            if (CFG.game.getActiveProvinceID() >= 0 && CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID() > 0) {
               nOfCivID = CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID();
            } else {
               nOfCivID = CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID();
            }

            for(int i = 0; i < this.iValuesSize; ++i) {
               this.lValues.get(i).buildPopulationOfCivByNationalitiesData(nOfCivID);
            }

            for(int i = this.lValues.size() - 1; i >= 0; --i) {
               if (this.lValues.get(i).getValue() == 0) {
                  this.lValues.remove(i);
               }
            }

            this.iValuesSize = this.lValues.size();
            nTexts.add("[" + CFG.game.getCiv(nOfCivID).getCivName() + "]");
            nColors.add(
               new Color(
                  (float)CFG.game.getCiv(nOfCivID).getR() / 255.0F,
                  (float)CFG.game.getCiv(nOfCivID).getG() / 255.0F,
                  (float)CFG.game.getCiv(nOfCivID).getB() / 255.0F,
                  1.0F
               )
            );
            this.verticalInfo = new Graph_Vertical_Info(nTexts, nColors, this.getWidth() - ((int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2), false);
         }

         List<Graph_Vertical_Data> tempData = new ArrayList<>();

         for(int i = 0; i < this.iValuesSize; ++i) {
            tempData.add(this.lValues.get(i));
         }

         this.lValues.clear();

         while(tempData.size() > 0) {
            int tempMaxID = 0;

            for(int i = 1; i < tempData.size(); ++i) {
               if (tempData.get(i).getValue() > tempData.get(tempMaxID).getValue()) {
                  tempMaxID = i;
               }
            }

            this.lValues.add(tempData.get(tempMaxID));
            tempData.remove(tempMaxID);
         }

         try {
            this.iMinPoint = this.iMaxPoint = this.lValues.get(0).getValue();
         } catch (IndexOutOfBoundsException var8) {
            this.iMinPoint = 0;
         }

         this.fAvaragePoint = 0.0F;
         long tempAvarage = 0L;
         int tempAvarageSize = 0;

         for(int i = 0; i < this.iValuesSize; ++i) {
            if (this.iMaxPoint < this.lValues.get(i).getValue()) {
               this.iMaxPoint = this.lValues.get(i).getValue();
            }

            if (this.iMinPoint > this.lValues.get(i).getValue()) {
               this.iMinPoint = this.lValues.get(i).getValue();
            }

            if (this.lValues.get(i).getValue() > 0) {
               ++tempAvarageSize;
               tempAvarage += (long)this.lValues.get(i).getValue();
            }
         }

         this.fAvaragePoint = (float)tempAvarage / (float)tempAvarageSize;
         this.iAvaragePosY = (int)(
            (float)(this.getHeight() - (int)((float)CFG.TEXT_HEIGHT * 0.7F) - CFG.PADDING * 2)
               - (float)(this.getHeight() - (int)((float)CFG.TEXT_HEIGHT * 0.7F) - CFG.PADDING * 2)
                  * 100.0F
                  * this.fAvaragePoint
                  / (float)(this.iMaxPoint - this.iMinPoint)
                  / 100.0F
         );
         this.roundAverage();
         this.updateMoveable();
         this.updateInView();
         this.countValuesTotal();
      }
   }

   protected final void countValuesTotal() {
      this.iValuesTotal = 0;

      for(int i = 0; i < this.iValuesSize; ++i) {
         this.iValuesTotal += this.lValues.get(i).getValue();
      }
   }

   protected final void buildValuesHeights() {
      for(int i = 0; i < this.iValuesSize; ++i) {
         this.lValues
            .get(i)
            .buildHeights(this.getHeight() - (int)((float)CFG.TEXT_HEIGHT * 0.7F) - CFG.PADDING * 2 - (CFG.CIV_FLAG_HEIGHT + CFG.PADDING * 2), this.iMaxPoint);
      }
   }

   private final void roundAverage() {
      if (this.fAvaragePoint - (float)((int)this.fAvaragePoint) != 0.0F) {
         this.bDecimal = (byte)Math.round((this.fAvaragePoint - (float)((int)this.fAvaragePoint)) * 100.0F);
         this.fAvaragePoint -= this.fAvaragePoint - (float)((int)this.fAvaragePoint);
         this.lessThanTen = false;
         if (this.bDecimal % 10 == 0) {
            this.bDecimal = (byte)(this.bDecimal / 10);
         } else if (this.bDecimal < 10) {
            this.lessThanTen = true;
         }
      } else {
         this.bDecimal = 0;
      }
   }

   @Override
   protected boolean getMoveable() {
      return this.moveable;
   }

   @Override
   protected int getCurrent() {
      return this.statisticsMode ? this.iButtonsPosY : this.iButtonsPosX;
   }

   @Override
   protected void setCurrent(int nButtonsPosX) {
      if (this.statisticsMode) {
         if (nButtonsPosX > 0) {
            nButtonsPosX = 0;
            CFG.menuManager.setUpdateSliderMenuPosY(true);
            this.scrollModeY = false;
         } else if (nButtonsPosX
            < -((int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2) * this.iValuesSize
               + (this.getHeight() - ((int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2) * 2)) {
            nButtonsPosX = -((int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2) * this.iValuesSize
               + (this.getHeight() - ((int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2) * 2);
            CFG.menuManager.setUpdateSliderMenuPosY(true);
            this.scrollModeY = false;
         }

         if (this.iButtonsPosY != nButtonsPosX) {
            this.iButtonsPosY = nButtonsPosX;
            this.updateInView();
            CFG.setRender_3(true);
         }
      } else {
         if (nButtonsPosX < 0) {
            nButtonsPosX = 0;
            CFG.menuManager.setUpdateSliderMenuPosX(true);
            this.scrollModeY = false;
         } else if (nButtonsPosX
            > this.getButtonsWidth() - this.getWidth() + this.iDataWidth + CFG.PADDING - ((int)((float)CFG.TEXT_HEIGHT * 0.7F) - CFG.PADDING * 2)) {
            nButtonsPosX = this.getButtonsWidth() - this.getWidth() + this.iDataWidth + CFG.PADDING - ((int)((float)CFG.TEXT_HEIGHT * 0.7F) - CFG.PADDING * 2);
            CFG.menuManager.setUpdateSliderMenuPosX(true);
            this.scrollModeY = false;
         }

         if (this.iButtonsPosX != nButtonsPosX) {
            this.iButtonsPosX = nButtonsPosX;
            this.updateInView();
            CFG.setRender_3(true);
         }
      }
   }

   @Override
   protected final void scrollTheMenu() {
      if (this.moveable && this.iScrollPosX > 0 && this.iScrollPosX2 > 0 && (float)Math.abs(this.iScrollPosX - this.iScrollPosX2) > 3.0F * CFG.DENSITY) {
         this.fScrollNewMenuPosY = (float)(this.iScrollPosX - this.iScrollPosX2) * 1.25F;
         this.scrollModeY = true;
      }
   }

   @Override
   protected final void setScrollPosY(int iScrollPosX) {
      this.iScrollPosX2 = this.iScrollPosX;
      this.iScrollPosX = iScrollPosX;
   }

   @Override
   protected void setTypeOfButton(Button.TypeOfButton typeOfButton) {
      this.iScrollPosX = this.iScrollPosX2 = -1;
      this.scrollModeY = false;
   }

   @Override
   protected boolean getAnotherView() {
      return this.statisticsMode;
   }

   @Override
   protected void setAnotherView(boolean inAnotherView) {
      this.statisticsMode = inAnotherView;
      this.scrollModeY = false;
      this.iButtonsPosX = this.iButtonsPosY = 0;
      if (!this.statisticsMode) {
         for(int i = 0; i < this.iValuesSize; ++i) {
            this.lValues.get(i).resetAnimation();
         }
      }

      this.updateMoveable();
      this.updateInView();
      this.setHoveredID(-1);
   }

   @Override
   protected void setVisible(boolean isVisible) {
      super.setVisible(isVisible);
      this.setHoveredID(-1);
   }

   interface DrawStatisticsData {
      void draw(SpriteBatch var1, int var2, int var3, int var4, int var5);

      String getTotal();

      String getStatsLP(int var1);

      int getStatsLPCivFlagID(int var1);
   }
}
