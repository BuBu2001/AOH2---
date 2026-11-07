package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.GdxRuntimeException;

class Ideology {
   private String sName;
   private String extraTag;
   protected int GOV_GROUP_ID;
   protected String AI_TYPE;
   private Image iCrownImage = null;
   private Image iCrownVassalImage = null;
   private Image iCrownImageScaled = null;
   protected float MIN_GOODS = 0.15F;
   protected float MIN_INVESTMENTS = 0.1F;
   protected float RESEARCH_COST = 0.15F;
   protected float ACCEPTABLE_TAXATION = 0.25F;
   protected float MILITARY_UPKEEP = 1.0F;
   protected float ADMINISTRATION_COST = 1.0F;
   protected float ADMINISTRATION_COST_DISTANCE = 1.0F;
   protected float ADMINISTRATION_COST_CAPITAL = 0.6F;
   protected float INCOME_TAXATION = 1.0F;
   protected float INCOME_PRODUCTION = 1.0F;
   protected int COST_OF_MOVE = 6;
   protected int COST_OF_MOVE_SAME_PROVINCE = 4;
   protected int COST_OF_MOVE_OWN_PROVINCE = 2;
   protected int COST_OF_DISBAND = 20;
   protected int COST_OF_PLUNDER = 12;
   protected int COST_OF_RECRUIT = 2;
   protected int DEFENSE_BONUS = 2;
   protected int CAN_BECOME_CIVILIZED = -1;
   protected float CIVILIZE_TECH_LEVEL = 1.0F;
   protected int AVAILABLE_SINCE_AGE_ID = 0;
   protected boolean REVOLUTIONARY = false;
   private Color oColor;

   protected final float getMin_Goods(int nCivID) {
      return this.MIN_GOODS;
   }

   protected final float getMin_Goods() {
      return this.MIN_GOODS;
   }

   protected Ideology(
      String sName,
      String extraTag,
      int iR,
      int iG,
      int iB,
      float MIN_GOODS,
      float MIN_INVESTMENTS,
      int COST_OF_MOVE,
      int COST_OF_MOVE_SAME_PROVINCE,
      int COST_OF_MOVE_OWN_PROVINCE,
      float RESEARCH_COST,
      float ACCEPTABLE_TAXATION,
      int DEFENSE_BONUS,
      float MILITARY_UPKEEP,
      float ADMINISTRATION_COST,
      float ADMINISTRATION_COST_DISTANCE,
      float INCOME_TAXATION,
      float INCOME_PRODUCTION,
      int COST_OF_RECRUIT,
      int COST_OF_DISBAND,
      int COST_OF_PLUNDER,
      int CAN_BECOME_CIVILIZED,
      int AVAILABLE_SINCE_AGE_ID,
      float ADMINISTRATION_COST_CAPITAL,
      float CIVILIZE_TECH_LEVEL,
      String AI_TYPE,
      boolean REVOLUTIONARY,
      int GOV_GROUP_ID
   ) {
      this.sName = sName;
      this.extraTag = extraTag;
      this.oColor = new Color((float)iR / 255.0F, (float)iG / 255.0F, (float)iB / 255.0F, 0.425F);
      this.GOV_GROUP_ID = GOV_GROUP_ID;
      if (RESEARCH_COST < 0.01F) {
         RESEARCH_COST = 0.01F;
      }

      if (ACCEPTABLE_TAXATION < 0.01F) {
         ACCEPTABLE_TAXATION = 0.01F;
      }

      if (ACCEPTABLE_TAXATION > 0.99F) {
         ACCEPTABLE_TAXATION = 0.99F;
      }

      if ((double)MIN_GOODS < 0.01) {
         MIN_GOODS = 0.01F;
      }

      if (MIN_GOODS > 1.0F) {
         MIN_GOODS = 1.0F;
      }

      if ((double)MIN_INVESTMENTS < 0.01) {
         MIN_INVESTMENTS = 0.01F;
      }

      if (MIN_INVESTMENTS > 1.0F) {
         MIN_INVESTMENTS = 1.0F;
      }

      this.ACCEPTABLE_TAXATION = ACCEPTABLE_TAXATION;
      this.MIN_GOODS = MIN_GOODS;
      this.MIN_INVESTMENTS = MIN_INVESTMENTS;
      this.RESEARCH_COST = RESEARCH_COST;
      if (COST_OF_MOVE < 0) {
         COST_OF_MOVE = 0;
      }

      if (COST_OF_MOVE_SAME_PROVINCE < 0) {
         COST_OF_MOVE_SAME_PROVINCE = 0;
      }

      if (COST_OF_MOVE_OWN_PROVINCE < 0) {
         COST_OF_MOVE_OWN_PROVINCE = 0;
      }

      if (COST_OF_DISBAND < 0) {
         COST_OF_DISBAND = 0;
      }

      if (COST_OF_PLUNDER < 0) {
         COST_OF_PLUNDER = 0;
      }

      this.COST_OF_MOVE = COST_OF_MOVE;
      this.COST_OF_MOVE_SAME_PROVINCE = COST_OF_MOVE_SAME_PROVINCE;
      this.COST_OF_MOVE_OWN_PROVINCE = COST_OF_MOVE_OWN_PROVINCE;
      this.COST_OF_DISBAND = COST_OF_DISBAND;
      this.COST_OF_PLUNDER = COST_OF_PLUNDER;
      if (DEFENSE_BONUS < -40) {
         DEFENSE_BONUS = 40;
      }

      if (DEFENSE_BONUS > 40) {
         DEFENSE_BONUS = 40;
      }

      this.DEFENSE_BONUS = DEFENSE_BONUS;
      if ((double)MILITARY_UPKEEP < 0.1) {
         MILITARY_UPKEEP = 0.1F;
      }

      this.MILITARY_UPKEEP = MILITARY_UPKEEP;
      if (ADMINISTRATION_COST < 0.1F) {
         ADMINISTRATION_COST = 0.1F;
      }

      this.ADMINISTRATION_COST = ADMINISTRATION_COST;
      if ((double)ADMINISTRATION_COST_DISTANCE < 0.01) {
         ADMINISTRATION_COST_DISTANCE = 0.01F;
      }

      this.ADMINISTRATION_COST_DISTANCE = ADMINISTRATION_COST_DISTANCE;
      if ((double)INCOME_TAXATION < 0.01) {
         INCOME_TAXATION = 0.01F;
      }

      this.INCOME_TAXATION = INCOME_TAXATION;
      if (INCOME_PRODUCTION < 0.01F) {
         INCOME_PRODUCTION = 0.01F;
      }

      this.INCOME_PRODUCTION = INCOME_PRODUCTION;
      if (COST_OF_RECRUIT < 1) {
         COST_OF_RECRUIT = 1;
      }

      this.COST_OF_RECRUIT = COST_OF_RECRUIT;
      this.CAN_BECOME_CIVILIZED = CAN_BECOME_CIVILIZED;
      this.AVAILABLE_SINCE_AGE_ID = AVAILABLE_SINCE_AGE_ID;
      if ((double)ADMINISTRATION_COST_CAPITAL < 0.01) {
         ADMINISTRATION_COST_CAPITAL = 0.01F;
      } else if (ADMINISTRATION_COST_CAPITAL > 1.0F) {
         ADMINISTRATION_COST_CAPITAL = 1.0F;
      }

      this.ADMINISTRATION_COST_CAPITAL = ADMINISTRATION_COST_CAPITAL;
      if ((double)CIVILIZE_TECH_LEVEL < 0.01) {
         CIVILIZE_TECH_LEVEL = 0.01F;
      }

      this.CIVILIZE_TECH_LEVEL = CIVILIZE_TECH_LEVEL;
      this.REVOLUTIONARY = REVOLUTIONARY;
      this.AI_TYPE = AI_TYPE;

      try {
         this.iCrownImage = new Image(
            new Texture(Gdx.files.internal("UI/icons/crowns/crown" + extraTag + ".png"), Pixmap.Format.RGBA8888, true), Texture.TextureFilter.Linear
         );
      } catch (GdxRuntimeException var32) {
         this.iCrownImage = new Image(new Texture(Gdx.files.internal("UI/icons/crowns/crown.png"), Pixmap.Format.RGBA8888, true), Texture.TextureFilter.Linear);
      }

      try {
         this.iCrownVassalImage = new Image(
            new Texture(Gdx.files.internal("UI/icons/crowns/crown_x" + extraTag + ".png"), Pixmap.Format.RGBA8888, true), Texture.TextureFilter.Linear
         );
      } catch (GdxRuntimeException var31) {
         this.iCrownVassalImage = new Image(
            new Texture(Gdx.files.internal("UI/icons/crowns/crown_x.png"), Pixmap.Format.RGBA8888, true), Texture.TextureFilter.Linear
         );
      }

      try {
         this.iCrownImageScaled = new Image(
            new Texture(Gdx.files.internal("UI/" + CFG.getRescouresPath() + "crowns/" + "crown" + extraTag + ".png"), Pixmap.Format.RGBA8888, true),
            Texture.TextureFilter.Linear
         );
      } catch (GdxRuntimeException var30) {
         this.iCrownImageScaled = new Image(
            new Texture(Gdx.files.internal("UI/" + CFG.getRescouresPath() + "crowns/" + "crown.png"), Pixmap.Format.RGBA8888, true),
            Texture.TextureFilter.Linear
         );
      }
   }

   protected final String getName() {
      return this.sName;
   }

   protected final void setName(String sName) {
      this.sName = sName;
   }

   protected final String getExtraTag() {
      return this.extraTag;
   }

   protected final void setExtraTag(String extraTag) {
      this.extraTag = extraTag;
   }

   protected final Color getColor() {
      return this.oColor;
   }

   protected final Image getiCrownImage() {
      return this.iCrownImage;
   }

   protected final Image getiCrownVassalImage() {
      return this.iCrownVassalImage;
   }

   protected final Image getCrownImageScaled() {
      return this.iCrownImageScaled;
   }
}
