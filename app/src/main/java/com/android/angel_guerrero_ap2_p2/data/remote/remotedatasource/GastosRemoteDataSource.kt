package com.android.angel_guerrero_ap2_p2.data.remote.remotedatasource

import com.android.angel_guerrero_ap2_p2.data.remote.dto.GastosRequest
import com.android.angel_guerrero_ap2_p2.data.remote.dto.GastosResponse
import com.android.angel_guerrero_ap2_p2.data.remote.dto.GastosResponseDto
import retrofit2.HttpException
import javax.inject.Inject

class GastosRemoteDataSource @Inject constructor(
    private val api: GastosApi
) {

    suspend fun getGastos(
        fecha: String,
        suplidor: String,
        ncf: String,
        itbis: Double,
        monto: Double
    ): Result<GastosResponseDto> {
        return try {
            val response = api.getGastos(fecha, suplidor, ncf, itbis, monto)
            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception("Error de red, ${response.code()}: ${response.message()}"))
            }
        } catch (e: HttpException) {
            Result.failure(Exception("Error de servidor", e))
        } catch (e: Exception) {
            Result.failure(Exception("Error desconocido", e))
        }
    }

    suspend fun getGastosById(id: Int): Result<GastosResponse> {
        return try {
            val response = api.getGastosById(id)
            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception("Error de red ${response.code()}"))
            }
        } catch (e: HttpException) {
            Result.failure(Exception("Error de servidor", e))
        } catch (e: Exception) {
            Result.failure(Exception("Error desconocido", e))
        }
    }

    suspend fun postGasto(gasto: GastosRequest): Result<GastosResponse> {
        return try {
            val response = api.saveGastos(gasto)
            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception("Error de red ${response.code()}"))
            }
        } catch (e: HttpException) {
            Result.failure(Exception("Error de servidor", e))
        } catch (e: Exception) {
            Result.failure(Exception("Error desconocido", e))
        }
    }

    suspend fun putGasto(id: Int, gasto: GastosRequest): Result<GastosResponse> {
        return try {
            val response = api.updateGastos(id, gasto)
            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception("Error de red ${response.code()}"))
            }
        } catch (e: HttpException) {
            Result.failure(Exception("Error de servidor", e))
        } catch (e: Exception) {
            Result.failure(Exception("Error desconocido", e))
        }
    }
}