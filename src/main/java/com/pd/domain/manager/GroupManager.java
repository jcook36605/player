package com.pd.domain.manager;

import com.pd.domain.model.GroupShot;
import com.pd.domain.model.SessionGroup;

import javax.faces.model.SelectItem;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: gy6258
 * Date: 3/8/13
 * Time: 2:22 PM
 * To change this template use File | Settings | File Templates.
 */
public class GroupManager implements Serializable {
    private SessionGroup group;
    private GroupShot groupShot;

    private String name;
    private String description;
    private List<SelectItem> groups;

    public GroupManager(List<SessionGroup> gps){
        groups = new ArrayList<SelectItem>();
        for (SessionGroup sessionGroup : gps) {
            groups.add(new SelectItem(sessionGroup.getId(), sessionGroup.getName()));
        }
    }

    public SessionGroup getGroup() {
        return group;
    }

    public void setGroup(SessionGroup group) {
        this.group = group;
    }

    public GroupShot getGroupShot() {
        return groupShot;
    }

    public void setGroupShot(GroupShot groupShot) {
        this.groupShot = groupShot;
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

    public List<SelectItem> getGroups() {
        return groups;
    }

    public void setGroups(List<SelectItem> groups) {
        this.groups = groups;
    }
}
