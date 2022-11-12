package uz.icebergsoft.mobilenews.presentation.support.moxy

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel : ViewModel() {

    var compositeDisposable = CompositeDisposable()
        private set

    final fun clearViewModel() {
        compositeDisposable.dispose()
    }
}