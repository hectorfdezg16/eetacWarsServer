package edu.upc.dsa.services;

import edu.upc.dsa.GameManager;
import edu.upc.dsa.GameManagerImpl;
import edu.upc.dsa.models.ExistantUserException;
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
import java.util.Collection;
import java.util.HashMap;
import java.util.logging.Logger;

@Api(value = "/users", description = "Endpoint to Text Service")
@Path("users")
public class UserService {

    //creamos variables logger para ir comentando el service del user
    final static Logger logger = Logger.getLogger(UserService.class.getName());

    //instancia privada para hacer el gservice
    private GameManager gservice;

    public UserService() throws Exception {
        this.gservice = GameManagerImpl.getInstance();
        if (gservice.numUsers() == 0) {
            this.gservice.addUser("Tatiana", "hola");
            this.gservice.addUser("Gabriel", "buenas");
            this.gservice.addUser("Kevin", "bye");
            this.gservice.addUser("Oscar", "hello");
            this.gservice.addUser("Miquel", "adios");
        }
    }

    //obtener todos los usuarios
    @GET
    @ApiOperation(value = "obtener lista de usuarios", notes = "x")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = User.class, responseContainer = "HashMap"),
    })
    @Path("/getUsers")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getAllUsers() {
        HashMap<String, User> users = this.gservice.findAll();
        GenericEntity<Collection<User>> entity = new GenericEntity<Collection<User>>(users.values()){};
        return Response.status(201).entity(entity).build();
    }

    //hacemos el post de un user /añadimos un usuario al servicio
    //y le damos una respuesta correcta al haberlo añadido
    @POST
    @ApiOperation(value = "registrar un usuario", notes = "x")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", responseContainer = "List"),
            @ApiResponse(code = 500, message="Existant user", responseContainer = "List")
    })

    @Path("/registerUser")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addUser(User user) {
        try {
            this.gservice.addUser(user.getUsername(), user.getPassword());
            return Response.status(201).build();
        }
        catch (ExistantUserException e){
            return Response.status(500).build();
        }
    }

    //este servicio lo utilizaremos para encontar un usuario según su id
    @GET
    @ApiOperation(value = "obtener usuario según su username", notes = "x")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = User.class),
            @ApiResponse(code = 404, message = "UserNotFoundException")
    })
    @Path("/getUser/{username}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getUser(@PathParam("username") String username) throws UserNotFoundException {
        //versión alternativa que de momento funciona
        User user=this.gservice.getUser(username);
        if(user==null) return Response.status(404).build();
        else return Response.status(201).entity(user).build();
    }

    //añadir un eliminar usuario
    @DELETE
    @ApiOperation(value = "eliminar un usuario", notes = "x")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "UserNotFoundException")
    })

    @Path("/deleteUser/{username}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteUser(@PathParam("username") String username) throws UserNotFoundException {
        User user = this.gservice.getUser(username);
        if(user == null) return Response.status(404).build();
        else this.gservice.deleteUserAdmin(username);
        return Response.status(201).build();
    }

    //por último hacemos un put de usuario
    @PUT
    @ApiOperation(value = "actualizar un usuario", notes = "x")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "UserNotFoundException")
    })

    @Path("/updateUser")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateUser(User u) throws UserNotFoundException {
        User user = this.gservice.updateUser(u);
        if(user == null) return Response.status(404).build();
        return Response.status(201).build();
    }
}
