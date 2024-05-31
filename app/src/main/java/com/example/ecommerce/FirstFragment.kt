package com.example.ecommerce

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel

class FirstFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_first, container, false)

        val imageList = ArrayList<SlideModel>() // Create image list
        imageList.add(SlideModel(R.drawable.sssssss,ScaleTypes.FIT))
        imageList.add(SlideModel(R.drawable.ss,ScaleTypes.FIT))
        imageList.add(SlideModel(R.drawable.ssss,ScaleTypes.FIT))

        val imageSlider = rootView.findViewById<ImageSlider>(R.id.image_slider)
        imageSlider.setImageList(imageList)

        return rootView
    }
}
