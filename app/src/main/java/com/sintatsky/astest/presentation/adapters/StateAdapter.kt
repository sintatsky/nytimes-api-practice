package com.sintatsky.astest.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sintatsky.astest.databinding.ItemErrorStateBinding
import com.sintatsky.astest.databinding.ItemLoadStateBinding

class StateAdapter(private val retry: (() -> Unit)? = null) :
    LoadStateAdapter<StateAdapter.StateViewHolder>() {

    abstract class StateViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        abstract fun bind(loadState: LoadState, retry: (() -> Unit)? = null)
    }

    override fun getStateViewType(loadState: LoadState) = when (loadState) {
        is LoadState.NotLoading -> error("Not supported")
        LoadState.Loading -> PROGRESS
        is LoadState.Error -> ERROR
    }


    override fun onBindViewHolder(holder: StateViewHolder, loadState: LoadState) {
        holder.bind(loadState, retry)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): StateViewHolder {
        return when (loadState) {
            LoadState.Loading -> LoadStateHolder(LayoutInflater.from(parent.context), parent)
            is LoadState.Error -> ErrorStateHolder(LayoutInflater.from(parent.context), parent)
            is LoadState.NotLoading -> error("Not supported")
        }
    }

    private companion object {

        private const val ERROR = 1
        private const val PROGRESS = 0
    }

    class LoadStateHolder internal constructor(private val binding: ItemLoadStateBinding) :
        StateViewHolder(binding.root) {
        override fun bind(loadState: LoadState, retry: (() -> Unit)?) {}

        companion object {
            operator fun invoke(
                layoutInflater: LayoutInflater,
                parent: ViewGroup? = null,
                attachToRoot: Boolean = false
            ): LoadStateHolder {
                return LoadStateHolder(
                    ItemLoadStateBinding.inflate(
                        layoutInflater,
                        parent,
                        attachToRoot
                    )
                )
            }
        }
    }

    class ErrorStateHolder internal constructor(private val binding: ItemErrorStateBinding) :
        StateViewHolder(binding.root) {

        companion object {
            operator fun invoke(
                layoutInflater: LayoutInflater,
                parent: ViewGroup? = null,
                attachToRoot: Boolean = false
            ): ErrorStateHolder {
                return ErrorStateHolder(
                    ItemErrorStateBinding.inflate(
                        layoutInflater,
                        parent,
                        attachToRoot
                    )
                )
            }
        }

        override fun bind(loadState: LoadState, retry: (() -> Unit)?) {
            with(binding) {
                if (loadState is LoadState.Error) {
                    errorMessage.text = loadState.error.localizedMessage
                    retryLoad.setOnClickListener { retry?.invoke() }
                }
            }
        }
    }
}