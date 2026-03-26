package com.fadymarty.gametime.presentation.create_account

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.input.InputTransformation
import androidx.compose.foundation.text.input.maxLength
import androidx.compose.foundation.text.input.then
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
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.fadymarty.gametime.R
import com.fadymarty.gametime.common.util.ObserveAsEvents
import com.fadymarty.uikit.buttons.PrimaryButton
import com.fadymarty.uikit.common.theme.GameTimeTheme
import com.fadymarty.uikit.inputs.Input
import com.fadymarty.uikit.inputs.PasswordInput
import org.koin.compose.viewmodel.koinViewModel

/**
 * Экран регистрации
 *
 * Дата создания: 25-03-2026
 * Автор создания: 1
 */
@Composable
fun CreateAccountRoot(
    onCreateAccountClick: () -> Unit,
    onLoginClick: () -> Unit,
    viewModel: CreateAccountViewModel = koinViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    ObserveAsEvents(viewModel.events) { event ->
        when (event) {
            CreateAccountEvent.OnCreateAccountClick -> {
                onCreateAccountClick()
            }
            else -> Unit
        }
    }

    CreateAccountScreen(
        state = state,
        onEvent = { event ->
            when (event) {
                CreateAccountEvent.OnLoginClick -> {
                    onLoginClick()
                }
                else -> Unit
            }
            viewModel.onEvent(event)
        }
    )
}

@Composable
private fun CreateAccountScreen(
    state: CreateAccountState,
    onEvent: (CreateAccountEvent) -> Unit,
) {
    Scaffold { innerPadding ->
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = innerPadding,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Spacer(modifier = Modifier.height(21.dp))
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 27.dp),
                    painter = painterResource(R.drawable.img_create_account),
                    contentDescription = null
                )
            }
            stickyHeader {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(GameTimeTheme.colorScheme.background)
                        .padding(
                            start = 52.dp,
                            top = 26.dp,
                            end = 99.dp,
                            bottom = 12.dp
                        ),
                    text = "Create Account",
                    style = GameTimeTheme.typography.title1Extrabold,
                    color = GameTimeTheme.colorScheme.accentInactive
                )
            }
            item {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 52.dp, end = 28.dp),
                    text = "Hi, kindly fill in the form to proceed \ncombat",
                    style = GameTimeTheme.typography.caption2Regular
                )
                Spacer(modifier = Modifier.height(37.dp))
            }
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 52.dp, end = 60.dp),
                    verticalArrangement = Arrangement.spacedBy(24.dp)
                ) {
                    Input(
                        state = state.fullNameState,
                        placeholder = "Full Name"
                    )
                    Input(
                        state = state.userNameState,
                        placeholder = "User Name"
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Input(
                            modifier = Modifier.width(35.dp),
                            state = state.countryCodeState,
                            placeholder = "+234",
                            inputTransformation = InputTransformation.maxLength(4)
                        )
                        Spacer(modifier = Modifier.width(18.dp))
                        Input(
                            modifier = Modifier.weight(1f),
                            state = state.phoneNumberState,
                            placeholder = "Your Phone",
                            inputTransformation = InputTransformation.then {
                                if (!asCharSequence().isDigitsOnly()) {
                                    revertAllChanges()
                                }
                            }
                        )
                    }
                    Input(
                        state = state.emailState,
                        placeholder = "Email"
                    )
                    PasswordInput(
                        state = state.passwordState,
                        placeholder = "Password",
                        isPasswordVisible = state.isPasswordVisible,
                        onTogglePasswordVisibility = {
                            onEvent(CreateAccountEvent.OnTogglePasswordVisibility)
                        }
                    )
                    PasswordInput(
                        state = state.confirmPasswordState,
                        placeholder = "Confirm Password",
                        isPasswordVisible = state.isConfirmPasswordVisible,
                        onTogglePasswordVisibility = {
                            onEvent(CreateAccountEvent.OnToggleConfirmPasswordVisibility)
                        }
                    )
                }
                Spacer(modifier = Modifier.height(44.dp))
            }
            item {
                PrimaryButton(
                    modifier = Modifier.padding(horizontal = 82.dp),
                    label = "Create Account",
                    onClick = {
                        onEvent(CreateAccountEvent.OnCreateAccountClick)
                    }
                )
                Spacer(modifier = Modifier.height(23.dp))
            }
            item {
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
                Spacer(modifier = Modifier.height(24.dp))
            }
            item {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 40.dp),
                    text = buildAnnotatedString {
                        val fullText = "Already have an account? \nLogin"
                        val login = "Login"
                        append(fullText)

                        addLink(
                            clickable = LinkAnnotation.Clickable(
                                tag = login,
                                styles = TextLinkStyles(
                                    style = SpanStyle(
                                        fontWeight = FontWeight.W700,
                                        color = GameTimeTheme.colorScheme.accentInactive
                                    )
                                )
                            ) {
                                onEvent(CreateAccountEvent.OnLoginClick)
                            },
                            start = fullText.indexOf(login),
                            end = fullText.indexOf(login) + login.length
                        )
                    },
                    style = GameTimeTheme.typography.caption2Regular,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(5.dp))
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun CreateAccountPreview() {
    GameTimeTheme {
        CreateAccountScreen(
            state = CreateAccountState(),
            onEvent = {}
        )
    }
}