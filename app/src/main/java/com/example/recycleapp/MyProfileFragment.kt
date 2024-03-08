package com.example.recycleapp

import android.app.Dialog
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.example.recycleapp.databinding.FragmentMyProfileBinding
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.example.recycleapp.ChangePasswordFragment
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.bottomnavigation.BottomNavigationView

class MyProfileFragment : Fragment() {

    private lateinit var auth: FirebaseAuth
    private lateinit var databaseReference: DatabaseReference
    private lateinit var storageReference: StorageReference
    private lateinit var imageUri: Uri
    private lateinit var binding: FragmentMyProfileBinding
    private lateinit var dialog: Dialog


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMyProfileBinding.inflate(inflater, container, false)
        val changepass = resources.getStringArray(R.array.changepass)
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.dropdownitem, changepass)

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
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        hideFab()
        auth = FirebaseAuth.getInstance()
        val uid = auth.currentUser?.uid
        databaseReference = FirebaseDatabase.getInstance().getReference("Users")

        binding.saveBtn.setOnClickListener {
            showProgressBar()
            val name = binding.etName.text.toString()
            val emailAccount = binding.etEmailAccount.text.toString()
            val city = binding.etCity.text.toString()

            val user = User(name, emailAccount, city)
            if (uid != null) {
                databaseReference.child(uid).setValue(user).addOnCompleteListener {
                    if (it.isSuccessful) {
                        uploadProfilePic()
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

    private fun uploadProfilePic() {
        imageUri = Uri.parse("android.resource://${requireActivity().packageName}/${R.drawable.profilepic}")
        storageReference =
            FirebaseStorage.getInstance().getReference("Users/${auth.currentUser?.uid}")
        storageReference.putFile(imageUri).addOnSuccessListener {
            hideProgressBar()
            Toast.makeText(
                requireContext(),
                "Profile successfully updated",
                Toast.LENGTH_SHORT
            ).show()
        }.addOnFailureListener {
            hideProgressBar()
            Toast.makeText(
                requireContext(),
                "Failed to upload the image",
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
        dialog.dismiss()
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

}
