package sk.stuba.fei.uim.oop.assignment3.cart;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private ICartService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CartResponse addCart() {
        return new CartResponse(this.service.create());
    }

    @GetMapping("/{id}")
    public CartResponse getACart(@PathVariable("id") Long id) {
        return new CartResponse(service.getById(id));

    }

    @DeleteMapping("/{id}")
    public void deleteACart(@PathVariable("id") Long id) {
        service.deleteById(id);
    }

    @PostMapping("/{id}/add")
    public CartResponse addAProductToCart(@PathVariable("id") Long cartId, @RequestBody ShoppingListRequest request) {
        return new CartResponse(service.addPtoC(cartId, request));
    }

    @GetMapping("/{id}/pay")
    public String payForCart(@PathVariable("id") Long cartId) {
        return this.service.pay(cartId);
    }


}
