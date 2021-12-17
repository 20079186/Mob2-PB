package ie.wit.mob2_pb.activities

import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity

import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts

import androidx.recyclerview.widget.LinearLayoutManager

import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase
import ie.wit.mob2_pb.R
import ie.wit.mob2_pb.databinding.ActivityMainBinding
import ie.wit.mob2_pb.main.MainApp
import ie.wit.mob2_pb.models.FlowerModel
import ie.wit.mob2_pb.adapters.*


class MainActivity : AppCompatActivity(), FlowerListener{

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

        val layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = FlowerAdapter(app.flowers.findAll(),this)


//        val navView: BottomNavigationView = binding.navView
//
//        val navController = findNavController(R.id.recyclerView)
//        val appBarConfiguration = AppBarConfiguration(
//            setOf(
//                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
//            )
//        )
//        setupActionBarWithNavController(navController, appBarConfiguration)
//        navView.setupWithNavController(navController)


//
//        binding.buttonFirst.setOnClickListener() {
//            val launcherIntent = Intent(this, FlowerSActivity::class.java)
//            startActivityForResult(launcherIntent,0)
//
//
//        }
                loadFlowers()
                registerRefreshCallback()
            }


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

