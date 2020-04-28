package com.sparkysparkybooman.vk.hwmhelp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class About extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(R.string.about_bar);

    }

    public void onGooglePlayClick(View view) {
        Intent gpIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.gp_link)));
        startActivity(gpIntent);
    }

    public void onLWMClick(View view) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.hwm_reflink)));
        startActivity(browserIntent);
    }

    public void onVKClick(View view) {
        Intent vkIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.vk_link)));
        startActivity(vkIntent);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(About.this, MainActivity.class);
                startActivity(intent);
                finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
