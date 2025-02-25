package zenn.dilan.idecided.fragments

import DataReceiver
import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import zenn.dilan.idecided.R



class BoxFragment : Fragment(), DataReceiver {
    private var textTitle: String? = null
    private var image: String? = null
    private var dataList = mutableListOf<Pair<String?, Float?>>()
    private lateinit var viewModel: SharedViewModel

    override fun onDataReceived(inputText: String, ratingValue: Float) {
        // Обработка полученных данных
        val text = inputText
        val rat = ratingValue
//        dataList.add(Pair(text, rat)) // Добавляем новую пару в массив
        if (textTitle == getString(R.string.listH1)) {
            viewModel.dataListPlus.add(Pair(text, rat))
            dataList = viewModel.dataListPlus // Обновляем dataList
        } else if (textTitle == getString(R.string.listH2)) {
            viewModel.dataListMinus.add(Pair(text, rat))
            dataList = viewModel.dataListMinus // Обновляем dataList
        }
        else if (textTitle == getString(R.string.quH1)) {
            viewModel.dataQuadratPlus1.add(Pair(text, rat))
            dataList = viewModel.dataQuadratPlus1
        }
        else if (textTitle == getString(R.string.quH2)) {
            viewModel.dataQuadratMinus1.add(Pair(text, rat))
            dataList = viewModel.dataQuadratMinus1
        }
        else if (textTitle == getString(R.string.quH3)) {
            viewModel.dataQuadratMinus2.add(Pair(text, rat))
            dataList = viewModel.dataQuadratMinus2
        }
        else if (textTitle == getString(R.string.quH4)) {
            viewModel.dataQuadratPlus2.add(Pair(text, rat))
            dataList = viewModel.dataQuadratPlus2
        }
        updateMessage() // Обновляем отображение данных
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
        arguments?.let {
            textTitle = it.getString("textTittle")
            image = it.getString("image")
            if (textTitle == getString(R.string.listH1)) {
                dataList = viewModel.dataListPlus
            }
            else if (textTitle == getString(R.string.listH2)) {
                dataList = viewModel.dataListMinus
            }
            else if (textTitle == getString(R.string.quH1)) {
                dataList = viewModel.dataQuadratPlus1
            }
            else if (textTitle == getString(R.string.quH2)) {
                dataList = viewModel.dataQuadratMinus1
            }
            else if (textTitle == getString(R.string.quH3)) {
                dataList = viewModel.dataQuadratMinus2
            }
            else if (textTitle == getString(R.string.quH4)) {
                dataList = viewModel.dataQuadratPlus2
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_box, container, false)
        val title: TextView = view.findViewById(R.id.listTittle)
        val butAdd: ImageButton = view.findViewById(R.id.btn_add)
        val butResult: ImageButton = view.findViewById(R.id.btn_result)
        butAdd.setOnClickListener(this::onbutAddClick)
        butResult.setOnClickListener(this::onbutResClick)
        title.text = textTitle

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateMessage() // Теперь вызываем здесь
    }

    private fun onbutAddClick(view: View) {
        val infoFragments = DialogFragmentAdd()
        // Используем parentFragmentManager вместо supportFragmentManager
        val manager = parentFragmentManager
        infoFragments.setOnDataReceivedListener(this)
        infoFragments.show(manager, "addFragment")
    }
    private fun updateMessage() {
        val frayLay: LinearLayout = view?.findViewById(R.id.fray_lay) ?: return
        val drawable: ImageView = view?.findViewById(R.id.imageViewList) ?: return
        val butResult: ImageButton = view?.findViewById(R.id.btn_result) ?: return
        frayLay.removeAllViews() // Очищаем текущие элементы

        if (dataList.isEmpty()) {

            // Установите изображение, если dataList пуст
            if(image == "list"){
                drawable.setImageResource(R.drawable.croco_null)
                if (viewModel.dataListPlus.isEmpty() || viewModel.dataListMinus.isEmpty()) {
                    butResult.setImageResource(R.drawable.result_passiv) // Установите изображение по умолчанию
                    butResult.setOnClickListener {
                        Toast.makeText(context, "Сначала заполните все листы", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    butResult.setImageResource(R.drawable.result_activ) // Установите изображение, когда списки не пустые
                    butResult.setOnClickListener {
                        // Действие при нажатии, когда списки заполнены
                    }
                }
            }
            else{
                drawable.setImageResource(R.drawable.cat_null)
                if (viewModel.dataQuadratPlus1.isEmpty() || viewModel.dataQuadratPlus2.isEmpty() || viewModel.dataQuadratMinus1.isEmpty() || viewModel.dataQuadratMinus2.isEmpty()) {
                    butResult.setImageResource(R.drawable.result_passiv) // Установите изображение по умолчанию
                    butResult.setOnClickListener {
                        Toast.makeText(context, "Сначала заполните все листы", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    butResult.setImageResource(R.drawable.result_activ) // Установите изображение, когда списки не пустые
                    butResult.setOnClickListener {
                        // Действие при нажатии, когда списки заполнены
                    }
                }
            }
        } else {
            drawable.setImageResource(R.color.transparent)
            for (data in dataList) {
                val layoutGlav = LinearLayout(context).apply {
                    orientation = LinearLayout.VERTICAL
                    layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )
                    setBackgroundColor(ContextCompat.getColor(context, R.color.transparent))
                    setPadding(10, 10, 10, 10)
                }
                val layout = LinearLayout(context).apply {
                    orientation = LinearLayout.HORIZONTAL
                    layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )
                    setBackgroundColor(ContextCompat.getColor(context, R.color.transparent))
                    setPadding(10, 10, 10, 10)
                    gravity = Gravity.CENTER_VERTICAL
                }

                val textView = TextView(context).apply {
                    text = "${data.first}"
                    layoutParams = LinearLayout.LayoutParams(
                        0,
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        1f // Занимает оставшееся пространство
                    )
                    textSize = 30f
                    setTextColor(ContextCompat.getColor(context, R.color.black))
                    setBackgroundColor(ContextCompat.getColor(context, R.color.transparent))
                    setPadding(0, 0, 20, 0)
                }
                val textView2 = TextView(context).apply {
                    text = "${data.second?.toInt()}"
                    textSize = 30f
                    layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                    ).apply {
                        gravity = Gravity.CENTER
                    }
                    setBackgroundColor(ContextCompat.getColor(context, R.color.transparent))
                    setPadding(20, 0, 0, 0)
                    setTextColor(ContextCompat.getColor(context, R.color.black))
                }

                val ratImageView = ImageView(context).apply {
                    setImageResource(R.drawable.rating_on) // Убедитесь, что у Вас есть rat.png в ресурсах
                    layoutParams = LinearLayout.LayoutParams(
                        100,
                        100
                    ).apply {
                        gravity = Gravity.CENTER // Центрируем изображение
                    }
                    setPadding(0, 0, 20, 0)
                }

                val deleteImageView = ImageView(context).apply {
                    setImageResource(R.drawable.delete_add) // Убедитесь, что у Вас есть delete.png в ресурсах
                    layoutParams = LinearLayout.LayoutParams(
                        70,
                        70
                    ).apply {
                        gravity = Gravity.CENTER // Центрируем изображение
                    }
                    setOnClickListener {
                        // Удаляем элемент из dataList и обновляем отображение
                        dataList.remove(data)
                        updateMessage()
                    }
                    setPadding(20, 0, 0, 0)
                }

                // Добавление View для разделения
                val dividerView = View(context).apply {
                    layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        3
                    )
                    setBackgroundColor(
                        ContextCompat.getColor(
                            context,
                            R.color.gold
                        )
                    ) // Установка цвета
                }

                layout.addView(textView)
                layout.addView(textView2)
                layout.addView(ratImageView)
                layout.addView(deleteImageView)
                layoutGlav.addView(layout)
                layoutGlav.addView(dividerView)
                frayLay.addView(layoutGlav)
            }
        }
    }

    private fun onbutResClick(view: View) {
        val butResult: ImageButton = view.findViewById(R.id.btn_result)
        if (viewModel.dataListPlus.isEmpty() || viewModel.dataListMinus.isEmpty()) {
            butResult.setImageResource(R.drawable.result_passiv) // Установите изображение по умолчанию
            butResult.setOnClickListener {
                Toast.makeText(context, "Сначала заполните все листы", Toast.LENGTH_SHORT).show()
            }
        } else {
            butResult.setImageResource(R.drawable.result_activ) // Установите изображение, когда списки не пустые
            butResult.setOnClickListener {
                // Действие при нажатии, когда списки заполнены
            }
        }
    }

}