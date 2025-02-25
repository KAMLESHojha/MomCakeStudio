package com.kamlesh.major.controller;

import com.kamlesh.major.dto.ProductDTO;
import com.kamlesh.major.model.Category;
import com.kamlesh.major.model.Product;
import com.kamlesh.major.service.CategoryService;
import com.kamlesh.major.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Controller
public class AdminController {
    public static  String uplaodDir=System.getProperty("user.dir")+"/src/main/resources/static/productImages";

    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductService productService;

    @GetMapping("/admin")
    public String adminHome(){
        return "adminHome";
    }
    @GetMapping("/admin/categories")
    public String getCat(Model model){
          model.addAttribute("categories",categoryService.getAllCategory());
        return "categories";
    }
    @GetMapping("/admin/categories/add")
    public String getCatAdd(Model model){
        model.addAttribute("category", new Category());
        return "categoriesAdd";
    }

    @PostMapping ("/admin/categories/add")
    public String postCatAdd(@ModelAttribute("category") Category category){
        categoryService.addCategory(category);
        return "redirect:/admin/categories";
    }
    @GetMapping("/admin/categories/delete/{id}")
    public  String deleteCat(@PathVariable int id ) {

        categoryService.removeCategoryById(id);
        return "redirect:/admin/categories";
    }
    @GetMapping("/admin/categories/update/{id}")
        public String updateCat(@PathVariable int id, Model model){
            Optional<Category> category=categoryService.getCategoryById(id);
            if(category.isPresent()){
                model.addAttribute("category",category.get());
                return "categoriesAdd";
        }
            else {
                return "404";
            }
    }
    //product Section
    @GetMapping("/admin/products")
    public  String products(Model model) {

        model.addAttribute("products",productService.getAllProduct());
        return "products";
    }
    @GetMapping("/admin/products")
    public  String productAddGet(Model model) {

        model.addAttribute("productsDTO",new ProductDTO());
        model.addAttribute("categories",categoryService.getAllCategory());
        return "productsAdd";
    }
    @PostMapping("/admin/products/add"){
        public String productAddPost(@ModelAttribute("productDTO")ProductDTO productDTO ,@RequestParam("productImage")MultipartFile  file,@RequestParam("imgName")String imgName) throw IOException{

            Product product= new Product();
            product.setId(productDTO.getName());
            product.setCategory(categoryService.getCategoryById(productDTO.getCategoryId().get());
            product.setPrice(productDTO.getPrice());
            product.setPrice(productDTO.getWeight());
            product.setPrice(productDTO.Description());
            String imageUUID;
            if (!file.isEmpty()){
                imageUUID =file.getOriginalFilename();
                Path fileNameAndPath = Paths.get(uplaodDir,imageUUID);
                Files.write(fileNameAndPath,file.getBytes());
            }
            else {
                imageUUID=imgName;
            }
            product.setImageName(imageUUID);
            productService.addProduct(product);

            return "redirect:/admin/products";
        }
    }
    @PostMapping(".admin/product/delete/{id}")
        public String deleteProduct(@PathVariable Long id){
        productService.removeProductsById(id);
        return "redirect:/admin/products";
    }

    @GetMapping("/admin/product/update/{id}")
    public String productUpdateGet(@PathVariable long id ,Model model){
        Product product=productService.getProductById(id).get();
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setCategoryId(product.getCategory().getId());
        productDTO.setPrice(product.getPrice());
        productDTO.setWeight(product.getWeight());
        productDTO.setDescription(product.getDescription());
        productDTO.setImageName(product.getImageName());

        model.addAttribute("categories",categoryService.getAllCategory());
        model.addAttribute("productDTO",productDTO);


        return "productsAdd";

    }
}
