package ie.wit.mob2_pb.activities.pages

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import ie.wit.mob2_pb.R
import ie.wit.mob2_pb.activities.MainActivity

import ie.wit.mob2_pb.databinding.ActivityFeedbackBinding


class FeedbackActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFeedbackBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feedback)


        binding = ActivityFeedbackBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val button2 = binding.button2

        button2.setOnClickListener {
            val launcherIntent = Intent(this, MainActivity::class.java)
            startActivityForResult(launcherIntent,0)
                Toast.makeText(
                    this,
                    "Feedback submitted!",
                    Toast.LENGTH_SHORT
                ).show()
            }

        }

    }



