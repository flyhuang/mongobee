package com.github.mongobee.utils;

import com.mongodb.MongoCommandException;
import com.mongodb.client.MongoDatabase;

public class DBUtils {

  public static void createCollectionIfNotExists(MongoDatabase db, String collectionName) {
    boolean exists = false;
    for (String collection : db.listCollectionNames()) {
      if (collection.equalsIgnoreCase(collectionName)) {
        exists = true;
        break;
      }
    }
    if (!exists && !db.getClass().getSimpleName().equals("FongoMongoDatabase")) {
      try {
        db.createCollection(collectionName);
      } catch (MongoCommandException e) {
        if (e.getCode() != 48) {
          throw e;
        }
      }
    }
  }
}
