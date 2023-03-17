package project.service;

import java.util.ArrayList;

import project.dao.ProductDAO;
import project.dto.ProductDTO;

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
	
	
}
