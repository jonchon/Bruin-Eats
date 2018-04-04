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

public class MenuAdapterSimple extends BaseAdapter {

    LayoutInflater mInflater;
    String[] menu;


    public MenuAdapterSimple(Context c, String[] i){
        menu = i;
        mInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return menu.length;
    }

    @Override
    public Object getItem(int i) {
        return menu[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View v = mInflater.inflate(R.layout.my_foodview_detail2, null);
        TextView nameTextView = (TextView) v.findViewById(R.id.menuNameTextView2);

        nameTextView.setText(menu[i]);


        return v;
    }
}
