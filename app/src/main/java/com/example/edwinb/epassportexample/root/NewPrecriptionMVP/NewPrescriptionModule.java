package com.example.edwinb.epassportexample.root.NewPrecriptionMVP;

//import com.example.edwinb.epassportexample.root.LoginMVP.DatabaseRepository;
import com.example.edwinb.epassportexample.root.LoginMVP.DatabaseRepository;
import com.example.edwinb.epassportexample.root.LoginMVP.LoginActivityMVP;
import com.example.edwinb.epassportexample.root.LoginMVP.LoginActivityPresenter;
import com.example.edwinb.epassportexample.root.LoginMVP.LoginModel;
import com.example.edwinb.epassportexample.root.LoginMVP.LoginRepository;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Edwin B on 10/26/2017.
 */

@Module
public class NewPrescriptionModule {


        @Provides
        public NewPrescriptionMVP.Presenter provideNewPrescriptionActivityPresenter(NewPrescriptionMVP.Model model)
        {
            return new NewPrescriptionPresenter(model);
        }


        @Provides
        public NewPrescriptionMVP.Model provideNewPrescriptionActivityModel(NewPrescriptionRepository repository)
        {
            return new NewPrescriptionModel(repository);
        }

        @Provides
        public NewPrescriptionRepository provideNewPrescriptionRepository()
        {
            return new DatabaseRepository2();
        }


}
