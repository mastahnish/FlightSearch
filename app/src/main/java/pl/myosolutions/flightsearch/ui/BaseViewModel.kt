package pl.myosolutions.flightsearch.ui

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable


open class BaseViewModel : ViewModel() {
    protected val viewModelDisposables = CompositeDisposable()

    override fun onCleared() {
        viewModelDisposables.clear()
        super.onCleared()
    }

}
