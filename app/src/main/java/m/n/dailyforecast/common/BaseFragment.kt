package m.n.dailyforecast.common

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import com.google.android.material.snackbar.Snackbar
import m.n.dailyforecast.data.AppError
import m.n.dailyforecast.utils.ConnectionLiveData
import m.n.dailyforecast.utils.hideKeyBoard

abstract class BaseFragment<T : ViewBinding>(layoutId: Int) : Fragment(layoutId) {
    private var _binding: T? = null
    private var _networkLive: ConnectionLiveData? = null
    val networkLive: ConnectionLiveData
        get() {
            if (_networkLive == null) {
                _networkLive = (requireActivity() as BaseActivity).networkLive
            }
            return _networkLive!!
        }

    // This property is only valid between onCreateView and
    // onDestroyView.
    val binding get() = _binding!!

    /**
     *
     */
    abstract fun provideViewModelObserver(): Array<BaseViewModel>?

    /**
     *
     */
    abstract fun doBindView(view: View): T

    /**
     *
     */
    abstract fun setupViewEvent()

    /**
     *
     */
    abstract fun doListenObserver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        provideViewModelObserver()?.forEach { lifecycle.addObserver(it) }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = doBindView(view).apply {
            view.setOnClickListener {
                requireActivity().hideKeyBoard()
            }
        }
        setupViewEvent()
        doListenObserver()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun handleError(appError: AppError) {
        Snackbar.make(binding.root, appError.messageError, Snackbar.LENGTH_SHORT)
            .setAction("OK") { /*empty*/ }.show()
    }

}