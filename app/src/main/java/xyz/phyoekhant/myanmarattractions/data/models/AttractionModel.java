package xyz.phyoekhant.myanmarattractions.data.models;

import android.util.Log;

import com.google.gson.reflect.TypeToken;

import org.json.JSONException;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import xyz.phyoekhant.myanmarattractions.data.vos.AttractionVO;
import xyz.phyoekhant.myanmarattractions.utils.CommonInstances;
import xyz.phyoekhant.myanmarattractions.utils.JsonUtils;

/**
 * Created by Phyoe Khant on 7/7/2016.
 */
public class AttractionModel {

    private static final String DUMMY_ATTRACTION_LIST = "myanmar_attractions.json";

    private static AttractionModel objInstance;

    private List<AttractionVO> attractionList;

    private AttractionModel(){
        attractionList = initializeAttractionList();
    }

    public static AttractionModel getInstance(){
        if(objInstance == null) {
            objInstance = new AttractionModel();
        }

        return objInstance;
    }

    private List<AttractionVO> initializeAttractionList() {
        List<AttractionVO> eventList = new ArrayList<>();

        try {
            String dummyEventList = JsonUtils.getInstance().loadDummyData(DUMMY_ATTRACTION_LIST);
            Type listType = new TypeToken<List<AttractionVO>>() {
            }.getType();
            eventList = CommonInstances.getGsonInstance().fromJson(dummyEventList, listType);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.i("DB Data : ",eventList.toString());
        return eventList;
    }

    public List<AttractionVO> getAttractionList() {
        return attractionList;
    }

    public AttractionVO getAttractionByTitle(String eventTitle) {
        for (AttractionVO attraction : attractionList) {
            if (attraction.getAttractionTitle().equals(eventTitle)) {
                return attraction;
            }
        }
        return null;
    }
}
