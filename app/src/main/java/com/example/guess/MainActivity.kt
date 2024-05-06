package com.example.guess

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.TextView
import android.widget.RadioGroup


class MainActivity : AppCompatActivity() {

    private lateinit var textViewResult: TextView
    private lateinit var textViewTest: TextView
    private lateinit var btnConfirm: Button
    private lateinit var groupGamer: RadioGroup
    private lateinit var groupPC: RadioGroup
    private lateinit var optionGamer: RadioButton
    private lateinit var optionPC: RadioButton
    private lateinit var imageGamer: ImageView
    private lateinit var imagePC: ImageView

    private val imageList = listOf(
        R.drawable.stone,
        R.drawable.scissors,
        R.drawable.paper
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageGamer = findViewById(R.id.myImage)
        imagePC = findViewById(R.id.pcImage)
        textViewResult = findViewById(R.id.result)
        textViewTest = findViewById(R.id.result_test)
        btnConfirm = findViewById(R.id.confirm_button)
        groupGamer = findViewById(R.id.radioGroup)
        groupPC = findViewById(R.id.radioGroupPC)

        btnConfirm.setOnClickListener {
            pcGame() //先让电脑选 否则optionGamer为NULL，会崩溃
            val optionGamerId = groupGamer.checkedRadioButtonId
            val optionPCId = groupPC.checkedRadioButtonId
            optionGamer = findViewById(optionGamerId)
            optionPC = findViewById(optionPCId)

            val judge = object : JudgeCallback {  //创建一个匿名的实例
                //实现回调方法
                override fun onButtonClicked(gamer: RadioButton, pc: RadioButton) {
                    onSetResult(gamer,pc)
                }
            }
            judge.onButtonClicked(optionGamer, optionPC)
        }
    }


    // 电脑随机出拳
    private fun pcGame() {
        val x = (0..2).random()
        val stoneButtonPC = findViewById<RadioButton>(R.id.stoneButtonPC)
        val scissorsButtonPC = findViewById<RadioButton>(R.id.scissorsButtonPC)
        val paperButtonPC = findViewById<RadioButton>(R.id.paperButtonPC)

        when (x) {
            0 -> stoneButtonPC.isChecked = true     // 石头
            1 -> scissorsButtonPC.isChecked = true  // 剪刀
            2 -> paperButtonPC.isChecked = true     // 布
        }
    }


    // 判断并显示图片和结果
    private fun onSetResult(gamer: RadioButton, pc: RadioButton) {
        val gid = gamer.tag.toString().toInt()
        val pid = pc.tag.toString().toInt()

        val result = when {
            gid == pid -> R.string.draw
            (gid == 0 && pid == 1) || (gid == 1 && pid == 2) || (gid == 2 && pid == 0) -> R.string.win
            else -> R.string.lose
        }

        // 显示结果
        imageGamer.setImageResource(imageList[gid])
        imagePC.setImageResource(imageList[pid])
        textViewResult.setText(result)
        textViewTest.text = getString(R.string.text_result_test, gamer.text, pc.text)
    }


}