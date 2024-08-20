package com.learning.tools

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import java.util.concurrent.ConcurrentHashMap

/**
 *   send a sticky event
 *   LiveDataBus.with<String>("stickyData").setStickyData("stickyData")
 *
 *   observer a sticky event
 *   LiveDataBus.with<String>("stickyData").observerSticky(this, true) {
 *      println("data from LiveDataBus: $it")
 *   }
 *
 * Create by Qing at 2024/8/20 11:18
 */
object LiveDataEventBus {

    private val eventMap = ConcurrentHashMap<String, StickyLiveData<*>>()

    fun <T> with(eventName: String): StickyLiveData<T> {
        //基于事件名称订阅、分发消息，
        //由于一个livedata只能发送一种数据类型
        //所以不同的event事件，需要使用不同的livedata实例 去分发
        var liveData = eventMap[eventName]
        if (liveData == null) {
            liveData = StickyLiveData<T>(eventName)
            eventMap[eventName] = liveData
        }
        @Suppress("UNCHECKED_CAST")
        return liveData as StickyLiveData<T>
    }

    class StickyLiveData<T>(private val eventName: String) : LiveData<T>() {
        internal var mStickyData: T? = null
        internal var mVersion = 0

        // 在主线程去发送数据
        fun setStickyData(stickyData: T) {
            mStickyData = stickyData
            setValue(stickyData)
        }

        // 不受线程的限制
        @Suppress("UNUSED")
        fun postStickyData(stickyData: T) {
            mStickyData = stickyData
            postValue(stickyData)
        }

        override fun setValue(value: T) {
            mVersion++
            super.setValue(value)
        }

        override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
            observerSticky(owner, false, observer)
        }

        // 允许指定注册的观察则是否需要关心黏性事件
        // sticky =true, 如果之前存在已经发送的数据，那么这个observer会收到之前的黏性事件消息
        fun observerSticky(owner: LifecycleOwner, sticky: Boolean, observer: Observer<in T>) {
            owner.lifecycle.addObserver(LifecycleEventObserver { _, event ->
                //监听宿主发生销毁事件，主动把livedata移除掉。
                if (event == Lifecycle.Event.ON_DESTROY) {
                    eventMap.remove(eventName)
                }
            })
            super.observe(owner, StickyObserver(this, sticky, observer))
        }
    }

    class StickyObserver<T>(
        private val stickyLiveData: StickyLiveData<T>,
        private val sticky: Boolean,
        private val observer: Observer<in T>
    ) : Observer<T> {
        // lastVersion 和 livedata 的 mVersion 对齐的原因，就是为控制黏性事件的分发。
        // sticky 不等于true , 只能接收到注册之后发送的消息，如果要接收黏性事件，则sticky需要传递为true
        private var lastVersion = stickyLiveData.mVersion
        override fun onChanged(t: T) {
            if (lastVersion >= stickyLiveData.mVersion) {
                // 就说明stickyLiveData  没有更新的数据需要发送。
                if (sticky && stickyLiveData.mStickyData != null) {
                    observer.onChanged(stickyLiveData.mStickyData!!)
                }
                return
            }
            lastVersion = stickyLiveData.mVersion
            observer.onChanged(t)
        }
    }
}