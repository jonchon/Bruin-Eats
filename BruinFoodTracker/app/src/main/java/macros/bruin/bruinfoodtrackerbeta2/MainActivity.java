package macros.bruin.bruinfoodtrackerbeta2;

import android.app.FragmentManager;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    /*ListView myListView;
    String[] restaurants;
    String[] macros;
    String[] times;
    String[][] formattedMacros = {
            {"56g","13g","76g"},
            {"42g","18g","83g"},
            {"2g","10g","15g"},
            {"61g","24g","78g"},
            {"1g","0g","27g"}
    };*/

    public void setActionBarTitle(String title){
        getSupportActionBar().setTitle(title);
    }

    public void clear(File path){
        String[] files;
        files = path.list();
        for (int i=0; i<files.length; i++) {
            File myFile = new File(path, files[i]);
            myFile.delete();
        }
    }

    public void testInput(File path){

        String[] names = new String[3];
        names[0] = getResources().getString(R.string.mp1_name);
        String contents = getResources().getString(R.string.mp1_contents);
        names[1] = getResources().getString(R.string.mp2_name);
        String contents2 = getResources().getString(R.string.mp2_contents);
        names[2] = getResources().getString(R.string.mp3_name);
        String contents3 = getResources().getString(R.string.mp3_contents);


        //String contents = "DeNeve|0124|Chicken_Tenders|46|35|19|44|76|Curly_Fries|12|24|39|11|93|";
        //String name = "test";
        //File path = getActivity().getFilesDir();


        try {
            stringWriter(names[0], contents, path);
            stringWriter(names[1], contents2, path);
            stringWriter(names[2], contents3, path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stringWriter(String name, String contents, File path) throws IOException {
        File file = new File(path, name+".macro");
        FileOutputStream stream = new FileOutputStream(file);
        try {
            stream.write(contents.getBytes());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            stream.close();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        File path = getApplicationContext().getFilesDir();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent showRestaurantList = new Intent(getApplicationContext(), RestaurantList.class);
                startActivity(showRestaurantList);
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                //        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);



        FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
        tx.replace(R.id.screen_area, new DashboardFragment());
        tx.commit();
        setActionBarTitle("Dashboard");




        /*
        Resources res = getResources();
        myListView = (ListView) findViewById(R.id.myListView);
        restaurants = res.getStringArray(R.array.restaurants);
        macros = res.getStringArray(R.array.macros);
        times = res.getStringArray(R.array.times);

        /**for (int i = 0; i < (macros.length / 3); i++){
            for (int j = 0; j < 3; j++){
                formattedMacros[i][j] = macros[j + (3*i)];
            }
        }**/


        /*
        ItemAdapter itemAdapter = new ItemAdapter(this, restaurants, formattedMacros, times);
        myListView.setAdapter(itemAdapter);

        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent showDetailActivity = new Intent(getApplicationContext(), DetailActivity.class);
                showDetailActivity.putExtra("macros.bruin.listapp.INDEX", i);
                startActivity(showDetailActivity);
                overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
            }
        });*/



    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        File path = new File(getResources().getString(R.string.main_file_directory));

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            testInput(path);
            FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
            tx.replace(R.id.screen_area, new DashboardFragment());
            tx.commit();
            return true;
        }
        else if (id == R.id.action_refresh) {
            FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
            tx.replace(R.id.screen_area, new DashboardFragment());
            tx.commit();
            return true;
        }
        else if (id == R.id.action_deleteall) {
            clear(path);
            FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
            tx.replace(R.id.screen_area, new DashboardFragment());
            tx.commit();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        Fragment fragment = null;
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        int id = item.getItemId();

        if (id == R.id.nav_dashboard) {
            fragment = new DashboardFragment();
            setActionBarTitle("Dashboard");
            if (!fab.isShown()){
                fab.show();
            }
        } else if (id == R.id.nav_goals) {
            fragment = new TempFragment();
            setActionBarTitle("Goals");
            if (fab.isShown()){
                fab.hide();
            }
        } else if (id == R.id.nav_history) {
            //Intent showHistoryActivity = new Intent(getApplicationContext(), TestHistoryActivity.class);
            //startActivity(showHistoryActivity);
            fragment = new HistoryFragment2();
            setActionBarTitle("History");
            if (fab.isShown()){
                fab.hide();
            }
        } else if (id == R.id.nav_settings) {
            fragment = new SettingsFragment();
            setActionBarTitle("Settings");
            if (fab.isShown()){
                fab.hide();
            }
        }

        if (fragment != null){
            android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction ft = fragmentManager.beginTransaction();

            ft.replace(R.id.screen_area, fragment);
            ft.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
