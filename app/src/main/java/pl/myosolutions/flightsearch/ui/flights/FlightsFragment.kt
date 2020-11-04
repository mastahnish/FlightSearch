package pl.myosolutions.flightsearch.ui.flights

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_flights.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import pl.myosolutions.flightsearch.BaseFragment
import pl.myosolutions.flightsearch.Constants.FILTERED_PRICE_FORMAT
import pl.myosolutions.flightsearch.Constants.SLIDER_FILTER_MAX
import pl.myosolutions.flightsearch.Constants.SLIDER_FILTER_MIN
import pl.myosolutions.flightsearch.MainActivity
import pl.myosolutions.flightsearch.R
import pl.myosolutions.flightsearch.extensions.*
import pl.myosolutions.flightsearch.models.dto.flights.FlightListItem
import pl.myosolutions.flightsearch.ui.flightdetails.FlightDetailsFragment


class FlightsFragment : BaseFragment(), FlightItemListener, SeekBar.OnSeekBarChangeListener {

    private val viewModel: FlightsViewModel by viewModel()
    private lateinit var allFlightsFromSearch: List<FlightListItem>
    private lateinit var currency: String

    private val flightsAdapter: FlightsAdapter by lazy {
        FlightsAdapter(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_flights, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showBackButton()
        setupUI()
        observeFlights()
    }

    private fun setupUI() {
        flightsList.run{
            layoutManager = LinearLayoutManager(requireContext())
            adapter = flightsAdapter
        }
        slider.run{
            setOnSeekBarChangeListener(this@FlightsFragment)
        }
    }

    private fun observeFlights() {
        observeLiveDataOnce(viewModel.getFlightSearch()) { flightSearch ->

               val searchedFlights = flightSearch.trips.flatMap { flightTrip ->
                    updateToolbar(flightTrip.originName, flightTrip.destinationName)
                    updateFilters(flightSearch.currency)

                    flightTrip.dates.flatMap { flightDate ->
                        flightDate.flights.map { flight ->
                            FlightListItem(
                                flightDate.dateOut,
                                flight.flightNumber,
                                flight.duration,
                                flight.regularFare.fares.first().amount,
                                flightSearch.currency,
                                flightTrip.origin,
                                flightTrip.destination,
                                flight.infantsLeft,
                                flight.regularFare.fareClass,
                                flight.regularFare.fares.first().discountInPercent
                            )
                        }
                    }
                }

            flightsAdapter.updateFlightsData(searchedFlights)
            allFlightsFromSearch = searchedFlights
        }
    }

    private fun updateFilters(currentCurrency: String) {
        currency = currentCurrency
        minValue.text = String.format(SLIDER_FILTER_MIN, currency)
        maxValue.text = String.format(SLIDER_FILTER_MAX, currency)
        filteredValue.text = String.format(FILTERED_PRICE_FORMAT, slider.progress, currency)
    }

    private fun updateToolbar(originName: String, destinationName: String) {
        (requireActivity() as MainActivity).updateToolbar(originName, destinationName)
    }

    companion object {
        const val TAG = "FlightsFragment"
        fun newInstance() = FlightsFragment()
    }

    override fun onFlightClicked(flight: FlightListItem) {
        (requireActivity() as MainActivity).replaceFragment(
            FlightDetailsFragment.newInstance(
                Gson().toJson(
                    flight,
                    FlightListItem::class.java
                )
            ), FlightDetailsFragment.TAG
        )
    }

    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
        if(fromUser) {
            filteredValue.text = String.format(FILTERED_PRICE_FORMAT, progress, currency)
            flightsAdapter.updateFlightsData(allFlightsFromSearch.filter { it.amount.toInt() > progress })
        }
    }

    override fun onStartTrackingTouch(p0: SeekBar?) {}

    override fun onStopTrackingTouch(p0: SeekBar?) {}
}