package xyz.phyoekhant.mynmarattractions.data.vos;

import com.google.gson.annotations.SerializedName;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Phyoe Khant on 7/7/2016.
 */
public class AttractionVO {

    public static String IMAGE_URL = "http://www.aungpyaephyo.xyz/myanmar_attractions/";

    //annotation = link for json node name
    @SerializedName("title")
    private String attractionTitle;

    @SerializedName("desc")
    private String attractionDesc;

    //@SerializedName("images")
    //@SerializedName("stock_photo")
    @SerializedName("images")
    private String[] stockPhotoPath;

    private int photo_count;

    public AttractionVO(String attractionTitle,  String[] stockPhotoPath, String attractionDesc) {
        this.attractionTitle = attractionTitle;
        this.attractionDesc = attractionDesc;

        this.stockPhotoPath = stockPhotoPath;
        this.photo_count = stockPhotoPath.length;
    }

    public String getAttractionTitle() {
        return attractionTitle;
    }

    public String getAttractionDesc() {
        return attractionDesc;
    }

    public String[] getStockPhotoPath() {
        for (int i = 0; i < getPhoto_count(); i++){
            stockPhotoPath[i] = IMAGE_URL + stockPhotoPath[i];
        }
        return stockPhotoPath;
    }

    public int getPhoto_count() {
        return photo_count;
    }
}
