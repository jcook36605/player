package com.pd.domain.manager;

import com.pd.domain.model.Recommendation;
import com.pd.domain.model.Shot;
import com.pd.domain.model.ShotType;

import javax.faces.model.SelectItem;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: gy6258
 * Date: 3/12/13
 * Time: 11:00 AM
 * To change this template use File | Settings | File Templates.
 */
public class RecommendationManager implements Serializable {
    private Recommendation recommendation;

    private String name;
    private String description;
    private List<SelectItem> recommendations;
    private List<Recommendation> allRecommendations;
    private List<Shot> recommendationByShots;

    public RecommendationManager(List<Recommendation> recs, List<Shot> shots){

        recommendationByShots = shots;
        allRecommendations = recs;
        recommendations = new ArrayList<SelectItem>();
        for (Recommendation r : allRecommendations) {
            recommendations.add(new SelectItem(r.getId(), r.getName()));
        }

    }

    public List<Recommendation> getAllRecommendations() {
        return allRecommendations;
    }

    public void setAllRecommendations(List<Recommendation> allRecommendations) {
        this.allRecommendations = allRecommendations;
    }

    public Recommendation getRecommendation() {
        return recommendation;
    }

    public void setRecommendation(Recommendation recommendation) {
        this.recommendation = recommendation;
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

    public List<SelectItem> getRecommendations() {
        return recommendations;
    }

    public void setRecommendations(List<SelectItem> recommendations) {
        this.recommendations = recommendations;
    }

    public List<Shot> getRecommendationByShots() {
        return recommendationByShots;
    }

    public void setRecommendationByShots(List<Shot> recommendationByShots) {
        this.recommendationByShots = recommendationByShots;
    }
}
