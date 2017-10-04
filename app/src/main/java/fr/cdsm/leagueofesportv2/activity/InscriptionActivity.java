package fr.cdsm.leagueofesportv2.activity;

import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.afollestad.materialdialogs.MaterialDialog;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

import fr.cdsm.leagueofesportv2.R;

public class InscriptionActivity extends AppCompatActivity {

    EditText mEmail, mPassword;
    String email, password;
    private FirebaseAuth mAuth;
    public FirebaseUser user;
    Button btn_create;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);

        mAuth = FirebaseAuth.getInstance();
        mEmail = (EditText) findViewById(R.id.et_email_sign);
        mPassword = (EditText) findViewById(R.id.et_password_sign);
        btn_create = (Button) findViewById(R.id.btn_create);

        btn_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createAccount(v);
            }
        });
    }

    public void createAccount(View view) {
        email = mEmail.getText().toString();
        password = mPassword.getText().toString();

        if ("".equals(email) || "".equals(password)) {
            new MaterialDialog.Builder(this)
                    .title("Completion des champs nécéssaires")
                    .content("Afin de vous connecter voyez remplir les champs")
                    .positiveText("OK")
                    .show();
        } else {
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        user = mAuth.getCurrentUser();

                        new MaterialDialog.Builder(InscriptionActivity.this)
                                .title("Création du profil")
                                .content("La création de votre profil profil est Réussie ! Vous allez être redirigé vers l'écran de connexion")
                                .positiveText("OK")
                                .dismissListener(new DialogInterface.OnDismissListener() {
                                    @Override
                                    public void onDismiss(DialogInterface dialog) {
                                        onBackPressed();
                                    }
                                })
                                .show();

                    } else {
                        new MaterialDialog.Builder(InscriptionActivity.this)
                                .title("Création au profil")
                                .content("La création de votre profil est a échouez ! Veuillez nous excuser")
                                .positiveText("OK")
                                .show();
                    }
                }
            });
        }
    }
}
