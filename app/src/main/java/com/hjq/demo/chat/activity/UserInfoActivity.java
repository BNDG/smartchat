package com.hjq.demo.chat.activity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.hjq.demo.R;
import com.hjq.demo.aop.Log;
import com.hjq.demo.aop.SingleClick;
import com.hjq.demo.chat.cons.Constant;
import com.hjq.demo.chat.entity.User;
import com.hjq.demo.chat.listener.ContactCallback;
import com.hjq.demo.chat.utils.AvatarGenerator;
import com.hjq.demo.chat.utils.DensityUtil;
import com.hjq.demo.utils.CheckUtil;

import butterknife.BindView;
import butterknife.OnClick;
import com.bndg.smack.SmartCommHelper;
import com.bndg.smack.enums.SmartConversationType;

/**
 * 用户详情
 * 备注 更多信息- 发消息
 *
 * @author zhou
 */
public class UserInfoActivity extends ChatBaseActivity {

    @BindView(R.id.ll_root)
    LinearLayout mRootLl;

    @BindView(R.id.ll_nick_name)
    LinearLayout mNickNameLl;

    @BindView(R.id.tv_nick_name)
    TextView mNickNameTv;

    @BindView(R.id.tv_name)
    TextView mNameTv;

    @BindView(R.id.sdv_avatar)
    ImageView mAvatarSdv;

    @BindView(R.id.iv_sex)
    ImageView mSexIv;

    @BindView(R.id.tv_wx_id)
    TextView mWxIdTv;

    @BindView(R.id.iv_more)
    ImageView mSettingIv;

    // 设置备注和标签
    @BindView(R.id.rl_edit_contact)
    RelativeLayout mEditContactRl;

    // 标签
    @BindView(R.id.rl_tags)
    RelativeLayout mTagsRl;

    @BindView(R.id.tv_tags)
    TextView mTagsTv;

    // 描述
    @BindView(R.id.rl_desc)
    RelativeLayout mDescRl;

    @BindView(R.id.tv_desc)
    TextView mDescTv;

    // 星标好友
    @BindView(R.id.iv_star_friends)
    ImageView mStarFriendsIv;

    // 操作按钮  根据是否好友关系分为如下两种
    // 是好友: 发送消息
    // 非好友: 添加到通讯录
    @BindView(R.id.rl_operate)
    RelativeLayout mOperateRl;
    @BindView(R.id.rl_add)
    View rlAdd;

    // 朋友圈
    @BindView(R.id.sdv_moments_photo_1)
    ImageView mMomentsPhoto1Sdv;

    @BindView(R.id.sdv_moments_photo_2)
    ImageView mMomentsPhoto2Sdv;

    @BindView(R.id.sdv_moments_photo_3)
    ImageView mMomentsPhoto3Sdv;

    @BindView(R.id.sdv_moments_photo_4)
    ImageView mMomentsPhoto4Sdv;

    @BindView(R.id.rl_moments)
    RelativeLayout mMomentsRl;

    @BindView(R.id.tv_operate)
    TextView mOperateTv;

    @BindView(R.id.rl_voice_or_video_call)
    RelativeLayout mVoiceOrVideoCallRl;

    @BindView(R.id.tv_voice_or_video_call)
    TextView mVoiceOrVideoCallTv;

    @BindView(R.id.rl_blocked_contact_tips)
    RelativeLayout mBlockedContactTipsRl;

    User mContact;
    String mContactId;
    private String mFrom;

    @Log
    public static void start(Context context, String param) {
        Intent intent = new Intent(context, UserInfoActivity.class);
        intent.putExtra(Constant.CONTACT_ID, param);
        context.startActivity(intent);
    }

    @Log
    public static void start(Context context, String id, String from) {
        Intent intent = new Intent(context, UserInfoActivity.class);
        intent.putExtra(Constant.CONTACT_ID, id);
        intent.putExtra(Constant.FROM_WHERE, from);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_user_info;
    }

    @Override
    public void initView() {
        mSettingIv.setVisibility(View.VISIBLE);
        setTitleStrokeWidth(mOperateTv);
        setTitleStrokeWidth(mVoiceOrVideoCallTv);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        mContactId = getIntent().getStringExtra(Constant.CONTACT_ID);
        mFrom = getIntent().getStringExtra(Constant.FROM_WHERE);
    }

    @SingleClick
    @OnClick({R.id.iv_more, R.id.sdv_avatar, R.id.rl_edit_contact, R.id.ll_mobiles, R.id.rl_tags,
            R.id.rl_desc, R.id.rl_moments, R.id.rl_more, R.id.rl_operate, R.id.rl_voice_or_video_call, R.id.rl_add})
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            // 用户设置
            case R.id.iv_more:
                UserSettingActivity.start(UserInfoActivity.this, mContactId, mContact.getUserNickName(),
                        mContact.isFriend() ? Constant.IS_FRIEND : Constant.IS_NOT_FRIEND);
                break;
            // 头像
            case R.id.sdv_avatar:
                BigImageActivity.start(UserInfoActivity.this, mContact.getUserId(), mContact.getUserNickName());
                break;
            // 进入编辑联系人页(设置备注和标签)
            // 设置备注和标签
            // 电话号码
            // 标签
            // 描述
            case R.id.rl_edit_contact:
            case R.id.ll_mobiles:
            case R.id.rl_tags:
            case R.id.rl_desc:
                intent = new Intent(UserInfoActivity.this, EditContactActivity.class);
                intent.putExtra("contactId", mContact.getUserId());
                intent.putExtra("isFriend", mContact.isFriend() ? Constant.IS_FRIEND : Constant.IS_NOT_FRIEND);
                startActivity(intent);
                break;
            // 朋友圈
            case R.id.rl_moments:
//                intent = new Intent(UserInfoActivity.this, ContactMomentsActivity.class);
//                intent.putExtra("userId", mContactId);
//                startActivity(intent);
                break;
            // 更多信息
            case R.id.rl_more:
                intent = new Intent(UserInfoActivity.this, UserInfoMoreActivity.class);
                intent.putExtra("contactId", mContactId);
                startActivity(intent);
                break;
            // 发消息
            case R.id.rl_operate:
                ChatActivity.start(this, SmartConversationType.SINGLE.name(),
                        mContactId, mContact.getUserNickName());
                break;
            case R.id.rl_voice_or_video_call:

                break;

            case R.id.rl_add:
                intent = new Intent(this, NewFriendsApplyConfirmActivity.class);
                intent.putExtra("contactId", mContactId);
                intent.putExtra("from", mFrom);
                startActivity(intent);
                break;
        }
    }


    // 渲染数据
    private void loadData(User user) {
        // 渲染朋友圈图片
        if (user.isFriend()) {
            mOperateRl.setVisibility(View.VISIBLE);
            rlAdd.setVisibility(View.GONE);
        } else {
            mOperateRl.setVisibility(View.GONE);
            rlAdd.setVisibility(View.VISIBLE);
        }
        AvatarGenerator.loadAvatar(this, mContact.getUserId(), mContact.getUserNickName(),
                mAvatarSdv, true);
        if (Constant.USER_SEX_MALE.equals(user.getUserSex())) {
            mSexIv.setImageResource(R.drawable.icon_sex_male);
        } else if (Constant.USER_SEX_FEMALE.equals(user.getUserSex())) {
            mSexIv.setImageResource(R.drawable.icon_sex_female);
        } else {
            mSexIv.setVisibility(View.GONE);
        }
        String userPhone = user.getUserPhone();
        if (SmartCommHelper.getInstance().isDeveloperMode()) {
            if (!TextUtils.isEmpty(user.getUserId())) {
                mWxIdTv.setText(String.format(getString(R.string.account_label), user.getUserId()));
            }
        } else {
            if (!TextUtils.isEmpty(userPhone)) {
                mWxIdTv.setText(String.format(getString(R.string.account_label), userPhone));
            } else {
                mWxIdTv.setText(String.format(getString(R.string.account_label), CheckUtil.getNotNullString(user.getUserAccount())));
            }
        }

        // 备注
        if (!TextUtils.isEmpty(user.getUserContactAlias())) {
            mNameTv.setText(user.getUserContactAlias());
            mNickNameLl.setVisibility(View.VISIBLE);
            mNickNameTv.setText(String.format(getString(R.string.nick_name_label), user.getUserNickName()));
        } else {
            mNickNameLl.setVisibility(View.GONE);
            mNameTv.setText(user.getUserNickName());
        }

        // 标签
        mTagsRl.setVisibility(View.GONE);

        // 描述
        if (!TextUtils.isEmpty(user.getUserContactDesc())) {
            mDescRl.setVisibility(View.VISIBLE);
            mDescTv.setText(user.getUserContactDesc());
        } else {
            mDescRl.setVisibility(View.GONE);
        }

        // 电话号码,标签,描述任一信息不为空则隐藏"设置备注和标签"
        if (!TextUtils.isEmpty(user.getUserContactDesc())) {
            mEditContactRl.setVisibility(View.GONE);
        } else {
            mEditContactRl.setVisibility(View.VISIBLE);
        }

        // 是否星标好友
        if (Constant.CONTACT_IS_STARRED.equals(user.getIsStarred())) {
            mStarFriendsIv.setVisibility(View.VISIBLE);
        } else {
            mStarFriendsIv.setVisibility(View.GONE);
        }

        // 是否黑名单用户
        if (Constant.CONTACT_IS_BLOCKED.equals(user.getIsBlocked())) {
            mVoiceOrVideoCallRl.setVisibility(View.GONE);
            mBlockedContactTipsRl.setVisibility(View.VISIBLE);
        } else {
//            mVoiceOrVideoCallRl.setVisibility(View.VISIBLE);
            mBlockedContactTipsRl.setVisibility(View.GONE);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        userDao.getUserById(UserInfoActivity.this, mContactId, new ContactCallback() {
            @Override
            public void getUser(@Nullable User userById) {
                if (userById == null) {
                    return;
                }
                mContact = userById;
                loadData(userById);
            }
        });
    }

    private void addMobileView(int index, int mobileListSize, String mobile) {
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                DensityUtil.dip2px(this, 48));
        View view = LayoutInflater.from(this).inflate(R.layout.item_user_info_contact_mobile, null);
        view.setLayoutParams(lp);
        TextView mobileTempTv = view.findViewById(R.id.tv_mobile_temp);
        View shortDividerView = view.findViewById(R.id.view_divider_short);
        View longDividerView = view.findViewById(R.id.view_divider_long);
        if (index == 0) {
            mobileTempTv.setVisibility(View.VISIBLE);
        } else {
            mobileTempTv.setVisibility(View.INVISIBLE);
        }

        if (index == mobileListSize - 1) {
            longDividerView.setVisibility(View.VISIBLE);
            shortDividerView.setVisibility(View.GONE);
        } else {
            longDividerView.setVisibility(View.GONE);
            shortDividerView.setVisibility(View.VISIBLE);
        }

        TextView mobileTv = view.findViewById(R.id.tv_mobile);
        mobileTv.setText(mobile);

        mobileTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupWindow(mobile);
            }
        });

    }


    /**
     * 显示底部弹出框
     */
    private void showPopupWindow(String mobile) {
        LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.popup_window_contact_phone, null);
        // 给popwindow加上动画效果
        LinearLayout mPopRootLl = view.findViewById(R.id.ll_pop_root);
        view.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in));
        mPopRootLl.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.push_bottom_in));
        // 设置popwindow的宽高
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        PopupWindow mPopupWindow = new PopupWindow(view, dm.widthPixels, ViewGroup.LayoutParams.WRAP_CONTENT);

        // 使其聚集
        mPopupWindow.setFocusable(true);
        // 设置允许在外点击消失
        mPopupWindow.setOutsideTouchable(true);

        // 这个是为了点击“返回Back”也能使其消失，并且并不会影响你的背景
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
        backgroundAlpha(0.5f);  //透明度

        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(1f);
            }
        });
        // 弹出的位置
        mPopupWindow.showAtLocation(mRootLl, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);

        // 呼叫
        RelativeLayout mCallRl = view.findViewById(R.id.rl_call);
        mCallRl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPopupWindow.dismiss();
                Intent intent = new Intent(Intent.ACTION_DIAL);
                Uri data = Uri.parse("tel:" + mobile);
                intent.setData(data);
                startActivity(intent);
            }
        });

        // 复制
        RelativeLayout mCopyRl = view.findViewById(R.id.rl_copy);
        mCopyRl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPopupWindow.dismiss();
                // 获取剪贴板管理器
                ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                // 创建普通字符型ClipData
                ClipData mClipData = ClipData.newPlainText("Label", mobile);
                // 将ClipData内容放到系统剪贴板里
                cm.setPrimaryClip(mClipData);
                Toast.makeText(UserInfoActivity.this, "已复制", Toast.LENGTH_SHORT).show();
            }
        });

        // 取消
        RelativeLayout mCancelRl = view.findViewById(R.id.rl_cancel);
        mCancelRl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPopupWindow.dismiss();
            }
        });
    }

    /**
     * 设置添加屏幕的背景透明度
     * 1.0完全不透明，0.0f完全透明
     *
     * @param bgAlpha 透明度值
     */
    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        // 0.0-1.0
        lp.alpha = bgAlpha;
        getWindow().setAttributes(lp);
    }

}