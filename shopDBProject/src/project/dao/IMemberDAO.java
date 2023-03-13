package project.dao;

import project.dto.MemberDTO;

public interface IMemberDAO {

	// 회원가입 기능 (INSERT)
	int memberSignUp(MemberDTO dto);
	
	// 아이디 중복 확인 기능 (SELECT)
	boolean memberIdCheck(String id);
	
	// 전화번호 중복 확인 기능 (SELECT)
	boolean memberPhoneCheck(String phoneNumber);
	
	// 로그인 기능 (SELECT)
	MemberDTO memberLogin(String id, String password);
	
	// 비밀번호 찾기 기능 (SELECT)
	String memberPwFind(String id, String phoneNumber);
	
	// 정보 수정 기능 (UPDATE)
	
	
	// 회원 탈퇴 기능 (DELETE)
	
	
}
