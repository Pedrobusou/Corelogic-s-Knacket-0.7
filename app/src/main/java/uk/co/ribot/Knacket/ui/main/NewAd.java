package uk.co.ribot.Knacket.ui.main;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnItemSelected;
import uk.co.ribot.Knacket.R;
import uk.co.ribot.Knacket.ui.fragment.FragmentNavigationButtons;

public class NewAd extends AppCompatActivity implements FragmentNavigationButtons.OnFragmentInteractionListener {
    @Bind(R.id.spinnerCategory) Spinner spinnerCategory;
    @Bind(R.id.rlDateTime) RelativeLayout rlDateTime;
    @Bind(R.id.rlProfilePic) RelativeLayout rlProfilePic;
    @Bind(R.id.rlProfileVid) RelativeLayout rlProfileVid;
    @Bind(R.id.tvProfilePic) TextView tvProfilePic;
    @Bind(R.id.tvProfileVid) TextView tvProfileVid;
    @Bind(R.id.etDateTime) TextView dateTime;
    @Bind(R.id.toolbar) Toolbar toolbar;

    private int SELECT_IMAGE = 23748;
    private int SELECT_VIDEO = 23749;
    private int TAKE_PICTURE = 29038;
    private int TAKE_VIDEO = 29039;
    public String category;
    public String date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_ad);
        ButterKnife.bind(this);

        setUpContent();

        rlProfilePic.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                takePic();
                return false;
            }
        });

        rlProfileVid.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                takeVid();
                return false;
            }
        });

        rlDateTime.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                dateTimePicker();
                return false;
            }
        });
    }

    @OnItemSelected(R.id.spinnerCategory) void setCategory(){
        category = spinnerCategory.getSelectedItem().toString();
    }

    public void setUpContent() {
        setSupportActionBar(toolbar);

        List<String> spinnerArray = new ArrayList<String>() {{
            add("To Get From Server");
        }};

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, spinnerArray);
        spinnerCategory.setAdapter(spinnerArrayAdapter);
    }

    public void dateTimePicker() {
        final View dialogView = View.inflate(this, R.layout.dialog_date_time_picker, null);
        final AlertDialog alertDialog = new AlertDialog.Builder(this).create();

        dialogView.findViewById(R.id.date_time_set).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatePicker datePicker = (DatePicker) dialogView.findViewById(R.id.date_picker);
                TimePicker timePicker = (TimePicker) dialogView.findViewById(R.id.time_picker);

                date = datePicker.getYear() + "-" + datePicker.getMonth() + "-" + datePicker.getDayOfMonth() + " " + timePicker.getCurrentHour() + ":" + timePicker.getCurrentMinute();
                dateTime.setText(date);
                alertDialog.dismiss();
            }
        });
        alertDialog.setView(dialogView);
        alertDialog.show();
    }

    public void takePic(){
        try{
            final CharSequence[] items = {"Select from gallery", "Take pic"};

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Select a pic");
            builder.setItems(items, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int item) {
                    switch(item){
                        case 0:
                            Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                            intent.setType("image/*");
                            startActivityForResult(intent, SELECT_IMAGE);
                            break;
                        case 1:
                            startActivityForResult(new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE), TAKE_PICTURE);
                            break;
                    }

                }
            });
            AlertDialog alert = builder.create();
            alert.show();
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public void takeVid(){
        try{
            final CharSequence[] items = {"Select from gallery", "Take vid"};

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Select a vid");
            builder.setItems(items, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int item) {
                    switch(item){
                        case 0:
                            Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Video.Media.INTERNAL_CONTENT_URI);
                            intent.setType("video/*");
                            startActivityForResult(intent, SELECT_VIDEO);
                            break;
                        case 1:
                            startActivityForResult(new Intent(android.provider.MediaStore.ACTION_VIDEO_CAPTURE), TAKE_VIDEO);
                            break;
                    }

                }
            });
            AlertDialog alert = builder.create();
            alert.show();
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        try{
            if (requestCode == SELECT_IMAGE)
                if (resultCode == Activity.RESULT_OK) {
                    Uri selectedImage = data.getData();
                    tvProfilePic.setText(getPathImage(selectedImage));
                    //imgPhoto.setImageURI(selectedImage);
                }
            if(requestCode == TAKE_PICTURE)
                if(resultCode == Activity.RESULT_OK){
                    Uri selectedImage = data.getData();
                    tvProfilePic.setText(getPathImage(selectedImage));
                    //imgPhoto.setImageURI(selectedImage);
                }

            if (requestCode == SELECT_VIDEO)
                if (resultCode == Activity.RESULT_OK) {
                    Uri selectedVideo = data.getData();
                    tvProfileVid.setText(getPathVid(selectedVideo));
                    //imgPhoto.setImageURI(selectedImage);
                }
            if(requestCode == TAKE_VIDEO)
                if(resultCode == Activity.RESULT_OK){
                    Uri selectedVideo = data.getData();
                    tvProfileVid.setText(getPathVid(selectedVideo));
                    //imgPhoto.setImageURI(selectedImage);
                }
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    private String getPathImage(Uri uri) {
        String[] projection = { android.provider.MediaStore.Images.Media.DATA };
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(android.provider.MediaStore.Video.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }

    private String getPathVid(Uri uri) {
        String[] projection = { android.provider.MediaStore.Video.Media.DATA };
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(android.provider.MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {}
}