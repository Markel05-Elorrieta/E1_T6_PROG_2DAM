package model.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;

import model.BcryptMethods;
import model.User;
import model.exceptions.*;
import model.metodoak.Backup;
import model.offline.UserOffline;
import resources.GlobalVariables;


public class UserDAO {
	
	private DbConexion dbConexion = new DbConexion();
	private BcryptMethods bCrypt = new BcryptMethods();
	UserOffline userOff = null;
	
	
	public boolean checkLogin(String username, String password) throws Exception {
		if (!GlobalVariables.isConnexion) {
			if (userOff == null) {
				userOff = new UserOffline();
			}
			return userOff.checkLogin(username, password);
		}
		// Get the Firestore instance
		Firestore db = dbConexion.getConnection();
		try {

			
			// Get the user with the given username
	        ApiFuture<QuerySnapshot> query = db.collection("erabiltzaileak").whereEqualTo("erabiltzailea", username).get();
	        QuerySnapshot querySnapshot = query.get();
	        List<QueryDocumentSnapshot> userDoc = querySnapshot.getDocuments();
	        
			if (userDoc.isEmpty()) {
				// If there is no user with that username
				return false;
			}
			String hashedPwd = userDoc.get(0).getString("pasahitza");
			if (!bCrypt.checkPassword(password, hashedPwd)) {
				// If the password is incorrect
				return false;
			}
			// If the user exists and the password is correct
			
			// Create the User object with the data from the Firestore
			GlobalVariables.loggedUser = new User(userDoc.get(0).getString("erabiltzailea"), userDoc.get(0).getString("izena"), userDoc.get(0).getString("abizenak"), hashedPwd, userDoc.get(0).getDate("jaiotze_data"), userDoc.get(0).getString("email"), userDoc.get(0).getDouble("telefonoa").intValue(), userDoc.get(0).getDouble("maila").intValue());
			// Close the connection
			dbConexion.closeConnection(db);
			return true;
		}catch (Exception e) {
			dbConexion.closeConnection(db);
			throw new LostDbConnection();
		}
       
	}
	
	public boolean registerUser(User newUser) throws Exception {
		if (!GlobalVariables.isConnexion) {
			if (userOff == null) {
				userOff = new UserOffline();
			}
			return userOff.registerUser(newUser);
		}
		// Get the Firestore instance
		Firestore db = dbConexion.getConnection();
		
		try {
			// Get the collection of users
			CollectionReference users = db.collection("erabiltzaileak");
			// Create the map with the data of the
			try {
				this.checkUsername(newUser.getUsername());
			} catch (UsernameException e) {
				throw e;
			}
			
			Map<String, Object> user = new HashMap<>();
			// Add the data to the map
			user.put("erabiltzailea", newUser.getUsername());
			user.put("izena", newUser.getName());
			user.put("abizenak", newUser.getSubname());
			user.put("pasahitza", newUser.getPassword());
			user.put("jaiotze_data", newUser.getBirthdate());
			user.put("email", newUser.getEmail());
			user.put("telefonoa", newUser.getPhone());
			user.put("maila", newUser.getMaila());
			// Add the user to the collection
			DocumentReference newUserDR = users.document();
			newUserDR.set(user);
			// Close the connection
			dbConexion.closeConnection(db);
			return true;
		} catch (Exception e) {
			dbConexion.closeConnection(db);
			throw new LostDbConnection();
		}
	}
	
	private String checkUsername(String username) throws UsernameException, Exception {
		// Get the Firestore instance
		Firestore db = dbConexion.getConnection();
		
		// Get the user with the given username
		ApiFuture<QuerySnapshot> query = db.collection("erabiltzaileak").whereEqualTo("erabiltzailea", username).get();
		QuerySnapshot querySnapshot = query.get();
		List<QueryDocumentSnapshot> userDoc = querySnapshot.getDocuments();

		if (userDoc.isEmpty()) {
			// If there is no user with that username
			dbConexion.closeConnection(db);
			return username;
		}
		// Close the connection
		dbConexion.closeConnection(db);
		throw new UsernameException();
	}
	
	public ArrayList<User> getUsers() throws Exception {
		// Get the Firestore instance
		Firestore db = dbConexion.getConnection();

		// Get the collection of users
		CollectionReference users = db.collection("erabiltzaileak");
		// Get all the users
		ApiFuture<QuerySnapshot> query = users.get();
		QuerySnapshot querySnapshot = query.get();
		List<QueryDocumentSnapshot> userDocs = querySnapshot.getDocuments();

		ArrayList<User> userList = new ArrayList<User>();
		// Create the User objects with the data from the Firestore
		for (QueryDocumentSnapshot userDoc : userDocs) {
			User user = new User(
					userDoc.getString("erabiltzailea"),
					userDoc.getString("izena"),
					userDoc.getString("abizenak"),
					userDoc.getString("pasahitza"), 
					userDoc.getDate("jaiotze_data"),
					userDoc.getString("email"),
					userDoc.getDouble("telefonoa").intValue(),
					userDoc.getDouble("maila").intValue()
				);
			userList.add(user);
		}
		// Close the connection
		dbConexion.closeConnection(db);
		return userList;
	}
}
