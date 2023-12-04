package com.example.b07project;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import android.content.SharedPreferences;
import android.text.TextUtils;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.runners.MockitoJUnitRunner;


import com.example.b07project.LoginMVP.LoginModel;
import com.example.b07project.LoginMVP.LoginPresenter;
import com.example.b07project.LoginMVP.LoginView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;

import com.example.b07project.LoginMVP.LoginModel;
import com.example.b07project.LoginMVP.LoginPresenter;
import com.example.b07project.LoginMVP.LoginView;
import com.google.firebase.database.DataSnapshot;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

@RunWith(MockitoJUnitRunner.class)
public class LoginPresenterTest {

    @Mock
    private LoginView mockView;

    @Mock
    private LoginModel mockModel;

    @Mock
    private FirebaseAuth mockAuth;

    @Mock
    private FirebaseUser mockFirebaseUser;

    @Mock
    private SharedPreferences mockSharedPreferences;

    @Mock
    private DataSnapshot mockDataSnapshot;

    private LoginPresenter presenter;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        presenter = new LoginPresenter(mockView, mockModel);
    }
    @Test
    public void testOnLoginClick_EmptyEmail_DisplayErrorMessage() {
        // Arrange

        when(mockView.findEmailEditText()).thenReturn("");
        when(mockView.findPasswordEditText()).thenReturn("password");

        // Act
        presenter.onLoginClick();

        // Assert
        verify(mockView).displayText("Email cannot Be Empty!");
    }

    @Test
    public void testOnLoginClick_EmptyPassword_DisplayErrorMessage() {
        // Arrange
        when(mockView.findEmailEditText()).thenReturn("email@example.com");
        when(mockView.findPasswordEditText()).thenReturn("");

        // Act
        presenter.onLoginClick();

        // Assert
        verify(mockView).displayText("Password cannot Be Empty!");
    }

    @Test
    public void testOnLoginClick_ValidCredentials_InvokeModelCheckLogin() {
        // Arrange
        when(mockView.findEmailEditText()).thenReturn("email@example.com");
        when(mockView.findPasswordEditText()).thenReturn("password");

        // Act
        presenter.onLoginClick();

        // Assert
        verify(mockModel).checkLogin("email@example.com", "password", presenter);
    }

    @Test
    public void testOnLogin_NonEmptyUid_DisplayLoginSuccessfulMessage() {

        // Act
        presenter.onLogin("uid");

        // Assert
        verify(mockView).displayText("Login Successful");
    }

    @Test
    public void testOnLogin_EmptyUid_DisplayErrorMessage() {

        // Act
        presenter.onLogin("");

        // Assert
        verify(mockView).displayText("Incorrect Password Entered or Username Does Not Exist!");
    }

    @Test
    public void testFetchContext_ReturnSharedPreferences() {
        // Arrange
        when(mockView.getCont()).thenReturn(mockSharedPreferences);

        // Act
        SharedPreferences result = presenter.fetchContext();

        // Assert
        assertEquals(mockSharedPreferences, result);
    }

    @Test
    public void testCheckRole_StudentRole_SwitchToStudentHomePage() {
        // Arrange
        when(mockView.findEmailEditText()).thenReturn("email@example.com");
        when(mockView.findPasswordEditText()).thenReturn("password");

        // Act
        presenter.onLoginClick();
        presenter.onLogin("uid");
        presenter.checkRole("Student");

        // Assert
        verify(mockView).switchToStudentHomePage();
    }

    @Test
    public void testCheckRole_AdminRole_SwitchToAdminHomePage() {
        // Arrange
        when(mockView.findEmailEditText()).thenReturn("email@example.com");
        when(mockView.findPasswordEditText()).thenReturn("password");

        // Act
        presenter.onLoginClick();
        presenter.onLogin("uid");
        presenter.checkRole("Admin");

        // Assert
        verify(mockView).switchToAdminHomePage();
    }

    @Test
    public void testCheckUser_ExistingUserData_RedirectHomePage() {
        // Arrange
        when(mockDataSnapshot.exists()).thenReturn(true);

        // Act
        presenter.checkUser(mockDataSnapshot, "uid");

        // Assert
        verify(mockModel).redirectHomePage(mockDataSnapshot, "uid", presenter);
    }

    @Test
    public void testCheckUser_NonExistingUserData_DisplayErrorMessage() {
        // Arrange
        when(mockDataSnapshot.exists()).thenReturn(false);

        // Act
        presenter.checkUser(mockDataSnapshot, "uid");

        // Assert
        verify(mockView).displayText("Userdata Not Found");
    }
}


    /*
public class LoginPresenterTest {

    @Mock
    private LoginView mockView;

    @Mock
    private LoginModel mockModel;

    private LoginPresenter presenter;


    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        presenter = new LoginPresenter(mockView, mockModel);
    }

    @Test
    public void testOnLoginClick_EmptyEmail_DisplayErrorMessage() {
        // Arrange
        when(mockView.findEmailEditText()).thenReturn("");
        when(mockView.findPasswordEditText()).thenReturn("password");

        // Act
        presenter.onLoginClick();

        // Assert
        verify(mockView).displayText("Email cannot Be Empty!");
    }

    @Test
    public void testOnLoginClick_EmptyPassword_DisplayErrorMessage() {
        // Arrange
        when(mockView.findEmailEditText()).thenReturn("email");
        when(mockView.findPasswordEditText()).thenReturn("");

        // Act
        presenter.onLoginClick();

        // Assert
        verify(mockView).displayText("Password cannot Be Empty!");
    }

    @Test
    public void testOnLogin_ValidCredentials_CallsModelCheckLogin() {
        // Arrange
        when(mockView.findEmailEditText()).thenReturn("email");
        when(mockView.findPasswordEditText()).thenReturn("password");

        // Act
        presenter.onLoginClick();

        // Assert
        verify(mockModel).checkLogin("email", "password", presenter);
    }

    @Test
    public void testOnLogin_SuccessfulLogin_DisplaySuccessMessage() {
        // Arrange
        String uid = "someUid";

        // Act
        presenter.onLogin(uid);

        // Assert
        verify(mockView).displayText("Login Successful");
        verify(mockModel).fetchAndDisplayUserData(uid, presenter);
    }

    // Add more test cases as needed for other scenarios.

}
*/
