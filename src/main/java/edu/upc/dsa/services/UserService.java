package edu.upc.dsa.services;

import edu.upc.dsa.GameManager;
import edu.upc.dsa.GameManagerImpl;
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
import java.util.List;

@Api(value = "/users", description = "Endpoint to Text Service")
@Path("users")
public class UserService {

    private GameManager gservice;

    public UserService() {
        this.gservice = GameManagerImpl.getInstance();
        if (gservice.numUsers() == 0) {
            this.gservice.addUser("3", "Tatiana", "hola");
            this.gservice.addUser("8", "Gabriel", "buenas");
            this.gservice.addUser("1", "Kevin", "bye");
            this.gservice.addUser("12", "Oscar", "hello");
            this.gservice.addUser("56", "Miquel", "adios");
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
    @ApiOperation(value = "añadir un usuario", notes = "x")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful")
    })

    @Path("/addUser")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addUser(User user) {
        String id = user.getId();
        String username = user.getUsername();
        String password = user.getPassword();
        this.gservice.addUser(id, username, password);

        return Response.status(201).build();
    }

    //este servicio lo utilizaremos para encontar un usuario según su id
    @GET
    @ApiOperation(value = "obtener usuario según su id", notes = "x")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = User.class),
            @ApiResponse(code = 404, message = "UserNotFoundException")
    })
    @Path("/getUser/{idUser}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getUser(@PathParam("idUser") String id) throws UserNotFoundException {
        /*User user = null;
        try {
            user = this.gservice.getUser(id);
            GenericEntity<User> entity = new GenericEntity<User>(user) {
            };
            return Response.status(201).entity(entity).build();

        } catch (UserNotFoundException e) {
            e.printStackTrace();
            return Response.status(404).build();
        }*/

        //versión alternativa que de momento funciona
        User user=this.gservice.getUser(id);
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

    @Path("/deleteUser/{idUser}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteUser(@PathParam("idUser") String id) throws UserNotFoundException {
        User user = this.gservice.getUser(id);
        if(user == null) return Response.status(404).build();
        else this.gservice.deleteUserAdmin(id);
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
