package com.task.healthylife.view.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.task.healthylife.R
import com.task.healthylife.viewModel.AuthViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavController, authViewModel: AuthViewModel = viewModel()) {

    var userName by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var loginSuccess by remember { mutableStateOf(false) }
    var passwordError by rememberSaveable { mutableStateOf<String?>(null) }



    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter)
                .height(400.dp)
                .clip(RoundedCornerShape(bottomStart = 50.dp, bottomEnd = 50.dp))
                .background(Color(0xFF3BA170))

        ) {
            Spacer(modifier = Modifier.height(220.dp))
            Text(
                text = "Welcome Back",
                color = Color.White,
                fontSize = 18.sp,
                modifier = Modifier.padding(start = 50.dp)
            )
            Text(
                text = "Healthy Life",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 36.sp,
                modifier = Modifier.padding(start = 50.dp)
            )
        }
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .imePadding()
                .fillMaxWidth()
                .height(550.dp)
                .align(Alignment.BottomCenter)
                .padding(30.dp)
                .shadow(elevation = 30.dp, shape = RoundedCornerShape(20.dp))
                .clip(RoundedCornerShape(20.dp))
                .background(Color(0xFFF5F5F5))
        ) {
            Row(
                modifier = Modifier
                    .padding(15.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Login",
                    color = Color.Black,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(5.dp)
                )
                Icon(
                    painter = painterResource(R.drawable.user),
                    contentDescription = null,
                    tint = Color.Black,
                    modifier = Modifier.padding(5.dp)
                )
            }
            OutlinedTextField(
                value = userName,
                onValueChange = { userName = it },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    containerColor = Color.Transparent,
                    unfocusedTextColor = Color.Black,
                    unfocusedBorderColor = Color(0xFF3BA170),
                    focusedBorderColor = Color(0xFF3BA170),
                    focusedTextColor = Color.Black
                ),
                shape = RoundedCornerShape(10.dp),
                placeholder = {
                    Text(
                        text = "Email",
                        fontSize = 16.sp
                    )
                },
                maxLines = 1,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 7.dp)
                    .clip(RoundedCornerShape(10.dp))
            )
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                shape = RoundedCornerShape(10.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    containerColor = Color.Transparent,
                    unfocusedTextColor = Color.Black,
                    focusedTextColor = Color.Black,
                    focusedBorderColor = Color(0xFF3BA170),
                    unfocusedBorderColor = Color(0xFF3BA170),
                    errorBorderColor = Color.Red,
                    errorTextColor = Color.Black,
                    errorSupportingTextColor = Color.Red
                ),
                supportingText = { Text(passwordError.orEmpty()) },
                isError = passwordError != null,
                placeholder = {
                    Text(
                        text = "Password",
                        fontSize = 16.sp
                    )
                },
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    Icon(
                        painter = painterResource(if (passwordVisible) R.drawable.show_password else R.drawable.unshow_password),
                        contentDescription = null,
                        tint = Color.Black,
                        modifier = Modifier
                            .clickable { passwordVisible = !passwordVisible }
                    )
                },
                maxLines = 1,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 7.dp)
                    .clip(RoundedCornerShape(10.dp))
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    text = "Forget Password ?",
                    color = Color(0xFF757171),
                    fontSize = 10.sp,
                )
            }
            Button(
                onClick = {
                    authViewModel.login(
                        userName = userName,
                        password = password,
                        onResult = { success ->
                            loginSuccess = success
                            if (loginSuccess) {
                                navController.navigate("main") {
                                    popUpTo("login") { inclusive = true }
                                }
                                authViewModel.insertUser(userName = userName, password = password)
                            } else {
                                passwordError = "password is not correct"
                            }
                        }
                    )
                },
                enabled = userName.isNotBlank() && password.isNotBlank(),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF3BA170),
                    contentColor = Color.White,
                    disabledContainerColor = Color.Transparent,
                    disabledContentColor = Color(0xFF3BA170)
                ),
                modifier = Modifier
                    .padding(horizontal = 20.dp, vertical = 15.dp)
                    .fillMaxWidth()
                    .height(50.dp)
                    .border(
                        width = 1.dp,
                        color = Color(0xFF3BA170),
                        shape = RoundedCornerShape(10.dp)
                    ),
            ) {
                Text(
                    text = "Login"
                )
            }
            Row(
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                HorizontalDivider(
                    modifier = Modifier
                        .weight(1f)
                        .height(1.dp),
                    color = Color(0xFF757171)
                )
                Text(
                    text = "or login with",
                    fontSize = 12.sp,
                    modifier = Modifier
                        .padding(horizontal = 5.dp)
                )
                HorizontalDivider(
                    modifier = Modifier
                        .weight(1f)
                        .height(1.dp),
                    color = Color(0xFF757171)
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(R.drawable.google),
                    contentDescription = null,
                    modifier = Modifier.padding(10.dp)
                )
                Image(
                    painter = painterResource(R.drawable.facebook),
                    contentDescription = null,
                    modifier = Modifier.padding(10.dp)

                )
                Image(
                    painter = painterResource(R.drawable.apple),
                    contentDescription = null,
                    modifier = Modifier.padding(10.dp)

                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Don't have an account ?",
                    fontSize = 12.sp,
                    color = Color(0xFF757171)
                )
                Text(
                    text = "  SignUp",
                    color = Color.Black,
                    fontSize = 14.sp,
                    modifier = Modifier.clickable {
                        navController.navigate("signin") {
                            popUpTo("login") { inclusive = true }
                        }
                    }
                )
            }
        }
    }
}