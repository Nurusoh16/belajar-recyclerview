package com.rajapulau.belajarrecyclerview;

import android.app.Activity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.rajapulau.belajarrecyclerview.R;

public class MainActivity extends Activity {
    ContactsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupViews();
    }

    void setupViews() {
        RecyclerView recyclerView = findViewById(R.id.recycler_view);

        adapter = new ContactsAdapter();

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

//        adapter.addMoreContacts(Contact.createContactsList(20));

        Button addMoreButton = findViewById(R.id.add_more_contacts);
        addMoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.addMoreContacts(Contact.createContactsList(5));
            }
        });
    }
}
