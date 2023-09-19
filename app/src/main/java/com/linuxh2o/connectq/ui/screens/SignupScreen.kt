package com.linuxh2o.connectq.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.linuxh2o.connectq.R
import com.linuxh2o.connectq.configs.AppRouter
import com.linuxh2o.connectq.ui.components.ProgressIndicator
import com.linuxh2o.connectq.ui.viewmodels.CommonViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignupScreen(navController: NavController, commonViewModel: CommonViewModel){
  val scrollState = rememberScrollState()

  Box(modifier = Modifier.fillMaxSize()) {
    Column(
      modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .verticalScroll(scrollState, true),
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      val nameState = remember { mutableStateOf(TextFieldValue()) }
      val numberState = remember { mutableStateOf(TextFieldValue()) }
      val emailState = remember { mutableStateOf(TextFieldValue()) }
      val passwordState = remember { mutableStateOf(TextFieldValue()) }

      val localFocus = LocalFocusManager.current

      Image(
        painter = painterResource(id = R.drawable.ic_connectq),
        contentDescription = "app logo",
        modifier = Modifier
          .size(200.dp)
          .padding(top = 16.dp)
          .padding(8.dp)
      )

      Text(
        text = "Signup",
        modifier = Modifier.padding(8.dp),
        fontSize = 28.sp
      )

      OutlinedTextField(
        value = nameState.value,
        onValueChange = { nameState.value = it},
        modifier = Modifier.padding(8.dp),
        label = { Text(text = "Full Name") },
      )

      OutlinedTextField(
        value = numberState.value,
        onValueChange = { numberState.value = it},
        modifier = Modifier.padding(8.dp),
        label = { Text(text = "Mobile Number") },
      )

      OutlinedTextField(
        value = emailState.value,
        onValueChange = { emailState.value = it},
        modifier = Modifier.padding(8.dp),
        label = { Text(text = "Email ID") },
      )

      OutlinedTextField(
        value = passwordState.value,
        onValueChange = { passwordState.value = it},
        modifier = Modifier.padding(8.dp),
        visualTransformation = PasswordVisualTransformation(),
        label = { Text(text = "Password") }
      )
      
      Button(
        modifier = Modifier.padding(8.dp),
        onClick = {
          localFocus.clearFocus(force = true)
          commonViewModel.onSignup(
            nameState.value.text,
            numberState.value.text,
            emailState.value.text,
            passwordState.value.text
          )
        }
      ) {
        Text(text = "Signup")  
      }

      Text(
        text = "Already have an account? Login here.",
        color = Color.Blue,
        modifier = Modifier
          .padding(8.dp)
          .clickable {
            navController.navigate(AppRouter.Login.route){
              popUpTo(AppRouter.Login.route)
              launchSingleTop = true
            }
          }
      )
    }

    // Progress indicator
    val isLoading = commonViewModel.isLoading.value
    if (isLoading){
      ProgressIndicator()
    }
  }
}