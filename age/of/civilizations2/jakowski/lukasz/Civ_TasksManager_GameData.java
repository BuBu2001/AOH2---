package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

class Civ_TasksManager_GameData implements Serializable {
   private static final long serialVersionUID = 0L;
   protected List<Civ_Task> civTasks = new ArrayList<>();
   protected int iTasksSize = 0;

   protected final void addNewTask(Civ_Task nTask) {
      for(int i = this.civTasks.size() - 1; i >= 0; --i) {
         switch(this.civTasks.get(i).taskType) {
            default:
               if (this.civTasks.get(i).iProvinceID == nTask.iProvinceID) {
                  return;
               }
         }
      }

      this.civTasks.add(nTask);
      this.iTasksSize = this.civTasks.size();
   }

   protected final void runTasks() {
      int i = 0;

      while(i < this.iTasksSize) {
         ++i;
      }
   }
}
