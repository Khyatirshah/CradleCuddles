package com.cradlecuddles.db

import android.content.Context
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import com.cradlecuddles.Utils.Constants
import com.cradlecuddles.Utils.Utils
import com.cradlecuddles.models.BabyBasicDetails
import com.cradlecuddles.models.BabyBodyDetails
import com.cradlecuddles.models.ChildDiet

import com.cradlecuddles.models.Vaccination

import java.io.FileOutputStream
import java.io.IOException
import java.util.ArrayList

/**
 * Created by Khyati Shah on 11/30/2018.
 */
class DataBaseHelper
/**
 * Constructor
 * Takes and keeps a reference of the passed context in order to access to the application assets and resources.
 *
 * @param context
 */
(private val myContext: Context) : SQLiteOpenHelper(myContext, DB_NAME, null, 1) {


    private var myDataBase: SQLiteDatabase? = null


    /**
     * Creates a empty database on the system and rewrites it with your own database.
     */
    @Throws(IOException::class)
    fun createDataBase() {

        val dbExist = checkDataBase()

        if (dbExist) {
            //do nothing - database already exist
        } else {

            //By calling this method and empty database will be created into the default system path
            //of your application so we are gonna be able to overwrite that database with our database.
            this.readableDatabase

            try {

                copyDataBase()

            } catch (e: IOException) {

                throw Error("Error copying database")

            }

        }

    }

    /**
     * Check if the database already exist to avoid re-copying the file each time you open the application.
     *
     * @return true if it exists, false if it doesn't
     */
    private fun checkDataBase(): Boolean {

        var checkDB: SQLiteDatabase? = null

        try {
            val myPath = DB_PATH + DB_NAME
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY)

        } catch (e: SQLiteException) {

            //database does't exist yet.

        }

        checkDB?.close()

        return if (checkDB != null) true else false
    }

    /**
     * Copies your database from your local assets-folder to the just created empty database in the
     * system folder, from where it can be accessed and handled.
     * This is done by transfering bytestream.
     */
    @Throws(IOException::class)
    private fun copyDataBase() {
        try {


            //Open your local db as the input stream
            val myInput = myContext.assets.open(DB_NAME)

            // Path to the just created empty db
            val outFileName = DB_PATH + DB_NAME

            //Open the empty db as the output stream
            val myOutput = FileOutputStream(outFileName)

            //transfer bytes from the inputfile to the outputfile
            val buffer = ByteArray(1024)
            //var length: Int
            var length = myInput.read(buffer)
            while (length > 0) {
                myOutput.write(buffer, 0, length)
                length = myInput.read(buffer)
            }

            //Close the streams
            myOutput.flush()
            myOutput.close()
            myInput.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    @Throws(SQLException::class)
    fun openDataBase() {

        //Open the database
        val myPath = DB_PATH + DB_NAME
        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY)

    }

    @Synchronized
    override fun close() {

        if (myDataBase != null)
            myDataBase!!.close()

        super.close()

    }

    override fun onCreate(db: SQLiteDatabase) {

    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {

    }

    fun getVaccinations(age: String): ArrayList<Vaccination> {
        val vaccinations = ArrayList<Vaccination>()
        try {
            // String q = "SELECT name from " + Constants.TABLE_VACCINATION + " WHERE Age ='" + age + "' ";

            myDataBase = readableDatabase
            val cursor = myDataBase!!.query(Constants.TABLE_VACCINATION, null,
                    "Age" + "=?",
                    arrayOf(age), null, null, null, null)
            if (cursor != null) {
                cursor.moveToFirst()
                for (i in 0 until cursor.count) {
                    var tempVacc = Vaccination()
                    tempVacc.vaccName = cursor.getString(0)
                    tempVacc.age = cursor.getString(1)
                    tempVacc.approxPrice = cursor.getString(4)
                    tempVacc.contentTag = cursor.getString(3)
                    tempVacc.doses = cursor.getInt(2)
                    tempVacc.isDone = cursor.getInt(5)
                    vaccinations.add(tempVacc)
                    cursor.moveToNext()
                }
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }
        return vaccinations
    }

    fun updateIsDone(vaccName: String, isDone: Int) {
        myDataBase = writableDatabase
        val strSQL = "UPDATE " + Constants.TABLE_VACCINATION + " SET IsDone = $isDone WHERE VaccName = '$vaccName'"
        myDataBase!!.execSQL(strSQL)
    }

    fun getChildProfileImage(): Bitmap? {

        myDataBase = readableDatabase
        val qu = "select *  from " + Constants.TABLE_BABY_PROFILE
        val cursor = myDataBase!!.rawQuery(qu, null)

        if (cursor != null && cursor.count > 0) {
            cursor.moveToFirst()
            var imgByte = cursor.getString(7)
            if (imgByte != null) {
                val bytarray = Base64.decode(imgByte, Base64.DEFAULT)
                val bmimage = BitmapFactory.decodeByteArray(bytarray, 0,
                        bytarray.size)
                //return BitmapFactory.decodeByteArray(imgByte, 0, imgByte.size)
                return bmimage
            }
            cursor!!.close()
        }
        return null
    }

    fun setChildProfileImage(bitmapImage: Bitmap) {
        myDataBase = writableDatabase
        var byteImage = Utils.getBitmapAsByteArray(bitmapImage)

        val qu = "select *  from " + Constants.TABLE_BABY_PROFILE
        val cursor = myDataBase!!.rawQuery(qu, null)
        var strSQL: String
        if (cursor != null && cursor.count > 0) {
            strSQL = "UPDATE " + Constants.TABLE_BABY_PROFILE + " SET profile_image = '$byteImage'"
        } else {
            strSQL = "INSERT OR REPLACE INTO " + Constants.TABLE_BABY_PROFILE + " ('profile_image') Values ('$byteImage')"
        }

        myDataBase!!.execSQL(strSQL)
    }

    fun removeChildProfileImage() {
        myDataBase = writableDatabase
        val qu = "select *  from " + Constants.TABLE_BABY_PROFILE
        val cursor = myDataBase!!.rawQuery(qu, null)
        var strSQL: String
        if (cursor != null && cursor.count > 0) {
            strSQL = "UPDATE " + Constants.TABLE_BABY_PROFILE + " SET profile_image = ''"
        } else {
            strSQL = "INSERT OR REPLACE INTO " + Constants.TABLE_BABY_PROFILE + " ('profile_image') Values ('')"
        }

        myDataBase!!.execSQL(strSQL)
    }

    fun setBabyBasics(babyBasicDetails: BabyBasicDetails) {
        myDataBase = writableDatabase
        val qu = "select *  from " + Constants.TABLE_BABY_PROFILE
        val cursor = myDataBase!!.rawQuery(qu, null)
        var strSQL: String
        if (cursor != null && cursor.count > 0) {
            strSQL = "UPDATE " + Constants.TABLE_BABY_PROFILE + " SET name = '${babyBasicDetails.name}', gender = '${babyBasicDetails.gender}', date_of_birth = '${babyBasicDetails.DOB}', time_of_birth = '${babyBasicDetails.TOD}'"
        } else {
            strSQL = "INSERT OR REPLACE INTO " + Constants.TABLE_BABY_PROFILE + " ('name', 'gender', 'date_of_birth', 'time_of_birth') Values ('${babyBasicDetails.name}','${babyBasicDetails.gender}', '${babyBasicDetails.DOB}','${babyBasicDetails.TOD}')"
        }

        myDataBase!!.execSQL(strSQL)
    }

    fun setBabyBody(babyBodyDetails: BabyBodyDetails) {
        myDataBase = writableDatabase
        val qu = "select *  from " + Constants.TABLE_BABY_PROFILE
        val cursor = myDataBase!!.rawQuery(qu, null)
        var strSQL: String
        if (cursor != null && cursor.count > 0) {
            strSQL = "UPDATE " + Constants.TABLE_BABY_PROFILE + " SET height = '${babyBodyDetails.height}', weight = '${babyBodyDetails.weight}', blood_group = '${babyBodyDetails.bloodGroup}'"
        } else {
            strSQL = "INSERT OR REPLACE INTO " + Constants.TABLE_BABY_PROFILE + " ('height', 'weight', 'blood_group') Values ('${babyBodyDetails.height}','${babyBodyDetails.weight}', '${babyBodyDetails.bloodGroup}')"
        }

        myDataBase!!.execSQL(strSQL)
    }

    fun getBabyBasics(): BabyBasicDetails? {
        var babyBasicDetails: BabyBasicDetails? = null
        val qu = "select *  from " + Constants.TABLE_BABY_PROFILE
        val cursor = myDataBase!!.rawQuery(qu, null)
        if (cursor.count > 0) {
            cursor.moveToFirst()
            if (cursor.getString(0) != null) {
                babyBasicDetails = BabyBasicDetails()
                babyBasicDetails.name = cursor.getString(0)
                babyBasicDetails.gender = cursor.getString(1)
                babyBasicDetails.DOB = cursor.getString(2)
                babyBasicDetails.TOD = cursor.getString(3)
            }
        }
        return babyBasicDetails
    }

    fun getBabyBody(): BabyBodyDetails? {
        var babyBodyDetails: BabyBodyDetails? = null
        val qu = "select *  from " + Constants.TABLE_BABY_PROFILE
        val cursor = myDataBase!!.rawQuery(qu, null)
        if (cursor.count > 0) {
            cursor.moveToFirst()
            if (cursor.getString(4) != null) {
                babyBodyDetails = BabyBodyDetails()
                babyBodyDetails.height = cursor.getString(4).toDouble()
                babyBodyDetails.weight = cursor.getString(5).toDouble()
                babyBodyDetails.bloodGroup = cursor.getString(6)
            }
            cursor.close()
        }
        return babyBodyDetails
    }

    fun getChildDiet(strAge: String): ChildDiet? {

        myDataBase = readableDatabase
        val qu = "select *  from " + Constants.TABLE_BABY_DIET + " where Age = '" + "Diet for " + strAge + "'"
        val cursor = myDataBase!!.rawQuery(qu, null)
        var childDiet: ChildDiet? = null
        if (cursor != null && cursor.count > 0) {
            cursor.moveToFirst()
            childDiet = ChildDiet()
            if (!cursor.getString(cursor.getColumnIndex("Age")).isNullOrBlank())
                childDiet.age = cursor.getString(cursor.getColumnIndex("Age"))
            if (!cursor.getString(cursor.getColumnIndex("Feed")).isNullOrBlank())
                childDiet.feed = cursor.getString(cursor.getColumnIndex("Feed"))
            if (!cursor.getString(cursor.getColumnIndex("HowMuch")).isNullOrBlank())
                childDiet.howMuch = cursor.getString(cursor.getColumnIndex("HowMuch"))
            if (!cursor.getString(cursor.getColumnIndex("NotToFeed")).isNullOrBlank())
                childDiet.notToFeed = cursor.getString(cursor.getColumnIndex("NotToFeed"))
            if (!cursor.getString(cursor.getColumnIndex("DescBottom")).isNullOrBlank())
                childDiet.descBottom = cursor.getString(cursor.getColumnIndex("DescBottom"))
            if (!cursor.getString(cursor.getColumnIndex("DescTop")).isNullOrBlank())
                childDiet.descTop = cursor.getString(cursor.getColumnIndex("DescTop"))
            cursor!!.close()
        }
        return childDiet
    }

    companion object {

        //The Android's default system path of your application database.
        private val DB_PATH = "/data/data/com.cradlecuddles/databases/"

        private val DB_NAME = "cardle_cuddles.db"
    }


}
