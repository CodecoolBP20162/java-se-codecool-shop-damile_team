import com.codecool.shop.controller.ProductController;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import spark.Request;
import spark.Response;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

import static spark.Spark.*;
import static spark.debug.DebugScreen.enableDebugScreen;

public class Main {

    public static void main(String[] args) {

        // default server settings
        exception(Exception.class, (e, req, res) -> e.printStackTrace());
        staticFileLocation("/public");
        port(8884);

        // populate some data for the memory storage
        populateData();

        // Always start with more specific routes
        get("/hello", (req, res) -> "Hello World");

        // Always add generic routes to the end
        get("/", ProductController::renderWelcomePage, new ThymeleafTemplateEngine());
        get("/tablets", ProductController::renderTablets, new ThymeleafTemplateEngine());
        get("/mobiles", ProductController::renderMobiles, new ThymeleafTemplateEngine());
        get("/tvs", ProductController::renderTVs, new ThymeleafTemplateEngine());

        get("/amazon", ProductController::renderAmazon, new ThymeleafTemplateEngine());
        get("/lenovo", ProductController::renderLenovo, new ThymeleafTemplateEngine());
        get("/apple", ProductController::renderApple, new ThymeleafTemplateEngine());
        get("/samsung", ProductController::renderSamsung, new ThymeleafTemplateEngine());
        get("/others", ProductController::renderOthers, new ThymeleafTemplateEngine());

        // Equivalent with above
        get("/products", (Request req, Response res) -> {
           return new ThymeleafTemplateEngine().render( ProductController.renderProducts(req, res) );
        });

        // Add this line to your project to enable the debug screen
        enableDebugScreen();
    }

    public static void populateData() {

        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();

        //setting up a new supplier
        Supplier amazon = new Supplier("Amazon", "Digital content and services");
        supplierDataStore.add(amazon);
        Supplier lenovo = new Supplier("Lenovo", "Computers");
        supplierDataStore.add(lenovo);
        Supplier apple = new Supplier("Apple", "Mobile phones");
        supplierDataStore.add(apple);
        Supplier samsung = new Supplier("Samsung", "Computers and mobile phones");
        supplierDataStore.add(samsung);
        Supplier others = new Supplier("Others", "Digital content and services");
        supplierDataStore.add(others);

        //setting up a new product category
        ProductCategory tablet = new ProductCategory("Tablet", "Hardware", "A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.");
        productCategoryDataStore.add(tablet);

        ProductCategory mobile = new ProductCategory("Mobile", "Hardware", "A mobile phone is a portable telephone that can make and receive calls over a radio frequency link while the user is moving within a telephone service area.");
        productCategoryDataStore.add(mobile);

        ProductCategory tv = new ProductCategory("TV", "Hardware", "A flat panel LCD TV set that uses LEDs (light emitting diodes) for its backlight source rather than the earlier cold cathode fluorescent lamps.");
        productCategoryDataStore.add(tv);

        //setting up products and printing it
        productDataStore.add(new Product("Amazon Fire", 49.9f, "USD", "Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.", tablet, amazon));
        productDataStore.add(new Product("Lenovo IdeaPad Miix 700", 479, "USD", "Keyboard cover is included. Fanless Core m5 processor. Full-size USB ports. Adjustable kickstand.", tablet, lenovo));
        productDataStore.add(new Product("Amazon Fire HD 8", 89, "USD", "Amazon's latest Fire HD 8 tablet is a great value for media consumption.", tablet, amazon));

        productDataStore.add(new Product("Iphone 9", 649f, "USD", "Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.", mobile, apple));
        productDataStore.add(new Product("Samsung Galaxy Note 7", 969, "USD", "Keyboard cover is included. Fanless Core m5 processor. Full-size USB ports. Adjustable kickstand.", mobile, samsung));
        productDataStore.add(new Product("HTC Desire", 90, "USD", "Our HTC Desire is a great value for media consumption.", mobile, others));

        productDataStore.add(new Product("Samsung UN28H4000", 464, "USD", "Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.", tv, samsung));
        productDataStore.add(new Product("Vizio D24-D1 24\" Smart LCD TV", 849, "USD", "Keyboard cover is included. Fanless Core m5 processor. Full-size USB ports. Adjustable kickstand.", tv, others));
        productDataStore.add(new Product("Element ELEFW328R", 128f, "USD", "Our HTC Desire is a great value for media consumption.", tv, others));

    }


}
