package xyz.phyoekhant.myanmarattractions.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import xyz.phyoekhant.myanmarattractions.MyanmarAttractionsApp;
import xyz.phyoekhant.myanmarattractions.R;
import xyz.phyoekhant.myanmarattractions.data.vos.AttractionVO;
import xyz.phyoekhant.myanmarattractions.fragments.AttractionsFragment;
import xyz.phyoekhant.myanmarattractions.views.holders.AttractionViewHolder;


/**
 * Created by Phyoe Khant on 7/7/2016.
 */
public class AttractionAdapter extends RecyclerView.Adapter<AttractionViewHolder> {

    private LayoutInflater inflater;
    private List<AttractionVO> attractionList;
    private AttractionsFragment.ControllerAttractionItem mAttractionItemController;

    public AttractionAdapter(List<AttractionVO> attractionList, AttractionsFragment.ControllerAttractionItem attractionItemController) {
        inflater = LayoutInflater.from(MyanmarAttractionsApp.getContext());
        this.attractionList = attractionList;
        mAttractionItemController = attractionItemController;
    }

    @Override
    public AttractionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.view_item_attraction, parent, false);
        final AttractionViewHolder attractionVH = new AttractionViewHolder(view, mAttractionItemController);
        return attractionVH;
    }

    @Override
    public void onBindViewHolder(AttractionViewHolder holder, int position) {
        holder.setData(attractionList.get(position));
    }

    @Override
    public int getItemCount() {
        return attractionList.size();
    }
}