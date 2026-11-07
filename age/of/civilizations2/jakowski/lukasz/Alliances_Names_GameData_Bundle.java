package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

class Alliances_Names_GameData_Bundle implements Serializable {
   private static final long serialVersionUID = 0L;
   private List<String> sWords = new ArrayList<>();

   protected Alliances_Names_GameData_Bundle(String nWord) {
      this.sWords.add(nWord);
   }

   protected final void addWord(String nWord) {
      this.sWords.add(nWord);
   }

   protected final void removeWord(int i) {
      this.sWords.remove(i);
   }

   protected final String getWord(int i) {
      return this.sWords.get(i);
   }

   protected final String setWord(int i, String nWord) {
      return this.sWords.set(i, nWord);
   }

   protected final int getWordsSize() {
      return this.sWords.size();
   }
}
