package edu.upc.dsa;

import edu.upc.dsa.models.*;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class GameManagerImpl implements GameManager {

    //llamamos al fichero de propiedades log4j
    final static Logger logger = Logger.getLogger(GameManagerImpl.class);

    //implementamos la fachada como Singleton
    private static GameManager instance;

    //inicializamos la matriz de usuarios
    private HashMap<String, User> users;

    //a parte de añadir el hash/tabla de usuarios
    //vamos a agregar todas las listas de las diferentes clases
    //listas de usuario
    private ArrayList<Play> playsByPlayer;
    private ArrayList<Item> itemsByPlayer;
    //agregamos números de los partidas iniciales e items iniciales
    //y los inicializamos en el constructor
    private int numPlays;
    private int numItems;

    //lista de partida
    private LinkedList<Map> mapsByPlay;

    //listas de mapa
    private LinkedList<Enemy> enemiesByMap;
    private LinkedList<Ally> alliesByMap;

    //constructor privado vacio de momento
    private GameManagerImpl() {

        //inicializamos número de items y partidas
        numPlays = 0;
        numItems = 0;
        //inicializamos en el constructor también las diferentes listas
        playsByPlayer = new ArrayList<>();
        itemsByPlayer = new ArrayList<>();
        mapsByPlay = new LinkedList<>();
        enemiesByMap = new LinkedList<>();
        alliesByMap = new LinkedList<>();
        users = new HashMap<>();

    }

    //aqui implementamos el singleton
    public static GameManager getInstance() {
        if (instance==null) instance = new GameManagerImpl();
        return instance;
    }

    //limpiar todas las estructuras de datos
    @Override
    public void clear() {
        instance=null;
        //linkedlist y arraylist funcionan de la misma manera
        playsByPlayer.clear();
        itemsByPlayer.clear();
        mapsByPlay.clear();
        enemiesByMap.clear();
        alliesByMap.clear();
        users.clear();
    }

    //funcion que implementa el contrato UserManager
    //función añadir usuario
    //Add a new User
    public void addUser(String id, String username, String password){
        this.users.put(id, new User(id, username, password));
    }

    @Override
    public int numUsers() {
        logger.info("Numero de usuarios en el sistema: " +this.users.size());
        return this.users.size();
    }

    @Override
    public void addPlay(String id, String positionX, String positionY) {

    }

    @Override
    public void addItem(String id, String name, String total, String idPlayer) throws UserNotFoundException {
        //desarrollar esta función que es meter un item en un usuario
    }

    @Override
    public void addMap(String id, String level, String total) {

    }

    @Override
    public void addEnemy(String id, String level, String total) {

    }

    @Override
    public void addAlly(String id, String level, String total) {

    }

    @Override
    public void saveStatus(String idPlay, String username) {

    }

    @Override
    public int getStatus(String idPlay, String username) throws PlayNotFoundException {
        return 0;
    }

    //a continuación a implementar todas las funciones creadas
    @Override
    public User getUser(String id) throws UserNotFoundException {
        User user = this.users.get(id);
        return user;
    }

    @Override
    public User deleteUser(String id, String password) throws UserNotFoundException {
        return null;
    }

    @Override
    public User updateUser() throws UserNotFoundException {
        return null;
    }





}
