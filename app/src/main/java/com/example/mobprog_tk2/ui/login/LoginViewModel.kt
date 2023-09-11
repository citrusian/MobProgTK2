package com.example.mobprog_tk2.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.util.Patterns
import com.example.mobprog_tk2.data.LoginRepository
import com.example.mobprog_tk2.data.Result

import com.example.mobprog_tk2.R
import java.security.KeyStore

class LoginViewModel(private val loginRepository: LoginRepository) : ViewModel() {

    private val _loginForm = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = _loginForm

    private val _loginResult = MutableLiveData<LoginResult>()
    val loginResult: LiveData<LoginResult> = _loginResult

    fun login(username: String, password: String) {
        // can be launched in a separate asynchronous job
        val result = loginRepository.login(username, password)

        if (result is Result.Success) {
            _loginResult.value =
                LoginResult(success = LoggedInUserView(displayName = result.data.displayName))
        } else {
            _loginResult.value = LoginResult(error = R.string.login_failed)
        }
    }

    fun loginDataChanged(username: String, password: String) {
        if (!isUserNameValid(username)) {
            _loginForm.value = LoginFormState(usernameError = R.string.invalid_username)
        } else if (!isPasswordValid(password)) {
            _loginForm.value = LoginFormState(passwordError = R.string.invalid_password)
        } else {
            _loginForm.value = LoginFormState(isDataValid = true)
        }
    }

    // A placeholder username validation check
//    private fun isUserNameValid(username: String): Boolean {
//        val specialCharacters = arrayOf("@", "#", "$", "%", "^", "&")
//        return if (specialCharacters.any { it in username }) {
//            Patterns.EMAIL_ADDRESS.matcher(username).matches()
//        } else {
//            username.isNotBlank()
//        }
//    }


    // Username Validation
    // Regex Test
    private fun isUserNameValid(username: String): Boolean {
//        val specialCharacters = [^aA-zZ]
//        val specialCharacters = intArrayOf("^", "a"-"z")
        val specialCharacters = Regex("[^a-zA-Z]")
        return if (specialCharacters.containsMatchIn(username)) {
            Patterns.EMAIL_ADDRESS.matcher(username).matches()
        } else {
            username.isNotBlank()
        }
    }

    // Password Validation
    private fun isPasswordValid(password: String): Boolean {
        val specialCharacters = Regex("[^a-zA-Z]")
//        return if (specialCharacters.containsMatchIn(password)) {
//            Patterns.EMAIL_ADDRESS.matcher(password).matches()
//        } else {
//            return password.length > 3
//        }
        // Regex sometimes needed in password
        return password.length > 3
    }
}