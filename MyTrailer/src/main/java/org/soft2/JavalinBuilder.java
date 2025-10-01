package org.soft2;

import io.javalin.Javalin;

import static io.javalin.apibuilder.ApiBuilder.*;

public class JavalinBuilder {

    public static void startServer(int port) {
        Javalin server = Javalin.create(config -> {
            config.router.apiBuilder(() -> {
                path("/api", () -> {
                    path("/TODO_INSERT_OUR_OWN_PATHS_AND_ROUTES", () -> {
                        get((context) -> {
                            //Our handlers go here. This is just an example.
                        });
                    });
                    path("/TODO_INSERT_MORE_OF_OUR_OWN_PATHS_AND_ROUTES", () -> {

                    });
                });
            });
        }).start(port);
    }
}
