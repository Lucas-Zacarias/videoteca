package com.unlam.edu.ar.videotecamoviltp.Classes

import com.unlam.edu.ar.videotecamoviltp.Classes.User as User

class User(var name : String, val email : String, val password : String) {

   private fun changeUserName(newUserName : String) { name = newUserName }

    /*/*Sobreescritura para poder, al ingresar el elemento a la lista de usuarios,
     asegurarme que no se repitan usuarios con el mismo email*/*/
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as User

        if (email != other.email) return false

        return true
    }

    override fun hashCode(): Int {
        return email.hashCode()
    }

}

class ListUsers(){

        private val listUsers: MutableList<User> = mutableListOf()

        fun addUserToTheList(newUser: User): Boolean {
            var successfulAdd: Boolean = false
            if (searchUserInTheListByEmail(newUser.email) !is User) {
                successfulAdd = listUsers.add(newUser)
            }
            return successfulAdd
        }



        private fun searchUserInTheListByEmail(emailUser: String): User {
            var wantedUser: User? = null

            for (userInTheList in listUsers) {
                if (userInTheList.email.equals(emailUser)) {
                    wantedUser = userInTheList
                }
            }
            return wantedUser ?: throw UserNotFoundException("No se ha encontrado al usuario en la lista")
        }

        fun deleteUser(userToDelete: User): Boolean {
            var successfulDelete: Boolean = false

            if (searchUserInTheListByEmail(userToDelete.email) is User) {
                successfulDelete = listUsers.remove(userToDelete)
            }

            return successfulDelete
        }
    }
