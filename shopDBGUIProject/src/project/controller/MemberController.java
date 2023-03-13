package project.controller;

import project.dto.MemberDTO;
import project.service.MemberService;

public class MemberController {

	private MemberService memberService;
	
	public MemberController() {
		memberService = new MemberService();
	}
	
	// 사용자 정보를 받아 회원가입 처리
	public int requestSignUp(MemberDTO member) {
		int response = 0;
		// 성공 시 1 반환 (실패 시 0 or 2 or 3 or 4 반환)
		response = memberService.signUp(member);
		return response;
	}
	
	// id 값을 받아 중복된 id인지 체크
	public int requestCheckId(String id) {
		int response = 0;
		// 중복 시 1 반환 (중복되지 않았다면 0, 값 미입력 시 2 반환)
		response = memberService.checkId(id);
		return response;
	}
}
