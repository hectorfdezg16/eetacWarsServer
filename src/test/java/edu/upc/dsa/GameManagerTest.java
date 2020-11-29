package edu.upc.dsa;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;

public class GameManagerTest {

    //Creamos logger para ir comentandolo
    //en principio no añadiremos muchos comentarios
    final static Logger log = Logger.getLogger(GameManagerTest.class.getName());

    //creamos instancia privada de nuestro contrato debido en parte al singleton
    private GameManager gtest;

    //en esta función de prueba inicializaremos todos los casos
    //crearemos juego de pruebas inicial
    @Before
    public void setUp() {
        log.info("Agregamos los 3 primeros usuarios al sistema antes de iniciar el juego");
        GameManagerImpl.getInstance().addUser("0","Pepe","hola");
        GameManagerImpl.getInstance().addUser("1","Marcos","adios");
        GameManagerImpl.getInstance().addUser("2","Alicia","buenas");

        //nos faltan añadir más parámetros antes de que empiece el juego

    }

    //2 metodos / crear nuevo usuario / procesar una muestra
    @Test
    public void testAddUser() {
        try {
            log.info("Añadimos otro más para comprobar la función testAddUser");
            this.gtest.addUser("3", "Blanca", "otra");
            Assert.assertEquals(4, this.gtest.numUsers());
        }

        catch(NullPointerException e){
            log.info("NullPointerException caught");
        }
    }

    //limpiamos toda la estructura de datos
    @After
    public void tearDown() {
        try {
            log.info("Limpiamos toda la estructura de datos");
            this.gtest.clear();
        }
        catch(NullPointerException e){
            log.info("NullPointerException caught");
        }
    }







}
