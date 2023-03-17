package project.viewFrame;

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

import project.controller.CartController;
import project.controller.ProductController;
import project.dto.CartDTO;
import project.dto.MemberDTO;
import project.dto.ProductDTO;

public class ProductInfoFrame extends JFrame implements ActionListener {
	
	private ProductController productController;
	private ShopFrame mContext;
	private CartController cartController;
	private MemberDTO loginMember;
	private ProductDTO currentProduct;
	
	private JPanel topPanel;
	private JPanel namePanel;
	private JLabel nameLabel;
	private JPanel pricePanel;
	private JLabel priceLabel;
	private JPanel mainPhotoPanel;
	private JLabel mainPhotoLabel;
	
	private JLabel amountLabel;
	private JTextField amountField;
	private JButton addCartButton;
	
	private Color grayColor;

	public ProductInfoFrame(ProductDTO targetDto, MemberDTO loginMember, ShopFrame mContext) {
		this.mContext = mContext;
		this.loginMember = loginMember;
		this.currentProduct = targetDto;
		productController = new ProductController();
		cartController = new CartController();
		initData();
		setInitLayout();
		addEventListenr();
	}
	
	private void initData() {
		setTitle("'" + currentProduct.getName() + "' 상세 페이지");
		setSize(400, 600);
		grayColor = new Color(232, 239, 239);
		topPanel = new JPanel();
		namePanel = new JPanel();
		nameLabel = new JLabel(currentProduct.getName() + " ");
		pricePanel = new JPanel();
		priceLabel = new JLabel("판매 가격 : " + currentProduct.getPrice() + "원");
		mainPhotoPanel = new JPanel();
		mainPhotoLabel = new JLabel(new ImageIcon(currentProduct.getMainPhoto()));
		
		amountLabel = new JLabel("수량");
		amountField = new JTextField("1");
		addCartButton = new JButton("장바구니에 추가");
	}

	private void setInitLayout() {
		setLayout(null);
		setResizable(false);	
		setLocationRelativeTo(null);
		getContentPane().setBackground(grayColor);
		
		topPanel.setSize(400, 3);
		topPanel.setLocation(0, 0);
		add(topPanel);
		
		namePanel.setSize(400, 52);
		namePanel.setLocation(0, 3);
		add(namePanel);

		nameLabel.setFont(new Font("맑은 고딕", Font.BOLD, 33));
		namePanel.add(nameLabel);

		pricePanel.setSize(400, 40);
		pricePanel.setLocation(0, 55);
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

		mainPhotoPanel.setSize(400, 200);
		mainPhotoPanel.setLocation(0, 90);
		mainPhotoPanel.setLayout(null);
		add(mainPhotoPanel);
		
		mainPhotoLabel.setSize(200, 200);
		mainPhotoLabel.setLocation(90, 0);
		mainPhotoPanel.add(mainPhotoLabel);
		
		amountLabel.setSize(30, 30);
		amountLabel.setLocation(50, 310);
		amountLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		add(amountLabel);
		
		amountField.setSize(30, 23);
		amountField.setLocation(90, 315);
		amountField.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		add(amountField);
		
		addCartButton.setSize(155, 30);
		addCartButton.setLocation(175, 310);
		addCartButton.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		addCartButton.setBorder(null);
		addCartButton.setBackground(new Color(171, 222, 210));
		add(addCartButton);
		
		setVisible(true);
	}
	
	private void addEventListenr() {
		addCartButton.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton targetButton = (JButton) e.getSource();
		
		if (targetButton == addCartButton) {
			if (amountField.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "수량을 숫자로 입력해주세요.", "실패", JOptionPane.PLAIN_MESSAGE);
				return;
			}
			int amount = 0;
			// 방어적 코드 (예외 처리)
			try {
				amount = Integer.parseInt(amountField.getText());
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(null, "수량을 숫자로 입력해주세요.", "실패", JOptionPane.PLAIN_MESSAGE);
				return;
			}
			CartDTO cartDTO = new CartDTO(loginMember.getId(), currentProduct.getId(), amount);
			int result = cartController.requestAddProduct(loginMember, cartDTO);
			
			if (result == 2) {
				// 상품이 중복됨을 알려주고, 수량을 더 추가할 것인지 물음
				int a = JOptionPane.showConfirmDialog(this,
						"이미 장바구니에 해당 상품이 존재합니다." + "\n수량을 " + amount + "개만큼 추가하시겠습니까?", "중복 상품",
						JOptionPane.YES_NO_OPTION);
				if (a == JOptionPane.YES_OPTION) {
					// 수량 갱신 메서드
					CartDTO targetCart = cartController.requestViewCartByProductId(loginMember, currentProduct.getId());
					// 기존 수량 + 추가 수량
					int totalAmount = amount + targetCart.getAmount();
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
		}
	} // end of actionPerformed	
}