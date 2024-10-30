package com.example.forminformation

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var inputMSSV: EditText
    private lateinit var inputHoTen: EditText
    private lateinit var radioGroupGender: RadioGroup
    private lateinit var inputEmail: EditText
    private lateinit var inputPhone: EditText
    private lateinit var calendarViewBirthDate: CalendarView
    private lateinit var provinceSpinner: Spinner
    private lateinit var checkThao: CheckBox
    private lateinit var checkDienAnh: CheckBox
    private lateinit var checkAmNhac: CheckBox
    private lateinit var checkAgree: CheckBox
    private lateinit var btnSubmit: Button

    private var selectedDate: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Khởi tạo view
        inputMSSV = findViewById(R.id.inputMSSV)
        inputHoTen = findViewById(R.id.inputHoTen)
        radioGroupGender = findViewById(R.id.radioGroupGender)
        inputEmail = findViewById(R.id.inputEmail)
        inputPhone = findViewById(R.id.inputPhone)
        calendarViewBirthDate = findViewById(R.id.calendarViewBirthDate)
        provinceSpinner = findViewById(R.id.provinceSpinner)
        checkThao = findViewById(R.id.checkThao)
        checkDienAnh = findViewById(R.id.checkDienAnh)
        checkAmNhac = findViewById(R.id.checkAmNhac)
        checkAgree = findViewById(R.id.checkAgree)
        btnSubmit = findViewById(R.id.btnSubmit)

        // Thiết lập CalendarView
        calendarViewBirthDate.setOnDateChangeListener { _, year, month, dayOfMonth ->
            selectedDate = "$dayOfMonth/${month + 1}/$year"
        }

        // Thêm dữ liệu tỉnh/thành vào Spinner (đơn giản hóa, bạn có thể thay bằng dữ liệu thực tế từ file JSON)
        val provinces = arrayOf("Hà Nội", "TP. Hồ Chí Minh", "Đà Nẵng", "Hải Phòng", "Cần Thơ")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, provinces)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        provinceSpinner.adapter = adapter

        // Xử lý nút Submit
        btnSubmit.setOnClickListener {
            if (!checkAgree.isChecked) {
                Toast.makeText(this, "Bạn cần đồng ý với các điều khoản.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val mssv = inputMSSV.text.toString()
            val hoTen = inputHoTen.text.toString()
            val email = inputEmail.text.toString()
            val phone = inputPhone.text.toString()
            val dateOfBirth = selectedDate

            // Lấy giới tính
            val gender = when (radioGroupGender.checkedRadioButtonId) {
                R.id.radioMale -> "Nam"
                R.id.radioFemale -> "Nữ"
                else -> "Khác"
            }

            // Lấy sở thích
            val hobbies = mutableListOf<String>()
            if (checkThao.isChecked) hobbies.add("Thể thao")
            if (checkDienAnh.isChecked) hobbies.add("Điện ảnh")
            if (checkAmNhac.isChecked) hobbies.add("Âm nhạc")

            // Lấy tỉnh/thành
            val province = provinceSpinner.selectedItem.toString()

            // Hiển thị thông tin đã nhập
            val info = """
                MSSV: $mssv
                Họ tên: $hoTen
                Giới tính: $gender
                Email: $email
                Số điện thoại: $phone
                Ngày sinh: $dateOfBirth
                Nơi ở: $province
                Sở thích: ${hobbies.joinToString(", ")}
            """.trimIndent()
            Toast.makeText(this, "Thông tin đã được ghi nhận!\n$info", Toast.LENGTH_LONG).show()
        }
    }
}
