package edu.upc.dsa.services;

import edu.upc.dsa.GameManager;
import edu.upc.dsa.GameManagerImpl;
import edu.upc.dsa.models.User;
import edu.upc.dsa.models.UserAlreadyConectedException;
import edu.upc.dsa.models.UserNotFoundException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.awt.*;
import java.util.logging.Logger;

//en este fichero de la api se llevara a cabo todas las operaciones del usuario con la web
@Api(value="/auth", description = "Servicio del usuario")
@Path("/auth")
public class AuthenticationService {

    //creamos la variable para utilizar el log4j
    final static Logger logger = Logger.getLogger(AuthenticationService.class.getName());

    //tenemos que crear variable para poder utilizar el GameManager
    private GameManager gameManager;

    public AuthenticationService(){
        //buscar esta instancia de autenticacion
        this.gameManager = GameManagerImpl.getInstance();
    }

    @POST
    @ApiOperation(value = "cargar el perfil", notes = "escribir el nombre de usuario y la contraseña para loggearse")
    @ApiResponses(value = {
            @ApiResponse(code= 201, message = "Succesful", response= User.class, responseContainer="hacer un login"),
            @ApiResponse(code= 404, message = "Usuario no existe"),
            @ApiResponse(code= 402, message = "Usuario ya esta conectado en otro dispositivo")
    })

    //parece que el login nos da buenos resultados
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response logIn(User user){
        String username = user.getUsername();
        String password = user.getPassword();

        try{
            User u = this.gameManager.logIn(username, password);
            return Response.status(201).entity(u).build();
        } catch (UserNotFoundException e){
            e.printStackTrace();
            return Response.status(404).build();
        } catch (UserAlreadyConectedException e){
            e.printStackTrace();
            return Response.status(402).build();
        }
    }

}
