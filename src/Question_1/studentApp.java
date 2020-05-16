/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Question_1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author HP
 */
public class studentApp {

    public void insertUpdateDeleteStudent(char operation, Integer id, String name,
            String major, Double grade) {

        Connection con = MyConnection.getConnection();
        PreparedStatement ps;
        if (operation == 'i') //for insert
        {
            try {
                ps = con.prepareStatement("INSERT INTO student(name,major,grade) VALUES (?,?,?)");
                ps.setString(1, name);
                ps.setString(2, major);
                ps.setDouble(3, grade);

                if (ps.executeUpdate() > 0) {
                    JOptionPane.showMessageDialog(null, "New Student Added");
                }
            } catch (SQLException ex) {
                Logger.getLogger(studentApp.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        if (operation == 'u') //for update
        {
            try {
                ps = con.prepareStatement("UPDATE `student` SET `name`= ?,`major`= ?,`grade`= ? WHERE `id` = ?");
                ps.setString(1, name);
                ps.setString(2, major);
                ps.setDouble(3, grade);
                ps.setInt(4, id);

                if (ps.executeUpdate() > 0) {
                    JOptionPane.showMessageDialog(null, "Student Data Updated");
                }
            } catch (SQLException ex) {
                Logger.getLogger(studentApp.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        if (operation == 'd') //for delete
        {
            int yesORno = JOptionPane.showConfirmDialog(null, "The score will be also deleted", "Delete Student", JOptionPane.OK_CANCEL_OPTION, 0);
            if (yesORno == 0) {
                try {
                    ps = con.prepareStatement("DELETE FROM `student` WHERE `id`= ?");
                    ps.setInt(1, id);

                    if (ps.executeUpdate() > 0) {
                        JOptionPane.showMessageDialog(null, "Student Deleted");
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(studentApp.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }

    }

    public void Fill_Student_Table(JTable table) {
        Connection con = MyConnection.getConnection();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("SELECT * FROM `student` ");

            ResultSet rs = ps.executeQuery();
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            Object[] row;
            while (rs.next()) {
                row = new Object[4];
                row[0] = rs.getString(1);
                row[1] = rs.getString(2);
                row[2] = rs.getString(3);
                row[3] = rs.getString(4);
                model.addRow(row);
            }
        } catch (SQLException ex) {
            Logger.getLogger(studentApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void insertCourse(Integer id, String name,
            String room) {

        Connection con = MyConnection.getConnection();
        PreparedStatement ps;

        try {
            ps = con.prepareStatement("INSERT INTO course(name,room) VALUES (?,?)");
            ps.setString(1, name);
            ps.setString(2, room);

            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "New Course Added");
            }
        } catch (SQLException ex) {
            Logger.getLogger(studentApp.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    public void Fill_Course_combo(JComboBox combo) {
        Connection con = MyConnection.getConnection();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("SELECT * FROM `course`");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                combo.addItem(rs.getString(2));
            }
        } catch (SQLException ex) {
            Logger.getLogger(studentApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void insertCourse_student(Integer Sid,
            Integer Cid, Double semester) {

        Connection con = MyConnection.getConnection();
        PreparedStatement ps;

        try {
            ps = con.prepareStatement("INSERT INTO registration(student_id,course_id,semester) VALUES (?,?,?)");
            ps.setInt(1, Sid);
            ps.setInt(2, Cid);
            ps.setDouble(3, semester);

            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "course Added");
            }
        } catch (SQLException ex) {
            Logger.getLogger(studentApp.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public int getCourseId(String courselabel) {
        int courseid = 0;
        Connection con = MyConnection.getConnection();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("SELECT * FROM `course` WHERE `name` = ?");
            ps.setString(1, courselabel);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                courseid = rs.getInt("Id");
            }
        } catch (SQLException ex) {
            Logger.getLogger(studentApp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return courseid;

    }

    public void Fill_registration_Table(JTable table) {
        Connection con = MyConnection.getConnection();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("SELECT * FROM `registration`");
            ResultSet rs = ps.executeQuery();
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            Object[] row;
            while (rs.next()) {
                row = new Object[3];
                row[0] = rs.getInt(1);
                row[1] = rs.getInt(2);
                row[2] = rs.getDouble(3);

                model.addRow(row);
            }
        } catch (SQLException ex) {
            Logger.getLogger(studentApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
