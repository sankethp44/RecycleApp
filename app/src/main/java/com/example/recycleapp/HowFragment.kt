import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.core.view.ViewCompat
import com.example.recycleapp.R
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.android.material.floatingactionbutton.FloatingActionButton

class HowFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_how, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        hideFab()

        val textViewHow = view.findViewById<TextView>(R.id.TextView)
        ViewCompat.setTransitionName(textViewHow, "howText")

        // Set the paragraph text for textViewHow
        val paragraphText = """
            Our app is designed to help users understand the recycling process and make eco-friendly choices in their everyday lives. Here's how you can use our app:

            1. Scan Objects: Use the scanner feature to determine whether an object is recyclable or not.

            2. Find Exchange Points: Locate nearby exchange points where you can drop off recyclable items.

            3. Earn Points: Earn points every time you recycle. These points can be redeemed for rewards in our app.

            4. Shop: Browse through our store and purchase eco-friendly products using your earned points.

            5. Get Involved: Spread awareness about recycling and environmental sustainability by sharing our app with friends and family.

            We hope our app inspires you to adopt greener habits and contribute to a healthier planet. Thank you for joining us in the journey towards a more sustainable future!
        """.trimIndent()

        textViewHow.text = paragraphText
    }

    private fun hideFab() {
        val extendedFabHome: ExtendedFloatingActionButton =
            requireActivity().findViewById(R.id.extendedFabHome)
        extendedFabHome.visibility = View.GONE
    }

    private fun showFab() {
        val extendedFab: ExtendedFloatingActionButton = requireActivity().findViewById(R.id.extendedFab)
        extendedFab.visibility = View.VISIBLE

        val regularFab: FloatingActionButton = requireActivity().findViewById(R.id.fab)
        regularFab.visibility = View.VISIBLE

        val appbarhome: BottomAppBar =
            requireActivity().findViewById(R.id.bottomAppBar)
        appbarhome.visibility = View.VISIBLE

        val home: BottomNavigationView =
            requireActivity().findViewById(R.id.bottom_navigation)
        home.visibility = View.VISIBLE
    }

    override fun onResume() {
        super.onResume()
        showFab()
    }
}
