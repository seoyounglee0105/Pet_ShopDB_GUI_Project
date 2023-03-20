package com.mungyang.viewFrame.panel;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.mungyang.controller.MemberController;
import com.mungyang.controller.OrderController;
import com.mungyang.dto.MemberDTO;
import com.mungyang.dto.OrderDTO;
import com.mungyang.viewFrame.ShopFrame;

public class ViewOrderPanel extends JPanel {
	
	private ShopFrame mContext;
	private JLabel nameLabel;
	private MemberController memberController;
	private OrderController orderController;
	private Color panelColor;
	
	private JLabel[] labelArray = new JLabel[3];
	private JPanel borderPanel;
	private ArrayList<JLabel[]> orderList;

	public ViewOrderPanel(ShopFrame mContext) {
		this.mContext = mContext;
		initData();
		setInitLayout();
	}

	private void initData() {
		setSize(799, 730);
		memberController = new MemberController();
		orderController = new OrderController(mContext);
		panelColor = new Color(230, 230, 230);
		nameLabel = new JLabel("주문 내역 (최근 10개)");
		
		labelArray[0] = new JLabel("상품 이름");
		labelArray[1] = new JLabel("구매 수량");
		labelArray[2] = new JLabel("총 주문 금액");

		borderPanel = new JPanel();

		orderList = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			JLabel[] jLabels = new JLabel[3];
			jLabels[0] = new JLabel("");
			jLabels[1] = new JLabel("");
			jLabels[2] = new JLabel("");
			orderList.add(jLabels);
		}		
	}
	
	private void setInitLayout() {
		setBackground(panelColor);
		setLayout(null);
		
		nameLabel.setBounds(30, 20, 250, 20);
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
		borderPanel.setBounds(40, 121, 630, 1);
		borderPanel.setBackground(Color.black);
		add(borderPanel);

		// 데이터가 나오는 곳
		int x = 43;
		int y = 125;
		int yB = 125;
		for (int i = 0; i < orderList.size(); i++) {
			for (int j = 0; j < 3; j++) {
				orderList.get(i)[j].setBounds(x, y, 200, 30);
				orderList.get(i)[j].setFont(new Font("맑은 고딕", Font.BOLD, 18));
				add(orderList.get(i)[j]);
				x += 250;
			}
			x = 43;
			y += 35;
		}
	}
	
	// 주문 내역 정보 초기화
	public void orderInfoNull() {
		for (int i = 0; i < orderList.size(); i++) {
			for (int j = 0; j < 3; j++) {
				orderList.get(i)[j].setText(null);
			}
		}
	}

	public void viewOrder() {
		MemberDTO loginMemberDto = memberController.requestMemberInfo(mContext.getLoginId());
		ArrayList<OrderDTO> orders = orderController.requestSelectOrder(mContext.getLoginId());
		
		orderInfoNull();
		if (orders.size() == 0) {
			System.out.println("주문 내역이 없습니다.");
			return;
		}
		
		// 장바구니 항목 개수만큼 반복
		for (int i = 0; i < orders.size(); i++) {
			orderList.get(i)[0].setText(orders.get(i).getProductName());
			orderList.get(i)[1].setText(orders.get(i).getAmount() + "");
			orderList.get(i)[2].setText(orders.get(i).getTotalPrice() + "");
		}
	}
	
}
