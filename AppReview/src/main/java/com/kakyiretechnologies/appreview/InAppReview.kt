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

import android.app.Activity
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.play.core.review.ReviewManagerFactory
import com.kakyiretechnologies.appreview.PersistUserOpenTimes.Companion.TAG


/**
 * @author Kakyire (Daniel Frimpong)
 *
 *Trigger InAppReview action when user opens the app [numberOfTimes]+
 * @param numberOfTimes (default value is 5) when review action to be triggered
 */
@JvmOverloads
fun AppCompatActivity.reviewApp(numberOfTimes: Int = 5) {

    val inAppReviewViewModel by viewModels<InAppReviewViewModel>()

    Log.d(TAG, "reviewApp: $inAppReviewViewModel")
    inAppReviewViewModel.appOpened.observe(this, { times ->
        times?.let {
            Log.d(TAG, "reviewApp: $it")
            if (it >= numberOfTimes) {
                appReviewConfiguration(this)
            }
        }
    })
}

/**
 * @author Kakyire (Daniel Frimpong)
 *
 *Trigger InAppReview action when user opens the app [numberOfTimes]+
 * @param numberOfTimes (default value is 5) when review action to be triggered
 */
@JvmOverloads
fun Fragment.reviewApp(numberOfTimes: Int = 5) {

    val inAppReviewViewModel by viewModels<InAppReviewViewModel>()

    Log.d(TAG, "reviewApp: $inAppReviewViewModel")
    inAppReviewViewModel.appOpened.observe(requireActivity(), { times ->
        times?.let {
            Log.d(TAG, "reviewApp: $it")
            if (it >= numberOfTimes) {
                appReviewConfiguration(requireActivity())
            }
        }
    })
}

/**
 *  @author Kakyire (Daniel Frimpong)
 *
 * Configure InAppReview to be called by [reviewApp]
 * when user opens the for a specific number of times
 *
 * @param view [Fragment] or [Activity] to trigger the action from
 */
private fun appReviewConfiguration(view: Activity) {

    val manager = ReviewManagerFactory.create(view.applicationContext)


    manager.requestReviewFlow()
        .addOnCompleteListener { request ->
            if (request.isSuccessful) {
                val reviewInfo = request.result

                manager.launchReviewFlow(view, reviewInfo)
                    .apply {

                        addOnCompleteListener {
                            Log.d(TAG, "Review complete: $it")
                        }

                        addOnFailureListener {
                            Log.d(TAG, "Review failure: $it")
                        }
                    }
            } else {
                Log.d(TAG, "Request error: ${request.exception} ")
            }
        }
}




