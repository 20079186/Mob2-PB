package ie.wit.mob2_pb.activities

import android.app.Activity
import android.content.SharedPreferences
import android.os.Bundle
import android.provider.Settings.Global.getString
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.*
import com.google.android.material.switchmaterial.SwitchMaterial
import ie.wit.mob2_pb.R
import ie.wit.mob2_pb.databinding.ActivityMainBinding
import ie.wit.mob2_pb.databinding.SettingsActivityBinding
import java.util.prefs.PreferenceChangeListener

class SettingsActivity : AppCompatActivity() {


    private lateinit var binding: SettingsActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_activity)


//
//        R.id.themeSwitch.setOnCheckedChangeListener { _, checkedId ->
//            when(checkedId){
//                true -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
//
//                false -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
//            }

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.settings, SettingsFragment())
                .commit()

            supportActionBar?.setDisplayHomeAsUpEnabled(true)

//            PreferenceManager.getDefaultSharedPreferences(this)
//                .registerOnSharedPreferenceChangeListener(this)
        }

    }
        }

//    override fun onDestroy() {
//            super.onDestroy()
//            PreferenceManager.getDefaultSharedPreferences(this)
//                .unregisterOnSharedPreferenceChangeListener(this)
//        }
    class SettingsFragment : PreferenceFragmentCompat() {
        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey)
            }
        }
//
//    override fun provideSummary(preference: ListPreference?): CharSequence =
//        if (preference?.key == getString(R.string.dark_mode)) preference.entry
//        else "Unknown Preference"
//
//    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
//        val darkModeString = getString(R.string.dark_mode)
//        key?.let {
//            if (it == darkModeString) sharedPreferences?.let { pref ->
//                val darkModeValues = resources.getStringArray(R.array.dark_mode_values)
//                when (pref.getString(darkModeString, darkModeValues[0])) {
//                    darkModeValues[0] -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
//                    darkModeValues[1] -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
//                    darkModeValues[2] -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
//                    darkModeValues[3] -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY)
//                }
//            }
//        }
//    }






