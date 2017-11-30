package com.egci428.mysql

/**
 * Created by 6272user on 11/30/2017 AD.
 */
class Comment {
    var id: Long = 0
    var commentdata: String? = null

    // Will be used by the ArrayAdapter in the ListView
    override fun toString(): String {
        return commentdata.toString()
    }
}