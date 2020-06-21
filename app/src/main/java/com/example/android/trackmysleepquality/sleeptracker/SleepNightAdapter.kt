/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.trackmysleepquality.sleeptracker

import android.view.LayoutInflater
import android.view.ViewGroup

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.trackmysleepquality.database.SleepNight
import com.example.android.trackmysleepquality.databinding.ListItemSleepNightBinding

class SleepNightAdapter(val onClickListener: SleepNightListener) : ListAdapter<SleepNight, SleepNightAdapter.ViewHolder>(SleepNightDiffCallBack()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SleepNightAdapter.ViewHolder {
        return ViewHolder.from(parent)

    }


    override fun onBindViewHolder(holder: SleepNightAdapter.ViewHolder, position: Int) {

        val item = getItem(position)
        holder.bind(item, onClickListener)

    }


    class ViewHolder private constructor(val binding: ListItemSleepNightBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: SleepNight, onClickListener: SleepNightListener) {
            binding.sleepNight = item
            binding.clickListener = onClickListener
            binding.executePendingBindings()

        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater: LayoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemSleepNightBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }

    }
}

class SleepNightDiffCallBack : DiffUtil.ItemCallback<SleepNight>() {

    override fun areItemsTheSame(oldItem: SleepNight, newItem: SleepNight): Boolean {
        //Se o item mostrado é o mesmo do que saiu. Usado na parte de reciclagem
        return oldItem.nightId == newItem.nightId
    }

    override fun areContentsTheSame(oldItem: SleepNight, newItem: SleepNight): Boolean {
        //Olhar se o conteúdo do item mudou, seria aplicado a edições
        return oldItem == newItem
    }

}

class SleepNightListener(val clickListener: (sleepId: Long) -> Unit) {
    fun onClick(night: SleepNight) = clickListener(night.nightId)
}