package com.vinayak.mydemoapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
//import androidx.compose.material3.FabPosition
import androidx.compose.material.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.NavigationBarItem
//import androidx.compose.material3.Scaffold
import androidx.compose.material.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vinayak.mydemoapp.finance_tracker.WalletDashboardFullScreen
import com.vinayak.mydemoapp.ui.theme.MyDemoAppTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WalletAppTheme {
                WalletScaffold()
            }
        }
    }
}

@Composable
fun WalletScaffold(
//    viewModel: WalletViewModel = viewModel()
) {
    val navItems = listOf("Home", "Activity", "Wallet", "Profile")
    val selectedIndex = remember { mutableIntStateOf(0) }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /* Add action */ },
                containerColor = Color(0xFF6E56CF),
                contentColor = Color.White,
                shape = CircleShape
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        },
        floatingActionButtonPosition = FabPosition.Center,
        isFloatingActionButtonDocked = true,
        bottomBar = {
            BottomAppBar (
                modifier = Modifier.height(80.dp),
                backgroundColor = Color(0xFFDEDDDD),
                elevation = 6.dp,
                cutoutShape = CircleShape
            ) {
                navItems.forEachIndexed { index, item ->
                    val icon = when (item) {
                        "Home" -> Icons.Default.Home
                        "Activity" -> Icons.Default.Create
                        "Wallet" -> Icons.Default.MailOutline
                        else -> Icons.Default.Person
                    }
                    NavigationBarItem(
                        icon = { Icon(icon, contentDescription = item) },
                        label = { Text(item) },
                        selected = selectedIndex.intValue == index,
                        onClick = { selectedIndex.intValue = index }
                    )
                }
            }
        },
        content = { padding ->
            WalletDashboardFullScreen()
        }
    )
}

@Composable
fun WalletAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) {
        darkColorScheme(
            primary = Color(0xFFBB86FC),
            background = Color(0xFF121212),
            surface = Color(0xFF1E1E1E)
        )
    } else {
        lightColorScheme(
            primary = Color(0xFF6E56CF),
            background = Color.White,
            surface = Color(0xFFF5F5F5)
        )
    }

    MaterialTheme(
        colorScheme = colorScheme,
//        typography = Typography(),
        content = content
    )
}

const val supermanDescription = """Superman is one of the most iconic and enduring superheroes in popular culture. Created by Jerry Siegel and Joe Shuster, he first appeared in Action Comics #1 in 1938 and quickly became a symbol of hope, justice, and heroism. Born as Kal-El on the doomed planet Krypton, he was sent to Earth as an infant, where he was raised by the Kent family in Smallville and given the name Clark Kent.
    """

val superList = List(20) {

//    Superhero(
//        name = "Superman",
//        description = supermanDescription
//    )
}

@Composable
fun SuperheroList(
    modifier: Modifier = Modifier,
) {
    var toShowDetailPage by remember {
        mutableStateOf(false)
    }

    if(!toShowDetailPage) {
        LazyColumn(
            state = rememberLazyListState()
        ) {
            itemsIndexed(superList) { index, item ->
                SuperheroCard(
                    onClick = {
                        toShowDetailPage = true
//                    SuperHeroDetailPage()
                    }
                )
            }
        }
    } else {
        SuperHeroDetailPage(
            onBackClick = {
                Log.d("CLICKED","BACK_CLICKED")
                toShowDetailPage = false
            }
        )
    }
}

data class Superhero(
    val name: String = "THE SUPERMAN",
    val description: String = supermanDescription,
    val image: Int? = R.drawable.superman
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SuperHeroDetailPage(
    superhero: Superhero = Superhero(),
    onBackClick: () -> Unit
) {

    val scope = rememberCoroutineScope()

//    Scaffold(
//        topBar = {
//            CenterAlignedTopAppBar(
//                title = { Text(text = "THE SUPERMAN") },
//                navigationIcon = {
//                    IconButton(onClick = { onBackClick.invoke() }) {
//                        Icon(
//                            modifier = Modifier
//                                .width(95.dp)
//                                .height(75.dp),
//                            imageVector = Icons.Default.ArrowBack,
//                            contentDescription = "Back"
//                        )
//                    }
//                }
//            )
//        }
//    ) { innerPadding ->
    Column {

//        CenterAlignedTopAppBar(
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(150.dp)
//                .border(width = 2.dp, color = Color.Black),
//            title = { Text(text = "THE SUPERMAN", fontSize = 22.sp, modifier = Modifier.clickable {
//                onBackClick()
//            }) },
//            navigationIcon = {
//                IconButton(onClick = { onBackClick() }) {
//                    Icon(
//                        modifier = Modifier
//                            .width(95.dp)
//                            .height(55.dp),
//                        imageVector = Icons.Default.ArrowBack,
//                        contentDescription = "Back"
//                    )
//                }
//            }
//        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 50.dp, start = 10.dp)
                .height(150.dp)
        ) {
            IconButton(onClick = { onBackClick() }) {
                Icon(
                    modifier = Modifier
                        .width(95.dp)
                        .height(55.dp),
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back"
                )
            }
            Text(modifier = Modifier.padding(top = 10.dp, start = 30.dp).fillMaxWidth(), text = "THE SUPERMAN", fontSize = 22.sp)
        }

        SuperheroSheetContent(
            modifier = Modifier,
//                .padding(innerPadding)
//                .padding(vertical = 100.dp),
            superhero = superhero
        )

    }

//    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SuperheroSheetContent(
    modifier: Modifier = Modifier,
    superhero: Superhero
) {
    val scope = rememberCoroutineScope()
    val sheetState = rememberModalBottomSheetState()

    Column(
        modifier = Modifier
            .padding(top = 30.dp)
//            .border(width = 2.dp, color = Color.Green),
    ) {
        Image(
            modifier = Modifier,
            painter = painterResource(superhero.image ?: R.drawable.superman),
            contentDescription = ""
        )

        Box(
            modifier = Modifier.fillMaxHeight(0.8F)
        ) {
            ModalBottomSheet(
                modifier = Modifier
//                    .wrapContentHeight()
//                    .offset(y=60.dp)
                ,
                onDismissRequest = {
                    scope.launch {
                        sheetState.hide()
                    }
                },
                sheetState = sheetState
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(
                            vertical = 30.dp,
                            horizontal = 18.dp
                        ),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = superhero.name,
                        fontSize = 19.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(Modifier.height(15.dp))
                    Text(
                        text = superhero.description,
                        fontSize = 15.sp,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }

    }


}

@Composable
fun SuperheroCard(
    modifier: Modifier = Modifier,
    supermanDesc: String = supermanDescription,
    onClick: (String) -> Unit = {}
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable { onClick("detailId") },
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(R.drawable.superman),
                contentDescription = "Superman",
                modifier = Modifier
                    .size(80.dp)
                    .padding(end = 12.dp)
            )

            VerticalDivider(
                modifier = Modifier
                    .width(1.dp)
                    .height(70.dp),
                color = Color.Black
            )

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 8.dp)
            ) {
                Text(
                    text = "The Superman".uppercase(),
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                ExpandableDescriptionText(text = supermanDesc)
            }
        }
    }
}




@Composable
fun ExpandableDescriptionText(
    text: String,
    minimizedMaxLines: Int = 3
) {
    var expanded by remember { mutableStateOf(false) }
    var isTextOverflowing by remember { mutableStateOf(false) }

    // Used to measure text overflow
    val textLayoutResult = remember { mutableStateOf<TextLayoutResult?>(null) }

    Column(
        modifier = Modifier
            .animateContentSize(
                animationSpec = tween(
                    durationMillis = 300,
                    easing = FastOutSlowInEasing
                )
            )
    ) {
        Text(
            text = text,
            maxLines = if (expanded) Int.MAX_VALUE else minimizedMaxLines,
            onTextLayout = { layoutResult ->
                isTextOverflowing = layoutResult.hasVisualOverflow
                textLayoutResult.value = layoutResult
            },
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.bodyLarge
        )

        if (isTextOverflowing || expanded) {
            Text(
                text = if (expanded) "Read less" else "Read more",
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .padding(top = 4.dp)
                    .clickable { expanded = !expanded },
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold)
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyDemoAppTheme {
//        SuperheroCard(
//
//        )
    }
}