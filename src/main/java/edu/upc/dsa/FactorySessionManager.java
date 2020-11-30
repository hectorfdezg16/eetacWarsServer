package edu.upc.dsa;


import java.sql.Connection;

public class FactorySessionManager {
    public static SessionManager openSession(){

        //nos falta hacer la conexion base datos
        //connection conn/ahora ya está hecho
        Connection connection = getConnection();

        //información importante cuando yo inicie conexión con la base datos
        //he de conectarme al puerto 3307, ya que 3306 no me funciona

        SessionManager session = new SessionManagerImpl(connection);
        return session;
    }

    //aqui vamos a crear la conexión a nuestra base datos con el driver
    public static Connection getConnection(){
        Connection connection = null;
        return connection;
    }
}
