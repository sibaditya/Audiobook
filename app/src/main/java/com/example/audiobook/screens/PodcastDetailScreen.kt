package com.example.audiobook.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.audiobook.model.Podcasts
import com.example.audiobook.repository.PodcastMapperManager


// Start of the Detail screen for each podcast.
@Composable
fun PodcastsDetailScreen(
    podcasts: Podcasts?,
    navController: NavHostController
) {
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
    Column {
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

        Card(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
                .clip(RoundedCornerShape(16.dp))
                .align(Alignment.CenterHorizontally),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 4.dp
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = podcast?.title.toString(),
                    modifier = Modifier.padding(0.dp, 0.dp, 12.dp, 0.dp),
                    fontWeight = FontWeight.Bold,
                    style = typography.bodyLarge
                )
                Spacer(modifier = Modifier.height(8.dp))

                AsyncImage(
                    model = podcast?.thumbnail,
                    contentDescription = "Podcast Image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .border(BorderStroke(1.dp, Color.Black)),
                    contentScale = ContentScale.Fit
                )

                Spacer(modifier = Modifier.width(16.dp))

                Text(
                    text = podcast?.publisher.toString(),
                    modifier = Modifier.padding(0.dp, 0.dp, 12.dp, 0.dp),
                    style = typography.bodyMedium
                )

                Spacer(modifier = Modifier.width(16.dp))

                // Retrieve the Boolean value for "ButtonState"
                val isFavourited = PodcastMapperManager.getValue(podcast?.id) ?: false

                // State to trigger recomposition when value changes
                var currentState by remember { mutableStateOf(isFavourited) }

                Button(
                    onClick = {
                        val newValue = !currentState
                        PodcastMapperManager.updateValue(podcast?.id, newValue)
                        currentState = newValue
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (currentState) Color.Red else Color.Gray
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(
                        text = if (currentState) "Favourited" else "Favourite",
                        color = Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = podcast?.description.toString(),
                    modifier = Modifier.padding(0.dp, 0.dp, 12.dp, 0.dp),
                    style = typography.bodyMedium
                )
                Spacer(modifier = Modifier.width(16.dp))
            }
        }
    }
}


