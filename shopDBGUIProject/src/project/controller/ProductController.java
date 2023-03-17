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
		ArrayList<ProductDTO> responseList = null;
		
		responseList = productService.selectAll();
		return responseList;
	}
	
	// 전체 조회 & 정렬 요청
	public ArrayList<ProductDTO> requestSelectAllOrderBy(int orderIndex) {
		ArrayList<ProductDTO> responseList = null;
		
		responseList = productService.selectAllOrderBy(orderIndex);
		return responseList;
	}
	
	// 카테고리별 조회 요청
	public ArrayList<ProductDTO> requestSelectCategory(int categoryId) {
		ArrayList<ProductDTO> responseList = null;
		
		responseList = productService.selectByCategory(categoryId);
		return responseList;
	}
	
	// 카테고리별 조회 & 정렬 요청
	public ArrayList<ProductDTO> requestSelectCategoryOrderBy(int orderIndex, int categoryId) {
		ArrayList<ProductDTO> responseList = null;
		
		responseList = productService.selectByCategoryOrderBy(orderIndex, categoryId);
		return responseList;
	}
	
	// MainPhoto 경로에 해당하는 Dto 조회 요청
	public ProductDTO requestSelectMainPhoto(String mainPhoto) {
		ProductDTO responseDto = null;
		
		responseDto = productService.selectByMainPhoto(mainPhoto);
		return responseDto;
	}
}
