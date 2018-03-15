package club.findups.filedirectory;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class DataBaseActivity extends AppCompatActivity {

    TextView result;
    EditText file_id_et, file_name_et, file_space_et;
    EditText D_id_et, D_name_et, D_space_et;
    Button insert, showAll, delete;

    DataBaseCentre dbc;

    String id, name, space,Did,Dname,Dspace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_base);

        dbc=new DataBaseCentre(this);

        result=(TextView) findViewById(R.id.result);
        file_id_et=(EditText) findViewById(R.id.file_id);
        file_name_et=(EditText) findViewById(R.id.file_name);
        file_space_et=(EditText) findViewById(R.id.file_space);
        D_id_et =(EditText) findViewById(R.id.Dfile_id);
        D_name_et= (EditText)findViewById(R.id.Dfile_name);
        D_space_et= (EditText) findViewById(R.id.Dfile_space);


        insert=(Button) findViewById(R.id.insert);
        showAll=(Button) findViewById(R.id.get_all);
        delete=(Button) findViewById(R.id.delete);

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id=file_id_et.getText().toString();
                name=file_name_et.getText().toString();
                space=file_space_et.getText().toString();
                Did=file_id_et.getText().toString();
                Dname=file_name_et.getText().toString();
                Dspace=file_space_et.getText().toString();
                dbc.insertData(id,name,space,Did,Dname, Dspace);

            }
        });

        showAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res= dbc.getAllData();
                if(res.getCount()==0){
                    result.setText("Error No Data Found");
                    return;
                }
                StringBuffer buffer =new StringBuffer();
                while (res.moveToNext()){
                    buffer.append("ID :"+res.getString(0)+"\n");
                    buffer.append("FileName :"+res.getString(1)+"\n");
                    buffer.append("Space :"+res.getString(2)+"\n\n");
                    buffer.append("D ID :"+res.getString(3)+"\n");
                    buffer.append("D FileName :"+res.getString(4)+"\n");
                    buffer.append("D Space :"+res.getString(5)+"\n\n");
                }
                result.setText(buffer.toString());
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbc.deleteData();
            }
        });

    }

    public void saveData(String id, String name, String space){
        SharedPreferences sp = getSharedPreferences("directory", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor= sp.edit();
        editor.putString("id",id);
        editor.putString("dir_name",name);
        editor.putString("dir_space",space);
        editor.putString("Did",Did);
        editor.putString("Ddir_name",Dname);
        editor.putString("Ddir_space",Dspace);
        editor.apply();
    }

    public String[] retrieveData(){
        SharedPreferences sp = getSharedPreferences("directory", Context.MODE_PRIVATE);

        String id=sp.getString("id","");
        String dir_name=sp.getString("dir_name","");
        String dir_space=sp.getString("dir_space","");
        String Did=sp.getString("Did","");
        String Ddir_name=sp.getString("Ddir_name","");
        String Ddir_space=sp.getString("Ddir_space","");
        String[] result={id,dir_name,dir_space,Did,Ddir_name,Ddir_space};

        return result;
    }

}
