package amaragya.uika.uas

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_splash_screen.*


class SplashScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        watch_animation.setAnimation("26834-rocket.json")
        watch_animation.playAnimation()


        Handler().postDelayed(Runnable {
            startActivity(
                Intent(
                    applicationContext,
                    MainActivity::class.java
                )
            )
        }, (3000))
    }
}