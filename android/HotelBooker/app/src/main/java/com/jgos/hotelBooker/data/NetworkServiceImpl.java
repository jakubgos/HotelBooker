package com.jgos.hotelBooker.data;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.jgos.hotelBooker.data.entity.LoginData;
import com.jgos.hotelBooker.data.interfaces.NetworkService;
import com.jgos.hotelBooker.data.serverEntity.endpoint.HotelOffer;
import com.jgos.hotelBooker.data.serverEntity.endpoint.RateRequest;
import com.jgos.hotelBooker.data.serverEntity.endpoint.RegisterRequest;
import com.jgos.hotelBooker.data.serverEntity.endpoint.RegisterResult;
import com.jgos.hotelBooker.data.serverEntity.endpoint.ReservationRequest;
import com.jgos.hotelBooker.data.serverEntity.endpoint.ReservationResponse;
import com.jgos.hotelBooker.data.serverEntity.endpoint.SearchRequest;
import com.jgos.hotelBooker.data.serverEntity.endpoint.UserReservationResponse;
import com.jgos.hotelBooker.data.serverEntity.hotel.data.City;
import com.jgos.hotelBooker.data.serverEntity.hotel.data.ResultStatus;
import com.jgos.hotelBooker.filter.interfaces.LoginServiceCityListResult;
import com.jgos.hotelBooker.filter.interfaces.SearchRequestResult;
import com.jgos.hotelBooker.hotelList.interfaces.ReservationRequestResult;
import com.jgos.hotelBooker.login.entity.LoginReqParam;
import com.jgos.hotelBooker.login.interfaces.LoginServiceLoginResult;
import com.jgos.hotelBooker.login.interfaces.LoginServiceRegisterResult;
import com.jgos.hotelBooker.login.interfaces.getPictureResult;
import com.jgos.hotelBooker.reservation.interfaces.RateRequestResult;
import com.jgos.hotelBooker.reservation.interfaces.UserReservationResult;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
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
            "192.168.0.3";
            //"80.211.194.227";
    private static final int SERVER_PORT =
            8080;
    private static final String SEARCH_PATH =
            "api/searchOffer";
    private static final String MAKE_RESERVATION_PATH =
            "api/reservation";
    private static final String REGISTER_PATH =
            "api/register";
    private static final String GET_USER_RESERVATION_PATH =
            "api/getUserReservation";
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
    private static final String RATE_PATH =
            "api/rate";
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

    @Override
    public void reservationRequest(final ReservationRequest reservationRequest, final LoginData loginData, final ReservationRequestResult reservationRequestResult) {
        final Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                Log.d("MyApp_Service", "reservationRequest invoked");

                OkHttpClient okHttpClient = new OkHttpClient();
                String json = null;
                try {
                    json = objectMapper.writeValueAsString(reservationRequest);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                    reservationRequestResult.reservationRequestFailure("Failed to create Json Object");
                    return;
                }

                HttpUrl url = new HttpUrl.Builder()
                        .scheme("http")
                        .host(SERVER_ADDRESS)
                        .port(8080)
                        .addPathSegments(MAKE_RESERVATION_PATH)
                        .build();

                Request request = new Request.Builder()
                        .url(url)
                        .addHeader("Authorization", "Bearer " + loginData.getAccess_token())
                        .post(RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json))
                        .build();

                Log.d("MyApp_Service", "NetworkServiceImpl reservationRequest request " + request.toString() + "JSON: " + json);

                Response response = null;
                try {
                    response = okHttpClient
                            .newCall(request)
                            .execute();

                    String responseJson = response.body().string();
                    Log.d("MyApp_Service", "NetworkServiceImpl searchRequest response string:" + responseJson);
                    if (response.code() == 404) {
                        reservationRequestResult.reservationRequestFailure("404: " + responseJson);
                        return;

                    }

                    ReservationResponse reservationResponse = objectMapper.readValue(responseJson, TypeFactory.defaultInstance().constructType(ReservationResponse.class));

                    //Log.d("MyApp_Service", "NetworkServiceImpl getParkingList list " + list.toString());
                    Log.d("MyApp_Service", "NetworkServiceImpl reservationRequest result " + reservationResponse.toString());

                    switch (reservationResponse.getStatus()) {
                        case NO_DATA:
                            reservationRequestResult.reservationRequestFailure(NO_DATA_ERROR);
                            break;
                        case RESERVATION_NOT_POSSIBLE:
                            reservationRequestResult.reservationRequestReject(reservationResponse);
                            break;
                        case OK:
                            reservationRequestResult.reservationRequestResult(reservationResponse);
                            break;
                        default:
                            reservationRequestResult.reservationRequestFailure(UNKNOWN_ERROR + reservationResponse.getStatus().getValue());

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    reservationRequestResult.reservationRequestFailure(UNKNOWN_ERROR + e.getMessage());

                }
            }
        });
        thread.start();
    }

    @Override
    public void downloadUserReservation(final LoginData loginData, final UserReservationResult userReservationResult) {
        final Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                Log.d("MyApp_Service", "downloadUserReservation invoked");

                OkHttpClient okHttpClient = new OkHttpClient();
                String json = null;

                HttpUrl url = new HttpUrl.Builder()
                        .scheme("http")
                        .host(SERVER_ADDRESS)
                        .port(8080)
                        .addPathSegments(GET_USER_RESERVATION_PATH)
                        .build();

                Request request = new Request.Builder()
                        .url(url)
                        .addHeader("Authorization", "Bearer " + loginData.getAccess_token())
                        //.post(RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json))
                        .build();

                Log.d("MyApp_Service", "NetworkServiceImpl downloadUserReservation request " + request.toString() + "JSON: " + json);

                Response response = null;
                try {
                    response = okHttpClient
                            .newCall(request)
                            .execute();

                    String responseJson = response.body().string();
                    Log.d("MyApp_Service", "NetworkServiceImpl downloadUserReservation response string:" + responseJson);
                    if (response.code() == 404) {
                        userReservationResult.reservationRequestFailure("404: " + responseJson);
                        return;

                    }

                    UserReservationResponse userReservationResponse = objectMapper.readValue(responseJson, TypeFactory.defaultInstance().constructType(UserReservationResponse.class));

                    //Log.d("MyApp_Service", "NetworkServiceImpl getParkingList list " + list.toString());
                    Log.d("MyApp_Service", "NetworkServiceImpl downloadUserReservation result " + userReservationResponse.toString());

                    switch (userReservationResponse.getStatus()) {
                        case NO_DATA:
                            userReservationResult.userReservationNotAvailable();
                            break;
                        case OK:
                            userReservationResult.userReservationResult(userReservationResponse);
                            break;
                        default:
                            userReservationResult.reservationRequestFailure(UNKNOWN_ERROR + userReservationResponse.getStatus().getValue());

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    userReservationResult.reservationRequestFailure(UNKNOWN_ERROR + e.getMessage());

                }
            }
        });
        thread.start();

    }

    @Override
    public void register(final RegisterRequest registerRequest, final LoginServiceRegisterResult loginServiceRegisterResult) {
        final Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                Log.d("MyApp_Service", "register invoked");

                OkHttpClient okHttpClient = new OkHttpClient();
                String json = null;
                try {
                    json = objectMapper.writeValueAsString(registerRequest);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                    loginServiceRegisterResult.registerResultFailed("Failed to create Json Object");
                    return;
                }

                HttpUrl url = new HttpUrl.Builder()
                        .scheme("http")
                        .host(SERVER_ADDRESS)
                        .port(8080)
                        .addPathSegments(REGISTER_PATH)
                        .build();

                Request request = new Request.Builder()
                        .url(url)
                        .post(RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json))
                        .build();

                Log.d("MyApp_Service", "NetworkServiceImpl registerRequest request " + request.toString() + "JSON: " + json);

                Response response = null;
                try {
                    response = okHttpClient
                            .newCall(request)
                            .execute();

                    String responseJson = response.body().string();
                    //Log.d("MyApp_Service", "NetworkServiceImpl searchRequest result " + responseJson);

                    RegisterResult registerResult = objectMapper.readValue(responseJson, TypeFactory.defaultInstance().constructType(RegisterResult.class));

                    //Log.d("MyApp_Service", "NetworkServiceImpl getParkingList list " + list.toString());
                    Log.d("MyApp_Service", "NetworkServiceImpl register result " + registerResult.ordinal());

                    if (registerResult == RegisterResult.OK) {
                        loginServiceRegisterResult.registerResultOk();
                    } else if (registerResult == RegisterResult.USEREXIST) {
                        loginServiceRegisterResult.registerResultFailed("Login zarezerwowany");
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                    loginServiceRegisterResult.registerResultFailed("Exception happen...");
                }
            }
        });
        thread.start();
    }

    @Override
    public void rateRequest(final LoginData loginData, final RateRequest rateRequest, final RateRequestResult rateRequestResult) {
        final Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                Log.d("MyApp_Service", "rateRequest invoked");

                OkHttpClient okHttpClient = new OkHttpClient();
                String json = null;
                try {
                    json = objectMapper.writeValueAsString(rateRequest);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                    rateRequestResult.rateResultNok("Failed to create Json Object");
                    return;
                }

                HttpUrl url = new HttpUrl.Builder()
                        .scheme("http")
                        .host(SERVER_ADDRESS)
                        .port(8080)
                        .addPathSegments(RATE_PATH)
                        .build();

                Request request = new Request.Builder()
                        .url(url)
                        .addHeader("Authorization", "Bearer " + loginData.getAccess_token())
                        .post(RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json))
                        .build();



                Log.d("MyApp_Service", "NetworkServiceImpl rateRequest request " + request.toString() + "JSON: " + json);

                Response response = null;
                try {
                    response = okHttpClient
                            .newCall(request)
                            .execute();

                    String responseJson = response.body().string();
                    //Log.d("MyApp_Service", "NetworkServiceImpl searchRequest result " + responseJson);

                    ResultStatus registerResult = objectMapper.readValue(responseJson, TypeFactory.defaultInstance().constructType(ResultStatus.class));

                    //Log.d("MyApp_Service", "NetworkServiceImpl getParkingList list " + list.toString());
                    Log.d("MyApp_Service", "NetworkServiceImpl register result " + registerResult.ordinal());

                    if (registerResult == ResultStatus.OK) {
                        rateRequestResult.rateResultOk();
                    } else if (registerResult == ResultStatus.RATE_ERROR) {
                        rateRequestResult.rateResultNok("Failed to rate");
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                    rateRequestResult.rateResultNok("Exception happen...");
                }
            }
        });
        thread.start();
    }

    @Override
    public void getPicture(final String picturePath, final getPictureResult getPictureResult) {
        final Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Log.e("MyApp_Service", "DOWNLOAD STARTING");
                HttpURLConnection urlConnection = null;
                try {
                    String fullUrl = picturePath.replace("\\", "/");

                    URL uri = new URL("http://" + SERVER_ADDRESS + ":8080/" + fullUrl);
                    urlConnection = (HttpURLConnection) uri.openConnection();

                    InputStream inputStream = urlConnection.getInputStream();
                    if (inputStream != null) {
                        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                        Log.e("ImageDownloaderTask", "DOWNLOAD finished!");

                        getPictureResult.getPictureResultOk(bitmap);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    urlConnection.disconnect();
                    getPictureResult.getPictureResultNOk();
                    Log.w("ImageDownloader", "Error downloading image from " + picturePath);
                } finally {
                    if (urlConnection != null) {
                        urlConnection.disconnect();

                    }
                }
            }
        });
        thread.start();
        }
}
