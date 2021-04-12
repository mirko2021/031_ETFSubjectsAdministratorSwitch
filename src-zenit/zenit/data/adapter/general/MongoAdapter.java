package zenit.data.adapter.general;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

/**
 * 
 * @author VM
 * @version 1.0
 */
public interface MongoAdapter<T> {
	public MongoClient getDriver();
	public MongoDatabase getDatabase();
	public MongoCollection<T> getTable();
}
