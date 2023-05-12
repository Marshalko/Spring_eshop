package sk.stuba.fei.uim.oop.assignment3.product;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/product")
public class ProductController {


    @Autowired
    private IProductService service;


    @GetMapping()
    public List<ProductResponse> getAllProducts() {
        return this.service.getAll().stream().map(ProductResponse::new).collect(Collectors.toList());
    }

    @PostMapping()
    @ResponseStatus(value = HttpStatus.CREATED)
    public ProductResponse addProduct(@RequestBody ProductRequest request) {
        return new ProductResponse(this.service.create(request));
    }


    @GetMapping("/{id}")
    public Product getAProduct(@PathVariable("id") Long id) {
        return this.service.getById(id);
    }

    @PutMapping("/{id}")

    public Product updateAProduct(@PathVariable("id") Long id, @RequestBody ProductRequest request) {
        return this.service.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteAProduct(@PathVariable("id") Long id) {
        this.service.delete(id);
    }


    @GetMapping("/{id}/amount")
    public ResponseTransfer getAnAmount(@PathVariable("id") Long id) {
        return new ResponseTransfer(service.getAmount(id));
    }

    @PostMapping("/{id}/amount")
    public ResponseTransfer addAmount(@PathVariable("id") Long id, @RequestBody ProductRequest request) {
        return new ResponseTransfer(service.addAmount(id, request));
    }
}
