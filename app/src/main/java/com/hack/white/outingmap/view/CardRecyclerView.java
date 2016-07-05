package com.hack.white.outingmap.view;

import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;

import com.hack.white.outingmap.common.MapHttpClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CardRecyclerView extends RecyclerView {
    public CardRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setRecyclerAdapter(context);
        setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
    }

    public void setRecyclerAdapter(final Context context) {
        final Handler handler = new Handler();
        setLayoutManager(new LinearLayoutManager(context));
        MapHttpClient.getJson("",
                new MapHttpClient.HttpClientInterface() {
                    @Override
                    public void onSuccess(Object param) {
                        try {
                            final JSONArray shops = ((JSONObject)param).getJSONArray("shops");
                            final int count = ((JSONObject)param).getInt("count");
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    setAdapter(new CardRecyclerAdapter(context, shops, count));
                                }
                            });
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
    }
}