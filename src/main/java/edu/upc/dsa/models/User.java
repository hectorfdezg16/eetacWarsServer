package edu.upc.dsa.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//Usuario = Jugador
public class User {


    //aplicando modelo de usuario del juego
    //en el UML teniamos usuario y jugador
    //lo modificaremos de tal manera que usuario y jugador sean lo mismo
    String id;
    String username;
    String mail;
    String name;
    String lastname;
    String city;
    String password;

    //atributos originalmente para jugador pero finalmente para usuario
    //de momento solo añadimos estos dos, se pueden añadir mucho más depende la complejidad del juego
    String life;
    String level;

    //los atributos/listas que posee un usuario
    //un usuario tendrá items: espadas, escudos y también un número de partidas
    //tanto para items como para partidas vamos a crear arrays ya que creemos
    //habrán muchas armas, pociones y muchisimas partidas de cada usuario
    List <Play> playsByPlayer;
    List <Item> itemsByPlayer;

    //constructor vacio para el json
    public User(String id) {
    }

    //constructor vacio error para el 415
    //preguntarle porque aun no se porque motivo???
    public User() {
    }

    //constructor a parte para añadir usuarios con lo básico
    public User(String username, String password) {
        //this.id = id;
        this.username = username;
        this.password = password;
        this.playsByPlayer = new ArrayList<>();
        this.itemsByPlayer = new ArrayList<>();
    }

    //ahora pondremos los getters y setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLife() {
        return life;
    }

    public void setLife(String life) {
        this.life = life;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    //a continuación pondremos el tostring por si hace falta mostrarlo por consola


    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
