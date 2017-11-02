package com.example.edwinb.epassportexample.root.NewPrecriptionMVP;

import android.support.annotation.Nullable;

import com.example.edwinb.epassportexample.Firebase.FirebaseHelper;
import com.example.edwinb.epassportexample.retrofit.pojos.User;

import javax.inject.Inject;

/**
 * Created by Edwin B on 10/26/2017.
 */

public class NewPrescriptionPresenter implements NewPrescriptionMVP.Presenter {

    @Nullable
    private NewPrescriptionMVP.View view;
    private NewPrescriptionMVP.Model model;


    public NewPrescriptionPresenter(NewPrescriptionMVP.Model model) {
        this.model = model;

    }

    @Override
    public void setView(NewPrescriptionMVP.View view) {
        this.view = view;
    }

    @Override
    public void addTimeButtonClicked() {

        if (view != null)
        {
            // Add more error detection here
            if (view.getPreTitle().trim().equals("")
                    || view.getPreDosage().trim().equals("")
                    || view.getPreStartDay().trim().equals("")
                    || view.getPreTime().trim().equals(""))
            {
                view.showUserInputError();
            }
            else
            {
                model.createPreSheet(view.getUserEmail(), view.getPreTitle(), view.getPreDosage(), view.getPreStartDay(), view.getPreTime());
                view.showUserAddPrescriptionSuccessMessage();

            }
        }

    }

    @Override
    public void cancelTimeButtonClicked() {

        // Logic for cancel button goes here if
        // you have time to do the MVP way

        if (view != null)
        {
            view.setPreTitle("");
            view.setPreDosage("");
            view.setPreStartDay("");
            view.setPreTime("");
        }

    }




}
