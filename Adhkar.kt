package com.example.adhanquran

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn,
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable // الاستيراد الذي كان ناقصاً
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class DhikrItem(
    val id: Int,
    val text: String,
    val targetCount: Int,
    val category: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AthkarScreen() {
    var selectedCategory by rememberSaveable { mutableStateOf("الصباح") }

    val categories = listOf("الصباح", "المساء", "بعد الصلاة", "التسابيح")

    val allDhikr = listOf(
        DhikrItem(1, "أصبحنا وأصبح الملك لله، والحمد لله ولا إله إلا الله وحدَهُ لا شريك له.", 1, "الصباح"),
        DhikrItem(2, "آية الكرسي: اللَّهُ لَا إِلَٰهَ إِلَّا هُوَ الْحَيُّ الْقَيُّومُ...", 1, "الصباح"),
        DhikrItem(3, "أمسَيْنا وأمسَى الملك لله، والحمد لله ولا إله إلا الله وحدَهُ لا شريك له.", 1, "المساء"),
        DhikrItem(4, "أستغفر الله العظيم وأتوب إليه.", 100, "التسابيح"),
        DhikrItem(5, "سبحان الله وبحمده، سبحان الله العظيم.", 33, "التسابيح"),
        DhikrItem(6, "أستغفر الله (3 مرات بعد الصلاة)", 3, "بعد الصلاة"),
        DhikrItem(7, "اللهم أنت السلام ومنك السلام تباركت يا ذا الجلال والإكرام", 1, "بعد الصلاة")
    )

    val filteredDhikr = allDhikr.filter { it.category == selectedCategory }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "الأذكار والتسابيح",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        // شريط التبويبات للأذكار
        TabRow(selectedTabIndex = categories.indexOf(selectedCategory)) {
            categories.forEachIndexed { index, category ->
                Tab(
                    selected = selectedCategory == category,
                    onClick = { selectedCategory = category },
                    text = { Text(category, fontSize = 14.sp) }
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // قائمة الأذكار
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            itemsIndexed(filteredDhikr) { _, dhikr ->
                DhikrCard(dhikr = dhikr)
            }
        }
    }
}

@Composable
fun DhikrCard(dhikr: DhikrItem) {
    // حفظ العداد لكل ذكر بشكل مستقل
    var currentCount by rememberSaveable { mutableStateOf(0) }

    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = if (currentCount >= dhikr.targetCount) 
                MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.4f)
            else 
                MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = dhikr.text,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(bottom = 12.dp)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = {
                        if (currentCount < dhikr.targetCount) {
                            currentCount++
                        }
                    },
                    enabled = currentCount < dhikr.targetCount
                ) {
                    Text(if (currentCount >= dhikr.targetCount) "تم الإكمال ✓" else "تكرار ($currentCount / ${dhikr.targetCount})")
                }

                OutlinedButton(
                    onClick = { currentCount = 0 }
                ) {
                    Text("إعادة")
                }
            }
        }
    }
}
