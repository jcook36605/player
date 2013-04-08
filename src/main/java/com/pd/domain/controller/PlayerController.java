package com.pd.domain.controller;

import com.pd.domain.bean.PlayerService;
import com.pd.domain.bean.ShotService;
import com.pd.domain.manager.PlayerManager;
import com.pd.domain.model.*;
import com.pd.domain.util.EJBLookUp;
import com.pd.domain.web.PlayerProfile;


import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: gy6258
 * Date: 2/12/13
 * Time: 3:25 PM
 * To change this template use File | Settings | File Templates.
 */

@ManagedBean
@SessionScoped
public class PlayerController implements Serializable {

    @Inject
    private Logger log;

    @Inject
    private EntityManager em;

    private PlayerProfile playerProfile;

    private List<Player> players;
    private List<PlayerShot> playerShots;

    private List<Task> shotTasks = null;
    private List<Task> allTasks = null;

    private List<Recommendation> playerRecommendations;
    private List<Recommendation> shotRecommendations;
    private List<Recommendation> allRecommendations;

    private List<ShotTask> playerShotTasks;
    private ShotTask selectedShotTask;

    private PlayerManager playerManager;


    @PostConstruct
    public void setUp(){
        System.out.println("PlayerController.setUp");
        playerManager = new PlayerManager();
        searchPlayers();

    }
    public void addNote(){
        System.out.println("PlayerController.addNote");
        Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String text = params.get("note");

        Note note = new Note();
        note.setCreatedDate(new Date());
        note.setText(text);

        ShotTask shotTask = playerProfile.getShotTask();
        shotTask.getNoteList().add(note);

        ShotService shotService = new EJBLookUp().lookUpShotService();
        shotService.addNoteToTask(shotTask, note);
    }

    public String previous(String page){
        return page;
    }

    public String selectPlayer(long playerId) throws Exception {
        log.info("selectPlayer.............................. "+playerId);
        PlayerService playerService = new EJBLookUp().lookUpPlayerService();
        playerService.syncPlayerWithGroups(playerId);
        playerShots = playerService.findPlayerShots(playerId);
        Player player = playerService.find(playerId);
        playerProfile = new PlayerProfile(player);

        playerManager = new PlayerManager();

        return "player.jsf";
    }

    public String savePlayer(){
        System.out.println("PlayerController.savePlayer");
        PlayerService playerService = new EJBLookUp().lookUpPlayerService();
        Player player = new Player();
        player.setFirstName(playerManager.getFirstName());
        player.setLastName(playerManager.getLastName());
        player.setAddress(playerManager.getAddress());
        player.setCity(playerManager.getCity());
        playerService.save(player);
        playerManager.reset();
        playerProfile = new PlayerProfile(player);
        playerShots = new ArrayList<PlayerShot>();
        players = new EJBLookUp().lookUpPlayerService().findAll(0, "myname");
        return "playerResults";
    }

    public String updatePlayer(){
        System.out.println("PlayerController.updatePlayer");
        PlayerService playerService = new EJBLookUp().lookUpPlayerService();
        Player player = playerProfile.getPlayer();
        playerService.save(player);
        playerManager.reset();
        return "player";
    }


      public String addShot(Shot shot){
         boolean saveIt = true;
         for (PlayerShot playerShot : playerShots) {
             if(playerShot.getShotID().getId() == shot.getId()){
                 saveIt = false;
                 break;
              }
         }
         if(saveIt){
             PlayerService playerService = new EJBLookUp().lookUpPlayerService();
             PlayerShot playerShot = playerService.addShot(playerProfile.getPlayer(), shot);
             playerShots.add(playerShot);
         }
         return "player";
     }

    public String addTaskToShot(Task task){
        System.out.println("PlayerController.addTaskToShot" + task );
        PlayerShot playerShot = playerProfile.getPlayerShot();

        if(playerShot.getShotTaskList() == null){
            playerShot.setShotTaskList(new ArrayList<ShotTask>());
        }

        ShotTask shotTask = new ShotTask();
        shotTask.setCreateDate(new Date());
        shotTask.setPlayerShotID(playerShot);
        shotTask.setTaskID(task);

        ShotService shotService = new EJBLookUp().lookUpShotService();
        shotService.save(shotTask);

        playerShot.getShotTaskList().add(shotTask);

        Task removeTask=null;
        for (Task sTasks : shotTasks) {
            if(sTasks.getName().equalsIgnoreCase(shotTask.getTaskID().getName())){
                removeTask = sTasks;
                break;
            }
        }

        if(removeTask != null){
           shotTasks.remove(removeTask);
        }

        return "playerShot";
    }

    public String selectShotTask(ShotTask shotTask){
        System.out.println("PlayerController.selectShotTask  "+shotTask);
        System.out.println("PS: " + shotTask.getPlayerShotID().getShotID());
        ShotService shotService = new EJBLookUp().lookUpShotService();
        shotTask = shotService.loadShotTask(shotTask.getId());
        playerProfile.setShotTask(shotTask);


        allRecommendations = shotService.findAllRecommendations();

        playerRecommendations = shotTask.getRecommendationList();
        if(playerRecommendations == null){
            playerRecommendations = new ArrayList<Recommendation>();
        }

        Shot shot = shotService.loadShot(playerProfile.getPlayerShot().getShotID().getId());
        shotRecommendations = shot.getRecommendationList();
        if(shotRecommendations == null){
            shotRecommendations = new ArrayList<Recommendation>();
        }

        removeSelected();

        return "playerShotTask";
    }

    public String addRecommendationToShots(Recommendation rec){
        System.out.println("PlayerController.addRecommendationToShots   "+rec);
        ShotService shotService = new EJBLookUp().lookUpShotService();
        shotService.addRecommendation(playerProfile.getShotTask(), rec);
        playerRecommendations.add(rec);
        shotRecommendations.remove(rec);
        return "playerShotTask";
    }


    public String selectPlayerShot(PlayerShot ps){
        System.out.println("PlayerController.selectPlayerShot  "+ps);
        ShotService shotService = new EJBLookUp().lookUpShotService();
        PlayerShot playerShot = shotService.loadPlayerShot(ps.getId());


        allTasks = shotService.findAllTasks();

        playerShotTasks = playerShot.getShotTaskList();

        playerProfile.setPlayerShot(playerShot);


        ShotType shotType = shotService.loadShotType(playerShot.getShotID().getShotTypeID().getId());


        shotTasks = new ArrayList<Task>();
        List<Task> shotTypeTasks = shotType.getTaskList();
        for (Task foundShotTask : shotTypeTasks) {
            boolean hasTask = hasTask(foundShotTask);

            if(!hasTask) {
                shotTasks.add(foundShotTask);
            }
        }
        return "playerShot";
    }




    private boolean hasTask(Task foundShotTask) {
        boolean hasTask = false;
        for (ShotTask playerTask : playerShotTasks) {
             if(playerTask.getTaskID().getName().equalsIgnoreCase(foundShotTask.getName())){
                 hasTask = true;
                 break;
             }
        }
        return hasTask;
    }

    public String searchPlayers() {
        log.info("searchPlayers.............................. ");

        players = new EJBLookUp().lookUpPlayerService().findAll(0, "myname");

        return "playerResults";
    }

    public PlayerProfile getPlayerProfile() {
        return playerProfile;
    }

    public void setPlayerProfile(PlayerProfile playerProfile) {
        this.playerProfile = playerProfile;
    }

    public List<ShotTask> getPlayerShotTasks() {
        return playerShotTasks;
    }

    public void setPlayerShotTasks(List<ShotTask> playerShotTasks) {
        this.playerShotTasks = playerShotTasks;
    }

    public List<PlayerShot> getPlayerShots() {
        return playerShots;
    }

    public void setPlayerShots(List<PlayerShot> playerShots) {
        this.playerShots = playerShots;
    }

    public List<Task> getShotTasks() {
        return shotTasks;
    }

    public void setShotTasks(List<Task> shotTasks) {
        this.shotTasks = shotTasks;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<Recommendation> getPlayerRecommendations() {
        return playerRecommendations;
    }

    public void setPlayerRecommendations(List<Recommendation> playerRecommendations) {
        this.playerRecommendations = playerRecommendations;
    }

    public List<Recommendation> getShotRecommendations() {
        return shotRecommendations;
    }

    public void setShotRecommendations(List<Recommendation> shotRecommendations) {
        this.shotRecommendations = shotRecommendations;
    }

    public List<Task> getAllTasks() {
        return allTasks;
    }

    public void setAllTasks(List<Task> allTasks) {
        this.allTasks = allTasks;
    }

    public List<Recommendation> getAllRecommendations() {
        return allRecommendations;
    }

    public void setAllRecommendations(List<Recommendation> allRecommendations) {
        this.allRecommendations = allRecommendations;
    }

    public ShotTask getSelectedShotTask() {
        return selectedShotTask;
    }

    public void setSelectedShotTask(ShotTask selectedShotTask) {
        this.selectedShotTask = selectedShotTask;
    }

    public PlayerManager getPlayerManager() {
        return playerManager;
    }

    public void setPlayerManager(PlayerManager playerManager) {
        this.playerManager = playerManager;
    }

    private void removeSelected() {
        List<Recommendation> removeSelected = new ArrayList<Recommendation>();
        for (Recommendation playerRecommendation : playerRecommendations) {
            for (Recommendation shotRecommendation : shotRecommendations) {
                if(shotRecommendation.getName().equalsIgnoreCase(playerRecommendation.getName())){
                    removeSelected.add(shotRecommendation);
                }
            }
        }
        shotRecommendations.removeAll(removeSelected);
    }
}
