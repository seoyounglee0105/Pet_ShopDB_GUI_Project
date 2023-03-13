package project.dto;

public class MemberDTO {

	private String id;
	private String password;
	private String memberGrade;
	private String name;
	private String phoneNumber;
	private String address;

	public MemberDTO(String id, String password, String name, String phone_number, String address) {
		this.id = id;
		this.password = password;
		this.name = name;
		this.phoneNumber = phone_number;
		this.address = address;
	}
	
	// 생성자 오버로딩
	public MemberDTO() {
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMemberGrade() {
		return memberGrade;
	}

	public void setMemberGrade(String memberGrade) {
		this.memberGrade = memberGrade;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	
	
	
	
	
}
