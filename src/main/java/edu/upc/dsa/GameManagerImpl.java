package edu.upc.dsa;

import edu.upc.dsa.models.*;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

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
    private int numPlays, numItems, numUsers, numMaps;

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
        numUsers = 0;
        numMaps = 0;
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

    //implementamos el logIn
    public User logIn(String username, String password) throws UserNotFoundException, UserAlreadyConectedException{
        //de la función getIdUser que hemos creado abajo obtenemos el idenficador
        //identificador deseado dando username y password
        int idUser = getIdUser(username, password);
        //en esta parte también se crea una sesión y una implementación de sesión
        //instanciamos primeras variables
        User user = null;
        SessionManager session = null;

        //intentamos abrir sesión
        try{
            //no funcionará ya que la función get no está bien implementada
            session = FactorySessionManager.openSession();
            user = (User)session.get(User.class, idUser);
        }
        catch (Exception e){
            logger.error("Error al intentar abrir la sesión: " +e.getMessage());
        }
        finally {
            session.close();
        }

        return user;
    }

    //mirar id de un usuario pasando su nombre de usuario y su contraseña
    public int getIdUser(String username, String password) throws UserNotFoundException{
        //crear una sesión
        SessionManager session = null;

        int idUser;

        try{
            //porque hacemos una factoria???
            //motivo== porque tendremos que abrir muchas sesiones para el usuario
            //conectarse, desconectarse

            //inicamos una sesión
            session = FactorySessionManager.openSession();

            idUser = session.getId(User.class, username, password);
        }
        catch(Exception e){
            logger.error("Este usuario no existe: " +e.getMessage());
            throw new UserNotFoundException();
        }
        finally{
            session.close();
        }

        return idUser;
    }


    //funcion que implementa el contrato UserManager
    //función añadir usuario
    //Add a new User
    public void addUser(String id, String username, String password){
        //por si hubiese mismo id cosa poco probable
        if(!this.users.containsKey(id)){
            this.users.put(id, new User(id, username, password));
        }
    }

    @Override
    public int numUsers() {
        logger.info("Numero de usuarios en el sistema: " +this.users.size());
        return this.users.size();
    }

    @Override
    public void addPlay(String id, String positionX, String positionY) throws UserNotFoundException {

    }

    @Override
    public void addItem(String id, String name, String total, String idPlayer) throws UserNotFoundException {
        //User = Player
        /*User user = null;
        Item item;
        for(int i = 0; i<this.numUsers; i++){
            if(idPlayer.equals(this.arra))
        }*/
    }

    @Override
    public void addMap(String id, String level, String total) {

    }

    @Override
    public void addEnemy(String id, String name, String total, String idMap) throws MapFullException, MapNotFoundException{
        Map map = null;
        for(int i = 0; i<this.numMaps; i++){
            if(idMap.equals(this.mapsByPlay.get(i).getId())){
                map = this.mapsByPlay.get(i);
            }
        }
        logger.info("Map: " +map);

        //si hay un mapa
        if (map != null){
            LinkedList<Enemy> enemies = (LinkedList<Enemy>) map.getEnemiesByMap();
            int maxenemies = Integer.parseInt(map.getTotal());
            if(enemies.size() < maxenemies){
                map.addEnemy(new Enemy(id, name, total, idMap));
            }
            else{
                logger.error("¡El mapa que ústed quiere para añadir un enemigo está lleno!");
                throw new MapFullException();
            }
        }
        else{
            logger.error("¡El mapa no existe!");
            throw new MapNotFoundException();
        }
    }

    @Override
    public void addAlly(String id, String name, String total, String idMap) throws MapFullException, MapNotFoundException {
        Map map = null;
        for(int i = 0; i<this.numMaps; i++){
            if(idMap.equals(this.mapsByPlay.get(i).getId())){
                map = this.mapsByPlay.get(i);
            }
        }
        logger.info("Map: " +map);

        //si hay un mapa
        if (map != null){
            LinkedList<Ally> allies = (LinkedList<Ally>) map.getAlliesByMap();
            int maxallies = Integer.parseInt(map.getTotal());
            if(allies.size() < maxallies){
                map.addAlly(new Ally(id, name, total, idMap));
            }
            else{
                logger.error("¡El mapa que ústed quiere para añadir un enemigo está lleno!");
                throw new MapFullException();
            }
        }
        else{
            logger.error("¡El mapa no existe!");
            throw new MapNotFoundException();
        }
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

    //este eliminar usuario solo lo puede hacer el propio usuario o un administrador
    @Override
    public void deleteUser(String id, String password) throws UserNotFoundException {
        User user = null;
        //comprobar que la contraseña sea la del id introducido
        if (this.users.containsKey(id) && user.getPassword().equals(password)) {
            this.users.remove(id);
            logger.info("El usuario se ha eliminado correctamente");
        } else {
            logger.info("¡Este usuario no existe!");
            throw new UserNotFoundException();
        }
    }

    //falta implementar adduser/ de momento no lo veo
    @Override
    public User updateUser() throws UserNotFoundException {
        return null;
    }


}
