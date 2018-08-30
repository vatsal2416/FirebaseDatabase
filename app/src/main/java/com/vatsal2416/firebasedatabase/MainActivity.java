package com.vatsal2416.firebasedatabase;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView lview;
    Button btn;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lview = findViewById(R.id.lview);

        btn = findViewById(R.id.button2);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        reference = database.getReference("Users");
        final List<String> list = new ArrayList<>();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Iterable<DataSnapshot> data = dataSnapshot.getChildren();
                        for(DataSnapshot data1 : data){
                            String msg = "";
                            msg = msg +"\n"+data1.getValue();
                            list.add(msg);
                        }

                        ArrayAdapter<String> myadapter = new ArrayAdapter<String>
                                (MainActivity.this,android.R.layout.simple_list_item_1,list);
                        lview.setAdapter(myadapter);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(getApplicationContext(),"Database Error!",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }
}
