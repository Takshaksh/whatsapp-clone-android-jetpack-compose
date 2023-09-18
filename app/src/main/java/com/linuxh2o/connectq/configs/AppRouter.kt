package com.linuxh2o.connectq.configs

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.linuxh2o.connectq.ui.components.NotifyMessage
import com.linuxh2o.connectq.ui.screens.ChatListScreen
import com.linuxh2o.connectq.ui.screens.LoginScreen
import com.linuxh2o.connectq.ui.screens.ProfileScreen
import com.linuxh2o.connectq.ui.screens.SignupScreen
import com.linuxh2o.connectq.ui.screens.SingleChatScreen
import com.linuxh2o.connectq.ui.screens.SingleStatusScreen
import com.linuxh2o.connectq.ui.screens.StatusListScreen
import com.linuxh2o.connectq.ui.viewmodels.CommonViewModel

sealed class AppRouter(val route: String){
  object Signup : AppRouter(SIGN_UP)
  object Login : AppRouter(LOGIN)
  object Profile : AppRouter(PROFILE)
  object ChatList : AppRouter(CHAT_LIST)
  object SingleChat : AppRouter("$SINGLE_CHAT/{chatId}"){
    fun createRoute(id: String) = "$SINGLE_CHAT/$id"
  }
  object StatusList : AppRouter(STATUS_LIST)
  object SingleStatus : AppRouter("$SINGLE_STATUS/{statusId}"){
    fun createRoute(id: String) = "$SINGLE_STATUS/$id"
  }

  companion object{
    private const val SIGN_UP = "signup"
    private const val LOGIN = "login"
    private const val PROFILE = "profile"
    private const val CHAT_LIST = "chatList"
    private const val SINGLE_CHAT = "singleChat"
    private const val STATUS_LIST = "statusList"
    private const val SINGLE_STATUS = "singleStatus"
  }
}

@Composable
fun AppNavigation(){
  val navController = rememberNavController()
  val commonViewModel: CommonViewModel = hiltViewModel()
  
  NotifyMessage(commonViewModel = commonViewModel)

  NavHost(navController = navController, startDestination = AppRouter.Signup.route){
    composable(AppRouter.Signup.route){
      SignupScreen(navController, commonViewModel)
    }

    composable(AppRouter.Login.route){
      LoginScreen()
    }

    composable(AppRouter.Profile.route){
      ProfileScreen()
    }

    composable(AppRouter.StatusList.route){
      StatusListScreen()
    }

    composable(AppRouter.SingleStatus.route){
      SingleStatusScreen("123")
    }
    
    composable(AppRouter.ChatList.route){
      ChatListScreen()
    }
    
    composable(AppRouter.SingleChat.route){
      SingleChatScreen(chatId = "123")
    }
  }
}
