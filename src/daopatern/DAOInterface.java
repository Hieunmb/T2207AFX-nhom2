package daopatern;

import java.util.ArrayList;

public interface DAOInterface<S> {
    ArrayList<S> getAll();
    boolean create(S classes);
    boolean update(S classes);
    boolean delete(S classes);
    S find(Integer id);
}
