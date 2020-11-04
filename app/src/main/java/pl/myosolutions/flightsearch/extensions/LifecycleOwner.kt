package pl.myosolutions.flightsearch.extensions

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

inline fun <T> LifecycleOwner.observeLiveData(data: LiveData<T>, crossinline onChanged: (T) -> Unit) {
    data.observe(this, Observer {
        it?.let { value -> onChanged(value) }
    })
}

inline fun <T> LifecycleOwner.observeLiveDataOnce(data: LiveData<T>, crossinline onChanged: (T) -> Unit) {
    data.observe(this, object : Observer<T> {
        override fun onChanged(value: T?) {
            value?.let { onChanged.invoke(it) }
            data.removeObserver(this)
        }
    })
}
