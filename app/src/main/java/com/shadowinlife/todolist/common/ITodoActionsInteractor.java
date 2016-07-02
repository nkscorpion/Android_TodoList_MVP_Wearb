package com.shadowinlife.todolist.common;

import com.shadowinlife.todolist.models.Entity.Todo;

/**
 * Created by shadowinlife on 16/7/2.
 */
public interface ITodoActionsInteractor {
    /**
     * Delete To.Do in the database
     *
     * @param todo
     */
    void delete(Todo todo);
}
