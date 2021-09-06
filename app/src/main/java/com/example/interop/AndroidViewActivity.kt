package com.example.interop

import android.os.Bundle
import android.view.ViewGroup
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import com.example.interop.ui.theme.InteropTheme

class AndroidViewActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            InteropTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    GetEmailValidator()
                }
            }
        }
    }
}
@Preview
@Composable
fun GetEmailValidator(){
    AndroidView(modifier = Modifier.fillMaxWidth(), factory = { context ->
        CustomEmailValidator(
            context, hint = "Enter email id",
            label = "Email Validator",
            imgRight = ContextCompat.getDrawable(
                context,
                R.drawable.ic_launcher_foreground
            )!!,
            imgWrong = ContextCompat.getDrawable(
                context,
                R.drawable.ic_launcher_background
            )!!
        ).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        }
    })
}