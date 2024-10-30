package model.dao;

import java.util.ArrayList;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.FieldPath;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;

import model.exceptions.LostDbConnection;
import model.objects.Ariketa;
import model.objects.Workout;
import resources.GlobalVariables;

public class AriketakDAO {
	private DbConexion dbConexion = new DbConexion();
	private Firestore db;

	public ArrayList<Ariketa> getAriketak(Workout workout) throws Exception {
		if (!GlobalVariables.isConnexion) {
			// AriketakOffline ariketakOff = new AriketakOffline();
			// return workoutsOff.getAriketak(Workout workout);
		}
		ArrayList<Ariketa> ariketaList = new ArrayList<>();

		db = dbConexion.getConnection();
		try {
			for (int i = 0; i < workout.getAriketaSize(); i++) {
				ApiFuture<QuerySnapshot> query = db.collection("ariketak")
						.whereEqualTo(FieldPath.documentId(), workout.getAriketasID().get(i)).get();
				QuerySnapshot querySnapshot = query.get();

				QueryDocumentSnapshot document = querySnapshot.getDocuments().get(0);

				String izena = document.getString("izena");
				int iraupena = document.getLong("denbora").intValue();
				String deskribapena = document.getString("deskribapena");
				String video_url = document.getString("video_url");
				String landu_muskulua = document.getString("landutako_muskulua");

				Ariketa ariketa = new Ariketa(izena, deskribapena, landu_muskulua, iraupena, video_url);
				ariketaList.add(ariketa);
			}
			
			dbConexion.closeConnection(db);
			return ariketaList;
		} catch (Exception e) {
			dbConexion.closeConnection(db);
			throw new LostDbConnection();
		}
	}
}
