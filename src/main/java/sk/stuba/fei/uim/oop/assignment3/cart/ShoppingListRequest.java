package sk.stuba.fei.uim.oop.assignment3.cart;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShoppingListRequest {

    private Long productId;
    private int amount;

    public ShoppingListRequest(Long productId, int amount) {
        this.productId = productId;
        this.amount = amount;
    }
}
