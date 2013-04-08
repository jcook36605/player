/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pd.domain.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author gy6258
 */
@Entity
@Table(name = "group_shot")
@NamedQueries({
    @NamedQuery(name = "GroupShot.findAll", query = "SELECT g FROM GroupShot g"),
    @NamedQuery(name = "GroupShot.findById", query = "SELECT g FROM GroupShot g WHERE g.id = :id")})
public class GroupShot implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Long id;
    @JoinColumn(name = "Group_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private SessionGroup groupID;

    @JoinColumn(name = "Shot_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Shot shotID;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "groupShotID")
    private List<GroupShotTask> groupShotTaskList;

    public GroupShot() {
    }

    public GroupShot(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SessionGroup getGroupID() {
        return groupID;
    }

    public void setGroupID(SessionGroup groupID) {
        this.groupID = groupID;
    }

    public Shot getShotID() {
        return shotID;
    }

    public void setShotID(Shot shotID) {
        this.shotID = shotID;
    }

    public List<GroupShotTask> getGroupShotTaskList() {
        return groupShotTaskList;
    }

    public void setGroupShotTaskList(List<GroupShotTask> groupShotTaskList) {
        this.groupShotTaskList = groupShotTaskList;
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
        if (!(object instanceof GroupShot)) {
            return false;
        }
        GroupShot other = (GroupShot) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.pd.domain.model.GroupShot[ id=" + id + " ]";
    }
    
}
