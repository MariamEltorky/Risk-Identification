/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectmanagement;

import com.lowagie.text.Cell;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Rectangle;
import com.lowagie.text.Row;
import com.lowagie.text.pdf.PdfCell;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfTable;
import com.lowagie.text.pdf.PdfWriter;
import static com.lowagie.text.rtf.RtfWriter.paragraph;
import static com.sun.java.accessibility.util.AWTEventMonitor.addWindowListener;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.DosFileAttributes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;
import javafx.scene.text.Text;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import static javax.swing.JTable.PrintMode.NORMAL;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.icepdf.ri.common.ComponentKeyBinding;
import org.icepdf.ri.common.SwingController;
import org.icepdf.ri.common.SwingViewBuilder;


/**
 *
 * @author Mariam Eltorky
 */
public class ProjectManagementController implements java.awt.event.ActionListener {
    Connection conn=null;
   ResultSet rs=null;
   PreparedStatement pst=null;
    LogInGUI logingui;
    StartGUI startgui;
    NormalUserGUI normalusergui;
    RecommendationTable recommendationtable;
    SuperUserGUI superusergui;
    
    Preferences preference;
    boolean rememberPreference;
    
     SwingController control;
     File fileD=new File("D:\\Recommendation.pdf");
     File fileE=new File("E:\\Recommendation.pdf");
      File fileF=new File("F:\\Recommendation.pdf");
      File fileG=new File("G:\\Recommendation.pdf");
      File fileH=new File("H:\\Recommendation.pdf");
    public void rememberme () {
        preference=Preferences.userNodeForPackage(this.getClass());
        rememberPreference=preference.getBoolean("rememberMe", Boolean.valueOf(""));
        if (rememberPreference) {
            logingui.getUsername().setText(preference.get("UserName", ""));
            logingui.getPassword().setText(preference.get("Password", ""));
        }
    }
    
    public void ToCloseFiles (File file) {
         FileReader actualFile = null;
             try {
                 actualFile = new FileReader(file);
                 actualFile.close();
             } catch (FileNotFoundException ex) {
                 Logger.getLogger(ProjectManagementController.class.getName()).log(Level.SEVERE, null, ex);
             } catch (IOException ex) {
                 Logger.getLogger(ProjectManagementController.class.getName()).log(Level.SEVERE, null, ex);
             } finally {
                 try {
                     actualFile.close();
                 } catch (IOException ex) {
                     Logger.getLogger(ProjectManagementController.class.getName()).log(Level.SEVERE, null, ex);
                 }
             }
    }
    
    public void InsertIntoPDF(String fileP) {
          PdfPTable tb1=new PdfPTable(9);
            Document doc=new Document();
            File file=new File(fileP);
            Path filepath=Paths.get(fileP);
              try {
                       
                      PdfWriter writer =PdfWriter.getInstance(doc, new FileOutputStream(file));
                        Files.setAttribute(filepath,"dos:hidden", true);
                        doc.open();
                        
                        tb1.setWidthPercentage(110);
                        
                      
                        PdfPCell Title=new PdfPCell(new Paragraph("Risk Identification",  FontFactory.getFont(FontFactory.TIMES_BOLD , 40 , Font.BOLD , Color.black)));
                        PdfPCell inputs=new PdfPCell(new Paragraph("Kind Of Oragnization: " + normalusergui.getKindoforganization().getSelectedItem() +"\n \n \n" 
                              + "Project Type: " + normalusergui.getProjecttype().getSelectedItem() +"\n \n \n" 
                                + "Project Size: " + normalusergui.getProjectsize().getSelectedItem() +"\n \n \n"
                                + "Contract Type: " + normalusergui.getContracttype().getSelectedItem() +"\n \n \n"
                                 + "Project Location: " + normalusergui.getProjectlocation().getSelectedItem() +"\n \n \n",  FontFactory.getFont(FontFactory.TIMES_BOLD , 14 , Font.BOLD , Color.black)));
                        PdfPCell IDCell=new PdfPCell(new Paragraph("Number",  FontFactory.getFont(FontFactory.TIMES_BOLD , 14 , Font.BOLD , Color.black)));
                        PdfPCell cell1=new PdfPCell(new Paragraph("Recommended Risk Factors",  FontFactory.getFont(FontFactory.TIMES_BOLD , 18 , Font.BOLD , Color.black)));
                        PdfPCell cell2=new PdfPCell(new Paragraph("Recommended Response Strategy",  FontFactory.getFont(FontFactory.TIMES_BOLD , 18 , Font.BOLD , Color.black)));
                        PdfPCell cell3=new PdfPCell(new Paragraph( "Recommended Response Action" ,  FontFactory.getFont(FontFactory.TIMES_BOLD , 18 , Font.BOLD , Color.black)));
                        Title.setBackgroundColor(Color.WHITE);
                        inputs.setBackgroundColor(Color.WHITE);
                        cell1.setBackgroundColor(Color.WHITE);
                        cell2.setBackgroundColor(Color.WHITE);
                        cell3.setBackgroundColor(Color.WHITE);
                        IDCell.setBackgroundColor(Color.WHITE);
                        Title.setColspan(9);
                        inputs.setColspan(9);
                        cell3.setColspan(3);
                        cell1.setColspan(3);
                        cell2.setColspan(2);
                        Title.setPaddingTop(20);
                        inputs.setPaddingTop(20);
                        cell1.setPaddingTop(20);
                        cell2.setPaddingTop(20);
                        cell3.setPaddingTop(20);
                        IDCell.setPaddingTop(30);
                        Title.setPaddingBottom(20);
                        inputs.setPaddingBottom(20);
                        cell1.setPaddingBottom(20);
                        cell2.setPaddingBottom(20);
                        cell3.setPaddingBottom(20);
                        IDCell.setPaddingBottom(20);
                        Title.setPaddingLeft(10);
                        inputs.setPaddingLeft(10);
                        cell1.setPaddingLeft(10);
                        cell2.setPaddingLeft(10);
                        cell3.setPaddingLeft(10);
                        IDCell.setPaddingLeft(7);
                        Title.setBorderWidth(3);
                        Title.setBorderColor(Color.WHITE);
                        inputs.setBorderWidth(3);
                        inputs.setBorderColor(Color.WHITE);
                        cell1.setBorderWidth(3);
                        cell1.setBorderColor(Color.blue);
                        cell2.setBorderWidth(3);
                        cell2.setBorderColor(Color.blue);
                        cell3.setBorderWidth(3);
                        cell3.setBorderColor(Color.blue);
                        IDCell.setBorderWidth(3);
                        IDCell.setBorderColor(Color.blue);
                        tb1.addCell(Title);
                        tb1.addCell(inputs);
                        tb1.addCell(IDCell);
                        tb1.addCell(cell1);
                        tb1.addCell(cell2);
                        tb1.addCell(cell3);
                        
                        int c=1;
                        for (int i=0;i<recommendationtable.getTable().getRowCount();i++) {
                            PdfPCell IDCellin=new PdfPCell(new Paragraph(Integer.toString(c++),  FontFactory.getFont(FontFactory.TIMES_BOLD , 18 , Font.BOLD , Color.black)));
                            PdfPCell cell4=new PdfPCell(new Paragraph(recommendationtable.getTable().getValueAt(i, 0).toString(), FontFactory.getFont(FontFactory.TIMES_BOLD , 15 , Font.PLAIN , Color.black )));
                            PdfPCell cell5=new PdfPCell(new Paragraph(recommendationtable.getTable().getValueAt(i, 1).toString() , FontFactory.getFont(FontFactory.TIMES_BOLD , 15 , Font.PLAIN , Color.black)));
                            PdfPCell cell6=new PdfPCell(new Paragraph(recommendationtable.getTable().getValueAt(i, 2).toString() , FontFactory.getFont(FontFactory.TIMES_BOLD , 15 , Font.PLAIN , Color.black)));
                            
                            cell4.setBackgroundColor(Color.WHITE);
                            cell5.setBackgroundColor(Color.WHITE);
                            cell6.setBackgroundColor(Color.WHITE);
                            IDCellin.setBackgroundColor(Color.WHITE);
                            cell4.setPaddingTop(20);
                            cell5.setPaddingTop(20);
                            cell6.setPaddingTop(20);
                            IDCellin.setPaddingTop(20);
                            cell4.setPaddingBottom(20);
                            cell5.setPaddingBottom(20);
                            cell6.setPaddingBottom(20);
                            IDCellin.setPaddingBottom(20);
                            cell4.setPaddingLeft(10);
                            cell5.setPaddingLeft(10);
                            cell6.setPaddingLeft(10);
                            IDCellin.setPaddingLeft(10);
                            cell6.setExtraParagraphSpace(20);
                            cell4.setBorderWidth(3);
                            cell4.setBorderColor(Color.blue);
                            cell5.setBorderWidth(3);
                            cell5.setBorderColor(Color.blue);
                            cell6.setBorderWidth(3);
                            cell6.setBorderColor(Color.blue);
                            IDCellin.setBorderWidth(3);
                            IDCellin.setBorderColor(Color.blue);
                            cell6.setColspan(3);
                            cell4.setColspan(3);
                            cell5.setColspan(2);
                            tb1.addCell(IDCellin );
                            tb1.addCell(cell4 );
                            tb1.addCell(cell5 );
                            tb1.addCell(cell6 );
                           
                        }
                        
                        doc.add(tb1);
                    } catch (DocumentException ex) {
                        Logger.getLogger(ProjectManagementController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(ProjectManagementController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(ProjectManagementController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    doc.close();
                 
                    
 /* try {
            control=new SwingController();
            SwingViewBuilder factry=new SwingViewBuilder(control);
            JPanel veiwerCompntpnl=factry.buildViewerPanel();
            ComponentKeyBinding.install(control, veiwerCompntpnl);
            control.getDocumentViewController().setAnnotationCallback(
                    new org.icepdf.ri.common.MyAnnotationCallback(
                    control.getDocumentViewController()));
                  control.openDocument("D:\\Recommendation.pdf");
                recommendationtable.getPanepdf().setViewportView(veiwerCompntpnl); 
               
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(recommendationtable,"Cannot Load Pdf");
        }*/

if (Desktop.isDesktopSupported()) {
    try {
        File myFile = new File(fileP);
        Desktop.getDesktop().open(myFile);
         if (myFile.canRead()){
             FileReader actualFile = new FileReader(myFile);
             actualFile.close();
         }
    } catch (IOException ex) {
        // no application registered for PDFs
    }
    }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
                conn=DBConnection.connectDB();
                
         if (e.getSource() == logingui.getLogin()){
             //addWindowListener(logingui);
                 rememberme ();
                 
             //    String sql="SELECT * from UsersTypes WHERE Username Like ? AND Password Like ? ; ";
        //try{
         //    pst=conn.prepareStatement(sql);
           //  pst.setString(1, logingui.getUsername().getText());
           //  pst.setString(2, logingui.getPassword().getText());
           //  rs=pst.executeQuery();
             
           //  if (rs.next()) {
         
      
               if ((("User".equalsIgnoreCase(logingui.getUsername().getText()))&&("123456".equalsIgnoreCase(logingui.getPassword().getText()))) ||
                       (("Admin".equalsIgnoreCase(logingui.getUsername().getText()))&&("159753".equalsIgnoreCase(logingui.getPassword().getText()))) ) {
                   if (logingui.getRemember().isSelected()  && !rememberPreference) {
                       preference.put("UserName" , logingui.getUsername().getText());
                       preference.put("Password" , logingui.getPassword().getText());
                       preference.putBoolean("rememberMe", true);
                   }
                   else if (!(logingui.getRemember().isSelected()) && rememberPreference) {
                        preference.put("UserName" , "");
                       preference.put("Password" , "");
                       preference.putBoolean("rememberMe", false);
                   }
                  if ((("User".equals(logingui.getUsername().getText()))&&("123456".equals(logingui.getPassword().getText())))) {
                     
                     logingui.setVisible(false);
                   //  normalusergui.setLocation(logingui.getX() ,logingui.getY());
                     normalusergui.setVisible(true);
                     normalusergui.getProjecttype().setSelectedItem(null);
                     normalusergui.getProjectsize().setSelectedItem(null);
                     normalusergui.getContracttype().setSelectedItem(null);
                      normalusergui.getKindoforganization().setSelectedItem(null);
                    normalusergui.getProjectlocation().setSelectedItem(null);   
                  }
                  else if ((("Admin".equals(logingui.getUsername().getText()))&&("159753".equals(logingui.getPassword().getText())))) {
                       
                      logingui.setVisible(false);
                       superusergui.setVisible(true);
                       superusergui.getId().setText("");
                     superusergui.getGroup().setText("");
                     superusergui.getFactor().setText("");
                     superusergui.getStra().setText("");
                     superusergui.getAction().setText("");
                     DefaultTableModel model=(DefaultTableModel) superusergui.getTable().getModel();
                      model.setRowCount(0);
                  }
               }
             
             else {
                  logingui.getUsername().setText("");
                  logingui.getPassword().setText("");
                 JOptionPane.showMessageDialog(null, "Please Enter The Correct Username and Password");
             }
          
       /* }catch(Exception ex) {
            //JOptionPane.showMessageDialog(null, "Failed");
        }*/
            }
         
         if (e.getSource() == superusergui.getAdd()){
              if  (fileD.canRead()){
             ToCloseFiles(fileD);
         }
         else if (fileE.canRead()){
            ToCloseFiles(fileE);
         }
         else if (fileF.canRead()){
              ToCloseFiles(fileF);
         }
         else if (fileG.canRead()){
              ToCloseFiles(fileG);
         }
         else if (fileH.canRead()){
              ToCloseFiles(fileH);
         }
          else {
             System.out.println("All Files are not open");
         }
             try {
                 if (!(superusergui.getId().getText().equalsIgnoreCase("")) && !(superusergui.getFactor().getText().equalsIgnoreCase("")) &&
                         !(superusergui.getStra().getText().equalsIgnoreCase("")) && !(superusergui.getAction().getText().equalsIgnoreCase("")) &&
                        !(superusergui.getGroup().getText().equalsIgnoreCase("")) ) {
                int id=Integer.parseInt(superusergui.getId().getText().trim());
                String factor=superusergui.getFactor().getText().trim();
                String stra=superusergui.getStra().getText().trim();
                String action=superusergui.getAction().getText().trim();
                String group=superusergui.getGroup().getText().trim().toUpperCase();

                String tocheckprimary="SELECT ID from Recommended WHERE ID = ? ; ";
                pst=conn.prepareStatement(tocheckprimary);
                pst.setInt(1 , id);
                rs=pst.executeQuery();
                if (rs.next()) {
                    JOptionPane.showMessageDialog(null, "This ID Already Added");
                }
                else {
                    conn=DBConnection.connectDB();
                    if (group.length()>1) {
                        JOptionPane.showMessageDialog(null, "You Must Enter Only One Group");
                    }
                    else {   
                        try {
                            String sql="Insert Into Recommended Values (? , ? , ? , ?) ; COMMIT; ";
                            pst=conn.prepareStatement(sql);
                            pst.setInt(1 , id);
                            pst.setString(2 , factor);
                            pst.setString(3 , stra);
                            pst.setString(4 , action);
                            pst.execute();
                            String sql2="Insert Into Groups Values (? , ? ) ; COMMIT; ";
                            pst=conn.prepareStatement(sql2);
                            pst.setString(1 , group);
                            pst.setInt(2 , id);
                            pst.execute();
                            JOptionPane.showMessageDialog(null, "Successful Add");
                            superusergui.getId().setText("");
                            superusergui.getFactor().setText("");
                            superusergui.getStra().setText("");
                            superusergui.getAction().setText("");
                            superusergui.getGroup().setText("");
                        
                        } catch (SQLException ex) {
                            Logger.getLogger(ProjectManagementController.class.getName()).log(Level.SEVERE, null, ex);
                        } finally {
                            try{
                                rs.close();
                                pst.close();
                            }catch(Exception ex) {
                            
                            }
                        }
                    }
            }
                 }
                 else {
                     JOptionPane.showMessageDialog(null, "You Must Enter All Data Which You Want To ADD");
                 }
                 } catch (SQLException ex) {
                Logger.getLogger(ProjectManagementController.class.getName()).log(Level.SEVERE, null, ex);
            }
            finally {
                 if (conn != null) {
                     try {
                          conn.close(); 
                     }catch (SQLException ex) {
      
                      }
                     }
            }
         }
          if (e.getSource() == superusergui.getDelet()){
              if  (fileD.canRead()){
             ToCloseFiles(fileD);
         }
         else if (fileE.canRead()){
            ToCloseFiles(fileE);
         }
         else if (fileF.canRead()){
              ToCloseFiles(fileF);
         }
         else if (fileG.canRead()){
              ToCloseFiles(fileG);
         }
         else if (fileH.canRead()){
              ToCloseFiles(fileH);
         }
          else {
             System.out.println("All Files are not open");
         }
               try {
                if (!(superusergui.getId().getText().equalsIgnoreCase(""))) {
                int id=Integer.parseInt(superusergui.getId().getText().trim());
                 String tocheckprimary="SELECT ID from Recommended WHERE ID = ? ; ";
                pst=conn.prepareStatement(tocheckprimary);
                pst.setInt(1 , id);
                rs=pst.executeQuery();
                if (!rs.next()) {
                    JOptionPane.showMessageDialog(null, "This ID Not Exist");
                }
                else {
                String sql="DELETE FROM Groups WHERE Recommended_ID = ?  ; COMMIT; ";
                pst=conn.prepareStatement(sql);
                pst.setInt(1 , id);
                pst.execute();
                String sql2="DELETE FROM Recommended WHERE ID = ?  ; COMMIT;";
                pst=conn.prepareStatement(sql2);
                pst.setInt(1 , id);
                pst.execute();
                JOptionPane.showMessageDialog(null, "Successful Delete");
                superusergui.getId().setText("");
                superusergui.getFactor().setText("");
                superusergui.getStra().setText("");
                superusergui.getAction().setText("");
                superusergui.getGroup().setText("");
                }
                }
                 else {
                JOptionPane.showMessageDialog(null, "You Must Enter ID Which You Want To Delete");
             }
             
            } catch (SQLException ex) {
                Logger.getLogger(ProjectManagementController.class.getName()).log(Level.SEVERE, null, ex);
            }
             finally {
                   try{
                                rs.close();
                                pst.close();
                            }catch(Exception ex) {
                            
                            }
                 if (conn != null) {
                     try {
                          conn.close(); 
                     } catch (SQLException ex) {
      
                      }
                     }
            }
          }
          
          
          if (e.getSource() == superusergui.getShow()){
              if  (fileD.canRead()){
             ToCloseFiles(fileD);
         }
         else if (fileE.canRead()){
            ToCloseFiles(fileE);
         }
         else if (fileF.canRead()){
              ToCloseFiles(fileF);
         }
         else if (fileG.canRead()){
              ToCloseFiles(fileG);
         }
         else if (fileH.canRead()){
              ToCloseFiles(fileH);
         }
          else {
             System.out.println("All Files are not open");
         }
              DefaultTableModel model=(DefaultTableModel) superusergui.getTable().getModel();
               model.setRowCount(0);
               ArrayList<ALLDataTOEdit> DSS=new ArrayList<ALLDataTOEdit>();
               
            try {
                String sql="Select rs.ID , rs.RecommendedRiskFactors , rs.RecommendedResponseStrategy , rs.RecommendedResponseAction , gs.GroupID"
                        + "  FROM Recommended rs , Groups gs WHERE rs.ID==gs.Recommended_ID  ; ";
                Statement st=conn.createStatement();
                rs=st.executeQuery(sql);
                 ALLDataTOEdit DS;
                 while(rs.next()) {
                 DS=new ALLDataTOEdit(rs.getInt("ID"),rs.getString("RecommendedRiskFactors") , rs.getString("RecommendedResponseStrategy"), rs.getString("RecommendedResponseAction") , rs.getString("GroupID"));
                 DSS.add(DS);
             }
                 
          
        Object[] row= new Object[5];
        for (int i=0;i<DSS.size();i++) {
            row[0]=DSS.get(i).getID();
            row[1]=DSS.get(i).getRecommendedRiskFactors();
            row[2]=DSS.get(i).getRecommendedResponseStrategy();
            row[3]=DSS.get(i).getRecommendedResponseAction();
            row[4]=DSS.get(i).getGroupID();
            model.addRow(row);
        }
            } catch (SQLException ex) {
                Logger.getLogger(ProjectManagementController.class.getName()).log(Level.SEVERE, null, ex);
            }
          }
          
          if (e.getSource() == superusergui.getLogoutsuper()){
              if  (fileD.canRead()){
             ToCloseFiles(fileD);
         }
         else if (fileE.canRead()){
            ToCloseFiles(fileE);
         }
         else if (fileF.canRead()){
              ToCloseFiles(fileF);
         }
         else if (fileG.canRead()){
              ToCloseFiles(fileG);
         }
         else if (fileH.canRead()){
              ToCloseFiles(fileH);
         }
          else {
             System.out.println("All Files are not open");
         }
              superusergui.setVisible(false);
              logingui.setLocation(superusergui.getX() ,superusergui.getY());
             logingui.setVisible(true);
             logingui.getUsername().setText("");
             logingui.getPassword().setText("");
          
          }

        if (e.getSource() == normalusergui.getShowrecommendation()){
           
            recommendationtable.getTable().setVisible(true);
        //    recommendationtable.getTable().setBackground(new java.awt.Color(153,153,153));
           // NOI18N
            recommendationtable.getTable().getAutoResizeMode();
      //  recommendationtable.getTable().setForeground(new java.awt.Color(51, 0, 255));
        recommendationtable.getTable().setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
             new String [] {
                "Recommended Risk Factors", "Recommended Response Strategy", "Recommended Response Action"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
     
          recommendationtable.getTable().setFont(new java.awt.Font("Century Gothic", 1, 15));
        
      //  recommendationtable.getBacke().setIcon(new javax.swing.ImageIcon(getClass().getResource("/projectmanagement/backend.png"))); // NOI18N
        
   //    recommendationtable.getPanepdf().setViewportView(recommendationtable.getBacke());

        
        
        recommendationtable.getTable().setRowHeight(50);
        recommendationtable.getTable().setRowMargin(2);
        
    //   recommendationtable.getPanepdf().setBackground(new java.awt.Color(26, 49, 83));

       
            
          if ((normalusergui.getProjecttype().getSelectedItem()!=null)
                  && (normalusergui.getProjectsize().getSelectedItem()!=null)
                  && (normalusergui.getContracttype().getSelectedItem()!=null)
                  && (normalusergui.getKindoforganization().getSelectedItem()!=null)
                  && (normalusergui.getProjectlocation().getSelectedItem()!=null)) {
              
         
            if ((normalusergui.getProjecttype().getSelectedItem().toString()=="Infrastructure/Tunnels")
                  && (normalusergui.getProjectsize().getSelectedItem().toString()=="Medium")
                  && (normalusergui.getContracttype().getSelectedItem().toString()=="EPC")
                  && (normalusergui.getKindoforganization().getSelectedItem().toString()=="Client/Consultant")
                  && (normalusergui.getProjectlocation().getSelectedItem().toString()=="Non-Remote Location") ) {
                
                     
                     normalusergui.setVisible(false);
     
                     //recommendationtable.setLocation(normalusergui.getX() ,normalusergui.getY());
                     recommendationtable.setVisible(true);
                     recommendationtable.ShowGroupA();
              }
            else if ((normalusergui.getProjecttype().getSelectedItem().toString()=="Infrastructure/Tunnels")
                  && (normalusergui.getProjectsize().getSelectedItem().toString()=="Medium")
                  && (normalusergui.getContracttype().getSelectedItem().toString()=="EPC")
                  && (normalusergui.getKindoforganization().getSelectedItem().toString()=="Contractor")
                  && (normalusergui.getProjectlocation().getSelectedItem().toString()=="Non-Remote Location")) {
                
                     normalusergui.setVisible(false);
                    // recommendationtable.setLocation(normalusergui.getX() ,normalusergui.getY());
                     recommendationtable.setVisible(true);
                     recommendationtable.ShowGroupB();
            }
            else if ((normalusergui.getProjecttype().getSelectedItem().toString()=="Buildings Construction")
                  && (normalusergui.getProjectsize().getSelectedItem().toString()=="Medium")
                  && (normalusergui.getContracttype().getSelectedItem().toString()=="EPC")
                  && (normalusergui.getKindoforganization().getSelectedItem().toString()=="Client/Consultant")
                  && (normalusergui.getProjectlocation().getSelectedItem().toString()=="Non-Remote Location")) {
                
                     normalusergui.setVisible(false);
                    // recommendationtable.setLocation(normalusergui.getX() ,normalusergui.getY());
                     recommendationtable.setVisible(true);
                     recommendationtable.ShowGroupC();
            }
            else if ((normalusergui.getProjecttype().getSelectedItem().toString()=="Buildings Construction")
                  && (normalusergui.getProjectsize().getSelectedItem().toString()=="Medium")
                  && (normalusergui.getContracttype().getSelectedItem().toString()=="EPC")
                  && (normalusergui.getKindoforganization().getSelectedItem().toString()=="Contractor")
                  && (normalusergui.getProjectlocation().getSelectedItem().toString()=="Non-Remote Location")) {
                
                     normalusergui.setVisible(false);
                   //  recommendationtable.setLocation(normalusergui.getX() ,normalusergui.getY());
                     recommendationtable.setVisible(true);
                     recommendationtable.ShowGroupD();
            }
            /*********************************************** start Group One ***********************************/
           else if ((normalusergui.getProjecttype().getSelectedItem().toString()=="Infrastructure/Tunnels")
                  && (normalusergui.getProjectsize().getSelectedItem().toString()=="Mega")
                  && (normalusergui.getContracttype().getSelectedItem().toString()=="EPC")
                  && (normalusergui.getKindoforganization().getSelectedItem().toString()=="Client/Consultant")
                  && (normalusergui.getProjectlocation().getSelectedItem().toString()=="Non-Remote Location") ) {
                
                     
                     normalusergui.setVisible(false);
                    // recommendationtable.setLocation(normalusergui.getX() ,normalusergui.getY());
                     recommendationtable.setVisible(true);
                     recommendationtable.ShowGroupAOne();
              }
            else if ((normalusergui.getProjecttype().getSelectedItem().toString()=="Infrastructure/Tunnels")
                  && (normalusergui.getProjectsize().getSelectedItem().toString()=="Mega")
                  && (normalusergui.getContracttype().getSelectedItem().toString()=="EPC")
                  && (normalusergui.getKindoforganization().getSelectedItem().toString()=="Contractor")
                  && (normalusergui.getProjectlocation().getSelectedItem().toString()=="Non-Remote Location")) {
                
                     normalusergui.setVisible(false);
                    // recommendationtable.setLocation(normalusergui.getX() ,normalusergui.getY());
                     recommendationtable.setVisible(true);
                     recommendationtable.ShowGroupBOne();
            }
            else if ((normalusergui.getProjecttype().getSelectedItem().toString()=="Buildings Construction")
                  && (normalusergui.getProjectsize().getSelectedItem().toString()=="Mega")
                  && (normalusergui.getContracttype().getSelectedItem().toString()=="EPC")
                  && (normalusergui.getKindoforganization().getSelectedItem().toString()=="Client/Consultant")
                  && (normalusergui.getProjectlocation().getSelectedItem().toString()=="Non-Remote Location")) {
                
                     normalusergui.setVisible(false);
                    // recommendationtable.setLocation(normalusergui.getX() ,normalusergui.getY());
                     recommendationtable.setVisible(true);
                     recommendationtable.ShowGroupCOne();
            }
            else if ((normalusergui.getProjecttype().getSelectedItem().toString()=="Buildings Construction")
                  && (normalusergui.getProjectsize().getSelectedItem().toString()=="Mega")
                  && (normalusergui.getContracttype().getSelectedItem().toString()=="EPC")
                  && (normalusergui.getKindoforganization().getSelectedItem().toString()=="Contractor")
                  && (normalusergui.getProjectlocation().getSelectedItem().toString()=="Non-Remote Location")) {
                
                     normalusergui.setVisible(false);
                   //  recommendationtable.setLocation(normalusergui.getX() ,normalusergui.getY());
                     recommendationtable.setVisible(true);
                     recommendationtable.ShowGroupDOne();
            }
            
            /*********************************************** end Group One***********************************/
            
              /*********************************************** start Group One Two ***********************************/
           else if ((normalusergui.getProjecttype().getSelectedItem().toString()=="Infrastructure/Tunnels")
                  && (normalusergui.getProjectsize().getSelectedItem().toString()=="Mega")
                  && (normalusergui.getContracttype().getSelectedItem().toString()=="Design and Built")
                  && (normalusergui.getKindoforganization().getSelectedItem().toString()=="Client/Consultant")
                  && (normalusergui.getProjectlocation().getSelectedItem().toString()=="Non-Remote Location") ) {
                
                     
                     normalusergui.setVisible(false);
                    // recommendationtable.setLocation(normalusergui.getX() ,normalusergui.getY());
                     recommendationtable.setVisible(true);
                     recommendationtable.ShowGroupAOneTwo();
              }
            else if ((normalusergui.getProjecttype().getSelectedItem().toString()=="Infrastructure/Tunnels")
                  && (normalusergui.getProjectsize().getSelectedItem().toString()=="Mega")
                  && (normalusergui.getContracttype().getSelectedItem().toString()=="Design and Built")
                  && (normalusergui.getKindoforganization().getSelectedItem().toString()=="Contractor")
                  && (normalusergui.getProjectlocation().getSelectedItem().toString()=="Non-Remote Location")) {
                
                     normalusergui.setVisible(false);
                    // recommendationtable.setLocation(normalusergui.getX() ,normalusergui.getY());
                     recommendationtable.setVisible(true);
                     recommendationtable.ShowGroupBOneTwo();
            }
            else if ((normalusergui.getProjecttype().getSelectedItem().toString()=="Buildings Construction")
                  && (normalusergui.getProjectsize().getSelectedItem().toString()=="Mega")
                  && (normalusergui.getContracttype().getSelectedItem().toString()=="Design and Built")
                  && (normalusergui.getKindoforganization().getSelectedItem().toString()=="Client/Consultant")
                  && (normalusergui.getProjectlocation().getSelectedItem().toString()=="Non-Remote Location")) {
                
                     normalusergui.setVisible(false);
                    // recommendationtable.setLocation(normalusergui.getX() ,normalusergui.getY());
                     recommendationtable.setVisible(true);
                     recommendationtable.ShowGroupCOneTwo();
            }
            else if ((normalusergui.getProjecttype().getSelectedItem().toString()=="Buildings Construction")
                  && (normalusergui.getProjectsize().getSelectedItem().toString()=="Mega")
                  && (normalusergui.getContracttype().getSelectedItem().toString()=="Design and Built")
                  && (normalusergui.getKindoforganization().getSelectedItem().toString()=="Contractor")
                  && (normalusergui.getProjectlocation().getSelectedItem().toString()=="Non-Remote Location")) {
                
                     normalusergui.setVisible(false);
                  //   recommendationtable.setLocation(normalusergui.getX() ,normalusergui.getY());
                     recommendationtable.setVisible(true);
                     recommendationtable.ShowGroupDOneTwo();
            }
            
            /*********************************************** end Group One Two***********************************/
            
             
            
             /*********************************************** start Group One Three ***********************************/
           else if ((normalusergui.getProjecttype().getSelectedItem().toString()=="Infrastructure/Tunnels")
                  && (normalusergui.getProjectsize().getSelectedItem().toString()=="Mega")
                  && (normalusergui.getContracttype().getSelectedItem().toString()=="EPC")
                  && (normalusergui.getKindoforganization().getSelectedItem().toString()=="Client/Consultant")
                  && (normalusergui.getProjectlocation().getSelectedItem().toString()=="Remote Location") ) {
                
                     
                     normalusergui.setVisible(false);
                    // recommendationtable.setLocation(normalusergui.getX() ,normalusergui.getY());
                     recommendationtable.setVisible(true);
                     recommendationtable.ShowGroupAOneThree();
              }
            else if ((normalusergui.getProjecttype().getSelectedItem().toString()=="Infrastructure/Tunnels")
                  && (normalusergui.getProjectsize().getSelectedItem().toString()=="Mega")
                  && (normalusergui.getContracttype().getSelectedItem().toString()=="EPC")
                  && (normalusergui.getKindoforganization().getSelectedItem().toString()=="Contractor")
                  && (normalusergui.getProjectlocation().getSelectedItem().toString()=="Remote Location")) {
                
                     normalusergui.setVisible(false);
                    // recommendationtable.setLocation(normalusergui.getX() ,normalusergui.getY());
                     recommendationtable.setVisible(true);
                     recommendationtable.ShowGroupBOneThree();
            }
            else if ((normalusergui.getProjecttype().getSelectedItem().toString()=="Buildings Construction")
                  && (normalusergui.getProjectsize().getSelectedItem().toString()=="Mega")
                  && (normalusergui.getContracttype().getSelectedItem().toString()=="EPC")
                  && (normalusergui.getKindoforganization().getSelectedItem().toString()=="Client/Consultant")
                  && (normalusergui.getProjectlocation().getSelectedItem().toString()=="Remote Location")) {
                
                     normalusergui.setVisible(false);
                    // recommendationtable.setLocation(normalusergui.getX() ,normalusergui.getY());
                     recommendationtable.setVisible(true);
                     recommendationtable.ShowGroupCOneThree();
            }
            else if ((normalusergui.getProjecttype().getSelectedItem().toString()=="Buildings Construction")
                  && (normalusergui.getProjectsize().getSelectedItem().toString()=="Mega")
                  && (normalusergui.getContracttype().getSelectedItem().toString()=="EPC")
                  && (normalusergui.getKindoforganization().getSelectedItem().toString()=="Contractor")
                  && (normalusergui.getProjectlocation().getSelectedItem().toString()=="Remote Location")) {
                
                     normalusergui.setVisible(false);
                   //  recommendationtable.setLocation(normalusergui.getX() ,normalusergui.getY());
                     recommendationtable.setVisible(true);
                     recommendationtable.ShowGroupDOneThree();
            }
            
            /*********************************************** end Group One Three***********************************/
            
            
            
            
             /*********************************************** start Group Two ***********************************/
           else if ((normalusergui.getProjecttype().getSelectedItem().toString()=="Infrastructure/Tunnels")
                  && (normalusergui.getProjectsize().getSelectedItem().toString()=="Medium")
                  && (normalusergui.getContracttype().getSelectedItem().toString()=="Design and Built")
                  && (normalusergui.getKindoforganization().getSelectedItem().toString()=="Client/Consultant")
                  && (normalusergui.getProjectlocation().getSelectedItem().toString()=="Non-Remote Location") ) {
                
                     
                     normalusergui.setVisible(false);
                   //  recommendationtable.setLocation(normalusergui.getX() ,normalusergui.getY());
                     recommendationtable.setVisible(true);
                     recommendationtable.ShowGroupATwo();
              }
            else if ((normalusergui.getProjecttype().getSelectedItem().toString()=="Infrastructure/Tunnels")
                  && (normalusergui.getProjectsize().getSelectedItem().toString()=="Medium")
                  && (normalusergui.getContracttype().getSelectedItem().toString()=="Design and Built")
                  && (normalusergui.getKindoforganization().getSelectedItem().toString()=="Contractor")
                  && (normalusergui.getProjectlocation().getSelectedItem().toString()=="Non-Remote Location")) {
                
                     normalusergui.setVisible(false);
                    // recommendationtable.setLocation(normalusergui.getX() ,normalusergui.getY());
                     recommendationtable.setVisible(true);
                     recommendationtable.ShowGroupBTwo();
            }
            else if ((normalusergui.getProjecttype().getSelectedItem().toString()=="Buildings Construction")
                  && (normalusergui.getProjectsize().getSelectedItem().toString()=="Medium")
                  && (normalusergui.getContracttype().getSelectedItem().toString()=="Design and Built")
                  && (normalusergui.getKindoforganization().getSelectedItem().toString()=="Client/Consultant")
                  && (normalusergui.getProjectlocation().getSelectedItem().toString()=="Non-Remote Location")) {
                
                     normalusergui.setVisible(false);
                   //  recommendationtable.setLocation(normalusergui.getX() ,normalusergui.getY());
                     recommendationtable.setVisible(true);
                     recommendationtable.ShowGroupCTwo();
            }
            else if ((normalusergui.getProjecttype().getSelectedItem().toString()=="Buildings Construction")
                  && (normalusergui.getProjectsize().getSelectedItem().toString()=="Medium")
                  && (normalusergui.getContracttype().getSelectedItem().toString()=="Design and Built")
                  && (normalusergui.getKindoforganization().getSelectedItem().toString()=="Contractor")
                  && (normalusergui.getProjectlocation().getSelectedItem().toString()=="Non-Remote Location")) {
                
                     normalusergui.setVisible(false);
                    // recommendationtable.setLocation(normalusergui.getX() ,normalusergui.getY());
                     recommendationtable.setVisible(true);
                     recommendationtable.ShowGroupDTwo();
            }
            
            /*********************************************** end Group Two***********************************/
            
            
            
            
            /*********************************************** start Group Two Three***********************************/
           else if ((normalusergui.getProjecttype().getSelectedItem().toString()=="Infrastructure/Tunnels")
                  && (normalusergui.getProjectsize().getSelectedItem().toString()=="Medium")
                  && (normalusergui.getContracttype().getSelectedItem().toString()=="Design and Built")
                  && (normalusergui.getKindoforganization().getSelectedItem().toString()=="Client/Consultant")
                  && (normalusergui.getProjectlocation().getSelectedItem().toString()=="Remote Location") ) {
                
                     
                     normalusergui.setVisible(false);
                    // recommendationtable.setLocation(normalusergui.getX() ,normalusergui.getY());
                     recommendationtable.setVisible(true);
                     recommendationtable.ShowGroupATwoThree();
              }
            else if ((normalusergui.getProjecttype().getSelectedItem().toString()=="Infrastructure/Tunnels")
                  && (normalusergui.getProjectsize().getSelectedItem().toString()=="Medium")
                  && (normalusergui.getContracttype().getSelectedItem().toString()=="Design and Built")
                  && (normalusergui.getKindoforganization().getSelectedItem().toString()=="Contractor")
                  && (normalusergui.getProjectlocation().getSelectedItem().toString()=="Remote Location")) {
                
                     normalusergui.setVisible(false);
                   //  recommendationtable.setLocation(normalusergui.getX() ,normalusergui.getY());
                     recommendationtable.setVisible(true);
                     recommendationtable.ShowGroupBTwoThree();
            }
            else if ((normalusergui.getProjecttype().getSelectedItem().toString()=="Buildings Construction")
                  && (normalusergui.getProjectsize().getSelectedItem().toString()=="Medium")
                  && (normalusergui.getContracttype().getSelectedItem().toString()=="Design and Built")
                  && (normalusergui.getKindoforganization().getSelectedItem().toString()=="Client/Consultant")
                  && (normalusergui.getProjectlocation().getSelectedItem().toString()=="Remote Location")) {
                
                     normalusergui.setVisible(false);
                    // recommendationtable.setLocation(normalusergui.getX() ,normalusergui.getY());
                     recommendationtable.setVisible(true);
                     recommendationtable.ShowGroupCTwoThree();
            }
            else if ((normalusergui.getProjecttype().getSelectedItem().toString()=="Buildings Construction")
                  && (normalusergui.getProjectsize().getSelectedItem().toString()=="Medium")
                  && (normalusergui.getContracttype().getSelectedItem().toString()=="Design and Built")
                  && (normalusergui.getKindoforganization().getSelectedItem().toString()=="Contractor")
                  && (normalusergui.getProjectlocation().getSelectedItem().toString()=="Remote Location")) {
                
                     normalusergui.setVisible(false);
                    // recommendationtable.setLocation(normalusergui.getX() ,normalusergui.getY());
                     recommendationtable.setVisible(true);
                     recommendationtable.ShowGroupDTwoThree();
            }
            
            /*********************************************** end Group Two Three***********************************/
            
            
            /*********************************************** start Group Three***********************************/
           else if ((normalusergui.getProjecttype().getSelectedItem().toString()=="Infrastructure/Tunnels")
                  && (normalusergui.getProjectsize().getSelectedItem().toString()=="Medium")
                  && (normalusergui.getContracttype().getSelectedItem().toString()=="EPC")
                  && (normalusergui.getKindoforganization().getSelectedItem().toString()=="Client/Consultant")
                  && (normalusergui.getProjectlocation().getSelectedItem().toString()=="Remote Location") ) {
                
                     
                     normalusergui.setVisible(false);
                    // recommendationtable.setLocation(normalusergui.getX() ,normalusergui.getY());
                     recommendationtable.setVisible(true);
                     recommendationtable.ShowGroupAThree();
              }
            else if ((normalusergui.getProjecttype().getSelectedItem().toString()=="Infrastructure/Tunnels")
                  && (normalusergui.getProjectsize().getSelectedItem().toString()=="Medium")
                  && (normalusergui.getContracttype().getSelectedItem().toString()=="EPC")
                  && (normalusergui.getKindoforganization().getSelectedItem().toString()=="Contractor")
                  && (normalusergui.getProjectlocation().getSelectedItem().toString()=="Remote Location")) {
                
                     normalusergui.setVisible(false);
                    // recommendationtable.setLocation(normalusergui.getX() ,normalusergui.getY());
                     recommendationtable.setVisible(true);
                     recommendationtable.ShowGroupBThree();
            }
            else if ((normalusergui.getProjecttype().getSelectedItem().toString()=="Buildings Construction")
                  && (normalusergui.getProjectsize().getSelectedItem().toString()=="Medium")
                  && (normalusergui.getContracttype().getSelectedItem().toString()=="EPC")
                  && (normalusergui.getKindoforganization().getSelectedItem().toString()=="Client/Consultant")
                  && (normalusergui.getProjectlocation().getSelectedItem().toString()=="Remote Location")) {
                
                     normalusergui.setVisible(false);
                    // recommendationtable.setLocation(normalusergui.getX() ,normalusergui.getY());
                     recommendationtable.setVisible(true);
                     recommendationtable.ShowGroupCThree();
            }
            else if ((normalusergui.getProjecttype().getSelectedItem().toString()=="Buildings Construction")
                  && (normalusergui.getProjectsize().getSelectedItem().toString()=="Medium")
                  && (normalusergui.getContracttype().getSelectedItem().toString()=="EPC")
                  && (normalusergui.getKindoforganization().getSelectedItem().toString()=="Contractor")
                  && (normalusergui.getProjectlocation().getSelectedItem().toString()=="Remote Location")) {
                
                     normalusergui.setVisible(false);
                    // recommendationtable.setLocation(normalusergui.getX() ,normalusergui.getY());
                     recommendationtable.setVisible(true);
                     recommendationtable.ShowGroupDThree();
            }
            
            /*********************************************** end Group Three***********************************/
            
            
            
             /*********************************************** start Group One Two Three***********************************/
           else if ((normalusergui.getProjecttype().getSelectedItem().toString()=="Infrastructure/Tunnels")
                  && (normalusergui.getProjectsize().getSelectedItem().toString()=="Mega")
                  && (normalusergui.getContracttype().getSelectedItem().toString()=="Design and Built")
                  && (normalusergui.getKindoforganization().getSelectedItem().toString()=="Client/Consultant")
                  && (normalusergui.getProjectlocation().getSelectedItem().toString()=="Remote Location") ) {
                
                     
                     normalusergui.setVisible(false);
                    // recommendationtable.setLocation(normalusergui.getX() ,normalusergui.getY());
                     recommendationtable.setVisible(true);
                     recommendationtable.ShowGroupAOneTwoThree();
              }
            else if ((normalusergui.getProjecttype().getSelectedItem().toString()=="Infrastructure/Tunnels")
                  && (normalusergui.getProjectsize().getSelectedItem().toString()=="Mega")
                  && (normalusergui.getContracttype().getSelectedItem().toString()=="Design and Built")
                  && (normalusergui.getKindoforganization().getSelectedItem().toString()=="Contractor")
                  && (normalusergui.getProjectlocation().getSelectedItem().toString()=="Remote Location")) {
                
                     normalusergui.setVisible(false);
                    // recommendationtable.setLocation(normalusergui.getX() ,normalusergui.getY());
                     recommendationtable.setVisible(true);
                     recommendationtable.ShowGroupBOneTwoThree();
            }
            else if ((normalusergui.getProjecttype().getSelectedItem().toString()=="Buildings Construction")
                  && (normalusergui.getProjectsize().getSelectedItem().toString()=="Mega")
                  && (normalusergui.getContracttype().getSelectedItem().toString()=="Design and Built")
                  && (normalusergui.getKindoforganization().getSelectedItem().toString()=="Client/Consultant")
                  && (normalusergui.getProjectlocation().getSelectedItem().toString()=="Remote Location")) {
                
                     normalusergui.setVisible(false);
                   //  recommendationtable.setLocation(normalusergui.getX() ,normalusergui.getY());
                     recommendationtable.setVisible(true);
                     recommendationtable.ShowGroupCOneTwoThree();
            }
            else if ((normalusergui.getProjecttype().getSelectedItem().toString()=="Buildings Construction")
                  && (normalusergui.getProjectsize().getSelectedItem().toString()=="Mega")
                  && (normalusergui.getContracttype().getSelectedItem().toString()=="Design and Built")
                  && (normalusergui.getKindoforganization().getSelectedItem().toString()=="Contractor")
                  && (normalusergui.getProjectlocation().getSelectedItem().toString()=="Remote Location")) {
                
                     normalusergui.setVisible(false);
                   //  recommendationtable.setLocation(normalusergui.getX() ,normalusergui.getY());
                     recommendationtable.setVisible(true);
                     recommendationtable.ShowGroupDOneTwoThree();
            }
            
            /*********************************************** end Group One Two Three***********************************/ 
            
             /*********************************************** start Group One Three ***********************************/
           else if ((normalusergui.getProjecttype().getSelectedItem().toString()=="Infrastructure/Tunnels")
                  && (normalusergui.getProjectsize().getSelectedItem().toString()=="Mega")
                  && (normalusergui.getContracttype().getSelectedItem().toString()=="EPC")
                  && (normalusergui.getKindoforganization().getSelectedItem().toString()=="Client/Consultant")
                  && (normalusergui.getProjectlocation().getSelectedItem().toString()=="Remote Location") ) {
                
                     
                     normalusergui.setVisible(false);
                   //  recommendationtable.setLocation(normalusergui.getX() ,normalusergui.getY());
                     recommendationtable.setVisible(true);
                     recommendationtable.ShowGroupAOneThree();
              }
            else if ((normalusergui.getProjecttype().getSelectedItem().toString()=="Infrastructure/Tunnels")
                  && (normalusergui.getProjectsize().getSelectedItem().toString()=="Mega")
                  && (normalusergui.getContracttype().getSelectedItem().toString()=="EPC")
                  && (normalusergui.getKindoforganization().getSelectedItem().toString()=="Contractor")
                  && (normalusergui.getProjectlocation().getSelectedItem().toString()=="Remote Location")) {
                
                     normalusergui.setVisible(false);
                    // recommendationtable.setLocation(normalusergui.getX() ,normalusergui.getY());
                     recommendationtable.setVisible(true);
                     recommendationtable.ShowGroupBOneThree();
            }
            else if ((normalusergui.getProjecttype().getSelectedItem().toString()=="Buildings Construction")
                  && (normalusergui.getProjectsize().getSelectedItem().toString()=="Mega")
                  && (normalusergui.getContracttype().getSelectedItem().toString()=="EPC")
                  && (normalusergui.getKindoforganization().getSelectedItem().toString()=="Client/Consultant")
                  && (normalusergui.getProjectlocation().getSelectedItem().toString()=="Remote Location")) {
                
                     normalusergui.setVisible(false);
                   //  recommendationtable.setLocation(normalusergui.getX() ,normalusergui.getY());
                     recommendationtable.setVisible(true);
                     recommendationtable.ShowGroupCOneThree();
            }
            else if ((normalusergui.getProjecttype().getSelectedItem().toString()=="Buildings Construction")
                  && (normalusergui.getProjectsize().getSelectedItem().toString()=="Mega")
                  && (normalusergui.getContracttype().getSelectedItem().toString()=="EPC")
                  && (normalusergui.getKindoforganization().getSelectedItem().toString()=="Contractor")
                  && (normalusergui.getProjectlocation().getSelectedItem().toString()=="Remote Location")) {
                
                     normalusergui.setVisible(false);
                  //   recommendationtable.setLocation(normalusergui.getX() ,normalusergui.getY());
                     recommendationtable.setVisible(true);
                     recommendationtable.ShowGroupDOneThree();
            }
            
            /*********************************************** end Group One Three***********************************/
            
            
            
            
             /*********************************************** start Group Two ***********************************/
           else if ((normalusergui.getProjecttype().getSelectedItem().toString()=="Infrastructure/Tunnels")
                  && (normalusergui.getProjectsize().getSelectedItem().toString()=="Medium")
                  && (normalusergui.getContracttype().getSelectedItem().toString()=="Design and Built")
                  && (normalusergui.getKindoforganization().getSelectedItem().toString()=="Client/Consultant")
                  && (normalusergui.getProjectlocation().getSelectedItem().toString()=="Non-Remote Location") ) {
                
                     
                     normalusergui.setVisible(false);
                   //  recommendationtable.setLocation(normalusergui.getX() ,normalusergui.getY());
                     recommendationtable.setVisible(true);
                     recommendationtable.ShowGroupATwo();
              }
            else if ((normalusergui.getProjecttype().getSelectedItem().toString()=="Infrastructure/Tunnels")
                  && (normalusergui.getProjectsize().getSelectedItem().toString()=="Medium")
                  && (normalusergui.getContracttype().getSelectedItem().toString()=="Design and Built")
                  && (normalusergui.getKindoforganization().getSelectedItem().toString()=="Contractor")
                  && (normalusergui.getProjectlocation().getSelectedItem().toString()=="Non-Remote Location")) {
                
                     normalusergui.setVisible(false);
                  //   recommendationtable.setLocation(normalusergui.getX() ,normalusergui.getY());
                     recommendationtable.setVisible(true);
                     recommendationtable.ShowGroupBTwo();
            }
            else if ((normalusergui.getProjecttype().getSelectedItem().toString()=="Buildings Construction")
                  && (normalusergui.getProjectsize().getSelectedItem().toString()=="Medium")
                  && (normalusergui.getContracttype().getSelectedItem().toString()=="Design and Built")
                  && (normalusergui.getKindoforganization().getSelectedItem().toString()=="Client/Consultant")
                  && (normalusergui.getProjectlocation().getSelectedItem().toString()=="Non-Remote Location")) {
                
                     normalusergui.setVisible(false);
                  //   recommendationtable.setLocation(normalusergui.getX() ,normalusergui.getY());
                     recommendationtable.setVisible(true);
                     recommendationtable.ShowGroupCTwo();
            }
            else if ((normalusergui.getProjecttype().getSelectedItem().toString()=="Buildings Construction")
                  && (normalusergui.getProjectsize().getSelectedItem().toString()=="Medium")
                  && (normalusergui.getContracttype().getSelectedItem().toString()=="Design and Built")
                  && (normalusergui.getKindoforganization().getSelectedItem().toString()=="Contractor")
                  && (normalusergui.getProjectlocation().getSelectedItem().toString()=="Non-Remote Location")) {
                
                     normalusergui.setVisible(false);
                 //    recommendationtable.setLocation(normalusergui.getX() ,normalusergui.getY());
                     recommendationtable.setVisible(true);
                     recommendationtable.ShowGroupDTwo();
            }
            
            /*********************************************** end Group Two***********************************/
            
            
            
            
            /*********************************************** start Group Two Three***********************************/
           else if ((normalusergui.getProjecttype().getSelectedItem().toString()=="Infrastructure/Tunnels")
                  && (normalusergui.getProjectsize().getSelectedItem().toString()=="Medium")
                  && (normalusergui.getContracttype().getSelectedItem().toString()=="Design and Built")
                  && (normalusergui.getKindoforganization().getSelectedItem().toString()=="Client/Consultant")
                  && (normalusergui.getProjectlocation().getSelectedItem().toString()=="Remote Location") ) {
                
                     
                     normalusergui.setVisible(false);
                  //   recommendationtable.setLocation(normalusergui.getX() ,normalusergui.getY());
                     recommendationtable.setVisible(true);
                     recommendationtable.ShowGroupATwoThree();
              }
            else if ((normalusergui.getProjecttype().getSelectedItem().toString()=="Infrastructure/Tunnels")
                  && (normalusergui.getProjectsize().getSelectedItem().toString()=="Medium")
                  && (normalusergui.getContracttype().getSelectedItem().toString()=="Design and Built")
                  && (normalusergui.getKindoforganization().getSelectedItem().toString()=="Contractor")
                  && (normalusergui.getProjectlocation().getSelectedItem().toString()=="Remote Location")) {
                
                     normalusergui.setVisible(false);
                  //   recommendationtable.setLocation(normalusergui.getX() ,normalusergui.getY());
                     recommendationtable.setVisible(true);
                     recommendationtable.ShowGroupBTwoThree();
            }
            else if ((normalusergui.getProjecttype().getSelectedItem().toString()=="Buildings Construction")
                  && (normalusergui.getProjectsize().getSelectedItem().toString()=="Medium")
                  && (normalusergui.getContracttype().getSelectedItem().toString()=="Design and Built")
                  && (normalusergui.getKindoforganization().getSelectedItem().toString()=="Client/Consultant")
                  && (normalusergui.getProjectlocation().getSelectedItem().toString()=="Remote Location")) {
                
                     normalusergui.setVisible(false);
                 //    recommendationtable.setLocation(normalusergui.getX() ,normalusergui.getY());
                     recommendationtable.setVisible(true);
                     recommendationtable.ShowGroupCTwoThree();
            }
            else if ((normalusergui.getProjecttype().getSelectedItem().toString()=="Buildings Construction")
                  && (normalusergui.getProjectsize().getSelectedItem().toString()=="Medium")
                  && (normalusergui.getContracttype().getSelectedItem().toString()=="Design and Built")
                  && (normalusergui.getKindoforganization().getSelectedItem().toString()=="Contractor")
                  && (normalusergui.getProjectlocation().getSelectedItem().toString()=="Remote Location")) {
                
                     normalusergui.setVisible(false);
                  //   recommendationtable.setLocation(normalusergui.getX() ,normalusergui.getY());
                     recommendationtable.setVisible(true);
                     recommendationtable.ShowGroupDTwoThree();
            }
            
            /*********************************************** end Group Two Three***********************************/
            
            
            /*********************************************** start Group Three***********************************/
           else if ((normalusergui.getProjecttype().getSelectedItem().toString()=="Infrastructure/Tunnels")
                  && (normalusergui.getProjectsize().getSelectedItem().toString()=="Medium")
                  && (normalusergui.getContracttype().getSelectedItem().toString()=="EPC")
                  && (normalusergui.getKindoforganization().getSelectedItem().toString()=="Client/Consultant")
                  && (normalusergui.getProjectlocation().getSelectedItem().toString()=="Remote Location") ) {
                
                     
                     normalusergui.setVisible(false);
                  //   recommendationtable.setLocation(normalusergui.getX() ,normalusergui.getY());
                     recommendationtable.setVisible(true);
                     recommendationtable.ShowGroupAThree();
              }
            else if ((normalusergui.getProjecttype().getSelectedItem().toString()=="Infrastructure/Tunnels")
                  && (normalusergui.getProjectsize().getSelectedItem().toString()=="Medium")
                  && (normalusergui.getContracttype().getSelectedItem().toString()=="EPC")
                  && (normalusergui.getKindoforganization().getSelectedItem().toString()=="Contractor")
                  && (normalusergui.getProjectlocation().getSelectedItem().toString()=="Remote Location")) {
                
                     normalusergui.setVisible(false);
                 //    recommendationtable.setLocation(normalusergui.getX() ,normalusergui.getY());
                     recommendationtable.setVisible(true);
                     recommendationtable.ShowGroupBThree();
            }
            else if ((normalusergui.getProjecttype().getSelectedItem().toString()=="Buildings Construction")
                  && (normalusergui.getProjectsize().getSelectedItem().toString()=="Medium")
                  && (normalusergui.getContracttype().getSelectedItem().toString()=="EPC")
                  && (normalusergui.getKindoforganization().getSelectedItem().toString()=="Client/Consultant")
                  && (normalusergui.getProjectlocation().getSelectedItem().toString()=="Remote Location")) {
                
                     normalusergui.setVisible(false);
                  //   recommendationtable.setLocation(normalusergui.getX() ,normalusergui.getY());
                     recommendationtable.setVisible(true);
                     recommendationtable.ShowGroupCThree();
            }
            else if ((normalusergui.getProjecttype().getSelectedItem().toString()=="Buildings Construction")
                  && (normalusergui.getProjectsize().getSelectedItem().toString()=="Medium")
                  && (normalusergui.getContracttype().getSelectedItem().toString()=="EPC")
                  && (normalusergui.getKindoforganization().getSelectedItem().toString()=="Contractor")
                  && (normalusergui.getProjectlocation().getSelectedItem().toString()=="Remote Location")) {
                
                     normalusergui.setVisible(false);
                 //    recommendationtable.setLocation(normalusergui.getX() ,normalusergui.getY());
                     recommendationtable.setVisible(true);
                     recommendationtable.ShowGroupDThree();
            }
            
            /*********************************************** end Group Three***********************************/
            
            
            
             /*********************************************** start Group One Two Three***********************************/
            else if ((normalusergui.getProjecttype().getSelectedItem().toString()=="Infrastructure/Tunnels")
                  && (normalusergui.getProjectsize().getSelectedItem().toString()=="Mega")
                  && (normalusergui.getContracttype().getSelectedItem().toString()=="Design and Built")
                  && (normalusergui.getKindoforganization().getSelectedItem().toString()=="Client/Consultant")
                  && (normalusergui.getProjectlocation().getSelectedItem().toString()=="Remote Location") ) {
                
                     
                     normalusergui.setVisible(false);
                  //   recommendationtable.setLocation(normalusergui.getX() ,normalusergui.getY());
                     recommendationtable.setVisible(true);
                     recommendationtable.ShowGroupAOneTwoThree();
              }
            else if ((normalusergui.getProjecttype().getSelectedItem().toString()=="Infrastructure/Tunnels")
                  && (normalusergui.getProjectsize().getSelectedItem().toString()=="Mega")
                  && (normalusergui.getContracttype().getSelectedItem().toString()=="Design and Built")
                  && (normalusergui.getKindoforganization().getSelectedItem().toString()=="Contractor")
                  && (normalusergui.getProjectlocation().getSelectedItem().toString()=="Remote Location")) {
                
                     normalusergui.setVisible(false);
                   //  recommendationtable.setLocation(normalusergui.getX() ,normalusergui.getY());
                     recommendationtable.setVisible(true);
                     recommendationtable.ShowGroupBOneTwoThree();
            }
            else if ((normalusergui.getProjecttype().getSelectedItem().toString()=="Buildings Construction")
                  && (normalusergui.getProjectsize().getSelectedItem().toString()=="Mega")
                  && (normalusergui.getContracttype().getSelectedItem().toString()=="Design and Built")
                  && (normalusergui.getKindoforganization().getSelectedItem().toString()=="Client/Consultant")
                  && (normalusergui.getProjectlocation().getSelectedItem().toString()=="Remote Location")) {
                
                     normalusergui.setVisible(false);
                 //    recommendationtable.setLocation(normalusergui.getX() ,normalusergui.getY());
                     recommendationtable.setVisible(true);
                     recommendationtable.ShowGroupCOneTwoThree();
            }
           
            else if ((normalusergui.getProjecttype().getSelectedItem().toString()=="Buildings Construction")
                  && (normalusergui.getProjectsize().getSelectedItem().toString()=="Mega")
                  && (normalusergui.getContracttype().getSelectedItem().toString()=="Design and Built")
                  && (normalusergui.getKindoforganization().getSelectedItem().toString()=="Contractor")
                  && (normalusergui.getProjectlocation().getSelectedItem().toString()=="Remote Location")) {
                
                     normalusergui.setVisible(false);
                   //  recommendationtable.setLocation(normalusergui.getX() ,normalusergui.getY());
                     recommendationtable.setVisible(true);
                     recommendationtable.ShowGroupDOneTwoThree();
            }
        }
            
            /*********************************************** end Group One Two Three***********************************/
        
           else{
                
                     JOptionPane.showMessageDialog(null, "You Must Select All Project Details");
            } 
        }
     
    
        if (e.getSource() == recommendationtable.getBack()){
         if  (fileD.canRead()){
             ToCloseFiles(fileD);
         }
         else if (fileE.canRead()){
            ToCloseFiles(fileE);
         }
         else if (fileF.canRead()){
              ToCloseFiles(fileF);
         }
         else if (fileG.canRead()){
              ToCloseFiles(fileG);
         }
         else if (fileH.canRead()){
              ToCloseFiles(fileH);
         }
          else {
             System.out.println("All Files are not open");
         }
        
                 recommendationtable.setVisible(false);
                 normalusergui.setLocation(recommendationtable.getX() ,recommendationtable.getY());
                  normalusergui.setVisible(true);
                  normalusergui.getProjecttype().setSelectedItem(null);
                 normalusergui.getProjectsize().setSelectedItem(null);
                 normalusergui.getContracttype().setSelectedItem(null);
                 normalusergui.getKindoforganization().setSelectedItem(null);
                  normalusergui.getProjectlocation().setSelectedItem(null);
           
        }
        if (e.getSource() == recommendationtable.getLogout()){
            if  (fileD.canRead()){
             ToCloseFiles(fileD);
         }
         else if (fileE.canRead()){
            ToCloseFiles(fileE);
         }
         else if (fileF.canRead()){
              ToCloseFiles(fileF);
         }
         else if (fileG.canRead()){
              ToCloseFiles(fileG);
         }
         else if (fileH.canRead()){
              ToCloseFiles(fileH);
         }
          else {
             System.out.println("All Files are not open");
         }
             recommendationtable.setVisible(false);
              logingui.setLocation(recommendationtable.getX() ,recommendationtable.getY());
             logingui.setVisible(true);
             logingui.getUsername().setText("");
             logingui.getPassword().setText("");
            
        }
       
        
        if (e.getSource() == recommendationtable.getShowPDF()){
            
          
              Path checkpathD=Paths.get("D:\\");
              Path checkpathE=Paths.get("E:\\");
              Path checkpathF=Paths.get("F:\\");
              Path checkpathG=Paths.get("G:\\");
              Path checkpathH=Paths.get("H:\\");
            if (Files.exists(checkpathD)) {
                 if (fileD.canRead()){
                    fileD.delete();
              }
                 InsertIntoPDF("D:\\Recommendation.pdf");
           
            }
            else if (Files.exists(checkpathE)) {
                 if (fileE.canRead()){
                    fileE.delete();
              }
                 InsertIntoPDF("E:\\Recommendation.pdf");
           
                }
            else if (Files.exists(checkpathF)) {
                 if (fileF.canRead()){
                    fileF.delete();
              }
                 InsertIntoPDF("F:\\Recommendation.pdf");
           
                }
            else if (Files.exists(checkpathG)) {
                 if (fileG.canRead()){
                    fileG.delete();
              }
                 InsertIntoPDF("G:\\Recommendation.pdf");
           
                }
           else if (Files.exists(checkpathH)) {
                 if (fileH.canRead()){
                    fileH.delete();
              }
                 InsertIntoPDF("H:\\Recommendation.pdf");
           
                }
            else {
                System.out.println("Not Exist");
            }
            }
        
        
        
           if (e.getSource() == recommendationtable.getGeneratepdf()){
                Document doc=new Document();
                JFileChooser j= new JFileChooser();
                int returnValue=j.showOpenDialog(recommendationtable);
                if ( returnValue == JFileChooser.APPROVE_OPTION) {
                     File fileToSave = j.getSelectedFile();
                    try {
                   
                        PdfWriter writer =PdfWriter.getInstance(doc, new FileOutputStream(fileToSave+".pdf"));
                        doc.open();
                       
                        PdfPTable tb1=new PdfPTable(9);
                        tb1.setWidthPercentage(110);
                        PdfPCell Title=new PdfPCell(new Paragraph("Risk Identification",  FontFactory.getFont(FontFactory.TIMES_BOLD , 40 , Font.BOLD , Color.black)));
                        PdfPCell inputs=new PdfPCell(new Paragraph("Kind Of Oragnization: " + normalusergui.getKindoforganization().getSelectedItem() +"\n \n \n" 
                              + "Project Type: " + normalusergui.getProjecttype().getSelectedItem() +"\n \n \n" 
                                + "Project Size: " + normalusergui.getProjectsize().getSelectedItem() +"\n \n \n"
                                + "Contract Type: " + normalusergui.getContracttype().getSelectedItem() +"\n \n \n"
                                 + "Project Location: " + normalusergui.getProjectlocation().getSelectedItem() +"\n \n \n",  FontFactory.getFont(FontFactory.TIMES_BOLD , 14 , Font.BOLD , Color.black)));
                        PdfPCell IDCell=new PdfPCell(new Paragraph("Number",  FontFactory.getFont(FontFactory.TIMES_BOLD , 14 , Font.BOLD , Color.black)));
                        PdfPCell cell1=new PdfPCell(new Paragraph("Recommended Risk Factors",  FontFactory.getFont(FontFactory.TIMES_BOLD , 18 , Font.BOLD , Color.black)));
                        PdfPCell cell2=new PdfPCell(new Paragraph("Recommended Response Strategy",  FontFactory.getFont(FontFactory.TIMES_BOLD , 18 , Font.BOLD , Color.black)));
                        PdfPCell cell3=new PdfPCell(new Paragraph( "Recommended Response Action" ,  FontFactory.getFont(FontFactory.TIMES_BOLD , 18 , Font.BOLD , Color.black)));
                        Title.setBackgroundColor(Color.WHITE);
                        inputs.setBackgroundColor(Color.WHITE);
                        cell1.setBackgroundColor(Color.WHITE);
                        cell2.setBackgroundColor(Color.WHITE);
                        cell3.setBackgroundColor(Color.WHITE);
                        IDCell.setBackgroundColor(Color.WHITE);
                        Title.setColspan(9);
                        inputs.setColspan(9);
                        cell3.setColspan(3);
                        cell1.setColspan(3);
                        cell2.setColspan(2);
                        Title.setPaddingTop(20);
                        inputs.setPaddingTop(20);
                        cell1.setPaddingTop(20);
                        cell2.setPaddingTop(20);
                        cell3.setPaddingTop(20);
                        IDCell.setPaddingTop(30);
                        Title.setPaddingBottom(20);
                        inputs.setPaddingBottom(20);
                        cell1.setPaddingBottom(20);
                        cell2.setPaddingBottom(20);
                        cell3.setPaddingBottom(20);
                        IDCell.setPaddingBottom(20);
                        Title.setPaddingLeft(10);
                        inputs.setPaddingLeft(10);
                        cell1.setPaddingLeft(10);
                        cell2.setPaddingLeft(10);
                        cell3.setPaddingLeft(10);
                        IDCell.setPaddingLeft(7);
                        Title.setBorderWidth(3);
                        Title.setBorderColor(Color.WHITE);
                        inputs.setBorderWidth(3);
                        inputs.setBorderColor(Color.WHITE);
                        cell1.setBorderWidth(3);
                        cell1.setBorderColor(Color.blue);
                        cell2.setBorderWidth(3);
                        cell2.setBorderColor(Color.blue);
                        cell3.setBorderWidth(3);
                        cell3.setBorderColor(Color.blue);
                        IDCell.setBorderWidth(3);
                        IDCell.setBorderColor(Color.blue);
                        tb1.addCell(Title);
                        tb1.addCell(inputs);
                        tb1.addCell(IDCell);
                        tb1.addCell(cell1);
                        tb1.addCell(cell2);
                        tb1.addCell(cell3);
                        
                        int c=1;
                        for (int i=0;i<recommendationtable.getTable().getRowCount();i++) {
                            PdfPCell IDCellin=new PdfPCell(new Paragraph(Integer.toString(c++),  FontFactory.getFont(FontFactory.TIMES_BOLD , 18 , Font.BOLD , Color.black)));
                            PdfPCell cell4=new PdfPCell(new Paragraph(recommendationtable.getTable().getValueAt(i, 0).toString(), FontFactory.getFont(FontFactory.TIMES_BOLD , 15 , Font.PLAIN , Color.black )));
                            PdfPCell cell5=new PdfPCell(new Paragraph(recommendationtable.getTable().getValueAt(i, 1).toString() , FontFactory.getFont(FontFactory.TIMES_BOLD , 15 , Font.PLAIN , Color.black)));
                            PdfPCell cell6=new PdfPCell(new Paragraph(recommendationtable.getTable().getValueAt(i, 2).toString() , FontFactory.getFont(FontFactory.TIMES_BOLD , 15 , Font.PLAIN , Color.black)));
                            
                            cell4.setBackgroundColor(Color.WHITE);
                            cell5.setBackgroundColor(Color.WHITE);
                            cell6.setBackgroundColor(Color.WHITE);
                            IDCellin.setBackgroundColor(Color.WHITE);
                            cell4.setPaddingTop(20);
                            cell5.setPaddingTop(20);
                            cell6.setPaddingTop(20);
                            IDCellin.setPaddingTop(20);
                            cell4.setPaddingBottom(20);
                            cell5.setPaddingBottom(20);
                            cell6.setPaddingBottom(20);
                            IDCellin.setPaddingBottom(20);
                            cell4.setPaddingLeft(10);
                            cell5.setPaddingLeft(10);
                            cell6.setPaddingLeft(10);
                            IDCellin.setPaddingLeft(10);
                            cell6.setExtraParagraphSpace(20);
                            cell4.setBorderWidth(3);
                            cell4.setBorderColor(Color.blue);
                            cell5.setBorderWidth(3);
                            cell5.setBorderColor(Color.blue);
                            cell6.setBorderWidth(3);
                            cell6.setBorderColor(Color.blue);
                            IDCellin.setBorderWidth(3);
                            IDCellin.setBorderColor(Color.blue);
                            cell6.setColspan(3);
                            cell4.setColspan(3);
                            cell5.setColspan(2);
                            tb1.addCell(IDCellin );
                            tb1.addCell(cell4 );
                            tb1.addCell(cell5 );
                            tb1.addCell(cell6 );
                           
                        }
                        
                        doc.add(tb1);
                    
                        doc.close();
                    } catch (DocumentException ex) {
                        Logger.getLogger(ProjectManagementController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(ProjectManagementController.class.getName()).log(Level.SEVERE, null, ex);
                    } 
           }    
              }
          
          
               
        
         
          if (e.getSource() == recommendationtable.getGenerateexcel()){
              
              try {
                  JFileChooser j=new JFileChooser();
                   int x=j.showSaveDialog(recommendationtable);
                   File saveFile=j.getSelectedFile();
                   
              
                 if (saveFile !=null) {
                       saveFile=new File(saveFile.toString()+".xlsx");
                       Workbook wb=new XSSFWorkbook();
                       org.apache.poi.ss.usermodel.Sheet sheet=wb.createSheet("Recommendation");
                       org.apache.poi.ss.usermodel.Row rowCol=sheet.createRow(0);
                       
                       for (int i=0;i<recommendationtable.getTable().getColumnCount();i++) {
                           org.apache.poi.ss.usermodel.Cell cell=rowCol.createCell(i);
                           cell.setCellValue(recommendationtable.getTable().getColumnName(i).toString());
                       }
                       
                       for (int i=1;i<recommendationtable.getTable().getRowCount();i++) {
                           org.apache.poi.ss.usermodel.Row row=sheet.createRow(i);
                           for (int k=0;k<recommendationtable.getTable().getColumnCount();k++) {
                           org.apache.poi.ss.usermodel.Cell cell=row.createCell(k);
                           
                           if (recommendationtable.getTable().getValueAt(i, k) !=null) {
                               cell.setCellValue(recommendationtable.getTable().getValueAt(i, k).toString());
                           }
                           }
                       }
                       
                       
                       FileOutputStream out;
                      
                          out = new FileOutputStream(new File(saveFile.toString()));
                           wb.write(out);
                          wb.close();
                          out.close();
                          
                      } 
              }catch (FileNotFoundException ex) {
                          Logger.getLogger(ProjectManagementController.class.getName()).log(Level.SEVERE, null, ex);
                      }
                     
                      catch (IOException ex) {
                          Logger.getLogger(ProjectManagementController.class.getName()).log(Level.SEVERE, null, ex);
                      }
                       
                       
            } 
          }
    
    void addView(LogInGUI logingui) {
       this.logingui=logingui;
       
       
    }
    void addStart(StartGUI startgui) {
       this.startgui=startgui;
    }
    void addNormal(NormalUserGUI normalusergui) {
       this.normalusergui=normalusergui;
    }
     void addTable(RecommendationTable recommendationtable) {
       this.recommendationtable=recommendationtable;
    
    }
     void addForm(SuperUserGUI superusergui) {
       this.superusergui=superusergui;
    }
}
