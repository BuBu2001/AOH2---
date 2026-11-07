package age.of.civilizations2.jakowski.lukasz;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

class MessageBox_GameData implements Serializable {
   private static final long serialVersionUID = 0L;
   private List<Message> lMessages = new ArrayList<>();
   private int iMessagesSize = 0;

   protected final void updateNextTurn(int nCivID) {
      for(int i = 0; i < this.getMessagesSize(); ++i) {
         if (this.lMessages.get(i).updateNextTurn()) {
            if (!this.lMessages.get(i).requestsResponse) {
               if (this.lMessages.get(i).messageType == Message_Type.GIFT) {
                  this.lMessages.get(i).onAccept(nCivID);
               }

               this.lMessages.remove(i--);
               this.iMessagesSize = this.lMessages.size();
            } else if (!CFG.game.getCiv(nCivID).getControlledByPlayer() && this.lMessages.get(i).iNumOfTurnsLeft < -25) {
               this.lMessages.remove(i--);
               this.iMessagesSize = this.lMessages.size();
            }
         }
      }
   }

   protected final void addMessage(Message nMessage) {
      if (nMessage.messageType == Message_Type.DISEASE) {
         int nNumOfDisseaseMessages = 0;

         for(int i = 0; i < this.getMessagesSize(); ++i) {
            if (this.lMessages.get(i).messageType == Message_Type.DISEASE) {
               ++nNumOfDisseaseMessages;
            }
         }

         if (nNumOfDisseaseMessages > 2) {
            return;
         }
      } else {
         for(int i = 0; i < this.getMessagesSize(); ++i) {
            if (this.lMessages.get(i).iFromCivID == nMessage.iFromCivID && this.lMessages.get(i).messageType == nMessage.messageType) {
               if (nMessage.messageType == Message_Type.TECHNOLOGY_RESEARCHED) {
                  this.lMessages.get(i).iNumOfTurnsLeft = nMessage.iNumOfTurnsLeft;
                  return;
               }

               if (nMessage.messageType == Message_Type.UNCIVILIZED) {
                  this.lMessages.get(i).iNumOfTurnsLeft = nMessage.iNumOfTurnsLeft;
                  return;
               }

               if (nMessage.messageType != Message_Type.GIFT
                  && nMessage.messageType != Message_Type.WE_CAN_SIGN_PEACE
                  && nMessage.messageType != Message_Type.WE_CAN_SIGN_PEACE_STATUS_QUO
                  && nMessage.messageType != Message_Type.GIFT_REFUSED
                  && nMessage.messageType != Message_Type.GIFT_ACCEPTED
                  && nMessage.messageType != Message_Type.PLUNDER_REPROT
                  && nMessage.messageType != Message_Type.PLUNDER_REPROT_PLUNDRED
                  && nMessage.messageType != Message_Type.REVOLT
                  && nMessage.messageType != Message_Type.JOINED_A_WAR
                  && nMessage.messageType != Message_Type.TRANSFER_CONTROL
                  && nMessage.messageType != Message_Type.VASSALIZATION_ACCEPTED
                  && nMessage.messageType != Message_Type.VASSALIZATION_REJECTED
                  && nMessage.messageType != Message_Type.TRANSFER_CONTROL_REFUSED
                  && nMessage.messageType != Message_Type.TRANSFER_CONTROL_ACCEPTED
                  && nMessage.messageType != Message_Type.PROVINCES_NOT_SUPPLIED_STRAVES
                  && nMessage.messageType != Message_Type.PROVINCES_NOT_SUPPLIED_LOST_CONTROL
                  && nMessage.messageType != Message_Type.PROVINCES_NOT_SUPPLIED_LOST_CONTROL_ENEMY_LOST
                  && nMessage.messageType != Message_Type.PROVINCES_LOST_CONTROL
                  && nMessage.messageType != Message_Type.TRUCE
                  && nMessage.messageType != Message_Type.TRUCE_EXPIRED
                  && nMessage.messageType != Message_Type.LOAN_REPAID
                  && nMessage.messageType != Message_Type.BULIT_FARM
                  && nMessage.messageType != Message_Type.BULIT_PORT
                  && nMessage.messageType != Message_Type.BULIT_TOWER
                  && nMessage.messageType != Message_Type.BULIT_FORT
                  && nMessage.messageType != Message_Type.BULIT_LIBRARY
                  && nMessage.messageType != Message_Type.BUILT_ARMOURY
                  && nMessage.messageType != Message_Type.BUILT_WORKSHOP
                  && nMessage.messageType != Message_Type.FESTIVAL_IS_OVER
                  && nMessage.messageType != Message_Type.ASSMILIATION_IS_OVER
                  && nMessage.messageType != Message_Type.INVEST_IS_OVER
                  && nMessage.messageType != Message_Type.INVEST_IS_OVER_DEVELOPMENT) {
                  return;
               }

               if (nMessage.messageType == Message_Type.TRANSFER_CONTROL && nMessage.iValue == this.lMessages.get(i).iValue) {
                  return;
               }
            }
         }
      }

      this.lMessages.add(nMessage);
      this.iMessagesSize = this.lMessages.size();
   }

   protected final void removeMessage(int i) {
      try {
         this.lMessages.remove(i);
         this.iMessagesSize = this.lMessages.size();
      } catch (IndexOutOfBoundsException var3) {
      }
   }

   protected final void removeMessage_TypeFrom(int nFromCivID, Message_Type nType) {
      try {
         for(int i = this.getMessagesSize() - 1; i >= 0; --i) {
            if (this.getMessage(i).iFromCivID == nFromCivID && this.getMessage(i).messageType == nType) {
               this.removeMessage(i);
            }
         }
      } catch (IndexOutOfBoundsException var4) {
      }
   }

   protected Message getMessage(int i) {
      return this.lMessages.get(i);
   }

   protected final void clearMessages() {
      this.lMessages.clear();
      this.iMessagesSize = 0;
   }

   protected final int getMessagesSize() {
      return this.iMessagesSize;
   }
}
