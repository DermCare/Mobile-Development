package aiw.mobile.testonboardingpage.onboarding

import aiw.mobile.testonboardingpage.R
import aiw.mobile.testonboardingpage.onboarding.screens.FirstScreen
import aiw.mobile.testonboardingpage.onboarding.screens.SecondScreen
import aiw.mobile.testonboardingpage.onboarding.screens.ThirdScreen
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2


class ViewPagerFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

       val view = inflater.inflate(R.layout.fragment_view_pager, container, false)

        val fragmentList = arrayListOf<Fragment>(
            FirstScreen(),
            SecondScreen(),
            ThirdScreen()
        )

        val adapter = ViewPagerAdapter(
            fragmentList,
            requireActivity().supportFragmentManager,
            lifecycle
        )

        val viewPager = view.findViewById<ViewPager2>(R.id.viewPagerr)
        viewPager.adapter = adapter

        return view
    }

}

