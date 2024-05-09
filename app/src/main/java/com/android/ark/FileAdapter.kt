import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.ark.R

data class FileData(
    val title: String = "", // Assuming 'title' is the file name
    val url: String = "" // URL of the file
)

class FileAdapter(private val files: List<FileData>) : RecyclerView.Adapter<FileAdapter.ViewHolder>() {

    class ViewHolder(@SuppressLint("RestrictedApi") itemView: android.view.View) : RecyclerView.ViewHolder(itemView) {
        val fileName: TextView = itemView.findViewById(R.id.fileName)
        val downloadButton: Button = itemView.findViewById(R.id.downloadButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.file_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val file = files[position]
        holder.fileName.text = file.title
        holder.downloadButton.setOnClickListener {
            downloadFile(file.url)
        }
    }

    override fun getItemCount() = files.size

    private fun downloadFile(url: String) {
        // Download the file using the Firebase Storage SDK
    }
}