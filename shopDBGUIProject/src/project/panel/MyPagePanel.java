package project.panel;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

import project.viewFrame.ShopFrame;

public class MyPagePanel extends JPanel {

	private ShopFrame mContext;
	private JLabel nameLabel;
	private Color panelColor;

	public MyPagePanel(ShopFrame mContext) {
		this.mContext = mContext;
		initData();
		setInitLayout();
	}

	private void initData() {
		setSize(799, 730);
		panelColor = new Color(230, 230, 230);
		nameLabel = new JLabel("마이페이지");
	}

	private void setInitLayout() {
		setBackground(panelColor);
		setLayout(null);
		nameLabel.setSize(80, 20);
		nameLabel.setLocation(30, 20);
		nameLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		add(nameLabel);
	}
	
}
