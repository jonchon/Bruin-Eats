package macros.bruin.bruinfoodtrackerbeta2;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Junhyuck on 3/11/2018.
 */

public class HistoryFragment2 extends Fragment {

    List<String> DatesList = new ArrayList<String>();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_history_2, null);
    }

    public static boolean isInteger(String s) {
        return isInteger(s,10);
    }

    public static boolean isInteger(String s, int radix) {
        if(s.isEmpty()) return false;
        for(int i = 0; i < s.length(); i++) {
            if(i == 0 && s.charAt(i) == '-') {
                if(s.length() == 1) return false;
                else continue;
            }
            if(Character.digit(s.charAt(i),radix) < 0) return false;
        }
        return true;
    }

    public String[] traverseDashboard(File path) throws IOException {
        List<String> stringMealPeriodRecords = new ArrayList<String>();

        if (path.exists()) {
            File[] files = path.listFiles();
            for (int i = 0; i < files.length; ++i) {
                File file = files[i];
                String substring = file.getName().substring(0,6);
                if (!file.isDirectory()) {
                    if (isInteger(substring)) {
                        DatesList.add(substring.substring(0,2)+ "/" +substring.substring(2,4)+ "/" +substring.substring(4,6));
                        stringMealPeriodRecords.add(stringReader(file.getName(), path));
                    }
                }
            }
        }
        return stringMealPeriodRecords.toArray(new String[stringMealPeriodRecords.size()]);
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

    public void clear(File path){
        String[] files;
        files = path.list();
        for (int i=0; i<files.length; i++) {
            File myFile = new File(path, files[i]);
            myFile.delete();
        }
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

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        //Test RW Strings (Static Text)

        File path = new File(getResources().getString(R.string.main_file_directory));

        //clear(path);
        //testInput(path);

        int maxItems = 40;
        int elements = 0;
        String[][][] tempDashboard = new String[maxItems][][];

        /*for (int i = 0; i < 3; i++){
            String newContents = "";
            try {
                newContents = stringReader(names[i], path);
            } catch (IOException e) {
                e.printStackTrace();
            }
            String[] check = stringParser(newContents);
            String[][] full = arrayParser(check);
            dashboard[i] = full.clone();
        }*/

        String[] traversed = new String[maxItems];

        try {
            traversed = traverseDashboard(path);
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < traversed.length; i++){
            if (traversed[i] != null) {
                String[] check = stringParser(traversed[i]);
                String[][] full = arrayParser(check);
                tempDashboard[i] = full.clone();
            }
            else
                break;
        }

        for (int i = 0; i < maxItems; i++){
            if (tempDashboard[i]!=null){
                elements++;
            }
            else
                break;
        }

        final String[][][] dashboard = new String[elements][][];
        for (int i = 0; i < elements; i++){
            dashboard[i] = tempDashboard[i].clone();
        }





        //Date c = Calendar.getInstance().getTime();
        //System.out.println("Current time => " + c);

        //SimpleDateFormat df = new SimpleDateFormat("MMddyy");
        //String formattedDate = df.format(c);

        //String[] dashboardUnformatted;
        //String[][][] dashboardFormatted;
        //String[][][] dashboardFormatted = new String[20][20][20];

        /*try {
            dashboardUnformatted = traverseDashboard(path);
            String[][] temp;
            dashboardFormatted = new String[dashboardUnformatted.length][][];
            for (int i = 0; i < dashboardUnformatted.length; i++){
                dashboardFormatted[i] = arrayParser(stringParser(dashboardUnformatted[i])).clone();
            }

            Toast toast = Toast.makeText(getActivity(), dashboardFormatted[0][0][0], Toast.LENGTH_SHORT);
            toast.show();
        } catch (IOException e) {
            e.printStackTrace();
        }*/



        //Toast toast = Toast.makeText(getActivity(), formattedDate, Toast.LENGTH_SHORT);
        //Toast toast = Toast.makeText(getActivity(), dashboardFormatted[0][0][0], Toast.LENGTH_SHORT);
        //Toast toast1 = Toast.makeText(getActivity(), dashboard[0][0][0], Toast.LENGTH_SHORT);
        //Toast toast2 = Toast.makeText(getActivity(), dashboard[1][0][0], Toast.LENGTH_SHORT);
        //Toast toast3 = Toast.makeText(getActivity(), dashboard[2][0][0], Toast.LENGTH_SHORT);
        //Toast toast4 = Toast.makeText(getActivity(), dashboard[0][0][1], Toast.LENGTH_SHORT);

        //Toast toast = Toast.makeText(getActivity(), getActivity().getFilesDir() + "", Toast.LENGTH_LONG);
        //toast.show();
        //toast1.show();
        //toast2.show();
        //toast3.show();
        //toast4.show();








        //for(int i = 0; i < full.length; i++ ){
        //                }



        //PieChart Test (Static Numbers)






        ListView myListView;
        String[] restaurants = new String[dashboard.length];
        final String[] times = new String[dashboard.length];
        String[][] formattedMacros = new String[dashboard.length][3];
        DecimalFormat df = new DecimalFormat("#.#");

        for (int i = 0; i < dashboard.length; i++){
            if (dashboard[i]!=null) {
                double totalProtein = 0.0;
                double totalFat = 0.0;
                double totalCarbs = 0.0;
                restaurants[i] = dashboard[i][0][0];
                String tempTimeString = dashboard[i][0][1];
                String formattedTime = "";
                int tempTimeInt = Integer.valueOf(tempTimeString);
                if (tempTimeInt < 1200 && tempTimeInt > 99) {
                    int hour = tempTimeInt / 100;
                    int minute = tempTimeInt - (hour * 100);
                    String minuteString = "";
                    if (minute < 10) {
                        minuteString = "0" + Integer.toString(minute);
                    } else {
                        minuteString = Integer.toString(minute);
                    }
                    formattedTime = DatesList.get(i) + ", " + Integer.toString(hour) + ":" + minuteString + "AM";
                } else if (tempTimeInt < 100) {
                    String minuteString = "";
                    if (tempTimeInt < 10) {
                        minuteString = "0" + Integer.toString(tempTimeInt);
                    } else {
                        minuteString = Integer.toString(tempTimeInt);
                    }
                    formattedTime = DatesList.get(i) + ", " + "12:" + minuteString + "AM";
                } else {
                    int hour = tempTimeInt / 100 - 12;
                    int minute = tempTimeInt - ((hour + 12) * 100);
                    String minuteString = "";
                    if (minute < 10) {
                        minuteString = "0" + Integer.toString(minute);
                    } else {
                        minuteString = Integer.toString(minute);
                    }
                    formattedTime = DatesList.get(i) + ", " + Integer.toString(hour) + ":" + minuteString + "PM";
                }


                times[i] = formattedTime;
                int length = (dashboard[i].length - 2);
                for (int j = 1; j < length; j++) {
                    totalProtein += Double.parseDouble(dashboard[i][j][11]);
                    totalFat += Double.parseDouble(dashboard[i][j][3]);
                    totalCarbs += Double.parseDouble(dashboard[i][j][8]);
                }
                String[] tempString = new String[3];
                tempString[0] = df.format(totalProtein) + "g";
                tempString[1] = df.format(totalFat) + "g";
                tempString[2] = df.format(totalCarbs) + "g";
                formattedMacros[i] = tempString.clone();
            }
            else
                break;
        }

        float sumProtein = 0f;
        float sumFats = 0f;
        float sumCarbs = 0f;

        for(int i = 0; i < dashboard.length; i++){
            if(dashboard[i]!=null) {
                sumProtein += Float.parseFloat(formattedMacros[i][0].substring(0, formattedMacros[i][0].length() - 1));
                sumFats += Float.parseFloat(formattedMacros[i][1].substring(0, formattedMacros[i][1].length() - 1));
                sumCarbs += Float.parseFloat(formattedMacros[i][2].substring(0, formattedMacros[i][2].length() - 1));
            }
            else
                break;
        }

        //ListView Test for Meal Period Adaptation (Static Numbers)

        /*TextView proteinTextView = (TextView) view.findViewById(R.id.proteinTextView);
        TextView fatTextView = (TextView) view.findViewById(R.id.fatTextView);
        TextView carbsTextView = (TextView) view.findViewById(R.id.carbsTextView);

        proteinTextView.setText("Protein " + df.format(sumProtein) + "g");
        fatTextView.setText("Fats " + df.format(sumFats) + "g");
        carbsTextView.setText("Carbs " + df.format(sumCarbs) + "g");*/





        Resources res = getResources();
        myListView = (ListView) view.findViewById(R.id.myHistoryListView);
        if (elements!=0) {
            ItemAdapter itemAdapter = new ItemAdapter(getActivity(), restaurants, formattedMacros, times);
            myListView.setAdapter(itemAdapter);

            myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                    Date c = Calendar.getInstance().getTime();
                    SimpleDateFormat df = new SimpleDateFormat("MMddyy");
                    String formattedDateToday = df.format(c);

                    String formattedTime = times[i].substring(10);
                    String fileName = DatesList.get(i).substring(0,2) + DatesList.get(i).substring(3,5) + DatesList.get(i).substring(6,8) + "_" + dashboard[i][0][1] + ".macro";
                    Intent showDetailActivity = new Intent(getActivity(), DetailActivity.class);
                    showDetailActivity.putExtra("macros.bruin.bruinfoodtracker.TIME_FORMATTED", formattedTime);
                    showDetailActivity.putExtra("macros.bruin.bruinfoodtracker.FILE_NAME", fileName);

                    startActivity(showDetailActivity);
            /*onCreateAnimator(R.anim.slide_in, R.anim.slide_out);*/
                }
            });
        }
        else{
            String[] noData = new String[]{"No Data"};
            String[] nothing = new String[]{""};
            String[][] nothingD = new String[][]{{"0g", "0g", "0g"}};
            ItemAdapter itemAdapter = new ItemAdapter(getActivity(), noData, nothingD, nothing);
            myListView.setAdapter(itemAdapter);
        }
    }
}
