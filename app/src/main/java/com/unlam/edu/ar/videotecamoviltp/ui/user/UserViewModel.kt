package com.unlam.edu.ar.videotecamoviltp.ui.user

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.unlam.edu.ar.videotecamoviltp.domain.entities.UserEntity
import com.unlam.edu.ar.videotecamoviltp.data.repositories.database.UserEntityRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserViewModel(private val userEntityRepository: UserEntityRepository): ViewModel() {

    val userNameLiveData = MutableLiveData<String>()

    fun getUserNameById(userId:Int){
        CoroutineScope(Dispatchers.IO).launch {
            val user = userEntityRepository.getUserById(userId)
            if(user is UserEntity){
                withContext(Dispatchers.Main){
                    userNameLiveData.value = user.name
                }
            }
        }
    }
}