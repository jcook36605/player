/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pd.domain.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author gy6258
 */
@Entity
@Table(name = "group_shot_task")
@NamedQueries({
    @NamedQuery(name = "GroupShotTask.findAll", query = "SELECT g FROM GroupShotTask g"),
    @NamedQuery(name = "GroupShotTask.findById", query = "SELECT g FROM GroupShotTask g WHERE g.id = :id")})
public class GroupShotTask implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Long id;
    @JoinColumn(name = "Task_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private Task taskID;
    @JoinColumn(name = "Group_Shot_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private GroupShot groupShotID;

    public GroupShotTask() {
    }

    public GroupShotTask(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Task getTaskID() {
        return taskID;
    }

    public void setTaskID(Task taskID) {
        this.taskID = taskID;
    }

    public GroupShot getGroupShotID() {
        return groupShotID;
    }

    public void setGroupShotID(GroupShot groupShotID) {
        this.groupShotID = groupShotID;
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
        if (!(object instanceof GroupShotTask)) {
            return false;
        }
        GroupShotTask other = (GroupShotTask) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.pd.domain.model.GroupShotTask[ id=" + id + " ]";
    }
    
}
