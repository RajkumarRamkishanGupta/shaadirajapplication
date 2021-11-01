package com.tatadigital.qmin.myaccount.view.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.shaadirajapplication.R
import com.example.shaadirajapplication.ShaadiUser.data.User.Result
import com.example.shaadirajapplication.common.Utils
import com.example.shaadirajapplication.databinding.MainViewItemBinding


class UserAdapter(
    private val myUser: ArrayList<Result>,
    private val listener: ItemListener
) :
    RecyclerView.Adapter<UserAdapter.MyUserViewHolder>() {

    interface ItemListener {
        fun onConnect()
        fun onDecline(viewToAnimate: View?, position: Int)
    }

    inner class MyUserViewHolder(private val binding: MainViewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(myUser: Result) {

            binding.tvEmail.text = myUser.email

            myUser.registered?.date?.let {
                val formattedDate = Utils.getFormatedDate(it)
                binding.tvCreatedDate.text = formattedDate
            }
            binding.tvNameAge.text =
                "${myUser.name?.first}${myUser.name?.last},${myUser.dob?.age.toString()}"
            binding.tvLocation.text =
                "${myUser.location?.city},${myUser.location?.state},${myUser.location?.postcode}"

            binding.fabConnect.setOnClickListener { v -> listener.onConnect() }
            binding.fabDecline.setOnClickListener { v ->
                listener.onDecline(
                    binding.fabDecline,
                    adapterPosition
                )
            }

            Glide.with(binding.ivProfileImage.context)
                .load(myUser.picture?.medium)
                .transition(DrawableTransitionOptions.withCrossFade()).circleCrop()
                .into(binding.ivProfileImage)

            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyUserViewHolder {
        val binding = DataBindingUtil.inflate<MainViewItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.main_view_item,
            parent,
            false
        )
        return MyUserViewHolder(binding)
    }

    override fun getItemCount(): Int = myUser.size

    override fun onBindViewHolder(holder: MyUserViewHolder, position: Int) {
        holder.bind(myUser[position])
    }

    fun removeUser(viewToAnimate: View, position: Int) {
        val animation = AnimationUtils.loadAnimation(
            viewToAnimate.context, android.R.anim.slide_out_right
        )
        viewToAnimate.startAnimation(animation)
        myUser.removeAt(position)
        notifyItemRemoved(position)
    }

}