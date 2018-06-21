package com.tekbeast.mywallpapers.Remote;

import com.tekbeast.mywallpapers.data.model.Wallpaper;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

public interface WallpaperApi {
    String BASE_URL = "https://api.unsplash.com/";
    String API_KEY = "efbb067a453b8cbc126053a21d2f066c265aec30b3af81f0ec84fc6eaf553ed2";


    @GET("photos/?client_id=" + API_KEY)
    Call<List<Wallpaper>> getWallpapers(@Query("order_by") String orderBy,
                                        @Query("per_page") int perPage,
                                        @Query("page") int page);

    @GET
    @Streaming
    Call<ResponseBody> downloadImage(@Url String fileURL);

    class Factory {
        private static WallpaperApi service;
        public static WallpaperApi getInstance() {
            if (service == null) {
                Retrofit retrofit = new Retrofit.Builder()
                        .addConverterFactory(GsonConverterFactory.create())
                        .baseUrl(BASE_URL)
                        .build();
                service = retrofit.create(WallpaperApi.class);
                return service;
            } else {
                return service;
            }
        }
    }
}
