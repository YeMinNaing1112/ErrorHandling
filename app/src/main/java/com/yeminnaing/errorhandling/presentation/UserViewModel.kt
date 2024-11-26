package com.yeminnaing.errorhandling.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yeminnaing.errorhandling.R
import com.yeminnaing.errorhandling.domain.AuthRepository
import com.yeminnaing.errorhandling.domain.DataError
import com.yeminnaing.errorhandling.domain.PasswordError
import com.yeminnaing.errorhandling.domain.Result
import com.yeminnaing.errorhandling.domain.User
import com.yeminnaing.errorhandling.domain.UserDataValidator
import com.yeminnaing.errorhandling.ui.theme.UiText
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class UserViewModel(
    private val userDataValidator: UserDataValidator,
    private val repository: AuthRepository
):ViewModel() {

    private val  eventChannel= Channel<UserEvent> ()
    val events=eventChannel.receiveAsFlow()
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
                      val errorMessage= result.error.asUiText()
                     eventChannel.send(UserEvent.Error(errorMessage))
                }
                is Result.Success -> {
                    result.data
                }
            }
        }

    }

}

sealed interface UserEvent{
    data class Error(val error: UiText):UserEvent
}