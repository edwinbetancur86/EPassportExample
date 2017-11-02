package com.example.edwinb.epassportexample;

import com.example.edwinb.epassportexample.retrofit.pojos.User;
import com.example.edwinb.epassportexample.root.LoginMVP.LoginActivityMVP;
import com.example.edwinb.epassportexample.root.LoginMVP.LoginActivityPresenter;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Edwin B on 10/19/2017.
 */

public class PresenterTests {

    LoginActivityMVP.Model mockLoginModel;
    LoginActivityMVP.View mockView;
    LoginActivityPresenter presenter;
    User user;

    /*@Before
    public void setup()
    {
        mockLoginModel = mock(LoginActivityMVP.Model.class);

        user = new User("Fox1", "cool");

        //when(mockLoginModel.getUser()).thenReturn(user);

        mockView = mock(LoginActivityMVP.View.class);

        presenter = new LoginActivityPresenter(mockLoginModel);

        presenter.setView(mockView);

    }*/

    /*@Test
    public void noInteractionWithView()
    {
        presenter.getCurrentUser();

        verifyZeroInteractions(mockView);
    }*/

    /*@Test
    public void loadTheUserFromTheRepositoryWhenValidUserIsPresent()
    {
        when(mockLoginModel.getUser()).thenReturn(user);

        presenter.getCurrentUser();

        // Verify model interactions
        verify(mockLoginModel, times(1)).getUser();

        // Verify view interactions
        verify(mockView, times(1)).setPreTitle("Fox1");
        verify(mockView, times(1)).setPreDosage("cool");
        verify(mockView, never()).showUserNotAvailable();

    }*/

   /* @Test
    public void shouldShowErrorMessageWhenUserIsNull()
    {
        when(mockLoginModel.getUser()).thenReturn(null);

        presenter.getCurrentUser();

        // verify model interactions
        verify(mockLoginModel, times(1)).getUser();

        // verify view interactions
        verify(mockView, never()).setPreTitle("Fox");
        verify(mockView, never()).setPreDosage("cool");
        verify(mockView, times(1)).showUserNotAvailable();
    }
*/
    /*@Test
    public void shouldCreateErrorMessageIfFieldAreEmpty()
    {
        //set up the view mock
        when(mockView.getPreTitle()).thenReturn(""); // empty string

        presenter.saveUser();

        verify(mockView, times(1)).getPreTitle();
        verify(mockView, never()).getPreDosage();
        verify(mockView, times(1)).showInputError();

        // Now tell mockView to return a value for username and an empty password
        when(mockView.getPreTitle()).thenReturn("Dana");
        when(mockView.getPreDosage()).thenReturn("");

        presenter.saveUser();

        verify(mockView, times(2)).getPreTitle(); // called two times now, once before, and once now
        verify(mockView, times(1)).getPreDosage(); // Only called once
        verify(mockView, times(2)).showInputError(); // Called two times now, once before and once now
    }*/

   /* @Test
    public void shouldBeAbleToSaveAValidUser()
    {
        when(mockView.getPreTitle()).thenReturn("Dana");
        when(mockView.getPreDosage()).thenReturn("Scully");

        presenter.saveUser();

        // Called two more times in the saveUser call.
        verify(mockView, times(2)).getPreTitle();
        verify(mockView, times(2)).getPreDosage();

        // Make sure the repository save the user
        verify(mockLoginModel, times(1)).createUser("Dana", "Scully");

        // Make sure that the view showed the user saved messaged
        verify(mockView, times(1)).showUserAddPrescriptionSuccessMessage();
    }*/

}
