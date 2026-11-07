package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

class ServiceRibbon_GameData implements Serializable {
   private static final long serialVersionUID = 0L;
   private List<ServiceRibbon_Overlay_GameData> lLayers = new ArrayList<>();

   protected final ServiceRibbon_Overlay_GameData getServiceRibbon_Overlay(int i) {
      return this.lLayers.get(i);
   }

   protected final void addServiceRibbonOverlay(ServiceRibbon_Overlay_GameData nOverlay) {
      this.lLayers.add(nOverlay);
   }

   protected final void removeServiceRibbon_Overlay(int i) {
      this.lLayers.remove(i);
   }

   protected final int getSize() {
      return this.lLayers.size();
   }
}
