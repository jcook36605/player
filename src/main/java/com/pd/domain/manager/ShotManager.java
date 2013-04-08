package com.pd.domain.manager;

import com.pd.domain.model.Shot;
import com.pd.domain.model.ShotType;

import javax.faces.model.SelectItem;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: gy6258
 * Date: 3/8/13
 * Time: 9:10 AM
 * To change this template use File | Settings | File Templates.
 */
public class ShotManager implements Serializable {
    private Shot shot;

    private String type;
    private String name;
    private String description;
    private long typeId;

    private List<SelectItem> shotTypes;
    private List<ShotType> types;

    public ShotManager(List<ShotType> st) {
        types = st;
        this.shotTypes = new ArrayList<SelectItem>();
        for (ShotType type : types) {
           this.shotTypes.add(new SelectItem(type.getId(), type.getType()));
        }
        shot = new Shot();
    }

    public List<SelectItem> getShotTypes() {
        return shotTypes;
    }

    public void setShotTypes(List<SelectItem> shotTypes) {
        this.shotTypes = shotTypes;
    }

    public List<ShotType> getTypes() {
        return types;
    }

    public void setTypes(List<ShotType> types) {
        this.types = types;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getTypeId() {
        return typeId;
    }

    public void setTypeId(long typeId) {
        this.typeId = typeId;
    }

    public Shot getShot() {
        return shot;
    }

    public void setShot(Shot shot) {
        this.shot = shot;
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
}
