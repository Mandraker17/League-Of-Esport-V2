package fr.cdsm.leagueofesportv2.activity;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import fr.cdsm.leagueofesportv2.R;

public class ProfilActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    FirebaseUser user;
    Button btn_disco, btn_delete;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);


        btn_delete = (Button) findViewById(R.id.btn_delete);
        btn_disco = (Button) findViewById(R.id.btn_disconnect);

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeleteUser(v);
            }
        });

        btn_disco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Deconnect(v);
            }
        });


        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        if (user != null) {

            //Traitement pour afficher les infos du user sur les TextViews

            String email = user.getEmail();
            String uid = user.getUid();

            TextView textView_team = (TextView) findViewById(R.id.txt_team_profil);
            TextView textView_email = (TextView) findViewById(R.id.txt_email);

            textView_email.setText(email);
            textView_team.setText(uid);
        }
    }


    //Fonction pour se deconnecter
    public void Deconnect(View view) {
        mAuth.getInstance().signOut();
        onBackPressed();
    }

    //Fonction pour Supprimer son compte
    public void DeleteUser(View view) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        user.delete()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            new MaterialDialog.Builder(ProfilActivity.this)
                                    .title("Demande de suppression de compte")
                                    .content("Votre compte a été supprimé, vous allez retourner à l'écran de connexion.")
                                    .positiveText("OK")
                                    .show();
                        } else {
                            new MaterialDialog.Builder(ProfilActivity.this)
                                    .title("Demande de suppression de compte")
                                    .content("Une erreur est survenue, veuillez nous excusez.")
                                    .positiveText("OK")
                                    .show();
                        }
                    }
                });
        onBackPressed();
    }

}
