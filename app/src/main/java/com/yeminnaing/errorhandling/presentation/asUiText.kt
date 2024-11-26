package com.yeminnaing.errorhandling.presentation

import com.yeminnaing.errorhandling.R
import com.yeminnaing.errorhandling.domain.DataError
import com.yeminnaing.errorhandling.domain.Result
import com.yeminnaing.errorhandling.ui.theme.UiText

fun DataError.asUiText(): UiText {
    return when(this){
        DataError.Network.REQUEST_TIMEOUT -> UiText.StringResource(
            R.string.the_request_timeout
        )
        DataError.Network.TOO_MANY_REQUEST -> UiText.StringResource(
            R.string.too_many_request
        )
        DataError.Network.NO_INTERNET ->  UiText.StringResource(
            R.string.no_internet
        )
        DataError.Network.PAYLOAD_TOO_LARGE -> UiText.StringResource(
            R.string.payload_too_large
        )
        DataError.Network.SERVER_ERROR ->  UiText.StringResource(
            R.string.sever_error
        )
        DataError.Network.SERIALIZATION ->  UiText.StringResource(
            R.string.serialization
        )
        DataError.Network.UNKNOWN ->  UiText.StringResource(
            R.string.unknown
        )
        DataError.Local.DISK_FULL -> UiText.StringResource(
            R.string.dask_full
        )
    }

}
fun Result.Error<*,DataError>.asErrorUiText():UiText{
    return error.asUiText()
}
