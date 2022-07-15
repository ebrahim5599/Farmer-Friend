package com.graduation.farmerfriend.control

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.graduation.farmerfriend.ecommerce_models.IOTStatus
import com.graduation.farmerfriend.repos.EcommerceRepo
import com.graduation.farmerfriend.repos.ForecastRepo
import com.graduation.farmerfriend.repos.TipsRepo
import io.reactivex.rxjava3.core.Single

class ControlContainerViewModel : ViewModel() {
    private var ecommerceRepo: EcommerceRepo = EcommerceRepo.getInstance()

    fun getIotStatusLiveData(): LiveData<IOTStatus> {
        return ecommerceRepo.iotStatusLiveData
    }

    fun checkIotStatus(userName: String): Single<IOTStatus> {
        return ecommerceRepo.checkIotStatus(userName)
    }
}