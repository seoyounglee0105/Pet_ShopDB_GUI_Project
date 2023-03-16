package project.dao;

import java.util.ArrayList;

import project.dto.ProductDTO;

public interface IProductDAO {

	// 전체 SELECT
	ArrayList<ProductDTO> select();
	
	// 전체 SELECT & ORDER BY 포함
	ArrayList<ProductDTO> selectOrdeyBy(int orderIndex);

	// 한 개의 조건을 이용한 SELECT
	ArrayList<ProductDTO> select(String columnName, int value);
	
	// 한 개의 조건을 이용한 SELECT & ORDER BY 포함
	ArrayList<ProductDTO> selectOrdeyBy(int orderIndex, String columnName, int value);
	
	
	
	
}
