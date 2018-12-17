package com.cradlecuddles.fragments

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import com.cradlecuddles.MainActivity
import com.cradlecuddles.R
import com.cradlecuddles.foundation.BaseFragment
import android.widget.Toast
import android.app.Activity.RESULT_CANCELED
import android.app.Activity.RESULT_OK
import android.app.Activity
import android.net.Uri
import android.graphics.Bitmap
import android.media.MediaScannerConnection
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import com.cradlecuddles.CradleCuddles
import com.cradlecuddles.Utils.Utils
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException


/**
 * Created by Khyati Shah on 12/10/2018.
 */
class BabyProfileFragment : BaseFragment(), View.OnClickListener {

    var imgProfileEdit: ImageView? = null
    var profile_image: ImageView? = null
    var imgBasicInfoEdit: ImageView? = null
    private val GALLERY = 1
    private val CAMERA = 2

    override fun onResume() {
        super.onResume()
        (activity as MainActivity)
                .setActionBarTitle(getString(R.string.ChildProfile))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater!!.inflate(R.layout.fragment_baby_profile, container, false)
        imgProfileEdit = view.findViewById(R.id.imgProfileEdit)
        profile_image = view.findViewById(R.id.profile_image)
        imgBasicInfoEdit = view.findViewById(R.id.imgBasicInfoEdit)
        imgProfileEdit!!.setOnClickListener(this)
        imgBasicInfoEdit!!.setOnClickListener(this)
        var childProfileImage = CradleCuddles.dataBaseHelper!!.getChildProfileImage()
        if (childProfileImage != null) {
            profile_image!!.setImageBitmap(childProfileImage)
        }
        return view
    }

    override fun onClick(p0: View?) {
        when (p0!!.id) {
            R.id.imgProfileEdit -> showImageChooserDialog()
            R.id.imgBasicInfoEdit -> showEditInfoDialog()
        }
    }

    private fun showEditInfoDialog() {
        // create a Dialog component
        val dialog = Dialog(activity)
        //tell the Dialog to use the dialog.xml as it's layout description
        dialog.setContentView(R.layout.dialog_edit_baby_basic)
    }

    private fun showImageChooserDialog() {
        // create a Dialog component
        val dialog = Dialog(activity)
        //tell the Dialog to use the dialog.xml as it's layout description
        dialog.setContentView(R.layout.dialog_image_chooser)
        val llGallery = dialog.findViewById<LinearLayout>(R.id.llGallery)
        val llCamera = dialog.findViewById<LinearLayout>(R.id.llCamera)
        val llRemovePhoto = dialog.findViewById<LinearLayout>(R.id.llRemovePhoto)

        llGallery.setOnClickListener {
            val galleryIntent = Intent(Intent.ACTION_PICK,
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI)

            startActivityForResult(galleryIntent, GALLERY)
            dialog.dismiss()
        }
        llCamera.setOnClickListener {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(intent, CAMERA)
            dialog.dismiss()
        }
        llRemovePhoto.setOnClickListener {
            profile_image!!.setImageResource(R.mipmap.ic_profile)
            CradleCuddles.dataBaseHelper!!.removeChildProfileImage()
            dialog.dismiss()
        }
        dialog.show()
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        super.onActivityResult(requestCode, resultCode, data)
        /* if (resultCode == this.RESULT_CANCELED)
         {
         return
         }*/
        if (requestCode == GALLERY) {
            if (data != null) {
                val contentURI = data!!.data
                try {
                    val bitmap = MediaStore.Images.Media.getBitmap(activity.contentResolver, contentURI)
                    //val path = saveImage(bitmap)
                    CradleCuddles.dataBaseHelper!!.setChildProfileImage(bitmap)

                    profile_image!!.setImageBitmap(bitmap)

                } catch (e: IOException) {
                    e.printStackTrace()
                    Toast.makeText(activity, "Failed!", Toast.LENGTH_SHORT).show()
                }

            }

        } else if (requestCode == CAMERA) {
            val thumbnail = data!!.extras!!.get("data") as Bitmap
            profile_image!!.setImageBitmap(thumbnail)
            CradleCuddles.dataBaseHelper!!.setChildProfileImage(thumbnail)
            //saveImage(thumbnail)

        }
    }

   
    companion object {
        private val IMAGE_DIRECTORY = "/demonuts"
    }
}