package com.unlam.edu.ar.videotecamoviltp.Classes

class ListUsers(){

    var listUsers: MutableList<User> = mutableListOf()

    fun addUserToTheList(newUser: User): Boolean {
        var successfulAdd: Boolean = false
        if (searchUserInTheListByEmail(newUser.email) == null) {
            successfulAdd = listUsers.add(newUser)
        }
        return successfulAdd
    }



    private fun searchUserInTheListByEmail(emailUser: String): User? {
        var wantedUser: User? = null

        for (userInTheList in listUsers) {
            if (userInTheList.email.equals(emailUser)) {
                wantedUser = userInTheList
            }
        }
        return wantedUser
    }

    fun deleteUser(userToDelete: User): Boolean {
        var successfulDelete: Boolean = false

        if (searchUserInTheListByEmail(userToDelete.email) != null) {
            successfulDelete = listUsers.remove(userToDelete)
        }

        return successfulDelete
    }
}