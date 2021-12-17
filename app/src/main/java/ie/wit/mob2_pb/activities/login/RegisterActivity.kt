package ie.wit.mob2_pb.activities.login
//https://youtu.be/8I5gCLaS25w
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
//import kotlinx.android.synthetic.main.register_activity*
import ie.wit.mob2_pb.R
import ie.wit.mob2_pb.activities.MainActivity

import ie.wit.mob2_pb.databinding.RegisterActivityBinding

class RegisterActivity: AppCompatActivity() {


    private lateinit var binding: RegisterActivityBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register_activity)

        binding = RegisterActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val register = binding.btnRegister
        val remail = binding.registerUsername
        val rpassword = binding.registerPassword
        val login = binding.accountAlreadyLogin


        login?.setOnClickListener{
            startActivity(Intent(this, LoginActivity::class.java))
        }


//Checking to make sure input has been placed into the text boxes, trims any spaces from input

        register.setOnClickListener{
            when{
                TextUtils.isEmpty(remail.text.toString().trim { it <= ' '}) -> {
                    Toast.makeText(
                        this@RegisterActivity,
                        "Please enter email.",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                TextUtils.isEmpty(rpassword.text.toString().trim{ it <= ' '}) ->{
                    Toast.makeText(
                        this@RegisterActivity,
                        "Please enter password",
                        Toast.LENGTH_SHORT
                    ).show()

                }
                else -> {
                    val email: String = remail.text.toString().trim { it <= ' '}
                    val password: String = rpassword.text.toString().trim { it <= ' '}

//using firebase auth class, create an instance with firebase and will create a new user with a new email and password
                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(
                            OnCompleteListener<AuthResult> {task ->

                                if(task.isSuccessful) {

                                    val firebaseUser: FirebaseUser = task.result!!.user!!

                                    Toast.makeText(
                                        this@RegisterActivity,
                                        "You have registered successfully!",
                                        Toast.LENGTH_SHORT
                                    ).show()

//after successful register, it will sign the user in and will send user to the main activity, rids of extra instances in the stack if needed if loaded from login to register to login
                                    val intent =
                                        Intent(this@RegisterActivity, MainActivity::class.java)
                                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                    intent.putExtra("user_id", firebaseUser.uid) //sends the email and password through intent
                                    intent.putExtra("email_id", email)
                                    startActivity(intent)
                                    finish()
                                }
                                else{
//show exception if not successful
                                    Toast.makeText(
                                        this@RegisterActivity,
                                        task.exception!!.message.toString(),
                                        Toast.LENGTH_SHORT
                                    ).show()


                                }
                            }
                        )
                }
            }
        }
    }


}