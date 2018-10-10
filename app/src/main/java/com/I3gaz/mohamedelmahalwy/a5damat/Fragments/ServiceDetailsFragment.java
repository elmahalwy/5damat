package com.I3gaz.mohamedelmahalwy.a5damat.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.I3gaz.mohamedelmahalwy.a5damat.Adapters.ServiceDetailsVideosAndImagesAdapter;
import com.I3gaz.mohamedelmahalwy.a5damat.Adapters.ServiceDevelopmentsDetailsAdapter;
import com.I3gaz.mohamedelmahalwy.a5damat.Models.ServiceDetails.ImagesVIdeosModel;
import com.I3gaz.mohamedelmahalwy.a5damat.Models.ServiceDetails.ServiceDetails;
import com.I3gaz.mohamedelmahalwy.a5damat.Network.RetroWeb;
import com.I3gaz.mohamedelmahalwy.a5damat.Network.ServiceApi;
import com.I3gaz.mohamedelmahalwy.a5damat.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.I3gaz.mohamedelmahalwy.a5damat.Utils.ParentClass.handleException;
import static com.I3gaz.mohamedelmahalwy.a5damat.Utils.ParentClass.sharedPrefManager;

public class ServiceDetailsFragment extends Fragment {
    @BindView(R.id.rv_videos_and_images)
    RecyclerView rv_videos_and_images;
    ServiceDetailsVideosAndImagesAdapter serviceDetailsVideosAndImagesAdapter;
    List<ImagesVIdeosModel> imagesVIdeosModelList;
    LinearLayoutManager linearLayoutManager;
    @BindView(R.id.iv_like)
    ImageView iv_like;
    @BindView(R.id.tv_rate)
    TextView tv_rate;
    @BindView(R.id.tv__service_title)
    TextView tv__service_title;
    @BindView(R.id.tv_service_category)
    TextView tv_service_category;
    @BindView(R.id.tv_details)
    TextView tv_details;
    @BindView(R.id.tv_main_price)
    TextView tv_main_price;
    @BindView(R.id.tv_delivery_time)
    TextView tv_delivery_time;
    @BindView(R.id.rv_developments)
    RecyclerView rv_developments;
    ServiceDevelopmentsDetailsAdapter serviceDevelopmentsDetailsAdapter;
    LinearLayoutManager linearLayoutManagerDevlopmentsDetails;
    @BindView(R.id.tv_total_price_title)
    TextView tv_total_price_title;
    @BindView(R.id.tv_total_price)
    TextView tv_total_price;
    @BindView(R.id.btn_edit)
    Button btn_edit;
    @BindView(R.id.iv_owner)
    ImageView iv_owner;
    @BindView(R.id.tv_service_owner)
    TextView tv_service_owner;
    @BindView(R.id.tv_owner_type)
    TextView tv_owner_type;
    @BindView(R.id.iv_share)
    ImageView iv_share;
    @BindView(R.id.iv_open_chat)
    ImageView iv_open_chat;

    String user_id;


    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.service_details_fragment_layout, container, false);
        ButterKnife.bind(this, view);
        initUI();
        get_service_details();
        initEventDriven();
        return view;
    }

    private void initUI() {
        //// first recycler for images and videos ////
        imagesVIdeosModelList = new ArrayList<>();
        serviceDetailsVideosAndImagesAdapter = new ServiceDetailsVideosAndImagesAdapter(imagesVIdeosModelList, getContext());
        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayout.VERTICAL, false);
        rv_videos_and_images.setLayoutManager(linearLayoutManager);
        rv_videos_and_images.setAdapter(serviceDetailsVideosAndImagesAdapter);
        ///// end first recycler //////


        /// recycler for extra developments ////
        serviceDevelopmentsDetailsAdapter = new ServiceDevelopmentsDetailsAdapter(getActivity());
        linearLayoutManagerDevlopmentsDetails = new LinearLayoutManager(getContext(),LinearLayout.VERTICAL,false);
        rv_developments.setLayoutManager(linearLayoutManagerDevlopmentsDetails);

        ///// end //////
    }

    private void initEventDriven() {
        tv_total_price_title.setVisibility(View.GONE);
        tv_total_price.setVisibility(View.GONE);

        if (user_id.equals(sharedPrefManager.getUserDate().getId())) {
            iv_open_chat.setVisibility(View.GONE);
            iv_like.setVisibility(View.GONE);
            btn_edit.setText("تعديل الخدمة");

        } else {
            btn_edit.setText("طلب الخدمة");

        }

    }

    void get_service_details() {
        RetroWeb.getClient().create(ServiceApi.class).get_service_details(getArguments().getString("service_id"))
                .enqueue(new Callback<ServiceDetails>() {
                    @Override
                    public void onResponse(Call<ServiceDetails> call, Response<ServiceDetails> response) {
                        if (response.body().isValue()) {
                            for (int i = 0; i < response.body().getData().getImages().size(); i++) {
                                ImagesVIdeosModel imagesVIdeosModel = new ImagesVIdeosModel();
                                imagesVIdeosModel.setType("image");
                                imagesVIdeosModel.setVideo_image(response.body().getData().getImages().get(i));
                                imagesVIdeosModelList.add(imagesVIdeosModel);
                            }
                            for (int i = 0; i < response.body().getData().getVideos().size(); i++) {
                                ImagesVIdeosModel imagesVIdeosModel = new ImagesVIdeosModel();
                                imagesVIdeosModel.setType("video");
                                imagesVIdeosModel.setVideo_image(response.body().getData().getVideos().get(i));
                                imagesVIdeosModelList.add(imagesVIdeosModel);
                            }
                            tv_rate.setText(response.body().getData().getRate());
                            tv__service_title.setText(response.body().getData().getTitle());
                            tv_service_category.setText(response.body().getData().getCategory());
                            tv_details.setText(response.body().getData().getNote());
                            tv_main_price.setText("السعر الاساسي" + response.body().getData().getPrice());
                            tv_delivery_time.setText("مدة التسليم " + response.body().getData().getDeadline() + "يوم");
                            tv_service_owner.setText(response.body().getData().getOwner());
                            user_id = String.valueOf(response.body().getData().getOwnerId());

                            if (response.body().getData().getSubServices().isEmpty()) {
                                rv_developments.setVisibility(View.GONE);
                            } else {

                                serviceDevelopmentsDetailsAdapter.addAll(response.body().getData().getSubServices());
                                rv_developments.setAdapter(serviceDevelopmentsDetailsAdapter);
                            }
                        }

                    }

                    @Override
                    public void onFailure(Call<ServiceDetails> call, Throwable t) {
                        handleException(getActivity(), t);
                        t.printStackTrace();
                    }
                });
    }

}
