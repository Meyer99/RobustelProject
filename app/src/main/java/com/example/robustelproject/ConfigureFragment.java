package com.example.robustelproject;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class ConfigureFragment extends Fragment
{

    private TextInputEditText tvUserName, tvPassword, tvSSL, tvTopic;
    private MaterialButton btnConfigure, btnReset;

    public ConfigureFragment()
    {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_configure, container, false);
        tvUserName = (TextInputEditText) view.findViewById(R.id.name);
        tvPassword = (TextInputEditText) view.findViewById(R.id.password);
        tvSSL = (TextInputEditText) view.findViewById(R.id.ssl);
        tvTopic = (TextInputEditText) view.findViewById(R.id.topic);

        btnConfigure = (MaterialButton) view.findViewById(R.id.btnConfigure);
        btnReset = (MaterialButton) view.findViewById(R.id.btnReset);

        btnConfigure.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(tvUserName.getText().length() != 0 &&
                tvPassword.getText().length() != 0 &&
                tvSSL.getText().length() != 0 &&
                tvTopic.getText().length() != 0)
                {
                    Toast.makeText(getContext(),"配置成功",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(getContext(),"请输入正确的参数信息",Toast.LENGTH_SHORT).show();
                }
            }
        });


        btnReset.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                tvUserName.setText("");
                tvPassword.setText("");
                tvSSL.setText("");
                tvTopic.setText("");
            }
        });



        return view;
    }

}
