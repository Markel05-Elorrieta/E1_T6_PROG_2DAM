package model.dao;

import java.util.List;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;

import model.User;
import resources.GlobalVariables;

public class UserDAO {
	
	private DbConexion dbConexion = new DbConexion();
	
	public boolean checkLogin(String username, String password) throws Exception {
		// Get the Firestore instance
		Firestore db = dbConexion.getConnection();
		
		// Get the user with the given username
        ApiFuture<QuerySnapshot> query = db.collection("usuarios").whereEqualTo("erabiltzailea", username).get();
        QuerySnapshot querySnapshot = query.get();
        List<QueryDocumentSnapshot> userDoc = querySnapshot.getDocuments();
        
		if (userDoc.isEmpty()) {
			// If there is no user with that username
			return false;
		}
		if (!userDoc.get(0).getString("pasahitza").equals(password)) {
			// If the password is incorrect
			return false;
		}
		// If the user exists and the password is correct
		
		// Create the User object with the data from the Firestore
		GlobalVariables.loggedUser = new User(userDoc.get(0).getString("erabiltzailea"), userDoc.get(0).getString("izena"), userDoc.get(0).getString("abizenak"), userDoc.get(0).getString("pasahitza"), userDoc.get(0).getDate("jaiotze_data"), userDoc.get(0).getString("email"), userDoc.get(0).getDouble("telefonoa").intValue());
        return true;

	}
}
