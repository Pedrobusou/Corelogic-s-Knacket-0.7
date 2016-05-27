package uk.co.ribot.Knacket.ui.fragment;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import uk.co.ribot.Knacket.R;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemSelected;

public class FragmentFilter extends Fragment { //It works, but it isn't implemented yet
    @Bind(R.id.spinnerCategory) Spinner spinnerCategory;
    @Bind(R.id.spinnerTime) Spinner spinnerTime;
    @Bind((R.id.tvMaxKm)) TextView tvMaxKm;
    @Bind(R.id.seekBar) SeekBar seekBar;

    private String category, time;
    private int distance;

    public FragmentFilter() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_filter, container);
        ButterKnife.bind(this, view);

        setUpSpinners();
        setUpSeekBar();
        return view;
    }

    @OnClick(R.id.btnReset) void resetClicked(){ //Set ui with default values
        setUpSpinners();
        setUpSeekBar();
    }

    @OnItemSelected(R.id.spinnerTime) void setTime(){ //saves daytime
        time = spinnerTime.getSelectedItem().toString();
    }

    @OnItemSelected(R.id.spinnerCategory) void setCategory(){ //saves category
        category = spinnerCategory.getSelectedItem().toString();
    }

    private void setUpSpinners(){
        //Fill spinner with three-day-times adapter
        List<String> spinnerArray = new ArrayList<String>() {{
            add("Evening");
            add("Weekday");
            add("Weekend");
        }};
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, spinnerArray);
        spinnerTime.setAdapter(spinnerArrayAdapter);

        spinnerArray = new ArrayList<String>() {{ //this one should get categories from server
            add("To Get From Server");
        }};
        spinnerArrayAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, spinnerArray);
        spinnerCategory.setAdapter(spinnerArrayAdapter);

    }

    private void setUpSeekBar(){
        seekBar.setProgress(seekBar.getMax() / 2); //Sets default progress to half of total

        tvMaxKm.setText(seekBar.getProgress() + "km");
        distance = seekBar.getProgress();

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) { //Will show current progress in real time
                tvMaxKm.setText(seekBar.getProgress() + "km");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                distance = seekBar.getProgress();
            }
        });
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}