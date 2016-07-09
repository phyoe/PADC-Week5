package xyz.phyoekhant.mynmarattractions.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import xyz.phyoekhant.mynmarattractions.MyanmarAttractionsApp;
import xyz.phyoekhant.mynmarattractions.R;
import xyz.phyoekhant.mynmarattractions.data.models.AttractionModel;
import xyz.phyoekhant.mynmarattractions.data.vos.AttractionVO;

/**
 * Created by Phyoe Khant on 7/7/2016.
 */
public class AttractionDetailActivity extends AppCompatActivity {

    @Bind(R.id.fab_share)
    FloatingActionButton fabShare;
    @Bind(R.id.toolbar)
    Toolbar toolbar;

    private TextView tvAttractionDesc;
    private ImageView ivAttractionPhoto;
    private CollapsingToolbarLayout collapsingToolbar;
    private ShareActionProvider mShareActionProvider;

    private LinearLayout layout_other_image_holder;
    private ImageView iv_img_01;
    private ImageView iv_img_02;
    private ImageView iv_img_03;

    private static final String IE_ATTRACTION_TITLE = "IE_ATTRACTION_TITLE";
    private static final String IE_ATTRACTION_IMG_URL = "IE_ATTRACTION_IMG_URL";


    public static Intent newIntent(String attractionTitle, String attractionImageURL) {
        Intent intent = new Intent(MyanmarAttractionsApp.getContext(), AttractionDetailActivity.class);
        intent.putExtra(IE_ATTRACTION_TITLE, attractionTitle);
        intent.putExtra(IE_ATTRACTION_IMG_URL, attractionImageURL);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attraction_detail);
        ButterKnife.bind(this, this);

        setSupportActionBar(toolbar);
        ivAttractionPhoto = (ImageView) findViewById(R.id.iv_stock_photo);
        tvAttractionDesc = (TextView) findViewById(R.id.tv_event_desc);
        collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_accent_24dp);
        }

        //Gracefully Forback
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Context context = MyanmarAttractionsApp.getContext();
            String transitionName = getResources().getString(R.string.stock_photo_shared_transition);
            ivAttractionPhoto.setTransitionName(transitionName);
        }

        String attractionTitle = getIntent().getStringExtra(IE_ATTRACTION_TITLE);

        AttractionVO attraction = AttractionModel.getInstance().getAttractionByTitle(attractionTitle);
        if (attraction == null) {
            throw new RuntimeException("Can't find Attraction obj with the title : " + attractionTitle);
        } else {
            collapsingToolbar.setTitle(attraction.getAttractionTitle());
            tvAttractionDesc.setText(attraction.getAttractionDesc() + "\n\n" +
                    attraction.getAttractionDesc() + "\n\n" +
                    attraction.getAttractionDesc() + "\n\n" +
                    attraction.getAttractionDesc() + "\n\n" +
                    attraction.getAttractionDesc());

            String loadedImage = AttractionVO.IMAGE_URL + attraction.getStockPhotoPath()[0];

            Log.d("loadedImage : ", loadedImage);

            Glide.with(ivAttractionPhoto.getContext())
                    .load(loadedImage)
                    .centerCrop()
                    .error(R.drawable.stock_photo_placeholder)
                    .placeholder(R.drawable.stock_photo_placeholder)
                    .into(ivAttractionPhoto);


            iv_img_01 = (ImageView) findViewById(R.id.iv_img_01);
            iv_img_02 = (ImageView) findViewById(R.id.iv_img_02);

            //Start from second image
            Log.d("getPhoto_count : ", attraction.getPhoto_count() + "");

            int image_count = attraction.getStockPhotoPath().length;
            Log.d("tempImage : ", image_count + "");

            for (int i = 1; i < image_count; i++) {

                String tempImage = AttractionVO.IMAGE_URL + attraction.getStockPhotoPath()[i];

                Log.d("loadedImage : ", tempImage);

                //setting image resource
                if (i == 1) {
                    Glide.with(iv_img_01.getContext())
                            .load(tempImage)
                            .centerCrop()
                            .error(R.drawable.stock_photo_placeholder)
                            .placeholder(R.drawable.stock_photo_placeholder)
                            .into(iv_img_01);
                }
                if (i == 2) {
                    Glide.with(iv_img_02.getContext())
                            .load(tempImage)
                            .centerCrop()
                            .error(R.drawable.stock_photo_placeholder)
                            .placeholder(R.drawable.stock_photo_placeholder)
                            .into(iv_img_02);
                }
            }

        }
    }

    @OnClick(R.id.fab_share)
    public void onTapShare(View view) {

        /**
         Snackbar.make(view, "Share on Myanmar Attractions", Snackbar.LENGTH_LONG)
         .setAction("Action", null).show();
         /**/

        String attractionTitle = getIntent().getStringExtra(IE_ATTRACTION_TITLE);
        String attractionImgURL = getIntent().getStringExtra(IE_ATTRACTION_IMG_URL);

        String sharedTitle = "You shared \"" + attractionTitle + "\" Myanmar Attaraction";

        Intent sharingIntent = createShareIntent(attractionImgURL);
        startActivity(Intent.createChooser(sharingIntent, sharedTitle));
    }

    private static Intent createShareIntent(String sharURL) {
        Intent myShareIntent = new Intent(android.content.Intent.ACTION_SEND);
        myShareIntent.setType("text/plain");
        myShareIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "\n\n");
        myShareIntent.putExtra(android.content.Intent.EXTRA_TEXT, sharURL);
        return myShareIntent;
    }

}