package xyz.phyoekhant.mynmarattractions.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import xyz.phyoekhant.mynmarattractions.MyanmarAttractionsApp;
import xyz.phyoekhant.mynmarattractions.R;
import xyz.phyoekhant.mynmarattractions.data.vos.AttractionVO;
import xyz.phyoekhant.mynmarattractions.fragments.AttractionFragment;

/**
 * Created by Phyoe Khant on 7/7/2016.
 */
public class AttractionActivity extends AppCompatActivity
        implements AttractionFragment.ControllerAttractionItem {

    @Bind(R.id.toolbar) Toolbar toolbar;

    public static Intent newIntent() {
        Intent intent = new Intent(MyanmarAttractionsApp.getContext(), AttractionActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        Log.d("TAG", "AttractionActivity - onCreate");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attraction);
        ButterKnife.bind(this, this);

        setSupportActionBar(toolbar);

        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(true);
        }

        if (savedInstanceState == null) {
            AttractionFragment fragment = AttractionFragment.newInstance();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fl_container, fragment)
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public void onTapAttraction(AttractionVO attraction, ImageView ivStockPhoto) {

        Toast.makeText(getApplicationContext(), attraction.getAttractionTitle(), Toast.LENGTH_SHORT).show();

        //attraction.getStockPhotoPath()
        String loadedImage= AttractionVO.IMAGE_URL + attraction.getStockPhotoPath()[0];

        Log.d("getStockPhotoPath : ", loadedImage);

        Intent intent = AttractionDetailActivity.newIntent(attraction.getAttractionTitle(),loadedImage);
        ActivityOptionsCompat activityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(this,
                new Pair(ivStockPhoto, getString(R.string.stock_photo_shared_transition)));
        ActivityCompat.startActivity(this, intent, activityOptions.toBundle());
         /**/
    }
}