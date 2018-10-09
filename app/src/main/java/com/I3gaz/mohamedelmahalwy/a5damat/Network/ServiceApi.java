package com.I3gaz.mohamedelmahalwy.a5damat.Network;

import com.I3gaz.mohamedelmahalwy.a5damat.Adapters.CategoriesAdapter;
import com.I3gaz.mohamedelmahalwy.a5damat.Models.AllServices.AllServices;
import com.I3gaz.mohamedelmahalwy.a5damat.Models.LoginData.User;
import com.I3gaz.mohamedelmahalwy.a5damat.Models.MainCategories.MainCategories;
import com.I3gaz.mohamedelmahalwy.a5damat.Models.MyFavourites.Favourites;
import com.I3gaz.mohamedelmahalwy.a5damat.Models.ResetPassword.ResetPassword;
import com.I3gaz.mohamedelmahalwy.a5damat.Models.SpinnerModel.SpinnerModel;
import com.I3gaz.mohamedelmahalwy.a5damat.Models.SubCategories.SubCategories;

import java.net.URI;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ServiceApi {
    @POST(Urls.SignUp)
    Call<User> Sign_up_response_call(@Field("username") String username,
                                     @Field("mobile") String mobile,
                                     @Field("email") String email,
                                     @Field("device_token") String device_token,
                                     @Field("serial_num") String serial_num,
                                     @Field("password") String password);

    @POST(Urls.SignIn)
    Call<User> Sign_in_response_call(@Field("email") String email,
                                     @Field("password") String password,
                                     @Field("device_token") String device_token,
                                     @Field("serial_num") String serial_num);

    @POST(Urls.ResetPassword)
    Call<ResetPassword> RESET_PASSWORD_CALL(@Field("input") String input);

    @GET(Urls.MainCategroies)
    Call<MainCategories> Get_main_categories();

    @GET(Urls.SubCategroies)
    Call<SubCategories> get_sub_categories(@Path("category_id") String category_id);

    @GET(Urls.SpinnerUrl)
    Call<SpinnerModel> fill_spinner(@Path("spinner_url") String spinner_url);

    @GET(Urls.AllServices)
    Call<AllServices> get_all_service();

    @GET(Urls.ServiceCategory)
    Call<AllServices> get_service_category(@Path("category_id") String category_id);

    @GET(Urls.MyFavourites)
    Call<Favourites> get_my_favourites(@Field("user_id") String user_id);
}
