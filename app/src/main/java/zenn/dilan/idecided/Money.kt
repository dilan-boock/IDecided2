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

class Money : AppCompatActivity() {
    private lateinit var mPlayer: MediaPlayer
    private var isSoundEnabled = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_money)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val button_active: Button = findViewById<Button>(R.id.but_m)
        val button_sound: ImageButton = findViewById<ImageButton>(R.id.sound_m)
        val button_home: TextView = findViewById<TextView>(R.id.home_m)
        mPlayer= MediaPlayer.create(this, R.raw.sound_money)

        button_sound.setImageResource(R.drawable.sound_on)
        button_active.setOnClickListener(this::shakeAnimations)
        button_sound.setOnClickListener(this::toggleSound)
        button_home.setOnClickListener(this::button_homeClick)

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
        val button_active: Button = findViewById<Button>(R.id.but_m)

        val image: ImageView = findViewById<ImageView>(R.id.money)

        val random = (0..3).random()

        val rotationAnimator = ObjectAnimator.ofFloat(image, "rotation", 0f, 360f)
        image.setImageResource(R.drawable.money)
        rotationAnimator.setDuration(200)
        rotationAnimator.repeatCount = 20

        rotationAnimator.addUpdateListener { // Задаем обновление угла вращения в каждом шаге анимации
            val animatedValue = it.animatedValue as Float
            image.rotation = animatedValue
        }

        rotationAnimator.addListener(object : AnimatorListenerAdapter() { // Установите слушатель анимации для кнопки
            override fun onAnimationEnd(animation: Animator) {
                super.onAnimationEnd(animation)
                mPlayer.stop()
                GlobalScope.launch {
                    delay(400)

                }
                if (random == 0 || random == 1){
                    image.setImageResource(R.drawable.money_yes)
                }
                else {
                    image.setImageResource(R.drawable.money_no)
                }
                button_active.isEnabled = true // Активируйте кнопку после завершения анимации
                button_active.isSelected = false
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