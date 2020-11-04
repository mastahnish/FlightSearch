package pl.myosolutions.flightsearch.ui.search

import android.app.DatePickerDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.TextView
import android.widget.Toast
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.fragment_search.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import pl.myosolutions.flightsearch.BaseFragment
import pl.myosolutions.flightsearch.Constants.DAY_MONTH_YEAR_FORMAT
import pl.myosolutions.flightsearch.MainActivity
import pl.myosolutions.flightsearch.R
import pl.myosolutions.flightsearch.extensions.observeLiveDataOnce
import pl.myosolutions.flightsearch.extensions.text
import pl.myosolutions.flightsearch.ui.flights.FlightsFragment
import java.text.SimpleDateFormat
import java.util.*


class SearchFragment : BaseFragment(), DatePickerDialog.OnDateSetListener {

    private val viewModel: SearchViewModel by viewModel()
    private val calendar : Calendar = Calendar.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        downloadAvailablePlaces()
        hideBackButton()
        (requireActivity() as MainActivity).updateToolbarToStandard()
        setupUI()
    }

    private fun downloadAvailablePlaces() {
        //TODO show splash

        viewModel.downloadAllPlaces().subscribeBy (
            onSuccess = {
                //TODO hide splash
                Log.d("Ryanair", "downloadAllPlaces success")
            },
            onError = {
                //Show error
                Log.d("Ryanair", "downloadAllPlaces error")
            }
        ).addTo(fragmentDisposables)
    }


    private fun setupUI() {
        etOrigin.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
               observeLiveDataOnce(viewModel.searchPlaces(s.toString())) {
                   Log.d("Ryanair", "$it")
               }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

        })

        etDestination.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                observeLiveDataOnce(viewModel.searchPlaces(s.toString())) {
                    Log.d("Ryanair", "$it")
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

        })

        etDate.setOnClickListener {
            DatePickerDialog(
                requireContext(), this, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

        adultsPlus.setOnClickListener {
            incrementValue(adultsValue)
        }

        adultsMinus.setOnClickListener {
            decrementValue(adultsValue)
        }

        teensPlus.setOnClickListener {
            incrementValue(teensValue)
        }

        teensMinus.setOnClickListener {
            decrementValue(teensValue)
        }

        childrenPlus.setOnClickListener {
            incrementValue(childrenValue)
        }

        childrenMinus.setOnClickListener {
            decrementValue(childrenValue)
        }


        searchButton.setOnClickListener {
            searchButton.isEnabled = false

            viewModel.search(
                etDate.text(),
                "POZ",//etOrigin.text(),
                "ATH", //etDestination.text(),
                adultsValue.text().toInt(),
                teensValue.text().toInt(),
                childrenValue.text().toInt()
            ).subscribeBy(
                onSuccess = {
                    if(it.isSuccessful()){
                        Log.d(SearchFragment::class.java.name, "$it" )

                        //navigate to search results
                        (requireActivity() as MainActivity).replaceFragment(FlightsFragment.newInstance(), FlightsFragment.TAG)
                    }else{
                        Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                    }
                    searchButton.isEnabled = true
                },
                onError = {
                    searchButton.isEnabled = true
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                }
            ).addTo(fragmentDisposables)
        }
    }

    private fun decrementValue(value: TextView) {
        value.text.toString().toInt().run {
            if (this == 0) return
            value.text = (this - 1).toString()
        }
    }

    private fun incrementValue(value: TextView) {
        value.text = (value.text.toString().toInt()+1).toString()
    }

    companion object {
        const val TAG = "SearchFragment"
        fun newInstance() = SearchFragment()
    }

    override fun onDateSet(datePicker: DatePicker?, year: Int, monthOfYear: Int, dayOfMonth: Int) {
         calendar.apply{
            set(Calendar.YEAR, year)
            set(Calendar.MONTH, monthOfYear)
            set(Calendar.DAY_OF_MONTH, dayOfMonth)
        }

        etDate.setText(SimpleDateFormat(DAY_MONTH_YEAR_FORMAT, Locale.getDefault()).format(calendar.time))
    }

}