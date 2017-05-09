package com.codecool.shop.controller;

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
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductController {

    private static ProductCategory filteredCategory;
    private static Supplier filteredSupplier;
    private static List<Product> filteredProduct;


    public static ModelAndView renderProducts(Request req, Response res) {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();
        DaoWithJDBC daoWithJDBC = new DaoMemWithJDBC();

        Map params = new HashMap<>();
        params.put("category", productCategoryDataStore.getAll());
        params.put("supplier", supplierDataStore.getAll());
        params.put("products", daoWithJDBC.listAllProducts());
        return new ModelAndView(params, "product/index");
    }

    public static ModelAndView renderForCategory(Request req, Response res) {
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();

        String selectedCategory = req.params(":categoryName");

        for (ProductCategory cat : productCategoryDataStore.getAll()) {
            if (selectedCategory.equals(cat.getName())) {
                filteredCategory = productCategoryDataStore.find(cat.getId());
            }
        }

        filteredProduct = filteredCategory.getProducts();
        Map params = new HashMap<>();
        params.put("category", productCategoryDataStore.getAll());
        params.put("products", filteredProduct);
        params.put("supplier", supplierDataStore.getAll());
        return new ModelAndView(params, "product/index");
    }

    public static ModelAndView renderForSupplier(Request req, Response res) {
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();

        String selectedCategory = req.params(":supplierName");

        for (Supplier sup : supplierDataStore.getAll()) {
            if (selectedCategory.equals(sup.getName())) {
                filteredSupplier = supplierDataStore.find(sup.getId());
            }
        }
        filteredProduct = filteredSupplier.getProducts();
        Map params = new HashMap<>();
        params.put("suppliers", supplierDataStore.getAll());
        params.put("category", productCategoryDataStore.getAll());
        params.put("products", filteredProduct);
        return new ModelAndView(params, "product/index");
    }
}
