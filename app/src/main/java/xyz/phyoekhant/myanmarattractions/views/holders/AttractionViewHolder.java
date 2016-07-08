package xyz.phyoekhant.myanmarattractions.views.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.Bind;
import butterknife.ButterKnife;
import xyz.phyoekhant.myanmarattractions.R;
import xyz.phyoekhant.myanmarattractions.data.vos.AttractionVO;
import xyz.phyoekhant.myanmarattractions.fragments.AttractionsFragment;

/**
 * Created by aung on 6/26/16.
 */
public class AttractionViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    @Bind(R.id.tv_attraction_title) TextView tvAttractionTitle;
    @Bind(R.id.iv_stock_photo)  ImageView ivStockPhoto;
    @Bind(R.id.tv_attraction_desc)  TextView tvAttractionDesc;

    private AttractionVO mAttraction;
    private AttractionsFragment.ControllerAttractionItem mAttractionItemController;

    public AttractionViewHolder(View view, AttractionsFragment.ControllerAttractionItem attractionItemController) {
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

        /**
        Glide.with(ivStockPhoto.getContext())
                .load(attraction.getStockPhotoPath())
                .centerCrop()
                .placeholder(R.drawable.stock_photo_placeholder)
                .error(R.drawable.stock_photo_placeholder)
                .into(ivStockPhoto);
        /**/
    }

    @Override
    public void onClick(View view) {
        mAttractionItemController.onTapEvent(mAttraction, ivStockPhoto);
    }
}