package uz.icebegsoft.mobilenews.presentation.presentation.home.features.recommended

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import ru.surfstudio.android.easyadapter.EasyAdapter
import ru.surfstudio.android.easyadapter.ItemList
import uz.icerbersoft.mobilenews.R
import uz.icerbersoft.mobilenews.databinding.FragmentRecommendedNewsBinding
import uz.icebegsoft.mobilenews.presentation.global.GlobalActivity
import uz.icebegsoft.mobilenews.presentation.presentation.home.features.recommended.controller.RecommendedArticleItemController
import uz.icebegsoft.mobilenews.presentation.presentation.home.features.recommended.di.RecommendedArticlesDaggerComponent
import uz.icebegsoft.mobilenews.presentation.support.controller.StateEmptyItemController
import uz.icebegsoft.mobilenews.presentation.support.controller.StateErrorItemController
import uz.icebegsoft.mobilenews.presentation.support.controller.StateLoadingItemController
import uz.icebegsoft.mobilenews.presentation.utils.LoadingState.*
import uz.icebegsoft.mobilenews.presentation.utils.addCallback
import uz.icebegsoft.mobilenews.presentation.utils.onBackPressedDispatcher
import javax.inject.Inject

internal class RecommendedArticlesFragment : Fragment(R.layout.fragment_recommended_news) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: RecommendedArticlesViewModel by viewModels { viewModelFactory }

    private lateinit var binding: FragmentRecommendedNewsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        RecommendedArticlesDaggerComponent
            .create((requireActivity() as GlobalActivity).globalDaggerComponent)
            .inject(this)

        super.onCreate(savedInstanceState)
        onBackPressedDispatcher.addCallback(this) { viewModel.back() }
        observeArticleList()
    }

    private val easyAdapter = EasyAdapter()
    private val articleController = RecommendedArticleItemController(
        itemClickListener = { viewModel.openArticleDetailScreen(it.articleId) },
        bookmarkListener = { viewModel.updateBookmark(it) }
    )
    private val stateLoadingController = StateLoadingItemController(true)
    private val stateEmptyItemController = StateEmptyItemController(true)
    private val stateErrorController =
        StateErrorItemController(true) { viewModel.getRecommendedArticles() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRecommendedNewsBinding.bind(view)

        with(binding) {
            recyclerView.adapter = easyAdapter
            recyclerView.itemAnimator = null
        }

        if (savedInstanceState == null)
            viewModel.getRecommendedArticles()
    }

    override fun onDestroy() {
        viewModel.clearViewModel()

        super.onDestroy()
    }

    private fun observeArticleList() {
        viewModel.articlesLiveData.observe(this) { state ->
            val itemList = ItemList.create()
            when (state) {
                is SuccessItem -> itemList.addAll(state.data, articleController)
                is EmptyItem -> itemList.add(stateEmptyItemController)
                is ErrorItem -> itemList.add(stateErrorController)
                is LoadingItem -> itemList.add(stateLoadingController)
            }
            easyAdapter.setItems(itemList)
        }
    }

    companion object {

        fun newInstance() =
            RecommendedArticlesFragment()
    }
}