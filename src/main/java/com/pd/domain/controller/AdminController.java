package com.pd.domain.controller;

import com.pd.domain.bean.ShotService;
import com.pd.domain.manager.RecommendationManager;
import com.pd.domain.manager.ShotManager;
import com.pd.domain.manager.TaskManager;
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
 * Date: 2/27/13
 * Time: 10:10 AM
 * To change this template use File | Settings | File Templates.
 */
@ManagedBean
@SessionScoped
public class AdminController implements Serializable {

    private ShotManager shotManager;
    private TaskManager taskManager;
    private RecommendationManager recommendationManager;

    private List<ShotType> shotTypes;
    private List<Task> tasks;

    @PostConstruct
    public void setUp(){
        System.out.println("AdminController.setUp");
        shotTypes = new ArrayList<ShotType>();
        ShotService shotService = new EJBLookUp().lookUpShotService();
        List<ShotType> tmpShotTypes = shotService.findAll();
        for (ShotType tmpShotType : tmpShotTypes) {
            if(!tmpShotType.getShotList().isEmpty()){
                shotTypes.add(tmpShotType);
            }
        }

        tasks = shotService.findAllTasks();
        shotManager = new ShotManager(tmpShotTypes);
        taskManager = new TaskManager(tasks);
        recommendationManager  = new RecommendationManager(shotService.findAllRecommendations(), shotService.findShotRecommendations());
    }



    public String addShot(){
        System.out.println("AdminController.addShot");
        ShotService shotService = new EJBLookUp().lookUpShotService();
        Shot shot = shotService.addShot(shotManager.getName(), shotManager.getDescription(), shotManager.getTypeId());
        findType(shotManager.getTypeId()).getShotList().add(shot);
        return viewShots();
    }



    public String addShotType(){
        System.out.println("AdminController.addShotType");
        System.out.println("shotmanager: "+shotManager.getType());
        System.out.println("shotmanager: "+shotManager.getDescription());
        ShotService shotService = new EJBLookUp().lookUpShotService();
        ShotType shotType = shotService.addShotType(shotManager.getType(), shotManager.getDescription());
        shotManager.getShotTypes().add(new SelectItem(shotType.getId(), shotType.getType()));
        shotTypes.add(shotType);
        return viewShots();
    }

    public String loadShot(Shot shot){
        shotManager.setShot(shot);
       return "shot";
   }

    public String deleteShot(){
        System.out.println("AdminController.deleteShot");
         ShotService shotService = new EJBLookUp().lookUpShotService();
         boolean removed = shotService.delete(shotManager.getShot());
         if(removed){
             for (ShotType shotType : shotTypes) {
                 List<Shot> shots = shotType.getShotList();
                 Shot removeShot = null;
                 for (Shot shot : shots) {
                     if(shot.getId() == shotManager.getShot().getId()){
                        removeShot = shot;
                        break;
                     }
                 }
                 if(removeShot != null){
                     shots.remove(removeShot);
                     break;
                 }
             }
         }
         return viewShots();
    }

    public String saveShot(){
        System.out.println("AdminController.saveShot");
        ShotService shotService = new EJBLookUp().lookUpShotService();
        shotService.save(shotManager.getShot());
        return viewShots();
    }

    public String loadTask(Task t){
        taskManager.setTask(t);
        return "task";
    }

    public String deleteTask(){
        System.out.println("AdminController.deleteTask");
        ShotService shotService = new EJBLookUp().lookUpShotService();
        shotService.delete(taskManager.getTask());
        tasks.remove(taskManager.getTask());
        return viewTasks();
    }

    public String addTask(){
        ShotService shotService = new EJBLookUp().lookUpShotService();
        Task task = shotService.addTask(taskManager.getName(), taskManager.getDescription());
        taskManager.setTask(task);
        taskManager.getTasks().add((new SelectItem(task.getId(), task.getName())));
        tasks.add(task);
        return viewTasks();
    }

    public String saveTask(){
        ShotService shotService = new EJBLookUp().lookUpShotService();
        shotService.save(taskManager.getTask());
        return viewTasks();
    }

    public String addRecommendation(){
        Recommendation rec = new Recommendation();
        rec.setDescription(recommendationManager.getDescription());
        rec.setName(recommendationManager.getName());
        ShotService shotService = new EJBLookUp().lookUpShotService();
        shotService.save(rec);
        recommendationManager.getAllRecommendations().add(rec);

        return viewRecommendation();

    }

    public String saveRecommendation(){
        Recommendation rec = recommendationManager.getRecommendation();
        ShotService shotService = new EJBLookUp().lookUpShotService();
        shotService.save(rec);
        return viewRecommendation();
    }

    public String deleteRecommendation(){
        Recommendation rec = recommendationManager.getRecommendation();
        ShotService shotService = new EJBLookUp().lookUpShotService();
        boolean deleted = shotService.delete(rec);
        if(deleted){

        }
        return viewRecommendation();
    }

    public String loadRecommendation(Long id){
        Recommendation rec = recommendationManager.getRecommendation();
        ShotService shotService = new EJBLookUp().lookUpShotService();
        shotService.loadRecommendation(id);
        recommendationManager.setRecommendation(rec);
        return "recommendation";
    }

    public String viewMain(){
        System.out.println("AdminController.viewMain");
        return "admin";
    }

    public String viewShots(){
        System.out.println("AdminController.viewShots");
        return "adminShots";
    }

    public String viewTasks(){
        return "adminTasks";
    }

    public String viewRecommendation(){
        return "adminRecommendation";
    }

    public ShotManager getShotManager() {
        return shotManager;
    }

    public void setShotManager(ShotManager shotManager) {
        this.shotManager = shotManager;
    }

    public List<ShotType> getShotTypes() {
        return shotTypes;
    }

    public void setShotTypes(List<ShotType> shotTypes) {
        this.shotTypes = shotTypes;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskManager getTaskManager() {
        return taskManager;
    }

    public void setTaskManager(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

    public RecommendationManager getRecommendationManager() {
        return recommendationManager;
    }

    public void setRecommendationManager(RecommendationManager recommendationManager) {
        this.recommendationManager = recommendationManager;
    }


    private ShotType findType(long id){
        for (ShotType shotType : shotTypes) {
            if(shotType.getId().longValue() == id)
                return shotType;
        }
        return null;
    }

}
