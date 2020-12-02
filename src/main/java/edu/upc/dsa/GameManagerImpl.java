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
    public ArrayList<Item> items;
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
        this.numPlays = 0;
        this.numItems = 0;
        this.numUsers = 0;
        this.numMaps = 0;
        //inicializamos en el constructor también las diferentes listas
        this.playsByPlayer = new ArrayList<>();
        this.itemsByPlayer = new ArrayList<>();
        this.mapsByPlay = new LinkedList<>();
        this.enemiesByMap = new LinkedList<>();
        this.alliesByMap = new LinkedList<>();
        this.items=new ArrayList<>();
        this.users = new HashMap<>();

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
        this.playsByPlayer.clear();
        this.itemsByPlayer.clear();
        this.mapsByPlay.clear();
        this.enemiesByMap.clear();
        this.alliesByMap.clear();
        this.items.clear();
        this.users.clear();
    }

    //mirar id de un usuario pasando su nombre de usuario y su contraseña
    public int getIdUser(String username, String password) throws UserNotFoundException{
        User user= null;
        int idUser;

        if(!username.equals(user.getUsername()) && !password.equals(user.getPassword())) throw new UserNotFoundException();
        idUser= Integer.parseInt(user.getId());
        logger.info("Id del usuario "+username+ " es: "+idUser);
        return idUser;
    }


    //funcion que implementa el contrato UserManager
    //función añadir usuario
    //Add a new User
    public User addUser(String username, String password) throws ExistantUserException{
        //por si hubiese mismo id cosa poco probable
        User user = this.users.get(username);
        if(user!=null) throw new ExistantUserException();
        user = new User(username,password);
        this.users.put(username, user);
        logger.info("Nuevo usuario en el sistema: "+user.toString());
        return user;
    }

    public User getUserLogin(String username, String password) throws UserNotFoundException, PasswordNotMatchException{
        User user= this.users.get(username);
        if(user==null) throw new UserNotFoundException();
        if(!password.equals(user.getPassword())) throw new PasswordNotMatchException();
        logger.info("Usuario logeado: "+user.toString());
        return user;
    }

    //a continuación a implementar todas las funciones creadas
    @Override
    public User getUser(String username) throws UserNotFoundException{
        User user = this.users.get(username);
        return user;
    }

    @Override
    public int numUsers() {
        logger.info("Numero de usuarios en el sistema: " +this.users.size());
        return this.users.size();
    }

    @Override
    public int numItems() {
        logger.info("Numero de items en el sistema: " +this.items.size());
        return this.items.size();
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

    @Override
    public Item getItem(String name) throws ItemNotFoundException{
        logger.info("getItem("+name+")");

        for(Item item: this.items){
            if(item.getName().equals(name)){
                logger.info("getTrack("+name+"): " +item);
                return item;
            }
        }
        logger.warn("not found"+name);
        return null;
    }

    //devuélveme todos los usuarios
    @Override
    public HashMap<String, User> findAll() {
        return this.users;
    }

    //devélveme todos los items
    @Override
    public ArrayList<Item> findAllItems() {
        return this.items;
    }

    //este eliminar usuario solo lo puede hacer el propio usuario o un administrador
    @Override
    public void deleteUser(String username, String password) throws UserNotFoundException {
        User user = null;
        //comprobar que la contraseña sea la del id introducido
        if (this.users.containsKey(username) && user.getPassword().equals(password)) {
            this.users.remove(username);
            logger.info("El usuario se ha eliminado correctamente");
        } else {
            logger.info("¡Este usuario no existe!");
            throw new UserNotFoundException();
        }
    }

    @Override
    public void deleteUserAdmin(String username) throws UserNotFoundException{
            User user = this.getUser(username);
            if(user==null){
                logger.warn("not found" +user);
            }
            else logger.info(user+"deleted");

            this.users.remove(user.getUsername());
    }

    //eliminar item
    @Override
    public void deleteItem(String name) throws ItemNotFoundException{
        Item item = this.getItem(name);
        if(item==null){
            logger.warn("not found" +item);
        }
        else logger.info(item+"deleted");

        this.items.remove(item);
    }

    //falta implementar adduser/ de momento no lo veo
    @Override
    public User updateUser(User u) throws UserNotFoundException {
        User user = this.getUser(u.getUsername());

        if(user!=null){
            logger.info(u+" recibido");

            user.setUsername(u.getUsername());
            user.setPassword(u.getPassword());

            logger.info(user+" actualizado");
        }
        else {
            logger.warn("Usuario no encontrado");
            //throw new UserNotFoundException();
        }
        return user;
    }

    //actualizar objeto
    @Override
    public Item updateItem(Item i) throws ItemNotFoundException {
        Item item = this.getItem(i.getName());

        if(item!=null){
            logger.info(i+" recibido");

            item.setName(i.getName());
            item.setTotal(i.getTotal());

            logger.info(item+" actualizado");
        }
        else {
            logger.warn("Objeto no encontrado");
            //throw new ItemNotFoundException();
        }
        return item;
    }

    public Item addItem(Item item){
        this.items.add(item);
        return item;
    }

    //añadir item al juego
    public Item addItem(String name, String total){
        return this.addItem(new Item(name, total));
    }




}
