package com.example.mylife.com.example.activity;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mylife.R;
import com.example.mylife.com.example.entity.RecipeType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 彭旎 on 2017/7/27.
 */

public class AtyRecipeTypeIdAdapter extends BaseAdapter {
    private Context context;
    private List<RecipeType.RecipeList> list_recipeList = new ArrayList<RecipeType.RecipeList>();

    public AtyRecipeTypeIdAdapter(Context context, List<RecipeType.RecipeList> list_recipeList)
    {
        this.context = context;
        this.list_recipeList = list_recipeList;
    }
    @Override
    public int getCount() {
        return list_recipeList.size();
    }

    @Override
    public Object getItem(int i) {
        return list_recipeList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null)
        {
            view = View.inflate(getContext(), R.layout.item_recipe_id_type,null);
            TextView tv_recipeIdType = (TextView) view.findViewById(R.id.tv_recipeIdType);
            ListCell lc = new ListCell(tv_recipeIdType);
            view.setTag(lc);
        }

        //得到单个一项
        //设置控件属性
       ListCell lc = (ListCell) view.getTag();
        lc.getTv_recipeType().setText(list_recipeList.get(i).name);
        return view;
    }

    public  Context getContext()
    {
        return this.context;
    }

    public void clear()
    {
        list_recipeList.clear();
    }

    public static class ListCell
    {
        private TextView tv_recipeType;

        public ListCell(TextView tv_recipeName)
        {
            this.tv_recipeType = tv_recipeName;
        }
        public TextView getTv_recipeType()
        {
            return tv_recipeType;
        }

    }
}
