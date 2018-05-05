package model;

public class Organization {
	private Long organizationId;
	
	public Organization() {}
	
	

	public Organization(Long organizationId) {
		super();
		this.organizationId = organizationId;
	}



	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}
	
	
}
