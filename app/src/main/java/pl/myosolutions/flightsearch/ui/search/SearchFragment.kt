package pl.myosolutions.flightsearch.ui.search

import android.app.DatePickerDialog
import android.os.Bundle
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.DatePicker
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.fragment_search.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import pl.myosolutions.flightsearch.BaseFragment
import pl.myosolutions.flightsearch.Constants.DAY_MONTH_YEAR_FORMAT
import pl.myosolutions.flightsearch.Constants.EMPTY_STRING
import pl.myosolutions.flightsearch.MainActivity
import pl.myosolutions.flightsearch.R
import pl.myosolutions.flightsearch.extensions.*
import pl.myosolutions.flightsearch.models.entities.PlaceEntity
import pl.myosolutions.flightsearch.ui.flights.FlightsFragment
import java.text.SimpleDateFormat
import java.util.*
import kotlin.NoSuchElementException


class SearchFragment : BaseFragment(), DatePickerDialog.OnDateSetListener {

    private val viewModel: SearchViewModel by viewModel()
    private val calendar: Calendar = Calendar.getInstance()
    private var userOrigin: PlaceEntity? = null
    private var userDestination: PlaceEntity? = null

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
        observeLiveDataOnce(viewModel.getAllPlaces()){
            if(it.isNullOrEmpty()){
                progress_overlay.visible()
                viewModel.downloadAllPlaces().subscribeBy(
                    onSuccess = {
                        progress_overlay.gone()
                    },
                    onError = {error ->
                        progress_overlay.gone()
                        Toast.makeText(context, String.format(getString(R.string.error_during_data_load), error.message), Toast.LENGTH_LONG).show()
                    }
                ).addTo(fragmentDisposables)
            }
        }
    }

    private fun setupUI() {
        etOrigin.run {
            onlyUppercase()
            addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(enteredText: Editable?) {

                    try {
                        observeLiveDataOnce(viewModel.searchPlaces(enteredText.toString())) {
                            this@run.apply {
                                if (enteredText.isNullOrBlank()) {
                                    hint = getString(R.string.origin)
                                    tvOriginHint.text = EMPTY_STRING
                                    userOrigin = null
                                }else {
                                    if (it.isNotEmpty()) {
                                        userOrigin = it.first()
                                        tvOriginHint.text = userOrigin?.name
                                    } else {
                                        error = context.getString(R.string.empty_place)
                                        tvOriginHint.text = EMPTY_STRING
                                    }
                                }

                            }
                        }
                    } catch (exception: NoSuchElementException) {
                        Log.d(TAG, "${exception.message}")
                    }

                }

                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            })
            setOnEditorActionListener { _, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_NEXT) setPlace(etOrigin, userOrigin)
                return@setOnEditorActionListener false
            }
            onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus -> if (!hasFocus)  setPlace(etOrigin, userOrigin) }
        }

        etDestination.run {
            onlyUppercase()
            addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(enteredText: Editable?) {

                    try {
                        observeLiveDataOnce(viewModel.searchPlaces(enteredText.toString())) {
                            this@run.apply {
                                if (enteredText.isNullOrBlank()) {
                                    hint = getString(R.string.destination)
                                    tvDestinationHint.text = EMPTY_STRING
                                    userDestination = null
                                }else {
                                    if (it.isNotEmpty()) {
                                        userDestination = it.first()
                                        tvDestinationHint.text = userDestination?.name
                                    } else {
                                        error = context.getString(R.string.empty_place)
                                        tvDestinationHint.text = EMPTY_STRING
                                    }
                                }
                            }
                        }
                    } catch (exception: NoSuchElementException) {
                        Log.d(TAG, "${exception.message}")
                    }

                }

                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            })
            setOnEditorActionListener { _, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_NEXT) setPlace(etDestination, userDestination)
                return@setOnEditorActionListener false
            }
            onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus -> if (!hasFocus) setPlace(etDestination, userDestination) }
        }

        etDate.run {
            inputType = InputType.TYPE_NULL
            setOnClickListener {
                DatePickerDialog(
                    requireContext(),
                    this@SearchFragment,
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)
                ).show()
            }
        }

        adultsPlus.setOnClickListener {
            incrementValue(adultsValue)
            searchButton.isEnabled = areFieldsValid()
        }

        adultsMinus.setOnClickListener {
            decrementValue(adultsValue)
            searchButton.isEnabled = areFieldsValid()
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
            if(!areFieldsValid()){
                return@setOnClickListener
            }

            searchButton.isEnabled = false
            progress_overlay.visible()
            
            viewModel.search(
                etDate.text(),
                userOrigin!!.code,
                userDestination!!.code,
                adultsValue.text().toInt(),
                teensValue.text().toInt(),
                childrenValue.text().toInt()
            ).subscribeBy(
                onSuccess = {
                    if (it.isSuccessful()) {
                        (requireActivity() as MainActivity).replaceFragment(
                            FlightsFragment.newInstance(),
                            FlightsFragment.TAG
                        )
                    } else {
                        searchButton.isEnabled = true
                        Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                    }
                },
                onError = {
                    progress_overlay.gone()
                    searchButton.isEnabled = true
                    Toast.makeText(requireContext(), getString(R.string.no_such_connection), Toast.LENGTH_SHORT).show()
                }
            ).addTo(fragmentDisposables)
        }
    }

    private fun areFieldsValid(): Boolean = userOrigin!=null && userDestination!=null && etDate.text.isNotEmpty() && adultsValue.text.toString().toInt() > 0

    private fun setPlace(field: EditText, place: PlaceEntity?) {
        field.run {
            setText(place?.name ?: EMPTY_STRING)
            error = null
        }

       searchButton.isEnabled = areFieldsValid()
    }

    private fun decrementValue(value: TextView) {
        value.text.toString().toInt().run {
            if (this == 0) return
            value.text = (this - 1).toString()
        }
    }

    private fun incrementValue(value: TextView) {
        value.text = (value.text.toString().toInt() + 1).toString()
    }

    companion object {
        const val TAG = "SearchFragment"
        fun newInstance() = SearchFragment()
    }

    override fun onDateSet(datePicker: DatePicker?, year: Int, monthOfYear: Int, dayOfMonth: Int) {
        calendar.apply {
            set(Calendar.YEAR, year)
            set(Calendar.MONTH, monthOfYear)
            set(Calendar.DAY_OF_MONTH, dayOfMonth)
        }

        etDate.setText(
            SimpleDateFormat(
                DAY_MONTH_YEAR_FORMAT,
                Locale.getDefault()
            ).format(calendar.time)
        )

        searchButton.isEnabled = areFieldsValid()
    }

}