package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;
import java.util.List;

class UnionFlagsToGenerate_Manager {
   protected List<Image> lFlags_Small = new ArrayList<>();
   protected List<Image> lFlags_H = new ArrayList<>();
   protected List<UnionFlagsToGenerate> lFlags = new ArrayList<>();
   protected List<Integer> lCivs_FlagsToLoad = new ArrayList<>();

   protected UnionFlagsToGenerate_Manager() {
      this.loadImages();
   }

   protected final void generateFlags(SpriteBatch oSB) {
      while((!CFG.menuManager.getInGameView() || CFG.gameAction.getActiveTurnState() == Game_Action.TurnStates.INPUT_ORDERS) && this.lFlags.size() > 0) {
         if (!this.lFlags.get(0).generateFlag(oSB)) {
            return;
         }

         this.lFlags.remove(0);
      }

      while(
         (!CFG.menuManager.getInGameView() || CFG.gameAction.getActiveTurnState() == Game_Action.TurnStates.INPUT_ORDERS) && this.lCivs_FlagsToLoad.size() > 0
      ) {
         try {
            if (!CFG.game.getCiv(this.lCivs_FlagsToLoad.get(0)).loadFlag()) {
               return;
            }

            if (CFG.game.getCiv(this.lCivs_FlagsToLoad.get(0)).getControlledByPlayer()) {
               CFG.game.getPlayer(CFG.game.getPlayerID_ByCivID(this.lCivs_FlagsToLoad.get(0))).loadPlayersFlag();
            }
         } catch (IndexOutOfBoundsException var5) {
            try {
               this.lCivs_FlagsToLoad.remove(0);
            } catch (IndexOutOfBoundsException var4) {
            }

            return;
         } catch (RuntimeException var6) {
            return;
         }

         if (CFG.game.getCiv(this.lCivs_FlagsToLoad.get(0)).getFlag_IsNull()) {
            return;
         }

         this.lCivs_FlagsToLoad.remove(0);
      }
   }

   protected final void loadImages() {
      for(int i = 0; i < 4; ++i) {
         this.lFlags_Small.add(new Image(new Texture(Gdx.files.internal("game/unions/union_0_" + i + ".png")), Texture.TextureFilter.Nearest));
         this.lFlags_H.add(new Image(new Texture(Gdx.files.internal("game/unions/unionH_0_" + i + ".png")), Texture.TextureFilter.Nearest));
      }
   }

   protected final void addFlagToLoad(int nCivID) {
      for(int i = this.lCivs_FlagsToLoad.size() - 1; i >= 0; --i) {
         if (this.lCivs_FlagsToLoad.get(i) == nCivID) {
            return;
         }
      }

      this.lCivs_FlagsToLoad.add(nCivID);
   }
}
