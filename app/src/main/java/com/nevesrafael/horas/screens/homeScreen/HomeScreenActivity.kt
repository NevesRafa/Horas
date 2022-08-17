package com.nevesrafael.horas.screens.homeScreen

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nevesrafael.horas.databinding.ActivityHomeScreenBinding
import com.nevesrafael.horas.screens.creatWorkday.CreateWorkdayFragment

lateinit var binding: ActivityHomeScreenBinding

class HomeScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        configureFabHomeScreen()

    }

    fun configureFabHomeScreen() {
        binding.fabAddEvento.setOnClickListener {
//            val criaEventoFragment = CriaEventoFragment(quandoClicarNoSalvar = { evento ->
//                presenter.quandoClicaNoSalvar(evento)
//            })
            val createWorkdayFragment = CreateWorkdayFragment()
            createWorkdayFragment.show(supportFragmentManager, null)
        }
    }
}