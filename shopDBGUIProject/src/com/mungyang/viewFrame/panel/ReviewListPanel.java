package com.mungyang.viewFrame.panel;

import java.awt.Color;
import java.awt.Font;
import java.lang.annotation.Target;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.mungyang.controller.ReviewController;
import com.mungyang.dto.ProductDTO;
import com.mungyang.dto.ReviewDTO;

public class ReviewListPanel extends JPanel {

	private ProductDTO currentProduct;
	private ReviewController reviewController;
	private JLabel[] stars = new JLabel[5];
	private JLabel[] titles = new JLabel[5];
	
	public ReviewListPanel(ProductDTO targetDto) {
		this.currentProduct = targetDto;
		initData();
		setInitLayout();
		viewTitles();
	}

	private void initData() {
		setSize(340, 180);
		reviewController = new ReviewController();
		for (int i = 0; i < titles.length; i++) {
			stars[i] = new JLabel();
			titles[i] = new JLabel();
		}
	}
	
	private void setInitLayout() {
		setLayout(null);
		setBackground(Color.white);
		
		int y = 10;
		for (int i = 0; i < titles.length; i++) {
			stars[i].setBounds(20, y, 320, 20);
			stars[i].setFont(new Font("맑은 고딕", Font.BOLD, 13));
			stars[i].setForeground(new Color(228, 181, 28));
			add(stars[i]);
			titles[i].setBounds(100, y + 2, 320, 20);
			titles[i].setFont(new Font("맑은 고딕", Font.BOLD, 13));
			add(titles[i]);
			y += 20;
		}
	}
	
	private void viewTitles() {
		ArrayList<ReviewDTO> reviewList = reviewController.requestSelectReview(currentProduct.getId());
		
		// 한 페이지 내에 다 보일 수 있을 때
		for (int i = 0; i < reviewList.size(); i++) {
			int star = reviewList.get(i).getStar();
			String title = reviewList.get(i).getTitle();
			
			String starStr = null;
			if (star == 1) {
				starStr = "★☆☆☆☆";
			} else if (star == 2) {
				starStr = "★★☆☆☆";
			} else if (star == 3) {
				starStr = "★★★☆☆";
			} else if (star == 4) {
				starStr = "★★★★☆";
			} else if (star == 5) {
				starStr = "★★★★★";
			}
			
			stars[i].setText(starStr);
			titles[i].setText(title);
		}
		
	}

}
