package com.jahircelorio.spotmelody.Service.model;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import cafsoft.foundation.HTTPURLResponse;
import cafsoft.foundation.URLComponents;
import cafsoft.foundation.URLSession;

public class DreezerMusicService {
    private URLComponents components = null;

    public DreezerMusicService() {
        components = new URLComponents();
        components.setScheme("https");
        components.setHost("api.deezer.com");
        components.setPath("/genre");
    }

    public void getAllGenres(OnDataResponse delegate){
        URLSession.getShared().dataTask(components.getURL(),(data, response, error) -> {
            HTTPURLResponse  response1 = (HTTPURLResponse) response;
            Post post = null;
            int statusCode = -1;
            if (error == null && response1.getStatusCode() == 200){
                String text = data.toText();
                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.create();
                post = gson.fromJson(text,Post.class);
                post.toString();
                Log.d("OK","Exitoso");
                statusCode = response1.getStatusCode();
            }else{
                Log.d("MSG", "ERROR NO ES NULL");
            }

            if (delegate != null){
                delegate.onChange(error!=null,statusCode,post);
            }
        });
    }

    public interface OnDataResponse{
        public abstract void onChange(boolean isNetworkError, int statusCode, Post post);
    }
}
