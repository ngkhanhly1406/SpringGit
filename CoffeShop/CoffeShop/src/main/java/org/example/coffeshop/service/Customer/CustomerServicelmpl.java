package org.example.coffeshop.service.Customer;

import org.example.coffeshop.dao.CustomerRepository;
import org.example.coffeshop.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServicelmpl implements CustomerService{

    private final CustomerRepository customerRepository ;
    @Autowired
    public CustomerServicelmpl(CustomerRepository theCustomerRepository) {
        this.customerRepository = theCustomerRepository ;
    }

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAllByOrderByName();
    }

    @Override
    public void save(Customer theCustomer){
        customerRepository.save(theCustomer);
    }

    @Override
    public Customer findById(int theId) {
        Optional<Customer> result = customerRepository.findById(theId);
        Customer theCustomer = null;
        if (result.isPresent()) {
            theCustomer = result.get();
        }
        else {
            throw new RuntimeException("Menu not find id" + theId);
        }
        return theCustomer;
    }

    @Override
    public void deleteById(int theId) {
        customerRepository.deleteById(theId);
    }

}
