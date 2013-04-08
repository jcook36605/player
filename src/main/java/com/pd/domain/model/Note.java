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
@Table(name = "notes")
@NamedQueries({
    @NamedQuery(name = "Note.findAll", query = "SELECT n FROM Note n"),
    @NamedQuery(name = "Note.findById", query = "SELECT n FROM Note n WHERE n.id = :id"),
    @NamedQuery(name = "Note.findByCreatedDate", query = "SELECT n FROM Note n WHERE n.createdDate = :createdDate"),
    @NamedQuery(name = "Note.findByText", query = "SELECT n FROM Note n WHERE n.text = :text")})
public class Note implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Long id;
    @Basic(optional = false)
    @Column(name = "Created_Date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Basic(optional = false)
    @Column(name = "text", nullable = false, length = 500)
    private String text;
    @JoinTable(name = "player_shot_task_note", joinColumns = {
        @JoinColumn(name = "NOTE_ID", referencedColumnName = "ID", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "Player_Shot_Task_ID", referencedColumnName = "ID", nullable = false)})
    @ManyToMany
    private List<ShotTask> shotTaskList;

    public Note() {
    }

    public Note(Long id) {
        this.id = id;
    }

    public Note(Long id, Date createdDate, String text) {
        this.id = id;
        this.createdDate = createdDate;
        this.text = text;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
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
        if (!(object instanceof Note)) {
            return false;
        }
        Note other = (Note) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.pd.domain.model.Note[ id=" + id + " ]";
    }
    
}
