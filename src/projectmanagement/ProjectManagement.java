/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectmanagement;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import javax.swing.Timer;

/**
 *
 * @author Mariam Eltorky
 */
public class ProjectManagement {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
       ProjectManagementController PMC=new ProjectManagementController();
       DBConnection obj=new DBConnection();
       obj.connectDB();
       StartGUI startgui=new StartGUI();
       LogInGUI logingui=new LogInGUI();
       startgui.setVisible(true);
        Thread.sleep(3000);
       startgui.dispose();
       logingui.setVisible(true);
        /*File file=new File("D:\\Recommendation.pdf");
        if (file.exists()) {
         file.delete();
        }*/
  
       PMC.addView(logingui);
       logingui.addController(PMC);
       
        NormalUserGUI normalusergui=new NormalUserGUI();
        PMC.addNormal(normalusergui);
        normalusergui.addController(PMC);
        
        SuperUserGUI superusergui=new SuperUserGUI();
        PMC.addForm(superusergui);
        superusergui.addController(PMC);
        
        RecommendationTable recommendationtable=new RecommendationTable();
        PMC.addTable(recommendationtable);
        recommendationtable.addController(PMC);
        
        
    }
    
}
