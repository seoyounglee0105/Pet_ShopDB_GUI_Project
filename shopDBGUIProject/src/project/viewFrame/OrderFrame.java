package project.viewFrame;

import javax.swing.JFrame;

public class OrderFrame extends JFrame {

	public OrderFrame() {
		initData();
		setInitLayout();
		addEventListener();
	}
	
	private void initData() {
		setTitle("주문");
		setSize(400, 300);
		

	}

	private void setInitLayout() {
		setLayout(null);
		setResizable(false);	
		setLocationRelativeTo(null);

		setVisible(true);
	}
	
	private void addEventListener() {
	
	}
	
}
