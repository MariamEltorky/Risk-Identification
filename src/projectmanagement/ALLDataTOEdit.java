/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectmanagement;

/**
 *
 * @author Mariam Eltorky
 */
public class ALLDataTOEdit {
     private int ID;
    private String RecommendedRiskFactors;
    private String RecommendedResponseStrategy;
    private String RecommendedResponseAction;
    private String GroupID;
    
    public ALLDataTOEdit(int ID,String RecommendedRiskFactors , String RecommendedResponseStrategy , String RecommendedResponseAction , String GroupID) {
        this.ID=ID;
        this.RecommendedRiskFactors=RecommendedRiskFactors;
        this.RecommendedResponseStrategy=RecommendedResponseStrategy;
        this.RecommendedResponseAction=RecommendedResponseAction;
        this.GroupID=GroupID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setRecommendedRiskFactors(String RecommendedRiskFactors) {
        this.RecommendedRiskFactors = RecommendedRiskFactors;
    }

    public void setRecommendedResponseStrategy(String RecommendedResponseStrategy) {
        this.RecommendedResponseStrategy = RecommendedResponseStrategy;
    }

    public void setRecommendedResponseAction(String RecommendedResponseAction) {
        this.RecommendedResponseAction = RecommendedResponseAction;
    }

    public void setGroupID(String GroupID) {
        this.GroupID = GroupID;
    }

    public int getID() {
        return ID;
    }

    public String getRecommendedRiskFactors() {
        return RecommendedRiskFactors;
    }

    public String getRecommendedResponseStrategy() {
        return RecommendedResponseStrategy;
    }

    public String getRecommendedResponseAction() {
        return RecommendedResponseAction;
    }

    public String getGroupID() {
        return GroupID;
    }
}
