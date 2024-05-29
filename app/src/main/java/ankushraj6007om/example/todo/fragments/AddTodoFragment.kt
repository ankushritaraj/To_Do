package ankushraj6007om.example.todo.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowId
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import ankushraj6007om.example.todo.databinding.FragmentAddTodoBinding
import ankushraj6007om.example.todo.utils.ToDoData
import com.google.android.material.textfield.TextInputEditText

class AddTodoFragment : DialogFragment() {
    private lateinit var binding: FragmentAddTodoBinding
    private lateinit var listener: DialogNextBtnClickListener
    private var toDoData:ToDoData?=null


    fun setListener(Listener:HomeFragment){
        this.listener = listener
    }
    companion object{
        const val TAG ="AddTodoFragment"

        @JvmStatic
        fun newInstance(taskId: String,todo: String)=AddTodoFragment().apply {
            arguments=Bundle().apply {
                putString("taskIed",taskId)

            }
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentAddTodoBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (arguments!=null){
            toDoData=ToDoData(
                arguments?.getString("taskId").toString(),
                arguments?.getString("task").toString())

            binding.todoEt.setText(toDoData?.task)

        }

        registerEvents()
    }
    private fun registerEvents(){
        binding.todoNextBtn.setOnClickListener {
            val todoTask=binding.todoEt.text.toString()
            if(todoTask.isNotEmpty()){
                if (toDoData==null){
                    listener.onSaveTask(todoTask,binding.todoEt)
                }else{
                    toDoData?.task=todoTask
                    listener.onUpdateTask(toDoData!!,binding.todoEt)
                }

            }else{
                Toast.makeText(context,"Please type your task",Toast.LENGTH_SHORT).show()
            }
        }
        binding.todoClose.setOnClickListener {
            dismiss()
        }
    }



    interface DialogNextBtnClickListener{
        fun onSaveTask(todo: String,todoEt:TextInputEditText)
        fun onUpdateTask(toDoData: ToDoData,todoEt: TextInputEditText)
    }

}
