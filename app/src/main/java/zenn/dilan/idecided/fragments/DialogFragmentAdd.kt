package zenn.dilan.idecided.fragments
import DataReceiver
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.Matrix
import android.graphics.Typeface
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.DialogFragment
import zenn.dilan.idecided.R

class DialogFragmentAdd: DialogFragment() {

    private var listener: DataReceiver? = null

    fun setOnDataReceivedListener(listener: DataReceiver) {
        this.listener = listener
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        return activity?.let {
            val builder = AlertDialog.Builder(it, R.style.CustomDialog)
            val inflater = layoutInflater
            val dialogLayout = inflater.inflate(R.layout.activity_dialog_add, null) // Замените на Ваш layout
            val inputField: EditText = dialogLayout.findViewById<EditText>(R.id.editAdd) // Поле ввода
            val mess: TextView = dialogLayout.findViewById(R.id.textR)
            val ratingBar: RatingBar = dialogLayout.findViewById<RatingBar>(R.id.ratingbar)
            val buttonClose: TextView = dialogLayout.findViewById<TextView>(R.id.close_dialog)
            val buttonAdd: Button = dialogLayout.findViewById<Button>(R.id.positiv_but)

            ratingBar.setOnRatingBarChangeListener { _, rating, _ ->
                inputField.clearFocus()
                // Скрытие клавиатуры
                val imm = it.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(inputField.windowToken, 0)
                mess.text = getString(R.string.textIn3) + " " + rating.toInt()
            }

            builder.setView(dialogLayout)
            val alertDialog = builder.create()
            alertDialog.setOnShowListener {
                buttonClose.setOnClickListener {
                    alertDialog.dismiss() // Закрытие диалога
                }
                alertDialog.setCanceledOnTouchOutside(false)
            }
            alertDialog.window?.setDimAmount(0.9F)
            alertDialog.window?.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT)
            alertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            alertDialog.window?.setGravity(Gravity.CENTER)

            buttonAdd.setOnClickListener {
                val inputText = inputField.text.toString()
                val ratingValue = ratingBar.rating

                if (inputText.isEmpty()) {
                    inputField.error = getString(R.string.errorF2) // Подсказка для незаполненного поля
                    inputField.requestFocus() // Установка фокуса на поле ввода

                    // Удаление ошибки через 4 секунды
                    Handler(Looper.getMainLooper()).postDelayed({
                        inputField.error = null
                    }, 2000) // 4000 миллисекунд = 4 секунды

                } else if (ratingValue == 0f) {
                    // Подсказка для незаполненного рейтинга
                    Toast.makeText(it.context, getString(R.string.errorF1), Toast.LENGTH_SHORT)
                        .show()
                    ratingBar.requestFocus() // Установка фокуса на рейтинг
                    listener?.onDataReceived(inputText, ratingValue)
                    alertDialog.dismiss() // Закрытие диалога
                }
            }

            alertDialog
        } ?: throw IllegalStateException("Activity cannot be null")
    }

}