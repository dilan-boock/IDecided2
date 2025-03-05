package zenn.dilan.idecided

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
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
import java.io.IOException

class Fortuna : AppCompatActivity() {
    private lateinit var mPlayer: MediaPlayer
    private var isSoundEnabled = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_fortuna)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val button_active: Button = findViewById<Button>(R.id.but_f)
        val button_sound: ImageButton = findViewById<ImageButton>(R.id.sound_f)
        val button_home: TextView = findViewById<TextView>(R.id.home_f)
        mPlayer= MediaPlayer.create(this, R.raw.fort)

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
        val button_active: Button = findViewById<Button>(R.id.but_f)

        val image: ImageView = findViewById<ImageView>(R.id.fortuna)

        val random = (1..8).random()
        val target = random * 45f

        val rotationAnimator = ObjectAnimator.ofFloat(image, "rotation", 0f, target)
        rotationAnimator.setDuration(200)
        rotationAnimator.repeatCount = 10

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
                button_active.isEnabled = true // Активируйте кнопку после завершения анимации
                button_active.isSelected = false
                view?.let {
                    val buttonId = it.id // Получаем ID кнопки
                    val infoFragment = DialogFragmentInfo()

                    // Создаём Bundle и добавляем ID кнопки
                    val bundle = Bundle().apply {
                        putInt("buttonId", buttonId)
                        putInt("random", random)
                    }

                    // Устанавливаем Bundle в фрагмент
                    infoFragment.arguments = bundle

                    // Отображаем фрагмент
                    val manager = supportFragmentManager
                    infoFragment.show(manager, "infoFragment")
                }
            }
        })


        // Сброс MediaPlayer перед воспроизведением
        if (mPlayer.isPlaying) {
            mPlayer.stop()
        }
        mPlayer.reset()
        mPlayer.seekTo(0) // Сброс позиции воспроизведения
        try {
            val assetFileDescriptor = resources.openRawResourceFd(R.raw.fort)
            mPlayer.setDataSource(assetFileDescriptor.fileDescriptor, assetFileDescriptor.startOffset, assetFileDescriptor.length)
            assetFileDescriptor.close()
        } catch (e: IOException) {
            Log.e("MediaPlayer", "Error setting data source", e)
            return
        }
        mPlayer.setOnPreparedListener {
            if (!isSoundEnabled) {
                mPlayer.start() // Запуск воспроизведения после подготовки
            }
        }
        try {
            mPlayer.prepareAsync() // Асинхронная подготовка
        } catch (e: IllegalStateException) {
            Log.e("MediaPlayer", "IllegalStateException: ${e.message}")
            e.printStackTrace()
        }

        rotationAnimator.start() // Запускаем анимацию
        button_active.isEnabled = false //Выключаем кнопку, на время анимации
        button_active.isSelected = true

    }

    private fun toggleSound(view: View){
        val button_sound: ImageButton = findViewById<ImageButton>(R.id.sound_f)
        isSoundEnabled = !isSoundEnabled
        if (isSoundEnabled) {
            button_sound.setImageResource(R.drawable.sound_off) // Установите иконку звука выключенного
        } else {
            button_sound.setImageResource(R.drawable.sound_on)  // Установите иконку звука включенного
        }
    }
}