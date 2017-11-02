package com.example.edwinb.epassportexample.root.NewPrecriptionMVP;

import com.example.edwinb.epassportexample.retrofit.pojos.PreSheet;
import com.example.edwinb.epassportexample.retrofit.pojos.User;

/**
 * Created by Edwin B on 10/26/2017.
 */

public class NewPrescriptionModel implements NewPrescriptionMVP.Model{


    private NewPrescriptionRepository repository;

    public NewPrescriptionModel(NewPrescriptionRepository repository) {
        this.repository = repository;
    }


    @Override
    public void createPreSheet(String email, String title, String dosage,
                               String startDay, String time) {
        repository.postPresheet(new PreSheet(email, title, dosage, startDay, time));
    }

    @Override
    public PreSheet getPreSheet() {
        return repository.getPreSheet();
    }
}
