package com.ctt.appcarro.model

import android.graphics.Bitmap
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.net.URI

@Parcelize
data class Carro (val montadora: String? = null,
                  val modelo: String? = null,
                  val dataRetirada: String? = null,
                  val localRetirada: String? = null,
                  val qtdPortas: Int? = null,
                  val imagem: URI? = null) : Parcelable
