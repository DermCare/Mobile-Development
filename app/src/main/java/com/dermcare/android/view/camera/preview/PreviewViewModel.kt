package com.dermcare.android.view.camera.preview

import androidx.lifecycle.ViewModel
import com.dermcare.android.data.DataRepository
import java.io.File

class PreviewViewModel(private val repository: DataRepository) : ViewModel() {

    fun predictAction(file: File) = repository.predict(file)
}