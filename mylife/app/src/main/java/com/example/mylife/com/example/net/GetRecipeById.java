package com.example.mylife.com.example.net;

import com.example.mylife.com.example.config.Config;
import com.example.mylife.com.example.entity.Recipe;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 彭旎 on 2017/7/27.
 */

public class GetRecipeById {

    public GetRecipeById(String key, String cid, final GetRecipe.SuccessCallback successCallback, final GetRecipe.FailCallback failCallback)
    {
        new NetConnection(Config.URL_RECIPE_ID,HttpMethod.POST,new NetConnection.SuccessCallback(){
            @Override
            public void onSuccess(String result) {
             /*开始解析数据*/
                try{
                    List<Recipe> list_recipes = new ArrayList<>();
                    JSONObject jsonObject = new JSONObject(result);
                    JSONObject resultObject = jsonObject.getJSONObject("result");
                    JSONArray jsonArray = resultObject.getJSONArray("data");
                    for(int i = 0 ;i < jsonArray.length(); i ++)
                    {
                        Recipe recipe = null;
                        String recip_data = null;
                        recip_data = jsonArray.getJSONObject(i).toString();
                        recipe = new Gson().fromJson(recip_data,Recipe.class);
                        list_recipes.add(recipe);
                    }
                    if(successCallback != null)
                    {
                        successCallback.success(list_recipes);
                    }

                }catch (Exception e)
                {
                    e.printStackTrace();
                    if(failCallback != null)
                    {
                        failCallback.fail();
                    }
                }
            }
        },new NetConnection.FailCallback(){
            @Override
            public void onFail() {
                if(failCallback != null)
                {
                    failCallback.fail();
                }
            }
        },Config.KEY_KEY,key,Config.KEY_ID,cid);
    }


    public static interface  SuccessCallback
    {
        void success(List<Recipe> success);
    }

    public static interface  FailCallback
    {
        void fail();
    }

}
