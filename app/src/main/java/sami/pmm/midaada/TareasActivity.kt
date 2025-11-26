package sami.pmm.midaada

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class TareasActivity : AppCompatActivity() {

    private data class Task(var text: String, var completed: Boolean = false)

    private val allTasks = mutableListOf<Task>()
    private lateinit var adapter: BaseAdapter
    private enum class Filter { ALL, COMPLETED, PENDING }
    private var currentFilter: Filter = Filter.ALL

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tareas)

        val etTask = findViewById<EditText>(R.id.etTaskInput)
        val btnAdd = findViewById<Button>(R.id.btnAddTask)
        val listView = findViewById<ListView>(R.id.lvTasks)
        val rg = findViewById<RadioGroup>(R.id.rgTaskFilter)
        val rbAll = findViewById<RadioButton>(R.id.rbAll)
        val rbCompleted = findViewById<RadioButton>(R.id.rbCompleted)
        val rbPending = findViewById<RadioButton>(R.id.rbPending)

        adapter = object : BaseAdapter() {
            private fun visible(): List<Task> = when (currentFilter) {
                Filter.ALL -> allTasks
                Filter.COMPLETED -> allTasks.filter { it.completed }
                Filter.PENDING -> allTasks.filter { !it.completed }
            }
            override fun getCount(): Int = visible().size
            override fun getItem(position: Int): Any = visible()[position]
            override fun getItemId(position: Int): Long = position.toLong()
            override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                val holder: RowHolder
                val row: LinearLayout
                if (convertView == null) {
                    row = LinearLayout(this@TareasActivity).apply {
                        orientation = LinearLayout.HORIZONTAL
                        val pad = 8.dp()
                        setPadding(pad, pad, pad, pad)
                    }
                    val check = CheckBox(this@TareasActivity)
                    val text = TextView(this@TareasActivity).apply {
                        layoutParams = LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1f).apply {
                            marginStart = 8.dp()
                            marginEnd = 8.dp()
                        }
                    }
                    val deleteBtn = ImageButton(this@TareasActivity).apply {
                        setImageResource(android.R.drawable.ic_menu_delete)
                        contentDescription = "Eliminar"
                        background = null
                    }
                    row.addView(check)
                    row.addView(text)
                    row.addView(deleteBtn)
                    holder = RowHolder(check, text, deleteBtn)
                    row.tag = holder
                } else {
                    row = convertView as LinearLayout
                    holder = row.tag as RowHolder
                }

                val item = visible()[position]
                holder.text.text = item.text
                holder.check.setOnCheckedChangeListener(null)
                holder.check.isChecked = item.completed
                holder.check.setOnCheckedChangeListener { _, isChecked ->
                    item.completed = isChecked
                    notifyDataSetChanged()
                }
                holder.delete.setOnClickListener {
                    allTasks.remove(item)
                    notifyDataSetChanged()
                }
                return row
            }
        }
        listView.adapter = adapter

        btnAdd.setOnClickListener {
            val txt = etTask.text?.toString()?.trim() ?: ""
            if (TextUtils.isEmpty(txt)) {
                Toast.makeText(this, getString(R.string.tasks_empty_warning), Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            allTasks.add(Task(txt))
            etTask.setText("")
            adapter.notifyDataSetChanged()
        }

        rbAll.isChecked = true
        rg.setOnCheckedChangeListener { _, checkedId ->
            currentFilter = when (checkedId) {
                rbCompleted.id -> Filter.COMPLETED
                rbPending.id -> Filter.PENDING
                else -> Filter.ALL
            }
            adapter.notifyDataSetChanged()
        }
    }

    private data class RowHolder(val check: CheckBox, val text: TextView, val delete: ImageButton)
    private fun Int.dp(): Int = (this * resources.displayMetrics.density).toInt()
}