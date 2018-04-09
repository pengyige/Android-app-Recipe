package com.example.mylife.com.example.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 彭旎 on 2017/7/25.
 */


public class Recipe implements Parcelable{

    @SerializedName("id")
    public String id;

    @SerializedName("title")
    public String title;

    @SerializedName("tags")
    public String tags;

    @SerializedName("imtro")
    public String imtro;

    @SerializedName("ingredients")
    public String ingredients;

    @SerializedName("burden")
    public String burden;

    @SerializedName("albums")
    public List<String> albms;

    @SerializedName("steps")
    public List<Steps> list_steps;

    protected Recipe(Parcel in) {
        title = in.readString();
        tags = in.readString();
        imtro = in.readString();
        ingredients = in.readString();
        burden = in.readString();
        albms = in.createStringArrayList();
    }


    public static final Creator<Recipe> CREATOR = new Creator<Recipe>() {
        @Override
        public Recipe createFromParcel(Parcel in) {
            return new Recipe(in);

        }

        @Override
        public Recipe[] newArray(int size) {
            return new Recipe[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(tags);
        parcel.writeString(imtro);
        parcel.writeString(ingredients);
        parcel.writeString(burden);
        parcel.writeList(albms);
    }
   public class Steps
    {
        @SerializedName("img")
        public String img;

        @SerializedName("step")
        public String step;


    }

}


