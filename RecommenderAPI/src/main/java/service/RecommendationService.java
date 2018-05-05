package service;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

import java.util.ArrayList;
import java.util.List;

import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;

import model.ConsumerRecommendation;
import model.Organization;

public class RecommendationService {

	private static String DBName = "recommendationDB";
	private static String DBAddress = "localhost";
	private static String generalRecommendationCollectionName = "userGeneralRecommendations";
	private static String organizationRecommendationCollectionName = "userOrganizationRecommendations";
	private static String timeOfDayRecommendationCollectionName = "userTimeOfDayRecommendations";
	private static String weekDayRecommendationCollectionName = "userWeekDayRecommendations";

	public ConsumerRecommendation getGeneralRecommendation(int consumerId) {
		CodecRegistry pojoCodecRegistry = fromRegistries(MongoClient.getDefaultCodecRegistry(),
				fromProviders(PojoCodecProvider.builder().automatic(true).build()));

		MongoClient mongoClient = new MongoClient(DBAddress,
				MongoClientOptions.builder().codecRegistry(pojoCodecRegistry).build());

		MongoDatabase db = mongoClient.getDatabase(DBName);

		MongoCollection<ConsumerRecommendation> collection = db.getCollection(generalRecommendationCollectionName,
				ConsumerRecommendation.class);

		BasicDBObject query = new BasicDBObject();
		query.put("consumerId", consumerId);

		FindIterable<ConsumerRecommendation> cursor = collection.find(query).limit(1);

		ConsumerRecommendation consumerRecommendation = cursor.first();

		mongoClient.close();
		return consumerRecommendation;
	}

	public ConsumerRecommendation getOrganizationRecommendation(int organizationId, int consumerId) {
		CodecRegistry pojoCodecRegistry = fromRegistries(MongoClient.getDefaultCodecRegistry(),
				fromProviders(PojoCodecProvider.builder().automatic(true).build()));

		MongoClient mongoClient = new MongoClient(DBAddress,
				MongoClientOptions.builder().codecRegistry(pojoCodecRegistry).build());

		MongoDatabase db = mongoClient.getDatabase(DBName);

		MongoCollection<ConsumerRecommendation> collection = db.getCollection(organizationRecommendationCollectionName,
				ConsumerRecommendation.class);

		BasicDBObject query = new BasicDBObject();
		query.put("consumerId", consumerId);
		query.put("organizationId", organizationId);

		FindIterable<ConsumerRecommendation> cursor = collection.find(query).limit(1);

		ConsumerRecommendation consumerRecommendation = cursor.first();

		mongoClient.close();
		return consumerRecommendation;
	}

	public List<Long> getOrganizations(int organizationId) {
		CodecRegistry pojoCodecRegistry = fromRegistries(MongoClient.getDefaultCodecRegistry(),
				fromProviders(PojoCodecProvider.builder().automatic(true).build()));

		MongoClient mongoClient = new MongoClient(DBAddress,
				MongoClientOptions.builder().codecRegistry(pojoCodecRegistry).build());

		MongoDatabase db = mongoClient.getDatabase(DBName);

		MongoCollection<ConsumerRecommendation> collection = db.getCollection(organizationRecommendationCollectionName,
				ConsumerRecommendation.class);

		BasicDBObject query = new BasicDBObject();

		FindIterable<ConsumerRecommendation> cursor = collection.find(query);

		
		ArrayList<Long> organizations = new ArrayList<>();

		for(ConsumerRecommendation c : cursor) {
			if(!organizations.contains(c)) {
				organizations.add(c.getOrganizationId());
			}
		}
	
		mongoClient.close();
		return organizations;
	}

	public ConsumerRecommendation getWeekDayRecommendation(int weekDayNumber, int consumerId) {
		CodecRegistry pojoCodecRegistry = fromRegistries(MongoClient.getDefaultCodecRegistry(),
				fromProviders(PojoCodecProvider.builder().automatic(true).build()));

		MongoClient mongoClient = new MongoClient(DBAddress,
				MongoClientOptions.builder().codecRegistry(pojoCodecRegistry).build());

		MongoDatabase db = mongoClient.getDatabase(DBName);

		MongoCollection<ConsumerRecommendation> collection = db.getCollection(weekDayRecommendationCollectionName,
				ConsumerRecommendation.class);

		BasicDBObject query = new BasicDBObject();
		query.put("consumerId", consumerId);
		query.put("weekDay", weekDayNumber);

		System.out.println(query.toJson());

		FindIterable<ConsumerRecommendation> cursor = collection.find(query).limit(1);

		ConsumerRecommendation consumerRecommendation = cursor.first();

		mongoClient.close();
		return consumerRecommendation;
	}

	public ConsumerRecommendation getTimeOfDayRecommendation(String timeOfDay, int consumerId) {
		CodecRegistry pojoCodecRegistry = fromRegistries(MongoClient.getDefaultCodecRegistry(),
				fromProviders(PojoCodecProvider.builder().automatic(true).build()));

		MongoClient mongoClient = new MongoClient(DBAddress,
				MongoClientOptions.builder().codecRegistry(pojoCodecRegistry).build());

		MongoDatabase db = mongoClient.getDatabase(DBName);

		MongoCollection<ConsumerRecommendation> collection = db.getCollection(timeOfDayRecommendationCollectionName,
				ConsumerRecommendation.class);

		BasicDBObject query = new BasicDBObject();
		query.put("consumerId", consumerId);
		query.put("timeOfDay", timeOfDay);

		System.out.println(query.toJson());

		FindIterable<ConsumerRecommendation> cursor = collection.find(query).limit(1);

		ConsumerRecommendation consumerRecommendation = cursor.first();

		mongoClient.close();
		return consumerRecommendation;
	}

}
