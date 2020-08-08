package amaragya.uika.uas.galeri

import amaragya.uika.uas.R
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide


class GalleryAdapter(
    context: Context,
    listItems: ArrayList<Int>,
    adapterType: Int
) :
    PagerAdapter() {
    var context: Context = context
    var listItems: ArrayList<Int> = listItems
    var adapterType: Int = adapterType
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view: View = LayoutInflater.from(context).inflate(R.layout.gallery_cover, null)
        try {
            val linMain = view.findViewById(R.id.linMain) as LinearLayout
            val imageCover: ImageView = view.findViewById(R.id.imageCover) as ImageView
            linMain.tag = position
            when (adapterType) {
                GalleryActivity.ADAPTER_TYPE_TOP -> linMain.setBackgroundResource(R.drawable.shadow)
                GalleryActivity.ADAPTER_TYPE_BOTTOM -> linMain.setBackgroundResource(0)
            }

            Glide.with(context)
                .load(listItems[position])
                .into(imageCover)

            container.addView(view)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return view
    }

    override fun destroyItem(
        container: ViewGroup,
        position: Int,
        `object`: Any
    ) {
        container.removeView(`object` as View)
    }

    override fun getCount(): Int {
        return listItems.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

}