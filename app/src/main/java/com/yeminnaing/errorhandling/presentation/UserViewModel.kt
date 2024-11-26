package com.yeminnaing.errorhandling.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yeminnaing.errorhandling.domain.AuthRepository
import com.yeminnaing.errorhandling.domain.DataError
import com.yeminnaing.errorhandling.domain.PasswordError
import com.yeminnaing.errorhandling.domain.Result
import com.yeminnaing.errorhandling.domain.UserDataValidator
import kotlinx.coroutines.launch

class UserViewModel(
    private val userDataValidator: UserDataValidator,
    private val repository: AuthRepository
):ViewModel() {
    fun  onRegisterClick(password:String){
        when(val result=userDataValidator.validatePassword(password)){
            is Result.Error -> {
             when(result.error){
                 PasswordError.TOO_SHORT -> {}
                 PasswordError.NO_UPPERCASE -> {}
                 PasswordError.NO_DIGIT -> {}
             }

            }
            is Result.Success -> {

            }
        }

        viewModelScope.launch {
            when(val result= repository.register(password)){
                is Result.Error -> {
                    when(result.error){
                        DataError.Network.REQUEST_TIMEOUT -> TODO()
                        DataError.Network.TOO_MANY_REQUEST -> TODO()
                        DataError.Network.NO_INTERNET -> TODO()
                        DataError.Network.PAYLOAD_TOO_LARGE -> TODO()
                        DataError.Network.SERVER_ERROR -> TODO()
                        DataError.Network.SERIALIZATION -> TODO()
                        DataError.Network.UNKNOWN -> TODO()
                    }
                }
                is Result.Success -> TODO()
            }
        }

    }

}