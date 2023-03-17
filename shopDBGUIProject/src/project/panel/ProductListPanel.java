package project.panel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.xml.stream.events.EndDocument;

import project.controller.ProductController;
import project.dto.ProductDTO;
import project.viewFrame.LoginFrame;
import project.viewFrame.ProductInfoFrame;
import project.viewFrame.ShopFrame;

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
	private Color pointColor;
	
	// 정렬 버튼
	private JButton[] orderByButtons = new JButton[3];

	private int pageCount;
	private int curPage;
	
	// 현재 카테고리 상태
	private int categoryState;

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
		pointColor = new Color(111, 188, 170);
	}

	private void setInitLayout() {
		setLayout(null);
		setBackground(panelColor);
		categoryState = -1;

		// [0] : 상품 사진 (mainPhoto)
		// [1] : 상품 이름 (name)
		// [2] : 상품 가격 (price)
		for (int i = 0; i < 6; i++) {
			productInfoList.get(i)[0].setSize(200, 200);
			productInfoList.get(i)[1].setSize(200, 20);
			productInfoList.get(i)[1].setFont(new Font("맑은 고딕", Font.BOLD, 18));
			productInfoList.get(i)[2].setSize(200, 20);
			productInfoList.get(i)[2].setFont(new Font("맑은 고딕", Font.BOLD, 15));

		}

		// 1번째 줄
		int x = 63;
		int y = 80;
		for (int i = 0; i < 3; i++) {
			productInfoList.get(i)[0].setLocation(x, y);
			productInfoList.get(i)[1].setLocation(x, y + 210);
			productInfoList.get(i)[2].setLocation(x, y + 235);
			x += 230;
		}

		x = productInfoList.get(0)[2].getX();
		y = productInfoList.get(0)[2].getY() + 50;

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
		
		buttonX = 20;
		String[] strArr = {"가나다순", "신상품순", "판매량순"};
		for (int i = 0; i < orderByButtons.length; i++) {
			orderByButtons[i] = new JButton(strArr[i]);
			orderByButtons[i].setSize(80, 20);
			orderByButtons[i].setLocation(buttonX, 20);
			orderByButtons[i].setBorder(null);
			orderByButtons[i].setBackground(panelColor);
			orderByButtons[i].setFont(new Font("맑은 고딕", Font.BOLD, 15));
			add(orderByButtons[i]);
			buttonX += 80;
		}

	}

	private void addEventListener() {
		pageDownButton.addActionListener(this);
		pageUpButton.addActionListener(this);
		for (int i = 0; i < orderByButtons.length; i++) {
			orderByButtons[i].addActionListener(this);
		}
		// 상품 사진에 마우스리스너
		for (int i = 0; i < productInfoList.size(); i++) {
			productInfoList.get(i)[0].addMouseListener(new MouseListener() {
				@Override
				public void mouseReleased(MouseEvent e) {
				}
				@Override
				public void mousePressed(MouseEvent e) {
					JLabel targetJLabel = (JLabel) e.getSource();
					
					String targetDtoPhoto = targetJLabel.getIcon().toString();
					ProductDTO targetDTO = productController.requestSelectMainPhoto(targetDtoPhoto);
					new ProductInfoFrame(targetDTO, mContext);
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
		selectedOrderByButton(-1);
		categoryState = 0;
		targetDtoList = productController.requestSelectAll();

		// 현재 페이지 1로 초기화
		clearPage();

		// 총 페이지 수
		pageCount = (int) Math.ceil(targetDtoList.size() / 6.0);
		System.out.println(pageCount + "개의 페이지가 생성됩니다.");

		// 초기 페이지
		initView(targetDtoList);
	}

	public void showClothes() {
		productInfoNull();
		selectedOrderByButton(-1);
		categoryState = 1;
		targetDtoList = productController.requestSelectCategory(1);

		// 현재 페이지 1로 초기화
		clearPage();

		// 페이지 수
		pageCount = (int) Math.ceil(targetDtoList.size() / 6.0);
		System.out.println(pageCount + "개의 페이지가 생성됩니다.");

		// 초기 페이지
		initView(targetDtoList);
	}

	public void showFood() {
		productInfoNull();
		selectedOrderByButton(-1);
		categoryState = 2;
		targetDtoList = productController.requestSelectCategory(2);

		// 현재 페이지 1로 초기화
		clearPage();

		// 페이지 수
		pageCount = (int) Math.ceil(targetDtoList.size() / 6.0);
		System.out.println(pageCount + "개의 페이지가 생성됩니다.");

		// 초기 페이지
		initView(targetDtoList);
	}

	public void showLiving() {
		productInfoNull();
		selectedOrderByButton(-1);
		categoryState = 3;
		targetDtoList = productController.requestSelectCategory(3);

		// 현재 페이지 1로 초기화
		clearPage();

		// 페이지 수
		pageCount = (int) Math.ceil(targetDtoList.size() / 6.0);
		System.out.println(pageCount + "개의 페이지가 생성됩니다.");

		// 초기 페이지
		initView(targetDtoList);
	}

	public void showToy() {
		productInfoNull();
		selectedOrderByButton(-1);
		categoryState = 4;
		targetDtoList = productController.requestSelectCategory(4);

		// 현재 페이지 1로 초기화
		clearPage();

		// 페이지 수
		pageCount = (int) Math.ceil(targetDtoList.size() / 6.0);
		System.out.println(pageCount + "개의 페이지가 생성됩니다.");

		// 초기 페이지
		initView(targetDtoList);
	}

	public void showEtc() {
		productInfoNull();
		selectedOrderByButton(-1);
		categoryState = 5;
		targetDtoList = productController.requestSelectCategory(5);

		// 현재 페이지 1로 초기화
		clearPage();

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
			if (curPage == 1 || pageCount == 0) {
				System.out.println("최소 페이지 번호입니다.");
				return;
			}
			productInfoNull();
			curPage--;
			currentPageLabel.setText(curPage + "");
			
			// 2자릿수이면
			if (curPage >= 10) {
				currentPageLabel.setLocation(387, 650);
			} else {
				currentPageLabel.setLocation(393, 650);
			}
			
			viewByCurrentPage();

			// 페이지 번호를 높이는 버튼
		} else if (targetButton == pageUpButton) {
			// 최대 페이지 번호가 되었다면 더 이상 높이지 않음
			if (curPage == pageCount || pageCount == 0) {
				System.out.println("최대 페이지 번호입니다.");
				return;
			}
			productInfoNull();
			curPage++;
			currentPageLabel.setText(curPage + "");
			
			// 2자릿수이면
			if (curPage >= 10) {
				currentPageLabel.setLocation(387, 650);
			} else {
				currentPageLabel.setLocation(393, 650);
			}
			
			viewByCurrentPage();
			
		// 가나다순 정렬 버튼
		} else if (targetButton == orderByButtons[0]) {
			productInfoNull();
			selectedOrderByButton(0);
			
			if (categoryState == 0) {
				targetDtoList = productController.requestSelectAllOrderBy(0);
			} else {
				targetDtoList = productController.requestSelectCategoryOrderBy(0, categoryState);
			}

			// 현재 페이지 1로 초기화
			clearPage();

			// 페이지 수
			pageCount = (int) Math.ceil(targetDtoList.size() / 6.0);
			System.out.println(pageCount + "개의 페이지가 생성됩니다.");

			// 초기 페이지
			initView(targetDtoList);
			
		// 신상품순 정렬 버튼
		} else if (targetButton == orderByButtons[1]) {
			productInfoNull();
			selectedOrderByButton(1);
			
			if (categoryState == 0) {
				targetDtoList = productController.requestSelectAllOrderBy(1);
			} else {
				targetDtoList = productController.requestSelectCategoryOrderBy(1, categoryState);
			}
			
			// 현재 페이지 1로 초기화
			clearPage();

			// 페이지 수
			pageCount = (int) Math.ceil(targetDtoList.size() / 6.0);
			System.out.println(pageCount + "개의 페이지가 생성됩니다.");

			// 초기 페이지
			initView(targetDtoList);
			
		// 판매량순 정렬 버튼
		} else if (targetButton == orderByButtons[2]) {
			productInfoNull();
			selectedOrderByButton(2);
			
			if (categoryState == 0) {
				targetDtoList = productController.requestSelectAllOrderBy(2);
			} else {
				targetDtoList = productController.requestSelectCategoryOrderBy(2, categoryState);
			}
			
			// 현재 페이지 1로 초기화
			clearPage();

			// 페이지 수
			pageCount = (int) Math.ceil(targetDtoList.size() / 6.0);
			System.out.println(pageCount + "개의 페이지가 생성됩니다.");

			// 초기 페이지
			initView(targetDtoList);
			
		}

	}
	
	public void selectedOrderByButton(int index) {
		for (int i = 0; i < orderByButtons.length; i++) {
			// 해당 버튼이 선택되었다면 포인트 색을 부여함
			if (i == index) {
				orderByButtons[i].setForeground(pointColor);
			} else {
				// 나머지 버튼은 검정색 글자로
				orderByButtons[i].setForeground(Color.black);
			}
		}
	}
	
	// 현재 페이지 1로 초기화
	public void clearPage() {
		curPage = 1;
		currentPageLabel.setText(curPage + "");
		currentPageLabel.setLocation(393, 650);
	}
	
}
