package com.example.bingo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
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

public class Sala extends AppCompatActivity {

    Button btn_BINGO;
    Button btn_LINEA;

    String playerName = "";
    String roomName = "";
    String role = "";
    String message = "";

    FirebaseDatabase database;
    DatabaseReference messageRef;

    Game game = new Game();
    Game game2 = new Game();
    Game game3 = new Game();
    Game game4 = new Game();
    Game game5 = new Game();
    Game game6 = new Game();
    Game game7 = new Game();
    Game game8 = new Game();
    Game game9 = new Game();
    Game game10 = new Game();

    TextView numeroCartones;

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

//numero cartones
        //numeroCartones.setText(modelo.jugador.getNumCartones());

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
        carton1.setText(game.toString());
        carton2.setText(game2.toString());
        carton3.setText(game3.toString());
        carton4.setText(game4.toString());
        carton5.setText(game5.toString());
        carton6.setText(game6.toString());
        carton7.setText(game7.toString());
        carton8.setText(game8.toString());
        carton9.setText(game9.toString());
        carton10.setText(game10.toString());



        btn_BINGO = findViewById(R.id.btn_BINGO);
        btn_LINEA = findViewById(R.id.btn_LINEA);

        // btn_BINGO.setEnabled(false);
        database = FirebaseDatabase.getInstance("https://bingo-proyecto-default-rtdb.europe-west1.firebasedatabase.app/");

        SharedPreferences preferences = getSharedPreferences("PREFS", 0);
        playerName = preferences.getString("playerName", "");

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            roomName = extras.getString("roomName");
            if (roomName.equals(playerName))
                role = "host";
            else {
                role = "guest";
            }


            btn_BINGO.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    salaName.setText(modelo.jugador.getName());

                    //send message
                    btn_BINGO.setEnabled(true);
                    message=role+":Poked!";
                    messageRef.setValue(message);


                }
            });

            //listen for incoming message
            messageRef = database.getReference("rooms/" + roomName + "/message");
            message = role + " :Poked!";
            messageRef.setValue(message);
            addRoomEventListener();
        }
    }

    private void addRoomEventListener() {
        messageRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //mesagge recieved
                if (role.equals("host")) {
                    if (snapshot.getValue(String.class).contains("guest:")) {
                        btn_BINGO.setEnabled(true);
                        Toast.makeText(Sala.this,
                                "" + snapshot.getValue(String.class).replace("guest:", ""),
                                Toast.LENGTH_SHORT).show();
                    }
                } else {
                    if (snapshot.getValue(String.class).contains("host:")) {
                        btn_BINGO.setEnabled(true);
                        Toast.makeText(Sala.this,
                                "" + snapshot.getValue(String.class).replace("host:", ""),
                                Toast.LENGTH_SHORT).show();
                    }
                }
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