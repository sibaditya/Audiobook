package com.example.audiobook.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil3.compose.rememberAsyncImagePainter
import com.example.audiobook.model.Podcasts


// Start of the Detail screen for each cat.
@Composable
fun PodcastsDetailScreen(
    podcasts: Podcasts?,
    navController: NavHostController
) {
    val context = LocalContext.current

    AnimatedVisibility(
        visible = true,
        enter = expandVertically(
            expandFrom = Alignment.Top,
            initialHeight = { 0 }
        )
    ) {
        SetPodcastsDetails(podcasts, navController)
    }
}

// This UI starts with AnimatedVisibility
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SetPodcastsDetails(
    podcast: Podcasts?,
    navController: NavHostController
) {
    Surface {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            TopAppBar(
                title = {
                    Text(
                        text = "Back",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                colors = androidx.compose.material3.TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = Color(0xFF6200EE), // Purple color
                    titleContentColor = Color.White
                )
            )

            Spacer(modifier = Modifier.width(16.dp))

            val imagePainter: Painter = rememberAsyncImagePainter(podcast?.thumbnail)
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(BorderStroke(1.dp, Color.Black)),
                painter = imagePainter,
                alignment = Alignment.CenterStart,
                contentDescription = "",
                contentScale = ContentScale.Fit,
            )

            Spacer(modifier = Modifier.width(16.dp))

            var isSelected by remember { mutableStateOf(false) }

            Button(
                onClick = { isSelected = !isSelected },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isSelected) Color.Red else Color.Gray
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = if (isSelected) "Favourited" else "Favourite",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Text(
                text = podcast?.description.toString(),
                modifier = Modifier.padding(0.dp, 0.dp, 12.dp, 0.dp),
                style = typography.labelMedium
            )
        }
    }
}


