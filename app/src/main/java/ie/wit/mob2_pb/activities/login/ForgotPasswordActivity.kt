package ie.wit.mob2_pb.activities.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import ie.wit.mob2_pb.R
import ie.wit.mob2_pb.databinding.ForgotPasswordActivityBinding



private lateinit var binding: ForgotPasswordActivityBinding


class ForgotPasswordActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.forgot_password_activity)

        binding = ForgotPasswordActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val submit = binding.submit
        val emailId = binding.emailId


        submit.setOnClickListener {
            val email: String = emailId.text.toString().trim { it <= ' '}
            if (email.isEmpty()){
                Toast.makeText(
                    this,
                    "Please enter email address.",
                    Toast.LENGTH_SHORT
                ).show()
            }
            else{
                FirebaseAuth.getInstance().sendPasswordResetEmail(email)
                    .addOnCompleteListener{task ->
                        if(task.isSuccessful){
                            Toast.makeText(
                                this,
                                "Email sent successfully to reset your password!",
                                Toast.LENGTH_LONG
                            ).show()
                            finish()
                        }else{
                            Toast.makeText(
                                this,
                                task.exception!!.message.toString(),
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
            }
        }

    }
}