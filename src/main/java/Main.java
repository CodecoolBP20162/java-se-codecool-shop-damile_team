import com.codecool.shop.controller.ProductController;
import com.codecool.shop.dao.*;
import com.codecool.shop.dao.implementation.*;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import spark.Request;
import spark.Response;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

import java.io.IOException;

import static spark.Spark.*;
import static spark.debug.DebugScreen.enableDebugScreen;

public class Main {

    public static void main(String[] args) throws IOException {
        ProductDaoWithJDBC productDaoWithJDBC = new ProductDaoMemWithJDBC();
        System.out.println(productDaoWithJDBC.listAllProducts());
        ProductCategoryDaoWithJDBC productCategoryDaoWithJDBC = new ProductCategoryDaoMemWithJDBC();
        System.out.println(productCategoryDaoWithJDBC.getAllCategories());
        SupplierDaoWithJDBC supplierDaoWithJDBC = new SupplierDaoMemWithJDBC();
        System.out.println(supplierDaoWithJDBC.getAllSupplier());
        System.out.println("Products by Category");
        for (ProductCategory pc : productCategoryDaoWithJDBC.getAllCategories()) {
            System.out.println(pc.getProducts());
        }
        System.out.println("Products by Supplier");
        for (Supplier supplier : supplierDaoWithJDBC.getAllSupplier()) {
            System.out.println(supplier.getProducts());
        }
        System.out.println(productCategoryDaoWithJDBC.findCategory("Tablet"));
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

    }


}
