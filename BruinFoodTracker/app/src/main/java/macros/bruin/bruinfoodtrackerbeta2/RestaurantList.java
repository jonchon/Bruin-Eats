package macros.bruin.bruinfoodtrackerbeta2;

import android.app.ActionBar;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.Calendar;

public class RestaurantList extends AppCompatActivity {

    ListView myListView;
    String[] restaurants;
    String[] times;
    String[] periods;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Restaurants");
        setContentView(R.layout.activity_restaurant_list);

        Resources res = getResources();
        myListView = (ListView) findViewById(R.id.restaurantListView);
        restaurants = res.getStringArray(R.array.restaurants);
        times = res.getStringArray(R.array.times);
        periods = res.getStringArray(R.array.periods);

        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        boolean isWeekend = false;
        //int day = 7;

        switch (day) {
            case Calendar.SUNDAY:

            case Calendar.SATURDAY:
                isWeekend  = true;
                restaurants = res.getStringArray(R.array.restaurantsWE);
                times = res.getStringArray(R.array.timesWE);
                periods = res.getStringArray(R.array.periodsWE);

            default:
                break;
        }

        RestuarantAdapter itemAdapter = new RestuarantAdapter(this, restaurants, times, periods);
        myListView.setAdapter(itemAdapter);

        final boolean finalIsWeekend = isWeekend;
        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent showDetailActivity = new Intent(getApplicationContext(), MenuList.class);
                showDetailActivity.putExtra("macros.bruin.bruinfoodtracker.INDEX", i);
                showDetailActivity.putExtra("macros.bruin.bruinfoodtracker.IS_WEEKEND", finalIsWeekend);
                showDetailActivity.putExtra("macros.bruin.bruinfoodtracker.NAME", restaurants[i]);
                startActivity(showDetailActivity);
                //overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
            }
        });


    }
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
        }
        return true;
    }
}
