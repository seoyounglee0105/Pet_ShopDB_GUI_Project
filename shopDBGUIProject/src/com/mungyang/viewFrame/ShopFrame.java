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

import com.mungyang.controller.MemberController;
import com.mungyang.dto.MemberDTO;
import com.mungyang.viewFrame.panel.CartPanel;
import com.mungyang.viewFrame.panel.MyPagePanel;
import com.mungyang.viewFrame.panel.ProductListPanel;
import com.mungyang.viewFrame.panel.ReviewWritePanel;
import com.mungyang.viewFrame.panel.ViewOrderPanel;

public class ShopFrame extends JFrame implements ActionListener {

	private ShopFrame mContext = this;
	private String loginId; // 로그인된 회원 id
	private MemberController memberController;
	
	private JPanel topPanel; // 상단 패널
	private JLabel gradeLabel; // 로그인된 회원의 등급 아이콘
	private JLabel idLabel; // 로그인된 회원의 아이디
	private JButton logoutButton; // 로그아웃 버튼
	private JButton homeButton; // 홈 버튼
	private JButton myPageButton; // 마이페이지 버튼
	private JButton cartButton; // 장바구니 버튼
	private JPanel borderPanel; // 상단과 메인 패널 사이 경계
	private JLabel categoryLabel;
	private JPanel categoryPanel; // 카테고리 패널
	private JButton[] categoryButtons = new JButton[6]; // 카테고리 버튼 배열
	private JButton exitButton;
	
	private ProductListPanel productListPanel; // 카테고리 누르면 나오는 패널
	
	private CartPanel cartPanel;
	private MyPagePanel myPagePanel;
	private ReviewWritePanel reviewWritePanel;
	private ViewOrderPanel viewOrderPanel;
	
	private Color mintColor;
	private Color grayColor;
	private Color pointColor;
	
	public ShopFrame(String id) {
		loginId = id;
		initData();
		setInitLayout();
		addEventListener();
	}
	
	public void updateGradeIcon(String grade) {
		gradeLabel.setIcon(new ImageIcon("images/" + grade + ".png"));
	}
	
	private void initData() {
		setTitle("홈페이지");
		setSize(1000, 800);
		
		topPanel = new JPanel();
		
		memberController = new MemberController();
		MemberDTO loginMemberDto = memberController.requestMemberInfo(loginId);
		
		gradeLabel = new JLabel();
		if (loginMemberDto.getMemberGrade().equals("Gold")) {
			updateGradeIcon("Gold");
		} else if (loginMemberDto.getMemberGrade().equals("Silver")) {
			updateGradeIcon("Silver");
		} else {
			updateGradeIcon("Bronze");
		}
		
		// 회원 아이디
		idLabel = new JLabel(loginMemberDto.getName() + " 님");
		
		logoutButton = new JButton("로그아웃");
		homeButton = new JButton("Mung Yang");
		myPageButton = new JButton(new ImageIcon("images/home.png"));
		cartButton = new JButton(new ImageIcon("images/bag.png"));
		borderPanel = new JPanel();
		categoryLabel = new JLabel(new ImageIcon("images/category.png"));
		categoryPanel = new JPanel();
		
		String[] categoryNames = {"All", "Clothes", "Food", "Living", "Toy", "etc."};
		for (int i = 0; i < categoryButtons.length; i++) {
			categoryButtons[i] = new JButton(categoryNames[i]);
		}
		exitButton = new JButton("Exit");
		
		productListPanel = new ProductListPanel(mContext);
		cartPanel = new CartPanel(mContext);
		myPagePanel = new MyPagePanel(mContext);
		reviewWritePanel = new ReviewWritePanel(mContext);
		viewOrderPanel = new ViewOrderPanel(mContext);
		
		mintColor = new Color(200, 235, 226);
		grayColor = new Color(232, 239, 239);
		pointColor = new Color(111, 188, 170);
		
	}

	private void setInitLayout() {
		setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		
		topPanel.setBounds(0, 0, 1000, 61);
		topPanel.setBackground(mintColor);
		topPanel.setLayout(null);
		add(topPanel);
			
		gradeLabel.setBounds(10, 17, 30, 30);
		topPanel.add(gradeLabel);
		
		idLabel.setBounds(45, 15, 200, 30);
		idLabel.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		topPanel.add(idLabel);
		
		logoutButton.setBounds(137, 19, 60, 25);
		logoutButton.setBorder(null);
		logoutButton.setBackground(mintColor);
		logoutButton.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		topPanel.add(logoutButton);
		
		homeButton.setBounds(200, 6, 600, 45);
		homeButton.setBorder(null);
		homeButton.setBackground(mintColor);
		homeButton.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		topPanel.add(homeButton);
				
		cartButton.setBounds(880, 14, 40, 50);
		cartButton.setBorder(null);
		cartButton.setBackground(mintColor);
		topPanel.add(cartButton);
		
		myPageButton.setBounds(940, 14, 40, 50);
		myPageButton.setBorder(null);
		myPageButton.setBackground(mintColor);
		topPanel.add(myPageButton);
		
		borderPanel.setBounds(0, 61, 1000, 9);
		borderPanel.setBackground(Color.white);
		add(borderPanel);
		
		categoryPanel.setBounds(0, 70, 201, 730);
		categoryPanel.setBackground(grayColor);
		categoryPanel.setLayout(null);
		add(categoryPanel);
		int initY = 140;
		for (int i = 0; i < categoryButtons.length; i++) {
			categoryButtons[i].setBounds(60, initY += 67, 81, 30);
			categoryButtons[i].setFont(new Font("맑은 고딕", Font.BOLD, 20));
			categoryButtons[i].setBorder(null);
			categoryButtons[i].setBackground(Color.white);
			categoryPanel.add(categoryButtons[i]);
		}
		exitButton.setBounds(60, 630, 81, 30);
		exitButton.setForeground(new Color(153, 0, 0));
		exitButton.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		exitButton.setBorder(null);
		exitButton.setBackground(Color.white);
		categoryPanel.add(exitButton);
		
		categoryLabel.setBounds(13, 30, 175, 650);
		categoryPanel.add(categoryLabel);
	
		productListPanel.setLocation(201, 70);
		add(productListPanel);
		
		cartPanel.setLocation(201, 70);
		cartPanel.setVisible(false);
		add(cartPanel);
		
		myPagePanel.setLocation(201, 70);
		myPagePanel.setVisible(false);
		add(myPagePanel);
		
		reviewWritePanel.setLocation(201, 70);
		reviewWritePanel.setVisible(false);
		add(reviewWritePanel);
		
		viewOrderPanel.setLocation(201, 70);
		viewOrderPanel.setVisible(false);
		add(viewOrderPanel);

		setVisible(true);
	}
	
	private void addEventListener() {
		logoutButton.addActionListener(this);
		homeButton.addActionListener(this);
		cartButton.addActionListener(this);
		myPageButton.addActionListener(this);
		exitButton.addActionListener(this);
		for (int i = 0; i < categoryButtons.length; i++) {
			categoryButtons[i].addActionListener(this);
		}
	}
		
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton targetButton = (JButton) e.getSource();
		
		// 로그아웃 버튼
		if (targetButton == logoutButton) {
			// 로그아웃하면 해당 프레임이 닫히고 로그인 프레임을 다시 열음
			int a = JOptionPane.showConfirmDialog(this, "로그아웃하시겠습니까?", "로그아웃", JOptionPane.YES_NO_OPTION);
			if (a == JOptionPane.YES_OPTION) {
				this.dispose();
				new LoginFrame();
			}
			
		// 홈 버튼
		} else if (targetButton == homeButton) {
			selectedCategory(0);
			productListPanel.showAll();
			visiblePanel(0);
			
		// 종료 버튼
		} else if (targetButton == exitButton) {
			// 종료하면 프로그램이 완전히 종료됨
			int a = JOptionPane.showConfirmDialog(this, "프로그램을 종료하시겠습니까?", "종료", JOptionPane.YES_NO_OPTION);
			if (a == JOptionPane.YES_OPTION) {
				System.exit(0);
			}
			
		// 카테고리 - 전체 버튼
		} else if (targetButton == categoryButtons[0]) {
			selectedCategory(0);
			productListPanel.showAll();
			visiblePanel(0);
			
		// 카테고리 - 의류 버튼
		} else if (targetButton == categoryButtons[1]) {
			selectedCategory(1);
			productListPanel.showClothes();
			visiblePanel(0);
			
		// 카테고리 - 음식 버튼
		} else if (targetButton == categoryButtons[2]) {
			selectedCategory(2);
			productListPanel.showFood();
			visiblePanel(0);
			
		// 카테고리 - 가구 버튼
		} else if (targetButton == categoryButtons[3]) {
			selectedCategory(3);
			productListPanel.showLiving();
			visiblePanel(0);
			
		// 카테고리 - 장난감 버튼
		} else if (targetButton == categoryButtons[4]) {
			selectedCategory(4);
			productListPanel.showToy();
			visiblePanel(0);
			
		// 카테고리 - 기타 버튼
		} else if (targetButton == categoryButtons[5]) {
			selectedCategory(5);
			productListPanel.showEtc();
			visiblePanel(0);
			
		// 장바구니 버튼
		} else if (targetButton == cartButton) {
			selectedCategory(-1);
			cartPanel.viewCart();
			visiblePanel(1);
			
		// 마이페이지 버튼
		} else if (targetButton == myPageButton) {
			selectedCategory(-1);
			myPagePanel.refresh();
			visiblePanel(2);
		}
	} // end of actionPerformed
	
	// 아무것도 선택하지 않았다면 -1을 매개변수로
	public void selectedCategory(int index) {
		for (int i = 0; i < categoryButtons.length; i++) {
			// 해당 카테고리가 선택되었다면 포인트 색을 부여함
			if (i == index) {
				categoryButtons[i].setForeground(pointColor);
			} else {
				// 나머지 카테고리는 검정색 글자로
				categoryButtons[i].setForeground(Color.black);
			}
		}
	}

	// productList : 0 / cart : 1 / myPage : 2 / reviewWrite : 3 / viewOrder : 4
	public void visiblePanel(int index) {
		JPanel[] panelList = {productListPanel, cartPanel, myPagePanel, reviewWritePanel, viewOrderPanel};
		for (int i = 0; i < panelList.length; i++) {
			if (i == index) {
				panelList[i].setVisible(true);
			} else {
				panelList[i].setVisible(false);				
			}
		}
	}

	

	public String getLoginId() {
		return loginId;
	}

	public CartPanel getCartPanel() {
		return cartPanel;
	}

	public JLabel getGradeLabel() {
		return gradeLabel;
	}

	public void setGradeLabel(JLabel gradeLabel) {
		this.gradeLabel = gradeLabel;
	}

	public ProductListPanel getProductListPanel() {
		return productListPanel;
	}

	public ReviewWritePanel getReviewWritePanel() {
		return reviewWritePanel;
	}

	public ShopFrame getmContext() {
		return mContext;
	}

	public MemberController getMemberController() {
		return memberController;
	}

	public JPanel getTopPanel() {
		return topPanel;
	}

	public JLabel getIdLabel() {
		return idLabel;
	}

	public JButton getLogoutButton() {
		return logoutButton;
	}

	public JButton getHomeButton() {
		return homeButton;
	}

	public JButton getMyPageButton() {
		return myPageButton;
	}

	public JButton getCartButton() {
		return cartButton;
	}

	public JPanel getBorderPanel() {
		return borderPanel;
	}

	public JLabel getCategoryLabel() {
		return categoryLabel;
	}

	public JPanel getCategoryPanel() {
		return categoryPanel;
	}

	public JButton[] getCategoryButtons() {
		return categoryButtons;
	}

	public JButton getExitButton() {
		return exitButton;
	}

	public MyPagePanel getMyPagePanel() {
		return myPagePanel;
	}

	public Color getMintColor() {
		return mintColor;
	}

	public Color getGrayColor() {
		return grayColor;
	}

	public Color getPointColor() {
		return pointColor;
	}

	public ViewOrderPanel getViewOrderPanel() {
		return viewOrderPanel;
	}
	
	
		
}
