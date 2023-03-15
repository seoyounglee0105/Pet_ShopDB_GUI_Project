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
	
	public MainPanel(ShopFrame mContext) {
		this.mContext = mContext;
		initData();
		setInitLayout();
	}
	
	private void initData() {
		setSize(799, 730);
		
	}
	
	private void setInitLayout() {
		setBackground(mContext.getPanelColor());
		jLabel = null;
		jLabel = new JLabel("메인패널");
		add(jLabel);
	}

}
