package project.controller;

import java.util.ArrayList;

import project.dto.ProductDTO;
import project.service.ProductService;

public class ProductController {

	private ProductService productService;
	
	public ProductController() {
		productService = new ProductService();
	}
	
	// 전체 조회 요청
	public ArrayList<ProductDTO> requestSelectAll() {
		ArrayList<ProductDTO> responseDto = null;
		
		responseDto = productService.selectAll();
		return responseDto;
	}
	
	// 카테고리별 조회 요청
	public ArrayList<ProductDTO> requestSelectByCategory(int categoryId) {
		ArrayList<ProductDTO> responseDto = null;
		
		responseDto = productService.selectByCategory(categoryId);
		return responseDto;
	}
}
