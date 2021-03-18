package com.ctt.appcarro

import android.app.DatePickerDialog
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ctt.appcarro.model.Carro
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_carro.*
import java.text.SimpleDateFormat
import java.util.*
import androidx.core.app.ActivityCompat.startActivityForResult
import java.net.URI

class MainActivity : AppCompatActivity() {

    private val meuCalendario = Calendar.getInstance()
    private val RESULT_LOAD_IMAGE = 12345;
    private lateinit var imagemCarro: URI

    private lateinit var botaoBuscar : Button
    private lateinit var montadoraCarro : EditText
    private lateinit var modeloCarro : EditText
    private lateinit var dataRetiradaCarro : EditText
    private lateinit var localCarro : EditText
    private lateinit var qtdPortasCarro : EditText

    private fun updateLabel() {
        val meuFormato = "dd/MM/yyyy"
        val sdf = SimpleDateFormat(meuFormato, Locale.US)
        dttDataRetirada.setText(sdf.format(meuCalendario.getTime()))
    }

    val data = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
        meuCalendario.set(Calendar.YEAR, year)
        meuCalendario.set(Calendar.MONTH, month)
        meuCalendario.set(Calendar.DAY_OF_MONTH, dayOfMonth)
        updateLabel()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        botaoBuscar = findViewById(R.id.bttBuscar)
        montadoraCarro = findViewById(R.id.dttMontadora)
        modeloCarro = findViewById(R.id.dttModelo)
        dataRetiradaCarro = findViewById(R.id.dttDataRetirada)
        localCarro = findViewById(R.id.dttLocal)
        qtdPortasCarro = findViewById(R.id.dttPortas)

        //Botão buscar:
        botaoBuscar.setOnClickListener{
            val montadoraDigitada = montadoraCarro.text.toString()
            val modeloDigitado = modeloCarro.text.toString()
            val dataDigitada = dataRetiradaCarro.text.toString()
            val localDigitado = localCarro.text.toString()
            val qtdPortasDigitada = qtdPortasCarro.text.toString()

            if (montadoraDigitada.isEmpty()) {
                montadoraCarro.error = "É necessário informar a montadora do carro"
            } else if (modeloDigitado.isEmpty()) {
                modeloCarro.error = "É necessário informar o modelo desejado"
            } else if (dataDigitada.isEmpty()) {
                dataRetiradaCarro.error = "É necessário informar a data do aluguel"
            } else if (localDigitado.isEmpty()) {
                localCarro.error = "É necessário informar qual será o local de retirada do carro"
            } else if (qtdPortasDigitada.isEmpty()) {
                qtdPortasCarro.error = "É necessário informar a quantidade de portas"
            } else {
                try {
                    val carroEscolhido = Carro(
                        montadoraDigitada,
                        modeloDigitado,
                        dataDigitada,
                        localDigitado,
                        qtdPortasDigitada.toInt(),
//                        imagemCarro
                    )
                    redirecionar (carroEscolhido)
                } catch (err: Exception) {
                    Toast.makeText(this, "Opa, deu ruim", Toast.LENGTH_SHORT).show()
                }
            }
        }

        //Botão da data
        dttDataRetirada.setOnClickListener{
            DatePickerDialog(
                this@MainActivity, data, meuCalendario
                    .get(Calendar.YEAR), meuCalendario.get(Calendar.MONTH),
                meuCalendario.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

//        //Botão carrega imagem
//        bttCarregaImg.setOnClickListener{
//            escolherFoto()
//        }
    }

    fun escolherFoto (){
        val i = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(i, RESULT_LOAD_IMAGE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK) {
            imagemCarro = data?.extras?.get("data") as URI

            }
    }

    fun redirecionar (carro: Carro){

        val destinoActivity = Intent (this@MainActivity, CarroActivity::class.java)
        destinoActivity.putExtra("CARRO", carro)

        startActivity(destinoActivity)

        //finish()
    }
}