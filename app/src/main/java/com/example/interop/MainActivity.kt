package com.example.interop

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.example.interop.api.NewsService
import com.example.interop.databinding.ActivityMainBinding
import com.example.interop.rvbinder.GlobalAdapter
import com.example.interop.rvbinder.RvClickListener
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), RvClickListener, View.OnClickListener {
    lateinit var binding: ActivityMainBinding
    lateinit var categoryList: MutableState<List<String>>

    @ExperimentalMaterialApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.itemclick = this@MainActivity
        binding.click = this@MainActivity
        setContentView(binding.root)
        binding.composeToolbar.apply {
            text = mutableStateOf("")
            onImageClick = { getNews() }
        }
        binding.composeChips.setContent {
            categoryList = rememberSaveable {
                mutableStateOf(emptyList())
            }
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {
                if (categoryList.value.isNotEmpty())
                    items(items = categoryList.value) { k ->
                        ShowChips(category = k)
                    }
            }
        }
    }

    @Composable
    private fun ShowToolbar() {
        TopAppBar(
            modifier = Modifier
                .fillMaxWidth(), elevation = 7.dp
        ) {
            TextField(
                value = binding.composeToolbar.text.value,
                modifier = Modifier.fillMaxWidth(),
                onValueChange = { binding.composeToolbar.text.value = it },
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
                            getNews()
                        })
                    )
                },
                textStyle = MaterialTheme.typography.subtitle1
            )
        }
    }

    @ExperimentalMaterialApi
    @Composable
    private fun ShowChips(category: String) {
        Surface(
            modifier = Modifier
                .padding(end = 10.dp), onClick = {
                if (binding.composeToolbar.text.value != category) {
                    binding.composeToolbar.text.value = category
                    getNews()
                }
            },
            shape = MaterialTheme.shapes.medium, color = Color.Cyan, border = BorderStroke(
                1.dp,
                Color.Black
            )
        ) {
            Text(
                text = category,
                color = Color.Black,
                style = MaterialTheme.typography.subtitle2, modifier = Modifier.padding(5.dp)
            )
        }
    }

    override fun click(view: View, item: Any?, position: Int, adapter: GlobalAdapter<Any>) {
    }

    override fun onClick(v: View?) {

    }

    private fun getNews() {
        CoroutineScope(Dispatchers.IO).launch {
            val c = NewsService.newsInstance.getNews(binding.composeToolbar.text.value, 1)
            c.categories = listOf(
                "Entertainment",
                "Health",
                "Business",
                "Science",
                "Sports",
                "Technology"
            )
            binding.model = c
            categoryList.value = c.categories
        }
    }
}