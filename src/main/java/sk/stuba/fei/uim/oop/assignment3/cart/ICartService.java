package sk.stuba.fei.uim.oop.assignment3.cart;


public interface ICartService {

    Cart create();

    Cart getById(Long id);

    void deleteById(Long id);

    Cart addPtoC(Long id, ShoppingListRequest request);

    String pay(Long id);
}
