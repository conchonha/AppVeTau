package com.cj.trainticks.cores.services;

import com.cj.trainticks.utils.Constain;

public class APIServices {
    private static String baseurl= Constain.baseUrl;

    public static DataService getService(){
        return APIRetrofitClient.getClient(baseurl).create(DataService.class);
    }
}
