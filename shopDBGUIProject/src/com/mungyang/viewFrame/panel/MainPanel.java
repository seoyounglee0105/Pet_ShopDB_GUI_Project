package com.mungyang.viewFrame.panel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.mungyang.controller.ProductController;
import com.mungyang.dto.ProductDTO;
import com.mungyang.viewFrame.ShopFrame;

public class MainPanel extends JPanel implements ActionListener {

	private ShopFrame mContext;
	private ProductController productController;
	private JLabel nameLabel;
	private Color panelColor;
	
	private JTextField searchField;
	private JButton searchButton;

	public MainPanel(ShopFrame mContext) {
		this.mContext = mContext;
		initData();
		setInitLayout();
		addEventListener();
	}

	private void initData() {
		setSize(799, 730);
		productController = new ProductController();
		panelColor = new Color(230, 230, 230);
		nameLabel = new JLabel("메인");
		searchField = new JTextField("검색어를 입력해주세요.");
		searchButton = new JButton(new ImageIcon("images/search.png"));
	}

	private void setInitLayout() {
		setBackground(panelColor);
		setLayout(null);
		nameLabel.setSize(80, 20);
		nameLabel.setLocation(30, 20);
		nameLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		add(nameLabel);
		
		searchField.setSize(200, 30);
		searchField.setLocation(530, 20);
		searchField.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		searchField.setForeground(Color.gray);
		add(searchField);
		
		searchButton.setSize(30, 30);
		searchButton.setLocation(730, 20);
		searchButton.setBorder(null);
		searchButton.setBackground(panelColor);
		add(searchButton);
	}

	private void addEventListener() {
		searchButton.addActionListener(this);
		searchField.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
				// 클릭하면 검색어 힌트 제거
				searchField.setText("");
				searchField.setForeground(Color.black);
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
		searchField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				// 엔터를 누르면 검색 버튼 활성화
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					searchButton.doClick();
					searchField.transferFocus(); // 연속으로 클릭되는 걸 막기 위해 포커스 이동
				}
			}
		});
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton targetButton = (JButton) e.getSource();
		
		if (targetButton == searchButton) {
			// 방어적 코드 (입력되지 않으면 작동 X)
			if (searchField.getText().equals("")) {
				return;
			}
			
			String searchName = searchField.getText();
			// 없으면 size() == 0
			ArrayList<ProductDTO> searchList = productController.requestSearchProduct(searchName);
			
			// 검색되는 상품이 없다면
			if (searchList.size() == 0) {
				JOptionPane.showMessageDialog(null, "해당하는 상품이 존재하지 않습니다.", "", JOptionPane.PLAIN_MESSAGE);					
			// 검색되는 상품이 있다면
			} else {
				mContext.visiblePanel(1);
				mContext.getProductListPanel().searchProduct(searchList);
				mContext.getProductListPanel().getSearchLabel().setText("'" + searchName + "' 검색 결과입니다.");
			}
		}
	}

	public JTextField getSearchField() {
		return searchField;
	}

	public void setSearchField(JTextField searchField) {
		this.searchField = searchField;
	}
	
	

}
