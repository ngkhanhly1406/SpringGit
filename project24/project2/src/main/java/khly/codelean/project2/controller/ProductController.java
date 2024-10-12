package khly.codelean.project2.controller;

import khly.codelean.project2.dao.CategoryRepository;
import khly.codelean.project2.dao.ProductRepository;
import khly.codelean.project2.entity.Category;
import khly.codelean.project2.entity.Product;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;



import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

@SpringBootApplication
@Controller
@RequestMapping("/product")
public class ProductController {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    private static final Logger logger = Logger.getLogger(ProductController.class.getName());

    public ProductController(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }


    @GetMapping("/list")
    public String listProduct(Model model) {
        model.addAttribute("products", productRepository.findAll());
        return "admin/Product/list";
    }

    @GetMapping("/add")
    public String addProduct(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("categories", categoryRepository.findAll()); // Thêm danh sách category
        return "admin/Product/form";
    }

    @GetMapping("/edit/{id}")
    public String editProduct(@PathVariable("id") Long id, Model model) {
        model.addAttribute("product", productRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid Product Id:" + id)));
        return "admin/Product/form";
    }

    /*@PostMapping("/save")
    public String saveProduct(@ModelAttribute Product product) {
        productRepository.save(product);
        return "redirect:/product/list";
    }*/

    @PostMapping("/save")
    public String saveProduct(@ModelAttribute Product theProduct,
                              @RequestParam("imageFile") MultipartFile imageFile,
                              RedirectAttributes redirectAttributes) {
        // Kiểm tra xem Category có phải là null không
        if (theProduct.getCategory() == null || theProduct.getCategory().getCategoryid() == null) {
            redirectAttributes.addFlashAttribute("message", "Category must be selected.");
            return "redirect:/product/add"; // Trở về trang thêm sản phẩm với thông báo lỗi
        }

        // Lấy danh mục từ CategoryRepository
        Category category = categoryRepository.findById(theProduct.getCategory().getCategoryid())
                .orElseThrow(() -> new IllegalArgumentException("Invalid Category Id:" + theProduct.getCategory().getCategoryid()));
        theProduct.setCategory(category);

        // Nếu người dùng tải lên file ảnh
        if (!imageFile.isEmpty()) {
            try {
                // Đường dẫn để lưu trữ file
                String uploadDir = "src/main/resources/static/images/";
                String fileName = imageFile.getOriginalFilename();
                Path path = Paths.get(uploadDir + fileName);

                // Lưu file ảnh vào server
                Files.write(path, imageFile.getBytes());

                // Lưu đường dẫn của file ảnh vào product
                theProduct.setImage("/images/" + fileName);

            } catch (IOException e) {
                logger.log(Level.SEVERE, "Error saving image", e);
                redirectAttributes.addFlashAttribute("message", "Error saving image: " + e.getMessage());
                return "redirect:/product/add";
            }
        }

        productRepository.save(theProduct);

        return "redirect:/product/list";
    }


    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") Long id) {
        productRepository.deleteById(id);
        return "redirect:/product/list";

    }

    @GetMapping("/details/{id}")
    public String showProductDetail(@PathVariable("id") Long id, Model model) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Product Id:" + id));
        model.addAttribute("product", product);
        return "product-details-thumbnail-right";
    }
}
