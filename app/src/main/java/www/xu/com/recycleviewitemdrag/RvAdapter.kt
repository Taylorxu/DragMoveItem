package www.xu.com.recycleviewitemdrag

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import java.util.*

/**
 *自定义的adapter，实现 自定义的ItemTouchCallbackListener接口
 * 被用在 自定义的ItemTouchHelper.Callback 类中
 */
class RvAdapter(var data: MutableList<String>) : RecyclerView.Adapter<RvAdapter.ViewHolder>(), ItemTouchCallbackListener {
    override fun onMove(fromPosition: Int, toPosition: Int) {
        if (fromPosition != null) {
            if (fromPosition < toPosition!!) {
                for (i in fromPosition until toPosition) {
                    Collections.swap(data, i, i + 1)
                }
            } else {
                for (i in fromPosition downTo toPosition + 1) {
                    Collections.swap(data, i, i - 1)
                }
            }
        }
        notifyItemMoved(fromPosition, toPosition)
    }

    override fun onSwipe(position: Int) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RvAdapter.ViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return if (data == null) 0 else data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text = data[position]
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textView: TextView = itemView.findViewById(R.id.item_tv)

    }
}