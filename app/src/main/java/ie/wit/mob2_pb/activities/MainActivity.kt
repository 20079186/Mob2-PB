package ie.wit.mob2_pb.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController

import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView

import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import ie.wit.mob2_pb.R
import ie.wit.mob2_pb.activities.login.LoginActivity
import ie.wit.mob2_pb.databinding.ActivityMainBinding
import ie.wit.mob2_pb.main.MainApp
import ie.wit.mob2_pb.models.FlowerModel
import ie.wit.mob2_pb.adapters.*


class MainActivity : AppCompatActivity(), FlowerListener {

    private lateinit var refreshIntentLauncher: ActivityResultLauncher<Intent>
    private lateinit var analytics: FirebaseAnalytics
    private lateinit var binding: ActivityMainBinding
    lateinit var app: MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        // Obtain the FirebaseAnalytics instance.
        analytics = Firebase.analytics

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolbar.title = title
        setSupportActionBar(binding.toolbar)


        app = application as MainApp

//        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.nav_view)
//        val navController = findNavController(R.id.nav_)

        val layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = FlowerAdapter(app.flowers.findAll(), this)


        loadFlowers()
        registerRefreshCallback()
    }


//        val navView: BottomNavigationView = binding.navView
//
//        val navController = findNavController(R.id.recyclerView)
//        val appBarConfiguration = AppBarConfiguration(
//            setOf(
//                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notices
//            )
//        )
//        setupActionBarWithNavController(navController, appBarConfiguration)
//        navView.setupWithNavController(navController)
//
//


        override fun onFlowerClick(flower: FlowerModel) {
            val launcherIntent = Intent(this, FlowerSActivity::class.java)
            launcherIntent.putExtra("flower_edit", flower)
            startActivityForResult(launcherIntent,0)
        //    refreshIntentLauncher.launch(launcherIntent)
        }




        override fun onCreateOptionsMenu(menu: Menu): Boolean {
            // Inflate the menu; this adds items to the action bar if it is present.
            menuInflater.inflate(R.menu.menu_main, menu)
            return super.onCreateOptionsMenu(menu)


        }

        override fun onOptionsItemSelected(item: MenuItem): Boolean {
            when (item.itemId) {
                R.id.item_add -> {
                    val launcherIntent = Intent(this, FlowerSActivity::class.java)
                 //   startActivityForResult(launcherIntent,0)
                    refreshIntentLauncher.launch(launcherIntent)
                }
                R.id.action_settings ->{
                    val launcherIntent = Intent(this, SettingsActivity::class.java)
                    refreshIntentLauncher.launch(launcherIntent)
                }

                R.id.action_logout -> {
                    FirebaseAuth.getInstance().signOut()
                    startActivity(Intent(this, LoginActivity::class.java))
                    finish()
                }

                R.id.app_about -> {
                    val launcherIntent = Intent(this, AboutActivity::class.java)
                    refreshIntentLauncher.launch(launcherIntent)
                }

                R.id.send_feedback -> {
                    val launcherIntent = Intent(this, FeedbackActivity::class.java)
                    refreshIntentLauncher.launch(launcherIntent)
                }

                R.id.navigation_notices -> {
                    val launcherIntent = Intent(this, NoticesActivity::class.java)
                    refreshIntentLauncher.launch(launcherIntent)
                }
                R.id.navigation_home -> {
                    val launcherIntent = Intent(this, MainActivity::class.java)
                    refreshIntentLauncher.launch(launcherIntent)
                }
                R.id.navigation_dashboard -> {
                    val launcherIntent = Intent(this, DashboardActivity::class.java)
                    refreshIntentLauncher.launch(launcherIntent)
                }

                }
            return super.onOptionsItemSelected(item)
        }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        binding.recyclerView.adapter?.notifyDataSetChanged()
        super.onActivityResult(requestCode, resultCode, data)
    }


        private fun registerRefreshCallback() {
            refreshIntentLauncher =
                registerForActivityResult(ActivityResultContracts.StartActivityForResult())
                { loadFlowers() }
        }

        private fun loadFlowers() {
            showFlowers(app.flowers.findAll())
        }

        fun showFlowers(flowers: List<FlowerModel>) {
            binding.recyclerView.adapter = FlowerAdapter(flowers, this)
            binding.recyclerView.adapter?.notifyDataSetChanged()
        }
    }

