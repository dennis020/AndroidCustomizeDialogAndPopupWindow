package com.hbw020.androidcustomizedialogandpopupwindow.demo.utils;

import android.view.View;


public class CommonUtil {
    /**
     * 测量View的宽高
     *
     * @param view View
     */
    public static void measureWidthAndHeight(View view) {
        int widthMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        view.measure(widthMeasureSpec, heightMeasureSpec);
    }

//    /**
//     * 对数据进行排序
//     *
//     * @param list 要进行排序的数据源
//     */
//    public static void sortData(List<ContactBean> list) {
//        if (list == null || list.size() == 0) return;
//        for (int i = 0; i < list.size(); i++) {
//            ContactBean bean = list.get(i);
//            String tag = Pinyin.toPinyin(bean.getName().substring(0, 1).charAt(0)).substring(0, 1);
//            if (tag.matches("[A-Z]")) {
//                bean.setIndexTag(tag);
//            } else {
//                bean.setIndexTag("#");
//            }
//        }
//        Collections.sort(list, new Comparator<ContactBean>() {
//            @Override
//            public int compare(ContactBean o1, ContactBean o2) {
//                if ("#".equals(o1.getIndexTag())) {
//                    return 1;
//                } else if ("#".equals(o2.getIndexTag())) {
//                    return -1;
//                } else {
//                    return o1.getIndexTag().compareTo(o2.getIndexTag());
//                }
//            }
//        });
//    }
//
//    /**
//     * @param beans 数据源
//     * @return tags 返回一个包含所有Tag字母在内的字符串
//     */
//    public static String getTags(List<ContactBean> beans) {
//        StringBuilder builder = new StringBuilder();
//        for (int i = 0; i < beans.size(); i++) {
//            if (!builder.toString().contains(beans.get(i).getIndexTag())) {
//                builder.append(beans.get(i).getIndexTag());
//            }
//        }
//        return builder.toString();
//    }
}
