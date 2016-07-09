package xyz.phyoekhant.mynmarattractions.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import xyz.phyoekhant.mynmarattractions.MyanmarAttractionsApp;
import xyz.phyoekhant.mynmarattractions.R;
import xyz.phyoekhant.mynmarattractions.data.vos.AttractionVO;
import xyz.phyoekhant.mynmarattractions.fragments.AttractionFragment;
import xyz.phyoekhant.mynmarattractions.views.holders.AttractionViewHolder;

/**
 * Created by Phyoe Khant on 7/7/2016.
 */
public class AttractionAdapter extends RecyclerView.Adapter<AttractionViewHolder> {

    private LayoutInflater inflater;
    private List<AttractionVO> eventList;
    private AttractionFragment.ControllerAttractionItem mEventItemController;

    public AttractionAdapter(List<AttractionVO> eventList, AttractionFragment.ControllerAttractionItem eventItemController) {
        inflater = LayoutInflater.from(MyanmarAttractionsApp.getContext());
        this.eventList = eventList;
        mEventItemController = eventItemController;
    }

    @Override
    public AttractionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.view_item_attraction, parent, false);
        final AttractionViewHolder eventVH = new AttractionViewHolder(view, mEventItemController);
        return eventVH;
    }

    @Override
    public void onBindViewHolder(AttractionViewHolder holder, int position) {
        holder.setData(eventList.get(position));
    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }
}