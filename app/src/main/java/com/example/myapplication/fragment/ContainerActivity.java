package com.example.myapplication.fragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.myapplication.R;

public class ContainerActivity extends AppCompatActivity implements AFragment.OnFragmentInteractionListener, BFragment.OnFragmentInteractionListener, View.OnClickListener {
    private AFragment aFragment;
    private BFragment bFragment;
    private Button change_fragmentA_button;
    private Button change_fragmentB_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);

        change_fragmentA_button = findViewById(R.id.change_fragmentA_button);
        change_fragmentA_button.setOnClickListener(this);
        change_fragmentB_button = findViewById(R.id.change_fragmentB_button);
        change_fragmentB_button.setOnClickListener(this);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.change_fragmentA_button:
                initTransactionA();
                break;
            case R.id.change_fragmentB_button:
                initTransactionB();
                break;
        }
    }

    private void initTransactionA() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (aFragment == null) {
            aFragment = new AFragment();
            transaction.add(R.id.frameLayout, aFragment);
        }
        hideFragment(transaction);
        transaction.show(aFragment).commitAllowingStateLoss();
    }

    private void initTransactionB() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (bFragment == null) {
            bFragment = new BFragment();
            transaction.add(R.id.frameLayout, bFragment);
        }
        hideFragment(transaction);
        transaction.show(bFragment).commitAllowingStateLoss();
    }

    private void hideFragment(FragmentTransaction transaction) {
        if (aFragment != null) {
            transaction.hide(aFragment);
        }
        if (bFragment != null) {
            transaction.hide(bFragment);
        }
    }
}