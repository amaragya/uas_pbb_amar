package amaragya.uika.uas.galeri

import amaragya.uika.uas.R
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide


class GalleryFullScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.gallery_full_screen)
        val imgFull: ImageView = findViewById(R.id.imgFull)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            window.statusBarColor = Color.TRANSPARENT
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            imgFull.transitionName = GalleryActivity.EXTRA_TRANSITION_IMAGE

        Glide.with(this)
            .load(intent.getIntExtra("gambarnya", R.drawable.spot_kampus_2)).into(imgFull);
    }
}