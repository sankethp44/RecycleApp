package com.example.recycleapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ExchangePointFragment : Fragment() {

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
            "https://example.com/mumbai1.jpg",
            "https://example.com/mumbai2.jpg",
            "https://example.com/mumbai3.jpg"
        ),
        "Chennai" to listOf(
            "https://example.com/mumbai1.jpg",
            "https://example.com/mumbai2.jpg",
            "https://example.com/mumbai3.jpg"
        ),
        "Kolkata" to listOf(
            "https://example.com/mumbai1.jpg",
            "https://example.com/mumbai2.jpg",
            "https://example.com/mumbai3.jpg"
        ),
        "Hyderabad" to listOf(
            "https://example.com/mumbai1.jpg",
            "https://example.com/mumbai2.jpg",
            "https://example.com/mumbai3.jpg"
        ),
        "Pune" to listOf(
            "https://example.com/mumbai1.jpg",
            "https://example.com/mumbai2.jpg",
            "https://example.com/mumbai3.jpg"
        ),
        "Ahmedabad" to listOf(
            "https://example.com/mumbai1.jpg",
            "https://example.com/mumbai2.jpg",
            "https://example.com/mumbai3.jpg"
        ),
        "Jaipur" to listOf(
            "https://example.com/mumbai1.jpg",
            "https://example.com/mumbai2.jpg",
            "https://example.com/mumbai3.jpg"
        )
    )
    private val customLinksMap = mapOf(
        "https://lh5.googleusercontent.com/p/AF1QipNCjCLXT4YpqGJx5niw9zTFqjDJQROoblMUyjvw=w260-h175-n-k-no" to "https://www.google.com/maps/dir/12.9883,77.5847/15%2F4,+6th+cross+Azad+Nagar,+near+Government+Library,+Chamrajpet,+Bengaluru,+Karnataka+560018/@12.9883,77.5847,12z/data=!4m9!4m8!1m1!4e1!1m5!1m1!1s0x3bae152ab381c5c1:0x570bef534b3e8883!2m2!1d77.5571269!2d12.9565324?entry=ttu",
        "https://lh3.googleusercontent.com/p/AF1QipOZhNTyx7J9lX7hE7IiMbCQWrrcjzoaj01CSVyQ=s1360-w1360-h1020" to "https://www.google.com/maps/dir//Bangalore+Ewaste+Recyle+center(scrap+pickup),+154,+4th+A+Cross+Rd,+Seethappa+Layout,+Bangalore,+Bengaluru,+Karnataka+560032/data=!4m6!4m5!1m1!4e2!1m2!1m1!1s0x3bae171ba50c7bd3:0x4fc20a7682a35418?sa=X&ved=1t:57443&ictx=111",
        "https://lh3.googleusercontent.com/p/AF1QipN1DEeXjyHhfx2YKAaELpOZ1dzLMm-uruCTyka6=s1360-w1360-h1020" to "https://www.google.com/maps/dir/12.9883,77.5847/No+23,+E-Waste+Collection+Centre+in+Bangalore,+23+rd+A,+Marenahalli+Rd,+2nd+Phase,+J.+P.+Nagar,+Bengaluru,+Karnataka+540040/@12.9521286,77.542019,13z/data=!3m1!4b1!4m9!4m8!1m1!4e1!1m5!1m1!1s0x3bae1531285e6067:0xa6ab6630e3fbeb5!2m2!1d77.5863373!2d12.9147826?entry=ttu",
        "https://lh3.googleusercontent.com/p/AF1QipOGtJ8S9e1LFaFxaRsrZziCRltcXsI12tKRR4WV=s1360-w1360-h1020" to "https://www.google.com/maps/dir//1st+Floor,+Unit+No+31-32,+Ecostar+Recycling+-+E+Waste+Recycling+Mumbai,+Recycling+Centre,+Khuraiya+Estate,+CST+Road,+opposite+Ashok+Apartments,+Kalina,+Santacruz+East,+Mumbai,+Maharashtra+400098/data=!4m6!4m5!1m1!4e2!1m2!1m1!1s0x3be7c929f585420f:0x24c9426bb0e18797?sa=X&ved=1t:57443&ictx=111",
        "https://streetviewpixels-pa.googleapis.com/v1/thumbnail?panoid=XzSEQL57lR6Zs-1PneSlCw&cb_client=search.gws-prod.gps&yaw=285.33093&pitch=0&thumbfov=100&w=260&h=175" to "https://www.google.com/maps/dir//Spas+Recycling+Pvt+Ltd,+Unit+No.7,Hema+Industrial+Estate,Sarvodaya+Nagar,,+MHB+Colony+Rd,+Meghwadi,+Indira+Nagar,+Jogeshwari+East,+Mumbai,+Maharashtra+400060/data=!4m6!4m5!1m1!4e2!1m2!1m1!1s0x3be7b7d791f6d6d5:0x3bbed3a9524c3625?sa=X&ved=1t:57443&ictx=111",
        "https://www.google.com/maps/dir//Spas+Recycling+Pvt+Ltd,+Unit+No.7,Hema+Industrial+Estate,Sarvodaya+Nagar,,+MHB+Colony+Rd,+Meghwadi,+Indira+Nagar,+Jogeshwari+East,+Mumbai,+Maharashtra+400060/data=!4m6!4m5!1m1!4e2!1m2!1m1!1s0x3be7b7d791f6d6d5:0x3bbed3a9524c3625?sa=X&ved=1t:57443&ictx=111" to "https://www.google.com/maps/dir//BEST+RECYCLING+COMPANY+IN+MUMBAI,E-WASTE+SCRAP+BUYERS+IN+MUMBAI,COMPUTER+SCRAP+BUYERS+MUMBAI,E-WASTE+RECYCLING+COMPANY+MUMBAI,+A-103+SHIV+SHAKTI,CHS+1ST+FLOOR,ANDHERI+RTO,+Anna+Nagar,+Andheri+West,+Mumbai,+Maharashtra+400053/data=!4m6!4m5!1m1!4e2!1m2!1m1!1s0x3be7b765e3e43df3:0x5e91db5562b8ecce?sa=X&ved=1t:57443&ictx=111",
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
        imageContainer.removeAllViews() // Clear existing images
        for (imageUrl in images) {
            val image = ImageView(requireContext())
            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            params.setMargins(8, 8, 8, 8) // Add margins between images
            image.layoutParams = params
            Glide.with(this)
                .load(imageUrl)
                .into(image)
            image.setOnClickListener {
                // Open the custom link if available, otherwise open the image URL
                openLink(customLinksMap[imageUrl] ?: imageUrl)
            }

            imageContainer.addView(image) // Add image to the container
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
