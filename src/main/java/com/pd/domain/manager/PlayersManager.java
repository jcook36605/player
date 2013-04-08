package com.pd.domain.manager;

import com.pd.domain.model.Player;

import javax.faces.model.SelectItem;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: gy6258
 * Date: 3/8/13
 * Time: 1:19 PM
 * To change this template use File | Settings | File Templates.
 */
public class PlayersManager implements Serializable {
    private List<String> playerIds;
    private List<SelectItem> players;
    private Long[] selectedPlayers;

    public PlayersManager(List<Player> ps){
        players = new ArrayList<SelectItem>();
        for (Player p : ps) {
            players.add(new SelectItem(p.getId(), p.getFirstName()+" "+p.getLastName()));
        }
    }

    public Long[] getSelectedPlayers() {
        return selectedPlayers;
    }

    public void setSelectedPlayers(Long[] selectedPlayers) {
        this.selectedPlayers = selectedPlayers;
    }

    public List<SelectItem> getPlayers() {
        return players;
    }

    public void setPlayers(List<SelectItem> players) {
        this.players = players;
    }

    public List<String> getPlayerIds() {
        return playerIds;
    }

    public void setPlayerIds(List<String> playerIds) {
        this.playerIds = playerIds;
    }
}
