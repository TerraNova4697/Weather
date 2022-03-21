package com.example.weatherapplication.screens

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import com.example.weatherapplication.R
import com.example.weatherapplication.data.entities.WeatherResponse
import com.example.weatherapplication.util.ViewModelFactory
import com.example.weatherapplication.util.toDateString
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import org.w3c.dom.Text
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import kotlin.math.roundToInt

class MainActivity : BaseActivity() {

    @Inject lateinit var mainViewModelFactory: ViewModelFactory
    private lateinit var mainViewModel: MainViewModel

    private lateinit var svCity: SearchView
    private lateinit var ibCity: ImageButton
    private lateinit var ivWeather: ImageView
    private lateinit var tvWeather: TextView
    private lateinit var tvDescription: TextView
    private lateinit var tvDegree: TextView
    private lateinit var tvHumidity: TextView
    private lateinit var tvMinimum: TextView
    private lateinit var tvMaximum: TextView
    private lateinit var tvWind: TextView
    private lateinit var tvCity: TextView
    private lateinit var tvCountry: TextView
    private lateinit var tvSunriseTime: TextView
    private lateinit var tvSunsetTime: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        injector.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        svCity = findViewById(R.id.sv_city)
        ibCity = findViewById(R.id.ib_city)
        ivWeather = findViewById(R.id.iv_weather)
        tvWeather = findViewById(R.id.tv_weather)
        tvDescription = findViewById(R.id.tv_description)
        tvDegree = findViewById(R.id.tv_degree)
        tvHumidity = findViewById(R.id.tv_humidity)
        tvMinimum = findViewById(R.id.tv_minimum)
        tvMaximum = findViewById(R.id.tv_maximum)
        tvWind = findViewById(R.id.tv_wind)
        tvCity = findViewById(R.id.tv_city)
        tvCountry = findViewById(R.id.tv_country)
        tvSunriseTime = findViewById(R.id.tv_sunrise_time)
        tvSunsetTime = findViewById(R.id.tv_sunset_time)

        mainViewModel = ViewModelProvider(this, mainViewModelFactory).get(MainViewModel::class.java)

        mainViewModel.weather.observe(this) {
            setUpUI(it)
        }
        svCity.setOnSearchClickListener {
            ibCity.visibility = View.VISIBLE
        }
        svCity.setOnCloseListener {
            ibCity.visibility = View.GONE
            false
        }
        ibCity.setOnClickListener { mainViewModel.onCityInput(svCity.query.toString()) }
    }

    private fun setUpUI(weatherData: WeatherResponse) {

        for (i in weatherData.weather.indices){
            // CardView weather
            when (weatherData.weather[i].icon) {
                "01d" -> ivWeather.setImageResource(R.drawable.sunny)
                "02d" -> ivWeather.setImageResource(R.drawable.cloud)
                "03d" -> ivWeather.setImageResource(R.drawable.cloud)
                "04d" -> ivWeather.setImageResource(R.drawable.cloud)
                "09d" -> ivWeather.setImageResource(R.drawable.rain)
                "10d" -> ivWeather.setImageResource(R.drawable.rain)
                "11d" -> ivWeather.setImageResource(R.drawable.storm)
                "13d" -> ivWeather.setImageResource(R.drawable.snowflake)
                "01n" -> ivWeather.setImageResource(R.drawable.cloud)
                "02n" -> ivWeather.setImageResource(R.drawable.cloud)
                "03n" -> ivWeather.setImageResource(R.drawable.cloud)
                "04n" -> ivWeather.setImageResource(R.drawable.cloud)
                "09n" -> ivWeather.setImageResource(R.drawable.rain)
                "10n" -> ivWeather.setImageResource(R.drawable.rain)
                "11n" -> ivWeather.setImageResource(R.drawable.storm)
                "13n" -> ivWeather.setImageResource(R.drawable.snowflake)
            }
            weatherData.apply {
                tvWeather.text = weather[i].main
                tvDescription.text = weather[i].description

                // CardView temperature
                val currTemp = main.temp.roundToInt().toString() + getString(R.string.celsius)
                val currHumidity = main.humidity.toString() + getString(R.string.percent)
                tvDegree.text = currTemp
                tvHumidity.text = currHumidity

                //CardView MinMax
                val currMin = getString(R.string.min_temp) + "  " + main.temp_min.roundToInt().toString() + getString(R.string.celsius)
                val currMax = getString(R.string.max_temp) + "  " + main.temp_max.roundToInt().toString() + getString(R.string.celsius)
                tvMinimum.text = currMin
                tvMaximum.text = currMax

                // CardView Wind
                tvWind.text = wind.speed.roundToInt().toString()

                // CardView Other
                tvCity.text = name
                tvCountry.text = sys.country
                tvSunriseTime.text = sys.sunrise.toDateString(sys.sunrise, timezone)
                tvSunsetTime.text = sys.sunset.toDateString(sys.sunset, timezone)
            }

        }

    }
}