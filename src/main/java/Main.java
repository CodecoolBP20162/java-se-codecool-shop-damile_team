import static spark.Spark.*;

import shop.codecool.controller.*;
import spark.route.RouteOverview;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

public class Main {
    public static void main(String[] args) {

        // default server settings
        exception(Exception.class, (e, req, res) -> e.printStackTrace());
        staticFileLocation("/public");
        port(8888);

        get("/", ProductController::renderProducts, new ThymeleafTemplateEngine());
        get("/hello", (req, res) -> "Hello World");

    }
}
