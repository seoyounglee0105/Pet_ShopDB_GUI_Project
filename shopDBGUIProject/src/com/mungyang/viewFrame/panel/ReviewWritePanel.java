package com.mungyang.viewFrame.panel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.mungyang.controller.MemberController;
import com.mungyang.controller.OrderController;
import com.mungyang.controller.ProductController;
import com.mungyang.controller.ReviewController;
import com.mungyang.dto.OrderDTO;
import com.mungyang.dto.ReviewDTO;
import com.mungyang.viewFrame.LoginFrame;
import com.mungyang.viewFrame.ShopFrame;

// 주문된 상품 중 리뷰를 아직 작성하지 않은 상품이 있는 경우에만 
public class ReviewWritePanel extends JPanel implements ActionListener {

	private ShopFrame mContext;
	private OrderController orderController;
	private ReviewController reviewController;
	private ProductController productController;
	private MemberController memberController;
	private JLabel nameLabel;
	private Color panelColor;
	private JComboBox<String> productNameCombobox; // 리뷰를 작성할 상품을 선택하는 콤보박스
	// 콤보박스에 선택된 값 가져오기 : productNameCombobox.getSelectedItem();
	private JButton[] starButtons = new JButton[5];
	private JTextField titleField;
	private boolean titleHintOff;
	private JTextArea contentArea;
	private JScrollPane contentPane;
	private JLabel imageLabel;
	private JTextField imageField;
	private JButton[] writeButtons = new JButton[2];
	

	public ReviewWritePanel(ShopFrame mContext) {
		this.mContext = mContext;
		initData();
		setInitLayout();
		addEventListener();
	}

	private void initData() {
		setSize(799, 730);
		orderController = new OrderController(mContext);
		reviewController = new ReviewController();
		productController = new ProductController();
		memberController = new MemberController();
		
		panelColor = new Color(230, 230, 230);
		nameLabel = new JLabel("리뷰 작성");
		productNameCombobox = new JComboBox<String>();
		titleField = new JTextField("제목을 입력해주세요.");
		contentArea = new JTextArea();
		contentPane = new JScrollPane(contentArea);
		imageLabel = new JLabel("업로드할 이미지 파일명 : ");
		imageField = new JTextField("");
		
		writeButtons[0] = new JButton("완료");
		writeButtons[1] = new JButton("취소");
		
		for (int i = 0; i < starButtons.length; i++) {
			starButtons[i] = new JButton("☆");
		}
	}

	private void setInitLayout() {
		setBackground(panelColor);
		setLayout(null);
		nameLabel.setBounds(30, 20, 80, 20);
		nameLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		add(nameLabel);
		
		productNameCombobox.setBounds(70, 80, 165, 30);
		productNameCombobox.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		add(productNameCombobox);
		
		titleField.setBounds(70, 130, 640, 40);
		titleField.setForeground(Color.gray);
		titleField.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		add(titleField);
		
		contentPane.setBounds(70, 190, 640, 260);
		contentArea.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		add(contentPane);
		
		imageLabel.setBounds(70, 467, 200, 20);
		imageLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		add(imageLabel);
		
		imageField.setBounds(212, 470, 100, 20);
		imageField.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		add(imageField);
		
		// 작성/취소 버튼
		int x = 280;
		for (int i = 0; i < writeButtons.length; i++) {
			writeButtons[i].setBounds(x, 570, 55, 36);
			writeButtons[i].setBorder(null);
			writeButtons[i].setBackground(new Color(171, 222, 210));
			writeButtons[i].setFont(new Font("맑은 고딕", Font.BOLD, 25));
			add(writeButtons[i]);
			x += 160;
		}
		
		x = 560;
		for (int i = 0; i < starButtons.length; i++) {
			starButtons[i].setBounds(x, 80, 30, 30);
			starButtons[i].setBorder(null);
			starButtons[i].setBackground(panelColor);
			starButtons[i].setFont(new Font("맑은 고딕", Font.BOLD, 30));
			starButtons[i].setForeground(new Color(228, 181, 28));
			add(starButtons[i]);
			x += 30;
		}
	}
	
	private void addEventListener() {
		for (int i = 0; i < starButtons.length; i++) {
			starButtons[i].addActionListener(this);
		}
		
		for (int i = 0; i < writeButtons.length; i++) {
			writeButtons[i].addActionListener(this);
		}
		
		titleField.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if (titleHintOff == false) {
					titleField.setText("");
					titleField.setForeground(Color.black);
					titleHintOff = true;
				}					
			}
			@Override
			public void mousePressed(MouseEvent e) {
			}
			@Override
			public void mouseExited(MouseEvent e) {
			}
			@Override
			public void mouseEntered(MouseEvent e) {
			}
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
	}

	// 초기 상태로
	public void refresh() {
		for (int i = 0; i < starButtons.length; i++) {
			starButtons[i].setText("☆");
		}
		titleHintOff = false;
		titleField.setText("제목을 입력해주세요.");
		titleField.setForeground(Color.gray);
		contentArea.setText("");
		imageField.setText("");
		
		// 해당 회원의 주문 내역에 있는 상품들 중, 리뷰 작성 내역이 없는 것들만 콤보박스에 표시
		// 회원의 주문 내역
		ArrayList<OrderDTO> orderDtoList = orderController.requestSelectOrder(mContext.getLoginMemberDto().getId());
		ArrayList<String> productNames = new ArrayList<String>();
		// 기본값
		productNames.add("상품을 선택해주세요.");
		
		for (OrderDTO dto : orderDtoList) {
			int productId = dto.getProductId();
			// 이미 리뷰가 작성된 상품이라면 콤보박스에 표시 X
			if (reviewController.requestCheckReview(productId, mContext.getLoginMemberDto().getId())) {
				continue;
			}
			
			// 중복된 상품 이름은 추가 X
			if (productNames.contains(dto.getProductName())) {
				continue;
			}
			
			// 작성되지 않았다면 상품 이름을 콤보박스에 추가
			String productName = dto.getProductName();
			productNames.add(productName);
		}
		
		// 콤보박스에 넣기 위해 배열로 변환
		for (int i = 0; i < productNames.size(); i++) {
			productNameCombobox.addItem(productNames.get(i));
		}
		productNameCombobox.setSelectedIndex(0);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton targetButton = (JButton) e.getSource();
		
		// 작성 취소 버튼
		if (targetButton == writeButtons[1]) {
			int a = JOptionPane.showConfirmDialog(this, "작성을 취소하시겠습니까?", "취소", JOptionPane.YES_NO_OPTION);
			if (a == JOptionPane.YES_OPTION) {
				mContext.visiblePanel(3);
			}
			return;
		// 작성 완료 버튼
		} else if (targetButton == writeButtons[0]) {
			int a = JOptionPane.showConfirmDialog(this, "작성을 완료하셨습니까?", "완료", JOptionPane.YES_NO_OPTION);
			if (a == JOptionPane.YES_OPTION) {
				
				// 방어적 코드 : 입력되지 않은 값이 있는 경우 (이미지 파일 제외)
				if (productNameCombobox.getSelectedItem().equals("상품을 선택해주세요.") 
					|| starButtons[0].getText().equals("☆")
					|| titleField.getText().equals("")
					|| contentArea.getText().equals("")) {
					
					JOptionPane.showMessageDialog(null, "입력되지 않은 값이 있습니다.", "실패", JOptionPane.PLAIN_MESSAGE);
					return;
				}
				String writerId = mContext.getLoginMemberDto().getId();
				String productName = (String) productNameCombobox.getSelectedItem();
				int productId = productController.requestSelectByName(productName).getId();
				int star = 0;
				for (int i = starButtons.length - 1; i >= 0; i--) {
					if (starButtons[i].getText().equals("★")) {
						star = (i + 1);
						break;
					}
				}
				String title = titleField.getText();
				String content = contentArea.getText();
				
				String image = null;
				int temp = 0;
				// 이미지 파일명이 입력되어 있다면
				if (imageField.getText().equals("") == false) {
					temp = 1;
					image = imageField.getText();
				}
				
				int result = reviewController.requestWriteReview(new ReviewDTO(writerId, productId, star, title, content));
				
				if (result == 1) {
					// 상품 가격의 5% 만큼 적립금 지급
					int addPoint = (int) (productController.requestSelectByName(productName).getPrice() * 0.05);
					
					int newPoint = memberController.requestUpdatePoint(addPoint, writerId);
					
					if (newPoint != 0) {
						mContext.getLoginMemberDto().setPoint(addPoint);						
					}
					
					JOptionPane.showMessageDialog(null, "리뷰가 작성되었습니다.\n 적립금 " + addPoint + "원을 지급해드렸습니다.", "성공", JOptionPane.PLAIN_MESSAGE);
					System.out.println("리뷰 작성 완료");					
				} else {
					System.out.println("리뷰 작성 실패");
				}
			
				mContext.visiblePanel(3);
			}
			return;
		} // end of 작성완료버튼
		
		// 별점 버튼
		for (int i = 0; i < starButtons.length; i++) {
			if (targetButton == starButtons[i]) {
				choiceStar(i);
			}
		}
	}
	
	public void choiceStar(int index) {
		// 자신과 그 아래 starButton들을 색칠함
		for (int i = 0; i < starButtons.length; i++) {
			if (i <= index) {
				starButtons[i].setText("★");
			} else {
				starButtons[i].setText("☆");
				
			}
		}
	}
}
