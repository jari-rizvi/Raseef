package com.teamx.raseef.ui.fragments.editProfile

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import androidx.navigation.navOptions
import com.facebook.internal.logging.LogEvent
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.squareup.picasso.Picasso
import com.teamx.raseef.BR
import com.teamx.raseef.R
import com.teamx.raseef.baseclasses.BaseFragment
import com.teamx.raseef.data.remote.Resource
import com.teamx.raseef.databinding.FragmentEditProfileBinding
import com.teamx.raseef.data.dataclasses.Profile
import com.teamx.raseef.utils.DialogHelperClass
import com.teamx.raseef.utils.PrefHelper
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import org.json.JSONException
import java.io.File
import java.io.FileOutputStream


@AndroidEntryPoint
class EditProfileFragment() : BaseFragment<FragmentEditProfileBinding, EditProfileViewModel>() {

    override val layoutId: Int
        get() = R.layout.fragment_edit_profile
    override val viewModel: Class<EditProfileViewModel>
        get() = EditProfileViewModel::class.java
    override val bindingVariable: Int
        get() = BR.viewModel

    private lateinit var options: NavOptions


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        options = navOptions {
            anim {
                enter = R.anim.enter_from_left
                exit = R.anim.exit_to_left
                popEnter = R.anim.nav_default_pop_enter_anim
                popExit = R.anim.nav_default_pop_exit_anim
            }
        }

        mViewDataBinding.txtChange.setOnClickListener {

        }

        mViewDataBinding.btnBack.setOnClickListener {
            popUpStack()
        }

        mViewDataBinding.btnUpdate.setOnClickListener {
            updateProfile(jsonObjectOfProfile())
        }

        mViewDataBinding.profilePicture.setOnClickListener {
            fetchImageFromGallery()
        }

        subscribeToNetworkLiveData()

    }


    override fun subscribeToNetworkLiveData() {
        super.subscribeToNetworkLiveData()

        mViewModel.editProfile()

        mViewModel.editProfileResponse.observe(requireActivity()) {
            when (it.status) {
                Resource.Status.LOADING -> {
                    Log.d("TAG", "subscribeToNetworkLiveData:1 ")
                    loadingDialog.show()
                }
                Resource.Status.SUCCESS -> {
                    loadingDialog.dismiss()
                    it.data?.let { data ->
                        var addressString = ""
                        data.address?.let {

                            for (address in it) {
                                addressString =
                                    addressString + address.address.street_address + " " + address.address.city + " " + address.address.state + " " + address.address.country + " " + "Zip: " + address.address.zip + "\n"
                            }
                        }
                        val str = data.name ?: ""

                        mViewDataBinding.userName.setText(str)

//                        data.contact?.let { contact ->
//                            mViewDataBinding.editPhoneNumber.setText(contact)
//                        }

                        data.email?.let { email ->
                            mViewDataBinding.userEmail.setText(email)
                        }

                        data.contact?.let { contact ->
                            mViewDataBinding.PhoneNumber.setText(contact)
                        }


//                        mViewDataBinding.editAddress.setText(addressString)
//                        data.gender?.let { gender ->
//
//                            when (gender) {
//                                "Male" -> {
//                                    mViewDataBinding.radioMale.isChecked = true
//                                    mViewDataBinding.radioFemale.isChecked = false
//                                    mViewDataBinding.radioOther.isChecked = false
//                                }
//                                "Female" -> {
//                                    mViewDataBinding.radioMale.isChecked = false
//                                    mViewDataBinding.radioFemale.isChecked = true
//                                    mViewDataBinding.radioOther.isChecked = false
//                                }
//                                else -> {
//                                    mViewDataBinding.radioMale.isChecked = false
//                                    mViewDataBinding.radioFemale.isChecked = false
//                                    mViewDataBinding.radioOther.isChecked = true
//                                }
//                            }
//                        }
                        data.profile?.let { profile ->
                            mViewDataBinding.etPass.setText(profile.bio)
                            strImg = profile.avatar
                            strId = profile._id

                            Picasso.get().load(profile.avatar).into(mViewDataBinding.profilePicture)
                        }

                        Log.d("TAG", "subscribeToNetworkLiveData:2 $data")

                    }
                }
                Resource.Status.ERROR -> {
                    loadingDialog.dismiss()
                    DialogHelperClass.errorDialog(requireContext(), it.message!!)
                    Log.d("TAG", "subscribeToNetworkLiveData:3 ${it.message}")
                }
            }
        }
    }


    private fun updateProfile(obj: JsonObject) {
        Log.d("1235", "updateProfile: $obj")
        mViewModel.updateProfile(obj)
        mViewModel.updateProfileResponse.observe(viewLifecycleOwner) {
            when (it.status) {
                Resource.Status.LOADING -> {
                    Log.d("TAG", "updateProfile:1 ")
                    loadingDialog.show()
                }
                Resource.Status.SUCCESS -> {
                    loadingDialog.dismiss()
                    it.data?.let { data ->

                        val snackBar = Snackbar.make(
                            mViewDataBinding.consRoot, "Updated", Snackbar.LENGTH_SHORT
                        )
                        snackBar.show()
                    }
                }
                Resource.Status.ERROR -> {
                    loadingDialog.dismiss()
                    DialogHelperClass.errorDialog(requireContext(), it.message!!)
                    Log.d("TAG", "updateProfile:32 ${it.message}")
                }
            }
        }
    }

    var strImg = ""
    var strId = ""
    var strContact = ""
    private fun jsonObjectOfProfile(): JsonObject {

        val obj = JsonObject()
        try {

            val genderStr = "Male"
            /*       genderStr = if (mViewDataBinding.radioMale.isChecked) {
                       "Male"
                   } else if (mViewDataBinding.radioFemale.isChecked) {
                       "Female"
                   } else {
                       "Others"
                   }*/
//
//            mViewModel.viewModelScope.launch(Dispatchers.Main) {
//                dataStoreProvider.saveUserDetails(
//                    mViewDataBinding.userName.text.toString(),
//                    mViewDataBinding.userEmail.text.toString(),
//                    strImg,
//                    mViewDataBinding.PhoneNumber.text.toString()
//                )
//            }

            PrefHelper.getInstance(requireContext()).saveProfile(
                mViewDataBinding.userName.text.toString(),
                mViewDataBinding.userEmail.text.toString(),
                strImg,
                mViewDataBinding.PhoneNumber.text.toString()
            )

            PrefHelper.getInstance(requireContext()).name?.let { Log.e("PrefHelper", it) }
            PrefHelper.getInstance(requireContext()).email?.let { Log.e("PrefHelper", it) }
            PrefHelper.getInstance(requireContext()).avatar?.let { Log.e("PrefHelper", it) }
            PrefHelper.getInstance(requireContext()).number?.let { Log.e("PrefHelper", it) }

            obj.addProperty("name", mViewDataBinding.userName.text.toString())

            obj.addProperty("gender", genderStr)

            obj.add(
                "profile",
                Gson().toJsonTree(Profile(strId, strImg, mViewDataBinding.etPass.text.toString()))
            )



        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return obj
    }


    private fun fetchImageFromGallery() {
        startForResult.launch("image/*")
        checker()
    }

    private val startForResult =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            uri?.let {
                Picasso.get().load(it).into(mViewDataBinding.profilePicture)
                uploadWithRetrofit(it)

            }

        }


    private fun checker() {
        mViewModel.updateImgProfileResponse.observe(viewLifecycleOwner) {
            when (it.status) {
                Resource.Status.LOADING -> {
                    Log.d("TAG", "updateProfile:1 ")
                    loadingDialog.show()
                }
                Resource.Status.SUCCESS -> {
                    loadingDialog.dismiss()
                    it.data?.let { data ->

                        this.strImg = data[0]
//                        Toast.makeText(requireContext(), "hellol${strImg}", Toast.LENGTH_SHORT)
//                            .show()
                    }
                }
                Resource.Status.ERROR -> {
                    loadingDialog.dismiss()
                    DialogHelperClass.errorDialog(requireContext(), it.message!!)
                    Log.d("TAG", "updateProfile:3 ${it.message}")
                }
            }
        }
    }


    private fun uploadWithRetrofit(uri: Uri) {
        val fileDir = requireContext().applicationContext.filesDir
        val file = File(fileDir, "picture.png")
        val inputStream = requireContext().contentResolver.openInputStream(uri)
        val outputStream = FileOutputStream(file)
        inputStream!!.copyTo(outputStream)
        val requestBody = file.asRequestBody("image/*".toMediaTypeOrNull())

        val filePart: MultipartBody.Part = MultipartBody.Part.createFormData(
            "attachment", file.name, requestBody
        )
        mViewModel.updateImgProfile(filePart)

    }


}