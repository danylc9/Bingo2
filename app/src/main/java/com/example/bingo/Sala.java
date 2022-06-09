package com.example.bingo;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.example.bingo.DashBoardInterfaces.DashBoardModel;
import com.example.bingo.Data.Game;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class Sala extends AppCompatActivity {
    ViewModelGeneral viewModel = new ViewModelGeneral();

    Button btn_BINGO;
    Button btn_LINEA;

    Button btn_SacarNumero;

    String playerName = "";
    String roomName = "";
    String message = "";

    FirebaseDatabase database;
    DatabaseReference messageRef;
    DatabaseReference messageRefBingo;
    DatabaseReference messageRefLinea;
    DatabaseReference messageRefNumero;



    TextView numeroCartones;
    TextView textStatus;
    TextView textNumber;

    TextView carton1;
    TextView salaName;
    TextView carton2;
    TextView carton3;
    TextView carton4;
    TextView carton5;
    TextView carton6;
    TextView carton7;
    TextView carton8;
    TextView carton9;
    TextView carton10;


    DashBoardModel modelo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sala);

        carton1 = findViewById(R.id.TextTestMatrix);
        salaName = findViewById(R.id.textSala);
        numeroCartones = findViewById(R.id.numCartones);
        textStatus = findViewById(R.id.textStatus);
        btn_SacarNumero = findViewById(R.id.but_SacarNumero);
        textNumber = findViewById(R.id.textNumber);


//numero cartones

        //cartones
        carton2 = findViewById(R.id.carton2);
        carton3 = findViewById(R.id.carton3);
        carton4 = findViewById(R.id.carton4);
        carton5 = findViewById(R.id.carton5);
        carton6 = findViewById(R.id.carton6);
        carton7 = findViewById(R.id.carton7);
        carton8 = findViewById(R.id.carton8);
        carton9 = findViewById(R.id.carton9);
        carton10 = findViewById(R.id.carton10);

        //inicializar cartones
        carton1.setText(viewModel.game.toString());
        carton2.setText(viewModel.game2.toString());
        carton3.setText(viewModel.game3.toString());
        carton4.setText(viewModel.game4.toString());
        carton5.setText(viewModel.game5.toString());
        carton6.setText(viewModel.game6.toString());
        carton7.setText(viewModel.game7.toString());
        carton8.setText(viewModel.game8.toString());
        carton9.setText(viewModel.game9.toString());
        carton10.setText(viewModel.game10.toString());


        btn_BINGO = findViewById(R.id.btn_BINGO);
        btn_LINEA = findViewById(R.id.btn_LINEA);


        numeroCartones.setText(String.valueOf(viewModel.getNumCartones()));


        // btn_BINGO.setEnabled(false);
        database = FirebaseDatabase.getInstance("https://bingo-proyecto-default-rtdb.europe-west1.firebasedatabase.app/");

        SharedPreferences preferences = getSharedPreferences("PREFS", 0);
        playerName = preferences.getString("playerName", "");

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            roomName = extras.getString("roomName");
            //sala name
            salaName.setText(roomName);

            if (!roomName.equals("Sala: " + viewModel.getNameJugador())) {
                btn_SacarNumero.setEnabled(false);
            }
            btn_SacarNumero.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    int min_val = 0;
                    int max_val = 99;

                    ThreadLocalRandom tlr = ThreadLocalRandom.current();
                    int randomNum = tlr.nextInt(min_val, max_val);

                    while (viewModel.generadosContiene(randomNum)) {
                        randomNum = tlr.nextInt(min_val, max_val);
                    }
                    viewModel.generadosAdd(randomNum);
                    textNumber.setText(String.valueOf(randomNum));


                    //send number
                    message = String.valueOf(randomNum);
                    messageRefNumero.setValue(message);


                }
            });

            btn_BINGO.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    //send message
                    message = "BINGO!";
                    messageRefBingo.setValue(message);
                    messageRefLinea.setValue(message);



                }
            });

            btn_LINEA.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    //send message
                    message = "LINEA!";
                    messageRefLinea.setValue(message);


                }
            });
            //listen for incoming message
            messageRef = database.getReference("rooms/" + roomName + "/players" + "/" + viewModel.getNameJugador());
            message = "online";
            messageRef.setValue(message);

            messageRefBingo = database.getReference("rooms/" + roomName + "/bingo");
            messageRefLinea = database.getReference("rooms/" + roomName + "/linea");
            messageRefNumero = database.getReference("rooms/" + roomName + "/numero");


            if (viewModel.isPrimeraVez()) {
                messageRefBingo.setValue("");
                messageRefLinea.setValue("");
                messageRefNumero.setValue("0");
                viewModel.setPrimeraVezFalse();

            }
            addRoomEventListener();

        }
    }

    private void addRoomEventListener() {
        messageRefBingo.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.getValue(String.class).equals("BINGO!")) {
                    textStatus.setText("Game finished");
                    textStatus.setTextColor(Color.RED);
                    btn_BINGO.setEnabled(false);
                    btn_LINEA.setEnabled(false);
                    btn_SacarNumero.setEnabled(false);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                //error-retry
                messageRef.setValue(message);
            }
        });
        messageRefLinea.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.getValue(String.class).equals("LINEA!")) {
                    textStatus.setText("Ha salido linea");
                    textStatus.setTextColor(Color.BLUE);
                    btn_LINEA.setEnabled(false);
                }


            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                //error-retry
                messageRef.setValue(message);
            }
        });

        messageRefNumero.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                textNumber.setText(String.valueOf(snapshot.getValue(String.class)));


            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                //error-retry
                messageRef.setValue(message);
            }
        });


    }

    @Override
    public void onDestroy() {

        super.onDestroy();
    }

}