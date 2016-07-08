package xyz.phyoekhant.myanmarattractions.data.vos;

import com.google.gson.annotations.SerializedName;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Phyoe Khant on 7/7/2016.
 */
public class AttractionVO {

    //annotation = link for json node name
    @SerializedName("title")
    private String attractionTitle;

    @SerializedName("stock_photo")
    private String stockPhotoPath;

    @SerializedName("desc")
    private String attractionDesc;

    public AttractionVO(String attractionTitle, String stockPhotoPath, String attractionDesc) {
        this.attractionTitle = attractionTitle;
        this.stockPhotoPath = stockPhotoPath;
        this.attractionDesc = attractionDesc;
    }

    public String getAttractionTitle() {
        return attractionTitle;
    }

    public String getStockPhotoPath() {
        return stockPhotoPath;
    }

    public String getAttractionDesc() {
        return attractionDesc;
    }
}
