package sk.stuba.fei.uim.oop.assignment3.product;

import java.util.List;

public interface IProductService {

    List<Product> getAll();

    Product getById(Long id);

    Product create(ProductRequest request);

    Product update(Long id, ProductRequest request);

    void delete(Long id);

    int getAmount(Long id);

    int addAmount(Long id, ProductRequest request);

    Product save(Product p);
}
