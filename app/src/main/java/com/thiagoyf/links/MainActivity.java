package com.thiagoyf.links;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import adapters.SearchLinkAdapter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void actionClick(View view){
        switch (view.getId()){
            case R.id.activity_main_create:
                startActivity(new Intent(this, CreateLinkActivity.class));
                break;
            case R.id.activity_main_list:
                startActivity(new Intent(this, ListLinkActivity.class));
                break;
            case  R.id.activity_main_search:
                startActivity(new Intent(this, SearchLinkActivity.class));
                break;
            default:
                break;
        }
    }
}
