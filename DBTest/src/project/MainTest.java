package project;

import project.controller.MemberController;
import project.dto.MemberDTO;

public class MainTest {

	public static void main(String[] args) {
		
		MemberController memberController = new MemberController();
		
		MemberDTO resultDTO = memberController.requestLogin("ab", "1234");

		// toString을 재정의해두어서 주소 값 대신 멤버변수 값이 출력됨
		System.out.println(resultDTO);
		
	}

}
