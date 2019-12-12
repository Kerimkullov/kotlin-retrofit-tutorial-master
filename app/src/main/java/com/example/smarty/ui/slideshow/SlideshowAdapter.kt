import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.smarty.R

class SlideshowAdapter(
    context: Context?, textViewResourceId: Int,
    objects: Array<String>?
) :
    ArrayAdapter<String?>(context!!, textViewResourceId, objects!!) {
    private val mContext: Context?
    @SuppressLint("ViewHolder")
    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup
    ): View { // return super.getView(position, convertView, parent);
        val catNames = arrayOf(
            "Сотрудники", "Покупатели", "Торговые Точки", "Размеры"
        )
        val slideshow_iconsval = intArrayOf(R.drawable.ic_menu_camera, R.drawable.ic_menu_gallery, R.drawable.ic_menu_slideshow, R.drawable.ic_menu_manage)
        val inflater = mContext!!
            .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val row: View = inflater.inflate(
            R.layout.listfragment_row, parent,
            false
        )
        val catNameTextView =
            row.findViewById<View>(R.id.textViewName) as TextView
        catNameTextView.text = catNames[position]
        val iconImageView: ImageView =
            row.findViewById<View>(R.id.imageViewIcon) as ImageView
        // Присваиваем значок
        iconImageView.setImageResource(slideshow_iconsval[position])
        return row
    }

    init {
        mContext = context
    }
}