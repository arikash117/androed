package com.example.ariandroid.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ariandroid.ui.screens.AuthorizationScreen
import com.example.ariandroid.ui.screens.OnboardingScreen
import com.example.ariandroid.ui.screens.SplashScreen
import com.example.ariandroid.ui.screens.authorization.SignUp3Screen
import com.example.ariandroid.ui.screens.authorization.Congratulations
import com.example.ariandroid.ui.screens.authorization.LogInScreen
import com.example.ariandroid.ui.screens.authorization.SignUp1Screen
import com.example.ariandroid.ui.screens.authorization.SignUp2Screen

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

        // Навигация онбординг экрана
        composable("OnboardingScreen") {
            OnboardingScreen(
                navigateToAutorization = {
                    navController.navigate("AuthorizationScreen") {
                        popUpTo("OnboardingScreen") {inclusive = true}
                    }
                }
            )
        }

        // Навигация страницы входа в аккаунт
        composable ("LogInScreen") {
            LogInScreen(
                navigateToSignUp = {
                    navController.navigate("SignUp1Screen") {}
                },
            )
        }

        // Навигация страницы 1 регистрации
        composable ("SignUp1Screen") {
            SignUp1Screen(
                navigateToSignUp2 = {
                    navController.navigate("SignUp2Screen")
                },
                navigateBack = {
                    navController.navigate("AuthorizationScreen")
                },
            )
        }

        // Навигация страницы 2 регистрации
        composable ("SignUp2Screen") {
            SignUp2Screen(
                navigateToSignUp3 = {
                    navController.navigate("SignUp3Screen")
                },
                navigateBack = {
                    navController.navigate("SignUp1Screen")
                },
            )
        }

        // Навигация страницы 3 регистрации
        composable ("SignUp3Screen") {
            SignUp3Screen()
        }

        // Навигация страницы успешной регистрации
        composable ("Congratulations") {
            Congratulations()
        }

    }
}