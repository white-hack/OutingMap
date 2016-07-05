package com.hack.white.outingmap.data;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import com.hack.white.outingmap.common.MapHttpClient;
import com.hack.white.outingmap.common.MapUtil;
import com.hack.white.outingmap.view.CardRecyclerAdapter;

import org.json.JSONObject;

import java.net.URLDecoder;

public class CardData {
    private Context context;

    public CardData(Context context) {
        this.context = context;
    }

    public void getCardDetailData(JSONObject json, CardRecyclerAdapter.ViewHolder vh) {
        try {
            vh.textView_main.setText(json.getString("title"));
            vh.textView_sub.setText(MapUtil.getCategory(json.getJSONArray("category")));
            MapHttpClient.getImage(URLDecoder.decode(json.getString("imgUrl"), "UTF-8"), vh.imageView);
            vh.layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "test", Toast.LENGTH_SHORT).show();
                }
            });

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
