package com.mdk78.testtask.model;



import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Quote {

    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("createdBy")
    @Expose
    public Integer createdBy;
    @SerializedName("text")
    @Expose
    public String text;
    @SerializedName("tagList")
    @Expose
    public List<String> tagList = null;

}