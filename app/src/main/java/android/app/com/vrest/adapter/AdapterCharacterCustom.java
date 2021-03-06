package android.app.com.vrest.adapter;

import android.app.Activity;
import android.app.com.vrest.R;
import android.app.com.vrest.models.CharacterCatalog;
import android.app.com.vrest.models.Result;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;


public class AdapterCharacterCustom extends BaseAdapter {

    private final CharacterCatalog characters;
    private final Activity act;

    public AdapterCharacterCustom(CharacterCatalog characters, Activity act){
        this.characters = characters;
        this.act = act;
    }

    @Override
    public int getCount() {
        return characters.results.size();
    }

    @Override
    public Object getItem(int position) {
        return characters.results.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = act.getLayoutInflater().inflate(R.layout.lista_characters_custom, parent, false);

        Result character = characters.results.get(position);

        TextView id = (TextView)
                view.findViewById(R.id.lista_characters_custom_id);
        TextView nome = (TextView)
                view.findViewById(R.id.lista_characters_custom_name);
        TextView status = (TextView)
                view.findViewById(R.id.lista_characters_custom_description);
        ImageView image = (ImageView)
                view.findViewById(R.id.lista_characters_custom_image);

        Picasso.with( act ).load(character.getImage()).into(image);

        //image.setImageResource(result.origin.url);
        id.setText( character.getId() );
        nome.setText(character.getName());
        status.setText(character.getStatus());

        return view;
    }
}
