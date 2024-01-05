package db;

import java.sql.ResultSet;

interface IDBConnector {
    void executeRequest(String response);
    ResultSet executeRequestWithAnswer(String response);
    void close();
}
