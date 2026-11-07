package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import java.util.ArrayList;
import java.util.List;

class ImageManager {
   private static List<Image> images = new ArrayList<>();

   protected static final int addImage(String imageName) {
      return addImage(imageName, Texture.TextureFilter.Linear);
   }

   protected static final int addImage(String imageName, Texture.TextureFilter nTextureFilter) {
      return addImage(imageName, Pixmap.Format.RGBA8888, nTextureFilter);
   }

   protected static final int addImage(String imageName, Pixmap.Format nFormat, Texture.TextureFilter nTextureFilter) {
      images.add(new Image(new Texture(Gdx.files.internal(imageName), nFormat, true), nTextureFilter));
      return images.size() - 1;
   }

   protected static final int addImage(String imageName, Pixmap.Format nFormat, Texture.TextureFilter nTextureFilter, Texture.TextureWrap nTextureWrap) {
      images.add(new Image(new Texture(Gdx.files.internal(imageName), nFormat, true), nTextureFilter, nTextureWrap));
      return images.size() - 1;
   }

   protected static final Image getImage(int ID) {
      return images.get(ID);
   }

   protected static final int getImagesSize() {
      return images.size();
   }
}
