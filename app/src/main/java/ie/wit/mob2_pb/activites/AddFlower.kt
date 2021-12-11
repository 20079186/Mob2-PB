package ie.wit.mob2_pb.activites

import android.os.Bundle

import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import ie.wit.mob2_pb.R
import ie.wit.mob2_pb.databinding.AddFlowerBinding
import ie.wit.mob2_pb.models.FlowerModel

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class AddFlower : Fragment() {

    private var _binding: AddFlowerBinding? = null
    var flower = FlowerModel()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = AddFlowerBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
                Snackbar.make(it, "Please enter the flower's family name", Snackbar.LENGTH_LONG)
                    .show()
            }
            if (flower.season.isEmpty()) {
                Snackbar.make(it, "Please enter the flower's season", Snackbar.LENGTH_LONG)
                    .show()
            }
            if (flower.description.isEmpty()) {
                Snackbar.make(
                    it,
                    "Please enter a description for the flower",
                    Snackbar.LENGTH_LONG
                ).show()
            }
            if (flower.care.isEmpty()) {
                Snackbar.make(it, "Please enter how to care for the flower", Snackbar.LENGTH_LONG)
                    .show()
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}