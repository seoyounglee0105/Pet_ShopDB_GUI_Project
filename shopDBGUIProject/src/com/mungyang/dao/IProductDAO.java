package com.mungyang.dao;

import java.util.ArrayList;

import com.mungyang.dto.ProductDTO;

public interface IProductDAO {

	// 전체 SELECT
	ArrayList<ProductDTO> select();
	
	// 전체 SELECT & ORDER BY 포함
	ArrayList<ProductDTO> selectOrdeyBy(int orderIndex);

	// 한 개의 조건을 이용한 SELECT
	ArrayList<ProductDTO> select(String columnName, int value);

	// 한 개의 고유 값 조건을 이용한 SELECT
	ProductDTO select(String UniqueColumnName, String value);
	
	// 한 개의 조건을 이용한 SELECT & ORDER BY 포함
	ArrayList<ProductDTO> selectOrdeyBy(int orderIndex, String columnName, int value);
	
	// product_name LIKE 활용 조건을 이용한 SELECT
	ArrayList<ProductDTO> selectLike(String searchName);
	
	// UPDATE (판매량)
	int update(int sales, int productId);
	
	
}
