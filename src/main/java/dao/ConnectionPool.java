package dao;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class ConnectionPool {
    private static final List<Connection> connections = new LinkedList<>();
    private static ConnectionPool pool;
    private static final int POOL_SIZE = 4;

    private ConnectionPool() {
        try {
            InitialContext initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            DataSource dataSource = (DataSource) envContext.lookup("jdbc/WT-DB");

            for (int i = 0; i < POOL_SIZE; i++) {
                connections.add(dataSource.getConnection());
            }
        } catch (SQLException | NamingException e) {
        }
    }

    public static ConnectionPool getConnectionPool() {
       return ConnectionPoolHolder.INSTANCE;
    }

    public Connection getConnection() {
        while (connections.size() == 0) {
            Thread.yield();
        }

        Connection connection = connections.stream().findFirst().orElseThrow(RuntimeException::new);
        connections.remove(connection);
        return connection;
    }

    public void returnConnection(Connection connection) {
        if (!connections.contains(connection)) {
            connections.add(connection);
        }
    }

    private static class ConnectionPoolHolder {
        static final ConnectionPool INSTANCE = new ConnectionPool();
    }
}
