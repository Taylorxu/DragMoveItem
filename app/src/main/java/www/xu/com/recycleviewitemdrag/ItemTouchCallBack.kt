package www.xu.com.recycleviewitemdrag

import android.app.Activity
import android.app.Service
import android.content.Context
import android.content.res.Resources
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.support.v7.widget.GridLayoutManager
import android.os.Vibrator

/**
 * 声明一个继承ItemTouchHelper.Callback
 */
open class ItemTouchCallBack(var adapter: RvAdapter, var activity: Context) : ItemTouchHelper.Callback() {
    /**
     * 拖动\滑动方向
     */
    override fun getMovementFlags(recyclerView: RecyclerView?, viewHolder: RecyclerView.ViewHolder?): Int {
        return if (recyclerView?.layoutManager is GridLayoutManager) {//方格布局
            val dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN or
                    ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
            val swipeFlags = 0
            ItemTouchHelper.Callback.makeMovementFlags(dragFlags, swipeFlags)
        } else {//列表布局
            val dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN
            val swipeFlags = 0
            ItemTouchHelper.Callback.makeMovementFlags(dragFlags, swipeFlags)
        }
    }

    /**
     * 此方法被调用条件是 ItemTouchHelper 企图移动被拖拽的item 从原来的位置到新的位置上
     * <p>
     * 如果此方法返回TRUE，ItemTouchHelper 假设 viewHolder 已经被移到了‘目标ViewHolder’的位置上
     * <p>
     * If you don't support drag & drop, this method will never be called.
     *
     * @param recyclerView 指定的 RecyclerView 用ItemTouchHelper绑定[itemTouchHelper.attachToRecyclerView(recycle_view)]
     * @param viewHolder   被用户拖拽的ViewHolder
     * @param target     一个将要被正在处于拖拽中的viewHolder占有位置的viewHolder,即目标位置上的viewHolder
     * @return True  已经移到了目标位置上
     */
    override fun onMove(recyclerView: RecyclerView?, viewHolder: RecyclerView.ViewHolder?, target: RecyclerView.ViewHolder?): Boolean {
        //得到当拖拽的viewHolder的Position
        val fromPosition = viewHolder?.adapterPosition
        //拿到当前拖拽到的item的viewHolder的position
        val toPosition = target?.adapterPosition
        //被绑定的RecycleView 中的adapter，实现自定义接口中的onMove 去处理数据
        adapter.onMove(fromPosition!!, toPosition!!)

        return true
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder?, direction: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /**
     * 长按选中Item的时候开始调用
     *
     * @param viewHolder
     * @param actionState
     */
    override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
        if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {//非闲置状态下
            viewHolder!!.itemView.setBackgroundColor(Color.LTGRAY)
            //获取系统震动服务
            val vib = activity.getSystemService(Service.VIBRATOR_SERVICE) as Vibrator
            vib.vibrate(70)
        }
        super.onSelectedChanged(viewHolder, actionState)
    }

    /**
     * 手指松开的时候还原
     * @param recyclerView
     * @param viewHolder
     */
    override fun clearView(recyclerView: RecyclerView?, viewHolder: RecyclerView.ViewHolder) {
        super.clearView(recyclerView, viewHolder)
        viewHolder.itemView.setBackgroundColor(activity.resources.getColor(R.color.colorAccent)) //Color.parseColor("#FF4081")
    }
}