package khly.codelean.appemp.dao;


import khly.codelean.appemp.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    // that's it ... no need to write any code LOL!

    // add a method to sort by last name
    public List<Product> findAllByOrderByNameAsc();

}
