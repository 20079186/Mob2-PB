package ie.wit.mob2_pb.activities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*

import androidx.fragment.app.Fragment
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import ie.wit.mob2_pb.R
import ie.wit.mob2_pb.databinding.FlowerActivityBinding
import ie.wit.mob2_pb.helpers.showImagePicker
import ie.wit.mob2_pb.main.MainApp
import ie.wit.mob2_pb.models.FlowerModel
import timber.log.Timber
import timber.log.Timber.i


class FlowerSActivity :  AppCompatActivity() {

    private lateinit var binding: FlowerActivityBinding
    private lateinit var imageIntentLauncher: ActivityResultLauncher<Intent>
    private lateinit var refreshIntentLauncher: ActivityResultLauncher<Intent>

    lateinit var app: MainApp
    var flower = FlowerModel()
    var edit = false



//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//
//
//        binding = FlowerActivityBinding.inflate(inflater, container, false)
//        return binding.root
//
//    }

    override fun onCreate( savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = FlowerActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        app = application as MainApp

        if (intent.hasExtra("flower_edit")) {
            edit = true
            flower = intent.extras?.getParcelable("flower_edit")!!
            binding.addFlowerName.setText(flower.name)
            binding.addFlowerSeason.setText(flower.season)
            binding.addFlowerCare.setText(flower.care)
            binding.addFlowerDescription.setText(flower.description)
            binding.addFlowerFamily.setText(flower.family)
            binding.buttonAddFlower.text = "Save Flower"
            Picasso.get()
                .load(flower.image)
                .into(binding.imageView)
            if (flower.image != Uri.EMPTY) {
           //     binding.imageButton.setText(R.string.change_flower_image)
            }
            }

        binding.imageButton.setOnClickListener {
            showImagePicker(imageIntentLauncher)
        }



//        binding.buttonClear.setOnClickListener {
//            val launcherIntent = Intent(this, MainActivity::class.java)
//            startActivityForResult(launcherIntent, 0)
//
//
//        }


        binding.buttonAddFlower.setOnClickListener {
            flower.name = binding.addFlowerName.text.toString()
            flower.family = binding.addFlowerFamily.text.toString()
            flower.season = binding.addFlowerSeason.text.toString()
            flower.description = binding.addFlowerDescription.text.toString()
            flower.care = binding.addFlowerCare.text.toString()
            if (flower.name.isEmpty()) {
                Snackbar.make(it, "Please enter the flower name", Snackbar.LENGTH_LONG).show()
            }
            if (flower.family.isEmpty()) {
                Snackbar.make(it, "Please enter the flower's family name", Snackbar.LENGTH_LONG)
                    .show()
            }
            if (flower.season.isEmpty()) {
                Snackbar.make(it, "Please enter the flower's season", Snackbar.LENGTH_LONG).show()
            }
            if (flower.description.isEmpty()) {
                Snackbar.make(it, "Please enter a description for the flower", Snackbar.LENGTH_LONG)
                    .show()
            }
            if (flower.care.isEmpty()) {
                Snackbar.make(it, "Please enter how to care for the flower", Snackbar.LENGTH_LONG)
                    .show()
            }
            if (edit) {
                app.flowers.update(flower.copy())
            } else {
                app.flowers.create(flower.copy())
            }


            setResult(RESULT_OK)
            finish()


        }
        registerImagePickerCallback()
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_delete -> {
                app.flowers.delete(flower)
                finish()
            }
            R.id.item_clear -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_flowers, menu)
        if (edit) menu.getItem(0).isVisible = true
        return super.onCreateOptionsMenu(menu)
    }


    private fun registerImagePickerCallback() {
        imageIntentLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult())
            { result ->
                when(result.resultCode){
                    RESULT_OK -> {
                        if (result.data != null) {
                            i("Got Result ${result.data!!.data}")
                            flower.image = result.data!!.data!!
                            Picasso.get()
                                .load(flower.image)
                                .into(binding.imageView)
                          // binding.imageButton.setText(R.string.change_flower_image)
                        } // end of if
                    }
                    RESULT_CANCELED -> { } else -> { }
                }
            }
    }
}

//    override fun onDestroyView() {
//        super.onDestroyView()
//        binding =
//    }







