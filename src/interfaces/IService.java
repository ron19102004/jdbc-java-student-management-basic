package interfaces;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IService<X> {
    ArrayList<X> findAll() throws SQLException;
    boolean save(X x) throws SQLException;
    boolean remove(long id);
    boolean update(X x);
}
