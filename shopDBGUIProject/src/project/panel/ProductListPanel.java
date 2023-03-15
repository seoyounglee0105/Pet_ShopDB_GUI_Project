package project.panel;

import java.awt.Font;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import project.controller.ProductController;
import project.dto.ProductDTO;
import project.frame.ShopFrame;

public class ProductListPanel extends JPanel {

	private ShopFrame mContext;
	private JLabel titleLabel;
	private ProductController productController;
	private ArrayList<JLabel[]> productInfoList;

	public ProductListPanel(ShopFrame mContext) {
		this.mContext = mContext;
		initData();
		setInitLayout();
	}

	private void initData() {
		setSize(799, 730);
		titleLabel = new JLabel("");
		productController = new ProductController();

		productInfoList = new ArrayList<>();

		for (int i = 0; i < 6; i++) {
			JLabel[] array = new JLabel[3];
			for (int j = 0; j < 3; j++) {
				array[j] = new JLabel();
			}
			productInfoList.add(array);
		}

	}

	private void setInitLayout() {
		setLayout(null);
		setBackground(mContext.getPanelColor());
		titleLabel.setSize(100, 20);
		titleLabel.setLocation(350, 20);
		add(titleLabel);

		// [0] : 상품 사진 (mainPhoto)
		// [1] : 상품 이름 (name)
		// [2] : 상품 가격 (price)
		for (int i = 0; i < 6; i++) {
			productInfoList.get(i)[0].setSize(200, 200);
//			productInfoList.get(i)[0].setIcon(new ImageIcon("images/test.png"));
			productInfoList.get(i)[1].setSize(200, 20);
			productInfoList.get(i)[1].setFont(new Font("맑은 고딕", Font.BOLD, 20));
//			productInfoList.get(i)[1].setText("XXXXX");
			productInfoList.get(i)[2].setSize(200, 20);
			productInfoList.get(i)[2].setFont(new Font("맑은 고딕", Font.BOLD, 15));
//			productInfoList.get(i)[2].setText("XXXXX");			

		}

		// 1번째 줄
		int x = 63;
		int y = 50;
		for (int i = 0; i < 3; i++) {
			productInfoList.get(i)[0].setLocation(x, y);
			productInfoList.get(i)[1].setLocation(x, y + 210);
			productInfoList.get(i)[2].setLocation(x, y + 235);

			x += 230;
		}

		x = productInfoList.get(0)[2].getX();
		y = productInfoList.get(0)[2].getY() + 80;

		// 2번째 줄
		for (int i = 3; i < 6; i++) {
			productInfoList.get(i)[0].setLocation(x, y);
			productInfoList.get(i)[1].setLocation(x, y + 210);
			productInfoList.get(i)[2].setLocation(x, y + 235);

			x += 230;
		}

		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 3; j++) {
				add(productInfoList.get(i)[j]);
			}
		}
	}

	public void ShowAll() {
		ProductInfoNull();
		titleLabel.setText("All");
		ArrayList<ProductDTO> list = productController.requestSelectAll();

		// 한 페이지 내에 모든 DTO를 표현할 수 있다면
		if (list.size() <= 6) {
			for (int i = 0; i < list.size(); i++) {
				productInfoList.get(i)[0].setIcon(new ImageIcon(list.get(i).getMainPhoto()));
				productInfoList.get(i)[1].setText(list.get(i).getName());
				productInfoList.get(i)[2].setText(list.get(i).getPrice() + "원");

			}
		}

		// 여기 안에 페이지 버튼 만들어서 누르면 넘어가게

	}

	public void ShowClothes() {
		ProductInfoNull();
		titleLabel.setText("Clothes");
		ArrayList<ProductDTO> list = productController.requestSelectByCategory(1);

		// 한 페이지 내에 모든 DTO를 표현할 수 있다면
		if (list.size() <= 6) {
			for (int i = 0; i < list.size(); i++) {
				productInfoList.get(i)[0].setIcon(new ImageIcon(list.get(i).getMainPhoto()));
				productInfoList.get(i)[1].setText(list.get(i).getName());
				productInfoList.get(i)[2].setText(list.get(i).getPrice() + "원");

			}
		}
	}

	public void ShowFood() {
		ProductInfoNull();
		titleLabel.setText("Food");
		ArrayList<ProductDTO> list = productController.requestSelectByCategory(2);

		// 한 페이지 내에 모든 DTO를 표현할 수 있다면
		if (list.size() <= 6) {
			for (int i = 0; i < list.size(); i++) {
				productInfoList.get(i)[0].setIcon(new ImageIcon(list.get(i).getMainPhoto()));
				productInfoList.get(i)[1].setText(list.get(i).getName());
				productInfoList.get(i)[2].setText(list.get(i).getPrice() + "원");

			}
		}
	}

	public void ShowLiving() {
		ProductInfoNull();
		titleLabel.setText("Living");
		ArrayList<ProductDTO> list = productController.requestSelectByCategory(3);

		// 한 페이지 내에 모든 DTO를 표현할 수 있다면
		if (list.size() <= 6) {
			for (int i = 0; i < list.size(); i++) {
				productInfoList.get(i)[0].setIcon(new ImageIcon(list.get(i).getMainPhoto()));
				productInfoList.get(i)[1].setText(list.get(i).getName());
				productInfoList.get(i)[2].setText(list.get(i).getPrice() + "원");

			}
		}

	}

	public void ShowToy() {
		ProductInfoNull();
		titleLabel.setText("Toy");
		ArrayList<ProductDTO> list = productController.requestSelectByCategory(4);

		// 한 페이지 내에 모든 DTO를 표현할 수 있다면
		if (list.size() <= 6) {
			for (int i = 0; i < list.size(); i++) {
				productInfoList.get(i)[0].setIcon(new ImageIcon(list.get(i).getMainPhoto()));
				productInfoList.get(i)[1].setText(list.get(i).getName());
				productInfoList.get(i)[2].setText(list.get(i).getPrice() + "원");

			}
		}

	}

	public void ShowEtc() {
		ProductInfoNull();
		titleLabel.setText("etc.");
		ArrayList<ProductDTO> list = productController.requestSelectByCategory(5);

		// 한 페이지 내에 모든 DTO를 표현할 수 있다면
		if (list.size() <= 6) {
			for (int i = 0; i < list.size(); i++) {
				productInfoList.get(i)[0].setIcon(new ImageIcon(list.get(i).getMainPhoto()));
				productInfoList.get(i)[1].setText(list.get(i).getName());
				productInfoList.get(i)[2].setText(list.get(i).getPrice() + "원");

			}
		}

	}

	// 상품 정보 초기화
	public void ProductInfoNull() {
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 3; j++) {
				// 사진 라벨이라면
				if (j == 0) {
					productInfoList.get(i)[j].setIcon(null);
				}
				productInfoList.get(i)[j].setText(null);
			}
		}
	}

}
