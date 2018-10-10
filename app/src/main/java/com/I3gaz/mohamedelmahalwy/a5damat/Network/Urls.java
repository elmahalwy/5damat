package com.I3gaz.mohamedelmahalwy.a5damat.Network;


import com.I3gaz.mohamedelmahalwy.a5damat.Adapters.CategoriesAdapter;

public class Urls {
    public final static String BaseUrl = "http://5dmat.e3gaz.net/api/v1/";
    public final static String SignUp = "user";
    public final static String SignIn = "user/login";
    public final static String ResetPassword = "reset_password";
    public final static String MainCategroies = "category";
    public final static String SubCategroies = "category/{category_id}";
    public final static String SpinnerUrl = "{category_id}";
    public final static String AllServices = "all-services";
    public final static String ServiceCategory = "category/{category_id}";
    public final static String MyFavourites = "user_favs";
    public final static String Spinner_add_service__Url = "settings/{category_id}";
    public final static String Spinner_sub_categoryUrl = "category/{category_id}";
    public final static String AddOrDeleteItemToFavourites = "fav_service";
    public final static String MyServices = "my_services/{user_id}";
    public final static String MyMessges ="user_messages";
    public final static String ServiceDetails = "single-service";
    public final static String add_service = "service";

}
