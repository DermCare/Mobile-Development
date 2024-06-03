package aiw.mobile.testonboardingpage.onboarding.screens

import aiw.mobile.testonboardingpage.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class ThirdScreen : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_third_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val next = view.findViewById<AppCompatImageView>(R.id.imageView14)
        next?.setOnClickListener {
            /*val intent = Intent(requireContext(), LoginFragment::class.java)
            startActivity(intent)*/
            findNavController().navigate(R.id.action_viewPagerFragment_to_loginFragment)
        }

        val skip = view.findViewById<TextView>(R.id.textView)
        skip.setOnClickListener {
            findNavController().navigate(R.id.action_viewPagerFragment_to_loginFragment)
        }
    }

}