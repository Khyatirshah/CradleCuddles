package com.cradlecuddles.fragments

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import com.cradlecuddles.MainActivity
import com.cradlecuddles.R
import com.cradlecuddles.foundation.BaseFragment
import android.app.Activity.RESULT_CANCELED
import android.app.Activity.RESULT_OK
import android.app.Activity
import android.net.Uri
import android.graphics.Bitmap
import android.media.MediaScannerConnection
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.*
import com.cradlecuddles.CradleCuddles
import com.cradlecuddles.Utils.Utils
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import android.R.attr.scaleHeight
import android.R.attr.scaleWidth
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.graphics.drawable.ScaleDrawable
import android.support.v4.content.ContextCompat
import android.graphics.drawable.Drawable
import android.text.TextUtils
import android.widget.*
import com.cradlecuddles.Utils.Constants
import com.cradlecuddles.db.DataBaseHelper
import com.cradlecuddles.models.BabyBasicDetails
import java.text.SimpleDateFormat
import java.util.*


/**
 * Created by Khyati Shah on 12/10/2018.
 */
class BabyProfileFragment : BaseFragment(), View.OnClickListener {

    var imgProfileEdit: ImageView? = null
    lateinit var imgBodyEdit : ImageView
    var profile_image: ImageView? = null
    var imgBasicInfoEdit: ImageView? = null
    lateinit var valName: TextView
    lateinit var valGender: TextView
    lateinit var valDOB: TextView
    lateinit var valTimeOfBirth: TextView

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
        imgBodyEdit = view.findViewById(R.id.imgBodyEdit)
        imgBasicInfoEdit = view.findViewById(R.id.imgBasicInfoEdit)
        valName = view.findViewById(R.id.valName)
        valGender = view.findViewById(R.id.valGender)
        valDOB = view.findViewById(R.id.valDOB)
        valTimeOfBirth = view.findViewById(R.id.valTimeOfBirth)

        imgProfileEdit!!.setOnClickListener(this)
        imgBasicInfoEdit!!.setOnClickListener(this)
        imgBodyEdit!!.setOnClickListener(this)
        filData()
        return view
    }

    private fun filData() {
        var childProfileImage = CradleCuddles.dataBaseHelper!!.getChildProfileImage()
        if (childProfileImage != null) {
            profile_image!!.setImageBitmap(childProfileImage)
        }
        var babyBasicDetails = CradleCuddles.dataBaseHelper!!.getBabyBasics()

        if (babyBasicDetails != null) {
            valName.text = babyBasicDetails.name
            valGender.text = babyBasicDetails.gender
            valDOB.text = babyBasicDetails.DOB
            valTimeOfBirth.text = babyBasicDetails.TOD
        }
    }

    override fun onClick(p0: View?) {
        when (p0!!.id) {
            R.id.imgProfileEdit -> showImageChooserDialog()
            R.id.imgBasicInfoEdit -> showEditInfoDialog()
            R.id.imgBodyEdit -> showBodyEditDialog()
        }
    }

    private fun showBodyEditDialog(){

    }
    private fun showEditInfoDialog() {
        // create a Dialog component
        val dialog = Dialog(activity)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_edit_baby_basic)
        dialog.show();
        val spinnerGender = dialog.findViewById<Spinner>(R.id.spinnerGender)
        val adapter = ArrayAdapter.createFromResource(activity, R.array.gender, R.layout.spinner_item);
        spinnerGender.setAdapter(adapter);
        val edtDOB = dialog.findViewById<EditText>(R.id.edtDOB)
        val edtTOB = dialog.findViewById<EditText>(R.id.edtTOB)
        val btnOk = dialog.findViewById<Button>(R.id.btnOk)
        val edtName = dialog.findViewById<EditText>(R.id.edtName)
        btnOk.setOnClickListener {
            if (TextUtils.isEmpty(edtName.text)) {
                edtName.error = "Please Enter Name"
                return@setOnClickListener
            }
            if (TextUtils.isEmpty(edtDOB.text)) {
                //edtDOB.error = "Please Select Date of Birth"
                Toast.makeText(activity,"Please Select Date of Birth",Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            if (TextUtils.isEmpty(edtTOB.text)) {
                //edtDOB.error = "Please Select Time of Birth"
                Toast.makeText(activity,"Please Select Time of Birth",Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            val babyBasicDetails: BabyBasicDetails = BabyBasicDetails()
            babyBasicDetails.name = edtName.text.toString()
            babyBasicDetails.gender = spinnerGender.selectedItem.toString()
            babyBasicDetails.DOB = edtDOB.text.toString()
            babyBasicDetails.TOD = edtTOB.text.toString()
            CradleCuddles.dataBaseHelper!!.setBabyBasics(babyBasicDetails)

            dialog.dismiss()
            //Refreshing UI
            filData()
        }
        val btnCancel = dialog.findViewById<Button>(R.id.btnCancel)
        btnCancel.setOnClickListener { dialog.dismiss() }
        var cal = Calendar.getInstance()
        //  var time = Calendar.getInstance()
        val dateSetListener = DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            cal.set(Calendar.YEAR, year)
            cal.set(Calendar.MONTH, monthOfYear)
            cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            val sdf = SimpleDateFormat(Constants.standardDtFormat, Locale.US)
            edtDOB.setText(sdf.format(cal.time))
        }
        val imgDatePicker = dialog.findViewById<ImageView>(R.id.imgDatePicker)
        imgDatePicker.setOnClickListener {
            DatePickerDialog(activity, dateSetListener,
                    cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH),
                    cal.get(Calendar.DAY_OF_MONTH)).show()
        }
        val imgTimePicker = dialog.findViewById<ImageView>(R.id.imgTimePicker)
        imgTimePicker.setOnClickListener {
            val hour = cal.get(Calendar.HOUR_OF_DAY)
            val minute = cal.get(Calendar.MINUTE)
            val tpd = TimePickerDialog(activity, TimePickerDialog.OnTimeSetListener(function = { view, h, m ->

                edtTOB.setText(h.toString() + " : " + m)
                cal.set(Calendar.HOUR_OF_DAY, h)
                cal.set(Calendar.MINUTE, m)

            }), hour, minute, false)

            tpd.show()
        }
    }

    private fun showImageChooserDialog() {
        // create a Dialog component
        val dialog = Dialog(activity)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
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