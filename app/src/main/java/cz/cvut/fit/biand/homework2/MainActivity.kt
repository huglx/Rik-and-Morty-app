package cz.cvut.fit.biand.homework2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import cz.cvut.fit.biand.homework2.navigation.Navigation
import cz.cvut.fit.biand.homework2.ui.theme.Homework2Theme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Homework2Theme {
                Navigation()
            }
        }
    }
}
