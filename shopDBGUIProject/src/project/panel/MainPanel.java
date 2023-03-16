package project.panel;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

import project.controller.MemberController;
import project.dto.MemberDTO;
import project.frame.ShopFrame;

public class MainPanel extends JPanel {
	
	private ShopFrame mContext;
	private JLabel jLabel;
	private Color panelColor;
	private boolean state;
	
	public MainPanel(ShopFrame mContext) {
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
		jLabel = new JLabel("메인패널");
		add(jLabel);
	}

	public boolean isState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}
	
	

}
