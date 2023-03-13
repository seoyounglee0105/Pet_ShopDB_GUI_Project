package project.frame;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import project.controller.MemberController;
import project.dao.MemberDAO;
import project.dto.MemberDTO;

public class LoginFrame extends JFrame implements ActionListener {
	
	private MemberController memberController;

	private JPanel panel1;
	private JLabel idLabel; // 아이디
	private JTextField idTextField; // 아이디 입력창
	private JLabel pwLabel; // 비밀번호
	private JPasswordField pwField; // 비밀번호 입력창
	private JButton loginButton; // 로그인 버튼
	
	private JPanel panel2;
	private JButton signUpButton; // 회원가입 버튼
	private JButton pwFindButton; // 비밀번호 찾기 버튼
	
	public LoginFrame() {
		initData();
		setInitLayout();
		addEventListener();
	}
	
	private void initData() {
		setTitle("로그인");
		setSize(400, 300);
		
		memberController = new MemberController();
		
		panel1 = new JPanel();
		idLabel = new JLabel("    아이디");
		idTextField = new JTextField(10);
		pwLabel = new JLabel("비밀번호");
		pwField = new JPasswordField(10);
		loginButton = new JButton("로그인");
		
		panel2 = new JPanel();
		signUpButton = new JButton("회원 가입");
		pwFindButton = new JButton("비밀번호 찾기");
	}

	private void setInitLayout() {
		setLayout(null);
		setResizable(false);	
		setLocationRelativeTo(null);
		Color backgroundColor = new Color(200, 235, 226);
		getContentPane().setBackground(backgroundColor);
		
		// panel1
		panel1.setSize(200, 55);
		panel1.setLocation(40, 70);
		panel1.setBackground(backgroundColor);
		add(panel1);
		
		panel1.add(idLabel);
		panel1.add(idTextField);
		panel1.add(pwLabel);
		panel1.add(pwField);

		loginButton.setSize(80, 40);
		loginButton.setLocation(240, 77);
		add(loginButton);	
		
		// panel2
		panel2.setSize(400, 100);
		panel2.setLocation(0, 180);
		panel2.setBackground(backgroundColor);
		add(panel2);
		
		panel2.add(signUpButton);
		panel2.add(pwFindButton);
		
		setVisible(true);
	}
	
	private void addEventListener() {
		signUpButton.addActionListener(this);
		loginButton.addActionListener(this);
		pwFindButton.addActionListener(this);
	}
		
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton targetButton = (JButton) e.getSource();
		
		// 회원 가입 버튼
		if (targetButton == signUpButton) {
			new SignUpFrame();
		
		// 비밀번호 찾기 버튼
		} else if (targetButton == pwFindButton) {
			new PwFindFrame();
			
		// 로그인 버튼
		} else if (targetButton == loginButton) {
			String id = idTextField.getText();
			String pw = new String(pwField.getPassword());
			
			MemberDTO loginMemberDTO = memberController.requestLogin(id, pw);
			
			// null이면 로그인에 실패한 것
			if (loginMemberDTO == null) {
				JOptionPane.showMessageDialog(null, "아이디나 비밀번호를 확인해주세요.", "로그인 실패", JOptionPane.PLAIN_MESSAGE);								
			// 로그인 성공
			} else {
				// 확인을 누르면 로그인 프레임 닫기 + 쇼핑몰 홈페이지 프레임
				JOptionPane.showMessageDialog(null, id + "님, 환영합니다!", "로그인 성공", JOptionPane.PLAIN_MESSAGE);
				this.dispose();
			}			
		}
	}

	public static void main(String[] args) {
		new LoginFrame();
	}

	
}
