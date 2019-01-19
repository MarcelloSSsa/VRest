package android.app.com.vrest.activity;

import android.app.com.vrest.R;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class DescriptionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_description );

        Bundle extra = getIntent().getExtras();

        String idCharacter = null;

        if (extra != null){
            idCharacter = extra.getString( "id" );
        }
        Toast.makeText( getApplicationContext(), idCharacter,Toast.LENGTH_SHORT ).show();
    }
}
