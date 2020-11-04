package pl.myosolutions.flightsearch

import androidx.fragment.app.Fragment
import io.reactivex.disposables.CompositeDisposable

abstract class BaseFragment : Fragment() {

    val fragmentDisposables = CompositeDisposable()

    override fun onDestroy() {
        fragmentDisposables.clear()
        super.onDestroy()
    }

    protected fun showBackButton() {
        (activity as MainActivity).showBackButton()
    }

    protected fun hideBackButton() {
        (activity as MainActivity).hideBackButton()
    }

}