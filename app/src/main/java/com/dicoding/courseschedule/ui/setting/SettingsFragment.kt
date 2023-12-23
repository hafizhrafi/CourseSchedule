package com.dicoding.courseschedule.ui.setting

import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatDelegate
import com.dicoding.courseschedule.util.NightMode
import androidx.preference.ListPreference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreference
import android.Manifest
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.dicoding.courseschedule.R
import com.dicoding.courseschedule.notification.DailyReminder
import java.util.Locale

class SettingsFragment : PreferenceFragmentCompat() {

    private val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                showToast("Notifications permission granted")
            } else {
                showToast("Notifications will not show without permission")
            }
        }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)

        requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)

        //TODO 10 : Update theme based on value in ListPreference
        val changeTheme = findPreference<ListPreference>(getString(R.string.pref_key_dark))

        changeTheme?.setOnPreferenceChangeListener { _, newValue ->
            val themeMode = NightMode.valueOf(newValue.toString().uppercase(Locale.getDefault()))
            updateTheme(themeMode.value)
            true
        }

        //TODO 11 : Schedule and cancel notification in DailyReminder based on SwitchPreference
        val notifPreference = findPreference<SwitchPreference>(getString(R.string.pref_key_notify))
        val dailyReminder = DailyReminder()
        notifPreference?.setOnPreferenceChangeListener {_, newValue ->
            val value = newValue as Boolean
            if (value) {
                dailyReminder.setDailyReminder(requireContext())
            } else {
                dailyReminder.cancelAlarm(requireContext())
            }
            true
        }
    }

    private fun updateTheme(nightMode: Int): Boolean {
        AppCompatDelegate.setDefaultNightMode(nightMode)
        requireActivity().recreate()
        return true
    }
}