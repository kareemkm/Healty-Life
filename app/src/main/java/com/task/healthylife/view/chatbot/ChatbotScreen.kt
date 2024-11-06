package com.task.healthylife.view.chatbot

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.IconButton
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.TextField
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.task.healthylife.R
import com.task.healthylife.model.Message
import com.task.healthylife.viewModel.ChatbotViewModel

@Composable
fun ChatbotScreen(viewModel: ChatbotViewModel = viewModel(), navController: NavController) {

    val chatMessages by viewModel.chatMessages.collectAsState()
    var userInput by remember { mutableStateOf("") }
    val listState = rememberLazyListState()

    LaunchedEffect(chatMessages.size) {
        if (chatMessages.isNotEmpty()) {
            listState.animateScrollToItem(chatMessages.size - 1)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFFFD188).copy(alpha = 0.2f),
                        Color(0xFF3BA170).copy(alpha = 0.2f)
                    )
                )
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .padding(top = 40.dp, bottom = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .clip(shape = CircleShape)
                    .border(width = 1.dp, color = Color(0xFFE3E5E5), shape = CircleShape)
                    .clickable {
                        navController.navigate("main") {
                            popUpTo("chatbot") { inclusive = true }
                        }
                    },
                contentAlignment = Alignment.Center

            ) {
                Icon(
                    painter = painterResource(R.drawable.back),
                    contentDescription = null,
                    tint = Color(0xFF72777A)
                )
            }
            Box(
                modifier = Modifier
                    .padding(start = 40.dp)
                    .size(50.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.chatbot),
                    contentDescription = null,
                    tint = Color(0xFF3BA170),
                    modifier = Modifier.fillMaxSize()
                )
            }
            Column(
                modifier = Modifier
                    .padding(start = 10.dp)
                    .height(50.dp)
                    .padding(top = 3.dp)
                    .weight(1f)
            ) {
                Text(
                    text = "FitBot",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Row(
                    modifier = Modifier,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .padding(top = 4.dp)
                            .size(8.dp)
                            .clip(shape = CircleShape)
                            .background(Color(0xFF7DDE86))
                    )
                    Text(
                        text = " Always active",
                        fontSize = 10.sp,
                        color = Color(0xFF72777A)
                    )
                }
            }
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .clip(shape = CircleShape)
                    .border(width = 1.dp, color = Color(0xFFE3E5E5), shape = CircleShape)
                    .clickable { },
                contentAlignment = Alignment.Center

            ) {
                Icon(
                    painter = painterResource(R.drawable.menu_meatballs),
                    contentDescription = null,
                    tint = Color(0xFF72777A)
                )
            }
        }
        LazyColumn(
            state = listState,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            verticalArrangement = Arrangement.Bottom
        ) {
            if (chatMessages.isEmpty()){
                item {
                    Box(
                        modifier = Modifier
                            .padding(horizontal = 80.dp)
                            .padding(bottom = 130.dp)
                            .fillMaxWidth()
                    ) {
                        Image(
                            painter = painterResource(R.drawable.no_data),
                            contentDescription = null
                        )
                    }
                }
            }else{
                items(chatMessages) { message ->
                    MessageCard(message = message)
                }
            }
        }

        Row(
            modifier = Modifier
                .padding(horizontal = 30.dp)
                .padding(top = 10.dp, bottom = 30.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(
                value = userInput,
                onValueChange = { userInput = it },
                shape = RoundedCornerShape(30.dp),
                trailingIcon = {
                    Icon(
                        painter = painterResource(R.drawable.microphone_1),
                        contentDescription = null,
                        tint = Color(0xFF979C9E),
                        modifier = Modifier
                            .padding(end = 15.dp)
                    )
                },
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    backgroundColor = Color.Transparent,
                    textColor = Color.Black,
                    cursorColor = Color(0xFF979C9E)

                ),
                placeholder = {
                    Text(
                        text = "Type a message...",
                        color = Color(0xFF979C9E),
                        fontSize = 16.sp
                    )
                },
                modifier = Modifier
                    .border(
                        width = 2.dp,
                        color = Color(0xFF979C9E),
                        shape = RoundedCornerShape(30.dp)
                    )
                    .weight(1f)
            )
            IconButton(
                modifier = Modifier
                    .padding(start = 20.dp)
                    .clip(shape = CircleShape)
                    .shadow(elevation = 22.dp)
                    .background(Color(0xFFFFD188)),
                onClick = {

                    viewModel.sendMessage(userInput)
                    userInput = ""
                }
            ) {
                Icon(
                    painter = painterResource(R.drawable.send_2),
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier
                        .padding(10.dp)
                        .size(35.dp)
                )
            }
        }

    }
}

@Composable
fun MessageCard(message: Message) {
    if (message.role == "user") {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp)
                .padding(end = 20.dp, start = 60.dp)
        ) {
            Box(
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(20.dp))
                    .background(Color(0xFF3BA170).copy(alpha = 0.2f))
                    .padding(10.dp)
                    .align(Alignment.CenterEnd)
            ) {
                Text(
                    text = message.content,
                    fontSize = 16.sp,
                    color = Color(0xFF303437)
                )
            }
        }
    } else {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp)
                .padding(end = 50.dp, start = 20.dp)
        ) {
            Row(
                verticalAlignment = Alignment.Top
            ) {
                Icon(
                    painter = painterResource(R.drawable.chatbot),
                    contentDescription = null,
                    tint = Color(0xFF3BA170)
                )
                Box(
                    modifier = Modifier
                        .clip(
                            shape = RoundedCornerShape(
                                bottomEnd = 20.dp,
                                bottomStart = 20.dp,
                                topEnd = 20.dp
                            )
                        )
                        .background(Color(0xFFFFD188).copy(0.2f))
                        .padding(10.dp)
                ) {
                    Text(
                        text = message.content,
                        fontSize = 16.sp,
                        color = Color(0xFF3BA170)
                    )
                }
            }
        }
    }
}
