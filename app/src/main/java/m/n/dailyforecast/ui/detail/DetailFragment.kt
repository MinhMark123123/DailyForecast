package m.n.dailyforecast.ui.detail

import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import m.n.dailyforecast.R
import m.n.dailyforecast.common.BaseFragment
import m.n.dailyforecast.common.BaseViewModel
import m.n.dailyforecast.data.ResultViewState
import m.n.dailyforecast.databinding.FragmentDetailBinding
import m.n.dailyforecast.utils.SpacesItemDecoration

@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentDetailBinding>(R.layout.fragment_detail) {

    private val detailViewModel: DetailViewModel by viewModels()
    private var nameTitle: String? = null
    private val adapter: AdapterForecast = AdapterForecast()
    override fun provideViewModelObserver(): Array<BaseViewModel> = arrayOf(detailViewModel)

    override fun doBindView(view: View): FragmentDetailBinding = FragmentDetailBinding.bind(view)

    override fun setupViewEvent() {
        loadArg()
        setupRecycler()
        binding.closeIcon.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun loadArg() {
        arguments?.let {
            nameTitle = it.getString("name")
            detailViewModel.lat = it.getDouble("lat")
            detailViewModel.lon = it.getDouble("lon")
        }
        nameTitle?.let {
            binding.title.text = it
        }
    }

    private fun setupRecycler() {
        binding.rvForecast.layoutManager =
            GridLayoutManager(requireContext(), 2, RecyclerView.VERTICAL, false)
        binding.rvForecast.setHasFixedSize(true)
        binding.rvForecast.addItemDecoration(SpacesItemDecoration(16))
        binding.rvForecast.adapter = adapter
    }


    override fun doListenObserver() {
        networkLive.observe(viewLifecycleOwner){
            if (it) detailViewModel.retry()
        }
        detailViewModel.listDailyLive.observe(viewLifecycleOwner) {
            when (it) {
                is ResultViewState.Success -> {
                    binding.circleProgress.hide()
                    adapter.submitList(it.data)
                }
                is ResultViewState.Error -> {
                    binding.circleProgress.hide()
                    handleError(it.exception)
                }
                is ResultViewState.Loading -> {
                    binding.circleProgress.show()
                }
            }
        }
    }
}