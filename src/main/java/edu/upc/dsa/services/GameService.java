package edu.upc.dsa.services;


import edu.upc.dsa.GameManager;
import edu.upc.dsa.GameManagerImpl;
import edu.upc.dsa.models.Item;
import edu.upc.dsa.models.ItemNotFoundException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Api(value = "/game", description = "Endpoint to Text Service")
@Path("game")
public class GameService {

    private GameManager gservice;


    public GameService() {
        this.gservice = GameManagerImpl.getInstance();
        if (gservice.numItems() == 0) {
            this.gservice.addItem("1", "poción");
            this.gservice.addItem("13", "escudo");
            this.gservice.addItem("6", "espada");
            this.gservice.addItem("7", "armadura");
            this.gservice.addItem("4", "casco");
        }
    }

    //vamos a obtener todos los objetos del juego
    @GET
    @ApiOperation(value = "obtener todos los ítems", notes = "x")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Item.class, responseContainer = "Item class"),
    })
    @Path("/getItems")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getAllItems() {
        List<Item> items = this.gservice.findAllItems();
        GenericEntity<List<Item>> entity = new GenericEntity<List<Item>>(items){};
        return Response.status(201).entity(entity).build();
    }

    //vamos a obtener un ítem del juego según su id
    @GET
    @ApiOperation(value = "obtener ítem según su id", notes = "x")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Item.class, responseContainer = "Item"),
            @ApiResponse(code = 404, message = "ItemNotFoundException")
    })
    @Path("/getItem/{idItem}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getUser(@PathParam("idItem") String id) throws ItemNotFoundException {
        //versión alternativa que de momento funciona
        Item item=this.gservice.getItem(id);
        if(item==null) return Response.status(404).build();
        else return Response.status(201).entity(item).build();
    }

    //haremos también el additem
    @POST
    @ApiOperation(value = "añadir un ítem", notes = "x")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful")
    })

    @Path("/addItem")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addItem(Item item) {
        String id = item.getId();
        String name = item.getName();
        this.gservice.addItem(id, name);

        return Response.status(201).build();
    }

    //eliminar un ítem del juego
    //después haremos eliminar un ítem por parte del usuario
    @DELETE
    @ApiOperation(value = "eliminar un ítem", notes = "x")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "ItemNotFoundException")
    })

    @Path("/deleteItem/{idItem}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteUser(@PathParam("idItem") String id) throws ItemNotFoundException {
        Item item = this.gservice.getItem(id);
        if(item == null) return Response.status(404).build();
        else this.gservice.deleteItem(id);
        return Response.status(201).build();
    }

    //por último hacemos un put de usuario
    @PUT
    @ApiOperation(value = "actualizar un objeto", notes = "x")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "ItemNotFoundException")
    })

    @Path("/updateItem")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateItem(Item i) throws ItemNotFoundException {
        Item item = this.gservice.updateItem(i);
        if(item == null) return Response.status(404).build();
        return Response.status(201).build();
    }

}
