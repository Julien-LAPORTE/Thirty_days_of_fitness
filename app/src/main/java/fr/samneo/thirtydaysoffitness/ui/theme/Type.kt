package fr.samneo.thirtydaysoffitness.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import fr.samneo.thirtydaysoffitness.R

val DancingScript = FontFamily(
    Font(R.font.dancingscript_regular), Font(R.font.dancingscript_bold)
)
val Satisfy = FontFamily(
    Font(R.font.satisfy_regular)
)

// Set of Material typography styles to start with
val Typography = Typography(

    // Day's number
    displayLarge = TextStyle(
        fontFamily = DancingScript,
        fontWeight = FontWeight.Bold,
        fontSize = 40.sp,
        lineHeight = 60.sp,
        letterSpacing = 0.5.sp
    ),
    // exercice title
    displayMedium = TextStyle(
        fontFamily = Satisfy,
        fontWeight = FontWeight.Normal,
        fontSize = 28.sp,
        lineHeight = 42.sp,
        letterSpacing = 0.5.sp
    ),
    // exercise instructions
    bodyMedium = TextStyle(
        fontFamily = Satisfy,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),

    // Disclaimer text
    bodyLarge = TextStyle(
        fontFamily = DancingScript,
        fontWeight = FontWeight.Normal,
        fontSize = 28.sp,
        lineHeight = 42.sp,
        letterSpacing = 0.5.sp
    )

)