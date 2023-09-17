package com.linuxh2o.connectq.ui.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
  val firebaseAuth: FirebaseAuth,
  val firebaseFirestore: FirebaseFirestore,
  val firebaseStorage: FirebaseStorage
) : ViewModel() {

  private val _isLoading = mutableStateOf(false)
  val isLoading: State<Boolean>
    get() = _isLoading

  fun setisLoading(value: Boolean){
    _isLoading.value = value
  }


}