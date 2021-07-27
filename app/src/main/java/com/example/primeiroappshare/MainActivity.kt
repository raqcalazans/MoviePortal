package com.example.primeiroappshare

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.primeiroappshare.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnVerFilmes.setOnClickListener {
            val intent = Intent(this, ListMoviesActivity::class.java)
            startActivity(intent)
        }
    }
}

//    fun ehUmBomFilme(filme: String?): String{
//        return when {
//            filme.isNullOrBlank() -> "Erro, preciso de um nome para avaliar"
//            filme.length < 5 -> "Um nome tão curto não pode ser bom"
//            else -> "É, talvez seja bom"
//        }
//    }
//
//    fun criarNumeros() {
//        val lista = listOf<Int>(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
//        var listaMutavel = mutableListOf<String>()
//
//        for(i in lista) {
//            if(i%2 == 0) {
//                listaMutavel.add(i.toString())
//            } else {
//                listaMutavel.add("-")
//            }
//        }
//        println(listaMutavel)

//        FUNÇÃO PROF LEO
//        val list = List(10){it+1}
//        var mutableList = mutableListOf<String>()
//        list.forEach {
//            mutableList.add(if (it%2 == 0 ) it.toString() else "-")
//        }
//        println(mutableList)
//    }


