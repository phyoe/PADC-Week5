package xyz.phyoekhant.mynmarattractions.views.holders;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.Bind;
import butterknife.ButterKnife;
import xyz.phyoekhant.mynmarattractions.R;
import xyz.phyoekhant.mynmarattractions.data.vos.AttractionVO;
import xyz.phyoekhant.mynmarattractions.fragments.AttractionFragment;

/**
 * Created by Phyoe Khant on 7/7/2016.
 */
public class AttractionViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    @Bind(R.id.tv_attraction_title) TextView tvAttractionTitle;
    @Bind(R.id.iv_stock_photo)  ImageView ivStockPhoto;
    @Bind(R.id.tv_attraction_desc)  TextView tvAttractionDesc;

    private AttractionVO mAttraction;
    private AttractionFragment.ControllerAttractionItem mAttractionItemController;

    public AttractionViewHolder(View view, AttractionFragment.ControllerAttractionItem attractionItemController) {
        super(view);
        ButterKnife.bind(this, view);

        view.setOnClickListener(this);
        mAttractionItemController = attractionItemController;

        tvAttractionTitle = (TextView) view.findViewById(R.id.tv_attraction_title);
        ivStockPhoto = (ImageView) view.findViewById(R.id.iv_stock_photo);
        tvAttractionDesc = (TextView) view.findViewById(R.id.tv_attraction_desc);
    }

    public void setData(AttractionVO attraction) {
        this.mAttraction = attraction;

        tvAttractionTitle.setText(attraction.getAttractionTitle());
        tvAttractionDesc.setText(attraction.getAttractionDesc());

        String loadedImage= AttractionVO.IMAGE_URL + attraction.getStockPhotoPath()[0];

        Log.d("getStockPhotoPath : ", loadedImage);

        Glide.with(ivStockPhoto.getContext())
                .load(loadedImage)
                .centerCrop()
                .placeholder(R.drawable.stock_photo_placeholder)
                .error(R.drawable.stock_photo_placeholder)
                .into(ivStockPhoto);
    }

    @Override
    public void onClick(View view) {
        mAttractionItemController.onTapAttraction(mAttraction, ivStockPhoto);
    }
}