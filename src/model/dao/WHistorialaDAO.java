package model.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.FieldPath;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;

import model.exceptions.LostDbConnection;
import model.objects.WHistoriala;
import model.objects.Workout;
import model.offline.WorkoutsOffline;
import resources.GlobalVariables;

public class WHistorialaDAO {
	private DbConexion dbConexion = new DbConexion();
	private Firestore db;
	private WorkoutsDAO workoutdao = new WorkoutsDAO();
	
	public ArrayList<WHistoriala> getWHistoriala() throws Exception {
		/*if (!GlobalVariables.isConnexion) {
			WorkoutsOffline workoutsOff = new WorkoutsOffline();
			return workoutsOff.getWorkouts();
		}
		*/
		ArrayList<WHistoriala> historialList = new ArrayList<>();

		db = dbConexion.getConnection();
		try {
			// Query the workouts collection
			ApiFuture<QuerySnapshot> query = db.collection("historiala")
					.whereEqualTo("user_id", GlobalVariables.loggedUser.getUserID()).get();
			QuerySnapshot querySnapshot = query.get();
			// Process each workout document
			for (QueryDocumentSnapshot document : querySnapshot) {
				int denboraTotala = document.getLong("tiempo_total").intValue();
				Date data = document.getDate("fecha");
				String betetakoAriketak = String.valueOf(document.getLong("ejercicios").intValue() + "%");
				Workout wAux = workoutdao.getWorkoutById(document.getString("id_workout"));
				
				WHistoriala historial = new WHistoriala(wAux.getIzena(), wAux.getMaila(), denboraTotala, wAux.getDenboraTotala(), data, betetakoAriketak);
				historialList.add(historial);
			}
			dbConexion.closeConnection(db);
			return historialList;
		} catch (Exception e) {
			dbConexion.closeConnection(db);
			throw new LostDbConnection();
		}
	}
}
