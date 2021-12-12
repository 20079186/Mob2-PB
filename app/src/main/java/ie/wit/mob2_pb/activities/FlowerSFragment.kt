package ie.wit.mob2_pb.activities

import android.content.Intent
import android.os.Bundle

import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation.setViewNavController
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import ie.wit.mob2_pb.R
import ie.wit.mob2_pb.databinding.FlowerActivityBinding
import ie.wit.mob2_pb.helpers.showImagePicker
import ie.wit.mob2_pb.main.MainApp
import ie.wit.mob2_pb.models.FlowerModel
import timber.log.Timber

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class FlowerSFragment :  Fragment() {

    private lateinit var binding: FlowerActivityBinding
    private lateinit var imageIntentLauncher : ActivityResultLauncher<Intent>
    val application = app
    lateinit var app: MainApp
    var flower = FlowerModel()
    var edit = false


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FlowerActivityBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FlowerActivityBinding.inflate(layoutInflater)
      //  setViewNavController(binding.root)

        app = application as MainApp

        if(intent.hasExtra("flower_edit"){
                edit = true
                flower = intent.extras?.getParcelable("flower_edit")!!
            binding.addFlowerName.setText(flower.name)
        }




        )


        binding.buttonClear.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }


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
                Snackbar.make(it, "Please enter the flower's family name", Snackbar.LENGTH_LONG).show()
            }
            if (flower.season.isEmpty()) {
                Snackbar.make(it, "Please enter the flower's season", Snackbar.LENGTH_LONG).show()
            }
            if (flower.description.isEmpty()) {
                Snackbar.make(it, "Please enter a description for the flower", Snackbar.LENGTH_LONG).show()
            }
            if (flower.care.isEmpty()) {
                Snackbar.make(it, "Please enter how to care for the flower", Snackbar.LENGTH_LONG).show()
            }
            else{
                if (edit) {
                    app.flowers
                }
            binding.imageButton.setOnClickListener {
                showImagePicker(imageIntentLauncher)
            }

        }
            registerImagePickerCallback()

        }
    }

//    override fun onDestroyView() {
//        super.onDestroyView()
//        binding =
//    }


    private fun registerImagePickerCallback() {
        imageIntentLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult())
            { result ->
                when(result.resultCode){
                    AppCompatActivity.RESULT_OK -> {
                        if (result.data != null) {
                            Timber.i("Got Result ${result.data!!.data}")
                            flower.image = result.data!!.data!!
                            Picasso.with(context)
                                .load(flower.image)
                                .into(binding.imageView)
                          //  binding.imageButton.setText(R.string.button_add_flower_photo)
                        } // end of if
                    }
                    AppCompatActivity.RESULT_CANCELED -> { } else -> { }
                }
            }
    }
}









