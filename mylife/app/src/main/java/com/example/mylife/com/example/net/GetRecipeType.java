package com.example.mylife.com.example.net;

import com.example.mylife.com.example.config.Config;
import com.example.mylife.com.example.entity.Recipe;
import com.example.mylife.com.example.entity.RecipeType;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by 彭旎 on 2017/7/27.
 */

public class GetRecipeType {
    public GetRecipeType(String url_recipeTyle, final SuccessCallback successCallback, final GetRecipeType.FailCallback failCallback) {
        new NetConnection(url_recipeTyle, HttpMethod.POST, new NetConnection.SuccessCallback() {
            @Override
            public void onSuccess(String result) {
                try {
                    //返回的所有菜谱类型
                    List<RecipeType> list = new ArrayList<RecipeType>();
                    JSONObject jsonObject = new JSONObject(result);
                    if (jsonObject.getString("resultcode").equals("200")){
                        JSONArray jsonArray_result = jsonObject.getJSONArray("result");
                        for (int i = 0 ; i < jsonArray_result.length(); i ++)
                        {
                            RecipeType recipe_data = null;
                             recipe_data = new Gson().fromJson(jsonArray_result.getJSONObject(i).toString(),RecipeType.class);
                            list.add(recipe_data);
                        }

                        if(successCallback != null)
                        {
                            successCallback.Success(list);
                        }
                    }
                }catch (Exception e)
                {
                    e.printStackTrace();
                    if(failCallback != null)
                    {
                        failCallback.Fail();
                    }
                }
            }
        }, new NetConnection.FailCallback() {
            @Override
            public void onFail() {

            }
    }, Config.KEY_KEY,Config.APPKEY);
    }

    public static interface SuccessCallback {
        void Success(List<RecipeType> result_Type);
    }

    public static interface FailCallback
    {
        void Fail();
    }
}
