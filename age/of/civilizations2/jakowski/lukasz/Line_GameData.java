package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;

class Line_GameData implements Serializable {
   private static final long serialVersionUID = 0L;
   private String sImageName;
   private boolean reapeatImage = true;
   private boolean flipX = false;
   private boolean movable = true;

   protected Line_GameData() {
   }

   protected final String getImageName() {
      return this.sImageName;
   }

   protected final void setImageName(String sImageName) {
      this.sImageName = sImageName;
   }

   protected final boolean getRapeatImage() {
      return this.reapeatImage;
   }

   protected final void setReapeatImage(boolean reapeatImage) {
      this.reapeatImage = reapeatImage;
   }

   protected final boolean getFlipX() {
      return this.flipX;
   }

   protected final void setFlipX(boolean flipX) {
      this.flipX = flipX;
   }

   protected final boolean getMovable() {
      return this.movable;
   }

   protected final void setMovable(boolean movable) {
      this.movable = movable;
   }
}
