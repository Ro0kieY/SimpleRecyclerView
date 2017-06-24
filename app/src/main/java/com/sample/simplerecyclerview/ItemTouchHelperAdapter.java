package com.sample.simplerecyclerview;

/**
 * Created by Jia on 2017/6/21.
 */

public interface ItemTouchHelperAdapter {

    void onItemMove(int fromPosition, int toPosition);
    void onItemSwiped(int position);

}
