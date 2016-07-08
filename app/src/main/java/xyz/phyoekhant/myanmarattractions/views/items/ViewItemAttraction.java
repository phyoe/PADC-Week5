package xyz.phyoekhant.myanmarattractions.views.items;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import xyz.phyoekhant.myanmarattractions.R;
import xyz.phyoekhant.myanmarattractions.data.vos.AttractionVO;

/**
 * Created by Phyoe Khant on 7/7/2016.
 */
public class ViewItemAttraction extends CardView {

    private TextView tvAttractionTitle;
    private ImageView ivStockPhoto;
    private TextView tvAttractionDesc;

    public ViewItemAttraction(Context context) {
        super(context);
    }

    public ViewItemAttraction(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ViewItemAttraction(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        tvAttractionTitle = (TextView) findViewById(R.id.tv_attraction_title);
        ivStockPhoto = (ImageView) findViewById(R.id.iv_stock_photo);
        tvAttractionDesc = (TextView) findViewById(R.id.tv_attraction_desc);
    }

    public void setData(AttractionVO attraction) {
        tvAttractionTitle.setText(attraction.getAttractionTitle());
        tvAttractionDesc.setText(attraction.getAttractionDesc());

        Glide.with(getContext())
                .load(attraction.getStockPhotoPath())
                .centerCrop()
                .placeholder(R.drawable.stock_photo_placeholder)
                .into(ivStockPhoto);
    }
}
