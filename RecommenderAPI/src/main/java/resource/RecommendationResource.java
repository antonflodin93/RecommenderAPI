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
	@Path("query")
	public ConsumerRecommendation test(@Context UriInfo uriInfo) {
		
		// Manage queries like http://localhost:8080/RecommenderAPI/recommendations/query?consumer=82974&&weekDay=6
		List<String> consumerList = uriInfo.getQueryParameters().get("consumer");
		List<String> timeOfDayList = uriInfo.getQueryParameters().get("timeOfDay");
		List<String> weekDayList = uriInfo.getQueryParameters().get("weekDay");
		List<String> organizationsList = uriInfo.getQueryParameters().get("organization");

		ConsumerRecommendation consumerRecommendation = null;
		if (uriInfo.getQueryParameters().get("timeOfDay") != null
				&& uriInfo.getQueryParameters().get("consumer") != null) {
			String timeOfDay = timeOfDayList.get(0);
			int consumerId = Integer.parseInt(consumerList.get(0));
			consumerRecommendation = recommendationService.getTimeOfDayRecommendation(timeOfDay, consumerId);

		} else if (uriInfo.getQueryParameters().get("weekDay") != null
				&& uriInfo.getQueryParameters().get("consumer") != null) {
			int weekDay = Integer.parseInt(weekDayList.get(0));
			int consumerId = Integer.parseInt(consumerList.get(0));
			consumerRecommendation = recommendationService.getWeekDayRecommendation(weekDay, consumerId);
			
		} else if (uriInfo.getQueryParameters().get("organization") != null
				&& uriInfo.getQueryParameters().get("consumer") != null) {
			int organizationId = Integer.parseInt(organizationsList.get(0));
			int consumerId = Integer.parseInt(consumerList.get(0));
			consumerRecommendation = recommendationService.getOrganizationRecommendation(organizationId, consumerId);
		} else if (uriInfo.getQueryParameters().get("consumer") != null) {
			int consumerId = Integer.parseInt(consumerList.get(0));
			consumerRecommendation = recommendationService.getGeneralRecommendation(consumerId);
		}
		
		return consumerRecommendation;

	}
}
