package com.pd.domain.bean;

import com.pd.domain.model.*;

import javax.ejb.Remote;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: gy6258
 * Date: 2/13/13
 * Time: 2:36 PM
 * To change this template use File | Settings | File Templates.
 */
@Remote
public interface ShotService {


    public Shot addShot(String name, String desc, long typeId);

    public Task addTask(String name, String desc);

    public ShotType addShotType(String type, String desc);

    public void save(Shot shot);

    public void save(Task t);

    public boolean delete(Shot shot);

    public boolean delete(Recommendation rec);

    public boolean delete(Task t);

    public List<Recommendation> findAllRecommendations();

    public List<Task> findAllTasks();

    public List<Shot> findShotRecommendations();

    public List<ShotType> findAll();

    public GroupShot addShot(SessionGroup group, Shot shot);

    /*public PlayerShot findPlayerShot(long shotId);
*/
    public void save(PlayerShot ps);

    public void save(ShotTask st);

    public void save(Recommendation recommendation);


    public Recommendation loadRecommendation(Long id);

    public void addRecommendation(Shot shot, Recommendation rec);

    public void addRecommendation(ShotTask shotTask, Recommendation rec);

    public List<ShotTask> loadPlayerShotTask(Shot shot, long playerId);

    public Shot loadShot(long shotId);

    public ShotType loadShotType(long id);

    public ShotTask loadShotTask(long shotTaskId);

    public PlayerShot loadPlayerShot(long shotId);

    public GroupShot loadGroupShot(long shotId);

    public GroupShotTask addTask(GroupShot gs, Task t);

    public void addNoteToTask(ShotTask task, Note note);
}
