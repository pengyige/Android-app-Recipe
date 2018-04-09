package com.example.mylife.com.example.activity;

import android.content.Context;
import android.media.Image;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mylife.R;
import com.example.mylife.com.example.entity.RecipeType;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 彭旎 on 2017/7/27.
 */

public class AtyRecipeTypeAdapter extends BaseAdapter {
    private Context context;
    private List<RecipeType> list_recipeType = new ArrayList<RecipeType>();

    public AtyRecipeTypeAdapter(Context context ,List<RecipeType> list_recipeType)
    {
        this.context  = context;
        this.list_recipeType = list_recipeType;
    }
    @Override
    public int getCount() {
        return list_recipeType.size();
    }

    @Override
    public Object getItem(int i) {
        return list_recipeType.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null)
        {
          view = View.inflate(getContext(), R.layout.item_recipe_type,null);
            TextView tv_recipeTypeText = (TextView) view.findViewById(R.id.tv_recipeTypeText);
            ImageView iv_recipeTypePic = (ImageView) view.findViewById(R.id.iv_recipeTypePic);
            ListCell lc = new ListCell(tv_recipeTypeText,iv_recipeTypePic);
            view.setTag(lc);

        }

        ListCell lc = (ListCell) view.getTag();
        lc.getTv_recipeName().setText(list_recipeType.get(i).name);
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
