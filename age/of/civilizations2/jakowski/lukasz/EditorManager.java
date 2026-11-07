package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;
import java.util.List;

class EditorManager {
   private List<Editor> lEditors = new ArrayList<>();
   protected static int SHIFT_ARMY = -1;
   protected static int SHIFT_PORT = -1;
   protected static int LEVEL_OF_PORT = -1;
   protected static int PROVINCE_PIXMAP = -1;
   protected static int PROVINCE_CONTINENTS = -1;
   protected static int PROVINCE_REGIONS = -1;
   protected static int WASTELAND = -1;
   protected static int TERRAIN_TYPE = -1;
   protected static int NEIGHBORING_PROVINCES = -1;
   protected static int GROWTH_RATE = -1;
   protected static int PROVINCE_MAP_REGIONS = -1;
   private int inUseID = -1;

   protected EditorManager() {
   }

   private final int addEditor(Editor nEditor) {
      this.lEditors.add(nEditor);
      return this.lEditors.size() - 1;
   }

   protected final void draw(SpriteBatch oSB) {
      if (this.inUseID >= 0) {
         this.lEditors.get(this.inUseID).draw(oSB);
      }
   }

   protected final boolean keyDown(int keycode) {
      if (this.inUseID >= 0) {
         this.lEditors.get(this.inUseID).keyDown(keycode);
         return true;
      } else {
         return false;
      }
   }

   protected final boolean keyUp(int keycode) {
      if (this.inUseID >= 0) {
         this.lEditors.get(this.inUseID).keyUp(keycode);
         return true;
      } else {
         return false;
      }
   }

   protected void touchDown(int screenX, int screenY, int pointer, int button) {
      if (this.inUseID >= 0) {
         this.lEditors.get(this.inUseID).touchDown(screenX, screenY, pointer, button);
      }
   }

   protected void touchDragged(int screenX, int screenY, int pointer) {
      if (this.inUseID >= 0) {
         this.lEditors.get(this.inUseID).touchDragged(screenX, screenY, pointer);
      }
   }

   protected void touchUp(int screenX, int screenY, int pointer, int button) {
      if (this.inUseID >= 0) {
         this.lEditors.get(this.inUseID).touchUp(screenX, screenY, pointer, button);
      }
   }

   protected final void resetInUseEditors() {
      for(int i = 0; i < this.lEditors.size(); ++i) {
         this.lEditors.get(i).setInUse(false);
      }

      this.inUseID = -1;
   }

   protected final void setInUse(Editors eEditor) {
      switch(eEditor) {
         case eTERRAINS:
            if (TERRAIN_TYPE < 0) {
               TERRAIN_TYPE = this.addEditor(new Editor_TerrainType());
            }

            this.inUseID = TERRAIN_TYPE;
            break;
         case eSHIFT_ARMY:
            if (SHIFT_ARMY < 0) {
               SHIFT_ARMY = this.addEditor(new Editor_ShiftArmy());
            }

            this.inUseID = SHIFT_ARMY;
            break;
         case eSHIFT_PORT:
            if (SHIFT_PORT < 0) {
               SHIFT_PORT = this.addEditor(new Editor_ShiftPort());
            }

            this.inUseID = SHIFT_PORT;
            break;
         case eLEVEL_OF_PORT:
            if (LEVEL_OF_PORT < 0) {
               LEVEL_OF_PORT = this.addEditor(new Editor_LevelOfPort());
            }

            this.inUseID = LEVEL_OF_PORT;
            break;
         case ePROVINCE_TEXTURE:
            if (PROVINCE_PIXMAP < 0) {
               PROVINCE_PIXMAP = this.addEditor(new Editor_ProvinceTexture());
            }

            this.lEditors.get(PROVINCE_PIXMAP).setInUse(true);
            this.inUseID = PROVINCE_PIXMAP;
            break;
         case ePROVINCE_CONTINENTS:
            if (PROVINCE_CONTINENTS < 0) {
               PROVINCE_CONTINENTS = this.addEditor(new Editor_Continents());
            }

            this.inUseID = PROVINCE_CONTINENTS;
            break;
         case ePROVINCE_REGIONS:
            if (PROVINCE_REGIONS < 0) {
               PROVINCE_REGIONS = this.addEditor(new Editor_Regions());
            }

            this.inUseID = PROVINCE_REGIONS;
            break;
         case ePROVINCE_MAP_REGIONS:
            if (PROVINCE_MAP_REGIONS < 0) {
               PROVINCE_MAP_REGIONS = this.addEditor(new Editor_MapRegions());
            }

            this.inUseID = PROVINCE_MAP_REGIONS;
            break;
         case eGROWTH_RATE:
            if (GROWTH_RATE < 0) {
               GROWTH_RATE = this.addEditor(new Editor_GrowthRate());
            }

            this.inUseID = GROWTH_RATE;
            break;
         case eNEIGHBORING_PROVINCES:
            if (NEIGHBORING_PROVINCES < 0) {
               NEIGHBORING_PROVINCES = this.addEditor(new Editor_NeighboringProvinces());
            }

            this.inUseID = NEIGHBORING_PROVINCES;
      }
   }
}
