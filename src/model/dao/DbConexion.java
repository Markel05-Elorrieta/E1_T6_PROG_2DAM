package model.dao;

import java.io.FileInputStream;

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
}
