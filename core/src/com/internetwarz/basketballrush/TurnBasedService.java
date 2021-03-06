package com.internetwarz.basketballrush;


import com.internetwarz.basketballrush.model.PlayerTurn;

import java.util.ArrayList;
import java.util.List;

public abstract class TurnBasedService {

    // we can interrupt match and then return to it, since its turn-based game:)
    //
    protected boolean gameStartedLocally = false;


    public boolean isGameStartedLocally() {
        return gameStartedLocally;
    }

    public TurnBasedCallBacks coreGameplayCallBacks = null;
    public abstract void onQuickMatchClicked();
    public abstract void onStartMatchClicked();


    // Cancel the game. Should possibly wait until the game is canceled before
    // giving up on the view.
    public abstract void onCancelClicked();

    public abstract void onLeaveClicked();
    public abstract void onFinishClicked();
    public abstract void showMatchMakingLobby();


    // Upload your new gamestate, then take a turn, and pass it on to the next
    // player.
    /**
     * @param selectedNumber - number that player selects durinf turn
     */
    public abstract void onDoneClicked(int selectedNumber);




    /*
     * Callbacks for interacting with core libgdx from Android or desctop module
     */
    public static class TurnBasedCallBacks {

         private  List<PlayerDataAction> onMatchStartedCallbacks = new ArrayList<>();
         private  List<VoidAction> onMatchLeftCallbacks = new ArrayList<>();
         private  List<PlayerDataAction> onEnemyTurnCallbacks = new ArrayList<>();

            public void addMatchStartedCallback(PlayerDataAction callback)
            {
                onMatchStartedCallbacks.add(callback);
            }

        public void addMatchLeftCallback(VoidAction callback)
        {
            onMatchLeftCallbacks.add(callback);
        }

         public void addEnemyTurnFinishedCallback(PlayerDataAction callback)
         {
             onEnemyTurnCallbacks.add(callback);
         }

        public void fireMatchStartedEvent(PlayerTurn param)
        {
            for (PlayerDataAction a : onMatchStartedCallbacks) a.Action(param);
        }

        public void fireMatchLeftEvent()
        {
            for (VoidAction a : onMatchLeftCallbacks) a.Action();
        }

        void fireEnemyTurnFinishedEvent(PlayerTurn param)
        {
            for (PlayerDataAction a : onEnemyTurnCallbacks) a.Action(param);
        }

    }

    interface VoidAction{

         void Action();
    }

    interface PlayerDataAction {

         void Action(PlayerTurn param);
    }
}
