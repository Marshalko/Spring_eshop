package sk.stuba.fei.uim.oop.assignment3.cart;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;


@Setter
@Getter
@NoArgsConstructor
@Embeddable
public class ShoppingList {

    private Long productId;
    private int amount = 0;


}
