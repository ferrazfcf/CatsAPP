package ferrazfcf.catsapp.core.data.remote

import ferrazfcf.catsapp.breed_details_and_gallery.data.dto.BreedInfoDTO
import ferrazfcf.catsapp.breed_list.data.dto.BreedItemDTO
import ferrazfcf.catsapp.core.data.dto.ImageDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface Service {

    @GET("v1/breeds")
    suspend fun requestBreedItemList(
        @QueryMap params: Map<String, String>
    ): Response<List<BreedItemDTO>>

    @GET("v1/breeds/{breed}")
    suspend fun requestBreedInfo(
        @Path("breed") breed: String
    ): Response<BreedInfoDTO>

    @GET("v1/images/search")
    suspend fun requestBreedImageList(
        @QueryMap params: Map<String, String>
    ): Response<List<ImageDTO>>
}
