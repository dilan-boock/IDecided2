package zenn.dilan.idecided

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class About : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_rasrab)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val textBoosty: TextView = findViewById<TextView>(R.id.textBoosty)
        val textTelegram: TextView = findViewById<TextView>(R.id.textTelegram)
        val button_home: TextView = findViewById<TextView>(R.id.home_a)

        textBoosty.setOnClickListener(this::button_boostyClick)
        textTelegram.setOnClickListener(this::button_telegramClick)
        button_home.setOnClickListener(this::button_homeClick)
    }
    private fun button_homeClick(view: View?) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
    private fun button_boostyClick(view: View?) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://boosty.to/dilan_zenn"))
        startActivity(intent)
    }
    private fun button_telegramClick(view: View?) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://t.me/dilanzenn"))
        startActivity(intent)
    }
}