package com.example.mylife;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.mylife.com.example.activity.AtyRecipe;
import com.example.mylife.com.example.config.Config;
import com.example.mylife.com.example.entity.Recipe;
import com.example.mylife.com.example.net.GetRecipe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private GridView gv_gridView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        initLayout();


    }

    public void init()
    {

        gv_gridView = (GridView) findViewById(R.id.gv_gridView);
    }

    /**
     * 打开菜谱Activity
     * */
    public void OpenRecipe()
    {
        Intent intent = new Intent(MainActivity.this, AtyRecipe.class);
        startActivity(intent);

    }

    public void initLayout()
    {
        /*添加GridView中的子项*/
        ArrayList<HashMap<String,Object>> menuList = new ArrayList<HashMap<String, Object>>();
        for(int i = 1; i < 5 ;i ++)
        {
            HashMap<String,Object> map = new HashMap<String,Object>();
            map.put("ItemImage",R.drawable.cai);
            map.put("ItemText","菜谱");
            menuList.add(map);

        }
    /*
    * 参数一 数据源
    * 参数二 子项布局
    * 参数三 map对应的key资源
    * 参数丝 资源对应的控件ID
    * */
        SimpleAdapter saItem = new SimpleAdapter(
                this,menuList,R.layout.item,new String[]{"ItemImage","ItemText"},
                new int[]{R.id.iv_imageView,R.id.tv_title}
        );

        //添加Item到网格
        gv_gridView.setAdapter(saItem);

        //添加点击事件
        gv_gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i)
                {
                    case 0:
                        OpenRecipe();
                        break;
                    case 1:
                        OpenRecipe();
                        break;
                    case 2:
                        OpenRecipe();
                        break;
                    case 3:
                        OpenRecipe();
                        break;
                    default:break;
                }
            }
        });

    }


}
