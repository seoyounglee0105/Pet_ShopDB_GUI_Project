package com.mungyang.viewFrame.panel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.mungyang.viewFrame.ShopFrame;

public class MyPagePanel extends JPanel implements ActionListener {

	private ShopFrame mContext;
	private JLabel nameLabel;
	private JButton reviewWriteButton;
	private Color panelColor;

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
		reviewWriteButton = new JButton("리뷰 작성");
	}

	private void setInitLayout() {
		setBackground(panelColor);
		setLayout(null);
		nameLabel.setSize(80, 20);
		nameLabel.setLocation(30, 20);
		nameLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		add(nameLabel);
		
		reviewWriteButton.setBounds(200, 200, 200, 30);
		add(reviewWriteButton);
	}
	
	private void addEventListener() {
		reviewWriteButton.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton targetButton = (JButton) e.getSource();
		
		if (targetButton == reviewWriteButton) {
			// 리뷰 작성 패널로 이동
			mContext.visiblePanel(4);
			mContext.getReviewWritePanel().refresh();
		}
		
	}
}
