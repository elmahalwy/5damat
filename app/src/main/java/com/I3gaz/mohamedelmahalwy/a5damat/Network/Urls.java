package com.I3gaz.mohamedelmahalwy.a5damat.Network;


import com.I3gaz.mohamedelmahalwy.a5damat.Adapters.CategoriesAdapter;

public class Urls {
    public final static String BaseUrl = "http://e3gaz.net/5dmat/public/api/v1/";
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
    public final static String MyMessges = "user_messages";
    public final static String single_message = "single_message";
    public final static String send_message = "send_message";
    public final static String report_message = "make_report";
    public final static String ServiceDetails = "single-service";
    public final static String add_service = "service";
    public final static String incoming_orders = "incoming_orders/{user_id}/{status}";
    public final static String purchases = "purchases/{user_id}/{status}";
    public final static String OrderService = "order_service";
    public final static String UserNotifications = "notifications/{user_id}";
    public final static String change_request_status = "order_status";
    public final static String single_request = "single_order/{order_id}";
    public final static String search = "search";
    public final static String upload_image = "service_image";


}
