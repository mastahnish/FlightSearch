package pl.myosolutions.flightsearch.ui.flightdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_flight_details.*
import pl.myosolutions.flightsearch.BaseFragment
import pl.myosolutions.flightsearch.Constants.DISCOUNT_FORMAT
import pl.myosolutions.flightsearch.MainActivity
import pl.myosolutions.flightsearch.R
import pl.myosolutions.flightsearch.models.dto.flights.FlightListItem


class FlightDetailsFragment : BaseFragment() {

    private lateinit var flightItem: FlightListItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let{
            flightItem = Gson().fromJson(it.getString(FLIGHT_ITEM), FlightListItem::class.java)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_flight_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showBackButton()
        (requireActivity() as MainActivity).updateToolbarToStandard()
        filterFlightDetails()
    }

    private fun filterFlightDetails() {
        flightItem.run {
            tvFlightNumber.text = flightNumber
            tvOrigin.text = origin
            tvDestination.text = destination
            tvDiscount.text = if (discountInPercent > 0) String.format(
                DISCOUNT_FORMAT,
                discountInPercent
            ) else context?.getString(
                R.string.no_discount
            )
            tvFareClass.text = String.format(getString(R.string.classFare), fareClass)
            tvInfants.text = infantsLeft.toString()
        }
    }

    companion object {
        const val TAG = "FlightDetailsFragment"
        private const val FLIGHT_ITEM = "flightItem"
        fun newInstance(flightItem: String) = FlightDetailsFragment().apply{
            arguments = Bundle().apply {
                putString(FLIGHT_ITEM, flightItem)
            }
        }
    }
}