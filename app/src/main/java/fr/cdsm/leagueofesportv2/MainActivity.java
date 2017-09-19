package fr.cdsm.leagueofesportv2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    News actu;
    public TextView textView;
    DatabaseReference newsDatabase = FirebaseDatabase.getInstance().getReference();
    ArrayList<News> testarraylist = new ArrayList<News>();
    public MaterialDialog material;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.text1);

        material = new MaterialDialog.Builder(this)
                .title("Hello")
                .content("Coucou")
                .progress(true, 0)
                .show();

        getData();
    }

    private void getData() {
        newsDatabase.child("News").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot news : dataSnapshot.getChildren()) {
                    actu = news.getValue(News.class);
                }
                textView.setText(actu.text);
                material.cancel();
            }

            @Override

            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
}
