package webapp.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SqlHelper {
    private final ConnectionFactory connectionFactory;

    public SqlHelper(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public void getConnection() throws SQLException {
        Connection conn = connectionFactory.getConnection();
    }

    public void prepareStatement(Connection conn, String sql) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(sql);
    }

}