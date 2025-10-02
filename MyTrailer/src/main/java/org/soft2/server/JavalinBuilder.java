package org.soft2.server;

import org.soft2.dao.OrderDaoMock;
import org.soft2.exceptions.APIException;
import org.soft2.exceptions.ExceptionHandler;
import org.soft2.handlers.LocationController;
import org.soft2.handlers.MessageHandler;
import org.soft2.handlers.ReturnHandler;
import org.soft2.orderhandler.OrderHandler;

import io.javalin.Javalin;
import static io.javalin.apibuilder.ApiBuilder.get;
import static io.javalin.apibuilder.ApiBuilder.path;
import static io.javalin.apibuilder.ApiBuilder.post;
import io.javalin.validation.ValidationException;

public class JavalinBuilder {

    private static final OrderHandler orderHandler = new OrderHandler(new OrderDaoMock());

    public static void startServer(int port) {
        Javalin.create(config -> {
            config.router.apiBuilder(() -> {
                path("/api", () -> {
                    path("/locations", () -> {
                        get(LocationController::getLocations);
                    });
                    path("/locations/{locationId}", () -> {
                        get(LocationController::getTrailersByLocation);
                    });
                    get("/", (ctx) -> ctx.status(418)); // Visit me in the browser ;)
                    post("/createorder", orderHandler::handleOrderCreation);

                    path("/TODO_INSERT_MORE_OF_OUR_OWN_PATHS_AND_ROUTES", () -> {

                    });
                    path("/createBill", () -> {
                        post(MessageHandler::createBill);
                    });
                    path("/returnTrailer", ()->{
                        post(ReturnHandler::returnTrailer);
                    });

                    path("sendTestMessage", () -> {
                        post(MessageHandler::sendTestMessage);
                    });
                    
                });
            });
            //Insert other configuration here if needed.
        })
                .exception(APIException.class, (ExceptionHandler::apiExceptionHandler))
                .exception(ValidationException.class, (ExceptionHandler::validationExceptionHandler))
                .start(port);
    }
}
