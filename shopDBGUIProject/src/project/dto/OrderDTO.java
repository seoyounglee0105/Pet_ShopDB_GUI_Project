package project.dto;

public class OrderDTO {

	private int id;
	private String memberId;
	private int productId;
	private int amount;
	private String orderDate;
	private int state; // 0 : 상품 준비중, 1 : 배송중, 2 : 배송 완료
	
	private String productName;
	private int totalPrice;
	
	public OrderDTO(String memberId, int productId, int amount) {
		this.memberId = memberId;
		this.productId = productId;
		this.amount = amount;
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

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}	
	
	
}
