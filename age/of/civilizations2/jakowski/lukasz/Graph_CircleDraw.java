package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.PolygonRegion;
import com.badlogic.gdx.graphics.g2d.PolygonSprite;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.EarClippingTriangulator;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ShortArray;
import java.util.List;

class Graph_CircleDraw {
   private PolygonSpriteBatch oPB;
   private TextureRegion texture;
   private Image textureOver;
   private Image circleFrame;
   private Vector2 center;
   private Vector2 centerTop;
   private Vector2 leftTop;
   private Vector2 leftBottom;
   private Vector2 rightBottom;
   private Vector2 rightTop;
   private float[] fv;
   private Graph_CircleDraw.IntersectAt intersectAt;

   protected Graph_CircleDraw(String nFileName, String fileNameOver, String nFlieNameFrame) {
      this.texture = new TextureRegion(new Texture(Gdx.files.internal("UI/" + CFG.getRescouresPath() + "graph/" + nFileName), Pixmap.Format.RGBA8888, true));
      this.textureOver = new Image(
         new Texture(Gdx.files.internal("UI/" + CFG.getRescouresPath() + "graph/" + fileNameOver), Pixmap.Format.RGBA8888, true), Texture.TextureFilter.Linear
      );
      this.circleFrame = new Image(
         new Texture(Gdx.files.internal("UI/" + CFG.getRescouresPath() + "graph/" + nFlieNameFrame), Pixmap.Format.RGBA8888, true),
         Texture.TextureFilter.Linear
      );
      this.oPB = new PolygonSpriteBatch();
      this.center = new Vector2((float)(this.texture.getRegionWidth() / 2), (float)(this.texture.getRegionHeight() / 2));
      this.centerTop = new Vector2((float)(this.texture.getRegionWidth() / 2), (float)this.texture.getRegionHeight());
      this.leftTop = new Vector2(0.0F, (float)this.texture.getRegionHeight());
      this.leftBottom = new Vector2(0.0F, 0.0F);
      this.rightBottom = new Vector2((float)this.texture.getRegionWidth(), 0.0F);
      this.rightTop = new Vector2((float)this.texture.getRegionWidth(), (float)this.texture.getRegionHeight());
      this.setPercentage(0.0F);
   }

   protected final void draw(SpriteBatch oSB, int nPosX, int nPosY, List<Graph_CircleData> nData, boolean isActive) {
      try {
         this.drawCircle100(
            oSB,
            nPosX,
            nPosY,
            new Color(
               (float)CFG.game.getCiv(nData.get(0).getDataID()).getR() / 255.0F,
               (float)CFG.game.getCiv(nData.get(0).getDataID()).getG() / 255.0F,
               (float)CFG.game.getCiv(nData.get(0).getDataID()).getB() / 255.0F,
               1.0F
            )
         );
      } catch (IndexOutOfBoundsException var7) {
         this.drawCircle100(
            oSB,
            nPosX,
            nPosY,
            new Color(
               CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getR(),
               CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getG(),
               CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getB(),
               1.0F
            )
         );
      }

      oSB.end();
      this.drawGraph(nPosX, nPosY, nData);
      oSB.begin();
      this.textureOver.draw(oSB, nPosX, nPosY);
      if (isActive) {
         oSB.setColor(new Color(1.0F, 1.0F, 1.0F, 0.335F));
         this.textureOver.draw(oSB, nPosX, nPosY);
         oSB.setColor(Color.WHITE);
      }

      this.circleFrame.draw(oSB, nPosX, nPosY);
   }

   private final void drawGraph(int nPosX, int nPosY, List<Graph_CircleData> nData) {
      try {
         this.oPB.begin();
         float drawnPercentage = nData.get(0).getPercentage();

         for(int i = 1; i < nData.size(); ++i) {
            this.setPercentage(drawnPercentage);

            try {
               this.drawCircle(
                  nPosX,
                  nPosY,
                  new Color(
                     (float)CFG.game.getCiv(nData.get(i).getDataID()).getR() / 255.0F,
                     (float)CFG.game.getCiv(nData.get(i).getDataID()).getG() / 255.0F,
                     (float)CFG.game.getCiv(nData.get(i).getDataID()).getB() / 255.0F,
                     1.0F
                  )
               );
            } catch (IndexOutOfBoundsException var7) {
               this.drawCircle(
                  nPosX,
                  nPosY,
                  new Color(
                     CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getR(),
                     CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getG(),
                     CFG.settingsManager.COLOR_PROVINCE_DISCOVERY.getB(),
                     1.0F
                  )
               );
            }

            drawnPercentage += nData.get(i).getPercentage();
         }

         this.oPB.end();
      } catch (IllegalStateException var8) {
      }
   }

   private final void drawCircle100(SpriteBatch oSB, int nPosX, int nPosY, Color nColor) {
      oSB.setColor(nColor);
      oSB.draw(
         this.texture.getTexture(),
         (float)nPosX,
         (float)(-nPosY - this.texture.getRegionHeight()),
         0.0F,
         0.0F,
         (float)this.texture.getRegionWidth(),
         (float)this.texture.getRegionHeight(),
         1.0F,
         1.0F,
         0.0F,
         0,
         0,
         this.texture.getRegionWidth(),
         this.texture.getRegionHeight(),
         false,
         false
      );
      oSB.setColor(Color.WHITE);
   }

   private final void drawCircle(int nPosX, int nPosY, Color nColor) {
      if (this.fv != null) {
         EarClippingTriangulator e = new EarClippingTriangulator();
         ShortArray sv = e.computeTriangles(this.fv);
         PolygonRegion polyReg = new PolygonRegion(this.texture, this.fv, sv.toArray());
         PolygonSprite poly = new PolygonSprite(polyReg);
         poly.setOrigin(0.0F, 0.0F);
         poly.setPosition((float)nPosX, (float)(CFG.GAME_HEIGHT - this.texture.getRegionHeight() - nPosY));
         poly.setRotation(0.0F);
         poly.setColor(nColor);
         poly.draw(this.oPB);
      }
   }

   protected final void setPercentage(float percent) {
      float angle = this.convertToRadians(90.0F);
      angle -= this.convertToRadians(percent * 360.0F / 100.0F);
      float len = this.texture.getRegionWidth() > this.texture.getRegionHeight()
         ? (float)this.texture.getRegionWidth()
         : (float)this.texture.getRegionHeight();
      float dy = (float)(Math.sin((double)angle) * (double)len);
      float dx = (float)(Math.cos((double)angle) * (double)len);
      Vector2 line = new Vector2(this.center.x + dx, this.center.y + dy);
      Vector2 v = this.IntersectPoint(line);
      if (this.intersectAt == Graph_CircleDraw.IntersectAt.TOP) {
         if (v.x >= (float)(this.texture.getRegionWidth() / 2)) {
            this.fv = new float[]{
               this.center.x,
               this.center.y,
               this.centerTop.x,
               this.centerTop.y,
               this.leftTop.x,
               this.leftTop.y,
               this.leftBottom.x,
               this.leftBottom.y,
               this.rightBottom.x,
               this.rightBottom.y,
               this.rightTop.x,
               this.rightTop.y,
               v.x,
               v.y
            };
         } else {
            this.fv = new float[]{this.center.x, this.center.y, this.centerTop.x, this.centerTop.y, v.x, v.y};
         }
      } else if (this.intersectAt == Graph_CircleDraw.IntersectAt.BOTTOM) {
         this.fv = new float[]{
            this.center.x, this.center.y, this.centerTop.x, this.centerTop.y, this.leftTop.x, this.leftTop.y, this.leftBottom.x, this.leftBottom.y, v.x, v.y
         };
      } else if (this.intersectAt == Graph_CircleDraw.IntersectAt.LEFT) {
         this.fv = new float[]{this.center.x, this.center.y, this.centerTop.x, this.centerTop.y, this.leftTop.x, this.leftTop.y, v.x, v.y};
      } else if (this.intersectAt == Graph_CircleDraw.IntersectAt.RIGHT) {
         this.fv = new float[]{
            this.center.x,
            this.center.y,
            this.centerTop.x,
            this.centerTop.y,
            this.leftTop.x,
            this.leftTop.y,
            this.leftBottom.x,
            this.leftBottom.y,
            this.rightBottom.x,
            this.rightBottom.y,
            v.x,
            v.y
         };
      } else {
         this.fv = null;
      }
   }

   private final Vector2 IntersectPoint(Vector2 line) {
      Vector2 v = new Vector2();
      boolean isIntersect = Intersector.intersectSegments(this.leftTop, this.rightTop, this.center, line, v);
      if (isIntersect) {
         this.intersectAt = Graph_CircleDraw.IntersectAt.TOP;
         return v;
      } else {
         isIntersect = Intersector.intersectSegments(this.leftBottom, this.rightBottom, this.center, line, v);
         if (isIntersect) {
            this.intersectAt = Graph_CircleDraw.IntersectAt.BOTTOM;
            return v;
         } else {
            isIntersect = Intersector.intersectSegments(this.leftTop, this.leftBottom, this.center, line, v);
            if (isIntersect) {
               this.intersectAt = Graph_CircleDraw.IntersectAt.LEFT;
               return v;
            } else {
               isIntersect = Intersector.intersectSegments(this.rightTop, this.rightBottom, this.center, line, v);
               if (isIntersect) {
                  this.intersectAt = Graph_CircleDraw.IntersectAt.RIGHT;
                  return v;
               } else {
                  this.intersectAt = Graph_CircleDraw.IntersectAt.NONE;
                  return null;
               }
            }
         }
      }
   }

   private final float convertToRadians(float angleInDegrees) {
      return angleInDegrees * ((float) (Math.PI / 180.0));
   }

   protected final int getWidth() {
      return this.circleFrame.getWidth();
   }

   protected static enum IntersectAt {
      NONE,
      TOP,
      BOTTOM,
      LEFT,
      RIGHT;
   }
}
