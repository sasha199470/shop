package ru.megastroy.controllers;

import lombok.Getter;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ru.megastroy.controllers.core.SessionController;
import ru.megastroy.dao.BasketDao;
import ru.megastroy.dao.ProductDao;
import ru.megastroy.domain.Basket;
import ru.megastroy.domain.Product;
import ru.megastroy.domain.User;
import ru.megastroy.exceptions.NotExists;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Sasha Andreeva on 19.05.17.
 */
@Transactional
@RestController
public class UserController extends SessionController {

    @Autowired
    ProductDao productDao;

    @Autowired
    BasketDao basketDao;

    @RequestMapping(value = "/me/add-to-basket", method = RequestMethod.GET)
    public ResponseEntity<?> catalog(HttpServletRequest request,
                                     @RequestParam("id") Long productId,
                                     @RequestParam("number") Long number) {

        User user = getSessionUser(request);
        Hibernate.initialize(user.getBaskets());
        Product product = productDao.get(productId);
        if (product == null) {
            throw new NotExists("Product doesn't exist: " + productId);
        }
        List<Basket> baskets = user.getBaskets();

        for (Basket basket : baskets) {
            if (basket.getProduct().getId() == productId) {
                basket.setNamber(basket.getNamber() + number);
                basketDao.update(basket);
                return new ResponseEntity<>(HttpStatus.OK);
            }
        }
        Basket basket = new Basket();
        basket.setProduct(product);
        basket.setNamber(number);
        basket.setUser(user);
        basketDao.add(basket);

        user.getBaskets().add(basket);
        userDao.update(user);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/me/get-basket", method = RequestMethod.GET)
    public ResponseEntity<?> getBasket(HttpServletRequest request) {
        User user = getSessionUser(request);
        Hibernate.initialize(user.getBaskets());
        List<BasketResponse> baskets = user.getBaskets()
                .stream()
                .map(BasketResponse::new)
                .collect(Collectors.toList());

        return new ResponseEntity<>(baskets, HttpStatus.OK);
    }

    private class BasketResponse {
        @Getter
        private String name;

        @Getter
        private long number;

        @Getter
        private long id;

        @Getter
        private int price;

        BasketResponse(Basket basket) {
            this.name = basket.getProduct().getName();
            this.number = basket.getNamber();
            this.id = basket.getId();
            this.price = basket.getProduct().getPrice();
        }
    }
}
