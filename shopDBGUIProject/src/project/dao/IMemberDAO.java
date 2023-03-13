package project.dao;

import project.dto.MemberDTO;

public interface IMemberDAO {

	// 회원 추가 (회원가입)
	int insert(MemberDTO dto);
	
	// 아이디와 비밀번호를 알 때 (로그인)
	MemberDTO selectByIdAndPassword(String id, String password);

	// 비밀번호를 모르고, 아이디와 전화번호를 알 때 (비밀번호 찾기)
	String selectByIdAndPhoneNumber(String id, String phoneNumber);
	
	// 아이디 중복 확인 기능 (SELECT)
	int selectById(String id);
	
	// 전화번호 중복 확인 기능 (SELECT)
	int selectByPhoneNumber(String phoneNumber);
	
	// 정보 수정 기능 (UPDATE)
	
	
	// 회원 탈퇴 기능 (DELETE)
	
	
}
