package project.viewFrame;

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

import project.controller.CartController;
import project.controller.MemberController;
import project.controller.OrderController;
import project.dto.CartDTO;
import project.dto.MemberDTO;
import project.dto.OrderDTO;

public class OrderFrame extends JFrame implements ActionListener {

	private ShopFrame mContext;
	private ArrayList<CartDTO> targetCarts;
	private MemberDTO loginMemberDto; // 로그인된 회원의 DTO객체
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

		loginMemberDto = mContext.getLoginMemberDto();
		cartController = new CartController();
		orderController = new OrderController();
		memberController = new MemberController();

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
			labelArray[j].setSize(200, 30);
			labelArray[j].setLocation(x, 20);
			labelArray[j].setFont(new Font("맑은 고딕", Font.BOLD, 17));
			add(labelArray[j]);
			x += 220;
		}

		borderPanel.setSize(295, 1);
		borderPanel.setLocation(45, 51);
		borderPanel.setBackground(Color.black);
		add(borderPanel);

		// 장바구니 목록 나올 곳
		x = 50;
		int y = 50;
		for (int i = 0; i < productList.size(); i++) {
			for (int j = 0; j < 2; j++) {
				productList.get(i)[j].setSize(200, 30);
				productList.get(i)[j].setLocation(x, y);
				productList.get(i)[j].setFont(new Font("맑은 고딕", Font.BOLD, 14));
				add(productList.get(i)[j]);
				x += 275;
			}
			x = 50;
			y += 20;
		}

		totalPriceLabel.setSize(400, 36);
		totalPriceLabel.setLocation(45, 270);
		totalPriceLabel.setFont(new Font("맑은 고딕", Font.BOLD, 23));
		add(totalPriceLabel);

		pointLabel.setSize(300, 20);
		pointLabel.setLocation(45, 340);
		pointLabel.setFont(new Font("맑은 고딕", Font.BOLD, 17));
		add(pointLabel);
		pointLabel2.setSize(300, 20);
		pointLabel2.setLocation(45, 365);
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
			orderButtons[i].setSize(55, 36);
			orderButtons[i].setLocation(x, 430);
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

			try {
				money = priceSum + Integer.parseInt(pointField.getText());
				// 만약 텍스트 필드에 잘못된 값이 입력됐을 경우 catch로 빠짐
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(null, "적립금 칸은 비워두시거나 숫자를 입력해주세요.", "실패", JOptionPane.PLAIN_MESSAGE);
				return;
			}

			int a = JOptionPane.showConfirmDialog(this, "총 결제 금액은 " + money + "원입니다.\n결제하시겠습니까?", "",
					JOptionPane.YES_NO_OPTION);
			if (a == JOptionPane.YES_OPTION) {
				for (int i = 0; i < targetCarts.size(); i++) {
					OrderDTO orderDTO = new OrderDTO(loginMemberDto.getId(), targetCarts.get(i).getProductId(),
							targetCarts.get(i).getAmount());
					int result = orderController.requestAddOrder(orderDTO);
					System.out.println(result);
					cartController.requestDeleteProduct(targetCarts.get(i));
				}
				try {
					// 적립금이 제대로 사용되면 갱신
					int updatedPoint = loginMemberDto.getPoint() - Integer.parseInt(pointField.getText());
					memberController.requestUpdatePoint(updatedPoint, loginMemberDto.getId());
				} catch (Exception e2) {
				}
				mContext.getCartPanel().viewCart(); // cartPanel 동기화
				JOptionPane.showMessageDialog(null, "주문이 완료되었습니다.", "주문 성공", JOptionPane.PLAIN_MESSAGE);
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
