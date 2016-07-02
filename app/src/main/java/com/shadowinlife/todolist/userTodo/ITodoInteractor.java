package com.shadowinlife.todolist.userTodo;

import com.shadowinlife.todolist.common.ITodoActionsInteractor;
import com.shadowinlife.todolist.models.Entity.Todo;

/**
 * Created by shadowinlife on 16/7/2.
 */
public interface ITodoInteractor extends ITodoActionsInteractor {

    /**
     * Insert new To.Do in the database
     *
     * @param todo
     */
    void create(Todo todo);

}
