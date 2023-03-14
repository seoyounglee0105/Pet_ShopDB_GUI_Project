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
	private Color grayColor;
	
	public MainPanel(ShopFrame mContext) {
		this.mContext = mContext;
		initData();
		setInitLayout();
		System.out.println("Ddd");
		repaint();
	}
	
	private void initData() {
		setSize(1000, 730);
		grayColor = new Color(225, 225, 225);
		
	}
	
	private void setInitLayout() {
		setBackground(grayColor);
		jLabel = null;
		jLabel = new JLabel("xpxpxpxpx");
		add(jLabel);
		repaint();
	}

}
