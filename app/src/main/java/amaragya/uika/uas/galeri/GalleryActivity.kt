package amaragya.uika.uas.galeri

import amaragya.uika.uas.R
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import kotlinx.android.synthetic.main.activity_gallery.*


class GalleryActivity : AppCompatActivity() {


    private lateinit var pictList: ArrayList<Int>
    private lateinit var titleList: ArrayList<String>
    private lateinit var viewpagerTop: ViewPager
    private lateinit var viewPagerBackground: ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallery)

        supportActionBar!!.title = ""
        supportActionBar!!.setHomeButtonEnabled(true)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        titleList = ArrayList()
        pictList = ArrayList()

        titleList.add(("Gerbang").toUpperCase())
        pictList.add(R.drawable.spot_kampus_1)


        titleList.add(("Gedung FT").toUpperCase())
        pictList.add(R.drawable.spot_kampus_2)

        titleList.add(("Perpustakaan").toUpperCase())
        pictList.add(R.drawable.spot_kampus_3)

        titleList.add(("Kelas di meja ruang dosen").toUpperCase())
        pictList.add(R.drawable.spot_kampus_4)

        titleList.add(("Lab FT").toUpperCase())
        pictList.add(R.drawable.spot_kampus_5)

        titleList.add(("Masjid").toUpperCase())
        pictList.add(R.drawable.spot_kampus_6)



        init()
        setupViewPager()
        next.visibility = View.VISIBLE
        judul.visibility = View.VISIBLE
        judul.text = titleList!![0]

        next.setOnClickListener {
            viewpagerTop!!.setCurrentItem(viewpagerTop!!.currentItem + 1, true)
            viewPagerBackground!!.setCurrentItem(viewpagerTop!!.currentItem + 1, true)
        }

        before.setOnClickListener {
            viewpagerTop!!.setCurrentItem(viewpagerTop!!.currentItem - 1, true)
            viewPagerBackground!!.setCurrentItem(viewpagerTop!!.currentItem - 1, true)
        }


        val choosedImage = intent.getIntExtra("choosed_image", 0)
        if (choosedImage != 0) {
            viewpagerTop!!.setCurrentItem(pictList.indexOf(choosedImage), true)
            viewPagerBackground!!.setCurrentItem(pictList.indexOf(choosedImage), true)
        }
    }

    private fun init() {
        viewpagerTop = findViewById<View>(R.id.viewpagerTop) as ViewPager
        viewPagerBackground = findViewById<View>(R.id.viewPagerbackground) as ViewPager
        viewpagerTop!!.clipChildren = false
        viewpagerTop!!.pageMargin = resources.getDimensionPixelOffset(R.dimen.pager_margin)
        viewpagerTop!!.offscreenPageLimit = 3
        viewpagerTop!!.setPageTransformer(false, GalleryEffect(this)) // Set transformer
    }


    /**
     * Setup viewpager and it's events
     */
    private fun setupViewPager() {
        // Set Top ViewPager Adapter
        val adapter =
            GalleryAdapter(this, pictList, ADAPTER_TYPE_TOP)
        viewpagerTop!!.adapter = adapter

        // Set Background ViewPager Adapter
        val adapterBackground =
            GalleryAdapter(this, pictList, ADAPTER_TYPE_BOTTOM)
        viewPagerBackground!!.adapter = adapterBackground
        viewpagerTop!!.addOnPageChangeListener(object : OnPageChangeListener {
            private var index = 0
            override fun onPageSelected(position: Int) {
                index = position
                if (position == 0) {
                    before.visibility = View.INVISIBLE
                } else {
                    before.visibility = View.VISIBLE
                }

                if (position == (pictList.size - 1)) {
                    next.visibility = View.INVISIBLE
                } else {
                    next.visibility = View.VISIBLE
                }
                judul.text = titleList!![position]
            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                val width: Int = viewPagerBackground!!.getWidth()
                viewPagerBackground!!.scrollTo(
                    (width * position + width * positionOffset).toInt(),
                    0
                )
            }

            override fun onPageScrollStateChanged(state: Int) {
                if (state == ViewPager.SCROLL_STATE_IDLE) {
                    viewPagerBackground!!.setCurrentItem(index)
                }
            }
        })
    }

    fun clickEvent(view: View) {
        when (view.id) {
            R.id.linMain -> if (view.tag != null) {
                val poisition = view.tag.toString().toInt()
                val intent = Intent(this, GalleryFullScreenActivity::class.java)
                intent.putExtra("gambarnya", pictList[poisition])
                startActivity(intent)
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    companion object {
        public val ADAPTER_TYPE_TOP = 1
        public val ADAPTER_TYPE_BOTTOM = 2
        public val EXTRA_IMAGE = "image"
        public val EXTRA_TRANSITION_IMAGE = "image"
    }

}