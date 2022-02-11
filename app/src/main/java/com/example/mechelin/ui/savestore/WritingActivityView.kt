package com.example.mechelin.ui.savestore

import com.example.mechelin.data.remote.SaveStoreResponse
import retrofit2.Response

interface WritingActivityView {
    fun onPostWritingSuccess(response: SaveStoreResponse)

    fun onPostWritingFailure(message: String)
}