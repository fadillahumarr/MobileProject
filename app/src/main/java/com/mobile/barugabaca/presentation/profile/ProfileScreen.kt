package com.mobile.barugabaca.presentation.profile

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.mobile.barugabaca.R
import com.mobile.barugabaca.presentation.navigation.Routes
import com.mobile.barugabaca.ui.theme.PrimaryColor
import com.mobile.barugabaca.ui.theme.QuarternaryColor
import com.mobile.barugabaca.ui.theme.TertiaryColor

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ProfileScreen(navController: NavController) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            // Top Bar
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(
                        elevation = 4.dp,
                        shape = RoundedCornerShape(0.dp),
                        clip = false
                    )
                    .background(Color.White)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Profil",
                        style = TextStyle(
                            color = PrimaryColor,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
            }
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(listOf(Color.White, PrimaryColor))
                    )
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box() {
                        Image(
                            painter = painterResource(id = R.drawable.profilepicture),
                            contentDescription = "Profile Picture",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(100.dp)
                                .clip(CircleShape)
                        )
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .size(24.dp)
                                .clip(CircleShape)
                                .background(PrimaryColor)
                                .align(Alignment.BottomEnd)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Edit,
                                contentDescription = "Edit Profile Picture",
                                tint = Color.White,
                                modifier = Modifier.size(16.dp)
                            )
                        }
                    }
                    Text(
                        text = "Arifah Deswina",
                        style = TextStyle(
                            color = PrimaryColor,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        ),
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                ) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth(),
                        colors = CardColors(
                            containerColor = TertiaryColor,
                            contentColor = Color.White,
                            disabledContentColor = TertiaryColor,
                            disabledContainerColor = TertiaryColor,
                        ),
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = "Detail Pribadi",
                                style = TextStyle(
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold
                                ),
                            )
                            Column(
                                verticalArrangement = Arrangement.spacedBy(4.dp),
                                modifier = Modifier.padding(16.dp)
                            ) {
                                Column() {
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.Phone,
                                            contentDescription = "Phone Number",
                                            tint = Color.White,
                                            modifier = Modifier.size(16.dp)
                                        )
                                        Spacer(modifier = Modifier.width(8.dp))
                                        Text(
                                            text = "Nomor Telepon",
                                            style = TextStyle(
                                                fontSize = 16.sp,
                                                fontWeight = FontWeight.SemiBold
                                            ),
                                        )
                                    }
                                    Text(
                                        text = "+62 1234566789",
                                        style = TextStyle(
                                            fontSize = 14.sp,
                                            fontWeight = FontWeight.Normal
                                        ),
                                        modifier = Modifier.padding(top = 4.dp)
                                    )
                                }
                                Column() {
                                    Column() {
                                        Row(
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            Icon(
                                                imageVector = Icons.Default.Email,
                                                contentDescription = "Email",
                                                tint = Color.White,
                                                modifier = Modifier.size(16.dp)
                                            )
                                            Spacer(modifier = Modifier.width(8.dp))
                                            Text(
                                                text = "Email",
                                                style = TextStyle(
                                                    fontSize = 16.sp,
                                                    fontWeight = FontWeight.SemiBold
                                                ),
                                            )
                                        }
                                        Text(
                                            text = "arifahdeswinaeak@gmail.com",
                                            style = TextStyle(
                                                fontSize = 14.sp,
                                                fontWeight = FontWeight.Normal
                                            ),
                                            modifier = Modifier.padding(top = 4.dp)
                                        )
                                    }
                                }
                                Column() {
                                    Column() {
                                        Row(
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            Icon(
                                                imageVector = Icons.Default.DateRange,
                                                contentDescription = "Birthdate",
                                                tint = Color.White,
                                                modifier = Modifier.size(16.dp)
                                            )
                                            Spacer(modifier = Modifier.width(8.dp))
                                            Text(
                                                text = "Tanggal Lahir",
                                                style = TextStyle(
                                                    fontSize = 16.sp,
                                                    fontWeight = FontWeight.SemiBold
                                                ),
                                            )
                                        }
                                        Text(
                                            text = "3 Desember 2004",
                                            style = TextStyle(
                                                fontSize = 14.sp,
                                                fontWeight = FontWeight.Normal
                                            ),
                                            modifier = Modifier.padding(top = 4.dp)
                                        )
                                    }
                                }
                            }
                        }
                    }
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp),
                        colors = CardColors(
                            containerColor = TertiaryColor,
                            contentColor = Color.White,
                            disabledContentColor = TertiaryColor,
                            disabledContainerColor = TertiaryColor,
                        ),
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        ) {
                            Text(
                                text = "Keanggotaan Perpustaakaan",
                                style = TextStyle(
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold
                                ),
                            )
                            Column(
                                verticalArrangement = Arrangement.spacedBy(4.dp),
                                modifier = Modifier.padding(16.dp),
                            ) {
                                Column() {
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Image(
                                            painter = painterResource(id = R.drawable.card1),
                                            contentDescription = "Library Card",
                                            modifier = Modifier.size(60.dp)
                                        )
                                        Spacer(modifier = Modifier.width(16.dp))
                                        Column() {
                                            Text(
                                                text = "Kartu Pepustakaan CSA",
                                                style = TextStyle(
                                                    fontSize = 14.sp,
                                                    fontWeight = FontWeight.SemiBold
                                                ),
                                            )
                                            Button(
                                                onClick = {},
                                                modifier = Modifier
                                                    .padding(top = 4.dp)
                                                    .height(30.dp),
                                                colors = ButtonColors(
                                                    containerColor = QuarternaryColor,
                                                    contentColor = Color.White,
                                                    disabledContentColor = QuarternaryColor,
                                                    disabledContainerColor = QuarternaryColor,
                                                )
                                            ) {
                                                Text(
                                                    text = "Lihat Kartu",
                                                    style = TextStyle(
                                                        fontSize = 10.sp,
                                                        fontWeight = FontWeight.Normal
                                                    ),
                                                )
                                                Spacer(modifier = Modifier.width(4.dp))
                                                Icon(
                                                    imageVector = Icons.AutoMirrored.Filled.ExitToApp,
                                                    contentDescription = "Go to link",
                                                    tint = Color.White,
                                                    modifier = Modifier.size(16.dp)
                                                )
                                            }
                                        }
                                    }
                                }
                                Column() {
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Image(
                                            painter = painterResource(id = R.drawable.card2),
                                            contentDescription = "Library Card",
                                            modifier = Modifier.size(60.dp)
                                        )
                                        Spacer(modifier = Modifier.width(16.dp))
                                        Column() {
                                            Text(
                                                text = "Kartu Perpustakaan UIN",
                                                style = TextStyle(
                                                    fontSize = 14.sp,
                                                    fontWeight = FontWeight.SemiBold
                                                ),
                                            )
                                            Button(
                                                onClick = {},
                                                modifier = Modifier
                                                    .padding(top = 4.dp)
                                                    .height(30.dp),
                                                colors = ButtonColors(
                                                    containerColor = QuarternaryColor,
                                                    contentColor = Color.White,
                                                    disabledContentColor = QuarternaryColor,
                                                    disabledContainerColor = QuarternaryColor,
                                                )
                                            ) {
                                                Text(
                                                    text = "Lihat Kartu",
                                                    style = TextStyle(
                                                        fontSize = 10.sp,
                                                        fontWeight = FontWeight.Normal
                                                    ),
                                                )
                                                Spacer(modifier = Modifier.width(4.dp))
                                                Icon(
                                                    imageVector = Icons.AutoMirrored.Filled.ExitToApp,
                                                    contentDescription = "Go to link",
                                                    tint = Color.White,
                                                    modifier = Modifier.size(16.dp)
                                                )
                                            }
                                        }
                                    }
                                }
                                Column(
                                    modifier = Modifier.padding(vertical = 8.dp)
                                ) {
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Image(
                                            painter = painterResource(id = R.drawable.card3),
                                            contentDescription = "Library Card",
                                            modifier = Modifier.size(60.dp)
                                        )
                                        Spacer(modifier = Modifier.width(16.dp))
                                        Column() {
                                            Text(
                                                text = "Kartu Perpustakaan UNM",
                                                style = TextStyle(
                                                    fontSize = 14.sp,
                                                    fontWeight = FontWeight.SemiBold
                                                ),
                                            )
                                            Button(
                                                onClick = {},
                                                modifier = Modifier
                                                    .padding(top = 4.dp)
                                                    .height(30.dp),
                                                colors = ButtonColors(
                                                    containerColor = QuarternaryColor,
                                                    contentColor = Color.White,
                                                    disabledContentColor = QuarternaryColor,
                                                    disabledContainerColor = QuarternaryColor,
                                                )
                                            ) {
                                                Text(
                                                    text = "Lihat Kartu",
                                                    style = TextStyle(
                                                        fontSize = 10.sp,
                                                        fontWeight = FontWeight.Normal
                                                    ),
                                                )
                                                Spacer(modifier = Modifier.width(4.dp))
                                                Icon(
                                                    imageVector = Icons.AutoMirrored.Filled.ExitToApp,
                                                    contentDescription = "Go to link",
                                                    tint = Color.White,
                                                    modifier = Modifier.size(16.dp)
                                                )
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    Column() {
                        Button(
                            onClick = {
                                // Navigasi ke Login
                                navController.navigate(Routes.Login.route)
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 16.dp),
                            colors = ButtonColors(
                                containerColor = QuarternaryColor,
                                contentColor = Color.White,
                                disabledContentColor = QuarternaryColor,
                                disabledContainerColor = QuarternaryColor,
                            )
                        ) {
                            Text(
                                text = "Keluar",
                                style = TextStyle(
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold
                                ),
                            )
                        }
                    }
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun PreviewProfileScreen() {
    ProfileScreen(navController = rememberNavController())
}