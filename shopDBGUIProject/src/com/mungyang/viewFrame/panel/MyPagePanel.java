package com.mungyang.viewFrame.panel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.mungyang.viewFrame.ShopFrame;

public class MyPagePanel extends JPanel implements ActionListener {

	private ShopFrame mContext;
	private JLabel nameLabel;
	private JButton reviewWriteButton;
	private Color panelColor;
	private JButton[] buttons = new JButton[3];

	public MyPagePanel(ShopFrame mContext) {
		this.mContext = mContext;
		initData();
		setInitLayout();
		addEventListener();
	}

	private void initData() {
		setSize(799, 730);
		panelColor = new Color(230, 230, 230);
		nameLabel = new JLabel("마이페이지");
		buttons[0] = new JButton(new ImageIcon("images/note.png")); // 회원 정보 수정
		buttons[1] = new JButton(new ImageIcon("images/order.png")); // 주문 조회
		buttons[2] = new JButton(new ImageIcon("images/review.png")); // 리뷰 작성
	}

	private void setInitLayout() {
		setBackground(panelColor);
		setLayout(null);
		nameLabel.setSize(80, 20);
		nameLabel.setLocation(30, 20);
		nameLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		add(nameLabel);
		
		int x = 60;
		int y = 300;
		for (int i = 0; i < buttons.length; i++) {
			buttons[i].setBounds(x, y, 140, 140);
			buttons[i].setBorder(null);
			buttons[i].setBackground(panelColor);
			add(buttons[i]);
			x += 250;
		}
		
	}
	
	private void addEventListener() {
		buttons[2].addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton targetButton = (JButton) e.getSource();
		
		if (targetButton == buttons[2]) {
			// 리뷰 작성 패널로 이동
			mContext.visiblePanel(3);
			mContext.getReviewWritePanel().refresh();
		}
		
	}
}
