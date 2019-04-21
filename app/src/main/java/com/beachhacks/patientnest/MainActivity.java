package com.beachhacks.patientnest;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private ListView alerts;
    private ListView meds;

    private static final String TAG = "users";
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        alerts = (ListView)findViewById(R.id.lvAlerts);
        meds = (ListView)findViewById(R.id.lvMeds);

        List<String> arrAlerts = new ArrayList<>();
        arrAlerts.add("Skittles dose #2 in 5 hours");

        List<String> arrMeds = new ArrayList<>();
        arrMeds.add("M&M's");
        arrMeds.add("Dose: 100mg");
        arrMeds.add("Instructions: Take 100mg every 7 hours.");
        arrMeds.add("Last dosage date: 3/6/2018");
        arrMeds.add("Last dosage time: 4:00 pm");
        arrMeds.add("Provider: Nurse Alice");
        arrMeds.add("---------------------------------------");
        arrMeds.add("Skittles");
        arrMeds.add("Dose: 200mg");
        arrMeds.add("Instructions: Take 200mg every 5 hours.");
        arrMeds.add("Last dosage date: 4/19/2019");
        arrMeds.add("Last dosage time: 2:00 pm");
        arrMeds.add("Provider: Nurse Jane");

        ArrayAdapter<String> arrayAdapterAlerts = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrAlerts);
        alerts.setAdapter(arrayAdapterAlerts);

        ArrayAdapter<String> arrayAdapterMeds = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrMeds);
        meds.setAdapter(arrayAdapterMeds);

        db = FirebaseFirestore.getInstance();
        db.collection("users").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Log.d(TAG, document.getId() + " => " + document.getData());
                    }
                }
                else {
                    Log.d(TAG, "Error getting documents.", task.getException());
                }
            }
        });

    }
}
