package com.i3gaz.mohamedelmahalwy.a5damat.Network;

import com.i3gaz.mohamedelmahalwy.a5damat.Models.LoginData.User;
import com.i3gaz.mohamedelmahalwy.a5damat.Models.ResetPassword.ResetPassword;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.POST;

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
}
