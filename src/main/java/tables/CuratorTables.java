package tables;

import db.MySQLConnector;
import objects.Curator;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class CuratorTables extends AbsTables {

    public CuratorTables() {
        super("curator");
        columns = new HashMap<>();
        columns.put("fio", "varchar(100)");
        columns.put("id","bigint PRIMARY KEY AUTO_INCREMENT");
        create();
    }

    public ArrayList<Curator> selectAll(){
        String sqlQuery = String.format("SELECT * FROM %s", tableName);
        return selectByQuery(sqlQuery);
    }

    public ArrayList<Curator> selectByFio(String fio){
        String sqlQuery = String.format("SELECT * FROM %s WHERE fio = '%s'",
                tableName, fio);
        return selectByQuery(sqlQuery);
    }

    private ArrayList<Curator> selectByQuery(String sqlQuery){
        ArrayList<Curator> curators = new ArrayList<>();
        db = new MySQLConnector();
        ResultSet rs = db.executeRequestWithAnswer(sqlQuery);
        try {

            while (rs.next()) {

                curators.add(new Curator(
                        rs.getString("fio"),
                        rs.getInt("id")
                ));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            db.close();
        }
        return curators;
    }

    public void insert(Curator curator){
        db = new MySQLConnector();
        String sqlQuery = String.format("INSERT INTO %s (fio, id) " + "VALUES ('%s', '%d')",
                tableName, curator.getFio(), curator.getId());
        db.executeRequest(sqlQuery);
        db.close();
    }

    public void update(Curator curators){
        db = new MySQLConnector();
        String sqlQuery = String.format("UPDATE %s SET " +
                        "fio='%s' WHERE id = %d ",
                tableName,
                curators.getFio(),
                curators.getId());
        db.executeRequest(sqlQuery);
        db.close();
    }

    public void delete(int id){
        db = new MySQLConnector();
        String sqlQuery = String.format("DELETE FROM %s WHERE id='%d'",
                "curator", id);
        db.executeRequest(sqlQuery);
        db.close();
    }

}