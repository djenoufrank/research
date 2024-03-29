package com.example.jetbizcard

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetbizcard.ui.theme.JetBizCardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetBizCardTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    createBizCard("Android")
                }
            }
        }
    }
}

@Composable
fun createBizCard(name: String) {
    val buttonClickedState= remember {
        mutableStateOf(false)
    }
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Card(
            modifier = Modifier
                .width(200.dp)
                .height(390.dp)
                .padding(8.dp),
            shape = RoundedCornerShape(corner = CornerSize(15.dp)),
            backgroundColor = Color.Cyan,
            elevation = 4.dp
        ){

        Column(
            modifier = Modifier.height(300.dp), verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            createProfileImage()
            Divider()
            createInfo()
            Button(onClick ={
        buttonClickedState.value= !buttonClickedState.value
            }){
                Text("port folio", style=MaterialTheme.typography.button)
            }
            if(buttonClickedState.value){
                content()
            }else{
                Box(){

                }
            }
        }

    }
}
}

@Composable
private fun createInfo() {
    Column(modifier = Modifier.padding(5.dp)) {
        Text(
            text = "Miles P.", style = MaterialTheme.typography.h4,
            color = MaterialTheme.colors.primaryVariant
        )
        Text(text = "Android Compose Programmer", modifier = Modifier.padding(3.dp))

        Text(
            text = "@themilesCompose", modifier = Modifier.padding(3.dp),
            style = MaterialTheme.typography.subtitle1
        )


    }
}

@Composable
private fun createProfileImage(modifier: Modifier =Modifier) {//modifier: Modifier =Modifier veut dire que le parametre n'est pas obligatoire
    Surface(
        modifier = Modifier
            .size(150.dp)
            .padding(5.dp), shape = CircleShape,
        border = BorderStroke(0.5.dp, Color.LightGray), elevation = 4.dp,
        color = MaterialTheme.colors.onSurface.copy(alpha = 0.5.toFloat())
    ) {
        Image(
            painter = painterResource(id = R.drawable.people_image),
            contentDescription = "profil image",
            modifier = Modifier.size(135.dp),
            contentScale = ContentScale.Crop
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JetBizCardTheme {
        createBizCard("Android")
    }
}
@Preview
@Composable//tout ce qui pourrait etre visible ds la vue
fun content(){
    Box(modifier = Modifier
        .fillMaxHeight()
        .fillMaxWidth()
        .padding(5.dp)){
        Surface(modifier = Modifier
            .padding(3.dp)
            .fillMaxWidth()
            .fillMaxHeight(),
        shape = RoundedCornerShape(corner = CornerSize(6.dp)),
        border = BorderStroke(width = 2.dp,color=Color.LightGray)
        )
        {
    Portfolio(data= listOf("project 1","project 2","project 3"))
        }
    }
}

@Composable
fun Portfolio(data: List<String>) {
    LazyColumn{//lazyColum est coe le recycleview
        items(data) { item ->
            Card(modifier = Modifier
                .padding(13.dp)
                .fillMaxWidth(),
                shape = RectangleShape,
                elevation = 4.dp) {
                Row(modifier = Modifier
                    .padding(8.dp)
                    .background(MaterialTheme.colors.surface)
                    .padding(7.dp)) {
                    createProfileImage(modifier = Modifier.size(100.dp))
                    Column( modifier = Modifier
                        .padding(7.dp)
                        .align(alignment = Alignment.CenterVertically)) {

                        Text(text = item, fontWeight = FontWeight.Bold)
                        Text(text = "A great Project",
                            style = MaterialTheme.typography.body2)
                    }

                }

            }

        }
    }
}
