package model;

public class ProductScores {
	private Long productId;
	private double rating;
	
	
	public ProductScores(Long productId, double rating) {
		super();
		this.productId = productId;
		this.rating = rating;
	}
	
	
	
	public ProductScores() {
	}


	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
		this.rating = rating;
	}
	
	

}
