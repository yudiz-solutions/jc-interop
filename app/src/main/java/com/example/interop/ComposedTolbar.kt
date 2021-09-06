package com.example.interop

import android.content.Context
import android.util.AttributeSet
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.AbstractComposeView
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp

class ComposedToolbar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AbstractComposeView(context, attrs, defStyleAttr) {
    lateinit var text: MutableState<String>
    lateinit var onImageClick: () -> Unit

    @Composable
    override fun Content() {
        TopAppBar(
            modifier = Modifier
                .fillMaxWidth(), elevation = 7.dp
        ) {
            TextField(
                value = text.value,
                modifier = Modifier.fillMaxWidth(),
                onValueChange = { text.value = it },
                label = {
                    Text(
                        text = "Search",
                        style = TextStyle(color = MaterialTheme.colors.onPrimary)
                    )
                },
                trailingIcon = {
                    Image(
                        imageVector = Icons.Default.Search,
                        contentDescription = "News Image",
                        modifier = Modifier.clickable(true, onClick = {
                            onImageClick()
                        })
                    )
                },
                textStyle = MaterialTheme.typography.subtitle1
            )
        }
    }


}