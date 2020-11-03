package pl.myosolutions.flightsearch.ui.search

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.fragment_search.*
import pl.myosolutions.flightsearch.R
import org.koin.androidx.viewmodel.ext.android.viewModel
import pl.myosolutions.flightsearch.MainActivity
import pl.myosolutions.flightsearch.extensions.*
import pl.myosolutions.flightsearch.ui.flights.FlightsFragment

class SearchFragment : Fragment() {

    private val viewModel: SearchViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupUI()
    }

    private fun setupUI() {
        searchButton.setOnClickListener {
            viewModel.search(
               "2021-05-12",//etDate.text(),
                "POZ",//etOrigin.text(),
                "ATH", //etDestination.text(),
                1,//adultsValue.text().toInt(),
                0, //teensValue.text().toInt(),
                0//, childrenValue.text().toInt()
            ).subscribeBy(
                onSuccess = {
                    if(it.isSuccessful()){
                        Log.d(SearchFragment::class.java.name, "$it" )

                        //navigate to search results
                        (requireActivity() as MainActivity).replaceFragment(FlightsFragment.newInstance(), FlightsFragment.TAG)
                    }else{
                        Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                    }
                },
                onError = {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                }
            )
        }
    }

    companion object {
        const val TAG = "SearchFragment"
        fun newInstance() = SearchFragment()
    }
}