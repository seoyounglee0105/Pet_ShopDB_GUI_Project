package com.mungyang.service;

import java.util.ArrayList;

import com.mungyang.dao.ProductDAO;
import com.mungyang.dto.ProductDTO;

public class ProductService {

	private ProductDAO productDAO;
	
	public ProductService() {
		productDAO = new ProductDAO();
	}
	
	// 전체 조회
	public ArrayList<ProductDTO> selectAll(){
		ArrayList<ProductDTO> resultList = null;
	
		resultList = productDAO.select();
		return resultList;
	}
	
	// 전체 조회 & 정렬
	public ArrayList<ProductDTO> selectAllOrderBy(int orderIndex){
		ArrayList<ProductDTO> resultList = null;
		
		resultList = productDAO.selectOrdeyBy(orderIndex);
		return resultList;
	}
	
	// 카테고리별 조회
	public ArrayList<ProductDTO> selectByCategory(int categoryId){
		ArrayList<ProductDTO> resultList = null;
		
		resultList = productDAO.select("category_id", categoryId);
		return resultList;
	}
	
	// 카테고리별 조회 & 정렬
	public ArrayList<ProductDTO> selectByCategoryOrderBy(int orderIndex, int categoryId){
		ArrayList<ProductDTO> resultList = null;
		
		resultList = productDAO.selectOrdeyBy(orderIndex, "category_id", categoryId);			
		return resultList;
	}
	
	// MainPhoto 경로에 해당하는 Dto 조회
	public ProductDTO selectByMainPhoto(String mainPhoto) {
		ProductDTO resultDto = null;
		
		resultDto = productDAO.select("main_photo", mainPhoto);
		return resultDto;
	}
	
	// 검색어에 해당하는 Dto 조회
	public ArrayList<ProductDTO> searchProduct(String searchName){
		ArrayList<ProductDTO> resultList = null;
		
		resultList = productDAO.selectLike(searchName);
		return resultList;
	}
	
	// 판매량 갱신
	public int updateSales(int addSales, int productId) {
		int result = 0;
		
		ProductDTO targetProduct = productDAO.select("id", productId).get(0);
		
		int updateSales = targetProduct.getSales() + addSales;
		
		result = productDAO.update(updateSales, productId);	
		if (result == 1) {
			System.out.println("판매량이 " + updateSales + "로 갱신되었습니다.");
		}
		return result;
	}
	
	// 이름에 해당하는 Dto 조회
	public ProductDTO selectByName(String name) {
		ProductDTO result = null;
		result = productDAO.select("name", name);
		return result;
	}
	
}
