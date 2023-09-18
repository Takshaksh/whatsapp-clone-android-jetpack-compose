package com.linuxh2o.connectq.ui.viewmodels

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.linuxh2o.connectq.data.models.Event
import com.linuxh2o.connectq.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class CommonViewModel @Inject constructor(
  val firebaseAuth: FirebaseAuth,
  val firebaseFirestore: FirebaseFirestore,
  val firebaseStorage: FirebaseStorage
) : ViewModel() {


  private val _isLoading = mutableStateOf(false)
  val isLoading: State<Boolean>
    get() = _isLoading

  val popupNotification = mutableStateOf<Event<String>?>(null)
  val isSignedIn = mutableStateOf(false)

  init {
    //handleException(customMessage = "Test")
  }

  fun onSignup(name: String, number: String, email: String, password: String){
    if (name.isEmpty() or number.isEmpty() or email.isEmpty() or password.isEmpty()){
      handleException(customMessage = "Please fill in all fields.")
      return
    }
    _isLoading.value = true

    firebaseFirestore.collection(Constants.COLLECTION_USER).whereEqualTo("number", number)
      .get()
      .addOnSuccessListener {
        if (it.isEmpty){
          firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener{task ->
              if (task.isSuccessful){
                isSignedIn.value = true
              }
            }
        }else{
          handleException(customMessage = "Number already exists.")
        }
      }

  }

  fun setisLoading(value: Boolean){
    _isLoading.value = value
  }

  private fun handleException(exception: Exception? = null, customMessage: String = ""){
    Log.e(TAG, "handleException: $exception")
    exception?.printStackTrace()

    val errorMessage = exception?.localizedMessage ?: ""
    val message = if (customMessage.isEmpty()) errorMessage else "$customMessage : $errorMessage"
    popupNotification.value = Event(message)
    _isLoading.value = false
  }

  companion object{
    private const val TAG = "CQ/CommonviewModel"
  }
}