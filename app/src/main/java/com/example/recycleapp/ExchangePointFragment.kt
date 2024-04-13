package com.example.recycleapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ExchangePointFragment : Fragment() {
    private val imageTextMap = mapOf(
        "https://lh5.googleusercontent.com/p/AF1QipNCjCLXT4YpqGJx5niw9zTFqjDJQROoblMUyjvw=w260-h175-n-k-no" to "Aluminum Cans: Aluminum cans typically range from ₹18 per kilogram.\n" +
                "\n" +
                "Plastic Bottles: Depending on the type of plastic and local demand, plastic bottles can be worth anywhere from ₹8 per kilogram.\n" +
                "\n" +
                "Glass Bottles: Clear glass bottles usually fetch around ₹4 per kilogram, while colored glass may have slightly lower rates.\n" +
                "\n" +
                "Newspapers: Newspapers are generally valued at around ₹1 per kilogram.\n" +
                "\n" +
                "Cardboard: Cardboard prices vary but usually fall between ₹1 per kilogram, depending on its condition and demand.\n" +
                "\n" +
                "Steel Cans: Steel cans typically bring in around ₹4 per kilogram.\n" +
                "\n" +
                "Electronics (E-Waste): Electronics recycling rates can vary widely based on the type and condition of the electronics. Some items, like old cell phones or computer parts, may have recycling incentives or trade-in programs.\n" +
                "\n" +
                "Car Batteries: Car batteries are usually worth around ₹350 each when recycled.",
        "https://lh3.googleusercontent.com/p/AF1QipOZhNTyx7J9lX7hE7IiMbCQWrrcjzoaj01CSVyQ=s1360-w1360-h1020" to "Aluminum Cans: Aluminum cans typically range from ₹19 per kilogram.\n" +
                "\n" +
                "Plastic Bottles: Depending on the type of plastic and local demand, plastic bottles can be worth anywhere from ₹10 per kilogram.\n" +
                "\n" +
                "Glass Bottles: Clear glass bottles usually fetch around ₹5 per kilogram, while colored glass may have slightly lower rates.\n" +
                "\n" +
                "Newspapers: Newspapers are generally valued at around ₹2 per kilogram.\n" +
                "\n" +
                "Cardboard: Cardboard prices vary but usually fall between ₹2 per kilogram, depending on its condition and demand.\n" +
                "\n" +
                "Steel Cans: Steel cans typically bring in around ₹6 per kilogram.\n" +
                "\n" +
                "Electronics (E-Waste): Electronics recycling rates can vary widely based on the type and condition of the electronics. Some items, like old cell phones or computer parts, may have recycling incentives or trade-in programs.\n" +
                "\n" +
                "Car Batteries: Car batteries are usually worth around ₹350 each when recycled.",
        "https://lh3.googleusercontent.com/p/AF1QipN1DEeXjyHhfx2YKAaELpOZ1dzLMm-uruCTyka6=s1360-w1360-h1020" to "Aluminum Cans: Aluminum cans typically range from ₹18 to ₹36 per kilogram.\n" +
                "\n" +
                "Plastic Bottles: Depending on the type of plastic and local demand, plastic bottles can be worth anywhere from ₹8 to ₹20 per kilogram.\n" +
                "\n" +
                "Glass Bottles: Clear glass bottles usually fetch around ₹4 to ₹8 per kilogram, while colored glass may have slightly lower rates.\n" +
                "\n" +
                "Newspapers: Newspapers are generally valued at around ₹1 to ₹2 per kilogram.\n" +
                "\n" +
                "Cardboard: Cardboard prices vary but usually fall between ₹1 to ₹5 per kilogram, depending on its condition and demand.\n" +
                "\n" +
                "Steel Cans: Steel cans typically bring in around ₹4 to ₹8 per kilogram.\n" +
                "\n" +
                "Electronics (E-Waste): Electronics recycling rates can vary widely based on the type and condition of the electronics. Some items, like old cell phones or computer parts, may have recycling incentives or trade-in programs.\n" +
                "\n" +
                "Car Batteries: Car batteries are usually worth around ₹350 to ₹700 each when recycled.",
        "https://lh3.googleusercontent.com/p/AF1QipOGtJ8S9e1LFaFxaRsrZziCRltcXsI12tKRR4WV=s1360-w1360-h1020" to "Aluminum Cans: Aluminum cans typically range from ₹18 to ₹36 per kilogram.\n" +
                "\n" +
                "Plastic Bottles: Depending on the type of plastic and local demand, plastic bottles can be worth anywhere from ₹8 to ₹20 per kilogram.\n" +
                "\n" +
                "Glass Bottles: Clear glass bottles usually fetch around ₹4 to ₹8 per kilogram, while colored glass may have slightly lower rates.\n" +
                "\n" +
                "Newspapers: Newspapers are generally valued at around ₹1 to ₹2 per kilogram.\n" +
                "\n" +
                "Cardboard: Cardboard prices vary but usually fall between ₹1 to ₹5 per kilogram, depending on its condition and demand.\n" +
                "\n" +
                "Steel Cans: Steel cans typically bring in around ₹4 to ₹8 per kilogram.\n" +
                "\n" +
                "Electronics (E-Waste): Electronics recycling rates can vary widely based on the type and condition of the electronics. Some items, like old cell phones or computer parts, may have recycling incentives or trade-in programs.\n" +
                "\n" +
                "Car Batteries: Car batteries are usually worth around ₹350 to ₹700 each when recycled.",
        "https://streetviewpixels-pa.googleapis.com/v1/thumbnail?panoid=XzSEQL57lR6Zs-1PneSlCw&cb_client=search.gws-prod.gps&yaw=285.33093&pitch=0&thumbfov=100&w=260&h=175" to "Aluminum Cans: Aluminum cans typically range from ₹18 to ₹36 per kilogram.\n" +
                "\n" +
                "Plastic Bottles: Depending on the type of plastic and local demand, plastic bottles can be worth anywhere from ₹8 to ₹20 per kilogram.\n" +
                "\n" +
                "Glass Bottles: Clear glass bottles usually fetch around ₹4 to ₹8 per kilogram, while colored glass may have slightly lower rates.\n" +
                "\n" +
                "Newspapers: Newspapers are generally valued at around ₹1 to ₹2 per kilogram.\n" +
                "\n" +
                "Cardboard: Cardboard prices vary but usually fall between ₹1 to ₹5 per kilogram, depending on its condition and demand.\n" +
                "\n" +
                "Steel Cans: Steel cans typically bring in around ₹4 to ₹8 per kilogram.\n" +
                "\n" +
                "Electronics (E-Waste): Electronics recycling rates can vary widely based on the type and condition of the electronics. Some items, like old cell phones or computer parts, may have recycling incentives or trade-in programs.\n" +
                "\n" +
                "Car Batteries: Car batteries are usually worth around ₹350 to ₹700 each when recycled.",
        "https://www.google.com/maps/dir//Spas+Recycling+Pvt+Ltd,+Unit+No.7,Hema+Industrial+Estate,Sarvodaya+Nagar,,+MHB+Colony+Rd,+Meghwadi,+Indira+Nagar,+Jogeshwari+East,+Mumbai,+Maharashtra+400060/data=!4m6!4m5!1m1!4e2!1m2!1m1!1s0x3be7b7d791f6d6d5:0x3bbed3a9524c3625?sa=X&ved=1t:57443&ictx=111" to "Aluminum Cans: Aluminum cans typically range from ₹18 to ₹36 per kilogram.\n" +
                "\n" +
                "Plastic Bottles: Depending on the type of plastic and local demand, plastic bottles can be worth anywhere from ₹8 to ₹20 per kilogram.\n" +
                "\n" +
                "Glass Bottles: Clear glass bottles usually fetch around ₹4 to ₹8 per kilogram, while colored glass may have slightly lower rates.\n" +
                "\n" +
                "Newspapers: Newspapers are generally valued at around ₹1 to ₹2 per kilogram.\n" +
                "\n" +
                "Cardboard: Cardboard prices vary but usually fall between ₹1 to ₹5 per kilogram, depending on its condition and demand.\n" +
                "\n" +
                "Steel Cans: Steel cans typically bring in around ₹4 to ₹8 per kilogram.\n" +
                "\n" +
                "Electronics (E-Waste): Electronics recycling rates can vary widely based on the type and condition of the electronics. Some items, like old cell phones or computer parts, may have recycling incentives or trade-in programs.\n" +
                "\n" +
                "Car Batteries: Car batteries are usually worth around ₹350 to ₹700 each when recycled.",

        "https://lh3.googleusercontent.com/p/AF1QipPPpms0TufS-tcQPVt6hvSqB032hQlwJuTl-lIH=s1360-w1360-h1020" to "Aluminum Cans: Aluminum cans typically range from ₹18 to ₹36 per kilogram.\n" +
                "\n" +
                "Plastic Bottles: Depending on the type of plastic and local demand, plastic bottles can be worth anywhere from ₹8 to ₹20 per kilogram.\n" +
                "\n" +
                "Glass Bottles: Clear glass bottles usually fetch around ₹4 to ₹8 per kilogram, while colored glass may have slightly lower rates.\n" +
                "\n" +
                "Newspapers: Newspapers are generally valued at around ₹1 to ₹2 per kilogram.\n" +
                "\n" +
                "Cardboard: Cardboard prices vary but usually fall between ₹1 to ₹5 per kilogram, depending on its condition and demand.\n" +
                "\n" +
                "Steel Cans: Steel cans typically bring in around ₹4 to ₹8 per kilogram.\n" +
                "\n" +
                "Electronics (E-Waste): Electronics recycling rates can vary widely based on the type and condition of the electronics. Some items, like old cell phones or computer parts, may have recycling incentives or trade-in programs.\n" +
                "\n" +
                "Car Batteries: Car batteries are usually worth around ₹350 to ₹700 each when recycled.",
        "https://lh3.googleusercontent.com/p/AF1QipMT0nNEhxy_2wgLkPqRiXhvnoENZ-M-5NGYpHnp=s1360-w1360-h1020" to "Aluminum Cans: Aluminum cans typically range from ₹18 to ₹36 per kilogram.\n" +
                "\n" +
                "Plastic Bottles: Depending on the type of plastic and local demand, plastic bottles can be worth anywhere from ₹8 to ₹20 per kilogram.\n" +
                "\n" +
                "Glass Bottles: Clear glass bottles usually fetch around ₹4 to ₹8 per kilogram, while colored glass may have slightly lower rates.\n" +
                "\n" +
                "Newspapers: Newspapers are generally valued at around ₹1 to ₹2 per kilogram.\n" +
                "\n" +
                "Cardboard: Cardboard prices vary but usually fall between ₹1 to ₹5 per kilogram, depending on its condition and demand.\n" +
                "\n" +
                "Steel Cans: Steel cans typically bring in around ₹4 to ₹8 per kilogram.\n" +
                "\n" +
                "Electronics (E-Waste): Electronics recycling rates can vary widely based on the type and condition of the electronics. Some items, like old cell phones or computer parts, may have recycling incentives or trade-in programs.\n" +
                "\n" +
                "Car Batteries: Car batteries are usually worth around ₹350 to ₹700 each when recycled.",
        "https://lh5.googleusercontent.com/p/AF1QipPhceWJwntLSIXZOr-IyYM8dUqi2xLxiyQBgyx3=w260-h175-n-k-no" to "Aluminum Cans: Aluminum cans typically range from ₹18 to ₹36 per kilogram.\n" +
                "\n" +
                "Plastic Bottles: Depending on the type of plastic and local demand, plastic bottles can be worth anywhere from ₹8 to ₹20 per kilogram.\n" +
                "\n" +
                "Glass Bottles: Clear glass bottles usually fetch around ₹4 to ₹8 per kilogram, while colored glass may have slightly lower rates.\n" +
                "\n" +
                "Newspapers: Newspapers are generally valued at around ₹1 to ₹2 per kilogram.\n" +
                "\n" +
                "Cardboard: Cardboard prices vary but usually fall between ₹1 to ₹5 per kilogram, depending on its condition and demand.\n" +
                "\n" +
                "Steel Cans: Steel cans typically bring in around ₹4 to ₹8 per kilogram.\n" +
                "\n" +
                "Electronics (E-Waste): Electronics recycling rates can vary widely based on the type and condition of the electronics. Some items, like old cell phones or computer parts, may have recycling incentives or trade-in programs.\n" +
                "\n" +
                "Car Batteries: Car batteries are usually worth around ₹350 to ₹700 each when recycled.",
        "https://lh3.googleusercontent.com/p/AF1QipMnxFNmprt6v1rq8rBm6TpxHGyRbvx63uFuXRoQ=s1360-w1360-h1020" to "Aluminum Cans: Aluminum cans typically range from ₹18 to ₹36 per kilogram.\n" +
                "\n" +
                "Plastic Bottles: Depending on the type of plastic and local demand, plastic bottles can be worth anywhere from ₹8 to ₹20 per kilogram.\n" +
                "\n" +
                "Glass Bottles: Clear glass bottles usually fetch around ₹4 to ₹8 per kilogram, while colored glass may have slightly lower rates.\n" +
                "\n" +
                "Newspapers: Newspapers are generally valued at around ₹1 to ₹2 per kilogram.\n" +
                "\n" +
                "Cardboard: Cardboard prices vary but usually fall between ₹1 to ₹5 per kilogram, depending on its condition and demand.\n" +
                "\n" +
                "Steel Cans: Steel cans typically bring in around ₹4 to ₹8 per kilogram.\n" +
                "\n" +
                "Electronics (E-Waste): Electronics recycling rates can vary widely based on the type and condition of the electronics. Some items, like old cell phones or computer parts, may have recycling incentives or trade-in programs.\n" +
                "\n" +
                "Car Batteries: Car batteries are usually worth around ₹350 to ₹700 each when recycled.",
        "https://www.google.com/maps?sca_esv=8e47e8b36fbcdee8&lqi=ChRyZWN5Y2xlIHNob3AgY2hlbm5haUi3yKXqzriAgAhaLBAAEAEYABgBGAIiFHJlY3ljbGUgc2hvcCBjaGVubmFpKgQIFBABKgQIAxAAkgESc2NyYXBfbWV0YWxfZGVhbGVyqgFPEAEqECIMcmVjeWNsZSBzaG9wKAAyHxABIhsOiK0N1WGnaTafkJGqxpuCCr09IOdp2S2ZtnwyGBACIhRyZWN5Y2xlIHNob3AgY2hlbm5haQ&phdesc=mPqSuG78CJY&vet=12ahUKEwj_uP3o6ruFAxV8T2wGHfzWATYQ8UF6BAgFEFg..i&lei=97cYZr_hKvyeseMP_K2HsAM&cs=1&um=1&ie=UTF-8&fb=1&gl=in&sa=X&geocode=KT9yxyQQXVI6Mb-1at6Xfb0B&daddr=No-9,+Omr+road,+Sri+Sowdeswari+Nagar,+Thoraipakkam,+Chennai,+Tamil+Nadu+600097" to "Aluminum Cans: Aluminum cans typically range from ₹18 to ₹36 per kilogram.\n" +
                "\n" +
                "Plastic Bottles: Depending on the type of plastic and local demand, plastic bottles can be worth anywhere from ₹8 to ₹20 per kilogram.\n" +
                "\n" +
                "Glass Bottles: Clear glass bottles usually fetch around ₹4 to ₹8 per kilogram, while colored glass may have slightly lower rates.\n" +
                "\n" +
                "Newspapers: Newspapers are generally valued at around ₹1 to ₹2 per kilogram.\n" +
                "\n" +
                "Cardboard: Cardboard prices vary but usually fall between ₹1 to ₹5 per kilogram, depending on its condition and demand.\n" +
                "\n" +
                "Steel Cans: Steel cans typically bring in around ₹4 to ₹8 per kilogram.\n" +
                "\n" +
                "Electronics (E-Waste): Electronics recycling rates can vary widely based on the type and condition of the electronics. Some items, like old cell phones or computer parts, may have recycling incentives or trade-in programs.\n" +
                "\n" +
                "Car Batteries: Car batteries are usually worth around ₹350 to ₹700 each when recycled.",
        "https://lh3.googleusercontent.com/p/AF1QipPH1aEaDYzEjBlgF0qfcFy7K2SOHC5gS2yYp3E=s1360-w1360-h1020" to "Aluminum Cans: Aluminum cans typically range from ₹18 to ₹36 per kilogram.\n" +
                "\n" +
                "Plastic Bottles: Depending on the type of plastic and local demand, plastic bottles can be worth anywhere from ₹8 to ₹20 per kilogram.\n" +
                "\n" +
                "Glass Bottles: Clear glass bottles usually fetch around ₹4 to ₹8 per kilogram, while colored glass may have slightly lower rates.\n" +
                "\n" +
                "Newspapers: Newspapers are generally valued at around ₹1 to ₹2 per kilogram.\n" +
                "\n" +
                "Cardboard: Cardboard prices vary but usually fall between ₹1 to ₹5 per kilogram, depending on its condition and demand.\n" +
                "\n" +
                "Steel Cans: Steel cans typically bring in around ₹4 to ₹8 per kilogram.\n" +
                "\n" +
                "Electronics (E-Waste): Electronics recycling rates can vary widely based on the type and condition of the electronics. Some items, like old cell phones or computer parts, may have recycling incentives or trade-in programs.\n" +
                "\n" +
                "Car Batteries: Car batteries are usually worth around ₹350 to ₹700 each when recycled.",
        "https://lh3.googleusercontent.com/p/AF1QipO-yHA-oWquOAdvmAlyIVaG30zOKHj1yGq0Xd5Y=s1360-w1360-h1020" to "Aluminum Cans: Aluminum cans typically range from ₹18 to ₹36 per kilogram.\n" +
                "\n" +
                "Plastic Bottles: Depending on the type of plastic and local demand, plastic bottles can be worth anywhere from ₹8 to ₹20 per kilogram.\n" +
                "\n" +
                "Glass Bottles: Clear glass bottles usually fetch around ₹4 to ₹8 per kilogram, while colored glass may have slightly lower rates.\n" +
                "\n" +
                "Newspapers: Newspapers are generally valued at around ₹1 to ₹2 per kilogram.\n" +
                "\n" +
                "Cardboard: Cardboard prices vary but usually fall between ₹1 to ₹5 per kilogram, depending on its condition and demand.\n" +
                "\n" +
                "Steel Cans: Steel cans typically bring in around ₹4 to ₹8 per kilogram.\n" +
                "\n" +
                "Electronics (E-Waste): Electronics recycling rates can vary widely based on the type and condition of the electronics. Some items, like old cell phones or computer parts, may have recycling incentives or trade-in programs.\n" +
                "\n" +
                "Car Batteries: Car batteries are usually worth around ₹350 to ₹700 each when recycled.",
        "https://lh3.googleusercontent.com/p/AF1QipOPryyhIr66FIcffj9TyMSHGK4nzKQiA5C3Rj4d=s1360-w1360-h1020" to "Aluminum Cans: Aluminum cans typically range from ₹18 to ₹36 per kilogram.\n" +
                "\n" +
                "Plastic Bottles: Depending on the type of plastic and local demand, plastic bottles can be worth anywhere from ₹8 to ₹20 per kilogram.\n" +
                "\n" +
                "Glass Bottles: Clear glass bottles usually fetch around ₹4 to ₹8 per kilogram, while colored glass may have slightly lower rates.\n" +
                "\n" +
                "Newspapers: Newspapers are generally valued at around ₹1 to ₹2 per kilogram.\n" +
                "\n" +
                "Cardboard: Cardboard prices vary but usually fall between ₹1 to ₹5 per kilogram, depending on its condition and demand.\n" +
                "\n" +
                "Steel Cans: Steel cans typically bring in around ₹4 to ₹8 per kilogram.\n" +
                "\n" +
                "Electronics (E-Waste): Electronics recycling rates can vary widely based on the type and condition of the electronics. Some items, like old cell phones or computer parts, may have recycling incentives or trade-in programs.\n" +
                "\n" +
                "Car Batteries: Car batteries are usually worth around ₹350 to ₹700 each when recycled.",
        "https://lh3.googleusercontent.com/p/AF1QipM6Cf9xLPRmGWz8MuA_vnppz2C_9zQDSW9NzCUD=s1360-w1360-h1020" to "Aluminum Cans: Aluminum cans typically range from ₹18 to ₹36 per kilogram.\n" +
                "\n" +
                "Plastic Bottles: Depending on the type of plastic and local demand, plastic bottles can be worth anywhere from ₹8 to ₹20 per kilogram.\n" +
                "\n" +
                "Glass Bottles: Clear glass bottles usually fetch around ₹4 to ₹8 per kilogram, while colored glass may have slightly lower rates.\n" +
                "\n" +
                "Newspapers: Newspapers are generally valued at around ₹1 to ₹2 per kilogram.\n" +
                "\n" +
                "Cardboard: Cardboard prices vary but usually fall between ₹1 to ₹5 per kilogram, depending on its condition and demand.\n" +
                "\n" +
                "Steel Cans: Steel cans typically bring in around ₹4 to ₹8 per kilogram.\n" +
                "\n" +
                "Electronics (E-Waste): Electronics recycling rates can vary widely based on the type and condition of the electronics. Some items, like old cell phones or computer parts, may have recycling incentives or trade-in programs.\n" +
                "\n" +
                "Car Batteries: Car batteries are usually worth around ₹350 to ₹700 each when recycled.",
        "https://lh3.googleusercontent.com/p/AF1QipP_OHP8Rs8p_4dXn_jC3E9vomlWokKhfypMXKLu=s1360-w1360-h1020" to "Aluminum Cans: Aluminum cans typically range from ₹18 to ₹36 per kilogram.\n" +
                "\n" +
                "Plastic Bottles: Depending on the type of plastic and local demand, plastic bottles can be worth anywhere from ₹8 to ₹20 per kilogram.\n" +
                "\n" +
                "Glass Bottles: Clear glass bottles usually fetch around ₹4 to ₹8 per kilogram, while colored glass may have slightly lower rates.\n" +
                "\n" +
                "Newspapers: Newspapers are generally valued at around ₹1 to ₹2 per kilogram.\n" +
                "\n" +
                "Cardboard: Cardboard prices vary but usually fall between ₹1 to ₹5 per kilogram, depending on its condition and demand.\n" +
                "\n" +
                "Steel Cans: Steel cans typically bring in around ₹4 to ₹8 per kilogram.\n" +
                "\n" +
                "Electronics (E-Waste): Electronics recycling rates can vary widely based on the type and condition of the electronics. Some items, like old cell phones or computer parts, may have recycling incentives or trade-in programs.\n" +
                "\n" +
                "Car Batteries: Car batteries are usually worth around ₹350 to ₹700 each when recycled.",
    )

    private val cityImagesMap = mapOf(
        "Bengaluru" to listOf(
            "https://lh5.googleusercontent.com/p/AF1QipNCjCLXT4YpqGJx5niw9zTFqjDJQROoblMUyjvw=w260-h175-n-k-no" ,
            "https://lh3.googleusercontent.com/p/AF1QipOZhNTyx7J9lX7hE7IiMbCQWrrcjzoaj01CSVyQ=s1360-w1360-h1020",
            "https://lh3.googleusercontent.com/p/AF1QipN1DEeXjyHhfx2YKAaELpOZ1dzLMm-uruCTyka6=s1360-w1360-h1020"
        ),
        "Mumbai" to listOf(
            "https://lh3.googleusercontent.com/p/AF1QipOGtJ8S9e1LFaFxaRsrZziCRltcXsI12tKRR4WV=s1360-w1360-h1020",
            "https://streetviewpixels-pa.googleapis.com/v1/thumbnail?panoid=XzSEQL57lR6Zs-1PneSlCw&cb_client=search.gws-prod.gps&yaw=285.33093&pitch=0&thumbfov=100&w=260&h=175",
            "https://www.google.com/maps/dir//Spas+Recycling+Pvt+Ltd,+Unit+No.7,Hema+Industrial+Estate,Sarvodaya+Nagar,,+MHB+Colony+Rd,+Meghwadi,+Indira+Nagar,+Jogeshwari+East,+Mumbai,+Maharashtra+400060/data=!4m6!4m5!1m1!4e2!1m2!1m1!1s0x3be7b7d791f6d6d5:0x3bbed3a9524c3625?sa=X&ved=1t:57443&ictx=111"
        ),
        "Delhi" to listOf(
            "https://lh3.googleusercontent.com/p/AF1QipONbUso0kSxswE65xYj20Bhui-ggLzp4OpsOOGI=s1360-w1360-h1020",
            "https://lh3.googleusercontent.com/p/AF1QipPPpms0TufS-tcQPVt6hvSqB032hQlwJuTl-lIH=s1360-w1360-h1020",
            "https://lh3.googleusercontent.com/p/AF1QipMT0nNEhxy_2wgLkPqRiXhvnoENZ-M-5NGYpHnp=s1360-w1360-h1020"
        ),
        "Chennai" to listOf(
            "https://lh5.googleusercontent.com/p/AF1QipPhceWJwntLSIXZOr-IyYM8dUqi2xLxiyQBgyx3=w260-h175-n-k-no",
            "https://lh3.googleusercontent.com/p/AF1QipMnxFNmprt6v1rq8rBm6TpxHGyRbvx63uFuXRoQ=s1360-w1360-h1020",
            "https://www.google.com/maps?sca_esv=8e47e8b36fbcdee8&lqi=ChRyZWN5Y2xlIHNob3AgY2hlbm5haUi3yKXqzriAgAhaLBAAEAEYABgBGAIiFHJlY3ljbGUgc2hvcCBjaGVubmFpKgQIFBABKgQIAxAAkgESc2NyYXBfbWV0YWxfZGVhbGVyqgFPEAEqECIMcmVjeWNsZSBzaG9wKAAyHxABIhsOiK0N1WGnaTafkJGqxpuCCr09IOdp2S2ZtnwyGBACIhRyZWN5Y2xlIHNob3AgY2hlbm5haQ&phdesc=mPqSuG78CJY&vet=12ahUKEwj_uP3o6ruFAxV8T2wGHfzWATYQ8UF6BAgFEFg..i&lei=97cYZr_hKvyeseMP_K2HsAM&cs=1&um=1&ie=UTF-8&fb=1&gl=in&sa=X&geocode=KT9yxyQQXVI6Mb-1at6Xfb0B&daddr=No-9,+Omr+road,+Sri+Sowdeswari+Nagar,+Thoraipakkam,+Chennai,+Tamil+Nadu+600097"
        ),
        "Kolkata" to listOf(
            "https://lh3.googleusercontent.com/p/AF1QipPH1aEaDYzEjBlgF0qfcFy7K2SOHC5gS2yYp3E=s1360-w1360-h1020",
            "https://lh3.googleusercontent.com/p/AF1QipO-yHA-oWquOAdvmAlyIVaG30zOKHj1yGq0Xd5Y=s1360-w1360-h1020",
        ),
        "Hyderabad" to listOf(
            "https://lh3.googleusercontent.com/p/AF1QipOPryyhIr66FIcffj9TyMSHGK4nzKQiA5C3Rj4d=s1360-w1360-h1020",
            "https://lh3.googleusercontent.com/p/AF1QipM6Cf9xLPRmGWz8MuA_vnppz2C_9zQDSW9NzCUD=s1360-w1360-h1020",
            "https://lh3.googleusercontent.com/p/AF1QipP_OHP8Rs8p_4dXn_jC3E9vomlWokKhfypMXKLu=s1360-w1360-h1020"
        ),

    )
    private val customLinksMap = mapOf(
        "https://lh5.googleusercontent.com/p/AF1QipNCjCLXT4YpqGJx5niw9zTFqjDJQROoblMUyjvw=w260-h175-n-k-no" to "https://www.google.com/maps/dir/12.9883,77.5847/15%2F4,+6th+cross+Azad+Nagar,+near+Government+Library,+Chamrajpet,+Bengaluru,+Karnataka+560018/@12.9883,77.5847,12z/data=!4m9!4m8!1m1!4e1!1m5!1m1!1s0x3bae152ab381c5c1:0x570bef534b3e8883!2m2!1d77.5571269!2d12.9565324?entry=ttu",
        "https://lh3.googleusercontent.com/p/AF1QipOZhNTyx7J9lX7hE7IiMbCQWrrcjzoaj01CSVyQ=s1360-w1360-h1020" to "https://www.google.com/maps/dir//Bangalore+Ewaste+Recyle+center(scrap+pickup),+154,+4th+A+Cross+Rd,+Seethappa+Layout,+Bangalore,+Bengaluru,+Karnataka+560032/data=!4m6!4m5!1m1!4e2!1m2!1m1!1s0x3bae171ba50c7bd3:0x4fc20a7682a35418?sa=X&ved=1t:57443&ictx=111",
        "https://lh3.googleusercontent.com/p/AF1QipN1DEeXjyHhfx2YKAaELpOZ1dzLMm-uruCTyka6=s1360-w1360-h1020" to "https://www.google.com/maps/dir/12.9883,77.5847/No+23,+E-Waste+Collection+Centre+in+Bangalore,+23+rd+A,+Marenahalli+Rd,+2nd+Phase,+J.+P.+Nagar,+Bengaluru,+Karnataka+540040/@12.9521286,77.542019,13z/data=!3m1!4b1!4m9!4m8!1m1!4e1!1m5!1m1!1s0x3bae1531285e6067:0xa6ab6630e3fbeb5!2m2!1d77.5863373!2d12.9147826?entry=ttu",
        "https://lh3.googleusercontent.com/p/AF1QipOGtJ8S9e1LFaFxaRsrZziCRltcXsI12tKRR4WV=s1360-w1360-h1020" to "https://www.google.com/maps/dir//1st+Floor,+Unit+No+31-32,+Ecostar+Recycling+-+E+Waste+Recycling+Mumbai,+Recycling+Centre,+Khuraiya+Estate,+CST+Road,+opposite+Ashok+Apartments,+Kalina,+Santacruz+East,+Mumbai,+Maharashtra+400098/data=!4m6!4m5!1m1!4e2!1m2!1m1!1s0x3be7c929f585420f:0x24c9426bb0e18797?sa=X&ved=1t:57443&ictx=111",
        "https://streetviewpixels-pa.googleapis.com/v1/thumbnail?panoid=XzSEQL57lR6Zs-1PneSlCw&cb_client=search.gws-prod.gps&yaw=285.33093&pitch=0&thumbfov=100&w=260&h=175" to "https://www.google.com/maps/dir//Spas+Recycling+Pvt+Ltd,+Unit+No.7,Hema+Industrial+Estate,Sarvodaya+Nagar,,+MHB+Colony+Rd,+Meghwadi,+Indira+Nagar,+Jogeshwari+East,+Mumbai,+Maharashtra+400060/data=!4m6!4m5!1m1!4e2!1m2!1m1!1s0x3be7b7d791f6d6d5:0x3bbed3a9524c3625?sa=X&ved=1t:57443&ictx=111",
        "https://www.google.com/maps/dir//Spas+Recycling+Pvt+Ltd,+Unit+No.7,Hema+Industrial+Estate,Sarvodaya+Nagar,,+MHB+Colony+Rd,+Meghwadi,+Indira+Nagar,+Jogeshwari+East,+Mumbai,+Maharashtra+400060/data=!4m6!4m5!1m1!4e2!1m2!1m1!1s0x3be7b7d791f6d6d5:0x3bbed3a9524c3625?sa=X&ved=1t:57443&ictx=111" to "https://www.google.com/maps/dir//BEST+RECYCLING+COMPANY+IN+MUMBAI,E-WASTE+SCRAP+BUYERS+IN+MUMBAI,COMPUTER+SCRAP+BUYERS+MUMBAI,E-WASTE+RECYCLING+COMPANY+MUMBAI,+A-103+SHIV+SHAKTI,CHS+1ST+FLOOR,ANDHERI+RTO,+Anna+Nagar,+Andheri+West,+Mumbai,+Maharashtra+400053/data=!4m6!4m5!1m1!4e2!1m2!1m1!1s0x3be7b765e3e43df3:0x5e91db5562b8ecce?sa=X&ved=1t:57443&ictx=111",
        "https://lh3.googleusercontent.com/p/AF1QipONbUso0kSxswE65xYj20Bhui-ggLzp4OpsOOGI=s1360-w1360-h1020" to "https://www.google.com/maps?sca_esv=8e47e8b36fbcdee8&lqi=ChJyZWN5Y2xlIHNob3AgZGVsaGlIgoD3rNSVgIAIWiQQABABGAAYAiIScmVjeWNsZSBzaG9wIGRlbGhpKgYIAxAAEAGSARh3YXN0ZV9tYW5hZ2VtZW50X3NlcnZpY2WqAWEKCC9tLzA5ZjA3CggvbS8wajZ2NxABKhAiDHJlY3ljbGUgc2hvcCgAMh8QASIbUhW4QG11ojOA6sGQBSq1g4LZysAS3j7iwK-YMhYQAiIScmVjeWNsZSBzaG9wIGRlbGhp&phdesc=g4gzbWvGJJo&vet=12ahUKEwi3pvfd6buFAxUr2jgGHWWmABEQ8UF6BAgFEFg..i&lei=1LYYZrfsBau04-EP5cyCiAE&cs=1&um=1&ie=UTF-8&fb=1&gl=in&sa=X&geocode=KZV6en7IBA05MVcmL2_aqVMX&daddr=Block+RZ,+169,+Raghu+Nagar,+Dabri,+New+Delhi,+Delhi+110045",
        "https://lh3.googleusercontent.com/p/AF1QipPPpms0TufS-tcQPVt6hvSqB032hQlwJuTl-lIH=s1360-w1360-h1020" to "https://www.google.com/maps/dir//D442,+07,+Ramphal+Chowk+Rd,+near+k+c+restaurant,+Sector+6+Dwarka,+New+Delhi,+Delhi+110075/@28.5891566,76.9893745,12z/data=!4m8!4m7!1m0!1m5!1m1!1s0x390d1bc35c97894f:0x1bc960dd431bcf84!2m2!1d77.0717759!2d28.5891815?entry=ttu",
        "https://lh3.googleusercontent.com/p/AF1QipMT0nNEhxy_2wgLkPqRiXhvnoENZ-M-5NGYpHnp=s1360-w1360-h1020" to "https://lh3.googleusercontent.com/p/AF1QipMT0nNEhxy_2wgLkPqRiXhvnoENZ-M-5NGYpHnp=s1360-w1360-h1020",
        "https://lh5.googleusercontent.com/p/AF1QipPhceWJwntLSIXZOr-IyYM8dUqi2xLxiyQBgyx3=w260-h175-n-k-no" to "https://www.google.com/maps/dir//No+30,+2nd+Floor,+Mylai+Ranganathan+St,+T.+Nagar,+Chennai,+Tamil+Nadu+600017/@13.0370887,80.1555524,12z/data=!3m1!4b1!4m8!4m7!1m0!1m5!1m1!1s0x3a526704ba076e31:0xb20e3901f20a6d66!2m2!1d80.2379543!2d13.0371017?entry=ttu",
        "https://lh3.googleusercontent.com/p/AF1QipMnxFNmprt6v1rq8rBm6TpxHGyRbvx63uFuXRoQ=s1360-w1360-h1020" to "https://www.google.com/maps?sca_esv=8e47e8b36fbcdee8&lqi=ChRyZWN5Y2xlIHNob3AgY2hlbm5haUi3yKXqzriAgAhaLBAAEAEYABgBGAIiFHJlY3ljbGUgc2hvcCBjaGVubmFpKgQIFBABKgQIAxAAkgESc2NyYXBfbWV0YWxfZGVhbGVyqgFPEAEqECIMcmVjeWNsZSBzaG9wKAAyHxABIhsOiK0N1WGnaTafkJGqxpuCCr09IOdp2S2ZtnwyGBACIhRyZWN5Y2xlIHNob3AgY2hlbm5haQ&phdesc=mPqSuG78CJY&vet=12ahUKEwj_uP3o6ruFAxV8T2wGHfzWATYQ8UF6BAgFEFg..i&lei=97cYZr_hKvyeseMP_K2HsAM&cs=1&um=1&ie=UTF-8&fb=1&gl=in&sa=X&geocode=KT9yxyQQXVI6Mb-1at6Xfb0B&daddr=No-9,+Omr+road,+Sri+Sowdeswari+Nagar,+Thoraipakkam,+Chennai,+Tamil+Nadu+600097",
        "https://www.google.com/maps?sca_esv=8e47e8b36fbcdee8&lqi=ChRyZWN5Y2xlIHNob3AgY2hlbm5haUi3yKXqzriAgAhaLBAAEAEYABgBGAIiFHJlY3ljbGUgc2hvcCBjaGVubmFpKgQIFBABKgQIAxAAkgESc2NyYXBfbWV0YWxfZGVhbGVyqgFPEAEqECIMcmVjeWNsZSBzaG9wKAAyHxABIhsOiK0N1WGnaTafkJGqxpuCCr09IOdp2S2ZtnwyGBACIhRyZWN5Y2xlIHNob3AgY2hlbm5haQ&phdesc=mPqSuG78CJY&vet=12ahUKEwj_uP3o6ruFAxV8T2wGHfzWATYQ8UF6BAgFEFg..i&lei=97cYZr_hKvyeseMP_K2HsAM&cs=1&um=1&ie=UTF-8&fb=1&gl=in&sa=X&geocode=KT9yxyQQXVI6Mb-1at6Xfb0B&daddr=No-9,+Omr+road,+Sri+Sowdeswari+Nagar,+Thoraipakkam,+Chennai,+Tamil+Nadu+600097" to "https://www.google.com/maps/dir//5,+Triplicane+High+Rd,+Ellis+Puram,+Padupakkam,+Triplicane,+Chennai,+Tamil+Nadu+600005/@13.0665409,80.1911581,12z/data=!3m1!4b1!4m8!4m7!1m0!1m5!1m1!1s0x3a526774419c8057:0xd762ef341de0db72!2m2!1d80.27356!2d13.0665539?entry=ttu",
        "https://lh3.googleusercontent.com/p/AF1QipPH1aEaDYzEjBlgF0qfcFy7K2SOHC5gS2yYp3E=s1360-w1360-h1020" to "https://www.google.com/maps?sca_esv=8e47e8b36fbcdee8&lqi=ChRyZWN5Y2xlIHNob3Aga29sa2F0YUi13MGkobiAgAhaKhAAEAEYABgCIhRyZWN5Y2xlIHNob3Aga29sa2F0YSoECAMQACoECBQQAXoHS29sa2F0YZIBGHdhc3RlX21hbmFnZW1lbnRfc2VydmljZZoBJENoZERTVWhOTUc5blMwVkpRMEZuU1VOcGEwdFFTREJuUlJBQqoBYwoIL20vMGN2dzkKCC9tLzBqNnY3EAEqECIMcmVjeWNsZSBzaG9wKAAyHxABIhsN3hEw_azmNxvbLmfQvtJ67h5n8m3mZ4BhSnoyGBACIhRyZWN5Y2xlIHNob3Aga29sa2F0YQ&phdesc=BkkM8XdFmCI&vet=12ahUKEwiurYq367uFAxWtyTgGHdjUBjoQ8UF6BAgFEFg..i&lei=m7gYZu7zHa2T4-EP2Kmb0AM&cs=1&um=1&ie=UTF-8&fb=1&gl=in&sa=X&geocode=Kf__6zUEdwI6MV2Ih1Az4NLO&daddr=9A,+Marquis+St,+Esplanade,+Park+Street+area,+Kolkata,+West+Bengal+700016",
        "https://lh3.googleusercontent.com/p/AF1QipO-yHA-oWquOAdvmAlyIVaG30zOKHj1yGq0Xd5Y=s1360-w1360-h1020" to "https://www.google.com/maps?sca_esv=8e47e8b36fbcdee8&lqi=ChRyZWN5Y2xlIHNob3Aga29sa2F0YUie4-b9jK-AgAhaKhAAEAEYARgCIhRyZWN5Y2xlIHNob3Aga29sa2F0YSoECAMQACoECBQQAZIBG3VzZWRfb2ZmaWNlX2Z1cm5pdHVyZV9zdG9yZaoBTxABKhAiDHJlY3ljbGUgc2hvcCgAMh8QASIbDd4RMP2s5jcb2y5n0L7Seu4eZ_Jt5meAYUp6MhgQAiIUcmVjeWNsZSBzaG9wIGtvbGthdGE&phdesc=iXD-GdyDxqU&vet=12ahUKEwiurYq367uFAxWtyTgGHdjUBjoQ8UF6BAgFEFg..i&lei=m7gYZu7zHa2T4-EP2Kmb0AM&cs=1&um=1&ie=UTF-8&fb=1&gl=in&sa=X&geocode=Kf-abAPMdwI6MXRw6azcme0T&daddr=33H,+B,+1,+Mirza+Ghalib+St,+Kolkata,+West+Bengal+700016",
        "https://lh3.googleusercontent.com/p/AF1QipOPryyhIr66FIcffj9TyMSHGK4nzKQiA5C3Rj4d=s1360-w1360-h1020" to "https://www.google.com/maps?sca_esv=8e47e8b36fbcdee8&lqi=ChZyZWN5Y2xlIHNob3AgaHlkZXJhYmFkSNm055q6rYCACFogEAAQARgAGAIiFnJlY3ljbGUgc2hvcCBoeWRlcmFiYWSSAQ5zY3JhcF9tZXJjaGFudJoBJENoZERTVWhOTUc5blMwVkpRMEZuU1VOVGVUa3RlWEIzUlJBQqoBURABKhAiDHJlY3ljbGUgc2hvcCgAMh8QASIbUWpDus879qGL6i1tNmogqNxKNZN0U8CMccJMMhoQAiIWcmVjeWNsZSBzaG9wIGh5ZGVyYWJhZA&vet=12ahUKEwjdjoyT7LuFAxWy3TgGHSDKDHUQ8UF6BAgZEFg..i&lei=XLkYZt3wG7K74-EPoJSzqAc&cs=1&um=1&ie=UTF-8&fb=1&gl=in&sa=X&geocode=Ke0j5RhImcs7MfwGPb-hnZvi&daddr=16-9-749/95,+Race+Course+Rd,+Papaiah+Basthi,+Old+Malakpet,+Hyderabad,+Telangana+500036",
        "https://lh3.googleusercontent.com/p/AF1QipM6Cf9xLPRmGWz8MuA_vnppz2C_9zQDSW9NzCUD=s1360-w1360-h1020" to "https://www.google.com/maps?sca_esv=8e47e8b36fbcdee8&lqi=ChZyZWN5Y2xlIHNob3AgaHlkZXJhYmFkSPT01e6muYCACFogEAAQARgAGAIiFnJlY3ljbGUgc2hvcCBoeWRlcmFiYWR6CUh5ZGVyYWJhZJIBGHdhc3RlX21hbmFnZW1lbnRfc2VydmljZZoBI0NoWkRTVWhOTUc5blMwVkpRMEZuU1VOV2FFazJVVkZCRUFFqgFlCggvbS8wOWM2dwoIL20vMGo2djcQASoQIgxyZWN5Y2xlIHNob3AoADIfEAEiG1FqQ7rPO_ahi-otbTZqIKjcSjWTdFPAjHHCTDIaEAIiFnJlY3ljbGUgc2hvcCBoeWRlcmFiYWQ&vet=12ahUKEwjdjoyT7LuFAxWy3TgGHSDKDHUQ8UF6BAgZEFg..i&lei=XLkYZt3wG7K74-EPoJSzqAc&cs=1&um=1&ie=UTF-8&fb=1&gl=in&sa=X&geocode=KefwqsR1l8s7Ma6FJDEIj_nS&daddr=8-2-601/G/292,+Gouri+Shankar+Nagar+Colony,+Banjara+Hills,+Hyderabad,+Telangana+500034",
        "https://lh3.googleusercontent.com/p/AF1QipP_OHP8Rs8p_4dXn_jC3E9vomlWokKhfypMXKLu=s1360-w1360-h1020" to "https://www.google.com/maps/dir/12.9634,77.5855/RECYCLE+ONE,+16-9-406%2FA%2F54%2F1,+old+malakpet,+near+zaqriya+mosque,+Wahed+Nagar,+Amberpet,+Hyderabad,+Telangana+500036/@15.1615434,75.3605467,7z/data=!3m1!4b1!4m9!4m8!1m1!4e1!1m5!1m1!1s0x3bcb9941566b1a07:0xaca59e61776fadad!2m2!1d78.5036849!2d17.3849115?entry=ttu",

    )
    private lateinit var spinner: Spinner
    private lateinit var imageContainer: LinearLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_exchange_point, container, false)

        spinner = view.findViewById(R.id.spinner)
        imageContainer = view.findViewById(R.id.imageContainer)

        val citiesAdapter = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.metropolitan_cities,
            android.R.layout.simple_spinner_item
        ).apply {
            setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        }
        spinner.adapter = citiesAdapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedCity = parent?.getItemAtPosition(position).toString()
                displayImages(selectedCity)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Do nothing
            }
        }
        return view
    }

    private fun displayImages(selectedCity: String) {
        val images = cityImagesMap[selectedCity] ?: emptyList()
        imageContainer.removeAllViews()
        for (imageUrl in images) {
            val image = ImageView(requireContext())
            Glide.with(this)
                .load(imageUrl)
                .into(image)
            image.setOnClickListener {
                openLink(customLinksMap[imageUrl] ?: imageUrl)
            }

            val imageText = imageTextMap[imageUrl] ?: ""

            val textView = TextView(requireContext())
            textView.text = imageText

            val container = LinearLayout(requireContext())
            container.orientation = LinearLayout.VERTICAL
            container.addView(image)
            container.addView(textView)

            imageContainer.addView(container)

            // Add spacer view between images
            val spacer = View(requireContext())
            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                35 // Adjust the spacing here as needed
            )
            spacer.layoutParams = params
            imageContainer.addView(spacer)
        }
    }


    private fun openLink(link: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
        startActivity(intent)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}
