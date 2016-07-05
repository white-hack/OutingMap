package com.hack.white.outingmap.view;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hack.white.outingmap.R;
import com.hack.white.outingmap.data.CardData;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class CardRecyclerAdapter extends RecyclerView.Adapter<CardRecyclerAdapter.ViewHolder> {
    private Context context;
    private CardData cardData;
    private List<JSONArray> jsonList = new ArrayList<>();
    public int itemCount = 0;

    //一度に表示する一覧数
    private static int LIST_VIEW_COUNT = 100;

    public CardRecyclerAdapter(Context context, JSONArray shops, int count) {
        super();
        this.context = context;
        cardData = new CardData(context);
        jsonList.add(shops);
        itemCount = count;
    }

    @Override
    public int getItemCount() {
        return itemCount;
    }

    @Override
    public void onBindViewHolder(ViewHolder vh, final int position) {
        try {
            cardData.getCardDetailData(jsonList.get(position / LIST_VIEW_COUNT).getJSONObject(position % LIST_VIEW_COUNT), vh);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public CardRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View v = layoutInflater.inflate(R.layout.card_view, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView_main;
        public TextView textView_sub;
        public LinearLayout layout;
        public ImageView imageView;

        public ViewHolder(View v) {
            super(v);
            textView_main = (TextView) v.findViewById(R.id.textView_main);
            textView_sub = (TextView) v.findViewById(R.id.textView_sub);
            layout = (LinearLayout) v.findViewById(R.id.layout);
            imageView = (ImageView) v.findViewById(R.id.imageView);
        }
    }
}
