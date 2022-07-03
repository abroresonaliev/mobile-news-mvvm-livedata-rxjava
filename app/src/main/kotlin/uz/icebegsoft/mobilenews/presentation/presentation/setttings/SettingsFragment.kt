package uz.icebegsoft.mobilenews.presentation.presentation.setttings

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import ru.surfstudio.android.easyadapter.EasyAdapter
import ru.surfstudio.android.easyadapter.ItemList
import uz.icebegsoft.mobilenews.presentation.global.GlobalActivity
import uz.icebegsoft.mobilenews.presentation.presentation.setttings.controller.DayNightModeItemController
import uz.icebegsoft.mobilenews.presentation.presentation.setttings.di.SettingsDaggerComponent
import uz.icebegsoft.mobilenews.presentation.support.controller.StateEmptyItemController
import uz.icebegsoft.mobilenews.presentation.support.controller.StateErrorItemController
import uz.icebegsoft.mobilenews.presentation.support.controller.StateLoadingItemController
import uz.icebegsoft.mobilenews.presentation.support.event.LoadingListEvent.*
import uz.icebegsoft.mobilenews.presentation.utils.addCallback
import uz.icebegsoft.mobilenews.presentation.utils.convertToAppDelegateModeNight
import uz.icerbersoft.mobilenews.R
import uz.icerbersoft.mobilenews.databinding.FragmentSettingsBinding
import javax.inject.Inject

internal class SettingsFragment : Fragment(R.layout.fragment_settings) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: SettingsViewModel by viewModels { viewModelFactory }

    private lateinit var binding: FragmentSettingsBinding

    private val easyAdapter = EasyAdapter()
    private val dayNightModeController = DayNightModeItemController {
        viewModel.saveDayNightMode(it)

        (requireActivity() as GlobalActivity)
            .updateNightMode(it.dayNightMode.convertToAppDelegateModeNight())
    }
    private val stateLoadingController = StateLoadingItemController(isFullScreen = true)
    private val stateEmptyController = StateEmptyItemController(isFullScreen = true)
    private val stateErrorController = StateErrorItemController(isFullScreen = true) {}

    override fun onCreate(savedInstanceState: Bundle?) {
        SettingsDaggerComponent
            .create((requireActivity() as GlobalActivity).globalDaggerComponent)
            .inject(this)

        super.onCreate(savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(this) { viewModel.back() }

        observeLiveData()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSettingsBinding.bind(view)

        with(binding) {
            backIv.setOnClickListener { requireActivity().onBackPressedDispatcher.onBackPressed() }
            settingsRv.adapter = easyAdapter
            settingsRv.itemAnimator = null
        }

        viewModel.getAvailableSettings()
    }

    private fun observeLiveData() {
        viewModel.dayNightModesLiveData.observe(this) { event ->
            val itemList = ItemList.create()
            when (event) {
                is LoadingState -> itemList.add(stateLoadingController)
                is SuccessState -> itemList.addAll(event.data, dayNightModeController)
                is EmptyState -> itemList.add(stateEmptyController)
                is ErrorState -> itemList.add(stateErrorController)
            }
            easyAdapter.setItems(itemList)
        }
    }

    companion object {

        fun newInstance() =
            SettingsFragment()
    }
}