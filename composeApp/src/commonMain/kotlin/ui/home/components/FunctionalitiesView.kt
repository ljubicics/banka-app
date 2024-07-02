package ui.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import banka3_app.composeapp.generated.resources.Res
import banka3_app.composeapp.generated.resources.account_icon
import banka3_app.composeapp.generated.resources.currency_exchange_icon
import banka3_app.composeapp.generated.resources.euro_icon
import org.jetbrains.compose.resources.painterResource

@Composable
fun FunctionalitiesView(
    onNewPaymentClick: () -> Unit = {},
    onCurrencyExchangeClick: () -> Unit = {},
    onAccountsClick: () -> Unit = {}
) {
    Row(
        modifier = Modifier,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Column(
            modifier = Modifier,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .width(60.dp)
                    .height(60.dp)
                    .clip(shape = RoundedCornerShape(8.dp))
                    .background(Color.LightGray)
                    .clickable {
                        onNewPaymentClick()
                    },
                contentAlignment = Alignment.Center,
            ) {
                Icon(
                    painter = painterResource(Res.drawable.euro_icon),
                    contentDescription = null
                )
            }
            Text(
                text = "Payment",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }
        Spacer(modifier = Modifier.width(32.dp))
        Column(
            modifier = Modifier,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .width(60.dp)
                    .height(60.dp)
                    .clip(shape = RoundedCornerShape(8.dp))
                    .background(Color.LightGray)
                    .clickable {
                        onCurrencyExchangeClick()
                    },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(Res.drawable.currency_exchange_icon),
                    contentDescription = null
                )
            }
            Text(
                text = "Exchange",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }
        Spacer(modifier = Modifier.width(32.dp))
        Column(
            modifier = Modifier,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .width(60.dp)
                    .height(60.dp)
                    .clip(shape = RoundedCornerShape(8.dp))
                    .background(Color.LightGray)
                    .clickable {
                        onAccountsClick()
                    },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(Res.drawable.account_icon),
                    contentDescription = null
                )
            }
            Text(
                text = "Accounts",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }
    }
}
