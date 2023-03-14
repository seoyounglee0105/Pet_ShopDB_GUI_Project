package project.controller;

import project.dto.MemberDTO;
import project.service.MemberService;

public class MemberController {

	private MemberService memberService;
	
	public MemberController() {
		memberService = new MemberService();
	}
	
	// id와 password 값을 받아 로그인 처리
	public MemberDTO requestLogin(String id, String password) {
		MemberDTO responseDto = null;
		// 정보가 정확하다면 DTO 객체 반환 (정확하지 않거나 값 미입력 시 null 반환)
		responseDto = memberService.loginMember(id, password);
		return responseDto;
	}	
}

