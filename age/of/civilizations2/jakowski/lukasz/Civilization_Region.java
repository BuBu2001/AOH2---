package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.CatmullRomSpline;
import com.badlogic.gdx.math.Vector2;
import java.util.ArrayList;
import java.util.List;

class Civilization_Region {
   private int iRegionID;
   private List<Integer> lProvinces;
   private int iProvincesSize;
   private boolean isSupplied = false;
   private boolean seaAccess = false;
   private boolean seaAccess_HavePort = false;
   private boolean haveNotOccupiedProvince = false;
   protected boolean isKeyRegion = false;
   protected int iAveragePotential = 0;
   protected List<Integer> lCostalineProvinces;
   private List<Integer> shortestLine = null;
   private int iMinX = 0;
   private int iMaxX = 0;
   private int iMinY = 0;
   private int iMaxY = 0;
   private float fontScale = 1.0F;
   private float fAngle = 0.0F;
   private int iCharMaxWidth = 0;
   private int iCharMaxHeight = 0;
   private List<Point_XY> lPoints = new ArrayList<>();
   private List<Float> lPointsAngle = new ArrayList<>();
   protected boolean drawName = true;
   private int iAvaragePointPosX = 0;
   private int iAvaragePointPosY = 0;
   private List<Boolean> triedToUse = new ArrayList<>();

   protected Civilization_Region() {
   }

   protected Civilization_Region(int nProvinceID, int iRegionID) {
      this.lProvinces = new ArrayList<>();
      this.shortestLine = new ArrayList<>();
      this.lCostalineProvinces = new ArrayList<>();
      this.iRegionID = iRegionID;
      this.addProvince(nProvinceID);
   }

   protected final boolean checkRegionBordersWithEnemy(int nCivID) {
      for(int i = 0; i < this.getProvincesSize(); ++i) {
         if (CFG.game.getProvince(this.getProvince(i)).getBordersWithEnemy()) {
            return true;
         }
      }

      return false;
   }

   protected final void addProvince(int nProvinceID) {
      this.lProvinces.add(nProvinceID);
      this.iProvincesSize = this.lProvinces.size();
      if (CFG.game.getProvince(nProvinceID).getNeighboringSeaProvincesSize() > 0) {
         this.lCostalineProvinces.add(nProvinceID);
      }

      if (CFG.game.getProvince(nProvinceID).getIsCapital()) {
         this.isKeyRegion = true;
      }

      CFG.game.getProvince(nProvinceID).setCivRegionID(this.iRegionID);
      if (!this.seaAccess) {
         for(int i = 0; i < CFG.game.getProvince(nProvinceID).getNeighboringSeaProvincesSize(); ++i) {
            if (CFG.game.getProvince(CFG.game.getProvince(nProvinceID).getNeighboringSeaProvinces(i)).getLevelOfPort() == -2) {
               this.seaAccess = true;
               break;
            }
         }
      }

      if (this.seaAccess && !this.seaAccess_HavePort && CFG.game.getProvince(nProvinceID).getLevelOfPort() > 0) {
         this.seaAccess_HavePort = true;
      }

      if (!this.haveNotOccupiedProvince && !CFG.game.getProvince(nProvinceID).isOccupied()) {
         this.haveNotOccupiedProvince = true;
      }
   }

   protected final void removeProvinceID(int nProvinceID) {
      for(int i = 0; i < this.iProvincesSize; ++i) {
         if (this.lProvinces.get(i) == nProvinceID) {
            this.lProvinces.remove(i);
            this.iProvincesSize = this.lProvinces.size();

            for(int j = 0; j < this.lCostalineProvinces.size(); ++j) {
               if (this.lCostalineProvinces.get(j) == nProvinceID) {
                  this.lCostalineProvinces.remove(j);
                  break;
               }
            }

            CFG.game.getProvince(nProvinceID).setCivRegionID(-1);
            break;
         }
      }

      if (this.seaAccess) {
         this.seaAccess = false;

         for(int k = 0; k < this.iProvincesSize; ++k) {
            for(int i = 0; i < CFG.game.getProvince(this.getProvince(k)).getNeighboringSeaProvincesSize(); ++i) {
               if (CFG.game.getProvince(CFG.game.getProvince(this.getProvince(k)).getNeighboringSeaProvinces(i)).getLevelOfPort() == -2) {
                  this.seaAccess = true;
                  k = this.iProvincesSize;
                  break;
               }
            }
         }

         if (!this.seaAccess) {
            this.seaAccess_HavePort = false;
         } else if (this.seaAccess_HavePort) {
            this.seaAccess_HavePort = false;

            for(int k = 0; k < this.iProvincesSize; ++k) {
               if (CFG.game.getProvince(this.getProvince(k)).getLevelOfPort() > 0) {
                  this.seaAccess_HavePort = true;
                  break;
               }
            }
         }
      }

      if (this.haveNotOccupiedProvince && !CFG.game.getProvince(nProvinceID).isOccupied()) {
         this.haveNotOccupiedProvince = false;

         for(int k = 0; k < this.iProvincesSize; ++k) {
            if (!CFG.game.getProvince(this.getProvince(k)).isOccupied()) {
               this.haveNotOccupiedProvince = true;
               break;
            }
         }
      }
   }

   protected final void removeProvince(int i) {
      CFG.game.getProvince(this.lProvinces.get(i)).setCivRegionID(-1);

      for(int j = 0; j < this.lCostalineProvinces.size(); ++j) {
         if (this.lCostalineProvinces.get(j) == this.lProvinces.get(i)) {
            this.lCostalineProvinces.remove(j);
            break;
         }
      }

      this.lProvinces.remove(i);
      this.iProvincesSize = this.lProvinces.size();
   }

   protected final boolean containsProvince(int nProvinceID) {
      for(int i = 0; i < this.iProvincesSize; ++i) {
         if (this.lProvinces.get(i) == nProvinceID) {
            return true;
         }
      }

      return false;
   }

   private final boolean canBeUsedInPath(int id) {
      int iNumOfCivProvinces = 0;
      int iNumOfNonCivProvinces = 0;

      for(int i = 0; i < CFG.game.getProvince(this.lProvinces.get(id)).getNeighboringProvincesSize(); ++i) {
         if (CFG.game.getProvince(this.lProvinces.get(id)).getCivID()
            == CFG.game.getProvince(CFG.game.getProvince(this.lProvinces.get(id)).getNeighboringProvinces(i)).getCivID()) {
            ++iNumOfCivProvinces;
         } else {
            ++iNumOfNonCivProvinces;
         }
      }

      if (iNumOfNonCivProvinces > 0) {
         return iNumOfCivProvinces > 1;
      } else {
         return true;
      }
   }

   protected final boolean buildRegionPath() {
      this.drawName = false;
      this.buildMinMaxBounds();
      if (this.lProvinces.size() == 1) {
         return false;
      } else {
         if (this.lProvinces.size() > 2) {
            if (!CFG.settingsManager.DRAW_CIVILIZATIONS_NAMES_OVER_PRPOVINCES_IN_GAME) {
               return false;
            }

            if (this.triedToUse.size() == 0) {
               for(int i = 0; i < this.iProvincesSize; ++i) {
                  this.triedToUse.add(false);
               }
            }

            for(int i = 0; i < this.iProvincesSize; ++i) {
               if (CFG.game.getProvince(this.lProvinces.get(i)).getBelowZero()) {
                  return false;
               }
            }

            int startID = -1;

            for(int i = 0; i < this.iProvincesSize; ++i) {
               if (!this.triedToUse.get(i)) {
                  startID = i;
                  break;
               }
            }

            if (startID == -1) {
               return false;
            }

            int fromProvinceID_LEFTRIGHT = startID;
            int toProvinceID_LEFTRIGHT = startID;
            int fromProvinceID_RIGHTLEFT = startID;
            int toProvinceID_RIGHTLEFT = startID;
            int fromProvinceID_BOTTOM = startID;
            int toProvinceID_TOP = startID;
            int fromProvinceID_LR = startID;
            int toProvinceID_LR = startID;
            int leftBottomDistance = (int)Math.sqrt(
               Math.pow((double)(this.iMinX - CFG.game.getProvince(this.lProvinces.get(startID)).getCenterX()), 2.0)
                  + Math.pow((double)(this.iMaxY - CFG.game.getProvince(this.lProvinces.get(startID)).getCenterY()), 2.0)
            );
            int rightTopDistance = (int)Math.sqrt(
               Math.pow((double)(this.iMaxX - CFG.game.getProvince(this.lProvinces.get(startID)).getCenterX()), 2.0)
                  + Math.pow((double)(this.iMinY - CFG.game.getProvince(this.lProvinces.get(startID)).getCenterY()), 2.0)
            );
            int rightBottomDistance = (int)Math.sqrt(
               Math.pow((double)(this.iMaxX - CFG.game.getProvince(this.lProvinces.get(startID)).getCenterX()), 2.0)
                  + Math.pow((double)(this.iMaxY - CFG.game.getProvince(this.lProvinces.get(startID)).getCenterY()), 2.0)
            );
            int leftTopDistance = (int)Math.sqrt(
               Math.pow((double)(this.iMinX - CFG.game.getProvince(this.lProvinces.get(startID)).getCenterX()), 2.0)
                  + Math.pow((double)(this.iMinY - CFG.game.getProvince(this.lProvinces.get(startID)).getCenterY()), 2.0)
            );

            for(int i = startID + 1; i < this.iProvincesSize; ++i) {
               if (!this.triedToUse.get(i)) {
                  int tempDistance = this.getLineWidth(
                     this.iMinX,
                     this.iMaxY,
                     CFG.game.getProvince(this.lProvinces.get(i)).getCenterX() + CFG.game.getProvince(this.lProvinces.get(i)).getShiftX(),
                     CFG.game.getProvince(this.lProvinces.get(i)).getCenterY() + CFG.game.getProvince(this.lProvinces.get(i)).getShiftY()
                  );
                  if (tempDistance < leftBottomDistance) {
                     leftBottomDistance = tempDistance;
                     fromProvinceID_LEFTRIGHT = i;
                  }

                  tempDistance = this.getLineWidth(
                     this.iMaxX,
                     this.iMinY,
                     CFG.game.getProvince(this.lProvinces.get(i)).getCenterX() + CFG.game.getProvince(this.lProvinces.get(i)).getShiftX(),
                     CFG.game.getProvince(this.lProvinces.get(i)).getCenterY() + CFG.game.getProvince(this.lProvinces.get(i)).getShiftY()
                  );
                  if (tempDistance < rightTopDistance) {
                     rightTopDistance = tempDistance;
                     toProvinceID_LEFTRIGHT = i;
                  }

                  tempDistance = this.getLineWidth(
                     this.iMaxX,
                     this.iMaxY,
                     CFG.game.getProvince(this.lProvinces.get(i)).getCenterX() + CFG.game.getProvince(this.lProvinces.get(i)).getShiftX(),
                     CFG.game.getProvince(this.lProvinces.get(i)).getCenterY() + CFG.game.getProvince(this.lProvinces.get(i)).getShiftY()
                  );
                  if (tempDistance < rightBottomDistance) {
                     rightBottomDistance = tempDistance;
                     fromProvinceID_RIGHTLEFT = i;
                  }

                  tempDistance = this.getLineWidth(
                     this.iMinX,
                     this.iMinY,
                     CFG.game.getProvince(this.lProvinces.get(i)).getCenterX() + CFG.game.getProvince(this.lProvinces.get(i)).getShiftX(),
                     CFG.game.getProvince(this.lProvinces.get(i)).getCenterY() + CFG.game.getProvince(this.lProvinces.get(i)).getShiftY()
                  );
                  if (tempDistance < leftTopDistance) {
                     leftTopDistance = tempDistance;
                     toProvinceID_RIGHTLEFT = i;
                  }

                  if (CFG.game.getProvince(this.lProvinces.get(fromProvinceID_BOTTOM)).getCenterY()
                        + CFG.game.getProvince(this.lProvinces.get(fromProvinceID_BOTTOM)).getShiftY()
                     < CFG.game.getProvince(this.lProvinces.get(i)).getCenterY() + CFG.game.getProvince(this.lProvinces.get(i)).getShiftY()) {
                     fromProvinceID_BOTTOM = i;
                  }

                  if (CFG.game.getProvince(this.lProvinces.get(toProvinceID_TOP)).getCenterY()
                        + CFG.game.getProvince(this.lProvinces.get(toProvinceID_TOP)).getShiftY()
                     > CFG.game.getProvince(this.lProvinces.get(i)).getCenterY() + CFG.game.getProvince(this.lProvinces.get(i)).getShiftY()) {
                     toProvinceID_TOP = i;
                  }

                  if (CFG.game.getProvince(this.lProvinces.get(fromProvinceID_LR)).getCenterX()
                           + CFG.game.getProvince(this.lProvinces.get(fromProvinceID_LR)).getShiftX()
                        > CFG.game.getProvince(this.lProvinces.get(i)).getCenterX() + CFG.game.getProvince(this.lProvinces.get(i)).getShiftX()
                     && CFG.game.getProvince(this.lProvinces.get(i)).getCenterY() + CFG.game.getProvince(this.lProvinces.get(i)).getShiftY()
                        >= this.iMinY + (this.iMaxY - this.iMinY) / 2) {
                     fromProvinceID_LR = i;
                  }

                  if (CFG.game.getProvince(this.lProvinces.get(toProvinceID_LR)).getCenterX()
                           + CFG.game.getProvince(this.lProvinces.get(toProvinceID_LR)).getShiftX()
                        < CFG.game.getProvince(this.lProvinces.get(i)).getCenterX() + CFG.game.getProvince(this.lProvinces.get(i)).getShiftX()
                     && CFG.game.getProvince(this.lProvinces.get(i)).getCenterY() + CFG.game.getProvince(this.lProvinces.get(i)).getShiftY()
                        <= this.iMinY + (this.iMaxY - this.iMinY) / 2) {
                     toProvinceID_LR = i;
                  }
               }
            }

            if (this.getLineWidth(fromProvinceID_LEFTRIGHT, toProvinceID_LEFTRIGHT) > this.getLineWidth(fromProvinceID_RIGHTLEFT, toProvinceID_RIGHTLEFT)) {
               if (this.getLineWidth(fromProvinceID_LEFTRIGHT, toProvinceID_LEFTRIGHT) > this.getLineWidth(fromProvinceID_BOTTOM, toProvinceID_TOP)) {
                  if (this.getLineWidth(fromProvinceID_LEFTRIGHT, toProvinceID_LEFTRIGHT) > this.getLineWidth(fromProvinceID_LR, toProvinceID_LR)) {
                     this.shortestLine.add(fromProvinceID_LEFTRIGHT);
                     this.shortestLine.add(toProvinceID_LEFTRIGHT);
                  } else {
                     this.shortestLine.add(fromProvinceID_LR);
                     this.shortestLine.add(toProvinceID_LR);
                  }
               } else if (this.getLineWidth(fromProvinceID_BOTTOM, toProvinceID_TOP) > this.getLineWidth(fromProvinceID_LR, toProvinceID_LR)) {
                  this.shortestLine.add(fromProvinceID_BOTTOM);
                  this.shortestLine.add(toProvinceID_TOP);
               } else {
                  this.shortestLine.add(fromProvinceID_LR);
                  this.shortestLine.add(toProvinceID_LR);
               }
            } else if (this.getLineWidth(fromProvinceID_RIGHTLEFT, toProvinceID_RIGHTLEFT) > this.getLineWidth(fromProvinceID_BOTTOM, toProvinceID_TOP)) {
               if (this.getLineWidth(fromProvinceID_RIGHTLEFT, toProvinceID_RIGHTLEFT) > this.getLineWidth(fromProvinceID_LR, toProvinceID_LR)) {
                  this.shortestLine.add(fromProvinceID_RIGHTLEFT);
                  this.shortestLine.add(toProvinceID_RIGHTLEFT);
               } else {
                  this.shortestLine.add(fromProvinceID_LR);
                  this.shortestLine.add(toProvinceID_LR);
               }
            } else if (this.getLineWidth(fromProvinceID_BOTTOM, toProvinceID_TOP) > this.getLineWidth(fromProvinceID_LR, toProvinceID_LR)) {
               this.shortestLine.add(fromProvinceID_BOTTOM);
               this.shortestLine.add(toProvinceID_TOP);
            } else {
               this.shortestLine.add(fromProvinceID_LR);
               this.shortestLine.add(toProvinceID_LR);
            }

            if (CFG.game.getProvince(this.lProvinces.get(this.shortestLine.get(0))).getCenterX()
               > CFG.game.getProvince(this.lProvinces.get(this.shortestLine.get(1))).getCenterX()) {
               int tempS = this.shortestLine.get(0);
               this.shortestLine.set(0, this.shortestLine.get(1));
               this.shortestLine.set(1, tempS);
            }

            if (this.shortestLine.size() == 0 || this.shortestLine.get(0) == this.shortestLine.get(1)) {
               this.shortestLine.clear();
               this.triedToUse.clear();
               return false;
            }

            Point_XY tD = this.canDrawTextProperly(this.lProvinces.get(this.shortestLine.get(0)), this.lProvinces.get(this.shortestLine.get(1)));
            if (tD != null) {
               if (this.getLineWidth(
                     tD.getPosX(),
                     tD.getPosY(),
                     CFG.game.getProvince(this.lProvinces.get(this.shortestLine.get(0))).getCenterX()
                        + CFG.game.getProvince(this.lProvinces.get(this.shortestLine.get(0))).getShiftX(),
                     CFG.game.getProvince(this.lProvinces.get(this.shortestLine.get(0))).getCenterY()
                        + CFG.game.getProvince(this.lProvinces.get(this.shortestLine.get(0))).getShiftY()
                  )
                  < this.getLineWidth(
                     tD.getPosX(),
                     tD.getPosY(),
                     CFG.game.getProvince(this.lProvinces.get(this.shortestLine.get(1))).getCenterX()
                        + CFG.game.getProvince(this.lProvinces.get(this.shortestLine.get(1))).getShiftX(),
                     CFG.game.getProvince(this.lProvinces.get(this.shortestLine.get(1))).getCenterY()
                        + CFG.game.getProvince(this.lProvinces.get(this.shortestLine.get(1))).getShiftY()
                  )) {
                  this.triedToUse.set(this.shortestLine.get(0), true);
               } else {
                  this.triedToUse.set(this.shortestLine.get(1), true);
               }

               this.shortestLine.clear();
               return this.buildRegionPath();
            }

            tD = null;
            this.triedToUse.clear();
            this.buildScaleOfText();
         }

         this.updateDrawRegionName();
         return true;
      }
   }

   protected final void updateDrawRegionName() {
      this.drawName = true;
      if (CFG.FOG_OF_WAR == 2) {
         for(int i = 0; i < this.lProvinces.size(); ++i) {
            if (!CFG.game.getPlayer(CFG.PLAYER_TURNID).getMetProvince(this.lProvinces.get(i))) {
               this.drawName = false;
               break;
            }
         }
      }
   }

   private final void buildMinMaxBounds() {
      try {
         this.iMinX = CFG.game.getProvince(this.lProvinces.get(0)).getMinX();
         this.iMaxX = CFG.game.getProvince(this.lProvinces.get(0)).getMaxX();
         this.iMinY = CFG.game.getProvince(this.lProvinces.get(0)).getMinY();
         this.iMaxY = CFG.game.getProvince(this.lProvinces.get(0)).getMaxY();

         for(int i = 1; i < this.iProvincesSize; ++i) {
            if (CFG.game.getProvince(this.lProvinces.get(i)).getMinX() < this.iMinX) {
               this.iMinX = CFG.game.getProvince(this.lProvinces.get(i)).getMinX();
            }

            if (CFG.game.getProvince(this.lProvinces.get(i)).getMaxX() > this.iMaxX) {
               this.iMaxX = CFG.game.getProvince(this.lProvinces.get(i)).getMaxX();
            }

            if (CFG.game.getProvince(this.lProvinces.get(i)).getMinY() < this.iMinY) {
               this.iMinY = CFG.game.getProvince(this.lProvinces.get(i)).getMinY();
            }

            if (CFG.game.getProvince(this.lProvinces.get(i)).getMaxY() > this.iMaxY) {
               this.iMaxY = CFG.game.getProvince(this.lProvinces.get(i)).getMaxY();
            }
         }
      } catch (IndexOutOfBoundsException var2) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var2);
         }
      }
   }

   protected final void buildScaleOfText() {
      try {
         if (this.shortestLine.size() > 1) {
            int iDistance = (int)Math.sqrt(
               Math.pow(
                     (double)(
                        CFG.game.getProvince(this.lProvinces.get(this.shortestLine.get(0))).getCenterX()
                           + CFG.game.getProvince(this.lProvinces.get(this.shortestLine.get(0))).getShiftX()
                           - CFG.game.getProvince(this.lProvinces.get(this.shortestLine.get(1))).getCenterX()
                           - CFG.game.getProvince(this.lProvinces.get(this.shortestLine.get(1))).getShiftX()
                     ),
                     2.0
                  )
                  + Math.pow(
                     (double)(
                        CFG.game.getProvince(this.lProvinces.get(this.shortestLine.get(0))).getCenterY()
                           + CFG.game.getProvince(this.lProvinces.get(this.shortestLine.get(0))).getShiftY()
                           - CFG.game.getProvince(this.lProvinces.get(this.shortestLine.get(1))).getCenterY()
                           - CFG.game.getProvince(this.lProvinces.get(this.shortestLine.get(1))).getShiftY()
                     ),
                     2.0
                  )
            );
            CFG.glyphLayout
               .setText(CFG.fontBorder, CFG.game.getCiv(CFG.game.getProvince(this.lProvinces.get(this.shortestLine.get(0))).getCivID()).getCivName());
            int tempNumOfInterations = 0;

            try {
               do {
                  if ((float)iDistance > CFG.glyphLayout.width) {
                     CFG.fontBorder.getData().setScale(CFG.fontBorder.getData().scaleX + 0.1F);
                     CFG.glyphLayout
                        .setText(CFG.fontBorder, CFG.game.getCiv(CFG.game.getProvince(this.lProvinces.get(this.shortestLine.get(0))).getCivID()).getCivName());
                     if ((float)iDistance < CFG.glyphLayout.width) {
                        this.fontScale = CFG.fontBorder.getData().scaleX - 0.1F;
                        break;
                     }
                  } else {
                     CFG.fontBorder.getData().setScale(CFG.fontBorder.getData().scaleX - 0.1F);
                     CFG.glyphLayout
                        .setText(CFG.fontBorder, CFG.game.getCiv(CFG.game.getProvince(this.lProvinces.get(this.shortestLine.get(0))).getCivID()).getCivName());
                     if ((float)iDistance > CFG.glyphLayout.width) {
                        this.fontScale = CFG.fontBorder.getData().scaleX + 0.1F;
                        break;
                     }
                  }
               } while(tempNumOfInterations++ != 1000);
            } catch (IndexOutOfBoundsException var9) {
               this.fontScale = 0.1F;
            } catch (NullPointerException var10) {
               this.fontScale = 0.1F;

               try {
                  CFG.game.getCiv(CFG.game.getProvince(this.lProvinces.get(0)).getCivID()).setUpdateRegions(true);
               } catch (IndexOutOfBoundsException var7) {
               } catch (NullPointerException var8) {
               }
            } catch (IllegalStateException var11) {
               this.fontScale = 0.1F;
            }

            if (this.fontScale > 20.0F) {
               this.fontScale *= 0.2F;
            } else if (this.fontScale > 15.0F) {
               this.fontScale *= 0.225F;
            } else if (this.fontScale > 10.0F) {
               this.fontScale *= 0.25F;
            } else if ((double)this.fontScale > 7.5) {
               this.fontScale *= 0.3F;
            } else if (this.fontScale > 5.0F) {
               this.fontScale *= 0.325F;
            } else if ((double)this.fontScale > 3.5) {
               this.fontScale *= 0.35F;
            } else if ((double)this.fontScale > 2.5) {
               this.fontScale *= 0.375F;
            } else if (this.fontScale > 2.0F) {
               this.fontScale *= 0.4F;
            } else if ((double)this.fontScale > 1.75) {
               this.fontScale *= 0.45F;
            } else if ((double)this.fontScale > 1.5) {
               this.fontScale *= 0.475F;
            } else {
               this.fontScale *= 0.5F;
            }

            CFG.fontBorder.getData().setScale(1.0F);
            this.buildAvaragePoint();
            this.buildDrawData();
         }
      } catch (NullPointerException var12) {
         this.fontScale = 0.1F;

         try {
            CFG.game.getCiv(CFG.game.getProvince(this.lProvinces.get(0)).getCivID()).setUpdateRegions(true);
         } catch (IndexOutOfBoundsException var5) {
         } catch (NullPointerException var6) {
         }
      }
   }

   private final Point_XY canDrawTextProperly(int fromProvinceID, int toProvinceID) {
      this.buildAvaragePoint();
      List<Point_XY> tempPoints = new ArrayList<>();
      int tX = CFG.game.getProvince(fromProvinceID).getCenterX() + CFG.game.getProvince(fromProvinceID).getShiftX();
      int tX2 = CFG.game.getProvince(toProvinceID).getCenterX() + CFG.game.getProvince(toProvinceID).getShiftX();
      int extra10X = tX + (int)Math.abs((float)(tX2 - tX) * 0.15F) * (tX > tX2 ? -1 : 1);
      int extra10X2 = tX2 + (int)Math.abs((float)(tX2 - tX) * 0.15F) * (tX2 > tX ? -1 : 1);
      int tY = CFG.game.getProvince(fromProvinceID).getCenterY() + CFG.game.getProvince(fromProvinceID).getShiftY();
      int tY2 = CFG.game.getProvince(toProvinceID).getCenterY() + CFG.game.getProvince(toProvinceID).getShiftY();
      int extra10Y = tY + (int)Math.abs((float)(tY2 - tY) * 0.15F) * (tY > tY2 ? -1 : 1);
      int extra10Y2 = tY2 + (int)Math.abs((float)(tY2 - tY) * 0.15F) * (tY2 > tY ? -1 : 1);
      int iPrecission = CFG.game.getCiv(CFG.game.getProvince(fromProvinceID).getCivID()).getCivNameLength() * 10;
      Vector2[] vPoints = new Vector2[iPrecission];
      Vector2[] dataSet = new Vector2[]{
         new Vector2((float)extra10X, (float)extra10Y),
         new Vector2((float)extra10X, (float)extra10Y),
         new Vector2((float)this.iAvaragePointPosX, (float)this.iAvaragePointPosY),
         new Vector2((float)extra10X2, (float)extra10Y2),
         new Vector2((float)extra10X2, (float)extra10Y2)
      };
      CatmullRomSpline<Vector2> oCatmull = new CatmullRomSpline<>(dataSet, false);

      for(int i = 0; i < iPrecission; ++i) {
         vPoints[i] = new Vector2();
         oCatmull.valueAt(vPoints[i], (float)i / ((float)iPrecission - 1.0F));
      }

      int tempPrecissionWidth = 0;

      for(int i = 0; i < iPrecission - 1; ++i) {
         tempPrecissionWidth += this.getLineWidth((int)vPoints[i].x, (int)vPoints[i].y, (int)vPoints[i + 1].x, (int)vPoints[i + 1].y);
      }

      tempPoints.add(new Point_XY((int)vPoints[0].x, (int)vPoints[0].y));
      int aceptableWidth = 0;

      try {
         aceptableWidth = tempPrecissionWidth / (CFG.game.getCiv(CFG.game.getProvince(fromProvinceID).getCivID()).getCivNameLength() - 1);
      } catch (ArithmeticException var22) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var22);
         }
      }

      int i = 1;

      for(int startPrecision = 0; i < CFG.game.getCiv(CFG.game.getProvince(fromProvinceID).getCivID()).getCivNameLength(); ++i) {
         for(int currentPointsWidth = 0; startPrecision < iPrecission - 1; ++startPrecision) {
            int tempPrecisionWidth = this.getLineWidth(
               (int)vPoints[startPrecision].x, (int)vPoints[startPrecision].y, (int)vPoints[startPrecision + 1].x, (int)vPoints[startPrecision + 1].y
            );
            if (currentPointsWidth + tempPrecisionWidth >= aceptableWidth && currentPointsWidth <= aceptableWidth) {
               tempPoints.add(new Point_XY((int)vPoints[startPrecision].x, (int)vPoints[startPrecision].y));
               currentPointsWidth += tempPrecisionWidth;
               break;
            }

            currentPointsWidth += tempPrecisionWidth;
         }
      }

      for(int ix = tempPoints.size() - 1; ix >= 0; --ix) {
         int nNewChosenProvinceID = CFG.game.setProvinceID_Point(tempPoints.get(ix).getPosX(), tempPoints.get(ix).getPosY() - CFG.TEXT_HEIGHT / 2);
         if (nNewChosenProvinceID >= 0
            && !CFG.game.getProvince(nNewChosenProvinceID).getSeaProvince()
            && CFG.game.getProvince(fromProvinceID).getCivID() != CFG.game.getProvince(nNewChosenProvinceID).getCivID()) {
            return tempPoints.get(ix);
         }
      }

      return null;
   }

   private final void buildDrawData() {
      CFG.fontBorder.getData().setScale(this.fontScale);
      this.iCharMaxWidth = 1;
      this.iCharMaxHeight = 1;

      try {
         for(int i = 0; i < CFG.game.getCiv(CFG.game.getProvince(this.shortestLine.get(0)).getCivID()).getCivNameLength(); ++i) {
            CFG.glyphLayout.setText(CFG.fontBorder, "" + CFG.game.getCiv(CFG.game.getProvince(this.shortestLine.get(0)).getCivID()).getCivNameCharacter(i));
            if (CFG.glyphLayout.width > (float)this.iCharMaxWidth) {
               this.iCharMaxWidth = (int)CFG.glyphLayout.width;
            }

            if (CFG.glyphLayout.height > (float)this.iCharMaxWidth) {
               this.iCharMaxHeight = (int)CFG.glyphLayout.height;
            }
         }
      } catch (IndexOutOfBoundsException var37) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var37);
         }
      } catch (NullPointerException var38) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var38);
         }

         try {
            CFG.game.getCiv(CFG.game.getProvince(this.lProvinces.get(0)).getCivID()).setUpdateRegions(true);
         } catch (IndexOutOfBoundsException var29) {
         } catch (NullPointerException var30) {
         }
      } catch (IllegalStateException var39) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var39);
         }
      }

      this.fAngle = (float)(
         Math.atan2(
               (double)(
                  CFG.game.getProvince(this.getProvince(this.shortestLine.get(0))).getCenterY()
                     + CFG.game.getProvince(this.getProvince(this.shortestLine.get(0))).getShiftY()
                     - (
                        CFG.game.getProvince(this.getProvince(this.shortestLine.get(1))).getCenterY()
                           + CFG.game.getProvince(this.getProvince(this.shortestLine.get(1))).getShiftY()
                     )
               ),
               (double)(
                  -(
                        CFG.game.getProvince(this.getProvince(this.shortestLine.get(0))).getCenterX()
                           + CFG.game.getProvince(this.getProvince(this.shortestLine.get(0))).getShiftX()
                     )
                     + CFG.game.getProvince(this.getProvince(this.shortestLine.get(1))).getCenterX()
                     + CFG.game.getProvince(this.getProvince(this.shortestLine.get(1))).getShiftX()
               )
            )
            * 180.0
            / Math.PI
      );
      this.lPoints.clear();
      this.lPointsAngle.clear();
      int fromProvinceID = this.lProvinces.get(this.shortestLine.get(0));
      int toProvinceID = this.lProvinces.get(this.shortestLine.get(1));
      int tX = CFG.game.getProvince(fromProvinceID).getCenterX() + CFG.game.getProvince(fromProvinceID).getShiftX();
      int tX2 = CFG.game.getProvince(toProvinceID).getCenterX() + CFG.game.getProvince(toProvinceID).getShiftX();
      int extra10X = tX + (int)Math.abs((float)(tX2 - tX) * 0.15F) * (tX > tX2 ? -1 : 1);
      int extra10X2 = tX2 + (int)Math.abs((float)(tX2 - tX) * 0.15F) * (tX2 > tX ? -1 : 1);
      int tY = CFG.game.getProvince(fromProvinceID).getCenterY() + CFG.game.getProvince(fromProvinceID).getShiftY();
      int tY2 = CFG.game.getProvince(toProvinceID).getCenterY() + CFG.game.getProvince(toProvinceID).getShiftY();
      int extra10Y = tY + (int)Math.abs((float)(tY2 - tY) * 0.15F) * (tY > tY2 ? -1 : 1);
      int extra10Y2 = tY2 + (int)Math.abs((float)(tY2 - tY) * 0.15F) * (tY2 > tY ? -1 : 1);
      int iPrecission = CFG.game.getCiv(CFG.game.getProvince(fromProvinceID).getCivID()).getCivNameLength() * 10;
      Vector2[] vPoints = new Vector2[iPrecission];
      Vector2[] dataSet = new Vector2[]{
         new Vector2((float)extra10X, (float)extra10Y),
         new Vector2((float)extra10X, (float)extra10Y),
         new Vector2((float)this.iAvaragePointPosX, (float)this.iAvaragePointPosY),
         new Vector2((float)extra10X2, (float)extra10Y2),
         new Vector2((float)extra10X2, (float)extra10Y2)
      };
      CatmullRomSpline<Vector2> oCatmull = new CatmullRomSpline<>(dataSet, false);

      for(int i = 0; i < iPrecission; ++i) {
         vPoints[i] = new Vector2();
         oCatmull.valueAt(vPoints[i], (float)i / ((float)iPrecission - 1.0F));
      }

      int tempPrecissionWidth = 0;

      for(int i = 0; i < iPrecission - 1; ++i) {
         tempPrecissionWidth += this.getLineWidth((int)vPoints[i].x, (int)vPoints[i].y, (int)vPoints[i + 1].x, (int)vPoints[i + 1].y);
      }

      this.lPoints.add(new Point_XY((int)vPoints[0].x, (int)vPoints[0].y));
      int aceptableWidth = 0;

      try {
         aceptableWidth = tempPrecissionWidth / (CFG.game.getCiv(CFG.game.getProvince(fromProvinceID).getCivID()).getCivNameLength() - 1);
      } catch (ArithmeticException var36) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var36);
         }
      }

      int i = 1;

      for(int startPrecision = 0; i < CFG.game.getCiv(CFG.game.getProvince(fromProvinceID).getCivID()).getCivNameLength(); ++i) {
         for(int currentPointsWidth = 0; startPrecision < iPrecission - 1; ++startPrecision) {
            int tempPrecisionWidth = this.getLineWidth(
               (int)vPoints[startPrecision].x, (int)vPoints[startPrecision].y, (int)vPoints[startPrecision + 1].x, (int)vPoints[startPrecision + 1].y
            );
            if (currentPointsWidth + tempPrecisionWidth >= aceptableWidth && currentPointsWidth <= aceptableWidth) {
               this.lPoints.add(new Point_XY((int)vPoints[startPrecision].x, (int)vPoints[startPrecision].y));
               currentPointsWidth += tempPrecisionWidth;
               break;
            }

            currentPointsWidth += tempPrecisionWidth;
         }
      }

      try {
         for(int ix = 0; ix < CFG.game.getCiv(CFG.game.getProvince(fromProvinceID).getCivID()).getCivNameLength(); ++ix) {
            CFG.glyphLayout.setText(CFG.fontBorder, "" + CFG.game.getCiv(CFG.game.getProvince(fromProvinceID).getCivID()).getCivNameCharacter(ix));
            this.lPoints.get(ix).setPosX(this.lPoints.get(ix).getPosX() - (int)(CFG.glyphLayout.width / 2.0F));
            float tempPointsAngle = 0.0F;

            try {
               if (ix < CFG.game.getCiv(CFG.game.getProvince(fromProvinceID).getCivID()).getCivNameLength() - 1) {
                  tempPointsAngle = this.getLinesAngle(
                     this.lPoints.get(ix).getPosX(), this.lPoints.get(ix).getPosY(), this.lPoints.get(ix + 1).getPosX(), this.lPoints.get(ix + 1).getPosY()
                  );
                  tempPointsAngle = (
                        tempPointsAngle
                           + this.getLinesAngle(
                              this.lPoints.get(ix - 1).getPosX(),
                              this.lPoints.get(ix - 1).getPosY(),
                              this.lPoints.get(ix).getPosX(),
                              this.lPoints.get(ix).getPosY()
                           )
                     )
                     / 2.0F;
               } else {
                  tempPointsAngle = this.getLinesAngle(
                     this.lPoints.get(ix - 1).getPosX(), this.lPoints.get(ix - 1).getPosY(), this.lPoints.get(ix).getPosX(), this.lPoints.get(ix).getPosY()
                  );
               }

               this.lPointsAngle.add(tempPointsAngle);
            } catch (IndexOutOfBoundsException var31) {
               if (CFG.LOGS) {
                  CFG.exceptionStack(var31);
               }

               if (ix == 0) {
                  try {
                     this.lPointsAngle
                        .add(
                           this.getLinesAngle(
                              this.lPoints.get(ix).getPosX(),
                              this.lPoints.get(ix).getPosY(),
                              this.lPoints.get(ix + 1).getPosX(),
                              this.lPoints.get(ix + 1).getPosY()
                           )
                        );
                  } catch (IndexOutOfBoundsException var28) {
                     this.lPointsAngle.add(this.fAngle);
                  }
               } else {
                  try {
                     this.lPointsAngle
                        .add(
                           this.getLinesAngle(
                              this.lPoints.get(ix - 1).getPosX(),
                              this.lPoints.get(ix - 1).getPosY(),
                              this.lPoints.get(ix).getPosX(),
                              this.lPoints.get(ix).getPosY()
                           )
                        );
                  } catch (IndexOutOfBoundsException var27) {
                     this.lPointsAngle.add(this.fAngle);
                  }
               }
            } catch (NullPointerException var32) {
               if (CFG.LOGS) {
                  CFG.exceptionStack(var32);
               }

               if (ix == 0) {
                  try {
                     this.lPointsAngle
                        .add(
                           this.getLinesAngle(
                              this.lPoints.get(ix).getPosX(),
                              this.lPoints.get(ix).getPosY(),
                              this.lPoints.get(ix + 1).getPosX(),
                              this.lPoints.get(ix + 1).getPosY()
                           )
                        );
                  } catch (IndexOutOfBoundsException var26) {
                     this.lPointsAngle.add(this.fAngle);
                  }
               } else {
                  try {
                     this.lPointsAngle
                        .add(
                           this.getLinesAngle(
                              this.lPoints.get(ix - 1).getPosX(),
                              this.lPoints.get(ix - 1).getPosY(),
                              this.lPoints.get(ix).getPosX(),
                              this.lPoints.get(ix).getPosY()
                           )
                        );
                  } catch (IndexOutOfBoundsException var25) {
                     this.lPointsAngle.add(this.fAngle);
                  }
               }

               try {
                  CFG.game.getCiv(CFG.game.getProvince(this.lProvinces.get(0)).getCivID()).setUpdateRegions(true);
               } catch (IndexOutOfBoundsException var23) {
               } catch (NullPointerException var24) {
               }
            }
         }
      } catch (IndexOutOfBoundsException var33) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var33);
         }
      } catch (NullPointerException var34) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var34);
         }

         try {
            CFG.game.getCiv(CFG.game.getProvince(this.lProvinces.get(0)).getCivID()).setUpdateRegions(true);
         } catch (IndexOutOfBoundsException var21) {
         } catch (NullPointerException var22) {
         }
      } catch (IllegalStateException var35) {
         if (CFG.LOGS) {
            CFG.exceptionStack(var35);
         }
      }
   }

   private final void buildAvaragePoint() {
      long lAvarageX = 0L;
      long lAvarageY = 0L;
      int tempMinX = CFG.game.getProvince(this.lProvinces.get(this.shortestLine.get(0))).getMinX();
      int tempMaxX = CFG.game.getProvince(this.lProvinces.get(this.shortestLine.get(0))).getMaxX();
      int tempMinY = CFG.game.getProvince(this.lProvinces.get(this.shortestLine.get(0))).getMinY();
      int tempMaxY = CFG.game.getProvince(this.lProvinces.get(this.shortestLine.get(0))).getMaxY();
      if (CFG.game.getProvince(this.lProvinces.get(this.shortestLine.get(1))).getMinX() < tempMinX) {
         tempMinX = CFG.game.getProvince(this.lProvinces.get(this.shortestLine.get(1))).getMinX();
      }

      if (CFG.game.getProvince(this.lProvinces.get(this.shortestLine.get(1))).getMaxX() > tempMaxX) {
         tempMaxX = CFG.game.getProvince(this.lProvinces.get(this.shortestLine.get(1))).getMaxX();
      }

      if (CFG.game.getProvince(this.lProvinces.get(this.shortestLine.get(1))).getMinY() < tempMinY) {
         tempMinY = CFG.game.getProvince(this.lProvinces.get(this.shortestLine.get(1))).getMinY();
      }

      if (CFG.game.getProvince(this.lProvinces.get(this.shortestLine.get(1))).getMaxY() > tempMaxY) {
         tempMaxY = CFG.game.getProvince(this.lProvinces.get(this.shortestLine.get(1))).getMaxY();
      }

      int tSize = 0;

      for(int i = 0; i < this.getProvincesSize(); ++i) {
         if (CFG.game.getProvince(this.getProvince(i)).getCenterX() + CFG.game.getProvince(this.getProvince(i)).getShiftX() >= tempMinX
            && CFG.game.getProvince(this.getProvince(i)).getCenterX() + CFG.game.getProvince(this.getProvince(i)).getShiftX() <= tempMaxX) {
            if (CFG.game.getProvince(this.getProvince(i)).getCenterY() + CFG.game.getProvince(this.getProvince(i)).getShiftY() >= tempMinY
               && CFG.game.getProvince(this.getProvince(i)).getCenterY() + CFG.game.getProvince(this.getProvince(i)).getShiftY() <= tempMaxY) {
               lAvarageX += (long)(CFG.game.getProvince(this.getProvince(i)).getCenterX() + CFG.game.getProvince(this.getProvince(i)).getShiftX());
               lAvarageY += (long)(CFG.game.getProvince(this.getProvince(i)).getCenterY() + CFG.game.getProvince(this.getProvince(i)).getShiftY());
               ++tSize;
            }
         } else if ((
               CFG.game.getProvince(this.getProvince(i)).getMinX() > tempMinX && CFG.game.getProvince(this.getProvince(i)).getMinX() <= tempMaxX
                  || CFG.game.getProvince(this.getProvince(i)).getMaxX() > tempMinX && CFG.game.getProvince(this.getProvince(i)).getMaxX() <= tempMaxX
            )
            && (
               CFG.game.getProvince(this.getProvince(i)).getMinY() >= tempMinY && CFG.game.getProvince(this.getProvince(i)).getMinY() <= tempMaxY
                  || CFG.game.getProvince(this.getProvince(i)).getMaxY() >= tempMinY && CFG.game.getProvince(this.getProvince(i)).getMaxY() <= tempMaxY
            )) {
            lAvarageX += (long)(CFG.game.getProvince(this.getProvince(i)).getCenterX() + CFG.game.getProvince(this.getProvince(i)).getShiftX());
            lAvarageY += (long)(CFG.game.getProvince(this.getProvince(i)).getCenterY() + CFG.game.getProvince(this.getProvince(i)).getShiftY());
            ++tSize;
         }
      }

      if (tSize == 0) {
         tSize = 1;
      }

      this.iAvaragePointPosX = (int)(lAvarageX / (long)tSize);
      this.iAvaragePointPosY = (int)(lAvarageY / (long)tSize);
   }

   protected final void drawCivilizationName(
      SpriteBatch oSB, int fromProvinceID, int toProvinceID, float fontScale, float nAngle, int nCharMaxWidth, int nCharMaxHeight
   ) {
      CFG.fontBorder.getData().setScale(fontScale);

      for(int i = 0; i < CFG.game.getCiv(CFG.game.getProvince(fromProvinceID).getCivID()).getCivNameLength(); ++i) {
         CFG.drawTextRotatedBorder(
            oSB,
            "" + CFG.game.getCiv(CFG.game.getProvince(fromProvinceID).getCivID()).getCivNameCharacter(i),
            CFG.map.getMapCoordinates().getPosX() + this.lPoints.get(i).getPosX(),
            CFG.map.getMapCoordinates().getPosY() + this.lPoints.get(i).getPosY() - nCharMaxHeight / 2,
            new Color(1.0F, 1.0F, 1.0F, Game_Render.CIVILIZATION_NAMES_ALPHA),
            this.lPointsAngle.get(i)
         );
      }
   }

   protected final void drawCivilizationName_SecondSideOfMap(
      SpriteBatch oSB, int fromProvinceID, int toProvinceID, float fontScale, float nAngle, int nCharMaxWidth, int nCharMaxHeight
   ) {
      if (CFG.game.getProvince(fromProvinceID).getTranslateProvincePosX() > 0) {
         CFG.fontBorder.getData().setScale(fontScale);

         for(int i = 0; i < CFG.game.getCiv(CFG.game.getProvince(fromProvinceID).getCivID()).getCivNameLength(); ++i) {
            CFG.drawTextRotatedBorder(
               oSB,
               "" + CFG.game.getCiv(CFG.game.getProvince(fromProvinceID).getCivID()).getCivNameCharacter(i),
               CFG.map.getMapCoordinates().getSecondSideOfMap_MoveX() + CFG.map.getMapCoordinates().getPosX() + this.lPoints.get(i).getPosX(),
               CFG.map.getMapCoordinates().getPosY() + this.lPoints.get(i).getPosY() - nCharMaxHeight / 2,
               new Color(1.0F, 1.0F, 1.0F, Game_Render.CIVILIZATION_NAMES_ALPHA),
               this.lPointsAngle.get(i)
            );
         }
      }
   }

   protected float getLinesAngle(int fromPosX, int fromPosY, int toPosX, int toPosY) {
      return (float)(Math.atan2((double)(fromPosY - toPosY), (double)(-fromPosX + toPosX)) * 180.0 / Math.PI);
   }

   protected int getLineWidth(int fromCenterPosProvinceID, int toCenterPosProvinceID) {
      return this.getLineWidth(
         CFG.game.getProvince(this.lProvinces.get(fromCenterPosProvinceID)).getCenterX()
            + CFG.game.getProvince(this.lProvinces.get(fromCenterPosProvinceID)).getShiftX(),
         CFG.game.getProvince(this.lProvinces.get(fromCenterPosProvinceID)).getCenterY()
            + CFG.game.getProvince(this.lProvinces.get(fromCenterPosProvinceID)).getShiftY(),
         CFG.game.getProvince(this.lProvinces.get(toCenterPosProvinceID)).getCenterX()
            + CFG.game.getProvince(this.lProvinces.get(toCenterPosProvinceID)).getShiftX(),
         CFG.game.getProvince(this.lProvinces.get(toCenterPosProvinceID)).getCenterY()
            + CFG.game.getProvince(this.lProvinces.get(toCenterPosProvinceID)).getShiftY()
      );
   }

   protected int getLineWidth(int fromPosX, int fromPosY, int toPosX, int toPosY) {
      return (int)Math.sqrt(Math.pow((double)(fromPosX - toPosX), 2.0) + Math.pow((double)(fromPosY - toPosY), 2.0));
   }

   protected final int getProvince(int i) {
      return this.lProvinces.get(i);
   }

   protected final int getProvincesSize() {
      return this.iProvincesSize;
   }

   protected final boolean getSeaAccess() {
      return this.seaAccess;
   }

   protected final boolean getSeaAccess_HavePort() {
      return this.seaAccess_HavePort;
   }

   protected final boolean getSeaAccess_HavePort_Check() {
      for(int i = 0; i < this.getProvincesSize(); ++i) {
         if (CFG.game.getProvince(this.getProvince(i)).getLevelOfPort() > 0) {
            return true;
         }
      }

      return false;
   }

   protected final void setSeaAccess_HavePort(boolean seaAccess_HavePort) {
      this.seaAccess_HavePort = seaAccess_HavePort;
   }

   protected final boolean getHaveNotOccupiedProvince() {
      return this.haveNotOccupiedProvince;
   }

   protected final List<Integer> getShortestPath() {
      return this.shortestLine;
   }

   protected final float getFontScale() {
      return this.fontScale;
   }

   protected final int getRegionID() {
      return this.iRegionID;
   }

   protected final void setRegionID(int iRegionID) {
      this.iRegionID = iRegionID;

      for(int i = 0; i < this.iProvincesSize; ++i) {
         CFG.game.getProvince(i).setCivRegionID(iRegionID);
      }
   }

   protected final float getAngle() {
      return this.fAngle;
   }

   protected final int getCharMaxWidth() {
      return this.iCharMaxWidth;
   }

   protected final int getCharMaxHeight() {
      return this.iCharMaxHeight;
   }

   protected final boolean getIsSupplied() {
      return this.isSupplied;
   }

   protected final boolean setIsSupplied(boolean isSupplied) {
      this.isSupplied = isSupplied;
      return this.getIsSupplied();
   }
}
