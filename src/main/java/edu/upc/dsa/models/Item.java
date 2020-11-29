package edu.upc.dsa.models;

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

    //constructor vacio, con parametros, getters y setters
    public Item() {
    }

    //constructor basico
    //de momento tenemos dudas de poner en el basico nivel o no
    //lo veremos más adelante
    public Item(String id, String name, String total, String idPlayer) {
        this.id = id;
        this.name = name;
        this.total = total;
        this.idPlayer = idPlayer;
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
}
