package club.findups.filedirectory;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;

public class DataBaseCentre extends SQLiteOpenHelper {

    public static final String DATABASE_NAME ="FILEMANAGEMENT.db";
    public static final String TABLE_NAME ="FILES";

    //Directory Schema
    public static final String D_Name="DirectoryName";
    public static final String D_Id="DID";
    public static final String D_Size= "DBSize";
    public static final String D_Parent = "DirectoryParent";


    //FIle CHunk schema
    public static final String FILE_CHUNK = "FileChunk";
    public static final Integer FileC_Size =1;
    public static final Integer FileC_ID =1;
    public static final String FileC_Parent="Parent";


    public static final String ID ="ID";
    public static final String FILENAME ="FILENAME";
    public static final String DIRECTORYSPACE ="DIRECTORYSPACE";
    public static final String PROPIC ="PROPIC";
    public static final String STAR ="STAR";
    Context mContext;

    public DataBaseCentre(Context context) {
        super(context, DATABASE_NAME, null, 1);
        mContext=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+TABLE_NAME+" (ID VARCHAR(100) PRIMARY KEY,FILENAME VARCHAR(100),DIRECTORYSPACE VARCHAR(100))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DELETE TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String id, String filename, String space,String did, String dname, String dsize){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID,id);
        contentValues.put(FILENAME,filename);
        contentValues.put(DIRECTORYSPACE,space);
        contentValues.put(D_Name,dname);
        contentValues.put(D_Id,did);
        contentValues.put(D_Size,dsize);

        long result= db.insert(TABLE_NAME,null,contentValues);
        if(result ==-1){
            return false;
        }else {
            return true;
        }
    }

    public Cursor getAllData(){
        SQLiteDatabase db= this.getWritableDatabase();
        Cursor res=db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        try {
            // res = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        }catch (Exception e){
            // res=null;
        }
        return res;
    }

    public Integer deleteData (){
        SQLiteDatabase db= this.getWritableDatabase();
        return db.delete(TABLE_NAME,null,null);
    }


}