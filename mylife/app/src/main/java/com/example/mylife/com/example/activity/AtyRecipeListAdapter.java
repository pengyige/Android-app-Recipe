package com.example.mylife.com.example.activity;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mylife.R;
import com.example.mylife.com.example.entity.Recipe;
import com.example.mylife.com.example.net.HttpMethod;
import com.example.mylife.com.example.net.NetConnection;

import java.util.ArrayList;
import java.util.List;

import javax.crypto.spec.IvParameterSpec;

/**
 * Created by 彭旎 on 2017/7/25.
 */

public class AtyRecipeListAdapter extends BaseAdapter {
    private List<Recipe> list_recipe = new ArrayList<Recipe>();
    private Context context;

    public AtyRecipeListAdapter(Context context, List<Recipe> list_recipe) {
        this.context = context;
        this.list_recipe = list_recipe;
    }

    @Override
    public int getCount() {
        return list_recipe.size();
    }

    @Override
    public Object getItem(int i) {
        return list_recipe.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.item_recipe,null);
            TextView tv_recipeName = (TextView)view.findViewById(R.id.tv_recipeName);
            ImageView iv_recipePic = (ImageView)view.findViewById(R.id.iv_recipePic);
            ListCell listCell = new ListCell(tv_recipeName,iv_recipePic);
            view.setTag(listCell);
        }

      final  ListCell lc = (ListCell) view.getTag();
       Recipe recipe =(Recipe) getItem(i);
        lc.getTv_recipeName().setText(recipe.title);
        String image_url = recipe.albms.get(0).replace("\\","");
        Glide.with(getContext()).load(image_url).into(lc.getIv_recipePic());

        return view;

    }
    public Context getContext()
    {
        return this.context;
    }

    public static class ListCell
    {
        private TextView tv_recipeName;
        private ImageView iv_recipePic;
        public ListCell(TextView tv_recipeName,ImageView iv_recipePic)
        {
            this.tv_recipeName = tv_recipeName;
            this.iv_recipePic = iv_recipePic;
        }
        public TextView getTv_recipeName()
        {
            return tv_recipeName;
        }
        public ImageView getIv_recipePic()
        {
            return iv_recipePic;
        }
    }





}

