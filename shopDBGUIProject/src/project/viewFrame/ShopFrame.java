package project.viewFrame;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import project.controller.MemberController;
import project.controller.ProductController;
import project.dto.MemberDTO;
import project.panel.CartPanel;
import project.panel.MainPanel;
import project.panel.MyPagePanel;
import project.panel.ProductListPanel;

public class ShopFrame extends JFrame implements ActionListener {

	private ShopFrame mContext = this;
	private MemberDTO loginMemberDto; // 로그인된 회원의 DTO객체
	
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
	
	private MainPanel mainPanel; // 홈 버튼 누르면 나오는 패널
	private ProductListPanel productListPanel; // 카테고리 누르면 나오는 패널
	
	private CartPanel cartPanel;
	private MyPagePanel myPagePanel;
	
	private Color mintColor;
	private Color grayColor;
	private Color pointColor;
	
	public ShopFrame(MemberDTO loginMember) {
		this.loginMemberDto = loginMember;
		initData();
		setInitLayout();
		addEventListener();
	}
	
	private void initData() {
		setTitle("홈페이지");
		setSize(1000, 800);
		
		// 회원 등급 아이콘
		ImageIcon gradeIcon = null;
		
		topPanel = new JPanel();
		if (loginMemberDto.getMemberGrade().equals("Gold")) {
			gradeIcon = new ImageIcon("images/gold.png");			
		} else if (loginMemberDto.getMemberGrade().equals("Silver")) {
			gradeIcon = new ImageIcon("images/silver.png");						
		} else {
			gradeIcon = new ImageIcon("images/bronze.png");		
		}
		gradeLabel = new JLabel(gradeIcon);
		
		// 회원 아이디
		idLabel = new JLabel(loginMemberDto.getName() + " 님");
		
		logoutButton = new JButton("로그아웃");
		homeButton = new JButton("Pet Shopping Mall");
		myPageButton = new JButton(new ImageIcon("images/home.png"));
		cartButton = new JButton(new ImageIcon("images/bag.png"));
		borderPanel = new JPanel();
		categoryLabel = new JLabel(new ImageIcon("images/category2.png"));
		categoryPanel = new JPanel();
		
//		String[] categoryNames = {"전체", "의류", "음식", "가구", "장난감", "기타"};
		String[] categoryNames = {"All", "Clothes", "Food", "Living", "Toy", "etc."};
		for (int i = 0; i < categoryButtons.length; i++) {
			categoryButtons[i] = new JButton(categoryNames[i]);
		}
		exitButton = new JButton("Exit");
		
		mainPanel = new MainPanel(mContext);
		productListPanel = new ProductListPanel(mContext);
		cartPanel = new CartPanel(mContext);
		myPagePanel = new MyPagePanel(mContext);
		
		mintColor = new Color(200, 235, 226);
		grayColor = new Color(232, 239, 239);
		pointColor = new Color(111, 188, 170);
		
	}

	private void setInitLayout() {
		setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		
		topPanel.setLocation(0, 0);
		topPanel.setSize(1000, 61);
		topPanel.setBackground(mintColor);
		topPanel.setLayout(null);
		add(topPanel);
			
		gradeLabel.setSize(30,30);
		gradeLabel.setLocation(10, 17);
		topPanel.add(gradeLabel);
		
		idLabel.setSize(200, 30);
		idLabel.setLocation(45, 15);
		idLabel.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		topPanel.add(idLabel);
		
		logoutButton.setSize(60, 25);
		logoutButton.setLocation(137, 19);
		logoutButton.setBorder(null);
		logoutButton.setBackground(mintColor);
		logoutButton.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		topPanel.add(logoutButton);
		
		homeButton.setSize(600, 45);
		homeButton.setLocation(200, 8);
		homeButton.setBorder(null);
		homeButton.setBackground(mintColor);
		homeButton.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		topPanel.add(homeButton);
				
		cartButton.setSize(40, 50);
		cartButton.setLocation(880, 14);
		cartButton.setBorder(null);
		cartButton.setBackground(mintColor);
		topPanel.add(cartButton);
		
		myPageButton.setSize(40, 50);
		myPageButton.setLocation(930, 14);
		myPageButton.setBorder(null);
		myPageButton.setBackground(mintColor);
		topPanel.add(myPageButton);
		
		borderPanel.setLocation(0, 61);
		borderPanel.setSize(1000, 9);
		borderPanel.setBackground(Color.white);
		add(borderPanel);
		
		categoryPanel.setLocation(0, 70);
		categoryPanel.setSize(201, 730);
		categoryPanel.setBackground(grayColor);
		categoryPanel.setLayout(null);
		add(categoryPanel);
		int initY = 140;
		for (int i = 0; i < categoryButtons.length; i++) {
			categoryButtons[i].setLocation(60, initY += 67);
			categoryButtons[i].setSize(81, 30);
			categoryButtons[i].setFont(new Font("맑은 고딕", Font.BOLD, 20));
			categoryButtons[i].setBorder(null);
			categoryButtons[i].setBackground(Color.white);
			categoryPanel.add(categoryButtons[i]);
		}
		exitButton.setLocation(60, 630);
		exitButton.setSize(81, 30);
		exitButton.setForeground(new Color(153, 0, 0));
		exitButton.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		exitButton.setBorder(null);
		exitButton.setBackground(Color.white);
		
		categoryPanel.add(exitButton);
		categoryLabel.setLocation(13, 30);
		categoryLabel.setSize(175, 650);
		categoryPanel.add(categoryLabel);
	
		mainPanel.setLocation(201, 70);
		add(mainPanel);
		
		productListPanel.setLocation(201, 70);
		productListPanel.setVisible(false);
		add(productListPanel);
		
		cartPanel.setLocation(201, 70);
		cartPanel.setVisible(false);
		add(cartPanel);
		
		myPagePanel.setLocation(201, 70);
		myPagePanel.setVisible(false);
		add(myPagePanel);

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
			selectedCategory(-1);
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
			visiblePanel(1);
			
		// 카테고리 - 의류 버튼
		} else if (targetButton == categoryButtons[1]) {
			selectedCategory(1);
			productListPanel.showClothes();
			visiblePanel(1);
			
		// 카테고리 - 음식 버튼
		} else if (targetButton == categoryButtons[2]) {
			selectedCategory(2);
			productListPanel.showFood();
			visiblePanel(1);
			
		// 카테고리 - 가구 버튼
		} else if (targetButton == categoryButtons[3]) {
			selectedCategory(3);
			productListPanel.showLiving();
			visiblePanel(1);
			
		// 카테고리 - 장난감 버튼
		} else if (targetButton == categoryButtons[4]) {
			selectedCategory(4);
			productListPanel.showToy();
			visiblePanel(1);
			
		// 카테고리 - 기타 버튼
		} else if (targetButton == categoryButtons[5]) {
			selectedCategory(5);
			productListPanel.showEtc();
			visiblePanel(1);
			
		// 장바구니 버튼
		} else if (targetButton == cartButton) {
			selectedCategory(-1);
			cartPanel.viewCart();
			visiblePanel(2);
			
		// 마이페이지 버튼
		} else if (targetButton == myPageButton) {
			selectedCategory(-1);
			visiblePanel(3);
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

	// main : 0 / productList : 1 / cart : 2 / myPage : 3
	public void visiblePanel(int index) {
		JPanel[] panelList = {mainPanel, productListPanel, cartPanel, myPagePanel};
		for (int i = 0; i < panelList.length; i++) {
			panelList[i].setVisible(false);
			if (i == index) {
				panelList[i].setVisible(true);
			}
		}
	}

	public MemberDTO getLoginMemberDto() {
		return loginMemberDto;
	}

	public CartPanel getCartPanel() {
		return cartPanel;
	}
	
	
		
}
