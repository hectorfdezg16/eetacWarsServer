package edu.upc.dsa.services;


import edu.upc.dsa.GameManager;
import edu.upc.dsa.GameManagerImpl;
import edu.upc.dsa.models.Item;
import edu.upc.dsa.models.User;
import edu.upc.dsa.models.UserNotFoundException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;

@Api(value = "/Game", description = "Endpoint to Text Service")
@Path("Game")
public class GameService {

    private GameManager gservice;


    public GameService() {
        this.gservice = GameManagerImpl.getInstance();
    }


    //hacemos el post de un user /añadimos un usuario al servicio
    //y le damos una respuesta correcta al haberlo añadido
    @POST
    @ApiOperation(value = "add user", notes = "x")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful")
    })

    @Path("/adduser")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addUser(User user) {
        String id = user.getId();
        String username = user.getUsername();
        String password = user.getPassword();
        this.gservice.addUser(id, username, password);

        return Response.status(201).build();
        //me sale error 415 undomented
    }
    /*@GET
    @ApiOperation(value = "obtener usuario con un id", notes = "x")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = User.class, responseContainer = "Obtener un usuario según id"),
            @ApiResponse(code = 404, message = "UserNotFoundException")
    })
    @Path("/getusers/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getUser(@PathParam("id") String id) {
        User user = null;
        try{
            user = this.gservice.getUser(id);
            GenericEntity<HashMap<String, User>> entity = new GenericEntity<HashMap<String, User>>(user){};
            return Response.status(201).entity(entity).build();

        }catch (UserNotFoundException e){
            e.printStackTrace();
            return Response.status(404).build();
        }
    }*/


    //añadimos también un laboratorio al servicio con un post
    /*@POST
    @ApiOperation(value = "add a item", notes = "x")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful")
    })
    @Path("/additem")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addItem(Item item) {
        String id = item.getId();
        String total = item.getTotal();
        String value = item.getValue();
        String idPlayer = item.getIdPlayer();
        this.gservice.addItem(id, total, value, idPlayer);

        return Response.status(201).build();
    }*/

}
