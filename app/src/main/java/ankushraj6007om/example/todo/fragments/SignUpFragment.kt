package ankushraj6007om.example.todo.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import ankushraj6007om.example.todo.R
import ankushraj6007om.example.todo.databinding.FragmentSignUpBinding
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth

class SignUpFragment : Fragment() {
    private lateinit var auth: FirebaseAuth
    private lateinit var navController: NavController
    private lateinit var binding: FragmentSignUpBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSignUpBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init(view)
        registerEvents()
    }
    private fun init(view: View){
        navController = Navigation.findNavController(view)
        auth=FirebaseAuth.getInstance()
    }
    private fun registerEvents(){
        binding.textView.setOnClickListener{
            navController.navigate(R.id.action_signUpFragment_to_signInFragment)
        }


        binding.button.setOnClickListener {
            val email = binding.email.text.toString().trim()
            val password = binding.PassWord.text.toString().trim()
            val verifypassword = binding.ReTypePassWord.text.toString().trim()

            if (email.isNotEmpty() && password.isNotEmpty() && verifypassword.isNotEmpty()) {
                if (password == verifypassword) {
                    binding.progressBar.visibility=View.VISIBLE
                    auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(
                        OnCompleteListener {
                            if (it.isSuccessful) {
                                Toast.makeText(
                                    context,
                                    "Regestered Successsfully",
                                    Toast.LENGTH_SHORT
                                ).show()
                                navController.navigate(R.id.action_signUpFragment_to_homeFragment)

                            } else {
                                Toast.makeText(context, it.exception?.message, Toast.LENGTH_SHORT)
                                    .show()
                            }
                            binding.progressBar.visibility=View.GONE
                        })
                }
                else {
                    Toast.makeText(context,"PassWord doesn't match", Toast.LENGTH_SHORT)
                        .show()
                }
            }else {
                Toast.makeText(context,"Empty fields not allowed", Toast.LENGTH_SHORT)
                    .show()
            }
        }}}