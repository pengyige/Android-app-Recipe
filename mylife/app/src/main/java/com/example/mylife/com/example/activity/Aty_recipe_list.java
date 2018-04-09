package com.example.mylife.com.example.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mylife.R;
import com.example.mylife.com.example.config.Config;
import com.example.mylife.com.example.entity.Recipe;
import com.example.mylife.com.example.net.GetRecipe;
import com.example.mylife.com.example.net.GetRecipeById;

import java.util.List;

/**
 * Created by 彭旎 on 2017/7/25.
 */

public class Aty_recipe_list extends AppCompatActivity{
    private ListView lv_recipeList;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_list);
        lv_recipeList = (ListView) findViewById(R.id.lv_recipeList);
        Intent intent = getIntent();
        String recipe_Name = intent.getStringExtra(Config.RECIPE_NAME);
        String recipe_Id = intent.getStringExtra(Config.RECIPE_TYPE_ID);

        //获取菜谱信息
        if(recipe_Name != null) {
            AcquireRecipe(recipe_Name);
        }
        else
        {
            if(recipe_Id != null)
            AcquireRecipeById(recipe_Id);
        }
    }

    public  void AcquireRecipe(String recipe_Name)
    {
        final ProgressDialog pd = ProgressDialog.show(Aty_recipe_list.this,getResources().getString(R.string.connect_server_now),
                getResources().getString(R.string.connect_server));
           new GetRecipe(Config.APPKEY,recipe_Name, new GetRecipe.SuccessCallback() {
            @Override
            public void success(List<Recipe> success) {
                pd.dismiss();
                //初始化适配器
                AtyRecipeListAdapter atyRecipeListAdapter = new AtyRecipeListAdapter(Aty_recipe_list.this,
                        success);
                lv_recipeList.setAdapter(atyRecipeListAdapter);
                lv_recipeList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    /*打菜谱详细界面*/
                        Intent intent = new Intent(Aty_recipe_list.this
                        ,AtyRecipeMethod.class);
                       Recipe recipe =  (Recipe)adapterView.getItemAtPosition(i);
                        AtyRecipeMethod.recipe = recipe;
                        Aty_recipe_list.this.startActivity(intent);
                    }
                });

            }
        }, new GetRecipe.FailCallback() {
            @Override
            public void fail() {
                pd.dismiss();
                Toast.makeText(Aty_recipe_list.this,"失败了",Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void AcquireRecipeById(String recipe_Id)
    {
        final ProgressDialog pd = ProgressDialog.show(Aty_recipe_list.this,getResources().getString(R.string.connect_server_now),
                getResources().getString(R.string.connect_server));
        new GetRecipeById(Config.APPKEY,recipe_Id, new GetRecipe.SuccessCallback() {
            @Override
            public void success(List<Recipe> success) {
                pd.dismiss();
                //初始化适配器
                AtyRecipeListAdapter atyRecipeListAdapter = new AtyRecipeListAdapter(Aty_recipe_list.this,
                        success);
                lv_recipeList.setAdapter(atyRecipeListAdapter);
                lv_recipeList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    /*打菜谱详细界面*/
                        Intent intent = new Intent(Aty_recipe_list.this
                                ,AtyRecipeMethod.class);
                        Recipe recipe =  (Recipe)adapterView.getItemAtPosition(i);
                        AtyRecipeMethod.recipe = recipe;
                        Aty_recipe_list.this.startActivity(intent);
                    }
                });

            }
        }, new GetRecipe.FailCallback() {
            @Override
            public void fail() {
                pd.dismiss();
                Toast.makeText(Aty_recipe_list.this,"失败了",Toast.LENGTH_SHORT).show();

            }
        });
    }
}
