package com.thiagoyf.links;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import daos.LinkDao;
import models.Link;

public class CreateLinkActivity extends AppCompatActivity {
    private EditText edtTitle, edtCategory, edtUrl;

    private LinkDao linkDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_link);

        edtTitle = (EditText) findViewById(R.id.activity_create_link_title);
        edtCategory = (EditText) findViewById(R.id.activity_create_link_category);
        edtUrl = (EditText) findViewById(R.id.activity_create_link_url);

        linkDao = new LinkDao(this);
    }

    public boolean validate(){
        boolean valid = true;

        String tilte = edtTitle.getText().toString();
        String category = edtCategory.getText().toString();
        String url = edtUrl.getText().toString();

        if (tilte.equals("")){
            valid = false;
            edtTitle.setError(getString(R.string.message_required_field));
        }

        if (category.equals("")){
            valid = false;
            edtCategory.setError(getString(R.string.message_required_field));
        }

        if (url.equals("")){
            valid = false;
            edtUrl.setError(getString(R.string.message_required_field));
        }

        return valid;
    }

    public void createLink(View view){
        String title = edtTitle.getText().toString();
        String category = edtCategory.getText().toString();
        String url = edtUrl.getText().toString();

        boolean validate = validate();

        if (validate) {
            Link link = new Link();
            link.setTitle(title);
            link.setCategory(category);
            link.setUrl(url);
            link.setStatus(0);

            if (linkDao.searchLinkByTitle(title) == null) {
                long resultado = linkDao.createLink(link);

                if (resultado != -1) {
                    Toast.makeText(this, getString(R.string.message_link_registered_success),
                            Toast.LENGTH_LONG).show();

                    finish();
                    startActivity(new Intent(this, MainActivity.class));
                } else {
                    Toast.makeText(this, getString(R.string.message_link_registered_fail),
                            Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(this, getString(R.string.message_link_already_registered),
                        Toast.LENGTH_LONG).show();
            }
        }
    }
}
