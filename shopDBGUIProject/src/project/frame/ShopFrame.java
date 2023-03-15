package project.frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;

import project.controller.MemberController;
import project.dto.MemberDTO;
import project.panel.CartPanel;
import project.panel.MainPanel;
import project.panel.MyPagePanel;

public class ShopFrame extends JFrame implements ActionListener {

	private ShopFrame mContext = this;
	private MemberController memberController;
	private MemberDTO loginMemberDto; // 로그인된 회원의 DTO객체
	
	private JPanel topPanel; // 상단 패널
	private JLabel gradeLabel; // 로그인된 회원의 등급 아이콘
	private JLabel idLabel; // 로그인된 회원의 아이디
	private JButton logoutButton; // 로그아웃 버튼
	private JButton homeButton; // 홈 버튼
	private JButton myPageButton; // 마이페이지 버튼
	private JButton cartButton; // 장바구니 버튼
	private JPanel borderPanel; // 상단과 메인 패널 사이 경계
	
	private MainPanel mainPanel;
	private CartPanel cartPanel;
	private MyPagePanel myPagePanel;
	private Color mintColor;
	
	public ShopFrame(MemberDTO loginMember) {
		this.loginMemberDto = loginMember;
		initData();
		setInitLayout();
		addEventListener();
	}
	
	private void initData() {
		setTitle("홈페이지");
		setSize(1000, 800);
		
		memberController = new MemberController();
		
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
		idLabel.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		
		logoutButton = new JButton("로그아웃");
		homeButton = new JButton("Shopping Mall");
		myPageButton = new JButton(new ImageIcon("images/home.png"));
		cartButton = new JButton(new ImageIcon("images/bag.png"));
		
		borderPanel = new JPanel();
		mintColor = new Color(200, 235, 226);
		mainPanel = new MainPanel(mContext);
	}
	
	// 프레임 기본 설정 + 상단 패널 관련
	private void setInitLayout() {
		setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		
		topPanel.setLocation(0, 0);
		topPanel.setSize(1000, 60);
		topPanel.setBackground(mintColor);
		topPanel.setLayout(null);
		add(topPanel);
			
		gradeLabel.setSize(30,30);
		gradeLabel.setLocation(10, 16);
		topPanel.add(gradeLabel);
		
		idLabel.setSize(200, 30);
		idLabel.setLocation(45, 14);
		topPanel.add(idLabel);
		
		logoutButton.setSize(85, 25);
		logoutButton.setLocation(130, 18);
		logoutButton.setBorder(null);
		logoutButton.setBackground(mintColor);
		logoutButton.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		topPanel.add(logoutButton);
		
		homeButton.setSize(600, 54);
		homeButton.setLocation(200, 3);
		homeButton.setBorder(null);
		homeButton.setBackground(mintColor);
		homeButton.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		topPanel.add(homeButton);
				
		cartButton.setSize(40, 50);
		cartButton.setLocation(880, 13);
		cartButton.setBorder(null);
		cartButton.setBackground(mintColor);
		topPanel.add(cartButton);
		
		myPageButton.setSize(40, 50);
		myPageButton.setLocation(930, 13);
		myPageButton.setBorder(null);
		myPageButton.setBackground(mintColor);
		topPanel.add(myPageButton);
		
		borderPanel.setSize(1000, 7);
		borderPanel.setLocation(0, 60);
		add(borderPanel);
		
		mainPanel.setLocation(0, 70);
		add(mainPanel);
		
		setVisible(true);
	}
	
	private void addEventListener() {
		logoutButton.addActionListener(this);
		homeButton.addActionListener(this);
		cartButton.addActionListener(this);
		myPageButton.addActionListener(this);
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
			mainPanel.setVisible(true);
			
		// 장바구니 버튼
		} else if (targetButton == cartButton) {
			mainPanel.setVisible(false);	
			
		} else if (targetButton == myPageButton) {
			mainPanel.setVisible(false);	
		}
	} // end of actionPerformed
}
