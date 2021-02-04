package m.n.dailyforecast.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import m.n.dailyforecast.R
import m.n.dailyforecast.common.BaseFragment
import m.n.dailyforecast.common.BaseViewModel
import m.n.dailyforecast.data.CityLocation
import m.n.dailyforecast.data.ResultViewState
import m.n.dailyforecast.databinding.FragmentHomeBinding

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    private val homeViewModel: HomeViewModel by viewModels()
    private val adapterCities = AdapterCities()

    override fun provideViewModelObserver(): Array<BaseViewModel> = arrayOf(homeViewModel)

    override fun doBindView(view: View): FragmentHomeBinding = FragmentHomeBinding.bind(view)

    override fun setupViewEvent() {
        setupRecyclerCountry()
        binding.searchBox.searchBtn.setOnClickListener {
            homeViewModel.queryLocation(binding.searchBox.searchInput.text?.toString() ?: "")
        }
    }

    private fun setupRecyclerCountry() {
        binding.rvResult.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        binding.rvResult.adapter = adapterCities
        adapterCities.clickHandler = object : SearchItemClicked {
            override fun invoke(viwe: View, data: CityLocation) {
                val agr = Bundle().apply {
                    putDouble("lat", data.geometry.lat)
                    putDouble("lon", data.geometry.lng)
                    putString("name", data.components.city ?: data.formatted)
                }
                findNavController().navigate(R.id.detailFragment, agr)
            }
        }
    }

    override fun doListenObserver() {
        homeViewModel.listCityLive.observe(viewLifecycleOwner, {
            when (it) {
                is ResultViewState.Success -> {
                    binding.circleProgress.hide()
                    adapterCities.submitList(it.data)
                }
                is ResultViewState.Error -> {
                    binding.circleProgress.hide()
                    handleError(it.exception)
                }
                is ResultViewState.Loading -> {
                    binding.circleProgress.show()
                }
            }

        })
        networkLive.observe(viewLifecycleOwner) {
            if (it) homeViewModel.retryQuery()
        }
    }
}