package com.markups.umeed;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import static android.content.Context.MODE_PRIVATE;

public class ProfileFragment extends Fragment {

    public ProfileFragment() {
        // Required empty public constructor
    }

TextView profile_tv_name,profile_tv_ID,profile_tv_contact,profile_bt_logout;
    SharedPreferences spref;
    SharedPreferences.Editor edit;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        spref = getActivity().getApplicationContext().getSharedPreferences("user", MODE_PRIVATE);
        edit=spref.edit();
        final View v= inflater.inflate(R.layout.fragment_profile, container, false);
        profile_tv_name=v.findViewById(R.id.profile_tv_name);
        profile_tv_contact=v.findViewById(R.id.profile_tv_contact);
        profile_tv_ID=v.findViewById(R.id.profile_tv_ID);
        profile_bt_logout=v.findViewById(R.id.profile_bt_logout);
        profile_tv_name.setText(spref.getString("name","name"));
        profile_tv_ID.setText(spref.getString("id","id"));
        profile_tv_contact.setText(spref.getString("contact","contact"));
        profile_bt_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               edit.clear();
               edit.commit();
               startActivity(new Intent(v.getContext(),LogInActivity.class));
               getActivity().finish();
            }
        });
        return v;
    }
}