/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pd.domain.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author gy6258
 */
@Entity
@Table(name = "shot_type", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"ID"})})
@NamedQueries({
    @NamedQuery(name = "ShotType.findAll", query = "SELECT s FROM ShotType s"),
    @NamedQuery(name = "ShotType.findById", query = "SELECT s FROM ShotType s WHERE s.id = :id"),
    @NamedQuery(name = "ShotType.findByType", query = "SELECT s FROM ShotType s WHERE s.type = :type"),
    @NamedQuery(name = "ShotType.findByDescription", query = "SELECT s FROM ShotType s WHERE s.description = :description"),
    @NamedQuery(name = "ShotType.findByCategory", query = "SELECT s FROM ShotType s WHERE s.category = :category")})
public class ShotType implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Long id;
    @Basic(optional = false)
    @Column(name = "Type", nullable = false, length = 255)
    private String type;
    @Column(name = "Description", length = 1000)
    private String description;
    @Column(name = "Category", length = 255)
    private String category;
    @JoinTable(name = "shot_type_task", joinColumns = {
        @JoinColumn(name = "shot_type_id", referencedColumnName = "ID", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "task_id", referencedColumnName = "ID", nullable = false)})
    @ManyToMany
    private List<Task> taskList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "shotTypeID")
    private List<Shot> shotList;

    public ShotType() {
    }

    public ShotType(Long id) {
        this.id = id;
    }

    public ShotType(Long id, String type) {
        this.id = id;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    public List<Shot> getShotList() {
        return shotList;
    }

    public void setShotList(List<Shot> shotList) {
        this.shotList = shotList;
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
        if (!(object instanceof ShotType)) {
            return false;
        }
        ShotType other = (ShotType) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.pd.domain.model.ShotType[ id=" + id + " ]";
    }
    
}
