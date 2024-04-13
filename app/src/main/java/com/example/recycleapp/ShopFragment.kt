package com.example.recycleapp

import android.app.AlertDialog
import kotlin.Triple
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class ShopFragment : Fragment() {

    private var totalPrice: Double = 0.0
    private lateinit var productContainer: LinearLayout
    private lateinit var checkoutButton: Button

    private lateinit var database: FirebaseDatabase
    private lateinit var userPointsRef: DatabaseReference
    private lateinit var selectedProducts: List<Triple<String, Double, Int>>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_shop, container, false)

        productContainer = view.findViewById(R.id.productContainer)
        checkoutButton = view.findViewById(R.id.checkoutButton)

        // Initialize Firebase database
        database = FirebaseDatabase.getInstance()
        val userId = FirebaseAuth.getInstance().currentUser?.uid
        userPointsRef = database.getReference("Users").child(userId ?: "").child("points")

        displayProducts()

        // Handle checkout button click
        checkoutButton.setOnClickListener {
            calculateTotalPrice()
            showCheckoutDialog(totalPrice)
        }

        return view
    }
    private fun showCheckoutDialog(totalPrice: Double) {
        val dialogView = layoutInflater.inflate(R.layout.dialog_checkout, null)
        val addressEditText = dialogView.findViewById<EditText>(R.id.addressEditText)
        val totalAmountTextView = dialogView.findViewById<TextView>(R.id.totalAmountTextView)

        totalAmountTextView.text = "Total Amount: ₹${String.format("%.2f", totalPrice)}"

        val dialogBuilder = AlertDialog.Builder(requireContext())
            .setView(dialogView)
            .setPositiveButton("OK") { dialog, _ ->
                val address = addressEditText.text.toString()
                if (address.isNotEmpty()) {
                    // Update Firebase database with user's address and deduct total amount from balance
                    val userId = FirebaseAuth.getInstance().currentUser?.uid
                    userId?.let { uid ->
                        val userRef = FirebaseDatabase.getInstance().getReference("Users").child(uid)
                        userRef.child("address").setValue(address) { databaseError, _ ->
                            if (databaseError == null) {
                                // Address updated successfully, now deduct the total amount from balance
                                deductTotalAmountFromBalance(totalPrice)
                            } else {
                                showToast("Failed to update address")
                            }
                        }
                    }
                    dialog.dismiss()
                } else {
                    showToast("Please enter your address")
                }
            }
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.cancel()
            }

        val dialog = dialogBuilder.create()
        dialog.show()
    }
    private fun displayProducts() {
        val product1 = Triple("Doms Single Line Note Book", 105.99, R.drawable.product1)
        val product2 = Triple("Apsara Scholars School Kit", 59.99, R.drawable.product2)
        val product3 = Triple("Jumbo Wax Crayons 12 Shades", 39.99, R.drawable.product3)
        val product4 = Triple("Classmate Octane Ball Blister Blue", 29.99, R.drawable.product4)
        val product5 = Triple("Badminton Racket", 139.99, R.drawable.product5)
        val product6 = Triple("Universal Mobile Phone Holder", 35.99, R.drawable.product6)
        val product7 = Triple("Dearjoy Giraffe Soft Toy", 140.99, R.drawable.product7)
        val product8 = Triple("Uno Playing Card Game", 79.99, R.drawable.product8)
        val product9 = Triple("Kwality Wall's Magnum Truffle Combo", 100.99, R.drawable.product9)

        selectedProducts = listOf(product1, product2, product3, product4,product5,product6,product7,product8,product9)

        productContainer.addView(createProductView(product1))
        productContainer.addView(createProductView(product2))
        productContainer.addView(createProductView(product3))
        productContainer.addView(createProductView(product4))
        productContainer.addView(createProductView(product5))
        productContainer.addView(createProductView(product6))
        productContainer.addView(createProductView(product7))
        productContainer.addView(createProductView(product8))
        productContainer.addView(createProductView(product9))

    }

    private fun createProductView(product: Triple<String, Double, Int>): View {
        val productView = layoutInflater.inflate(R.layout.product_item, null)

        val productNameTextView: TextView = productView.findViewById(R.id.productNameTextView)
        val productPriceTextView: TextView = productView.findViewById(R.id.productPriceTextView)
        val productImageView: ImageView = productView.findViewById(R.id.productImageView)

        productNameTextView.text = product.first
        val priceInRupees = "₹${String.format("%.2f", product.second)}"
        productPriceTextView.text = priceInRupees
        productImageView.setImageResource(product.third)

        return productView
    }

    private fun calculateTotalPrice() {
        totalPrice = 0.0
        for ((index, product) in selectedProducts.withIndex()) {
            val checkbox = productContainer.getChildAt(index).findViewById<CheckBox>(R.id.productCheckbox)
            if (checkbox.isChecked) {
                totalPrice += product.second
            }
        }
    }


    private fun deductTotalAmountFromBalance(totalPrice: Double) {
        val userId = FirebaseAuth.getInstance().currentUser?.uid
        userId?.let { uid ->
            val userPointsRef = FirebaseDatabase.getInstance().getReference("Users").child(uid).child("points")
            userPointsRef.runTransaction(object : Transaction.Handler {
                override fun doTransaction(mutableData: MutableData): Transaction.Result {
                    val currentPoints = mutableData.getValue(Double::class.java) ?: 0.0
                    if (currentPoints >= totalPrice) {
                        mutableData.value = currentPoints - totalPrice
                        return Transaction.success(mutableData)
                    }
                    return Transaction.abort()
                }

                override fun onComplete(databaseError: DatabaseError?, committed: Boolean, dataSnapshot: DataSnapshot?) {
                    if (committed) {
                        showToast("Checkout successful!")
                    } else {
                        showToast("Not enough points")
                    }
                }
            })
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}