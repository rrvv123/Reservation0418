package kr.ac.kopo.reservation0418

import android.graphics.Color
import android.os.Bundle
import android.os.SystemClock
import android.view.View
import android.widget.Button
import android.widget.CalendarView
import android.widget.Chronometer
import android.widget.RadioGroup
import android.widget.RadioGroup.OnCheckedChangeListener
import android.widget.TextView
import android.widget.TimePicker
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    lateinit var chrono : Chronometer
    lateinit var btnStart : Button
    lateinit var btnDone : Button
    lateinit var rg : RadioGroup
    lateinit var calendar : CalendarView
    lateinit var timePick : TimePicker
    lateinit var textResult : TextView
    var selectedYear : Int = 0
    var selectedMonth : Int = 0
    var selectedDay : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        chrono = findViewById<Chronometer>(R.id.chrono)
        btnStart = findViewById<Button>(R.id.btnStart)
        btnDone = findViewById<Button>(R.id.btnDone)
        rg = findViewById<RadioGroup>(R.id.rg)
        calendar = findViewById<CalendarView>(R.id.calendar)
        timePick = findViewById<TimePicker>(R.id.timePick)
        textResult = findViewById<TextView>(R.id.textResult)

        calendar.visibility = View.INVISIBLE
        timePick.visibility = View.INVISIBLE

        rg.setOnCheckedChangeListener(rgListner)

        btnStart.setOnClickListener {
            chrono.base = SystemClock.elapsedRealtime()
            chrono.start()
            chrono.setTextColor(Color.MAGENTA)
        }

        btnDone.setOnClickListener {
            chrono.stop()
            chrono.setTextColor(Color.CYAN)
            textResult.setText("" + selectedYear + "년" + selectedMonth + "월" + selectedDay + "일")
                                // 코틀린은 정수로 시작하면 오류가 뜨므로 "" 따옴표인 빈문자열로 먼저 시작해줘야함.
            textResult.append("" + timePick.currentHour + "시")
            textResult.append("" + timePick.currentMinute + "분")
        }

        calendar.setOnDateChangeListener { view, year, month, dayOfMonth ->
            selectedYear = year
            selectedMonth = month + 1
            selectedDay = dayOfMonth
        }
    }

    var rgListner = OnCheckedChangeListener{group, checked ->
        calendar.visibility = View.INVISIBLE
        timePick.visibility = View.INVISIBLE

        when(rg.checkedRadioButtonId){
            R.id.rbDate -> calendar.visibility = View.VISIBLE
            R.id.rbTime -> timePick.visibility = View.VISIBLE
        }
    }
}