package com.example.ariandroid.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ariandroid.presentation.screens.AuthorizationScreen
import com.example.ariandroid.presentation.screens.NoConnectionScreen
import com.example.ariandroid.presentation.screens.OnboardingScreen
import com.example.ariandroid.presentation.screens.SplashScreen
import com.example.ariandroid.presentation.screens.authorization.SignUp3Screen
import com.example.ariandroid.presentation.screens.authorization.Congratulations
import com.example.ariandroid.presentation.screens.authorization.LogInScreen
import com.example.ariandroid.presentation.screens.authorization.SignUp1Screen
import com.example.ariandroid.presentation.screens.authorization.SignUp2Screen
import com.example.ariandroid.presentation.viewmodel.ConnectionViewModel

@Composable
fun NavGraph() {
    val navController = rememberNavController()
    val connectionViewModel: ConnectionViewModel = hiltViewModel()

    val hasInternet by connectionViewModel.connectionState.collectAsState()

    LaunchedEffect(hasInternet) {
        if (!hasInternet) {
            // Если нет интернета, переходим на экран NoConnectionScreen
            if (navController.currentDestination?.route != "NoConnectionScreen") {
                navController.navigate("NoConnectionScreen") {
                    // Очищаем бэкстек до сплэш-экрана
                    popUpTo("SplashScreen") { inclusive = false }
                }
            }
        } else {
            // Если интернет появился и мы на экране NoConnectionScreen, переходим на Authorization
            if (navController.currentDestination?.route == "NoConnectionScreen") {
                navController.navigate("AuthorizationScreen") {
                    popUpTo("NoConnectionScreen") { inclusive = true }
                }
            }
        }
    }

    // Проверяем соединение при запуске
    LaunchedEffect(Unit) {
        connectionViewModel.checkConnection()
    }

    NavHost(
        navController = navController,
        startDestination = "SplashScreen"
    ) {
        // Навигация сплэш-экрана
        composable("SplashScreen") {
            SplashScreen (
                onSplashEnd = {
                    connectionViewModel.checkConnection()
                    if (connectionViewModel.connectionState.value) {
                        navController.navigate("OnboardingScreen") {
                            popUpTo("SplashScreen") { inclusive = true }
                        }
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
                    connectionViewModel.checkConnection()
                    if (connectionViewModel.connectionState.value) {
                        navController.navigate("AuthorizationScreen") {
                            popUpTo("OnboardingScreen") { inclusive = true }
                        }
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
            SignUp3Screen(
                navigateToCongrats = {
                    navController.navigate("Congratulations")
                },
                navigateBack = {
                    navController.navigate("SignUp2Screen")
                },
            )
        }

        // Навигация страницы успешной регистрации
        composable ("Congratulations") {
            Congratulations()
        }

        // Навигация на экран нет соединения
        composable ("NoConnectionScreen") {
            NoConnectionScreen(
                onRetry = {
                    connectionViewModel.refreshConnection()
                }
            )
        }
    }
}