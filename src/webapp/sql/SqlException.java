package webapp.sql;

import webapp.exception.StorageException;
import org.postgresql.util.PSQLException;
import webapp.exception.ExistStorageException;

import java.sql.SQLException;

public class SqlException {
    private SqlException() {
    }

    public static StorageException convertException(SQLException e) {
        if (e instanceof PSQLException) {
            if (e.getSQLState().equals("23505")) {
                return new ExistStorageException(null);
            }
        }
        return new StorageException(e);
    }
}