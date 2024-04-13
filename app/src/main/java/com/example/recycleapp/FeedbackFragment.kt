package com.example.recycleapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.android.material.floatingactionbutton.FloatingActionButton
import android.widget.Button
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.util.Properties
import javax.mail.*
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage
import com.sun.mail.smtp.SMTPTransport
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class FeedbackFragment : Fragment() {
    private lateinit var editTextName: EditText
    private lateinit var editTextFeedback: EditText
    private lateinit var buttonSubmit: Button

    private lateinit var mAuth: FirebaseAuth
    private lateinit var databaseReference: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_feedback, container, false)

        mAuth = FirebaseAuth.getInstance()
        databaseReference = FirebaseDatabase.getInstance().reference.child("Feedback")

        editTextName = view.findViewById(R.id.editTextName)
        editTextFeedback = view.findViewById(R.id.editTextFeedback)
        buttonSubmit = view.findViewById(R.id.buttonSubmit)

        buttonSubmit.setOnClickListener {
            submitFeedback()
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        hideFab()
    }

    private fun hideFab() {
        val extendedFab: ExtendedFloatingActionButton =
            requireActivity().findViewById(R.id.extendedFab)
        extendedFab.visibility = View.GONE

        val regularFab: FloatingActionButton =
            requireActivity().findViewById(R.id.fab)
        regularFab.visibility = View.GONE

        val extendedFabHome: ExtendedFloatingActionButton =
            requireActivity().findViewById(R.id.extendedFabHome)
        extendedFabHome.visibility = View.GONE

        val appbarhome: BottomAppBar = requireActivity().findViewById(R.id.bottomAppBar)
        appbarhome.visibility = View.GONE

        val home: BottomNavigationView =
            requireActivity().findViewById(R.id.bottom_navigation)
        home.visibility = View.GONE
    }


    private fun sendEmail(name: String, feedback: String, userEmail: String) {
        val properties = Properties().apply {
            put("mail.smtp.auth", "true")
            put("mail.smtp.starttls.enable", "true")
            put("mail.smtp.host", "smtp.gmail.com")
            put("mail.smtp.port", "587")
        }

        val session = Session.getInstance(properties, object : Authenticator() {
            override fun getPasswordAuthentication(): PasswordAuthentication {
                return PasswordAuthentication("spalaksha@gmail.com", "rwqabsibzsoagpom")
            }
        })

        val message = MimeMessage(session).apply {
            setFrom(InternetAddress("spalaksha@gmail.com"))
            setRecipients(Message.RecipientType.TO, InternetAddress.parse("sankethp673@gmail.com"))
            subject = "Thank you for your feedback $name"
            setText("We value your feedback, and resolve any issue if mentioned in your feedback asap. \n \nContact to this address if you need any help! \n \n \n \nThank you and have a nice day. ")
        }

        val transport = session.transport
        try {
            transport.connect("smtp.gmail.com", "spalaksha@gmail.com", "rwqabsibzsoagpom")
            transport.sendMessage(message, message.allRecipients)
            // Email sent successfully
        } catch (e: MessagingException) {
            e.printStackTrace()
            // Failed to send email
        } finally {
            transport.close()
        }
    }

    private fun submitFeedback() {
        val name = editTextName.text.toString().trim()
        val feedback = editTextFeedback.text.toString().trim()
        val userEmail = mAuth.currentUser?.email

        if (name.isEmpty() || feedback.isEmpty()) {
            Toast.makeText(requireContext(), "Name and Feedback cannot be empty", Toast.LENGTH_SHORT).show()
            return
        }

        // Save feedback to Firebase Realtime Database
        val feedbackId = databaseReference.push().key ?: ""
        val feedbackData = Feedback(name, feedback, userEmail ?: "")
        databaseReference.child(feedbackId).setValue(feedbackData)
            .addOnSuccessListener {
                sendEmailInBackground(name, feedback, userEmail ?: "")
                Toast.makeText(requireContext(), "Feedback submitted successfully", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener { e ->
                Toast.makeText(requireContext(), "Failed to submit feedback: ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun sendEmailInBackground(name: String, feedback: String, userEmail: String?) {
        GlobalScope.launch(Dispatchers.IO) {
            if (userEmail != null) {
                sendEmail(name, feedback, userEmail)
            }
        }
    }
    data class Feedback(val name: String, val feedback: String, val email: String)
}
