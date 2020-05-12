package com.example.Note.domain.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.Note.domain.Category;
import com.example.Note.domain.CategoryRepository;



@Controller
public class CategoryController {

	@Autowired
	private CategoryRepository CRepository;
	
	/**
	 * List all category name
	 *
	 */
	@RequestMapping(value = "/categorylist")
	public String categoryList(Model model) {
		model.addAttribute("categories", CRepository.findAll());
		return "Category/categorylist";
	}
	/**
	 * Form for add new category
	 *
	 */
	@RequestMapping(value = "/newcategory")
	public String addCategory(Model model) {
		model.addAttribute("category", new Category());
		return "Category/newcategory";
	}
	/**
	 * Insert Category to database
	 * Redirect to new note
	 */
	@RequestMapping(value = "/savecategory", method = RequestMethod.POST)
	public String save(Category category) {
		CRepository.save(category);
		return "redirect:/newnote";
	}
	/**
	 * Update Category name
	 *
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveCategory(Category category) {
		CRepository.save(category);
		return "redirect:/categorylist";
	}
	/**
	 * Update Category name form
	 *
	 */
    @RequestMapping(value = "/updatecategory/{category_id}", method = RequestMethod.GET)
    public String modifyCategory(@PathVariable("category_id") Long category_id, Model model) {
    	Optional<Category> category = CRepository.findById(category_id);
    	model.addAttribute("category", category);
        return "Category/updatecategory";
    }    
	/**
	 * Delete Category
	 *
	 */
    @RequestMapping(value = "/delete/{category_id}", method = RequestMethod.GET)
    public String deleteCategory(@PathVariable("category_id") Long category_id, Model model) {
    	CRepository.deleteById(category_id);
        return "redirect:/categorylist";
    }
//---------------------------------------------------------------------------------------------
	/**
	 * End point
	 * Json body
	 * allCategory(): List all category
	 * categoryById():list category by Id
	 */
    @RequestMapping(value="api/categoryJson", method= RequestMethod.GET)
    public @ResponseBody List<Category> allCategory(){
    	return (List<Category>) CRepository.findAll();
    }
    
    @RequestMapping(value="api/categoryJson/{category_id}", method= RequestMethod.GET)
    public @ResponseBody Optional<Category> categoryById(@PathVariable("category_id") Long category_id){
    	return  CRepository.findById(category_id);
    }
    
}
