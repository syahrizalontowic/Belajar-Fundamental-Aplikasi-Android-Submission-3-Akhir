package com.syahrizal.favoriteuser.ui.activity

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import androidx.appcompat.app.AppCompatActivity
import com.syahrizal.favoriteuser.R
import com.syahrizal.favoriteuser.service.AlarmReceiver
import kotlinx.android.synthetic.main.activity_settings.*

class SettingsActivity : AppCompatActivity() {

    private lateinit var alarmReceiver: AlarmReceiver

    companion object {
        const val BOOLEAN_KEY = "booleankey"
        const val SHARED_PREFERENCE = "sharedpreference"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        alarmReceiver = AlarmReceiver()

        val sharedPreferences = getSharedPreferences(SHARED_PREFERENCE, Context.MODE_PRIVATE)

        val getBoolean = sharedPreferences.getBoolean(BOOLEAN_KEY, false)

        tvSelectLanguage.setOnClickListener {
            startActivity(Intent(Settings.ACTION_LOCALE_SETTINGS))
        }

        scOnOff.isChecked = getBoolean

        scOnOff.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                val editor = sharedPreferences.edit()
                editor.apply {
                    putBoolean(BOOLEAN_KEY, true)
                }.apply()

                alarmReceiver.setRepeatAlarm(this, AlarmReceiver.EXTRA_TYPE, "09:00")

            } else {
                val editor = sharedPreferences.edit()
                editor.apply {
                    putBoolean(BOOLEAN_KEY, false)
                }.apply()

                alarmReceiver.cancelAlarm(this, AlarmReceiver.TYPE_ALARM_REPEATING)
            }
        }

        tvFavoriteUser.setOnClickListener {
            startActivity(Intent(applicationContext, FavoriteActivity::class.java))
        }

        tvAuthor.setOnClickListener {
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://github.com/syahrizalontowic")
                )
            )
        }

        tvSearchMain.setOnClickListener {
            startActivity(Intent(applicationContext, MainActivity::class.java))
        }

        title = applicationContext.resources.getString(R.string.pengaturan)
    }
}

