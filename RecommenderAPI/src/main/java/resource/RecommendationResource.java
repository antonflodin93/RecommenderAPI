package resource;

import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import model.Organization;
import model.ConsumerRecommendation;

import service.RecommendationService;

@Path("/")
public class RecommendationResource {
	private RecommendationService recommendationService = new RecommendationService();

	// Get regular recommendation
	@GET
	@Path("consumer/{consumerId}")
	@Produces(MediaType.APPLICATION_JSON)
	public ConsumerRecommendation getGeneralRecommendation(@PathParam("consumerId") int consumerId) {

		return (recommendationService.getGeneralRecommendation(consumerId));
	}
	

	// Get recommendation based on organization
	@GET
	@Path("organization/{organizationId}/consumer/{consumerId}")
	@Produces(MediaType.APPLICATION_JSON)
	public ConsumerRecommendation getOrganizationRecommendation(@PathParam("organizationId") int organizationId,
			@PathParam("consumerId") int consumerId) {

		return (recommendationService.getOrganizationRecommendation(organizationId, consumerId));
	}
	
	// Get list of organization
	@GET
	@Path("organizations")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getOrganizations(@PathParam("organizationId") int organizationId) {

		List<Long> organizationIds = recommendationService.getOrganizations(organizationId);
		GenericEntity<List<Long>> entity = new GenericEntity<List<Long>>(organizationIds) {};
		
		return Response.ok(entity).build();
	}

	// Get recommendation based on week day
	@GET
	@Path("weekDay/{weekDayNumber}/consumer/{consumerId}")
	@Produces(MediaType.APPLICATION_JSON)
	public ConsumerRecommendation getWeekDayRecommendation(@PathParam("weekDayNumber") int weekDayNumber,
			@PathParam("consumerId") int consumerId) {

		return (recommendationService.getWeekDayRecommendation(weekDayNumber, consumerId));
	}

	// Get recommendation based on time of day
	@GET
	@Path("timeOfDay/{timeOfDay}/consumer/{consumerId}")
	@Produces(MediaType.APPLICATION_JSON)
	public ConsumerRecommendation getTimeOfDayRecommendation(@PathParam("timeOfDay") String timeOfDay,
			@PathParam("consumerId") int consumerId) {

		return (recommendationService.getTimeOfDayRecommendation(timeOfDay, consumerId));
	}

	@GET
	@Path("/query")
	public String test(@Context UriInfo uriInfo, @PathParam("organizationId") int organizationId) {

		// Check which kind of recommendation requested

		List<String> timeOfDayList = uriInfo.getQueryParameters().get("timeOfDay");
		// System.out.println("timeOfDayList: " + timeOfDayList);

		if (uriInfo.getQueryParameters().get("timeOfDay") != null) {
			System.out.println("Conaints timeofday");
		} else {
			System.out.println("Dont contains!");
		}

		System.out.println("organizationId: " + organizationId);
		System.out.println(uriInfo.getQueryParameters());
		System.out.println(uriInfo.getQueryParameters().size());
		return "hej";
	}

}
