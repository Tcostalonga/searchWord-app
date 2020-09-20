package tarsila.costalonga.searchwordapp.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface WordAPI {

    @GET("api/v4/dictionary/{word}")
    suspend fun getWord(
        @Path("word") word: String
    ): Response<WordClass>


}