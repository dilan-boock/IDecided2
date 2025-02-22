package zenn.dilan.idecided

import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Locale

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val but_quadrat: Button = findViewById<Button>(R.id.but_quadrat)
        val but_list: Button = findViewById<Button>(R.id.but_list)
        val but_money: Button = findViewById<Button>(R.id.but_money)
        val but_fortuna: Button = findViewById<Button>(R.id.but_fortuna)
        val but_taro: Button = findViewById<Button>(R.id.but_taro)
        val but_dnd: Button = findViewById<Button>(R.id.but_dnd)
        val but_about: Button = findViewById<Button>(R.id.but_about)

        val info_quadrat: ImageButton = findViewById<ImageButton>(R.id.info_quadrat)
        val info_list: ImageButton = findViewById<ImageButton>(R.id.info_list)
        val info_money: ImageButton = findViewById<ImageButton>(R.id.info_money)
        val info_fortuna: ImageButton = findViewById<ImageButton>(R.id.info_fortuna)
        val info_taro: ImageButton = findViewById<ImageButton>(R.id.info_taro)
        val info_dnd: ImageButton = findViewById<ImageButton>(R.id.info_dnd)
        val info_about: ImageButton = findViewById<ImageButton>(R.id.info_about)

        val but_local_ru: Button = findViewById<Button>(R.id.languageRu)
        val but_local_en: Button = findViewById<Button>(R.id.languageEn)

        but_quadrat.setOnClickListener(this::onbutQuadratClick)
        but_list.setOnClickListener(this::onbutListClick)
        but_money.setOnClickListener(this::onbutMoneyClick)
        but_fortuna.setOnClickListener(this::onbutFortunaClick)
        but_taro.setOnClickListener(this::onbutTaroClick)
        but_dnd.setOnClickListener(this::onbutDndClick)
        but_about.setOnClickListener(this::onbutAboutClick)

        info_quadrat.setOnClickListener(this::onbutInfoClick)
        info_list.setOnClickListener(this::onbutInfoClick)
        info_money.setOnClickListener(this::onbutInfoClick)
        info_fortuna.setOnClickListener(this::onbutInfoClick)
        info_taro.setOnClickListener(this::onbutInfoClick)
        info_dnd.setOnClickListener(this::onbutInfoClick)
        info_about.setOnClickListener(this::onbutInfoClick)

        val configuration = resources.configuration

        // Получаем текущую локаль
        val currentLocale = configuration.locales[0]

        if (currentLocale.language == "en") {
            but_local_ru.isSelected = false
            but_local_ru.isEnabled = true
            but_local_en.isSelected = true
            but_local_en.isEnabled = false
        } else {
            but_local_ru.isSelected = true
            but_local_ru.isEnabled = false
            but_local_en.isSelected = false
            but_local_en.isEnabled = true
        }

        but_local_ru.setOnClickListener(this::onbutLocalRu)
        but_local_en.setOnClickListener(this::onbutLocalEn)

    }

    private fun onbutQuadratClick(view: View?) {
        val intent = Intent(this, Quadrat::class.java)
        startActivity(intent)
    }
    private fun onbutListClick(view: View?) {
        val intent = Intent(this, List::class.java)
        startActivity(intent)
    }
    private fun onbutMoneyClick(view: View?) {
        val intent = Intent(this, Money::class.java)
        startActivity(intent)
    }
    private fun onbutFortunaClick(view: View?) {
        val intent = Intent(this, Fortuna::class.java)
        startActivity(intent)
    }
    private fun onbutTaroClick(view: View?) {
        val intent = Intent(this, Taro::class.java)
        startActivity(intent)
    }
    private fun onbutDndClick(view: View?) {
        val intent = Intent(this, Dnd::class.java)
        startActivity(intent)
    }
    private fun onbutAboutClick(view: View?) {
        val intent = Intent(this, About::class.java)
        startActivity(intent)
    }
    private fun onbutLocalRu(view: View?) {
        val newLocale = Locale("default") // Здесь указывается желаемая локаль, например "en" для английского языка
        val configuration = getResources().getConfiguration()
        configuration.locale = newLocale
        getResources().updateConfiguration(configuration, getResources().getDisplayMetrics())
        recreate()
    }
    private fun onbutLocalEn(view: View?) {
        val newLocale = Locale("en") // Здесь указывается желаемая локаль, например "en" для английского языка
        val configuration = getResources().getConfiguration()
        configuration.locale = newLocale
        getResources().updateConfiguration(configuration, getResources().getDisplayMetrics())
        recreate()
    }
    private fun onbutInfoClick(view: View?) {
        view?.let {
            val buttonId = it.id // Получаем ID кнопки

        }
    }
}