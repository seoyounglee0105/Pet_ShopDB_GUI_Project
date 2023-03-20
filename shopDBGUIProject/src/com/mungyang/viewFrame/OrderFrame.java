package com.mungyang.viewFrame;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.mungyang.controller.CartController;
import com.mungyang.controller.MemberController;
import com.mungyang.controller.OrderController;
import com.mungyang.dto.CartDTO;
import com.mungyang.dto.MemberDTO;
import com.mungyang.dto.OrderDTO;

public class OrderFrame extends JFrame implements ActionListener {

	private ShopFrame mContext;
	private MemberDTO loginMemberDto;
	private ArrayList<CartDTO> targetCarts;
	private CartController cartController;
	private OrderController orderController;
	private MemberController memberController;

	private JLabel[] labelArray = new JLabel[2];
	private JPanel borderPanel;
	private ArrayList<JLabel[]> productList;
	private JLabel totalPriceLabel;
	private JLabel pointLabel;
	private JLabel pointLabel2;
	private JButton pointButton;
	private JTextField pointField;
	private JButton[] orderButtons = new JButton[2];
	private Color grayColor;
	private int priceSum; // 총 주문 금액

	public OrderFrame(ArrayList<CartDTO> targetCarts, ShopFrame mContext) {
		this.targetCarts = targetCarts;
		this.mContext = mContext;
		initData();
		setInitLayout();
		addEventListener();
	}

	private void initData() {
		setTitle("주문");
		setSize(400, 550);

		cartController = new CartController();
		orderController = new OrderController(mContext);
		memberController = new MemberController();
		// OrderFrame은 사용된 후 닫히므로, initData에서 한 번만 초기화해주면 됨
		loginMemberDto = memberController.requestMemberInfo(mContext.getLoginId());

		labelArray[0] = new JLabel("상품 이름");
		labelArray[1] = new JLabel("구매 수량");

		borderPanel = new JPanel();

		productList = new ArrayList<>();
		for (int i = 0; i < targetCarts.size(); i++) {
			JLabel[] jLabels = new JLabel[2];
			jLabels[0] = new JLabel(targetCarts.get(i).getProductName());
			jLabels[1] = new JLabel(targetCarts.get(i).getAmount() + "");
			priceSum += targetCarts.get(i).getTotalPrice();
			productList.add(jLabels);
		}
		totalPriceLabel = new JLabel("총 주문 금액 : " + priceSum + "원");
		pointLabel = new JLabel("적립금 사용");
		pointLabel2 = new JLabel("(현재 적립금 : " + loginMemberDto.getPoint() + "원)");
		pointButton = new JButton("모두 사용");
		pointField = new JTextField();

		orderButtons[0] = new JButton("결정");
		orderButtons[1] = new JButton("취소");
		grayColor = new Color(232, 239, 239);

	}

	private void setInitLayout() {
		setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		getContentPane().setBackground(grayColor);

		// 첫 줄
		int x = 45;
		for (int j = 0; j < 2; j++) {
			labelArray[j].setBounds(x, 20, 200, 30);
			labelArray[j].setFont(new Font("맑은 고딕", Font.BOLD, 17));
			add(labelArray[j]);
			x += 220;
		}
		borderPanel.setBounds(45, 51, 295, 1);
		borderPanel.setBackground(Color.black);
		add(borderPanel);

		// 장바구니 목록 나올 곳
		x = 50;
		int y = 50;
		for (int i = 0; i < productList.size(); i++) {
			for (int j = 0; j < 2; j++) {
				productList.get(i)[j].setBounds(x, y, 200, 30);
				productList.get(i)[j].setFont(new Font("맑은 고딕", Font.BOLD, 14));
				add(productList.get(i)[j]);
				x += 275;
			}
			x = 50;
			y += 20;
		}

		totalPriceLabel.setBounds(45, 270, 400, 36);
		totalPriceLabel.setFont(new Font("맑은 고딕", Font.BOLD, 23));
		add(totalPriceLabel);

		pointLabel.setBounds(45, 340, 300, 20);
		pointLabel.setFont(new Font("맑은 고딕", Font.BOLD, 17));
		add(pointLabel);
		pointLabel2.setBounds(45, 365, 300, 20);
		pointLabel2.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		add(pointLabel2);
		pointButton.setBounds(215, 339, 97, 20);
		pointButton.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		pointButton.setBorder(null);
		pointButton.setBackground(null);
		pointButton.setForeground(new Color(111, 188, 170));
		add(pointButton);
		pointField.setBounds(150, 342, 65, 20);
		add(pointField);

		// 주문/취소 버튼
		x = 83;
		for (int i = 0; i < orderButtons.length; i++) {
			orderButtons[i].setBounds(x, 430, 55, 36);
			orderButtons[i].setBorder(null);
			orderButtons[i].setBackground(new Color(171, 222, 210));
			orderButtons[i].setFont(new Font("맑은 고딕", Font.BOLD, 25));
			add(orderButtons[i]);
			x += 160;
		}

		setVisible(true);
	}

	private void addEventListener() {
		orderButtons[0].addActionListener(this);
		orderButtons[1].addActionListener(this);
		pointButton.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton targetButton = (JButton) e.getSource();

		// 결정 버튼
		if (targetButton == orderButtons[0]) {
			int money = 0;
			int usedPoint = 0;
			
			try {
				if (pointField.getText().equals("") || Integer.parseInt(pointField.getText()) == 0) {
					usedPoint = 0;
				// 보유 적립금보다 더 많이 입력한 경우
				} else if (Integer.parseInt(pointField.getText()) > loginMemberDto.getPoint()) {
					JOptionPane.showMessageDialog(null, "보유한 적립금보다 큰 금액을 입력하셨습니다.", "실패", JOptionPane.PLAIN_MESSAGE);
					return;
				// 사용 적립금을 음수로 입력한 경우
				} else if (Integer.parseInt(pointField.getText()) < 0) {
					JOptionPane.showMessageDialog(null, "0 이상의 금액을 입력해주세요.", "실패", JOptionPane.PLAIN_MESSAGE);
					return;					
				} else {
					usedPoint = Integer.parseInt(pointField.getText());
				}
			// 만약 텍스트 필드에 잘못된 값이 입력됐을 경우 catch로 빠짐					
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(null, "적립금 칸은 비워두시거나 숫자를 입력해주세요.", "실패", JOptionPane.PLAIN_MESSAGE);
				return;
			} // end of catch
			
			money = priceSum - usedPoint;

			int result = 0;
			int a = JOptionPane.showConfirmDialog(this, "총 결제 금액은 " + money + "원입니다.\n결제하시겠습니까?", "",
					JOptionPane.YES_NO_OPTION);
			if (a == JOptionPane.YES_OPTION) {
				for (int i = 0; i < targetCarts.size(); i++) {
					// cart 테이블의 레코드 하나씩을 order 테이블로 옮기고 cart에서 삭제함
					OrderDTO orderDTO = new OrderDTO(loginMemberDto.getId(), targetCarts.get(i).getProductId(),
							targetCarts.get(i).getAmount());
					result = orderController.requestAddOrder(orderDTO);
					cartController.requestDeleteProduct(targetCarts.get(i));
				}
				// 적립금 수정
				int newPoint = memberController.requestUpdatePoint((-1 * usedPoint), loginMemberDto.getId());
				mContext.getCartPanel().viewCart(); // cartPanel 동기화
				JOptionPane.showMessageDialog(null, "주문이 완료되었습니다.", "주문 성공", JOptionPane.PLAIN_MESSAGE);
				if (result == 2) {
					String newGrade = memberController.requestMemberInfo(mContext.getLoginId()).getMemberGrade();
					JOptionPane.showMessageDialog(null, "축하합니다!\n회원 등급이 " + newGrade + "로 변경되셨습니다!", "회원 등급 변경", JOptionPane.PLAIN_MESSAGE);					
				}
				this.dispose();
			}
			// 취소 버튼
		} else if (targetButton == orderButtons[1]) {
			int a = JOptionPane.showConfirmDialog(this, "주문을 취소하시겠습니까?", "", JOptionPane.YES_NO_OPTION);
			if (a == JOptionPane.YES_OPTION) {
				this.dispose();
			}
			// 적립금 모두 사용 버튼
		} else if (targetButton == pointButton) {
			pointField.setText(loginMemberDto.getPoint() + "");
		}

	}

}
