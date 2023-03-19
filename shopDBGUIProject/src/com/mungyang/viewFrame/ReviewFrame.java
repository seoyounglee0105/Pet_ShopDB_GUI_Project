package com.mungyang.viewFrame;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.mungyang.controller.CartController;
import com.mungyang.controller.MemberController;
import com.mungyang.controller.ProductController;
import com.mungyang.dto.CartDTO;
import com.mungyang.dto.MemberDTO;
import com.mungyang.dto.ProductDTO;

public class ReviewFrame extends JFrame {

	private ShopFrame mContext;
	private ProductDTO currentProduct;


	private Color grayColor;

	public ReviewFrame(ProductDTO targetDto, ShopFrame mContext) {
		this.mContext = mContext;
		this.currentProduct = targetDto;
		initData();
		setInitLayout();
		addEventListener();
	}

	private void initData() {
		setTitle("'" + currentProduct.getName() + "' 리뷰 페이지");
		setSize(400, 600);
		grayColor = new Color(232, 239, 239);
	}

	private void setInitLayout() {
		setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		getContentPane().setBackground(grayColor);



		setVisible(true);
	}

	private void addEventListener() {
	}

}