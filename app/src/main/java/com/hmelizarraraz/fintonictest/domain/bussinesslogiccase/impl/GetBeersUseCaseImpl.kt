package com.hmelizarraraz.fintonictest.domain.bussinesslogiccase.impl

import com.hmelizarraraz.fintonictest.data.database.entities.BeerEntity
import com.hmelizarraraz.fintonictest.data.manager.DataManager
import com.hmelizarraraz.fintonictest.data.models.AppError
import com.hmelizarraraz.fintonictest.data.models.presentation.BeerUIModel
import com.hmelizarraraz.fintonictest.domain.bussinesslogiccase.GetBeersUseCase
import com.hmelizarraraz.fintonictest.domain.helper.BeerPresentationHelper
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

/**
 * GetBeersUseCaseImpl class to manage logic use case for beers
 */
class GetBeersUseCaseImpl @Inject constructor(
    private val dataManager: DataManager
) : GetBeersUseCase {

    private var mSuccess: ((MutableList<BeerUIModel>) -> Unit)? = null
    private var mError: ((AppError) -> Unit)? = null

    private val disposable = CompositeDisposable()

    /**
     * Method for success response
     *
     * @param success success action
     */
    override fun onSuccess(success: (MutableList<BeerUIModel>) -> Unit): GetBeersUseCase {
        this.mSuccess = success
        return this
    }

    /**
     * Method for error response
     *
     * @param error error action
     */
    override fun onError(error: (AppError) -> Unit): GetBeersUseCase {
        this.mError = error
        return this
    }

    /**
     * Method to subscribe and get data
     */
    override fun subscribe() {
        disposable.add(api())
    }

    /**
     * Method to get data from api
     */
    private fun api(): Disposable {
        return dataManager.getApiRepository().getBeers()
            .observeOn(Schedulers.io())
            .subscribe({ arrayList ->
                val beerDao = dataManager.getDBRepository().beerDao()
                beerDao.deleteAllBeers()
                    .subscribe( {
                        val beerList = mutableListOf<BeerEntity>()
                        arrayList.forEach { beer ->
                            val imageUrl = beer.image_url ?: ""

                            val beerEntity = BeerEntity(
                                beer.id,
                                beer.name ?: "",
                                false,
                                imageUrl,
                                beer.description ?: "")

                            beerList.add(beerEntity)
                        }
                        beerDao.insertBeers(beerList)
                            .subscribe( {
                                beerDao.loadBeers()
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe({
                                        mSuccess?.invoke(BeerPresentationHelper().getBeerList(it))
                                    }, { e ->
                                        mError?.invoke(AppError())
                                        Timber.e(e)
                                    })
                            }, { error->
                                mError?.invoke(AppError())
                                Timber.e(error)
                            })
                    }, {error ->
                        mError?.invoke(AppError())
                        Timber.e(error)
                    })
            }, {error ->
                Observable.just(error)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe {
                        mError?.invoke(AppError())
                    }
                Timber.e(error)
            })
    }

    /**
     * Method to unsubscribe observer
     */
    override fun unSubscribe() {
        disposable.clear()
    }

}