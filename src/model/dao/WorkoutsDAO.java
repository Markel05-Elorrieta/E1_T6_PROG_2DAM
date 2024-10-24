package model.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.FieldPath;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;

import model.BcryptMethods;
import model.Workout;
import resources.GlobalVariables;

public class WorkoutsDAO {
	private DbConexion dbConexion = new DbConexion();
	private Firestore db;

	 public ArrayList<Workout> getWorkouts() throws Exception {
	        ArrayList<Workout> workoutsList = new ArrayList<>();
	        
	        db = dbConexion.getConnection();

	        // Query the workouts collection
	        ApiFuture<QuerySnapshot> query = db.collection("workouts")
	                .whereLessThanOrEqualTo("maila", GlobalVariables.loggedUser.getMaila()).get();
	        QuerySnapshot querySnapshot = query.get();

	        // Process each workout document
	        for (QueryDocumentSnapshot document : querySnapshot) {
	            String izena = document.getString("izena");
	            Double maila = document.getDouble("maila");
	            String video_url = document.getString("video_url");
	            int ariketaSize = countAriketakByWorkoutId(document.getId());

	            Workout workout = new Workout(izena, maila, video_url, ariketaSize);
	            workoutsList.add(workout);
	        }
	        dbConexion.closeConnection(db);
	        return workoutsList;
	    }
	    private int countAriketakByWorkoutId(String workoutID) throws InterruptedException, ExecutionException {
	        // Query the workouts collection for the specific workout ID
	        ApiFuture<QuerySnapshot> query = db.collection("workouts")
	                .whereEqualTo(FieldPath.documentId(), workoutID).get();
	        QuerySnapshot querySnapshot = query.get();

	        // If no matching workout found, return 0
	        if (querySnapshot.isEmpty()) {
	            return 0;
	        }

	        // Get the reference to the ariketak subcollection
	        QueryDocumentSnapshot workoutDocument = querySnapshot.getDocuments().get(0);
	        CollectionReference ariketakCollection = workoutDocument.getReference().collection("ariketak");

	        // Query the ariketak subcollection
	        ApiFuture<QuerySnapshot> ariketakQuery = ariketakCollection.get();
	        QuerySnapshot ariketakQuerySnapshot = ariketakQuery.get();

	        // Return the count of documents in the ariketak subcollection
	        return ariketakQuerySnapshot.size();
	    }
}
