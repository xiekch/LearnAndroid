package com.example.myapplication.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AFragment extends Fragment implements View.OnClickListener {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";

    private String text;
    private Button button_send_message;
    private OnAMessageListener AMessageListener;

    private OnFragmentInteractionListener mListener;

    public AFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param text text to display
     * @return A new instance of fragment AFragment.
     */
    public static AFragment newInstance(String text) {
        AFragment fragment = new AFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, text);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            text = getArguments().getString(ARG_PARAM1);
        }
        Toast.makeText(getContext(), "AFragment create", Toast.LENGTH_SHORT).show();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_a, container, false);
        TextView textview = view.findViewById(R.id.ATextview);
        button_send_message = view.findViewById(R.id.button_send_message);
        textview.setText(text);
        button_send_message.setOnClickListener(this);
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
        if (context instanceof OnAMessageListener) {
            AMessageListener = (OnAMessageListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnMessageListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_send_message:
                if (AMessageListener != null) {
                    AMessageListener.setMessage("Here we go");
                }
                break;
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    // It requires activities to implement the interface instead of
    //  knowing the actual classes of activities
    // dependence inversion
    public interface OnAMessageListener {
        void setMessage(String text);
    }
}
