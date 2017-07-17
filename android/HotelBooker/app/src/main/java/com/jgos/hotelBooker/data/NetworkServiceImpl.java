package com.jgos.hotelBooker.data;

import android.util.Log;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.jgos.hotelBooker.data.entity.LoginData;
import com.jgos.hotelBooker.data.interfaces.NetworkService;
import com.jgos.hotelBooker.data.serverEntity.endpoint.HotelOffer;
import com.jgos.hotelBooker.data.serverEntity.endpoint.SearchRequest;
import com.jgos.hotelBooker.data.serverEntity.hotel.data.City;
import com.jgos.hotelBooker.filter.interfaces.LoginServiceCityListResult;
import com.jgos.hotelBooker.filter.interfaces.SearchRequestResult;
import com.jgos.hotelBooker.login.entity.LoginReqParam;
import com.jgos.hotelBooker.login.interfaces.LoginServiceLoginResult;

import java.io.IOException;
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

    public static final String UNKNOWN_ERROR = "Błąd:";
    private static final String SERVER_ADDRESS =
            "192.168.0.2";
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
    private static final String NO_DATA_ERROR = "Nie znaleźiono żadnych wyników";
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
                    loginData.setLogin(loginReqParam.getLogin());
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
                    searchRequestResult.searchRequestFailure("Failed to create Json Object");
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
                    //Log.d("MyApp_Service", "NetworkServiceImpl searchRequest result " + responseJson);

                    HotelOffer hotelOffer = objectMapper.readValue(responseJson, TypeFactory.defaultInstance().constructType(HotelOffer.class));

                    //Log.d("MyApp_Service", "NetworkServiceImpl getParkingList list " + list.toString());
                    Log.d("MyApp_Service", "NetworkServiceImpl searchRequest result " + hotelOffer.toString());

                    switch (hotelOffer.getStatus()) {
                        case NO_DATA:
                            searchRequestResult.searchRequestFailure(NO_DATA_ERROR);
                            break;
                        case OK:
                            searchRequestResult.getSearchRequestResult(hotelOffer);
                            break;
                        default:
                            searchRequestResult.searchRequestFailure(UNKNOWN_ERROR + hotelOffer.getStatus().getValue());

                    }


                } catch (IOException e) {
                    e.printStackTrace();
                    searchRequestResult.searchRequestFailure(UNKNOWN_ERROR + e.getMessage());

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
