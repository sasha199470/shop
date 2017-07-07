package ru.megastroy.controllers;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.megastroy.dao.CategoryDao;
import ru.megastroy.dao.FirmDao;
import ru.megastroy.dao.ProductDao;
import ru.megastroy.dao.SubcategoryDao;
import ru.megastroy.domain.Firm;
import ru.megastroy.domain.Product;
import ru.megastroy.domain.Subcategory;
import ru.megastroy.exceptions.NotExists;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by pccp on 11.05.2017.
 */
@RestController
public class ProductController {

    @Autowired
    SubcategoryDao subcategoryDao;

    @Autowired
    CategoryDao categoryDao;

    @Autowired
    ProductDao productDao;

    @Autowired
    FirmDao firmDao;

    @RequestMapping(value = "/product/sub/{sub_id}", method = RequestMethod.GET)
    @Transactional
    public ResponseEntity<?> getSubcategoryProduct(@PathVariable("sub_id") long subId) {
        Subcategory subcategory = subcategoryDao.get(subId);
        SubdirectoryProductResponse productResponse = new SubdirectoryProductResponse();
        List<ProductObject> products = subcategory.getProducts()
                .stream()
                .map(ProductObject::new)
                .collect(Collectors.toList());
        productResponse.setProducts(products);

        List<SubdirectoryResponse> subdirectories = categoryDao
                .get(subcategory.getCategory().getId()).getSubdirectories()
                .stream()
                .map(SubdirectoryResponse::new)
                .collect(Collectors.toList());
        productResponse.setSubdirectories(subdirectories);

        return new ResponseEntity<Object>(productResponse, HttpStatus.OK);
    }

    @RequestMapping(value = "/product/{product_id}", method = RequestMethod.GET)
    @Transactional
    public ResponseEntity<?> getProduct(@PathVariable("product_id") long productId) {

        Product product = productDao.get(productId);
        if (product == null) {
            throw new NotExists("Product doesn't exist: " + productId);
        }
        Hibernate.initialize(product);

        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @RequestMapping(value = "/product/add", method = RequestMethod.POST)
    @Transactional
    public ResponseEntity<?> addProduct(@RequestBody ProductObject pr) {

        Product product = new Product();
        product.setName(pr.getName());
        product.setDescription(pr.getDescription());
        product.setPrice(pr.getPrice());

        Firm firm = firmDao.get(pr.getFirm().getId());
        if (firm == null) {
            throw new NotExists("Firm doesn't exist: " + pr.getFirm().getId());
        }
        product.setFirm(firm);

        Subcategory subcategory = subcategoryDao.get(pr.getSubcategory().getId());
        if (subcategory == null) {
            throw new NotExists("Category doesn't exist: " + pr.getSubcategory().getId());
        }
        product.setSubcategory(subcategory);

        productDao.add(product);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    private class SubdirectoryProductResponse {
        @Getter @Setter
        private List<ProductObject> products;
        @Getter @Setter
        private List<SubdirectoryResponse> subdirectories;
    }

    private static class ProductObject {
        @Getter
        private long id;

        @Getter
        private Firm firm;

        @Getter
        private String description;

        @Getter
        private String name;

        @Getter
        private String image;

        @Getter
        private int price;

        @Getter
        private Subcategory subcategory;

        ProductObject(Product product) {
            this.id = product.getId();
            this.firm = product.getFirm();
            this.description = product.getDescription();
            this.name = product.getName();
            this.image = product.getPhoto();
            this.price = product.getPrice();
        }

        @JsonCreator
        ProductObject(@JsonProperty("name") String name,
                      @JsonProperty("description") String description,
                      @JsonProperty("price") Integer price,
                      @JsonProperty("firm") Firm firm,
                      @JsonProperty("subcategory") Subcategory subcategory) {
            this.name = name;
            this.description = description;
            this.price = price;
            this.firm = firm;
            this.subcategory= subcategory;
        }
    }

    private static class SubdirectoryResponse {

        @Getter
        private long id;

        @Getter
        private String name;

        SubdirectoryResponse(Subcategory subcategory) {
            this.id = subcategory.getId();
            this.name = subcategory.getName();
        }

    }
}
