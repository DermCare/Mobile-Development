package aiw.mobile.view.dashboard

import aiw.mobile.testonboardingpage.R
import aiw.mobile.testonboardingpage.databinding.ActivityBottomFragmentBinding
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class BottomActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBottomFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBottomFragmentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.hide()

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_bottom_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_dashboard, R.id.navigation_camera, R.id.navigation_profile
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        // Add OnDestinationChangedListener to NavController
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.navigation_camera -> hideBottomNavigation()
                else -> showBottomNavigation()
            }
        }
    }

    private fun hideBottomNavigation() {
        binding.navView.visibility = View.GONE
    }

    private fun showBottomNavigation() {
        binding.navView.visibility = View.VISIBLE
    }
}
