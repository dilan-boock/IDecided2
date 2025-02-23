package zenn.dilan.idecided

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import zenn.dilan.idecided.fragments.DialogFragmentInfo

class Taro : AppCompatActivity() {
    private lateinit var mPlayer: MediaPlayer
    private var isSoundEnabled = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_taro)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val button_active: Button = findViewById<Button>(R.id.but_t)
        val button_sound: ImageButton = findViewById<ImageButton>(R.id.sound_t)
        val button_home: TextView = findViewById<TextView>(R.id.home_t)

        button_sound.setImageResource(R.drawable.sound_on)
        button_active.setOnClickListener(this::shakeAnimations)
        button_sound.setOnClickListener(this::toggleSound)
        button_home.setOnClickListener(this::button_homeClick)

        mPlayer= MediaPlayer.create(this, R.raw.sound_taro)

    }
    private fun button_homeClick(view: View?) {
        if (mPlayer.isPlaying) {
            mPlayer.stop()
        }
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
    private fun shakeAnimations(view: View){
        val button_active: Button = findViewById<Button>(R.id.but_t)

        val image: ImageView = findViewById<ImageView>(R.id.taro)

        val rotationAnimator = ObjectAnimator.ofFloat(image, "alpha", 0f, 1f)
        rotationAnimator.setDuration(400)
        rotationAnimator.repeatCount = 10

        rotationAnimator.addListener(object : AnimatorListenerAdapter() { // Установите слушатель анимации для кнопки
            var currentRepeat = 0

            override fun onAnimationRepeat(animation: Animator) {
                currentRepeat++
                // Меняем ресурс изображения при каждом повторении
                when (currentRepeat) {
                    1 -> image.setImageResource(R.drawable.shirt1)
                    2 -> image.setImageResource(R.drawable.shirt2)
                    3 -> image.setImageResource(R.drawable.shirt3)
                    4 -> image.setImageResource(R.drawable.shirt4)
                    5 -> image.setImageResource(R.drawable.shirt5)
                    6 -> image.setImageResource(R.drawable.shirt6)
                    7 -> image.setImageResource(R.drawable.shirt1)
                    8 -> image.setImageResource(R.drawable.shirt2)
                    9 -> image.setImageResource(R.drawable.shirt3)
                    10 -> image.setImageResource(R.drawable.shirt4)
                    // Добавьте дополнительные ресурсы по мере необходимости
                }
            }
            override fun onAnimationEnd(animation: Animator) {
                super.onAnimationEnd(animation)
                mPlayer.stop()
                GlobalScope.launch {
                    delay(400)

                }
                button_active.isEnabled = true // Активируйте кнопку после завершения анимации
                button_active.isSelected = false
                view?.let {
                    val buttonId = it.id // Получаем ID кнопки
                    val infoFragment = DialogFragmentInfo()

                    // Создаём Bundle и добавляем ID кнопки
                    val bundle = Bundle().apply {
                        putInt("buttonId", buttonId)
                    }

                    // Устанавливаем Bundle в фрагмент
                    infoFragment.arguments = bundle

                    // Отображаем фрагмент
                    val manager = supportFragmentManager
                    infoFragment.show(manager, "infoFragment")
                }
            }
        })


        rotationAnimator.start() // Запускаем анимацию
        if (isSoundEnabled) {

        } else {
            mPlayer.start() // Запускаем звук
        }
        button_active.isEnabled = false //Выключаем кнопку, на время анимации
        button_active.isSelected = true

    }

    private fun toggleSound(view: View){
        val button_sound: ImageButton = findViewById<ImageButton>(R.id.sound_m)
        isSoundEnabled = !isSoundEnabled
        if (isSoundEnabled) {
            button_sound.setImageResource(R.drawable.sound_off) // Установите иконку звука выключенного
        } else {
            button_sound.setImageResource(R.drawable.sound_on)  // Установите иконку звука включенного
        }
    }

}