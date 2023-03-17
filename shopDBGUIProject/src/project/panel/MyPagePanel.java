package project.panel;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

import project.viewFrame.ShopFrame;

public class MyPagePanel extends JPanel {

	private ShopFrame mContext;
	private JLabel jLabel;
	private Color panelColor;

	public MyPagePanel(ShopFrame mContext) {
		this.mContext = mContext;
		initData();
		setInitLayout();
	}

	private void initData() {
		setSize(799, 730);
		panelColor = new Color(230, 230, 230);
	}

	private void setInitLayout() {
		setBackground(panelColor);
		jLabel = new JLabel("마이페이지 패널");
		add(jLabel);
	}
	
}
