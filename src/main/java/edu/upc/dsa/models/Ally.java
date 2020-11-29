package edu.upc.dsa.models;

//modelo Aliado = Enemigo
public class Ally {

    //atributos del modelo Aliado
    String id;
    String name;
    String type;
    String total;
    String life;
    String attack;
    String level;

    //constructor vacio
    public Ally() {
    }

    //constructor vacio
    public Ally(String id, String name, String total) {
        this.id = id;
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

    public String getLife() {
        return life;
    }

    public void setLife(String life) {
        this.life = life;
    }

    public String getAttack() {
        return attack;
    }

    public void setAttack(String attack) {
        this.attack = attack;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
