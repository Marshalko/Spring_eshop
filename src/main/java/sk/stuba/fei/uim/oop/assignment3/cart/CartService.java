package sk.stuba.fei.uim.oop.assignment3.cart;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import sk.stuba.fei.uim.oop.assignment3.product.IProductService;
import sk.stuba.fei.uim.oop.assignment3.product.Product;


@Service
public class CartService implements ICartService {

    private ICartRepository repository;

    @Autowired
    private IProductService productService;

    @Autowired
    public CartService(ICartRepository repository) {
        this.repository = repository;
    }

    @Override
    public Cart create() {

        Cart newCart = new Cart();

        return this.repository.save(newCart);
    }

    @Override
    public Cart getById(Long id) {
        return this.repository.findById(id).orElseThrow();
    }

    @Override
    public void deleteById(Long id) {
        Cart deleteCart = this.repository.findById(id).orElseThrow();
        this.repository.delete(deleteCart);
    }

    @Override
    public Cart addPtoC(Long id, ShoppingListRequest request) {

        Cart cart = repository.findById(id).orElseThrow();
        Product product = this.productService.getById(request.getProductId());

        if (cart.isPayed() || product.getAmount() < request.getAmount()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        for (int j = 0; j < cart.getShoppinglist().size(); j++) {

            if (cart.getShoppinglist().get(j).getProductId().equals(request.getProductId())) {

                cart.getShoppinglist().get(j).setAmount(cart.getShoppinglist().get(j).getAmount() + request.getAmount());
                product.setAmount(product.getAmount() - request.getAmount());
                this.productService.save(product);
                return this.repository.save(cart);
            }
        }

        ShoppingList list = new ShoppingList();
        list.setProductId(product.getId());
        list.setAmount(list.getAmount() + request.getAmount());
        cart.getShoppinglist().add(list);
        product.setAmount(product.getAmount() - request.getAmount());

        this.productService.save(product);
        return this.repository.save(cart);
    }

    @Override
    public String pay(Long id) {

        Cart cart = repository.findById(id).orElseThrow();
        Payment payment = new Payment();

        if (cart.isPayed()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

        }


        for (int i = 0; i < cart.getShoppinglist().size(); i++) {

            Product product = productService.getById(cart.getShoppinglist().get(i).getProductId());
            payment.setPrice(payment.getPrice() + (cart.getShoppinglist().get(i).getAmount() * product.getPrice()));

        }
        cart.setPayed(true);
        this.repository.save(cart);

        return String.valueOf(payment.getPrice());
    }


}
