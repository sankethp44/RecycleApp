package com.example.recycleapp

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.example.recycleapp.databinding.FragmentMyProfileBinding
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

@Suppress("DEPRECATION")
class MyProfileFragment : Fragment() {

    private lateinit var auth: FirebaseAuth
    private lateinit var databaseReference: DatabaseReference
    private lateinit var storageReference: StorageReference
    private lateinit var binding: FragmentMyProfileBinding
    private lateinit var dialog: Dialog
    private val pickImageRequest = 1
    private var selectedImageUri: Uri? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMyProfileBinding.inflate(inflater, container, false)
        val changepassword = resources.getStringArray(R.array.changepass)
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.dropdownitem, changepassword)
        binding.profileImage.setImageResource(R.drawable.profilepic)
        auth = FirebaseAuth.getInstance()
        databaseReference = FirebaseDatabase.getInstance().getReference("Users")

        val etName: EditText = binding.etName
        val etEmailAddress: EditText = binding.etEmailAccount
        val etCity: EditText = binding.etCity

        val autoCompleteTextView: AutoCompleteTextView =
            binding.root.findViewById(R.id.autoCompleteTextView)
        autoCompleteTextView.setAdapter(arrayAdapter)

        autoCompleteTextView.setOnItemClickListener { _, _, position, _ ->
            val selectedItem = arrayAdapter.getItem(position).toString()

            if (selectedItem == "Change Password") {
                val transaction: FragmentTransaction = requireFragmentManager().beginTransaction()
                transaction.replace(R.id.fragment_container, ChangePasswordFragment())
                transaction.addToBackStack(null)
                transaction.commit()
            }
        }

        val editIcon: ImageView = binding.root.findViewById(R.id.editIcon)
        editIcon.setOnClickListener {
            enableEditText()

            val containerView = view?.findViewById<View>(R.id.fragmentContainerprofile123)
            val originalVisibility = containerView?.visibility
            containerView?.visibility = View.INVISIBLE
            Handler().postDelayed({
                if (originalVisibility != null) {
                    containerView.visibility = originalVisibility
                }
            }, 250)

            Toast.makeText(requireContext(), "Edit your profile", Toast.LENGTH_SHORT).show()
        }

        val uid = auth.currentUser?.uid
        loadProfilePicture(uid)
        uid?.let {
            databaseReference.child(it).addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        val name = snapshot.child("name").getValue(String::class.java)
                        etName.setText(name)

                        val email = snapshot.child("emailAccount").getValue(String::class.java)
                        etEmailAddress.setText(email)

                        val city = snapshot.child("city").getValue(String::class.java)
                        etCity.setText(city)
                    }
                }
                override fun onCancelled(error: DatabaseError) {
                }
            })
        }
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        hideFab()
        auth = FirebaseAuth.getInstance()
        val uid = auth.currentUser?.uid
        databaseReference = FirebaseDatabase.getInstance().getReference("Users")

        val uploadPicImageView: ImageView = binding.root.findViewById(R.id.uploadPic)
        uploadPicImageView.setOnClickListener {
            openGallery()
        }

        binding.saveBtn.setOnClickListener {
            showProgressBar()
            val name = binding.etName.text.toString()
            val emailAccount = binding.etEmailAccount.text.toString()
            val city = binding.etCity.text.toString()

            val user = User(name, emailAccount, city)
            if (uid != null) {

                databaseReference.child(uid).setValue(user).addOnCompleteListener {
                    if (it.isSuccessful) {
                        hideProgressBar()
                        Toast.makeText(
                            requireContext(),
                            "Profile successfully updated",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        hideProgressBar()
                        Toast.makeText(
                            requireContext(),
                            "Failed to update profile",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }

    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, pickImageRequest)
    }
    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == pickImageRequest && resultCode == Activity.RESULT_OK && data != null) {
            selectedImageUri = data.data
            binding.profileImage.setImageURI(selectedImageUri)
            Toast.makeText(
                requireContext(),
                "Uploading...",
                Toast.LENGTH_SHORT
            ).show()
            selectedImageUri?.let { uploadProfilePic(it) }
        } else {
            Toast.makeText(
                requireContext(),
                "Failed to upload the profile picture",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun uploadProfilePic(imageUri: Uri) {
        storageReference = FirebaseStorage.getInstance().getReference("Users/${auth.currentUser?.uid}")

        storageReference.putFile(imageUri).addOnSuccessListener {
            hideProgressBar()
            Toast.makeText(
                requireContext(),
                "Profile picture successfully uploaded",
                Toast.LENGTH_SHORT
            ).show()
        }.addOnFailureListener {
            hideProgressBar()
            Toast.makeText(
                requireContext(),
                "Failed to upload the profile picture",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun showProgressBar() {
        dialog = Dialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_wait)
        dialog.setCanceledOnTouchOutside(false)
        dialog.show()
    }

    private fun hideProgressBar(){
        if (::dialog.isInitialized && dialog.isShowing) {
            dialog.dismiss()
        }
    }

    private fun hideFab() {
        val extendedFab: ExtendedFloatingActionButton =
            requireActivity().findViewById(R.id.extendedFab)
        extendedFab.visibility = View.GONE

        val regularFab: FloatingActionButton =
            requireActivity().findViewById(R.id.fab)
        regularFab.visibility = View.GONE

        val home: BottomNavigationView =
            requireActivity().findViewById(R.id.bottom_navigation)
        home.visibility = View.GONE

        val appbarhome: BottomAppBar =
            requireActivity().findViewById(R.id.bottomAppBar)
        appbarhome.visibility = View.GONE

    }

    private fun showFab() {
        val extendedFabHome: ExtendedFloatingActionButton = requireActivity().findViewById(R.id.extendedFabHome)
        extendedFabHome.visibility = View.VISIBLE
    }

    override fun onResume() {
        super.onResume()
        showFab()
    }
    private fun enableEditText() {
        val etName: EditText = binding.root.findViewById(R.id.etName)
        etName.isEnabled = true
        etName.requestFocus()

        val etEmailAddress: EditText = binding.root.findViewById(R.id.etEmailAccount)
        etEmailAddress.isEnabled = true
        etEmailAddress.requestFocus()

        val etCity: EditText = binding.root.findViewById(R.id.etCity)
        etCity.isEnabled = true
        etCity.requestFocus()
    }

    private fun loadProfilePicture(uid: String?) {
        if (uid != null) {
            val storageReference = FirebaseStorage.getInstance().getReference("Users/$uid")
            storageReference.downloadUrl.addOnSuccessListener { uri ->
                Glide.with(requireContext())
                    .load(uri)
                    .placeholder(R.drawable.profilepic)
                    .error(R.drawable.profilepic)
                    .into(binding.profileImage)
            }.addOnFailureListener {
                Toast.makeText(
                    requireContext(),
                    "Failed to upload the profile picture",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}
