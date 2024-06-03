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
import androidx.viewpager2.widget.ViewPager2

class SecondScreen : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val next = view.findViewById<AppCompatImageView>(R.id.imageView14)
        next.setOnClickListener {
            val viewPager = activity?.findViewById<ViewPager2>(R.id.viewPagerr)
            viewPager?.currentItem = 2 // Pindah ke ThirdScreen
        }

        val skip = view.findViewById<TextView>(R.id.textView)
        skip.setOnClickListener {
            findNavController().navigate(R.id.action_viewPagerFragment_to_loginFragment)
        }
    }

}