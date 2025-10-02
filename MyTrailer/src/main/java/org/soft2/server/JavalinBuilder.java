package org.soft2.server;

import io.javalin.Javalin;
import org.soft2.MessageHandler;
import org.soft2.exceptions.APIException;
import org.soft2.exceptions.ExceptionHandler;
import org.soft2.handlers.LocationController;

import static io.javalin.apibuilder.ApiBuilder.*;

public class JavalinBuilder {

    public static void startServer(int port) {
        Javalin.create(config -> {
            config.router.apiBuilder(() -> {
                path("/api", () -> {
                    path("/locations", () -> {
                        get(LocationController::getLocations);
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
