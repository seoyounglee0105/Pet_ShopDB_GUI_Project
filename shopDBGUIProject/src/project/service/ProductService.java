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
	
	// 카테고리별 조회
	public ArrayList<ProductDTO> selectByCategory(int categoryId){
		ArrayList<ProductDTO> resultList = null;
		
		resultList = productDAO.select("category_id", categoryId);
		
		return resultList;
	}
	
	
}
