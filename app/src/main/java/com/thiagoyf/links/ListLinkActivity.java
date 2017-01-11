package com.thiagoyf.links;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import adapters.LinkAdapter;
import daos.LinkDao;
import models.Link;

public class ListLinkActivity extends AppCompatActivity {
    private LinkDao linkDao;
    private List<Link> links;
    private LinkAdapter linkAdapter;

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_link);
        linkDao = new LinkDao(this);
        links = linkDao.listLink();

        linkAdapter = new LinkAdapter(this, links);

        listView = (ListView) findViewById(R.id.activity_list_link_lv_listLink);
        listView.setAdapter(linkAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Link link = (Link) adapterView.getItemAtPosition(i);

                Intent intent = new Intent(ListLinkActivity.this, LinkActivity.class);
                intent.putExtra("LINK_ID", link.getId());
                intent.putExtra("LINK_TITLE", link.getTitle());
                intent.putExtra("LINK_CATEGORY", link.getCategory());
                intent.putExtra("LINK_URL", link.getUrl());
                intent.putExtra("LINK_STATUS", link.getStatus());

                finish();
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onDestroy() {
        linkDao.close();
        super.onDestroy();
    }
}
