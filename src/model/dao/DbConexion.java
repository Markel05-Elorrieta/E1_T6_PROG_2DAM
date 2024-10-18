package model.dao;

import java.io.FileInputStream;


import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;

import com.google.cloud.firestore.QuerySnapshot;

public class DbConexion {
    public Firestore getConnection() throws Exception {
    	FileInputStream serviceAccount = new FileInputStream("src/resources/JEM_private_key.json");		
		FirestoreOptions firestoreOptions = FirestoreOptions.getDefaultInstance().toBuilder().setProjectId("jem-fit").setCredentials (GoogleCredentials.fromStream (serviceAccount)).build();
		Firestore db = firestoreOptions.getService();
		return db;
    }
    
	public void closeConnection(Firestore db) throws Exception {
		db.close();
	}
	
	
	public boolean testConnection() {
		Firestore db;
		try {
			db = getConnection();
	        ApiFuture<QuerySnapshot> query = db.collection("erabiltzaileak").get();
	        QuerySnapshot querySnapshot = query.get();
			closeConnection(db);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
