package edu.uoc.android.uocgamessuite.viewmodel.settings

import androidx.lifecycle.ViewModel
import edu.uoc.android.uocgamessuite.models.repository.FirebaseRepository
import edu.uoc.android.uocgamessuite.models.repository.IFirebaseRepository

class SettingsViewModel : ViewModel() {

    // Firebase interface
    private val firebaseInterface: IFirebaseRepository = FirebaseRepository()

    /**
     * Gets the current user email
     */
    fun getUserEmail(): String? {
        return firebaseInterface.getEmail()
    }
}