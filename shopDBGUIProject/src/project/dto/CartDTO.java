package project.dto;

public class CartDTO {

	private String memberId;
	private int productId;
	private int amount;
	
	private String productName;
	private int totalPrice;
	
	
	public CartDTO(String memberId, int productId, int amount) {
		this.memberId = memberId;
		this.productId = productId;
		this.amount = amount;
	}
	
	public CartDTO() {
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

	@Override
	public String toString() {
		return "CartDTO [memberId=" + memberId + ", productId=" + productId + ", amount=" + amount + ", productName="
				+ productName + ", totalPrice=" + totalPrice + "]";
	}
	
	
	
}
