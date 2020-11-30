package edu.upc.dsa;

import edu.upc.dsa.models.UserNotFoundException;

public interface SessionManager<E> {
    public void save(Object entity);
    public void close();
    //obtener id de cualquier clase dando su username y password
    public int getId(Class theClass, String username, String password) throws UserNotFoundException;

    //creamos otra función intersante para crear un objeto de cualquier clase
    Object get(Class theClass, int id);
}
