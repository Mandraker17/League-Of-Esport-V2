package fr.cdsm.leagueofesportv2.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.afollestad.materialdialogs.MaterialDialog;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.concurrent.Executor;

import fr.cdsm.leagueofesportv2.R;
import fr.cdsm.leagueofesportv2.activity.InscriptionActivity;
import fr.cdsm.leagueofesportv2.activity.ProfilActivity;

public class FragmentAuthentification extends android.app.Fragment {

    EditText mEmail, mPassword;
    String email, password;
    private static final String TAG = "EmailPassword";
    private FirebaseAuth mAuth;
    public FirebaseUser user;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_authentification, container, false);
        mAuth = FirebaseAuth.getInstance();

        mEmail = (EditText) rootView.findViewById(R.id.et_mail_auth);
        mPassword = (EditText) rootView.findViewById(R.id.et_password_auth);

        return rootView;
    }

    public void connexionCompte(final View view) {
        email = mEmail.getText().toString();
        password = mPassword.getText().toString();

        if (email == null || password == null) {
            new MaterialDialog.Builder(getActivity())
                    .title("Completions des champs nécéssaires")
                    .content("Afin de vous connecter voyez remplir les champs")
                    .positiveText("OK")
                    .show();
        } else {

            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener((Executor) this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        //on envoye un message dans la console et on met à jour la variable utilisateur et on va a l'activité ProfilActivity
                        new MaterialDialog.Builder(getActivity())
                                .title("Connexion au profil")
                                .content("La connexion au profil est Réussi ! Profitez bien")
                                .positiveText("OK")
                                .show();
                        user = mAuth.getCurrentUser();
                        goProfil(view);

                    } else {
                        // Si c'est une erreur on affiche un message d'erreur dans la console et en Toast
                        new MaterialDialog.Builder(getActivity())
                                .title("Connexion au profil")
                                .content("La connexion au profil a Echouée ! Veuillez verifier vos identifiants et mot de passes")
                                .positiveText("OK")
                                .show();
                    }
                }
            });
        }
    }

    public void forgotPassword(final View view) {
        new MaterialDialog.Builder(getActivity())
                .title("Demande de nouveau mot de passe")
                .content("Veuillez indiquez votre adresse email pour l'envoye d'une demande de réinitialisation de mot de passe")
                .inputType(InputType.TYPE_CLASS_TEXT)
                .input("", "", new MaterialDialog.InputCallback() {
                    @Override
                    public void onInput(@NonNull MaterialDialog dialog, CharSequence input) {
                        mAuth.sendPasswordResetEmail(String.valueOf(input))
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {

                                            new MaterialDialog.Builder(getActivity())
                                                    .title("Demande de réinitialisation de mot de passe")
                                                    .content("Un mail vous a été envoyé pour réinitialiser votre mot de passe. Suivez les instructions et réessayez après.")
                                                    .positiveText("OK")
                                                    .show();
                                        }
                                    }
                                });
                    }
                }).show();
    }

    public void goProfil(View view) {
        Intent intent = new Intent(getActivity(), ProfilActivity.class);
        startActivity(intent);
    }

    public void goInscription(View view) {
        Intent intent = new Intent(getActivity(), InscriptionActivity.class);
        startActivity(intent);
    }
}

