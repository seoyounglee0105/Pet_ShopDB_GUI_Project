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
import com.mungyang.viewFrame.panel.ReviewListPanel;

public class ProductInfoFrame extends JFrame implements ActionListener {

	private ShopFrame mContext;
	private MemberDTO loginMemberDto;	
	private ProductDTO currentProduct;
	private CartController cartController;
	private MemberController memberController;

	private JPanel topPanel;
	private JPanel namePanel;
	private JLabel nameLabel;
	private JPanel pricePanel;
	private JLabel priceLabel;
	private JPanel mainPhotoPanel;
	private JLabel mainPhotoLabel;

	private JLabel amountLabel;
	private JButton amountDownButton;
	private JButton amountUpButton;
	private JTextField amountField;
	private JButton addCartButton;
	
	private ReviewListPanel reviewListPanel;

	private Color grayColor;

	public ProductInfoFrame(ProductDTO targetDto, ShopFrame mContext) {
		this.mContext = mContext;
		this.currentProduct = targetDto;
		initData();
		setInitLayout();
		addEventListener();
	}

	private void initData() {
		setTitle("'" + currentProduct.getName() + "' 상세 페이지");
		setSize(400, 600);
		cartController = new CartController();
		memberController = new MemberController();
		loginMemberDto = memberController.requestMemberInfo(mContext.getLoginId());
		grayColor = new Color(232, 239, 239);
		topPanel = new JPanel();
		namePanel = new JPanel();
		nameLabel = new JLabel(currentProduct.getName() + " ");
		pricePanel = new JPanel();
		priceLabel = new JLabel("판매 가격 : " + currentProduct.getPrice() + "원");
		mainPhotoPanel = new JPanel();
		mainPhotoLabel = new JLabel(new ImageIcon(currentProduct.getMainPhoto()));

		amountLabel = new JLabel("수량");
		amountDownButton = new JButton("◀");
		amountUpButton = new JButton("▶");
		amountField = new JTextField("1");
		addCartButton = new JButton("장바구니에 추가");
		reviewListPanel = new ReviewListPanel(currentProduct, mContext);
	}

	private void setInitLayout() {
		setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		getContentPane().setBackground(grayColor);

		topPanel.setBounds(0, 0, 400, 3);
		add(topPanel);

		namePanel.setBounds(0, 3, 400, 52);
		add(namePanel);

		nameLabel.setFont(new Font("맑은 고딕", Font.BOLD, 33));
		namePanel.add(nameLabel);

		pricePanel.setBounds(0, 55, 400, 40);
		pricePanel.setLayout(null);
		add(pricePanel);

		priceLabel.setFont(new Font("맑은 고딕", Font.BOLD, 17));
		priceLabel.setSize(200, 30);
		switch ((currentProduct.getPrice() + "").length()) {
		case 3: {
			priceLabel.setLocation(122, 0);
			break;
		}
		case 4: {
			priceLabel.setLocation(117, 0);
			break;
		}
		case 5: {
			priceLabel.setLocation(113, 0);
			break;
		}
		case 6: {
			priceLabel.setLocation(108, 0);
			break;
		}
		} // end of switch

		pricePanel.add(priceLabel);

		mainPhotoPanel.setBounds(0, 90, 400, 200);
		mainPhotoPanel.setLayout(null);
		add(mainPhotoPanel);

		mainPhotoLabel.setBounds(90, 0, 200, 200);
		mainPhotoPanel.add(mainPhotoLabel);

		amountLabel.setBounds(50, 310, 30, 30);
		amountLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		add(amountLabel);

		JButton[] amountButtons = { amountDownButton, amountUpButton };
		int buttonX = 85;
		for (int i = 0; i < amountButtons.length; i++) {
			amountButtons[i].setSize(20, 24);
			amountButtons[i].setLocation(buttonX, 313);
			amountButtons[i].setBorder(null);
			amountButtons[i].setBackground(null);
			amountButtons[i].setFont(new Font("맑은 고딕", Font.BOLD, 13));
			add(amountButtons[i]);
			buttonX += 47;
		}

		amountField.setBounds(107, 315, 25, 21);
		amountField.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		amountField.setHorizontalAlignment(JLabel.CENTER);
		add(amountField);

		addCartButton.setBounds(175, 310, 155, 30);
		addCartButton.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		addCartButton.setBorder(null);
		addCartButton.setBackground(new Color(171, 222, 210));
		add(addCartButton);
		
		reviewListPanel.setLocation(22, 359);
		add(reviewListPanel);

		setVisible(true);
	}

	private void addEventListener() {
		addCartButton.addActionListener(this);
		amountDownButton.addActionListener(this);
		amountUpButton.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton targetButton = (JButton) e.getSource();

		if (amountField.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "수량을 숫자로 입력해주세요.", "실패", JOptionPane.PLAIN_MESSAGE);
			return;
		}

		int amount = 0;
		// 방어적 코드 (예외 처리)
		try {
			amount = Integer.parseInt(amountField.getText());
			if (amount > 99) {
				JOptionPane.showMessageDialog(null, "한 종류의 상품은 최대 99개까지만 담을 수 있습니다.", "실패", JOptionPane.PLAIN_MESSAGE);
				amountField.setText("99");
				return;
			}
		} catch (Exception e2) {
			JOptionPane.showMessageDialog(null, "수량을 숫자로 입력해주세요.", "실패", JOptionPane.PLAIN_MESSAGE);
			return;
		}

		// 장바구니 추가 버튼
		if (targetButton == addCartButton) {
			CartDTO cartDTO = new CartDTO(loginMemberDto.getId(), currentProduct.getId(), amount);
			int result = cartController.requestAddProduct(loginMemberDto, cartDTO);

			if (result == 2) {
				// 상품이 중복됨을 알려주고, 수량을 더 추가할 것인지 물음
				int a = JOptionPane.showConfirmDialog(this,
						"이미 장바구니에 해당 상품이 존재합니다." + "\n수량을 " + amount + "개만큼 추가하시겠습니까?", "중복 상품",
						JOptionPane.YES_NO_OPTION);
				if (a == JOptionPane.YES_OPTION) {
					// 수량 갱신 메서드
					CartDTO targetCart = cartController.requestViewCartByProductId(loginMemberDto,
							currentProduct.getId());
					// 기존 수량 + 추가 수량
					int totalAmount = amount + targetCart.getAmount();
					if (totalAmount > 99) {
						JOptionPane.showMessageDialog(null, "한 종류의 상품은 최대 99개까지만 담을 수 있습니다.", "실패", JOptionPane.PLAIN_MESSAGE);						
						return;
					}
					cartController.requestUpdateAmount(totalAmount, cartDTO);
					// 장바구니 패널 새로 동기화
					mContext.getCartPanel().viewCart();
					JOptionPane.showMessageDialog(null, "해당 상품을 장바구니에 담았습니다.", "장바구니 추가", JOptionPane.PLAIN_MESSAGE);
				}

				// 추가 성공
			} else if (result == 1) {
				JOptionPane.showMessageDialog(null, "해당 상품을 장바구니에 담았습니다.", "장바구니 추가", JOptionPane.PLAIN_MESSAGE);
				// 장바구니가 가득 찬 경우
			} else if (result == 3) {
				JOptionPane.showMessageDialog(null, "장바구니가 가득 찼습니다.", "실패", JOptionPane.PLAIN_MESSAGE);
			}
			// 수량 감소 버튼
		} else if (targetButton == amountDownButton) {

			if (amount == 1) {
				return;
			}
			amountField.setText((--amount) + "");
		} else if (targetButton == amountUpButton) {

			if (amount == 99) {
				JOptionPane.showMessageDialog(null, "최대 수량입니다.", "", JOptionPane.PLAIN_MESSAGE);
				return;
			}
			amountField.setText((++amount) + "");
		}
	} // end of actionPerformed
}