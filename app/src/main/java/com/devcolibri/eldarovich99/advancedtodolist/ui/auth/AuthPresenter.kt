package com.devcolibri.eldarovich99.advancedtodolist.ui.auth

import android.content.Intent
import android.widget.Toast
import androidx.biometric.BiometricPrompt
import androidx.fragment.app.FragmentActivity
import com.devcolibri.eldarovich99.advancedtodolist.R
import com.devcolibri.eldarovich99.advancedtodolist.ui.MainActivity
import java.util.concurrent.Executors


class AuthPresenter {
    private var activity: FragmentActivity? = null
    private var biometricPrompt: BiometricPrompt? = null
    private var promptInfo: BiometricPrompt.PromptInfo? = null

    private var biometricCallback: BiometricPrompt.AuthenticationCallback? = null

    fun onViewCreated(activity: FragmentActivity?){
        promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle(activity!!.getString(R.string.auth))
            .setNegativeButtonText("Cancel")
            .build()
        biometricCallback = object : BiometricPrompt.AuthenticationCallback() {
            override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                activity.runOnUiThread {
                    Toast.makeText(activity, "Error", Toast.LENGTH_SHORT).show()
                }
                super.onAuthenticationError(errorCode, errString)
            }

            override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                activity.runOnUiThread {
                    Toast.makeText(activity, "Success", Toast.LENGTH_SHORT).show()
                    val intent = Intent(activity, MainActivity::class.java)
                    activity.startActivity(intent)
                }
                super.onAuthenticationSucceeded(result)
            }

            override fun onAuthenticationFailed() {
                activity.runOnUiThread {
                    Toast.makeText(activity, "Fail", Toast.LENGTH_SHORT).show()
                }
                super.onAuthenticationFailed()
            }
        }
        biometricPrompt = BiometricPrompt(activity,
            Executors.newSingleThreadExecutor(), biometricCallback!!)
    }

    fun authenticate(){
        biometricPrompt?.authenticate(promptInfo!!)
    }

    fun onActivityDestroyed(){
        biometricCallback = null
        promptInfo = null
        biometricPrompt = null
        activity = null
    }
}