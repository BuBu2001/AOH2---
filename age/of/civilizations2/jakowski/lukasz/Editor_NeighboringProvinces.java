package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.GdxRuntimeException;
import java.util.ArrayList;
import java.util.List;

class Editor_NeighboringProvinces extends Editor {
   protected int activeProvinceID = -1;

   @Override
   protected void keyDown(int keycode) {
      if (Gdx.input.isKeyPressed(62)) {
         this.activeProvinceID = CFG.game.getActiveProvinceID();
         CFG.MANAGE_DIPLOMACY_ADD_NEW_PACT_CIV2 = this.activeProvinceID;
         CFG.menuManager.rebuildMapEditor_Connections_IDs(this.activeProvinceID);
      }

      if (CFG.game.getActiveProvinceID() >= 0) {
         if (Gdx.input.isKeyPressed(19)) {
            try {
               FileHandle file = Gdx.files.internal("map/" + CFG.map.getFile_ActiveMap_Path() + "updatePB/" + CFG.game.getActiveProvinceID());
               String[] tempSplit = file.readString().split(";");
               String[] prov = tempSplit[0].split(",");
               String[] tempX = tempSplit[1].split(",");
               String[] tempY = tempSplit[2].split(",");
               int provID = Integer.parseInt(prov[0]);
               List<Short> tempPointsX = new ArrayList<>();
               List<Short> tempPointsY = new ArrayList<>();

               for(int j = 0; j < tempX.length; ++j) {
                  tempPointsX.add((short)Integer.parseInt(tempX[j]));
                  tempPointsY.add((short)Integer.parseInt(tempY[j]));
               }

               CFG.game.getProvince(CFG.game.getActiveProvinceID()).removeProvinceBorder(provID);
               CFG.game.getProvince(CFG.game.getActiveProvinceID()).addProvinceBorder(provID, tempPointsX, tempPointsY);
               CFG.game.buildGameProvinceData(CFG.game.getActiveProvinceID());
            } catch (GdxRuntimeException var13) {
               CFG.toast.setInView("FILE NOT FOUND: [map/" + CFG.map.getFile_ActiveMap_Path() + "updatePB/" + CFG.game.getActiveProvinceID() + "]");
            }
         } else if (Gdx.input.isKeyPressed(20)) {
            updateProvince(CFG.game.getActiveProvinceID());
         }
      }

      if (Gdx.input.isKeyPressed(45)) {
         if (CFG.game.getActiveProvinceID() < 0) {
            return;
         }

         String tempX = "";
         String tempY = "";

         for(int i = 0; i < CFG.game.getProvince(CFG.game.getActiveProvinceID()).getPointsSize(); ++i) {
            tempX = tempX + "" + CFG.game.getProvince(CFG.game.getActiveProvinceID()).getPointsX(i) / CFG.map.getMapBG().getMapScale() + ",";
            tempY = tempY + "" + CFG.game.getProvince(CFG.game.getActiveProvinceID()).getPointsY(i) / CFG.map.getMapBG().getMapScale() + ",";
         }

         FileHandle fileSave = Gdx.files.local("map/" + CFG.map.getFile_ActiveMap_Path() + "MAP_POINTS");
         fileSave.writeString("", false);

         for(int i = 0; i < CFG.game.getProvincesSize(); ++i) {
            String tempX2 = "";
            String tempY2 = "";

            for(int j = 0; j < CFG.game.getProvince(i).getPointsSize(); ++j) {
               tempX2 = tempX2
                  + ""
                  + CFG.game.getProvince(i).getPointsX(j) / CFG.map.getMapBG().getMapScale()
                  + (CFG.game.getProvince(i).getPointsSize() - 1 == j ? "" : ",");
               tempY2 = tempY2
                  + ""
                  + CFG.game.getProvince(i).getPointsY(j) / CFG.map.getMapBG().getMapScale()
                  + (CFG.game.getProvince(i).getPointsSize() - 1 == j ? "" : ",");
            }

            fileSave.writeString(tempX2 + "\n", true);
            fileSave.writeString(tempY2 + "\n", true);
         }
      }

      if (this.activeProvinceID >= 0) {
         if (Gdx.input.isKeyPressed(66) && this.activeProvinceID != CFG.game.getActiveProvinceID()) {
            for(int i = 0; i < CFG.game.getProvince(CFG.game.getActiveProvinceID()).getNeighboringProvincesSize(); ++i) {
               if (CFG.game.getProvince(CFG.game.getActiveProvinceID()).getNeighboringProvinces(i) == this.activeProvinceID) {
                  return;
               }
            }

            for(int i = 0; i < CFG.game.getProvince(CFG.game.getActiveProvinceID()).getNeighboringSeaProvincesSize(); ++i) {
               if (CFG.game.getProvince(CFG.game.getActiveProvinceID()).getNeighboringSeaProvinces(i) == this.activeProvinceID) {
                  return;
               }
            }

            for(int i = 0; i < CFG.game.getProvince(this.activeProvinceID).getNeighboringSeaProvincesSize(); ++i) {
               if (CFG.game.getProvince(this.activeProvinceID).getNeighboringSeaProvinces(i) == CFG.game.getActiveProvinceID()) {
                  return;
               }
            }

            if (CFG.game.getProvince(this.activeProvinceID).getSeaProvince()) {
               CFG.game.getProvince(this.activeProvinceID).addNeighboringProvince(CFG.game.getActiveProvinceID());
            } else if (CFG.game.getProvince(CFG.game.getActiveProvinceID()).getSeaProvince()) {
               CFG.game.getProvince(this.activeProvinceID).addNeighboringSeaProvince(CFG.game.getActiveProvinceID());
               CFG.game.getProvince(this.activeProvinceID).setLevelOfPort(1);
            } else {
               CFG.game.getProvince(this.activeProvinceID).addNeighboringProvince(CFG.game.getActiveProvinceID());
            }

            if (CFG.game.getProvince(CFG.game.getActiveProvinceID()).getSeaProvince()) {
               CFG.game.getProvince(CFG.game.getActiveProvinceID()).addNeighboringProvince(this.activeProvinceID);
            } else if (CFG.game.getProvince(this.activeProvinceID).getSeaProvince()) {
               CFG.game.getProvince(CFG.game.getActiveProvinceID()).addNeighboringSeaProvince(this.activeProvinceID);
               CFG.game.getProvince(CFG.game.getActiveProvinceID()).setLevelOfPort(1);
            } else {
               CFG.game.getProvince(CFG.game.getActiveProvinceID()).addNeighboringProvince(this.activeProvinceID);
            }

            List<Short> nPointsX = new ArrayList<>();
            List<Short> nPointsY = new ArrayList<>();

            for(int i = 0; i < CFG.game.getProvince(CFG.game.getActiveProvinceID()).getPointsSize(); ++i) {
               boolean found = false;
               int j = 0;
               if (j < CFG.game.getProvince(this.activeProvinceID).getPointsSize()) {
                  found = true;
                  boolean l1 = false;
                  int o = 0;

                  for(int oSize = CFG.game.getProvince(this.activeProvinceID).getPointsSize(); o < oSize; ++o) {
                     if (CFG.game
                              .getProvince(CFG.game.getActiveProvinceID())
                              .getPointsX(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getPointsSize() - 1)
                           == CFG.game.getProvince(this.activeProvinceID).getPointsX(o)
                        && CFG.game
                              .getProvince(CFG.game.getActiveProvinceID())
                              .getPointsY(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getPointsSize() - 1)
                           == CFG.game.getProvince(this.activeProvinceID).getPointsY(o)) {
                        l1 = true;
                     }
                  }

                  if (l1) {
                     l1 = false;
                     o = 0;

                     for(int oSize = CFG.game.getProvince(CFG.game.getActiveProvinceID()).getPointsSize(); o < oSize; ++o) {
                        if (CFG.game.getProvince(this.activeProvinceID).getPointsX(CFG.game.getProvince(this.activeProvinceID).getPointsSize() - 1)
                              == CFG.game.getProvince(CFG.game.getActiveProvinceID()).getPointsX(o)
                           && CFG.game.getProvince(this.activeProvinceID).getPointsY(CFG.game.getProvince(this.activeProvinceID).getPointsSize() - 1)
                              == CFG.game.getProvince(CFG.game.getActiveProvinceID()).getPointsY(o)) {
                           l1 = true;
                        }
                     }

                     if (!l1) {
                        boolean f1 = false;

                        for(int ox = CFG.game.getProvince(this.activeProvinceID).getPointsSize() - 1; ox >= 0; --ox) {
                           if (!f1) {
                              int n = 0;

                              for(int nSize = CFG.game.getProvince(CFG.game.getActiveProvinceID()).getPointsSize(); n < nSize; ++n) {
                                 if (CFG.game.getProvince(this.activeProvinceID).getPointsX(ox)
                                       == CFG.game.getProvince(CFG.game.getActiveProvinceID()).getPointsX(n)
                                    && CFG.game.getProvince(this.activeProvinceID).getPointsY(ox)
                                       == CFG.game.getProvince(CFG.game.getActiveProvinceID()).getPointsY(n)) {
                                    f1 = true;
                                    nPointsX.add((short)CFG.game.getProvince(this.activeProvinceID).getPointsX(ox));
                                    nPointsY.add((short)CFG.game.getProvince(this.activeProvinceID).getPointsY(ox));
                                    break;
                                 }
                              }
                           } else {
                              boolean end = true;
                              int n = 0;

                              for(int nSize = CFG.game.getProvince(CFG.game.getActiveProvinceID()).getPointsSize(); n < nSize; ++n) {
                                 if (CFG.game.getProvince(this.activeProvinceID).getPointsX(ox)
                                       == CFG.game.getProvince(CFG.game.getActiveProvinceID()).getPointsX(n)
                                    && CFG.game.getProvince(this.activeProvinceID).getPointsY(ox)
                                       == CFG.game.getProvince(CFG.game.getActiveProvinceID()).getPointsY(n)) {
                                    end = false;
                                    nPointsX.add((short)CFG.game.getProvince(this.activeProvinceID).getPointsX(ox));
                                    nPointsY.add((short)CFG.game.getProvince(this.activeProvinceID).getPointsY(ox));
                                    break;
                                 }
                              }

                              if (end) {
                                 break;
                              }
                           }
                        }
                     } else {
                        o = 0;
                        boolean t1 = false;

                        for(int ox = CFG.game.getProvince(CFG.game.getActiveProvinceID()).getPointsSize() - 1; ox >= 0; --ox) {
                           t1 = false;
                           int n = 0;

                           for(int nSize = CFG.game.getProvince(this.activeProvinceID).getPointsSize(); n < nSize; ++n) {
                              if (CFG.game.getProvince(CFG.game.getActiveProvinceID()).getPointsX(ox)
                                    == CFG.game.getProvince(this.activeProvinceID).getPointsX(n)
                                 && CFG.game.getProvince(CFG.game.getActiveProvinceID()).getPointsY(ox)
                                    == CFG.game.getProvince(this.activeProvinceID).getPointsY(n)) {
                                 t1 = true;
                                 break;
                              }
                           }

                           if (!t1) {
                              ++ox;

                              while(ox < CFG.game.getProvince(CFG.game.getActiveProvinceID()).getPointsSize() - 1) {
                                 nPointsX.add((short)CFG.game.getProvince(CFG.game.getActiveProvinceID()).getPointsX(ox));
                                 nPointsY.add((short)CFG.game.getProvince(CFG.game.getActiveProvinceID()).getPointsY(ox));
                                 ++ox;
                              }
                              break;
                           }
                        }

                        for(int h = 0; h < CFG.game.getProvince(CFG.game.getActiveProvinceID()).getPointsSize(); ++h) {
                           boolean addT = false;

                           for(int n = 0; n < CFG.game.getProvince(this.activeProvinceID).getPointsSize(); ++n) {
                              if (CFG.game.getProvince(CFG.game.getActiveProvinceID()).getPointsX(h)
                                    == CFG.game.getProvince(this.activeProvinceID).getPointsX(n)
                                 && CFG.game.getProvince(CFG.game.getActiveProvinceID()).getPointsY(h)
                                    == CFG.game.getProvince(this.activeProvinceID).getPointsY(n)) {
                                 addT = true;
                                 break;
                              }
                           }

                           if (!addT) {
                              break;
                           }

                           nPointsX.add((short)CFG.game.getProvince(CFG.game.getActiveProvinceID()).getPointsX(h));
                           nPointsY.add((short)CFG.game.getProvince(CFG.game.getActiveProvinceID()).getPointsY(h));
                        }
                     }
                  } else {
                     boolean f1 = false;

                     for(int ox = CFG.game.getProvince(CFG.game.getActiveProvinceID()).getPointsSize() - 1; ox >= 0; --ox) {
                        if (!f1) {
                           int n = 0;

                           for(int nSize = CFG.game.getProvince(this.activeProvinceID).getPointsSize(); n < nSize; ++n) {
                              if (CFG.game.getProvince(CFG.game.getActiveProvinceID()).getPointsX(ox)
                                    == CFG.game.getProvince(this.activeProvinceID).getPointsX(n)
                                 && CFG.game.getProvince(CFG.game.getActiveProvinceID()).getPointsY(ox)
                                    == CFG.game.getProvince(this.activeProvinceID).getPointsY(n)) {
                                 f1 = true;
                                 nPointsX.add((short)CFG.game.getProvince(CFG.game.getActiveProvinceID()).getPointsX(ox));
                                 nPointsY.add((short)CFG.game.getProvince(CFG.game.getActiveProvinceID()).getPointsY(ox));
                                 break;
                              }
                           }
                        } else {
                           boolean end = true;
                           int n = 0;

                           for(int nSize = CFG.game.getProvince(this.activeProvinceID).getPointsSize(); n < nSize; ++n) {
                              if (CFG.game.getProvince(CFG.game.getActiveProvinceID()).getPointsX(ox)
                                    == CFG.game.getProvince(this.activeProvinceID).getPointsX(n)
                                 && CFG.game.getProvince(CFG.game.getActiveProvinceID()).getPointsY(ox)
                                    == CFG.game.getProvince(this.activeProvinceID).getPointsY(n)) {
                                 end = false;
                                 nPointsX.add((short)CFG.game.getProvince(CFG.game.getActiveProvinceID()).getPointsX(ox));
                                 nPointsY.add((short)CFG.game.getProvince(CFG.game.getActiveProvinceID()).getPointsY(ox));
                                 break;
                              }
                           }

                           if (end) {
                              break;
                           }
                        }
                     }
                  }
               }

               if (found) {
                  break;
               }
            }

            for(int a = 0; a < nPointsX.size(); ++a) {
               nPointsX.set(a, (short)(nPointsX.get(a) / CFG.map.getMapBG().getMapScale()));
               nPointsY.set(a, (short)(nPointsY.get(a) / CFG.map.getMapBG().getMapScale()));
            }

            if (this.activeProvinceID > CFG.game.getActiveProvinceID()) {
               CFG.game.getProvince(CFG.game.getActiveProvinceID()).addProvinceBorder(this.activeProvinceID, nPointsX, nPointsY);
            } else {
               CFG.game.getProvince(this.activeProvinceID).addProvinceBorder(CFG.game.getActiveProvinceID(), nPointsX, nPointsY);
            }

            CFG.game.buildGameProvinceData(this.activeProvinceID);
            CFG.game.buildGameProvinceData(CFG.game.getActiveProvinceID());
            CFG.toast.setInView(CFG.langManager.get("Added") + " [" + this.activeProvinceID + " - " + CFG.game.getActiveProvinceID() + "]");
            CFG.menuManager.rebuildMapEditor_Connections_IDs(this.activeProvinceID);
         }

         if (Gdx.input.isKeyPressed(41) && this.activeProvinceID != CFG.game.getActiveProvinceID()) {
            List<Short> nPointsX = new ArrayList<>();
            List<Short> nPointsY = new ArrayList<>();
            if (this.activeProvinceID > CFG.game.getActiveProvinceID()) {
               for(int i = 0;
                  i < CFG.game.getProvince(CFG.game.getActiveProvinceID()).getProvinceBordersLandByLand(this.activeProvinceID).lPointsX.size();
                  ++i
               ) {
                  nPointsX.add(
                     CFG.game
                        .getProvince(CFG.game.getActiveProvinceID())
                        .getProvinceBordersLandByLand(this.activeProvinceID)
                        .lPointsX
                        .get(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getProvinceBordersLandByLand(this.activeProvinceID).lPointsX.size() - 1 - i)
                  );
                  nPointsY.add(
                     CFG.game
                        .getProvince(CFG.game.getActiveProvinceID())
                        .getProvinceBordersLandByLand(this.activeProvinceID)
                        .lPointsY
                        .get(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getProvinceBordersLandByLand(this.activeProvinceID).lPointsY.size() - 1 - i)
                  );
               }

               if (nPointsX.size() == 0) {
                  for(int i = 0;
                     i < CFG.game.getProvince(CFG.game.getActiveProvinceID()).getProvinceBordersLandBySea(this.activeProvinceID).lPointsX.size();
                     ++i
                  ) {
                     nPointsX.add(
                        CFG.game
                           .getProvince(CFG.game.getActiveProvinceID())
                           .getProvinceBordersLandBySea(this.activeProvinceID)
                           .lPointsX
                           .get(
                              CFG.game.getProvince(CFG.game.getActiveProvinceID()).getProvinceBordersLandBySea(this.activeProvinceID).lPointsX.size() - 1 - i
                           )
                     );
                     nPointsY.add(
                        CFG.game
                           .getProvince(CFG.game.getActiveProvinceID())
                           .getProvinceBordersLandBySea(this.activeProvinceID)
                           .lPointsY
                           .get(
                              CFG.game.getProvince(CFG.game.getActiveProvinceID()).getProvinceBordersLandBySea(this.activeProvinceID).lPointsY.size() - 1 - i
                           )
                     );
                  }
               }

               if (nPointsX.size() == 0) {
                  for(int i = 0;
                     i < CFG.game.getProvince(CFG.game.getActiveProvinceID()).getProvinceBordersSeaBySea(this.activeProvinceID).lPointsX.size();
                     ++i
                  ) {
                     nPointsX.add(
                        CFG.game
                           .getProvince(CFG.game.getActiveProvinceID())
                           .getProvinceBordersSeaBySea(this.activeProvinceID)
                           .lPointsX
                           .get(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getProvinceBordersSeaBySea(this.activeProvinceID).lPointsX.size() - 1 - i)
                     );
                     nPointsY.add(
                        CFG.game
                           .getProvince(CFG.game.getActiveProvinceID())
                           .getProvinceBordersSeaBySea(this.activeProvinceID)
                           .lPointsY
                           .get(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getProvinceBordersSeaBySea(this.activeProvinceID).lPointsY.size() - 1 - i)
                     );
                  }
               }
            } else {
               for(int i = 0;
                  i < CFG.game.getProvince(this.activeProvinceID).getProvinceBordersLandByLand(CFG.game.getActiveProvinceID()).lPointsX.size();
                  ++i
               ) {
                  nPointsX.add(
                     CFG.game
                        .getProvince(this.activeProvinceID)
                        .getProvinceBordersLandByLand(CFG.game.getActiveProvinceID())
                        .lPointsX
                        .get(CFG.game.getProvince(this.activeProvinceID).getProvinceBordersLandByLand(CFG.game.getActiveProvinceID()).lPointsX.size() - 1 - i)
                  );
                  nPointsY.add(
                     CFG.game
                        .getProvince(this.activeProvinceID)
                        .getProvinceBordersLandByLand(CFG.game.getActiveProvinceID())
                        .lPointsY
                        .get(CFG.game.getProvince(this.activeProvinceID).getProvinceBordersLandByLand(CFG.game.getActiveProvinceID()).lPointsY.size() - 1 - i)
                  );
               }

               if (nPointsX.size() == 0) {
                  for(int i = 0;
                     i < CFG.game.getProvince(this.activeProvinceID).getProvinceBordersLandBySea(CFG.game.getActiveProvinceID()).lPointsX.size();
                     ++i
                  ) {
                     nPointsX.add(
                        CFG.game
                           .getProvince(this.activeProvinceID)
                           .getProvinceBordersLandBySea(CFG.game.getActiveProvinceID())
                           .lPointsX
                           .get(
                              CFG.game.getProvince(this.activeProvinceID).getProvinceBordersLandBySea(CFG.game.getActiveProvinceID()).lPointsX.size() - 1 - i
                           )
                     );
                     nPointsY.add(
                        CFG.game
                           .getProvince(this.activeProvinceID)
                           .getProvinceBordersLandBySea(CFG.game.getActiveProvinceID())
                           .lPointsY
                           .get(
                              CFG.game.getProvince(this.activeProvinceID).getProvinceBordersLandBySea(CFG.game.getActiveProvinceID()).lPointsY.size() - 1 - i
                           )
                     );
                  }
               }

               if (nPointsX.size() == 0) {
                  for(int i = 0;
                     i < CFG.game.getProvince(this.activeProvinceID).getProvinceBordersSeaBySea(CFG.game.getActiveProvinceID()).lPointsX.size();
                     ++i
                  ) {
                     nPointsX.add(
                        CFG.game
                           .getProvince(this.activeProvinceID)
                           .getProvinceBordersSeaBySea(CFG.game.getActiveProvinceID())
                           .lPointsX
                           .get(CFG.game.getProvince(this.activeProvinceID).getProvinceBordersSeaBySea(CFG.game.getActiveProvinceID()).lPointsX.size() - 1 - i)
                     );
                     nPointsY.add(
                        CFG.game
                           .getProvince(this.activeProvinceID)
                           .getProvinceBordersSeaBySea(CFG.game.getActiveProvinceID())
                           .lPointsY
                           .get(CFG.game.getProvince(this.activeProvinceID).getProvinceBordersSeaBySea(CFG.game.getActiveProvinceID()).lPointsY.size() - 1 - i)
                     );
                  }
               }
            }

            if (this.activeProvinceID > CFG.game.getActiveProvinceID()) {
               CFG.game.getProvince(CFG.game.getActiveProvinceID()).removeProvinceBorder(this.activeProvinceID);
            } else {
               CFG.game.getProvince(this.activeProvinceID).removeProvinceBorder(CFG.game.getActiveProvinceID());
            }

            if (this.activeProvinceID > CFG.game.getActiveProvinceID()) {
               CFG.game.getProvince(CFG.game.getActiveProvinceID()).addProvinceBorder(this.activeProvinceID, nPointsX, nPointsY);
            } else {
               CFG.game.getProvince(this.activeProvinceID).addProvinceBorder(CFG.game.getActiveProvinceID(), nPointsX, nPointsY);
            }

            CFG.game.buildGameProvinceData(this.activeProvinceID);
            CFG.game.buildGameProvinceData(CFG.game.getActiveProvinceID());
         }

         if (Gdx.input.isKeyPressed(67) && this.activeProvinceID != CFG.game.getActiveProvinceID()) {
            CFG.game.getProvince(CFG.game.getActiveProvinceID()).removeNeighboringProvince(this.activeProvinceID);
            CFG.game.getProvince(CFG.game.getActiveProvinceID()).removeNeighboringSeaProvince(this.activeProvinceID);
            CFG.game.getProvince(this.activeProvinceID).removeNeighboringProvince(CFG.game.getActiveProvinceID());
            CFG.game.getProvince(this.activeProvinceID).removeNeighboringSeaProvince(CFG.game.getActiveProvinceID());
            if (!CFG.game.getProvince(CFG.game.getActiveProvinceID()).getSeaProvince()
               && CFG.game.getProvince(CFG.game.getActiveProvinceID()).getNeighboringSeaProvincesSize() == 0) {
               CFG.game.getProvince(CFG.game.getActiveProvinceID()).setLevelOfPort(-1);
            }

            if (!CFG.game.getProvince(this.activeProvinceID).getSeaProvince()
               && CFG.game.getProvince(this.activeProvinceID).getNeighboringSeaProvincesSize() == 0) {
               CFG.game.getProvince(this.activeProvinceID).setLevelOfPort(-1);
            }

            if (this.activeProvinceID > CFG.game.getActiveProvinceID()) {
               CFG.game.getProvince(CFG.game.getActiveProvinceID()).removeProvinceBorder(this.activeProvinceID);
            } else {
               CFG.game.getProvince(this.activeProvinceID).removeProvinceBorder(CFG.game.getActiveProvinceID());
            }

            CFG.game.buildGameProvinceData(this.activeProvinceID);
            CFG.game.buildGameProvinceData(CFG.game.getActiveProvinceID());
            CFG.toast.setInView(CFG.langManager.get("Removed") + " [" + this.activeProvinceID + " - " + CFG.game.getActiveProvinceID() + "]");
            CFG.menuManager.rebuildMapEditor_Connections_IDs(this.activeProvinceID);
         }
      }
   }

   protected static final void updateProvince(int nID) {
      try {
         FileHandle file = Gdx.files.internal("map/" + CFG.map.getFile_ActiveMap_Path() + "update/" + nID);
         String[] tempSplit = file.readString().split(";");
         String[] tempX = tempSplit[0].split(",");
         String[] tempY = tempSplit[1].split(",");
         List<Short> tempPointsX = new ArrayList<>();
         List<Short> tempPointsY = new ArrayList<>();

         for(int j = 0; j < tempX.length; ++j) {
            tempPointsX.add((short)Integer.parseInt(tempX[j]));
            tempPointsY.add((short)Integer.parseInt(tempY[j]));
         }

         CFG.game.getProvince(nID).setPoints(tempPointsX, tempPointsY);
         CFG.game.getProvince(nID).buildProvinceBG(true);
         CFG.game.getProvince(nID).loadProvinceBG();
         CFG.game.buildGameProvinceData(CFG.game.getActiveProvinceID());
      } catch (GdxRuntimeException var8) {
         CFG.toast.setInView("FILE NOT FOUND: [map/" + CFG.map.getFile_ActiveMap_Path() + "update/" + nID + "]");
      }
   }

   @Override
   public String toString() {
      return "ACTIVE PROVINCE ID: "
         + this.activeProvinceID
         + "\n\nSPACE -> SET ACTIVE PROVINCE\nENTER -> ADD CONNECTION\nBACKSPACE -> REMOVE CONNECTION\nM -> REFLECT PROVINCE BORDER\n\nUP -> UPDATE PB VIA FILE\nDOWN -> UPDATE PROVINCE VIA FILE";
   }
}
