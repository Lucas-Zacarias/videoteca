package com.unlam.edu.ar.videotecamoviltp.Classes


class User(var name : String, val email : String, val password : String) {

   fun changeUserName(newUserName : String) { name = newUserName }

}
