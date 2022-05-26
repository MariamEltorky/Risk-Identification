/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectmanagement;

public class DataClass {
    private String RecommendedRiskFactors;
    private String RecommendedResponseStrategy;
    private String RecommendedResponseAction;
    
    public DataClass(String RecommendedRiskFactors , String RecommendedResponseStrategy , String RecommendedResponseAction) {
        this.RecommendedRiskFactors=RecommendedRiskFactors;
        this.RecommendedResponseStrategy=RecommendedResponseStrategy;
        this.RecommendedResponseAction=RecommendedResponseAction;
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

    public String getRecommendedRiskFactors() {
        return RecommendedRiskFactors;
    }

    public String getRecommendedResponseStrategy() {
        return RecommendedResponseStrategy;
    }

    public String getRecommendedResponseAction() {
        return RecommendedResponseAction;
    }
    
    
}
