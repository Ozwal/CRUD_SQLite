package tinoco.castro.aplicacioncrud;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ReadClass extends AppCompatActivity implements View.OnClickListener {

    Button btnCerrarRead;
    TextView txtUsuarioRead, txtEmailRead, txtTelefonoRead, txtFechaRead;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.leer_main);

        btnCerrarRead = (Button)findViewById(R.id.btnCerrarRead);
        txtUsuarioRead = (TextView)findViewById(R.id.txtUsuarioRead);
        txtEmailRead = (TextView)findViewById(R.id.txtEmailRead);
        txtTelefonoRead = (TextView)findViewById(R.id.txtTelefonoRead);
        txtFechaRead = (TextView)findViewById(R.id.txtFechaNacimientoRead);

        btnCerrarRead.setOnClickListener(this);

        Bundle b = this.getIntent().getExtras();

        if (b != null){
            Contacto contacto = (Contacto)b.getSerializable("contacto");
            txtUsuarioRead.setText(contacto.getUsuario());
            txtEmailRead.setText(contacto.getEmail());
            txtTelefonoRead.setText(contacto.getTelefono());
            txtFechaRead.setText(contacto.getFechaNacimiento());
        }else{
            finish();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnCerrarRead:
                finish();
                break;
        }
    }
}
