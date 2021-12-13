package ie.wit.mob2_pb.activities

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import ie.wit.mob2_pb.R
import ie.wit.mob2_pb.databinding.UserHomeBinding


class UserHomeFActivity : AppCompatActivity() {

    private var _binding: UserHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//
//        _binding = UserHomeBinding.inflate(inflater, container, false)
//        return binding.root
//
//    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        binding.buttonFirst.setOnClickListener {
//            setContentView(R.layout.flower_activity)
//        }
//    }
}
