package com.example.edwinb.epassportexample.root.NewPrecriptionMVP;

import com.example.edwinb.epassportexample.retrofit.pojos.PreSheet;
import com.example.edwinb.epassportexample.retrofit.pojos.User;
import com.example.edwinb.epassportexample.root.LoginMVP.LoginActivityMVP;

/**
 * Created by Edwin B on 10/25/2017.
 */

public interface NewPrescriptionMVP {

    interface View
    {
        String getUserEmail();
        String getPreTitle();
        String getPreDosage();
        String getPreStartDay();
        String getPreTime();

        void showUserInputError();
        void showUserAddPrescriptionSuccessMessage();

        void setPreTitle(String preTitle);
        void setPreDosage(String preDosage);
        void setPreStartDay(String preStartDay);
        void setPreTime(String preTime);
    }

    interface Presenter
    {
        void setView(NewPrescriptionMVP.View view);

        void addTimeButtonClicked();
        void cancelTimeButtonClicked();

    }

    interface Model
    {
        void createPreSheet(String email, String title, String dosage,
                            String startDay, String time);

        PreSheet getPreSheet();
    }

}
