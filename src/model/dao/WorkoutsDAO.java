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

import model.exceptions.LostDbConnection;
import model.objects.Workout;
import model.offline.WorkoutsOffline;
import resources.GlobalVariables;

public class WorkoutsDAO {
	private DbConexion dbConexion = new DbConexion();
	private Firestore db;

	public ArrayList<Workout> getWorkouts() throws Exception {
		if (!GlobalVariables.isConnexion) {
			WorkoutsOffline workoutsOff = new WorkoutsOffline();
			return workoutsOff.getWorkouts();
		}
		ArrayList<Workout> workoutsList = new ArrayList<>();

		db = dbConexion.getConnection();
		try {
			// Query the workouts collection
			ApiFuture<QuerySnapshot> query = db.collection("workouts")
					.whereLessThanOrEqualTo("maila", GlobalVariables.loggedUser.getMaila()).get();
			QuerySnapshot querySnapshot = query.get();

			// Process each workout document
			for (QueryDocumentSnapshot document : querySnapshot) {
				String id = document.getId();
				String izena = document.getString("izena");
				int maila = document.getLong("maila").intValue();
				String video_url = document.getString("video_url");
				List<String> ariketaList = (List<String>) document.get("ariketasID");
				int ariketaSize = ariketaList.size();

				Workout workout = new Workout(id, izena, maila, video_url, ariketaSize);
				workoutsList.add(workout);
			}
			dbConexion.closeConnection(db);
			return workoutsList;
		} catch (Exception e) {
			dbConexion.closeConnection(db);
			throw new LostDbConnection();
		}
	}

	public ArrayList<Workout> getWorkoutsBackup() throws Exception {
		ArrayList<Workout> workoutsList = new ArrayList<>();

		db = dbConexion.getConnection();

		// Query the workouts collection
		ApiFuture<QuerySnapshot> query = db.collection("workouts").get();
		QuerySnapshot querySnapshot = query.get();

		// Process each workout document
		for (QueryDocumentSnapshot document : querySnapshot) {
			String id = document.getId();
			String izena = document.getString("izena");
			int maila = document.getLong("maila").intValue();
			String video_url = document.getString("video_url");
			List<String> ariketaList = (List<String>) document.get("ariketasID");
			int ariketaSize = ariketaList.size();

			Workout workout = new Workout(id, izena, maila, video_url, ariketaSize);
			workoutsList.add(workout);
		}
		dbConexion.closeConnection(db);
		return workoutsList;
	}

	public ArrayList<String> getAriketasIDByWorkout(Workout workout) throws Exception {
		ArrayList<String> ariketasID = new ArrayList<>();
		db = dbConexion.getConnection();
		try {
			// Query the workouts collection
			ApiFuture<QuerySnapshot> query = db.collection("workouts")
					.whereEqualTo(FieldPath.documentId(), workout.getWorkoutID()).get();
			QuerySnapshot querySnapshot = query.get();

			// Process each workout document
			QueryDocumentSnapshot document = querySnapshot.getDocuments().get(0);
			List<String> ariketaList = (List<String>) document.get("ariketasID");
			ariketasID.addAll(ariketaList);

			dbConexion.closeConnection(db);
			return ariketasID;
		} catch (Exception e) {
			dbConexion.closeConnection(db);
			throw new LostDbConnection();
		}
	}
}
