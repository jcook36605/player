/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pd.domain.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author gy6258
 */
@Entity
@Table(name = "group_session")
@NamedQueries({
    @NamedQuery(name = "SessionGroup.findAll", query = "SELECT s FROM SessionGroup s"),
    @NamedQuery(name = "SessionGroup.findById", query = "SELECT s FROM SessionGroup s WHERE s.id = :id"),
    @NamedQuery(name = "SessionGroup.findByName", query = "SELECT s FROM SessionGroup s WHERE s.name = :name"),
    @NamedQuery(name = "SessionGroup.findByDescription", query = "SELECT s FROM SessionGroup s WHERE s.description = :description"),
    @NamedQuery(name = "SessionGroup.findByCreateDate", query = "SELECT s FROM SessionGroup s WHERE s.createDate = :createDate")})
public class SessionGroup implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Long id;
    @Basic(optional = false)
    @Column(name = "Name", nullable = false, length = 200)
    private String name;
    @Column(name = "Description", length = 1000)
    private String description;
    @Basic(optional = false)
    @Column(name = "Create_Date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date createDate;
    @JoinTable(name = "group_players", joinColumns = {
        @JoinColumn(name = "Group_ID", referencedColumnName = "ID")}, inverseJoinColumns = {
        @JoinColumn(name = "Player_ID", referencedColumnName = "ID")})
    @ManyToMany
    private List<Player> playerList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "groupID")
    private List<GroupShot> groupShotList;


    public SessionGroup() {
    }

    public SessionGroup(Long id) {
        this.id = id;
    }

    public SessionGroup(Long id, String name, Date createDate) {
        this.id = id;
        this.name = name;
        this.createDate = createDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<GroupShot> getGroupShotList() {
        return groupShotList;
    }

    public void setGroupShotList(List<GroupShot> groupShotList) {
        this.groupShotList = groupShotList;
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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public List<Player> getPlayerList() {
        return playerList;
    }

    public void setPlayerList(List<Player> playerList) {
        this.playerList = playerList;
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
        if (!(object instanceof SessionGroup)) {
            return false;
        }
        SessionGroup other = (SessionGroup) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.pd.domain.model.SessionGroup[ id=" + id + " ]";
    }
    
}
