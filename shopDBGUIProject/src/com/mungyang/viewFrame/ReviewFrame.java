package com.mungyang.viewFrame;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.mungyang.dto.ProductDTO;
import com.mungyang.dto.ReviewDTO;

public class ReviewFrame extends JFrame {

	private ProductDTO currentProduct;
	private ReviewDTO currentReview;

	private JLabel writerIdLabel;
	private JLabel starLabel;
	private JLabel titleLabel;
	private JTextField titleField;

	private JTextArea contentArea;
	private JScrollPane contentPane;

	private boolean photoOn;
	private JLabel photoLabel;

	private Color grayColor;

	public ReviewFrame(ProductDTO targetProduct, ReviewDTO targetReview) {
		this.currentProduct = targetProduct;
		this.currentReview = targetReview;
		initData();
		setInitLayout();
	}

	private void initData() {
		setTitle("'" + currentProduct.getName() + "' 리뷰 페이지");
		grayColor = new Color(232, 239, 239);

		String writerId = currentReview.getWriterId();
		writerIdLabel = new JLabel("작성자 : " + writerId);

		int star = currentReview.getStar();
		if (star == 1) {
			starLabel = new JLabel("★☆☆☆☆");
		} else if (star == 2) {
			starLabel = new JLabel("★★☆☆☆");
		} else if (star == 3) {
			starLabel = new JLabel("★★★☆☆");
		} else if (star == 4) {
			starLabel = new JLabel("★★★★☆");
		} else if (star == 5) {
			starLabel = new JLabel("★★★★★");
		}

		titleLabel = new JLabel("제목 : ");

		String title = currentReview.getTitle();
		titleField = new JTextField(title);

		String content = currentReview.getContent();
		contentArea = new JTextArea(content);
		contentPane = new JScrollPane(contentArea);

		// 이미지가 존재한다면
		if (currentReview.getPhoto() != null) {
			photoOn = true;
			photoLabel = new JLabel(new ImageIcon("images/" + currentReview.getPhoto()));
		}
	}

	private void setInitLayout() {
		setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		getContentPane().setBackground(grayColor);

		writerIdLabel.setBounds(20, 20, 200, 30);
		writerIdLabel.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		add(writerIdLabel);

		starLabel.setBounds(265, 18, 200, 30);
		starLabel.setForeground(new Color(228, 181, 28));
		starLabel.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		add(starLabel);

		titleLabel.setBounds(20, 60, 60, 30);
		titleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		add(titleLabel);

		titleField.setBounds(70, 65, 290, 25);
		titleField.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		titleField.setEditable(false);
		add(titleField);

		setVisible(true);

		contentArea.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		contentArea.setLineWrap(true); // 이거 해놔야 자동으로 줄바꿈됨
		contentArea.setEditable(false); // 편집 불가능
		add(contentPane);

		// 사진이 존재하는 경우
		if (photoOn == true) {
			setSize(400, 450);

			contentPane.setBounds(23, 290, 338, 100);
			photoLabel.setBounds(118, 115, 150, 150);
			add(photoLabel);
			
		// 사진이 존재하지 않는 경우 
		} else if (photoOn == false) {
			setSize(400, 250);
			
			contentPane.setBounds(23, 110, 338, 80);
		}
	}
}