package edu.upc.dsa.models;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Play {

    //atributos modelo Partida
    String id;
    String positionX;
    String positionY;
    String totalKills;
    String total;
    String time;

    //hay que recordar que una partida tendrá una serie de mapas
    //como no habrá muchos mapas en el juego nos hemos decidido por una LinkedList
    List<Map> mapsByPlay;

    //constructor vacio, basico y getters y setters
    public Play() {
    }

    //constructor básico
    public Play(String id, String positionX, String positionY) {
        this.id = id;
        this.positionX = positionX;
        this.positionY = positionY;
        this.mapsByPlay = new LinkedList<>();
    }

    //getters y setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPositionX() {
        return positionX;
    }

    public void setPositionX(String positionX) {
        this.positionX = positionX;
    }

    public String getPositionY() {
        return positionY;
    }

    public void setPositionY(String positionY) {
        this.positionY = positionY;
    }

    public String getTotalKills() {
        return totalKills;
    }

    public void setTotalKills(String totalKills) {
        this.totalKills = totalKills;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
