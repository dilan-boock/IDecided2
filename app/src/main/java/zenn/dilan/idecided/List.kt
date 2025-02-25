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
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.util.ArrayList

class List : AppCompatActivity() {
    private var fragmentA: BoxFragment? = null
    private var fragmentB: BoxFragment? = null
    private var currentFragment: BoxFragment? = null
    private var textBut: TextView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_list)
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
        fragmentA = BoxFragment()
        fragmentB = BoxFragment()
        currentFragment = fragmentA

        butBack.isEnabled = false
        butBack.isSelected = true
        butNext.isEnabled = true
        butNext.isSelected = false


        val argsA = Bundle().apply {
            putString("textTittle", getString(R.string.listH1))
            putString("image", "list")
        }
        fragmentA?.arguments = argsA

        val argsB = Bundle().apply {
            putString("textTittle", getString(R.string.listH2))
            putString("image", "list")
        }
        fragmentB?.arguments = argsB

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentList, currentFragment!!)
            .commit()
    }


    private fun onbutBack(view: View?) {

        val butBack: ImageButton = findViewById(R.id.back)
        val butNext: ImageButton = findViewById(R.id.next)
        if (currentFragment == fragmentB) {
            textBut?.text = "1/2"
            butBack.isEnabled = false
            butBack.isSelected = true
            butNext.isEnabled = true
            butNext.isSelected = false
            currentFragment = fragmentA
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentList, currentFragment!!)
                .commit()
        }
    }
    private fun onbutNext(view: View?) {
        val butBack: ImageButton = findViewById(R.id.back)
        val butNext: ImageButton = findViewById(R.id.next)
        if (currentFragment == fragmentA) {
            textBut?.text = "2/2"
            butBack.isEnabled = true
            butBack.isSelected = false
            butNext.isEnabled = false
            butNext.isSelected = true
            currentFragment = fragmentB
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentList, currentFragment!!)
                .commit()
        }
    }
    private fun onbutHome(view: View?) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}