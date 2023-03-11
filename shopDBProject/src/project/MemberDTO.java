package ex05;

public class MemberDTO {

	private String id;
	private String password;
	private String member_grade;
	private String name;
	private String phone_number;
	private String address;

	public MemberDTO(String id, String password, String member_grade, String name, String phone_number, String address,
			String email) {
		this.id = id;
		this.password = password;
		this.member_grade = member_grade;
		this.name = name;
		this.phone_number = phone_number;
		this.address = address;
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

	public String getMember_grade() {
		return member_grade;
	}

	public void setMember_grade(String member_grade) {
		this.member_grade = member_grade;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	
	
	
	
	
}
