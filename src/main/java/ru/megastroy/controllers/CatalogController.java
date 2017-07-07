package ru.megastroy.controllers;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.megastroy.dao.CategoryDao;
import ru.megastroy.domain.Category;
import ru.megastroy.domain.Subcategory;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by pccp on 11.05.2017.
 */
@RestController
public class CatalogController {

    @Autowired
    CategoryDao categoryDao;

    @RequestMapping(value = "/catalog/category/all", method = RequestMethod.GET)
    @Transactional
    public ResponseEntity<?> catalog() {
        List<CategoriesResponse> catalogs = categoryDao.getAll()
                .stream()
                .map(CategoriesResponse::new)
                .collect(Collectors.toList());

        return new ResponseEntity<Object>(catalogs, HttpStatus.OK);
    }

    private class CategoriesResponse {

        @Getter
        private long id;

        @Getter
        private String name;

        @Getter
        private List<CategoriesResponse> subcategories = null;

        CategoriesResponse(Category category) {
            this.id = category.getId();
            this.name= category.getName();
            this.subcategories = category.getSubdirectories()
                    .stream()
                    .map(CategoriesResponse::new)
                    .collect(Collectors.toList());
        }

        private CategoriesResponse(Subcategory subcategory) {
            this.id = subcategory.getId();
            this.name = subcategory.getName();
        }
    }
}
