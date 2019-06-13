package com.devcolibri.eldarovich99.advancedtodolist.ui.auth

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
//import androidx.biometric.BiometricPrompt
import com.devcolibri.eldarovich99.advancedtodolist.R
import kotlinx.android.synthetic.main.activity_auth.*
//import kotlinx.android.synthetic.main.activity_auth.*

class AuthActivity : AppCompatActivity() {
    //@Inject
    var authPresenter = AuthPresenter()
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        //Injector.getAppComponent().inject(this)
        setContentView(R.layout.activity_auth)

        launchAuthenticationButton.setOnClickListener{
            authPresenter.authenticate()
        }
    }

    override fun onStart() {
        authPresenter.onViewCreated(this)
        super.onStart()
    }

    override fun onStop() {
        authPresenter.onActivityDestroyed()
        super.onStop()
    }
}
