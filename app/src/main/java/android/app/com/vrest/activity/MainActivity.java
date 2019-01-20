package android.app.com.vrest.activity;

import android.app.com.vrest.ApiService;
import android.app.com.vrest.R;
import android.app.com.vrest.adapter.AdapterCharacterCustom;
import android.app.com.vrest.models.Info;
import android.app.com.vrest.models.Result;
import android.app.com.vrest.models.CharacterCatalog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private ListView listaCharacters;
    private Button prev;
    private Button next;
    private TextView countPageText;
    private int countPage = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_main);

        listaCharacters = findViewById( R.id.charactersList );
        countPageText = findViewById( R.id.id_count_page );
        prev = findViewById( R.id.id_button_prev );
        next = findViewById( R.id.id_next_button );

        countPageText.setText( "" );
        prev.setVisibility( View.GONE );

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl( ApiService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final ApiService service = retrofit.create(ApiService.class);
        Call<CharacterCatalog> requestCatalog = service.listCatalog();

        prev.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (countPage == 1){
                    prev.setVisibility( View.GONE );
                }else{
                    next.setVisibility( View.VISIBLE );
                    countPage--;

                    if (countPage == 1)
                        prev.setVisibility( View.GONE );

                    Call<CharacterCatalog> requestPageCatalogue = service.listPage( countPage );
                    requestNewPage(requestPageCatalogue);
                }
            }
        } );

        next.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (countPage == 20){
                    next.setVisibility( View.GONE );
                }else{
                    prev.setVisibility( View.VISIBLE );
                    countPage++;

                    if (countPage == 20)
                        next.setVisibility( View.GONE );

                    Call<CharacterCatalog> requestPageCatalogue = service.listPage( countPage );
                    requestNewPage(requestPageCatalogue);
                }
            }
        } );

        requestCatalog.enqueue(new Callback<CharacterCatalog>() {
            @Override
            public void onResponse(Call<CharacterCatalog> call, Response<CharacterCatalog> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(MainActivity.this, "Erro no servidor ("
                            + response.code() + ")",Toast.LENGTH_SHORT).show();
                    Log.e("ERRO", "Erro no servidor (" + response.code() + ")");
                }
                else {
                    final CharacterCatalog catalog = response.body();
                    showListCharacter(catalog);
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

    private void requestNewPage(Call<CharacterCatalog> requestPageCatalogue) {
        requestPageCatalogue.enqueue( new Callback<CharacterCatalog>() {
            @Override
            public void onResponse(Call<CharacterCatalog> call, Response<CharacterCatalog> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(MainActivity.this, "Erro no servidor ("
                            + response.code() + ")",Toast.LENGTH_SHORT).show();
                    Log.e("ERRO", "Erro no servidor (" + response.code() + ")");
                }
                else {
                    final CharacterCatalog catalog = response.body();
                    showListCharacter(catalog);
                }
            }

            @Override
            public void onFailure(Call<CharacterCatalog> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Verifique a internet (" + t.getMessage() + ")"
                        , Toast.LENGTH_SHORT).show();
                Log.e("ERRO", "Verifique a conexão com a internet (" + t.getMessage() + ")");
            }
        } );
    }

    private void showListCharacter(CharacterCatalog catalog) {

        AdapterCharacterCustom adapter =
                new AdapterCharacterCustom(catalog,MainActivity.this);

        listaCharacters.setAdapter(adapter);

        listaCharacters.setOnItemClickListener( new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent( MainActivity.this, DescriptionActivity.class );
                Result r = (Result) parent.getAdapter().getItem( position );
                intent.putExtra( "id", r.getId() );
                intent.putExtra( "name", r.getName() );
                intent.putExtra( "status", r.getStatus() );
                intent.putExtra( "species", r.getSpecies() );
                intent.putExtra( "gender", r.getGender() );
                intent.putExtra( "image", r.getImage() );
                intent.putExtra( "origin", r.getOrigin().getName() );

                startActivity( intent );
            }
        } );
    }
}
