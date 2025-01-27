import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.audiobook.R
import com.example.audiobook.viewmodel.PodcastListViewModel
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavHostController
import coil3.compose.rememberAsyncImagePainter
import com.example.audiobook.MainRoute
import com.example.audiobook.model.Podcasts

@Composable
fun PodcastScreen(viewModel: PodcastListViewModel, navController: NavHostController) {
    // Observe the podcast LiveData using observeAsState
    val bestPodcasts by viewModel.bestPodcasts.observeAsState(null)

    // Fetch podcast data when the composable is launched
    LaunchedEffect(Unit) {
        viewModel.fetchBestPodcasts()
    }

    Column {
        if (bestPodcasts == null || bestPodcasts?.data == null || bestPodcasts?.data?.podcasts == null) {
            Text(text = stringResource(id = R.string.loading))
        } else {
            // Display the list of podcast
            PodcastItem(bestPodcasts?.data?.podcasts, navController)
        }
    }
}

// Composable function for displaying a list of podcast
@Composable
fun PodcastItem(items: List<Podcasts>?, navController: NavHostController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            LazyColumn {
                items(items!!) { item ->
                    // Display podcast
                    PodcastCard(item, onItemClicked = {
                        navController.currentBackStackEntry?.savedStateHandle?.set("podcast", item)
                        navController.navigate(MainRoute.PodcastDetail)
                    },
                        navController)
                    // Add a divider between items
                    Divider(Modifier.height(1.dp))
                }
            }
        }
    }
}


@Composable
fun PodcastCard(podcast: Podcasts, onItemClicked: (podcast: Podcasts) -> Unit, navController: NavHostController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(16.dp))
            .clickable(onClick = {
                onItemClicked(podcast)
                //navController.navigate("${MainRoute.PodcastDetail}?podcast=${podcast}")
            }),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            val imagePainter: Painter = rememberAsyncImagePainter(podcast.thumbnail)
            Image(
                modifier = Modifier
                    .size(100.dp, 100.dp)
                    .border(BorderStroke(1.dp, Color.Black)),
                painter = imagePainter,
                alignment = Alignment.CenterStart,
                contentDescription = "",
                contentScale = ContentScale.Fit,
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.align(Alignment.CenterVertically)) {
                Text(
                    text = podcast.title.toString(),
                    modifier = Modifier.padding(0.dp, 0.dp, 12.dp, 0.dp),
                    fontWeight = FontWeight.Bold,
                    style = typography.bodyLarge
                )
                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = podcast.publisher.toString(),
                    modifier = Modifier.padding(0.dp, 0.dp, 12.dp, 0.dp),
                    style = typography.bodyMedium
                )

                if(true) {
                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = stringResource(id = R.string.favourited_tag),
                        modifier = Modifier.padding(0.dp, 0.dp, 12.dp, 0.dp),
                        style = typography.bodyLarge,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }
    }
}
