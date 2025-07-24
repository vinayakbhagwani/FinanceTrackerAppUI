package com.vinayak.mydemoapp.finance_tracker

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.LocalDate
import java.util.Calendar

import android.app.DatePickerDialog
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.IconButton
import androidx.compose.runtime.*
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun WalletDashboardFullScreen() {

    Column(
        modifier = Modifier
            .fillMaxSize()
//            .verticalScroll(rememberScrollState())
            .background(Color.White)
    ) {
        Spacer(Modifier.height(20.dp))
        WalletHeader(name = "Siyam Ahmed", balance = "$4,570.80")
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {
            item {
                UpcomingPaymentsSection()
            }
            item {
                DonutChartSection()
            }
            item {
                RecentTransactionsSection()
            }
            item {
                QuickMenuSection()
            }
            item {
                Spacer(Modifier.height(100.dp))
            }
        }
    }

}

@Composable
fun WalletHeader(name: String, balance: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(16.dp)
    ) {
        Text("Hello,", style = MaterialTheme.typography.bodyLarge)
        Text(name, style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold))

        Spacer(modifier = Modifier.height(16.dp))

        Card(
            colors = CardDefaults.cardColors(containerColor = Color(0xFFEAEAFF)),
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text("Current Balance", color = Color.Gray)
                    Text(balance, style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold))
                }
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        }
    }
}


@Composable
fun UpcomingPaymentsSection() {
    Column(modifier = Modifier.padding(16.dp)) {
        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
            Text("Upcoming payment", style = MaterialTheme.typography.titleMedium)
            Text("See all", color = Color.Gray, fontSize = 12.sp)
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row {
            PaymentCard("Adobe Premium", "$30/month", "2 days left", Color(0xFF6750A4))
            Spacer(modifier = Modifier.width(8.dp))
            PaymentCard("Apple Premium", "$30/month", "2 days left", Color(0xFFBBBABA), icon = Icons.Default.Info)
        }
    }
}

@Composable
fun PaymentCard(title: String, amount: String, daysLeft: String, backgroundColor: Color, icon: ImageVector = Icons.Default.Settings) {
    Card(
        colors = CardDefaults.cardColors(containerColor = backgroundColor),
        modifier = Modifier
            .width(160.dp)
            .height(100.dp)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Icon(icon, contentDescription = title, tint = Color.White, modifier = Modifier.size(24.dp))
            Text(title, color = Color.White)
            Text(amount, color = Color.White, fontSize = 12.sp)
            Text(daysLeft, color = Color.White, fontSize = 10.sp)
        }
    }
}

@Composable
fun RecentTransactionsSection() {
    Column(modifier = Modifier.padding(16.dp)) {
        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
            Text("Recent Transactions", style = MaterialTheme.typography.titleMedium)
            Text("See all", color = Color.Gray, fontSize = 12.sp)
        }

        Spacer(modifier = Modifier.height(8.dp))

        TransactionItem("Apple Inc.", "-$230.50", "21 Sep, 03:02 PM")
        TransactionItem("Adobe", "-$130.50", "21 Sep, 03:22 PM")
        TransactionItem("Amazon", "-$20.50", "21 Sep, 02:02 PM")
    }
}

@Composable
fun TransactionItem(title: String, amount: String, time: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(title, fontWeight = FontWeight.Bold)
            Text(time, fontSize = 12.sp, color = Color.Gray)
        }
        Text(amount, color = Color.Red, fontWeight = FontWeight.Bold)
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DonutChartSection() {
    var showDatePicker by remember { mutableStateOf(false) }
    var selectedDate by remember { mutableStateOf<LocalDate?>(null) }
//    var animateValue by remember { mutableStateOf(false) }

    Column(modifier = Modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Row(
            Modifier
                .fillMaxWidth()
            ,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Activity", style = MaterialTheme.typography.titleMedium)
            IconButton(onClick = { showDatePicker = true }) {
                Icon(Icons.Default.DateRange, contentDescription = "Pick Date")
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Card(
            modifier = Modifier
                .fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFEAEAFF)),
            shape = RoundedCornerShape(16.dp)
        ) {
            DonutChart(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .width(200.dp)
                    .height(200.dp)
                    .padding(24.dp),
                values = listOf(30f, 20f, 15f, 35f),
                colors = listOf(
                    Color(0xFF6E56CF),
                    Color(0xFFBFAAFF),
                    Color(0xFF3F3D56),
                    Color(0xFF85C1E9)
                ),
//                animate = animateValue
            )
        }

        if (showDatePicker) {
            DatePickerDialogCompose(
                onDateSelected = {
                    selectedDate = it
                    showDatePicker = false
//                    animateValue = true
                },
                onDismiss = {
                    showDatePicker = false
                }
            )
        }

        selectedDate?.let {
            Text(
                text = "Selected: ${it.format(DateTimeFormatter.ofPattern("dd MMM yyyy"))}",
                modifier = Modifier.padding(start = 16.dp)
            )
        }
    }
}

@Composable
fun DonutChart(
    modifier: Modifier = Modifier,
    values: List<Float>,
    colors: List<Color>,
    savingsText: String = "$2,482",
    label: String = "Your savings",
    animate: Boolean = true
) {
    val total = values.sum()

    // Track animation progress with key(date)
//    val animatedSweepAngles = values.map { value ->
//        val targetSweep = (value / total) * 360f
//        val animated by animateFloatAsState(
//            targetValue = if (animate) targetSweep else targetSweep,
//            animationSpec = tween(durationMillis = 1000, easing = LinearOutSlowInEasing),
//            label = "ArcSweep"
//        )
//        animated
//    }

    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            var startAngle = -90f
            values.forEachIndexed { index, value ->
                val sweepAngle = (value / total) * 360f
                drawArc(
                    color = colors.getOrElse(index) { Color.Gray },
                    startAngle = startAngle,
                    sweepAngle = sweepAngle,
                    useCenter = false,
                    style = Stroke(width = 40f, cap = StrokeCap.Round)
                )
                startAngle += sweepAngle
            }
        }

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = savingsText, fontWeight = FontWeight.Bold, fontSize = 20.sp)
            Text(text = label, fontSize = 12.sp, color = Color.Gray)
        }
    }
}


@Composable
fun QuickMenuSection() {
    Column(modifier = Modifier.padding(16.dp)) {
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Quick Menu", style = MaterialTheme.typography.titleMedium)
            Text("See all", fontSize = 12.sp, color = Color.Gray)
        }

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            QuickMenuCard("Top up wallet", Icons.Default.AddCircle)
            QuickMenuCard("Create wallet budget", Icons.Default.MailOutline)
            QuickMenuCard("Lock card", Icons.Default.Lock)
        }
    }
}

@Composable
fun QuickMenuCard(title: String, icon: ImageVector) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF2F2F2)),
        modifier = Modifier
            .size(85.dp)
//            .weight(1F)
            .aspectRatio(1f)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(12.dp)
        ) {
            Icon(icon, contentDescription = title, tint = Color(0xFF6E56CF), modifier = Modifier.size(24.dp))
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = title,
                textAlign = TextAlign.Center,
                fontSize = 12.sp
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DatePickerDialogCompose(
    onDateSelected: (LocalDate) -> Unit,
    onDismiss: () -> Unit
) {
    val context = LocalContext.current
    val calendar = Calendar.getInstance()

//    AndroidView(factory = {
        DatePickerDialog(
            context,
            { _, year, month, dayOfMonth ->
                val date = LocalDate.of(year, month + 1, dayOfMonth)
                onDateSelected(date)
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ).apply {
            setOnDismissListener { onDismiss() }
            show()
        }
//    })
}

