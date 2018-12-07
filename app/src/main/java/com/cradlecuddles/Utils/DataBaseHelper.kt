package com.cradlecuddles.Utils

import android.content.Context
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper

import com.cradlecuddles.models.Vaccination

import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream
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
        
    }

    companion object {

        //The Android's default system path of your application database.
        private val DB_PATH = "/data/data/com.cradlecuddles/databases/"

        private val DB_NAME = "cardle_cuddles.db"
    }


}
