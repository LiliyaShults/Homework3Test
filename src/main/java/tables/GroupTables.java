package tables;

import db.MySQLConnector;
import objects.Group;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class GroupTables extends AbsTables {

   public GroupTables() {
      super("`group`");
      columns = new HashMap<>();
      columns.put("id", "bigint PRIMARY KEY AUTO_INCREMENT");
      columns.put("name", "varchar(15)");
      columns.put("id_curator", "bigint");
      create();
   }
   public ArrayList<Group> selectAll(){
      String sqlQuery = String.format("SELECT * FROM %s", tableName);
      return selectByQuery(sqlQuery);
   }

   private ArrayList<Group> selectByQuery(String sqlQuery){
      ArrayList<Group> groups = new ArrayList<>();
      db = new MySQLConnector();
      ResultSet rs = db.executeRequestWithAnswer(sqlQuery);
      try {
         while (rs.next()) {

            groups.add(new Group(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getInt("id_curator")
            ));
         }
      } catch (SQLException ex) {
         ex.printStackTrace();
      } finally {
         db.close();
      }
      return groups;
   }

   public void insert(Group group){
      db = new MySQLConnector();
      String sqlQuery = String.format("INSERT INTO %s (id, name, id_curator) " +
                      "VALUES ('%d', '%s','%d')",
              tableName, group.getId(), group.getName(), group.getId_curator());
      db.executeRequest(sqlQuery);
      db.close();
   }

   public void selectGroupInfo () throws SQLException {
      String sqlQuery = String.format("SELECT g.name, c.fio  FROM `group` g join curator c on g.id_curator = c.id");
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

}
