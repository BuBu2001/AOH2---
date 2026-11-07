package age.of.civilizations2.jakowski.lukasz;

import com.badlogic.gdx.Gdx;
import com.codedisaster.steamworks.SteamAPI;
import com.codedisaster.steamworks.SteamAPICall;
import com.codedisaster.steamworks.SteamApps;
import com.codedisaster.steamworks.SteamAuth;
import com.codedisaster.steamworks.SteamFriends;
import com.codedisaster.steamworks.SteamFriendsCallback;
import com.codedisaster.steamworks.SteamID;
import com.codedisaster.steamworks.SteamLeaderboardEntriesHandle;
import com.codedisaster.steamworks.SteamLeaderboardEntry;
import com.codedisaster.steamworks.SteamLeaderboardHandle;
import com.codedisaster.steamworks.SteamPublishedFileID;
import com.codedisaster.steamworks.SteamRemoteStorage;
import com.codedisaster.steamworks.SteamRemoteStorageCallback;
import com.codedisaster.steamworks.SteamResult;
import com.codedisaster.steamworks.SteamUGC;
import com.codedisaster.steamworks.SteamUGCCallback;
import com.codedisaster.steamworks.SteamUGCDetails;
import com.codedisaster.steamworks.SteamUGCHandle;
import com.codedisaster.steamworks.SteamUGCQuery;
import com.codedisaster.steamworks.SteamUser;
import com.codedisaster.steamworks.SteamUserCallback;
import com.codedisaster.steamworks.SteamUserStats;
import com.codedisaster.steamworks.SteamUserStatsCallback;
import com.codedisaster.steamworks.SteamUtils;

class Steam_Game {
   protected SteamUser user;
   protected SteamUserStats userStats;
   protected SteamRemoteStorage remoteStorage;
   protected SteamUGC ugc;
   protected SteamUtils utils;
   protected SteamApps apps;
   protected SteamFriends friends;
   protected int S_VASS = 0;
   protected int S_UNIO = 0;
   protected int S_ALLI = 0;
   protected static int iScore = -1;
   protected SteamLeaderboardHandle currentLeaderboard = null;

   protected final void uploadVassals() {
      if (this.userStats != null && !CFG.SANDBOX_MODE) {
         ++this.S_VASS;
         this.userStats.setStatI("S_VASS", this.S_VASS);
         this.userStats.storeStats();
      }
   }

   protected final void uploadUnions() {
      if (this.userStats != null && !CFG.SANDBOX_MODE) {
         ++this.S_UNIO;
         this.userStats.setStatI("S_UNIO", this.S_UNIO);
         this.userStats.storeStats();
      }
   }

   protected final void uploadAlliance() {
      if (this.userStats != null && !CFG.SANDBOX_MODE) {
         ++this.S_ALLI;
         this.userStats.setStatI("S_ALLI", this.S_ALLI);
         this.userStats.storeStats();
      }
   }

   protected final void uploadScore() {
      try {
         if (this.userStats != null && !CFG.SANDBOX_MODE) {
            if (iScore < 0) {
               this.userStats.findLeaderboard("Conquered Provinces");
            } else {
               ++iScore;
            }

            this.checkAchievement();
         }
      } catch (NullPointerException var2) {
         CFG.exceptionStack(var2);
      } catch (IndexOutOfBoundsException var3) {
         CFG.exceptionStack(var3);
      } catch (StackOverflowError var4) {
         CFG.exceptionStack(var4);
      }
   }

   protected final void checkGovermentAchievement(int toIdeologyID) {
      try {
         if (CFG.ideologiesManager.getIdeology(toIdeologyID).getExtraTag().equals("")) {
            Gdx.app.log("AoC", "G_DEM");
            this.userStats.setAchievement("G_DEM");
            this.userStats.storeStats();
         } else if (CFG.ideologiesManager.getIdeology(toIdeologyID).getExtraTag().equals("_m")) {
            Gdx.app.log("AoC", "G_MON");
            this.userStats.setAchievement("G_MON");
            this.userStats.storeStats();
         } else if (CFG.ideologiesManager.getIdeology(toIdeologyID).getExtraTag().equals("_c")) {
            Gdx.app.log("AoC", "G_COM");
            this.userStats.setAchievement("G_COM");
            this.userStats.storeStats();
         } else if (CFG.ideologiesManager.getIdeology(toIdeologyID).getExtraTag().equals("_f")) {
            Gdx.app.log("AoC", "G_FAC");
            this.userStats.setAchievement("G_FAC");
            this.userStats.storeStats();
         } else if (CFG.ideologiesManager.getIdeology(toIdeologyID).getExtraTag().equals("_r")) {
            Gdx.app.log("AoC", "G_REP");
            this.userStats.setAchievement("G_REP");
            this.userStats.storeStats();
         } else if (CFG.ideologiesManager.getIdeology(toIdeologyID).getExtraTag().equals("_h")) {
            Gdx.app.log("AoC", "G_HOR");
            this.userStats.setAchievement("G_HOR");
            this.userStats.storeStats();
         } else if (CFG.ideologiesManager.getIdeology(toIdeologyID).getExtraTag().equals("_s")) {
            Gdx.app.log("AoC", "G_CIT");
            this.userStats.setAchievement("G_CIT");
            this.userStats.storeStats();
         }
      } catch (IndexOutOfBoundsException var3) {
      } catch (NullPointerException var4) {
      }
   }

   protected final void checkFormableAchievement(int nCivID) {
      try {
         String realTag = CFG.game.getCiv(nCivID).getCivTag();
         if (realTag.equals("atr_m")) {
            Gdx.app.log("AoC", "AUSTR_0");
            this.userStats.setAchievement("AUSTR_0");
            this.userStats.storeStats();
         } else if (realTag.equals("kita") || realTag.equals("ita") || realTag.equals("ita2")) {
            Gdx.app.log("AoC", "ITAL_0");
            this.userStats.setAchievement("ITAL_0");
            this.userStats.storeStats();
         } else if (realTag.equals("tur_m")) {
            Gdx.app.log("AoC", "OTT_0");
            this.userStats.setAchievement("OTT_0");
            this.userStats.storeStats();
         } else if (realTag.equals("auhh")) {
            Gdx.app.log("AoC", "AUH_0");
            this.userStats.setAchievement("AUH_0");
            this.userStats.storeStats();
         } else if (realTag.equals("jap")) {
            Gdx.app.log("AoC", "JAP_0");
            this.userStats.setAchievement("JAP_0");
            this.userStats.storeStats();
         } else if (realTag.equals("rus_m") || realTag.equals("rus2")) {
            Gdx.app.log("AoC", "RUS_0");
            this.userStats.setAchievement("RUS_0");
            this.userStats.storeStats();
         }
      } catch (IndexOutOfBoundsException var3) {
      } catch (NullPointerException var4) {
      }
   }

   protected final void uploadScore_OnSave() {
      try {
         if (this.userStats != null && !CFG.SANDBOX_MODE) {
            this.userStats.uploadLeaderboardScore(this.currentLeaderboard, SteamUserStats.LeaderboardUploadScoreMethod.KeepBest, iScore, null);
         }
      } catch (NullPointerException var2) {
         CFG.exceptionStack(var2);
      } catch (IndexOutOfBoundsException var3) {
         CFG.exceptionStack(var3);
      } catch (StackOverflowError var4) {
         CFG.exceptionStack(var4);
      }
   }

   protected final void checkAchievement() {
      if (iScore >= 1500 && !this.userStats.isAchieved("CONQ_7", false)) {
         Gdx.app.log("AoC", "CONQ_7");
         this.userStats.setAchievement("CONQ_7");
         this.userStats.storeStats();
      }

      if (iScore >= 750 && !this.userStats.isAchieved("CONQ_6", false)) {
         Gdx.app.log("AoC", "CONQ_6");
         this.userStats.setAchievement("CONQ_6");
         this.userStats.storeStats();
      }

      if (iScore >= 500 && !this.userStats.isAchieved("CONQ_5", false)) {
         Gdx.app.log("AoC", "CONQ_5");
         this.userStats.setAchievement("CONQ_5");
         this.userStats.storeStats();
      }

      if (iScore >= 300 && !this.userStats.isAchieved("CONQ_4", false)) {
         Gdx.app.log("AoC", "CONQ_4");
         this.userStats.setAchievement("CONQ_4");
         this.userStats.storeStats();
      }

      if (iScore >= 150 && !this.userStats.isAchieved("CONQ_3", false)) {
         Gdx.app.log("AoC", "CONQ_3");
         this.userStats.setAchievement("CONQ_3");
         this.userStats.storeStats();
      }

      if (iScore >= 75 && !this.userStats.isAchieved("CONQ_2", false)) {
         Gdx.app.log("AoC", "CONQ_2");
         this.userStats.setAchievement("CONQ_2");
         this.userStats.storeStats();
      }

      if (iScore >= 30 && !this.userStats.isAchieved("CONQ_1", false)) {
         Gdx.app.log("AoC", "CONQ_1");
         this.userStats.setAchievement("CONQ_1");
         this.userStats.storeStats();
      }

      if (iScore >= 10 && !this.userStats.isAchieved("CONQ_0", false)) {
         Gdx.app.log("AoC", "CONQ_0");
         this.userStats.setAchievement("CONQ_0");
         this.userStats.storeStats();
      }
   }

   protected Steam_Game() {
      if (CFG.isDesktop()) {
         if (SteamAPI.isSteamRunning()) {
            Gdx.app.log("AoC", "isSteamRunning: true");
            System.out.println("Register user ...");
            this.user = new SteamUser(new SteamUserCallback() {
               @Override
               public void onValidateAuthTicket(SteamID steamID, SteamAuth.AuthSessionResponse authSessionResponse, SteamID ownerSteamID) {
               }

               @Override
               public void onMicroTxnAuthorization(int appID, long orderID, boolean authorized) {
               }

               @Override
               public void onEncryptedAppTicket(SteamResult result) {
               }
            });
            System.out.println("Register user stats callback ...");
            this.userStats = new SteamUserStats(
               new SteamUserStatsCallback() {
                  @Override
                  public void onUserStatsReceived(long gameId, SteamID steamIDUser, SteamResult result) {
                     Gdx.app.log("AoC", "User stats received: gameId=" + gameId + ", userId=" + steamIDUser + ", result=" + result.toString());
                     int numAchievements = Steam_Game.this.userStats.getNumAchievements();
                     Gdx.app.log("AoC", "Num of achievements: " + numAchievements);
   
                     for(int i = 0; i < numAchievements; ++i) {
                        String name = Steam_Game.this.userStats.getAchievementName(i);
                        boolean achieved = Steam_Game.this.userStats.isAchieved(name, false);
                        Gdx.app.log("AoC", "# " + i + " : name=" + name + ", achieved=" + (achieved ? "yes" : "no"));
                     }
   
                     Steam_Game.this.S_VASS = Steam_Game.this.userStats.getStatI("S_VASS", 0);
                     Gdx.app.log("AoC", "S_VASS: " + Steam_Game.this.S_VASS);
                     Steam_Game.this.S_UNIO = Steam_Game.this.userStats.getStatI("S_UNIO", 0);
                     Gdx.app.log("AoC", "S_UNIO: " + Steam_Game.this.S_UNIO);
                     Steam_Game.this.S_ALLI = Steam_Game.this.userStats.getStatI("S_ALLI", 0);
                     Gdx.app.log("AoC", "S_ALLI: " + Steam_Game.this.S_ALLI);
                  }
   
                  @Override
                  public void onUserStatsStored(long gameId, SteamResult result) {
                     Gdx.app.log("AoC", "onUserStatsStored");
                  }
   
                  @Override
                  public void onUserStatsUnloaded(SteamID steamIDUser) {
                     Gdx.app.log("AoC", "onUserStatsUnloaded");
                  }
   
                  @Override
                  public void onUserAchievementStored(long gameId, boolean isGroupAchievement, String achievementName, int curProgress, int maxProgress) {
                     Gdx.app.log("AoC", "onUserAchievementStored");
                  }
   
                  @Override
                  public void onLeaderboardFindResult(SteamLeaderboardHandle leaderboard, boolean found) {
                     Gdx.app.log("AoC", "Leaderboard find result: handle=" + leaderboard.toString() + ", found=" + (found ? "yes" : "no"));
                     if (found) {
                        Gdx.app
                           .log(
                              "AoC",
                              "Leaderboard: name="
                                 + Steam_Game.this.userStats.getLeaderboardName(leaderboard)
                                 + ", entries="
                                 + Steam_Game.this.userStats.getLeaderboardEntryCount(leaderboard)
                           );
                        Steam_Game.this.currentLeaderboard = leaderboard;
                        Steam_Game.this.userStats
                           .downloadLeaderboardEntriesForUsers(Steam_Game.this.currentLeaderboard, new SteamID[]{Steam_Game.this.user.getSteamID()});
                     } else {
                        Steam_Game.this.currentLeaderboard = leaderboard;
                        Steam_Game.this.userStats
                           .uploadLeaderboardScore(Steam_Game.this.currentLeaderboard, SteamUserStats.LeaderboardUploadScoreMethod.KeepBest, 1, null);
                     }
                  }
   
                  @Override
                  public void onLeaderboardScoresDownloaded(SteamLeaderboardHandle leaderboard, SteamLeaderboardEntriesHandle entries, int numEntries) {
                     Gdx.app
                        .log(
                           "AoC",
                           "Leaderboard scores downloaded: handle=" + leaderboard.toString() + ", entries=" + entries.toString() + ", count=" + numEntries
                        );
                     if (numEntries == 0) {
                        Steam_Game.this.userStats
                           .uploadLeaderboardScore(Steam_Game.this.currentLeaderboard, SteamUserStats.LeaderboardUploadScoreMethod.KeepBest, 1, null);
                     } else {
                        for(int i = 0; i < numEntries; ++i) {
                           SteamLeaderboardEntry entry = new SteamLeaderboardEntry();
                           if (Steam_Game.this.userStats.getDownloadedLeaderboardEntry(entries, i, entry, null)) {
                              Gdx.app
                                 .log(
                                    "AoC",
                                    "Leaderboard entry #"
                                       + i
                                       + ": steamIDUser="
                                       + entry.getSteamIDUser().getAccountID()
                                       + ", globalRank="
                                       + entry.getGlobalRank()
                                       + ", score="
                                       + entry.getScore()
                                 );
                              Steam_Game.iScore = Math.max(Steam_Game.iScore + 1, entry.getScore() + 1);
                              Steam_Game.this.userStats
                                 .uploadLeaderboardScore(
                                    Steam_Game.this.currentLeaderboard, SteamUserStats.LeaderboardUploadScoreMethod.KeepBest, Steam_Game.iScore, null
                                 );
                           }
                        }
                     }
                  }
   
                  @Override
                  public void onLeaderboardScoreUploaded(
                     boolean success, SteamLeaderboardHandle leaderboard, int score, boolean scoreChanged, int globalRankNew, int globalRankPrevious
                  ) {
                     Gdx.app
                        .log(
                           "AoC",
                           "Leaderboard score uploaded: "
                              + (success ? "yes" : "no")
                              + ", handle="
                              + leaderboard.toString()
                              + ", score="
                              + score
                              + ", changed="
                              + (scoreChanged ? "yes" : "no")
                              + ", globalRankNew="
                              + globalRankNew
                              + ", globalRankPrevious="
                              + globalRankPrevious
                        );
                     Steam_Game.this.userStats.storeStats();
                  }
   
                  @Override
                  public void onGlobalStatsReceived(long gameId, SteamResult result) {
                     Gdx.app.log("AoC", "onGlobalStatsReceived");
                  }
               }
            );
            this.remoteStorage = new SteamRemoteStorage(new SteamRemoteStorageCallback() {
               @Override
               public void onFileShareResult(SteamUGCHandle fileHandle, String fileName, SteamResult result) {
               }

               @Override
               public void onDownloadUGCResult(SteamUGCHandle fileHandle, SteamResult result) {
               }

               @Override
               public void onPublishFileResult(SteamPublishedFileID publishedFileID, boolean needsToAcceptWLA, SteamResult result) {
               }

               @Override
               public void onUpdatePublishedFileResult(SteamPublishedFileID publishedFileID, boolean needsToAcceptWLA, SteamResult result) {
               }

               @Override
               public void onPublishedFileSubscribed(SteamPublishedFileID publishedFileID, int appID) {
               }

               @Override
               public void onPublishedFileUnsubscribed(SteamPublishedFileID publishedFileID, int appID) {
               }

               @Override
               public void onPublishedFileDeleted(SteamPublishedFileID publishedFileID, int appID) {
               }

               @Override
               public void onFileWriteAsyncComplete(SteamResult result) {
               }

               @Override
               public void onFileReadAsyncComplete(SteamAPICall fileReadAsync, SteamResult result, int offset, int read) {
               }
            });
            this.ugc = new SteamUGC(
               new SteamUGCCallback() {
                  @Override
                  public void onUGCQueryCompleted(
                     SteamUGCQuery query, int numResultsReturned, int totalMatchingResults, boolean isCachedData, SteamResult result
                  ) {
                  }
   
                  @Override
                  public void onSubscribeItem(SteamPublishedFileID publishedFileID, SteamResult result) {
                  }
   
                  @Override
                  public void onUnsubscribeItem(SteamPublishedFileID publishedFileID, SteamResult result) {
                  }
   
                  @Override
                  public void onRequestUGCDetails(SteamUGCDetails details, SteamResult result) {
                  }
   
                  @Override
                  public void onCreateItem(SteamPublishedFileID publishedFileID, boolean needsToAcceptWLA, SteamResult result) {
                  }
   
                  @Override
                  public void onSubmitItemUpdate(SteamPublishedFileID publishedFileID, boolean needsToAcceptWLA, SteamResult result) {
                  }
   
                  @Override
                  public void onDownloadItemResult(int appID, SteamPublishedFileID publishedFileID, SteamResult result) {
                  }
   
                  @Override
                  public void onUserFavoriteItemsListChanged(SteamPublishedFileID publishedFileID, boolean wasAddRequest, SteamResult result) {
                  }
   
                  @Override
                  public void onSetUserItemVote(SteamPublishedFileID publishedFileID, boolean voteUp, SteamResult result) {
                  }
   
                  @Override
                  public void onGetUserItemVote(
                     SteamPublishedFileID publishedFileID, boolean votedUp, boolean votedDown, boolean voteSkipped, SteamResult result
                  ) {
                  }
   
                  @Override
                  public void onStartPlaytimeTracking(SteamResult result) {
                  }
   
                  @Override
                  public void onStopPlaytimeTracking(SteamResult result) {
                  }
   
                  @Override
                  public void onStopPlaytimeTrackingForAllItems(SteamResult result) {
                  }
   
                  @Override
                  public void onDeleteItem(SteamPublishedFileID publishedFileID, SteamResult result) {
                  }
               }
            );
            this.apps = new SteamApps();
            this.friends = new SteamFriends(new SteamFriendsCallback() {
               @Override
               public void onSetPersonaNameResponse(boolean success, boolean localSuccess, SteamResult result) {
               }

               @Override
               public void onPersonaStateChange(SteamID steamID, SteamFriends.PersonaChange change) {
               }

               @Override
               public void onGameOverlayActivated(boolean active) {
               }

               @Override
               public void onGameLobbyJoinRequested(SteamID steamIDLobby, SteamID steamIDFriend) {
               }

               @Override
               public void onAvatarImageLoaded(SteamID steamID, int image, int width, int height) {
               }

               @Override
               public void onFriendRichPresenceUpdate(SteamID steamIDFriend, int appID) {
               }

               @Override
               public void onGameRichPresenceJoinRequested(SteamID steamIDFriend, String connect) {
               }

               @Override
               public void onGameServerChangeRequested(String server, String password) {
               }
            });
            this.userStats.requestCurrentStats();
         } else {
            Gdx.app.log("AoC", "isSteamRunning: false");
         }
      }
   }
}
