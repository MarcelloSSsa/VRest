package android.app.com.vrest;

import android.app.com.vrest.models.CharacterCatalog;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    public static final  String BASE_URL = "https://rickandmortyapi.com/api/";

    @GET("character")
    Call<CharacterCatalog> listCatalog();
}
