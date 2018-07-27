package www.xu.com.recycleviewitemdrag

interface ItemTouchCallbackListener {
    fun onMove(fromPosition: Int, toPosition: Int)
    fun onSwipe(position: Int)
}