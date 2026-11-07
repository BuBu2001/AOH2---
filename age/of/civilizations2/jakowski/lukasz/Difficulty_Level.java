package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

class Difficulty_Level extends MenuElement {
   protected static final Color COLOR_BG = new Color(0.05490196F, 0.07450981F, 0.11764706F, 1.0F);
   protected static final Color COLOR_BG_DIFF = new Color(0.039215688F, 0.047058824F, 0.06666667F, 1.0F);
   private float fPercentage = 1.0F;
   private long lTime = 0L;
   protected int iCurrentPosX = 0;

   protected Difficulty_Level(int iPosX, int iPosY, int iWidth, int iHeight, float fPercentage) {
      this.setPosX(iPosX);
      this.setPosY(iPosY);
      this.setWidth(iWidth);
      this.setHeight(iHeight);
      this.fPercentage = fPercentage;
      this.updateSlider(-1);
      this.lTime = System.currentTimeMillis();
      this.typeOfElement = MenuElement.TypeOfElement.BUTTON;
   }

   @Override
   protected void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
      if (System.currentTimeMillis() < this.lTime + 425L) {
         this.iCurrentPosX = (int)((float)this.getWidth() * this.fPercentage * ((float)(System.currentTimeMillis() - this.lTime) / 425.0F));
         this.iCurrentPosX = (int)Math.min((float)this.iCurrentPosX, (float)this.getWidth() * this.fPercentage);
         CFG.setRender_3(true);
      } else {
         this.iCurrentPosX = (int)((float)this.getWidth() * this.fPercentage);
      }

      oSB.setColor(COLOR_BG);
      ImageManager.getImage(Images.pix255_255_255).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight());
      oSB.setColor(COLOR_BG_DIFF);
      ImageManager.getImage(Images.pix255_255_255)
         .draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - 1 + iTranslateY, this.iCurrentPosX, this.getHeight());
      oSB.setColor(CFG.COLOR_CREATE_NEW_GAME_BOX_PLAYERS);
      ImageManager.getImage(Images.pix255_255_255)
         .draw(oSB, this.getPosX() + this.iCurrentPosX + iTranslateX, this.getPosY() + 2 + iTranslateY, 1, this.getHeight() - 4);
      oSB.setColor(Color.WHITE);
      ImageManager.getImage(Images.difficulty_box)
         .draw2(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.difficulty_box).getHeight() + iTranslateY,
            this.getWidth() - ImageManager.getImage(Images.difficulty_box).getWidth(),
            this.getHeight() - ImageManager.getImage(Images.difficulty_box).getHeight()
         );
      ImageManager.getImage(Images.difficulty_box)
         .draw2(
            oSB,
            this.getPosX() + this.getWidth() - ImageManager.getImage(Images.difficulty_box).getWidth() + iTranslateX,
            this.getPosY() - ImageManager.getImage(Images.difficulty_box).getHeight() + iTranslateY,
            ImageManager.getImage(Images.difficulty_box).getWidth(),
            this.getHeight() - ImageManager.getImage(Images.difficulty_box).getHeight(),
            true
         );
      ImageManager.getImage(Images.difficulty_box)
         .draw2(
            oSB,
            this.getPosX() + iTranslateX,
            this.getPosY() + this.getHeight() - ImageManager.getImage(Images.difficulty_box).getHeight() * 2 + iTranslateY,
            this.getWidth() - ImageManager.getImage(Images.difficulty_box).getWidth(),
            ImageManager.getImage(Images.difficulty_box).getHeight(),
            false,
            true
         );
      ImageManager.getImage(Images.difficulty_box)
         .draw(
            oSB,
            this.getPosX() + this.getWidth() - ImageManager.getImage(Images.difficulty_box).getWidth() + iTranslateX,
            this.getPosY() + this.getHeight() - ImageManager.getImage(Images.difficulty_box).getHeight() + iTranslateY,
            true,
            true
         );
   }

   @Override
   protected void setCurrent(int nCurrent) {
      this.fPercentage = (float)nCurrent / 100.0F;
      this.lTime = System.currentTimeMillis();
   }

   @Override
   protected int getCurrent() {
      return (int)(this.fPercentage * 100.0F);
   }
}
