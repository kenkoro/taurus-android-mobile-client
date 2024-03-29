package com.kenkoro.taurus.client.feature.sewing.presentation.screen.user

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.kenkoro.taurus.client.feature.sewing.data.source.remote.dto.response.GetUserResponse
import com.kenkoro.taurus.client.feature.sewing.data.source.repository.UserRepositoryImpl
import com.kenkoro.taurus.client.feature.sewing.data.util.UserRole
import dagger.hilt.android.lifecycle.HiltViewModel
import io.ktor.client.statement.HttpResponse
import javax.inject.Inject

@HiltViewModel
class UserViewModel
  @Inject
  constructor(
    private val userRepository: UserRepositoryImpl,
  ) : ViewModel() {
    var user by
      mutableStateOf(
        GetUserResponse(
          id = -1,
          subject = "None",
          password = "None",
          image = "None",
          firstName = "None",
          lastName = "None",
          role = UserRole.Others,
          salt = "None",
        ),
      )
      private set

    var isLoading by mutableStateOf(true)
      private set

    fun onLoad(isLoading: Boolean) {
      this.isLoading = isLoading
    }

    fun onGetUserResponse(userResponse: GetUserResponse) {
      user = userResponse
    }

    suspend fun getUser(
      subject: String,
      token: String,
    ): HttpResponse {
      return userRepository.run {
        token(token)
        getUser(subject)
      }
    }
  }