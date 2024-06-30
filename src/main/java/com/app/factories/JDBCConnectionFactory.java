package com.app.factories;

import com.app.utils.PropertiesManager;

import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class JDBCConnectionFactory {
    private final static String USERNAME = "db.username";

    private final static String PASSWORD = "db.password";

    private final static String URL = "db.url";

    private final static String POOL_SIZE = "db.pool.size";

    private final static int DEFAULT_POOL_SIZE = 10;

    private static BlockingQueue<Connection> connectionPool;

    private static List<Connection> sourceConnections;

    private JDBCConnectionFactory() {}

    static {
        try {
            initConnectionPool();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void initConnectionPool() throws SQLException {
        String poolSize = PropertiesManager.getPropertyByKey(POOL_SIZE);
        int size = poolSize == null ? DEFAULT_POOL_SIZE : Integer.parseInt(poolSize);
        connectionPool = new ArrayBlockingQueue<>(size);
        sourceConnections = new ArrayList<>(size);

        for (int i = 0; i < size; i++) {
            Connection connection = openConnection();
            Connection proxyConnection = (Connection) Proxy.newProxyInstance(JDBCConnectionFactory.class.getClassLoader(), new Class[]{Connection.class},
                    (proxy, method, args) -> method.getName().equals("close") ? connectionPool.add((Connection) proxy) : method.invoke(connection, args));
            connectionPool.add(proxyConnection);
            sourceConnections.add(connection);
        }
    }

    private static Connection openConnection() throws SQLException{
        return DriverManager.getConnection(PropertiesManager.getPropertyByKey(URL),
                PropertiesManager.getPropertyByKey(USERNAME),
                PropertiesManager.getPropertyByKey(PASSWORD));
    }

    public static Connection get() throws SQLException {
        try {
            return connectionPool.take();
        } catch (InterruptedException e) {
            throw new SQLException(e);
        }
    }

    public static void closePool() throws SQLException {
        for (Connection connection : sourceConnections)
            connection.close();
    }
}
