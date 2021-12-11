package ie.wit.mob2_pb.activites

import android.os.Bundle

import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import ie.wit.mob2_pb.R
import ie.wit.mob2_pb.databinding.AddFlowerBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class AddFlower : Fragment() {

    private var _binding: AddFlowerBinding? = null

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
            val addFlower = binding.addFlowerName.text.toString()
            if(addFlower.isNotEmpty()) {
                ("")
            }    }







    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}