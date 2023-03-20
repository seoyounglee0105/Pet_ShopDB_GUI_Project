package com.mungyang.viewFrame.panel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.mungyang.controller.MemberController;
import com.mungyang.dto.MemberDTO;
import com.mungyang.viewFrame.ShopFrame;

public class MyPagePanel extends JPanel implements ActionListener {

	private ShopFrame mContext;
	private JLabel nameLabel;
	private Color panelColor;
	private MemberController memberController;

	private JPanel pointPanel;
	private JLabel[] infoNameLabels = new JLabel[2];
	private JLabel gradeLabel;
	private JLabel pointLabel;
	private JButton[] buttons = new JButton[3];

	public MyPagePanel(ShopFrame mContext) {
		this.mContext = mContext;
		initData();
		setInitLayout();
		addEventListener();
	}

	private void initData() {
		setSize(799, 730);
		memberController = new MemberController();
		panelColor = new Color(230, 230, 230);
		nameLabel = new JLabel("마이페이지");
		pointPanel = new JPanel();
		infoNameLabels[0] = new JLabel("회원 등급");
		infoNameLabels[1] = new JLabel("    적립금");
		gradeLabel = new JLabel();
		pointLabel = new JLabel();

		buttons[0] = new JButton(new ImageIcon("images/note.png")); // 회원 정보 수정
		buttons[1] = new JButton(new ImageIcon("images/order.png")); // 주문 조회
		buttons[2] = new JButton(new ImageIcon("images/review.png")); // 리뷰 작성
	}

	private void setInitLayout() {
		setBackground(panelColor);
		setLayout(null);
		nameLabel.setBounds(30, 20, 80, 20);
		nameLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		add(nameLabel);

		pointPanel.setBounds(575, 30, 180, 80);
		pointPanel.setBackground(Color.white);
		add(pointPanel);
		pointPanel.setLayout(null);
		
		int y1 = 5;
		for (int i = 0; i < infoNameLabels.length; i++) {
			infoNameLabels[i].setBounds(10, y1, 80, 30);
			infoNameLabels[i].setFont(new Font("맑은 고딕", Font.BOLD, 18));
			pointPanel.add(infoNameLabels[i]);
			y1 += 40;
		}
		
		JLabel[] list = {gradeLabel, pointLabel};
		
		int y2 = 5;
		for (JLabel jLabel : list) {
			jLabel.setBounds(100, y2, 100, 30);
			jLabel.setFont(new Font("맑은 고딕", Font.BOLD, 16));
			pointPanel.add(jLabel);
			y2 += 40;
		}

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
	
	public void refresh() {
		MemberDTO loginMemberDto = memberController.requestMemberInfo(mContext.getLoginId());
		gradeLabel.setText(loginMemberDto.getMemberGrade());
		pointLabel.setText(loginMemberDto.getPoint() + "원");
	}
}
