package com.example.mylife.com.example.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mylife.R;
import com.example.mylife.com.example.config.Config;
import com.example.mylife.com.example.entity.Recipe;
import com.example.mylife.com.example.entity.RecipeType;
import com.example.mylife.com.example.net.GetRecipe;
import com.example.mylife.com.example.net.GetRecipeById;
import com.example.mylife.com.example.net.GetRecipeType;
import com.example.mylife.com.example.net.HttpMethod;
import com.example.mylife.com.example.net.NetConnection;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 彭旎 on 2017/7/25.
 */

public class AtyRecipe extends AppCompatActivity {
    private EditText et_recipeName;
    private Button btn_OK;

    private ListView lv_recipeType;
    private ListView lv_recipeName;

    static TextView tv_old = null;
    private  List<RecipeType.RecipeList> list_recipeList;//选择类型之后的菜名和id列表
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);
        init();
        btn_OK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(et_recipeName.getText())) {
                    return;
                }
                Intent intent = new Intent(AtyRecipe.this, Aty_recipe_list.class);
                intent.putExtra(Config.RECIPE_NAME, et_recipeName.getText().toString());
                startActivity(intent);
            }
        });
         /*加载菜谱类型*/
         new GetRecipeType(Config.URL_RECIPE_TYPE, new GetRecipeType.SuccessCallback() {
             @Override
             public void Success(List<RecipeType> result_Type) {

               AtyRecipeTypeAdapter arrayAdapter = new AtyRecipeTypeAdapter(AtyRecipe.this,result_Type);
                 lv_recipeType.setAdapter(arrayAdapter);

                 /*添加点击监听*/
                 lv_recipeType.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                     @Override
                     public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                         /*获取点击的菜谱类型*/
                         if(tv_old != null)
                         {
                             tv_old.setTextColor(Color.GRAY);
                         }

                        RecipeType recipeType =(RecipeType) adapterView.getItemAtPosition(i);
                         list_recipeList = recipeType.list;
                         TextView textView =(TextView)view.findViewById(R.id.tv_recipeTypeText);
                         tv_old = textView;

                         textView.setTextColor(Color.YELLOW);

                         /*为ID类型创建适配器*/
                         AtyRecipeTypeIdAdapter atyRecipeTypeIdAdapter = new AtyRecipeTypeIdAdapter(AtyRecipe.this,
                                 list_recipeList);
                       lv_recipeName.setAdapter(atyRecipeTypeIdAdapter);
                         lv_recipeName.setVisibility(View.VISIBLE);
                         handleIdTypeRecipe();
                     }
                 });
             }
         }, new GetRecipeType.FailCallback() {
             @Override
             public void Fail() {
             }
         });
    }




    public void init()
    {
        et_recipeName = (EditText) findViewById(R.id.et_recipeName);
        btn_OK = (Button) findViewById(R.id.btn_OK);
        lv_recipeType = (ListView) findViewById(R.id.lv_recipeType);
        lv_recipeName = (ListView) findViewById(R.id.lv_recipeName);
    }

    /*处理ID类型菜谱监听*/
    public void handleIdTypeRecipe()
    {
        lv_recipeName.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
              RecipeType.RecipeList recipeList =(RecipeType.RecipeList)  adapterView.getItemAtPosition(i);
                String id = recipeList.id;
                Intent intent = new Intent(AtyRecipe.this, Aty_recipe_list.class);
                intent.putExtra(Config.RECIPE_TYPE_ID, id);
                startActivity(intent);
            }
        });
    }
}
