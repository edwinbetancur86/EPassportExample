package com.example.edwinb.epassportexample.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.edwinb.epassportexample.NewPrescription.NewPrescriptionActivity;
import com.example.edwinb.epassportexample.R;
import com.example.edwinb.epassportexample.SQLiteOpenHelper.SQLiteHelper;
import com.example.edwinb.epassportexample.retrofit.SampleAPI;
import com.example.edwinb.epassportexample.retrofit.pojos.PreSheet;
import com.example.edwinb.epassportexample.root.App;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AlertFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AlertFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AlertFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private final String TAG = "AlertFragment";
    private final String SP_ALERT_SAVE_DATA = "AlertData";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    /*@BindView(R.id.prescriptName)
    EditText prescriptionEdit;
    @BindView(R.id.timeOfPrescriptEdit)
    EditText timePrescriptEdit;*/


    public AlertFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AlertFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AlertFragment newInstance(String param1, String param2) {
        AlertFragment fragment = new AlertFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);

            ((App) getContext()).getComponent().inject(this);

            //ButterKnife.bind(getActivity());



        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_alert, container, false);

        EditText prescriptionTitleEdit = (EditText) view.findViewById(R.id.prescriptName);
        EditText timePrescriptEdit = (EditText) view.findViewById(R.id.timeOfPrescriptEdit);

        SharedPreferences sp = getContext()
                .getSharedPreferences(SP_ALERT_SAVE_DATA, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sp.getString("PreSheetData", "");
        PreSheet preSheet = gson.fromJson(json, PreSheet.class);

        if (preSheet != null)
        {
            prescriptionTitleEdit.setText(preSheet.getPreTitle());
            timePrescriptEdit.setText(preSheet.getPreStartDay());
        }


        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
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
            //Toast.makeText(context, "Fragment Alert Attached", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onDetach() {

        super.onDetach();
        mListener = null;
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


   /* public String getUserEmail() {


        Cursor res = sqLiteHelper.getAllData();

        if (res.getCount() == 0) {
            Toast.makeText(getActivity(), "Error, no email value in database", Toast.LENGTH_LONG).show();
            return null;
        } else {
            ArrayList<String> cursorResList = new ArrayList<>();

            while (res.moveToNext()) {
                cursorResList.add(res.getString(1));
            }
            Log.d(TAG, String.valueOf(cursorResList.get(0)));
            Toast.makeText(getActivity(), String.valueOf(cursorResList.get(0)), Toast.LENGTH_LONG).show();

            return cursorResList.get(0);

        }


    }*/




}
