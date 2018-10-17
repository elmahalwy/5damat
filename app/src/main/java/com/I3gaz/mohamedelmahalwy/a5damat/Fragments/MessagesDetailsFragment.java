package com.I3gaz.mohamedelmahalwy.a5damat.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.I3gaz.mohamedelmahalwy.a5damat.Activites.HomeActivity;
import com.I3gaz.mohamedelmahalwy.a5damat.Models.ReportMessage.ReportMessage;
import com.I3gaz.mohamedelmahalwy.a5damat.Models.SendMessage.SendMessage;
import com.I3gaz.mohamedelmahalwy.a5damat.Models.SingleMessage.SingleMessage;
import com.I3gaz.mohamedelmahalwy.a5damat.Network.RetroWeb;
import com.I3gaz.mohamedelmahalwy.a5damat.Network.ServiceApi;
import com.I3gaz.mohamedelmahalwy.a5damat.R;
import com.I3gaz.mohamedelmahalwy.a5damat.Utils.ParentClass;
import com.squareup.picasso.Picasso;


import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.I3gaz.mohamedelmahalwy.a5damat.Utils.ParentClass.handleException;
import static com.I3gaz.mohamedelmahalwy.a5damat.Utils.ParentClass.makeToast;
import static com.I3gaz.mohamedelmahalwy.a5damat.Utils.ParentClass.sharedPrefManager;

public class MessagesDetailsFragment extends Fragment {
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.relative_report)
    RelativeLayout relative_report;
    @BindView(R.id.iv_user_message)
    ImageView iv_user_message;
    @BindView(R.id.tv_user_name)
    TextView tv_user_name;
    @BindView(R.id.tv_message_date)
    TextView tv_message_date;
    @BindView(R.id.tv_message_content)
    TextView tv_message_content;
    @BindView(R.id.et_add_comment)
    EditText et_add_comment;
    @BindView(R.id.no_socials)
    RelativeLayout no_socials;
    @BindView(R.id.iv_check)
    ImageView iv_check;
    @BindView(R.id.read_instructions)
    RelativeLayout read_instructions;
    @BindView(R.id.iv_check1)
    ImageView iv_check1;
    @BindView(R.id.btn_send)
    Button btn_send;
    boolean check_no_socials = false;
    boolean check_read_instructions = false;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.message_details_fragment_layout, container, false);
        ButterKnife.bind(this, view);
        initUI();
        initEventDriven();
        get_single_message();
        return view;
    }

    void initUI() {
        ((HomeActivity)getContext()).tv_toolbar_title.setText("الرسائل");
        no_socials.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check_no_socials == false) {
                    check_no_socials = true;
                    iv_check.setImageResource(R.mipmap.circle2);
                }
                if (check_no_socials == true) {
                    check_no_socials = false;
                    iv_check.setImageResource(R.mipmap.circle_grey);
                }
            }
        });

        read_instructions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check_read_instructions == false) {
                    check_read_instructions = true;
                    iv_check1.setImageResource(R.mipmap.circle2);
                }
                if (check_read_instructions == true) {
                    check_read_instructions = false;
                    iv_check1.setImageResource(R.mipmap.circle2);
                }

            }
        });
    }

    void initEventDriven() {
        relative_report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                report();
            }
        });
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                send_message();
            }
        });


    }

    void get_single_message() {
        ((HomeActivity) getActivity()).showdialog();

        RetroWeb.getClient().create(ServiceApi.class).get_single_message(getArguments().getString("message_id")).enqueue(new Callback<SingleMessage>() {
            @Override
            public void onResponse(Call<SingleMessage> call, Response<SingleMessage> response) {
                ((HomeActivity) getActivity()).dismis_dialog();
                if (response.body().isValue()) {
                    tv_title.setText(response.body().getData().getServiceTitle());
                    Picasso.with(getContext()).load(response.body().getData().getSenderImage()).into(iv_user_message);
                    tv_user_name.setText(response.body().getData().getMessageSender());
                    tv_message_content.setText(response.body().getData().getMessage());
                    tv_message_date.setText(response.body().getData().getMessageDate());


                }

            }

            @Override
            public void onFailure(Call<SingleMessage> call, Throwable t) {
                ((HomeActivity) getActivity()).dismis_dialog();
                handleException(getActivity(), t);
                t.printStackTrace();
            }
        });

    }

    void report() {
        ((HomeActivity) getActivity()).showdialog();
        RetroWeb.getClient().create(ServiceApi.class).report_message(String.valueOf(ParentClass.sharedPrefManager.getUserDate().getId()),
                getArguments().getString("user_id"), getArguments().getString("message_id")).enqueue(new Callback<ReportMessage>() {
            @Override
            public void onResponse(Call<ReportMessage> call, Response<ReportMessage> response) {
                ((HomeActivity) getActivity()).dismis_dialog();
                if (response.body().isValue()) {
                    makeToast(getContext(), "تم ارسال بلاغك سيتم مراجعته في اقرب وقت");
                }
            }

            @Override
            public void onFailure(Call<ReportMessage> call, Throwable t) {
                ((HomeActivity) getActivity()).dismis_dialog();
                handleException(getActivity(), t);
                t.printStackTrace();
            }
        });
    }

    void send_message() {
        boolean cancel = false;
        View focusView = null;
        if (check_no_socials == false) {
            makeToast(getContext(), "برجاء التأكد ان هذه الرسالة لا تحتوي علي وسائل تواصل خارجية");
            focusView = no_socials;
            cancel = true;
        }
        if (check_read_instructions == false) {
            makeToast(getContext(), "برجاء التأكد ان هذه الرسالة لا تحتوي علي وسائل تواصل خارجية");
            focusView = read_instructions;
            cancel = true;
        }
        if (cancel) {
        } else {
            ((HomeActivity) getActivity()).showdialog();
            RetroWeb.getClient().create(ServiceApi.class).send_message(String.valueOf(sharedPrefManager.getUserDate().getId()),
                    getArguments().getString("service_id"), et_add_comment.getText().toString()).enqueue(new Callback<SendMessage>() {
                @Override
                public void onResponse(Call<SendMessage> call, Response<SendMessage> response) {
                    ((HomeActivity) getActivity()).dismis_dialog();
                    if (response.body().isValue()) {
                        makeToast(getContext(), "تم ارسال رسالتك بنجاح...");
                    }

                }

                @Override
                public void onFailure(Call<SendMessage> call, Throwable t) {
                    ((HomeActivity) getActivity()).dismis_dialog();
                    handleException(getActivity(), t);
                    t.printStackTrace();
                }
            });
        }
    }


}
