/**
 * Copyright 2021 Kakyire (Daniel Frimpong)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package com.kakyiretechnologies.appreview

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


/**
 *   @author Kakyire (Daniel Frimpong)
 *
 *   Increase [appOpened] times by calling [PersistUserOpenTimes.increaseAppOpenTimes]
 *   and observe it in [reviewApp] to trigger review action
 */
internal class InAppReviewViewModel(application: Application) : AndroidViewModel(application) {

    private val persistUserOpenTimes = PersistUserOpenTimes(application.applicationContext)

    private val _appOpened = MutableLiveData<Int>()
    val appOpened: LiveData<Int> = _appOpened

    init {

        viewModelScope.launch {
            persistUserOpenTimes.apply {
                increaseAppOpenTimes()
                appOpenedTimesFlow.collect {
                    _appOpened.postValue(it)
                }
            }


        }
    }

}