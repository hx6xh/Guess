package com.example.guess

import android.widget.RadioButton


interface JudgeCallback {
    fun onButtonClicked(gamer: RadioButton, pc: RadioButton) //回调函数
}
