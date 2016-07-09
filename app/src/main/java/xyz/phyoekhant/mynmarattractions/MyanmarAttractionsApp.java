package xyz.phyoekhant.mynmarattractions;

import android.app.Application;
import android.content.Context;

/**
 * Created by Phyoe Khant on 7/7/2016.
 */
public class MyanmarAttractionsApp extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    public static Context getContext() {
        return context;
    }
}
