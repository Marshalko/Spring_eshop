package sk.stuba.fei.uim.oop.assignment3.cart;


import lombok.Getter;

import java.util.List;

@Getter
public class CartResponse {

    private Long id;
    private List shoppingList;
    private boolean payed;


    public CartResponse(Cart c) {

        this.id = c.getId();

        this.shoppingList = c.getShoppinglist();

        this.payed = c.isPayed();
    }
}
