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
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import uk.co.ribot.Knacket.R;
import uk.co.ribot.Knacket.ui.fragment.FragmentNavigationButtons;

public class EditProfile extends AppCompatActivity implements FragmentNavigationButtons.OnFragmentInteractionListener {
    @Bind(R.id.rlProfilePic) RelativeLayout rlProfilePic;
    @Bind(R.id.rlProfileVid) RelativeLayout rlProfileVid;
    @Bind(R.id.tvProfilePic) TextView tvProfilePic;
    @Bind(R.id.tvProfileVid) TextView tvProfileVid;
    @Bind(R.id.toolbar_title) TextView toolbar_title;
    @Bind(R.id.toolbar) Toolbar toolbar;

    private int SELECT_IMAGE = 23748;
    private int SELECT_VIDEO = 23749;
    private int TAKE_PICTURE = 29038;
    private int TAKE_VIDEO = 29039;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        ButterKnife.bind(this);

        toolbar_title.setText(R.string.title_activity_edit_profile);
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
    }

    public void setUpContent() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
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
        } catch(Exception e){}
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