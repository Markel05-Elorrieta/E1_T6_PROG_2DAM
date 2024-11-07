package model.dao;

import java.util.ArrayList;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.FieldPath;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;

import model.exceptions.LostDbConnection;
import model.objects.Ariketa;
import model.objects.Serie;
import model.objects.Workout;
import resources.GlobalVariables;

public class SerieakDAO {
	private DbConexion dbConexion = new DbConexion();
	private Firestore db;

	public ArrayList<Serie> getSerieByAriketaId(ArrayList<String> serieID) throws Exception {
		if (!GlobalVariables.isConnexion) {
			// AriketakOffline ariketakOff = new AriketakOffline();
			// return workoutsOff.getAriketak(Workout workout);
		}
		ArrayList<Serie> ariketaList = new ArrayList<>();

		db = dbConexion.getConnection();
		try {
			
			for (int i = 0; i < serieID.size(); i++) {
				ApiFuture<QuerySnapshot> query = db.collection("serieak").whereEqualTo(FieldPath.documentId(), serieID.get(i)).get();

				QuerySnapshot querySnapshot = query.get();
				QueryDocumentSnapshot document = querySnapshot.getDocuments().get(0);
				
					String izena = document.getString("izena");
					int iraupena = document.getLong("denbora").intValue();
					int repetizioak = document.getLong("repes").intValue();

					Serie serie = new Serie(izena, iraupena, repetizioak);
					ariketaList.add(serie);
			}

			dbConexion.closeConnection(db);
			return ariketaList;
		} catch (Exception e) {
			dbConexion.closeConnection(db);
			throw new LostDbConnection();
		}
	}
}
