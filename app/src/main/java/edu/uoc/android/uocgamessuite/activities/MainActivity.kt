package edu.uoc.android.uocgamessuite.activities

import android.os.Bundle
import android.os.StrictMode
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import edu.uoc.android.uocgamessuite.R
import edu.uoc.android.uocgamessuite.databinding.ActivityMainBinding
import edu.uoc.android.uocgamessuite.ui.calendar.CalendarFragment
import edu.uoc.android.uocgamessuite.ui.lists.ListsFragment
import edu.uoc.android.uocgamessuite.ui.search.SearchFragment
import edu.uoc.android.uocgamessuite.ui.settings.SettingsFragment
import edu.uoc.android.uocgamessuite.ui.trending.TrendingFragment

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration

    private lateinit var binding: ActivityMainBinding

    /**
     * Function launched when the activity is started
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Set thread policy to permit http calls
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set navigation listeners
        binding.navView.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.navigation_search -> {
                    supportFragmentManager.popBackStack(
                        null,
                        FragmentManager.POP_BACK_STACK_INCLUSIVE
                    )
                    openFragment(SearchFragment())
                    true
                }
                R.id.navigation_trending -> {
                    supportFragmentManager.popBackStack(
                        null,
                        FragmentManager.POP_BACK_STACK_INCLUSIVE
                    )
                    openFragment(TrendingFragment())
                    true
                }
                R.id.navigation_lists -> {
                    supportFragmentManager.popBackStack(
                        null,
                        FragmentManager.POP_BACK_STACK_INCLUSIVE
                    )
                    openFragment(ListsFragment())
                    true
                }
                R.id.navigation_calendar -> {
                    supportFragmentManager.popBackStack(
                        null,
                        FragmentManager.POP_BACK_STACK_INCLUSIVE
                    )
                    openFragment(CalendarFragment())
                    true
                }
                R.id.navigation_settings -> {
                    supportFragmentManager.popBackStack(
                        null,
                        FragmentManager.POP_BACK_STACK_INCLUSIVE
                    )
                    openFragment(SettingsFragment())
                    true
                }
                else -> false
            }
        }
        // Set lists view selected in nav bar by default
        binding.navView.selectedItemId = R.id.navigation_lists
    }

    /**
     * Replaces the content with the selected fragment
     */
    private fun openFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.nav_host_fragment_activity_main, fragment)
            .addToBackStack(null)
            .commit()
    }

    /**
     * Closes the app when the user press the back button from one of the main fragments
     */
    override fun onBackPressed() {
        supportFragmentManager.fragments.lastOrNull()?.let { currentFragment ->
            if (currentFragment is SearchFragment || currentFragment is TrendingFragment || currentFragment is ListsFragment
                || currentFragment is CalendarFragment || currentFragment is SettingsFragment) {
                finish()
            }
        }
        super.onBackPressed()
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}