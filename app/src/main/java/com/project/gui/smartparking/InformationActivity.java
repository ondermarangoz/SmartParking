package com.project.gui.smartparking;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

import static java.lang.Integer.parseInt;

public class InformationActivity extends Activity {
    String str;
    int cnt = 0;
    int cnt2 = 0;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        textView = findViewById(R.id.parking_limitText);







        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("Malls/ruyapark");


        // Attach a listener to read the data at our posts reference
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                System.out.println(dataSnapshot.getValue().toString());


                str = dataSnapshot.getValue().toString();
                String second = "=1";
                String third = "=0";
                int ind = 0;

                while (true) {
                    int pos = str.indexOf(second, ind);
                    if (pos < 0) break;
                    cnt++;
                    ind = pos + 1; // Advance by second.length() to avoid self repetitions
                }
                System.out.println(cnt);

                int ind2 = 0;
                while (true) {
                    int pos2 = str.indexOf(third, ind2);
                    if (pos2 < 0) break;
                    cnt2++;
                    ind2 = pos2 + 1; // Advance by second.length() to avoid self repetitions
                }
                System.out.println(cnt2);



                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        textView.setText(cnt+"/"+(cnt+cnt2)+" of parking space is full");
                    }
                }).start();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
