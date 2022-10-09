package com.example.simplesensorvm


import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import com.example.simplesensorvm.databinding.FragmentThirdBinding
import com.example.simplesensorvm.model.MainViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices


class ThirdFragment : Fragment() {

    private var _binding: FragmentThirdBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentThirdBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvLocation.text

        viewModel.location.observe(viewLifecycleOwner) { location ->
            if (location == null) {
                binding.tvLocation.text = getString(R.string.no_position)
            } else {
                binding.tvLocation.text = getString(
                    R.string.gps_position,
                    location.longitude,
                    location.latitude,
                    location.time
                )
            }
        }

     }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}