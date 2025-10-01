package org.soft2.server;

import io.javalin.Javalin;
import org.soft2.MessageHandler;
import org.soft2.exceptions.APIException;
import org.soft2.exceptions.ExceptionHandler;

import static io.javalin.apibuilder.ApiBuilder.*;

public class JavalinBuilder {

    public static void startServer(int port) {
        Javalin.create(config -> {
            config.router.apiBuilder(() -> {
                path("/api", () -> {
                    path("/TODO_INSERT_OUR_OWN_PATHS_AND_ROUTES", () -> {
                        get((context) -> {
                            //Our handlers go here. This is just an example.
                        });
                    });
                    path("/TODO_INSERT_MORE_OF_OUR_OWN_PATHS_AND_ROUTES", () -> {

                    });
                    path("sendTestMessage", () -> {
                        post(MessageHandler::sendTestMessage);
                    });
                });
            });
            //Insert other configuration here if needed.
        })
                .exception(APIException.class, (ExceptionHandler::apiExceptionHandler))
                .start(port);
    }
}
