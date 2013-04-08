package com.pd.domain.bean;

import com.pd.domain.model.*;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: gy6258
 * Date: 2/13/13
 * Time: 2:36 PM
 * To change this template use File | Settings | File Templates.
 */
@Stateless(name="ShotBean")
@Remote(ShotService.class)
public class ShotBean implements ShotService {
    @Inject
    private EntityManager em;


    @Override
    public ShotTask loadShotTask(long shotTaskId) {
        ShotTask shotTask = em.find(ShotTask.class, shotTaskId);
        System.out.println("FOUND: "+shotTask);
        System.out.println("SIZE: " + shotTask.getRecommendationList().size());
        shotTask.getNoteList().size();
        return shotTask;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void addRecommendation(Shot shot, Recommendation rec) {
        Shot foundShot = em.find(Shot.class, shot.getId());
        Recommendation recommendation = em.find(Recommendation.class, rec.getId());

        foundShot.getRecommendationList().add(recommendation);
        recommendation.getShotList().add(foundShot);

        em.persist(foundShot);
        em.persist(recommendation);
    }

    @Override
    public void addRecommendation(ShotTask shotTask, Recommendation rec) {
        ShotTask foundShot = em.find(ShotTask.class, shotTask.getId());
        Recommendation recommendation = em.find(Recommendation.class, rec.getId());

        foundShot.getRecommendationList().add(recommendation);
        recommendation.getShotTaskList().add(foundShot);

        em.persist(foundShot);
        em.persist(recommendation);
    }

    @Override
    public Shot loadShot(long shotId) {
        Shot shot = em.find(Shot.class, shotId);
        shot.getRecommendationList().size();
        return shot;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public ShotType loadShotType(long id) {
        ShotType shotType = em.find(ShotType.class, id);
        shotType.getTaskList().size();
        shotType.getShotList().size();
        return shotType;

    }

    @Override
    public List<ShotTask> loadPlayerShotTask(Shot shot, long playerId) {

        Query query = em.createNamedQuery("ShotTask.findByPlayer");
        query.setParameter("playerId", playerId);
        query.setParameter("shotId", shot.getId());
        List<ShotTask> result = query.getResultList();
        if(result == null ) result = new ArrayList<ShotTask>();

        for (ShotTask shotTask : result) {
            shotTask.getRecommendationList().size();
        }

        return result;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void save(ShotTask st) {
        if(st.getId() == null){
            em.persist(st);
        }
        else
            em.merge(st);

    }

    @Override
    public void save(Recommendation recommendation) {
        if(recommendation.getId() == null){
            em.persist(recommendation);
        }
        else
            em.merge(recommendation);
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void save(PlayerShot ps) {
        System.out.println("ShotBean.save  "+ps);
        if(ps.getId() == null){
            em.persist(ps);
        }
        else
            em.merge(ps);
        //To change body of implemented methods use File | Settings | File Templates.
    }

  /*  @Override
    public PlayerShot findPlayerShot(long shotId) {
        PlayerShot playerShot = em.find(PlayerShot.class, shotId);
        return playerShot;
    }*/

    @Override
    public void save(Shot shot) {
        Shot found = em.find(Shot.class, shot.getId());
        found.setName(shot.getName());
        found.setDescription(shot.getDescription());
        em.persist(found);
    }

    @Override
    public void save(Task task) {
        Task found = em.find(Task.class, task.getId());
        found.setName(task.getName());
        found.setDescription(task.getDescription());
        em.persist(found);
    }


    @Override
    public boolean delete(Shot shot) {
        Shot found = em.find(Shot.class, shot.getId());
        if(found.getPlayerShotList().size() == 0 && found.getRecommendationList().size() == 0){
            em.remove(found);
            return  true;
        }
        return false;
    }

    @Override
    public boolean delete(Recommendation rec) {
        Recommendation found = em.find(Recommendation.class, rec.getId());
        if(found.getShotTaskList().size() == 0 && found.getShotList().size() == 0){
            em.remove(found);
            return  true;
        }
        return false;
    }

    @Override
    public boolean delete(Task t) {
        Task found = em.find(Task.class, t.getId());
        if(found.getShotTaskList().size() == 0 && found.getShotTaskList().size() == 0){
            em.remove(found);
            return  true;
        }
        return false;
    }


    @Override
    public ShotType addShotType(String type, String desc) {
        ShotType shotType = new ShotType();
        shotType.setType(type);
        shotType.setDescription(desc);
        em.persist(shotType);
        return shotType;
    }

    @Override
    public Shot addShot(String name, String desc, long shotId) {
        ShotType type = em.find(ShotType.class, shotId);

        Shot shot = new Shot();
        shot.setShotTypeID(type);
        shot.setName(name);
        shot.setDescription(desc);
        em.persist(shot);

        if(type.getShotList()== null){
            type.setShotList(new ArrayList<Shot>());
        }

        type.getShotList().add(shot);
        em.persist(type);

        return shot;
    }

    @Override
    public Task addTask(String name, String desc) {
        Task task = new Task();
        task.setName(name);
        task.setDescription(desc);
        task.setStartDate(new Date());
        em.persist(task);
        return task;
    }

    @Override
    public PlayerShot loadPlayerShot(long shotId) {
        PlayerShot playerShot = em.find(PlayerShot.class, shotId);
        if(playerShot == null)
            return null;

        playerShot.getShotTaskList().size();
        return playerShot;
    }

    @Override
    public GroupShot loadGroupShot(long shotId) {
        GroupShot groupShot = em.find(GroupShot.class, shotId);
        groupShot.getGroupShotTaskList().size();
        return groupShot;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public GroupShotTask addTask(GroupShot gs, Task t) {
        //
        GroupShot foundShot = em.find(GroupShot.class, gs.getId());
        Task foundTask = em.find(Task.class, t.getId());

        GroupShotTask groupShotTask = new GroupShotTask();
        groupShotTask.setGroupShotID(foundShot);
        groupShotTask.setTaskID(foundTask);

        em.persist(groupShotTask);


        foundShot.getGroupShotTaskList().add(groupShotTask);
        em.persist(foundShot);

        return groupShotTask;
    }

    @Override
    public void addNoteToTask(ShotTask task, Note note) {
        System.out.println("ShotBean.addNoteToTask");


        em.persist(note);
        ShotTask shotTask = em.find(ShotTask.class, task.getId());
        shotTask.getNoteList().add(note);

        if(note.getShotTaskList() == null){
            note.setShotTaskList(new ArrayList<ShotTask>());
        }
        note.getShotTaskList().add(shotTask);

        em.persist(shotTask);


    }




    @Override
    public Recommendation loadRecommendation(Long id) {
        Recommendation rec = em.find(Recommendation.class,id);
        return rec;
    }


    @Override
    public List<Recommendation> findAllRecommendations() {
        System.out.println("ShotBean.findAllRecommendations");
        Query query = em.createNamedQuery("Recommendation.findAll");
        return query.getResultList();
    }

    @Override
    public List<Shot> findShotRecommendations() {
        List<Shot> shots = new ArrayList<Shot>();
        Query query = em.createNamedQuery("Shot.findAll");
        List<Shot> found = query.getResultList();
        for (Shot shot : found) {
            Shot s = em.find(Shot.class, shot.getId());
            s.getRecommendationList().size();
            shots.add(s);
        }

        return shots;
    }

    @Override
    public List<Task> findAllTasks() {
        System.out.println("ShotBean.findAllTasks");
        Query query = em.createNamedQuery("Task.findAll");
        return query.getResultList();
    }

    @Override
    public List<ShotType> findAll() {
        Query query = em.createNamedQuery("ShotType.findAll");
        List<ShotType> shotTypes = query.getResultList();
        System.out.println("ShotBean.findAll  "+shotTypes.size());
        List<ShotType> result = new ArrayList<ShotType>();
        for (ShotType shotType : shotTypes) {
            ShotType found = em.find(ShotType.class, shotType.getId());
            System.out.println("TYPE:"+found.getType()+" SIZE: "+found.getShotList().size());
            result.add(found);
        }
        return result;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public GroupShot addShot(SessionGroup group, Shot shot) {
        Shot foundShot = em.find(Shot.class, shot.getId());
        SessionGroup foundGroup = em.find(SessionGroup.class, group.getId());

        GroupShot groupShot = new GroupShot();
        groupShot.setGroupID(foundGroup);
        groupShot.setShotID(foundShot);


        em.persist(groupShot);

        foundGroup.getGroupShotList().add(groupShot);
        em.persist(foundGroup);

        return groupShot;
    }
}
