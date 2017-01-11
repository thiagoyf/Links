package com.thiagoyf.links;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import daos.LinkDao;
import models.Link;

public class LinkActivity extends AppCompatActivity {
    private TextView txtTitle, txtCategory, txtUrl;

    private LinkDao linkDao;
    private Link link;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_link);

        txtTitle = (TextView) findViewById(R.id.activity_link_title);
        txtCategory = (TextView) findViewById(R.id.activity_link_category);
        txtUrl = (TextView) findViewById(R.id.activity_link_url);

        linkDao  = new LinkDao(this);

        setFields();
    }

    private void setFields() {
        link = new Link();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            buildLembrete(extras);

            String title = getString(R.string.create_link_title) + link.getTitle();
            String category = getString(R.string.create_link_category) + link.getCategory();
            String url = getString(R.string.create_link_url) + link.getUrl();

            txtTitle.setText(title);
            txtCategory.setText(category);
            txtUrl.setText(url);
        }
    }

    private void buildLembrete(Bundle extras) {
        link.setId(extras.getInt("LINK_ID"));
        link.setTitle(extras.getString("LINK_TITLE"));
        link.setCategory(extras.getString("LINK_CATEGORY"));
        link.setUrl(extras.getString("LINK_URL"));
        link.setStatus(extras.getInt("LINK_STATUS"));
    }

    public void readLink(View view) {
        link.setStatus(1);
        long result = linkDao.updateLink(link);

        finish();
        startActivity(new Intent(this, ListLinkActivity.class));
    }

    @Override
    protected void onDestroy() {
        linkDao.close();
        super.onDestroy();
    }
}
