package com.example.b07project;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

/**
 * An activity for handling complaints.
 */
public class ComplaintsPage extends AppCompatActivity {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public ComplaintsPage() {
        // Required empty public constructor
    }

    public static Intent newIntent(Context context, String param1, String param2) {
        Intent intent = new Intent(context, ComplaintsPage.class);
        intent.putExtra(ARG_PARAM1, param1);
        intent.putExtra(ARG_PARAM2, param2);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_complaints_page);

        Intent intent = getIntent();
        if (intent != null && intent.getExtras() != null) {
            mParam1 = intent.getStringExtra(ARG_PARAM1);
            mParam2 = intent.getStringExtra(ARG_PARAM2);
        }
    }
}
