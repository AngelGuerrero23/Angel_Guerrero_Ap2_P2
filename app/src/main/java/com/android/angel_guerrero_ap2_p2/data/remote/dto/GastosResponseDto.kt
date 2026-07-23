package com.android.angel_guerrero_ap2_p2.data.remote.dto

import com.android.angel_guerrero_ap2_p2.domain.model.Gasto

data class GastosResponseDto(
    val items: List<GastosResponse>
)
data class GastosRequest(
    val fecha: String,
    val suplidor: String,
    val ncf: String,
    val itbis: Double,
    val monto: Double
)
data class GastosResponse(
    val gastoId: Int = 0,
    val fecha: String? = null,
    val suplidor: String? = null,
    val ncf: String? = null,
    val itbis: Double? = null,
    val monto: Double? = null
) {
    fun toDomain() = Gasto(
        gastoId = gastoId,
        fecha = fecha ?: "",
        suplidor = suplidor ?: "",
        ncf = ncf ?: "",
        itbis = itbis ?: 0.0,
        monto = monto ?: 0.0
    )
}