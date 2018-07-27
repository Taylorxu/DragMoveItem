package www.xu.com.recycleviewitemdrag

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.helper.ItemTouchHelper
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recycle_view.layoutManager = GridLayoutManager(baseContext, 3)
        var adapter = RvAdapter(getData())
        recycle_view.adapter = adapter
        //定义itemTouchHelper
        var itemTouchHelper = ItemTouchHelper(ItemTouchCallBack(adapter,this))
        itemTouchHelper.attachToRecyclerView(recycle_view)

    }

    private fun getData(): MutableList<String> {
        var list = arrayListOf<String>()
        for (i in 0..10) {
            list.add("第$i 条数据")
        }
        return list!!
    }
}
