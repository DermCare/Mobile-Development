package aiw.mobile.view.login

import aiw.mobile.testonboardingpage.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tvRegister = view.findViewById<TextView>(R.id.tvRegister)
        tvRegister.setOnClickListener{
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }

        val ivLoginOrRegister = view.findViewById<AppCompatButton>(R.id.customViewLoginButton)
        ivLoginOrRegister.setOnClickListener{
            findNavController().navigate(R.id.action_loginFragment_to_bottomActivity)
        }

    }

}