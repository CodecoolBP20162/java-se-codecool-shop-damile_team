import com.codecool.shop.controller.ProductController;
import com.codecool.shop.dao.DaoWithJDBC;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.DaoMemWithJDBC;
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
        DaoWithJDBC daoWithJDBC = new DaoMemWithJDBC();
        System.out.println(daoWithJDBC.listAllProducts());
        // default server settings
        exception(Exception.class, (e, req, res) -> e.printStackTrace());
        staticFileLocation("/public");
        port(8884);

        // populate some data for the memory storage
        populateData();

        // Always add generic routes to the end
        get("/", (Request req, Response res) -> {
            return new ThymeleafTemplateEngine().render(ProductController.renderProducts(req, res));
        });

        // Equivalent with above
        get("/Category/:categoryName", (Request req, Response res) -> {
            return new ThymeleafTemplateEngine().render(ProductController.renderForCategory(req, res));
        });
//
        get("/Supplier/:supplierName", (Request req, Response res) -> {
            return new ThymeleafTemplateEngine().render(ProductController.renderForSupplier(req, res));
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


    }


}
