package com.mdk78.testtask.di;



import com.mdk78.testtask.network.NetworkService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class NetworkServiceModule {
    @Singleton
    @Provides
    public NetworkService providesNetworkService() {
        return new NetworkService();
    }
}
