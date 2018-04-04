package macros.bruin.bruinfoodtrackerbeta2;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by Junhyuck on 3/7/2018.
 */

public class RestuarantAdapter extends BaseAdapter {

    LayoutInflater mInflater;
    String[] restaurants;
    String[] times;
    String[] periods;

    public RestuarantAdapter(Context c, String[] i, String[] p, String[] d){
        restaurants = i;
        times = p;
        periods = d;
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

        View v = mInflater.inflate(R.layout.my_restaurantview_detail, null);

        TextView nameTextView = (TextView) v.findViewById(R.id.menuNameTextView);
        TextView timeTextView = (TextView) v.findViewById(R.id.timeTextView);
        TextView periodTextView = (TextView) v.findViewById(R.id.periodTextView);

        nameTextView.setText(restaurants[i]);
        timeTextView.setText(times[i]);
        periodTextView.setText(periods[i]);

        if (periods[i].equals("Breakfast")){
            periodTextView.setTextColor(Color.parseColor("#00a017"));
        }
        else if (periods[i].equals("Lunch/Brunch")){
            periodTextView.setTextColor(Color.parseColor("#00a08d"));
        }
        else if (periods[i].equals("Lunch")){
            periodTextView.setTextColor(Color.parseColor("#000da0"));
        }
        else if (periods[i].equals("Dinner")){
            periodTextView.setTextColor(Color.parseColor("#5d00a0"));
        }
        else if (periods[i].equals("Late Night")){
            periodTextView.setTextColor(Color.parseColor("#a00000"));
        }

        return v;
    }
}
