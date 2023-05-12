package sk.stuba.fei.uim.oop.assignment3.cart;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
public class Cart {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Setter
    @ElementCollection
    private List<ShoppingList> shoppinglist = new ArrayList<>();

    private boolean payed = false;

}
