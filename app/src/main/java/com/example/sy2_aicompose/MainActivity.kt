package com.example.sy2_aicompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Camera
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sy2_aicompose.ui.theme.Blue600
import com.example.sy2_aicompose.ui.theme.Green600
import com.example.sy2_aicompose.ui.theme.Purple600
import com.example.sy2_aicompose.ui.theme.Red600
import com.example.sy2_aicompose.ui.theme.Sy2aicomposeTheme

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Sy2aicomposeTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar(
                            title = { Text("LiteRT AI Demo") },
                            actions = {
                                IconButton(onClick = {}) {
                                    Icon(Icons.Filled.MoreVert, contentDescription = "更多选项")
                                }
                            },
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = Blue600,
                                titleContentColor = Color.White
                            )
                        )
                    }
                ) { innerPadding ->
                    MainContent(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun MainContent(modifier: Modifier = Modifier) {
    var modelName by remember { mutableStateOf("MobileNet") }
    var result by remember { mutableStateOf("Cat") }
    var confidence by remember { mutableStateOf("96.2%") }
    var inferenceTime by remember { mutableStateOf("28 ms") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CameraPreviewSection()
        
        Spacer(modifier = Modifier.height(16.dp))
        
        ResultSection(
            modelName = modelName,
            result = result,
            confidence = confidence,
            time = inferenceTime
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        ButtonSection(
            onCameraClick = { },
            onGalleryClick = { },
            onSwitchModelClick = {
                modelName = if (modelName == "MobileNet") "ResNet" else "MobileNet"
            },
            onClearClick = {
                result = ""
                confidence = ""
                inferenceTime = ""
            }
        )
    }
}

@Composable
fun CameraPreviewSection() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .background(Color.LightGray)
            .border(BorderStroke(2.dp, Color.Gray), shape = RoundedCornerShape(8.dp)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                Icons.Filled.Camera,
                contentDescription = "Camera Preview",
                modifier = Modifier.size(64.dp),
                tint = Color.DarkGray
            )
            Text(
                text = "Camera Preview",
                color = Color.DarkGray,
                fontSize = 16.sp
            )
        }
    }
}

@Composable
fun ResultSection(modelName: String, result: String, confidence: String, time: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            ResultRow(label = "Model:", value = modelName)
            ResultRow(label = "Result:", value = result)
            ResultRow(label = "Confidence:", value = confidence)
            ResultRow(label = "Time:", value = time)
        }
    }
}

@Composable
fun ResultRow(label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
        )
        Text(
            text = if (value.isEmpty()) "-" else value,
            fontWeight = FontWeight.Normal,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Composable
fun ButtonSection(
    onCameraClick: () -> Unit,
    onGalleryClick: () -> Unit,
    onSwitchModelClick: () -> Unit,
    onClearClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            ActionButton(
                text = "拍照识别",
                icon = Icons.Filled.Camera,
                backgroundColor = Blue600,
                onClick = onCameraClick
            )
            ActionButton(
                text = "相册导入",
                icon = Icons.Filled.Image,
                backgroundColor = Green600,
                onClick = onGalleryClick
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            ActionButton(
                text = "切换模型",
                icon = Icons.Filled.Refresh,
                backgroundColor = Purple600,
                onClick = onSwitchModelClick
            )
            ActionButton(
                text = "清空结果",
                icon = Icons.Filled.Delete,
                backgroundColor = Red600,
                onClick = onClearClick
            )
        }
    }
}

@Composable
fun ActionButton(
    text: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    backgroundColor: Color,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth(0.5f)
            .height(56.dp),
        shape = RoundedCornerShape(8.dp),
        colors = androidx.compose.material3.ButtonDefaults.buttonColors(
            containerColor = backgroundColor,
            contentColor = Color.White
        )
    ) {
        Icon(
            icon,
            contentDescription = text,
            modifier = Modifier.size(20.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = text,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MainContentPreview() {
    Sy2aicomposeTheme {
        MainContent()
    }
}