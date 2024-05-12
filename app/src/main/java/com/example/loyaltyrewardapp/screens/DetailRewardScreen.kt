package com.example.loyaltyrewardapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Badge
import androidx.compose.material.DropdownMenu
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.loyaltyrewardapp.R


@Composable
fun InfoRewardCard(url: String, name: String, description: String, point: Int, isAdmin: Boolean = false, onClick: () -> Unit){
    var expandedMenu by remember { mutableStateOf(false) }
//    val anchorPosition = remember { mutableStateOf<Offset?>(null) }

    ElevatedCard(
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        ),
        modifier = Modifier.padding(6.dp)
            .clickable {
                onClick()
            }.widthIn(max = 200.dp)
    ) {
        Column {
            Box {
                AsyncImage(
                    model = url,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .height(140.dp)
                        .fillMaxWidth()
                )
                if (isAdmin) {

                    IconButton(
                        onClick = {
                            expandedMenu = true
                            // Tính toán vị trí của nút ba chấm
                        },
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .absolutePadding(5.dp)
                    ) {
                        Column {
                            Icon(
                                imageVector = Icons.Filled.MoreVert,
                                contentDescription = "More options"
                            )
                                DropdownMenu(
                                    expanded = expandedMenu,
                                    onDismissRequest = {
                                        expandedMenu = false
                                    },
                                ) {
                                    DropdownMenuItem(onClick = { /* Handle edit action */ },  text = {  Text("Chỉnh sửa") })
                                    DropdownMenuItem(onClick = { /* Handle edit action */ },  text = {  Text("Xóa") })
                                }


                    }

                    }
                }
                // DropdownMenu hiển thị dưới nút ba chấm

            }
            Column(modifier = Modifier.padding(8.dp)) {
                Row (){
                    Text(text = name, style = MaterialTheme.typography.titleMedium, maxLines = 1, overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.padding(vertical = 2.dp)
                    )
                }
                Row{
                    Text(text = description, style = MaterialTheme.typography.bodyMedium, color = Color.Gray,  maxLines = 2, overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.padding(vertical = 2.dp))
                }
                Row (horizontalArrangement = Arrangement.End, verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()){
                    Image(painter = painterResource(id = R.drawable.coin_icon), contentDescription = null, modifier = Modifier.size(20.dp))
                    Spacer(modifier = Modifier.size(8.dp))
                    Text(text = "$point điểm", style = MaterialTheme.typography.bodyMedium, color = Color.Red, fontWeight = FontWeight.Medium)

                }
            }
        }
    }
}



@Composable
fun DetailReward() {

}

//@Preview(showBackground = true)
//@Composable
//fun DetailRewardPreview() {
//    InfoRewardCard("URL","Tên sản phẩm", "Mô tả sản phẩm", 3, isAdmin = true)
//}