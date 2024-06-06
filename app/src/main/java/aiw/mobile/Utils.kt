package aiw.mobile.utils

import android.content.Context
import android.net.Uri
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.core.content.ContextCompat
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

object FileUtils {

    private const val FILENAME_FORMAT = "yyyy-MM-dd-HH-mm-ss-SSS"

    fun takePhoto(
        context: Context,
        imageCapture: ImageCapture,
        onImageSaved: (Uri) -> Unit,
        onError: (ImageCaptureException) -> Unit
    ) {
        val photoFile = File(
            context.externalMediaDirs.firstOrNull(),
            SimpleDateFormat(FILENAME_FORMAT, Locale.US)
                .format(System.currentTimeMillis()) + ".jpg"
        )

        val outputOptions = ImageCapture.OutputFileOptions.Builder(photoFile).build()

        imageCapture.takePicture(
            outputOptions, ContextCompat.getMainExecutor(context),
            object : ImageCapture.OnImageSavedCallback {
                override fun onError(exc: ImageCaptureException) {
                    onError(exc)
                }

                override fun onImageSaved(output: ImageCapture.OutputFileResults) {
                    val savedUri = Uri.fromFile(photoFile)
                    onImageSaved(savedUri)
                }
            }
        )
    }
}
