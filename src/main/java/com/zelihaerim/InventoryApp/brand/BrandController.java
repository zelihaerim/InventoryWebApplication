package com.zelihaerim.InventoryApp.brand;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.zelihaerim.InventoryApp.category.Category;
import com.zelihaerim.InventoryApp.category.CategoryRepository;
import com.zelihaerim.InventoryApp.product.Product;

@Controller
public class BrandController {
	@Autowired
	private BrandRepository brandRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	
	@GetMapping("/brands/new")
	public String showCreateNewBrandForm(Model model) {
		List<Category> listCategories= categoryRepository.findAll();
		model.addAttribute("brand",new Brand());
		model.addAttribute("listCategories",listCategories);
		return "brand_form";
	}
	
	@PostMapping("/brands/save")
	public String saveBrand(Brand brand) {
		brandRepository.save(brand);
		return "redirect:/brands";
	}
	
	@GetMapping("/brands")
	public String listBrands(Model model) {
		List<Brand> listBrands=brandRepository.findAll();
		model.addAttribute("listBrands",listBrands);
		return "brands";
	}
	
	@GetMapping("brands/edit/{id}")
	public String showEditBrandForm(@PathVariable("id") Integer id,Model model) {
		Brand brand = brandRepository.findById(id).get();
		List<Category> listCategories = categoryRepository.findAll();
		// i.e. :  all categories are : sport, art, garment, kitchen, bedroom, carpet
		// Brand= "Karaca", has categories = kitchen, bedroom then
		// after edit Brand= "Karaca", has categories = kitchen, bedroom, carpet
		model.addAttribute(brand);
		model.addAttribute("listCategories",listCategories);
		return "brand_form";
	}
	@GetMapping("brands/delete/{id}")
	public String DeleteProduct(@PathVariable("id") Integer id,Model model) {
		brandRepository.deleteById(id);
		return "redirect:/brands";
	}
	
	
}
