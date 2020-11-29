package edu.upc.dsa;

import edu.upc.dsa.models.PlayNotFoundException;
import edu.upc.dsa.models.User;
import edu.upc.dsa.models.UserNotFoundException;

public interface GameManager {

    //iremos añadiendo funciones en el contrato según vayamos viendo el juego
    //y las prestaciones que se necesita para el usuario

    //creamos la función de añadir un usuario
    public void addUser(String id, String username, String password);

    //obtener un usuario
    public User getUser(String id) throws UserNotFoundException;

    //eliminar un usuario un hash solo se puede hacer si se tiene acceso a la contraseña
    //osea solo un admin o él mismo
    public User deleteUser(String id, String password) throws UserNotFoundException;

    //modificar parametros de un usuario
    public User updateUser() throws UserNotFoundException;

    //numeros de usuario en el sistema
    public int numUsers();

    //en los arrays que yo he creado empiezo a añadir funciones
    public void addPlay(String id, String positionX, String positionY);
    public void addItem(String id, String name, String total, String idPlayer) throws UserNotFoundException;

    //seguimos añadiendo objetos en los linkedlist
    public void addMap(String id, String level, String total);
    public void addEnemy(String id, String level, String total);
    public void addAlly(String id, String level, String total);

    //salvar el estado de la partida y recuperar ese estado
    public void saveStatus(String idPlay, String username);
    public int getStatus(String idPlay, String username) throws PlayNotFoundException;


    //limpiamos todas las estructuras de datos
    public void clear();

}
