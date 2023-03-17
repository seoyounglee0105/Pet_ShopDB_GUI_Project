package project.viewFrame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import project.controller.MemberController;
import project.dao.MemberDAO;

public class PwFindFrame extends JFrame implements ActionListener, MouseListener {

	private MemberController memberController;
	
	private JLabel idLabel; // 아이디
	private JTextField idTextField; // 아이디 입력창
	private JLabel phoneLabel; // 전화번호
	private JTextField phoneTextField; // 전화번호 입력창
	private JButton pwFindButton; // 비밀번호 찾기 버튼
	private Boolean phoneHintOff;
	
	public PwFindFrame() {
		initData();
		setInitLayout();
		addEventListener();
	}

	private void initData() {
		setTitle("비밀번호 찾기");
		setSize(400, 200);
		
		memberController = new MemberController();
		phoneHintOff = false;

		idLabel = new JLabel("    아이디");
		idTextField = new JTextField("", 10);
		
		phoneLabel = new JLabel("전화번호");
		phoneTextField = new JTextField("010-####-####", 10);
		
		pwFindButton = new JButton("비밀번호 찾기");
	}

	private void setInitLayout() {
		setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null); // JFrame 가운데 배치
		Color backgroundColor = new Color(200, 235, 226);
		getContentPane().setBackground(backgroundColor);

		idLabel.setLocation(30, 50);
		idLabel.setSize(200, 10);
		add(idLabel);
		
		idTextField.setLocation(85, 46);
		idTextField.setSize(93, 20);
		add(idTextField);
		
		phoneLabel.setLocation(30, 100);
		phoneLabel.setSize(200, 10);
		add(phoneLabel);
		
		phoneTextField.setLocation(85, 96);
		phoneTextField.setSize(93, 20);
		add(phoneTextField);
		
		pwFindButton.setSize(115, 40);
		pwFindButton.setLocation(235, 60);
		add(pwFindButton);	
		
		setVisible(true);
	}

	private void addEventListener() {
		phoneTextField.addMouseListener(this);
		pwFindButton.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton targetButton = (JButton) e.getSource();
		String resultPw = null;
		
		// 비밀번호 찾기 버튼
		if (targetButton == pwFindButton) {
			
			String id = idTextField.getText();
			String phoneNumber = phoneTextField.getText();
			
			resultPw = memberController.requestFindPassword(id, phoneNumber);
			
			// 입력된 정보가 정확하다면
			if (resultPw != null) {
				JOptionPane.showMessageDialog(null, id + "님의 비밀번호는 " + resultPw + "입니다.", "비밀번호 찾기 성공", JOptionPane.PLAIN_MESSAGE);	
				this.dispose(); // 확인 누르면 프레임 꺼짐
			} else {
				JOptionPane.showMessageDialog(null, "정보를 잘못 입력하셨습니다.", "실패", JOptionPane.PLAIN_MESSAGE);					
			}
		}
	}


	// 형식 힌트 제거
	@Override
	public void mousePressed(MouseEvent e) {
		JTextField targetTextField = (JTextField) e.getSource();

		if (targetTextField == phoneTextField) {
			// 처음 눌렀을 때만 힌트가 없어지도록
			if (phoneHintOff == false) {
				targetTextField.setText("010-");
				phoneHintOff = true;
			}
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}
}
