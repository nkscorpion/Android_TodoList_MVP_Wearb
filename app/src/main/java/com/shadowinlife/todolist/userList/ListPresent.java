package com.shadowinlife.todolist.userList;

import android.content.Context;
import android.widget.Toast;

import com.shadowinlife.todolist.models.Entity.Todo;
import com.shadowinlife.todolist.models.TodoRepository;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by shadowinlife on 16/7/6.
 */
public class ListPresent implements IListPresent {
    private IListView view;
    private IListInteractor interactor;
    private Context context;


    public ListPresent(IListView view, Context context) {
        this.view = view;
        this.interactor = new TodoRepository(context.getContentResolver());
        this.context = context;
    }

    @Override
    public void refreshSession() {
        ArrayList<Todo> todoList = new ArrayList<>();
        Todo test1 = new Todo();
        test1.setTitle("test1");
        test1.setDescription("aa");
        test1.setEdited(new Date().getTime());
        test1.setCompleted(false);
        Todo test2 = new Todo();
        test2.setTitle("test2");
        test2.setEdited(new Date().getTime());
        test2.setCompleted(false);
        Todo test3 = new Todo();
        test3.setTitle("test3");
        test3.setEdited(new Date().getTime());
        test3.setCompleted(false);
        todoList.add(test1);
        todoList.add(test2);
        todoList.add(test3);
        // interactor.get();
        view.setTodos(todoList);
        view.notifyListDataSetChanged();
    }

    @Override
    public void onAddTodoButtonClick() {
        //TODO jump to add todoactivy
    }

    @Override
    public void onClickTodoItemToEdit(Todo todo) {

    }

    @Override
    public void onLongClickTodoItem(Todo todo) {

    }

    @Override
    public void updateTodoIsCompleted(Todo todo, boolean completed, int position) {
        todo.setCompleted(completed);
        interactor.update(todo);
        ArrayList<Todo> todoList = interactor.get();
        view.notifyListItemRemoved(position);
        view.setTodos(todoList);
        for (Todo todoObject : todoList) {
            if (todoObject.getId() == todo.getId()) {
                view.notifyListItemInserted(todoList.indexOf(todoObject));
            }
        }
    }

    @Override
    public void delete(Todo todo) {
        interactor.delete(todo);
        view.setTodos(interactor.get());
        view.notifyListDataSetChanged();
        Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show();
    }
}
