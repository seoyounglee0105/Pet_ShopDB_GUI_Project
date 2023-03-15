package project.service;

import project.dao.MemberDAO;
import project.dto.MemberDTO;

public class MemberService {

	private MemberDAO memberDAO;
	// 오타 방지를 위해 String 배열로 선언해서 사용
	private String[] memberColumns = {"id", "password", "member_grade", 
									  "name", "phone_number", "address"};
	
	public MemberService() {
		memberDAO = new MemberDAO();
	}

	// 회원 가입 로직 처리
	// result 값에 따라 다른 결과가 나타나도록 함
	public int signUp(MemberDTO member) {
		int result = 0;

		// 입력 값이 유효한지 확인하기
		// 입력되지 않은 값이 있다면 실행 X (result == 2)
		if (member.getId().equals("") || member.getPassword().equals("") || member.getName().equals("")
				|| member.getPhoneNumber().equals("") || member.getAddress().equals("")) {
			result = 2;
			return result;

			// 전화번호 형식에 맞지 않다면 실행 X (result == 3)
			// '-'가 인덱스 3번, 8번에 나와야 하고, 총 길이가 13이어야 함
		} else if (member.getPhoneNumber().indexOf("-") != 3 || member.getPhoneNumber().lastIndexOf("-") != 8
				|| member.getPhoneNumber().length() != 13) {
			result = 3;
			return result;

			// id나 전화번호가 중복된 경우 실행 X (result == 4)
		} else if ( memberDAO.select(memberColumns[0], member.getId()).size() == 1
						|| memberDAO.select(memberColumns[4], member.getPhoneNumber()).size() == 1 ) {
			result = 4;
			return result;
		}

		// 사용자 권한 여기서 지정

		// 성공했다면 result == 1 (아니면 0)
		result = memberDAO.insert(member);
		return result;
	} // end of signUp

	// 아이디 중복 확인 로직 처리
	public int checkId(String id) {
		int result = 0;

		// id가 입력되지 않았다면 실행 X (result == 2)
		if (id.equals("")) {
			result = 2;
			return result;
		}
		// 중복이라면 result == 1 (아니면 0)
		result = memberDAO.select(memberColumns[0], id).size(); 
		return result;
	} // end of checkId

	// 전화번호 중복 확인 로직 처리
	public int checkPhoneNumber(String phoneNumber) {
		int result = 0;

		// phonNumber가 입력되지 않았다면 실행 X (result == 3)
		if (phoneNumber.equals("")) {
			result = 3;
			return result;
			
		// 전화번호 형식에 맞지 않다면 실행 X (result == 2)
		// '-'가 인덱스 3번, 8번에 나와야 하고, 총 길이가 13이어야 함
		} else if (phoneNumber.indexOf("-") != 3 || phoneNumber.lastIndexOf("-") != 8
				|| phoneNumber.length() != 13) {
			result = 2;
			return result;
		}

		// 중복이라면 result == 1 (아니면 0)
		result = memberDAO.select(memberColumns[4], phoneNumber).size();
		System.out.println(result);
		return result;
	} // end of checkPhoneNumber
	
	// 로그인 로직 처리
	public MemberDTO loginMember(String id, String password) {
		MemberDTO resultMemberDTO = null;
		
		// 입력되지 않은 값이 있다면 실행 X
		if (id.equals("") || password.equals("")) {
			return resultMemberDTO;
		}
		
		// 입력한 정보가 정확하다면 객체가 생성됨 (아니면 null)
		resultMemberDTO = memberDAO.select(memberColumns[0], id, memberColumns[1], password);
		
		// 로그인 시에 password 정보를 반환하지 않도록 함 (민감한 정보)
		if (resultMemberDTO != null) {
			resultMemberDTO.setPassword(null);			
		}
		
		return resultMemberDTO;
	} // end of loginMember
	
	// 비밀번호 찾기 로직 처리
	public String findPassword(String id, String phoneNumber) {
		String resultPw = null;
		
		// 입력되지 않은 값이 있다면 실행 X
		if (id.equals("") || phoneNumber.equals("")) {
			return resultPw;
		}
		
		// 입력한 정보가 정확하다면 객체가 생성됨 (아니면 null)
		MemberDTO targetDto = memberDAO.select(memberColumns[0], id, 
																							memberColumns[4], phoneNumber);
		
		// 방어적 코드 (null이 아닐 때만)
		if (targetDto != null) {
			resultPw = targetDto.getPassword();
		}
		return resultPw;
	} // end of findPassword

}
