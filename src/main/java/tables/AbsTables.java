package tables;

import db.IDBConnector;
import db.MySQLConnector;

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
        String sqlRequest = String.format("CREATE TABLE IF NOT EXISTS %s (%s)",
                this.tableName, convertMapColumnsToString());
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

    }
