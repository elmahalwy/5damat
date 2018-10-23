package com.I3gaz.mohamedelmahalwy.a5damat.Network;

import com.I3gaz.mohamedelmahalwy.a5damat.Models.AddOrDeleteItemsToFavourites.AddOrDeleteItemToFavourit;
import com.I3gaz.mohamedelmahalwy.a5damat.Models.AddService;
import com.I3gaz.mohamedelmahalwy.a5damat.Models.AddServiceJson.AddServiceJson;
import com.I3gaz.mohamedelmahalwy.a5damat.Models.AllServices.AllServices;
import com.I3gaz.mohamedelmahalwy.a5damat.Models.Assistance.Assisatnce;
import com.I3gaz.mohamedelmahalwy.a5damat.Models.EditProfile.EditProfile;
import com.I3gaz.mohamedelmahalwy.a5damat.Models.LoginData.User;
import com.I3gaz.mohamedelmahalwy.a5damat.Models.MainCategories.MainCategories;
import com.I3gaz.mohamedelmahalwy.a5damat.Models.MyBalance.Balance;
import com.I3gaz.mohamedelmahalwy.a5damat.Models.MyFavourites.Favourites;
import com.I3gaz.mohamedelmahalwy.a5damat.Models.MyMessages.MyMessages;
import com.I3gaz.mohamedelmahalwy.a5damat.Models.MyServices.MyServices;
import com.I3gaz.mohamedelmahalwy.a5damat.Models.OrderService.OrderService;
import com.I3gaz.mohamedelmahalwy.a5damat.Models.ReportMessage.ReportMessage;
import com.I3gaz.mohamedelmahalwy.a5damat.Models.RequestsModel.RequestDetailsModel;
import com.I3gaz.mohamedelmahalwy.a5damat.Models.RequestsModel.RequestsChangeStatusModel;
import com.I3gaz.mohamedelmahalwy.a5damat.Models.RequestsModel.Requests_Tab_Model;
import com.I3gaz.mohamedelmahalwy.a5damat.Models.ResetPassword.ResetPassword;
import com.I3gaz.mohamedelmahalwy.a5damat.Models.SendMessage.SendMessage;
import com.I3gaz.mohamedelmahalwy.a5damat.Models.ServiceDetails.ServiceDetails;
import com.I3gaz.mohamedelmahalwy.a5damat.Models.SingleMessage.SingleMessage;
import com.I3gaz.mohamedelmahalwy.a5damat.Models.SpinnerModel.SpinnerModel;
import com.I3gaz.mohamedelmahalwy.a5damat.Models.SpinnerModel.SpinnerssModelss;
import com.I3gaz.mohamedelmahalwy.a5damat.Models.SubCategories.SubCategories;
import com.I3gaz.mohamedelmahalwy.a5damat.Models.UserNotifications.Notifications;
import com.I3gaz.mohamedelmahalwy.a5damat.Models.UserProfile.UserProfile;

import java.util.ArrayList;
import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

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

    @FormUrlEncoded
    @POST(Urls.MyFavourites)
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
    @POST(Urls.single_message)
    Call<SingleMessage> get_single_message(@Field("message_id") String message_id);

    @FormUrlEncoded
    @POST(Urls.report_message)
    Call<ReportMessage> report_message(@Field("reporter_id") String reporter_id,
                                       @Field("reported_id") String reported_id,
                                       @Field("message_id") String message_id);

    @FormUrlEncoded
    @POST(Urls.send_message)
    Call<SendMessage> send_message(@Field("sender_id") String sender_id,
                                   @Field("service_id") String service_id,
                                   @Field("msg") String msg);

    @FormUrlEncoded
    @POST(Urls.ServiceDetails)
    Call<ServiceDetails> get_service_details(@Field("service_id") String service_id,
                                             @Field("user_id") String user_id);

    @Headers("Accept: application/json")
    @POST("service")
    Call<AddService> add_service(@Body AddServiceJson main);

    @GET(Urls.incoming_orders)
    Call<Requests_Tab_Model> incoming_orders(@Path("user_id") String user_id, @Path("status") String status);

    @GET(Urls.purchases)
    Call<Requests_Tab_Model> purchases(@Path("user_id") String user_id, @Path("status") String status);

    @FormUrlEncoded
    @POST(Urls.OrderService)
    Call<OrderService> order_service(@Field("user_id") String user_id,
                                     @Field("service_id") String service_id,
                                     @Field("total_salary") String total_salary,
                                     @Field("sub_services[]") ArrayList<String> developments);

    @GET(Urls.UserNotifications)
    Call<Notifications> user_notifications(@Path("user_id") String user_id);

    @FormUrlEncoded
    @POST(Urls.change_request_status)
    Call<RequestsChangeStatusModel> change_request_status(@Field("order_id") String order_id,
                                                          @Field("status") String status, @Field("refuse") String refuse);

    @GET(Urls.single_request)
    Call<RequestDetailsModel> single_request(@Path("order_id") String order_id);

    @FormUrlEncoded
    @POST(Urls.search)
    Call<AllServices> search(@Field("service") String search_key);

    @GET(Urls.about_us)
    Call<Assisatnce> assisatnce();

    @Multipart
    @POST(Urls.update_profile)
    Call<EditProfile> update_profile_with_image(@Query("username") String username,
                                                @Query("user_id") String user_id,
                                                @Query("mobile") String mobile,
                                                @Query("email") String email,
                                                @Query("country_code") String country_code,
                                                @Query("gender") String gender,
                                                @Query("old_password") String old_password,
                                                @Query("new_password") String new_password,
                                                @Query("c_new_password") String c_new_password,
                                                @Query("birthday") String birthday,
                                                @Part MultipartBody.Part image);

    @FormUrlEncoded
    @POST(Urls.update_profile)
    Call<EditProfile> update_profile_without_image(@Field("username") String username,
                                                   @Field("user_id") String user_id,
                                                   @Field("mobile") String mobile,
                                                   @Field("email") String email,
                                                   @Field("country_code") String country_code,
                                                   @Field("gender") String gender,
                                                   @Field("old_password") String old_password,
                                                   @Field("new_password") String new_password,
                                                   @Field("c_new_password") String c_new_password,
                                                   @Field("birthday") String birthday
    );

    @GET(Urls.user_profile)
    Call<UserProfile> user_profile(@Path("user_id") String user_id);

    @GET(Urls.balance)
    Call<Balance> my_balance(@Path("user_id") String user_id);

}

