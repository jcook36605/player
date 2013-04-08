package com.pd.domain.web;

import com.pd.domain.model.Player;
import com.pd.domain.model.PlayerShot;
import com.pd.domain.model.ShotTask;
import com.pd.domain.model.Task;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: gy6258
 * Date: 2/20/13
 * Time: 9:40 AM
 * To change this template use File | Settings | File Templates.
 */
public class PlayerProfile implements Serializable {

    private PlayerShot playerShot;
    private ShotTask shotTask;
    private Player player;

   /* private Task task;
*/


    public PlayerProfile(Player playerId) {
        this.player = playerId;
    }

    /*public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }*/

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public PlayerShot getPlayerShot() {
        return playerShot;
    }

    public void setPlayerShot(PlayerShot playerShot) {
        this.playerShot = playerShot;
    }

    public ShotTask getShotTask() {
        return shotTask;
    }

    public void setShotTask(ShotTask shotTask) {
        shotTask.setPlayerShotID(playerShot);
        this.shotTask = shotTask;
        if(this.shotTask.getRecommendationList() == null){
            this.shotTask.setRecommendationList(new ArrayList());
        }
    }


}
