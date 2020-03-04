package com.mdk78.testtask;

import android.app.Application;

import com.mdk78.testtask.di.AppComponent;


import com.mdk78.testtask.di.DaggerAppComponent;
import com.mdk78.testtask.di.NetworkServiceModule;


public class App extends Application {
    private static AppComponent appComponent;


    @Override
    public void onCreate() {
        super.onCreate();
       appComponent = DaggerAppComponent.create();

    }

    public static AppComponent getComponent() {
        return appComponent;
    }

}
