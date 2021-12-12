package ie.wit.mob2_pb.activities

import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import ie.wit.mob2_pb.R
import ie.wit.mob2_pb.databinding.ActivityMainBinding
import ie.wit.mob2_pb.main.MainApp
import ie.wit.mob2_pb.models.FlowerModel


class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var refreshIntentLauncher: ActivityResultLauncher<Intent>
    private lateinit var binding: ActivityMainBinding
    lateinit var app: MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolbar.title = title
        setSupportActionBar(binding.toolbar)
        app = application as MainApp
        loadFlowers()

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    override fun onFlowerClick(flower: FlowerModel) {
        val launcherIntent = Intent(this, FlowerSFragment::class.java)
        launcherIntent.putExtra("flower_edit", flower)
        refreshIntentLauncher.launch(launcherIntent)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.item_add -> {
                val launcherIntent = Intent(this, FlowerSFragment::class.java)
                refreshIntentLauncher.launch(launcherIntent)
            }
        }
        //   R.id.action_settings -> true
        return super.onOptionsItemSelected(item)
    }


    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }



    private fun registerRefreshCallback() {
        refreshIntentLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult())
            { loadFlowers() }
    }

    private fun loadFlowers(){
        showFlowers(app.flowers.findAll())
    }

    fun showFlowers(flowers: List<FlowerModel>){
     //   binding.recyclerView.adapter = FlowerAdapter(flowers, this)
     //   binding.recyclerView.adapter?.notifyDataSetChanged()
    }
}

