package com.example.mylife.com.example.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by 彭旎 on 2017/7/27.
 */

public class RecipeType {
    @SerializedName("name")
    public String name;

    @SerializedName("list")
    public List<RecipeList> list;

    public class RecipeList
    {
        @SerializedName("id")
        public String id;

        @SerializedName("name")
        public String name;

    }
}
