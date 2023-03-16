package project.panel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.xml.stream.events.EndDocument;

import project.controller.ProductController;
import project.dto.ProductDTO;
import project.frame.ShopFrame;

public class ProductListPanel extends JPanel implements ActionListener {

	private ShopFrame mContext;
	private ProductController productController;

	// 현재 조회 대상인 DTO 객체들의 리스트
	private ArrayList<ProductDTO> targetDtoList;

	private ArrayList<JLabel[]> productInfoList;
	private JButton pageDownButton;
	private JButton pageUpButton;
	private JLabel currentPageLabel;
	private Color panelColor;

	private int pageCount;
	private int curPage;

	public ProductListPanel(ShopFrame mContext) {
		this.mContext = mContext;
		initData();
		setInitLayout();
		addEventListener();
	}

	private void initData() {
		setSize(799, 730);
		productController = new ProductController();

		productInfoList = new ArrayList<>();

		for (int i = 0; i < 6; i++) {
			JLabel[] array = new JLabel[3];
			for (int j = 0; j < 3; j++) {
				array[j] = new JLabel();
			}
			productInfoList.add(array);
		}

		pageDownButton = new JButton("◁");
		pageUpButton = new JButton("▷");
		currentPageLabel = new JLabel("");
		panelColor = new Color(230, 230, 230);
	}

	private void setInitLayout() {
		setLayout(null);
		setBackground(panelColor);

		// [0] : 상품 사진 (mainPhoto)
		// [1] : 상품 이름 (name)
		// [2] : 상품 가격 (price)
		for (int i = 0; i < 6; i++) {
			productInfoList.get(i)[0].setSize(200, 200);
//			productInfoList.get(i)[0].setIcon(new ImageIcon("images/test.png"));
			productInfoList.get(i)[1].setSize(200, 20);
			productInfoList.get(i)[1].setFont(new Font("맑은 고딕", Font.BOLD, 18));
//			productInfoList.get(i)[1].setText("XXXXX");
			productInfoList.get(i)[2].setSize(200, 20);
			productInfoList.get(i)[2].setFont(new Font("맑은 고딕", Font.BOLD, 15));
//			productInfoList.get(i)[2].setText("XXXXX");			

		}

		// 1번째 줄
		int x = 63;
		int y = 60;
		for (int i = 0; i < 3; i++) {
			productInfoList.get(i)[0].setLocation(x, y);
			productInfoList.get(i)[1].setLocation(x, y + 210);
			productInfoList.get(i)[2].setLocation(x, y + 235);
			x += 230;
		}

		x = productInfoList.get(0)[2].getX();
		y = productInfoList.get(0)[2].getY() + 60;

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

		JButton[] pageButtons = { pageDownButton, pageUpButton };
		int buttonX = 350;

		for (int i = 0; i < pageButtons.length; i++) {
			pageButtons[i].setSize(30, 30);
			pageButtons[i].setLocation(buttonX, 650);
			pageButtons[i].setBorder(null);
			pageButtons[i].setBackground(panelColor);
			pageButtons[i].setFont(new Font("맑은 고딕", Font.BOLD, 20));
			add(pageButtons[i]);
			buttonX += 67;
		}

		currentPageLabel.setSize(30, 30);
		currentPageLabel.setLocation(393, 650);
		currentPageLabel.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		add(currentPageLabel);

	}

	private void addEventListener() {
		pageDownButton.addActionListener(this);
		pageUpButton.addActionListener(this);
	}

	// 처음 조회 기능을 사용했을 때 페이지 1번 화면 출력
	public void initView(ArrayList<ProductDTO> list) {
		// 방어적 코드 : 조회할 상품이 없을 때
		if (pageCount == 0) {
			return;
		}

		// pageCount == 1인 경우 (한 페이지 내에 모든 DTO를 표현할 수 있는 경우)
		if (pageCount == 1) {
			for (int i = 0; i < list.size(); i++) {
				productInfoList.get(i)[0].setIcon(new ImageIcon(list.get(i).getMainPhoto()));
				productInfoList.get(i)[1].setText(list.get(i).getName());
				productInfoList.get(i)[2].setText(list.get(i).getPrice() + "원");
			}
			// 페이지가 2개 이상인 경우
		} else {
			for (int i = 0; i < 6; i++) {
				productInfoList.get(i)[0].setIcon(new ImageIcon(list.get(i).getMainPhoto()));
				productInfoList.get(i)[1].setText(list.get(i).getName());
				productInfoList.get(i)[2].setText(list.get(i).getPrice() + "원");
			}
		}
	}

	// 페이지 up/down 버튼을 눌러서 페이지를 이동했을 때 화면 출력
	public void viewByCurrentPage() {
		// pageCount - 1까지는 항상 6개 모두 출력함

		int startIndex = (curPage - 1) * 6;
		int endIndex = (curPage * 6) - 1;

		// InfoList  0
		
		
		if (curPage < pageCount) {
			for (int i = startIndex; i <= endIndex; i++) {
				for (int j = i - ((curPage - 1) * 6); j <= i - ((curPage - 1) * 6); j++) {
					productInfoList.get(j)[0].setIcon(new ImageIcon(targetDtoList.get(i).getMainPhoto()));
					productInfoList.get(j)[1].setText(targetDtoList.get(i).getName());
					productInfoList.get(j)[2].setText(targetDtoList.get(i).getPrice() + "원");
				}
			}
		}
		// curPage == pageCount가 되면 6개 이하가 출력될 수 있음
		int count = targetDtoList.size() % 6; // 출력될 개수
		// 마지막 인덱스 : (count가 0이 아니라면) 시작 인덱스 + 출력될 개수 - 1
		int endIndex2; 
		if (count != 0) {
			endIndex2 = startIndex + count - 1;			
		} else {
			endIndex2 = startIndex + 5;
		}
		
		// InfoList 인덱스 = DtoList 인덱스 - ((curPage - 1) * 6)
		
		if (curPage == pageCount) {
			for (int i = startIndex; i <= endIndex2; i++) {
				for (int j = i - ((curPage - 1) * 6); j <= i - ((curPage - 1) * 6); j++) {
					productInfoList.get(j)[0].setIcon(new ImageIcon(targetDtoList.get(i).getMainPhoto()));
					productInfoList.get(j)[1].setText(targetDtoList.get(i).getName());
					productInfoList.get(j)[2].setText(targetDtoList.get(i).getPrice() + "원");					
				}
			}
		}
	} // end of viewByCurrentPage

	public void showAll() {
		productInfoNull();
		targetDtoList = productController.requestSelectAll();

		// 현재 페이지 1로 초기화
		curPage = 1;
		currentPageLabel.setText(curPage + "");

		// 총 페이지 수
		pageCount = (int) Math.ceil(targetDtoList.size() / 6.0);
		System.out.println(pageCount + "개의 페이지가 생성됩니다.");

		// 초기 페이지
		initView(targetDtoList);
	}

	public void showClothes() {
		productInfoNull();
		targetDtoList = productController.requestSelectByCategory(1);

		// 현재 페이지 1로 초기화
		curPage = 1;
		currentPageLabel.setText(curPage + "");

		// 페이지 수
		pageCount = (int) Math.ceil(targetDtoList.size() / 6.0);
		System.out.println(pageCount + "개의 페이지가 생성됩니다.");

		// 초기 페이지
		initView(targetDtoList);
	}

	public void showFood() {
		productInfoNull();
		targetDtoList = productController.requestSelectByCategory(2);

		// 현재 페이지 1로 초기화
		curPage = 1;
		currentPageLabel.setText(curPage + "");

		// 페이지 수
		pageCount = (int) Math.ceil(targetDtoList.size() / 6.0);
		System.out.println(pageCount + "개의 페이지가 생성됩니다.");

		// 초기 페이지
		initView(targetDtoList);
	}

	public void showLiving() {
		productInfoNull();
		targetDtoList = productController.requestSelectByCategory(3);

		// 현재 페이지 1로 초기화
		curPage = 1;
		currentPageLabel.setText(curPage + "");

		// 페이지 수
		pageCount = (int) Math.ceil(targetDtoList.size() / 6.0);
		System.out.println(pageCount + "개의 페이지가 생성됩니다.");

		// 초기 페이지
		initView(targetDtoList);
	}

	public void showToy() {
		productInfoNull();
		targetDtoList = productController.requestSelectByCategory(4);

		// 현재 페이지 1로 초기화
		curPage = 1;
		currentPageLabel.setText(curPage + "");

		// 페이지 수
		pageCount = (int) Math.ceil(targetDtoList.size() / 6.0);
		System.out.println(pageCount + "개의 페이지가 생성됩니다.");

		// 초기 페이지
		initView(targetDtoList);
	}

	public void showEtc() {
		productInfoNull();
		targetDtoList = productController.requestSelectByCategory(5);

		// 현재 페이지 1로 초기화
		curPage = 1;
		currentPageLabel.setText(curPage + "");

		// 페이지 수
		pageCount = (int) Math.ceil(targetDtoList.size() / 6.0);
		System.out.println(pageCount + "개의 페이지가 생성됩니다.");

		// 초기 페이지
		initView(targetDtoList);
	}

	// 상품 정보 초기화
	public void productInfoNull() {
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 3; j++) {
				// 사진 라벨이라면
				if (j == 0) {
					productInfoList.get(i)[j].setIcon(null);
				}
				productInfoList.get(i)[j].setText(null);
			}
		}
	} // end of productInfoNull

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton targetButton = (JButton) e.getSource();

		// 페이지 번호를 낮추는 버튼
		if (targetButton == pageDownButton) {
			// 1일 때는 더 이상 낮추지 않음
			if (curPage == 1) {
				System.out.println("최소 페이지 번호입니다.");
				return;
			}
			productInfoNull();
			curPage--;
			currentPageLabel.setText(curPage + "");
			viewByCurrentPage();
			System.out.println("page Down");

			// 페이지 번호를 높이는 버튼
		} else if (targetButton == pageUpButton) {
			// 최대 페이지 번호가 되었다면 더 이상 높이지 않음
			if (curPage == pageCount) {
				System.out.println("최대 페이지 번호입니다.");
				return;
			}
			productInfoNull();
			curPage++;
			currentPageLabel.setText(curPage + "");
			viewByCurrentPage();
			System.out.println("page Up");
		}

	}

}
