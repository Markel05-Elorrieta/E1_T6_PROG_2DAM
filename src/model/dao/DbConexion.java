package model.dao;

import java.io.FileInputStream;
import java.net.InetAddress;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;


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

			InetAddress address;
			try {
				address = InetAddress.getByName("firestore.googleapis.com");
				return address.isReachable(5000);
			} catch (Exception e) {
				return false;
			}
			
           
		
	}
}
