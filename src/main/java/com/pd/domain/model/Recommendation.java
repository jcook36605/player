/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pd.domain.model;

import java.io.Serializable;
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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author gy6258
 */
@Entity
@Table(name = "recommendation")
@NamedQueries({
    @NamedQuery(name = "Recommendation.findAll", query = "SELECT r FROM Recommendation r"),
    @NamedQuery(name = "Recommendation.findById", query = "SELECT r FROM Recommendation r WHERE r.id = :id"),
    @NamedQuery(name = "Recommendation.findByName", query = "SELECT r FROM Recommendation r WHERE r.name = :name"),
    @NamedQuery(name = "Recommendation.findByDescription", query = "SELECT r FROM Recommendation r WHERE r.description = :description"),
    @NamedQuery(name = "Recommendation.findByType", query = "SELECT r FROM Recommendation r WHERE r.type = :type")})
public class Recommendation implements Serializable {
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
    @Column(name = "Type", length = 200)
    private String type;
    @JoinTable(name = "shot_recommendation", joinColumns = {
        @JoinColumn(name = "recommendation_id", referencedColumnName = "ID", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "shot_id", referencedColumnName = "ID", nullable = false)})
    @ManyToMany
    private List<Shot> shotList;
    @ManyToMany(mappedBy = "recommendationList")
    private List<ShotTask> shotTaskList;

    public Recommendation() {
    }

    public Recommendation(Long id) {
        this.id = id;
    }

    public Recommendation(Long id, String name) {
        this.id = id;
        this.name = name;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Shot> getShotList() {
        return shotList;
    }

    public void setShotList(List<Shot> shotList) {
        this.shotList = shotList;
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
        if (!(object instanceof Recommendation)) {
            return false;
        }
        Recommendation other = (Recommendation) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.pd.domain.model.Recommendation[ id=" + id + " ]";
    }
    
}
