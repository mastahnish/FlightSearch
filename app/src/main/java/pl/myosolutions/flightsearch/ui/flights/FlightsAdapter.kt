package pl.myosolutions.flightsearch.ui.flights

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.flight_list_item.view.*
import pl.myosolutions.flightsearch.Constants.DATE_TIME_FORMAT
import pl.myosolutions.flightsearch.Constants.DAY_MONTH_YEAR_FORMAT
import pl.myosolutions.flightsearch.Constants.PRICE_FORMAT
import pl.myosolutions.flightsearch.R
import pl.myosolutions.flightsearch.models.dto.flights.FlightListItem
import pl.myosolutions.flightsearch.ui.flights.FlightsAdapter.FlightsViewHolder
import java.text.ParseException
import java.text.SimpleDateFormat

interface FlightItemListener {
    fun onFlightClicked(flight: FlightListItem)
}

class FlightsAdapter(
    private val listener: FlightItemListener
) : RecyclerView.Adapter<FlightsViewHolder>(){

    private var flights: List<FlightListItem> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlightsViewHolder  =
        FlightsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.flight_list_item, parent, false))


    override fun getItemCount(): Int = flights.size

    override fun onBindViewHolder(holder: FlightsViewHolder, position: Int) {
        holder.bind(flights[position])
    }

    fun updateFlightsData(data: List<FlightListItem>) {
        this.flights = data
        notifyDataSetChanged()
    }

    inner class FlightsViewHolder(view: View) : RecyclerView.ViewHolder(view){
        private val flightDate: TextView = view.flightDate
        private val number: TextView = view.flightNumber
        private val flightDuration: TextView = view.flightDuration
        private val flightPrice: TextView = view.flightPrice
        private val itemLayout: View = view.itemLayout


        fun bind(flight: FlightListItem){
            flight.run {
                flightDate.text = try {
                    SimpleDateFormat(DAY_MONTH_YEAR_FORMAT).format(SimpleDateFormat(DATE_TIME_FORMAT).parse(dateOut))
                } catch (e: ParseException) {
                    dateOut
                }
                number.text = flightNumber
                flightDuration.text = duration
                flightPrice.text = String.format(PRICE_FORMAT, amount, currency)
            }

            itemLayout.setOnClickListener { listener.onFlightClicked(flight) }
        }
    }
}