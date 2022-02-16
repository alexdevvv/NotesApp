package com.example.notesapp.screens

import android.app.Activity
import android.app.AlertDialog
import com.example.notesapp.R

 fun createDialog(messageError: String, activity: Activity) {
    val builder = AlertDialog.Builder(activity)
    builder.setTitle("Сообщение")
    builder.setMessage(messageError)
    builder.setPositiveButton(
        android.R.string.ok
    ) { dialog, which ->
        dialog.dismiss()
    }
    builder.create().show()
}