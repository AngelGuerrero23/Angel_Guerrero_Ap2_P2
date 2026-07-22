package com.android.angel_guerrero_ap2_p2.data.remote.dto

import com.android.angel_guerrero_ap2_p2.domain.model.Examen

data class ExamenResponseDto(
    val items: List<ExamenDto>
)

data class ExamenDto(

){
    fun toDomain()= Examen(

    )
}