package com.automation.ui.base.common.auth.register;

import com.automation.ui.base.common.auth.FormPage;
import com.automation.ui.base.common.auth.SignUpUser;
import com.automation.ui.base.common.auth.signin.SignInPage;

import java.time.LocalDate;

public interface RegisterPage extends FormPage {

    RegisterPage typeEmailAddress(String email);

    RegisterPage typeUsername(String username);

    RegisterPage typePassword(String password);

    RegisterPage typeBirthday(int month, int day, int year);

    SignInPage navigateToSignIn();

    void signUp(String email, String username, String password, LocalDate birthday);

    void signUp(SignUpUser user);

    RegisterPage fillForm(SignUpUser user);

    boolean isPasswordMasked();

    void togglePasswordVisibility();

}
