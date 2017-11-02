package com.example.edwinb.epassportexample.root.NewPrecriptionMVP;

import com.example.edwinb.epassportexample.retrofit.pojos.PreSheet;
import com.example.edwinb.epassportexample.retrofit.pojos.User;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by Edwin B on 10/26/2017.
 */

public interface NewPrescriptionRepository {

    PreSheet getPreSheet();

    void postPresheet(PreSheet preSheet);


}
