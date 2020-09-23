package com.example.myapplication.fragment

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.myapplication.R

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [BFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [BFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BFragment : Fragment(), View.OnClickListener {
    private var text: String? = null
    private var mListener: OnFragmentInteractionListener? = null
    private var BMessageListener: OnBMessageListener? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            text = arguments!!.getString(ARG_PARAM1)
        }
        Toast.makeText(context, "BFragment create", Toast.LENGTH_SHORT).show()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_b, container, false)
        val textview = view.findViewById<TextView>(R.id.BTextView)
        textview.text = text
        val button_send_message = view.findViewById<Button>(R.id.button_send_message)
        button_send_message.setOnClickListener(this)
        return view
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri?) {
        if (mListener != null) {
            mListener!!.onFragmentInteraction(uri)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mListener =
            if (context is OnFragmentInteractionListener) {
                context
            } else {
                throw RuntimeException(
                    context.toString()
                            + " must implement OnFragmentInteractionListener"
                )
            }
        BMessageListener = if (context is OnBMessageListener) {
            context
        } else {
            throw RuntimeException("$context must implement OnBMessageListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
        Toast.makeText(activity, "BFragment onDetach", Toast.LENGTH_SHORT).show()
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.button_send_message -> if (BMessageListener != null) {
                BMessageListener!!.setMessage("")
            }
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments](http://developer.android.com/training/basics/fragments/communicating.html) for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri?)
    }

    interface OnBMessageListener {
        fun setMessage(text: String)
    }

    companion object {
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private const val ARG_PARAM1 = "param1"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param text text to display
         * @return A new instance of fragment BFragment.
         */
        fun newInstance(text: String?): BFragment {
            val fragment = BFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, text)
            fragment.arguments = args
            return fragment
        }
    }
}