package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

public class ProductController {

    public static ModelAndView renderWelcomePage(Request req, Response res) {
        Map params = new HashMap<>();
        return new ModelAndView(params, "product/welcome");
    }

    public static ModelAndView renderProducts(Request req, Response res) {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();

        Map params = new HashMap<>();
        params.put("category", productCategoryDataStore.getAll());
        params.put("products", productDataStore.getAll());
        return new ModelAndView(params, "product/index");
    }
    public static ModelAndView renderTablets(Request req, Response res) {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();

        Map params = new HashMap<>();
        params.put("category", productCategoryDataStore.find(1));
        params.put("products", productDataStore.getBy(productCategoryDataStore.find(1)));
        return new ModelAndView(params, "product/index");
    }

    public static ModelAndView renderMobiles(Request req, Response res) {

        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();

        Map params = new HashMap<>();
        params.put("category", productCategoryDataStore.find(2));
        params.put("products", productDataStore.getBy(productCategoryDataStore.find(2)));
        return new ModelAndView(params, "product/index");
    }

    public static ModelAndView renderTVs(Request req, Response res) {

        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();

        Map params = new HashMap<>();
        params.put("category", productCategoryDataStore.find(3));
        params.put("products", productDataStore.getBy(productCategoryDataStore.find(3)));
        return new ModelAndView(params, "product/index");
    }
    public static ModelAndView renderAmazon(Request req, Response res) {

        ProductDao productDataStore = ProductDaoMem.getInstance();
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();

        Map params = new HashMap<>();
        params.put("suppliers", supplierDataStore.find(1));
        params.put("products", productDataStore.getBy(supplierDataStore.find(1)));
        return new ModelAndView(params, "product/supplier");
    }
    public static ModelAndView renderLenovo(Request req, Response res) {

        ProductDao productDataStore = ProductDaoMem.getInstance();
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();

        Map params = new HashMap<>();
        params.put("suppliers", supplierDataStore.find(2));
        params.put("products", productDataStore.getBy(supplierDataStore.find(2)));
        return new ModelAndView(params, "product/supplier");
    }

    public static ModelAndView renderApple(Request req, Response res) {

        ProductDao productDataStore = ProductDaoMem.getInstance();
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();

        Map params = new HashMap<>();
        params.put("suppliers", supplierDataStore.find(3));
        params.put("products", productDataStore.getBy(supplierDataStore.find(3)));
        return new ModelAndView(params, "product/supplier");
    }

    public static ModelAndView renderSamsung(Request req, Response res) {

        ProductDao productDataStore = ProductDaoMem.getInstance();
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();

        Map params = new HashMap<>();
        params.put("suppliers", supplierDataStore.find(4));
        params.put("products", productDataStore.getBy(supplierDataStore.find(4)));
        return new ModelAndView(params, "product/supplier");
    }
    public static ModelAndView renderOthers(Request req, Response res) {

        ProductDao productDataStore = ProductDaoMem.getInstance();
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();

        Map params = new HashMap<>();
        params.put("suppliers", supplierDataStore.find(5));
        params.put("products", productDataStore.getBy(supplierDataStore.find(5)));
        return new ModelAndView(params, "product/supplier");
    }
}
