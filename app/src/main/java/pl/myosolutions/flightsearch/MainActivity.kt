package pl.myosolutions.flightsearch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import pl.myosolutions.flightsearch.Constants.BaseUrl.getBaseUrl
import pl.myosolutions.flightsearch.ui.search.SearchFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d(MainActivity::javaClass.name, "${getBaseUrl()}")

        initializeFragment()
    }

    private fun initializeFragment() {
       replaceFragment(SearchFragment.newInstance(), SearchFragment.TAG)
    }

    fun replaceFragment(fragment: Fragment, tag: String) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment, tag).addToBackStack("").commit()
    }
}