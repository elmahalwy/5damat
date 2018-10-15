package com.I3gaz.mohamedelmahalwy.a5damat.Network;

import com.I3gaz.mohamedelmahalwy.a5damat.Models.AddOrDeleteItemsToFavourites.AddOrDeleteItemToFavourit;
import com.I3gaz.mohamedelmahalwy.a5damat.Models.AddService;
import com.I3gaz.mohamedelmahalwy.a5damat.Models.AddServiceJson.AddServiceJson;
import com.I3gaz.mohamedelmahalwy.a5damat.Models.AllServices.AllServices;
import com.I3gaz.mohamedelmahalwy.a5damat.Models.LoginData.User;
import com.I3gaz.mohamedelmahalwy.a5damat.Models.MainCategories.MainCategories;
import com.I3gaz.mohamedelmahalwy.a5damat.Models.MyFavourites.Favourites;
import com.I3gaz.mohamedelmahalwy.a5damat.Models.MyMessages.MyMessages;
import com.I3gaz.mohamedelmahalwy.a5damat.Models.MyServices.MyServices;
import com.I3gaz.mohamedelmahalwy.a5damat.Models.RequestsModel.Requests_Tab_Model;
import com.I3gaz.mohamedelmahalwy.a5damat.Models.ResetPassword.ResetPassword;
import com.I3gaz.mohamedelmahalwy.a5damat.Models.ServiceDetails.ServiceDetails;
import com.I3gaz.mohamedelmahalwy.a5damat.Models.SpinnerModel.SpinnerModel;
import com.I3gaz.mohamedelmahalwy.a5damat.Models.SpinnerModel.SpinnerssModelss;
import com.I3gaz.mohamedelmahalwy.a5damat.Models.SubCategories.SubCategories;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ServiceApi {
    @FormUrlEncoded
    @POST(Urls.SignUp)
    Call<User> Sign_up_response_call(@Field("username") String username,
                                     @Field("mobile") String mobile,
                                     @Field("email") String email,
                                     @Field("device_token") String device_token,
                                     @Field("serial_num") String serial_num,
                                     @Field("password") String password);

    @FormUrlEncoded
    @POST(Urls.SignIn)
    Call<User> Sign_in_response_call(@Field("email") String email,
                                     @Field("password") String password,
                                     @Field("device_token") String device_token,
                                     @Field("serial_num") String serial_num);

    @FormUrlEncoded
    @POST(Urls.ResetPassword)
    Call<ResetPassword> RESET_PASSWORD_CALL(@Field("input") String input);

    @GET(Urls.MainCategroies)
    Call<MainCategories> Get_main_categories();

    @GET(Urls.SubCategroies)
    Call<SubCategories> get_sub_categories(@Path("category_id") String category_id);

    @GET(Urls.SpinnerUrl)
    Call<SpinnerModel> fill_spinner(@Path("category_id") String spinner_url);

    @GET(Urls.Spinner_sub_categoryUrl)
    Call<SpinnerModel> fill_spinner_sub_category(@Path("category_id") String category_id);

    @GET(Urls.AllServices)
    Call<AllServices> get_all_service();

    @GET(Urls.ServiceCategory)
    Call<AllServices> get_service_category(@Path("category_id") String category_id);

    @GET(Urls.MyFavourites)
    Call<Favourites> get_my_favourites(@Field("user_id") String user_id);

    @GET(Urls.Spinner_add_service__Url)
    Call<SpinnerssModelss> fill_add_service_spinner(@Path("category_id") String category_id);

    @FormUrlEncoded
    @POST(Urls.AddOrDeleteItemToFavourites)
    Call<AddOrDeleteItemToFavourit> add_or_item_to_favourites(@Field("user_id") String user_id,
                                                              @Field("service_id") String service_id);

    @GET(Urls.MyServices)
    Call<MyServices> get_my_services(@Path("user_id") String user_id);

    @FormUrlEncoded
    @POST(Urls.MyMessges)
    Call<MyMessages> get_my_messages(@Field("user_id") String user_id);

    @FormUrlEncoded
    @POST(Urls.ServiceDetails)
    Call<ServiceDetails> get_service_details(@Field("service_id") String service_id);

    @Headers("Accept: application/json")
    @POST("service")
    Call<AddService> add_service(@Body AddServiceJson main);

    @GET(Urls.incoming_orders)
    Call<Requests_Tab_Model> incoming_orders(@Path("user_id") String user_id, @Path("status") String status);

    @GET(Urls.purchases)
    Call<Requests_Tab_Model> purchases(@Path("user_id") String user_id, @Path("status") String status);

}
