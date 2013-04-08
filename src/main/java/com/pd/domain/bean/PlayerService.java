package com.pd.domain.bean;

import com.pd.domain.model.*;

import javax.ejb.Remote;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: gy6258
 * Date: 2/13/13
 * Time: 1:01 PM
 * To change this template use File | Settings | File Templates.
 */
@Remote
public interface PlayerService {

    public List<Player> findAll(long id, String cmd);

    public List<SessionGroup> findAll();

    public List<PlayerShot> findPlayerShots(long playerId, long shotId);

    public List<PlayerShot> findPlayerShots(long playerId);

    public void syncPlayerWithGroups(Long playerId);

   public SessionGroup addGroup(String name, String des);

    public void save(SessionGroup group);

    public void save(SessionGroup group, List<String> players);

    public void save(GroupShot gs);

    public void delete(SessionGroup group);

    public PlayerShot addShot(Player player, Shot shot);

    public SessionGroup load(Long id);

    public Player find(long id);

    public void save(Player p);
}
