package com.azismihsan.scholarshipinformation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvScholarship;
    private ArrayList<scholarship> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvScholarship = findViewById(R.id.rv_scholarship);
        rvScholarship.setHasFixedSize(true);

        list.addAll(scholarshipdata.getListData());
        showRecyclerList();
    }

    private void showRecyclerList(){
        rvScholarship.setLayoutManager(new LinearLayoutManager(this));
        ListScholarshipAdapter ListScholarshipHolder = new ListScholarshipAdapter(list);
        rvScholarship.setAdapter(ListScholarshipHolder);
    }
}
