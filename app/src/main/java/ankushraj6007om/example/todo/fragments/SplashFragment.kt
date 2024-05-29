package ankushraj6007om.example.todo.fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import ankushraj6007om.example.todo.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.Transaction

class SplashFragment : Fragment() {
    private lateinit var auth: FirebaseAuth
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController=Navigation.findNavController(view)
        auth = FirebaseAuth.getInstance()
        Handler(Looper.myLooper()!!).postDelayed(Runnable {
            if(auth.currentUser!=null){
                navController.navigate(R.id.action_splashFragment_to_homeFragment)

            }else{
                navController.navigate(R.id.action_splashFragment_to_signInFragment)

            }

        },2000)
    }
}