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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ie.wit.mob2_pb.R
import ie.wit.mob2_pb.databinding.ActivityMainBinding
import ie.wit.mob2_pb.main.MainApp
import ie.wit.mob2_pb.models.FlowerModel
import ie.wit.mob2_pb.adapters.*


class MainActivity : AppCompatActivity(), FlowerListener{

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

        val layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager

        binding.recyclerView.adapter = FlowerAdapter(app.flowers.findAll(),this)


        binding.buttonFirst.setOnClickListener() {
            val launcherIntent = Intent(this, FlowerSActivity::class.java)
            startActivityForResult(launcherIntent,0)


        }

            binding.fab.setOnClickListener { view ->
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()




                loadFlowers()
                registerRefreshCallback()
            }

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

