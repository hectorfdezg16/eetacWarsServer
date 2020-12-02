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
import java.util.List;

@Api(value = "/game", description = "Endpoint to Text Service")
@Path("game")
public class GameService {

    private GameManager gservice;


    public GameService() {
        this.gservice = GameManagerImpl.getInstance();
        if (gservice.numItems() == 0) {
            this.gservice.addItem("poción","3");
            this.gservice.addItem("escudo","2");
            this.gservice.addItem("espada","1");
            this.gservice.addItem("armadura","2");
            this.gservice.addItem("casco","3");
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
    @ApiOperation(value = "obtener ítem según su nombre", notes = "x")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Item.class),
            @ApiResponse(code = 404, message = "ItemNotFoundException")
    })
    @Path("/getItem/{name}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getItem(@PathParam("name") String name) throws ItemNotFoundException {
        //versión alternativa que de momento funciona
        Item item=this.gservice.getItem(name);
        if(item==null) return Response.status(404).build();
        else return Response.status(201).entity(item).build();
    }

    //haremos también el additem
    @POST
    @ApiOperation(value = "crear un ítem", notes = "x")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful")
    })

    @Path("/addItem")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addItem(Item item) {
        String name = item.getName();
        String total = item.getTotal();
        this.gservice.addItem(name, total);

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

    @Path("/deleteItem/{name}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteItem(@PathParam("name") String name) throws ItemNotFoundException {
        Item item = this.gservice.getItem(name);
        if(item == null) return Response.status(404).build();
        else this.gservice.deleteItem(name);
        return Response.status(201).build();
    }

    //por último hacemos un put del objeto
    @PUT
    @ApiOperation(value = "actualizar un ítem", notes = "x")
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
