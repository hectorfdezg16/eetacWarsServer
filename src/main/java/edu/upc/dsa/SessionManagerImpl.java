package edu.upc.dsa;

import edu.upc.dsa.models.UserNotFoundException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

//crearemos el manager de session después veremos la importancia que le damos
public class SessionManagerImpl implements SessionManager{

    //como siempre variable logger para informar de todos los procesos
    final static Logger logger = Logger.getLogger(GameManagerImpl.class.getName());

    //creamos variable conexión, evidentemente si no estamos conectados no podemos crear sesión
    private final Connection connection;

    // constructor para inicializar variables
    public SessionManagerImpl(Connection connection){
        //inicializamos esta variable ya que sino no la prodremos utilizar
        this.connection = connection;
    }


    public void save(Object entity){
        //todo contenido base datos
    }

    //función cerrar una sesión
    public void close(){
        try{
            this.connection.close();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public int getId(Class theClass, String username, String password) throws UserNotFoundException {
        //seguimos sin poder implementar metodos porque no hemos conectado con la base de datos
        return 0;
    }

    @Override
    public Object get(Class theClass, int id) {
        //no vamos a implementar esta función porque necesita de la base de datos todavía
        Object entity = null;
        return entity;
    }
}
