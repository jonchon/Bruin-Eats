package macros.bruin.bruinfoodtrackerbeta2;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Switch;

/**
 * Created by Junhyuck on 3/11/2018.
 */

public class SettingsFragment extends Fragment {

    private static Switch sw;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_settings, null);
    }

    SharedPreferences sharedpreferences;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        sw = (Switch) view.findViewById(R.id.switch2);


        final SharedPreferences sharedpreferences = getActivity().getSharedPreferences("Settings", Context.MODE_PRIVATE);
        boolean fidgetDefault = sharedpreferences.getBoolean("isFidgetSpinner", false);
        sw.setChecked(fidgetDefault);

        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                boolean isSpin;
                if (isChecked) {
                    isSpin = true;
                } else {
                    isSpin = false;
                }
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putBoolean("isFidgetSpinner", isSpin);
                editor.commit();
            }
        });












    }
}
