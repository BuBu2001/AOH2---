package age.of.civilizations2.jakowski.lukasz;

class View_Type {
   protected boolean drawCivNamesOver = false;
   protected boolean canMoveArmy = false;
   protected Game_Render.Renderer oRenderer;
   protected Game_Render_Province.DrawProvinces drawProvinces;

   protected void enableViewAction() {
   }

   protected void disableViewAction() {
   }

   protected void updateActiveCivInfo_ExtraAction(int newCivID) {
   }

   protected void updateActiveProvinceID_ExtraAction(int oldProvince, int newProvince) {
   }

   protected void setActiveProvinceAction() {
   }

   protected MenuElement_Hover getProvinceInformations() {
      return null;
   }
}
