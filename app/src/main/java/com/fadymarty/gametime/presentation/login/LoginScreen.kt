package com.fadymarty.gametime.presentation.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.fadymarty.uikit.buttons.PrimaryButton
import com.fadymarty.uikit.common.theme.GameTimeTheme
import com.fadymarty.uikit.inputs.Input
import com.fadymarty.uikit.inputs.PasswordInput
import com.fadymarty.gametime.R
import com.fadymarty.gametime.common.util.ObserveAsEvents
import org.koin.compose.viewmodel.koinViewModel

/**
 * Экран авторизации
 *
 * Дата создания: 25-03-2026
 * Автор создания: 1
 */
@Composable
fun LoginRoot(
    onLoginClick: () -> Unit,
    onCreateAccountClick: () -> Unit,
    viewModel: LoginViewModel = koinViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    ObserveAsEvents(viewModel.events) { event ->
        when (event) {
            LoginEvent.OnLoginClick -> {
                onLoginClick()
            }
            else -> Unit
        }
    }

    LoginScreen(
        state = state,
        onEvent = { event ->
            when (event) {
                LoginEvent.OnCreateAccountClick -> {
                    onCreateAccountClick()
                }
                else -> Unit
            }
            viewModel.onEvent(event)
        }
    )
}

@Composable
private fun LoginScreen(
    state: LoginState,
    onEvent: (LoginEvent) -> Unit,
) {
    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(top = 51.dp, bottom = 9.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 52.dp),
                painter = painterResource(R.drawable.img_login),
                contentDescription = null
            )
            Spacer(modifier = Modifier.height(41.dp))
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(GameTimeTheme.colorScheme.background)
                    .padding(start = 52.dp, end = 99.dp),
                text = "Welcome Back!",
                style = GameTimeTheme.typography.title1Extrabold,
                color = GameTimeTheme.colorScheme.accentInactive
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 52.dp, end = 28.dp),
                text = "Hi, Kindly login to continue battle",
                style = GameTimeTheme.typography.caption2Regular
            )
            Spacer(modifier = Modifier.height(40.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 52.dp, end = 60.dp),
                horizontalAlignment = Alignment.End
            ) {
                Input(
                    state = state.emailState,
                    placeholder = "Email"
                )
                Spacer(modifier = Modifier.height(24.dp))
                PasswordInput(
                    state = state.passwordState,
                    placeholder = "Password",
                    isPasswordVisible = state.isPasswordVisible,
                    onTogglePasswordVisibility = {
                        onEvent(LoginEvent.OnTogglePasswordVisibility)
                    }
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Forgot Password?",
                    style = GameTimeTheme.typography.caption2Regular
                )
            }
            Spacer(modifier = Modifier.height(27.dp))
            PrimaryButton(
                modifier = Modifier.padding(horizontal = 82.dp),
                label = "Let’s Combat!",
                onClick = {
                    onEvent(LoginEvent.OnLoginClick)
                }
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "Connect With:",
                style = GameTimeTheme.typography.caption2Bold,
                textAlign = TextAlign.Center,
                color = GameTimeTheme.colorScheme.accentInactive
            )
            Spacer(modifier = Modifier.height(7.dp))
            Row(
                horizontalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                Image(
                    modifier = Modifier.size(35.dp, 34.dp),
                    painter = painterResource(R.drawable.ic_google),
                    contentDescription = null
                )
                Image(
                    modifier = Modifier.size(35.dp, 34.dp),
                    painter = painterResource(R.drawable.ic_facebook),
                    contentDescription = null
                )
            }
            Spacer(modifier = Modifier.height(18.dp))
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 40.dp),
                text = buildAnnotatedString {
                    val fullText = "Don’t have an account? \nCreate Account"
                    val createAccount = "Create Account"
                    append(fullText)

                    addLink(
                        clickable = LinkAnnotation.Clickable(
                            tag = createAccount,
                            styles = TextLinkStyles(
                                style = SpanStyle(
                                    fontWeight = FontWeight.W700,
                                    color = GameTimeTheme.colorScheme.accentInactive
                                )
                            )
                        ) {
                            onEvent(LoginEvent.OnCreateAccountClick)
                        },
                        start = fullText.indexOf(createAccount),
                        end = fullText.indexOf(createAccount) + createAccount.length
                    )
                },
                style = GameTimeTheme.typography.caption2Regular,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun LoginPreview() {
    GameTimeTheme {
        LoginScreen(
            state = LoginState(),
            onEvent = {}
        )
    }
}