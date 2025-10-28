import android.Manifest
import android.app.AlertDialog
import android.content.Context
import android.net.Uri
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.FileProvider
import java.io.File

@Composable
fun rememberImagePicker(
    onImageSelected: (Uri?) -> Unit,
    onPermissionDenied: () -> Unit = {}
): ImagePicker {

    val context = LocalContext.current

    // Для камеры — создаем временный файл
    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture(),
        onResult = { success ->
            if (success) {
                val uri = getTempCameraUri(context)
                onImageSelected(uri)
            } else {
                onImageSelected(null)
            }
        }
    )

    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            onImageSelected(uri)
        }
    )

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted ->
            if (isGranted) {
                showImageSourceDialog(context, cameraLauncher, galleryLauncher)
            } else {
                onPermissionDenied()
            }
        }
    )

    return remember {
        ImagePicker {
            val permission = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                Manifest.permission.READ_MEDIA_IMAGES
            } else {
                Manifest.permission.READ_EXTERNAL_STORAGE
            }

            permissionLauncher.launch(permission)
        }
    }
}

private fun showImageSourceDialog(
    context: Context,
    cameraLauncher: ActivityResultLauncher<Uri>,
    galleryLauncher: ActivityResultLauncher<String>
) {
    val builder = AlertDialog.Builder(context)
    builder.setTitle("Выберите источник")
        .setItems(arrayOf("Сделать фото", "Выбрать из галереи")) { _, which ->
            when (which) {
                0 -> {
                    // Камера
                    val uri = getTempCameraUri(context)
                    cameraLauncher.launch(uri)
                }
                1 -> {
                    // Галерея
                    galleryLauncher.launch("image/*")
                }
            }
        }
        .setNegativeButton("Отмена", null)
        .show()
}

private fun getTempCameraUri(context: Context): Uri {
    val filename = "temp_camera_photo.jpg"
    val file = File(context.cacheDir, filename)
    return FileProvider.getUriForFile(
        context,
        "${context.packageName}.fileprovider",
        file
    )
}

class ImagePicker(
    private val onPickImage: () -> Unit
) {
    fun pickImage() {
        onPickImage()
    }
}
