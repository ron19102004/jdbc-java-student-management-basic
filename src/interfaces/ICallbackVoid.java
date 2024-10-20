package interfaces;

import java.sql.SQLException;

@FunctionalInterface
public interface ICallbackVoid {
    void exec() throws SQLException;
}
