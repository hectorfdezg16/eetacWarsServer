package edu.upc.dsa;

import edu.upc.dsa.models.*;

import java.util.ArrayList;
import java.util.HashMap;

public interface GameManager {

    //iremos añadiendo funciones en el contrato según vayamos viendo el juego
    //y las prestaciones que se necesita para el usuario

    //añadimos el login
    public User logIn(String username, String password) throws UserNotFoundException, UserAlreadyConectedException;

    //mirar id de un usuario
    public int getIdUser(String username, String password) throws UserNotFoundException;

    //creamos la función de añadir un usuario
    public void addUser(String id, String username, String password);
    //añadir un item al juego / como admin / no como user
    public Item addItem(Item item);
    public Item addItem(String id, String name);

    //obtener un usuario
    public User getUser(String id) throws UserNotFoundException;
    //obtener un item
    public Item getItem(String id) throws ItemNotFoundException;
    //tenemos que implementar también la función devuélveme todos los usuarios
    public HashMap<String,User> findAll();
    public ArrayList<Item> findAllItems();

    //eliminar un usuario un hash solo se puede hacer si se tiene acceso a la contraseña
    //osea solo un admin o él mismo
    public void deleteUser(String id, String password) throws UserNotFoundException;
    public void deleteUserAdmin(String id) throws UserNotFoundException;
    public void deleteItem(String id) throws  ItemNotFoundException;
    //modificar parametros de un usuario
    public User updateUser(User u) throws UserNotFoundException;
    public Item updateItem(Item i) throws ItemNotFoundException;

    //numeros de usuario en el sistema
    public int numUsers();
    public int numItems();

    //seguimos añadiendo objetos en los linkedlist
    public void addEnemy(String id, String level, String total, String idMap) throws MapFullException, MapNotFoundException;
    public void addAlly(String id, String level, String total, String idMap) throws MapFullException, MapNotFoundException;

    //salvar el estado de la partida y recuperar ese estado
    public void saveStatus(String idPlay, String username);
    public int getStatus(String idPlay, String username) throws PlayNotFoundException;

    //limpiamos todas las estructuras de datos
    public void clear();

}
