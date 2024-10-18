package model.dao;

import java.util.ArrayList;
import java.util.List;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;

import model.BcryptMethods;
import model.Workout;
import resources.GlobalVariables;

public class WorkoutsDAO {
	private DbConexion dbConexion = new DbConexion();

	public ArrayList<Workout> getWorkouts() throws Exception {
		Workout workout = null;
		ArrayList<Workout> workoutsList = new ArrayList<Workout>();
		// Get the Firestore instance
		Firestore db = dbConexion.getConnection();
		
        ApiFuture<QuerySnapshot> query = db.collection("workouts").whereLessThanOrEqualTo("maila", GlobalVariables.loggedUser.getMaila()).get();
        QuerySnapshot querySnapshot = query.get();
        
		for (QueryDocumentSnapshot document : querySnapshot) {
			String izena = document.getString("izena");
			Double maila =  document.getDouble("maila");
			String video_url = document.getString("video_url");

			workout = new Workout(izena, maila, video_url);
			workoutsList.add(workout);
		}
		return workoutsList;
	}
}
