package library.service;

import java.util.List;

import library.model.Category;
import library.repository.CategoryRepository;

public class CategoryService {

	private CategoryRepository categoryRepository;

	public CategoryService(CategoryRepository categoryRepository) {

		this.categoryRepository = categoryRepository;
	}
	
	public List<Category> findAll() {

		return categoryRepository.findAll();
	}

}
