package ankushraj6007om.example.todo.utils

import android.location.GnssAntennaInfo.Listener
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ankushraj6007om.example.todo.databinding.EachTodoBinding
import ankushraj6007om.example.todo.fragments.HomeFragment

class ToDoAdapter(private val list: MutableList<ToDoData>):
RecyclerView.Adapter<ToDoAdapter.ToDoViewHolder>(){
    private var listener:ToDoAdapterClicksInterface?=null
    fun setListener(listener: HomeFragment){
        this.listener=listener
    }
    inner class ToDoViewHolder(val binding:EachTodoBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder {
        val binding=EachTodoBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ToDoViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }
    interface ToDoAdapterClicksInterface{
        fun onDeleteTaskBtnClicked(toDoData: ToDoData)
        fun onEditTaskBtnClicked(toDoData: ToDoData)
    }

    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) {
        with(holder){
            with(list[position]){
                binding.todoTask.text=this.task

                binding.deleteTask.setOnClickListener{
                    listener?.onDeleteTaskBtnClicked(this)

                }
                binding.editTask.setOnClickListener{
                    listener?.onEditTaskBtnClicked(this)

                }
            }
        }

    }
}