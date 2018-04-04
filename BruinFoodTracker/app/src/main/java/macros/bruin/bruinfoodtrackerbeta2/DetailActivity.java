package macros.bruin.bruinfoodtrackerbeta2;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DetailActivity extends AppCompatActivity {

    public String stringReader(String name, File path) throws IOException {

        File file = new File(path, name);
        //+".macro"

        int length = (int) file.length();

        byte[] bytes = new byte[length];

        FileInputStream in = new FileInputStream(file);
        try {
            in.read(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            in.close();
        }

        return new String(bytes);
    }

    public String[] stringParser(String contents){
        String tempString = "";
        List<String> stringArrayList = new ArrayList<String>();

        for (int i=0; i<contents.length(); i++) {
            char c = contents.charAt(i);

            if(c == '|'){
                stringArrayList.add(tempString);
                tempString = "";
            }
            else if(c == '_'){
                tempString += ' ';
            }
            else{
                tempString += c;
            }
        }
        return stringArrayList.toArray(new String[stringArrayList.size()]);
    }

    public String[][] arrayParser(String[] contents){
        int num_elements = (contents.length - 2)/17 + 1;
        String[][] parsed = new String[num_elements][];
        String[] temp = new String[17];

        parsed[0] = new String[2];
        parsed[0][0] = contents[0];
        parsed[0][1] = contents[1];

        int countCol = 0, countRow = 1;
        for (int i = 2; i < contents.length; i++){
            if (countCol == 17) {
                parsed[countRow] = temp.clone();
                countRow++;
                countCol = 0;
            }
            temp[countCol] = contents[i];
            countCol++;
            if (i == contents.length - 1){
                parsed[countRow] = temp.clone();
                countRow++;
                countCol = 0;
            }
        }
        return parsed;
    }

    public void delete(String name, File path){
        String[] files;
        files = path.list();
        for (int i=0; i<files.length; i++) {
            File myFile = new File(path, files[i]);
            if (myFile.getName().equals(name)) {
                myFile.delete();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);



        Intent in = getIntent();
        String time = in.getStringExtra("macros.bruin.bruinfoodtracker.TIME_FORMATTED");
        String fileName = in.getStringExtra("macros.bruin.bruinfoodtracker.FILE_NAME");
        File path = new File(getResources().getString(R.string.main_file_directory));

        //toolbar.setTitle(time);

        String[][] data;
        String resName = "";
        List<String> itemNamesArrayList = new ArrayList<String>();
        DecimalFormat df = new DecimalFormat("#.#");
        double totalCal = 0.0;
        double totalFat = 0.0;
        double totalCarbs = 0.0;
        double totalProtein = 0.0;


        try {
            data = arrayParser(stringParser(stringReader(fileName, path)));
            for (int i = 1; i < data.length; i++){
                //if(data[i]!=null){
                    itemNamesArrayList.add("         " + data[i][0]);
                    totalCal += Double.parseDouble(data[i][1]);
                    totalProtein += Double.parseDouble(data[i][11]);
                    totalFat += Double.parseDouble(data[i][3]);
                    totalCarbs += Double.parseDouble(data[i][8]);
                //}
                //else
                //    break;
            }

            itemNamesArrayList.add(0, fileName.substring(0,2)+ "/" +fileName.substring(2,4)+ "/" +fileName.substring(4,6) + ", " + time);
            itemNamesArrayList.add(1, "Total Calories : " + df.format(totalCal));
            itemNamesArrayList.add(2, "Total Protein : " + df.format(totalProtein) + "g");
            itemNamesArrayList.add(3, "Total Fat : " + df.format(totalFat) + "g");
            itemNamesArrayList.add(4, "Total Carbs : " + df.format(totalCarbs) + "g");
            itemNamesArrayList.add(5, "Menu Consumed :");
            itemNamesArrayList.add("File Name : " + fileName);
            resName = data[0][0];
        } catch (IOException e) {
            e.printStackTrace();
        }

        toolbar.setTitle(resName);
        Resources res = getResources();
        ListView myListView;
        myListView = (ListView) findViewById(R.id.myMPContentsListView);
        String[] itemNames = itemNamesArrayList.toArray(new String[itemNamesArrayList.size()]);


        MenuAdapterSimple itemAdapter = new MenuAdapterSimple(this, itemNames);
        myListView.setAdapter(itemAdapter);

        //Toast toast = Toast.makeText(getApplicationContext(), itemNames[0], Toast.LENGTH_SHORT);
        //toast.show();






        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab2);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = getIntent();
                File path = new File(getResources().getString(R.string.main_file_directory));
                String fileName = in.getStringExtra("macros.bruin.bruinfoodtracker.FILE_NAME");
                delete(fileName, path);
                finish();

            }
        });
    }

    /*
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
        }
        return true;
    }*/
}
