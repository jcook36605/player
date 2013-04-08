package com.pd.domain.bean;

import com.pd.domain.model.*;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: gy6258
 * Date: 2/13/13
 * Time: 1:00 PM
 * To change this template use File | Settings | File Templates.
 */
@Stateless(name="PlayerBean")
@Remote(PlayerService.class)
public class PlayerBean implements PlayerService {

    @Inject
    private EntityManager em;

    @Override
    public Player find(long id) {
        Player player = em.find(Player.class, id);
        return player;
    }

    @Override
    public void save(Player p) {
        if(p.getId() != null){
            Player player = em.find(Player.class, p.getId());
            player.setFirstName(p.getFirstName());
            player.setLastName(p.getLastName());
            em.persist(player);
        }
        else {
            em.persist(p);
        }
        System.out.println("PlayerBean.save    "+p.getId());
    }

    @Override
    public List<Player> findAll(long id, String cmd) {
        System.out.println("PlayerBean.findAll");
        List<Player> players = new ArrayList<Player>();
        Query query = em.createNamedQuery("Player.findAll");
        players.addAll(query.getResultList());
        for (Player player : players) {
            System.out.println("Player: "+player.getFirstName());
        }
        return players;
    }

    @Override
    public List<PlayerShot> findPlayerShots(long playerId) {
        Query query = em.createNamedQuery("PlayerShot.findPlayer");
        query.setParameter("playerId", playerId);
        //em.
        return query.getResultList();  //To change body of implemented methods use File | Settings | File Templates.
    }


    @Override
    public List<PlayerShot> findPlayerShots(long playerId, long shotId) {
        Query query = em.createNamedQuery("PlayerShot.findPlayerId");
        query.setParameter("playerId", playerId);
        query.setParameter("shotId", shotId);
        return query.getResultList();
    }

    public void syncPlayerWithGroups(Long playerId){
        List<SessionGroup> groups = findAll(playerId);
        Player player = em.find(Player.class, playerId);
        for (SessionGroup group : groups) {
            List<GroupShot> shots = group.getGroupShotList();
            for (GroupShot groupShot : shots) {
                addShot(player, groupShot);
            }
        }
    }


    private List<SessionGroup> findAll(Long playerId) {
        Player player = em.find(Player.class, playerId);
        List<SessionGroup> groups = new ArrayList<SessionGroup>();

        if(player.getSessionGroupList().size() > 0){

            List<SessionGroup> gps = player.getSessionGroupList();
            for (SessionGroup gp : gps) {
                if(gp.getGroupShotList().size() > 0){
                    groups.add(gp);
                }
            }

        }
        return groups;
    }

    @Override
    public List<SessionGroup> findAll() {
        Query query = em.createNamedQuery("SessionGroup.findAll");
        return query.getResultList();
    }

    @Override
    public SessionGroup load(Long id) {
        SessionGroup group = em.find(SessionGroup.class, id);
        group.getPlayerList().size();
        group.getGroupShotList().size();
        return group;
    }

    @Override
    public void save(SessionGroup group) {
        SessionGroup found = em.find(SessionGroup.class, group.getId());

        if(group.getCreateDate() == null){
            found.setCreateDate(new Date());
        }
        found.setDescription(group.getDescription());
        found.setName(group.getName());
        em.persist(found);
    }


    @Override
    public void save(SessionGroup gp, List<String> ids) {
        List<Player> players = new ArrayList<Player>();
        for (String id : ids) {
            Player p = em.find(Player.class, Long.valueOf(id));
            players.add(p);
        }
        SessionGroup group = em.find(SessionGroup.class, gp.getId());
        group.setPlayerList(players);
        em.persist(group);
    }

    @Override
    public void save(GroupShot gs) {
        SessionGroup sg = em.find(SessionGroup.class, gs.getGroupID().getId());
        gs.setGroupID(sg);

    }

    @Override
    public void delete(SessionGroup group) {
        SessionGroup sg = em.find(SessionGroup.class, group.getId());
        em.remove(sg);
    }

    @Override
    public PlayerShot addShot(Player player, Shot shot) {
            Shot foundShot = em.find(Shot.class, shot.getId());
            Player foundPlayer = em.find(Player.class, player.getId());
            PlayerShot playerShot = new PlayerShot();
            playerShot.setPlayerID(foundPlayer);
            playerShot.setShotID(foundShot);
            em.persist(playerShot);
            foundShot.getPlayerShotList().add(playerShot);
            em.persist(foundPlayer);
            em.persist(foundShot);
            return playerShot;
    }

    @Override
    public SessionGroup addGroup(String name, String des) {
        SessionGroup group = new SessionGroup();
        group.setName(name);
        group.setDescription(des);
        group.setCreateDate(new Date());
        em.persist(group);
        return group;
    }


    private void addShot(Player player, GroupShot shot) {
        List<PlayerShot> playerShots = findPlayerShots(player.getId());
        GroupShot groupShot = em.find(GroupShot.class, shot.getId());
        if(playerShots.isEmpty()){
            addShot(player, groupShot.getShotID());
        }
        else {
            for (PlayerShot playerShot : playerShots) {
                List<GroupShotTask> groupShotTasks = groupShot.getGroupShotTaskList();
                for (GroupShotTask groupShotTask : groupShotTasks) {

                    Task task = groupShotTask.getTaskID();
                    if(playerShot.getShotID().getId() == groupShot.getShotID().getId()){
                        PlayerShot foundShot = em.find(PlayerShot.class, playerShot.getId());
                        boolean alreadyHadTask = false;
                        List<ShotTask> shotTaskList = foundShot.getShotTaskList();
                        for (ShotTask shotTaskFound : shotTaskList) {
                            if(shotTaskFound.getTaskID().getId() == task.getId()){
                                alreadyHadTask = true;
                                break;
                            }
                        }
                        if(!alreadyHadTask){
                            ShotTask shotTask = new ShotTask();
                            shotTask.setCreateDate(new Date());
                            shotTask.setPlayerShotID(playerShot);
                            shotTask.setTaskID(task);
                            em.persist(shotTask);
                        }
                    }
                    else {
                        boolean hasTask = false;
                        List<ShotTask> list = playerShot.getShotTaskList();
                        for (ShotTask shotTask : list) {
                            if(shotTask.getTaskID().getId() == task.getId()){
                                hasTask = true;
                                break;
                            }
                        }

                        if(!hasTask){
                            ShotTask shotTask = new ShotTask();
                            shotTask.setCreateDate(new Date());
                            shotTask.setPlayerShotID(playerShot);
                            shotTask.setTaskID(task);
                            em.persist(shotTask);
                        }

                    }
                }
            }
        }
    }

}
