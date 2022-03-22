package com.example.weatherapplication.screens

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import com.example.weatherapplication.R
import com.example.weatherapplication.data.entities.WeatherResponse
import com.example.weatherapplication.databinding.ActivityMainBinding
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

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        injector.inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainViewModel = ViewModelProvider(this, mainViewModelFactory).get(MainViewModel::class.java)

        mainViewModel.weather.observe(this) {
            setUpUI(it)
        }
        binding.apply {
            svCity.setOnSearchClickListener {
                ibCity.visibility = View.VISIBLE
            }
            svCity.setOnCloseListener {
                ibCity.visibility = View.GONE
                false
            }
            ibCity.setOnClickListener { mainViewModel.onCityInput(svCity.query.toString()) }
        }
    }

    private fun setUpUI(weatherData: WeatherResponse) {
        binding.apply {
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_activity_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.menu.main_activity_menu -> {
                mainViewModel.onRefreshClicked()
                return true
            }else -> super.onOptionsItemSelected(item)
        }
    }
}