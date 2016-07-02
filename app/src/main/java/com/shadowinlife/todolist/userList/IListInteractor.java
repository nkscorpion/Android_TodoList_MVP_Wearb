package com.shadowinlife.todolist.userList;

import com.shadowinlife.todolist.common.ITodoActionsInteractor;
import com.shadowinlife.todolist.models.Entity.Todo;

import java.util.ArrayList;

/**
 * Created by shadowinlife on 16/7/2.
 */
public interface IListInteractor extends ITodoActionsInteractor {
    /**
     * Get all To.Dos from database
     *
     * @return ArrayList
     */
    ArrayList<Todo> get();

    /**
     * Update old To.Do
     *
     * @param todo
     */
    void update(Todo todo);
}
