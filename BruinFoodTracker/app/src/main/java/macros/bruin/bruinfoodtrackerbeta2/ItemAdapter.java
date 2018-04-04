package macros.bruin.bruinfoodtrackerbeta2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by Junhyuck on 3/7/2018.
 */

public class ItemAdapter extends BaseAdapter {

    LayoutInflater mInflater;
    String[] restaurants;
    String[][] macros;
    String[] times;

    public ItemAdapter(Context c, String[] i, String[][] p, String[] d){
        restaurants = i;
        macros = p;
        times = d;
        mInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return restaurants.length;
    }

    @Override
    public Object getItem(int i) {
        return restaurants[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View v = mInflater.inflate(R.layout.my_listview_detail, null);
        TextView nameTextView = (TextView) v.findViewById(R.id.menuNameTextView);
        TextView proteinTextView = (TextView) v.findViewById(R.id.proteinTextView);
        TextView fatTextView = (TextView) v.findViewById(R.id.fatTextView);
        TextView carbTextView = (TextView) v.findViewById(R.id.carbsTextView);
        TextView timeTextView = (TextView) v.findViewById(R.id.timeTextView);

        String name = restaurants[i];
        String protein = macros[i][0];
        String fat = macros[i][1];
        String carb = macros[i][2];
        String time = times[i];

        nameTextView.setText(name);
        proteinTextView.setText(protein);
        fatTextView.setText(fat);
        carbTextView.setText(carb);
        timeTextView.setText(time);

        return v;
    }
}
