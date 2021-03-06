package net.aiscope.gdd_app.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import net.aiscope.gdd_app.coroutines.DispatcherProvider
import net.aiscope.gdd_app.model.HealthFacility
import javax.inject.Inject

class FirestoreHealthFacilityRepository @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val firebaseAuth: FirebaseAuth,
    private val dispatchers: DispatcherProvider
) : HealthFacilityRepository {

    override suspend fun load(): HealthFacility = withContext(dispatchers.io()) {
        val user = firebaseAuth.currentUser ?: throw IllegalStateException("")
        val snapshot: QuerySnapshot = firestore.collection("facilities")
            .whereArrayContains("microscopists", getMicroscopistReference(user))
            .get().await()
        val document: DocumentSnapshot = snapshot.documents[0]
        HealthFacility(document["name"] as String, document.id, user.uid)
    }

    private fun getMicroscopistReference(user: FirebaseUser): DocumentReference {
        return firestore.collection("microscopists").document(user.uid)
    }
}
