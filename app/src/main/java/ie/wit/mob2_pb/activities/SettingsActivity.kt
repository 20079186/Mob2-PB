package ie.wit.mob2_pb.activities

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.Preference
import androidx.preference.PreferenceCategory
import com.google.android.material.switchmaterial.SwitchMaterial
import androidx.preference.PreferenceFragmentCompat
import ie.wit.mob2_pb.R
import ie.wit.mob2_pb.databinding.ActivityMainBinding

class SettingsActivity : AppCompatActivity() {



    private lateinit var binding: PreferenceCategory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_activity)



//
//        R.id.themeSwitch.setOnCheckedChangeListener { this, checkedId ->
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
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
            }

        }

    class SettingsFragment : PreferenceFragmentCompat() {
        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey)



            }
        }





