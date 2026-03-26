package com.fadymarty.gametime.presentation.player_information

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.fadymarty.gametime.R
import com.fadymarty.gametime.presentation.components.CombatItem
import com.fadymarty.gametime.presentation.player_information.components.CategoryChip
import com.fadymarty.uikit.common.theme.GameTimeTheme
import io.github.ismoy.imagepickerkmp.domain.config.CameraCaptureConfig
import io.github.ismoy.imagepickerkmp.domain.config.ImagePickerConfig
import io.github.ismoy.imagepickerkmp.domain.config.PermissionAndConfirmationConfig
import io.github.ismoy.imagepickerkmp.presentation.ui.components.GalleryPickerLauncher
import io.github.ismoy.imagepickerkmp.presentation.ui.components.ImagePickerLauncher
import org.koin.compose.viewmodel.koinViewModel

/**
 *
 *
 * Дата создания: 26-03-2026
 * Автор создания: 1
 */
@Composable
fun PlayerInformationRoot(
    id: String,
    onBackClick: () -> Unit,
    viewModel: PlayerInformationViewModel = koinViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    PlayerInformationScreen(
        id = id,
        state = state,
        onEvent = { event ->
            when (event) {
                PlayerInformationEvent.OnBackClick -> {
                    onBackClick()
                }
                else -> Unit
            }
            viewModel.onEvent(event)
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun PlayerInformationScreen(
    id: String,
    state: PlayerInformationState,
    onEvent: (PlayerInformationEvent) -> Unit,
) {
    val tag = "PlayerInformationScreen"

    val context = LocalContext.current

    Scaffold { innerPadding ->
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = innerPadding
        ) {
            item {
                Icon(
                    modifier = Modifier
                        .padding(start = 29.5.dp, top = 16.dp)
                        .size(20.dp, 14.2.dp)
                        .clickable(
                            interactionSource = null,
                            indication = ripple(bounded = false)
                        ) {
                            onEvent(PlayerInformationEvent.OnBackClick)
                        },
                    imageVector = ImageVector.vectorResource(R.drawable.ic_back),
                    contentDescription = null,
                    tint = GameTimeTheme.colorScheme.accentInactive
                )
                Spacer(modifier = Modifier.height(35.8.dp))
                Text(
                    modifier = Modifier.padding(start = 40.dp),
                    text = "Player\nInformation",
                    style = GameTimeTheme.typography.title1Extrabold,
                    color = GameTimeTheme.colorScheme.accentInactive
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
            item {
                Column(
                    modifier = Modifier
                        .padding(horizontal = 40.dp)
                        .fillMaxWidth()
                        .border(
                            width = 1.dp,
                            brush = Brush.linearGradient(
                                colors = GameTimeTheme.colorScheme.accent
                            ),
                            shape = RoundedCornerShape(10.dp)
                        )
                        .background(
                            color = GameTimeTheme.colorScheme.background,
                            shape = RoundedCornerShape(10.dp)
                        )
                        .padding(top = 16.dp, bottom = 35.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        contentAlignment = Alignment.TopEnd
                    ) {
                        AsyncImage(
                            modifier = Modifier
                                .border(
                                    width = 1.dp,
                                    brush = Brush.linearGradient(
                                        colors = GameTimeTheme.colorScheme.accent
                                    ),
                                    shape = CircleShape
                                )
                                .clip(CircleShape)
                                .size(80.dp)
                                .clickable(
                                    enabled = id == "current"
                                ) {
                                    onEvent(
                                        PlayerInformationEvent.OnChangeImagePickerVisibility(true)
                                    )
                                },
                            model = ImageRequest.Builder(context)
                                .data(state.imageUri ?: R.drawable.img_profile_pic)
                                .crossfade(true)
                                .build(),
                            contentDescription = null,
                            contentScale = ContentScale.Crop
                        )
                        Box(
                            modifier = Modifier
                                .padding(top = 2.dp, end = 6.dp)
                                .size(16.dp)
                                .clip(CircleShape)
                                .background(Color(0xFF08F403))
                        )
                    }
                    Spacer(modifier = Modifier.height(7.79.dp))
                    Text(
                        text = "Scott Brown",
                        style = GameTimeTheme.typography.caption2Bold,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(0.21.dp))
                    Text(
                        text = buildAnnotatedString {
                            append("Status: ")
                            withStyle(
                                style = SpanStyle(
                                    color = Color(0xFF08F403),
                                    fontWeight = FontWeight.W700
                                )
                            ) {
                                append("Online")
                            }
                        },
                        style = GameTimeTheme.typography.caption2Regular.copy(
                            fontSize = 8.sp,
                            lineHeight = 1.em,
                            letterSpacing = 0.003.em
                        ),
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(11.dp))
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(5.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = buildAnnotatedString {
                                appendLine("Earned: ")
                                withStyle(
                                    style = SpanStyle(
                                        fontWeight = FontWeight.W700,
                                        color = GameTimeTheme.colorScheme.accentInactive
                                    )
                                ) {
                                    append($$"$5000")
                                }
                            },
                            style = GameTimeTheme.typography.caption2Regular
                        )
                        VerticalDivider(
                            modifier = Modifier.height(26.dp),
                            color = GameTimeTheme.colorScheme.placeholder
                        )
                        Text(
                            text = buildAnnotatedString {
                                appendLine("Staked: ")
                                withStyle(
                                    style = SpanStyle(
                                        fontWeight = FontWeight.W700,
                                        color = GameTimeTheme.colorScheme.accentInactive
                                    )
                                ) {
                                    append($$"$2000")
                                }
                            },
                            style = GameTimeTheme.typography.caption2Regular
                        )
                    }
                    Spacer(modifier = Modifier.height(30.dp))
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(9.dp),
                        verticalAlignment = Alignment.Bottom
                    ) {
                        Icon(
                            modifier = Modifier.size(29.dp),
                            imageVector = ImageVector.vectorResource(R.drawable.ic_royal_crown),
                            contentDescription = null,
                            tint = Color(0xFFF4C73E)
                        )
                        Text(
                            text = "Gold Player",
                            style = GameTimeTheme.typography.title3Regular,
                            color = Color(0xFFF4C73E)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(21.dp))
            }
            item {
                Text(
                    modifier = Modifier.padding(start = 40.dp),
                    text = "CATEGORY",
                    style = GameTimeTheme.typography.caption2Bold.copy(
                        fontSize = 6.sp,
                        lineHeight = 1.em,
                        letterSpacing = 0.003.em
                    ),
                    color = GameTimeTheme.colorScheme.accentInactive
                )
                Spacer(modifier = Modifier.height(9.dp))
            }
            item {
                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    contentPadding = PaddingValues(horizontal = 40.dp)
                ) {
                    item {
                        CategoryChip(
                            label = "Image"
                        )
                    }
                    item {
                        CategoryChip(
                            label = "Circles"
                        )
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
            }
            item {
                Text(
                    modifier = Modifier.padding(start = 40.dp),
                    text = "Player\nCombats",
                    style = GameTimeTheme.typography.headlineMedium.copy(
                        fontWeight = FontWeight.W700
                    ),
                    color = GameTimeTheme.colorScheme.accentInactive
                )
                Spacer(modifier = Modifier.height(12.dp))
            }
            items(3) {
                CombatItem()
                Spacer(modifier = Modifier.height(10.dp))
            }
        }
    }

    if (state.isImagePickerVisible) {
        ModalBottomSheet(
            onDismissRequest = {
                onEvent(PlayerInformationEvent.OnChangeImagePickerVisibility(false))
            }
        ) {

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        onEvent(PlayerInformationEvent.OnChangeGalleryVisibility(true))
                    }
                    .padding(16.dp),
                text = "Галерея"
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        onEvent(PlayerInformationEvent.OnChangeCameraVisibility(true))
                    }
                    .padding(16.dp),
                text = "Камера"
            )
        }
    }

    if (state.isGalleryVisible) {
        GalleryPickerLauncher(
            onPhotosSelected = {
                onEvent(PlayerInformationEvent.OnImageSelected(it.first().uri))
            },
            onDismiss = {
                onEvent(PlayerInformationEvent.OnChangeCameraVisibility(false))
            },
            onError = {
                Log.e(tag, "Ошибка — Не удалось получить фото из галереи")
            }
        )
    }
    if (state.isCameraVisible) {
        ImagePickerLauncher(
            config = ImagePickerConfig(
                onPhotoCaptured = {
                    onEvent(PlayerInformationEvent.OnImageSelected(it.uri))
                },
                onDismiss = {
                    onEvent(PlayerInformationEvent.OnChangeGalleryVisibility(false))
                },
                onError = {
                    Log.e(tag, "Ошибка — Не удалось сделать фото из камеры")
                },
                cameraCaptureConfig = CameraCaptureConfig(
                    permissionAndConfirmationConfig = PermissionAndConfirmationConfig(
                        skipConfirmation = true
                    )
                )
            )
        )
    }
}

@Preview(showSystemUi = true)
@Composable
private fun PlayerInformationPreview() {
    GameTimeTheme {
        PlayerInformationScreen(
            id = "current",
            state = PlayerInformationState(),
            onEvent = {}
        )
    }
}