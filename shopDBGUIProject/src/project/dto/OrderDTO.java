package project.dto;

public class OrderDTO {

	private int id;
	private String memberId;
	private int productId;
	private int amount;
	
	private String productName;
	private int totalPrice;

	public OrderDTO(int id, String memberId, int productId, int amount, String productName, int totalPrice) {
		super();
		this.id = id;
		this.memberId = memberId;
		this.productId = productId;
		this.amount = amount;
		this.productName = productName;
		this.totalPrice = totalPrice;
	}

	public OrderDTO() {
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}	
	
}
