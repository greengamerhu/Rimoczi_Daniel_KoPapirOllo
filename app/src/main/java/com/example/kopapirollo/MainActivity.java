package com.example.kopapirollo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private ImageView imageViewUser, imageViewGep;
    private Button buttonKo, buttonPapir, buttonOllo;
    private TextView textViewOut;
    private String[] seged = {"ko","papir","ollo"};
    private String gepPick= "";
    private String userPick= "";
    private int UserPoints = 0;
    private int GepPoints = 0;
    private AlertDialog.Builder ujJatek;
    Random rnd = new Random();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        buttonKo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Jatek("ko");
            }
        });
        buttonPapir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Jatek("papir");
            }
        });
        buttonOllo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Jatek("ollo");
            }
        });

    }

    private void Jatek(String userPick1) {
        userPick = userPick1;
        gepPick = seged[rnd.nextInt(3)];
        if (gepPick.equals(userPick)) {
            Toast.makeText(MainActivity.this, "Döntetlen", Toast.LENGTH_SHORT).show();
            kepSet();
        } else if(gepPick == "ko" && userPick == "papir" ||
                gepPick == "papir" && userPick == "ollo" ||
                gepPick == "ollo" && userPick == "ko") {
            UserPoints++;
            Toast.makeText(MainActivity.this, "Nyertél yey", Toast.LENGTH_SHORT).show();
            textViewOut.setText(String.format("Eredmény: Ember: %d Computer: %d", UserPoints, GepPoints));
            kepSet();
        } else {
            GepPoints++;
            Toast.makeText(MainActivity.this, "A gép nyert :(((", Toast.LENGTH_SHORT).show();
            textViewOut.setText(String.format("Eredmény: Ember: %d Computer: %d", UserPoints, GepPoints));
            kepSet();
        }
        if (GepPoints == 3) {
            ujJatek.setTitle("Vesztettél").show();
        }
        if (UserPoints == 3) {
            ujJatek.setTitle("Nyertél!!!").show();

        }
    }
    private void kepSet() {
        if(userPick == "ko" ) {
            imageViewUser.setImageResource(R.drawable.rock);
        }
        if(userPick == "papir" ) {
            imageViewUser.setImageResource(R.drawable.paper);
        }
        if(userPick == "ollo" ) {
            imageViewUser.setImageResource(R.drawable.scissors);
        }
        if(gepPick == "ko" ) {
            imageViewGep.setImageResource(R.drawable.rock);
        }
        if(gepPick == "papir" ) {

            imageViewGep.setImageResource(R.drawable.paper);
        }
        if (gepPick == "ollo") {
            imageViewGep.setImageResource(R.drawable.scissors);
        }
        if (GepPoints == 3) {

        }
    }
    private void ujJatek() {
        GepPoints = 0;
        UserPoints = 0;
        textViewOut.setText(String.format("Eredmény: Ember: %d Computer: %d", UserPoints, GepPoints));
        userPick = "";
        gepPick  = "";
        imageViewUser.setImageResource(R.drawable.rock);
        imageViewGep.setImageResource(R.drawable.rock);



    }
    private void init() {
        imageViewGep = findViewById(R.id.imageviewGep);
        imageViewUser = findViewById(R.id.imageviewUser);
        buttonKo = findViewById(R.id.buttonKo);
        buttonPapir = findViewById(R.id.buttonPapir);
        buttonOllo = findViewById(R.id.buttonOllo);
        textViewOut = findViewById(R.id.textviewOut);
        ujJatek = new AlertDialog.Builder(MainActivity.this);
        ujJatek.setCancelable(false).setTitle("")
                .setMessage("Szeretnél új játékot kezdeni?")
                .setNegativeButton("Nem", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                })
                .setPositiveButton("Igen", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ujJatek();
                    }
                }).create();
    }
}