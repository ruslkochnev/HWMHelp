package com.sparkysparkybooman.vk.hwmhelp;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.sparkysparkybooman.vk.hwmhelp.Adapter.CustomExpandableListAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public AdView mAdView;
    public WebView wb;
    public DrawerLayout drawer;
    String[][] array;
    String[][] arrayURL;
    List<String> title;
    HashMap<String, List<String>> child;
    String mActivityTitle;
    ExpandableListAdapter adapter;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private ExpandableListView expandableListView;
    private long backPressedTime;
    private Toast backToast;
    protected boolean isOnline() {
        String cs = Context.CONNECTIVITY_SERVICE;
        ConnectivityManager cm = (ConnectivityManager)
                getSystemService(cs);
        return cm.getActiveNetworkInfo() != null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        wb = findViewById(R.id.wb);
        wb.setWebViewClient(new MyWebViewClient());
        wb.getSettings().setUseWideViewPort(true);
        wb.getSettings().setLoadWithOverviewMode(true);
        wb.getSettings().setDomStorageEnabled(true);
        wb.getSettings().setAppCachePath("/data/data/" + getPackageName() + "/cache");
        wb.getSettings().setAllowFileAccess(true);
        wb.getSettings().setAppCacheEnabled(true);
        mDrawerLayout = findViewById(R.id.drawer_layout);
        mActivityTitle = getTitle().toString();
        expandableListView = findViewById(R.id.navList);
        View listHeaderView = getLayoutInflater().inflate(R.layout.nav_header, null, false);
        expandableListView.addHeaderView(listHeaderView);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle(R.string.app_name);


        array = new String[][]{
                {getString(R.string.nav_about), getString(R.string.nav_your_help),                          //Об игре - начало
                        getString(R.string.nav_faq), getString(R.string.nav_traffic),},                     //Об игре - конец
                {getString(R.string.nav_game_rules), getString(R.string.nav_general_rules),                 //Правила игры - начало
                        getString(R.string.nav_forum_rules), getString(R.string.nav_chat_rules),},          //Правила игры - конец
                {getString(R.string.nav_character), getString(R.string.nav_factions),                       //Персонаж - начало
                        getString(R.string.nav_skill_abilities), getString(R.string.nav_unique),            //-
                        getString(R.string.nav_guilds), getString(R.string.nav_stat_prot),                  //-
                        getString(R.string.nav_exp),},                                                      //Персонаж - конец
                {getString(R.string.nav_arts), getString(R.string.nav_sets),                                //Артефакты - начало
                        getString(R.string.nav_medals),},                                                   //Артефакты - конец
                {getString(R.string.nav_estate), getString(R.string.nav_statistic),                         //Недвижимость - начало
                        getString(R.string.nav_prod_work), getString(R.string.nav_est_depo),                //-
                        getString(R.string.nav_castle_construct), getString(R.string.nav_market),},         //Недвижимость - конец
                {getString(R.string.nav_combat_conduct), getString(R.string.nav_recruiting),                //Проведение боев - начало
                        getString(R.string.nav_troops_settlement), getString(R.string.nav_control_panel),   //-
                        getString(R.string.nav_initiative_bar), getString(R.string.nav_combat_managment),   //-
                        getString(R.string.nav_spells), getString(R.string.nav_special_abilities),          //-
                        getString(R.string.nav_heros_action), getString(R.string.nav_damage_heal),          //-
                        getString(R.string.nav_luck_morale),},                                              //Проведение боев - конец
                {getString(R.string.nav_ingame_communication), getString(R.string.nav_chat),                //Общение в игре - начало
                        getString(R.string.nav_forum), getString(R.string.nav_pm),},                        //Общение в игре - конец
                {getString(R.string.nav_tournaments),},                                                     //Турниры
                {getString(R.string.nav_card_game),},                                                       //Карточная игра
                {getString(R.string.nav_quests), getString(R.string.nav_text_quests),                       //Квесты - начало
                        getString(R.string.nav_battle_quests),},                                            //Квесты - конец
                {getString(R.string.nav_roulette),},                                                         //Рулетка
        };


        arrayURL = new String[][]{
                {getString(R.string.page_about), getString(R.string.page_your_help),                        //Об игре - начало
                        getString(R.string.page_faq), getString(R.string.page_traffic),},                   //Об игре - конец
                {getString(R.string.page_game_rules), getString(R.string.page_general_rules),               //Правила игры - начало
                        getString(R.string.page_forum_rules), getString(R.string.page_chat_rules),},        //Правила игры - конец
                {getString(R.string.page_character), getString(R.string.page_factions),                     //Персонаж - начало
                        getString(R.string.page_skill_abilities), getString(R.string.page_unique),          //-
                        getString(R.string.page_guilds), getString(R.string.page_stat_prot),                //-
                        getString(R.string.page_exp),},                                                     //Персонаж - конец
                {getString(R.string.page_arts), getString(R.string.page_sets),                              //Артефакты - начало
                        getString(R.string.page_medals),},                                                  //Артефакты - конец
                {getString(R.string.page_estate), getString(R.string.page_statistic),                       //Недвижимость - начало
                        getString(R.string.page_prod_work), getString(R.string.page_est_depo),              //-
                        getString(R.string.page_castle_construct), getString(R.string.page_market),},       //Недвижимость - конец
                {getString(R.string.page_combat_conduct), getString(R.string.page_recruiting),              //Проведение боев - начало
                        getString(R.string.page_troops_settlement), getString(R.string.page_control_panel), //-
                        getString(R.string.page_initiative_bar), getString(R.string.page_combat_managment), //-
                        getString(R.string.page_spells), getString(R.string.page_special_abilities),        //-
                        getString(R.string.page_heros_action), getString(R.string.page_damage_heal),        //-
                        getString(R.string.page_luck_morale),},                                             //Проведение боев - конец
                {getString(R.string.page_ingame_communication), getString(R.string.page_chat),              //Общение в игре - начало
                        getString(R.string.page_forum), getString(R.string.page_pm),},                      //Общение в игре - конец
                {getString(R.string.page_tournaments),},                                                    //Турниры
                {getString(R.string.page_card_game),},                                                      //Карточная игра
                {getString(R.string.page_quests), getString(R.string.page_text_quests),                     //Квесты - начало
                        getString(R.string.page_battle_quests),},                                           //Квесты - конец
                {getString(R.string.page_roulette),},                                                       //Рулетка

        };


        genData();
        addDrawersItem();
        setupDrawer();
        if (savedInstanceState == null)
            selectFirstItemAsDefault();


        MobileAds.initialize(getApplicationContext(),
                getString(R.string.ad_id));
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    private void selectFirstItemAsDefault() {
        wb.loadUrl(getString(R.string.page_news));
        getSupportActionBar().setTitle(R.string.nav_news_hot);
    }

    private void setupDrawer() {
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                invalidateOptionsMenu();
            }
        };
        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerLayout.setDrawerListener(mDrawerToggle);

    }

    private void addDrawersItem() {
        adapter = new CustomExpandableListAdapter(this, title, child);
        expandableListView.setAdapter(adapter);
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                getSupportActionBar().setTitle(title.get(groupPosition));

            }
        });
        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {
                getSupportActionBar().setTitle(R.string.app_name);
            }
        });
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                String selectedItem = ((List) (child.get(title.get(groupPosition)))).get(childPosition).toString();
                int i = title.indexOf(title.get(groupPosition));
                int j = child.get(title.get(groupPosition)).indexOf(selectedItem);
                //Toast.makeText(getApplicationContext(), array[i][j], Toast.LENGTH_SHORT).show();
                wb.loadUrl(arrayURL[i][j]);
                getSupportActionBar().setTitle(selectedItem);
                mDrawerLayout.closeDrawer(GravityCompat.START);
                return false;
            }
        });
    }

    private void genData() {
        title = new ArrayList<String>();
        child = new HashMap<String, List<String>>();

        title.add(array[0][0]);
        title.add(array[1][0]);
        title.add(array[2][0]);
        title.add(array[3][0]);
        title.add(array[4][0]);
        title.add(array[5][0]);
        title.add(array[6][0]);
        title.add(array[7][0]);
        title.add(array[8][0]);
        title.add(array[9][0]);
        title.add(array[10][0]);

        List<String> about = new ArrayList<String>();
        about.add(array[0][0]);
        about.add(array[0][1]);
        about.add(array[0][2]);
        about.add(array[0][3]);

        List<String> rules = new ArrayList<String>();
        rules.add(array[1][0]);
        rules.add(array[1][1]);
        rules.add(array[1][2]);
        rules.add(array[1][3]);

        List<String> character = new ArrayList<String>();
        character.add(array[2][0]);
        character.add(array[2][1]);
        character.add(array[2][2]);
        character.add(array[2][3]);
        character.add(array[2][3]);
        character.add(array[2][4]);
        character.add(array[2][5]);

        List<String> arts = new ArrayList<String>();
        arts.add(array[3][0]);
        arts.add(array[3][1]);
        arts.add(array[3][2]);

        List<String> estate = new ArrayList<String>();
        arts.add(array[4][0]);
        arts.add(array[4][1]);
        arts.add(array[4][2]);
        arts.add(array[4][3]);
        arts.add(array[4][4]);
        arts.add(array[4][5]);

        List<String> combat = new ArrayList<String>();
        combat.add(array[5][0]);
        combat.add(array[5][1]);
        combat.add(array[5][2]);
        combat.add(array[5][3]);
        combat.add(array[5][4]);
        combat.add(array[5][5]);
        combat.add(array[5][6]);
        combat.add(array[5][7]);
        combat.add(array[5][8]);
        combat.add(array[5][9]);
        combat.add(array[5][10]);

        List<String> communication = new ArrayList<String>();
        communication.add(array[6][0]);
        communication.add(array[6][1]);
        communication.add(array[6][2]);
        communication.add(array[6][3]);

        List<String> tournaments = new ArrayList<String>();
        tournaments.add(array[7][0]);

        List<String> gamble = new ArrayList<String>();
        gamble.add(array[8][0]);

        List<String> quests = new ArrayList<String>();
        quests.add(array[9][0]);
        quests.add(array[9][1]);
        quests.add(array[9][2]);

        List<String> roulette = new ArrayList<String>();
        roulette.add(array[10][0]);

        child.put(title.get(0), about);
        child.put(title.get(1), rules);
        child.put(title.get(2), character);
        child.put(title.get(3), arts);
        child.put(title.get(4), estate);
        child.put(title.get(5), combat);
        child.put(title.get(6), communication);
        child.put(title.get(7), tournaments);
        child.put(title.get(8), gamble);
        child.put(title.get(9), quests);
        child.put(title.get(10), roulette);
    }

    public void onFeedBackPressed(MenuItem item) {
        Intent intent = new Intent(Intent.ACTION_SENDTO); // it's not ACTION_SEND
        //intent.setType("text/plain");
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


            if (backPressedTime + 2000 > System.currentTimeMillis()) {
                backToast.cancel();
                super.onBackPressed();
                return;
            } else {
                backToast = Toast.makeText(getBaseContext(), R.string.toastMsgExit, Toast.LENGTH_SHORT);
                backToast.show();
            }

            backPressedTime = System.currentTimeMillis();

            }
        }


    public void OnClickNavHeader(View view) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.heroeswm.ru/?rid=692098"));
        startActivity(browserIntent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (mDrawerToggle.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }

    static class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}

