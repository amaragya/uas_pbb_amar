package amaragya.uika.uas

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class FuturePlanActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_future_plan)

        supportActionBar?.title = "Rencana 5 tahun mendatang"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

}