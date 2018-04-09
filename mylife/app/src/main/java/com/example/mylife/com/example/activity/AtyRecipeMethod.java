package com.example.mylife.com.example.activity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mylife.R;
import com.example.mylife.com.example.config.Config;
import com.example.mylife.com.example.entity.Recipe;

import org.w3c.dom.Text;

/**
 * Created by 彭旎 on 2017/7/26.
 */

public class AtyRecipeMethod extends AppCompatActivity {
    private TextView tv_Name;//菜名
    private TextView tv_type;//类型
    private TextView tv_imtro;//简介
    private TextView tv_ingredients;//食材
    private TextView tv_burden;//量
    private ImageView iv_albums;//食品图片
    private LinearLayout recipe_layout;
    static Recipe recipe;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reciipe_method);
        init();
        if(recipe != null)
        {
            String image_url = recipe.albms.get(0).replace("\\","");
            Glide.with(this).load(image_url).into(iv_albums);

            tv_Name.setText(recipe.title);
            tv_type.setText(recipe.tags);
            tv_imtro.setText(recipe.imtro);
            tv_burden.setText(recipe.burden);
            tv_ingredients.setText(recipe.ingredients);



            //动态添加菜谱方法布局
            recipe_layout.removeAllViews();
            for(Recipe.Steps steps :recipe.list_steps)
            {
                View view = LayoutInflater.from(this).inflate(R.layout.recipe_method_item,recipe_layout,false);
                ImageView iv_recipeMethodPic = (ImageView)view.findViewById(R.id.iv_recipeMethodPic);
                TextView tv_recipeMethodText = (TextView) view.findViewById(R.id.tv_recipeMethodText);

                String imageMethod = steps.img.replace("\\","");
                Glide.with(this).load(imageMethod).into(iv_recipeMethodPic);

                tv_recipeMethodText.setText(steps.step);
                recipe_layout.addView(view);

            }
        }
    }

    public void init()
    {
        tv_Name = (TextView) findViewById(R.id.tv_recipeMehodName);
        tv_type = (TextView) findViewById(R.id.tv_recipeType);
        tv_imtro = (TextView) findViewById(R.id.tv_recipeImtro);
        tv_ingredients = (TextView) findViewById(R.id.tv_recipeIngredients);
        tv_burden = (TextView) findViewById(R.id.tv_recipeBurden);
        iv_albums = (ImageView) findViewById(R.id.iv_recipePicture);
        recipe_layout = (LinearLayout) findViewById(R.id.recipe_layout);

    }
}
