package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

class Image {
   private Texture texture;
   private int iWidth;
   private int iHeight;

   protected Image(Texture texture) {
      this.init(texture, Texture.TextureFilter.Linear, Texture.TextureFilter.Linear, Texture.TextureWrap.ClampToEdge, Texture.TextureWrap.ClampToEdge);
   }

   protected Image(Texture texture, Texture.TextureFilter nTextureFilter) {
      this.init(texture, nTextureFilter, nTextureFilter, Texture.TextureWrap.ClampToEdge, Texture.TextureWrap.ClampToEdge);
   }

   protected Image(Texture texture, Texture.TextureFilter nTextureFilter, Texture.TextureWrap nTextureWrap) {
      this.init(texture, nTextureFilter, nTextureFilter, nTextureWrap, nTextureWrap);
   }

   protected Image(Texture texture, Texture.TextureFilter minFilter, Texture.TextureFilter magFilter) {
      this.init(texture, minFilter, magFilter, Texture.TextureWrap.ClampToEdge, Texture.TextureWrap.ClampToEdge);
   }

   protected Image(Texture texture, Texture.TextureFilter minFilter, Texture.TextureFilter magFilter, Texture.TextureWrap wrapU, Texture.TextureWrap wrapV) {
      this.init(texture, minFilter, magFilter, wrapU, wrapV);
   }

   protected final void init(
      Texture texture, Texture.TextureFilter minFilter, Texture.TextureFilter magFilter, Texture.TextureWrap wrapU, Texture.TextureWrap wrapV
   ) {
      this.texture = texture;
      this.texture.setFilter(minFilter, magFilter);
      this.texture.setWrap(wrapU, wrapV);
      this.iWidth = texture.getWidth();
      this.iHeight = texture.getHeight();
   }

   protected final void draw(SpriteBatch oSB) {
      this.draw(oSB, 0, 0, 0, 0, this.iWidth, this.iHeight, 1.0F, 1.0F, 0.0F, 0, 0, this.iWidth, this.iHeight, false, false);
   }

   protected final void draw(SpriteBatch oSB, int nPosX, int nPosY) {
      this.draw(oSB, nPosX, nPosY, 0, 0, this.iWidth, this.iHeight, 1.0F, 1.0F, 0.0F, 0, 0, this.iWidth, this.iHeight, false, false);
   }

   protected final void draw(SpriteBatch oSB, int nPosX, int nPosY, boolean flipX) {
      this.draw(oSB, nPosX, nPosY, 0, 0, this.iWidth, this.iHeight, 1.0F, 1.0F, 0.0F, 0, 0, this.iWidth, this.iHeight, flipX, false);
   }

   protected final void draw(SpriteBatch oSB, int nPosX, int nPosY, boolean flipX, boolean flipY) {
      this.draw(oSB, nPosX, nPosY, 0, 0, this.iWidth, this.iHeight, 1.0F, 1.0F, 0.0F, 0, 0, this.iWidth, this.iHeight, flipX, flipY);
   }

   protected final void draw(SpriteBatch oSB, int nPosX, int nPosY, float scale) {
      this.draw(oSB, nPosX, nPosY, 0, 0, this.iWidth, this.iHeight, scale, scale, 0.0F, 0, 0, this.iWidth, this.iHeight, false, false);
   }

   protected final void draw(SpriteBatch oSB, int nPosX, int nPosY, int nWidth) {
      this.draw(oSB, nPosX, nPosY, 0, 0, nWidth, this.iHeight, 1.0F, 1.0F, 0.0F, 0, 0, nWidth, this.iHeight, false, false);
   }

   protected final void draw(SpriteBatch oSB, int nPosX, int nPosY, int nWidth, boolean flipY) {
      this.draw(oSB, nPosX, nPosY, 0, 0, nWidth, this.iHeight, 1.0F, 1.0F, 0.0F, 0, 0, nWidth, this.iHeight, false, flipY);
   }

   protected final void draw(SpriteBatch oSB, int nPosX, int nPosY, int nWidth, boolean flipX, boolean flipY) {
      this.draw(oSB, nPosX, nPosY, 0, 0, nWidth, this.iHeight, 1.0F, 1.0F, 0.0F, 0, 0, nWidth, this.iHeight, flipX, flipY);
   }

   protected final void draw(SpriteBatch oSB, int nPosX, int nPosY, int nWidth, int nHeight) {
      this.draw(oSB, nPosX, nPosY + nHeight, 0, 0, nWidth, nHeight, 1.0F, 1.0F, 0.0F, 0, 0, this.iWidth, this.iHeight, false, false);
   }

   protected final void draw(SpriteBatch oSB, int nPosX, int nPosY, int nWidth, int nHeight, boolean flipX, boolean flipY) {
      this.draw(oSB, nPosX, nPosY + nHeight, 0, 0, nWidth, nHeight, 1.0F, 1.0F, 0.0F, 0, 0, this.iWidth, this.iHeight, flipX, flipY);
   }

   protected final void draw2(SpriteBatch oSB, int nPosX, int nPosY, int nWidth, int nHeight) {
      this.draw(oSB, nPosX, nPosY + nHeight, 0, 0, nWidth, nHeight, 1.0F, 1.0F, 0.0F, 0, 0, nWidth, nHeight, false, false);
   }

   protected final void draw2(SpriteBatch oSB, int nPosX, int nPosY, int nWidth, int nHeight, int srcX, int srcY) {
      this.draw(oSB, nPosX, nPosY + nHeight, 0, 0, nWidth, nHeight, 1.0F, 1.0F, 0.0F, srcX, srcY, nWidth, nHeight, false, false);
   }

   protected final void draw2(SpriteBatch oSB, int nPosX, int nPosY, int nWidth, int nHeight, int srcX, int srcY, float fRotate) {
      this.draw(oSB, nPosX, nPosY + nHeight, 0, 0, nWidth, nHeight, 1.0F, 1.0F, fRotate, srcX, srcY, nWidth, nHeight, false, false);
   }

   protected final void draw2(SpriteBatch oSB, int nPosX, int nPosY, int nWidth, int nHeight, int srcX, int srcY, float fRotate, boolean flipX, boolean flipY) {
      this.draw(oSB, nPosX, nPosY + nHeight, 0, 0, nWidth, nHeight, 1.0F, 1.0F, fRotate, srcX, srcY, nWidth, nHeight, flipX, flipY);
   }

   protected final void draw2(SpriteBatch oSB, int nPosX, int nPosY, int nWidth, int nHeight, boolean flipX) {
      this.draw(oSB, nPosX, nPosY + nHeight, 0, 0, nWidth, nHeight, 1.0F, 1.0F, 0.0F, 0, 0, nWidth, nHeight, flipX, false);
   }

   protected final void draw2(SpriteBatch oSB, int nPosX, int nPosY, int nWidth, int nHeight, boolean flipX, boolean flipY) {
      this.draw(oSB, nPosX, nPosY + nHeight, 0, 0, nWidth, nHeight, 1.0F, 1.0F, 0.0F, 0, 0, nWidth, nHeight, flipX, flipY);
   }

   protected final void draw(SpriteBatch oSB, int nPosX, int nPosY, int nWidth, int nHeight, float rotation) {
      this.draw(oSB, nPosX, nPosY, 0, 0, nWidth, -nHeight, 1.0F, 1.0F, rotation, 0, 0, nWidth, nHeight, false, false);
   }

   protected final void draw(SpriteBatch oSB, int nPosX, int nPosY, int nWidth, int nHeight, float rotation, int srcX) {
      this.draw(oSB, nPosX, nPosY, 0, 0, nWidth, -nHeight, 1.0F, 1.0F, rotation, srcX, 0, nWidth, nHeight, false, false);
   }

   protected final void draw(SpriteBatch oSB, int nPosX, int nPosY, int nWidth, int nHeight, float rotation, int srcX, boolean flipX) {
      this.draw(oSB, nPosX, nPosY, 0, 0, nWidth, -nHeight, 1.0F, 1.0F, rotation, srcX, 0, nWidth, nHeight, flipX, false);
   }

   protected final void draw3(SpriteBatch oSB, int nPosX, int nPosY, int nWidth, int nHeight) {
      this.draw(oSB, nPosX, nPosY + nHeight, 0, 0, nWidth, nHeight, 1.0F, 1.0F, 0.0F, 0, 0, this.iWidth, this.iHeight, false, false);
   }

   protected final void draw(
      SpriteBatch oSB,
      int nPosX,
      int nPosY,
      int originX,
      int originY,
      int nWidth,
      int nHeight,
      float scaleX,
      float scaleY,
      float rotation,
      int srcX,
      int srcY,
      int srcWidth,
      int srcHeight,
      boolean flipX,
      boolean flipY
   ) {
      oSB.draw(
         this.texture,
         (float)nPosX,
         (float)(-nPosY - this.iHeight),
         (float)originX,
         (float)originY,
         (float)nWidth,
         (float)nHeight,
         scaleX,
         scaleY,
         rotation,
         srcX,
         srcY,
         srcWidth,
         srcHeight,
         flipX,
         flipY
      );
   }

   protected final Texture getTexture() {
      return this.texture;
   }

   protected final void setTexture(Texture texture) {
      this.texture = texture;
   }

   protected final int getWidth() {
      return this.iWidth;
   }

   protected final int getHeight() {
      return this.iHeight;
   }

   protected final void dispose() {
      if (this.texture != null) {
         this.texture.dispose();
      }

      this.texture = null;
   }
}
