package com.shreyas.nytimes.ui

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.shreyas.nytimes.R
import com.shreyas.nytimes.model.OwnerData
import com.shreyas.nytimes.model.RepositoryData
import com.shreyas.nytimes.ui.theme.GithubRepositoryTheme
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

@Composable
fun GithubRepoListItem(repoData: RepositoryData) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(16.dp)),
        elevation = 0.dp,
        backgroundColor = MaterialTheme.colors.onSurface
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {

            val avatar = rememberAsyncImagePainter(model = repoData.owner.avatar_url)
            DrawComposableImage(
                image = avatar,
                composeContentDescription = "Github Repository Avatar",
                width = 80.dp,
                height = 80.dp,
                size = 16.dp
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.align(Alignment.CenterVertically)) {

                DrawComposableText(
                    content = repoData.name,
                    start = 0.dp,
                    top = 0.dp,
                    end = 12.dp,
                    bottom = 0.dp,
                    weightOfFont = FontWeight.Bold,
                    styleOfText = MaterialTheme.typography.h2
                )

                Spacer(modifier = Modifier.height(8.dp))

                DrawComposableText(
                    content = repoData.description,
                    start = 0.dp,
                    top = 0.dp,
                    end = 12.dp,
                    bottom = 0.dp,
                    weightOfFont = FontWeight.Thin,
                    styleOfText = MaterialTheme.typography.h4
                )

                Row(
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {

                    DrawComposableImage(
                        image = painterResource(id = R.drawable.ic_star),
                        composeContentDescription = "Repository Stars Count",
                        width = 5.dp,
                        height = 5.dp,
                        size = 1.dp
                    )

                    Spacer(modifier = Modifier.height(2.dp))

                    DrawComposableText(
                        content = repoData.stargazers_count.toString(),
                        start = 0.dp,
                        top = 0.dp,
                        end = 12.dp,
                        bottom = 0.dp,
                        weightOfFont = FontWeight.Thin,
                        styleOfText = MaterialTheme.typography.h4
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    DrawComposableImage(
                        image = painterResource(id = R.drawable.ic_fork),
                        composeContentDescription = "Repository Forks Count",
                        width = 5.dp,
                        height = 5.dp,
                        size = 1.dp
                    )

                    Spacer(modifier = Modifier.height(2.dp))

                    DrawComposableText(
                        content = repoData.forks_count.toString(),
                        start = 0.dp,
                        top = 0.dp,
                        end = 12.dp,
                        bottom = 0.dp,
                        weightOfFont = FontWeight.Thin,
                        styleOfText = MaterialTheme.typography.h4
                    )
                }

                Spacer(modifier = Modifier.height(4.dp))

                DrawComposableText(
                    content = "Last updated on " + setFormattedDate(repoData.updated_at),
                    start = 0.dp,
                    top = 0.dp,
                    end = 12.dp,
                    bottom = 0.dp,
                    weightOfFont = FontWeight.Thin,
                    styleOfText = MaterialTheme.typography.h4
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Preview("Night", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
private fun GithubRepositoryLineItemPreview() {
    GithubRepositoryTheme {
        GithubRepoListItem(previewRepoData())
    }
}

private fun previewRepoData(): RepositoryData = RepositoryData (
    name = "okhttp",
    description = "Square’s meticulous HTTP client for the JVM, Android, and GraalVM.",
    stargazers_count = 44786,
    forks_count = 9275,
    updated_at = "2023-12-12T02:20:23Z",
    html_url = "https://github.com/square/okhttp",
    owner = OwnerData(avatar_url = "https://avatars.githubusercontent.com/u/82592?v=4")
)

@Composable
private fun DrawComposableImage(
    image: Painter,
    composeContentDescription: String,
    width: Dp,
    height: Dp,
    size: Dp
) {
    Image(
        painter = image,
        modifier = Modifier
            .size(width, height)
            .clip(RoundedCornerShape(size)),
        alignment = Alignment.CenterStart,
        contentDescription = composeContentDescription,
        contentScale = ContentScale.Crop
    )
}

@Composable
private fun DrawComposableText(
    content: String,
    start: Dp,
    top: Dp,
    end: Dp,
    bottom: Dp,
    weightOfFont: FontWeight?,
    styleOfText: TextStyle
) {
    Text(
        text = content,
        modifier = Modifier.padding(start, top, end, bottom),
        color = MaterialTheme.colors.surface,
        fontWeight = weightOfFont,
        style = styleOfText
    )
}

private fun setFormattedDate(dateTime: String): String {
    val dateTimeInstance = LocalDateTime.ofInstant(Instant.parse(dateTime), ZoneOffset.UTC)
    return DateTimeFormatter.ofPattern(LAST_UPDATED_DATE_FORMAT).format(dateTimeInstance).toString()
}

const val LAST_UPDATED_DATE_FORMAT: String = "dd MM, yyyy"