package edu.uoc.android.uocgamessuite.viewmodel.lists

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import edu.uoc.android.uocgamessuite.models.repository.FirebaseRepository
import edu.uoc.android.uocgamessuite.models.repository.IFirebaseRepository
import edu.uoc.android.uocgamessuite.ui.lists.CreateListFragment
import edu.uoc.android.uocgamessuite.utils.ListType

class CreateListViewModel : ViewModel() {

    // Interface of create list to prevent the lost with screen rotation
    var createListInterface: CreateListFragment.CreateListInterface? = null

    // Livedata objects
    val createListSuccessful: MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>() }
    val createListError: MutableLiveData<Exception> by lazy { MutableLiveData<Exception>() }

    // Firebase interface
    private val firebaseInterface: IFirebaseRepository = FirebaseRepository()

    // Success creating list
    private val createListSuccessfulFunction = Observer<Boolean> {
        createListSuccessful.value = it
        clearInterfaceObservers()
    }

    // Error creating list
    private val createListErrorFunction = Observer<Exception> {
        createListError.value = it
        clearInterfaceObservers()
    }

    /**
     * Creates a new personal list
     */
    fun createList(listName: String) {
        firebaseInterface.createListSuccessful.observeForever(createListSuccessfulFunction)
        firebaseInterface.createListError.observeForever(createListErrorFunction)

        firebaseInterface.createList(listName, ListType.PERSONAL)
    }

    /**
     * Clear all observers
     */
    private fun clearInterfaceObservers() {
        firebaseInterface.createListSuccessful.removeObserver { }
        firebaseInterface.createListError.removeObserver { }
    }
}