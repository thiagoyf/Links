package com.thiagoyf.links;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import adapters.SearchLinkAdapter;
import daos.LinkDao;
import models.Link;

public class SearchLinkActivity extends AppCompatActivity {
    private LinkDao linkDao;
    private SearchLinkAdapter searchLinkAdapter;
    private List<Link> links;

    private ListView listView;
    private EditText edtTitle, edtCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_link);

        edtTitle = (EditText) findViewById(R.id.activity_search_link_title);
        edtCategory= (EditText) findViewById(R.id.activity_search_link_category);

        linkDao = new LinkDao(this);
        links = new ArrayList<Link>();

        listView = (ListView) findViewById(R.id.activity_list_link_lv_listLink);

    }

    public void searchLink(View view){
        String title = edtTitle.getText().toString();
        String category = edtCategory.getText().toString();

        Toast.makeText(this, "Há Fazer", Toast.LENGTH_LONG).show();
    }
}
