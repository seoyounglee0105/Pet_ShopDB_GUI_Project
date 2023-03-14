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
		int responseType = 0;
		// 성공 시 1 반환 (실패 시 0 or 2 or 3 or 4 반환)
		responseType = memberService.signUp(member);
		return responseType;
	}
	
	// id 값을 받아 중복된 id인지 체크
	public int requestCheckId(String id) {
		int responseType = 0;
		// 중복 시 1 반환 (중복되지 않았다면 0, 값 미입력 시 2 반환)
		responseType = memberService.checkId(id);
		return responseType;
	}
	
	// phoneNumber 값을 받아 중복된 phoneNumber인지 체크
	public int requestCheckPhoneNumber(String phoneNumber) {
		int responseType = 0;
		// 중복 시 1 반환 (중복되지 않았다면 0, 값 미입력 시 2 반환)
		responseType = memberService.checkPhoneNumber(phoneNumber);
		return responseType;
	}
	
	// id와 password 값을 받아 로그인 처리
	public MemberDTO requestLogin(String id, String password) {
		MemberDTO responseDto = null;
		// 정보가 정확하다면 DTO 객체 반환 (정확하지 않거나 값 미입력 시 null 반환)
		responseDto = memberService.loginMember(id, password);
		return responseDto;
	}
	
	// id와 phoneNumber 값을 받아 비밀번호 찾기
	public String requestFindPassword(String id, String phoneNumber) {
		String responseStr = null;
		// 정보가 정확하다면 password 반환 (정확하지 않거나 값 미입력 시 null 반환)
		responseStr = memberService.findPassword(id, phoneNumber);
		return responseStr;
	}
}
