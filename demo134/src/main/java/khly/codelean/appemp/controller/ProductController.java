package khly.codelean.appemp.controller;

import khly.codelean.appemp.entity.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    private ProductService productService;
    public ProductController(ProductService theProductService) {
        productService = theProductService;
    }

    // add mapping for "/list"

    @GetMapping("/list")
    public String listProduct(Model theModel) {

        // get employees from db
        List<Product> theProducts = productService.findAll();

        // add to the spring model
        theModel.addAttribute("products", theProducts);

        return "product/list-product";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel) {

        // create model attribute to bind form data
        Product theProduct = new Product();

        theModel.addAttribute("product", theProduct);

        return "product/product-form";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("productId") int theId, Model theModel) {

        // get the product from the service
        Product theProduct = productService.findById(theId);

        // set product as a model attribute to pre-populate the form
        theModel.addAttribute("product", theProduct);

        // send over to our form
        return "product/product-form";
    }


    @PostMapping("/save")
    public String saveProduct(@ModelAttribute("product") Product theProduct) {

        // save the product
        productService.save(theProduct);

        // use a redirect to prevent duplicate submissions
        return "redirect:/product/list";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam("productId") int theId) {

        // delete the product
        productService.deleteById(theId);

        // redirect to /product/list
        return "redirect:/product/list";

    }
}

