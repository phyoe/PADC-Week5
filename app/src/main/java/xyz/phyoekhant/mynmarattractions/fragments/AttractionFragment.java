package xyz.phyoekhant.mynmarattractions.fragments;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import xyz.phyoekhant.mynmarattractions.R;
import xyz.phyoekhant.mynmarattractions.adapters.AttractionAdapter;
import xyz.phyoekhant.mynmarattractions.data.models.AttractionModel;
import xyz.phyoekhant.mynmarattractions.data.vos.AttractionVO;


/**
 * Created by Phyoe Khant on 7/7/2016.
 */
public class AttractionFragment extends Fragment {
    private AttractionAdapter mAttractionAdapter;
    private ControllerAttractionItem mAttractionItemController;

    public static AttractionFragment newInstance() {
        return new AttractionFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mAttractionItemController = (ControllerAttractionItem) context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d("TAG", "AttractionFragment - onCreate");

        mAttractionAdapter = new AttractionAdapter(AttractionModel.getInstance().getAttractionList(), mAttractionItemController);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Log.d("TAG", "AttractionFragment - onCreateView");

        View view = inflater.inflate(R.layout.fragment_attractions, container, false);

        RecyclerView rvAttraction = (RecyclerView) view.findViewById(R.id.rv_attractions);
        rvAttraction.setAdapter(mAttractionAdapter);
        rvAttraction.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        final SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 3000);
            }
        });
        /**/

        return view;
    }

    public interface ControllerAttractionItem {
        void onTapAttraction(AttractionVO event, ImageView ivStockPhoto);
    }
}