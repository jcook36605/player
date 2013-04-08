package com.pd.domain.controller;

import com.pd.domain.bean.PlayerService;
import com.pd.domain.bean.ShotService;
import com.pd.domain.manager.GroupManager;
import com.pd.domain.manager.PlayersManager;
import com.pd.domain.model.*;
import com.pd.domain.util.EJBLookUp;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: gy6258
 * Date: 3/8/13
 * Time: 1:14 PM
 * To change this template use File | Settings | File Templates.
 */
@ManagedBean
@SessionScoped
public class SessionController implements Serializable {
    private List<Player> players;
    private GroupManager groupManager;
    private PlayersManager playersManager;

    private List<SessionGroup> groups;
    private List<GroupShotTask> groupShotTasks;
    private List<Task> allTasks;


    @PostConstruct
    public void setUp(){
        System.out.println("SessionController.setUp");
        PlayerService playerService = new EJBLookUp().lookUpPlayerService();
        groups = playerService.findAll();
        players = playerService.findAll(0, null);
        groupManager = new GroupManager(groups);
        playersManager = new PlayersManager(players);
    }



    public String loadGroup(Long id){
        PlayerService playerService = new EJBLookUp().lookUpPlayerService();
        SessionGroup sessionGroup = playerService.load(id);
        System.out.println("SHOTS SELECTED: "+sessionGroup.getGroupShotList());
        groupManager.setGroup(sessionGroup);
        List<Player> playerList = sessionGroup.getPlayerList();
        List<String> selectedPlayers = new ArrayList<String>();
        for (Player player : playerList) {
            selectedPlayers.add(String.valueOf(player.getId()));
        }
        playersManager.setPlayerIds(selectedPlayers);
        return "group";
    }

    public void addTask(Task t){
        GroupShot groupShot = groupManager.getGroupShot();
        ShotService shotService = new EJBLookUp().lookUpShotService();
        GroupShotTask groupShotTask = shotService.addTask(groupShot, t);
        groupShot.getGroupShotTaskList().add(groupShotTask);
    }

    public String selectGroupShot(GroupShot gs){
        System.out.println("PlayerController.groupShot  "+gs);
        SessionGroup sessionGroup = groupManager.getGroup();
        loadGroup(sessionGroup.getId());
        ShotService shotService = new EJBLookUp().lookUpShotService();
        GroupShot groupShot = shotService.loadGroupShot(gs.getId());
        ShotType shotType = shotService.loadShotType(groupShot.getShotID().getShotTypeID().getId());
        groupShot.getShotID().setShotTypeID(shotType);
        groupManager.setGroupShot(groupShot);


        allTasks = shotService.findAllTasks();

        groupShotTasks = groupShot.getGroupShotTaskList();


        return "groupShot";
    }

    public void addShot(Shot shot){

            boolean saveIt = true;
            for (GroupShot groupShot : groupManager.getGroup().getGroupShotList()) {
                if(groupShot.getShotID().getId() == shot.getId()){
                    saveIt = false;
                    break;
                }
            }
            if(saveIt){
                SessionGroup sessionGroup = groupManager.getGroup();
                ShotService shotService = new EJBLookUp().lookUpShotService();
                GroupShot groupShot = shotService.addShot(sessionGroup, shot);
                sessionGroup.getGroupShotList().add(groupShot);
            }

    }

    public String addShotView(){
        System.out.println("SessionController.addShotView");
        groupManager.setGroupShot(null);
        return "groupProfile";
    }


    public String saveGroup(){
        System.out.println("SessionController.saveGroup");
        SessionGroup sessionGroup = groupManager.getGroup();
        PlayerService playerService = new EJBLookUp().lookUpPlayerService();
        playerService.save(sessionGroup, playersManager.getPlayerIds());
        return "group";
    }

    public String deleteGroup(){
        SessionGroup sessionGroup = groupManager.getGroup();
        PlayerService playerService = new EJBLookUp().lookUpPlayerService();
        playerService.delete(sessionGroup);
        groups.remove(sessionGroup);

        return viewGroups();

    }
    public void addGroup(){
        System.out.println("SessionController.addGroup");
        PlayerService playerService = new EJBLookUp().lookUpPlayerService();
        SessionGroup group = playerService.addGroup(groupManager.getName(), groupManager.getDescription());
        groupManager.setName("");
        groupManager.setDescription("");
        groups.add(group);
        groupManager.getGroups().add(new SelectItem(group.getId(), group.getName()));
    }

    public String viewGroups(){
        return "adminGroups";
    }

    public List<SessionGroup> getGroups() {
        return groups;
    }

    public void setGroups(List<SessionGroup> groups) {
        this.groups = groups;
    }

    public GroupManager getGroupManager() {
        return groupManager;
    }

    public void setGroupManager(GroupManager groupManager) {
        this.groupManager = groupManager;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<GroupShotTask> getGroupShotTasks() {
        return groupShotTasks;
    }

    public void setGroupShotTasks(List<GroupShotTask> groupShotTasks) {
        this.groupShotTasks = groupShotTasks;
    }

    public List<Task> getAllTasks() {
        return allTasks;
    }

    public void setAllTasks(List<Task> allTasks) {
        this.allTasks = allTasks;
    }

    public PlayersManager getPlayersManager() {
        return playersManager;
    }

    public void setPlayersManager(PlayersManager playersManager) {
        this.playersManager = playersManager;
    }
}
