package com.unlam.edu.ar.videotecamoviltp.Classes

import com.unlam.edu.ar.videotecamoviltp.Classes.User as User

class User(var name : String, val email : String, val password : String) {

   fun changeUserName(newUserName : String) { name = newUserName }

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
