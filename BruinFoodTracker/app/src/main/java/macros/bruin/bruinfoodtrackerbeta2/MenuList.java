package macros.bruin.bruinfoodtrackerbeta2;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MenuList extends AppCompatActivity {

    ListView myListView;
    String[] menu;

    private List<FoodItem> foodItems = new ArrayList<>();
    private List<String> finalString = new ArrayList<>();

    private void readMacrosData(InputStream is) {
        //InputStream is = getResources().openRawResource(R.raw.dinner_feast_at_rieber);

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(is, Charset.forName("UTF-8"))
        );

        String line = "";
        try {
            //step over headers
            reader.readLine();
            while ( (line = reader.readLine()) != null)
            {
                Log.d("MyActivity", "Line: " + line);
                /*if(line.charAt(0) == '"')
                {
                    for(int i = 0; i < line.length(); i++)
                    {
                        if(line.charAt(i) == '"')
                        {

                        }
                    }
                }
                */
                String[] tokens = line.split(",");

                //read the data
                FoodItem item = new FoodItem();
                item.setFood_item(tokens[0]);
                if (tokens[1].length() > 0)
                    item.setCalories(Double.parseDouble(tokens[1]));
                else
                    item.setCalories(0);
                if (tokens[2].length() > 0)
                    item.setFat_calories(Double.parseDouble(tokens[2]));
                else
                    item.setFat_calories(0);
                if (tokens[3].length() > 0)
                    item.setTotal_fat(Double.parseDouble(tokens[3]));
                else
                    item.setTotal_fat(0);
                if (tokens[4].length() > 0)
                    item.setSaturated_fat(Double.parseDouble(tokens[4]));
                else
                    item.setSaturated_fat(0);
                if (tokens[5].length() > 0)
                    item.setTrans_fat(Double.parseDouble(tokens[5]));
                else
                    item.setTrans_fat(0);
                if (tokens[6].length() > 0)
                    item.setCholesterol(Double.parseDouble(tokens[6]));
                else
                    item.setCholesterol(0);
                if (tokens[7].length() > 0)
                    item.setSodium(Double.parseDouble(tokens[7]));
                else
                    item.setSodium(0);
                if (tokens[8].length() > 0)
                    item.setTotal_carbohydrate(Double.parseDouble(tokens[8]));
                else
                    item.setTotal_carbohydrate(0);
                if (tokens[9].length() > 0)
                    item.setDietary_fiber(Double.parseDouble(tokens[9]));
                else
                    item.setDietary_fiber(0);
                if (tokens[10].length() > 0)
                    item.setSugars(Double.parseDouble(tokens[10]));
                else
                    item.setSugars(0);
                if (tokens[11].length() > 0)
                    item.setProtein(Double.parseDouble(tokens[11]));
                else
                    item.setProtein(0);
                if (tokens[12].length() > 0)
                    item.setVitamin_a(Integer.parseInt(tokens[12]));
                else
                    item.setVitamin_a(0);
                if (tokens[13].length() > 0)
                    item.setVitamin_c(Integer.parseInt(tokens[13]));
                else
                    item.setVitamin_c(0);
                if (tokens[14].length() > 0)
                    item.setCalcium(Integer.parseInt(tokens[14]));
                else
                    item.setCalcium(0);
                if (tokens[15].length() > 0)
                    item.setIron(Integer.parseInt(tokens[15]));
                else
                    item.setIron(0);
                if (tokens[16].length() > 0)
                    if(Integer.parseInt(tokens[16]) == 1)
                        item.setVegetarian(true);
                    else
                        item.setVegetarian(false);
                else
                    item.setVegetarian(false);
                if (tokens[17].length() > 0)
                    if(Integer.parseInt(tokens[17]) == 1)
                        item.setVegan(true);
                    else
                        item.setVegan(false);
                else
                    item.setVegan(false);
                if (tokens[18].length() > 0)
                    if(Integer.parseInt(tokens[18]) == 1)
                        item.setPeanuts(true);
                    else
                        item.setPeanuts(false);
                else
                    item.setPeanuts(false);
                if (tokens[19].length() > 0)
                    if(Integer.parseInt(tokens[19]) == 1)
                        item.setTree_nuts(true);
                    else
                        item.setTree_nuts(false);
                else
                    item.setTree_nuts(false);
                if (tokens[20].length() > 0)
                    if(Integer.parseInt(tokens[20]) == 1)
                        item.setWheat(true);
                    else
                        item.setWheat(false);
                else
                    item.setWheat(false);
                if (tokens[21].length() > 0)
                    if(Integer.parseInt(tokens[21]) == 1)
                        item.setSoy(true);
                    else
                        item.setSoy(false);
                else
                    item.setSoy(false);
                if (tokens[22].length() > 0)
                    if(Integer.parseInt(tokens[22]) == 1)
                        item.setDairy(true);
                    else
                        item.setDairy(false);
                else
                    item.setDairy(false);
                if (tokens[23].length() > 0)
                    if(Integer.parseInt(tokens[23]) == 1)
                        item.setEggs(true);
                    else
                        item.setEggs(false);
                else
                    item.setEggs(false);
                if (tokens[24].length() > 0)
                    if(Integer.parseInt(tokens[24]) == 1)
                        item.setShellfish(true);
                    else
                        item.setShellfish(false);
                else
                    item.setShellfish(false);
                if (tokens[25].length() > 0)
                    if(Integer.parseInt(tokens[25]) == 1)
                        item.setFish(true);
                    else
                        item.setFish(false);
                else
                    item.setFish(false);
                if (tokens[26].length() > 0)
                    if(Integer.parseInt(tokens[26]) == 1)
                        item.setAdd_on(true);
                    else
                        item.setAdd_on(false);
                else
                    item.setAdd_on(false);

                foodItems.add(item);

                //Log.d("MyActivity", "Just created: " + item);
            }
        } catch (IOException e) {

            Log.wtf("MyActivity", "Error reading data file on line" + line, e);
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
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_menu_list);



        Resources res = getResources();
        myListView = (ListView) findViewById(R.id.menuListView);
        //menu = res.getStringArray(R.array.TheStudyMenu);

        InputStream is = getResources().openRawResource(R.raw.breakfast_de_neve);;
        Intent in = getIntent();
        int index = in.getIntExtra("macros.bruin.bruinfoodtracker.INDEX", 0);
        final String restaurantName = in.getStringExtra("macros.bruin.bruinfoodtracker.NAME");
        getSupportActionBar().setTitle(restaurantName);
        Boolean isWeekend = in.getBooleanExtra("macros.bruin.bruinfoodtracker.IS_WEEKEND", false);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab3);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Date c = Calendar.getInstance().getTime();
                SimpleDateFormat df = new SimpleDateFormat("MMddyy_HHmm");
                SimpleDateFormat dff = new SimpleDateFormat("HHmm");
                String formattedDateToday = df.format(c);
                String formattedTime = dff.format(c);
                String contents = restaurantName + "|" + formattedTime + "|";
                File path = new File(getResources().getString(R.string.main_file_directory));

                for (int i = 0; i < finalString.size(); i++){
                    contents += finalString.get(i);
                }

                try {
                    stringWriter(formattedDateToday, contents, path);
                } catch (IOException e) {
                    e.printStackTrace();
                }


                Intent goBack = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(goBack);
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                //        .setAction("Action", null).show();
            }
        });

        if(!isWeekend){
            switch (index) {
                case 0:
                    is = getResources().openRawResource(R.raw.breakfast_de_neve);
                    break;
                case 1:
                    is = getResources().openRawResource(R.raw.breakfast_bruin_plate);
                    break;
                case 2:
                    is = getResources().openRawResource(R.raw.bruin_cafe);
                    break;
                case 3:
                    is = getResources().openRawResource(R.raw.rendezvous);
                    break;
                case 4:
                    is = getResources().openRawResource(R.raw.de_neve_grab_n_go);
                    break;
                case 5:
                    is = getResources().openRawResource(R.raw.the_study_at_hedrick);
                    break;
                case 6:
                    is = getResources().openRawResource(R.raw.lunch_covel);
                    break;
                case 7:
                    is = getResources().openRawResource(R.raw.lunch_de_neve);
                    break;
                case 8:
                    is = getResources().openRawResource(R.raw.lunch_feast_at_rieber);
                    break;
                case 9:
                    is = getResources().openRawResource(R.raw.lunch_bruin_plate);
                    break;
                case 10:
                    is = getResources().openRawResource(R.raw.bruin_cafe);
                    break;
                case 11:
                    is = getResources().openRawResource(R.raw.cafe_1919);
                    break;
                case 12:
                    is = getResources().openRawResource(R.raw.rendezvous);
                    break;
                case 13:
                    is = getResources().openRawResource(R.raw.de_neve_grab_n_go);
                    break;
                case 14:
                    is = getResources().openRawResource(R.raw.the_study_at_hedrick);
                    break;
                case 15:
                    is = getResources().openRawResource(R.raw.dinner_covel);
                    break;
                case 16:
                    is = getResources().openRawResource(R.raw.dinner_de_neve);
                    break;
                case 17:
                    is = getResources().openRawResource(R.raw.dinner_feast_at_rieber);
                    break;
                case 18:
                    is = getResources().openRawResource(R.raw.dinner_bruin_plate);
                    break;
                case 19:
                    is = getResources().openRawResource(R.raw.bruin_cafe);
                    break;
                case 20:
                    is = getResources().openRawResource(R.raw.cafe_1919);
                    break;
                case 21:
                    is = getResources().openRawResource(R.raw.rendezvous);
                    break;
                case 22:
                    is = getResources().openRawResource(R.raw.the_study_at_hedrick);
                    break;
                case 23:
                    is = getResources().openRawResource(R.raw.de_neve_late_night);
                    break;
                case 24:
                    is = getResources().openRawResource(R.raw.bruin_cafe);
                    break;
                case 25:
                    is = getResources().openRawResource(R.raw.cafe_1919);
                    break;
                case 26:
                    is = getResources().openRawResource(R.raw.rendezvous);
                    break;
                case 27:
                    is = getResources().openRawResource(R.raw.the_study_at_hedrick);
                    break;
                default:
                    break;
            }
        }
        else{
            switch (index) {
                case 1:
                    is = getResources().openRawResource(R.raw.lunch_covel);
                    break;
                case 2:
                    is = getResources().openRawResource(R.raw.lunch_de_neve);
                    break;
                case 3:
                    is = getResources().openRawResource(R.raw.lunch_bruin_plate);
                    break;
                case 4:
                    is = getResources().openRawResource(R.raw.bruin_cafe);
                    break;
                case 5:
                    is = getResources().openRawResource(R.raw.rendezvous);
                    break;
                case 6:
                    is = getResources().openRawResource(R.raw.the_study_at_hedrick);
                    break;
                case 7:
                    is = getResources().openRawResource(R.raw.dinner_covel);
                    break;
                case 8:
                    is = getResources().openRawResource(R.raw.dinner_de_neve);
                    break;
                case 9:
                    is = getResources().openRawResource(R.raw.dinner_bruin_plate);
                    break;
                case 10:
                    is = getResources().openRawResource(R.raw.bruin_cafe);
                    break;
                case 11:
                    is = getResources().openRawResource(R.raw.rendezvous);
                    break;
                case 12:
                    is = getResources().openRawResource(R.raw.the_study_at_hedrick);
                    break;
                case 13:
                    is = getResources().openRawResource(R.raw.de_neve_late_night);
                    break;
                case 14:
                    is = getResources().openRawResource(R.raw.bruin_cafe);
                    break;
                case 15:
                    is = getResources().openRawResource(R.raw.the_study_at_hedrick);
                    break;
                default:
                    break;
            }
        }

        readMacrosData(is);
        menu = new String[foodItems.size()];
        final boolean[] wasSelected = new boolean[foodItems.size()];
        for(int i = 0; i < foodItems.size(); i++)
        {
            menu[i] = foodItems.get(i).getFood_item();
            wasSelected[i] = false;
            //Log.d("MyActivity", "Item Names: " + item_names[i]);
        }





        MenuAdapterSimple itemAdapter = new MenuAdapterSimple(this, menu);
        myListView.setAdapter(itemAdapter);

        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (wasSelected[i]){
                    view.setBackgroundColor(0x00000000);;
                    finalString.remove(foodItems.get(i).toMacroString());
                    wasSelected[i] = false;
                }
                else{
                    //view.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    view.setBackgroundColor(Color.LTGRAY);
                    finalString.add(foodItems.get(i).toMacroString());
                    wasSelected[i] = true;
                }
                //Intent showDetailActivity = new Intent(getApplicationContext(), TestActivity.class);
                //showDetailActivity.putExtra("macros.bruin.listapp.INDEX", i);
                //startActivity(showDetailActivity);
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