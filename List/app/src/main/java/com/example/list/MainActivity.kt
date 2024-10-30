package com.example.list

import android.os.Bundle
import android.text.TextUtils
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var inputNumber: EditText
    private lateinit var radioGroup: RadioGroup
    private lateinit var showButton: Button
    private lateinit var listView: ListView
    private lateinit var errorTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        inputNumber = findViewById(R.id.inputNumber)
        radioGroup = findViewById(R.id.radioGroup)
        showButton = findViewById(R.id.showButton)
        listView = findViewById(R.id.listView)
        errorTextView = findViewById(R.id.errorTextView)

        showButton.setOnClickListener {
            errorTextView.text = ""  // Xóa thông báo lỗi
            val n = inputNumber.text.toString()

            if (TextUtils.isEmpty(n)) {
                errorTextView.text = "Lỗi! Vui lòng nhập một số dương"
                return@setOnClickListener
            }

            val num = n.toIntOrNull()
            if (num == null || num < 1) {
                errorTextView.text = "Số không hợp lệ, vui lòng nhập một số dương!"
                return@setOnClickListener
            }

            val result = when (radioGroup.checkedRadioButtonId) {
                R.id.radioEven -> generateEvenNumbers(num)
                R.id.radioOdd -> generateOddNumbers(num)
                R.id.radioSquare -> generateSquareNumbers(num)
                else -> {
                    errorTextView.text = "Hãy chọn loại số cần hiển thị"
                    return@setOnClickListener
                }
            }

            val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, result)
            listView.adapter = adapter
        }
    }

    private fun generateEvenNumbers(n: Int): List<Int> {
        val evenNumbers = mutableListOf<Int>()
        for (i in 0..n) {
            if (i % 2 == 0) evenNumbers.add(i)
        }
        return evenNumbers
    }

    private fun generateOddNumbers(n: Int): List<Int> {
        val oddNumbers = mutableListOf<Int>()
        for (i in 1..n) {
            if (i % 2 != 0) oddNumbers.add(i)
        }
        return oddNumbers
    }

    private fun generateSquareNumbers(n: Int): List<Int> {
        val squareNumbers = mutableListOf<Int>()
        var i = 0
        while (i * i <= n) {
            squareNumbers.add(i * i)
            i++
        }
        return squareNumbers
    }
}
