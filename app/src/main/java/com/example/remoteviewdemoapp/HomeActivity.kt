package com.example.remoteviewdemoapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.remoteviewdemoapp.databinding.ActivityHomeBinding
import sync2app.com.syncapplive.additionalSettings.utils.Constants

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding


    private val sharedBiometric: SharedPreferences by lazy {
        applicationContext.getSharedPreferences(
            Constants.SHARED_BIOMETRIC,
            Context.MODE_PRIVATE
        )
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()


        binding.apply {
            closeBs.setOnClickListener {
                val intent = Intent(applicationContext, MainActivity::class.java)
                startActivity(intent)
            }
        }
    }


    private fun initView() {


        binding.apply {


            val editor = sharedBiometric.edit()

            val imagelock = sharedBiometric.getString(Constants.imgEnableLockScreen, "")
            val imagAutoBoot = sharedBiometric.getString(Constants.imgEnableAutoBoot, "")


            imgEnableLockScreen.isChecked = imagelock.equals(Constants.imgEnableLockScreen)
            imgEnableAutoBoot.isChecked = imagAutoBoot.equals(Constants.imgEnableAutoBoot)


            /// enable the lockscreen
            imgEnableLockScreen.setOnCheckedChangeListener { compoundButton, isValued ->
                if (compoundButton.isChecked) {
                    editor.putString(Constants.imgEnableLockScreen, "imgEnableLockScreen")
                    editor.apply()

                } else {

                    editor.remove("imgEnableLockScreen")
                    editor.apply()


                }
            }


            /// enable the Auto Boot
            imgEnableAutoBoot.setOnCheckedChangeListener { compoundButton, isValued -> // we are putting the values into SHARED PREFERENCE
                if (compoundButton.isChecked) {
                    editor.putString(Constants.imgEnableAutoBoot, "imgEnableAutoBoot")
                    editor.apply()
                } else {

                    // stop lock screen
                    editor.remove(Constants.imgEnableAutoBoot)
                    editor.apply()

                }
            }


        }
    }

}