package com.example.bingo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.bingo.DashBoardInterfaces.DashBoardModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Lobby extends AppCompatActivity   {

//
    ListView listView;
    Button btn_room;

    List<String>roomsList;

    String playerName="";
    String roomName="";

    FirebaseDatabase database;
    DatabaseReference roomRef;
    DatabaseReference roomsRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lobby);

        database=FirebaseDatabase.getInstance("https://bingo-proyecto-default-rtdb.europe-west1.firebasedatabase.app/");

        //get the player name and asign his room to the player name
        SharedPreferences preferences=getSharedPreferences("PREFS",0);
        playerName=preferences.getString("playerName","");
        roomName=playerName;

        listView=findViewById(R.id.ListView);
        btn_room=findViewById(R.id.Create_room);

        //all existing available room;
        roomsList=new ArrayList<>();

        btn_room.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //create room and add yourself as player1
                btn_room.setText("CREATING ROOM");
                btn_room.setEnabled(false);
                roomName=playerName;
                roomRef=database.getReference("rooms/"+roomName+"player1:");
                addRoomEventListener();
                roomRef.setValue(playerName);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //join an existing room and add yourselft as player2
                roomName=roomsList.get(i);
                roomRef=database.getReference("rooms/"+roomName+"player2:");
                addRoomEventListener();
                roomRef.setValue(playerName);
            }
        });
        //show if new room is available
        addRoomsEventeListener();
    }

    private void addRoomEventListener(){
        roomRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //join the room
                btn_room.setText("CREATING ROOM");
                btn_room.setEnabled(true);
                Intent intent=new Intent(getApplicationContext(),Sala.class);
                intent.putExtra("roomName",roomName);
                startActivity(intent);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                //error
                btn_room.setText("CREATING ROOM");
                btn_room.setEnabled(false);
                Toast.makeText(Lobby.this,"Error!",Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void addRoomsEventeListener(){
        roomsRef=database.getReference("rooms");
        roomsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //show list of rooms
                roomsList.clear();
                Iterable<DataSnapshot> rooms=snapshot.getChildren();
                for (DataSnapshot dataSnapshot : rooms){
                    roomsList.add(dataSnapshot.getKey());

                    ArrayAdapter<String> adapter=new ArrayAdapter<>(Lobby.this,
                            android.R.layout.simple_list_item_1,roomsList);
                    listView.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                //error-nothing
            }
        });
    }
}