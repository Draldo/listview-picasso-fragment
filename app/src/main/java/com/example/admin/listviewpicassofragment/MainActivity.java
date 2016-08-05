package com.example.admin.listviewpicassofragment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity implements ListviewFragment.CallbackComponent {

    private static final String TAG = MainActivity.class.getSimpleName() + "TAG_";
    private static final String[] img = new String[]{"http://goo.gl/Jy4clk","http://goo.gl/7Wa3Tv","http://goo.gl/AzrjpX","http://goo.gl/HlF9o7","https://goo.gl/BmIp1G"};

    private ImageView mImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mImage = (ImageView) findViewById(R.id.imageview_item);
    }

    public void itemClicked(int position){
        if (mImage != null) {
            Picasso
                    .with(getApplication())
                    .load(img[position])
                    .fit()
                    .into(mImage);
        }
    }
}
