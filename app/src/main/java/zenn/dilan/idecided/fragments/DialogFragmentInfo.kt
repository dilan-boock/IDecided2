package zenn.dilan.idecided.fragments
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.app.Dialog
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.Matrix
import android.graphics.Typeface
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.DialogFragment
import zenn.dilan.idecided.R

class DialogFragmentInfo: DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {

            val builder = AlertDialog.Builder(it, R.style.CustomDialog)
            val buttonId = arguments?.getInt("buttonId")
            val randomFortuna = arguments?.getInt("random")
            val buttonBox = arguments?.getString("buttonBox")
            val res = arguments?.getInt("res")

            val inflater = layoutInflater
            val dialogLayout = inflater.inflate(R.layout.activity_dialog_info, null) // Замените на Ваш layout
            val textTitle: TextView = dialogLayout.findViewById<TextView>(R.id.textTitle)
            val textMessage: TextView = dialogLayout.findViewById<TextView>(R.id.textMessage)
            val button: Button = dialogLayout.findViewById<Button>(R.id.positiv_but)
            val image: ImageView = dialogLayout.findViewById<ImageView>(R.id.image_Dialog)
            var isZoomed = false


            image.setOnClickListener {
                if(!isZoomed){
                    // Увеличиваем изображение
                    val scaleX = ObjectAnimator.ofFloat(image, "scaleX", 1f, 3f)
                    val scaleY = ObjectAnimator.ofFloat(image, "scaleY", 1f, 3f)
                    val tranceY = ObjectAnimator.ofFloat(image, "translationY", 0f, -150f)
                    val animatorSet = AnimatorSet()
                    animatorSet.play(scaleX).with(scaleY).with(tranceY)
                    animatorSet.duration = 300 // Длительность анимации
                    animatorSet.start()

                    isZoomed = true
                }
                else{
                    // Возвращаем изображение в исходное состояние
                    val scaleX = ObjectAnimator.ofFloat(image, "scaleX", 3f, 1f)
                    val scaleY = ObjectAnimator.ofFloat(image, "scaleY", 3f, 1f)
                    val tranceY = ObjectAnimator.ofFloat(image, "translationY", -150f, 0f)
                    val animatorSet = AnimatorSet()
                    animatorSet.play(scaleX).with(scaleY).with(tranceY)
                    animatorSet.duration = 300 // Длительность анимации
                    animatorSet.start()

                    isZoomed = false // Сбрасываем флаг
                }

            }


            val matrix = Matrix().apply {
                preScale(1f, -1f) // Переворачиваем по вертикали
            }

            // Сравнение ID кнопки с ресурсом
            if (buttonId == R.id.info_quadrat) {
                textTitle.text = getString(R.string.but1)
                textMessage.text = getString(R.string.infoMenu1)
                button.text = getString(R.string.infoOut1)
            }
            else if(buttonId == R.id.info_list){
                textTitle.text = getString(R.string.but2)
                textMessage.text = getString(R.string.infoMenu2)
                button.text = getString(R.string.infoOut1)
            }
            else if(buttonId == R.id.info_money){
                textTitle.text = getString(R.string.but3)
                textMessage.text = getString(R.string.infoMenu3)
                button.text = getString(R.string.infoOut1)
            }
            else if(buttonId == R.id.info_fortuna){
                textTitle.text = getString(R.string.but4)
                textMessage.text = getString(R.string.infoMenu4)
                button.text = getString(R.string.infoOut1)
            }
            else if(buttonId == R.id.info_taro){
                textTitle.text = getString(R.string.but5)
                textMessage.text = getString(R.string.infoMenu5)
                button.text = getString(R.string.infoOut1)
            }
            else if(buttonId == R.id.info_dnd){
                textTitle.text = getString(R.string.but6)
                textMessage.text = getString(R.string.infoMenu6)
                button.text = getString(R.string.infoOut1)
            }
            else if(buttonId == R.id.info_about){
                textTitle.text = getString(R.string.but7)
                textMessage.text = getString(R.string.infoMenu7)
                button.text = getString(R.string.infoOut1)
            }
            else if(buttonId == R.id.but_d){
                val random = (0..20).random()
                textTitle.text = getString(R.string.dndH)

                if(random == 0){
                    textTitle.text = getString(R.string.dndH1)
                    textMessage.text = getString(R.string.dnd0)
                }
                else if(random == 1){
                    textMessage.text = getString(R.string.dnd1)
                }
                else if(random == 2){
                    textMessage.text = getString(R.string.dnd2)
                }
                else if(random == 3){
                    textMessage.text = getString(R.string.dnd3)
                }
                else if(random == 4){
                    textMessage.text = getString(R.string.dnd4)
                }
                else if(random == 5){
                    textMessage.text = getString(R.string.dnd5)
                }
                else if(random == 6){
                    textMessage.text = getString(R.string.dnd6)
                }
                else if(random == 7){
                    textMessage.text = getString(R.string.dnd7)
                }
                else if(random == 8){
                    textMessage.text = getString(R.string.dnd8)
                }
                else if(random == 9){
                    textMessage.text = getString(R.string.dnd9)
                }
                else if(random == 10){
                    textMessage.text = getString(R.string.dnd10)
                }
                else if(random == 11){
                    textMessage.text = getString(R.string.dnd11)
                }
                else if(random == 12){
                    textMessage.text = getString(R.string.dnd12)
                }
                else if(random == 13){
                    textMessage.text = getString(R.string.dnd13)
                }
                else if(random == 14){
                    textMessage.text = getString(R.string.dnd14)
                }
                else if(random == 15){
                    textMessage.text = getString(R.string.dnd15)
                }
                else if(random == 16){
                    textMessage.text = getString(R.string.dnd16)
                }
                else if(random == 17){
                    textMessage.text = getString(R.string.dnd17)
                }
                else if(random == 18){
                    textMessage.text = getString(R.string.dnd18)
                }
                else if(random == 19){
                    textMessage.text = getString(R.string.dnd19)
                }
                else if(random == 20){
                    textMessage.text = getString(R.string.dnd20)
                }
                button.text = getString(R.string.infoOut3)
            }
            else if(buttonId == R.id.but_f){
                textTitle.text = getString(R.string.fortunaH)

                if(randomFortuna == 1){
                    textMessage.text = getString(R.string.fortuna1)
                }
                else if(randomFortuna == 2){
                    textMessage.text = getString(R.string.fortuna2)
                }
                else if(randomFortuna == 3){
                    textMessage.text = getString(R.string.fortuna3)
                }
                else if(randomFortuna == 4){
                    textMessage.text = getString(R.string.fortuna4)
                }
                else if(randomFortuna == 5){
                    textMessage.text = getString(R.string.fortuna5)
                }
                else if(randomFortuna == 6){
                    textMessage.text = getString(R.string.fortuna6)
                }
                else if(randomFortuna == 7){
                    textMessage.text = getString(R.string.fortuna7)
                }
                else if(randomFortuna == 8){
                    textMessage.text = getString(R.string.fortuna8)
                }
                button.text = getString(R.string.infoOut2)
            }
            else if(buttonId == R.id.but_t){
                val randomN = (1..11).random()
                val random = (1..22).random()
                if (randomN >= 1 && randomN <= 5){
                    if(random == 1){
                        textTitle.text = getString(R.string.taroH1)
                        textMessage.text = getString(R.string.taro1M)
                        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.chariot)
                        val flippedBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, false)
                        image.setImageBitmap(flippedBitmap)
                    }
                    else if(random == 2){
                        textTitle.text = getString(R.string.taroH2)
                        textMessage.text = getString(R.string.taro2M)
                        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.court)
                        val flippedBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, false)
                        image.setImageBitmap(flippedBitmap)
                    }
                    else if(random == 3){
                        textTitle.text = getString(R.string.taroH3)
                        textMessage.text = getString(R.string.taro3M)
                        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.death)
                        val flippedBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, false)
                        image.setImageBitmap(flippedBitmap)
                    }
                    else if(random == 4){
                        textTitle.text = getString(R.string.taroH4)
                        textMessage.text = getString(R.string.taro4M)
                        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.devil)
                        val flippedBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, false)
                        image.setImageBitmap(flippedBitmap)
                    }
                    else if(random == 5){
                        textTitle.text = getString(R.string.taroH5)
                        textMessage.text = getString(R.string.taro5M)
                        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.empreror)
                        val flippedBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, false)
                        image.setImageBitmap(flippedBitmap)
                    }
                    else if(random == 6){
                        textTitle.text = getString(R.string.taroH6)
                        textMessage.text = getString(R.string.taro6M)
                        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.empress)
                        val flippedBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, false)
                        image.setImageBitmap(flippedBitmap)
                    }
                    else if(random == 7){
                        textTitle.text = getString(R.string.taroH7)
                        textMessage.text = getString(R.string.taro7M)
                        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.fortuna)
                        val flippedBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, false)
                        image.setImageBitmap(flippedBitmap)
                    }
                    else if(random == 8){
                        textTitle.text = getString(R.string.taroH8)
                        textMessage.text = getString(R.string.taro8M)
                        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.hanged)
                        val flippedBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, false)
                        image.setImageBitmap(flippedBitmap)
                    }
                    else if(random == 9){
                        textTitle.text = getString(R.string.taroH9)
                        textMessage.text = getString(R.string.taro9M)
                        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.hermit)
                        val flippedBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, false)
                        image.setImageBitmap(flippedBitmap)
                    }
                    else if(random == 10){
                        textTitle.text = getString(R.string.taroH10)
                        textMessage.text = getString(R.string.taro10M)
                        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.jester)
                        val flippedBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, false)
                        image.setImageBitmap(flippedBitmap)
                    }
                    else if(random == 11){
                        textTitle.text = getString(R.string.taroH11)
                        textMessage.text = getString(R.string.taro11M)
                        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.justice)
                        val flippedBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, false)
                        image.setImageBitmap(flippedBitmap)
                    }
                    else if(random == 12){
                        textTitle.text = getString(R.string.taroH12)
                        textMessage.text = getString(R.string.taro12M)
                        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.lovers)
                        val flippedBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, false)
                        image.setImageBitmap(flippedBitmap)
                    }
                    else if(random == 13){
                        textTitle.text = getString(R.string.taroH13)
                        textMessage.text = getString(R.string.taro13M)
                        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.magik)
                        val flippedBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, false)
                        image.setImageBitmap(flippedBitmap)
                    }
                    else if(random == 14){
                        textTitle.text = getString(R.string.taroH14)
                        textMessage.text = getString(R.string.taro14M)
                        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.moderation)
                        val flippedBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, false)
                        image.setImageBitmap(flippedBitmap)
                    }
                    else if(random == 15){
                        textTitle.text = getString(R.string.taroH15)
                        textMessage.text = getString(R.string.taro15M)
                        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.moon)
                        val flippedBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, false)
                        image.setImageBitmap(flippedBitmap)
                    }
                    else if(random == 16){
                        textTitle.text = getString(R.string.taroH16)
                        textMessage.text = getString(R.string.taro16M)
                        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.sonne)
                        val flippedBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, false)
                        image.setImageBitmap(flippedBitmap)
                    }
                    else if(random == 17){
                        textTitle.text = getString(R.string.taroH17)
                        textMessage.text = getString(R.string.taro17M)
                        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.power)
                        val flippedBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, false)
                        image.setImageBitmap(flippedBitmap)
                    }
                    else if(random == 18){
                        textTitle.text = getString(R.string.taroH18)
                        textMessage.text = getString(R.string.taro18M)
                        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.priest)
                        val flippedBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, false)
                        image.setImageBitmap(flippedBitmap)
                    }
                    else if(random == 19){
                        textTitle.text = getString(R.string.taroH19)
                        textMessage.text = getString(R.string.taro19M)
                        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.priestess)
                        val flippedBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, false)
                        image.setImageBitmap(flippedBitmap)
                    }
                    else if(random == 20){
                        textTitle.text = getString(R.string.taroH20)
                        textMessage.text = getString(R.string.taro20M)
                        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.star)
                        val flippedBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, false)
                        image.setImageBitmap(flippedBitmap)
                    }
                    else if(random == 21){
                        textTitle.text = getString(R.string.taroH21)
                        textMessage.text = getString(R.string.taro21M)
                        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.tower)
                        val flippedBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, false)
                        image.setImageBitmap(flippedBitmap)
                    }
                    else if(random == 22){
                        textTitle.text = getString(R.string.taroH22)
                        textMessage.text = getString(R.string.taro22M)
                        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.world)
                        val flippedBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, false)
                        image.setImageBitmap(flippedBitmap)
                    }
                }
                else{
                    if(random == 1){
                        textTitle.text = getString(R.string.taroH1)
                        textMessage.text = getString(R.string.taro1)
                        image.setImageResource(R.drawable.chariot)
                    }
                    else if(random == 2){
                        textTitle.text = getString(R.string.taroH2)
                        textMessage.text = getString(R.string.taro2)
                        image.setImageResource(R.drawable.court)
                    }
                    else if(random == 3){
                        textTitle.text = getString(R.string.taroH3)
                        textMessage.text = getString(R.string.taro3)
                        image.setImageResource(R.drawable.death)
                    }
                    else if(random == 4){
                        textTitle.text = getString(R.string.taroH4)
                        textMessage.text = getString(R.string.taro4)
                        image.setImageResource(R.drawable.devil)
                    }
                    else if(random == 5){
                        textTitle.text = getString(R.string.taroH5)
                        textMessage.text = getString(R.string.taro5)
                        image.setImageResource(R.drawable.empreror)
                    }
                    else if(random == 6){
                        textTitle.text = getString(R.string.taroH6)
                        textMessage.text = getString(R.string.taro6)
                        image.setImageResource(R.drawable.empress)
                    }
                    else if(random == 7){
                        textTitle.text = getString(R.string.taroH7)
                        textMessage.text = getString(R.string.taro7)
                        image.setImageResource(R.drawable.fortuna)
                    }
                    else if(random == 8){
                        textTitle.text = getString(R.string.taroH8)
                        textMessage.text = getString(R.string.taro8)
                        image.setImageResource(R.drawable.hanged)
                    }
                    else if(random == 9){
                        textTitle.text = getString(R.string.taroH9)
                        textMessage.text = getString(R.string.taro9)
                        image.setImageResource(R.drawable.hermit)
                    }
                    else if(random == 10){
                        textTitle.text = getString(R.string.taroH10)
                        textMessage.text = getString(R.string.taro10)
                        image.setImageResource(R.drawable.jester)
                    }
                    else if(random == 11){
                        textTitle.text = getString(R.string.taroH11)
                        textMessage.text = getString(R.string.taro11)
                        image.setImageResource(R.drawable.justice)
                    }
                    else if(random == 12){
                        textTitle.text = getString(R.string.taroH12)
                        textMessage.text = getString(R.string.taro12)
                        image.setImageResource(R.drawable.lovers)
                    }
                    else if(random == 13){
                        textTitle.text = getString(R.string.taroH13)
                        textMessage.text = getString(R.string.taro13)
                        image.setImageResource(R.drawable.magik)
                    }
                    else if(random == 14){
                        textTitle.text = getString(R.string.taroH14)
                        textMessage.text = getString(R.string.taro14)
                        image.setImageResource(R.drawable.moderation)
                    }
                    else if(random == 15){
                        textTitle.text = getString(R.string.taroH15)
                        textMessage.text = getString(R.string.taro15)
                        image.setImageResource(R.drawable.moon)
                    }
                    else if(random == 16){
                        textTitle.text = getString(R.string.taroH16)
                        textMessage.text = getString(R.string.taro16)
                        image.setImageResource(R.drawable.sonne)
                    }
                    else if(random == 17){
                        textTitle.text = getString(R.string.taroH17)
                        textMessage.text = getString(R.string.taro17)
                        image.setImageResource(R.drawable.power)
                    }
                    else if(random == 18){
                        textTitle.text = getString(R.string.taroH18)
                        textMessage.text = getString(R.string.taro18)
                        image.setImageResource(R.drawable.priest)
                    }
                    else if(random == 19){
                        textTitle.text = getString(R.string.taroH19)
                        textMessage.text = getString(R.string.taro19)
                        image.setImageResource(R.drawable.priestess)
                    }
                    else if(random == 20){
                        textTitle.text = getString(R.string.taroH20)
                        textMessage.text = getString(R.string.taro20)
                        image.setImageResource(R.drawable.star)
                    }
                    else if(random == 21){
                        textTitle.text = getString(R.string.taroH21)
                        textMessage.text = getString(R.string.taro21)
                        image.setImageResource(R.drawable.tower)
                    }
                    else if(random == 22){
                        textTitle.text = getString(R.string.taroH22)
                        textMessage.text = getString(R.string.taro22)
                        image.setImageResource(R.drawable.world)
                    }
                }
                button.text = getString(R.string.infoOut3)
            }
            else if(buttonId == R.id.info_quadrat){
                textTitle.text = getString(R.string.but3)
                textMessage.text = getString(R.string.infoMenu3)
                button.text = getString(R.string.infoOut1)
            }
            else if(buttonId == R.id.info_list){
                textTitle.text = getString(R.string.but4)
                textMessage.text = getString(R.string.infoMenu4)
                button.text = getString(R.string.infoOut1)
            }
            else{
                textTitle.text = getString(R.string.methods)
                if (buttonBox == "list"){
                    if (res == -1){
                        textMessage.text = getString(R.string.mess1)
                        button.text = getString(R.string.infoOut1)
                    }
                    else if (res == 1){
                        textMessage.text = getString(R.string.mess2)
                        button.text = getString(R.string.infoOut2)
                    }
                    else{
                        textMessage.text = getString(R.string.mess3)
                        button.text = getString(R.string.infoOut3)
                    }

                }
                else {
                    textTitle.text = "НЕ ГРУЗИТ"
                    textMessage.text = "НЕ ГРУЗИТ"
                    button.text = "НЕ ГРУЗИТ"
                }
            }


            // Установка цвета текста сообщения

            builder.setView(dialogLayout)
            val alertDialog = builder.create()
            alertDialog.setOnShowListener {
                button.setOnClickListener {
                    alertDialog.dismiss() // Закрытие диалога
                }
                alertDialog.setCanceledOnTouchOutside(false)

            }
            alertDialog.window?.setDimAmount(0.9F);
            alertDialog.window?.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT)
            alertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            alertDialog.window?.setGravity(Gravity.CENTER)


            alertDialog
        } ?: throw IllegalStateException("Activity cannot be null")
    }

}