package model;

import java.util.List;

import javax.validation.constraints.Null;

public class ConsumerRecommendation {
	private Long consumerId;
	private List<ProductScores> recommendations;
	private Long organizationId;
	private String timestamp;
	private long weekDay;
	private String timeOfDay;

	public ConsumerRecommendation() {
		super();
	}

	public ConsumerRecommendation(Long consumerId, List<ProductScores> recommendations) {
		super();
		this.consumerId = consumerId;
		this.recommendations = recommendations;
	}

	public Long getConsumerId() {
		return consumerId;
	}

	public void setConsumerId(Long consumerId) {
		this.consumerId = consumerId;
	}

	public List<ProductScores> getRecommendations() {
		return recommendations;
	}

	public void setRecommendations(List<ProductScores> recommendations) {
		this.recommendations = recommendations;
	}

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public long getWeekDay() {
		return weekDay;
	}

	public void setWeekDay(long weekDay) {
		this.weekDay = weekDay;
	}

	public String getTimeOfDay() {
		return timeOfDay;
	}

	public void setTimeOfDay(String timeOfDay) {
		this.timeOfDay = timeOfDay;
	}
	
	
	
	

}
