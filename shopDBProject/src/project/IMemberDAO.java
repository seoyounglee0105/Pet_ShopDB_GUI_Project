package ex05;

public interface IMemberDAO {

	// 회원가입 기능 (INSERT)
	int memberSignUp(String id, String password, String name, 
										String phoneNumber, String address);
	
	// 아이디 중복 확인 기능 (SELECT)
	int memberIdCheck(String id);
	
	// 로그인 기능 (SELECT)
	 int memberLogin(String id, String password);
	
	// 비밀번호 찾기 기능 (SELECT)
	
	
	// 정보 수정 기능 (UPDATE)
	
	
	// 회원 탈퇴 기능 (DELETE)
	
	
}
