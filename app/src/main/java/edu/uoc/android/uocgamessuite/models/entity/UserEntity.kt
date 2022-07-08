package edu.uoc.android.uocgamessuite.models.entity

import com.google.firebase.database.Exclude

class UserEntity {

    /**
     * UserEntity id
     */
    @get:Exclude
    var id: String = ""

    /**
     * UserEntity email
     */
    var email: String = ""
}