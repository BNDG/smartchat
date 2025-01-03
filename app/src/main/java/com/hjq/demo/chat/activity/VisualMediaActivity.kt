package com.hjq.demo.chat.activity

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.TextUtils
import android.widget.TextView
import androidx.core.app.ActivityOptionsCompat
import com.bndg.smack.enums.SmartContentType
import com.chad.library.adapter.base.listener.OnLoadMoreListener
import com.hjq.demo.R
import com.hjq.demo.chat.adapter.VisualMediaAdapter
import com.hjq.demo.chat.cons.Constant
import com.hjq.demo.chat.dao.MessageDao
import com.hjq.demo.chat.entity.ChatMessage
import com.hjq.demo.chat.widget.LQRRecyclerView
import com.hjq.demo.ui.activity.ImagePreviewActivity
import com.hjq.demo.utils.Trace
import java.io.File

/**
 * @author r
 * @date 2024/12/25
 * @description Brief description of the file content.
 */
class VisualMediaActivity : ChatBaseActivity(), OnLoadMoreListener {
    private val rvList: LQRRecyclerView by lazy { findViewById(R.id.rv_list) }
    private var adapter: VisualMediaAdapter = VisualMediaAdapter(mutableListOf())
    private var conversationId: String? = null
    var pageCount = 0
    override fun getLayoutId(): Int {
        return R.layout.data_list_activity2
    }

    override fun initView() {
        findViewById<TextView>(R.id.tv_title).text = getString(R.string.image)
    }

    override fun initData() {
        rvList.adapter = adapter
        adapter.setOnItemClickListener { _, imgView, position ->
            val item = adapter.getItem(position)
            if (item.messageType == SmartContentType.IMAGE) {
                val uri = if (!TextUtils.isEmpty(item.fileLocalPath)) {
                    // 本地读
                    Uri.fromFile(File(item.fileLocalPath))
                } else {
                    // 网络获取
                    Uri.parse(item.messageContent)
                }
                // 生成转场动画的bundle对象
                val bundle = ActivityOptionsCompat.makeSceneTransitionAnimation(this, imgView, "share_image")
                    .toBundle()
                // 如果有参数传递，可以这么添加
                bundle?.putString(Constant.MESSAGE_FILE_LOCAL, item.fileLocalPath)
                bundle?.putString(Constant.MESSAGE_ORIGIN_ID, item.originId)
                ImagePreviewActivity.start(context, uri.toString(), bundle)
            }
        }
        conversationId = intent.getStringExtra(Constant.CONVERSATION_ID)
        searchRecord()
        initLoadMore()
    }

    fun initLoadMore() {
        adapter.let {
            it.animationEnable = true
            it.loadMoreModule.setOnLoadMoreListener(this)
            it.loadMoreModule.isAutoLoadMore = true
            it.loadMoreModule.isEnableLoadMoreIfNotFullPage = false
        }
    }

    override fun onLoadMore() {
        searchRecord(false)
    }

    private fun searchRecord(isRefresh: Boolean = true) {
        if (isRefresh) {
            pageCount = 0
        }
        MessageDao.getInstance().searchMediaMsgByConversationId(
            conversationId,
            pageCount,
            object : MessageDao.MessageDaoCallback {
                override fun getSearchMessages(chatMessages: MutableList<ChatMessage>?) {
                    chatMessages?.let {
                        if (isRefresh) {
                            adapter.setList(it)
                        } else {
                            adapter.addData(it)
                        }
                        adapter.loadMoreModule.isEnableLoadMore = true
                        if (chatMessages.size < MessageDao.SEARCH_PAGE_SIZE) {
                            Trace.d("loadMoreEnd")
                            adapter.loadMoreModule.loadMoreEnd(true)
                        } else {
                            adapter.loadMoreModule.loadMoreComplete()
                            pageCount++
                        }
                    }
                }
            })
    }

    override fun initListener() {
    }

    companion object {

        fun start(context: Context, extras: Bundle?) {
            val intent = Intent(context, VisualMediaActivity::class.java)
            extras?.let {
                intent.putExtras(it)
            }
            context.startActivity(intent)
        }
    }
}