package com.lilin.android.todayinformation.refresh;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.lilin.android.todayinformation.R;
import com.lilin.android.todayinformation.databinding.UltiHeaderLayoutBinding;
/**
 * 创建日期：2021/10/12 14:09
 *
 * @author lilin
 * @version 1.0
 * 包名： com.lilin.android.todayinformation.refresh
 * 类说明：
 */

/**
 * 默认的刷新样式
 */
public class DefaultRefreshHeaderManager extends BaseRefreshHeaderManager {
    private TextView headerText;

    public DefaultRefreshHeaderManager(Context context) {
        super(context);
    }

    @Override
    View getHeaderView() {
        View view = mLayoutInflater.inflate(R.layout.ulti_header_layout, null, false);
        UltiHeaderLayoutBinding bind = UltiHeaderLayoutBinding.bind(view);
        headerText = bind.headerText;
        return view;
    }

    @Override
    public void idle() {
        headerText.setText("下拉刷新");
    }

    @Override
    public void pullingDown() {
        headerText.setText("下拉刷新");
    }

    @Override
    public void release() {
        headerText.setText("释放刷新");
    }

    @Override
    public void refreshing() {
        headerText.setText("正在刷新");
    }

}
