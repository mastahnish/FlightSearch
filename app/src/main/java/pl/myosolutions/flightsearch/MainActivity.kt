package pl.myosolutions.flightsearch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.view.*
import pl.myosolutions.flightsearch.Constants.BaseUrl.getBaseUrl
import pl.myosolutions.flightsearch.extensions.goneAnimated
import pl.myosolutions.flightsearch.extensions.visibleAnimated
import pl.myosolutions.flightsearch.ui.search.SearchFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setToolbar()
        initializeFragment()
    }

    private fun initializeFragment() {
       replaceFragment(SearchFragment.newInstance(), SearchFragment.TAG)
    }

    fun replaceFragment(fragment: Fragment, tag: String) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment, tag).addToBackStack("").commit()
    }

    fun showBackButton() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    fun hideBackButton() {
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
    }

    private fun setToolbar() {
        setSupportActionBar(findViewById(R.id.toolbar))

        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }

    fun updateToolbar(originName: String, destinationName: String) {
        customToolbar.apply{
            toolbarLogo.goneAnimated {
                toolbarTitle.run{
                    this.text = originName + Constants.DASH + destinationName
                    this.visibleAnimated()
                }
            }
        }
    }

    fun updateToolbarToStandard(){
        customToolbar.apply{
            toolbarTitle.goneAnimated {
                toolbarLogo.visibleAnimated()
            }
        }
    }


}