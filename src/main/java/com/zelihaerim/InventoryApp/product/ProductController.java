package com.zelihaerim.InventoryApp.product;

import java.net.http.HttpRequest;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.zelihaerim.InventoryApp.category.Category;
import com.zelihaerim.InventoryApp.category.CategoryRepository;
@Controller
public class ProductController {
	
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	
	@GetMapping("/products")
	public String listCategories(Model model) {
		List<Product> listProducts = productRepository.findAll();
		model.addAttribute("listProducts",listProducts);
		return "products";
	}
	
	@GetMapping("/products/new")
	public String showCategoryNewForm(Model model) {
		List<Category> listCategories = categoryRepository.findAll();
		model.addAttribute("product",new Product());
		model.addAttribute("listCategories",listCategories);		
		return "product_form";
	}
	@PostMapping("/products/save")
	public String saveCategory(Product product, HttpServletRequest request) {
		String[] detailIDs = request.getParameterValues("detailID");
		
		String[] detailNames = request.getParameterValues("detailName");
		String[] detailValues = request.getParameterValues("detailValue");
		
		for (int i=0;i<detailNames.length;i++) {
			if(detailIDs != null && detailIDs.length>0) {
				product.setDetail(Integer.valueOf(detailIDs[i]), detailNames[i], detailValues[i]);
			}else {
				product.addDetail(detailNames[i], detailValues[i]);
			}
		}
		
		productRepository.save(product);
		return "redirect:/products";
	}
	@GetMapping("products/edit/{id}")
	public String showEditProductForm(@PathVariable("id") Integer id,Model model) {
		Product product = productRepository.findById(id).get();
		List<Category> listCategories = categoryRepository.findAll();
		model.addAttribute(product);
		model.addAttribute("listCategories",listCategories);
		return "product_form";
	}
	@GetMapping("products/delete/{id}")
	public String DeleteProduct(@PathVariable("id") Integer id,Model model) {
		productRepository.deleteById(id);
		return "redirect:/products";
	}

}
