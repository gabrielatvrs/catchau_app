package com.ctt.appcarro

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ctt.appcarro.model.Carro
import kotlinx.android.synthetic.main.activity_carro.*

class CarroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_carro)

        val carro = intent.extras?.get("CARRO") as Carro

//        if (carro.imagem != null) {
//            imgCarro.setImageURI(carro.imagem)
        //}

        txtDadosCarro.text = "Montadora: ${carro.montadora}" +
                "\n Modelo: ${carro.modelo}" +
                "\n Quantidade de portas: ${carro.qtdPortas}" +
                "\n Data da retirada: ${carro.dataRetirada}" +
                "\n Local da retirada: ${carro.localRetirada}"
    }
}