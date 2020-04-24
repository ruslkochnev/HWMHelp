package com.sparkysparkybooman.vk.hwmhelp;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    public AdView mAdView;
    public WebView wb;
    public DrawerLayout drawer;

    class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

    protected boolean isOnline() {
        String cs = Context.CONNECTIVITY_SERVICE;
        ConnectivityManager cm = (ConnectivityManager)
                getSystemService(cs);
        if (cm.getActiveNetworkInfo() == null) {

            return false;

        } else {

            return true;

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MobileAds.initialize(getApplicationContext(),
                getString(R.string.ad_id));
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
//        if (mAdView.isLoading()) {
//            setContentView(R.layout.activity_mainwoad);
//        } else if (!mAdView.isLoading()){
//            try {
//                wait(2000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            setContentView(R.layout.activity_main);
//        }

//        while (!mAdView.isLoading()) {
//            setContentView(R.layout.activity_mainwoad);
//
//        }
        wb = findViewById(R.id.wb);
        wb.setWebViewClient(new MyWebViewClient());
        wb.loadUrl(getString(R.string.page_news));

        wb.getSettings().setUseWideViewPort(true);
        wb.getSettings().setLoadWithOverviewMode(true);
        wb.getSettings().setDomStorageEnabled(true);
        wb.getSettings().setAppCachePath("/data/data/" + getPackageName() + "/cache");
        wb.getSettings().setAllowFileAccess(true);
        wb.getSettings().setAppCacheEnabled(true);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(Intent.ACTION_SENDTO); // it's not ACTION_SEND
//                intent.setType("text/plain");
//                intent.putExtra(Intent.EXTRA_SUBJECT, "Отклик из приложения");
//                intent.putExtra(Intent.EXTRA_TEXT, "Добрый день!");
//                intent.setData(Uri.parse("mailto:ruslkochnev@gmail.com"));
//                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                startActivity(intent);
//            }
//        });

        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }


    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_feedback, menu);
        return true;
    }


    public void onFeedBackPressed(MenuItem item) {
        Intent intent = new Intent(Intent.ACTION_SENDTO); // it's not ACTION_SEND
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, "Отклик из приложения");
        intent.putExtra(Intent.EXTRA_TEXT, "Добрый день!");
        intent.setData(Uri.parse("mailto:ruslkochnev@gmail.com"));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);


    }

    @Override
    public void onBackPressed() {


        if (wb.canGoBack()) {
            wb.goBack();
        } else {
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
            } else {
                super.onBackPressed();
            }
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch (id) {

            case R.id.nav_news_hot:
                wb.loadUrl(getString(R.string.page_news));
                wb.getSettings().setUseWideViewPort(true);
                wb.getSettings().setLoadWithOverviewMode(true);
                if (!isOnline()) { // loading offline
                    wb.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ONLY);
                } else {
                    wb.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
                }


                break;

            case R.id.nav_about:
                wb.loadUrl(getString(R.string.page_about));
                wb.getSettings().setUseWideViewPort(true);
                wb.getSettings().setLoadWithOverviewMode(true);


                break;
            case R.id.nav_yours_help:
                wb.loadUrl(getString(R.string.page_your_help));
                wb.getSettings().setUseWideViewPort(true);
                wb.getSettings().setLoadWithOverviewMode(true);

                break;
            case R.id.nav_faq:
                wb.loadUrl(getString(R.string.page_faq));
                wb.getSettings().setUseWideViewPort(true);
                wb.getSettings().setLoadWithOverviewMode(true);

                break;
            case R.id.nav_traffic:
                wb.loadUrl(getString(R.string.page_traffic));
                wb.getSettings().setUseWideViewPort(true);
                wb.getSettings().setLoadWithOverviewMode(true);

                break;
            case R.id.nav_game_rules:
                wb.loadUrl(getString(R.string.page_game_rules));
                wb.getSettings().setUseWideViewPort(true);
                wb.getSettings().setLoadWithOverviewMode(true);

                break;
            case R.id.nav_general_rules:
                wb.loadUrl(getString(R.string.page_general_rules));
                wb.getSettings().setUseWideViewPort(true);
                wb.getSettings().setLoadWithOverviewMode(true);

                break;
            case R.id.nav_forum_rules:
                wb.loadUrl(getString(R.string.page_forum_rules));
                wb.getSettings().setUseWideViewPort(true);
                wb.getSettings().setLoadWithOverviewMode(true);

                break;
            case R.id.nav_chat_rules:
                wb.loadUrl(getString(R.string.page_chat_rules));
                wb.getSettings().setUseWideViewPort(true);
                wb.getSettings().setLoadWithOverviewMode(true);

                break;
            case R.id.nav_character:
                wb.loadUrl(getString(R.string.page_character));
                wb.getSettings().setUseWideViewPort(true);
                wb.getSettings().setLoadWithOverviewMode(true);

                break;
            case R.id.nav_faction:
                wb.loadUrl(getString(R.string.page_factions));
                wb.getSettings().setUseWideViewPort(true);
                wb.getSettings().setLoadWithOverviewMode(true);

                break;
            case R.id.nav_skills_abilities:
                wb.loadUrl(getString(R.string.page_skill_abilities));
                wb.getSettings().setUseWideViewPort(true);
                wb.getSettings().setLoadWithOverviewMode(true);

                break;
            case R.id.nav_unique_abilities:
                wb.loadUrl(getString(R.string.page_unique));
                wb.getSettings().setUseWideViewPort(true);
                wb.getSettings().setLoadWithOverviewMode(true);

                break;
            case R.id.nav_guilds:
                wb.loadUrl(getString(R.string.page_guilds));
                wb.getSettings().setUseWideViewPort(true);
                wb.getSettings().setLoadWithOverviewMode(true);

                break;
            case R.id.nav_statistic_protocols:
                wb.loadUrl(getString(R.string.page_stat_prot));
                wb.getSettings().setUseWideViewPort(true);
                wb.getSettings().setLoadWithOverviewMode(true);

                break;
            case R.id.nav_exp_tables:
                wb.loadUrl(getString(R.string.page_exp));
                wb.getSettings().setUseWideViewPort(true);
                wb.getSettings().setLoadWithOverviewMode(true);

                break;
            case R.id.nav_arts:
                wb.loadUrl(getString(R.string.page_arts));
                wb.getSettings().setUseWideViewPort(true);
                wb.getSettings().setLoadWithOverviewMode(true);

                break;
            case R.id.nav_sets:
                wb.loadUrl(getString(R.string.page_sets));
                wb.getSettings().setUseWideViewPort(true);
                wb.getSettings().setLoadWithOverviewMode(true);

                break;
            case R.id.nav_medals:
                wb.loadUrl(getString(R.string.page_medals));
                wb.getSettings().setUseWideViewPort(true);
                wb.getSettings().setLoadWithOverviewMode(true);

                break;
            case R.id.nav_estate:
                wb.loadUrl(getString(R.string.page_estate));
                wb.getSettings().setUseWideViewPort(true);
                wb.getSettings().setLoadWithOverviewMode(true);

                break;
            case R.id.nav_statistic:
                wb.loadUrl(getString(R.string.page_statistic));
                wb.getSettings().setUseWideViewPort(true);
                wb.getSettings().setLoadWithOverviewMode(true);

                break;
            case R.id.nav_production_works:
                wb.loadUrl(getString(R.string.page_prod_work));
                wb.getSettings().setUseWideViewPort(true);
                wb.getSettings().setLoadWithOverviewMode(true);

                break;
            case R.id.nav_estates_deposit:
                wb.loadUrl(getString(R.string.page_est_depo));
                wb.getSettings().setUseWideViewPort(true);
                wb.getSettings().setLoadWithOverviewMode(true);

                break;
            case R.id.nav_castle_construct:
                wb.loadUrl(getString(R.string.page_castle_construct));
                wb.getSettings().setUseWideViewPort(true);
                wb.getSettings().setLoadWithOverviewMode(true);

                break;
            case R.id.nav_market:
                wb.loadUrl(getString(R.string.page_market));
                wb.getSettings().setUseWideViewPort(true);
                wb.getSettings().setLoadWithOverviewMode(true);

                break;
            case R.id.nav_combat_conduct:
                wb.loadUrl(getString(R.string.page_combat_conduct));
                wb.getSettings().setUseWideViewPort(true);
                wb.getSettings().setLoadWithOverviewMode(true);

                break;
            case R.id.nav_recruiting:
                wb.loadUrl(getString(R.string.page_recruiting));
                wb.getSettings().setUseWideViewPort(true);
                wb.getSettings().setLoadWithOverviewMode(true);

                break;
            case R.id.nav_troops_settlement:
                wb.loadUrl(getString(R.string.page_troops_settlement));
                wb.getSettings().setUseWideViewPort(true);
                wb.getSettings().setLoadWithOverviewMode(true);

                break;
            case R.id.nav_control_panel:
                wb.loadUrl(getString(R.string.page_control_panel));
                wb.getSettings().setUseWideViewPort(true);
                wb.getSettings().setLoadWithOverviewMode(true);

                break;
            case R.id.nav_initiative_bar:
                wb.loadUrl(getString(R.string.page_initiative_bar));
                wb.getSettings().setUseWideViewPort(true);
                wb.getSettings().setLoadWithOverviewMode(true);

                break;
            case R.id.nav_combat_managment:
                wb.loadUrl(getString(R.string.page_combat_managment));
                wb.getSettings().setUseWideViewPort(true);
                wb.getSettings().setLoadWithOverviewMode(true);

                break;
            case R.id.nav_spells:
                wb.loadUrl(getString(R.string.page_spells));
                wb.getSettings().setUseWideViewPort(true);
                wb.getSettings().setLoadWithOverviewMode(true);

                break;
            case R.id.nav_special_abilities:
                wb.loadUrl(getString(R.string.page_special_abilities));
                wb.getSettings().setUseWideViewPort(true);
                wb.getSettings().setLoadWithOverviewMode(true);

                break;
            case R.id.nav_heros_action:
                wb.loadUrl(getString(R.string.page_heros_action));
                wb.getSettings().setUseWideViewPort(true);
                wb.getSettings().setLoadWithOverviewMode(true);

                break;
            case R.id.nav_damage_heal:
                wb.loadUrl(getString(R.string.page_damage_heal));
                wb.getSettings().setUseWideViewPort(true);
                wb.getSettings().setLoadWithOverviewMode(true);

                break;
            case R.id.nav_luck_morale:
                wb.loadUrl(getString(R.string.page_luck_morale));
                wb.getSettings().setUseWideViewPort(true);
                wb.getSettings().setLoadWithOverviewMode(true);

                break;
            case R.id.nav_ingame_communication:
                wb.loadUrl(getString(R.string.page_ingame_communication));
                wb.getSettings().setUseWideViewPort(true);
                wb.getSettings().setLoadWithOverviewMode(true);

                break;
            case R.id.nav_chat:
                wb.loadUrl(getString(R.string.page_chat));
                wb.getSettings().setUseWideViewPort(true);
                wb.getSettings().setLoadWithOverviewMode(true);

                break;
            case R.id.nav_forum:
                wb.loadUrl(getString(R.string.page_forum));
                wb.getSettings().setUseWideViewPort(true);
                wb.getSettings().setLoadWithOverviewMode(true);

                break;
            case R.id.nav_pm:
                wb.loadUrl(getString(R.string.page_pm));
                wb.getSettings().setUseWideViewPort(true);
                wb.getSettings().setLoadWithOverviewMode(true);

                break;
            case R.id.nav_tournaments:
                wb.loadUrl(getString(R.string.page_tournaments));
                wb.getSettings().setUseWideViewPort(true);
                wb.getSettings().setLoadWithOverviewMode(true);

                break;
            case R.id.nav_card_game:
                wb.loadUrl(getString(R.string.page_card_game));
                wb.getSettings().setUseWideViewPort(true);
                wb.getSettings().setLoadWithOverviewMode(true);

                break;
            case R.id.nav_quests:
                wb.loadUrl(getString(R.string.page_quests));
                wb.getSettings().setUseWideViewPort(true);
                wb.getSettings().setLoadWithOverviewMode(true);

                break;
            case R.id.nav_text_quests:
                wb.loadUrl(getString(R.string.page_text_quests));
                wb.getSettings().setUseWideViewPort(true);
                wb.getSettings().setLoadWithOverviewMode(true);

                break;
            case R.id.nav_combat_quests:
                wb.loadUrl(getString(R.string.page_battle_quests));
                wb.getSettings().setUseWideViewPort(true);
                wb.getSettings().setLoadWithOverviewMode(true);

                break;
            case R.id.nav_roulette:
                wb.loadUrl(getString(R.string.page_roulette));
                wb.getSettings().setUseWideViewPort(true);
                wb.getSettings().setLoadWithOverviewMode(true);

                break;
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public void OnClickNavHeader(View view) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.heroeswm.ru/?rid=692098"));
        startActivity(browserIntent);
    }
}
