/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pd.domain.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author gy6258
 */
@Entity
@Table(name = "task", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"ID"})})
@NamedQueries({
    @NamedQuery(name = "Task.findAll", query = "SELECT t FROM Task t"),
    @NamedQuery(name = "Task.findById", query = "SELECT t FROM Task t WHERE t.id = :id"),
    @NamedQuery(name = "Task.findByName", query = "SELECT t FROM Task t WHERE t.name = :name"),
    @NamedQuery(name = "Task.findByDescription", query = "SELECT t FROM Task t WHERE t.description = :description"),
    @NamedQuery(name = "Task.findByStartDate", query = "SELECT t FROM Task t WHERE t.startDate = :startDate"),
    @NamedQuery(name = "Task.findByType", query = "SELECT t FROM Task t WHERE t.type = :type")})
public class Task implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Long id;
    @Basic(optional = false)
    @Column(name = "Name", nullable = false, length = 255)
    private String name;
    @Column(name = "Description", length = 1000)
    private String description;
    @Basic(optional = false)
    @Column(name = "Start_Date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;
    @Column(name = "Type", length = 100)
    private String type;
    @ManyToMany(mappedBy = "taskList")
    private List<ShotType> shotTypeList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "taskID")
    private List<GroupShotTask> groupShotTaskList;
    @OneToMany(mappedBy = "taskID")
    private List<ShotTask> shotTaskList;

    public Task() {
    }

    public Task(Long id) {
        this.id = id;
    }

    public Task(Long id, Date startDate) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<ShotType> getShotTypeList() {
        return shotTypeList;
    }

    public void setShotTypeList(List<ShotType> shotTypeList) {
        this.shotTypeList = shotTypeList;
    }

    public List<GroupShotTask> getGroupShotTaskList() {
        return groupShotTaskList;
    }

    public void setGroupShotTaskList(List<GroupShotTask> groupShotTaskList) {
        this.groupShotTaskList = groupShotTaskList;
    }

    public List<ShotTask> getShotTaskList() {
        return shotTaskList;
    }

    public void setShotTaskList(List<ShotTask> shotTaskList) {
        this.shotTaskList = shotTaskList;
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
        if (!(object instanceof Task)) {
            return false;
        }
        Task other = (Task) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.pd.domain.model.Task[ id=" + id + " ]";
    }
    
}
