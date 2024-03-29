package com.mungyang.viewFrame.panel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.mungyang.controller.CartController;
import com.mungyang.controller.MemberController;
import com.mungyang.controller.ProductController;
import com.mungyang.dto.CartDTO;
import com.mungyang.dto.MemberDTO;
import com.mungyang.viewFrame.OrderFrame;
import com.mungyang.viewFrame.ShopFrame;

public class CartPanel extends JPanel implements ActionListener {

	private ShopFrame mContext;
	private JLabel nameLabel;
	private Color panelColor;
	private CartController cartController;
	private MemberController memberController;
	private ArrayList<CartDTO> currentCarts;
	private JLabel[] labelArray = new JLabel[3];
	private JPanel borderPanel;
	private ArrayList<JLabel[]> productList;
	private JButton[] deleteButtons = new JButton[10];
	private JLabel totalPriceLabel;
	private JButton orderButton;

	public CartPanel(ShopFrame mContext) {
		this.mContext = mContext;
		initData();
		setInitLayout();
		addEventListener();
	}

	private void initData() {
		setSize(799, 730);
		cartController = new CartController();
		memberController = new MemberController();
		panelColor = new Color(230, 230, 230);
		nameLabel = new JLabel("장바구니");

		labelArray[0] = new JLabel("상품 이름");
		labelArray[1] = new JLabel("구매 수량");
		labelArray[2] = new JLabel("총 가격");

		borderPanel = new JPanel();

		productList = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			JLabel[] jLabels = new JLabel[3];
			jLabels[0] = new JLabel("");
			jLabels[1] = new JLabel("");
			jLabels[2] = new JLabel("");
			productList.add(jLabels);
			deleteButtons[i] = new JButton("X");
		}		
		totalPriceLabel = new JLabel();
		orderButton = new JButton("주문하기");
	}

	private void setInitLayout() {
		setBackground(panelColor);
		setLayout(null);
		nameLabel.setBounds(30, 20, 80, 20);
		nameLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		add(nameLabel);

		// 첫 줄
		int x1 = 40;
		for (int j = 0; j < 3; j++) {
			labelArray[j].setBounds(x1, 85, 200, 30);
			labelArray[j].setFont(new Font("맑은 고딕", Font.BOLD, 22));
			add(labelArray[j]);
			x1 += 250;
		}
		borderPanel.setBounds(40, 121, 575, 1);
		borderPanel.setBackground(Color.black);
		add(borderPanel);

		// 데이터가 나오는 곳
		int x = 43;
		int y = 125;
		int yB = 125;
		for (int i = 0; i < productList.size(); i++) {
			for (int j = 0; j < 3; j++) {
				productList.get(i)[j].setBounds(x, y, 200, 30);
				productList.get(i)[j].setFont(new Font("맑은 고딕", Font.BOLD, 18));
				add(productList.get(i)[j]);
				x += 250;
			}
			deleteButtons[i].setBounds(615, yB, 20, 30);
			deleteButtons[i].setBorder(null);
			deleteButtons[i].setBackground(null);
			deleteButtons[i].setForeground(Color.red);
			add(deleteButtons[i]);
			yB += 35;
			x = 43;
			y += 35;
		}
		
		totalPriceLabel.setBounds(60, 530, 400, 36);
		totalPriceLabel.setFont(new Font("맑은 고딕", Font.BOLD, 27));
		add(totalPriceLabel);
		
		orderButton.setBounds(550, 530, 120, 36);
		orderButton.setBorder(null);
		orderButton.setBackground(new Color(171, 222, 210));
		orderButton.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		add(orderButton);
	}

	private void addEventListener() {
		for (int i = 0; i < deleteButtons.length; i++) {
			deleteButtons[i].addActionListener(this);
		}
		orderButton.addActionListener(this);
	}
	
	// 장바구니 정보 초기화
	public void cartInfoNull() {
		for (int i = 0; i < productList.size(); i++) {
			for (int j = 0; j < 3; j++) {
				productList.get(i)[j].setText(null);
			}
			deleteButtons[i].setVisible(false);
		}
	}

	public void viewCart() {
		MemberDTO loginMemberDto = memberController.requestMemberInfo(mContext.getLoginId());
		currentCarts = cartController.requestViewCart(loginMemberDto);
		cartInfoNull();
		if (currentCarts.size() == 0) {
			System.out.println("장바구니에 담긴 상품이 없습니다.");
			totalPriceLabel.setText("총 주문 금액 : " + 0 + "원");			
			return;
		}
		int priceSum = 0;
		
		// 장바구니 항목 개수만큼 반복
		for (int i = 0; i < currentCarts.size(); i++) {
			productList.get(i)[0].setText(currentCarts.get(i).getProductName());
			productList.get(i)[1].setText(currentCarts.get(i).getAmount() + "");
			productList.get(i)[2].setText(currentCarts.get(i).getTotalPrice() + "");
			deleteButtons[i].setVisible(true);
			priceSum += currentCarts.get(i).getTotalPrice();
		}
		
		totalPriceLabel.setText("총 주문 금액 : " + priceSum + "원");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton targetButton = (JButton) e.getSource();
		
		if (targetButton == orderButton) {
			if (currentCarts.size() != 0) {
				new OrderFrame(currentCarts, mContext);
			}
			return;
		}
		
		for (int i = 0; i < deleteButtons.length; i++) {
			if (targetButton == deleteButtons[i]) {
				int a = JOptionPane.showConfirmDialog(this, "해당 상품을 삭제하시겠습니까?", "장바구니 삭제", JOptionPane.YES_NO_OPTION);
				if (a == JOptionPane.YES_OPTION) {
					int result = cartController.requestDeleteProduct(currentCarts.get(i));
					if (result != 1) {
						System.out.println("삭제되지 않음");
					}
					// 동기화
					viewCart();
				}
			}
			
		}
	}
}
