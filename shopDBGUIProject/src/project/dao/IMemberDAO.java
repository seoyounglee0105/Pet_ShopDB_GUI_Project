package project.dao;

import java.util.ArrayList;

import project.dto.MemberDTO;

public interface IMemberDAO {

	// 회원 추가 (회원가입)
	int insert(MemberDTO dto);
	
	// 한 개의 조건을 이용한 SELECT
	ArrayList<MemberDTO> select(String column, String value);
	
	// 두 개의 조건을 이용한 SELECT (정확히 식별할 때만 사용할 것이므로, 단일 객체를 반환
	MemberDTO select(String firstColumn, String firstValue, String secondColumn, String secondValue);
	
	
	// 정보 수정 기능 (UPDATE)
//	int UPDATE(MemberDTO dto, String id);
	int update(int point, String id);
	
	// 회원 탈퇴 기능 (DELETE)
	
	
}
