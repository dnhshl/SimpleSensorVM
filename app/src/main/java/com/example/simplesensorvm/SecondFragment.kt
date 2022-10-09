package com.example.simplesensorvm

import android.content.Context
import android.graphics.Color
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.simplesensorvm.databinding.FragmentSecondBinding
import com.example.simplesensorvm.model.MainViewModel

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {



    private var _binding: FragmentSecondBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var sensorManager : SensorManager
    private lateinit var gyroListener: SensorEventListener

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sensorManager = activity?.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        val gyro = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)

        binding.tvGyroRes.text = getString(R.string.gyro_res, gyro.resolution)
        binding.tvGyroRange.text = getString(R.string.gyro_range, gyro.maximumRange)


        gyroListener = object : SensorEventListener {
            override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
                //Funktion wird von uns nicht benötigt, muss aber vorhanden sein.
                Log.i(">>>>", "onAccuracyChanged")
            }

            override fun onSensorChanged(sensorEvent: SensorEvent) {
                binding.tvGyroX.text = getString(R.string.gyro_x, sensorEvent.values[0])
                binding.tvGyroY.text = getString(R.string.gyro_y, sensorEvent.values[1])
                binding.tvGyroZ.text = getString(R.string.gyro_z, sensorEvent.values[2])

                if(sensorEvent.values[1] > 0.5f){
                    getView()?.setBackgroundColor(Color.RED)
                }
                else if(sensorEvent.values[1] < -0.5f){
                    getView()?.setBackgroundColor(Color.GREEN)
                }
            }
        }

        sensorManager.registerListener(gyroListener, gyro,
            SensorManager.SENSOR_DELAY_NORMAL)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        sensorManager.unregisterListener(gyroListener)
        _binding = null
    }
}