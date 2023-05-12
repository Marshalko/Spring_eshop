package sk.stuba.fei.uim.oop.assignment3.product;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IProductService {

    private IProductRepository repository;


    @Autowired
    public ProductService(IProductRepository repository) {
        this.repository = repository;

    }


    @Override
    public List<Product> getAll() {
        return this.repository.findAll();
    }

    @Override
    public Product getById(Long id) {
        return this.repository.findById(id).orElseThrow();
    }

    @Override
    public Product update(Long id, ProductRequest request) {
        Product prUpdate = this.repository.findById(id).orElseThrow();
        if (request.getName() == null) {
            prUpdate.setDescription(request.getDescription());
            return this.repository.save(prUpdate);

        }
        if (request.getDescription() == null) {
            prUpdate.setName(request.getName());
            return this.repository.save(prUpdate);
        }
        prUpdate.setName(request.getName());
        prUpdate.setDescription(request.getDescription());
        return this.repository.save(prUpdate);
    }

    @Override
    public void delete(Long id) {
        Product prDelete = this.repository.findById(id).orElseThrow();
        this.repository.delete(prDelete);

    }

    @Override
    public Product create(ProductRequest request) {

        Product newProduct = new Product();

        newProduct.setName(request.getName());
        newProduct.setDescription(request.getDescription());
        newProduct.setAmount(request.getAmount());
        newProduct.setUnit(request.getUnit());
        newProduct.setPrice(request.getPrice());

        return this.repository.save(newProduct);
    }

    @Override
    public int getAmount(Long id) {
        Product amOfProduct = this.repository.findById(id).orElseThrow();
        return amOfProduct.getAmount();
    }

    @Override
    public int addAmount(Long id, ProductRequest request) {
        Product addAmountofProduct = this.repository.findById(id).orElseThrow();
        addAmountofProduct.setAmount(addAmountofProduct.getAmount() + request.getAmount());
        this.repository.save(addAmountofProduct);
        return addAmountofProduct.getAmount();
    }

    @Override
    public Product save(Product product) {
        return this.repository.save(product);
    }
}
