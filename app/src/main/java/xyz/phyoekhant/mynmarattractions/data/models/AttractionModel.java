package xyz.phyoekhant.mynmarattractions.data.models;

import android.util.Log;

import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xyz.phyoekhant.mynmarattractions.data.vos.AttractionVO;
import xyz.phyoekhant.mynmarattractions.utils.CommonInstances;
import xyz.phyoekhant.mynmarattractions.utils.JsonUtils;

/**
 * Created by Phyoe Khant on 7/7/2016.
 */
public class AttractionModel {
    private static final String DUMMY_ATTRACTION_LIST = "myanmar_attractions.json";

    private static final String TAG_INFO = "info";
    private static final String TAG_TITLE = "title";
    private static final String TAG_DESC = "desc";
    private static final String TAG_IMAGES = "images";
    private static final String TAG_IMAGE_01 = "image_01";
    private static final String TAG_IMAGE_02 = "image_02";
    private static final String TAG_IMAGE_03 = "image_03";

    private static AttractionModel objInstance;

    private List<AttractionVO> attractionList;
    //private ArrayList<HashMap<String, String>> attractionList_test;

    private AttractionModel(){
        //attractionList_test = initializeAttractionList_test();
        attractionList = initializeAttractionList();
    }

    public static AttractionModel getInstance(){
        if(objInstance == null) {
            objInstance = new AttractionModel();
        }
        return objInstance;
    }

    /**
    private  ArrayList<HashMap<String, String>> initializeAttractionList_test() {
        ArrayList<HashMap<String, String>> attractionList = new ArrayList<HashMap<String, String>>();

        try {
            String dummyAttractionList = JsonUtils.getInstance().loadDummyData(DUMMY_ATTRACTION_LIST);
            JSONObject jsonObj = new JSONObject(dummyAttractionList);

            JSONArray infos = jsonObj.getJSONArray(TAG_INFO);

            Log.d("HashMap : ", infos.length()+"");

            // looping through All infos
            for (int i = 0; i < infos.length(); i++) {
                JSONObject obj = infos.getJSONObject(i);
                String title = obj.getString(TAG_TITLE);
                String desc = obj.getString(TAG_DESC);

                // Images node is JSON Object
                String images = obj.getString(TAG_IMAGES);
                String[] image_array = images.split(",");
                String image_01 = "";
                String image_02 = "";
                String image_03 = "";
                if(image_array.length == 2) {
                    image_01 = image_array[0];
                    image_02 = image_array[1];
                }else if(image_array.length == 3) {
                    image_01 = image_array[0];
                    image_02 = image_array[1];
                    image_03 = image_array[2];
                }

                Log.d("image_array : ", image_array.length+"");


                // tmp hashmap for single info
                HashMap<String, String> info = new HashMap<String, String>();

                // adding every child node to HashMap key => value
                info.put(TAG_TITLE, title);
                info.put(TAG_DESC, desc);
                info.put(TAG_IMAGE_01, image_01);
                info.put(TAG_IMAGE_02, image_02);
                info.put(TAG_IMAGE_03, image_03);

                Log.d("HashMap : ", info.toString() );

                // adding student to students list
                attractionList.add(info);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Type hashType = new TypeToken<List<AttractionVO>>() {
        }.getType();
        return attractionList;
    }
    /**/

    private List<AttractionVO> initializeAttractionList() {
        List<AttractionVO> attractionList = new ArrayList<>();

        try {
            String dummyAttractionList = JsonUtils.getInstance().loadDummyData(DUMMY_ATTRACTION_LIST);
            Type listType = new TypeToken<List<AttractionVO>>() {
            }.getType();
            attractionList = CommonInstances.getGsonInstance().fromJson(dummyAttractionList, listType);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return attractionList;
    }

    public List<AttractionVO> getAttractionList() {
        return attractionList;
    }

    /**
    public ArrayList<HashMap<String, String>> getAttractionList_test() {
        return attractionList_test;
    }
    /**/

    public AttractionVO getAttractionByTitle(String eventTitle) {
        for (AttractionVO attraction : attractionList) {
            if (attraction.getAttractionTitle().equals(eventTitle)) {
                return attraction;
            }
        }
        return null;
    }
}
