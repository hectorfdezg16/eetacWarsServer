package edu.upc.dsa.models;

import java.util.List;

public class Item {

    //atributos del modelo Item
    String id;
    String name;
    String level;
    String type;
    //atributo total = cantidad de un item
    String total;
    //el atributo value viene conectado a través del nivel del jugador
    String value;
    //id del jugador que tiene estos items
    String idPlayer;

    //obtener la lista de usuarios que tienen un ítem en concreto
    List<User> itemsByUser;

    //constructor vacio para el json
    public Item(String id) {
    }

    //constructor vacio, con parametros, getters y setters
    public Item() {
    }

    public Item(String name, String total) {
        this.name = name;
        this.total = total;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getIdPlayer() {
        return idPlayer;
    }

    public void setIdPlayer(String idPlayer) {
        this.idPlayer = idPlayer;
    }

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", total='" + total + '\'' +
                '}';
    }
}
