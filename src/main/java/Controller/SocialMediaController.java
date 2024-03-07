package Controller;

import static org.mockito.Mockito.lenient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import Model.Account;
import Model.Message;
import Service.AccountService;
import Service.MessageService;
import io.javalin.Javalin;
import io.javalin.http.Context;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller. The endpoints you will need can be
 * found in readme.md as well as the test cases. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 * 
 *  GET localhost:8080/accounts/{account_id}/messages : list all posts from a particular user
 *  POST localhost:8080/register : create a new account
 *  POST localhost:8080/login : verify login
 *  GET localhost:8080/messages : retrieve all messages
 *  GET localhost:8080/messages/{message_id} : retrieve all messages by Id
 *  POST localhost:8080/messages : create a new message
 *  PATCH localhost:8080/messages/{message_id} : update a message
 *  DELETE localhost:8080/messages/{message_id} : delete message
 */
public class SocialMediaController {
    /**
     * In order for the test cases to work, you will need to write the endpoints in the startAPI() method, as the test
     * suite must receive a Javalin object from this method.
     * @return a Javalin app object which defines the behavior of the Javalin controller.
     */
    public Javalin startAPI() {
        Javalin app = Javalin.create();
        app.get("/accounts/{account_id}", this::getAllMessagesFromUserHandler);
        app.post("/register", this::postAccountHandler);
        app.post("login", this::postLoginHandler);
        app.get("/messages", this::getAllMessagesHandler);
        app.get("/messages/{message_id}", this::getAllMessagesByIdHandler);
        app.post("/messages", this::postMessageHandler); 
        app.patch("/messages/{message_id}", this::patchMessageHandler);
        app.delete("/messages/{message_id}", this::deleteMessageHandler);
        return app;
    }

    /**
     * This is an example handler for an example endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
  


    // private void exampleHandler(Context context) {
    //     context.json("sample text");
    // }


}