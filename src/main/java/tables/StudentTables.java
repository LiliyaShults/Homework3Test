package tables;

import db.MySQLConnector;
import objects.Student;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class StudentTables extends AbsTables {

    public StudentTables() {
        super("student");
        columns = new HashMap<>();
        columns.put("id", "bigint PRIMARY KEY AUTO_INCREMENT");
        columns.put("fio", "varchar(100)");
        columns.put("sex", "varchar(9)");
        columns.put("id_group", "int");
        create();
    }
    public ArrayList<Student> selectAll(){
        String sqlQuery = String.format("SELECT * FROM %s", tableName);
        return selectByQuery(sqlQuery);
    }

    public ArrayList<Student> selectBySex(String type){
        String sqlQuery = String.format("SELECT * FROM %s WHERE sex = '%s'", tableName, type);
        return selectByQuery(sqlQuery);
    }


    public Integer selectCount(String type) throws SQLException {
        String sqlQuery = String.format("SELECT COUNT(*) AS recordCount FROM %s WHERE sex ='%s'", tableName, type);
        db = new MySQLConnector();
        ResultSet rs = db.executeRequestWithAnswer(sqlQuery);
        rs.next();
        int count = rs.getInt("recordCount");
        db.close();
        return count;
    }

    public void selectStudensInfo () throws SQLException {
        String sqlQuery = String.format("SELECT s.fio, g.name, c.fio  FROM student s " +
                "join `group` g on s.id_group = g.id join curator c on g.id_curator = c.id");
        db = new MySQLConnector();
        ResultSet rs = db.executeRequestWithAnswer(sqlQuery);
        try {
            while (rs.next()) {
                System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            db.close();
        }
    }

    public void selectStudentsInGroup(String groupName) throws SQLException {
        String sqlQuery = String.format("SELECT s.fio, g.name FROM student s join `group` g " +
                "on s.id_group = g.id WHERE g.name  = '%s'", groupName);
        db = new MySQLConnector();
        ResultSet rs = db.executeRequestWithAnswer(sqlQuery);
        try {
            while (rs.next()) {
                System.out.println(rs.getString(1) + " " + rs.getString(2));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            db.close();
        }
    }

    private ArrayList<Student> selectByQuery(String sqlQuery){
        ArrayList<Student> students = new ArrayList<>();
        db = new MySQLConnector();
        ResultSet rs = db.executeRequestWithAnswer(sqlQuery);
        try {
            while (rs.next()) {
                students.add(new Student(
                        rs.getInt("id"),
                        rs.getString("fio"),
                        rs.getString("sex"),
                        rs.getInt("id_group")
                ));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            db.close();
        }
        return students;
    }

    public void insert(Student student){
        db = new MySQLConnector();
        String sqlQuery = String.format("INSERT INTO %s (id, fio, sex, id_group) " +
                        "VALUES ('%d', '%s', '%s', '%d')",
                tableName, student.getId(), student.getFio(),
                student.getSex(), student.getId_group());
        db.executeRequest(sqlQuery);
        db.close();
    }

    }

