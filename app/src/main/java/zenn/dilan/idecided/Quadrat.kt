package zenn.dilan.idecided

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import zenn.dilan.idecided.fragments.BoxFragment
import zenn.dilan.idecided.fragments.DialogFragmentAdd

class Quadrat : AppCompatActivity() {
    val fragmentA = BoxFragment()
    val fragmentB = BoxFragment()
    val fragmentC = BoxFragment()
    val fragmentD = BoxFragment()
    var currentFragment = fragmentA
    var currentFragmentIndex = 0
    private val fragments = listOf(fragmentA, fragmentB, fragmentC, fragmentD)

    private var textBut: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_quadrat)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val butBack: ImageButton = findViewById(R.id.back)
        val butNext: ImageButton = findViewById(R.id.next)
        val button_home: TextView = findViewById<TextView>(R.id.home_but)
        textBut = findViewById(R.id.textBut)

        butBack.setOnClickListener(this::onbutBack)
        butNext.setOnClickListener(this::onbutNext)
        button_home.setOnClickListener(this::onbutHome)

        // Инициализация фрагментов

        currentFragment = fragmentA

        butBack.isEnabled = false
        butBack.isSelected = true
        butNext.isEnabled = true
        butNext.isSelected = false
        textBut?.text = "1/4"

        val argsA = Bundle().apply {
            putString("textTittle", getString(R.string.quH1))
            putString("image", "1")
        }
        fragmentA.arguments = argsA

        val argsB = Bundle().apply {
            putString("textTittle", getString(R.string.quH2))
            putString("image", "2")
        }
        fragmentB.arguments = argsB

        val argsC = Bundle().apply {
            putString("textTittle", getString(R.string.quH3))
            putString("image", "3")
        }
        fragmentC.arguments = argsC

        val argsD = Bundle().apply {
            putString("textTittle", getString(R.string.quH4))
            putString("image", "4")
        }
        fragmentD.arguments = argsD

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentQuadrat, currentFragment)
            .commit()

    }


    private fun onbutBack(view: View?) {
        if (currentFragmentIndex > 0) {
            currentFragmentIndex--
            updateFragment()
        }
    }
    private fun onbutNext(view: View?) {
        if (currentFragmentIndex < fragments.size - 1) {
            currentFragmentIndex++
            updateFragment()
        }

    }

    private fun updateFragment() {
        val butBack: ImageButton = findViewById(R.id.back)
        val butNext: ImageButton = findViewById(R.id.next)

        // Обновляем текст кнопки
        textBut?.text = "${currentFragmentIndex + 1}/${fragments.size}"

        // Обновляем состояние кнопок
        butBack.isEnabled = currentFragmentIndex > 0
        butBack.isSelected = currentFragmentIndex == 0
        butNext.isEnabled = currentFragmentIndex < fragments.size - 1
        butNext.isSelected = currentFragmentIndex == fragments.size - 1
        currentFragment = fragments[currentFragmentIndex]
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentQuadrat, currentFragment)
            .commit()

    }

    private fun onbutHome(view: View?) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}