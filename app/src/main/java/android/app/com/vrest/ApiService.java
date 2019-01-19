package android.app.com.vrest;

import android.app.com.vrest.models.CharacterCatalog;
import android.app.com.vrest.models.Result;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    public static final  String BASE_URL = "https://rickandmortyapi.com/api/";

    @GET("character")
    Call<CharacterCatalog> listCatalog();

    @GET("character/")
    Call<Result> listCharacter(@Query( "id" ) String id);
}
