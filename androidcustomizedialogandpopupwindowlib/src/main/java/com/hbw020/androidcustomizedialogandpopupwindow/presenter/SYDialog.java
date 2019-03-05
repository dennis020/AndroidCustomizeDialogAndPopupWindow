package com.hbw020.androidcustomizedialogandpopupwindow.presenter;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;

import com.hbw020.androidcustomizedialogandpopupwindow.R;
import com.hbw020.androidcustomizedialogandpopupwindow.manager.ScreenUtil;


public class SYDialog extends SYBaseDialog implements IDialog {

    private SYDialogController controller;
    private IDialog.OnBuildListener buildListener;
    private static final String FTag = "dialogTag";
    private IDialog.OnDismissListener dismissListener;

    public SYDialog() {
        controller = new SYDialogController(this);
    }

    @Override
    protected int getLayoutRes() {
        return controller.getLayoutRes();
    }

    @Override
    protected View getDialogView() {
        return controller.getDialogView();
    }

    @Override
    protected int getDialogWidth() {
        return controller.getDialogWidth();
    }

    @Override
    protected int getDialogHeight() {
        return controller.getDialogHeight();
    }

    @Override
    protected boolean isCancelableOutside() {
        return controller.isCancelableOutside();
    }

    @Override
    public boolean isCancelable() {
        return controller.isCancelable();
    }

    @Override
    public float getDimAmount() {
        return controller.getDimAmount();
    }

    @Override
    protected int getGravity() {
        return controller.getGravity();
    }

    @Override
    protected int getAnimRes() {
        return controller.getAnimRes();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (controller == null) {
            controller = new SYDialogController(this);
        }
        //设置默认子View布局
        controller.setChildView(view);
        //回调给调用者，用来设置子View及点击事件等
        if (buildListener != null && getLayoutRes() != 0 && getBaseView() != null) {
            buildListener.onBuildChildView(this, getBaseView(), getLayoutRes());
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (controller != null) {
            controller = null;
        }
    }

    @Override
    public void onDestroy() {
        //监听dialog的dismiss
        if (dismissListener != null) {
            dismissListener.onDismiss(this);
        }
        super.onDestroy();
    }

    public static class Builder {
        private SYDialogController.SYParams params;
        private IDialog.OnBuildListener buildListener;
        private IDialog.OnDismissListener dismissListener;

        public Builder(Context context) {
            if (!(context instanceof Activity)) {
                throw new IllegalArgumentException("Context must be Activity");
            }
            params = new SYDialogController.SYParams();
            params.fragmentManager = ((Activity) context).getFragmentManager();
            params.context = context;
        }

        /**
         * 设置DialogView
         *
         * @param layoutRes 布局文件
         * @return Builder
         */
        public Builder setDialogView(@LayoutRes int layoutRes) {
            params.layoutRes = layoutRes;
            return this;
        }

        /**
         * 设置DialogView
         *
         * @param dialogView View
         * @return Builder
         */
        public Builder setDialogView(View dialogView) {
            params.dialogView = dialogView;
            return this;
        }

        /**
         * 设置屏幕宽度百分比
         *
         * @param percentage 0.0f~1.0f
         * @return Builder
         */
        public Builder setScreenWidthP(float percentage) {
            params.dialogWidth = (int) (ScreenUtil.getScreenWidth((Activity) params.context) * percentage);
            return this;
        }

        /**
         * 设置屏幕高度百分比
         *
         * @param percentage 0.0f~1.0f
         * @return Builder
         */
        public Builder setScreenHeightP(float percentage) {
            params.dialogHeight = (int) (ScreenUtil.getScreenHeight((Activity) params.context) * percentage);
            return this;
        }

        /**
         * 监听dialog的dismiss
         *
         * @param dismissListener IDialog.OnDismissListener
         * @return Builder
         */
        public Builder setOnDismissListener(IDialog.OnDismissListener dismissListener) {
            this.dismissListener = dismissListener;
            return this;
        }

        /**
         * 设置Dialog的宽度
         *
         * @param width 宽度
         * @return Builder
         */
        public Builder setWidth(int width) {
            params.dialogWidth = width;
            return this;
        }

        /**
         * 设置Dialog的高度
         *
         * @param height 高度
         * @return Builder
         */
        public Builder setHeight(int height) {
            params.dialogHeight = height;
            return this;
        }

        /**
         * 设置背景色色值
         *
         * @param percentage 0.0f~1.0f 1.0f为完全不透明
         * @return Builder
         */
        public Builder setWindowBackgroundP(float percentage) {
            params.dimAmount = percentage;
            return this;
        }

        /**
         * 设置Gravity
         *
         * @param gravity Gravity
         * @return Builder
         */
        public Builder setGravity(int gravity) {
            params.gravity = gravity;
            return this;
        }

        /**
         * 设置dialog外点击是否可以让dialog消失
         *
         * @param cancelableOutSide true 则在dialog屏幕外点击可以使dialog消失
         * @return Builder
         */
        public Builder setCancelableOutSide(boolean cancelableOutSide) {
            params.isCancelableOutside = cancelableOutSide;
            return this;
        }

        /**
         * 设置是否屏蔽物理返回键
         *
         * @param cancelable true 点击物理返回键可以让dialog消失；反之不消失
         * @return Builder
         */
        public Builder setCancelable(boolean cancelable) {
            params.cancelable = cancelable;
            return this;
        }

        /**
         * 构建子View的listener
         *
         * @param listener IDialog.OnBuildListener
         * @return Builder
         */
        public Builder setBuildChildListener(IDialog.OnBuildListener listener) {
            this.buildListener = listener;
            return this;
        }

        /**
         * 设置dialog的动画效果
         *
         * @param animStyle 动画资源文件
         * @return Builder
         */
        public Builder setAnimStyle(int animStyle) {
            params.animRes = animStyle;
            return this;
        }


        /**
         * 设置默认右侧点击按钮
         *
         * @param onclickListener IDialog.OnClickListener
         * @return Builder
         */
        public Builder setPositiveButton(IDialog.OnClickListener onclickListener) {
            return setPositiveButton("确定", onclickListener);
        }

        /**
         * 设置默认右侧点击按钮及文字
         *
         * @param btnStr          右侧文字
         * @param onclickListener IDialog.OnClickListener
         * @return Builder
         */
        public Builder setPositiveButton(CharSequence btnStr, IDialog.OnClickListener onclickListener) {
            params.positiveBtnListener = onclickListener;
            params.positiveStr = btnStr;
            params.showBtnRight = true;
            return this;
        }

        /**
         * 设置左侧按钮
         *
         * @param onclickListener IDialog.OnClickListener
         * @return Builder
         */
        public Builder setNegativeButton(IDialog.OnClickListener onclickListener) {
            return setNegativeButton("取消", onclickListener);
        }

        /**
         * 设置左侧文字及按钮
         *
         * @param btnStr          文字
         * @param onclickListener IDialog.OnClickListener
         * @return Builder
         */
        public Builder setNegativeButton(CharSequence btnStr, IDialog.OnClickListener onclickListener) {
            params.negativeBtnListener = onclickListener;
            params.negativeStr = btnStr;
            params.showBtnLeft = true;
            return this;
        }

        /**
         * 设置默认dialog的title
         *
         * @param title 标题
         * @return Builder
         */
        public Builder setTitle(CharSequence title) {
            params.titleStr = title;
            return this;
        }

        /**
         * 设置默认dialog的内容
         *
         * @param content 内容
         * @return Builder
         */
        public Builder setContent(CharSequence content) {
            params.contentStr = content;
            return this;
        }

        private SYDialog create() {
            SYDialog dialog = new SYDialog();
            params.apply(dialog.controller);
            dialog.buildListener = buildListener;
            dialog.dismissListener = dismissListener;
            return dialog;
        }

        /**
         * 展示Dialog
         *
         * @return SYDialog
         */
        public SYDialog show() {
            if (params.layoutRes <= 0 && params.dialogView == null) {
                //如果没有设置布局 提供默认设置
                setDefaultOption();
            }
            SYDialog dialog = create();
            if (params.context == null) return dialog;
            if (params.context instanceof Activity) {
                Activity activity = (Activity) params.context;
                if (activity.isFinishing()) return dialog;
            }
            removePreDialog();
            dialog.show(params.fragmentManager, FTag);
            return dialog;
        }

        /**
         * 设置默认Dialog的配置
         */
        private void setDefaultOption() {
            params.cancelable = false;
            params.isCancelableOutside = false;
            params.gravity = Gravity.CENTER;
            params.layoutRes = R.layout.layout_dialog_default;
            params.dimAmount = 0.5f;
            params.dialogWidth = (int) (ScreenUtil.getScreenWidth((Activity) params.context) * 0.85f);
            params.dialogHeight = WindowManager.LayoutParams.WRAP_CONTENT;
        }

        /**
         * 移除之前的dialog
         */
        private void removePreDialog() {
            FragmentTransaction ft = params.fragmentManager.beginTransaction();
            Fragment prev = params.fragmentManager.findFragmentByTag(FTag);
            if (prev != null) {
                ft.remove(prev);
            }
            ft.commitAllowingStateLoss();
        }


    }


}
