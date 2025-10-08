package com.example.ariandroid.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ariandroid.ui.screens.AuthorizationScreen
import com.example.ariandroid.ui.screens.OnboardingScreen
import com.example.ariandroid.ui.screens.SplashScreen

@Composable
fun NavGraph() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "SplashScreen"
    ) {
        // Навигация сплэш-экрана
        composable("SplashScreen") {
            SplashScreen (
                onSplashEnd = {
                    navController.navigate("OnboardingScreen") {
                        popUpTo("SplashScreen") { inclusive = true }
                    }
                }
            )
        }

        // Навигация страницы выбора авторизации
        composable("AuthorizationScreen") {
            AuthorizationScreen (
                navigateToLogIn = {
                    navController.navigate("LoginScreen") {}
                },
                navigateToSignUp = {
                    navController.navigate("SignUp1Screen") {}
                },
            )
        }

        // Навигация
        composable("OnboardingScreen") {
            OnboardingScreen(
                navigateToAutorization = {
                    navController.navigate("AuthorizationScreen") {
                        popUpTo("OnboardingScreen") {inclusive = true}
                    }
                }
            )
        }

    }
}