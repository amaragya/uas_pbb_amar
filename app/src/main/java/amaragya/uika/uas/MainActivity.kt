package amaragya.uika.uas

import amaragya.uika.uas.galeri.GalleryActivity
import amaragya.uika.uas.galeri.GalleryFullScreenActivity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        spot_1.setOnClickListener { v: View ->
            goToGallery(R.drawable.spot_kampus_1)
        }
        spot_2.setOnClickListener { v: View ->
            goToGallery(R.drawable.spot_kampus_2)
        }
        spot_3.setOnClickListener { v: View ->
            goToGallery(R.drawable.spot_kampus_3)
        }
        spot_4.setOnClickListener { v: View ->
            goToGallery(R.drawable.spot_kampus_4)
        }
        spot_5.setOnClickListener { v: View ->
            goToGallery(R.drawable.spot_kampus_5)
        }
        spot_6.setOnClickListener { v: View ->
            goToGallery(R.drawable.spot_kampus_6)
        }
        foto_profile.setOnClickListener {
            val intent = Intent(this, GalleryFullScreenActivity::class.java)
            intent.putExtra("gambarnya", R.drawable.foto)
            startActivity(intent)
        }


        scroll_view_wrapper.setOnScrollChangeListener(View.OnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            if (scrollY > 150) {
                contact_wrapper.visibility = View.GONE
            } else {
                contact_wrapper.visibility = View.VISIBLE
            }
        })
    }

    private fun openWa() {
        val url = "https://api.whatsapp.com/send?phone=6289602783380"
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(browserIntent)
    }

    private fun openEmail() {
        val i = Intent(Intent.ACTION_SEND)
        i.type = "message/rfc822"
        i.putExtra(Intent.EXTRA_EMAIL, arrayOf("amaragya@gmail.com"))
        try {
            startActivity(Intent.createChooser(i, "Send mail..."))
        } catch (ex: ActivityNotFoundException) {
            Toast.makeText(
                this@MainActivity,
                "There are no email clients installed.",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun openURL(url: String) {
        val openURL = Intent(android.content.Intent.ACTION_VIEW)
        openURL.data = Uri.parse(url)
        startActivity(openURL)
    }

    fun onClickEmail(view: View) {
        openEmail()
    }

    fun onClickChat(view: View) {
        openWa()
    }

    fun onLinkedinClick(view: View) {
        openURL("https://id.linkedin.com/in/amaragya")
    }

    fun onGithubClick(view: View) {
        openURL("https://github.com/amaragya")
    }

    fun onIGClick(view: View) {
        openURL("https://www.instagram.com/amar_agya/")
    }

    fun onWebClick(view: View) {
        openURL("https://amaragya.github.io")
    }

    fun onFuturePlanClick(view: View) {
        val intent = Intent(this@MainActivity, FuturePlanActivity::class.java)
        startActivity(intent)
    }

    fun onSpotKampusClick(view: View) {
        goToGallery(0)
    }

    fun goToGallery(ImageId: Int) {
        val intent = Intent(this@MainActivity, GalleryActivity::class.java)
        intent.putExtra("choosed_image", ImageId)
        startActivity(intent)
    }

}