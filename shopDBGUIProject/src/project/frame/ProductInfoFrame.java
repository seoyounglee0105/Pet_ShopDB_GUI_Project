package project.frame;

import java.awt.Color;

import javax.swing.JFrame;

import project.controller.ProductController;

public class ProductInfoFrame extends JFrame {
	
	private ProductController productController;

	public ProductInfoFrame() {
		initData();
		setInitLayout();
	}
	
	private void initData() {
		setTitle("로그인");
		setSize(400, 300);
		
	}

	private void setInitLayout() {
		setLayout(null);
		setResizable(false);	
		setLocationRelativeTo(null);
		Color backgroundColor = new Color(200, 235, 226);
		getContentPane().setBackground(backgroundColor);
		
		
		setVisible(true);
	}
	
}
