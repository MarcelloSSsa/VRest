package android.app.com.vrest;

import android.app.com.vrest.adapter.AdapterCharacterCustom;
import android.app.com.vrest.models.Result;
import android.app.com.vrest.models.Origin;
import android.app.com.vrest.models.CharacterCatalog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private ListView listaCharacters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listaCharacters = findViewById( R.id.charactersList );

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService service = retrofit.create(ApiService.class);
        Call<CharacterCatalog> requestCatalog = service.listCatalog();

        requestCatalog.enqueue(new Callback<CharacterCatalog>() {
            @Override
            public void onResponse(Call<CharacterCatalog> call, Response<CharacterCatalog> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(MainActivity.this, "Erro no servidor ("
                            + response.code() + ")",Toast.LENGTH_SHORT).show();
                    Log.e("ERRO", "Erro no servidor (" + response.code() + ")");
                }
                else {
                    CharacterCatalog catalog = response.body();
                    for (Result c : catalog.results){
                        Log.i("RESULT", String.format("id; %s, name: %s\nNome origin: %s, URL: %s\n"
                                ,c.id, c.name, c.origin.name, c.origin.url));

                        Log.i("RESULT", "/////////////////////////////////////////");
                    }

                    AdapterCharacterCustom adapter =
                            new AdapterCharacterCustom(catalog,MainActivity.this);

                    listaCharacters.setAdapter(adapter);
                }

            }

            @Override
            public void onFailure(Call<CharacterCatalog> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Verifique a internet (" + t.getMessage() + ")"
                        , Toast.LENGTH_SHORT).show();
                Log.e("ERRO", "Verifique a conexão com a internet (" + t.getMessage() + ")");
            }
        });
    }
}
