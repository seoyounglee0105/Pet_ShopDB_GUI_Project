package ex05;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class SignUpFrame extends JFrame implements ActionListener, MouseListener {

	private JPanel parentPanel1;

	private JPanel panel1;
	private JLabel idLabel; // 아이디
	private JTextField idTextField; // 아이디 입력창
	private JButton idCheckButton; // 중복확인 버튼

	private JPanel panel2;
	private JLabel pwLabel; // 비밀번호
	private JPasswordField pwPasswordField; // 비밀번호 입력창

	private JPanel panel3;
	private JLabel nameLabel; // 이름
	private JTextField nameTextField; // 이름 입력창

	private JPanel panel4;
	private JLabel phoneLabel; // 전화번호
	private JTextField phoneTextField; // 전화번호 입력창

	private JPanel panel5;
	private JLabel addrLabel; // 주소
	private JTextField addrTextField; // 주소 입력창

	private JPanel parentPanel2;

	private JPanel panel6;
	private JButton signUpButton; // 회원가입 버튼
	private Color backgroundColor;

	private JTextField[] textFields = new JTextField[4];
	private int phoneNumberHintCount = 0;

	public SignUpFrame() {
		initData();
		setInitLayout();
		addEventListener();
	}

	private void initData() {
		setTitle("회원가입");
		setSize(400, 350);
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		parentPanel1 = new JPanel();
		panel1 = new JPanel();
		idLabel = new JLabel("     아이디");
		idTextField = new JTextField("", 10);
		idCheckButton = new JButton("중복 확인");

		panel2 = new JPanel();
		pwLabel = new JLabel("비밀번호");
		pwPasswordField = new JPasswordField(10);

		panel3 = new JPanel();
		nameLabel = new JLabel("        이름");
		nameTextField = new JTextField("", 10);

		panel4 = new JPanel();
		phoneLabel = new JLabel("전화번호");
		phoneTextField = new JTextField("010-0000-0000", 10);

		panel5 = new JPanel();
		addrLabel = new JLabel("        주소");
		addrTextField = new JTextField("", 20);

		parentPanel2 = new JPanel();
		panel6 = new JPanel();
		signUpButton = new JButton("회원가입");

		textFields[0] = idTextField;
		textFields[1] = nameTextField;
		textFields[2] = phoneTextField;
		textFields[3] = addrTextField;

	}

	private void setInitLayout() {
		add(parentPanel1, BorderLayout.CENTER);
		parentPanel1.setBackground(backgroundColor);
		add(parentPanel2, BorderLayout.SOUTH);
		parentPanel2.setBackground(backgroundColor);

		parentPanel1.setLayout(new FlowLayout(FlowLayout.LEFT, 40, 18));
		setResizable(false);
		// 뜨는 위치 지정하는 법 알아보기
		setLocationRelativeTo(null); // JFrame 가운데 배치
		Color backgroundColor = new Color(200, 235, 226);
		getContentPane().setBackground(backgroundColor);

		parentPanel1.add(panel1);
		panel1.add(idLabel, FlowLayout.LEFT);
		panel1.add(idTextField);
		panel1.add(idCheckButton);
		panel1.setBackground(backgroundColor);

		parentPanel1.add(panel2);
		panel2.add(pwLabel);
		panel2.add(pwPasswordField);
		panel2.setBackground(backgroundColor);

		parentPanel1.add(panel3);
		panel3.add(nameLabel);
		panel3.add(nameTextField);
		panel3.setBackground(backgroundColor);

		parentPanel1.add(panel4);
		panel4.add(phoneLabel);
		panel4.add(phoneTextField);
		panel4.setBackground(backgroundColor);

		parentPanel1.add(panel5);
		panel5.add(addrLabel);
		panel5.add(addrTextField);
		panel5.setBackground(backgroundColor);

		parentPanel2.add(panel6);
		panel6.add(signUpButton);
		panel6.setBackground(backgroundColor);

		setVisible(true);
	}

	private void addEventListener() {
		signUpButton.addActionListener(this);
		idCheckButton.addActionListener(this);
		phoneTextField.addMouseListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton targetButton = (JButton) e.getSource();
		int result = 0;

		// 아이디 중복확인 버튼
		if (targetButton == idCheckButton) {
			// 방어적 코드 (입력되지 않은 값이 있다면 실행 X)
			if (idTextField.getText().isEmpty()) {
				System.out.println("값이 입력되지 않았습니다.");
				return;
			}
			String id = textFields[0].getText();
			
			MemberDAO memberDAO = new MemberDAO();
			result = memberDAO.memberIdCheck(id);
			
			// 아이디가 중복되었다면
			if (result == 1) {
				JOptionPane.showMessageDialog(null, "이미 존재하는 아이디입니다.", "", JOptionPane.PLAIN_MESSAGE);	
			} else {				
				JOptionPane.showMessageDialog(null, "사용 가능한 아이디입니다.", "", JOptionPane.PLAIN_MESSAGE);	
			}

		// 회원가입 버튼
		} else if (targetButton == signUpButton) {
			// 방어적 코드 (입력되지 않은 값이 있다면 실행 X)
			for (int i = 0; i < textFields.length; i++) {
				// 원래 했던 방식은 textFields[i].getText() == null이랑 ""이었는데
				// 인식이 안돼서 isEmpty()로 변경했더니 정상 작동
				if (textFields[i].getText().isEmpty()) {
					System.out.println("모든 값을 입력해주세요.");
					return;
				}
			}
			// 패스워드필드도 String으로 변경해서 isEmpty()로 써서 정상 작동
			if (new String(pwPasswordField.getPassword()).isEmpty()) {
				System.out.println("모든 값을 입력해주세요.");
				return;
			}

			String id = textFields[0].getText();
			char[] pwChar = pwPasswordField.getPassword();
			String pw = new String(pwChar);
			String name = textFields[1].getText();
			String phoneNumber = textFields[2].getText();
			String address = textFields[3].getText();

			MemberDAO memberDAO = new MemberDAO();
			result = memberDAO.memberSignUp(id, pw, name, phoneNumber, address);

			if (result == 1) {
				System.out.println("회원가입에 성공했습니다!");
				// 확인을 누르면 회원가입 프레임 종료
				JOptionPane.showMessageDialog(null, "회원가입에 성공했습니다!", "회원가입 성공", JOptionPane.PLAIN_MESSAGE);
				this.dispose();
			} else {
				JOptionPane.showMessageDialog(null, "중복되거나 입력되지 않은 값이 있는지 확인해주세요.", "회원가입 실패", JOptionPane.PLAIN_MESSAGE);				
			}
		}
	} // end of method

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		JTextField targetTextField = (JTextField) e.getSource();

		if (targetTextField == phoneTextField) {
			phoneNumberHintCount++;

			// 처음 눌렀을 때만 힌트가 없어지도록
			if (phoneNumberHintCount == 1) {
				targetTextField.setText("");
			}
		}
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

	public static void main(String[] args) {
		new SignUpFrame();
	}

}
