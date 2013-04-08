/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pd.domain.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author gy6258
 */
@Entity
@Table(name = "player_shot_tasks")
@NamedQueries({
    @NamedQuery(name = "ShotTask.findByPlayer", query = "SELECT s FROM ShotTask s WHERE s.playerShotID.playerID.id = :playerId and s.playerShotID.shotID.id = :shotId"),
    @NamedQuery(name = "ShotTask.findAll", query = "SELECT s FROM ShotTask s"),
    @NamedQuery(name = "ShotTask.findById", query = "SELECT s FROM ShotTask s WHERE s.id = :id"),
    @NamedQuery(name = "ShotTask.findByCreateDate", query = "SELECT s FROM ShotTask s WHERE s.createDate = :createDate")})
public class ShotTask implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Long id;
    @Column(name = "Create_Date")
    @Temporal(TemporalType.DATE)
    private Date createDate;
    @JoinTable(name = "player_shot_task_rec", joinColumns = {
        @JoinColumn(name = "player_shot_task_ID", referencedColumnName = "ID", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "recommendation_Id", referencedColumnName = "ID", nullable = false)})
    @ManyToMany
    private List<Recommendation> recommendationList;
    @ManyToMany(mappedBy = "shotTaskList")
    private List<Note> noteList;
    @JoinColumn(name = "Task_ID", referencedColumnName = "ID")
    @ManyToOne
    private Task taskID;
    @JoinColumn(name = "Player_Shot_ID", referencedColumnName = "ID")
    @ManyToOne
    private PlayerShot playerShotID;

    public ShotTask() {
    }

    public ShotTask(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public List<Recommendation> getRecommendationList() {
        return recommendationList;
    }

    public void setRecommendationList(List<Recommendation> recommendationList) {
        this.recommendationList = recommendationList;
    }

    public List<Note> getNoteList() {
        return noteList;
    }

    public void setNoteList(List<Note> noteList) {
        this.noteList = noteList;
    }

    public Task getTaskID() {
        return taskID;
    }

    public void setTaskID(Task taskID) {
        this.taskID = taskID;
    }

    public PlayerShot getPlayerShotID() {
        return playerShotID;
    }

    public void setPlayerShotID(PlayerShot playerShotID) {
        this.playerShotID = playerShotID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ShotTask)) {
            return false;
        }
        ShotTask other = (ShotTask) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.pd.domain.model.ShotTask[ id=" + id + " ]";
    }
    
}
