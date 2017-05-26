package com.jgos.hotelBooker.data;

import android.util.Log;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.jgos.hotelBooker.data.entity.City;
import com.jgos.hotelBooker.data.entity.Coordinates;
import com.jgos.hotelBooker.data.entity.HotelOffer;
import com.jgos.hotelBooker.data.entity.LoginData;
import com.jgos.hotelBooker.data.entity.Parking;
import com.jgos.hotelBooker.data.entity.SearchRequest;
import com.jgos.hotelBooker.filter.interfaces.LoginServiceCityListResult;
import com.jgos.hotelBooker.filter.interfaces.SearchRequestResult;
import com.jgos.hotelBooker.login.entity.LoginReqParam;
import com.jgos.hotelBooker.data.interfaces.NetworkService;
import com.jgos.hotelBooker.login.interfaces.LoginServiceLoginResult;
import com.jgos.hotelBooker.map.interfaces.ParkingListCallback;
import com.jgos.hotelBooker.parkingList.interfaces.FavoriteParkingCallback;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Credentials;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Bos on 2017-03-04.
 */

public class NetworkServiceImpl implements NetworkService {

    private static final String SERVER_ADDRESS =
            "192.168.0.4";
    private static final int SERVER_PORT =
            8080;
    private static final String PARKING_PATH =
            "api/parkings";

    private static final String SEARCH_PATH =
            "api/searchOffer";

    private static final String CITY_LIST_PATH =
            "api/getCityList";
    private static final String GRANT_TYPE =
            "password";
    private static final String CLIENT_ID =
            "client";
    private static final String CLIENT_SECRET =
            "secret";


    private static final String SERVER_LOGIN_PATH =
            "oauth/token";
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void login(final LoginReqParam loginReqParam, final LoginServiceLoginResult callBack) {

        final Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d("MyApp_Service", "view attemptLogin invoked");

                OkHttpClient okHttpClient = new OkHttpClient();


                HttpUrl url = new HttpUrl.Builder()
                        .scheme("http")
                        .host(SERVER_ADDRESS)
                        .port(SERVER_PORT)
                        .addPathSegments(SERVER_LOGIN_PATH)
                        .addQueryParameter("grant_type", GRANT_TYPE)
                        .addQueryParameter("client_id", CLIENT_ID)
                        .addQueryParameter("client_secret", CLIENT_SECRET)
                        .addQueryParameter("username", loginReqParam.getLogin())
                        .addQueryParameter("password", loginReqParam.getPassword())
                        .build();

                Request request = new Request.Builder()
                        .url(url)
                        .addHeader("Authorization", Credentials.basic("client", "secret"))
                        .post(RequestBody.create(MediaType.parse("application/json; charset=utf-8"), ""))
                        .build();
                Log.d("MyApp_Service", "view attemptLogin request " + request.toString());

                try {
                    Response response = okHttpClient
                            .newCall(request)
                            .execute();


                    String responseJson = response.body().string();

                    Log.d("MyApp_Service", "view attemptLogin result " + responseJson);
                    LoginData loginData = objectMapper.readValue(responseJson, LoginData.class);
                    Log.d("MyApp_Service", "view attemptLogin result loginData object" + loginData.toString());

                    if (response.isSuccessful()) {
                        callBack.loginSuccess(loginData);
                    } else {
                        callBack.loginFailed(loginData);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    callBack.loginFailed(new LoginData("Unexpected Error"));
                    return;
                }
            }
        });
        thread.start();
    }

    @Override
    public void getFavoriteParking(FavoriteParkingCallback callback) {
        //todo change it!
        ArrayList<Parking> result = new ArrayList<Parking>();
        result.add(new Parking((long) 1, 100, 10, "parking 1", new Coordinates(20, 20), ""));
        result.add(new Parking((long) 2, 100, 10, "parking 2", new Coordinates(20, 20), ""));
        result.add(new Parking((long) 3, 100, 10, "parking 3", new Coordinates(20, 20), ""));
        result.add(new Parking((long) 4, 100, 10, "parking 4", new Coordinates(20, 20), ""));
        result.add(new Parking((long) 5, 100, 10, "parking 5", new Coordinates(20, 20), ""));

        callback.onFavoriteParkingResult(result);

    }

    @Override
    public void getParkingList(final LoginData loginData, final Coordinates coordinates, final ParkingListCallback parkingListCallback) {
        final Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d("MyApp_Service", "view attemptLogin invoked");

                OkHttpClient okHttpClient = new OkHttpClient();
                String json = null;
                try {
                    json = objectMapper.writeValueAsString(coordinates);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                    parkingListCallback.parkingListFailed("Failed to create Json Object");
                }

                HttpUrl url = new HttpUrl.Builder()
                        .scheme("http")
                        .host(SERVER_ADDRESS)
                        .port(8080)
                        .addPathSegments(PARKING_PATH)
                        .addQueryParameter("radius", "100")
                        .addQueryParameter("latitude", "51.752565")
                        .addQueryParameter("longitude", "19.453313")
                        .build();

                Request request = new Request.Builder()
                        .url(url)
                        .addHeader("Authorization", "Bearer " + loginData.getAccess_token())
                        .build();

                Log.d("MyApp_Service", "NetworkServiceImpl getParkingList request " + request.toString() + "JSON: " + json);

                Response response = null;
                try {
                    response = okHttpClient
                            .newCall(request)
                            .execute();

                    String responseJson = response.body().string();
                    //Log.d("MyApp_Service", "NetworkServiceImpl getParkingList result " + responseJson);

                    List<Parking> list = objectMapper.readValue(responseJson, TypeFactory.defaultInstance().constructCollectionType(List.class,
                            Parking.class));

                    Log.d("MyApp_Service", "NetworkServiceImpl getParkingList list " + list.toString());

                    parkingListCallback.getParkingListResult(list);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();

    }

    @Override
    public void getCityList(final LoginData loginData, final LoginServiceCityListResult callBack) {
        final Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d("MyApp_Service", "networkimpl getCityList invoked");

                OkHttpClient okHttpClient = new OkHttpClient();

                HttpUrl url = new HttpUrl.Builder()
                        .scheme("http")
                        .host(SERVER_ADDRESS)
                        .port(8080)
                        .addPathSegments(CITY_LIST_PATH)
                        .build();

                Request request = new Request.Builder()
                        .url(url)
                        .addHeader("Authorization", "Bearer " + loginData.getAccess_token())
                        .build();

                Log.d("MyApp_Service", "NetworkServiceImpl getCityList request " + request.toString());

                Response response;
                try {
                    response = okHttpClient
                            .newCall(request)
                            .execute();

                    String responseJson = response.body().string();
                    Log.d("MyApp_Service", "NetworkServiceImpl getCityList response list " + responseJson);

                    List<City> list = objectMapper.readValue(responseJson, TypeFactory.defaultInstance().constructCollectionType(List.class,
                            City.class));

                    Log.d("MyApp_Service", "NetworkServiceImpl getCityList list " + list.toString());

                    callBack.getCityListResult(list);

                } catch (IOException e) {
                    callBack.failure("failed to get city list");
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

    @Override
    public void searchRequest(final SearchRequest searchRequest, final LoginData loginData, final SearchRequestResult searchRequestResult) {
        final Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d("MyApp_Service", "searchRequest invoked");

                OkHttpClient okHttpClient = new OkHttpClient();
                String json = null;
                try {
                    json = objectMapper.writeValueAsString(searchRequest);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                    searchRequestResult.failure("Failed to create Json Object");
                    return;
                }

                HttpUrl url = new HttpUrl.Builder()
                        .scheme("http")
                        .host(SERVER_ADDRESS)
                        .port(8080)
                        .addPathSegments(SEARCH_PATH)
                        .build();

                Request request = new Request.Builder()
                        .url(url)
                        .addHeader("Authorization", "Bearer " + loginData.getAccess_token())
                        .post(RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json))
                        .build();

                Log.d("MyApp_Service", "NetworkServiceImpl searchRequest request " + request.toString() + "JSON: " + json);

                Response response = null;
                try {
                    response = okHttpClient
                            .newCall(request)
                            .execute();

                    String responseJson = response.body().string();
                    Log.d("MyApp_Service", "NetworkServiceImpl searchRequest result " + responseJson);

                    //List<Parking> list = objectMapper.readValue(responseJson, TypeFactory.defaultInstance().constructCollectionType(List.class,
                    //        Parking.class));

                    //Log.d("MyApp_Service", "NetworkServiceImpl getParkingList list " + list.toString());

                    searchRequestResult.getSearchRequestResult(new ArrayList<HotelOffer>());

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }


    public void testMsg(LoginData s) {

        OkHttpClient okHttpClient = new OkHttpClient();
        String json = null;

        HttpUrl url = new HttpUrl.Builder()
                .scheme("http")
                .host(SERVER_ADDRESS)
                .port(8080)
                .addPathSegments("api/test")

                .build();

        Request request = new Request.Builder()
                .url(url)
                .addHeader("Authorization", "Bearer " + s.getAccess_token())
                .build();

        Log.d("MyApp_Service", "NetworkServiceImpl testMsg request " + request.toString() + "JSON: " + json);

        Response response = null;
        try {
            response = okHttpClient
                    .newCall(request)
                    .execute();

            String responseJson = response.body().string();
            Log.d("MyApp_Service", "NetworkServiceImpl testMsg result " + responseJson);


        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
