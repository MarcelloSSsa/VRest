package android.app.com.vrest.activity;

import android.app.com.vrest.R;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DescriptionActivity extends AppCompatActivity {

    private String idCharacter = null;
    private String nameCharacter = null;
    private String statusCharacter = null;
    private String speciesCharacter = null;
    private String genderCharacter = null;
    private String imageCharacter = null;
    private String originCharacter = null;

    private TextView idText;
    private TextView nameText;
    private TextView statusText;
    private TextView speciesText;
    private TextView genderText;
    private ImageView imageCharacterProfile;
    private TextView originText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_description );

        imageCharacterProfile = findViewById( R.id.id_image_character );

        idText = findViewById( R.id.id_id_text );
        nameText = findViewById( R.id.id_name_text );
        statusText = findViewById( R.id.id_stetus_text );
        speciesText = findViewById( R.id.id_specie_text );
        genderText = findViewById( R.id.id_gender_text );
        originText = findViewById( R.id.id_local_text );


        Bundle extra = getIntent().getExtras();

        if (extra != null){
            idCharacter = extra.getString( "id" );
            nameCharacter = extra.getString( "name" );
            statusCharacter = extra.getString( "status" );
            speciesCharacter = extra.getString( "species" );
            genderCharacter = extra.getString( "gender" );
            //imageCharacter = extra.getString( "image" );
            originCharacter = extra.getString( "origin" );
        }
        idText.setText( idCharacter );
        nameText.setText( nameCharacter );
        statusText.setText( statusCharacter );
        speciesText.setText( speciesCharacter );
        genderText.setText( genderCharacter );
        originText.setText( originCharacter );
    }
}
