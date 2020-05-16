/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Question_2;

import Question_1.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author HP
 */
public class student {
    public void Fill_Student_Table(JTable table,String query){
         Connection con = MyConnection.getConnection();
            PreparedStatement ps;
         try{
           ps = con.prepareStatement(query);
   //  "SELECT * FROM `student` "
           ResultSet rs =ps.executeQuery();        
           DefaultTableModel model =(DefaultTableModel) table.getModel();
           Object[] row;
           while(rs.next())
                {
                row = new Object[4];
                row[0] = rs.getString(1);
                row[1] = rs.getString(2);
                row[2] = rs.getString(3);
                row[3] = rs.getDouble(4);
                model.addRow(row);
                }
            } catch (SQLException ex) {
              Logger.getLogger(student.class.getName()).log(Level.SEVERE, null, ex);
          }
      }
    public void update_student(Integer id,String name,String major,Double grade){
       Connection con = MyConnection.getConnection();
         PreparedStatement ps;
            try 
               {
                   ps = con.prepareStatement("UPDATE `student` SET `name`= ?,`major`= ?,`grade`= ? WHERE `id` = ?");
                   ps.setString(1, name);
                   ps.setString(2, major);
                   ps.setDouble(3, grade);
                   ps.setInt(4, id);
                   
                    if(ps.executeUpdate() > 0){
                       JOptionPane.showMessageDialog(null, "Student Data Updated");
                     }
                } 
            catch (SQLException ex) 
                {
                     Logger.getLogger(student.class.getName()).log(Level.SEVERE,null,ex);
                }
        
        
    }
}
