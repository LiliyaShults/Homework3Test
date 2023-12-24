package tables;

import db.IDBConnector;
import db.MySQLConnector;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class AbsTables {
    public String tableName;
    public Map<String, String> columns;
    IDBConnector db;

    public AbsTables(String tableName) {
        this.tableName = tableName;
        this.columns = columns;
    }

    public void create() {
        String sqlRequest = String.format("CREATE TABLE IF NOT EXISTS %s (%s)", this.tableName, convertMapColumnsToString());
        db = new MySQLConnector();
        db.executeRequest(sqlRequest);
        db.close();
    }

    private String convertMapColumnsToString() {
        final String result = columns.entrySet().stream()
                .map((Map.Entry entry) -> String.format("%s %s", entry.getKey(), entry.getValue()))
                .collect(Collectors.joining(", "));
        return result;
    }

  /* public create(){
        String sqlRequest = String.format("SELECT this.tableName, this.tableName.%s, this.tableName.%s FROM this.tableName" +
                        "JOIN this.tableName.%s ON this.tableName.%d = this.tableName.%d " +
                        "JOIN this.tableName.%s ON this.tableName.%d = this.tableName.%d",
                tableName, convertMapColumnsToString());
        db = new MySQLConnector();
        db.executeRequest(sqlRequest);
        db.close();
    }*/
  /*public void count(){
      String sql = ("SELECT COUNT * FROM %s WHERE sex ='%s'", tableName,type) ;
      ResultSet rs = prest.executeQuery();
      while (rs.next()) {
          records = rs.getInt(1);
      }
  }*/


    public void writeAll(){
        db = new MySQLConnector();
        final String sqlRequest =String.format("SELECT * FORM %s", tableName);
        ResultSet rs = db.executeRequestWithAnswer(sqlRequest);

        try{
            int columns = rs.getMetaData().getColumnCount();

            while (rs.next()) {
                for (int i = 1; i <= columns; i++) {
                    System.out.print(rs.getString(i) + "\t");
                }
                System.out.println();
            }
            } catch (SQLException ex) {
                ex.printStackTrace();
            } finally {
                db.close();
            }

    }

    }
